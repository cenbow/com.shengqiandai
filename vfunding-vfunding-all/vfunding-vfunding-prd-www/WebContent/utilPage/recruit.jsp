<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="date" uri="http://cn.vfunding.vfunding.prd.www/tags"%>
<!doctype html>
<html>
<head>
<meta charset="${empty charset ?'utf-8':charset}">
<meta name="description" content="微积金会做什么？我们会做最先进的应用平台，该平台必须是一个开放的社会化信息分享平台。我们会做完善的风控体系，通过制度和系统实现投资理财信息的真实性和有效性，透明性，提供投融双方的一站式金融服务解决方案。我们会做最好的APP，我们会开发各类的金融移动应用，我们会做LBS方面的最佳实践。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>招贤纳士_微积金_中国领先的O2O互联网汽车金融平台</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/aboutUs.css" />
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
</head>
<body>
	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
	<!--中间-->
	<div class="content clear">
	<jsp:include page="${pageContext.request.contextPath }/utilPage/aboutUsMenu.jsp"></jsp:include>
		<div class="about-us-right fr box-shadow">

			<!-- 招贤纳士 -->
			<div class="au-box" id="employ_">
				<div class="au-title">招贤纳士</div>
				<div class="contact">
					<p class="what-do">微积金会做什么？</p>
					<p class="what-do">我们会做最先进的应用平台，该平台必须是一个开放的社会化信息分享平台。</p>
					<p class="what-do">我们会做完善的风控体系，通过制度和系统实现投资理财信息的真实性和有效性，透明性，提供投融双方的一站式金融服务解</p>
					<p class="what-do">决方案。</p>
					<p class="what-do">我们会做最好的APP，我们会开发各类的金融移动应用，我们会做LBS方面的最佳实践。</p>
				</div>
				<div class="contact">
					<p class="what-do">做完这些，微积金还做什么？</p>
					<p class="what-do">随着技术的变革，我们的项目需要不断迭代完善。</p>
					<p class="what-do">随着行业和用户的需求变化，我们需要持续地投入资源，因为我们追求极致的用户体验，我们是一家互联网科技公司。这些就</p>
					<p class="what-do">是我们的核心竞争力。</p>
					<p class="what-do">我们也会成立一个软件销售部，专门研发和销售金融服务类的软件和运服务。</p>
					<p class="what-do">我们会成为一个平台，我们的竞争对手是阿里金融，是银行……我们还做打败他们需要做的任何事。</p>
				</div>
				<div class="contact" style="margin-bottom: 0;">
					<p class="what-do">期待着您把你的梦想和热情注入微积金，微积金必将实现您的抱负！</p>
					<p class="what-do">我们现在还很幼小，但是因为有您的加入我们的将来一定会非常强大！</p>
					<p class="what-do">
						请发送您的简历至<a>hr@vfunding.cn</a>，标题请注明所申请职位。
					</p>
					<br /> <br />
					<p class="what-do">
						<b>诚聘岗位如下：</b>
					</p>
				</div>
				<div class="job-position">
					<a>网站架构师</a>
					<div class="job-duty">
						<p>职位描述：</p>
						<p>1. 负责金融理财产品规划和设计，规划符合业务发展和技术要求的理财产品发展路线图；</p>
						<p>2. 负责收集用户需求、现状调研、需求评估和分析、详细方案设计和需求文档撰写，产品测试验收和上线确认工作；</p>
						<p>3. 负责所管理的项目进度跟进，合理安排和协调各方资源推动项目前进；</p>
						<p>4. 负责金融产品的日常运营，撰写产品使用手册文档，并对业务部门相关使用、培训给予支持。</p>
						<br />
						<p>任职要求：</p>
						<p>1. 具备金融产品规划和设计能力，具备财务产品的经验和独到理解；</p>
						<p>2. 熟悉产品实现过程，包括需求分析、产品功能设计、业务流程设计、界面设计和系统测试等；</p>
						<p>3. 熟练使用Visio、Project、Excel、PPT等设计和应用软件，能熟练使用至少一款原型设计软件。</p>
						<p>4. 具有较强的沟通能力、逻辑能力和产品设计能力，对数据敏感，具备较强的分析加工能力；</p>
					</div>
				</div>
				<div class="job-position">
					<a>产品经理</a>
					<div class="job-duty">
						<p>职位描述：</p>
						<p>1. 负责金融理财产品规划和设计，规划符合业务发展和技术要求的理财产品发展路线图；</p>
						<p>2. 负责收集用户需求、现状调研、需求评估和分析、详细方案设计和需求文档撰写，产品测试验收和上线确认工作；</p>
						<p>3. 负责所管理的项目进度跟进，合理安排和协调各方资源推动项目前进；</p>
						<p>4. 负责金融产品的日常运营，撰写产品使用手册文档，并对业务部门相关使用、培训给予支持。</p>
						<br />
						<p>任职要求：</p>
						<p>1. 具备金融产品规划和设计能力，具备财务产品的经验和独到理解；</p>
						<p>2. 熟悉产品实现过程，包括需求分析、产品功能设计、业务流程设计、界面设计和系统测试等；</p>
						<p>3. 熟练使用Visio、Project、Excel、PPT等设计和应用软件，能熟练使用至少一款原型设计软件。</p>
						<p>4. 具有较强的沟通能力、逻辑能力和产品设计能力，对数据敏感，具备较强的分析加工能力；</p>
					</div>
				</div>
				<div class="job-position">
					<a>网站开发工程师</a>
					<div class="job-duty">
						<p>职位描述：</p>
						<p>1. 负责系统框架搭建、核心代码编写及技术问题解决；</p>
						<p>2. 负责系统代码质量评审及项目质量管控；</p>
						<p>3. 负责后期系统的运营、优化等管理；</p>
						<p>4. 参与公司其他大型项目的研发工作。</p>
						<br />
						<p>任职要求：</p>
						<p>1. 本科及以上学历，计算机相关专业；精通JAVA三层架构开发模式；</p>
						<p>2. 熟悉Eclipse开发工具，熟悉Ajax、Structs、Spring开发框架，熟悉JMS和Web Service的开发及Unix环境下的Java多线程开发；</p>
						<p>3. 3年以上基于web的J2EE项目开发工作经验，中大型互联网公司工作经历优先考虑；</p>
						<p>4. 具备一定的设计模式思想，能理解并运用常见模式；</p>
						<p>5. 熟悉Mysql/DB2，能编写较好性能、较复杂的SQL语句，包括存储过程，触发器等，熟悉调优方法；</p>
						<p>6. 精通JQuery等javascript技术，能独立编写较高质量的JS代码；</p>
						<p>7. 有高并发、高负载的项目开发经验；</p>
					</div>
				</div>
				<div class="job-position">
					<a>网站设计师</a>
					<div class="job-duty">
						<p>职位描述：</p>
						<p>1. 负责公司网站整体风格的定位及视觉把握；</p>
						<p>2. 根据业务部门要求完成相关活动、项目等专题页面设计，及广告制作等；</p>
						<p>3. 负责公司网站产品的用户界面和交互操作流程的设计和改进。</p>
						<br />
						<p>任职要求：</p>
						<p>1. 三年以上互联网公司设计经验，有电子商务网站设计经验者优先；</p>
						<p>2. 熟练使用Photoshop、Illustrator、Flash等设计软件；</p>
						<p>3. 熟悉HTML代码和DIV+CSS，了解网页制作流程，熟悉网站模版开发与设计；</p>
						<p>4. 熟悉用户体验，对交互设计有一定的认识；</p>
						<p>5. 具备良好的团队合作精神，善于沟通，领悟力强；</p>
						<p>6. 精通JQuery等javascript技术，能独立编写较高质量的JS代码；</p>
						<p>7. 具有高度的责任心，抗压能力强。</p>
					</div>
				</div>
				<div class="job-position">
					<a>网络推广经理</a>
					<div class="job-duty">
						<p>职位描述：</p>
						<p>1. 负责编制和实施推广策略，制定并实施公司在互联网/移动互联网领域的市场推广计划；</p>
						<p>2. 负责市场渠道的线上推广，进行推广效果监控和分析，以提高市场费用性价比；</p>
						<p>3. 负责寻找和接洽新的市场推广机会和推广平台，制定并实施流量获取方案；</p>
						<P>4. 主动学习并掌握公司的主要产品和解决方案，能够对行业客户提出的需求策划解决方案；</P>
						<p>5. 完成线上线下的市场推广活动；</p>
						<br />
						<p>任职要求：</p>
						<p>1. 大学本科及以上学历；</p>
						<p>2. 两年以上互联网市场推广工作经验，有互联网产品创业公司工作经历者优先；</p>
						<p>3. 熟悉互联网行业运营模式，有金融行业市场推广及社会化营销经验者优先；</p>
						<p>4. 有移动互联网、SEO、微博、微信等新兴互联网渠道推广经验者优先；</p>
						<p>5. 对数据敏感，有较强的分析和判断能力，对行业发展有自己的见解；</p>
						<p>6. 良好的沟通能力和执行能力，优异的团队组织能力及协调力。</p>
					</div>
				</div>
				<div class="job-position">
					<a>信用审核专员</a>
					<div class="job-duty">
						<p>职位描述：</p>
						<p>1. 审核借入者信用资料是否齐全并判断材料的真实性；</p>
						<p>2. 按照要求对借入者的信用材料进行分析核查并录入相关信息;</p>
						<p>3. 与其他部门同事合作，完善核审流程，加强信用管理，降低风险及信贷损失；</p>
						<P>4. 在工作中积极提出自己的意见和建议，优化工作流程；</P>
						<br />
						<p>任职要求：</p>
						<p>1. 吃苦耐劳，具有创业精神，愿意走到市场第一线；</p>
						<p>2. 经济、金融、市场等相关专业本科或以上学历；</p>
						<p>3. 两年以上工作经验，有担保，典当，银行，小额贷款公司等行业相关经历者优先考虑；</p>
						<p>4. 熟练使用常见办公软件；</p>
						<p>5. 工作主动负责，认真仔细，为人正直，诚信，善于沟通；</p>
						<p>6. 思维敏捷，学习能力强，乐于接受并能快速掌握新知识;</p>
						<p>7. 有过互联网金融公司借贷经验从业者，或者有过信用卡审核工作也优先;</p>
					</div>
				</div>
				<script>
					$(function() {
						$(".job-position").find("a").click(
								function() {
									var index = $(".job-position").find("a")
											.index($(this));
									$(".job-duty").eq(index).show();
									$(this).parent().siblings().find(
											".job-duty").hide();
								});
					});
				</script>
			</div>

		</div>
	</div>

	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>

</body>
</html>
