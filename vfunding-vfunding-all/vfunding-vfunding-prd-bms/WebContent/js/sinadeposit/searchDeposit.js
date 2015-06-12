/**
 * 查询新浪用户可用余额
 * louchen 2015-01-29
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
	condition = $("#condition").val();
	if(condition==""){
		showMsg("系统提示","请输入流水单号");
		return false;
	}
	var data={};
	data["condition"]=condition;
	showWait(true);
	$.ajax({
		url:baseUrl+"/searchDeposit",
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
	if(obj.sinaResult.depositList){
		var str = getSinaValue(obj,"depositList");
		var arr = str.split("^");
		var v = "微积金流水号:"+getVfundingValue(obj,"tradeNo")
			+",充值金额:"+getVfundingValue(obj,"money")
			+",充值状态:"+getStatus(json.obj.vfundingResult.status)
			+"。";
		var s = "新浪流水号:"+arr[0]
			+",充值金额:"+arr[1]
			+",充值状态:"+arr[2]
			+",充值时间:"+getLocalTime(arr[3])
			+",最后修改时间:"+getLocalTime(arr[4])
			+"。";
		if(json.obj.vfundingResult.status==4&&arr[2]=="SUCCESS"){
			v+= "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			v+= "<a href=\"javascript:repair('"+arr[0]+"');\">手动更新充值状态和金额入微积金账户["+arr[0]+"]</a>";
		}
		resultDiv.html(v+"<br/><br/>"+s);
	}else{
		resultDiv.html("查询结果为空");
	}
	
}
//获取微积金数据
function getVfundingValue(obj,field){
	return "<font color=\"blue\">"+obj.vfundingResult[field]+"</font>";
}
//获取新浪接口数据
function getSinaValue(obj,field){
	//return "<font color=\"blue\">"+obj.sinaResult[field]+"</font>";
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
//获取充值状态
function getStatus(value){
	if(value==0){
		return "待审核";
	}else if(value==1){
		return "处理成功";
	}else if((value==2||value==3)){
		return "处理失败";
	}else if(value==4){
		return "等待新浪回调";
	}
}
//手动更新
function repair(condition) {
	var data={};
	data["condition"]=condition;
	$.ajax({
		url:baseRepairUrl+"/repairRecharge",
		data:data,
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			json = $.parseJSON(data);
			showMsg("系统提示",json.msg);			
		}
	});
}