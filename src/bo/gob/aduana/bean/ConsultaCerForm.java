package bo.gob.aduana.bean;

import org.apache.struts.action.ActionForm;

public class ConsultaCerForm extends ActionForm {
   String fechaIni;
   String fechaFin;
    String opcion;

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getOpcion() {
        return opcion;
    }
}
