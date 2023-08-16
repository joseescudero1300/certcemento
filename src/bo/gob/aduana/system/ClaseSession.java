package bo.gob.aduana.system;

/*   
*   Nombre de la clase: ClaseSession, variables de sesion para el sistema
*
*   Fecha creación, Fecha Modificación
*
*   Autor creador, Autor Modificador
*/
 
public class ClaseSession {   
    private String usuario = "";
    private String perfil = "";
    private String nit = "";        
    private String ci = "";      
    private String nombreUsuario;
    private String area="";        
    private String varAux="";
    private String nomGerencia;
    private String nomUnidad;
    private String correo;    
    
    private String aduana = ""; 
    private String descAduana = "";
    private int gestion=0;
    private String referencia="";  
    private String descReferencia = "";
    
    private double totalValorFob = 0;
    private double sumaTotalValorFob = 0;    
    private String aduanaAux = ""; 
    private String regimen ="";
    private String modalidad;
    private double cupoHabitual;    
    
    public ClaseSession() { 
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getAduana() {
        return aduana;
    }

    public void setAduana(String aduana) {
        this.aduana = aduana;
    }


    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

    public int getGestion() {
        return gestion;
    }

    public void setVarAux(String varAux) {
        this.varAux = varAux;
    }

    public String getVarAux() {
        return varAux;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNomGerencia(String nomGerencia) {
        this.nomGerencia = nomGerencia;
    }

    public String getNomGerencia() {
        return nomGerencia;
    }

    public void setNomUnidad(String nomUnidad) {
        this.nomUnidad = nomUnidad;
    }

    public String getNomUnidad() {
        return nomUnidad;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setTotalValorFob(double totalValorFob) {
        this.totalValorFob = totalValorFob;
    }

    public double getTotalValorFob() {
        return totalValorFob;
    }

    public void setSumaTotalValorFob(double sumaTotalValorFob) {
        this.sumaTotalValorFob = sumaTotalValorFob;
    }

    public double getSumaTotalValorFob() {
        return sumaTotalValorFob;
    }

    public void setAduanaAux(String aduanaAux) {
        this.aduanaAux = aduanaAux;
    }

    public String getAduanaAux() {
        return aduanaAux;
    }

    public void setDescAduana(String descAduana) {
        this.descAduana = descAduana;
    }

    public String getDescAduana() {
        return descAduana;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getCi() {
        return ci;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setDescReferencia(String descReferencia) {
        this.descReferencia = descReferencia;
    }

    public String getDescReferencia() {
        return descReferencia;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setCupoHabitual(double cupoHabitual) {
        this.cupoHabitual = cupoHabitual;
    }

    public double getCupoHabitual() {
        return cupoHabitual;
    }
}
