package bo.gob.aduana.itextpdf;


import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;

import com.itextpdf.text.pdf.PdfStamper;

import com.itextpdf.text.pdf.PdfWriter;


import java.awt.Toolkit;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

import sun.font.FontFamily;

public class ITextPdfHelper {
    private Document _document;
    private String _mapPath;

    public ITextPdfHelper(Document document, String mapPath, int pageSize) {
        _document = document;
        _mapPath = mapPath;
        //_document.setMargins(85f, 85f, 70f, 70f);
        switch (pageSize) {
        case 1:
            _document.setPageSize(PageSize.LETTER);
            break;
        case 2:
            _document.setPageSize(PageSize.A4);
            break;
        case 3:
            _document.setPageSize(PageSize.LETTER.rotate());
            break;
        case 4:
            _document.setPageSize(PageSize.A4.rotate());
            break;        
        }
    }
    
    public void open(){
        _document.open();
    }

    public void setMargins(int type) {
        switch (type) {
        case ITextPdfFormat.MARGIN_0dot5:
            _document.setMargins(20f, 20f, 20f, 20f);
            break;
        case ITextPdfFormat.MARGIN_1:
            _document.setMargins(40f, 40f, 40f, 40f);
            break;
        case ITextPdfFormat.MARGIN_1dot5:
            _document.setMargins(60f, 60f, 60f, 60f);
            break;
        case ITextPdfFormat.MARGIN_2:
            _document.setMargins(80f, 80f, 80f, 80f);
            break;
        }

    }
    
    public void addImage(String image, float width, float height, float x, float y){
        Image img;
        try {
            img = Image.getInstance(_mapPath+image);
            img.scaleAbsolute(width, height);
            img.setAbsolutePosition(x, y);
            _document.add(img);
        } catch (BadElementException e) {
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } catch (DocumentException e) {
        } 
    }
    
    public void addImageByPath(String image, float width, float height, float x, float y){
        Image img;
        try {
            img = Image.getInstance(image);
            img.scaleAbsolute(width, height);
            img.setAbsolutePosition(x, y);
            _document.add(img);
        } catch (BadElementException e) {
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } catch (DocumentException e) {
        } 
    }
    
    public void addImageByPath2(String image, float width, float height){
        Image img;
        try {
            img = Image.getInstance(image);
           // img.scaleAbsolute(width, height);
           img.scalePercent(width);
            img.setAlignment(img.MIDDLE);
            _document.add(img);
        } catch (BadElementException e) {
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } catch (DocumentException e) {
        } 
    }
    
    public void addPdf417(String text, float width, float x, float y){
        try{
            BarcodePDF417 pdf417 = new BarcodePDF417();
            pdf417.setText(text);
            Image img = pdf417.getImage();
            img.scalePercent(width, width * pdf417.getYHeight());
            img.setAbsolutePosition(x, y);
            _document.add(img);
        } catch (BadElementException e) {
        } catch (DocumentException e) {
        }
    }
    
     public void addQR(String text, int width, float x, float y){
         try{
             BarcodeQRCode qrcode = new BarcodeQRCode(text.trim(), width, width, null);
              Image qrcodeImage = qrcode.getImage();
              qrcodeImage.setAbsolutePosition(x,y);
              //qrcodeImage.scalePercent(width);
             _document.add(qrcodeImage);
         } catch (BadElementException e) {
         } catch (DocumentException e) {
         }
     }
     
    public void addQR(String text, int width){
        Image img;
        try{
            BarcodeQRCode qrcode = new BarcodeQRCode(text.trim(), width, width, null);
             Image qrcodeImage = qrcode.getImage();       
            qrcodeImage.setAlignment(Element.ALIGN_CENTER);
            _document.add(qrcodeImage);
        /*    img = Image.getInstance("/u03/oracle/user_projects/data/certificacion/img/firma.jpg");
            // img.scaleAbsolute(width, height);
            img.scalePercent(20);
            img.setAlignment(Image.MIDDLE);
            _document.add(img);*/
        } catch (BadElementException e) {
        } catch (DocumentException e) {
        } 
    }
    

