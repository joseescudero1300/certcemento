package bo.gob.aduana.bean;

import java.sql.Clob;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class UsuarioForm extends ActionForm {
    
    
    private String usu_tipo_usu = "";   
    private String usu_tipo_doc = "";
    private String usu_nro_ident="";
    private String usu_nro_ident_en;
    private String usu_tipo_doc_en = "";
    private String usu_nro_nit;
    private String dep="";//Extencion
    private String usu_nombre;
    private String usu_ap_pat;
    private String usu_ap_mat;
    private String fecNacimiento;
    private String usu_razon_social;  
    private String usu_mail; 
    private String extencion="";
    private String direccion;
    /*Recibo de Pago*/
    private String gestion;
    private String aduana;
    private String numero;
    /*Certificacion*/
    private String tipo_certificacion="";
    private String otro;
    private String captcha;
    
    private String opcion;
    private String respuesta;
    private String mensaje; 
    
    private String fecha_hora;
    private String codigo;
    
    private String fecha;
    private Clob detalle_deuda;
    private String detalle;
    private String url;

    public String getUsu_nro_ident() {
        return usu_nro_ident;
    }

    public void setUsu_nro_ident(String newUsu_nro_ident) {
        usu_nro_ident = newUsu_nro_ident;
    }

    public String getUsu_tipo_doc() {
        return usu_tipo_doc;
    }

    public void setUsu_tipo_doc(String newUsu_tipo_doc) {
        usu_tipo_doc = newUsu_tipo_doc;
    }

    public String getUsu_nombre() {
        return usu_nombre;
    }

    public void setUsu_nombre(String newUsu_nombre) {
        usu_nombre = newUsu_nombre;
    }

    public String getUsu_ap_pat() {
        return usu_ap_pat;
    }

    public void setUsu_ap_pat(String newUsu_ap_pat) {
        usu_ap_pat = newUsu_ap_pat;
    }

    public String getUsu_ap_mat() {
        return usu_ap_mat;
    }

    public void setUsu_ap_mat(String newUsu_ap_mat) {
        usu_ap_mat = newUsu_ap_mat;
    }  

    public String getUsu_mail() {
        return usu_mail;
    }

    public void setUsu_mail(String newUsu_mail) {
        usu_mail = newUsu_mail;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String newDep) {
        dep = newDep;
    }

    public String getUsu_razon_social() {
        return usu_razon_social;
    }

    public void setUsu_razon_social(String newUsu_razon_social) {
        usu_razon_social = newUsu_razon_social;
    }

    public String getUsu_tipo_usu() {
        return usu_tipo_usu;
    }

    public void setUsu_tipo_usu(String newUsu_tipo_usu) {
        usu_tipo_usu = newUsu_tipo_usu;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return super.validate(mapping, request);
    }
    
    public void setUsu_nro_nit(String usu_nro_nit) {
        this.usu_nro_nit = usu_nro_nit;
    }

    public String getUsu_nro_nit() {
        return usu_nro_nit;
    }

    public void setFecNacimiento(String fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }

    public String getFecNacimiento() {
        return fecNacimiento;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
    }

    public String getGestion() {
        return gestion;
    }

    public void setAduana(String aduana) {
        this.aduana = aduana;
    }

    public String getAduana() {
        return aduana;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setTipo_certificacion(String tipo_certificacion) {
        this.tipo_certificacion = tipo_certificacion;
    }

    public String getTipo_certificacion() {
        return tipo_certificacion;
    }

    public void setOtro(String otro) {
        this.otro = otro;
    }

    public String getOtro() {
        return otro;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setUsu_nro_ident_en(String usu_nro_ident_en) {
        this.usu_nro_ident_en = usu_nro_ident_en;
    }

    public String getUsu_nro_ident_en() {
        return usu_nro_ident_en;
    }

    public void setUsu_tipo_doc_en(String usu_tipo_doc_en) {
        this.usu_tipo_doc_en = usu_tipo_doc_en;
    }

    public String getUsu_tipo_doc_en() {
        return usu_tipo_doc_en;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setDetalle_deuda(Clob detalle_deuda) {
        this.detalle_deuda = detalle_deuda;
    }

    public Clob getDetalle_deuda() {
        return detalle_deuda;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setExtencion(String extencion) {
        this.extencion = extencion;
    }

    public String getExtencion() {
        return extencion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
