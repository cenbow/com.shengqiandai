<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>微积金-理财师管理</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
<link href="${pageContext.request.contextPath }/css/tip.css"
	type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/licaishi.css" />
<script src="${pageContext.request.contextPath }/js/jquery1.8.3.js"></script>
<script src="${pageContext.request.contextPath }/js/fp/qrcode.js"></script>

<!-- 分页需要的CSS和JS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/paging/style.css"
	media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/paging/jquery.paginate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/friend/friendList.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/ZeroClipboard.js"></script>
<style type="text/css">
#rpl_table {
	
}
#rpl_table tbody td{
	height: 40px;
}
#rpl_table tfoot td{
	height: 40px;
}
#nil_table {
	
}
#nil_table tbody td{
	height: 40px;
}
#nil_table tfoot td{
	height: 40px;
}
</style>
</head>


<script type="text/javascript">
	$(function() {
		//排行榜
		$("#phb").click(function() {
			getReturnProfitLeaderboard();

			getNumberInvitationLeaderboard();
		});

	})

	//邀请人数排行榜
	function getNumberInvitationLeaderboard() {
		$.ajax({
			dataType : "json",
			url : "${pageContext.request.contextPath }/fp/nil",
			success : function(data) {
				if (data && data.obj && data.success) {
					//显示前10排行榜
					showView($("#nil_table"), data.obj.viewList);
					//显示个人排行信息
					showUserRankInfo($("#nil_table"), data.obj.userMap);
				}
			}

		});
	}

	//获取返利达人排行榜，以及个人排行 数据
	function getReturnProfitLeaderboard() {
		$.ajax({
			dataType : "json",
			url : "${pageContext.request.contextPath }/fp/rpl",
			success : function(data) {
				if (data && data.obj && data.success) {
					//显示前10排行榜
					showView($("#rpl_table"), data.obj.viewList);
					//显示个人排行信息
					showUserRankInfo($("#rpl_table"), data.obj.userMap);
				} else {
					alert(data.msg);
				}
			}

		});
	}

	var RPL = "rpl";
	var NIL = "nil";

	//显示个人排行信息
	function showUserRankInfo(j_table, userMap) {
		var j_tb = j_table;
		var prefix = j_tb.attr("id").split("_")[0];
		var j_tfoot = j_tb.find("tfoot");
		j_tfoot.html("");

		var index = userMap["index"];

		index = parseInt(index);
		if (isNaN(index)) {
			index = userMap["index"];
		} else {
			index = index + 1;
			var tr = $("#" + prefix + "_index_" + index).parent();
			tr.addClass("mid-blue");
		}

		var temp = j_tb.find(".mid-blue");
		if(temp.length == 0){
			var tr = "<tr class='mid-blue'>";
			tr += "<td  style='color:#fff;'>" + index + "</td>";
			tr += "<td  style='color:#fff;'>" + userMap["username"] + "</td>";

			if (prefix == RPL) {
				tr += "<td  style='color:#fff;'>" + userMap["feesSum"] + "</td>";
			} else if (prefix == NIL) {
				tr += "<td  style='color:#fff;'>" + userMap["number"] + "</td>";
			}
			tr += "</tr>";
			var tfoot = tr;
			j_tfoot.append(tfoot);
		}
		//排行榜中显示了的用户buzai
		
		
		//排行榜标题显示
		var context = "" ;
		if(typeof index == "number"){
			if(prefix == NIL){
				context = '您邀请的好友人数位列第&nbsp;<spanid="nil_spanRankIndex">'+index+'</span>&nbsp;位'
			}else if(prefix == RPL){
				context = '您获得的累计返利位列第&nbsp;<span id="rpl_spanRankIndex">'+index+'</span>&nbsp;位';
			}
		}else{
			if(prefix == NIL){
				context = '您尚未邀请任何好友，未进入排名'
			}else if(prefix == RPL){
				context = '您尚无任何好友投资，未进入排名';
			}
		}
		$("#" + prefix + "_spanRankContext").html(context);
	}

	function showView(j_table, viewList) {
		var j_tb = j_table;
		var prefix = j_tb.attr("id").split("_")[0];
		var j_tbody = j_tb.find("tbody");
		j_tbody.html("");

		var tbody = "";
		for (var i = 0; i < viewList.length; i++) {
			var v = viewList[i];

			var index = i + 1;
			var indexId = prefix + "_index_" + index;
			var tr = "<tr >";
			var indexTD = "<td  id='"+indexId+"'>" + index + "</td>";
			var usernameTD = "<td>" + userNameDisplayFormat(v["username"])
					+ "</td>";
			var feesSumAndnumberTD;
			if (prefix == RPL) {
				feesSumAndnumberTD = "<td>" + v["feesSum"] + "</td>";
			} else if (prefix == NIL) {
				feesSumAndnumberTD = "<td>" + v["number"] + "</td>";
			}

			tr += indexTD + usernameTD + feesSumAndnumberTD;
			tr += "</tr>";
			tbody += tr;
		}
		tbody += "<tr> <td colspan=3]'>.......</td> </tr>"

		j_tbody.append(tbody);
	}

	function userNameDisplayFormat(userName) {
		var tempStr = "***";
		var length = userName.length;
		var templen = 0;
		if (length >= 4) {
			templen = length - 2;
			return userName.substr(0, 1) + tempStr
					+ userName.substr(templen, length);
		} else if (length >= 2 && length < 4) {
			templen = length - 1;
			return userName.substr(0, 1) + tempStr
					+ userName.substr(templen, length);
		}
	}
