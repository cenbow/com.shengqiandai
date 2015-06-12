<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="${empty charset ?'utf-8':charset}">
<meta name="description"
	content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords"
	content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>微积金，中国领先的互联网汽车金融服务平台_理财_理财产品_投融资_理财需求_投资_投资标的_财富_财富成长_手机理财_微信理财_微理财</title>
<link rel="Shortcut Icon"
	href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="/css/aprilAchievement.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/aboutUs.css" />
<link type="text/css" rel="stylesheet" href="/css/aprilActivities.css"/>
<script src="${pageContext.request.contextPath}/js/jquery1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?key=67bd734bd2ef5e5ccecfeccbb5a221ee&v=1.1&services=true"></script>
<!-- 弹出层JS -->
<script	src="${pageContext.request.contextPath}/js/artDialog/artDialog.js?skin=default"></script>
</head>
<body>
	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
	<!--中间-->
	<div class="achievement"></div>
<div class="modal">
<div class="rect">

    <div class="tiao">
        <div class="money-ratio">${aprilTenderTotal}/${conditionTotal}</div>
        <div class="arrow-guide"></div>
        <div class="money-guide">继续投资${substractTotal}元，就可解锁下一关卡奖励啦！</div>
    </div>
	
	<c:if test="${bottom<100}">
	    <div class="tiao3">
	        <div class="money-ratio">${aprilTenderTotal}/${conditionTotal}</div>
	        <div class="arrow-guide"></div>
	        <div class="money-guide3">继续投资${substractTotal}元，就可解锁下一关卡奖励啦！</div>
   		 </div>    
	</c:if>
	
    <div class="rect-top-right"></div>
    <div class="rect-bottom-right"></div>
    
    <div class="sub-rect-top" style="width:${top}%"></div>
    <div class="sub-rect-right"></div>
    <div class="sub-rect-bottom" style="width:${bottom}%"></div>

    <div class="circle-guide1">
        <div class="avantar cg2">
            <div class="inner-cnt">
             <p class="rate">0.3%</p>
             <p class="jiaxi">加息</p>
            </div>
        </div>
        <div class="circle-guide-cnt">
            <p class="invest-all">投资累计达到</p>
            <p class="money"> 5,000元</p>
           <c:choose>
           		<c:when test="${empty al1}">
           			 <a class="fight">努力中</a>
           		</c:when>
           		<c:otherwise>
           			<c:if test="${al1.status==0}">
           				 <a class="getReward" aid="${al1.achievementId}">领取奖励</a>
           			</c:if>
           			<c:if test="${al1.status==1}">
           				 <a class="btn-link">已领取</a>
           			</c:if>
           		</c:otherwise>
           	</c:choose>
        </div>
    </div>

    <div class="circle-guide2">
        <div class="avantar cg2">
            <div class="inner-cnt">
                <p class="rate">0.1%</p>
                <p class="jiaxi">加息</p>
            </div>
        </div>
        <div class="circle-guide-cnt">
            <p class="invest-all">投资累计达到</p>
            <p class="money"> 10,000元</p>
            <c:choose>
           		<c:when test="${empty al2}">
           			 <a class="fight">努力中</a>
           		</c:when>
           		<c:otherwise>
           			<c:if test="${al2.status==0}">
           				 <a class="getReward" aid="${al2.achievementId}">领取奖励</a>
           			</c:if>
           			<c:if test="${al2.status==1}">
           				 <a class="btn-link">已领取</a>
           			</c:if>
           		</c:otherwise>
           	</c:choose>
        </div>
    </div>


    <div class="circle-guide3">
        <div class="avantar cg2">
            <div class="inner-cnt">
                <p class="rate">0.1%</p>
                <p class="jiaxi">加息</p>
            </div>
        </div>
        <div class="circle-guide-cnt">
            <p class="invest-all">投资累计达到</p>
            <p class="money"> 15,000元</p>
            <c:choose>
           		<c:when test="${empty al3}">
           			 <a class="fight">努力中</a>
           		</c:when>
           		<c:otherwise>
           			<c:if test="${al3.status==0}">
           				 <a class="getReward" aid="${al3.achievementId}">领取奖励</a>
           			</c:if>
           			<c:if test="${al3.status==1}">
           				 <a class="btn-link">已领取</a>
           			</c:if>
           		</c:otherwise>
           	</c:choose>
        </div>
    </div>

    <div class="circle-guide4">
        <div class="avantar cg2">
            <div class="inner-cnt">
                <p class="rate">0.1%</p>
                <p class="jiaxi">加息</p>
            </div>
        </div>
        <div class="circle-guide-cnt">
            <p class="invest-all">投资累计达到</p>
            <p class="money"> 20,000元</p>
            <c:choose>
           		<c:when test="${empty al4}">
           			 <a class="fight">努力中</a>
           		</c:when>
           		<c:otherwise>
           			<c:if test="${al4.status==0}">
           				 <a class="getReward" aid="${al4.achievementId}">领取奖励</a>
           			</c:if>
           			<c:if test="${al4.status==1}">
           				 <a class="btn-link">已领取</a>
           			</c:if>
           		</c:otherwise>
           	</c:choose>
        </div>
    </div>

    <div class="circle-guide5">
        <div class="avantar cg2">
            <div class="inner-cnt">
                <p class="rate">0.2%</p>
                <p class="jiaxi">加息</p>
            </div>
        </div>
        <div class="circle-guide-cnt">
            <p class="invest-all">投资累计达到</p>
            <p class="money"> 30,000元</p>
            <c:choose>
           		<c:when test="${empty al5}">
           			 <a class="fight">努力中</a>
           		</c:when>
           		<c:otherwise>
           			<c:if test="${al5.status==0}">
           				 <a class="getReward" aid="${al5.achievementId}">领取奖励</a>
           			</c:if>
           			<c:if test="${al5.status==1}">
           				 <a class="btn-link">已领取</a>
           			</c:if>
           		</c:otherwise>
           	</c:choose>
        </div>
    </div>

    <div class="circle-guide6">
        <div class="avantar cg2">
            <div class="inner-cnt">
                <p class="rate">0.2%</p>
                <p class="jiaxi">加息</p>
            </div>
        </div>
        <div class="circle-guide-cnt">
            <p class="invest-all">投资累计达到</p>
            <p class="money"> 40,000元</p>
            <c:choose>
           		<c:when test="${empty al6}">
           			 <a class="fight">努力中</a>
           		</c:when>
           		<c:otherwise>
           			<c:if test="${al6.status==0}">
           				 <a class="getReward" aid="${al6.achievementId}">领取奖励</a>
           			</c:if>
           			<c:if test="${al6.status==1}">
           				 <a class="btn-link">已领取</a>
           			</c:if>
           		</c:otherwise>
           	</c:choose>
        </div>
    </div>

    <div class="circle-guide7">
        <div class="avantar cg2">
            <div class="inner-cnt">
                <p class="rate">0.2%</p>
                <p class="jiaxi">加息</p>
            </div>
        </div>
        <div class="circle-guide-cnt">
            <p class="invest-all">投资累计达到</p>
            <p class="money"> 50,000元</p>
            <c:choose>
           		<c:when test="${empty al7}">
           			 <a class="fight">努力中</a>
           		</c:when>
           		<c:otherwise>
           			<c:if test="${al7.status==0}">
           				 <a class="getReward" aid="${al7.achievementId}">领取奖励</a>
           			</c:if>
           			<c:if test="${al7.status==1}">
           				 <a class="btn-link">已领取</a>
           			</c:if>
           		</c:otherwise>
           	</c:choose>
        </div>
    </div>


    <div class="circle-guide8">
        <div class="avantar cg2">
            <div class="inner-cnt">
                <p class="rate">0.6%</p>
                <p class="jiaxi">加息</p>
            </div>
        </div>
        <div class="circle-guide-cnt">
            <p class="invest-all">投资累计达到</p>
            <p class="money"> 80,000元</p>
           	<c:choose>
           		<c:when test="${empty al8}">
           			 <a class="fight">努力中</a>
           		</c:when>
           		<c:otherwise>
           			<c:if test="${al8.status==0}">
           				 <a class="getReward" aid="${al8.achievementId}">领取奖励</a>
           			</c:if>
           			<c:if test="${al8.status==1}">
           				 <a class="btn-link">已领取</a>
           			</c:if>
           		</c:otherwise>
           	</c:choose>
        </div>
    </div>
