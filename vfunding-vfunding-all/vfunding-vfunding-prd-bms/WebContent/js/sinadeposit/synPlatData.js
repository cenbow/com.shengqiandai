/**
 * 同步数据
 * louchen 2015-02-09
 */
var baseUrl;
var	resultDiv;
//onload
$(function() {
	init();
})
//初始化
function init(){
	baseUrl = "/sinasyn";
	resultDiv = $("#result");
	parent.$.messager.progress('close');
	showWait(false);
}
//代收
function do1() {
	$('#pullBtn').attr('href','#');
	showWait(true);
	$.ajax({
		url:baseUrl+"/initProjectPullAll",
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			showWait(false);
			json = $.parseJSON(data);
			if(json.success){
				showMsg("系统提示","操作成功");
			}else{
				showMsg("系统提示",json.msg);
			}			
		}
	});
}
//代付
function do2() {
	$('#pushBtn').attr('href','#');
	showWait(true);
	$.ajax({
		url:baseUrl+"/initProjectPushAll",
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			showWait(false);
			json = $.parseJSON(data);
			if(json.success){
				showMsg("系统提示","操作成功");
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
	
	
}
