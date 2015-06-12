/**
 * 查询新浪用户可用余额
 * louchen 2015-01-29
 */
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
	condition = $("#condition").val();
	if(condition==""){
		showMsg("系统提示","请输入用户名/手机号");
		return false;
	}
	var data={};
	data["condition"]=condition;
	showWait(true);
	$.ajax({
		url:baseUrl+"/searchBalance",
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
