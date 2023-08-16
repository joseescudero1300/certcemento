<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tiles:insert attribute="header" />
    <body>
        <div id="loading-ajax"></div>
        <div id="container">
            <tiles:insert attribute="navmain" />
            <div class="container-fluid" id="container-main">
                <div id="anb-messages" class="anb-messages"></div>
                <tiles:insert attribute="content" />
            </div>
        </div>
        <tiles:insert attribute="footer" />                     
      <%/*   <script type="text/javascript" src="lib/datatables/js/buttons.html5.min.js?${sessionScope.VERSION_ASSETS}"></script>
       <script type="text/javascript" src="lib/datatables/js/buttons.print.min.js"></script>        */%>
      <script src="js/main1.js?${sessionScope.VERSION_ASSETS}"></script>
        <script src="js/valida.js?${sessionScope.VERSION_ASSETS}"></script>
        
        <%//@ include file="/notifica/index.jsp" %>
    </body>
</html>
