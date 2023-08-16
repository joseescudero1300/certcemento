package bo.gob.aduana.system;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public class AuthForm extends ActionForm {

    private String usuario;
    private String clave;
    private String aduana;
    private String descAduana;
    private ArrayList opciones = new ArrayList();

    public String getAduana() {
        return aduana;
    }

    public void setAduana(String aduana) {
        this.aduana = aduana;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public ArrayList getOpciones() {
        return opciones;
    }

    public void setOpciones(ArrayList opciones) {
        this.opciones = opciones;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

        ActionErrors errors = new ActionErrors();

        if (usuario == null || usuario.equals("")) {
            errors.add("usuario", new ActionMessage("message.error.required", "Usuario"));
        }

        if (clave == null || clave.equals("")) {
            errors.add("clave", new ActionMessage("message.error.required", "Contraseña"));
        }
        return errors;
    }

    public void setDescAduana(String descAduana) {
        this.descAduana = descAduana;
    }

    public String getDescAduana() {
        return descAduana;
    }
}
