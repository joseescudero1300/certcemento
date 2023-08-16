package bo.gob.aduana.archivo;

import bo.gob.aduana.archivo.VerificacionCert.Watermark;
import bo.gob.aduana.bd.Sql;
import bo.gob.aduana.bean.UsuarioForm;
import bo.gob.aduana.itextpdf.ITextPdfFormat;
import bo.gob.aduana.itextpdf.ITextPdfHelper;

import bo.gob.aduana.system.Seguridad;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Color;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class CertificadoAdeudo extends Action {
    String ruta = "/u03/oracle/user_projects/data/certificacion/app.properties";
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws IOException, ServletException{
        Seguridad seg;
        UsuarioForm r = new UsuarioForm();
        try{
            response.setContentType("application/pdf");
            String marca = "";
            if(request.getParameter("wm")!=null){
                marca = request.getParameter("wm").toString();
            }
            
            /*******************************************************************************************************
             * DATOS DEL FORMULARIO
             ******************************************************************************************************/
           
            String codigoTramite= "";
            String codigoTramiteQR= "";
            int idTramite = 0; 
            
            /*******************************************************************************************************
             * CREACION DEL PDF
             ******************************************************************************************************/
            Document pdf = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(pdf, baos);
            if (marca.equals("prev")) {
                writer.setPageEvent(new Watermark());
            }
            String path = getServlet().getServletContext().getRealPath("");
            ITextPdfHelper document = new ITextPdfHelper(pdf, path, ITextPdfFormat.PAGE_LETTER);

            ITextPdfFormat fTitulo =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 13, ITextPdfFormat.STYLE_NORMAL, ITextPdfFormat.ALIGN_CENTER,
                                   ITextPdfFormat.SPACING_0, 0);

            ITextPdfFormat fDatosOperador =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 15, ITextPdfFormat.STYLE_BOLD,
                                   ITextPdfFormat.ALIGN_CENTER, ITextPdfFormat.SPACING_0, 0);
            ITextPdfFormat fleyenda =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 12, ITextPdfFormat.STYLE_NORMAL, ITextPdfFormat.ALIGN_JUSTIFY,
                                   ITextPdfFormat.SPACING_2, 0);
            ITextPdfFormat fleyendaIngles =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 8, ITextPdfFormat.STYLE_ITALIC,
                                   ITextPdfFormat.ALIGN_JUSTIFY, ITextPdfFormat.SPACING_1dot5, 0);
            ITextPdfFormat freferencia =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 12, ITextPdfFormat.STYLE_BOLD, ITextPdfFormat.ALIGN_RIGHT,
                                   ITextPdfFormat.SPACING_0, 0);
            ITextPdfFormat freferenciaIzq =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 15, ITextPdfFormat.STYLE_BOLD, ITextPdfFormat.ALIGN_LEFT,
                                   ITextPdfFormat.SPACING_0, 0);

            document.setMargins(ITextPdfFormat.MARGIN_2);
            document.open();

            if (true) {

                document.addParagraph("", fTitulo);
                document.addParagraph("", fleyendaIngles);
                document.addParagraph("", fleyendaIngles);
                document.addParagraph("<b>CERTIFICACIÓN</b>", fDatosOperador);
                document.addParagraph("<b>" + r.getCodigo() + "</b>", fDatosOperador);
                document.addParagraph("", fleyendaIngles);
                document.addParagraph("<b>La Gerencia Nacional Jurídica</b>", freferencia);
                document.addParagraph("<b>de la Aduana Nacional</b>", freferencia);
                document.addParagraph("<b>CERTIFICA:</b>", freferenciaIzq);
                document.addParagraph("", fleyendaIngles);
                if (r.getUsu_tipo_usu().equals("N"))
                    document.addParagraph("De acuerdo a la verificación de la base de datos consolidada del Sistema Informático de Ejecuciones Tributarias - PIET, " +
                                          "el Sr(a) <b>" + r.getUsu_nombre() + " " + r.getUsu_ap_pat() + " " +
                                          r.getUsu_ap_mat() + "</b> " + " con CI <b>" + r.getUsu_nro_ident() +
                                          "</b>, <bu>NO REGISTRA</bu> adeudos tributarios con la Aduana Nacional.",
                                          fleyenda);
                else
                    document.addParagraph("De acuerdo a la verificación de la base de datos consolidad del Sistema Informática de Ejecuciones Tributarias - PIET, " +
                                          "la empresa <b>" + r.getUsu_razon_social() + "</b>" + " con NIT <b>" +
                                          r.getUsu_nro_ident() +
                                          "</b>, <bu>NO REGISTRA</bu> adeudos tributarios con la Aduana Nacional.",
                                          fleyenda);

                document.addParagraph("La presente certificación se emite en cumplimiento al Procedimiento de Registro y Gestión de Operadores de Comercio Exterior, conforme lo " +
                                      "dispuesto por la Resolución Administrativa Nº RA-PE-01-004-15 de 13/03/2015",
                                      fleyenda);
                //if (r.getTipo_certificacion(""))
                int tip = 2;
                switch (tip) {
                case 0:
                    document.addParagraph("Es cuanto se certifica, para su uso en " + r.getOtro() + ".", fleyenda);
                    break;
                case 1: /*Autorizaciones previas*/
                    document.addParagraph("Es cuanto se certifica, en cumplimiento al Procedimiento para la emisión de Autorizaciones Previas de Importación, " +
                                          "en el marco del Decreto Supremo N° 2752 de 01 de mayo de 2016.", fleyenda);
                    break;
                case 2: /*Despacho directo*/
                    document.addParagraph("Es cuanto se certifica, en cumplimiento a la Resolución Administrativa RA-PE 01-011-13 de 20/08/2013, " +
                                          "que aprueba el “Procedimiento para el Registro y Control de Importadores de Mercancías de Manera Directa”.",
                                          fleyenda);
                    break;
                case 3: /*Registro y gestion*/
                    document.addParagraph("Es cuanto se certifica, en cumplimiento al Procedimiento de Registro y Gestión de Operadores de Comercio Exterior, " +
                                          "conforme lo dispuesto por la Resolución Administrativa N° RA-PE-01-004-15 de 13/03/2015.",
                                          fleyenda);
                    break;
                case 4: /*RITEX*/
                    document.addParagraph("Es cuanto se certifica, con el objeto de la incorporación al Régimen de Admisión Temporal para Perfeccionamiento Activo – RITEX, " +
                                          "aprobado mediante Decreto Supremo Nº 25706 con sus modificaciones.",
                                          fleyenda);
                    break;
                case 5: /*Observancia a 24 CPE*/
                    document.addParagraph("Es cuanto se certifica, en observancia al Art. 24 de la Constitución Política del Estado y el Art 129 del Código Tributario Boliviano.",
                                          fleyenda);
                    break;
                default:

                    break;
                }
                document.addParagraph("La presente certificación tiene una validez de seis (6) meses a partir de su emisión.",
                                      fleyenda);
                document.addParagraph("La Paz, " + r.getFecha(), fleyenda);
                document.addParagraph("", fleyendaIngles);

                document.addImageByPath("/u03/oracle/user_projects/data/certificacion/img/fondo_cert_adeudo.jpg", 612f, 792f,
                                        0f, 0f);

                /*******************************************************************************************************
                 * GENERACION DE LOS CODIGOS PDF417 Y QR
                 ******************************************************************************************************/
                Properties prop = new Properties();
                InputStream input = null;

                String esProduccion = "";
                String baseURL = "";

                try {
                    input = new FileInputStream(ruta);
                    prop.load(input);
                    esProduccion = prop.getProperty("es_produccion");
                    if (esProduccion != null) {
                        if (esProduccion.equals("1")) {
                            baseURL = prop.getProperty("base_url_prod");
                        } else {
                            baseURL = prop.getProperty("base_url_dev");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                } finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                BaseFont bf = null;
                bf = BaseFont.createFont(path + "/fonts/arial.ttf", BaseFont.CP1252, BaseFont.EMBEDDED);
                Phrase titreFormules =
                    new Phrase("Certificado Generado en fecha: " + "13/05/2019 14:30", new Font(bf, 8, Font.ITALIC));

                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, titreFormules, 465, 90, 0);


                String textoEncryptado = "", url_encriptado = "";
                String cod_cert = r.getCodigo();
                //String cod_cert = "201930142-D";
                seg = new Seguridad(ruta);
                try {
                    textoEncryptado = seg.encrypt(cod_cert);
                } catch (Exception e) {
                    textoEncryptado = "";
                }
                url_encriptado = baseURL + "VerificacionCert.do?id=" + textoEncryptado;

                boolean encriptadoOk = true;

                /* if (encriptadoOk) {
                    String contenidoPdf417 = url_encriptado;
                    document.addPdf417(contenidoPdf417, 50f, 465f, 75f); //50f, 465f, 75f
                }*/
                document.addQR(url_encriptado, 150, 230f, 150f); //80, 70f, 65f
                pdf = document.getDocument();
                pdf.close();


            } else {
                document.addParagraph("<bu>NO EXISTE CERTIFICADO</bu>", fDatosOperador);
                pdf = document.getDocument();
                pdf.close();
            }
            /*******************************************************************************************************
             * GUARDADADO DEL PDF Y ESCRITURA EN EL RESPONSE
             ******************************************************************************************************/
            ServletOutputStream outputStream = response.getOutputStream();
            baos.writeTo(outputStream);
            outputStream.flush();
            outputStream.close();
            } catch (Exception e) {
            e.printStackTrace();
            }
            return null;
    }
     
    public byte[] obtenerBytes(String path)  {
        FileInputStream fileInputStream = null;
        File file = new File(path);
        byte[] bFile = new byte[(int) file.length()];
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
 
            for (int i = 0; i < bFile.length; i++) {
                System.out.print((char)bFile[i]);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return bFile;
    }   

    class Watermark extends PdfPageEventHelper {
        Font FONT = new Font(Font.FontFamily.HELVETICA, 52, Font.BOLD, new BaseColor(200,200,200,50));
        
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            PdfGState gState = new PdfGState();
            gState.setFillOpacity(0.6f);
            cb.setGState(gState);
            
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, new Phrase("VISTA PREVIA", FONT),
                    397.5f, 321, writer.getPageNumber() % 2 == 1 ? 45 : -45);
        }
    }
    
    class FootMark extends PdfPageEventHelper {
        Font FONT = new Font(Font.FontFamily.HELVETICA, 6, Font.ITALIC, BaseColor.BLACK);
        private String text;
        public FootMark(String text){
            super();
            this.text = text;
        }
        
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            PdfGState gState = new PdfGState();
            cb.setGState(gState);
            
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_JUSTIFIED, new Phrase(text, FONT),
                    50, 70, 0);
        }
    }
    
    public InputStream genera_documento(String cod, ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                        HttpServletResponse response) {
        Seguridad seg = null;
        Sql sql = null;
        InputStream targetStream = null;
        UsuarioForm r = new UsuarioForm();
        try {
            sql = new Sql();
            r = sql.obtieneCertificado(cod);
        } catch (Exception e) {
            r.setRespuesta("NOK");
            r.setMensaje(e.getMessage().toString());
        } finally {
            sql.dispose();
        }

        try {
            Document pdf = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(pdf, baos);
            String path = "/u03/oracle/user_projects/data/adeudo";     
            ITextPdfHelper document = new ITextPdfHelper(pdf, path, ITextPdfFormat.PAGE_LETTER);

            ITextPdfFormat fTitulo =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 12, ITextPdfFormat.STYLE_NORMAL, ITextPdfFormat.ALIGN_CENTER,
                                   ITextPdfFormat.SPACING_0, 0);

            ITextPdfFormat fDatosOperador =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 16, ITextPdfFormat.STYLE_BOLD,
                                   ITextPdfFormat.ALIGN_CENTER, ITextPdfFormat.SPACING_0, 0);
            ITextPdfFormat fleyenda =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 16, ITextPdfFormat.STYLE_NORMAL, ITextPdfFormat.ALIGN_JUSTIFY,
                                   ITextPdfFormat.SPACING_2, 0);
            ITextPdfFormat fleyendaIngles =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 8, ITextPdfFormat.STYLE_ITALIC,
                                   ITextPdfFormat.ALIGN_JUSTIFY, ITextPdfFormat.SPACING_1dot5, 0);/*eSPACOIOP*/
            ITextPdfFormat freferencia =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 16, ITextPdfFormat.STYLE_BOLD, ITextPdfFormat.ALIGN_RIGHT,
                                   ITextPdfFormat.SPACING_0, 0);
            ITextPdfFormat freferenciaIzq =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 16, ITextPdfFormat.STYLE_BOLD, ITextPdfFormat.ALIGN_LEFT,
                                   ITextPdfFormat.SPACING_0, 0);

            document.setMargins(ITextPdfFormat.MARGIN_2);
            document.open();


            document.addParagraph("", fTitulo);
            document.addParagraph("", fleyendaIngles);
            document.addParagraph("", fleyendaIngles);
            document.addParagraph("<bu>C E R T I F I C A C I Ó N</bu>", fDatosOperador);
            //document.addParagraph("<bu>AN-PIET-CERT-" +r.getCodigo().substring(4)+"-"+ r.getCodigo().substring(0, 4) + "</bu>", fDatosOperador);
            document.addParagraph("<bu>" +r.getCodigo()+"</bu>", fDatosOperador);
            document.addParagraph("", fleyendaIngles);
           // document.addParagraph("<b>La Gerencia Nacional Jurídica</b>", freferencia);
            document.addParagraph("<b>LA ADUANA NACIONAL</b>", freferencia);
            document.addParagraph("<b>CERTIFICA:</b>", freferenciaIzq);
            document.addParagraph("", fleyendaIngles);
            /*aca*/
            if (r.getUsu_tipo_usu().equals("N"))
                document.addParagraph("De acuerdo a la verificación en la base de datos consolidada del Sistema Plataforma Informática de Ejecuciones Tributarias - PIET, " +
                                      "el Sr(a) <b>" + r.getUsu_nombre() + " " + r.getUsu_ap_pat() + " " +
                                      r.getUsu_ap_mat() + "</b> " + " con CI <b>" + r.getUsu_nro_ident() +" "+r.getDep()+
                                      "</b>, <bu>NO REGISTRA</bu> adeudos tributarios con la Aduana Nacional.",
                                      fleyenda);
            else
                document.addParagraph("De acuerdo a la verificación de la base de datos consolidada del Sistema Plataforma Informática de Ejecuciones Tributarias - PIET, " +
                                      "la empresa <b>" + r.getUsu_razon_social() + "</b> " + " con NIT <b>" +
                                      r.getUsu_nro_ident() +
                                      "</b>, <bu>NO REGISTRA</bu> adeudos tributarios con la Aduana Nacional.",
                                      fleyenda);

            
            //if (r.getTipo_certificacion(""))
            int tip = Integer.parseInt(r.getTipo_certificacion());
            switch (tip) {
            case 0:         
               // document.addParagraph("La presente certificación se emite para su uso en " + r.getOtro() + ".", fleyenda);
                document.addParagraph("Es cuanto se certifica, para su uso en " + r.getOtro() + ".", fleyenda);
                break;
            case 1: /*Autorizaciones previas*/
         /*   document.addParagraph("La presente certificación se emite en cumplimiento al Procedimiento para la emisión de Autorizaciones Previas de Importación, " + 
                                  "en el marco del Decreto Supremo N° 2752 de 01 de mayo de 2016.",
                                  fleyenda);*/
                document.addParagraph("Es cuanto se certifica, en cumplimiento al Procedimiento para la emisión de Autorizaciones Previas de Importación, " +
                                      "en el marco del Decreto Supremo N° 2752 de 01 de mayo de 2016.", fleyenda);
                break;
            case 2: /*Despacho directo*/
            /*document.addParagraph("La presente certificación se emite en cumplimiento a la Resolución Administrativa RA-PE 01-011-13 de 20/08/2013, " + 
                                  "que aprueba el “Procedimiento para el Registro y Control de Importadores de Mercancías de Manera Directa”.",
                                  fleyenda);*/
                document.addParagraph("Es cuanto se certifica, en cumplimiento a la Resolución Administrativa RA-PE 01-011-13 de 20/08/2013, " +
                                      "que aprueba el “Procedimiento para el Registro y Control de Importadores de Mercancías de Manera Directa”.",
                                      fleyenda);
                break;
            case 3: /*Registro y gestion*/
           /* document.addParagraph("La presente certificación se emite en cumplimiento al Procedimiento de Registro y Gestión de Operadores de Comercio Exterior, conforme lo " +
                                  "dispuesto por la Resolución Administrativa Nº RA-PE-01-004-15 de 13/03/2015",
                                  fleyenda);*/
                document.addParagraph("Es cuanto se certifica, en cumplimiento al Procedimiento de Registro y Gestión de Operadores de Comercio Exterior, " +
                                      "conforme lo dispuesto por la Resolución Administrativa N° RA-PE-01-004-15 de 13/03/2015.",
                                      fleyenda);
                break;
            case 4: /*RITEX*/
            /*document.addParagraph("La presente certificación se emite con el objeto de la incorporación al Régimen de Admisión Temporal para Perfeccionamiento Activo – RITEX, " + 
                                  "aprobado mediante Decreto Supremo Nº 25706 con sus modificaciones.",
                                  fleyenda);*/
                document.addParagraph("Es cuanto se certifica, con el objeto de la incorporación al Régimen de Admisión Temporal para Perfeccionamiento Activo – RITEX, " +
                                      "aprobado mediante Decreto Supremo Nº 25706 con sus modificaciones.",
                                      fleyenda);
                break;
            case 5: /*Observancia a 24 CPE*/
           /* document.addParagraph("La presente certificación se emite en observancia al Art. 24 de la Constitución Política del Estado y el Art 129 del Código Tributario Boliviano.",
                                  fleyenda);*/
                document.addParagraph("Es cuanto se certifica, en observancia al Art. 24 de la Constitución Política del Estado y el Art 129 del Código Tributario Boliviano.",
                                      fleyenda);
                break;
            default:

                break;
            }            
            
            document.addParagraph("La presente certificación tiene una validez de seis (6) meses a partir de su emisión.",
                                  fleyenda);
            document.addParagraph("La Paz, " + r.getFecha(), fleyenda);
            document.addParagraph("", fleyendaIngles);

            document.addImageByPath("/u03/oracle/user_projects/data/certificacion/img/fondo_cert_adeudo.jpg", 612f, 792f, 0f,
                                    0f);

            /*******************************************************************************************************
                     * GENERACION DE LOS CODIGOS PDF417 Y QR
                     ******************************************************************************************************/
            Properties prop = new Properties();
            InputStream input = null;

            String esProduccion = "";
            String baseURL = "";

            try {
                input = new FileInputStream(ruta);
                prop.load(input);
                esProduccion = prop.getProperty("es_produccion");
                if (esProduccion != null) {
                    if (esProduccion.equals("1")) {
                        baseURL = prop.getProperty("base_url_prod");
                    } else {
                        baseURL = prop.getProperty("base_url_dev");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            BaseFont bf = null;
            bf =
    BaseFont.createFont("/u03/oracle/user_projects/data/certificacion/fonts/times.ttf", BaseFont.CP1252, BaseFont.EMBEDDED);
            Phrase titreFormules =
                new Phrase("Certificado Generado en fecha: " + r.getFecha_hora(), new Font(bf, 8, Font.ITALIC));

            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, titreFormules, 465, 90, 0);


            String textoEncryptado = "", url_encriptado = "";

            String cod_cert = r.getCodigo();

            try {
                seg = new Seguridad(ruta);
                textoEncryptado = seg.encrypt(cod_cert);
            } catch (Exception e) {
                textoEncryptado = "";
            }
            url_encriptado = baseURL + "VerificacionCert.do?id=" + textoEncryptado;
            document.addQR(url_encriptado, 150, 230f, 150f);
            pdf = document.getDocument();
            pdf.close();
            targetStream = new ByteArrayInputStream(baos.toByteArray());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetStream;
    }
}

