/**
 * 查询新浪托管交易
 * louchen 2015-02-09
 */
var baseUrl;
var baseRepairUrl;
var	resultDiv;
//onload
$(function() {
	init();
})
//初始化
function init(){
	baseUrl = "/sinadeposit";
	baseRepairUrl = "/sinamaintenance";
	resultDiv = $("#result");
	parent.$.messager.progress('close');
	showWait(false);
}
//查询
function queryDetail() {
	userId = $("#userId").val();
	idType = $("#idType").val();
	start = $("#start").val();
	end = $("#end").val();
	pageNo = $("#pageNo").val();
	pageSize = $("#pageSize").val();
	if(userId==""){
		showMsg("系统提示","请输入用户名");
		return false;
	}
	var data={};
	data["userId"]=userId;
	data["idType"]=idType;
	data["start"]=start;
	data["end"]=end;
	data["pageNo"]=pageNo;
	data["pageSize"]=pageSize;
	showWait(true);
	$.ajax({
		url:baseUrl+"/searchHostingTrade",
		data:data,
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			showWait(false);
			json = $.parseJSON(data);
			if(json.success){
				showResult(json);
			}else{
				showMsg("系统提示",json.msg);
			}			
		}
	});
}
//右下角提示
function showMsg(title,msg){
	parent.$.messager.show({
		title : title,
		msg : msg,
		timeout : 5000,
		showType : 'slide'
	});
}
//等待图片
function showWait(flag){
	if(flag){
		resultDiv.html('');
		$("#wait").show();
	}else{
		$("#wait").hide();
	}
}
//输出查询结果
function showResult(json){
	obj = json.obj;
	if(obj.sinaResult.tradeList){
		var str = getSinaValue(obj,"tradeList");
		var list = str.split("|");
		var s = "";
		for(i=0;i<list.length;i++){
			s+=list[i]+"<br/>";
		}
//		var arr = str.split("^");
//		var s = "新浪流水号:"+arr[0]
//			+",充值金额:"+arr[1]
//			+",充值状态:"+arr[2]
//			+",充值时间:"+getLocalTime(arr[3])
//			+",最后修改时间:"+getLocalTime(arr[4])
//			+"。";

		resultDiv.html(s);
	}else{
		resultDiv.html("查询结果为空");
	}
	
}
//获取新浪接口数据
function getSinaValue(obj,field){
	return obj.sinaResult[field];
}
//新浪时间转日期时间
function getLocalTime(ns) {    
   var s1="-";
   var s2=":";
   var yyyy= ns.substring(0,4);      
   var MM = ns.substring(4,6);
   var dd = ns.substring(6,8);
   var HH = ns.substring(8,10);
   var mm = ns.substring(10,12);
   var ss = ns.substring(12,14);
   return yyyy+s1+MM+s1+dd+""+HH+s2+mm+s2+ss;
}     
//时间戳转时间
function getLocalTime_(nS) {     
   return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');     
}   
