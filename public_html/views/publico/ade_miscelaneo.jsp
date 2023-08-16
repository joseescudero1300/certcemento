<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ page import="bo.gob.aduana.bean.UsuarioForm"%>
<%
    UsuarioForm f = ( UsuarioForm ) request.getAttribute ( "usuario" );
    if (f==null)
        f=new UsuarioForm();
    String r="";
%>
<html:form action="/miscelaneo.do" styleId="formMiscelaneo" styleClass="validable" style="max-width:100%;margin:auto">
    <input type="hidden" id="miscusu_tipo_usu" name="usu_tipo_usu" value=""></input>
    <input type="hidden" id="miscopcion" name="opcion" value="miscelaneo"></input>
    <input type="hidden" id="miscusu_tipo_doc" name="usu_tipo_doc" value=""></input>
    <input type="hidden" id="usu_nro_ident" name="usu_nro_ident" value=""></input>
   <br>
     <div style="font-size:15px">&nbsp;&nbsp;&nbsp;&nbsp;<strong>NOTA:</strong> Si ya cuenta con el recibo de pago, seleccione directamente el paso <strong>2- Emisi&oacute;n de
                                                                                         certificaci&oacute;n</strong></div><br>
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">Registro Informaci&oacute;n Personal</h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-6">
                    <div class="form-group">
                        <label for="lblapellido">Tipo de Persona:</label>
                        <div class="row">
                            
                            <div class="col-xs-12 col-sm-6 col-md-8">
                                <input type="radio" id="rb_tipo_usu_jur" name="usu_tipo" value="J"
                                       onclick="cambia('J')"/>JUR&Iacute;DICA O EMPRESA UNIPERSONAL
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <input type="radio" id="rb_tipo_usu_nat" name="usu_tipo" value="N"
                                       onclick="cambia('N')"/>
                                 NATURAL
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-3" id='nat_0'>
                    <div class="form-group">                        
                         <label for="lblfecha">Nro. Carnet de Identidad:</label>
                      
                            <input type="text" maxlength="15" onblur="this.value=this.value.toUpperCase()"
                                   id="misc_ci"
                                   class="form-control text-left text-uppercase no-zero required">      
                    </div>
                </div>
                <div id='nat_1' class="col-xs-12 col-sm-6 col-md-3">
                    <div class="form-group" >
                        <label for="lblfecha">Expedido en:</label>
                         
                        <html:select property="extencion" styleId="miscextencion" styleClass="form-control">
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
            <div class="row" id='nat_2'>
                <div class="col-xs-12 col-sm-6 col-md-3">
                    <div class="form-group">
                        <label for="lblnombre">Nombre(s):</label>
                         
                        <html:text property="usu_nombre" maxlength="30" onblur="this.value=this.value.toUpperCase()"
                                   styleId="miscusu_nombre" styleClass="form-control"/>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-3">
                    <div class="form-group">
                        <label for="lblapellido">Primer Apellido:</label>
                         
                        <html:text property="usu_ap_pat" onblur="this.value=this.value.toUpperCase()" maxlength="30"
                                   styleId="miscusu_ap_pat" styleClass="form-control"/>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-3">
                    <div class="form-group">
                        <label for="lblmaterno">Segundo Apellido:</label>
                         
                        <html:text property="usu_ap_mat" onblur="this.value=this.value.toUpperCase()" maxlength="30"
                                   styleId="miscusu_ap_mat" styleClass="form-control"/>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-3">
                    <div class="form-group">
                        <label for="lblfecha">Fecha de Nacimiento:</label>
                        <div class="input-group date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>                             
                            <html:text styleClass="form-control" styleId="miscfecNacimiento" property="fecNacimiento"   maxlength="10"/>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="row" id='jur_1'>
                
                
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="form-group">                        
                         <label for="lblfecha">NIT</label>
                         <input type="text" maxlength="15" onblur="this.value=this.value.toUpperCase()"
                                   id="misc_nit"
                                   class="form-control text-left text-uppercase no-zero required" 
                                   onchange="pTraeDato('NIT');">                        
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="form-group">
                            <label for="lblnombre">Raz&oacute;n Social:</label>                             
                            <html:text property="usu_razon_social" maxlength="99" 
                                       onblur="this.value=this.value.toUpperCase()" styleId="miscusu_razon_social"
                                       styleClass="form-control"/>
                        </div>
                </div>
            </div>
            
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-3">
                    <div class="form-group">
                        <label for="lblnumero">Departamento:</label>
                         
                        <html:select property="dep" styleId="miscdep" styleClass="form-control">
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
                <div class="col-xs-12 col-sm-6 col-md-9">
                    <div class="form-group">
                        <label for="lblnumero">Direcci&oacute;n:</label>
                         
                        <html:text property="direccion" onblur="this.value=this.value.toUpperCase()" maxlength="200"
                                   styleId="direccion" styleClass="form-control"/>
                    </div>
                </div>
        </div>
        <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="form-group">
                        <label for="lblnombre">Correo Electr&oacute;nico:</label>
                         
                        <html:text property="usu_mail" maxlength="50" styleId="miscusu_mail" styleClass="form-control"/>
                    </div>
                </div>               
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <label for="lblnombre">Confirmaci&oacute;n Correo Electr&oacute;nico:</label>
                             <input type="text" id="usu_mail2" name="usu_mail2"  maxlength="50" class="form-control">                           
                        </div>
                    </div>                
            </div>
                  
            <div class="row" style=" margin-left: 0px;">              
                   <div class="col-xs-10 col-sm-6 col-md-6" style="border-top: 1px solid #ccc;
                                                                                              border-right: 1px solid #ccc;  
                                                                                              border-left: 1px solid #ccc;
                                                                                              border-top-right-radius: 6px;
                                                                                              border-top-left-radius: 6px;">
  <br>
                           <div class="form-group" >
                                <% nl.captcha.Captcha captcha = new nl.captcha.Captcha.Builder(200, 50)
                                .addText()
                                .build();
                                %>
                                <img src="simpleCaptcha.png" class="pull-left"  id="miscsimpleCaptcha" />     <br>                           
                            <a href="#1" id="misccambiarCaptcha" tabindex="-1" style="text-decoration: none;color: rgb(22, 85, 154);font-style: italic"><i class="fa fa-magic"></i>Cambiar C&oacute;digo de Seguridad</a>                
                            </div>
                   </div>
                      <div class="col-xs-1 col-sm-6 col-md-6">
                        <div class="form-group" >                                                  
                        </div>                        
                    </div> 
               </div>
            
            <div class="row" style=" margin-left: 0px;">
                
                <div class="col-xs-10 col-sm-6 col-md-6" style="border-right: 1px solid #ccc;
                                      border-bottom: 1px solid #ccc;
                                      border-left: 1px solid #ccc;
                                      border-bottom-right-radius: 6px;
                                      border-bottom-left-radius: 6px;">
                    <div class="form-group">
                        <label for="lblcaptcha">C&oacute;digo de Seguridad:</label>
                         
                        <input type="text" id="misctxtCaptcha" name="captcha" class="form-control text-left required"
                               maxlength="15"/>
                    </div>
                </div>
                <div class="col-xs-1 col-sm-3 col-md-6">
                    <div class="form-group"></div>
                </div>
            </div> 
            </div>
            <div class="row">
                <div class="col-sm-12" style="padding: 0px 0px 15px 0px;" align="center">               
                    <input type="button" name="guardar" id="guardar" class="btn btn-primary" value="Generar Formulario de Pago"/>                           
                </div>
            </div>        
    </div>
