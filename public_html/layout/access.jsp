<%@ page contentType="text/html;charset=utf-8"%>

<div class="access" id="access">
    <ul class="access-menu">
        <li data-toggle="tooltip" data-placement="left" title="Opción 1"><a href="#" class="access-menu-item btn-default" data-option="dashboardOption.do?id=1"><i class="fa fa-inbox"></i></a></li>
        <li data-toggle="tooltip" data-placement="left" title="Opción 2"><a href="#" class="access-menu-item btn-info" data-option="dashboardOption.do?id=2"><i class="fa fa-heart"></i></a></li>
        <li data-toggle="tooltip" data-placement="left" title="Opción 3"><a href="#" class="access-menu-item btn-success" data-option="dashboardOption.do?id=3"><i class="fa fa-external-link-square"></i></a></li>
        <li data-toggle="tooltip" data-placement="left" title="Opción 5"><a href="#" class="access-menu-item btn-primary" data-option="dashboardOption.do?id=4"><i class="fa fa-send"></i></a></li>
        <li data-toggle="tooltip" data-placement="left" title="Opción 4"><a href="#" class="access-menu-item btn-warning" data-option="dashboardOption.do?id=5"><i class="fa fa-envelope-o"></i></a></li>
    </ul>
    <button type="button" class="access-plus"  data-option="dashboardOption.do?id=0" data-toggle="tooltip" data-placement="left" title="Nuevo"><i class="fa fa-plus"></i></button>    
</div>
<script>
    $(document).ready(function () {
        $('.access [data-option]').on('click', function (e) {
           Anb.modal.show(this.getAttribute("data-option"));
        });
    });
</script>