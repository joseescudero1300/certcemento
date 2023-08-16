package bo.gob.aduana.archivo;

import bo.gob.aduana.archivo.VerificacionCert.Watermark;

import bo.gob.aduana.bean.UsuarioForm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import bo.gob.aduana.itextpdf.ITextPdfFormat;
import bo.gob.aduana.itextpdf.ITextPdfHelper;

import bo.gob.aduana.system.Seguridad;

import com.itextpdf.text.BaseColor;

import com.itextpdf.text.Document;

import com.itextpdf.text.Element;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

import javax.servlet.ServletOutputStream;

import org.bouncycastle.util.encoders.Base64;

public class Documento {
    String ruta = "/u03/oracle/user_projects/data/certificacion/app.properties";
    
    public InputStream aviso_primer_lugar(String lote,String monto,String fechaInicio, String fechafin) {

        Document pdf = new Document();
        String  archivo="";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream targetStream=null;
        
        try {
            Seguridad seg= new Seguridad(ruta);
            String path = new File(".").getCanonicalPath();
            PdfWriter.getInstance(pdf, baos);  
            ITextPdfHelper document = new ITextPdfHelper(pdf, path, ITextPdfFormat.PAGE_LETTER);
            ITextPdfFormat fTitulo = new ITextPdfFormat(16, ITextPdfFormat.STYLE_BOLD, ITextPdfFormat.ALIGN_CENTER, ITextPdfFormat.SPACING_1,0);
            ITextPdfFormat fFecha = new ITextPdfFormat(7, ITextPdfFormat.STYLE_NORMAL, ITextPdfFormat.ALIGN_RIGHT, ITextPdfFormat.SPACING_0,0);              
            ITextPdfFormat fCeldaDatoJustificado = new ITextPdfFormat(12, ITextPdfFormat.STYLE_NORMAL, ITextPdfFormat.ALIGN_JUSTIFY, ITextPdfFormat.SPACING_0,0);            
            document.setMargins(ITextPdfFormat.MARGIN_0dot5);
            document.open();                    
            /*******************************************************************************************************
             * DATOS DE CABECERA
             ******************************************************************************************************/                     
            /*String fechaInicio = "12/12/1980";  
            String fechafin = "12/12/1980";
            String lote="20182";
            String monto="2578";*/
            String parrafo1= new String("Usted debe efectuar el pago por el Lote Nº <b>"+lote+"</b> de Bs. <b>"+monto + "</b> en cualquier punto autorizado del Banco Unión a partir del <b>" +
                fechaInicio+"</b> hasta el <b>"+ fechafin+ "</b>, como fecha límite, caso contrario perderá el monto depositado como garantía y será suspedido para participar por 180 " +
                "días corridos en las Subastas de la Aduana Nacional. Realizado el pago, desde el día siguiente hábil se computa un plazo de 10 días hábiles, para que dentro " +
                "de ése plazo, la Administración Aduanera emita la resolución y la Declaración de Mercancías, por lo cual debe estar pendiente a su Búzon Electrónico constantemente, " +
                "para su notificación con lo indicado. \n\n" +
                "<b>IMPORTANTE.-</b> Para el pago del lote, genere su número de misceláneo en el Portal de Subasta, en la opción \"Formulario de Pago\".");
           
                document.addTable(4, 100,
                document.createImageCell("/u03/oracle/user_projects/data/certificacion/img/logo_form.jpg", 4),             
                document.createCell("NOTIFICACIÓN PRIMER LUGAR", fTitulo, 4,1, new BaseColor(100,200,150))/*,
                document.createCell(parrafo1, fCeldaDatoJustificado, 4,1, new BaseColor(225,242,255),20,true)*/
            );            
            String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());;
            //document.addParagraph("Fecha: "+fecha, fFecha);
            document.addParagraph("Aduana Nacional", fFecha);
            pdf = document.getDocument();
            pdf.close();
            //archivo = new String(Base64.encode(baos.toByteArray()), "UTF-8");
           // ByteArrayInputStream inStream = new ByteArrayInputStream( baos.toByteArray() );
             targetStream = new ByteArrayInputStream(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            archivo=e.getMessage().toString();
        }
        
        return targetStream;
    }
    
