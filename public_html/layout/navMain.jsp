<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<c:set var="list" value="${iconsMenu}"/>
<c:set var="icons" value="${fn:split(list, ' ')}" />
<c:set var="i" value="0" />

<header>
    <div class="logo" id="logo"></div>
    <div class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header pull-left">
                <button class="navbar-toggle pull-left visible-xs toggle-menu" type="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <ul class="header-menu nav navbar-nav pull-left pull-left toggle-menu">
                    <li>
                        <a class="btn-banner  hide-menu hidden-xs active" href="#" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="Menú">
                            <i class="fa fa-bars"></i>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="pull-right">
                <ul class="header-menu nav navbar-nav pull-left pull-left">
                    <li>
                        <a id="btn-banner" class="btn-banner hidden-xs" href="#" data-toggle="tooltip" data-placement="bottom" title="Ocultar banner">
                            <i class="fa fa-photo"></i>
                        </a>
                    </li>
                    <li>
                        <a id="btn-fullscreen" class="btn-fullscreen hidden-xs" href="#" data-toggle="tooltip" data-placement="bottom" title="Pantalla completa">
                            <i class="fa fa-expand"></i>
                            <i class="fa fa-compress"></i>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<nav id="sidebar">
    <div class="navigation">
        <div class="user-data">
            <a href="myaccount.do" title="Mi Cuenta">
            <div class="user-icon pull-left hvr-fade">
                <i class="fa fa-user"></i>
            </div>
            </a>
            <h4 class="user-details pull-left">
                ${sessionScope.usuario.nombreUsuario}<br>
                <sub id="administracion">
                    <i class="fa fa-home"></i> ${sessionScope.desc_aduana}
                </sub>
            </h4>
        </div>
        <div class="menu-title">
            &nbsp;<strong>Menú Principal</strong>
        </div>
        <ul class="list-unstyled">
            <c:set var="open" value="false" />
            <c:forEach items="${sessionScope.opciones}" var="opcion">
                <c:choose>
                    <c:when test="${opcion.codopc != '' && opcion.codant == '0'}">
                        <c:if test="${open}">
                            </ul>
                        </li>
                        </c:if>
                        <c:set var="open" value="true" />
                        <li class="has-submenu">
                            <a href="#">
                                <i class="fa fa-${icons[i]}"></i> 
                                <span class="nav-label">${opcion.desc}</span>
                                <i class="fa fa-chevron-right pull-right"></i>
                            </a>
                            <ul class="list-unstyled">
                            <c:set var="i" value="${i+1}" />
                    </c:when>
                    <c:otherwise>
                        <c:if test="${ opcion.codant != 8 }">
                            <li class="${ACCION == opcion.accion ? 'active' : ''}">
                                <a href="${opcion.accion}.do">${opcion.desc}</a>
                            </li>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${open}">
                    </ul>
                </li>
            </c:if>
            <li>
                <a href="logout.do">
                    <i class="fa fa-power-off"></i>
                    <span class="nav-label">Cerrar sesión</span>
                </a>
            </li>
        </ul>
    </div>
</nav>