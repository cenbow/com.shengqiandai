<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd");
	//	Date date = new Date();
	//	String sDate  = fmat.format(date);
	//	sDate = sDate + " 00:00:00";
	Calendar yest2 = Calendar.getInstance();
	yest2.add(Calendar.MONTH, -1);
	yest2.set(Calendar.DAY_OF_MONTH, 1);
	String sDate = fmat.format(yest2.getTime());

	Calendar yest = Calendar.getInstance();
	yest.set(Calendar.DAY_OF_MONTH, 0);
	String eDate = fmat.format(yest.getTime());
%>
<!DOCTYPE html>
<html>
<head>
<title>数据显示</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/finance/tenderList.js"></script>


<c:if test="${canChange==true }">
	<script type="text/javascript">
		$.canChange = true;
		$.canToGrant = true;
		$.canEdit = true;
	</script>
</c:if>
</head>

<body>
	<div id="timeTool">
		<table>
			<tr>
				<td>
					<select id="searchType">
						<option value="1" >按照投标时间</option>
						<option value="2" selected="selected">按照回款时间</option>
				 	</select>起始时间:<input id="startTime" type="text"
					onClick="WdatePicker()" class="Wdate" editable='false'
					value="<%=sDate%>" /> 截止时间:<input id="endTime" type="text"
					onClick="WdatePicker()" class="Wdate" value="<%=eDate%>"
					editable='false' /> <a href="javascript:queryDetail();"
					class="easyui-linkbutton" iconCls="icon-search" id="searchButton">查询</a>
					<a href="javascript:exportExcel();" class="easyui-linkbutton"
					iconCls="icon-undo">导出</a>&nbsp; &nbsp; </td>
			</tr>
			<!-- 
			<tr>
				<td>投标起始时间:<input id="startTime1" type="text"
					onClick="WdatePicker()" class="Wdate" editable='false'
					value="<%=sDate%>" /> 投标截止时间:<input id="endTime1" type="text"
					onClick="WdatePicker()" class="Wdate" value="<%=eDate%>"
					editable='false' /> <a href="javascript:queryDetail();"
					class="easyui-linkbutton" iconCls="icon-search" id="searchButton">查询</a>
					<a href="javascript:exportExcel();" class="easyui-linkbutton"
					iconCls="icon-undo">导出</a>&nbsp; &nbsp; </td>
			</tr>
			 -->
			<tr>
				<td>
				<span id="spanSearchType2" style="display: none;">
				<b style="color: red;">平台服务费总计：<span
						id="sumServiceFees"></span> &nbsp; &nbsp; 担保服务费总计：<span
						id="sumGuaranteeFees"></span></b>&nbsp; &nbsp;
				</span>
				
				<span id="spanSearchType1" style="display: none;">
				<b style="color: red;">待收平台服务费总计：<span
						id="sumWaitServiceFees"></span> &nbsp; &nbsp; 待收担保服务费总计：<span
						id="sumWaitGuaranteeFees"></span></b>
				
				</span>
				</td>
			</tr>
		</table>
	</div>
	<div id="reportdatagrid"></div>

</body>
</html>