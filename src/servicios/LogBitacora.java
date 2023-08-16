package servicios;


/*
import bitacora.Consulta;
import bitacora.Exception;
import bitacora.WsBitacora;
*/
public class LogBitacora {
    private String codSistema;
    private String codOpcion;
    private String descOpcion;
    private String paramBusqueda;
    private String ip;
    private String usuario;
    private int logBitacora;
    
    public LogBitacora(String codOpcion, String descOpcion, String paramBusqueda, String ip, String usuario, int logBitacora){
        this.codSistema = "ADEUDO";
        this.codOpcion = codOpcion;
        this.descOpcion = descOpcion;
        this.paramBusqueda = paramBusqueda;
        this.ip = ip;
        this.usuario = usuario;
        this.logBitacora = logBitacora;
    }
    
    public String armaXML(LogBitacora log){
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><Documento>"+
                      "<sistema-cod>" + log.codSistema +"</sistema-cod>"+
                      "<opcion-cod>" + log.codOpcion + "</opcion-cod>"+
                      "<opcion-dsc>" + log.descOpcion + "</opcion-dsc>"+
                      "<parametro>" + log.paramBusqueda + "</parametro>"+
                      "<ip>" + log.ip + "</ip>"+
                      "<usuario>" + log.usuario + "</usuario></Documento>";

        return xml;
    }
    
    public void enviaLog(LogBitacora log){
        if(log.logBitacora > 0){
          /*  WsBitacora wsBitacora = new WsBitacora();
            Consulta consulta = wsBitacora.getBitacora();
            String res;
            try {
                res = consulta.setRegistrarLogReporte(armaXML(log));
            } catch (Exception e) {
                System.out.println("error BITACORA: " + e.getMessage());
            }*/
        }
    }
    
}
