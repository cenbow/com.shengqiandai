var baseUrl;
var	resultDiv;
//onload
$(function() {
	init();
})
//初始化
function init(){
	baseUrl = "/sinadeposit";
	resultDiv = $("#result");
	parent.$.messager.progress('close');
	showWait(false);
}
//查询
function queryDetail() {
	username = $("#username").val();
	money = $("#money").val();
	summary = $("#summary").val();
	tradeCode = $("#tradeCode").val();
	if(username==""){
		showMsg("系统提示","请输入用户名/手机号");
		return false;
	}
	if(money==""){
		showMsg("系统提示","请输入金额");
		return false;
	}
	if(summary==""){
		showMsg("系统提示","请输入摘要");
		return false;
	}
	if(tradeCode==""){
		showMsg("系统提示","请输入交易码");
		return false;
	}
	var data={};
	data["username"]=username;
	data["money"]=money;
	data["summary"]=summary;
	data["tradeCode"]=tradeCode;
	showWait(true);
	$.ajax({
		url:baseUrl+"/singleHostingPayTrade",
		data:data,
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			showWait(false);
			json = $.parseJSON(data);
			if(json.success){
				showMsg("系统提示",json.msg);
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
	var v = "微积金账户净资产:"+getVfundingValue(obj,"total")
		+",可用余额:"+getVfundingValue(obj,"useMoney")
		+",冻结金额:"+getVfundingValue(obj,"noUseMoney")
		+",待收金额:"+getVfundingValue(obj,"collection")
		+"。";
	var s = "新浪账户余额:"+getSinaValue(obj,"balance")
		+",可用余额:"+getSinaValue(obj,"availableBalance")
		+",额外收益:"+getSinaValue(obj,"bonus")
		+"。";
	resultDiv.html(v+"<br/><br/>"+s);
}
//获取微积金用户资金
function getVfundingValue(obj,field){
	return "<font color=\"blue\">"+obj.v_account[field]+"</font>";
}
//获取新浪用户资金
function getSinaValue(obj,field){
	return "<font color=\"blue\">"+obj.s_account[field]+"</font>";
}
