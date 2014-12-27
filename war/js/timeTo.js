/**
 * TimeTo jQuery plug-in
 * Show countdown timer or realtime clock
 *
 * @author Alexey Teterin <altmoc@gmail.com>
 * @version 1.0.13
 * @license MIT http://opensource.org/licenses/MIT
 * @date 2014-01-17
 */
(function(k){if("object"===typeof exports){var n=require("jquery");module.exports=k(n||$)}else"function"===typeof define&&define.amd?define(["jquery"],k):k(n||$)})(function(k){function n(c){var g=this.data(),a=this.find("ul");if(g.vals&&0!==a.length){c||(c=g.seconds);var f=!1;g.intervalId&&(f=!0,clearTimeout(g.intervalId));var e=Math.floor(c/86400),d=86400*e,h=Math.floor((c-d)/3600),d=d+3600*h,l=Math.floor((c-d)/60);c-=d+60*l;e=(100>e?"0"+(10>e?"0":""):"")+e+(10>h?"0":"")+h+(10>l?"0":"")+l+(10>c?"0":"")+c;h=g.vals.length-1;for(l=e.length-1;0<=h;h--,l--)c=parseInt(e.substr(l,1)),g.vals[h]=c,a.eq(h).children().html(c);if(f){var b=this;g.ttStartTime=Date.now();g.intervalId=setTimeout(function(){q.call(b)},1E3);this.data("intervalId",g.intervalId)}}}function q(c){var g=this.find("ul"),a=this.data();if(a.vals&&0!=g.length){void 0==c&&(c=a.iSec);var f=a.vals[c],e=g.eq(c),d=e.children(),h=a.countdown?-1:1;d.eq(1).html(f);f+=h;if(c==a.iSec){var l=a.tickTimeout,b=(new Date).getTime()-a.ttStartTime;a.sec+=h;l+=Math.abs(a.seconds-a.sec)*l-b;a.intervalId=setTimeout(function(){q.call(t)},l)}if(0>f||f>a.limits[c])0>f?(f=a.limits[c],c==a.iHour&&0<a.displayDays&&0<c&&0==a.vals[c-1]&&(f=3)):f=0,0<c&&q.call(this,c-1);d.eq(0).html(f);var t=this;k.support.transition?(e.addClass("transition"),e.css({top:0}),setTimeout(function(){e.removeClass("transition");d.eq(1).html(f);e.css({top:"-"+a.height+"px"});0<h||c!=a.iSec||(a.sec==a.countdownAlertLimit&&g.parent().addClass("timeTo-alert"),0===a.sec&&(g.parent().removeClass("timeTo-alert"),a.intervalId&&(clearTimeout(a.intervalId),t.data("intervalId",null)),"function"===typeof a.callback&&a.callback()))},410)):e.stop().animate({top:0},400,c!=a.iSec?null:function(){d.eq(1).html(f);e.css({top:"-"+a.height+"px"});0<h||c!=a.iSec||(a.sec==a.countdownAlertLimit?g.parent().addClass("timeTo-alert"):0==a.sec&&(g.parent().removeClass("timeTo-alert"),a.intervalId&&(clearTimeout(a.intervalId),t.data("intervalId",null)),"function"===typeof a.callback&&a.callback()))});a.vals[c]=f}else a.intervalId&&(clearTimeout(a.intervalId),this.data("intervalId",null)),a.callback&&a.callback()}var r={start:function(c){c&&n.call(this,c);var g=this;c=setTimeout(function(){q.call(g)},1E3);this.data("ttStartTime",(new Date).getTime());this.data("intervalId",c)},stop:function(){var c=this.data();c.intervalId&&(clearTimeout(c.intervalId),this.data("intervalId",null));return c},reset:function(c){var g=r.stop.call(this);this.find("div").css({backgroundPosition:"left center"});this.find("ul").parent().removeClass("timeTo-alert");"undefined"===typeof c&&(c=g.value);g.vals&&(g.vals=null);n.call(this,c)}},s={en:{days:"days",hours:"hours",min:"minutes",sec:"seconds"},ru:{days:"\u0434\u043d\u0435\u0439",hours:"\u0447\u0430\u0441\u043e\u0432",min:"\u043c\u0438\u043d\u0443\u0442",sec:"\u0441\u0435\u043a\u0443\u043d\u0434"},ua:{days:"\u0434\u043di\u0432",hours:"\u0433\u043e\u0434\u0438\u043d",min:"\u0445\u0432\u0438\u043b\u0438\u043d",sec:"\u0441\u0435\u043a\u0443\u043d\u0434"},de:{days:"Tag",hours:"Uhr",min:"Minuten",sec:"Secunden"},fr:{days:"jours",hours:"heures",min:"minutes",sec:"secondes"},sp:{days:"d\u00edas",hours:"reloj",min:"minutos",sec:"segundos"},it:{days:"giorni",hours:"ore",min:"minuti",sec:"secondi"},nl:{days:"dagen",hours:"uren",min:"minuten",sec:"seconden"},no:{days:"dager",hours:"timer",min:"minutter",sec:"sekunder"},pt:{days:"dias",hours:"horas",min:"minutos",sec:"segundos"}};"undefined"===typeof k.support.transition&&(k.support.transition=function(){var c=(document.body||document.documentElement).style;return void 0!==c.transition||void 0!==c.WebkitTransition||void 0!==c.MozTransition||void 0!==c.MsTransition||void 0!==c.OTransition}());k.fn.timeTo=function(){for(var c={callback:null,captionSize:0,countdown:!0,countdownAlertLimit:10,displayCaptions:!1,displayDays:0,displayHours:!0,fontFamily:"Verdana, sans-serif",fontSize:28,lang:"en",seconds:0,start:!0,theme:"white",vals:[0,0,0,0,0,0,0,0,0],limits:[9,9,9,2,9,5,9,5,9],iSec:8,iHour:4,tickTimeout:1E3,intervalId:null},g,a={},f=0,e;e=arguments[f];++f)0==f&&"string"===typeof e?g=e:"object"==typeof e?"function"===typeof e.getTime?a.timeTo=e:a=k.extend(a,e):"function"==typeof e?a.callback=e:(e=parseInt(e),isNaN(e)||(a.seconds=e));if(a.timeTo){var d,f=(new Date).getTime();a.timeTo.getTime?d=a.timeTo.getTime():"number"===typeof a.timeTo&&(d=a.timeTo);a.timeTo>f&&(a.seconds=Math.floor((d-f)/1E3))}else if(a.time||!a.seconds)if((d=a.time)||(d=new Date),"object"===typeof d&&d.getTime)a.seconds=3600*d.getHours()+60*d.getMinutes()+d.getSeconds(),a.countdown=!1;else if("string"===typeof d){d=d.split(":");f=0;e=1;for(var h;h=d.pop();)f+=h*e,e*=60;a.seconds=f;a.countdown=!1}!1!==a.countdown&&86400<a.seconds&&"undefined"===typeof a.displayDays?(d=Math.floor(a.seconds/86400),a.displayDays=10>d&&1||100>d&&2||3):!0===a.displayDays?a.displayDays=3:a.displayDays&&(a.displayDays=0<a.displayDays?Math.floor(a.displayDays):3);return this.each(function(){var e=k(this),b=e.data(),d;if(b.vals)b.intervalId&&(clearInterval(b.intervalId),b.intervalId=null),k.extend(b,a);else{b=k.extend(c,a);b.height=Math.round(100*b.fontSize/93);b.width=Math.round(0.8*b.fontSize+0.13*b.height);b.displayHours=!(!b.displayDays&&!b.displayHours);e.addClass("timeTo").addClass("timeTo-"+b.theme).css({fontFamily:b.fontFamily,fontSize:b.fontSize+"px"});var f='<ul style="left:'+Math.round(b.height/10)+"px; top:-"+b.height+'px"><li>0</li><li>0</li></ul></div>',h=' style="width:'+b.width+"px; height:"+b.height+'px;"';d='<div class="first"'+h+">"+f;var f="<div"+h+">"+f,m=Math.round(2*b.width+3)+1,h=b.captionSize||Math.round(0.43*b.fontSize);thtml=(b.displayCaptions?(b.displayHours?'<figure style="max-width:'+m+'px">$1<figcaption style="font-size:'+h+'px">'+s[b.lang].hours+"</figcaption></figure><span>:</span>":"")+'<figure style="max-width:'+m+'px">$1<figcaption style="font-size:'+h+'px">'+s[b.lang].min+'</figcaption></figure><span>:</span><figure style="max-width:'+m+'px">$1<figcaption style="font-size:'+h+'px">'+s[b.lang].sec+"</figcaption></figure>":(b.displayHours?"$1<span>:</span>":"")+"$1<span>:</span>$1").replace(/\$1/g,d+f);if(0<b.displayDays){var m=0.4*b.fontSize,p=d;for(d=b.displayDays-1;0<d;d--)p+=1===d?f.replace('">'," margin-right:"+Math.round(m)+'px">'):f;thtml=(b.displayCaptions?'<figure style="width:'+Math.round(b.width*b.displayDays+m+4)+'px">$1<figcaption style="font-size:'+h+"px; padding-right:"+Math.round(m)+'px">'+s[b.lang].days+"</figcaption></figure>":"$1").replace(/\$1/,p)+thtml}e.html(thtml)}f=e.find("div");if(f.length<b.vals.length){h=b.vals.length-f.length;m=b.vals;p=b.limits;b.vals=[];b.limits=[];for(d=0;d<f.length;d++)b.vals[d]=m[h+d],b.limits[d]=p[h+d];b.iSec=b.vals.length-1;b.iHour=b.vals.length-5}b.sec=b.seconds;e.data(b);g&&r[g]?r[g].call(e,b.seconds):b.start?r.start.call(e,b.seconds):n.call(e,b.seconds)})};return jQuery});
