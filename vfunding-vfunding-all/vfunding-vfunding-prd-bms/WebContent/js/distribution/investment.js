var investmentList;
$(function() {
	var userId = $('#userId').val();
	investmentList = $("#investment").datagrid({
		url : '/system/userEmp/investmentHtml?userId=' + userId,
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
			field : 'borrowName',
			title : '项目名称',
			align : 'center',
			width : '130'
		},{
			field : 'jsyl',
			title : '净收益率',
			align : 'center',
			width : '130',
			formatter : function(value, row, index) {
				return value = row.apr-row.bfee-row.gfee;
			}
		},  
		{
			field : 'repaymentAccount',
			title : '待收金额',
			align : 'center',
			width : '130'
		},
		{
			field : 'repaymentYesAccount',
			title : '已收金额',
			align : 'center',
			width : '130'
		},
		 {
			field : 'timeLimit',
			title : '投资期限',
			align : 'center',
			width : '130'
		},
		{
			field : 'addtime',
			title : '投标时间',
			align : 'center',
			width : '130',

			formatter : function(value, row, index) {
				return value = timetodate(row.addtime, "yyyy-MM-dd");
			}

		},
		{
			field : 'status',
			title : '状态',
			align : 'center',
			width : '130',
			formatter : function(value, row, index) {
				if (value == 0) {
					return value = "失败";
				} else if (value == 1) {
					return value = "成功";
				} else {
					return value = "先下充值等待";
				}
			}
		} ] ],
		// toolbar : '#userEmpquery',
		onLoadSuccess : function() {
			parent.$.messager.progress('close');
			parent.$.modalDialog.openner_dataGrid = investmentList;
		}
	});
});

// js时间戳转时间
Date.prototype.pattern = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours() == 0 ? 12 : this.getHours(), // 小时
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds()
	// 秒
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt
				.replace(
						RegExp.$1,
						((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f"
								: "\u5468")
								: ""));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}
function timetodate(tim, dat) {
	return new Date(parseInt(tim) * 1000).pattern(dat); // "yyyy/MM/dd,hh,mm,ss"
}