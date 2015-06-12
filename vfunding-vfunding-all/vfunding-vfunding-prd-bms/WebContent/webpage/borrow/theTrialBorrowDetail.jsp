<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>初审列表</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
</head>
<body>
	<div style="width: 450px;">
		<div style="text-align: center; float: left; width: auto;"
			class="usermsg">
			<h3
				style="background-color: #0081c2; height: 30px; color: #ffffff; line-height: 30px;">一、用户信息</h3>
			<ul style="width: 920px; margin: 0 auto;">
				<li
					style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					用户名：${user.username}</li>
				<li
					style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					标题：${borrow.name}</li>
				<li
					style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					状态： <c:choose>
						<c:when test="${borrowAll.status==1}">
					初审通过
				</c:when>
						<c:otherwise>
					发布审批中
				</c:otherwise>
					</c:choose>
				</li>
				<li
					style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					借款用途：${empty borrowAll.use?'短期周转':(borrowAll.use==1?'短期周转':
				(borrowAll.use==2?'生意周转':(borrowAll.use==3?'生活周转':
				(borrowAll.use==4?'购物消费':(borrowAll.use==5?'不提现借款':(borrowAll.use==6?'其它借款':'创业借款'))))))}
				</li>
				<li
					style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					借款期限：<c:choose>
						<c:when test="${borrowAll.timeLimit>0}">
						${borrow.timeLimit}个月
					</c:when>
						<c:otherwise>
						${borrow.timeLimitDay}天
				</c:otherwise>
					</c:choose>
				</li>
				<li
					style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					还款方式： <c:choose>
						<c:when test="${borrowAll.style==0}">
					等额本息
				</c:when>
						<c:when test="${borrowAll.style==1}">
					等本等息
				</c:when>
						<c:when test="${borrowAll.style==2}">
					到期还款
				</c:when>
						<c:when test="${borrowAll.style==3}">
					按月付息
				</c:when>
					</c:choose>
				</li>
				<li
					style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					借款金额：${borrow.account}</li>
				<li
					style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					年利率：${borrow.apr }</li>
				<li
					style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					最低投标金额：${borrowAll.lowestAccount }元</li>
				<li
					style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					最多投标总额： <c:choose>
						<c:when test="${borrowAll.mostAccount>0}">
					${borrowAll.mostAccount }
			</c:when>
						<c:otherwise>
				无限制
			</c:otherwise>
					</c:choose>
				</li>

				<li
					style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					抵押类型： <c:if test="${borrow.mortgageTypeid==2}">债权抵押</c:if> <c:if
						test="${borrow.mortgageTypeid==1}">汽车抵押</c:if>
				</li>
				<c:if test="${borrow.mortgageTypeid == 2}">
					<li
						style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
						合同开始时间：<fmt:formatDate value="${borrow.contractStart}"
							pattern="yyyy-MM-dd" />
					</li>
					<li
						style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
						合同结束时间：<fmt:formatDate value="${borrow.contractEnd}"
							pattern="yyyy-MM-dd" />
					</li>
				</c:if>


			</ul>
		</div>

		<div style="text-align: center; float: left; width: auto;"
			class="usermsg">
			<h3
				style="background-color: #0081c2; height: 30px; color: #ffffff; line-height: 30px;">二、车主信息</h3>
			<ul style="width: 920px; margin: 0 auto;">
				<li
					style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					车主姓名：${mortgageCar.ownerName}</li>
				<li
					style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					车主身份证：${mortgageCar.ownerCardid}</li>
				<li
					style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					车主籍贯：${mortgageCar.ownerAddress }</li>
				<li
					style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					车牌号：${mortgageCar.carLicense}</li>
				<li
					style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					车架号：${mortgageCar.carStrutNum}</li>
				<li
					style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					发动机号：${mortgageCar.carNumber}</li>
				<li
					style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					注册日期：<fmt:formatDate value="${mortgageCar.registerDate}"
						pattern="yyyy-MM-dd" />
				</li>
				<li
					style="text-align: center; width: 300px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					发证日期：<fmt:formatDate value="${mortgageCar.certificationDate}"
						pattern="yyyy-MM-dd" />
				</li>
				<li
					style="text-align: center; width: 310px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					检验有效期：<fmt:formatDate value="${mortgageCar.checkValidDate}"
						pattern="yyyy-MM-dd" />
				</li>
				<li
					style="text-align: center; width: 900px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					内容：${borrow.content}</li>
			</ul>
		</div>

		<div style="text-align: center; float: left; width: 955px;"
			class="usermsg">
			<h3
				style="background-color: #0081c2; height: 30px; color: #ffffff; line-height: 30px;">三、汽车照片</h3>
			<ul style="width: 920px; margin: 0 auto;">
				<c:forEach items="${listPic}" var="v">
					<li
						style="text-align: center; width: 440px; float: left; list-style-type: none; padding: 10px 5px; border-bottom: 1px solid #DEDEDE;">
						<h4>[${v.type==0?"身份证正面":(v.type==1?"身份证反面":(v.type==2?"车的详细图片":(v.type==3?"行驶证":"其他")))}]</h4>
						<img src="${v.pic}" style="width: 440px; height: 500px;" />
					</li>
				</c:forEach>
			</ul>
		</div>

		<div style="text-align: center; float: left; width: 950px;"
			class="usermsg">
			<h3
				style="background-color: #0081c2; height: 30px; color: #ffffff; line-height: 30px;">四、资金状态</h3>
			<ul style="width: 920px; margin: 0 auto;">
				<li
					style="text-align: center; width: 250px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					公开我的帐户资金情况：</li>
				<li
					style="text-align: left; width: 670px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					<c:choose>
						<c:when test="${borrowAll.openAccount==1}">
							<input type="checkbox" checked="checked" disabled="disabled">
						</c:when>
						<c:otherwise>
							<input type="checkbox" disabled="disabled">
						</c:otherwise>
					</c:choose> 如果您勾上此选项，将会实时公开您帐户的：账户总额、可用余额、冻结总额。
				</li>
				<li
					style="text-align: center; width: 250px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					公开我的借款资金情况：</li>
				<li
					style="text-align: left; width: 670px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					<c:choose>
						<c:when test="${borrowAll.openBorrow==1}">
							<input type="checkbox" checked="checked" disabled="disabled">
						</c:when>
						<c:otherwise>
							<input type="checkbox" disabled="disabled">
						</c:otherwise>
					</c:choose> 如果您勾上此选项，将会实时公开您帐户的：借款总额、已还款总额、未还款总额、迟还总额、逾期总额。
				</li>

				<li
					style="text-align: center; width: 250px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					公开我的投标资金情况：</li>
				<li
					style="text-align: left; width: 670px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					<c:choose>
						<c:when test="${borrowAll.openTender==1}">
							<input type="checkbox" checked="checked" disabled="disabled">
						</c:when>
						<c:otherwise>
							<input type="checkbox" disabled="disabled">
						</c:otherwise>
					</c:choose> 如果您勾上此选项，将会实时公开您帐户的：投标总额、已收回总额、待收回总额。
				</li>

				<li
					style="text-align: center; width: 250px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					公开我的信用额度情况：</li>
				<li
					style="text-align: left; width: 670px; float: left; height: 12px; list-style-type: none; padding: 10px 0; border-bottom: 1px solid #DEDEDE;">
					<c:choose>
						<c:when test="${borrowAll.openCredit==1}">
							<input type="checkbox" checked="checked" disabled="disabled">
						</c:when>
						<c:otherwise>
							<input type="checkbox" disabled="disabled">
						</c:otherwise>
					</c:choose> 如果您勾上此选项，将会实时公开您帐户的：最低信用额度、最高信用额度
				</li>
			</ul>
		</div>

		<div
			style="text-align: center; float: left; width: auto; padding-top: 20px"
			class="usermsg">
			<h3
				style="background-color: #0081c2; height: 30px; color: #ffffff; line-height: 30px;">五、初审审核</h3>
			<form id="finalForm" name="finalForm">
				<ul style="width: 920px; margin: 0 auto;  height: 100px;">
					<li id="carBrand"
						style="text-align: center; width: 450px; float: left; height: 20px; list-style-type: none; padding: 10px 0;">
						汽车品牌：&nbsp;&nbsp;&nbsp;<input type="text" name="brand" id="brand"
						value="${mortgageCar.brand}" />
					</li>
					<li id="carMoney"
						style="text-align: center; width: 450px; float: left; height: 20px; list-style-type: none; padding: 10px 0;">
						汽车购买价：<input type="text" name="buyMoney" id="buyMoney"
						value="${mortgageCar.buyMoney}"
						onkeyup="this.value=this.value.replace(/\D/g,'')" />
					</li>
					<li id="carAssess"
						style="text-align: center; width: 450px; float: left; height: 20px; list-style-type: none; padding: 10px 0;">
						汽车评估价：<input type="text" name="assessMoney" id="assessMoney"
						value="${mortgageCar.assessMoney}"
						onkeyup="this.value=this.value.replace(/\D/g,'')" />
					</li>
					<li
						style="text-align: center; width: 450px; float: left; height: 20px; list-style-type: none; padding: 10px 0;">
						服务费率设置：<input type="text" value="${fee.bfee}" id="bfee"
						name="bfee">%
					</li>
					<li
						style="text-align: center; width: 450px; float: left; height: 20px; list-style-type: none; padding: 10px 0;">
						担保费率设置：<input type="text" value="${fee.gfee}" id="gfee"
						name="gfee">%
					</li>
					<li
						style="text-align: left; width: 450px; float: left; height: 20px; list-style-type: none; padding: 10px 0;">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="hidden" value="${borrow.apr}" id="apr"> 净收益率：<span
						id="aprStr"
						style="color: red; font-weight: bold; font-size: 18px;"></span>%
					</li>
					<li id="mostTenderCountLi"
						style="text-align: center; width: 450px; float: left; height: 20px; list-style-type: none; padding: 10px 0;">
						每人最大投资次数：<input type="text" value="0" id="mostTenderCount"
						name="mostTenderCount">
					</li>
					<li id="mostAccountLi"
						style="text-align: center; width: 450px; float: left; height: 20px; list-style-type: none; padding: 10px 0;">
						每人最大投资金额：<input type="text" value="0" id="mostAccount"
						name="mostAccount">
					</li>
					<li id="openShopTimeLi"
						style="text-align: center; width: 450px; float: left; height: 20px; list-style-type: none; padding: 10px 0;">
						开售时间：<input type="text" id=tenderTime
						name="tenderTime" onfocus="WdatePicker({startDate:'%y-%M-%d %H',dateFmt:'yyyy-MM-dd HH:00:00'})">
					</li>
					<li id="openShopTimeLi"
						style="text-align: center; width: 450px; float: left; height: 20px; list-style-type: none; padding: 10px 0;">
						
					</li>
				</ul>

				<div style="padding-top: 130px;">
					<input type="hidden" name="borrowId" value="${borrow.id }" /> <input
						type="hidden" name="lowestAccount"
						value="${borrow.lowestAccount }" /> <input type="hidden"
						name="id" value="${borrow.id }" /> 状态: &nbsp;&nbsp;&nbsp; <input
						type="radio" value="1" name="status">初审通过
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" value="2"
						name="status">初审不通过 <br />
					<h3 style="color: red;">
						<input type="checkbox" name="biaoType" id="biaoType"
							value="huodong" style="width: 30px; height: 30px;" /> 活动标<span
							style="font-size: 14px;">(勾选此项后，集团用户不返利)</span>
					</h3>
					审核备注:
					<textarea rows="3" cols="90" id="remark" name="verifyRemark"
						style="margin: 0px 0px 10px; width: 700px; height: 64px;">${borrowAll.verifyRemark}</textarea>
					<br /> <input type="button" value="保存信息" name="sub1" id="subfrom1"
						onclick="saveForm()"> &nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="审核此借款标"
						name="sub" id="subfrom" onclick="commitFrom()">
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/borrow/theTrialBorrowList.js"></script>

	<script>
		$(function() {
			$('input[name=status]').click(function() {
				if (this.value == 2) {
					$('#carBrand,#carMoney,#carAssess').hide();
				} else {
					$('#carBrand,#carMoney,#carAssess').show();
				}
			});
		})
	</script>
</body>
</html>