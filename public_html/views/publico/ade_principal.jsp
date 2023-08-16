<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ page import="java.util.List"%>
<%@ page import="bo.gob.aduana.entidades.web.Importacion"%>
<link type="text/css" rel="stylesheet" href="lib/fancybox/jquery.fancybox.css"/>
<link href="lib/paper-boostrap/css/paper-bootstrap-wizard.css" rel="stylesheet"/>
<link href="lib/paper-boostrap/css/themify-icons.css" rel="stylesheet"></link>
<div>
    <img alt="logo correo" src="img/logo_email.png" style="display:none"/>
</div>
<div class="panel panel-primary" style="max-width: 1000px;margin:auto">
    <div class="panel-heading">
        <h3 class="panel-title">
            <strong>
                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>Pasos a seguir</strong>
          
           
            
        </h3>
    </div>        
        <div class="panel-body" style="padding: 0px 0px 0px 0px;">
            <div class="panel-body" style="padding: 0px 20px 0px 20px;">
                <div class="row">
                    <html:errors/>
                </div>
                <br>
                
                <br>
                <div class="card wizard-card" data-color="azure" id="wizard">
                    <div class="wizard-navigation">
                        <div class="progress-with-circle">
                            <div class="progress-bar" role="progressbar" aria-valuenow="1" aria-valuemin="1"
                                 aria-valuemax="3" style="width: 21%;"></div>
                        </div>
                        <ul>
                            <li >
                                <a href="#details" data-toggle="tab"   >
                                    <div class="icon-circle">
                                        <i class="ti-money"></i>
                                    </div>
                                    <div style="margin-left: -70px;margin-right: -70px;">Paso 1- Formulario Para Pago</div></a>
                            </li>
                             
                            <li>
                                <a href="#captain" data-toggle="tab">
                                    <div class="icon-circle">
                                        <i class="ti-agenda"></i>
                                    </div>
                                    <div style="margin-left: -70px;margin-right: -70px;">Paso 2- Emisi&oacute;n de
                                                                                         certificaci&oacute;n</div></a>
                            </li>
                        </ul>
                    </div>
                   
                    <div class="tab-content">
                        <div class="tab-pane" id="details" >
                            <%@ include file="/views/publico/ade_miscelaneo.jsp"%>
                        </div>
                        <div class="tab-pane" id="captain">
                            <%@ include file="/views/publico/ade_solCertificacion.jsp"%>
                        </div>
                    </div>
                  
                </div>
            </div>
        </div>

      <div id="responsive" class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
                  <div class="modal-dialog">
                
                    <!-- Modal content-->
                    <div class="modal-content">
                      <div class="modal-header" style="background-color:#F5F5F5;">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title" ><font color="#000034"><strong>Confirmaci&oacute;n de Generaci&oacute;n de Formulario para pago</strong></font></h4>
                      </div>
                      <div  class="modal-body">
                               <div style="
                                                            padding-top: 15px;
                                                            padding-left: 10px;
                                                            padding-right: 10px;
                                                            padding-bottom: 1px;">
                           <p style="text-align: justify;color:#000000;">
                            Est&aacuet; usted seguro/a de haber ingresado correctamente los datos, ya que una vez confirme su solicitud, 
                            esta informaci&oacute;n no podr&aacute; ser modificada.
                            </p>                            
                           <p style="text-align: justify;color:#252525;"><input id="chkacepto" value="1" type="checkbox" tabindex="6" />
                                He le&iacute;do y acepto los datos ingresados los cuales ser&aacute;n utilizados para generar el c&oacute;digo 
                                de pago y consiguientemente para la emisi&oacute;n de la certificaci&oacute;n.
                            </p>                            
                             <p><center><input type="button" id="btnclose4" data-dismiss="modal" class="btn btn-danger" tabindex="7" value="Cancelar" style="color: #ffffff;"/>
                                    <input type="button" id="btnacepto" class="btn btn-success" disabled="true" tabindex="7" value="Confirmar Solicitud" style="color: #ffffff;background-color:#77c3ac" /></center>
                                </p> 
                         </div>
                                        
                      </div>
                 <br>
                  
                 
                      </div>
                    </div>
                
                  </div>
      
