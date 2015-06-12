var finalList;
	$(function() {
		finalList = $('#list').datagrid({
			url : '/system/funds/applyRechargeList',
			isField : "id",
			fit : true,
			pagination : true,//显示分页
			pageSize : 15,//分页大小
			pageList : [ 10, 15, 20 ],//每页的个数
			border : false,
			singleSelect : true,
			queryParams : {'status' : $('#status').val()},
			fit : true,//自动补全
			fitColumns : true,
			columns : [ [ {
				field : 'rechargeId',
				title : '充值编号',
				width : 60,
				align : 'center'
			}, {
				field : 'tradeNo',
				title : '流水号',
				width : 180,
				align : 'center'
			}, {
				field : 'userName',
				title : '用户名称',
				width : 100,
				align : 'center'
			}, {
				field : 'realName',
				title : '真实姓名',
				width : 100,
				align : 'center'
			}, {
				field : 'type',
				title : '类型',
				width : 60,
				align : 'center',
				formatter : function(value, row, index) {
					return value==1?'在线充值':'线下充值';
				}
			}, {
				field : 'bank',
				title : '充值银行',
				width : 100,
				align : 'center',
				formatter : function(value, row, index) {
					return value==null?'后台充值':value;
				}
			}, {
				field : 'money',
				title : '充值金额',
				align : 'center',
				width : 100,
				formatter : function(value, row, index) {
					return value+'元';
				}
			}, {
				field : 'fee',
				title : '费用',
				width : 50,
				align : 'center',
				formatter : function(value, row, index) {
					return value+'元';
				}
			}, {
				field : 'money',
				title : '到账金额',
				width : 100,
				align : 'center',
				formatter : function(value, row, index) {
					return value-row.fee+'元';
				}
			}, {
				field : 'addtime',
				title : '充值时间',
				width : 140,
				align : 'center'
			}, {
				field : 'verifyTimeStr',
				title : '审核时间',
				width : 140,
				align : 'center'
			}, {
				field : 'reward',
				title : '银行返回',
				width : 60,
				align : 'center'
			}, {
				field : 'status',
				title : '操作',
				width : 80,
				align : 'center',
				formatter : function(value, row, index) {
					var tempMsg;
					if(value==0){
						tempMsg="待审核";
					}else if(value==1){
						tempMsg="<font color=\"green\">处理成功</font>";
					}else if((value==2||value==3)){
						tempMsg="<font color=\"red\">处理失败</font>";
					}else if(value==4){
						tempMsg="等待新浪回调";
					}
					return tempMsg;
				}
			}
			]],
			toolbar : '#searchTool',
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			}
		});

	})
	
function queryDetail() {
	finalList.datagrid('load', {
		'username' : $('#userName').val(),
		'tradeNo' : $('#tradeNo').val(),
		'type' : $('#type').val(),
		'bank' : $('#bank').val(),
		'status' : $('#status').val(),
		'startTime' : $('#startTime').val(),
		'endTime' : $('#endTime').val()
	});
}
$(function(){
	$('#type').change(function(){
		$('#bank option').remove();
		if(this.value == 1){
			$("#bank").append('<option value="" selected="selected">全部</option>');
			$("#bank").append('<option value="32">国付宝</option>');
			$("#bank").append('<option value="55">网银在线</option>');
			$("#bank").append('<option value="54">汇潮支付</option>');
			$("#bank").append('<option value="57">新浪支付</option>');
		} else if(this.value == 2){
			$("#bank").append('<option value="" selected="selected">全部</option>');
			$("#bank").append('<option value="1">支付宝</option>');
			$("#bank").append('<option value="36">光大银行</option>');
		} else {
			$("#bank").append('<option value="" selected="selected">全部</option>');
			$("#bank").append('<option value="32">国付宝</option>');
			$("#bank").append('<option value="55">网银在线</option>');
			$("#bank").append('<option value="54">汇潮支付</option>');
			$("#bank").append('<option value="57">新浪支付</option>');
			$("#bank").append('<option value="1">支付宝</option>');
			$("#bank").append('<option value="36">光大银行</option>');
		}
	});
})
