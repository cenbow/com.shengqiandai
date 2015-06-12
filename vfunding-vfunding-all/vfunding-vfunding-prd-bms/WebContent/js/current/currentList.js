var finalList;
$(function() {

	finalList = $('#list_').datagrid(
			{
				url : '/system/currentBorrow/currentBorrowList?currentId='+$('#currentId').val(),
				isField : "id",
				pagination : true,// 显示分页
				pageSize : 15,// 分页大小
				pageList : [ 10, 15, 20 ],// 每页的个数
				checkOnSelect : false,
				selectOnCheck : false,
				singleSelect : true,
				fit : true,// 自动补全
				fitColumns : true,
				border : false,
				columns : [ [ {
					field : 'borrowId',
					title : '编号',
					align : 'center'
				},
				{
					field : 'money',
					title : '标金额',
					align : 'center',
					formatter : function(value, row, index) {
						return value + "元";
					}
				},
				{
					field : 'style',
					title : '类型',
					align : 'center',
					formatter : function(value, row, index) {
						return value==1?"抵押标":(value==2?"信用标":"其他");
					}
				},
				{
					field : 'apr',
					title : '利息',
					align : 'center',
					formatter : function(value, row, index) {
						return value + "%";
					}
				},
				{
					field : 'bFee',
					title : '服务费',
					align : 'center',
					formatter : function(value, row, index) {
						return value + "%";
					}
				},
				{
					field : 'gFee',
					title : '担保费',
					align : 'center',
					formatter : function(value, row, index) {
						return value + "%";
					}
				},
				{
					field : 'intoTimeStr',
					title : '入池时间',
					align : 'center'
				},
				{
					field : 'endTimeStr',
					title : '合同截止时间',
					align : 'center'
				},
				{
					field : 'addTimeStr',
					title : '添加时间',
					align : 'center'
				}
				] ],
				toolbar : '#searchTool',
				onLoadSuccess : function() {
					parent.$.messager.progress('close');
					parent.$.modalDialog.openner_dataGrid = finalList;
					$.ajax({
						url : '/system/current/selectCurrentById',
						data : {
							"id" : $('#currentId').val()
						},
						cache : false,
						type : 'post',
						error : function(d) {
							alert(d.responseText);
						},
						success : function(result) {
							result = $.parseJSON(result);
							$("#addSearchToolContent").html(
									"活期池名称：" + result.currentName + " 总金额： "
											+ result.money + "已借金额："
											+ result.moneyYes + " 最小投资额："
											+ result.moneyMin + " 最大投资额："
											+ result.moneyMax + " 单人最大投资额："
											+ result.moneyMaxSingle + " 备用金总额："
											+ result.moneyPreparation
											+ " 已用备用金："
											+ result.moneyPreparationYes)
						}
					});
					
					$.ajax({
						url : '/system/currentBorrow/borrowRemind',
						data : {
							"id" : $('#currentId').val()
						},
						type : 'post',
						success : function(result) {
							result = $.parseJSON(result);
							var obj=eval(result.obj);
							var content = '';
							for(var v in result.obj){
								content = content + '<a href="javascript:addBorrow('+"'"+result.obj[v]+"'"+');" >'+  result.obj[v].split('@')[0]+'需要续标' + result.obj[v].split('@')[1] + 
								'元;' +'</a>' + '  &nbsp;&nbsp;&nbsp;';
							}
							$('#msg').html(content);
						}
					});
					
					
				}
			});

})

function addCurrentUpdate(){
	parent.$.modalDialog({
		title : '续池',
		width : 600,
		height : 400,
		href : '/system/current/addCurrentPage?id='+$('#currentId').val(),
		buttons : [{
			text : '续池',
			iconCls:'icon-save',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = finalList;
				var f = parent.$.modalDialog.handler.find('#addCurrentform');
				f.submit();
			}
		},{
			text : '关闭',
			handler : function() {
				parent.$.modalDialog.handler.dialog('close');
			}
		}]
	});
}
function editCurrent(){
	parent.$.modalDialog({
		title : '续池',
		width : 900,
		height : 400,
		href : '/system/current/editCurrentPage?id='+$('#currentId').val(),
		buttons : [{
			text : '续池',
			iconCls:'icon-save',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = finalList;
				var f = parent.$.modalDialog.handler.find('#editCurrentform');
				f.submit();
			}
		},{
			text : '关闭',
			handler : function() {
				parent.$.modalDialog.handler.dialog('close');
			}
		}]
	});
}
function addBorrow(value){
	parent.$.modalDialog({
		title : '续标',
		width : 1000,
		height : 400,
		href : '/system/currentBorrow/addBorrowPage?value='+ value +'&currentId='+$('#currentId').val(),
		buttons : [ {
			text : '续标',
			iconCls:'icon-save',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = finalList;
				var f = parent.$.modalDialog.handler.find('#addBorrowform');
				f.submit();
			}
		},{
			text : '关闭',
			handler : function() {
				parent.$.modalDialog.handler.dialog('close');
			}
		}]
	});
}
function queryDetail() {
	finalList.datagrid('load', {
		'status' : $('#status').val()
	});
}