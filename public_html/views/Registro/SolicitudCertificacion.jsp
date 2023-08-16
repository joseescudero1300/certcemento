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
<div class="panel panel-primary" style="max-width: 1000px;margin:auto">
    <div class="panel-heading">
        <h3 class="panel-title">
            <strong>
                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>Formulario de Solicitud de Certificaci&oacute;n</strong>
        </h3>
    </div>
    <html:form action="/solicitud" styleId="formSolicitud" styleClass="validable" style="max-width:100%;margin:auto">
       
         
        <div class="panel-body" style="padding: 10px 15px 0px 20px;">      
        <div class="row" >
                <html:errors/>
            </div> <br>
            <div class="panel panel-default" style="padding: 0px 10px 0px 10px;">
                <div class="panel-heading">
                    <strong class="text-uppercase">SOLICITANTE</strong>
                </div>
                <br>
                <%
                if (f.getUsu_tipo_usu().equals("N"))
                {
                    %>
                     <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            <label for="lblnombre">Tipo de Persona:</label>
                             
                            <html:hidden property="usu_tipo_usu" styleId="usu_tipo_usu" value="N"/><br>
                             PERSONA NATURAL                           
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            <label for="lblnombre">Tipo de Documento:</label>
                             
                            <html:hidden property="usu_tipo_doc_en" styleId="usu_tipo_doc_en" value="<%=f.getUsu_tipo_doc_en()%>"/><br>
                             
                            <label>CI</label>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            <label for="lblnombre">N&uacute;mero de documento:</label><br>                             
                            <html:hidden property="usu_nro_ident_en" styleId="usu_nro_ident_en" value="<%=f.getUsu_nro_ident_en()%>"/>
                             <label>
                            <%=f.getUsu_nro_ident()%>
                             </label>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            <label for="lblnombre">Extensi&oacute;n del documento:</label><br>                             
                            <html:select property="dep" styleId="dep" styleClass="form-control">
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
                    </div>
                </div>               
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            <label for="lblnombre">Nombre(s):</label>
                             
                            <html:text property="usu_nombre" size="30" maxlength="50"
                                       onblur="this.value=this.value.toUpperCase()" styleId="usu_nombre"
                                       styleClass="form-control"/>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            <label for="lblapellido">Primer Apellido:</label>
                             
                            <html:text property="usu_ap_pat" size="30" onblur="this.value=this.value.toUpperCase()"
                                       maxlength="30" styleId="usu_ap_pat" styleClass="form-control"/>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            <label for="lblmaterno">Segundo Apellido:</label>
                             
                            <html:text property="usu_ap_mat" size="30" onblur="this.value=this.value.toUpperCase()"
                                       maxlength="30" styleId="usu_ap_mat" styleClass="form-control"/>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            <label for="lblfecha">Fecha de nacimiento:</label>
                             <div class="input-group date" data-provide="datepicker" data-date-format="mm/dd/yyyy">
                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>                                     
                                <html:text styleClass="date form-control" styleId="fecNacimiento" property="fecNacimiento"  maxlength="10"/>
                            </div>
                           
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <label for="lblnombre">Correo Electr&oacute;nico:</label>
                             
                            <html:text property="usu_mail" size="30" 
                                       maxlength="30" styleId="usu_mail" styleClass="form-control"/>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <label for="lblnombre">Confirmaci&oacute;n Correo Electr&oacute;nico:</label>
                             <input type="text" id="usu_mail2" name="usu_mail2"  maxlength="30" class="form-control">                           
                        </div>
                    </div>
                </div>
                    <%
                }
                else
                {
                %>
                     <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-4">
                        <div class="form-group">
                            <label for="lblnombre">Tipo de Persona:</label>
                             
                            <html:hidden property="usu_tipo_usu" styleId="usu_tipo_usu" value="J"/><br>
                            PERSONA JUR&Iacute;DICA
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-4">
                        <div class="form-group">
                            <label for="lblnombre">Tipo de Documento:</label>
                             
                            <html:hidden property="usu_tipo_doc_en" styleId="usu_tipo_doc_en" value="<%=f.getUsu_tipo_doc_en()%>"/><br>
                             
                            N&uacute;mero de Identificaci&oacute;n Tributaria
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-4">
                        <div class="form-group">
                            <label for="lblnombre">N&uacute;mero de documento:</label><br>                             
                            <html:hidden property="usu_nro_ident_en" styleId="usu_nro_ident_en" value="<%=f.getUsu_nro_ident_en()%>"/>
                             
                            <%=f.getUsu_nro_ident()%>
                             
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-4">
                        <div class="form-group">
                            <label for="lblnombre">Raz&oacute;n Social:</label>                             
                            <html:text property="usu_razon_social" size="30" maxlength="50"
                                       onblur="this.value=this.value.toUpperCase()" styleId="usu_razon_social"
                                       styleClass="form-control"/>
                        </div>
                    </div>                    
                   <div class="col-xs-12 col-sm-6 col-md-4">
                        <div class="form-group">
                            <label for="lblnombre">Correo Electr&oacute;nico:</label>
                             
                            <html:text property="usu_mail" size="30" 
                                       maxlength="30" styleId="usu_mail" styleClass="form-control"/>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-4">
                        <div class="form-group">
                            <label for="lblnombre">Confirmaci&oacute;n Correo Electr&oacute;nico:</label>
                             <input type="text" id="usu_mail2" name="usu_mail2"  maxlength="30" class="form-control">                           
                        </div>
                    </div>
                </div>               
                <div class="row">
                    
                </div>
               
                <%
                }%>
               
                <br>
            </div>
            <div class="panel panel-default" style="padding: 0px 10px 0px 10px;">
                <div class="panel-heading">
                    <strong class="text-uppercase">RECIBO DE PAGO POR LA CERTIFICACI&Oacute;N (CONCEPTO: 158 "LEGALIZACIONES CERTIFICACIONES")</strong>
                </div>
                <br>          
                
                <div class="row">
                    <div class="col-xs-6 col-sm-3 col-md-2">
                        <div class="form-group">
                            <label for="lblgestion">Gesti&oacute;n</label><br>
                             <html:hidden property="gestion" styleId="gestion" value="<%=f.getGestion()%>"/>
                            <label>
                            <%=f.getGestion()%>
                             </label>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2">
                        <div class="form-group">
                            <label for="lbladuana">Aduana</label><br>
                              <html:hidden property="aduana" styleId="aduana" value="<%=f.getAduana()%>"/>
                            <label>
                            <%=f.getAduana()%>
                             </label>
                            
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2">
                        <div class="form-group">
                            <label for="lblserial">Serial</label><br>
                             <label>
                            /  R  -
                             </label>
                            
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2">
                        <div class="form-group">
                            <label for="lblnumero">N&uacute;mero</label><br>
                             <html:hidden property="numero" styleId="numero" value="<%=f.getNumero()%>"/>
                            <label>
                            <%=f.getNumero()%>
                             </label>
                            
                        </div>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-3">
                        
                        </div>
                    <div class="col-xs-12 col-sm-12 col-md-5">
                        <div class="form-group">
                            <% nl.captcha.Captcha captcha = new nl.captcha.Captcha.Builder(200, 50)
                                .addText()
                                .build();
                                %>
                             
                            <img src="simpleCaptcha.png" class="pull-left" id="simpleCaptcha"/>
                        </div>
                        <a href="#" id="cambiarCaptcha" tabindex="-1"
                           style="text-decoration: none;color: rgb(22, 85, 154);font-style: italic">
                            <i class="fa fa-magic"></i>Cambiar c&oacute;digo de Seguridad</a>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-2">
                    
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-2">
                    
                    </div>
                 
                </div>
                
                <div class="row">
                    <div class="col-xs-12 col-sm-4 col-md-3">
                    <label for="lblcaptcha">C&oacute;digo de Seguridad:</label>
                    </div>
                
                    <div class="col-xs-12 col-sm-4 col-md-3">
                        <div class="form-group">
                            
                             
                            <input type="text" id="txtCaptcha" name="captcha" class="form-control text-left"
                                   maxlength="15"/>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-4 col-md-3">
                    
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-3">
                    
                    </div>
                </div>
                
                 <br>
                <div class="col-sm-12" style="padding: 0px 0px 15px 0px;" align="center">
                 <input type="hidden" id="opcion" name="opcion" value="generar">
                 <input type="button" name="guardar" id="guardar" class="btn btn-primary" value="Registrar Solicitud"/>
                  <input type="button" name="salir" id="salir" class="btn btn-primary" value=" &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    Salir &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"/>             
                  
                </div>
                
            </div>
            
            
        </div>
    </html:form>    
    <div id="responsive" class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
                  <div class="modal-dialog">
                
                    <!-- Modal content-->
                    <div class="modal-content">
                      <div class="modal-header" style="background-color:#F5F5F5;">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title" ><font color="#000034"><strong>Confirmaci&oacute;n de Generaci&oacute;n de Certificaci&oacute;n</strong></font></h4>
                      </div>
                      <div  class="modal-body">
                                        
                               
                               <div style="
                                                            padding-top: 15px;
                                                            padding-left: 10px;
                                                            padding-right: 10px;
                                                            padding-bottom: 1px;">                           
                            <p style="text-align: justify;color:#000000;">
                            <input id="chkacepto" value="1" type="checkbox" tabindex="6" />  Una vez confirmada la solicitud, la informaci&oacute;n no podr&aacute; ser modificada, y la certificaci&oacute;n  se remitir&aacute; 
                            al correo electr&oacute;nico que ha proporcionado.
                            </p>
                             <p><center><input type="button" id="btnclose4" data-dismiss="modal" class="btn btn-danger" tabindex="7" value="Cancelar" />
                                    <input type="button" id="btnacepto" class="btn btn-success" disabled="true" tabindex="7" value="Confirmar Solicitud"/></center>
                                </p> 
                         </div>
                                        
                      </div>
                 <br>
                  
                 
                      </div>
                    </div>
                
                  </div>
