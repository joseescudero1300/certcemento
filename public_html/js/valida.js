var dhoy=fHoy();
var danio;
function fHoy() {
  var fecha=new Date();
  var mes=(fecha.getMonth() < 9) ? ("0" + (fecha.getMonth() + 1)) : (fecha.getMonth() + 1);
  var dia=(fecha.getDate() <= 9) ? ("0" + fecha.getDate()) : (fecha.getDate());
  danio=fecha.getFullYear();
  return (dia+"/"+mes+"/"+danio);
}
function Trim(texto) {
  if(texto.length) {
    while(''+texto.charAt(0)==" ") {
      texto=texto.substring(1,texto.length);
    }
  }
  return texto;
}
function fTrim(texto) {
  aux=Trim(texto);
  if(aux=="")
    return false;
  else 
    return true;
}
function fInt(texto) {
  var mascara=new RegExp("^[0-9]{0,150}$", "g");
  return mascara.test(texto)
}
function fFloat(texto) {
  var mascara=new RegExp("^[0-9]{1,8}[.]{0,1}[0-9]{0,2}$", "g");
  if (!mascara.test(texto))
    return false;
  return (!isNaN(texto));
}
function fString(texto) {
  var mascara=new RegExp("^[a-zA-Z]{0,150}$", "g");
  return mascara.test(texto)
}
function fFecha(texto) {
  var mascara=new RegExp("^[0-3]{1}[0-9]{1}/(01|02|03|04|05|06|07|08|09|10|11|12)/[1-2][0-9]{3}$", "g");
  if (texto.length) {
    if (mascara.test(texto)) {
      var dd=texto.substring(0, 2);
      var mm=texto.substring(3, 5);
      var aa=texto.substring(6, 10);
      if (aa<1950)
        return false;
      if (dd== 0)
        return false;
      if (mm== 4 || mm==6 || mm==9 || mm==11) {
        if (dd > 30)
          return false;
      }
      if (mm==2) {
        var dias=aa % 4 ? 28 : 29;
        if (dd > dias)
          return false;
      } else {
        if (dd > 31)
          return false;
      }
      return true;
    } else {
      return false;
    }
  } else {
    return true;
  }
}
function foFecha(texto) {
  return (fTrim(texto) && fFecha(texto));
}
function foFecha(texto) {
  return (fTrim(texto) && fFecha(texto));
}
function foInt(texto) {
  return (fTrim(texto) && fInt(texto));
}
function foFloat(texto) {
  return (fTrim(texto) && fFloat(texto));
}
function foString(texto) {
  return (fTrim(texto) && fString(texto));
}
function Msg(msg) {
  alert("Se encontr\u00f3 los siguientes errores:\n" + msg + "\ncorr\u00edjalos para continuar.");
}
function fcFecha(fecini, fecfin) {
  var fini=fecini.substring(6, 10) + fecini.substring(3, 5) + fecini.substring(0, 2);
  var ffin=fecfin.substring(6, 10) + fecfin.substring(3, 5) + fecfin.substring(0, 2);
  if(fini > ffin)
    return true;
  else
    return false;
}
function fcFechaHoy(fecini, fecfin) {
  var fini=fecini.substring(6, 10) + fecini.substring(3, 5) + fecini.substring(0, 2);
  var ffin=fecfin.substring(6, 10) + fecfin.substring(3, 5) + fecfin.substring(0, 2);
  if(fini >= ffin)
    return true;
  else
    return false;
}
function validateEmail(sEmail) {
        var filter = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
        if (filter.test(sEmail)) {
        return true;
        }
        else {
        return false;
        }
    }
    
function isNit(value)
{
    var check = 0, x, y, z;
            for (var i = 0, l = value.length; i < l; i++) {
                x = i % 8;
                y = parseInt(value[l - i - 1]);
                z = permutations(x, y);
                check = verhoeff(check, z);
            }

            return check == 0;
}

function permutations(x, y)
{
var v = ["0123456789", "1576283094", "5803796142", "8916043527", "9453126870", "4286573901", "2793806415", "7046913258"];
            if (v[x]) {
                return parseInt(v[x][y]);
            }
            return -1;
}

function verhoeff(x, y)
{
var v = ["0123456789", "1234067895", "2340178956", "3401289567", "4012395678", "5987604321", "6598710432", "7659821043", "8765932104", "9876543210"]; 
            if (v[x]) {
                return parseInt(v[x][y]);
            }
            return -1;
}

function validaEdad(fecha){

    hoy=new Date();
    hoy_anio=hoy.getFullYear();
    hoy_mes=hoy.getMonth();
    hoy_dia=hoy.getUTCDate();
    var array_fecha = fecha.split("/");
    if (array_fecha.length!=3)
       return 'formato incorrecto (dd/mm/yyyy)';

    var ano;
    ano = parseInt(array_fecha[2]);
    if (isNaN(ano))
       return 'formato incorrecto (dd/mm/yyyy)';

    var mes;
    mes = parseInt(array_fecha[1]);
    if (isNaN(mes))
       return 'formato incorrecto (dd/mm/yyyy)';

    var dia;
    dia = parseInt(array_fecha[0]);
    if (isNaN(dia))
       return 'formato incorrecto (dd/mm/yyyy)';


    if (ano<=99)
       ano +=1900;

    edad=hoy_anio- ano - 1; 
       
    if (hoy_mes+1 - mes > 0)
    {
       edad = edad + 1; 
    }       
    else
    {
        if ((hoy_mes+1 - mes)==0 && hoy_dia - dia >= 0)
        {
            edad = edad + 1; 
        }
    }

    if (edad < 18 )
        return 'debe ser mayor de edad';
    else
    {
        if (edad > 100)
            return 'su edad no debe exceder los 100 a&ntilde;os';
        else
            return 'OK';
    }
}
function checkMask(o, fm) {
  var re = new RegExp(fm);
  return re.test(o);
}