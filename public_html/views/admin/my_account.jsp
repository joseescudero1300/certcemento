<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title"><i class="fa fa-user"></i> <strong>Mi cuenta</strong><sub> ${sessionScope.usuario.usuario}</sub></h3>
    </div>
    <div class="panel-body">
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#data" aria-controls="data" role="tab" data-toggle="tab">Mis datos personales</a></li>
            <li role="presentation"><a href="#password" aria-controls="password" role="tab" data-toggle="tab">Cambiar contraseña</a></li>
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane fade in active" id="data">
                <div class="panel-body form-horizontal user-data">                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Nombre completo:</label>
                        <div class="col-sm-10">
                            <div class="form-control-static">${sessionScope.usuario.nombreUsuario}</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Aduana:</label>
                        <div class="col-sm-10">
                            <div class="form-control-static">${sessionScope.aduana}</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Correo electrónico:</label>
                        <div class="col-sm-10">
                            <div class="form-control-static">${sessionScope.usuario.correo}</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Gerencia:</label>
                        <div class="col-sm-10">
                            <div class="form-control-static">${sessionScope.usuario.nomGerencia}</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Unidad:</label>
                        <div class="col-sm-10">
                            <div class="form-control-static">${sessionScope.usuario.nomUnidad}</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Perfil(es):</label>
                        <div class="col-sm-10">
                            <div class="form-control-static">${sessionScope.usuario.perfil}</div>
                        </div>
                    </div>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane fade" id="password">
                <div class="panel-body">
                    <html:form action="myaccountPassword.do" styleId="form-password" styleClass="form-horizontal">
                        <html:hidden property="usuario" value="${sessionScope.usuario.usuario}"/>
                        <div class="alert alert-info">
                            <strong>Nota.-</strong> La longitud de la nueva contraseña debe ser mayor a 8 caracteres, tener al menos una mayúscula y un caracter especial..
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 col-sm-offset-2 control-label">Contraseña anterior:</label>
                            <div class="col-sm-3">
                                <html:password property="clave" maxlength="15" styleClass="form-control required" redisplay="false"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 col-sm-offset-2 control-label">Nueva contraseña:</label>
                            <div class="col-sm-3">
                                <input type="password" name="confnuevo" maxlength="15" class="form-control required" data-matches="nuevo" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 col-sm-offset-2 control-label">Confirmar contraseña:</label>
                            <div class="col-sm-3">
                                <input type="password" name="nuevo" maxlength="15" class="form-control required" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-default" type="button" data-form="clean" tabindex="-1" ><i class="fa fa-eraser"></i> Limpiar</button>
                            </div>
                            <div class="col-sm-3">
                                <button type="submit" class="btn btn-primary"><i class="fa fa-lock"></i> Cambiar contraseña</button>
                            </div>
                        </div>
                    </html:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        Anb.form.submit('#form-password', function (form) {
            Anb.form.ajax('myaccountPassword.do', form, {
                success : function (data) {
                    Anb.form.clean(form);
                },
                error : function (data) {
                    Anb.form.clean(form);
                }
            });
        });
    });
</script>