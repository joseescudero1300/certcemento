package bo.gob.aduana.entidades.web;

public class Importacion {
   
    String aduana_des;
    String nro_registro;
    String fecha_val;
    String nro_doc;
    String razon_social;
    
    String respuesta;
    String mensaje;
    String detalle;

  

    public void setNro_doc(String nro_doc) {
        this.nro_doc = nro_doc;
    }

    public String getNro_doc() {
        return nro_doc;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getRazon_social() {
        return razon_social;
    }

    

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setAduana_des(String aduana_des) {
        this.aduana_des = aduana_des;
    }

    public String getAduana_des() {
        return aduana_des;
    }

    public void setNro_registro(String nro_registro) {
        this.nro_registro = nro_registro;
    }

    public String getNro_registro() {
        return nro_registro;
    }

    public void setFecha_val(String fecha_val) {
        this.fecha_val = fecha_val;
    }

    public String getFecha_val() {
        return fecha_val;
    }
}
