!function(t){"use strict";var n=t.getCharWidthsArray=function(t,n){n||(n={});var e,i,r,a=n.widths?n.widths:this.internal.getFont().metadata.Unicode.widths,s=a.fof?a.fof:1,h=n.kerning?n.kerning:this.internal.getFont().metadata.Unicode.kerning,o=h.fof?h.fof:1,f=0,l=a[0]||s,c=[];for(e=0,i=t.length;i>e;e++)r=t.charCodeAt(e),c.push((a[r]||l)/s+(h[r]&&h[r][f]||0)/o),f=r;return c},e=function(t){for(var n=t.length,e=0;n;)n--,e+=t[n];return e},i=(t.getStringUnitWidth=function(t,i){return e(n.call(this,t,i))},function(t,n,e,i){for(var r=[],a=0,s=t.length,h=0;a!==s&&h+n[a]<e;)h+=n[a],a++;r.push(t.slice(0,a));var o=a;for(h=0;a!==s;)h+n[a]>i&&(r.push(t.slice(o,a)),h=0,o=a),h+=n[a],a++;return o!==a&&r.push(t.slice(o,a)),r}),r=function(t,r,a){a||(a={});var s,h,o,f,l,c=n(" ",a)[0],g=t.split(" "),u=[],d=[u],p=a.textIndent||0,v=0,k=0;for(o=0,f=g.length;f>o;o++)if(s=g[o],h=n(s,a),k=e(h),p+v+k>r){if(k>r){for(l=i(s,h,r-(p+v),r),u.push(l.shift()),u=[l.pop()];l.length;)d.push([l.shift()]);k=e(h.slice(s.length-u[0].length))}else u=[s];d.push(u),p=k,v=c}else u.push(s),p+=v+k,v=c;var w=[];for(o=0,f=d.length;f>o;o++)w.push(d[o].join(" "));return w};t.splitTextToSize=function(t,n,e){e||(e={});var i,a=e.fontSize||this.internal.getFontSize(),s=function(t){var n={0:1},e={};if(t.widths&&t.kerning)return{widths:t.widths,kerning:t.kerning};var i=this.internal.getFont(t.fontName,t.fontStyle),r="Unicode";return i.metadata[r]?{widths:i.metadata[r].widths||n,kerning:i.metadata[r].kerning||e}:{widths:n,kerning:e}}.call(this,e);i=t.match(/[\n\r]/)?t.split(/\r\n|\r|\n/g):[t];var h=1*this.internal.scaleFactor*n/a;s.textIndent=e.textIndent?1*e.textIndent*this.internal.scaleFactor/a:0;var o,f,l=[];for(o=0,f=i.length;f>o;o++)l=l.concat(r(i[o],h,s));return l}}(jsPDF.API);