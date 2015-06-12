
//验证 radio按钮中value=3（审核通过） 的 是否都被选中，如果其中有一个为被选中，提交按钮显示 "打回" 
function checkRadioChecked() {
	$(":radio[value=3]").each(function(index) {
		if ($(this).attr("checked") == "checked") {
			$("#back").hide();
			$("#ok").show();
		} else {
			$("#ok").hide();
			$("#back").show();
			return false;
		}
	});
}
//根据点击radio按钮确定是显示"打回","发布"
function showHiddenContext(opt, id) {
	if ($(opt).val() == '2') {
		$(opt).parent().parent().find("div").show();
		$("#ok").hide();
		$("#back").show();
	} else {
		$(opt).parent().parent().find("div").hide();
		checkRadioChecked();
	}
}

//判断每一块是否都进行审核
function checkRadioCheckedAll(){
	var _radioVal3Sum =  $(":radio[value=3]:checked").size();
	var _radioVal2Sum = $(":radio[value=2]:checked").size();
	var _radioValSum = $(":radio[value=3]").size();
	if((_radioVal3Sum + _radioVal2Sum) != _radioValSum){
		$.messager.alert("提示","请对所有标的审核！")
		return false;
	}else{
		return true;
	}
}

//设置提交时的类型  save，back，ok
function setTrialStatus(status) {
	$("#trialStatus").val(status);
}
//textarea 在表单中无法直接提交，将textarea中的值set到一个input 标签中进行提交
function setRemark(opt){
	$.each(opt,function(index){
		$(this).parent().parent().find("input[name*='verifyRemark']").val($(this).val());
	});
}
$(function() {
	//页面加载时调用，确定显示哪个按钮
	checkRadioChecked();
	$("input[name='subBtn']").on("click", function() {
		//提交数据前，进行 提交类型与备注值的设置
		setTrialStatus($(this).attr("id"));
		setRemark($("textarea[name='context']"));
		if($(this).attr("id") == 'ok' || $(this).attr("id") == 'back'){
			if(!checkRadioCheckedAll()){
				return false;
			}
		}
		//提交数据
		$.ajax({
			url : '/system/week/trialWeek',
			data : $('#subTrial').serialize(),
			type : 'post',
			cache : false,
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert('很遗憾，出异常了' + errorThrown);
			},
			beforeSend : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog("close");
					parent.$.messager.show({
						title : '系统提示',
						msg : '操作成功',
						timeout : 5000,
						showType : 'slide'
					});
				} else {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog("close");
					parent.$.messager.show({
						title : '系统提示',
						msg : '操作失败:'+result.msg,
						timeout : 5000,
						showType : 'slide'
					});
				}
			}
		});
	});
});