    public InputStream genera_doc(UsuarioForm r)
        {
            
            Document pdf = new Document();
            String  archivo="";
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            InputStream targetStream=null;
            Seguridad seg=null;
                try {
                     seg= new Seguridad(ruta);
                String path = new File(".").getCanonicalPath();
                
                PdfWriter writer = PdfWriter.getInstance(pdf, baos);
               
                
                ITextPdfHelper document = new ITextPdfHelper(pdf, path, ITextPdfFormat.PAGE_LETTER);
    
                ITextPdfFormat fTitulo =
                    new ITextPdfFormat(ITextPdfFormat.FONT_ARIAL, 13, ITextPdfFormat.STYLE_NORMAL, ITextPdfFormat.ALIGN_CENTER,
                                       ITextPdfFormat.SPACING_0, 0);
    
                ITextPdfFormat fDatosOperador =
                    new ITextPdfFormat(ITextPdfFormat.FONT_ARIAL, 15, ITextPdfFormat.STYLE_BOLD,
                                       ITextPdfFormat.ALIGN_CENTER, ITextPdfFormat.SPACING_0, 0);
                ITextPdfFormat fleyenda =
                    new ITextPdfFormat(ITextPdfFormat.FONT_ARIAL, 12, ITextPdfFormat.STYLE_NORMAL, ITextPdfFormat.ALIGN_JUSTIFY,
                                       ITextPdfFormat.SPACING_2, 0);
                ITextPdfFormat fleyendaIngles =
                    new ITextPdfFormat(ITextPdfFormat.FONT_ARIAL, 8, ITextPdfFormat.STYLE_ITALIC,
                                       ITextPdfFormat.ALIGN_JUSTIFY, ITextPdfFormat.SPACING_1dot5, 0);
                ITextPdfFormat freferencia =
                    new ITextPdfFormat(ITextPdfFormat.FONT_ARIAL, 12, ITextPdfFormat.STYLE_BOLD, ITextPdfFormat.ALIGN_RIGHT,
                                       ITextPdfFormat.SPACING_0, 0);
                ITextPdfFormat freferenciaIzq =
                    new ITextPdfFormat(ITextPdfFormat.FONT_ARIAL, 15, ITextPdfFormat.STYLE_BOLD, ITextPdfFormat.ALIGN_LEFT,
                                       ITextPdfFormat.SPACING_0, 0);
    
                document.setMargins(ITextPdfFormat.MARGIN_2);
                document.open();
                    document.addParagraph("", fTitulo);
                    document.addParagraph("", fleyendaIngles);
                    document.addParagraph("", fleyendaIngles);
                    document.addParagraph("<bu>CERTIFICACIÓN</bu>", fDatosOperador);
                    document.addParagraph("<bu>"+r.getCodigo()+"</bu>", fDatosOperador);
                    document.addParagraph("", fleyendaIngles);
                    document.addParagraph("<b>La Gerencia Nacional Jurídica</b>", freferencia);
                    document.addParagraph("<b>de la Aduana Nacional</b>", freferencia);
                    document.addParagraph("<b>CERTIFICA:</b>", freferenciaIzq);
                    document.addParagraph("", fleyendaIngles);
                    if(r.getUsu_tipo_usu().equals("N"))
                        document.addParagraph("De acuerdo a la verificación de la base de datos consolidad del Sistema Informática de Ejecuciones Tributarias - PIET, " +
                            "el Sr(a) <b>"+r.getUsu_nombre()+" "+r.getUsu_ap_pat()+" "+r.getUsu_ap_mat()+ "</b> " +
                                          " con CI <b>"+r.getUsu_nro_ident()+"</b>, <bu>NO REGISTRA</bu> adeudos tributarios con la Aduana Nacional.",
                                          fleyenda);
                    else
                        document.addParagraph("De acuerdo a la verificación de la base de datos consolidad del Sistema Informática de Ejecuciones Tributarias - PIET, " +
                            "la empresa <b>"+r.getUsu_razon_social()+"</b> " +
                                          " con NIT <b>"+r.getUsu_nro_ident()+"</b>, <bu>NO REGISTRA</bu> adeudos tributarios con la Aduana Nacional.",
                                          fleyenda);
                    
                    document.addParagraph("La presente certificación se emite en cumplimiento al Procedimiento de Registro y Gestión de Operadores de Comercio Exterior, conforme lo " +
                                          "dispuesto por la Resolución Administrativa Nº RA-PE-01-004-15 de 13/03/2015",
                                          fleyenda);
                    //if (r.getTipo_certificacion(""))
                    int tip=Integer.parseInt(r.getTipo_certificacion());
                    switch (tip) {
                            case 0:                        
                                document.addParagraph("Es cuanto se certifica, para su uso en "+r.getOtro()+".",
                                               fleyenda);
                            break;                 
                         case 1:   /*Autorizaciones previas*/                     
                             document.addParagraph("Es cuanto se certifica, en cumplimiento al Procedimiento para la emisión de Autorizaciones Previas de Importación, " +
                                 "en el marco del Decreto Supremo N° 2752 de 01 de mayo de 2016.",
                                            fleyenda);
                         break;
                         case 2:        /*Despacho directo*/                
                             document.addParagraph("Es cuanto se certifica, en cumplimiento a la Resolución Administrativa RA-PE 01-011-13 de 20/08/2013, " +
                                 "que aprueba el “Procedimiento para el Registro y Control de Importadores de Mercancías de Manera Directa”.",
                                            fleyenda);
                         break;
                         case 3:          /*Registro y gestion*/              
                             document.addParagraph("Es cuanto se certifica, en cumplimiento al Procedimiento de Registro y Gestión de Operadores de Comercio Exterior, " +
                                 "conforme lo dispuesto por la Resolución Administrativa N° RA-PE-01-004-15 de 13/03/2015.",
                                            fleyenda);
                         break;
                         case 4:    /*RITEX*/                    
                             document.addParagraph("Es cuanto se certifica, con el objeto de la incorporación al Régimen de Admisión Temporal para Perfeccionamiento Activo – RITEX, " +
                                 "aprobado mediante Decreto Supremo Nº 25706 con sus modificaciones.",
                                            fleyenda);
                         break;
                         case 5:    /*Observancia a 24 CPE*/                    
                             document.addParagraph("Es cuanto se certifica, en observancia al Art. 24 de la Constitución Política del Estado y el Art 129 del Código Tributario Boliviano.",
                                            fleyenda);
                         break;
                            default:                       
    
                            break;
                     }                              
                    document.addParagraph("La presente certificación tiene una validez de seis (6) meses a partir de su emisión.",
                                          fleyenda);
                    document.addParagraph("La Paz, "+r.getFecha(), fleyenda);
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
                        new Phrase("Certificado Generado en fecha: "+r.getFecha_hora(), new Font(bf, 8, Font.ITALIC));
    
                    ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, titreFormules, 360, 90, 0);
    
    
                    String textoEncryptado = "", url_encriptado = "";
                    String cod_cert = r.getCodigo();
    
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
                    document.addQR(url_encriptado, 150, 450f, 0f); //80, 70f, 65f
                pdf = document.getDocument();
                pdf.close();
                //archivo = new String(Base64.encode(baos.toByteArray()), "UTF-8");
                // ByteArrayInputStream inStream = new ByteArrayInputStream( baos.toByteArray() );
                 targetStream = new ByteArrayInputStream(baos.toByteArray());
                } catch (Exception e) {
                e.printStackTrace();
                archivo=e.getMessage().toString();
                }
            return targetStream;
        }
   

}
