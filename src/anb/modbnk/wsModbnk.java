package anb.modbnk;

import bo.gob.aduana.system.Seguridad;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import web.Consulta;
import web.Wsfmismodbnk;

public class wsModbnk {
    public String generaFormulario(String xml)
    {
        String codigo="";
        String ruta = "/u03/oracle/user_projects/data/certificacion/app.properties";
        
        
        try
        {   
            List<String> list = new ArrayList<String>();
            Segmodbnk seg = new Segmodbnk(ruta); 
            
            URL url, baseUrl;
            baseUrl = Wsfmismodbnk.class.getResource(".");
            url = new URL(baseUrl, seg.ws_url);
            
            QName namespace = new QName(seg.wsdlNamespace, seg.wsdlServiceName);
            Wsfmismodbnk wsfmismodbnk = new Wsfmismodbnk(url, namespace);
            Consulta consulta = wsfmismodbnk.getWsfmismodbnk();
            
            list=encXmlAspb(xml,seg);
            list=consulta.registroFormMiscelaneo(seg.key_identificador, list); 
            String cadena=seg.decryptList(list);
           // System.out.println(cadena);
            return cadena;
        }
        catch(Exception e)
        {
            codigo="NOK|"+e.getMessage();
            }
        return codigo;
    }
    public ArrayList<String> encXmlAspb(String xml,Segmodbnk seg) throws Exception{        
        ArrayList<String> lista = new ArrayList<String>();
        int longitud = 200;
        do {
            if (xml.length() > longitud) {
                lista.add(seg.encrypt(xml.substring(0, longitud)));
                xml = xml.substring(longitud);
            } else {
                lista.add(seg.encrypt(xml));
                xml = "";
            }

        } while (xml.length() > 0);                    
        return lista;
    }    
}