    public void incluirLogo() {
        String imageFilePath = _mapPath + "/img/logo_form.jpg";
        try {
            Image logo = Image.getInstance(imageFilePath);
            logo.scaleAbsolute(150f, 80f);
            logo.setAlignment(Image.UNDERLYING);
            if (_document.getPageSize() == PageSize.LETTER) {
                logo.setAbsolutePosition(30f, 680f);
            } else if (_document.getPageSize() == PageSize.A4) {
                logo.setAbsolutePosition(35f, 800f);
            }

            _document.add(logo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Document getDocument() {
        return _document;
    }

    /**
     * Agrega un parrafo al documento
     * @param text
     * @param format
     */
    public void addParagraph(String text, ITextPdfFormat format) {
        if(text == null){
            text = " ";
        } else if (text.equals("")){
            text = " ";
        }
        Paragraph paragraph = new Paragraph();
        BaseFont bf = null;
        try {
            switch(format.getFont()){
            case ITextPdfFormat.FONT_ARIAL:
                bf = BaseFont.createFont(_mapPath+"/fonts/arial.ttf", BaseFont.CP1252, BaseFont.EMBEDDED);
                break;
            case ITextPdfFormat.FONT_PTSANS:
                bf = BaseFont.createFont(_mapPath+"/fonts/PTS55F.ttf", BaseFont.CP1252, BaseFont.EMBEDDED);
                break;
            case ITextPdfFormat.FONT_ROBOTO:
                bf = BaseFont.createFont(_mapPath+"/fonts/RobotoCondensedLight.ttf", BaseFont.CP1252, BaseFont.EMBEDDED);
                break;
            case ITextPdfFormat.FONT_TIMES:
                    bf = BaseFont.createFont(_mapPath+"/fonts/times.ttf", BaseFont.CP1252, BaseFont.EMBEDDED);
                    break;
            default:
                bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
                break;
            }
            Font font = new Font(bf,10,Font.NORMAL);
            paragraph.setFont(font);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        switch (format.getAlignment()) {
        case 1:
            paragraph.setAlignment(Element.ALIGN_CENTER);
            break;
        case 2:
            paragraph.setAlignment(Element.ALIGN_LEFT);
            break;
        case 3:
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            break;
        case 4:
            paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
            break;
        default:
            paragraph.setAlignment(format.getAlignment());
            break;
        }
        
        paragraph.getFont().setSize(format.getSize());
        
        switch (format.getSpacing()) {
        case 1:
            paragraph.setSpacingBefore(3);
            break;
        case 2:
            paragraph.setSpacingBefore(6);
            break;
        case 3:
            paragraph.setSpacingBefore(9);
            break;
        case 4:
            paragraph.setSpacingBefore(12);
            break;
        default:
            paragraph.setSpacingBefore(format.getSpacing());
            break;
        }
        paragraph.setIndentationLeft(14 * format.getTabsIndent());
        paragraph.setLeading(0f, 1.1f);
        
        
       /* while (text.contains("<14>") && text.contains("</14>")) {
            int bTagPosition = text.indexOf("<14>");
            if (bTagPosition == 0) {
                //paragraph.add(new Chunk(text.substring(3, text.length() - (text.length() - text.indexOf("</b>")) - 3), new Font(Font.FontFamily.HELVETICA, format.getSize(), Font.BOLD)));
                paragraph.add(new Chunk(text.substring(4, text.indexOf("</14>")),
                                        new Font(bf, 14, Font.BOLD)));
                text = text.substring(text.indexOf("</14>") + 5);
            } else {
                paragraph.add(text.substring(0, text.length() - (text.length() - bTagPosition)));
                text = text.substring(bTagPosition);
            }
        }*/
        
        while (text.contains("<b>") && text.contains("</b>")) {
            int bTagPosition = text.indexOf("<b>");
            if (bTagPosition == 0) {
                //paragraph.add(new Chunk(text.substring(3, text.length() - (text.length() - text.indexOf("</b>")) - 3), new Font(Font.FontFamily.HELVETICA, format.getSize(), Font.BOLD)));
                paragraph.add(new Chunk(text.substring(3, text.indexOf("</b>")),
                                        new Font(bf, format.getSize(), Font.BOLD)));
                text = text.substring(text.indexOf("</b>") + 4);
            } else {
                paragraph.add(text.substring(0, text.length() - (text.length() - bTagPosition)));
                text = text.substring(bTagPosition);
            }
        }
        while (text.contains("<u>") && text.contains("</u>")) {
            int bTagPosition = text.indexOf("<u>");
            if (bTagPosition == 0) {
                //paragraph.add(new Chunk(text.substring(3, text.length() - (text.length() - text.indexOf("</b>")) - 3), new Font(Font.FontFamily.HELVETICA, format.getSize(), Font.BOLD)));
                paragraph.add(new Chunk(text.substring(3, text.indexOf("</u>")),
                                        new Font(bf, format.getSize(), Font.UNDERLINE)));
                text = text.substring(text.indexOf("</u>") + 4);
            } else {
                paragraph.add(text.substring(0, text.length() - (text.length() - bTagPosition)));
                text = text.substring(bTagPosition);
            }
        }
        while (text.contains("<bu>") && text.contains("</bu>")) {
            int bTagPosition = text.indexOf("<bu>");
            if (bTagPosition == 0) {
                //paragraph.add(new Chunk(text.substring(3, text.length() - (text.length() - text.indexOf("</b>")) - 3), new Font(Font.FontFamily.HELVETICA, format.getSize(), Font.BOLD)));
                paragraph.add(new Chunk(text.substring(4, text.indexOf("</bu>")),
                                        new Font(bf, format.getSize(), Font.BOLD|Font.UNDERLINE)));
                text = text.substring(text.indexOf("</bu>") + 5);
            } else {
                paragraph.add(text.substring(0, text.length() - (text.length() - bTagPosition)));
                text = text.substring(bTagPosition);
            }
        }
        
        switch (format.getStyle()) {
        case 1:
            paragraph.getFont().setStyle(Font.NORMAL);
            break;
        case 2:
            paragraph.getFont().setStyle(Font.BOLD);
            break;
        case 3:
            paragraph.getFont().setStyle(Font.ITALIC);
            break;
        case 4:
            paragraph.getFont().setStyle(Font.BOLDITALIC);
            break;
        case 5:
            paragraph.getFont().setStyle(Font.UNDERLINE);
            break;
        default:
            paragraph.getFont().setStyle(format.getStyle());
            break;
        }
        
        paragraph.add(text);
        try {
            _document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
        
    private Paragraph createParagraph(String text, ITextPdfFormat format) {
        if(text == null){
            text = " ";
        } else if (text.equals("")){
            text = " ";
        }
            
        Paragraph paragraph = new Paragraph();
        BaseFont bf = null;
        try {
            switch(format.getFont()){
            case ITextPdfFormat.FONT_ARIAL:
                bf = BaseFont.createFont(_mapPath+"/fonts/arial.ttf", BaseFont.CP1252, BaseFont.EMBEDDED);
                break;
            case ITextPdfFormat.FONT_PTSANS:
                bf = BaseFont.createFont(_mapPath+"/fonts/PTS55F.ttf", BaseFont.CP1252, BaseFont.EMBEDDED);
                break;
            case ITextPdfFormat.FONT_ROBOTO:
                bf = BaseFont.createFont(_mapPath+"/fonts/RobotoCondensedLight.ttf", BaseFont.CP1252, BaseFont.EMBEDDED);
                break;
            default:
                bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
                break;
            }
            Font font = new Font(bf,10,Font.NORMAL);
            paragraph.setFont(font);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*BaseFont times;
        try {
            times = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
            Font font = new Font(times, 10, Font.NORMAL);
            paragraph.setFont(font);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        
        switch (format.getAlignment()) {
        case 1:
            paragraph.setAlignment(Element.ALIGN_CENTER);
            break;
        case 2:
            paragraph.setAlignment(Element.ALIGN_LEFT);
            break;
        case 3:
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            break;
        case 4:
            paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
            break;
        default:
            paragraph.setAlignment(format.getAlignment());
            break;
        }
        switch (format.getStyle()) {
        case 1:
            paragraph.getFont().setStyle(Font.NORMAL);
            break;
        case 2:
            paragraph.getFont().setStyle(Font.BOLD);
            break;
        case 3:
            paragraph.getFont().setStyle(Font.ITALIC);
            break;
        case 4:
            paragraph.getFont().setStyle(Font.BOLDITALIC);
            break;
        case 5:
            paragraph.getFont().setStyle(Font.UNDERLINE);
            break;
        default:
            paragraph.getFont().setStyle(format.getStyle());
            break;
        }
        
        paragraph.getFont().setSize(format.getSize());

        switch (format.getSpacing()) {
        case 1:
            paragraph.setSpacingBefore(3);
            break;
        case 2:
            paragraph.setSpacingBefore(6);
            break;
        case 3:
            paragraph.setSpacingBefore(9);
            break;
        case 4:
            paragraph.setSpacingBefore(12);
            break;
        default:
            paragraph.setSpacingBefore(format.getSpacing());
            break;
        }
                
        paragraph.setIndentationLeft(14 * format.getTabsIndent());
        paragraph.setLeading(0f, 1.1f);
        while (text.contains("<b>") && text.contains("</b>")) {
            int bTagPosition = text.indexOf("<b>");
            if (bTagPosition == 0) {
                //paragraph.add(new Chunk(text.substring(3, text.length() - (text.length() - text.indexOf("</b>")) - 3), new Font(Font.FontFamily.HELVETICA, format.getSize(), Font.BOLD)));
                paragraph.add(new Chunk(text.substring(3, text.indexOf("</b>")),
                                        new Font(bf, format.getSize(), Font.BOLD)));
                text = text.substring(text.indexOf("</b>") + 4);
            } else {
                paragraph.add(text.substring(0, text.length() - (text.length() - bTagPosition)));
                text = text.substring(bTagPosition);
            }
        }
        paragraph.add(text);
        return paragraph;
    }

    public void addTable(int cellCount, int tableWidth, PdfPCell... cells) {
        PdfPTable table = new PdfPTable(cellCount);

        float anchoTabla = tableWidth * 5;
        if (_document.getPageSize().equals(PageSize.LETTER)) {
            anchoTabla = tableWidth * 4.1f;
        } else if (_document.getPageSize().equals(PageSize.LETTER.rotate())) {
            anchoTabla = tableWidth * 5;
        }
        
        float[] widths = new float[cellCount];
        for (int i = 0; i < widths.length; i++) {
            widths[i] = anchoTabla / cellCount;
        }
        //table.setTotalWidth(anchoTabla);
        table.setWidthPercentage(tableWidth);
        //table.setLockedWidth(true);
        try {
            //table.setWidths(widths);
            for (PdfPCell cell : cells) {
                if(cell != null){
                    table.addCell(cell);
                }
            }
            _document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    
    public void addTable(int columnCount, int tableWidth, float[] cellWidths, PdfPCell... cells) {
        PdfPTable table = new PdfPTable(columnCount);
        float anchoTabla = tableWidth * 5;
        if (_document.getPageSize().equals(PageSize.LETTER)) {
            anchoTabla = tableWidth * 4.1f;
        } else if (_document.getPageSize().equals(PageSize.LETTER.rotate())) {
            anchoTabla = tableWidth * 5;
        }
        table.setWidthPercentage(tableWidth);
        //table.setLockedWidth(true);
        try {
            table.setWidths(cellWidths);
            for (PdfPCell cell : cells) {
                if(cell != null){
                    table.addCell(cell);
                }
            }
            _document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void addTable(int columnCount, int tableWidth, float[] cellWidths, int headerRowsCount, boolean splitLastRow, PdfPCell... cells) {
        PdfPTable table = new PdfPTable(columnCount);
        float anchoTabla = tableWidth * 5;
        if (_document.getPageSize().equals(PageSize.LETTER)) {
            anchoTabla = tableWidth * 4.1f;
        } else if (_document.getPageSize().equals(PageSize.LETTER.rotate())) {
            anchoTabla = tableWidth * 5;
        }
        table.setWidthPercentage(tableWidth);
        //table.setLockedWidth(true);
        table.setHeaderRows(headerRowsCount);
        table.setSplitLate(false);
        try {
            table.setWidths(cellWidths);
            for (PdfPCell cell : cells) {
                table.addCell(cell);
            }
            _document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param text
     * @param format
     * @param colspan
     * @param rowspan
     * @param background
     * @return
     */
    public PdfPCell createCell(String text, ITextPdfFormat format, int colspan, int rowspan, BaseColor background) {
        Paragraph paragraph = createParagraph(text, format);
        PdfPCell cell = new PdfPCell();
        cell.addElement(paragraph);
        //cell.Border = 1;
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        if (background != null) {
            cell.setBackgroundColor(background);
        }
        return cell;
    }
    
    /**
     *
     * @param text
     * @param format
     * @param colspan
     * @param rowspan
     * @param background
     * @param valign
     * @return
     */
    public PdfPCell createCell(String text, ITextPdfFormat format, int colspan, int rowspan, BaseColor background, int valign) {
        Paragraph paragraph = createParagraph(text, format);
        PdfPCell cell = new PdfPCell();
        cell.addElement(paragraph);
        //cell.Border = 1;
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        switch(valign){
            case ITextPdfFormat.VALIGN_MIDDLE:
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            break;
            case ITextPdfFormat.VALIGN_TOP:
                cell.setVerticalAlignment(Element.ALIGN_TOP);
            break;
            case ITextPdfFormat.VALIGN_BOTTOM:
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            break;
        }
        if (background != null) {
            cell.setBackgroundColor(background);
        }
        return cell;
    }

    public PdfPCell createImageCell(byte[] image, int colspan) {
        PdfPCell cell = new PdfPCell();
        if (image != null) {
            Image img = null;
            try {
                img = Image.getInstance(image);
            } catch (BadElementException e) {
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
            cell.addElement(img);
            cell.setColspan(colspan);
        } else {

        }
        return cell;
    }
    
    public PdfPCell createImageCell(String image, int colspan) {
        PdfPCell cell = new PdfPCell();
        if (image != null) {
            Image img = null;
            try {
                img = Image.getInstance(image);
            } catch (BadElementException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            cell.addElement(img);
            cell.setColspan(colspan);
        } else {

        }
        return cell;
    }
    
    public PdfPCell createImageCell(URL image, int colspan) {
        PdfPCell cell = new PdfPCell();
        if (image != null) {
            Image img = null;
            try {
                img = Image.getInstance(image);
            } catch (BadElementException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            cell.addElement(img);
            cell.setColspan(colspan);
        } else {

        }
        return cell;
    }

    public PdfPCell createCell(Element element, ITextPdfFormat format, int colspan, BaseColor background) {
        PdfPCell cell = new PdfPCell();
        cell.addElement(element);
        cell.setColspan(colspan);
        if (background != null) {
            cell.setBackgroundColor(background);
        }
        return cell;
    }
    /*
    public byte[] Paginar(byte[] byteDocumento)
    {
        MemoryStream ms = new MemoryStream();
        PdfReader pdfDocumento = new PdfReader(byteDocumento);
        int numeroPaginas = pdfDocumento.NumberOfPages;
        PdfStamper stamp = new PdfStamper(pdfDocumento, ms);
        int i = 0;

        PdfContentByte pdfDocumentoModificado;
        while (i < numeroPaginas)
        {
            i++;
            pdfDocumentoModificado = stamp.GetOverContent(i);
            Phrase piePagina = new Phrase("P�g. " + i + " de " + numeroPaginas);
            piePagina.Font.Size = 8;
            piePagina.Font.SetStyle(Font.ITALIC);
            ColumnText.ShowTextAligned(pdfDocumentoModificado,
            Element.ALIGN_RIGHT, piePagina, 550, 40, 0);
        }
        stamp.Close();


        byte[] byteDocumentoPaginado = ms.ToArray();
        ms.Close();
        return byteDocumentoPaginado;
    }

    public byte[] MarcarSinValorLegal(byte[] byteDocumento)
    {
        MemoryStream ms = new MemoryStream();
        PdfReader pdfDocumento = new PdfReader(byteDocumento);
        int numeroPaginas = pdfDocumento.NumberOfPages;
        PdfStamper stamp = new PdfStamper(pdfDocumento, ms);
        int i = 0;
        Image watermark_image = Image.GetInstance(_mapPath + "/UI/img/sinvalor.png");
        watermark_image.SetAbsolutePosition(0, 40);
        watermark_image.Transparency = new int[] { 1, 1 };
        PdfContentByte add_watermark;
        while (i < numeroPaginas)
        {
            i++;
            add_watermark = stamp.GetOverContent(i);
            add_watermark.AddImage(watermark_image);
        }
        stamp.Close();
        byte[] byteImagenSinValorLegal = ms.ToArray();
        ms.Close();
        return byteImagenSinValorLegal;
    }
    */ 

    
}