</div>
<script src="lib/jquery/jquery-1.12.0.min.js?${sessionScope.VERSION_ASSETS}"></script>
<script src="lib/bootstrap/js/bootstrap.min.js"></script>
<script src="lib/datepicker/js/bootstrap-datepicker.js?s=2"></script> 
<script type="text/javascript" src="lib/fancybox/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="lib/datatables/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="lib/bootstrap/js/bootbox.min.js"></script>
<script src="lib/paper-boostrap/js/jquery.bootstrap.wizard.js" type="text/javascript"></script>
<script src="lib/paper-boostrap/js/demo.js" type="text/javascript"></script>
<script src="lib/paper-boostrap/js/paper-bootstrap-wizard.js" type="text/javascript"></script>
<script src="lib/paper-boostrap/js/jquery.validate.min.js" type="text/javascript"></script>
<script>

  $(document).ready(function () {
  
      $('#main-table').DataTable( {
          "sPaginationType" : "full_numbers", 
          //"aoColumnDefs" : [{"bVisible" : false, "aTargets" : [0]}], 
"aLengthMenu" : [[10, 25, 50,  - 1], [10, 25, 50, "Todos"]], "pageLength" : 50, "oLanguage" :  {
              "sSearch" : "Buscar", "sLengthMenu" : "Registros _MENU_", "sZeroRecords" : "No existe registros.", "sInfo" : "Mostrando _START_ al _END_ de _TOTAL_ registros", "sInfgetoEmpty" : "Mostrando 0 al 0 de 0 registros", "sProcessing" : "Cargando registros", "sEmptyTable" : "No existe registros para mostrar", "sInfoEmpty" : "", "sInfoFiltered" : "", "oPaginate" :  {
                  "sNext" : "»", "sPrevious" : "«", "sFirst" : "Primero", "sLast" : "\u00DAltimo"
              }
          }
      });
      $("#rb_tipo_usu_jur").click();
      $("#rb_cer_tipo_usu_jur").click();
      
      $(".lnkVistaPrevia").fancybox( {   
      'width': 600,
        'height': 750,
          type : "iframe", iframe :  {          
              preload : false
          }
      });
  });

  window.onload = function (e) {
      $("#lnkpopup").fancybox( {
        //  type : "iframe", 'width' : 800, 'height' : 600
         type : "iframe",
         iframe : {
         preload : false,
        css : {
            'width'  : 600,
            'height' : 600,           
            'margin': 0
        }
    }

      });
      // if($("#cantidad").val()==0)
      //  $("#lnkpopup").click();
      <%
            if(request.getAttribute("tab")!=null)  
            {
                   String  s=(String)request.getAttribute("tab");
                   if (s.equals("2"))
                   {
                   %>
                   $('.nav-pills a[href="#captain"]').tab('show');
                   <%
                   }
            }
        if(request.getAttribute("usuario")!=null)     
          if (f.getUsu_tipo_usu().equals("J"))
          {
          %>
           $( "#rb_tipo_usu_jur").click();      
          $("#rb_tipo_usu_jur").prop( "checked", true );
          $("#misc_nit").val("<%=f.getUsu_nro_ident()%>");  
          $("#miscusu_razon_social").val("<%=f.getUsu_razon_social()%>");  
           $("#miscdep").val("<%=f.getDep()%>").change();    
             $("#direccion").val("<%=f.getDireccion()%>");   
             $("#miscusu_mail").val("<%=f.getUsu_mail()%>"); 
          <%
          }      
          else
          {
          %>
          $( "#rb_tipo_usu_nat").click();  
           $("#miscextencion").val("<%=f.getExtencion()%>").change();   
             $("#misc_ci").val("<%=f.getUsu_nro_ident()%>");  
             $("#miscusu_nombre").val("<%=f.getUsu_nombre()%>"); 
             $("#miscusu_ap_pat").val("<%=f.getUsu_ap_pat()%>"); 
             $("#miscusu_ap_mat").val("<%=f.getUsu_ap_mat()%>"); 
             $("#miscfecNacimiento").val("<%=f.getFecNacimiento()%>");              
             $("#miscdep").val("<%=f.getDep()%>").change();       
             $("#direccion").val("<%=f.getDireccion()%>");   
             $("#miscusu_mail").val("<%=f.getUsu_mail()%>");   
          <%
          }
            %>
  }

  $(function () {
    

  });
  
  function open()
{
    $('.details').show();
}
  
  
  
  
</script>
 <style type="text/css">
/*.fancybox-skin,
.fancybox-outer,
.fancybox-inner{width:800px;   height: 600px;}*/
</style>