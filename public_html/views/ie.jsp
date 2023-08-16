<!--[if IE]>
<script type="text/js-tmpl" id="ie-version-tmpl">   
    <div class="ie-version">
        <p>Usted tiene la versi&oacute;n <b>{version}</b> de Internet Explorer, el sistema no es compatible con esta versi&oacute;n, por favor instale otro navegador como:</p>
        <ul>
            <li>Chrome <a href="http://www.google.com/chrome/" target="blank">ir a su web de descarga</a> <i class="ie-version-sub">(Recomendado)</i></li>
            <li>Firefox <a href="https://www.mozilla.org/es-ES/firefox/new/" target="blank">ir a su web de descarga</a></li>
        </ul>
        <p class="ie-version-sub"><i><b>Nota.-</b> Si Ud. usa la versi&oacute;n 10 &oacute; 11 de Internet explorer, pueda que algunas funcionalidades no est&eacute;n disponibles con esas versiones, use otro navegador.</i></p>
    </div>
    <style type="text/css">
        body, html {background-color: #011826 !important; text-align: center;}
        .ie-version {background-color: #eee; color: #333; text-align: left; font: 12px normal Arial, Helvetica; margin: 100px auto; padding: 20px; width: 600px;}
        .ie-version-sub {font-size: 11px;}
    </style>
</script>
<script type="text/javascript">
    (function () {
        var ver = -1;
        var re = new RegExp("MSIE ([0-9]{1,}[.0-9]{0,})");
        if (re.exec(navigator.userAgent) != null) {
            ver = parseFloat( RegExp.$1 );
        }
        if (ver > -1 && ver < 10) {
            document.body.innerHTML = document.getElementById('ie-version-tmpl').innerHTML.replace('{version}', ver);
        }
    })();
</script>
<![endif]-->