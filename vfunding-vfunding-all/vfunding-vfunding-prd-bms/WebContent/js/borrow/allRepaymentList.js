var finalList;
	$(function() {
		finalList = $('#list')
				.datagrid(
						{
							url : '/system/borrow/allRepaymentSystemList',
							isField : "id",
							pagination : true,//显示分页
							pageSize : 15,//分页大小
							pageList : [ 10, 15, 20 ],//每页的个数
							checkOnSelect : false,
							selectOnCheck : false,
							singleSelect : true,
							fit : true,//自动补全
							fitColumns : true,
							border : false,
							columns : [ [
									{
										field : 'id',
										title : '还款编号',
										align : 'center'
									},
									{
										field : 'userName',
										title : '发标人',
										align : 'center'
									},
									{
										field : 'ownerName',
										title : '借款人',
										align : 'center'
									},
									{
										field : 'borrowName',
										title : '借款标题',
										align : 'left',
										formatter : function(value, row, index) {
											return '<a href="http://www.vfunding.cn/borrow/borrowDetail/'+row.borrowId+'" target="_blank">'+value+'</a>';
										}
									},
									{
										field : 'order',
										title : '期数',
										align : 'center',
										formatter : function(value, row, index) {
											return (value+1)+"/"+row.timeLimit;
										}
									},
									{
										field : 'repaymentTime',
										title : '应还时间',
										align : 'center',
										formatter : function(value, row, index) {
											return timetodate(value,"yyyy-MM-dd");
										}
									},
									{
										field : 'repaymentAccount',
										title : '应还本息',
										align : 'center',
										formatter : function(value, row, index) {
											return value + "元";
										}
									},
									{
										field : 'capital',
										title : '应还本金',
										align : 'center',
										formatter : function(value, row, index) {
											return value + "元";
										}
									},
									{
										field : 'interest',
										title : '应还利息',
										align : 'center',
										formatter : function(value, row, index) {
											return value + "元";
										}
									},
									{
										field : 'lateDays',
										title : '逾期天数',
										align : 'center',
										formatter : function(value, row, index) {
											return value + "天";
										}
									},
									{
										field : 'forfeit',
										title : '罚金',
										align : 'center',
										formatter : function(value, row, index) {
											return value + "元";
										}
									},
									{
										field : 'status',
										title : '操作',
										align : 'center',
										formatter : function(value, row, index) {
											return value==1?'已还':(value==0?'未还':'error');
										}
									},{
										field : 'other',
										title : '投资人列表',
										align : 'center',
										formatter : function(value, row, index) {
											return '<a id="goLook_'+row.borrowId+'">查看</a>';
										}
									} ] ],
							toolbar : '#searchTool',
							onLoadSuccess : function() {
								parent.$.messager.progress('close');
								$('a[id^="goLook_"]').click(function() {
									var id = this.id.split('_')[1];
									var cashdialog_ = parent.$.modalDialog({
										title : '投资人详情',
										width : 810,
										height : 500,
										href : '/system/funds/borrowTenders?id='+id
									});
									parent.$.messager.progress('close');
								});
							}
						});
	})
	function queryDetail() {
		finalList.datagrid('load', {
			'status' : $('#status').val(),
			'keyWord' : $('#name').val(),
			'username' : $('#username').val(),
			'startTime' : $('#startTime').val(),
			'endTime' : $('#endTime').val()
		});
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