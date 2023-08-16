package bo.gob.aduana;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;


public class DashboardAction extends MappingDispatchAction {

    public DashboardAction() {
        System.out.println("DashboardAction");
    }

    public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        String direccionar = "ok";
        return mapping.findForward(direccionar);
    }

    public ActionForward option(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        return mapping.findForward("dashboard.option");
    }


}
