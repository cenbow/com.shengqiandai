<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>预览提交</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
</head>
<body>
	<form id="subTrial" style="width: 100%; text-align: center;">
		<div style="width: 950px;">
			<div style="border:2px #00B2EE solid;text-align: center; float: left; width: auto;"
			class="usermsg">
			<h3
				style="margin:0;;background-color: #00B2EE; height: 30px; color: #ffffff; line-height: 30px;">计划详情</h3>
			<ul style="width: 100%; margin: 0 auto;">
					<li
						style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
						计划编号：${week.id}</li>
					<li
						style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
						标题：${week.name}</li>
					<li
						style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
						状态： <c:choose>
							<c:when test="${week.status==0}">
					未发布
					</c:when>
							<c:when test="${week.status==1}">
					待审核
					</c:when>
							<c:when test="${week.status==2}">
					审核失败
					</c:when>
							<c:when test="${week.status==3}">
					审核成功
					</c:when>
						</c:choose>
					</li>
					<li
						style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
						实际金额：${week.realityMoney}元</li>
					<li
						style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
						预期计划金额：${week.planMoney}元</li>
					<li
						style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
						计划期限：7天</li>
					<li
						style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
						利率：${week.apr}%</li>
					<li
					style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					服务费率：${week.wr.platformRate}%</li>
					<li
					style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					担保费率：${week.wr.guaranteeRate}%</li>
					<li
						style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
						创建时间：<fmt:formatDate value="${week.createTime}"
							pattern="yyyy-MM-dd" />
					</li>
					<li
						style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
						每份价值：${week.sharePrice}元</li>
					<li
						style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
						个人最小投资份数：${week.singleMin}份</li>
					<li
						style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
						个人最大投资份数：${week.singleMax}份</li>
					<li
						style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
						发售开始时间：<fmt:formatDate value="${week.saleTime}"
							pattern="yyyy-MM-dd" />
					</li>
					<li
						style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
						发售截止时间：<fmt:formatDate value="${week.expireTime}"
							pattern="yyyy-MM-dd" />
					</li>
				</ul>
				<div style="text-align: center; float: left; width: 100%;"
					class="usermsg">
					<span><input type="radio" name="saveStatus" value="3"
						${week.saveStatus == 3?'checked' : ''}
						onclick="showHiddenContext(this,${week.id})" />审核通过</span> <span><input
						type="radio" name="saveStatus" value="2"
						${week.saveStatus == 2?'checked' : ''}
						onclick="showHiddenContext(this,${week.id})" />审核不通过</span>
					<div id="weekDiv${week.id}"
						style="text-align:center;display: ${week.saveStatus == 2?'block' : 'none'};width:100%;">
						审核备注：
						<textarea style="resize: none; width: 80%; height: 70px;"
							id="weekRemark" name="context">${week.verifyRemark}</textarea>
					</div>
					<input type="hidden" name="verifyRemark" /> <input type="hidden"
						name="id" value="${week.id}" />
				</div>
			</div>

			<div
			style="text-align: right; float: right; width: 13%;position: fixed;margin-left:-13%;"
			id="borrowDetilTable" class="usermsg">
			<table id="borrowList" style="width: 100%; border: 1px black solid; background-color: white;">
				<thead>
					<tr>
						<th style="width: 100%; border: 1px black solid;">标的名称</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="borrow" items="${week.weekBorrowList}">
						<tr>
							<td style="width: 100%; border: 1px black solid;">
								<a href="#borrow_${borrow.id}" style="${borrow.status==3?'color:#008B00;':'color:red;'}">${borrow.name}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

			<c:forEach var="borrow" items="${week.weekBorrowList}" varStatus="vs">
				<div style="margin:5px 0px 0px 0px;padding:0;border:3px #008B00 solid;text-align: center; float: left; width: 100%;"
				class="usermsg">
				<h3 id="borrow_${borrow.id}"
					style="margin:0; background-color:${borrow.status==3?'#008B00':'red'}; height: 30px; color: #ffffff; line-height: 30px;">${borrow.name}(<c:choose>
									<c:when test="${borrow.status==0}">
										未审核
									</c:when>
									<c:when test="${borrow.status==1}">
										待审核
									</c:when>
									<c:when test="${borrow.status==2}">
										审核失败
									</c:when>
									<c:when test="${borrow.status==3}">
										审核成功
									</c:when>
								</c:choose>)</h3>
					<ul style="width: 920px; margin: 0 auto;">
						<li
							style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
							借款总金额：${borrow.account}</li>
						<li
							style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
							年利率：${borrow.apr}%</li>
						<li
							style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
							状态： <c:choose>
								<c:when test="${borrow.status==0}">
					未审核
					</c:when>
								<c:when test="${borrow.status==1}">
					待审核
					</c:when>
								<c:when test="${borrow.status==2}">
					审核失败
					</c:when>
								<c:when test="${borrow.status==3}">
					审核成功
					</c:when>
							</c:choose>
						</li>
						<li
							style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
							期限：${borrow.timeLimit}</li>
						<li
							style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
							标的品种：<c:choose>
								<c:when test="${borrow.type==1}">
					抵押标的
					</c:when>
								<c:when test="${borrow.type==2}">
					债券转让
					</c:when>
							</c:choose>
						</li>
						<li
							style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
							合同编号：${borrow.contractNumber}</li>
						<li
							style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
							合同起始时间：<fmt:formatDate value="${borrow.contractStart}"
								pattern="yyyy-MM-dd" />
						</li>
						<li
							style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
							合同结束时间：<fmt:formatDate value="${borrow.contractEnd}"
								pattern="yyyy-MM-dd" />
						</li>
					</ul>

					<div style="text-align: center; float: left; width: 100%;"
						class="usermsg">
						<h4 style="text-align:left; height: 30px; color: black; line-height: 30px;">抵押物信息</h4>
						<ul style="width: 100%; margin: 0 auto;">
							<li
								style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
								车主姓名：${borrow.weekBorrowPawn.ownerName}</li>
							<li
								style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
								车主身份证号码：${borrow.weekBorrowPawn.ownerCardid}</li>
							<li
								style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
								车主手机号码：${borrow.weekBorrowPawn.ownerMobile}</li>
							<li
								style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
								汽车品牌：${borrow.weekBorrowPawn.brand}</li>
							<li
								style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
								汽车评估价格：${borrow.weekBorrowPawn.assessMoney}</li>
						</ul>
					</div>
					<div style="text-align: center; float: left; width: 100%;"
						class="usermsg">
						<h4
							style="text-align:left; height: 30px; color: black; line-height: 30px;">图片</h4>
						<c:if
						test="${fn:length(borrow.weekBorrowPawn.weekBorrowPawnPicList) == 0}">
						<ul style="width: 100%; margin: 0 auto; text-align: center;">
							<h4>暂无图片</h4>
						</ul>
					</c:if>
					<c:if
						test="${fn:length(borrow.weekBorrowPawn.weekBorrowPawnPicList) > 0}">
						<ul style="width: 100%; margin: 0 auto; text-align: center;">
							<c:forEach var="pic"
								items="${borrow.weekBorrowPawn.weekBorrowPawnPicList}">
								<li
									style="text-align: center; width: 48%; float: left; list-style-type: none; padding: 1% 1%; border-bottom: 1px solid #DEDEDE;">
									<img src="${pic.pic}" width="80%">
								</li>
							</c:forEach>
						</ul>
					</c:if>
					</div>
					<div style="text-align: center; float: left; width: 100%;"
					class="usermsg">
					<span><input type="radio" name="wbs[${vs.index}].saveStatus"
						value="3" ${borrow.saveStatus == 3?'checked':''}
						onclick="showHiddenContext(this)" />审核通过</span> <span><input
						type="radio" name="wbs[${vs.index}].saveStatus" value="2"
						${borrow.saveStatus == 2?'checked':''}
						onclick="showHiddenContext(this)" />审核不通过</span>
					<div
						style="text-align:center;display: ${borrow.saveStatus == 2?'block' : 'none'};width:100%;">
						审核备注：
						<textarea style="resize: none; width: 80%; height: 70px;"
							name="context">${borrow.verifyRemark}</textarea>
					</div>
					<input type="hidden" name="wbs[${vs.index}].verifyRemark" /> <input
						type="hidden" value="${borrow.id}" name="wbs[${vs.index}].id" />
				</div>
				</div>
			</c:forEach>
			<c:if test="${week.status == 1}">
				<div style="text-align: center; float: left; width: 100%;"
					class="usermsg">
					<input type="hidden" name="trialStatus" id="trialStatus" /> <input type="button"
						id="save" name="subBtn" value="保存此信息" />
					<input type="button" name="subBtn" id="back" value="打回计划"  style="display: none;"/>
					<input type="button" name="subBtn" id="ok" value="发布计划" style="display: none;" />
				</div>
			</c:if>
		</div>
	</form>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/week/trialWeek.js"></script>
	<script type="text/javascript">
	
	</script>
</body>
</html>