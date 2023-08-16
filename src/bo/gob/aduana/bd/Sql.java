package bo.gob.aduana.bd;

import bo.gob.aduana.bean.ConsultaCerForm;
import bo.gob.aduana.bean.UsuarioForm;
import bo.gob.aduana.consulta.ConsultaForm;
import bo.gob.aduana.entidades.web.Importacion;

import bo.gob.aduana.system.Seguridad;

import java.io.InputStream;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

public class Sql extends Conexion {
    public Sql() throws Exception {
        super();
    }
   
    public UsuarioForm registraCertificacion(UsuarioForm bean)throws Exception 
    {
            UsuarioForm f=new UsuarioForm();
            String salida = "";
            CallableStatement call = null;
        try {       
            call = cn.prepareCall("{ ? = call pkg_certificacion.registra_certificado ( ?,?,?, ?,?,?, ?,?,?, ?,?,?, ?,?,?, ?,?,?,  ?,?)}");
            call.registerOutParameter(1, OracleTypes.VARCHAR);
            call.setString(2, bean.getUsu_nro_ident());
            call.setString(3, bean.getUsu_tipo_doc());
            call.setString(4, bean.getUsu_tipo_usu());
            call.setString(5, bean.getUsu_nombre());
            call.setString(6, bean.getUsu_ap_pat());
            call.setString(7, bean.getUsu_ap_mat());
            call.setString(8, bean.getUsu_razon_social());
            call.setString(9, bean.getDep());
            call.setString(10, bean.getFecNacimiento());
            call.setString(11, bean.getUsu_mail());
            call.setString(12, bean.getGestion());
            call.setString(13, bean.getAduana());
            call.setString(14, "R");
            call.setString(15, bean.getNumero());
            call.setString(16, bean.getTipo_certificacion());
            call.setString(17, bean.getOtro());
            call.setString(18, bean.getDetalle());
            call.setString(19, "PUBLICO");
            call.registerOutParameter(20, OracleTypes.VARCHAR);
            call.registerOutParameter(21, OracleTypes.VARCHAR);
            call.execute();
            String respuesta = (String)call.getObject(20);
            String mensaje = (String)call.getObject(21);
            f.setRespuesta(respuesta);
            f.setMensaje(mensaje);
        } catch (SQLException e) {
            System.out.println("Registro de Certificado: " + e.getMessage());
            throw new Exception(e.toString());
        } finally {
            try {
                call.close();
                dispose();
            } catch (SQLException ex) {
                System.out.println("Registro de Certificado: finally catch: " + ex.getMessage());
            }
        }
            return f;
    }
    
