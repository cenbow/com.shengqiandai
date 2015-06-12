/*
 * louchen 2014-11-21
 * cnzz全局js,需在<head>后引入
 */
var _czc = _czc || [];
_czc.push([ "_setAccount", "1253568232" ]);
_czc.push([ "_setAutoPageview", false ]);
// 统计当前请求地址
var _czc_href = window.location.href;
var _czc_host = window.location.protocol + "//" + window.location.host;
var _czc_relative = _czc_href.substring(_czc_host.length, _czc_href.length);
_czc_relative = _czc_relative == "/" ? "/index" : _czc_relative
//console.log("cnzz站点统计-->>请求地址->>:" + _czc_relative);
_czc.push([ '_trackPageview', _czc_relative ]);