</script>


<body>
	<!-------------头部-------------------->
	<jsp:include page="${pageContext.request.contextPath }/top.jsp"></jsp:include>
	<!--中间-->

	<div class="content">

		<div class="myAccount">

			<!-- 左边通用页面 -->
			<jsp:include
				page="${pageContext.request.contextPath }/user/accountLeft.jsp"></jsp:include>


			<div class="rechargeTakeCash fr">
				<div class="rT box-shadow">
					<div class="rTWrap">

						<div class="txTop">
							<b class="midLine"></b>
							<h2>理财师管理</h2>
						</div>

						<div class="licaishiManage">
							<ul>
								<li class="light-gray light-blue">邀请好友</li>
								<li class="light-gray">规则</li>
								<li class="light-gray">好友管理</li>
								<li class="light-gray" id="phb">排行榜</li>
							</ul>
						</div>

						<!--邀请好友-->
						<div class="lcs-content" style="display: block;">

							<div class="return-detail clear">
								<div class="last-month fl">
									上月返利总收入：<em>￥</em><span>${lastSumFees}</span>
								</div>
								<div class="all-return fl">
									返利累计收入：<em>￥</em><span>${sumFees}</span>
								</div>
								<a class="look-into fl" href="/fp/rebateDetail">查看明细</a>
							</div>
							<div class="lcs-invite">
								<div class="erweima">
									<h3 class="i-invite">我要邀请</h3>
									<p>1.通过扫描二维码分享给好友</p>
									<div class="erweima-pic">
										<div class="ewm" id="qrcode">
										</div>
										<a class="yulan">预览</a>
									</div>

								</div>
								<div class="friend">
									<p>2.通过好友注册时输入邀请码</p>
									<p class="tel">${inviteCode }</p>
								</div>
								<div class="copy">
									<p>3.通过复制链接邀请好友注册</p>
									<div class="copy-box">
										<input type="text" id="url" />
										<a id="d_clip_button" class="btn-copy" data-clipboard-target="url">复制</a> 
									</div>
								</div>

								<div class="share">
									<p>4.分享给好友</p>
									<div class="bdsharebuttonbox">

										<a title="分享到新浪微博" href="#" class="bds_tsina" data-cmd="tsina"
											style="font-size: 1em;margin-right:1em;" >&nbsp;新浪微博邀请</a> 
										<a title="分享到腾讯微博" href="#"
											class="bds_tqq" data-cmd="tqq" style="font-size: 1em;margin-right:1em;" >&nbsp;腾讯微博邀请</a>
										<a title="分享到QQ好友" href="#" class="bds_sqq" data-cmd="sqq"
											style="font-size: 1em;margin-right:1em;" >&nbsp;QQ邀请</a> <a href="#" class="bds_more"
											data-cmd="more"><span>更多邀请方式</span></a>
									</div>
									<script>
										window._bd_share_config = {
											"common" : {
												"bdSnsKey" : {},
												"bdUrl" : "http://www.vfunding.com.cn/fp/inviteRegister/${inviteCode}",
												"bdText" : "嗨！我已经加入国内领先的互联网汽车金融O2O服务平台——“微积金”进行理财，短期一个月理财产品年化收益达到10.6%哦！新浪支付资金托管，银行级别保障。安全、可靠、收益高！赶快和我一起开启财富之旅吧！",
												"bdMini" : "2",
												"bdMiniList" : false,
												"bdPic" : "",
												"bdStyle" : "0",
												"bdSize" : "24"
											},
											"share" : {}
										};
										with (document)
											0[(getElementsByTagName('head')[0] || body)
													.appendChild(createElement('script')).src = '/js/fp/share.js?v=89860593.js?cdnversion='
													+ ~(-new Date() / 36e5)];
									</script>
									<style>