    public UsuarioForm obtieneCertificado(String cod)throws Exception 
    {
            UsuarioForm f=new UsuarioForm();
            String salida = "";
            CallableStatement call = null;
        try {       
            call = cn.prepareCall("{ ? = call pkg_certificacion.obtiene_certificado (?,?,?)}");
            call.registerOutParameter(1, OracleTypes.CURSOR);
            call.setString(2, cod);            
            call.registerOutParameter(3, OracleTypes.VARCHAR);
            call.registerOutParameter(4, OracleTypes.VARCHAR);
            call.execute();
            String respuesta = (String)call.getObject(3);
            String mensaje = (String)call.getObject(4);
            if (respuesta.equals("OK"))
            {
                ResultSet rs = (ResultSet)call.getObject(1);
                if (!(rs == null || !rs.next())) {                    
                    do {                           
                        f.setCodigo(rs.getString("cer_cod"));     
                        f.setUsu_nro_ident(rs.getString("cer_nro_doc"));
                        f.setUsu_tipo_doc(rs.getString("cer_tipo_doc"));
                        f.setDep(rs.getString("cer_doc_extencion"));
                        f.setUsu_tipo_usu(rs.getString("cer_tipo_sujeto"));                     
                        f.setUsu_nombre(rs.getString("cer_nombre"));
                        f.setUsu_ap_pat(rs.getString("cer_apellido_pat"));
                        f.setUsu_ap_mat(rs.getString("cer_apellido_mat"));
                        f.setUsu_razon_social(rs.getString("cer_razon_social"));
                        f.setFecNacimiento(rs.getString("cer_fecha_nac"));
                        f.setUsu_mail(rs.getString("cer_correo"));
                        f.setUsu_razon_social(rs.getString("cer_razon_social"));
                        f.setFecNacimiento(rs.getString("cer_fecha_nac"));
                        
                        f.setGestion(rs.getString("cer_e_rcp_year"));
                        f.setAduana(rs.getString("cer_e_rcp_cuo"));
                        //f.set(rs.getString("cer_e_rcp_serial"));
                        f.setNumero(rs.getString("cer_e_rcp_nber"));
                        f.setTipo_certificacion(rs.getString("cer_tipo_certificacion"));
                        f.setOtro(rs.getString("cer_certificacion"));                        
                        f.setFecha_hora(rs.getString("fecha_reg"));
                        f.setRespuesta("OK");
                        f.setMensaje("Existe el certificado");  
                        f.setFecha(rs.getString("fecha"));
                        f.setDetalle_deuda(rs.getClob("detalle"));
                    } while (rs.next());                    
                } 
                else
                {
                        f.setRespuesta("NOK");
                        f.setMensaje("No existe el certificado");                   
                    }
                    
                
            }
            else
            {
                f.setRespuesta(respuesta);
                f.setMensaje(mensaje);
            }            
        } catch (SQLException e) {
            System.out.println("Registro de Certificado: " + e.getMessage());
            throw new Exception(e.toString());
        } finally {
            try {
                call.close();
                dispose();
            } catch (SQLException ex) {
                System.out.println("Registro de Certificado: finally catch: " + ex.getMessage());
            }
        }
        return f;
    }
        
    public List<Importacion> consultaImportacion(ConsultaForm bean) throws Exception {
        String salida = "";
        List<Importacion> registro = null;
        CallableStatement call = null;
        Importacion fila;
        /*
         * mensaje [0] = Mensaje
         * mensaje [1] = boton
         * */
        try {
         //   Esta consuilta debo modificar parq que busque importador
            call = cn.prepareCall("{ ? = call pkg_certificacion.verifica_importacion ( ?,?,?,?,?,?,  ?)}");
            call.registerOutParameter(1, OracleTypes.CURSOR);
            call.setString(2, bean.getUsu_nro_ident().trim());
            call.setString(3, bean.getUsu_tipo_doc());
            call.setString(4, bean.getGestion());
            call.setString(5, bean.getAduana());
            call.setString(6, bean.getNumero());                    
            call.registerOutParameter(7, OracleTypes.VARCHAR);
            call.registerOutParameter(8, OracleTypes.VARCHAR);
            call.execute();
            String respuesta = (String)call.getObject(7);
            String mensaje = (String)call.getObject(8);
            registro = new ArrayList<Importacion>();
            if (respuesta.equals("OK")) {
                ResultSet rs = (ResultSet)call.getObject(1);
                if (!(rs == null || !rs.next())) {
                    
                    do {
                        fila = new Importacion();
                       
                            fila.setNro_registro(rs.getString("dui"));
                            fila.setFecha_val(rs.getString("fec_validacion"));                            
                            fila.setNro_doc(rs.getString("nro_doc"));
                            fila.setRazon_social(rs.getString("nombre_razon"));
                            fila.setAduana_des(rs.getString("aduana"));                           
                            fila.setRespuesta("DEUDA");
                          //  fila.setDetalle(rs.getString("detalle"));
                            fila.setMensaje(mensaje.split("\\|")[0]);
                            registro.add(fila);
                       
                    } while (rs.next());
                    if (registro.isEmpty()) {
                        fila = new Importacion();
                        fila.setRespuesta("NODEUDA");
                        fila.setMensaje(mensaje.split("\\|")[1]);
                        registro.add(fila);
                    } 
                } else {
                    fila = new Importacion();
                    fila.setRespuesta("NODEUDA");
                    fila.setMensaje(mensaje.split("\\|")[1]);
                    registro.add(fila);
                }
            } else {
                fila = new Importacion();
                fila.setRespuesta(respuesta);
                fila.setMensaje(mensaje);
                registro.add(fila);
            }
        } catch (SQLException e) {
            System.out.println("guardarContrato catch: " + e.getMessage());
            throw new Exception(e.toString());
        } finally {
            try {
                call.close();
                dispose();
            } catch (SQLException ex) {
                System.out.println("guardarContrato finally catch: " + ex.getMessage());
            }
        }
        return registro;
    }

