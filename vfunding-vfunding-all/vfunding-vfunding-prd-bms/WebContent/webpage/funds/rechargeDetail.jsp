<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>初审列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
</head>
<body>
<div style="width:950px;">
	<div style="text-align:center;float:left;width:auto;" class="usermsg">
		<h3 style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;">一、充值审核/查看</h3>
		<ul style="width:920px;margin:0 auto;">
			<li style="text-align:center;width:450px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				用户名：${rechargeList.userName }
			</li>
			<li style="text-align:center;width:450px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				用户名：${rechargeList.userName }
			</li>			
			<li style="text-align:center;width:450px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				充值银行：${rechargeList.bank==null?'后台充值':rechargeList.bank}
			</li>			
			<li style="text-align:center;width:450px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				体现总额：${rechargeList.money}元
			</li>			
			<li style="text-align:center;width:450px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				到账金额：${rechargeList.money-rechargeList.fee}元
			</li>			
			<li style="text-align:center;width:450px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				手续费：${rechargeList.fee }元
			</li>			
			<li style="text-align:center;width:450px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				状态：
				<c:if test="${rechargeList.status==0}">充值待审</c:if>
				<c:if test="${rechargeList.status==1}">处理成功</c:if>
				<c:if test="${rechargeList.status==2||rechargeList.status==3}">处理失败</c:if>
			</li>			
			<li style="text-align:center;width:450px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				添加时间:${rechargeList.addtime }
			</li>
		</ul>
	</div>
	<div style="text-align:center;float:left;width:950px;" class="usermsg">
		<h3 style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;">二、审核此充值信息</h3>
		<form id="rechargeForm" name="rechargeForm">
			<input type="hidden" id="id" name="id" value="${rechargeList.rechargeId }"/>
			状态:
			<input type="radio" value="1" name="status">通过
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" value="0" name="status">不通过
			<br/>
			<br/>
			到账金额：<input type="text" id="money" value="${rechargeList.money-rechargeList.fee}" readonly="readonly"/>
			手续费：<input type="text" id="" value="${rechargeList.fee}" readonly="readonly"/>
			<br/>
			<br/>
			<c:if test="${rechargeList.bank == '支付宝'}">
				<input type="hidden" id="bankType" value="${rechargeList.bank }"/> 
				<div id="aliNum" style="display:none;margin-right: 225px;">
				支付宝充值流水号：<input type="text" id="msg" name="msg" style="width:300px"/>
				<br/>
				<br/>
				</div>
			</c:if>
			审核备注:
			<textarea rows="3" cols="90" id="remark" name="remark" style="margin: 0px 0px 10px; width: 700px; height: 64px;"></textarea>
			<br/>
			<br/>
			<input type="button" value="审核此充值信息" name="sub" id="subfrom">
		</form>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/funds/rechargeDetail.js"></script>
</body>
</html>