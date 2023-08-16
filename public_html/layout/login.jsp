<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
    <tiles:insert attribute="header" />
    <body class="login">
        <header>
            
        </header>
        <div class="container-login">
            <div class="container-fluid">
                <tiles:insert attribute="content" />
            </div>
        </div>
        <div class="text-center">
            <tiles:insert attribute="footer" />
        </div>
        <%@ include file="/views/ie.jsp" %>
        <script src="lib/bootstrap/js/bootstrap.min.js?${sessionScope.VERSION_ASSETS}"></script>
    </body>
</html>