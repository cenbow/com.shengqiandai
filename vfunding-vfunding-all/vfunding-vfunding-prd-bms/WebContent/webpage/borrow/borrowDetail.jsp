<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>复审列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
</head>
<body>
<div style="width:950px;">
	<div style="text-align:center;float:left;width:auto;" class="usermsg">
		<h3 style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;">一、用户信息</h3>
		<ul style="width:920px;margin:0 auto;">
			<li style="text-align:center;width:310px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			用户名：${user.username}</li>
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			标题:${borrow.name}</li>
			<li style="text-align:center;width:310px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			还款方式:
				<c:choose>
					<c:when test="${borrow.style==0}">
					等额本息
				</c:when>
					<c:when test="${borrow.style==1}">
					等本等息
				</c:when>
					<c:when test="${borrow.style==2}">
					到期还款
				</c:when>
					<c:when test="${borrow.style==3}">
					按月付息
				</c:when>
				</c:choose>
			</li>
			<li style="text-align:center;width:310px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			借款金额:${borrow.account}元</li>
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			 已借到款:<span style="color:red;">${borrow.accountYes}</span>元</li>
			<li style="text-align:center;width:310px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			年利率:${borrow.apr }%</li>
			<li style="text-align:center;width:310px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;"> 
			服务费: ${fee.bfee }%</li>
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;"> 
			 担保费: ${fee.gfee}%</li>
			<li style="text-align:center;width:310px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			净收益率:<span style="color:red;">${borrow.apr-fee.bfee-fee.gfee}</span>%</li>
			<li style="text-align:center;width:310px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			借款期限:${borrow.timeLimit}个月</li>
			<li style="text-align:center;width:310px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			借款用途:
				${empty borrowAll.use?'短期周转':(borrowAll.use==1?'短期周转':
					(borrowAll.use==2?'生意周转':(borrowAll.use==3?'生活周转':
					(borrowAll.use==4?'购物消费':(borrowAll.use==5?'不提现借款':(borrowAll.use==6?'其它借款':'创业借款'))))))}
			</li>
			
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			&nbsp;</li>
		</ul>
	</div>
	<div style="text-align:center;float:left;padding-top:auto;">
		<h3 style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;">二、投资信息</h3>
		<ul style="width:950px;margin:0 auto;">
			<li style="text-align:center;width:50px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			&nbsp;</li>
			<li style="text-align:center;width:130px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			序号：</li>
			<li style="text-align:center;width:130px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			用户名称</li>
			<li style="text-align:center;width:130px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			投资金额</li>
			<li style="text-align:center;width:130px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			有效金额</li>
			<li style="text-align:center;width:130px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			状态</li>
			<li style="text-align:center;width:250px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			投标时间</li>
		</ul>
		<c:forEach items="${tenderList}" var="v" varStatus="vs">
		<ul style="width:950px;margin:0 auto;">
			<li style="text-align:center;width:50px;float:left;height:10px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			&nbsp;</li>
			<li style="text-align:center;width:130px;float:left;height:10px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			${vs.index+1}</li>
			<li style="text-align:center;width:130px;float:left;height:10px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			${v.username}</li>
			<li style="text-align:center;width:130px;float:left;height:10px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			${v.money}元</li>
			<li style="text-align:center;width:130px;float:left;height:10px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			${v.account}元</li>
			<li style="text-align:center;width:130px;float:left;height:10px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
				<c:if test="${v.status==5}">待审</c:if>
				<c:if test="${v.status==2}">撤标</c:if>
				<c:if test="${v.status==1}">成功</c:if></li>
			<li style="text-align:center;width:250px;float:left;height:10px;list-style-type:none;padding:10px 0;border-bottom:1px solid #DEDEDE;">
			${v.tendertime}</li>
		</ul>
		</c:forEach>
	</div>
	<%-- <div style="text-align:center;padding-top:220px;">
		<h3>三、还款明细</h3>
		<table>
			<tr>
				<th>序号</th>
				<th>计划还款日</th>
				<th>预还金额</th>
				<th>本金</th>
				<th>利息</th>
				<th>平台服务费</th>
				<th>担保服务费</th>
			</tr>
			<tr>
				<td>1</td>
				<td>2014-01-01</td>
				<td>${borrow.repaymentAccount }</td>
				<td>${borrow.account}</td>
				<td>${borrow.repaymentAccount-borrow.account}</td>
				<td>${fee.bfee }%</td>
				<td>${fee.gfee }%</td>
			</tr>
		</table>
	</div> --%>
	<div style="text-align:center;padding-top:auto;float:left;width:950px;">
		<h3 style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;">三、车主信息</h3>
		<ul style="width:920px;margin:0 auto;">
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			车主姓名：${mortgageCar.ownerName}</li>
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			车主身份证：${mortgageCar.ownerCardid}</li>
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			车主手机号：${mortgageCar.ownerMobile }</li>
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			车主籍贯：${mortgageCar.ownerAddress }</li>
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			车牌号：${mortgageCar.carLicense}</li>
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			车架号：${mortgageCar.carStrutNum}</li>
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			发动机号：${mortgageCar.carNumber} </li>
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			 注册日期::<fmt:formatDate value="${mortgageCar.registerDate}" pattern="yyyy-MM-dd"/></li>
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			发证日期：<fmt:formatDate value="${mortgageCar.certificationDate}" pattern="yyyy-MM-dd"/></li>
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			检验有效期:<fmt:formatDate value="${mortgageCar.checkValidDate}" pattern="yyyy-MM-dd"/></li>
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			&nbsp;</li>
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			&nbsp;</li>
		</ul>
	</div>
	<div style="text-align:center;padding-top:auto;float:left;width:950px;">
		<h3 style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;">四、其他详细内容</h3>
		<ul style="width:920px;margin:0 auto;">
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			投标奖励:${borrow.award}</li>
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			添加时间:${borrow.addTimeStr1 }</li>
			<li style="text-align:center;width:300px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			招标时间:${borrow.verifyTimeStr1}</li>
			<li style="text-align:center;width:900px;float:left;height:12px;list-style-type:none;padding:12px 0;border-bottom:1px solid #DEDEDE;">
			内容：${borrow.content}</li>
		</ul>
	</div>
	<div style="text-align:center;padding-top:auto;float:left;width:950px;">
		<h3 style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;">五、汽车照片</h3>
		<ul style="width:920px;margin:0 auto;">
			<c:forEach items="${listPic}" var="v">
				<li style="text-align:center;width:440px;float:left;list-style-type:none;padding:10px 5px;border-bottom:1px solid #DEDEDE;">
					<img src="${v.pic}" style="width: 440px; height: 500px;" />
				</li>
			</c:forEach>
		</ul>
	</div>
	<div style="text-align:center;padding-top:auto;float:left;width:950px;">
	<h3 style="background-color:#0081c2;height:30px;color:#ffffff;line-height:30px;">六、复审审核</h3>
	<form id="finalForm" name="finalForm">
		<div>
			<input type="hidden" id="borrowId" name="borrowId"
				value="${borrow.id }" /> <br />状态:<input type="radio"
				checked="checked" value="3" name="status"> 复审通过<input
				type="radio" value="4" name="status"> 复审不通过 <br /> 审核备注:
			<textarea rows="3" cols="90" id="remark" name="remark" style="margin: 0px 0px 10px; width: 700px; height: 64px;"></textarea>
			<br /> <input type="button" value="审核此借款标" name="sub" id="subfrom">
		</div>
	</form>
	<br/>
	<br/>
	</div>
</div>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/borrow/borrowDetail.js"></script>
</body>
</html>