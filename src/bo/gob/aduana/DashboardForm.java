package bo.gob.aduana;

import org.apache.struts.action.ActionForm;

public class DashboardForm extends ActionForm{
    private double cotizacion;
    private String moneda;

    public void setCotizacion(double cotizacion) {
        this.cotizacion = cotizacion;
    }

    public double getCotizacion() {
        return cotizacion;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getMoneda() {
        return moneda;
    }
}
