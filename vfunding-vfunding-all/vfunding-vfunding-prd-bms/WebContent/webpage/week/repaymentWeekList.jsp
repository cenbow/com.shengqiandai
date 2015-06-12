<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>还款计划列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/week/repaymentWeekList.js"></script>
<c:if test="${canRepay==true }">
	<script type="text/javascript">
		$.canRepay = true;
	</script>
</c:if>
<style>
.datagrid-row-over, .datagrid-header td.datagrid-header-over {
	background: #eaf2ff;
	color: #000000;
	cursor: default;
}

.datagrid-row-selected {
	background: #eaf2ff;
	color: #000000;
}
/*
顶部操作栏样式
*/
.top1,.top2{
margin:10px;
}
.s1{
margin-left:0px;
}
.s2{
margin-left:20px;
color:#FF0000;
}
.s3{
margin-left:20px;
}
.s4{
margin-left:20px;
color:#0000FF;
}
.recharge{
margin-left:30px;
width: 50px;
display: inline-block;
border-radius:5px;
text-align: center;
vertical-align:middle;
padding: 3px 10px;
line-height: 24px;
background: #0094D5;
height: 24px;
color: #ffffff;
font-size: 16px;
cursor: pointer;
}
</style>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<div id="list"></div>
	</div>
	<div id="searchTool" style="display: none;">
		<div class="top1">
			<span class="s1">今日日期:${today}</span>
			<span class="s2">今日待还款总金额:${todayWeekRepayment==null?"0.00":todayWeekRepayment.repaymentAccount}元</span>
			<span class="s3">明日待还款总金额:${tomorrowWeekRepayment==null?"0.00":tomorrowWeekRepayment.repaymentAccount}元</span>
			<span class="s3">未来七天待还款总金额:${futureWeekRepayment==null?"0.00":futureWeekRepayment.repaymentAccount}元</span>
			<span class="s4">绑定的前台账户:${user==null?userMsg:user.username}</span>
			<span class="s4">账户可用余额:${account==null?accountMsg:account.useMoney}元</span>
			<div id="recharge" class="recharge">充值</div>
		</div>
		<div class="top2">
			<span class="s1">计划编号:<input type="text" id="weekId" name="weekId" style="width: 120px; height: 12px; margin: 0;"/></span>
			<span class="s3">
				还款状态:		
				<select id="status" name="status" style="width: 120px; height: 28px;">
					<option value="">全部</option>
					<option value="0">待还款</option>
					<option value="1">已还款</option>
				</select>
			</span> 
			<span class="s3">
				还款时间:<input type="text" id="startTime" name="saleTime" readonly="readonly" onClick="WdatePicker()" size="10" style="width:100px;cursor:pointer;background-color:#fff;"
					value="${today}"
					/>
			</span> 
			<span class="s3">
				到期时间:<input type="text" id="endTime" name="expireTime" readonly="readonly" onClick="WdatePicker()" size="10" style="width:100px;cursor:pointer;background-color:#fff;"
				value="${future}"/>
			</span> 
			<span class="s3">
				<a href="javascript:queryDetail();" class="easyui-linkbutton" iconCls="icon-search" id="searchButton">查询</a>
			</span> 
		</div>
	</div>	
</body>
</html>