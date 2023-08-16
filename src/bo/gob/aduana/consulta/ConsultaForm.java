package bo.gob.aduana.consulta;

import org.apache.struts.action.ActionForm;

public class ConsultaForm extends ActionForm {
    private String usu_nro_ident;
    private String usu_tipo_doc;
    private String usu_tipo_usu;
    private String usu_extencion;
    private String resultado;
    private String mensaje;    
    private String captcha;
    private String opcion;
    
    /*Recibo de Pago*/
    private String gestion;
    private String aduana;
    private String numero;
    
    private int cantidad;

    public void setUsu_nro_ident(String usu_nro_ident) {
        this.usu_nro_ident = usu_nro_ident;
    }

    public String getUsu_nro_ident() {
        return usu_nro_ident;
    }

    public void setUsu_tipo_doc(String usu_tipo_doc) {
        this.usu_tipo_doc = usu_tipo_doc;
    }

    public String getUsu_tipo_doc() {
        return usu_tipo_doc;
    }

    public void setUsu_tipo_usu(String usu_tipo_usu) {
        this.usu_tipo_usu = usu_tipo_usu;
    }

    public String getUsu_tipo_usu() {
        return usu_tipo_usu;
    }

    

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
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

    public void setUsu_extencion(String usu_extencion) {
        this.usu_extencion = usu_extencion;
    }

    public String getUsu_extencion() {
        return usu_extencion;
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

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }
}
