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
	yest2.add(Calendar.DAY_OF_YEAR, -1);
	String sDate = fmat.format(yest2.getTime());

	Calendar yest = Calendar.getInstance();
	yest.add(Calendar.DAY_OF_YEAR, -1);
	String eDate = fmat.format(yest.getTime());
%>
<!DOCTYPE html>
<head>
<title>数据显示</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/operate/reportdaysList.js"></script>


<c:if test="${canChange==true }">
	<script type="text/javascript">
		$.canChange = true;
		$.canToGrant = true;
		$.canEdit = true;
	</script>
</c:if>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">


	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'center',border:false"
			style="overflow: hidden;">
			<div id="timeTool">
			<table>
				<tr>
					<td>起始时间:<input id="startTime" type="text"
						onClick="WdatePicker()" class="Wdate" editable='false'
						value="<%=sDate%>" /> 截至时间:<input id="endTime" type="text"
						onClick="WdatePicker()" class="Wdate" value="<%=eDate%>"
						editable='false' />
					</td>
					<td width="50%"><a href="javascript:queryDetail();"
						class="easyui-linkbutton" iconCls="icon-search" id="searchButton">查询</a>
						<a href="javascript:exportExcel();" class="easyui-linkbutton"
						iconCls="icon-undo">导出</a>
				</tr>
			</table>
			</div>
			<div id="reportdatagrid"></div>
		</div>
	</div>
</body>
