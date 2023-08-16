package bo.gob.aduana.archivo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class Ayuda extends Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws IOException, ServletException {
        
        String ruta = "/u03/oracle/user_projects/data/certificacion/Archivos/"+request.getParameter("nombre");
        File file = new File(ruta); 
        if (file.exists()) {
            
            response.setContentType("application/pdf");
          //  response.setHeader("Content-Disposition","attachment;filename=Ayuda.pdf");          
            FileInputStream in = new FileInputStream(new File(ruta));
            ServletOutputStream out = response.getOutputStream();
            byte[] outputByte = new byte[4096];
            //copy binary content to output stream
            while(in.read(outputByte, 0, 256) != -1){
                out.write(outputByte, 0, 256);
            }
            in.close();
            out.flush();
            out.close();
           
        } return null;
         /*   
            //response.setContentType("application/pdf");
            //response.setHeader("Expires", "0");
            //response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            //response.setHeader("Pragma", "public");
            ByteArrayOutputStream ous = null;
            InputStream ios = null;
            try {
                byte[] buffer = new byte[1024];
                ous = new ByteArrayOutputStream();
                ios = new FileInputStream(file);
                int read = 0;
                while ((read = ios.read(buffer)) != -1) {
                    ous.write(buffer, 0, read);
                }
            }finally {
                try {
                    if (ous != null)
                        ous.close();
                } catch (IOException e) {
                }

                try {
                    if (ios != null)
                        ios.close();
                } catch (IOException e) {
                }
            }
            byte[] byteFile = ous.toByteArray();;
            OutputStream out = response.getOutputStream();
            
            out.write(byteFile, 0, byteFile.length); 
            if (out != null) {
                out.close();
            }
        }
        return null;*/
    }
}