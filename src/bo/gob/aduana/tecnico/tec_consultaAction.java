package bo.gob.aduana.tecnico;

import bo.gob.aduana.bd.Sql;

import bo.gob.aduana.bean.ConsultaCerForm;

import bo.gob.aduana.bean.UsuarioForm;

import bo.gob.aduana.librerias.Ctte;

import bo.gob.aduana.system.ClaseSession;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.MappingDispatchAction;

import servicios.DireccionIP;
import servicios.LogBitacora;

public class tec_consultaAction extends MappingDispatchAction {
    public ActionForward inicio(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws IOException, ServletException {
        ActionMessages mensaje = new ActionMessages();              
        Sql sql = null;
        ConsultaCerForm bean=(ConsultaCerForm)form;
        DireccionIP ip = new DireccionIP();
        LogBitacora log;
        
        if (bean.getOpcion() != null)
            if (bean.getOpcion().equals("buscar")) {
                try {
                    sql = new Sql();   
                    ClaseSession cs = ( ClaseSession ) request.getSession().getAttribute( "usuario" );
                    log = new LogBitacora(String.valueOf(1), 
                                          "Consulta Certificacion", 
                                          "FECHA DESDE: " + bean.getFechaIni() + ";FECHA HASTA: " +bean.getFechaFin() + ";",
                                          ip.getIpAddr(request),
                                          cs.getUsuario(),
                                          1);
                    log.enviaLog(log);
                    
                    List<UsuarioForm> lista_cert=sql.obtieneCertificados(bean);
                    //se tendria que buscar por el nit si es empresa de transporte o aerea
                    //List<Aduana> lista_aduana = sql.obtieneAduana("T");
                    request.setAttribute("lista_cert", lista_cert);            
                } catch (Exception e) {
                    mensaje.add("message.error", new ActionMessage("message.error", e.getMessage()));            
                    saveErrors(request, mensaje);
                } finally {
                    sql.dispose();
                }  
            }
           
        return mapping.findForward("ok");
    }
}
