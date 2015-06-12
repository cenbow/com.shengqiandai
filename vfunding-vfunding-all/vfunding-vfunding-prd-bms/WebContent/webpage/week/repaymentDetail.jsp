<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>还款详情</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
</head>
<body>
<style>
.previewImg{
width:180px;
height:180px;
}
</style>
<!-- easyui modalDialog装载必须放在body里 不然加载不到相关资源 -->
<!-- 当前jsp需要的其他js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/week/repaymentDetail.js"></script>
<!-- 标的新增或编辑页面 -->
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow-x: hidden;">
		<form id="addoreditform" method="post" style="text-align: center;">
			<h3	style="background-color: #0081c2; height: 30px; color: #ffffff; line-height: 30px;">一、还款信息</h3>
			<input type="hidden" value="${weekRepayment.id}" id="repaymentId" name="repaymentId"/>
			<input type="hidden" value="${weekRepayment.repaymentTime}" id="repaymentTime" name="repaymentTime"/>
			<table class="table table-hover table-condensed" style="text-align: center; ">
				<!-- 第0行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">计划名称:</th>
					<td style="vertical-align: middle;">
						<span>${week.name}</span>
					</td>
					<th></th>
					<td></td>
				</tr>
				<!-- 第一行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">还款金额:</th>
					<td style="vertical-align: middle;">
						<span>${weekRepayment.repaymentAccount}元</span>
					</td>
					<th></th>
					<td></td>
				</tr>
				<!-- 第二行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">当前绑定账户:</th>
					<td style="vertical-align: middle;">
						<span>${user.username}</span>
					</td>
					<th></th>
					<td></td>
				</tr>
				<!-- 第二行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">当前账户余额:</th>
					<td style="vertical-align: middle;">
						<span>${account==null?accountMsg:account.useMoney}</span>
					</td>
					<th></th>
					<td></td>
				</tr>
				<!-- 第三行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">指定其他账户付款:</th>
					<td style="vertical-align: middle;">
						<select id="repaymentUser" name="repaymentUser" style="width: 120px; height: 28px;">
							<option value=""></option>
							<option value="20">vf1010</option>					
						</select> 
					</td>
					<th></th>
					<td></td>
				</tr>
				<!-- 第四行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">备注:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="repaymentMemo" name="repaymentMemo"
						data-options="validType:['String','length[0,16]']"
						class="easyui-validatebox" 
						placeholder="请输入备注"
						value="" />
					</td>
					<th></th>
					<td></td>
				</tr>
			</table>
		</form>		
		
		<div align="center" style="padding: 20px;">
			<input type="button" value="还款" id="subBtn"/>
			<input type="button" value="关闭"   id="clsBtn" style="margin-left:50px;"/>
		</div>
	</div>
</div>
</body>
</html>