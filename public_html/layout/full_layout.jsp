<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<tiles:insert attribute="header" />
    <body>        
        <header style="position:absolute;">
            <div class="banner" style="display:block"></div> 
        </header>
        <div id="loading-ajax"></div>
        <div id="container" class="container-full">
            <div >
                <div id="anb-messages" class="anb-messages"></div>
                <c:if test="${!empty sessionScope.BIEN}">
                    <div class="alert alert-success">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <i class="glyphicon glyphicon-ok"></i> ${sessionScope.BIEN}
                    </div>
                    <% request.getSession().removeAttribute("BIEN");%>
                </c:if>
                <c:if test="${!empty sessionScope.ERROR}">
                    <div class="alert alert-danger">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <i class="glyphicon glyphicon-remove"></i> ${sessionScope.ERROR}
                    </div>
                    <% request.getSession().removeAttribute("ERROR"); %>
                </c:if>
                <tiles:insert attribute="content" />
            </div>
        </div>
        <jsp:useBean id="now" class="java.util.Date" />
        <fmt:formatDate var="year" pattern="yyyy" value="${now}" />

        <div class="footer text-center">
            <span class="center">&copy; ${year} <a href="http://www.aduana.gob.bo" target="_blank">Aduana Nacional</a> - <span class="hidden-xs">Todos los derechos reservados -</span> v1.3 </span>
            <a id="btn-scroll-top" class="btn-scroll-top" href="#">
                <i class="fa fa-chevron-up"></i>
            </a>
        </div>

     <%/*
    <script type="text/javascript" src="lib/jquery/jquery.dataTables.min.js"></script>
    <script src="lib/datatables/js/dataTables.buttons.min.js"></script>
    <script src="lib/datatables/js/jszip.min.js"></script>
    <script src="lib/datatables/js/pdfmake.min.js"></script>
    <script src="lib/datatables/js/vfs_fonts.js"></script>
    <script src="lib/datatables/js/buttons.html5.min.js"></script>
    <script src="lib/datatables/js/buttons.print.min.js"></script>    
      
    <script type="text/javascript" src="lib/sweet/sweetalert.min.js"></script>*/%>
    <script src="js/valida.js?3"></script>
    
        <%//@ include file="/notifica/index.jsp" %>
    </body>
</html>
