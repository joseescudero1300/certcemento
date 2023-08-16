package bo.gob.aduana.web;

import bo.gob.aduana.archivo.Documento;

import bo.gob.aduana.archivo.VerificacionCert;
import bo.gob.aduana.bd.Sql;
import bo.gob.aduana.bean.UsuarioForm;
import bo.gob.aduana.consulta.ConsultaForm;

import bo.gob.aduana.entidades.web.Importacion;

import bo.gob.aduana.system.Seguridad;

import java.io.FileInputStream;
import java.io.IOException;

import java.io.InputStream;

import java.util.List;

import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
public class SolicitudCertificacionAction extends Action {
    String ruta = "/u03/oracle/user_projects/data/certificacion/app.properties";
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws IOException, ServletException {
        boolean ok = false;
       
        ActionMessages mensaje = new ActionMessages();
        VerificacionCert doc=new VerificacionCert();
        UsuarioForm bean = (UsuarioForm)form;
        UsuarioForm r=new UsuarioForm();
        if (bean.getOpcion().equals("generar")) {
            
            Sql sql = null;
            try {
                sql = new Sql();
                Seguridad seg;
                try {
                    seg= new Seguridad(ruta);
                } catch (Exception e) {
                    mensaje.add("message.error", new ActionMessage("message.error", e.getMessage().toString()));
                    saveErrors(request, mensaje);
                    return mapping.findForward("consulta.index");
                }
                String Usu_nro_de=(seg.decrypt(bean.getUsu_nro_ident_en()));
                String Usu_tipo_de=(seg.decrypt(bean.getUsu_tipo_doc_en()));
                bean.setUsu_nro_ident(Usu_nro_de);
                bean.setUsu_tipo_doc(Usu_tipo_de);
                bean.setTipo_certificacion("N");      
                r=sql.registraCertificacion(bean);
                if (r.getRespuesta().equals("OK"))
                {
                    String cod_cert=r.getMensaje();                    
                    try
                    {
                        String correo="NOK";
                    InputStream arch=doc.genera_documento(cod_cert,mapping, form, request,
                                 response,correo);
                        sql = new Sql();
            sql.envia_email(bean.getUsu_mail(),"certificaciones@aduana.gob.bo","ADUANA NACIONAL - CERTIFICACION DE NO IMPORTACION DE CEMENTO PORTLAND Y/O PUZOLANICO","Archivo enviado ",
                                                       "certificado.pdf","application/pdf",arch,"N"); 
                    }
                    catch(Exception e)
                    {
                        System.out.print("Error email:"+ e.getMessage());
                    }
                    String textoEncryptado="";
                    try{
                       textoEncryptado =seg.encrypt(cod_cert);
                        System.out.print(seg.decrypt(textoEncryptado));
                    } catch(Exception e){
                       textoEncryptado="";
                    }
                    String url_encriptado ="VerificacionCert.do?id="+textoEncryptado;
                    String url_ref="<a class=\"lnkVistaPrevia\" data-fancybox-type=\"fancybox fancybox.iframe\" href=\""+url_encriptado+"\"> Ver Documento</a>";
                    mensaje.add("message.success",
                                new ActionMessage("message.success", "Se gener&oacute; el Certificado de No Importaci&oacute;n Cemento PORTLAND y/o PUZOL&Aacute;NICO, mismo se remiti&oacute; a su correo electr&oacute;nico" +
                        " o puede descargarlo aqu&iacute;: "+url_ref));
                }
                    else
                    {
                            mensaje.add("message.error", new ActionMessage("message.error", r.getMensaje()));
                    }                                             
            } catch (Exception e) {
                mensaje.add("message.error", new ActionMessage("message.error", e.getMessage()));
            } finally {
                sql.dispose();
            }          
            
            request.setAttribute("usuario", bean);
           // mensaje.add("message.error", new ActionMessage("message.error", "TODO CORRECTO"));
            saveErrors(request, mensaje);
        } 
        else
        if (bean.getOpcion().equals("volver")) {
            return mapping.findForward("consulta.index");
        }
      return mapping.findForward("solicitud.certificado");
  }
}
