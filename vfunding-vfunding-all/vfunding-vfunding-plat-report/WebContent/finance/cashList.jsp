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
	src="${pageContext.request.contextPath}/js/finance/cashList.js"></script>
<c:if test="${canChange==true }">
	<script type="text/javascript">
		$.canChange = true;
		$.canToGrant = true;
		$.canEdit = true;
	</script>
</c:if>
</head>

<body class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<div id="timeTool">
			<div>
				<table>
					<tr>
						<td>起始时间:<input id="startTime" type="text"
							onClick="WdatePicker()" class="Wdate" editable='false'
							value="<%=sDate%>" /> 截至时间:<input id="endTime" type="text"
							onClick="WdatePicker()" class="Wdate" value="<%=eDate%>"
							editable='false' /> <a href="javascript:queryDetail();"
							class="easyui-linkbutton" iconCls="icon-search" id="searchButton">查询</a>
							<a href="javascript:exportExcel();" class="easyui-linkbutton"
							iconCls="icon-undo">导出</a> &nbsp; &nbsp; <b style="color: red;">提现手续费总计：<span
								id="sumCashFees"></span> &nbsp; &nbsp; 提现所使用的红包总计：<span
								id="sumHongbao"></span></b></td>

					</tr>
				</table>
			</div>
		</div>
		<div id="reportdatagrid"></div>
	</div>
</body>
</html>