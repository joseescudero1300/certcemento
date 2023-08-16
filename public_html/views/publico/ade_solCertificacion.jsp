<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<html:form action="consultaAdeudo.do" styleId="formSolicitud" styleClass="validable" style="max-width:100%;margin:auto">
    <input type="hidden" id="cerusu_tipo_usu" name="usu_tipo_usu" value=""></input>
    <input type="hidden" id="ceropcion" name="opcion" value="buscar"></input>
    <input type="hidden" id="cerusu_tipo_doc" name="usu_tipo_doc" value=""></input>
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">I- Informaci&oacute;n Personal</h3>
        </div>
        <div class="panel-body">
                
                 <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-6">
                    <div class="form-group">
                        <label for="lblapellido">Tipo de Persona:</label>
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-8">
                                <input type="radio" id="rb_cer_tipo_usu_jur" name="cer_usu_tipo" value="J"
                                       onclick="cambia2('J')"/>JUR&Iacute;DICA O EMPRESA UNIPERSONAL
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <input type="radio" id="rb_cer_tipo_usu_nat" name="cer_usu_tipo" value="N"
                                       onclick="cambia2('N')"/>
                                 NATURAL
                            </div>
                            
                        </div>
                    </div>
                </div>
                
               
            </div>
            
            <div class="row">                
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="form-group">
                        <label for="lblnombre" id="cerlblnro">N&uacute;mero de Identificaci&oacute;n:</label>
                         
                        <html:text property="usu_nro_ident" maxlength="15" onblur="this.value=this.value.toUpperCase()"
                                   styleId="cer_nro"
                                   styleClass="form-control text-left text-uppercase no-zero required"/>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="form-group">
                        
                    </div>
                </div>
            </div>
        
            
        </div>
        <div class="panel-heading">
            <h3 class="panel-title">II- Ingrese el recibo de Pago por Certificaci&oacute;n</h3>
        </div>
        <div class="panel-body">
        
        
        
        <div class="row">
                <div class="col-xs-6 col-sm-3 col-md-3">
                    <div class="form-group">
                        <label for="lblgestion">Gesti&oacute;n</label></br>
                         
                        <html:text property="gestion" size="20" onblur="this.value=this.value.toUpperCase()"
                                   maxlength="4" styleId="gestion" styleClass="form-control"/>
                    </div>
                </div>
                <div class="col-xs-6 col-sm-3 col-md-3">
                    <div class="form-group">
                        <label for="lbladuana">Aduana</label></br>
                         
                        <html:text property="aduana" size="20" onblur="this.value=this.value.toUpperCase()"
                                   maxlength="3" styleId="aduana" styleClass="form-control"/>
                    </div>
                </div>
                <div class="col-xs-6 col-sm-3 col-md-3">
                    <div class="form-group">
                        <label for="lblserial">Serial</label></br>
                         
                        <input type="text" maxlength="10" value="/  R  -" readonly="true" class="form-control"
                               style="background-color: white;border-color:white"></input>
                    </div>
                </div>
                <div class="col-xs-6 col-sm-3 col-md-3">
                    <div class="form-group">
                        <label for="lblnumero">N&uacute;mero</label></br>
                         
                        <html:text property="numero" size="20" onblur="this.value=this.value.toUpperCase()"
                                   maxlength="10" styleId="numero" styleClass="form-control"/>
                    </div>
                </div>              
            </div>
            
            
                <div class="row">
                        <div class="col-xs-1 col-sm-3 col-md-3">
                                <div class="form-group" >
                                                          
                                </div>
                            </div> 
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
                                        <img src="simpleCaptcha.png" class="pull-left"  id="simpleCaptcha" />     <br>                           
                                    <a href="#1" id="cambiarCaptcha" tabindex="-1" style="text-decoration: none;color: rgb(22, 85, 154);font-style: italic"><i class="fa fa-magic"></i>Cambiar C&oacute;digo de Seguridad</a>                
                                    </div>
                           </div>
                              <div class="col-xs-1 col-sm-3 col-md-3">
                                <div class="form-group" >
                                                          
                                </div>
                            </div> 
                       </div>
                    
                    <div class="row">
                        <div class="col-xs-1 col-sm-3 col-md-3">
                            <div class="form-group"></div>
                        </div>
                        <div class="col-xs-10 col-sm-6 col-md-6" style="border-right: 1px solid #ccc;
          border-bottom: 1px solid #ccc;
          border-left: 1px solid #ccc;
          border-bottom-right-radius: 6px;
          border-bottom-left-radius: 6px;">
                            <div class="form-group">
                                <label for="lblcaptcha">C&oacute;digo de Seguridad:</label>
                                 
                                <input type="text" id="txtCaptcha" name="captcha" class="form-control text-left required"
                                       maxlength="15"/>
                            </div>
                        </div>
                        <div class="col-xs-1 col-sm-3 col-md-3">
                            <div class="form-group"></div>
                        </div>
                    </div>
                    
            <br>
            <div class="row">
                <div class="col-sm-12" style="padding: 0px 0px 0px 0px;" align="center">
                    <input type="button" name="buscar" id="buscar" class="btn btn-primary"
                           value="Buscar Pago"/>
                </div>
            </div>
        </div>
    </div>
</html:form>
<script>
  $("#cambiarCaptcha").click(function (e) {
          e.preventDefault();
          var d = new Date();
          $("#simpleCaptcha").attr("src", "simpleCaptcha.png?" + d.getTime());
      });
   function cambia2(valor) {
      if(valor=='N') {        
         $('#cerlblnro').text('N\u00FAmero de Carnet de Identidad:');        
          $('#cerusu_tipo_usu').val('N');
        $('#cerusu_tipo_doc').val('CI');
      } else {
       if(valor=='J') {        
       $('#cerusu_tipo_usu').val('J');
        $('#cerusu_tipo_doc').val('NIT');
        $('#cerlblnro').text('N\u00FAmero de Identificaci\u00F3n Tributaria:');              
       }
      }
    } 
    
    $("#buscar").click(function(e){           
           e.preventDefault();
           msg="";
           
           if ($('#cerusu_tipo_usu').val()=='N')
           {                
                if($("#cer_nro").val().trim()=='')
                   {
                        msg=msg+'El campo N\u00FAmero de Carnet de Identidad es obligatorio.\n';
                   }
                   else
                   {
                        if(!checkMask($('#cer_nro').val().trim(),'^([0-9]{5,14}|[0-9]{5,14}(-[A-Z0-9]{1,3}))$'))
                            msg = msg + 'Formato de N\u00FAmero de Documento de identidad incorrecto.\n';
                   }                
               /* if($("#usu_extencion").val().trim()=='')
                {
                    msg=msg+'El campo Extensi\u00F3n del documento es obligatorio.\n';
                }*/
           }   
           else
           {
                if ($('#cerusu_tipo_usu').val()=='J')
                if($("#cer_nro").val().trim()=='')
                   {
                        msg=msg+'El campo N\u00FAmero de Identificaci\u00F3n Tributaria es obligatorio.\n';
                   }
                   else
                   {
                        if (!isNit($('#cer_nro').val().trim()))
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
                msg = msg + 'N\u00FAmero de recibo de pago con formato incorrecto (S\u00F3lo n\u00FAmeros).\n';
        }
           
           
           if(msg=='')
           {           
                $('#dvLoading').show();
                $("#formSolicitud").submit();
           }             
            else
                alert(msg);
        });
    
    
      
</script>