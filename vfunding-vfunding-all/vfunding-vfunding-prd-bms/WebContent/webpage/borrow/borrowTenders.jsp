<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<style>
tr{
	border-bottom:1px solid #DEDEDE;
}
td{
	border-bottom:1px solid #DEDEDE;
}
</style>
<body>
<div style="margin:0 auto;">
	<table width="790" classs="table_">
		<tr>
			<td width="80" align="center" style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;font-size:16px;font-weight:bold;">序号</td>
			<td width="138" height="60" align="center" style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;font-size:16px;font-weight:bold;">投标人</td>
			<td width="183" align="center" style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;font-size:16px;font-weight:bold;">投标金额</td>
			<td width="217" align="center" style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;font-size:16px;font-weight:bold;">有效金额</td>
			<td width="200" align="center" style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;font-size:16px;font-weight:bold;">投标时间</td>
			<td width="133" align="center" style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;font-size:16px;font-weight:bold;">状态</td>
		</tr>
		<c:if test="${empty tenderList}">
			<tr>
				<td colspan="6" height="48">暂无投资记录</td>
			</tr>
		</c:if>
		<c:forEach var="tenderList" items="${tenderList }" varStatus="vs">
			<tr>
				<td width="80" align="center" style="border-bottom:1px solid #DEDEDE;">${vs.index+1 }</td>
				<td width="138" height="30" align="center" style="border-bottom:1px solid #DEDEDE;">${tenderList.allUsername }</td>
				<td width="183" align="center" style="border-bottom:1px solid #DEDEDE;">${tenderList.money }元</td>
				<td width="217" align="center" style="border-bottom:1px solid #DEDEDE;">${tenderList.account }元</td>
				<td width="200" align="center" style="border-bottom:1px solid #DEDEDE;">${tenderList.tendertime }</td>
				<td width="133" align="center" style="border-bottom:1px solid #DEDEDE;">
				<c:choose>
					<c:when test="${tenderList.status ==5 || tenderList.status ==50}">
					投资待审
					</c:when>
					<c:when test="${(tenderList.status ==1 || tenderList.status ==10) && tenderList.money-tenderList.account==0 }">
					投资成功
					</c:when>
					<c:when test="${ (tenderList.status ==1 || tenderList.status ==10)&& tenderList.money-tenderList.account>0 }">
					部分投资
					</c:when>
					<c:when test="${tenderList.status ==20}">
					投资失败
					</c:when>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>