var finalList;
	$(function() {
		finalList = $('#list').datagrid({
			url : '/system/funds/applyCashList?status=2',
			isField : "id",
			pagination : true,//显示分页
			pageSize : 15,//分页大小
			pageList : [ 10, 15, 20 ],//每页的个数
			singleSelect : true,
			fit : true,//自动补全
			fitColumns : true,
			columns : [ [ {
				field : 'cashId',
				title : '借款编号',
				align : 'center'
			}, {
				field : 'userName',
				title : '用户名称',
				align : 'center'
			}, {
				field : 'realName',
				title : '真实姓名',
				align : 'center'
			}, {
				field : 'bankNum',
				title : '提现账号',
				align : 'center'
			}, {
				field : 'bankName',
				title : '提现银行',
				align : 'center'
			}, {
				field : 'branch',
				title : '支行',
				align : 'center'
			}, {
				field : 'money',
				title : '提现总额',
				align : 'center',
				formatter : function(value, row, index) {
					return value + "元";
				}
			}, {
				field : 'account',
				title : '到账金额',
				align : 'center',
				formatter : function(value, row, index) {
					return value + "元";
				}
			}, {
				field : 'fee',
				title : '手续费',
				align : 'center',
				formatter : function(value, row, index) {
					return value + "元";
				}
			}, {
				field : 'useHongbao',
				title : '红包抵扣',
				align : 'center',
				formatter : function(value, row, index) {
					return value + "元";
				}
			}, {
				field : 'addtime',
				title : '提现时间',
				align : 'center'
			}, {
				field : 'status',
				title : '操作',
				align : 'center',
				formatter : function(value, row, index) {
					return value==2?'处理失败':(value==1?'处理成功':
						(value==0?('<input type="button" id="goApply_'+row.cashId+'" value="审核"></input>'):'error'));
				}
			}
			]],
			toolbar : '#searchTool',
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			}
		});

	})