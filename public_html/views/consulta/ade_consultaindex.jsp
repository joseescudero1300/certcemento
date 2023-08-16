<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ page import="java.util.List"%>
<%@ page import="bo.gob.aduana.entidades.web.Importacion"%>

<link type="text/css" rel="stylesheet" href="lib/fancybox/jquery.fancybox.css"/>
<div>
<img alt="logo correo" src="img/logo_email.png" style="display:none"/>
</div>
<div class="panel panel-primary" style="max-width: 1200px;margin:auto">
    <div class="panel-heading">
        <h3 class="panel-title">
            <strong><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Consulta Adeudos Tributarios</strong>
             
            <button class="btn btn-link pull-right btn-toggle" type="button" id="lnkpopup" 
            style="color: white; margin-top: -10px;" data-fancybox-type="fancybox fancybox.iframe"
             href="https://www.aduana.gob.bo/aduana7/sites/default/files/kcfinder/files/aplicaciones/CertificacionNoAdeudo/Roller_Informativo-CNAT.pdf#page=1&zoom=160">                
              <i class="fa fa-file-pdf-o" aria-hidden="true"></i>   
             Procedimiento
            </button>
      
        </h3>
    </div>
    <html:form action="consultaAdeudo.do" styleId="formRegistro" styleClass="validable" style="max-width:100%;margin:auto">
    <input type="hidden" id="usu_tipo_usu" name="usu_tipo_usu" value="">
    <input type="hidden" id="opcion" name="opcion" value="buscar">
    <input type="hidden" id="usu_tipo_doc" name="usu_tipo_doc" value="">
    <%
   int can=0;
   if(request.getAttribute("cantidad")!=null)   
   {
      can=2;
   }
   %>
    
    <input type="hidden" id="cantidad" name="cantidad" value="<%=can%>">
    <div class="panel-body" style="padding: 0px 0px 0px 0px;">
       
   
            <div class="panel-body" style="padding: 0px 15px 0px 20px;">
            
            <div class="row" >
                <html:errors/>
            </div>
            <br>
            
            <div class="row">
                    <div class="col-xs-12 col-sm-3 col-md-4">
                        <div class="form-group" >
                                                  
                        </div>
                    </div> 
                    <div class="col-xs-12 col-sm-6 col-md-4">
                        <div class="form-group" >
                            <label for="lblTipoPersona"> Tipo de Persona:</label>                                        
                        </div>
                    </div> 
                    <div class="col-xs-12 col-sm-3 col-md-4">
                        <div class="form-group" >
                                                  
                        </div>
                    </div> 
                   
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-3 col-md-4">
                        <div class="form-group" >
                                                  
                        </div>
                    </div> 
                    <div class="col-xs-12 col-sm-3 col-md-2">
                        <div class="form-group" >
                          
                            <input   type="radio" id="rb_tipo_usu_nat" name="usu_tipo" value="N" onclick="cambia('N')" />PERSONA NATURAL
                                           
                        </div>
                    </div> 
                     <div class="col-xs-12 col-sm-3 col-md-2">
                        <div class="form-group" >
                   
                            <input   type="radio" id="rb_tipo_usu_jur" name="usu_tipo" value="J" onclick="cambia('J')"/>PERSONA JUR&Iacute;DICA  O EMPRESA UNIPERSONAL                        
                        </div>
                    </div> 
                    <div class="col-xs-12 col-sm-3 col-md-4">
                        <div class="form-group" >
                                                  
                        </div>
                    </div> 
                   
                </div>
                 <div class="row">
                    <div class="col-xs-12 col-sm-3 col-md-4">
                        <div class="form-group" >
                                                  
                        </div>
                    </div> 
                    <%/*
                    <div class="col-xs-12 col-sm-4">
                        <div class="form-group">
                            <label for="lblTipoPersona" style="display:none;">Tipo de Documento:</label><br>
                             
                            <input type="text" maxlength="10" value="" readonly="true" class="form-control" id="lbltipodoc"
                                   style="background-color: white;border-color:white"></input>
                        </div>
                    </div>*/%>
                    <div class="col-xs-12 col-sm-6 col-md-4">
                        <div class="form-group">
                            <input type="text" maxlength="10" value="" readonly="true" class="form-control" id="lbltipodoc"
                                   style="background-color: white;border-color:white"></input>
                            <input type="text" id="usu_nro_ident" name="usu_nro_ident" class="form-control text-left text-uppercase no-zero required" onblur="this.value = this.value.toUpperCase();" maxlength="15" step="1"/>
                        </div>
                    </div> 
                    <div class="col-xs-12 col-sm-3 col-md-4">
                        <div class="form-group" >
                                                  
                        </div>
                    </div> 
                    
                   <%/*  <div class="col-xs-12 col-sm-6" id="campo_extencion" style="display:none;">
                       <div class="form-group">
                            <label for="lblnombre"><font color="red">*</font>Extensi&oacute;n del documento:</label><br>                             
                            <html:select property="usu_extencion" styleId="usu_extencion" styleClass="form-control col-lg-1">
                            <option value="">--Seleccione--</option>
                            <option value="LP">La Paz</option>
                            <option value="OR">Oruro</option>
                            <option value="CB">Cochabamba</option>
                            <option value="SC">Santa Cruz</option>
                            <option value="PO">Potosi</option>
                            <option value="CH">Chuquisaca</option>
                            <option value="TA">Tarija</option>
                            <option value="BE">Beni</option>
                            <option value="PA">Pando</option>
                        </html:select>
                        </div>
                    </div>*/%>
                </div>
                
                <div class="row">
                <div class="col-xs-12 col-sm-3 col-md-4">
                        <div class="form-group" >
                                                  
                        </div>
                    </div> 
                   <div class="col-xs-12 col-sm-6 col-md-4">
                           <div class="form-group" >
                                <% nl.captcha.Captcha captcha = new nl.captcha.Captcha.Builder(200, 50)
                                .addText()
                                .build();
                                %>
                                <img src="simpleCaptcha.png" class="pull-left"  id="simpleCaptcha" />                                
                            <a href="#" id="cambiarCaptcha" tabindex="-1" style="text-decoration: none;color: rgb(22, 85, 154);font-style: italic"><i class="fa fa-magic"></i>Cambiar C&oacute;digo de Seguridad</a>                
                            </div>
                   </div>
                      <div class="col-xs-12 col-sm-3 col-md-4">
                        <div class="form-group" >
                                                  
                        </div>
                    </div> 
               </div>
               
                <div class="row">
                   
                       <div class="col-xs-12 col-sm-3 col-md-4">
                        <div class="form-group" >
                                                  
                        </div>
                    </div> 
                      <div class="col-xs-12 col-sm-6 col-md-4">
                        <div class="form-group">
                            <label for="lblcaptcha">C&oacute;digo de Seguridad:</label>
                            <input type="text" id="txtCaptcha" name="captcha" class="form-control text-left required" maxlength="15"  />
                        </div>
                    </div> 
                    <div class="col-xs-12 col-sm-3 col-md-4">
                        <div class="form-group" >
                                                  
                        </div>
                    </div> 
                      
               </div>              
               
               
            </div>
              <br> 
             <%/* <a class="lnkVistaPrevia" data-fancybox-type="fancybox fancybox.iframe" href="CertificadoAdeudo.do"> Ver Documento</a>*/%>
               
                
                
                <div class="panel panel-default" style="padding: 0px 15px 0px 20px;">
               
            <div class="row">
            <div class="col-xs-0 col-sm-0 col-md-2">
                </div>
                <div class="col-xs-12 col-sm-12 col-md-9">
                    <strong class="text-uppercase" style="color: #333; background-color: #f5f5f5;">RECIBO DE PAGO POR LA CERTIFICACI&Oacute;N (CONCEPTO: 158 "LEGALIZACIONES CERTIFICACIONES")</strong>
                </div>
                <div class="col-xs-0 col-sm-0 col-md-1">
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-xs-0 col-sm-0 col-md-2">
                </div>
                    <div class="col-xs-6 col-sm-3 col-md-2">
                        <div class="form-group">
                            <label for="lblgestion">Gesti&oacute;n</label><br>
                             
                            <html:text property="gestion" size="20" onblur="this.value=this.value.toUpperCase()"
                                       maxlength="4" styleId="gestion" styleClass="form-control"/>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2">
                        <div class="form-group">
                            <label for="lbladuana">Aduana</label><br>
                             
                            <html:text property="aduana" size="20" onblur="this.value=this.value.toUpperCase()"
                                       maxlength="3" styleId="aduana" styleClass="form-control"/>
                        </div>
                    </div>
                   
                    <div class="col-xs-6 col-sm-3 col-md-2">
                        <div class="form-group">
                            <label for="lblserial">Serial</label><br>
                             
                            <input type="text" maxlength="10" value="/  R  -" readonly="true" class="form-control"
                                   style="background-color: white;border-color:white"></input>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2">
                        <div class="form-group">
                            <label for="lblnumero">N&uacute;mero</label><br>
                             
                            <html:text property="numero" size="20" onblur="this.value=this.value.toUpperCase()"
                                       maxlength="10" styleId="numero" styleClass="form-control"/>
                        </div>
                    </div>
                <div class="col-xs-0 col-sm-0 col-md-2">
                </div>
            </div>
        </div>
                
                
                <div class="col-sm-12" style="padding: 0px 0px 15px 0px;" align="center">               
                    <input type="button" name="guardar" id="guardar" class="btn btn-primary" value="Consultar"/>                           
                </div>
                
            </div>             
     </html:form> 
     
   <%
   if(request.getAttribute("nro_doc")!=null)
   {
    String l_nro_doc =(String)request.getAttribute("nro_doc");
    String l_tipo_doc =(String)request.getAttribute("tipo_doc");
    String l_gestion =(String)request.getAttribute("gestion");
    String l_aduana =(String)request.getAttribute("aduana");
    String l_numero =(String)request.getAttribute("numero");
        %>
        <html:form action="consultaAdeudo.do" styleId="formSolicitud">
            <input type="hidden" id="l_tipo_doc" name="usu_tipo_doc" value="<%=l_tipo_doc%>">
            <input type="hidden" id="opcion" name="opcion" value="llenar">
            <input type="hidden" id="l_nro_doc" name="usu_nro_ident" value="<%=l_nro_doc%>">
            <input type="hidden" id="l_gestion" name="gestion" value="<%=l_gestion%>">
            <input type="hidden" id="l_aduana" name="aduana" value="<%=l_aduana%>">
            <input type="hidden" id="l_numero" name="numero" value="<%=l_numero%>">
        </html:form> 
        <%
   }
   String x="0";
   if(request.getAttribute("lista")!=null)   
   {
  
   %>
        <div class="table-responsive"     style="padding: 0px 15px 10px 20px;">
            <table class="table table-striped table-hover" id="main-table">
                <thead>
                    <tr>
                        <th class="text-center">Nro.</th>
                        <th class="text-center">C&oacute;digo SET</th>
                        <th class="text-center">C&oacute;digo TET</th>
                        <th class="text-center">C&oacute;digo PIET</th>
                        <th class="text-center">Gerencia Regional</th>
                        <th class="text-center">Nro. Documento</th>
                        <th class="text-center">Nombre/Raz&oacute;n Social</th>                        
                        <th class="text-center">Deuda Inicial (Bs.)</th>
                        <th class="text-center">Estado</th>
                        <th class="text-center">Detalle</th>
                    </tr>
                </thead>        
                <tbody>                           
                  
                </tbody>
            </table>
        </div>
      <br>
        <blockquote style="
                         margin: 0px 50px 0px 50px; 
                        /* padding: 10px 20px 10px 10px; */
                        font-weight: 300;
                        border-top: 2px solid #ff0000;
                        border-left: 2px solid #ff0000;
                        border-bottom: 2px solid #ff0000;
                        border-right: 2px solid #ff0000;
                        text-align: center;
                        ">
            <span style="font-style:italic;font-size: 14px;color: black;">
                <strong>"Se le exhorta apersonarse a las oficinas de la Administraci&oacute;n Aduanera a efectos de cumplir con sus obligaciones tributarias pendientes de pago."                
                </strong>
            </span>
        </blockquote>
        <br>
   <%}%>
   
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
$("#rb_tipo_usu_nat").click();
//document.getElementById('rb_tipo_usu_nat').checked = true;
$(".lnkVistaPrevia").fancybox({
        type: "iframe",
        iframe : {
            preload: false
        }
    });
});

