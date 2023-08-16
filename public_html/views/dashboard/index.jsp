<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ include file="/helpers/form_helper.jsp" %>

<script type="text/javascript">
    $(function(){
        if($(window).width() < 768){
            $("#sidebar").addClass("collapsed");
            $("header").addClass("collapsed");
        } else{
            $("#sidebar").removeClass("collapsed");
            $("header").removeClass("collapsed");
        }
        
    });
</script>