</html:form>




<script src="lib/bootstrap/js/bootstrap.min.js"></script>  
    <script src="lib/datepicker/js/bootstrap-datepicker.js?s=2"></script> 
    <script type="text/javascript" src="lib/fancybox/jquery.fancybox.pack.js"></script>
<script>
        
$('.date form-control').datepicker({
       format: 'dd/mm/yyyy'
     });     
  $("#misccambiarCaptcha").click(function (e) {
          e.preventDefault();
          var d = new Date();
          $("#miscsimpleCaptcha").attr("src", "simpleCaptcha.png?" + d.getTime());
      });
function cambia(valor) {
      if(valor=='N') {  
        $('#miscusu_tipo_usu').val('N');
        $('#miscusu_tipo_doc').val('CI');      
        $('#nat_0').show();  
        $('#nat_1').show();       
        $('#nat_2').show(); 
        $('#jur_1').hide();      
        
        $("#misc_nit").val("");  
          $("#miscusu_razon_social").val("");
           $("#miscdep").val("").change();
             $("#direccion").val(""); 
             $("#miscusu_mail").val("");
        
      } else {
       if(valor=='J') {  
        $('#miscusu_tipo_usu').val('J');
        $('#miscusu_tipo_doc').val('NIT');       
        $('#nat_0').hide();       
        $('#nat_1').hide();    
        $('#nat_2').hide(); 
        $('#jur_1').show(); 
        
         $("#miscextencion").val("").change();   
             $("#misc_ci").val("");  
             $("#miscusu_nombre").val(""); 
             $("#miscusu_ap_pat").val(""); 
             $("#miscusu_ap_mat").val(""); 
             $("#miscfecNacimiento").val("");              
             $("#miscdep").val("").change();       
             $("#direccion").val("");   
             $("#miscusu_mail").val("");  
       }
      }
    }
  
     $("#guardar").click(function(e){           
               e.preventDefault();
        msg="";
        if($("#miscusu_tipo_usu").val()=='N')
        {
            if($("#misc_ci").val().trim()=='')
                   {
                        msg=msg+'El campo N\u00FAmero de Carnet de Identidad es obligatorio.\n';
                   }
                   else
                   {
                        if(!checkMask($('#misc_ci').val().trim(),'^([0-9]{5,14}|[0-9]{5,14}(-[A-Z0-9]{1,3}))$'))
                            msg = msg + 'Formato de N\u00FAmero de Documento de identidad incorrecto.\n';
                        else
                          $("#usu_nro_ident").val($("#misc_ci").val());                        
                   }     
            
            if($('#miscextencion').val().trim() == '') 
                msg = msg + 'Extenci\u00F3n del Documento de identidad obligatorio\n';
            if($('#miscusu_nombre').val().trim() == '') {
                msg = msg + 'Nombre(s) obligatorio\n';
                }
            if($('#miscusu_ap_pat').val().trim() == '' && $('#miscusu_ap_mat').val().trim() == '') {
                msg = msg + 'Primer Apellido o Segundo Apellido es obligatorio\n';
            }
            if($('#miscfecNacimiento').val()=='')
            {
                msg = msg + 'Fecha de nacimiento obligatorio.\n';
            }
            else
            {
                if(validaEdad($('#miscfecNacimiento').val())!='OK') {
                    msg = msg + 'Fecha de nacimiento ' + validaEdad($('#miscfecNacimiento').val()) +'\n';
                }
            }
            
        }
        else
        {
            if($("#miscusu_tipo_usu").val()=='J')
            {
                if($("#misc_nit").val().trim()=='')
                   {
                        msg=msg+'El campo NIT es obligatorio.\n';
                   }
                   else
                   {
                        if(!checkMask($('#misc_nit').val().trim(),'^([0-9]{5,14}|[0-9]{5,14}(-[A-Z0-9]{1,3}))$'))
                            msg = msg + 'Formato de NIT incorrecto.\n';
                        else
                          $("#usu_nro_ident").val($("#misc_nit").val());                        
                   }    
                   
                if($("#miscusu_razon_social").val().trim()=='')
                    msg = msg + 'Raz\u00F3n Social obligatorio\n';
            }
            //$("#usu_nro_ident").val($("#misc_nit").val());
        }
          if($('#direccion').val()=='')
            {
                msg = msg + 'Direcci\u00F3n es  obligatorio.\n';
            }
       if($('#miscdep').val()=='')
            {
                
                msg = msg + 'Departamento es  obligatorio.\n';
            } 
        
        
        if(!validateEmail($('#miscusu_mail').val())) {
            msg = msg + 'Correo Electr\u00F3nico incorrecto\n';
        }
        else
        {
            if ($('#miscusu_mail').val().trim()!=$('#usu_mail2').val().trim())
                msg = msg + 'Confirmaci\u00F3n de Correo Electr\u00F3nico distintos\n';
        }
        
        if($("#misctxtCaptcha").val().trim()=='')
        {
            msg=msg+'El campo Captcha es obligatorio.\n';
        }
           
           
           if(msg=='')
           {           
               /* $('#dvLoading').show();
                $("#formMiscelaneo").submit();*/
                $('#responsive').modal("show");
           }             
            else
                alert(msg);
        });
  $(function(){ 
      $("#chkacepto").click( function() {
         	if ($(this).is(":checked"))	
                {
                    $("#btnacepto").removeAttr("disabled");
                    $("#btnacepto").css("background-color", "#04A875");
                }                    
                else 
                {
                    $("#btnacepto").css("background-color", "#77c3ac");
                    $("#btnacepto").attr("disabled", true);
                }                
				});
     $("#btnacepto").click(function(e){           
               e.preventDefault();
               $('#responsive').modal('hide');
               $('#dvLoading').show();
                $("#formMiscelaneo").submit();
     });
    });       
function pTraeDato(tipo_doc )
{  
  var  nro_doc=$('#misc_nit').val();
    $.post("ajax/busca.jsp", "nro_doc="+nro_doc +"&tipo_doc="+tipo_doc, function(jsonfile) {                
             var v=jsonfile.trim();
              if (v!=='')
             {
               //var obj = JSON.parse('{ "name":"John", "age":30, "city":"New York"}');
               var obj = JSON.parse(v);
               
                    var razon=obj.razon_social;// xmlDoc.getElementsByTagName('razon_social')[0].childNodes[0].nodeValue;
                    var direccion=obj.direccion// xmlDoc.getElementsByTagName('direccion')[0].childNodes[0].nodeValue;
                    if (razon!='NO')
                    {
                       $('#miscusu_razon_social').val(razon);
                       $('#direccion').val(direccion);
                    }  
             }
            });     
}

</script>