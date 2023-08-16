<%@ page contentType="text/html;charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Certificaciones ${ !empty parentURI ? ' - ' : '' } ${parentURI} ${ !empty URI ? ' - ' : '' } ${URI}</title>
        
        <meta charset="utf-8" content="">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">        
        <meta name="description" content="TCF" />
        <meta name="Author" content="Miguel Huaygua" lang="es"/>        
        <meta name="copyright" content="© 2019, Aduana Naciona" lang="es">  
        <link type="text/css" rel="stylesheet" href="lib/bootstrap/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css?${sessionScope.VERSION_ASSETS}">
        <link type="text/css" rel="stylesheet" href="lib/bootstrap/css/jquery.dataTables.min.css"/>
        <link rel="stylesheet" type="text/css" href="lib/font-awesome/css/font-awesome.min.css?${sessionScope.VERSION_ASSETS}">       
        <link rel="stylesheet" type="text/css" href="lib/datatables/css/buttons.dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="lib/datepicker/css/datepicker.css?${sessionScope.VERSION_ASSETS}">
       
        <link rel="stylesheet" type="text/css" href="css/main.css?m=9">
        <link rel="stylesheet" type="text/css" href="css/imprimir.css?${sessionScope.VERSION_ASSETS}">
        
        <script src="lib/jquery/jquery-1.12.0.min.js?${sessionScope.VERSION_ASSETS}"></script>                              
        <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/build.css?${sessionScope.VERSION_ASSETS}">                    
        <link rel="stylesheet" type="text/css" href="lib/bootstrap-select/css/bootstrap-select.css?${sessionScope.VERSION_ASSETS}">                
        
        <script src="lib/bootstrap/js/bootstrap.min.js"></script>
         <script src="lib/bootstrap-select/js/bootstrap-select.js"></script>        
        <style type="text/css">
        #dvLoading
        {
           background:rgba(21, 21, 21, 0.6);
           height: 110%;
           width: 110%;
           position: fixed;
           z-index: 1000;
           margin-top: -25px;
           margin-left: -50px;
        }
        </style>
    </head>
<div id="dvLoading" style="display:none;" >
            <div style="position: absolute; top: 50%; left: 50%; width:50px; height:50px;"><i class="fa fa-spinner fa-spin fa-5x" style="color:#fff;"></i></div>
        </div>