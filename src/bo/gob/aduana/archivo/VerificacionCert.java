package bo.gob.aduana.archivo;

import bo.gob.aduana.bd.Sql;
import bo.gob.aduana.bean.UsuarioForm;
import bo.gob.aduana.itextpdf.ITextPdfFormat;
import bo.gob.aduana.itextpdf.ITextPdfHelper;
import bo.gob.aduana.itextpdf.MyEvent;
import bo.gob.aduana.system.Seguridad;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.MalformedURLException;

import java.sql.Clob;

import java.sql.SQLException;

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

import sun.font.FontFamily;

public class VerificacionCert extends Action {
    String ruta = "/u03/oracle/user_projects/data/certificacion/app.properties";
    String path = "/u03/oracle/user_projects/data/adeudo";
    
    public  String clobStringConversion(Clob clb) throws IOException, SQLException
    {
           if (clb == null)
            return  "";                  
           StringBuffer str = new StringBuffer();
           String strng;
                BufferedReader bufferRead = new BufferedReader(clb.getCharacterStream());         
                    while ((strng=bufferRead .readLine())!=null)
                        str.append(strng);         
           return str.toString();
    }        
    

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws IOException, ServletException {
        Seguridad seg = null;
        Sql sql = null;
        UsuarioForm r = new UsuarioForm();
        if (request.getParameter("id") != null) {
            String idTramite_enc = request.getParameter("id");
            try {
                seg = new Seguridad(ruta);
                idTramite_enc = idTramite_enc.replace(" ", "+");
                String idTramite = seg.decrypt(idTramite_enc);
                sql = new Sql();
                r = sql.obtieneCertificado(idTramite);
            } catch (Exception e) {
                r.setRespuesta("NOK");
                r.setMensaje(e.getMessage().toString());
            } finally {
                sql.dispose();
            }
        } else {
            r.setRespuesta("NOK");
            r.setMensaje("No existe el formulario consultado");
        }
        try {
            response.setContentType("application/pdf");
            String marca = "";
            if (request.getParameter("wm") != null) {
                marca = request.getParameter("wm").toString();
            }
            /*******************************************************************************************************
                 * CREACION DEL PDF
                 ******************************************************************************************************/
            Document pdf = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(pdf, baos);
            
            if (marca.equals("prev")) {
                writer.setPageEvent(new Watermark());
            }
            
            ITextPdfHelper document = new ITextPdfHelper(pdf, path, ITextPdfFormat.PAGE_LETTER);
           
            if (r.getTipo_certificacion().equals("I"))
            {
                    certificado_importacion(document,r,seg,writer,pdf);
                    
                }
            else
            {
                    certificado_no_importacion(document,r,seg,writer);    
                }
            
            
            pdf = document.getDocument();
            pdf.close();
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
    
    public void  certificado_no_importacion (ITextPdfHelper document,UsuarioForm r,Seguridad seg, PdfWriter writer)throws Exception
    {
           

            ITextPdfFormat fTitulo =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 12, ITextPdfFormat.STYLE_NORMAL, ITextPdfFormat.ALIGN_CENTER,
                                   ITextPdfFormat.SPACING_0, 0);

            ITextPdfFormat fDatosOperador =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 16, ITextPdfFormat.STYLE_BOLD,
                                   ITextPdfFormat.ALIGN_CENTER, ITextPdfFormat.SPACING_0, 0);
            ITextPdfFormat fleyenda =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 14, ITextPdfFormat.STYLE_NORMAL, ITextPdfFormat.ALIGN_JUSTIFY,
                                   ITextPdfFormat.SPACING_2, 0);
            ITextPdfFormat fleyendaIngles =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 8, ITextPdfFormat.STYLE_ITALIC,
                                   ITextPdfFormat.ALIGN_JUSTIFY, ITextPdfFormat.SPACING_1dot5, 0);/*Espacio*/
            ITextPdfFormat freferencia =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 16, ITextPdfFormat.STYLE_BOLD, ITextPdfFormat.ALIGN_RIGHT,
                                   ITextPdfFormat.SPACING_0, 0);
            ITextPdfFormat freferenciaIzq =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 16, ITextPdfFormat.STYLE_BOLD, ITextPdfFormat.ALIGN_LEFT,
                                   ITextPdfFormat.SPACING_0, 0);

            document.setMargins(ITextPdfFormat.MARGIN_2);
            document.open();

            if (r.getRespuesta().equals("OK")) {

                document.addParagraph("", fTitulo);
                document.addParagraph("", fleyendaIngles);
                document.addParagraph("", fleyendaIngles);
                document.addParagraph("<bu>C E R T I F I C A C I Ó N</bu>", fDatosOperador);
                //document.addParagraph("<bu>AN-PIET-CERT-" +r.getCodigo().substring(4)+"-"+ r.getCodigo().substring(0, 4) + "</bu>", fDatosOperador);
                document.addParagraph("<bu>"+r.getCodigo()+ "</bu>", fDatosOperador);
                document.addParagraph("", fleyendaIngles);
                //document.addParagraph("<b>La Gerencia Nacional Jurídica</b>", freferencia);
                document.addParagraph("<b>LA ADUANA NACIONAL</b>", freferencia);
                document.addParagraph("<b>CERTIFICA:</b>", freferenciaIzq);
                document.addParagraph("", fleyendaIngles);
                if (r.getUsu_tipo_usu().equals("N"))
                    document.addParagraph("De acuerdo a la verificación en los sistemas informáticos de la Aduana Nacional, " +
                                          "el/la Sr(a) <b>" + r.getUsu_nombre() + " " + r.getUsu_ap_pat() + " " +
                                          r.getUsu_ap_mat() + "</b>" + " con CI Nº <b>" + r.getUsu_nro_ident() +" "+r.getDep()+
                                          "</b>, <bu>NO REGISTRA</bu> Declaraciones Únicas de Importación de mercancías asociadas a las subpartidas " +
                                         "arancelarias 2523.10.00.00, 2523.21.00.00, 2523.29.00.10, 2523.29.00.90, 2523.90.00.10 y 2523.90.00.90 " +
                                         "con anterioridad a noventa (90) días calendario de la presentación a su solicitud de fecha "+r.getFecha()+".",
                                          fleyenda);
                else
                    document.addParagraph("De acuerdo a la verificación en los sistemas informáticos de la Aduana Nacional, " +
                                          "la empresa <b>" + r.getUsu_razon_social() + "</b>" + " con NIT <b>" +
                                          r.getUsu_nro_ident() +
                                        "</b>, <bu>NO REGISTRA</bu> Declaraciones Únicas de Importación de mercancías asociadas a las subpartidas " +
                                        "arancelarias 2523.10.00.00, 2523.21.00.00, 2523.29.00.10, 2523.29.00.90, 2523.90.00.10 y 2523.90.00.90 " +
                                        "con anterioridad a noventa (90) días calendario de la presentación a su solicitud de fecha "+r.getFecha()+".",
                                          fleyenda);

                
                document.addParagraph("Por tanto, la Aduana Nacional emite la presente Certificación de acuerdo a lo establecido en el Inciso g) Artículo 6 de la Resolución " +
                    "Ministerial MDPyEP/DESPACHO/N° 101 de 12/06/2019, que aprueba el Reglamento para el Certificado de Producción Nacional " +
                    "de Cemento Portland y/o Puzolánico con Clinker 100% de Origen Nacional.", fleyenda);
               
                /*document.addParagraph("La presente certificación tiene una validez de seis (6) meses a partir de su emisión.",
                                      fleyenda);*/
                //document.addParagraph("La Paz, " + r.getFecha()+".", fleyenda);
                document.addParagraph("", fleyendaIngles);

                document.addImageByPath("/u03/oracle/user_projects/data/certificacion/img/fondo_cert.jpg", 612f, 792f,
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
                bf = BaseFont.createFont(path + "/fonts/times.ttf", BaseFont.CP1252, BaseFont.EMBEDDED);
                Phrase titreFormules =
                    new Phrase("Certificación generada, La Paz " + r.getFecha_hora()/*r.getFecha_hora()*/, new Font(bf, 10, Font.ITALIC));

                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, titreFormules, 465, 90, 0);


                String textoEncryptado = "", url_encriptado = "";
                String cod_cert = r.getCodigo();

                try {
                    textoEncryptado = seg.encrypt(cod_cert);
                } catch (Exception e) {
                    textoEncryptado = "";
                }
                url_encriptado = baseURL + "VerificacionCert.do?id=" + textoEncryptado;
                document.addQR(url_encriptado, 150, 230f, 150f);
            } else {
                document.addParagraph("<bu>NO EXISTE CERTIFICADO</bu>", fDatosOperador);
               
            }
        }

    public void  certificado_importacion (ITextPdfHelper document,UsuarioForm r,Seguridad seg, PdfWriter writer,Document pdf)throws Exception
    {
            pdf.setMargins(60f, 60f, 125f, 80f);
            Image img=null;
            try {
                img = Image.getInstance("/u03/oracle/user_projects/data/certificacion/img/fondo_cert_importacion.jpg");
                img.scaleAbsolute(612f, 792f);
                img.setAbsolutePosition(0f, 0f);
            } catch (Exception e) {
            }            
            MyEvent encabezado = new MyEvent();              
                    encabezado.setEncabezado("Prueba de encabezado en iText");
                    encabezado.setImg(img);
                    encabezado.setFecha(r.getFecha_hora());
                    encabezado.setPath(path);
            writer.setPageEvent(encabezado);
                    
            ITextPdfFormat fTitulo =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 12, ITextPdfFormat.STYLE_NORMAL, ITextPdfFormat.ALIGN_CENTER,
                                   ITextPdfFormat.SPACING_0, 0);

            ITextPdfFormat fDatosOperador =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 16, ITextPdfFormat.STYLE_BOLD,
                                   ITextPdfFormat.ALIGN_CENTER, ITextPdfFormat.SPACING_0, 0);
            ITextPdfFormat fleyenda =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 14, ITextPdfFormat.STYLE_NORMAL, ITextPdfFormat.ALIGN_JUSTIFY,
                                   ITextPdfFormat.SPACING_2, 0);
            ITextPdfFormat fleyendaIngles =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 8, ITextPdfFormat.STYLE_ITALIC,
                                   ITextPdfFormat.ALIGN_JUSTIFY, ITextPdfFormat.SPACING_1dot5, 0);/*Espacio*/
            ITextPdfFormat freferencia =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 16, ITextPdfFormat.STYLE_BOLD, ITextPdfFormat.ALIGN_RIGHT,
                                   ITextPdfFormat.SPACING_0, 0);
            ITextPdfFormat freferenciaIzq =
                new ITextPdfFormat(ITextPdfFormat.FONT_TIMES, 16, ITextPdfFormat.STYLE_BOLD, ITextPdfFormat.ALIGN_LEFT,
                                   ITextPdfFormat.SPACING_0, 0);

           // document.setMargins(ITextPdfFormat.MARGIN_2);
       //    document.setMargins(60, 30, 30, 30);
            document.open();

            if (r.getRespuesta().equals("OK")) {

               // document.addParagraph("", fTitulo);
               // document.addParagraph("", fleyendaIngles);
               // document.addParagraph("", fleyendaIngles);
                document.addParagraph("<bu>C E R T I F I C A C I Ó N</bu>", fDatosOperador);
                //document.addParagraph("<bu>AN-PIET-CERT-" +r.getCodigo().substring(4)+"-"+ r.getCodigo().substring(0, 4) + "</bu>", fDatosOperador);
                document.addParagraph("<bu>"+r.getCodigo()+ "</bu>", fDatosOperador);
                document.addParagraph("", fleyendaIngles);
                //document.addParagraph("<b>La Gerencia Nacional Jurídica</b>", freferencia);
                document.addParagraph("<b>LA ADUANA NACIONAL</b>", freferencia);
                document.addParagraph("<b>CERTIFICA:</b>", freferenciaIzq);
                document.addParagraph("", fleyendaIngles);
             
                    document.addParagraph("De acuerdo a la verificación en los sistemas informáticos de la Aduana Nacional, " +
                                          "se pudo establecer que el documento  Nº <b>" + r.getUsu_nro_ident() +"</b>, "+                                         
                                          " <bu>REGISTRA</bu> Declaraciones Únicas de Importación de mercancías asociadas a las subpartidas arancelarias " +
                                          "2523.10.00.00, 2523.21.00.00, 2523.29.00.10, 2523.29.00.90, 2523.90.00.10 y 2523.90.00.90 con anterioridad a noventa (90) días calendario " +
                                          "de la presentación a su solicitud de fecha "+r.getFecha()+", de conformidad al siguiente detalle:",
                                          fleyenda);
             
             
               String detalles=clobStringConversion(r.getDetalle_deuda());
               String lista_detalle[]=detalles.split("\\|LF\\|");
                PdfPTable tabla = new PdfPTable(6);
                BaseFont bf = null;
                bf = BaseFont.createFont(path + "/fonts/times.ttf", BaseFont.CP1252, BaseFont.EMBEDDED);
                Font font8 = new Font(bf,10,Font.NORMAL); 
                Font font = new Font(bf,8,Font.NORMAL); 
                
                PdfPCell center =new PdfPCell();
                PdfPCell rigth = new PdfPCell();
                center.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                rigth.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                
             if (lista_detalle.length>0)
               {
                       //Añadimos una tabla de 3 columnas.      
                       document.addParagraph("", fleyendaIngles);
                       tabla.setWidthPercentage(100);
                       float[] medidaCeldas = {4,15,15,15,10,20};  //  
                       //Datos del ancho de cada columna.
                       tabla.setWidths(medidaCeldas);
                       Paragraph columna1 = new Paragraph("Nro",font8);
                       columna1.getFont().setStyle(Font.BOLD);
                        center=new PdfPCell(columna1);
                        center.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        center.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);     
                        center.setBackgroundColor(new BaseColor(225,242,255));
                       tabla.addCell(center);

                       Paragraph columna2 = new Paragraph("Administración de Aduana",font8);
                       columna2.getFont().setStyle(Font.BOLD);
                         center=new PdfPCell(columna2);
                         center.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                         center.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);          
                    center.setBackgroundColor(new BaseColor(225,242,255));
                         tabla.addCell(center);

                       Paragraph columna3 = new Paragraph("Número de Registro",font8);
                       columna3.getFont().setStyle(Font.BOLD);
                        center=new PdfPCell(columna3);
                        center.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        center.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);  
                    center.setBackgroundColor(new BaseColor(225,242,255));
                        tabla.addCell(center);
                       
                       Paragraph columna4 = new Paragraph("Fecha de Validación",font8);
                       columna4.getFont().setStyle(Font.BOLD);
                        center=new PdfPCell(columna4);
                        center.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        center.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);  
                    center.setBackgroundColor(new BaseColor(225,242,255));
                        tabla.addCell(center);
                       
                     
                       
                       Paragraph columna6 = new Paragraph("Nro. Documento",font8);
                       columna6.getFont().setStyle(Font.BOLD);
                       center=new PdfPCell(columna6);
                       center.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                       center.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);   
                    center.setBackgroundColor(new BaseColor(225,242,255));
                       tabla.addCell(center);
                       
                       Paragraph columna7 = new Paragraph("Nombre / Razón Social",font8);
                       columna7.getFont().setStyle(Font.BOLD);
                      center=new PdfPCell(columna7);
                      center.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                      center.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);    
                    center.setBackgroundColor(new BaseColor(225,242,255));
                      tabla.addCell(center);
                    
               for(int i=0;i<lista_detalle.length;i++)
               {
                      String detalle[]=lista_detalle[i].split("\\|");
                   
                       Paragraph txt_nro = new Paragraph( String.valueOf( i+1),font);                                                          
                       center=new PdfPCell(txt_nro);
                       center.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                       center.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                       tabla.addCell(center);
                   
                       Paragraph texto1 = new Paragraph(detalle[0],font);
                       center=new PdfPCell(texto1);
                       center.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                       center.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                       tabla.addCell(center);
                   
                       Paragraph texto2 = new Paragraph(detalle[1],font);
                       center=new PdfPCell(texto2);
                       center.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                       center.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                       tabla.addCell(center);
                   
                       Paragraph texto3 = new Paragraph(detalle[2],font);
                       center=new PdfPCell(texto3);
                       center.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                       center.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                       tabla.addCell(center);
                   
                       Paragraph texto4 = new Paragraph(detalle[3],font);
                      center=new PdfPCell(texto4);
                      center.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                      center.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                       tabla.addCell(center);
                   
                       Paragraph texto5 = new Paragraph(detalle[4],font);
                        center=new PdfPCell(texto5);
                        center.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        center.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                       tabla.addCell(center);
                   }
                    pdf.add(tabla);
                }
                
             //   document.addParagraph("La Paz, " + r.getFecha(), fleyenda);
                document.addParagraph("", fleyendaIngles);



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

                String textoEncryptado = "", url_encriptado = "";
                String cod_cert = r.getCodigo();

                try {
                    textoEncryptado = seg.encrypt(cod_cert);
                } catch (Exception e) {
                    textoEncryptado = "";
                }
                url_encriptado = baseURL + "VerificacionCert.do?id=" + textoEncryptado;

                boolean encriptadoOk = true;
                document.addQR(url_encriptado, 150);
              document.addImageByPath2("/u03/oracle/user_projects/data/certificacion/img/firma.jpg",20, 0);
            

            } else {
                document.addParagraph("<bu>NO EXISTE CERTIFICADO</bu>", fDatosOperador);
               
            }
            
        }


    public InputStream genera_documento(String cod, ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                        HttpServletResponse response,String email) {
        Seguridad seg = null;
        Sql sql = null;
        InputStream targetStream = null;
        UsuarioForm r = new UsuarioForm();
        try {
            seg = new Seguridad(ruta);
            sql = new Sql();
            r = sql.obtieneCertificado(cod);
        } catch (Exception e) {
            r.setRespuesta("NOK");
            r.setMensaje(e.getMessage().toString());
        } finally {
            sql.dispose();
        }
        try {
            response.setContentType("application/pdf");
            String marca = "";
            if (request.getParameter("wm") != null) {
                marca = request.getParameter("wm").toString();
            }
            /*******************************************************************************************************
                 * CREACION DEL PDF
                 ******************************************************************************************************/
            Document pdf = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(pdf, baos);
            
            if (marca.equals("prev")) {
                writer.setPageEvent(new Watermark());
            }
            
            ITextPdfHelper document = new ITextPdfHelper(pdf, path, ITextPdfFormat.PAGE_LETTER);
           
            if (r.getTipo_certificacion().equals("I"))
            {
                    if (r.getUsu_mail()!=null)
                        if (!r.getUsu_mail().trim().equals(""))
                        {
                                certificado_importacion(document,r,seg,writer,pdf);      
                                email=r.getUsu_mail();
                                request.getSession().setAttribute("email", email);
                            }
                    else
                        {
                                email="NOK";
                                request.getSession().setAttribute("email", email);
                            return null;
                            }
                                  
                }
            else
            {
                    certificado_no_importacion(document,r,seg,writer);    
                }
            pdf = document.getDocument();
            pdf.close();
            targetStream = new ByteArrayInputStream(baos.toByteArray());
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetStream;
    }


    public byte[] obtenerBytes(String path) {
        FileInputStream fileInputStream = null;
        File file = new File(path);
        byte[] bFile = new byte[(int)file.length()];
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();

            for (int i = 0; i < bFile.length; i++) {
                System.out.print((char)bFile[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bFile;
    }
    

    class Watermark extends PdfPageEventHelper {
        Font FONT = new Font(Font.FontFamily.HELVETICA, 52, Font.BOLD, new BaseColor(200, 200, 200, 50));

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            PdfGState gState = new PdfGState();
            gState.setFillOpacity(0.6f);
            cb.setGState(gState);

            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
                                       new Phrase("VISTA PREVIA", FONT), 397.5f, 321,
                                       writer.getPageNumber() % 2 == 1 ? 45 : -45);
        }
    }
    

    class FootMark extends PdfPageEventHelper {
        Font FONT = new Font(Font.FontFamily.HELVETICA, 6, Font.ITALIC, BaseColor.BLACK);
        private String text;

        public FootMark(String text) {
            super();
            this.text = text;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            PdfGState gState = new PdfGState();
            cb.setGState(gState);

            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_JUSTIFIED, new Phrase(text, FONT), 50,
                                       70, 0);
        }
    }
}

