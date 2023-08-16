package bo.gob.aduana.tecnico;

import bo.gob.aduana.librerias.App;
import bo.gob.aduana.system.AuthForm;

import bo.gob.aduana.system.ClaseSession;

import bo.gob.aduana.system.Util;

import cliente.ClaseEnvio;
import cliente.ServiciosUsuario;
import cliente.bean.ClaseOpcion;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.MappingDispatchAction;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;

public class LoginAction extends MappingDispatchAction {    

    protected ActionForward loginUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                      HttpServletResponse response, boolean ajax) throws IOException,
                                                                                         ServletException {

        AuthForm usuario = (AuthForm)form;
        ActionMessages errors = new ActionMessages();

        ServiciosUsuario serviciosUsuario = new ServiciosUsuario();
        ClaseEnvio claseEnvio = serviciosUsuario.getServiciosUsuario();
        request.getSession().setAttribute("OK", null);
        try {
            // Inicio Autentificacion Virtual
            String xmlv = "";
            String vparam = request.getParameter("vsessionid");
            DocumentBuilderFactory factoryv = DocumentBuilderFactory.newInstance();
            DocumentBuilder builderv = factoryv.newDocumentBuilder();
            if (vparam != null) {
                try {
                    xmlv = claseEnvio.fUsuarioVirtual(vparam);
                    Document doc = builderv.parse(new InputSource(new StringReader(xmlv)));
                    doc.getDocumentElement().normalize();
                    usuario.setUsuario(getTagXML(doc, "Usuario"));
                    usuario.setClave(getTagXML(doc, "Clave"));
                } catch (Exception ex) {
                    ;
                }
            }

            String xml =
                claseEnvio.listaOpcionesXML(usuario.getUsuario().toUpperCase(), usuario.getClave(), App.APP_NAME);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xml)));
            doc.getDocumentElement().normalize();

            ClaseSession cs = new ClaseSession();
            cs.setUsuario(usuario.getUsuario().toUpperCase());
            cs.setNombreUsuario(Util.capitalizeString(getTagXML(doc, "Usuario")));
            cs.setPerfil(getTagXML(doc, "Perfil"));
            cs.setNit(getTagXML(doc, "Nit"));
            cs.setNomGerencia(getTagXML(doc, "NombreGerencia"));
            cs.setNomUnidad(getTagXML(doc, "NombreUnidad"));
            cs.setCorreo(getTagXML(doc, "Correo"));
            cs.setCi(getTagXML(doc, "NroDocumento"));
            cs.setAduana("ALL");
            Calendar cal = Calendar.getInstance();
            cs.setGestion((cal.get(Calendar.YEAR)));
         
            usuario.getOpciones().clear();
            usuario.setOpciones(new ArrayList());
            NodeList OpcionesLista =
                ((Element)doc.getElementsByTagName("Opciones").item(0)).getElementsByTagName("Opcion");
            String vAccion ="";
            if (OpcionesLista != null) {
                for (int i = 0; i < OpcionesLista.getLength(); i++) {
                    Element itemOpcion = (Element)OpcionesLista.item(i);
                    ClaseOpcion bean = new ClaseOpcion();
                    bean.setCodopc(getTagXML(itemOpcion, "Codigo"));
                    bean.setCodant(getTagXML(itemOpcion, "NivelSuperior"));
                    bean.setAccion(getTagXML(itemOpcion, "Accion"));                    
                    String name = getTagXML(itemOpcion, "Descripcion");
                    bean.setDesc(bean.getCodant().equals("0") ? Util.capitalizeString(name) : name);
                    usuario.getOpciones().add(bean);
                    if( i == 0 && bean.getCodant().equals("0") ){
                        vAccion = bean.getAccion();
                    }else{
                        if( bean.getCodant().equals("0") ){
                            vAccion = vAccion + " " + bean.getAccion();
                        }
                    }                    
                }
            }
            
           
            
            request.setAttribute("URI", "Dashboard");
            request.getSession().setAttribute("opciones", usuario.getOpciones());
            request.getSession().setAttribute("usuario", cs);
            
            request.getSession().setAttribute("desc_aduana", usuario.getDescAduana());
            request.getSession().setAttribute("iconsMenu", vAccion);
            request.getSession().setAttribute("tipo_persona", "TECNICO");
            request.getSession().setAttribute("descripcion", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class=\"fa fa-credit-card\"></i>&nbsp; Perfil: "+cs.getPerfil());
            // Datos para el sistema de Notificaciones
            String USER = usuario.getUsuario().toUpperCase();

            request.getSession().setAttribute("USER", USER);
            request.getSession().setAttribute("APP", App.APP_NAME);
            // Fin de los datos para Notificaciones

            App.USER = USER;
            App.ADUANA = usuario.getAduana();
            request.getSession().setAttribute("ERROR", null);

        } catch (Exception e) {
            errors.add("message", new ActionMessage("message.error", "El usuario y/o contraseña no son válidos."));
            saveErrors(request, errors);
            App.USER = usuario.getUsuario();
            return mapping.findForward("index");
        }
        if (ajax) {
            response.getWriter().write("OK");
            return null;
        } else {
            return mapping.findForward("dashboard.index");
        }

    }

    public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws IOException, ServletException {


        init(request);

        return mapping.findForward("index2");
    }

    public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws IOException, ServletException {

        return loginUser(mapping, form, request, response, false);
    }

    public ActionForward loginAjaxForm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                       HttpServletResponse response) throws IOException, ServletException {

        init(request);

        return mapping.findForward("usuario.loginAjax");
    }

    public ActionForward loginAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                   HttpServletResponse response) throws IOException, ServletException {

        return loginUser(mapping, form, request, response, true);
    }

    public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws IOException, ServletException {

        request.getSession().removeAttribute("APP");
        request.getSession().removeAttribute("USER");
        request.getSession().removeAttribute("TOKEN");
        request.getSession().removeAttribute("usuario");
        request.getSession().removeAttribute("control");
        request.getSession().removeAttribute("opciones");
        request.getSession().removeAttribute("iconsMenu");

        return mapping.findForward("index");
    }

    public ActionForward cambiar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws IOException, ServletException {

        String direccionar = "ok";
        return mapping.findForward(direccionar);
    }

    protected void init(HttpServletRequest request) throws IOException {
        Properties prop = new Properties();
        InputStream in = new FileInputStream(App.PROPERTIES);
        prop.load(in);
        // Datos del sistema
        App.APP_NAME = prop.getProperty("system.app");
        App.JDBC = prop.getProperty("system.jdbc");
        App.VERSION = prop.getProperty("system.version");
        App.VERSION_ASSETS = prop.getProperty("system.version.assets");

        request.getSession().setAttribute("VERSION", App.VERSION);
        request.getSession().setAttribute("VERSION_ASSETS", App.VERSION_ASSETS);

        in.close();
    }

    private String getTagXML(Document doc, String tag) {
        try {
            NodeList listaNodosHijos = doc.getElementsByTagName(tag);
            return listaNodosHijos.item(0).getFirstChild().getNodeValue();
        } catch (Exception e) {
            return "";
        }
    }

    private String getTagXML(Element doc, String tag) {
        try {
            return (doc.getElementsByTagName(tag).item(0).getFirstChild().getNodeValue());
        } catch (Exception e) {
            return "";
        }
    }
}
