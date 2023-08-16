package bo.gob.aduana.system;

import cliente.bean.ClaseOpcion;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.tiles.TilesRequestProcessor;


public class AnbRequestProcessor extends TilesRequestProcessor {
    
    String URI = "";
    String parentURI = "";
    boolean ajax;

    @Override
    protected boolean processPreprocess(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        String message = "";
        String type = "";
        
        URI = "";
        parentURI = "";
        ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        String path = request.getServletPath();
        
        System.out.print("Path: " + path + " - Request type: " + (ajax? "AJAX" : "NORMAL"));
        
        if (path.equals("/index.do") || 
            path.equals("/logout.do") || 
            path.equals("/login.do") || 
            path.equals("/tecnico.do") || 
            path.startsWith("/login_tecnico") || 
            path.startsWith("/memo") ||            
            (ajax && (path.equals("/loginAjaxForm.do") || 
                      path.equals("/loginAjax.do"))) || 
            path.equals("/consulta.do") ||
            path.equals("/consultaTimbre.do")|| 
            path.startsWith("/consultaAdeudo")|| 
            path.startsWith("/solicitud")  || 
            path.startsWith("/CertificadoAdeudo") ||
            path.startsWith("/VerificacionCert") ||
            path.startsWith("/Ayuda") ||
            path.startsWith("/miscelaneo")
           
        ) {            
                System.out.println(" - URI Exception");
                return true;            
        }        
        System.out.println("session.getAttribute(USER):"+session.getAttribute("USER"));
        String vparam = request.getParameter("vsessionid");
        if ((session != null && session.getAttribute("USER") != null) || vparam != null ) {
            
            String objeto = Util.getObjeto(path.replace(".do", "").replace("/", ""));
            //Obtenemos los datos del padre y datos de la opción en Gusuarios
            obtenerDatosURI((List) session.getAttribute("opciones"), objeto);
            
            System.out.print(" - Nombre URI: " + (URI.length() > 0 ? URI : "Sin URI"));
            //Valida si existe la URI, si no es así quiere decir que no tiene permisos de acceso
            if (!URI.equals("") || objeto.equals("dashboard") || objeto.startsWith(objeto)) {
                System.out.println(" - Con permisos para el objeto: " + objeto);
                // Parámetros Globales para la sección donde se ingrese
                request.setAttribute("URI", path.equals("/dashboard.do") ? "Dashboard" : URI);
                request.setAttribute("ACCION", objeto);
                request.setAttribute("parentURI", path.equals("/dashboard.do") ? "" : parentURI);
                               
                return true;
            } else {
                message = "No tiene los permisos necesarios para ver esta página";
                type = "ERROR_PERMISSION";
            }
        } else {                           
                message = "Su <strong>sesión ha expirado</strong>, por favor ingrese de nuevo.";
                type = "SESSION_EXPIRED";
        }
        System.out.println(" - ERROR: " + type);
        
        //Determina si la llamada request es AJAX o NORMAL
        request.setAttribute("message", message);
        if (ajax) {
            try {
                response.getWriter().write(type);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            Util.redirect(request, response, type.equals("SESSION_EXPIRED") ? "/index.do" : "/views/404.jsp");
        }
        
        return false;
    }
    
    public void obtenerDatosURI(List opciones, String uri) {
        
        try {
            Iterator<ClaseOpcion> iterator = opciones.iterator();
            ArrayList<ClaseOpcion> lista = new ArrayList<ClaseOpcion>();

            while (iterator.hasNext()) {
                ClaseOpcion bean = iterator.next();
                lista.add(bean);
                if (uri.equals(bean.getAccion())) {
                    URI = bean.getDesc();
                    ListIterator li = lista.listIterator(lista.size());
                    while (li.hasPrevious()) {
                        ClaseOpcion beanSave = (ClaseOpcion) li.previous();
                        if (beanSave.getCodant().equals("0")) {
                            parentURI = beanSave.getDesc();
                            break;
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
