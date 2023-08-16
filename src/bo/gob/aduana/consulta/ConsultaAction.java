package bo.gob.aduana.consulta;

import bo.gob.aduana.bd.Sql;


import bo.gob.aduana.bean.UsuarioForm;
import bo.gob.aduana.entidades.web.Importacion;
import bo.gob.aduana.system.Seguridad;

import java.io.IOException;

import java.io.InputStream;
import java.io.StringReader;

import java.sql.CallableStatement;
import java.sql.Clob;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.sql.DataSource;

import javax.xml.parsers.DocumentBuilderFactory;

import nl.captcha.Captcha;

import oracle.jdbc.driver.OracleConnection;

import oracle.jdbc.internal.OracleTypes;

import oracle.sql.CLOB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.MappingDispatchAction;
import anb.modbnk.*;

import bo.gob.aduana.archivo.VerificacionCert;

public class ConsultaAction extends MappingDispatchAction {

    String ruta = "/u03/oracle/user_projects/data/certificacion/app.properties";

    public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws IOException, ServletException {
        ConsultaForm bean = (ConsultaForm)form;
        ActionMessages mensaje = new ActionMessages();
        Seguridad seg;
        request.setAttribute("cantidad", 1);
        try {
            seg= new Seguridad(ruta);
        } catch (Exception e) {
            mensaje.add("message.error", new ActionMessage("message.error", e.getMessage().toString()));
            saveErrors(request, mensaje);
            request.setAttribute("tab", "2"); 
            return mapping.findForward("publico.principal");
        }
      
        if (bean.getOpcion() != null)
            if (bean.getOpcion().equals("buscar")) {
                
                Captcha captcha = (Captcha)request.getSession().getAttribute(Captcha.NAME);

                if (captcha == null || !captcha.isCorrect(bean.getCaptcha())) {
                    mensaje.add("message.error", new ActionMessage("message.error", "C&oacute;digo de imagen es incorrecto"));
                    saveErrors(request, mensaje);
                    request.setAttribute("tab", "2"); 
                    return mapping.findForward("publico.principal");
                }
                request.getSession().removeAttribute(Captcha.NAME);
                Sql sql = null;
                try {
                    sql = new Sql();
                    bean.setUsu_nro_ident(bean.getUsu_nro_ident().trim());
                    List<Importacion> resp = sql.consultaImportacion(bean);
                    if (resp != null) {
                        String re = resp.get(0).getRespuesta();
                        if (re.equals("DEUDA")) {
                        String detalle="";
                            for(int i=0;i<resp.size();i++)
                            {
                                    Importacion fila = new Importacion();
                                                       fila=resp.get(i); 
                                    detalle=detalle+fila.getAduana_des()+"|"+fila.getNro_registro()+"|"+fila.getFecha_val()+"|"+
                                            fila.getNro_doc()+"|"+fila.getRazon_social()+"|LF|\n";                                   
                                }                         
                            UsuarioForm r=new UsuarioForm();
                            UsuarioForm f=new UsuarioForm();
                            f.setUsu_nro_ident(bean.getUsu_nro_ident());
                            f.setUsu_tipo_doc(bean.getUsu_tipo_doc());
                            f.setGestion(bean.getGestion());
                            f.setAduana(bean.getAduana());
                            f.setNumero(bean.getNumero());
                            f.setTipo_certificacion("I");                        
                            f.setDetalle(detalle);
                            f.setUsu_tipo_usu(bean.getUsu_tipo_usu());
                            sql = new Sql();
                            r=sql.registraCertificacion(f);
                            if (r.getRespuesta().equals("OK"))
                            {
                                String cod_cert=r.getMensaje();      
                                try
                                {
                                   String email="NOK";
                                    VerificacionCert doc=new VerificacionCert();
                                InputStream arch=doc.genera_documento(cod_cert,mapping, form, request,
                                             response,email);
                                    email= (String)request.getSession().getAttribute("email");
                                if (!email.equals("NOK"))    
                                {
                                        sql = new Sql();
                                        sql.envia_email(email,"certificaciones@aduana.gob.bo","ADUANA NACIONAL - CERTIFICACION DE IMPORTACION DE CEMENTO PORTLAND Y/O PUZOLANICO ","Archivo enviado ",
                                                                       "certificado.pdf","application/pdf",arch,"I"); 
                                    }
                                    
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
                                String url_ref="<a class=\"lnkVistaPrevia\" data-fancybox-type=\"fancybox fancybox.iframe\" href=\""+url_encriptado+"\"> VER DOCUMENTO</a>";
                                mensaje.add("message.error",
                                            new ActionMessage("message.error", "La persona con Nro. de documento "+bean.getUsu_nro_ident()
                                                                               +" cuenta con Declaraciones Únicas de Importación asociadas a las partidas arancelarias  descritas en Resolución Ministerial MDPyEP/DESPACHO/N° 101 de 12/06/2019, " +
                                                                           " se emiti&oacute; el certificado correspondiente a su correo electr&oacute;nico, puede visualizarlo y descargarlo aqu&iacute;: "+url_ref));
                                saveErrors(request, mensaje);
                            }
                                else
                                {
                                        mensaje.add("message.error", new ActionMessage("message.error", r.getMensaje()));
                                }                            
                            request.setAttribute("lista", resp);    
                            request.setAttribute("usuario", f); 
                     
                            return mapping.findForward("publico.deuda");
                            
                        } else {
                            if (re.equals("NODEUDA")) {                    
                                try {                                    
                                    String encript_nro = seg.encrypt(bean.getUsu_nro_ident());
                                    String encript_tipo_doc = seg.encrypt(bean.getUsu_tipo_doc());    
                                    sql = new Sql();
                                    UsuarioForm f=new UsuarioForm();
                                    f=sql.obtienememo(bean);
                                    f.setUsu_nro_ident(bean.getUsu_nro_ident());
                                    f.setUsu_tipo_doc(bean.getUsu_tipo_doc());
                                    f.setUsu_nro_ident_en(encript_nro);
                                    f.setUsu_tipo_doc_en(encript_tipo_doc);
                                    if(bean.getUsu_tipo_doc().equals("CI"))
                                        f.setUsu_tipo_usu("N");
                                    else
                                        f.setUsu_tipo_usu("J");
                                    f.setGestion(bean.getGestion());
                                    f.setAduana(bean.getAduana());
                                    f.setNumero(bean.getNumero());
                                    request.setAttribute("usuario", f);
                                    if( f.getRespuesta().equals("NOK"))
                                    {
                                            return mapping.findForward("solicitud.certificadonodata");
                                        }
                                    else
                                    {
                                            return mapping.findForward("solicitud.certificado");
                                        }
                                   
                                } catch (Exception e) {
                                    mensaje.add("message.error", new ActionMessage("message.error", e.getMessage().toString()));
                                    saveErrors(request, mensaje);
                                    return mapping.findForward("consulta.index");
                                }                            
                               
                               
                            } else {
                                mensaje.add("message.error",
                                            new ActionMessage("message.error", resp.get(0).getMensaje()));
                            }
                        }
                    }
                    
                    else {
                        mensaje.add("message.error",
                                    new ActionMessage("message.error", "No se pudo realizar la consulta"));
                    }
                } catch (Exception e) {
                    mensaje.add("message.error", new ActionMessage("message.error", e.getMessage()));
                } finally {
                    sql.dispose();
                }
                saveErrors(request, mensaje);
            } 
        request.setAttribute("tab", "2"); 
        return mapping.findForward("publico.principal");
    }
  
}