    public void envia_email(String p_to,String p_from,String p_subject,String p_html_msg,String p_attach_name,
                            String p_attach_mime,InputStream p_attach_blob,String cod_cer) throws Exception {
       
        CallableStatement call = null;
       
        try {
            call = cn.prepareCall("{ call pkg_certificacion.HTML_EMAIL_texto ( ?,?,?, ?,?,?, ?,?,?, ?,?)}");
            call.setString(1, p_to);
            call.setString(2, p_from);
            call.setString(3, p_subject);
            call.setString(4, " ");
            call.setString(5, p_html_msg);
            call.setString(6, p_attach_name );
            call.setString(7, p_attach_mime);
            call.setBlob(8, p_attach_blob);
            call.setString(9, "anbdm4.aduana.gob.bo");
            call.setInt(10, 25);
            call.setString(11, cod_cer);
            call.execute(); 
            System.out.println("Enviado correctamente");
        } catch (SQLException e) {
            System.out.println("guardarContrato catch: " + e.getMessage());
            throw new Exception(e.toString());
        } finally {
            try {
                call.close();
                dispose();
            } catch (SQLException ex) {
                System.out.println("guardarContrato finally catch: " + ex.getMessage());
            }
        }       
    }
    
    public UsuarioForm obtienememo(ConsultaForm bean)throws Exception 
    {
            UsuarioForm f=new UsuarioForm();
            String salida = "";
            CallableStatement call = null;
        try {       
            call = cn.prepareCall("{ ? = call pkg_certificacion.obtiene_memorizado (?,?,?,?)}");
            call.registerOutParameter(1, OracleTypes.CURSOR);
            call.setString(2, bean.getUsu_nro_ident());            
            call.setString(3, bean.getUsu_tipo_doc());       
            call.registerOutParameter(4, OracleTypes.VARCHAR);
            call.registerOutParameter(5, OracleTypes.VARCHAR);
            call.execute();
            String respuesta = (String)call.getObject(4);
            String mensaje = (String)call.getObject(5);
            if (respuesta.equals("OK"))
            {
                ResultSet rs = (ResultSet)call.getObject(1);
                if (!(rs == null || !rs.next())) {                    
                    do {                           
                       
                        f.setUsu_nro_ident(rs.getString("cer_nro_doc"));
                        f.setUsu_tipo_doc(rs.getString("cer_tipo_doc"));
                        f.setDep(rs.getString("cer_doc_extencion"));
                        f.setUsu_tipo_usu(rs.getString("cer_tipo_sujeto"));                     
                        f.setUsu_nombre(rs.getString("cer_nombre"));
                        f.setUsu_ap_pat(rs.getString("cer_apellido_pat"));
                        f.setUsu_ap_mat(rs.getString("cer_apellido_mat"));
                        f.setUsu_razon_social(rs.getString("cer_razon_social"));
                        f.setFecNacimiento(rs.getString("cer_fecha_nac"));
                        f.setUsu_mail(rs.getString("cer_correo"));                        
                        f.setRespuesta("OK");
                        f.setMensaje("Existe el certificado");                          
                    } while (rs.next());                    
                } 
                else
                {
                        f.setRespuesta("NOK");
                        f.setMensaje("No existe el certificado");                   
                    }
            }
            else
            {
                f.setRespuesta(respuesta);
                f.setMensaje(mensaje);
            }            
        } catch (SQLException e) {
            System.out.println("Registro de Certificado: " + e.getMessage());
            throw new Exception(e.toString());
        } finally {
            try {
                call.close();
                dispose();
            } catch (SQLException ex) {
                System.out.println("Registro de Certificado: finally catch: " + ex.getMessage());
            }
        }
        return f;
    }
    String ruta = "/u03/oracle/user_projects/data/certificacion/app.properties";
    public List<UsuarioForm> obtieneCertificados(ConsultaCerForm bean) throws Exception {      
        List<UsuarioForm> certificados = null;
        CallableStatement call = null;   
        UsuarioForm cert=null;
        Seguridad seg=null;
        try {
            seg= new Seguridad(ruta);
        } catch (Exception e) {         
        }
        try {
            call = cn.prepareCall("{ ? = call pkg_certificacion.obtiene_certificados( ?,?)}");
            call.registerOutParameter(1, OracleTypes.CURSOR);
            call.setString(2, bean.getFechaIni()); 
            call.setString(3, bean.getFechaFin());             
            call.execute();   
            certificados = new ArrayList<UsuarioForm>();
                ResultSet rs = (ResultSet)call.getObject(1);
                if (!(rs == null || !rs.next())) {                    
                    do {                  
                        cert = new UsuarioForm();
                        String tipo_viaje="";
                        String      textoEncryptado =seg.encrypt(rs.getString("cer_cod"));
                        String url_encriptado ="VerificacionCert.do?id="+textoEncryptado;
                        String url_ref="<a class=\"lnkVistaPrevia\" data-fancybox-type=\"fancybox fancybox.iframe\" href=\""+url_encriptado+"\"> "+rs.getString("cer_cod")+"</a>";
                        cert.setUrl(url_ref);
                        cert.setCodigo(rs.getString("cer_cod"));
                        cert.setFecha(rs.getString("fecha_reg"));
                        String nom=rs.getString("nombre_razon");
                        if (nom!=null)
                            cert.setUsu_razon_social(nom);
                        else
                            cert.setUsu_razon_social(" ");
                        cert.setUsu_nro_ident(rs.getString("cer_nro_doc"));
                        cert.setUsu_tipo_doc(rs.getString("cer_tipo_doc"));
                        cert.setTipo_certificacion(rs.getString("cer_certificacion"));                       
                        certificados.add(cert);                                    
                    } while (rs.next());                  
                } 
        } catch (SQLException e) {
            System.out.println("obtieneManifiestoMemo catch: " + e.getMessage());
            throw new Exception(e.toString());
        } finally {
            try {
                call.close();
                dispose();
            } catch (SQLException ex) {
                System.out.println("obtieneManifiestoMemo finally catch: " + ex.getMessage());
            }
        }
        return certificados;
    }
    
    public UsuarioForm getbusca(String nro_doc,String tipo_doc)throws Exception 
    {
            UsuarioForm f=new UsuarioForm();
            String salida = "";
            CallableStatement call = null;
        try {       
            call = cn.prepareCall("{ ? = call pkg_certificacion.carganom (?,?,?)}");
            call.registerOutParameter(1, OracleTypes.VARCHAR);
            call.setString(2, nro_doc);            
            call.setString(3,tipo_doc);       
            call.registerOutParameter(4, OracleTypes.VARCHAR);           
            call.execute();
            String razon = (String)call.getObject(1);
            String direccion = (String)call.getObject(4);
            f.setUsu_nro_nit(nro_doc);
            f.setUsu_razon_social(razon);
            f.setDireccion(direccion);
            f.setUsu_tipo_usu(tipo_doc);                  
        } catch (SQLException e) {
            System.out.println("Registro de Certificado: " + e.getMessage());
            throw new Exception(e.toString());
        } finally {
            try {
                dispose();
                call.close();
            } catch (SQLException ex) {
                System.out.println("Registro de Certificado: finally catch: " + ex.getMessage());
            }
        }
        return f;
    }
    
}