</div>



    <script src="lib/bootstrap/js/bootstrap.min.js"></script>  
    <script src="lib/datepicker/js/bootstrap-datepicker.js?s=2"></script> 
    <script type="text/javascript" src="lib/fancybox/jquery.fancybox.pack.js"></script>

<script>
$(document).ready(function(){ 
       
        $("#dep").val("<%=f.getDep()%>").change();
       
});
$('.date').datepicker({
       format: 'dd/mm/yyyy'
     });
$(".lnkVistaPrevia").fancybox({
        type: "iframe",
        iframe : {
            preload: false
        }
    });
    
$(function(){
$("#cambiarCaptcha").click(function(e){           
           e.preventDefault();
           var d = new Date();
           $("#simpleCaptcha").attr("src", "simpleCaptcha.png?"+d.getTime());
        });
    $("#salir").click(function(e){           
               e.preventDefault();
        $("#opcion").val('volver');
         $('#dvLoading').show();
              $("#formSolicitud").submit();
    });
     $("#guardar").click(function(e){           
               e.preventDefault();
        msg="";
        if($("#usu_tipo_usu").val()=='N')
        {
            if($('#dep').val().trim() == '') 
                msg = msg + 'Extenci\u00F3n del Documento de identidad obligatorio\n';
            if($('#usu_nombre').val().trim() == '') {
                msg = msg + 'Nombre(s) obligatorio\n';
                }
            if($('#usu_ap_pat').val().trim() == '' && $('#usu_ap_mat').val().trim() == '') {
                msg = msg + 'Primer Apellido o Segundo Apellido es obligatorio\n';
            }
            if($('#fecNacimiento').val()=='')
            {
                msg = msg + 'Fecha de nacimiento obligatorio.\n';
            }
            else
            {
                if(validaEdad($('#fecNacimiento').val())!='OK') {
                    msg = msg + 'Fecha de nacimiento ' + validaEdad($('#fecNacimiento').val()) +'\n';
                }
            }
            
        }
        else
        {
            if($("#usu_tipo_usu").val()=='J')
            {
                if($("#usu_razon_social").val().trim()=='')
                    msg = msg + 'Raz\u00F3n Social obligatorio\n';
            }
        }
        
        if(!validateEmail($('#usu_mail').val())) {
            msg = msg + 'Correo Electr\u00F3nico incorrecto\n';
        }
        else
        {
            if ($('#usu_mail').val().trim()!=$('#usu_mail2').val().trim())
                msg = msg + 'Confirmaci\u00F3n de Correo Electr\u00F3nico distintos\n';
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
       
        
        if($("#txtCaptcha").val().trim()=='')
        {
            msg=msg+'El campo Captcha es obligatorio.\n';
        }
        if (msg=='')
        {           
            previsualiza();             
        }            
        else
            alert(msg);
    });

    
    $("#chkacepto").click( function() {
					if ($(this).is(":checked"))	$("#btnacepto").removeAttr("disabled");
					else $("#btnacepto").attr("disabled", true);
				});
     $("#btnacepto").click(function(e){           
               e.preventDefault();
               $('#responsive').modal('hide');
                $('#dvLoading').show();
              $("#formSolicitud").submit();
     });
}); 

function previsualiza()
{
$( "#chkacepto" ).prop( "checked", false );

                    $('#responsive').modal("show");
}
</script>

