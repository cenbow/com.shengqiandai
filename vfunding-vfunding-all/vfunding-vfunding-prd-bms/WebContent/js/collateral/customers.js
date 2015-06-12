var customers;
$(function() {
	customers = $("#customersList")
			.datagrid(
					{
						url : '/system/collateral/customersY',
						fit : true,
						fitColumns : true,
						border : false,
						pagination : true,
						pageSize : 10,
						checkOnSelect : false,
						selectOnCheck : false,
						singleSelect : true,
						pageList : [ 10, 15, 20, 25, 30 ],
						columns : [ [
								{
									field : 'id',
									title : '序号',
									align : 'center'
								},
								{
									field : 'verifyTime',
									title : '发标日期',
									align : 'center',
									formatter : function(value, row, index) {
										return value==0?'-':timetodate(value,"yyyy-MM-dd hh:mm:ss");
									}
								},
								{
									field : 'account',
									title : '借款金额',
									align : 'center',
									formatter : function(value, row, index) {
										return value + "元";
									}
								},
								{
									field : 'ownerName',
									title : '客户姓名',
									align : 'center'
								},
								{
									field : 'mortgageType',
									title : '抵押品种',
									align : 'center',
									formatter : function(value, row, index) {
										if (value == '1') {
											return value = "汽车";
										} else if (value == '2') {
											return value = "债权抵押";
										} else {
											return value = "房屋";
										}
									}
								},
								{
									field : 'name',
									title : '借款标题',
									align : 'center'
								},
								{
									field : 'timeLimit',
									title : '借款期限',
									align : 'left',
									formatter : function(value, row, index) {
										return value + "个月";
									}
								},
								{
									field : 'contractStart',
									title : '合同开始日期',
									align : 'center'
								},
								{
									field : 'contractEnd',
									title : '合同结束日期',
									align : 'center'
								},
								{
									field : 'successTime',
									title : '满标日期',
									align : 'center',
									formatter : function(value, row, index) {
										return value==0?'-':timetodate(value,"yyyy-MM-dd hh:mm:ss");
									}
								},
								{
									field : 'endTime',
									title : '到期日期',
									align : 'center',
									formatter : function(value, row, index) {
										return value==0?'-':timetodate(value,"yyyy-MM-dd hh:mm:ss");
									}
								},
								{
									field : 'stuts',
									title : '操作',
									align : 'left',
									formatter : function(value, row, index) {
										return value = '[<a id="'
												+ row.id
												+ '" href="javascript: editEmp(\''
												+ row.id
												+ '\');"  style="color: black;text-decoration:none">编辑</a>]';
									}
								} ] ],
						toolbar : '#easrchTool',
						onLoadSuccess : function() {
							parent.$.messager.progress('close');
							parent.$.modalDialog.openner_dataGrid=customers;
						}
					});
});

function editEmp(id) {
	parent.$.modalDialog({
		title : '编辑借款客户信息',
		width : 500,
		height : 190,
		href : '/system/collateral/customersDetails?id=' + id
	});
	parent.$.messager.progress('close');
}

function updaBorrow() {
	var name = $('#ownerName').val();
	var contractStart = $('#contractStart').val();
	var contractEnd = $('#contractEnd').val();
	var id = $('#id').val();
	$.ajax({
		url : '/system/collateral/customersOK?id=' + id + '&ownerName=' + name
				+ '&contractStart=' + contractStart + '&contractEnd='
				+ contractEnd,
		cache : true,
		type : 'POST',
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
//			alert("成功！");
			parent.$.messager.show({
				title : '系统提示',
				msg : '操作成功',
				timeout : 5000,
				showType : 'slide'
			});
			parent.$.modalDialog.handler.dialog("close");
			parent.$.messager.progress('close');
			parent.$.modalDialog.openner_dataGrid.datagrid('reload');
		}
	});

}

function findCustomers() {
	customers.datagrid('load', {
		'ownerName' : $('#ownerName').val(),
		'mortgageType' : $('#mortgageType').val()
	});
	parent.$.messager.progress('close');
}

//js时间戳转时间
Date.prototype.pattern=function(fmt) {       
  var o = {        
  "M+" : this.getMonth()+1, //月份       
  "d+" : this.getDate(), //日      
  "h+" : this.getHours() == 0 ? 12 : this.getHours(), //小时       
  "H+" : this.getHours(), //小时       
  "m+" : this.getMinutes(), //分       
  "s+" : this.getSeconds()  //秒       
  };       
  if(/(y+)/.test(fmt)){       
      fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));       
  }      
  if(/(E+)/.test(fmt)){       
      fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : ""));       
  }       
  for(var k in o){       
      if(new RegExp("("+ k +")").test(fmt)){       
          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));       
      }       
  }
  return fmt;       
}
function timetodate(tim,dat){
  return new Date(parseInt(tim)*1000).pattern(dat);   //"yyyy/MM/dd,hh,mm,ss"    
}