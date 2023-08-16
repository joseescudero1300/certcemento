
<%@ page import="java.util.ArrayList"%>
<%@ page import="bo.gob.aduana.bd.Sql"%>
<%@ page import="bo.gob.aduana.bean.UsuarioForm"%>
<%
    Sql parametrica = new Sql();
    String json="";
    String nro_doc= request.getParameter("nro_doc").toString();
    String tipo_doc= request.getParameter("tipo_doc").toString();  
    UsuarioForm res = parametrica.getbusca(nro_doc,tipo_doc); 
    if (!res.getUsu_razon_social().equals("NO"))
    {
        json="{\"nro_doc\":\""+nro_doc+"\",\"tipo_doc\":\""+tipo_doc+"\",\"razon_social\":\""+res.getUsu_razon_social()+"\",\"direccion\":\""+res.getDireccion()+"\"}";
    }
   // String json="{\"nro_doc\":\""+nro_doc+"\",\"tipo_doc\":\""+tipo_doc+"\",\"razon_social\":\""+res.getUsu_razon_social()+"\",\"direccion\":\""+res.getDireccion()+"\"}";
 /*       String xml="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" + 
    "<resultado>\n" + 
    "        <dato></dato>\n" + 
    "		<nro_doc>"+nro_doc+"</nro_doc>\n" + 
    "		<tipo_doc>"+tipo_doc+"</tipo_doc>\n" + 
    "		<razon_social>"+res.getUsu_razon_social()+"</razon_social>\n" + 
    "		<direccion>"+res.getDireccion()+"</direccion>\n" + 
    "</resultado>";*/

   // response.getWriter().write("<status>ok</status>");
%>
<%=json%>
