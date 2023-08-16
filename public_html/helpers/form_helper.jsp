<%!
String modalHeader(String title)
{ 
    return "<div class=\"modal-header\">\n" + 
        "    <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n" + 
        "    <h4 class=\"modal-title\">" + title + "</h4>\n" + 
        "</div>";
}

String modalFooter(boolean edit) {
    return "<div class=\"modal-footer\">\n" + 
        "    <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">\n" + 
        "        <span class=\"glyphicon glyphicon-ban-circle\"></span> Cerrar\n" + 
        "    </button>\n" + 
        "    <button type=\"submit\" class=\"btn btn-primary save-loading\" id=\"\" " + (edit ? "disabled=\"true\"" : "" )+ ">\n" + 
        "        <span>\n" + 
        "            <span class=\"glyphicon glyphicon-ok\"></span> <span class=\"title-submit\">" + (edit ? "Modificar" : "Guardar") + "</span>\n" + 
        "        </span>\n" + 
        "        <span>\n" + 
        "            <i class=\"fa fa-cog fa-spin\"></i> <span>Guardando</span>\n" + 
        "        </span>\n" + 
        "    </button>\n" + 
        "</div>\n";
}
%>