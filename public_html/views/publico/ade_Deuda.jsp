<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ page import="java.util.List"%>
<%@ page import="bo.gob.aduana.entidades.web.Importacion"%>
<%@ page import="bo.gob.aduana.bean.UsuarioForm"%>
<link type="text/css" rel="stylesheet" href="lib/fancybox/jquery.fancybox.css"/>
<%
    UsuarioForm f = ( UsuarioForm ) request.getAttribute ( "usuario" );
    String r="";
%>
<div class="panel panel-primary" style="max-width: 1100px;margin:auto">
    <div class="panel-heading">
        <h3 class="panel-title">
            <strong>
                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>Deudas</strong>
        </h3>
    </div>
     
    <div class="panel panel-info" style="
    margin-left: 20px;
    margin-right: 20px;">
    <div class="panel-body">
    <div class="row" >
                <html:errors/>
            </div> 
    </div>
        
        
        <br>
        <div class="row">                   
                    
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            <label for="lblnombre">N&uacute;mero de documento:</label>
                        </div>
                    </div>     
                     <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            
                            <%=f.getUsu_nro_ident()%>  
                      
                        </div>
                    </div> 
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            <label for="lblnombre">N&uacute;mero de Recibo:</label>
                        </div>
                    </div>     
                     <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            <%=f.getGestion()%>/<%=f.getAduana()%> /  R  -<%=f.getNumero()%>
                      
                        </div>
                    </div> 
                    
                </div>   
             <div class="row">                   
                    
                    
                    
                </div>       
        <%
        if(request.getAttribute("lista")!=null)   
           {
           List<Importacion> resp=(List)request.getAttribute("lista");
           %>
           
           <div class="table-responsive"     style="/*padding: 0px 15px 10px 20px;*/">
            <table class="table table-striped table-hover" id="main-table">
                <thead>
                    <tr>
                        <th class="text-center">Nro.</th>
                        <th class="text-center">Administraci&oacute;n de Aduana</th>
                        <th class="text-center">N&uacute;mero de Registro</th>
                        <th class="text-center">Fecha de validaci&oacute;n</th>
                        <th class="text-center">Nro. Documento</th>
                        <th class="text-center">Nombre/Raz&oacute;n Social</th>                                                
                    </tr>
                </thead>        
                <tbody>                           
                  <%
                    for(int i=0;i<resp.size();i++)
                    {
                    Importacion fila = new Importacion();
                    fila=resp.get(i);                                            
                  %>
                        <tr>
                            <td class="text-right"><%=(i+1)%></td>
                            <td class="text-center"><%=fila.getAduana_des()%></td>
                            <td class="text-center"><%=fila.getNro_registro()%></td>
                            <td class="text-center"><%=fila.getFecha_val()%></td>
                            <td class="text-center"><%=fila.getNro_doc()%></td>
                            <td class="text-center"><%=fila.getRazon_social()%></td>                       
                        </tr>
                  <%}%>
                </tbody>
            </table>
        </div>
      <br>        
        
   <%}%>
           
           
           
           
                  
    </div>      
   
</div>



 <script type="text/javascript" src="lib/fancybox/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="lib/datatables/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="lib/bootstrap/js/bootbox.min.js"></script>

<script>
$(document).ready(function(){ 
 $('#main-table').DataTable({
 

            "sPaginationType" : "full_numbers", 
            //"aoColumnDefs" : [{"bVisible" : false, "aTargets" : [0]}], 
            "aLengthMenu" : [[10, 25, 50,  - 1], [10, 25, 50, "Todos"]], 
             "pageLength": 50,
                "oLanguage" : {
                "sSearch" : "Buscar",
                "sLengthMenu" : "Registros _MENU_", 
                "sZeroRecords" : "No existe registros.", 
                "sInfo" : "Mostrando _START_ al _END_ de _TOTAL_ registros", 
                "sInfgetoEmpty" : "Mostrando 0 al 0 de 0 registros", 
                "sProcessing" : "Cargando registros",
                "sEmptyTable" : "No existe registros para mostrar",
                "sInfoEmpty": "",
                "sInfoFiltered": "",
               
                "oPaginate" : {
                    "sNext" : "&raquo;", "sPrevious" : "&laquo;", "sFirst" : "Primero", "sLast" : "\u00DAltimo"
                }
            }
 });
 $(".lnkVistaPrevia").fancybox({
        type: "iframe",
        iframe : {
            preload: false
        }
    });
});
 
 function detalle(plote){
    
            $.ajax({type : "POST", url: "views/consulta/DetalleDeuda.jsp", data: jQuery.param({ id: plote}) , success: function(result){            
            bootbox.alert({
            title: "<font color='#003366'><b>Detalle de la deuda con C&oacute;digo SET "+plote+"</b></font>",
            message: "<center><h2><font color='#000000'>"+result.replace(/^\s+|\s+$/g,"")+"</font></h2></center>"
            });
        }});
}
</script>