window.onload = function(e){ 
    
    $("#lnkpopup").fancybox({
        type: "iframe",
        'width': 800,
        'height': 600
        
    });
    if($("#cantidad").val()==0)
        $("#lnkpopup").click();

}

$(function(){
       $("#cambiarCaptcha").click(function(e){           
           e.preventDefault();
           var d = new Date();
           $("#simpleCaptcha").attr("src", "simpleCaptcha.png?"+d.getTime());
        });
       $("#solicitarForm").click(function(e){           
           e.preventDefault();
          $("#formSolicitud").submit();
        });
        $("#guardar").click(function(e){           
           e.preventDefault();
           msg="";
           
           if ($('#usu_tipo_usu').val()=='N')
           {                
                if($("#usu_nro_ident").val().trim()=='')
                   {
                        msg=msg+'El campo N\u00FAmero de Carnet de Identidad es obligatorio.\n';
                   }
                   else
                   {
                        if(!checkMask($('#usu_nro_ident').val().trim(),'^([0-9]{5,14}|[0-9]{5,14}(-[A-Z0-9]{1,3}))$'))
                            msg = msg + 'Formato de N\u00FAmero de Documento de identidad incorrecto.\n';
                   }                
               /* if($("#usu_extencion").val().trim()=='')
                {
                    msg=msg+'El campo Extensi\u00F3n del documento es obligatorio.\n';
                }*/
           }   
           else
           {
                if ($('#usu_tipo_usu').val()=='J')
                if($("#usu_nro_ident").val().trim()=='')
                   {
                        msg=msg+'El campo N\u00FAmero de Identificaci\u00F3n Tributaria es obligatorio.\n';
                   }
                   else
                   {
                        if (!isNit($('#usu_nro_ident').val().trim()))
                           msg = msg + 'NIT no es v\u00E1lido \n';
                   }  
           }
           if($("#txtCaptcha").val().trim()=='')
           {
                msg=msg+'El campo C\u00F3digo de Seguridad es obligatorio.\n';
           }
           
           if ($('#gestion').val().length!=4)
        {
            msg = msg + 'Gesti\u00F3n de recibo de pago no tiene la longitud correcta.\n';    
        }
        else
        {
            if (isNaN($('#gestion').val()))
                msg = msg + 'Gesti\u00F3n de recibo de pago tiene formato incorrecto (yyyy).\n';
        }
        if ($('#aduana').val().length!=3)
        {
            msg = msg + 'Aduana de recibo de pago no tiene la longitud correcta.\n';    
        }
        else
        {
            if (isNaN($('#aduana').val()))
                msg = msg + 'Aduana de recibo de pago con formato incorrecto (Ej: 201).\n';
        }
        
        if ($('#numero').val().trim()=='')
        {
            msg = msg + 'N\u00FAmero de recibo de pago obligatorio.\n';    
        }
        else
        {
            if (isNaN($('#numero').val()))
                msg = msg + 'N\u00FAmero de recibo de pago con formato incorrecto (Solo numeros).\n';
        }
           
           
           if(msg=='')
           {           
                $('#dvLoading').show();
                $("#formRegistro").submit();
           }             
            else
                alert(msg);
        });
        
        
    });  



function cambia(valor) {
      if(valor=='N') {  
        $('#usu_tipo_usu').val('N');
        $('#usu_tipo_doc').val('CI');
        $('#lbltipodoc').val('N\u00FAmero de Carnet de Identidad:');
      //  $('#campo_extencion').show();       
      } else {
       if(valor=='J') {  
        $('#usu_tipo_usu').val('J');
        $('#usu_tipo_doc').val('NIT');
        $('#lbltipodoc').val('N\u00FAmero de Identificaci\u00F3n Tributaria:');
        $('#campo_extencion').hide();       
       }
      }
    }
function impresion()
{
    $("#formImpresion").submit();
}

function detalle(plote){
    
            $.ajax({type : "POST", url: "views/consulta/DetalleDeuda.jsp", data: jQuery.param({ id: plote}) , success: function(result){            
            bootbox.alert({
            title: "<font color='#003366'><b>Detalle de la deuda con C&oacute;digo SET "+plote+"</b></font>",
            message: "<center><h2><font color='#000000'>"+result.replace(/^\s+|\s+$/g,"")+"</font></h2></center>"
            });
        }});
}

</script>