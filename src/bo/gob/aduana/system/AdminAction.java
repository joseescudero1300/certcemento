package bo.gob.aduana.system;

import bo.gob.aduana.bean.UsuarioForm;

import cliente.ClaseEnvio;
import cliente.ServiciosUsuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;


public class AdminAction extends MappingDispatchAction {
    
    public ActionForward log(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        /*Respuesta<LogBean[]> res = null;
        if (request.getParameter("filter") != null) {
            String nivel = request.getParameter("nivel");
            String mensaje = request.getParameter("mensaje");
            String desde = request.getParameter("desde");
            String hasta = request.getParameter("hasta");            
        }
        
        if (res.getCodigo() == 1) {
            request.setAttribute("logs", res.getResultado());
        } else {
            request.setAttribute("ERROR", res.getMensaje());
        }*/
        return mapping.findForward("admin.log");
    } 
    
    public ActionForward myaccount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        
        return mapping.findForward("admin.myaccount");
    }
    
    public ActionForward changePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws IOException, ServletException {
        
        return null;
    }
}
