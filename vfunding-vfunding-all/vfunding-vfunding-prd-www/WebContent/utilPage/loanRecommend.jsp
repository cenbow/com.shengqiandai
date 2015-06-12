<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|基金|微基金|小额贷款|微财富|二手车抵押|车贷|" />
<title>我要借款_微积金_中国领先的O2O互联网汽车金融平台</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/borrow.css" />
</head>
<body>
	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath}/top.jsp"></jsp:include>
	<!--中间-->
	<div class="content box-shadow">
		<div class="cont-wrap">
			<div class="biao-title">
			        借款介绍
   
   <span>向名下拥有车辆的车主提供的专属借款</span>
			</div>
			<div class="cont">
				<div class="cont-left fl">申请条件：</div>
				<div class="cont-right fr">
					1. 23-60周岁<br /> 2. 本人名下拥有在当地车管所登记的车辆，即借款人必须拥有对该车辆的所有权
				</div>
			</div>
			<div class="cont">
				<div class="cont-left fl">借款额度：</div>
				<div class="cont-right fr">
					
第三方机构评估价的70%~80%，如用户仍有更多资金需求，根据用户个人征信、名下资产、工作的稳定性、<br/>
月稳定收入等风控维度，适当提供部分信用贷款。 
				</div>
			</div>
			<div class="cont">
				<div class="cont-left fl">借款利率：</div>
				<div class="cont-right fr">
                               年化18%~23.99%

				</div>
			</div>

			<div class="cont">
				<div class="cont-left fl">借款期限：</div>
				<div class="cont-right fr">1-12个月</div>
			</div>

			<div class="cont">
				<div class="cont-left fl">审核时间：</div>
				<div class="cont-right fr">1个自然日</div>
			</div>
			<div class="cont">
				<div class="cont-left fl">还款方式：</div>
				<div class="cont-right fr">
					等额本息<br />等本等息<br />先息后本 <br />到期还款
				</div>
			</div>
			<div class="cont">
				<div class="cont-left fl">风险管理费：</div>
				<div class="cont-right fr">
					对借款用户评定风险等级，不同风险等级，风险管理费不同。详见《微积金风险等级与风险管理费对应关系表》</div>
			</div>
			<div class="center">
				<a href="${pageContext.request.contextPath}/borrow/releaseBorrow" class="btn226">申请借款</a>
			</div>
		</div>
		<div class="bg-f578"></div>
		<div class="borrow-steps">
			<div class="biao-title">借款流程</div>
			<div class="step-bg"></div>
		</div>
	</div>
	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>
