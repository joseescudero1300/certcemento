<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.List"%>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="bo.gob.aduana.system.ClaseSession"%> 
<%@ page import="bo.gob.aduana.bean.UsuarioForm"%> 

<link type="text/css" rel="stylesheet" href="lib/fancybox/jquery.fancybox.css"/>
<div>
                <html:errors/>
            </div>
<div class="panel panel-primary" style="max-width:100%;margin:auto;top: 10px;">
    <div class="panel-heading">
        <h3 class="panel-title">
            <strong><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Consulta de Certificaciones</strong>
        </h3>
    </div>
    <html:form action="tec_busca_cer" styleId="formBusca" styleClass="validable" style="max-width:100%;margin:auto" enctype="multipart/form-data">
    
    <div class="panel-body" style="padding: 0px 0px 0px 0px;">
     
        <div class="panel panel-default">
          <div class="panel-heading">
                    <strong class="text-uppercase">Fecha de certificaci&oacute;n</strong>
                </div>
            <div class="panel-body" style="padding: 0px 15px 0px 15px;">
            
            
             <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-1">
                        <div class="form-group">
                            <label for="lblfecha" class="label-required">Inicio:</label>                                                    
                         </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">
                            
                             <div>                                                 
                                <html:text styleClass="date1 form-control" styleId="fechaIni" property="fechaIni"  maxlength="10"/>                                
                            </div>                           
                         </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-1">
                        <div class="form-group">
                            <label for="lblfecha" class="label-required">Fin:</label>
                                                                 
                               
                            
                         </div>
                </div>     
                 <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="form-group">  
                                <html:text styleClass="date2 form-control" styleId="fechaFin" property="fechaFin"  maxlength="10"/>                            
                         </div>
                </div>     
                    
            </div>
            
            </div>
            &nbsp;<code class="label-required">Campos Obligatorios</code>
        </div>            
        <div class="col-sm-12" style="padding: 0px 0px 15px 0px;" align="center">
            <button type="submit" name="buscar" id="buscar" class="btn btn-primary" value="guardar">Buscar</button>            
        </div>

        
     
        
    </div>  
    <input type="hidden" id="opcion" name="opcion" value=""></input>
    </html:form>


<%
        if(request.getAttribute("lista_cert")!=null)   
           {
           List<UsuarioForm> resp=(List)request.getAttribute("lista_cert");
           %>
           
           <div class="table-responsive"     style="/*padding: 0px 15px 10px 20px;*/">
            <table class="table table-striped table-hover" id="main-table">
                <thead>
                    <tr>
                        <th class="text-center">Nro.</th>
                        <th class="text-center">Certificaci&oacute;n</th>
                        <th class="text-center">Nombre o Raz&oacuten Social</th>
                        <th class="text-center">Tipo Documento</th>
                          <th class="text-center">Nro de Documento</th>
                        
                      
                        <th class="text-center">Fecha de Emisi&oacute;n</th>
                        <th class="text-center">Tipo de Certificaci&oacute;n</th>                                               
                    </tr>
                </thead>        
                <tbody>                           
                  <%
                    for(int i=0;i<resp.size();i++)
                    {
                    UsuarioForm fila = new UsuarioForm();
                    fila=resp.get(i);                                            
                  %>
                        <tr>
                            <td class="text-right"><%=(i+1)%></td>
                            <td class="text-center"><%=fila.getUrl()%></td>
                            <td class="text-center"><%=fila.getUsu_razon_social()%></td>
                            <td class="text-center"><%=fila.getUsu_tipo_doc()%></td>
                            <td class="text-center"><%=fila.getUsu_nro_ident()%></td>  
                            
                                                      
                            <td class="text-center"><%=fila.getFecha()%></td>
                            <td class="text-center"><%=fila.getTipo_certificacion().toUpperCase() %></td>                      
                        </tr>
                  <%}%>
                </tbody>
            </table>
        </div>
      <br>
      
        <br>
   <%}%>


  
<script type="text/javascript" src="lib/fancybox/jquery.fancybox.pack.js"></script> 
 <script src="lib/datepicker/js/bootstrap-datepicker.js?s=1"></script> 
 <script type="text/javascript" src="lib/bootstrap/js/bootbox.min.js"></script>
<script type="text/javascript">
$(".lnkVistaPrevia").fancybox({
        type: "iframe",
        iframe : {
            preload: false
        }
    });




    $(function(){
  $('.date1').datepicker({
     format: 'dd/mm/yyyy',
       language: 'es',
    //startDate: '-1m',
    endDate: '+0'
});
 $('.date2').datepicker({
     format: 'dd/mm/yyyy',
       language: 'es',
    startDate: '-1m',
    endDate: '+1d'
});
     $("#buscar").click(function(e){           
           e.preventDefault();
     var  msg="";
       
      if ( ($("#fecha_ini").val()=="" || $("#fecha_fin").val()=="") && $("#tramite").val()=="")
        {
            msg = msg + 'Debe de introducir fecha Inicial y final de emisión de Certificación\n';
        }
       
            if(msg=='')
           {           
                $("#opcion").val("buscar");
                $('#dvLoading').show();
                $("#formBusca").submit();
           }             
            else
                alert(msg);
     });
 });
</script>