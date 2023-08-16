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
    <div class="panel panel-info" style="
    margin-left: 20px;
    margin-right: 20px;">
    <div class="panel-body">
    <div class="row" >
                <html:errors/>
            </div> 
    </div>
        <div class="panel-heading">
            <h3 class="panel-title">I- Informaci&oacute;n Personal</h3>
        </div>
        <div class="panel-body">
        <br>
        <%
                if (f.getUsu_tipo_usu().equals("N"))
                {
                    %>
                    <html:hidden property="usu_tipo_usu" styleId="usu_tipo_usu" value="N"/>
                    <html:hidden property="usu_tipo_doc_en" styleId="usu_tipo_doc_en" value="<%=f.getUsu_tipo_doc_en()%>"/>
                     <html:hidden property="usu_nro_ident_en" styleId="usu_nro_ident_en" value="<%=f.getUsu_nro_ident_en()%>"/>
                     <html:hidden property="dep" styleId="dep" value="<%=f.getDep()%>"/>
                     <div class="row">
                    
                    
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            <label for="lblnombre">N&uacute;mero de documento:</label>
                        </div>
                    </div>     
                     <div class="col-xs-12 col-sm-6 col-md-9">
                        <div class="form-group">
                            
                            <%=f.getUsu_nro_ident()+" "+f.getDep()%>  
                      
                        </div>
                    </div> 
                </div>               
                <div class="row">
                <html:hidden property="usu_nombre" styleId="usu_nombre" value="<%=f.getUsu_nombre()%>"/>
                <html:hidden property="usu_ap_pat" styleId="usu_ap_pat" value="<%=f.getUsu_ap_pat()%>"/>
                <html:hidden property="usu_ap_mat" styleId="usu_ap_mat" value="<%=f.getUsu_ap_mat()%>"/>
                <html:hidden property="fecNacimiento" styleId="fecNacimiento" value="<%=f.getFecNacimiento()%>"/>
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            <label for="lblnombre">Nombre:</label>                         
                        </div>
                    </div>    
                     <div class="col-xs-12 col-sm-6 col-md-9">
                        <div class="form-group">
                            
                         
                            <%=f.getUsu_nombre() +" "+ f.getUsu_ap_pat()+" "+f.getUsu_ap_mat() %>  
                            
                        </div>
                    </div>    
                </div>
                <div class="row">
                <html:hidden property="usu_mail" styleId="usu_mail" value="<%=f.getUsu_mail()%>"/>
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            <label for="lblnombre">Correo Electr&oacute;nico:</label>
                            
                        </div>
                    </div>     
                    <div class="col-xs-12 col-sm-6 col-md-9">
                        <div class="form-group">
                            
                           
                            <%=f.getUsu_mail()%>  
                             
                        </div>
                    </div>     
                </div>
                    <%
                }
                else
                {
                %>
                     <div class="row">
                     <html:hidden property="usu_tipo_usu" styleId="usu_tipo_usu" value="J"/>
                     <html:hidden property="usu_tipo_doc_en" styleId="usu_tipo_doc_en" value="<%=f.getUsu_tipo_doc_en()%>"/>
                    <html:hidden property="usu_nro_ident_en" styleId="usu_nro_ident_en" value="<%=f.getUsu_nro_ident_en()%>"/>
                    <html:hidden property="usu_razon_social" styleId="usu_razon_social" value="<%=f.getUsu_razon_social()%>"/>
                    <html:hidden property="usu_mail" styleId="usu_mail" value="<%=f.getUsu_mail()%>"/>
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            <label for="lblnombre">Documento de Identidad:</label>                             
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-9">
                        <div class="form-group">
                           <%=(f.getUsu_nro_ident() )%>
                        </div>
                    </div>
                </div>               
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            <label for="lblnombre">Raz&oacute;n Social:</label>                                                     
                        </div>
                    </div> 
                    <div class="col-xs-12 col-sm-6 col-md-9">
                        <div class="form-group">                                 
                          <%=  f.getUsu_razon_social()%>
                        </div>
                    </div> 
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            <label for="lblnombre">Correo Electr&oacute;nico:</label>                           
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-9">
                        <div class="form-group">                           
                           <%=f.getUsu_mail()%>
                        </div>
                    </div>
                </div>
               
                <%
                }%>
        
        </div>
        <div class="panel-heading">
            <h3 class="panel-title">II- RECIBO DE PAGO POR LA CERTIFICACI&Oacute;N </h3>
        </div>
    <div class="panel-body">
        <br>
             <div class="row">
                    <div class="col-xs-6 col-sm-3 col-md-2">
                        <div class="form-group">
                            <label for="lblgestion">Gesti&oacute;n</label><br>
                             <html:hidden property="gestion" styleId="gestion" value="<%=f.getGestion()%>"/>
                          
                            <%=f.getGestion()%>
                             
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2">
                        <div class="form-group">
                            <label for="lbladuana">Aduana</label><br>
                              <html:hidden property="aduana" styleId="aduana" value="<%=f.getAduana()%>"/>
                          
                            <%=f.getAduana()%>
                        
                            
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2">
                        <div class="form-group">
                            <label for="lblserial">Serial</label><br>
                            
                            /  R  -
                          
                            
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-3 col-md-2">
                        <div class="form-group">
                            <label for="lblnumero">N&uacute;mero</label><br>
                             <html:hidden property="numero" styleId="numero" value="<%=f.getNumero()%>"/>
                           
                            <%=f.getNumero()%>
                   
                            
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
                            <input id="chkacepto" value="1" type="checkbox" tabindex="6" /> Una vez confirmada la solicitud, la informaci&oacute;n no podr&aacute; ser modificada, y la certificaci&oacute;n  se remitir&aacute; 
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
   
});

$(".lnkVistaPrevia").fancybox({
        type: "iframe",
       'titlePosition': 'inside',
        'titleShow':true,
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
        

        if($("#txtCaptcha").val().trim()=='')
        {
            msg=msg+'El campo Captcha es obligatorio.\n';
        }
        if (msg=='')
        {
            /*var r = confirm("Verifique que tenga acceso al correo electr\u00F3nico proporcionado, ya que se enviara el certificado a esa direcci\u00F3n. Esta seguro de registrar los datos?");
            if (r == true) {
                $('#dvLoading').show();
              $("#formSolicitud").submit();
            } */
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

