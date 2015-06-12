var finalList;
	$(function() {
		finalList = $('#list').datagrid({
			url : '/system/funds/applyRechargeList?status=0&type=2',
			isField : "id",
			fit : true,
			pagination : true,//显示分页
			pageSize : 15,//分页大小
			pageList : [ 10, 15, 20 ],//每页的个数
			border : false,
			singleSelect : true,
			queryParams : {'status' : 0,'type':2},
			fit : true,//自动补全
			fitColumns : true,
			columns : [ [ {
				field : 'rechargeId',
				title : '充值编号',
				width : 70,
				align : 'center'
			}, {
				field : 'tradeNo',
				title : '流水号',
				width : 200,
				align : 'center'
			}, {
				field : 'userName',
				title : '用户名称',
				width : 120,
				align : 'center'
			}, {
				field : 'realName',
				title : '真实姓名',
				width : 80,
				align : 'center'
			}, {
				field : 'type',
				title : '类型',
				width : 80,
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
				width : 120,
				align : 'center',
				formatter : function(value, row, index) {
					return value+'元';
				}
			}, {
				field : 'fee',
				title : '费用',
				width : 60,
				align : 'center',
				formatter : function(value, row, index) {
					return value+'元';
				}
			}, {
				field : 'money',
				title : '到账金额',
				width : 120,
				align : 'center',
				formatter : function(value, row, index) {
					return value-row.fee+'元';
				}
			}, {
				field : 'addtime',
				title : '充值时间',
				width : 150,
				align : 'center'
			}, {
				field : 'reward',
				title : '银行返回',
				width : 60,
				align : 'center'
			}, {
				field : 'status',
				title : '操作',
				width : 50,
				align : 'center',
				formatter : function(value, row, index) {
					if ($.canEdit) {
						return value==2?'处理失败':
							(value==0?('<a id="goApply_'+row.rechargeId+'">审核</a>'):(
									value==1?'处理成功':'error'));
					} else {
						return "无权限";
					}
				}
			}
			]],
			toolbar : '#searchTool',
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
				$('a[id^="goApply_"]').click(function() {
					var id = this.id.split('_')[1];
					var cashdialog_ = window.parent.$.modalDialog({
						title : '详情',
						width : 1000,
						height : 500,
						href : '/system/funds/rechargeDetail?id='+id
					});
					parent.$.messager.progress('close');
					parent.$.modalDialog.openner_dataGrid=finalList;
				}); 
			}
		});

	})
	
function queryDetail() {
	finalList.datagrid('load', {
		'username' : $('#userName').val(),
		'tradeNo' : $('#tradeNo').val(),
		'type' : '2',
		'status' : '0' // 线下充值待审
	});
}
function addRechargeOffline() {
	parent.$.modalDialog({
		title : '添加充值',
		width : 500,
		height : 400,
		href : '/system/funds/addRechargePage',
		buttons : [ {
			text : '添加',
			iconCls:'icon-save',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = finalList;
				var f = parent.$.modalDialog.handler.find('#rechargeForm');
				f.submit();
			}
		},{
			text : '关闭',
			handler : function() {
				parent.$.modalDialog.handler.dialog('close');
			}
		} ]
	});
}
