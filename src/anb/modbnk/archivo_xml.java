package anb.modbnk;

public class archivo_xml {
	
	public String xml_ar="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + 
	"<documento>\n" + 
	"  <origen>SUBASTAS</origen>\n" + 
	"  <datosFormulario>\n" + 
	"    <tipoDocumento></tipoDocumento>\n" + 
	"    <nroDocumento></nroDocumento>\n" + 
	"    <nombreOrazonSocial></nombreOrazonSocial>\n" + 
	"    <direccion></direccion>\n" + 
	"    <codigoAduana></codigoAduana>\n" + 
	"    <codigoDocumentoPago>10</codigoDocumentoPago>\n" + 
	"    <codigoTipoOperacion>5</codigoTipoOperacion>\n" + 
	"    <codigoUsuario></codigoUsuario>\n" + 
	"    <correoElectronico></correoElectronico>\n" + 
	"    <mensajeParaCorreo></mensajeParaCorreo>\n" + 
	"  </datosFormulario>\n" + 
	"  <detalleConceptos>\n" + 
	"    <detalleConcepto>\n" + 
	"      <codigoConcepto></codigoConcepto>\n" + 
	"      <referencia></referencia>\n" + 
	"      <importe></importe>\n" + 
	"    </detalleConcepto>\n" + 
	"  </detalleConceptos>\n" + 
	"</documento>";
	
	public String inserta(String xml,String init,String dato)
	{
		String end="</"+init+">";
		init="<"+init+">";
		
		
		
		int inicio=xml.indexOf(init)+init.length();
		int fina=xml.indexOf(end);
		 
		 String ini=xml.substring(0,inicio); 
		 String fin=xml.substring(fina,xml.length());
		 xml=ini+dato+fin;
		
		 return xml;
	}
	public String obtiene(String xml,String init)
	{
		String end="</"+init+">";
		init="<"+init+">";
				
		int inicio=xml.indexOf(init)+init.length();
		int fina=xml.indexOf(end);
		 
		 String ini=xml.substring(inicio,fina); 
		 
	
		
		 return ini;
	}


    public void setXml_ar(String xml_ar) {
        this.xml_ar = xml_ar;
    }

    public String getXml_ar() {
        return xml_ar;
    }
}

