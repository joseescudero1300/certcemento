!function(e){"use strict";var t="addImage_",r=function(e){var t,r;if(255===!e.charCodeAt(0)||216===!e.charCodeAt(1)||255===!e.charCodeAt(2)||224===!e.charCodeAt(3)||!e.charCodeAt(6)==="J".charCodeAt(0)||!e.charCodeAt(7)==="F".charCodeAt(0)||!e.charCodeAt(8)==="I".charCodeAt(0)||!e.charCodeAt(9)==="F".charCodeAt(0)||0===!e.charCodeAt(10))throw new Error("getJpegSize requires a binary jpeg file");for(var a=256*e.charCodeAt(4)+e.charCodeAt(5),i=4,n=e.length;n>i;){if(i+=a,255!==e.charCodeAt(i))throw new Error("getJpegSize could not find the size of the image");if(192===e.charCodeAt(i+1))return r=256*e.charCodeAt(i+5)+e.charCodeAt(i+6),t=256*e.charCodeAt(i+7)+e.charCodeAt(i+8),[t,r];i+=2,a=256*e.charCodeAt(i)+e.charCodeAt(i+1)}},a=function(e){var t=this.internal.newObject(),r=this.internal.write,a=this.internal.putStream;if(e.n=t,r("<</Type /XObject"),r("/Subtype /Image"),r("/Width "+e.w),r("/Height "+e.h),"Indexed"===e.cs?r("/ColorSpace [/Indexed /DeviceRGB "+(e.pal.length/3-1)+" "+(t+1)+" 0 R]"):(r("/ColorSpace /"+e.cs),"DeviceCMYK"===e.cs&&r("/Decode [1 0 1 0 1 0 1 0]")),r("/BitsPerComponent "+e.bpc),"f"in e&&r("/Filter /"+e.f),"dp"in e&&r("/DecodeParms <<"+e.dp+">>"),"trns"in e&&e.trns.constructor==Array)for(var i="",n=0;n<e.trns.length;n++)i+=e[i][n]+" "+e.trns[n]+" ",r("/Mask ["+i+"]");"smask"in e&&r("/SMask "+(t+1)+" 0 R"),r("/Length "+e.data.length+">>"),a(e.data),r("endobj")},i=function(){var e=this.internal.collections[t+"images"];for(var r in e)a.call(this,e[r])},n=function(){var e,r=this.internal.collections[t+"images"],a=this.internal.write;for(var i in r)e=r[i],a("/I"+e.i,e.n,"0","R")};e.addImage=function(e,a,o,c,s,h){if("object"==typeof e&&1===e.nodeType){var d=document.createElement("canvas");d.width=e.clientWidth,d.height=e.clientHeight;var l=d.getContext("2d");if(!l)throw"addImage requires canvas to be supported by browser.";l.drawImage(e,0,0,d.width,d.height),e=d.toDataURL("image/jpeg"),a="JPEG"}if("JPEG"!==a.toUpperCase())throw new Error("addImage currently only supports format 'JPEG', not '"+a+"'");var g,C=this.internal.collections[t+"images"],p=this.internal.getCoordinateString,f=this.internal.getVerticalCoordinateString;"data:image/jpeg;base64,"===e.substring(0,23)&&(e=atob(e.replace("data:image/jpeg;base64,",""))),C?g=Object.keys?Object.keys(C).length:function(e){var t=0;for(var r in e)e.hasOwnProperty(r)&&t++;return t}(C):(g=0,this.internal.collections[t+"images"]=C={},this.internal.events.subscribe("putResources",i),this.internal.events.subscribe("putXobjectDict",n));var u=r(e),A={w:u[0],h:u[1],cs:"DeviceRGB",bpc:8,f:"DCTDecode",i:g,data:e};return C[g]=A,s||h||(s=-96,h=-96),0>s&&(s=-1*A.w*72/s/this.internal.scaleFactor),0>h&&(h=-1*A.h*72/h/this.internal.scaleFactor),0===s&&(s=h*A.w/A.h),0===h&&(h=s*A.h/A.w),this.internal.write("q",p(s),"0 0",p(h),p(o),f(c+h),"cm /I"+A.i,"Do Q"),this}}(jsPDF.API);