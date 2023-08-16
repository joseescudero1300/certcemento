<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<div class="form-login">
    
    <div class="panel panel-default">
    <div class="logo"><h1>TCF</h1></div>
        <br/>
        <div class="panel-heading" style="background-image: url(img/tcf1.png);background-size: 90%;background-repeat: no-repeat;">
            <br/><br/><br/>
        </div>
        <br/>
        <html:form action="/login.do" styleClass="panel-body" focus="usuario">
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
