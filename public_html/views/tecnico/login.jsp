<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="java.sql.CallableStatement"%>
<%@ page import="oracle.jdbc.pool.OracleConnectionPoolDataSource"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="javax.sql.DataSource"%>
<%@ page import="oracle.jdbc.OracleTypes"%>
<%@ page import="javax.naming.InitialContext"%>

<div class="form-login">
    
    <div class="panel panel-default">
    <div class="logo"><h1>Adeudo</h1></div>
        <br/>
        <div class="panel-heading" style="background-image: url(img/logo_login.png);background-size: 90%;background-repeat: no-repeat;padding-left: 100px;">
            <div style="margin-bottom: 0;">
                    <cite style="font-size: 15px;">Certificaci&oacute;n de no Adeudos Tributarios</cite><br><br>
                    <span><strong>Perfil Técnico</strong></span>
                </div>
        </div>
        <br/>
        <html:form action="/login_tecnico.do" styleClass="panel-body" focus="usuario">
            <html:errors property="message"/>
            <c:if test="${!empty message}">
                <div class="alert alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <c:out value="${message}" escapeXml="false" />
                </div>
            </c:if>            
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                <input type="text" name="usuario" class="form-control" value="${AuthForm.usuario}" placeholder="Usuario" />
            </div>
            <html:errors property="usuario"/>
            <div class="input-group bottom">
                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <input type="password" name="clave" class="form-control" value="${AuthForm.clave}" placeholder="Contraseña" />
            </div>
            <html:errors property="clave"/>                                       
            <button class="btn btn-primary btn-lg btn-block" type="submit" id="btnIngresar"><i class="fa fa-lock"></i> Ingresar</button>            
        </html:form>        
        <div class="login_footer">
             &nbsp;
        </div>
    </div>    
</div>
