<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" pattern="yyyy" value="${now}" />

        <footer class="footer text-center">
            <span class="center" style="mix-blend-mode: exclusion">&copy; ${year} <a href="http://www.aduana.gob.bo" target="_blank">Aduana Nacional</a> - <span class="hidden-xs">Todos los derechos reservados -</span> v1.3</span>
            <a id="btn-scroll-top" class="btn-scroll-top" href="#">
                <i class="fa fa-chevron-up"></i>
            </a>
        </footer>
        
        <div id="main-modal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content"></div>
            </div>
        </div>
        <div id="second-modal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content"></div>
            </div>
        </div>
        <div id="alert-modal" class="modal fade"></div>        
        <script type="text/js-tmpl" id="anb-message-tmpl">
            <div class="anb-message anb-message-{type}">
                <span class="close" title="Cerrar">x</span>
                <div class="anb-message-header"><i class="fa {icon}"></i> {title}</div>
                <div class="anb-message-body">{message}</div>
            </div>
        </script>
        <script type="text/tmpl-js" id="tmpl-alert">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">{title}</h4>
                    </div>
                    <div class="modal-body">
                        <div>{message}</div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default {button_cancel}" data-dismiss="modal" ><i class="fa fa-ban"></i> <span>Cancelar</span></button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal"><i class="fa fa-check"></i> <span>Aceptar</span></button>
                    </div>
                </div>
            </div>
        </script>
        <script type="text/tmpl-js" id="tmpl-date">
            <div class="input-group date">
                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
            </div>
        </script>
        <script type="text/tmpl-js" id="tmpl-print">
            <!DOCTYPE html>
            <html lang="es">
                <head>
                    <title>Print</title>
                    <meta charset="UTF-8" />
                </head>
                <body>
                    {contenido}
                </body>
            </html>
        </script>