</div>
</div>
<script>
    $(function(){
        var progress1 = $(".sub-rect-top").width();
        var progress2 = $(".sub-rect-right").height();
        var progress3 = $(".sub-rect-bottom").width();

        if(progress1=="0"){
            $(".money-guide").css("right","-290px");
            $(".tiao").css("left",progress1).css("margin-left","-72px").show();
        }else if(progress1>"0"&&progress1<"220"){
            $(".money-guide").css("right","-290px");
            $(".tiao").css("left",progress1).css("margin-left","-88px").show();
            $(".circle-guide1").find(".cg2").css("background-position","0 -70px");
        }else if(progress1>="220"&&progress1<"460"){
            $(".money-guide").css("right","-290px");
            $(".tiao").css("left",progress1).css("margin-left","-88px").show();
            $(".circle-guide1,.circle-guide2").find(".cg2").css("background-position","0 -70px");
        }else if(progress1>="460"&&progress1<"685"){
            $(".money-guide").css("left","-290px");
            $(".tiao").css("left",progress1).css("margin-left","-89px").show();
            $(".circle-guide1,.circle-guide2,.circle-guide3").find(".cg2").css("background-position","0 -70px");

        }else if(progress1>="685"&&progress1<="770"){
            $(".sub-rect-top").css("width","100%");
            $(".tiao").css("display","none");
            $(".sub-rect-right").css("height","100%");
            $(".rect-top-right,.rect-bottom-right").show();
            $(".circle-guide1,.circle-guide2,.circle-guide3,.circle-guide4").find(".cg2").css("background-position","0 -70px");

        }

        if(progress3>"0"&&progress3<"63"){
            $(".rect-bottom-right").show();
            $(".money-guide3").css("left","-290px");
            $(".tiao3").css("right",progress3).css("margin-right","-88px").show();
        }else if(progress3>="65"&&progress3<"280"){
            $(".money-guide3").css("left","-290px");
            $(".tiao3").css("right",progress3).css("margin-right","-88px").show();
            $(".circle-guide5").find(".cg2").css("background-position","0 -70px");
        }else if(progress3>="280"&&progress3<"494"){
            $(".money-guide3").css("left","-290px");
            $(".tiao3").css("right",progress3).css("margin-right","-88px").show();
            $(".circle-guide5,.circle-guide6").find(".cg2").css("background-position","0 -70px");
        }else if(progress3>="494"&&progress3<"710"){
            $(".money-guide3").css("right","-290px");
            $(".tiao3").css("right",progress3).css("margin-right","-88px").show();
            $(".circle-guide5,.circle-guide6,.circle-guide7").find(".cg2").css("background-position","0 -70px");
        }else if(progress3>="710"&&progress3<"770"){
            $(".money-guide3").css("right","-290px");
            $(".tiao3").css("right",progress3).css("margin-right","-88px").show();
            $(".circle-guide5,.circle-guide6,.circle-guide7,.circle-guide8").find(".cg2").css("background-position","0 -70px");

        }else if(progress3=="770"){
            $(".money-guide3").css("right","-290px");
            $(".tiao3").css("right",progress3).css("margin-right","-118px").show();
            $(".circle-guide5,.circle-guide6,.circle-guide7,.circle-guide8").find(".cg2").css("background-position","0 -70px");
        }
		$(".getReward").on("click",function(){
			var data={};
			data["achievementId"]=$(this).attr("aid");
			$.ajax({
				type:'post',
				url:'/activity/april/pickAchievement',
				data:data,
				cache:true,
				async:true,
				success:function(json){
					json = $.parseJSON(json);
					if(json.success){
						window.location.href = "/giftbox/boxPage?dakai=2";
					}else{
						alert(json.msg);
					}
				}
			});
		});
    })
</script>
	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>
