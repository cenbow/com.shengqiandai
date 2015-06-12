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
	Calendar calendar1 = Calendar.getInstance();
	Calendar calendar2 = Calendar.getInstance();
	int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
	int offset1 = 1 - dayOfWeek;
	int offset2 = 7 - dayOfWeek;
	calendar1.add(Calendar.DATE, offset1 - 7);
	calendar2.add(Calendar.DATE, offset2 - 7);
	String sDate = fmat.format(calendar1.getTime());
	String eDate = fmat.format(calendar2.getTime());
%>
<!DOCTYPE html>
<html>
<head>
<title>数据显示</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/operate/reportdaysTwoList.js"></script>


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
	<div id="reportdatagrid" style="width: 100; height: 500px;"></div>

</body>
</html>