.bdshare-button-style0-24 a,.bdshare-button-style0-24 .bds_more {
	line-height: 18px;
}
</style>
								</div>
							</div>
						</div>

						<!--理财师规则-->
						<div class="lcs-content" style="padding-left: 20px;">
							<h3 class="lcs-rule">理财师规则</h3>
							<div class="lcs-rule-item">
								<p>* 成功邀请一位好友注册后，即可获得微积金理财师资格。</p>
								<p>* 理财师权益表现为：</p>
								<p class="pl10">1、享受最多三层被邀请好友的投资收益；</p>
								<p class="pl10">
									2、可以完成<span class="task">任务</span><a class="showTip tipcion"
										title="关注理财师活动页面，完成任务获得加息收益" href="javascript:;"></a>
									获得加息提升自身投资收益；<font class="mun">每成功推荐一个用户注册并投资满100元，即可立即获得一张年化0.3%的<a class="kill" href="/utilpage/toPage/wordExplain#miao" target="_blank" style="color:#00a0e9;">加息卡</a>，在礼品盒内查看。</font>
								</p>
								<p>* 被邀请人权益：</p>
								<p class="pl10">   被邀请人在完成注册并实名认证后可以立即获得2%的加息卡。</p>
								
							</div>
							<div class="lcs-step">
								<img
									src="${pageContext.request.contextPath }/images/account/lcs-step.png">
							</div>
							<div class="demo">
								<h3 class="lcs-rule">示例：</h3>
								<p class="lcs-demo">小微注册并投资成为微积金正式用户后，她邀请了她的男票小A也注册了微积金并完成了一笔10000元的投资，小A又邀请了他的同事小B成为微积金的用户投资了10000万，小B又邀请了他的家人C投资了微积金10000元。</p>
								<p class="lcs-demo">
									则小微作为最上级理财她一共可以获得：<br />小A的10000X0.72%（年化）+小B10000X0.09%（年化）+C的10000X0.09%（年化）
								</p>
								<p class="lcs-demo">
									由于小A成为正是用户后，邀请了小B进行投资，则小A也自动成为微积金理财师，则小A可以获得的收益为：<br />小B的10000X0.72%（年化）+小C的10000X0.09%（年化）
								</p>
							</div>
							
					<a class="get-task" href="/utilpage/toPage/lcsPost">马上获得任务</a>		
							
							
						</div>
						<!--好友管理-->
						<div class="lcs-content">
							<div class="friend myFriend">
								<div class="friendSearch">

									用户名：<input type="text" id="friendSearch" /> <a
										href="javascript:searchFriend();" class="btn78">搜索</a><input
										type="hidden" id="searched" value=""></input>
								</div>

								<div class="show-loading">
									<div class="loading"></div>
								</div>

								<div class="friendTable">
									<table width="780" cellpadding="0" cellspacing="0"
										id="myFriend">
										<tr>
											<td width="195" height="50" class="friendBg">好友用户名</td>
											<td width="195" class="friendBg">真实姓名</td>
											<td width="195" class="friendBg">注册时间</td>
											<td width="195" class="friendBg">用户级别</td>
										</tr>
										<tr>
											<td colspan="4">无好友信息</td>
										</tr>
									</table>
									<input type="hidden" id="pagCount" value="1">
									<div id="paging" style="display: none;"></div>
								</div>
							</div>
						</div>
						<!--理财师排行榜-->
						<div class="lcs-content">
							<div class="rangeBar clear">
								<div class="range-item fl">
									<div class="table-title clear">
										<h3 class="table-title-left fl">返利达人榜</h3>
										<span class="table-title-right fr" id="rpl_spanRankContext">您获得的累计返利位列第&nbsp;<span
											id="rpl_spanRankIndex"></span>&nbsp;位
										</span>
									</div>
									<!-- 返利达人排行榜 table-->
									<table cellpadding="0" cellspacing="0" id="rpl_table">
										<thead>
											<tr>
												<td class="light-gray" width="90" height="40">排行</td>
												<td class="light-gray" width="160">用户名</td>
												<td class="light-gray" width="130">返利</td>
											</tr>
										</thead>
										<tbody>
										</tbody>
										<tfoot>
										</tfoot>
									</table>
									<!-- 返利达人排行榜table -->
								</div>
								<div class="range-item fr" style="margin-right: 0;">

									<div class="table-title clear">
										<h3 class="table-title-left fl">人气排行榜</h3>
										<span class="table-title-right fr" id="nil_spanRankContext">您邀请的好友人数位列第&nbsp;<span
											id="nil_spanRankIndex"></span>&nbsp;位
										</span>
									</div>
									<table cellpadding="0" cellspacing="0" id="nil_table">
										<thead>
											<tr>
												<td class="light-gray" width="90" height="40">排行</td>
												<td class="light-gray" width="160">用户名</td>
												<td class="light-gray" width="130">直接邀请人数</td>
											</tr>
										</thead>
										<tbody>
										</tbody>
										<tfoot>
										</tfoot>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!------尾部------>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>

	<script src="${pageContext.request.contextPath }/js/tip.min.js"></script>
	<script>
		$(function() {
			$("#url").val("http://"+window.location.host+"/fp/inviteRegister/${inviteCode}");
			$(".licaishiManage li").live(
					"click",
					function() {
						var index = $(".licaishiManage").find("li").index(
								$(this));
						$(this).addClass("light-blue").siblings("li")
								.removeClass("light-blue");
						$(".lcs-content").eq(index).show().siblings(
								".lcs-content").hide();
						searchFriend();
					});

			function popLayer() {
				$("body")
						.append(
								'<div id="poplayer"></div><div class="poplayer-box"><div class="btn-close"></div></div>');
				var tmpHeight = $(document.body).height();
				$("#poplayer").css({
					position : 'absolute',
					left : 0,
					top : 0,
					right : 0,
					bottom : 0,
					height : tmpHeight,
					background : '#000000',
					'z-index' : '999'
				});
				$("#poplayer").fadeIn(500);
			}

			function popLayerHide() {
				$("#poplayer").remove();
				$(".poplayer-box").remove();
			}

			$(".yulan").click(function() {
				popLayer()
			});

			$("#poplayer,.btn-close").live('click', function() {
				popLayerHide();
			});

			$('.showTip').poshytip({
				className : 'tip-yellowsimple',
				showTimeout : 1,
				alignTo : 'target',
				alignX : 'center',
				offsetY : 8,
				allowTipHover : true
			});
			
		});
		//设置内容为当前url路径
		var url = window.location.host;
		qrCode.jsonData.content =  "http://"+url.replace("www","m")+"/toScan/${inviteCode}";
		//设置宽度尺寸
		qrCode.jsonData.w =  122;
		//设置外边框距
		qrCode.jsonData.m =  1;
		//在id为qrcode的元素下生成二维码图片
		qrCode.getQrcode('qrcode');
		
		
	</script>

		<script type="text/javascript">
		// 定义一个新的复制对象
		var clip = new ZeroClipboard(
				document.getElementById("d_clip_button"),
				{
					moviePath : "${pageContext.request.contextPath }/ZeroClipboard.swf"
				});

		// 复制内容到剪贴板成功后的操作
		clip.on('complete', function(client, args) {
			alert("链接已成功复制到您的剪切板中");
		});
	</script>


</body>
</html>
