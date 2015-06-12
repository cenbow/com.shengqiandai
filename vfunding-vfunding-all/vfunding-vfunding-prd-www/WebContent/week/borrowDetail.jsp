<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>微积金，中国领先的互联网汽车金融服务平台_理财_理财产品_投融资_理财需求_投资_投资标的_财富_财富成长_手机理财_微信理财_微理财</title>
</head>
<body>

<div class="dialog-head clear">
			<h2>${weekBorrowVO.wb.name}</h2>
		</div>
		<div id="carEntry" class="clear">
			<div class="car-info">
				<h3>车辆信息</h3>
				<table cellspacing="0" cellpadding="0" width="100%" id="car-info">
					<thead>
						<tr>
							<th>车辆品牌</th>
							<th>贷款金额</th>
							<th>审核时间</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${weekBorrowVO.wbp.brand}</td>
							<td>${weekBorrowVO.wb.account}</td>
							<td><fmt:formatDate value="${weekBorrowVO.wb.verifyTime}" pattern="yyyy-MM-dd"/></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="profile-audit">
				<h3>资料审核</h3>
				<table id="ziliaoshenhe" cellpadding="0" cellspacing="0"
					width="100%">
					<thead>
						<tr>
							<th>借款人身份证明</th>
							<th>借款人资产证明</th>
							<th>机动车登记证</th>
							<th>机动车行驶证</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
							<div class="pass tipicon"></div>
							</td>
							<td>
							<div class="pass tipicon"></div>
							</td>
							<td>
							<div class="pass tipicon"></div>
							</td>
							<td>
							<div class="pass tipicon"></div>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th>车主身份证</th>
							<th>车辆保险单</th>
							<th>车辆购置完税证明</th>
							<th>车辆抵押合同及借条</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><div class="pass tipicon"></div></td>
							<td><div class="pass tipicon"></div></td>
							<td><div class="pass tipicon"></div></td>
							<td><div class="pass tipicon"></div></td>
						</tr>
					</tbody>

				</table>

			</div>

			<div class="biao-pic">
				<h3>标的图片</h3>
				<div class="pics" style="text-align: center">
				<c:if test="${fn:length(weekBorrowVO.listWbpc) == 0}">
					<span style="text-align: center;" >暂无图片</span>
				</c:if>
				<c:forEach var="pic" items="${weekBorrowVO.listWbpc}">
					<img src="${pic.pic}" alt="标的图片" /> 
				</c:forEach>
				</div>
			</div>


		</div>
</body>
</html>
