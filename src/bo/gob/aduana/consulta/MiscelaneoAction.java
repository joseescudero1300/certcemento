package bo.gob.aduana.consulta;

import anb.modbnk.archivo_xml;
import anb.modbnk.wsModbnk;

import bo.gob.aduana.bd.Sql;
import bo.gob.aduana.bean.UsuarioForm;
import bo.gob.aduana.entidades.web.Importacion;
import bo.gob.aduana.system.Seguridad;

import java.io.IOException;

import java.sql.CallableStatement;
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

import nl.captcha.Captcha;

import oracle.jdbc.internal.OracleTypes;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.MappingDispatchAction;

public class MiscelaneoAction extends MappingDispatchAction {

    String ruta = "/u03/oracle/user_projects/data/certificacion/app.properties";

    public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws IOException, ServletException {
        UsuarioForm bean = (UsuarioForm)form;
        ActionMessages mensaje = new ActionMessages();
     
      
        if (bean.getOpcion() != null)          
                 if (bean.getOpcion().equals("miscelaneo")) {
                    request.setAttribute("usuario", bean);
                   String res="",mens="";
                    try {
                        archivo_xml ar_xml = new archivo_xml();  
                                    wsModbnk servicio = new wsModbnk();  
                                    String concepto = "158";
                                    String nro_doc = bean.getUsu_nro_ident();
                                    String tipo_doc = bean.getUsu_tipo_doc();
                                    String usuario = "PUBLICO";
                                    String nombres = bean.getUsu_nombre();
                                    String apellidos = bean.getUsu_ap_pat()+" "+bean.getUsu_ap_mat();
                                    String cod_dep = bean.getDep();
                                    String direccion = bean.getDireccion();
                                    String referencia = "CERTIFICACION NO ADEUDO TRIBUTARIO";
                                   
                                   
                                    String email = bean.getUsu_mail();
                                    String razon_social = bean.getUsu_razon_social();
                                    String tipo_usu = bean.getUsu_tipo_usu();
                                    
                                    String aduana="";
                                   String UFV="";
                                     String estado="";
                                     String msg="";
                                     String cod_form="";
                        estado="OK";
                                    Date date = new Date();
                                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                    String fecha =dateFormat.format(date);
                        DataSource ds2 = null;
                                       Connection cn2 = null;
                                       Statement st2 = null;
                                       String subasta = "";
                                       ResultSet rs=null;  
                                         try
                                              {
                                                
                                                InitialContext ic2 = new InitialContext();
                                                        ds2 = (DataSource)ic2.lookup("jdbc/certificacion");
                                                        cn2 = (Connection)ds2.getConnection();
                                                        st2 = cn2.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                                            
                                                        CallableStatement call = null;
                                                        call = cn2.prepareCall( "{ ? = call pkg_certificacion.memoriza_pago( ?,?,?, ?,?,?, ?,?,?, ?,?,?, ?) }" );
                                                        call.registerOutParameter( 1, OracleTypes.VARCHAR );
                                                        call.setString(2,nro_doc );          
                                                        call.setString(3,tipo_doc ); 
                                                        call.setString(4,tipo_usu ); 
                                                        call.setString(5,nombres ); 
                                                        call.setString(6,bean.getUsu_ap_pat() );
                                                         call.setString(7,bean.getUsu_ap_mat() );
                                                         call.setString(8,razon_social );
                                                         call.setString(9,bean.getExtencion() );
                                                        call.setString(10,bean.getFecNacimiento() );
                                                        call.setString(11,email );
                                                        call.setString(12,"PUBLICO" );
                                                        call.registerOutParameter( 13, OracleTypes.VARCHAR );
                                                        call.registerOutParameter( 14, OracleTypes.VARCHAR );
                                                        call.execute();     
                                                        res=(String) call.getObject( 1 );                     
                                                         mens=(String) call.getObject( 13 );   //monto a ser pagado                                              
                                                       
                                         } catch (SQLException e) {
                                                throw new Exception("Lotes: " + e.getMessage() + "(" + e.getErrorCode() + ")");
                                            } finally {
                                                try {
                                                    if (rs != null) {
                                                        rs.close();
                                                    }
                                                    rs = null;
                                                    if (cn2 != null) {
                                                        cn2.close();
                                                    }
                                                    cn2 = null;
                                    
                                                } catch (SQLException e) {
                                                    ;
                                                }
                                            }             
                        if (!res.equals("OK"))
                        {
                                mensaje.add("message.error", new ActionMessage("message.error", res));
                                saveErrors(request, mensaje);
                                return mapping.findForward("consulta.index"); 
                            }
                    
                        try
                             {
                               
                               InitialContext ic2 = new InitialContext();
                                       ds2 = (DataSource)ic2.lookup("jdbc/certificacion");
                                       cn2 = (Connection)ds2.getConnection();
                                       st2 = cn2.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                           
                                       CallableStatement call = null;
                                       call = cn2.prepareCall( "{ ? = call pkg_certificacion.aduana_departamento( ?,?) }" );
                                       call.registerOutParameter( 1, OracleTypes.VARCHAR );
                                       call.setString(2,cod_dep );          
                                       call.registerOutParameter( 3, OracleTypes.VARCHAR );
                                       call.execute();     
                                       aduana=(String) call.getObject( 1 );                     
                                        UFV=(String) call.getObject( 3 );   //monto a ser pagado 
                        
                        } catch (SQLException e) {
                               throw new Exception("Lotes: " + e.getMessage() + "(" + e.getErrorCode() + ")");
                           } finally {
                               try {
                                   if (rs != null) {
                                       rs.close();
                                   }
                                   rs = null;
                                   if (cn2 != null) {
                                       cn2.close();
                                   }
                                   cn2 = null;
                        
                               } catch (SQLException e) {
                                   ;
                               }
                           }
                            
                        String xml=ar_xml.getXml_ar();
                                  //apellidos="";//el servicio solo acepta 35
                                  //mensaje="aumentar caracteres de mensaje";
                                   String nombreOrazonSocial="";
                                   if (tipo_usu.equals("J"))
                                        nombreOrazonSocial=razon_social;
                                    else
                                        nombreOrazonSocial=nombres+" "+apellidos;
                                  if (nombreOrazonSocial.length()>35)
                                    nombreOrazonSocial=nombreOrazonSocial.substring(0,35);
                                if (direccion.length()>35)
                                    direccion=direccion.substring(0,35);
                              
                       msg="Se gener&oacute; correctamente el n&uacute;mero de formulario miscel&aacute;neo codfmiscelaneo para la certificaci&oacute;n de NO IMPORTACI&Oacute;N CEMENTO PORTLAND Y/O PUZOL&Iacute;NICO, "+
                                            "con este n&uacute;mero usted debe apersonarse ante cualquier sucursal del Banco Uni&oacute;n S.A. o a trav&eacute;s "+
                                            "de la plataforma de UNINET, para realizar el pago de Bs. "+UFV+", tomando en cuenta que la vigencia "+
                                            "del n&uacute;mero generado ser&aacute; hasta horas 23:59 de hoy "+fecha+"."; 
                      
                        
                                    if (msg.length()>700)
                                    msg=msg.substring(0,700);
                                 
                                    xml=ar_xml.inserta(xml,"tipoDocumento",tipo_doc);
                                    xml=ar_xml.inserta(xml,"nroDocumento",nro_doc);
                                    xml=ar_xml.inserta(xml,"nombreOrazonSocial","<![CDATA["+nombreOrazonSocial+"]]>");
                                    xml=ar_xml.inserta(xml,"direccion","<![CDATA["+direccion+"]]>");
                                    xml=ar_xml.inserta(xml,"codigoAduana",aduana);
                                    xml=ar_xml.inserta(xml,"codigoUsuario",usuario);
                                    xml=ar_xml.inserta(xml,"correoElectronico",email);
                                    xml=ar_xml.inserta(xml,"mensajeParaCorreo","<![CDATA["+msg+"]]>");            
                                    xml=ar_xml.inserta(xml,"codigoConcepto",concepto);
                                    xml=ar_xml.inserta(xml,"referencia",referencia);
                                    xml=ar_xml.inserta(xml,"importe",UFV);
                        String respuesta=servicio.generaFormulario(xml);
                                    //String respuesta="";//servicio.generaFormulario(xml);
                                    String resc="";
                                    
                                    if (respuesta.indexOf("NOK")>-1)
                                    {
                                        estado="NOK";
                                        msg=respuesta.substring(4);
                                        mensaje.add("message.error", new ActionMessage("message.error", msg));
                                        saveErrors(request, mensaje);
                                    }
                                    else
                                    {
                                        String codError=ar_xml.obtiene(respuesta,"codError");
                                        String descError=ar_xml.obtiene(respuesta,"descError");
                                        String FechaRegFormMisc="";
                                        cod_form="";
                                        if (codError.equals("0"))
                                        {
                                          
                                            cod_form=ar_xml.obtiene(respuesta,"codigoFormMisc");                                                        
                                                    /*msg="* Su formulario para pago de certificaci&oacute;n de no Adeudo fu&eacute; generado correctamente con el siguiente c&oacute;digo <strong>"+cod_form+" (Pago  miscel&aacute;neo)</strong><br> "+
                                                    "* Deber&aacute; apersonarse en cualquier sucursal  del Banco Uni&oacute;n S.A. o a trav&eacute;s de la plataforma de UNINET, <strong>para efectuar el pago de Bs. "+UFV+", valor equivalente a 50 UFV </strong><br>" +
                                                    "* Tomar en cuenta que la vigencia de este formulario es hasta las 23:59 del d&iacute;a de <strong>HOY</strong> ("+fecha+").";
                                                    */
                                                    msg="* Con este n&uacute;mero <strong>"+cod_form+" (c&oacute;digo  miscel&aacute;neo)</strong> usted debe realizar el <strong>pago de Bs. "+UFV+"</strong> (valor equivalente a 50 UFV's) por el concepto de " +
                                                        "emisi&oacute;n de la certificaci&oacute;n, en cualquier sucursal  del Banco Uni&oacute;n S.A. o a trav&eacute;s de la plataforma de UNINET.<br>" +
                                                    "* Tomar en cuenta que la vigencia del c&oacute;digo generado es hasta las 23:59 del d&iacute;a de <strong>HOY</strong> ("+fecha+").";
                                                    
                                            mensaje.add("message.success",
                                                        new ActionMessage("message.success", msg));
                                            saveErrors(request, mensaje);
                                            
                                        }
                                        else
                                        {
                                            msg=descError;
                                            mensaje.add("message.error", new ActionMessage("message.error", msg));
                                            saveErrors(request, mensaje);
                                        }
                                    }
                    } catch (Exception e) {
                        mensaje.add("message.error", new ActionMessage("message.error", e.getMessage().toString()));
                        saveErrors(request, mensaje);
                        return mapping.findForward("consulta.index");
                    }
                   return mapping.findForward("consulta.index"); 
                }    
        return mapping.findForward("consulta.index");
    }
  
}