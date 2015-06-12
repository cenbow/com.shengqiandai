<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="微积金(www.vfunding.cn) 整合了民营汽车借贷机构、二手车商、第三方汽车服务机构等传统领域企业，形成了独特的针对汽车提供投融资对接服务的创新型O2O互联网汽车金融服务模式。投资理财用户可以通过微积金平台进行投标、加入微理财计划、购买阳光理财产品等方式进行投资获得高收益；融资借款用户可以通过平台申请网络贷款、小额贷款、抵押贷款、汽车贷款等。" />
<meta name="keywords" content="微积金|理财|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|微理财|理财计划|网络贷款|微贷|红包|基金|微基金|小额贷款|微财富|宜车贷|二手车抵押|车贷|托管" />
<title>微积金，中国领先的互联网汽车金融服务平台_理财_理财产品_投融资_理财需求_投资_投资标的_财富_财富成长_手机理财_微信理财_微理财</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account3.css" />
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath }/css/zhanneiMessages.css" />

<script src="${pageContext.request.contextPath }/js/jquery1.8.3.js"></script>

</head>

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
							<h2>消息中心</h2>
							<div class="excel">
								<!-- <a id="ref"  href="javascript:location.reload(true);">您共有(${noReadCount })封未读信息</a> -->
								我的站内信息--
								<c:choose>
									<c:when test="${type == 'read' }">
								已读
								</c:when>
									<c:otherwise>
								未读
								</c:otherwise>
								</c:choose>


								<input type="hidden" id="noReadNum" value="${noReadCount }">
								<input type="hidden" id="readNum" value="${readCount }">
								<input type="hidden" id="type" value="${type }"> <input
									type="hidden" id="currentPage" value="${currentPage }">
								<input type="hidden" id="totalPage" value="${totalPages }">
							</div>
						</div>




			    <div class="msgNav">
			        <ul id="msgNavUl">
			            <li class="active">系统消息</li>
			            <li class="unactive">站内信</li>
			        </ul>
			    </div>


               <div class="msg-cnt">
						<div class="msg-bar clear">
						<div class="tab-control fl">
							<a class="qx"><input type="checkbox" id="selectAll" />
							   <label for="selectAll">全选</label>
							</a> 
							<a class="didread" href="javascript:;">已读</a>
							<a class="unread" href="javascript:;">未读</a>
						    <a class="delete" href="javascript:deteleMessages();">删除</a>
						    
						</div>


                    <span class="fr">
							<a href="${pageContext.request.contextPath }/user/message">未读<label
								id="newNoReadNum">${noReadCount }</label>封
							</a>，<a
								href="${pageContext.request.contextPath }/user/message?type=read">已读<label
								id="newReadNum">${readCount }</label>封
							</a>，共<label>${allCount }</label>封
                     </span>
						</div>

						<div class="msg-nav">

							<span class="topic">主题</span> <span class="topic-time">发送时间</span>
							<span class="topic-delete">操作</span>

						</div>

						<div class="messages">

							<c:forEach items="${page.rows}" var="row">
								<div class="msg">
									<div class="zn-msg">
										<input type="checkbox" id="checkbox1" deleteId="${row.id }"
											name="check-box" />
										<c:choose>
											<c:when test="${type =='read' }">
												<a class="msg-title  fl" title="yes"> <i id="${row.id }"
													class="msgbg xf2"></i> ${row.shortName }
												</a>
											</c:when>
											<c:otherwise>
												<a class="msg-title  fl"> <i id="${row.id }"
													class="msgbg xf1"></i> ${row.shortName }
												</a>
											</c:otherwise>
										</c:choose>


										<div class="cancel fr" deleteId="${row.id }"></div>
										<div class="msg-time fr">${row.addtimeStr }</div>
									</div>



									<div class="msg-content">${row.content }</div>

								</div>
							</c:forEach>

						</div>

                     </div>
						<div id="page-control">
							<a href="javascript:perPage();" class="prevPage">上一页</a> <span
								class="showNum"> 每页条${pageSize }，当前页${currentPage }/${totalPages }
							</span> <a href="javascript:nextPage();" class="nextPage">下一页</a>
						</div>


					</div>
				</div>
			</div>
		</div>
	</div>

	<!------尾部------>
	
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>


<script>
	$(function() {
		$(".msg-title").click(function() {
			var id = $(this).find("i").attr("id");
			var rem = $(this).find("i");
			//var cancel = $(this).find(".cancel");
			var isRead = $(this).attr("title");
			var read = $(this);

			if (isRead) {
				$('.msgTip').hide();
				$('#messageContent_' + id).show();
			} else {
				$.getJSON("/message/readMessage/" + id, function(data) {
					if (data.success) {
						rem.removeClass("xf1").addClass("xf2");
						//cancel.html('已读');
						read.attr("title", "yes");
						$('.msgTip').hide();
						$('#messageContent_' + id).show();
						var readNum = $('#readNum').val();
						var newReadNum = Number(readNum) + 1;
						var noReadNum = $('#noReadNum').val();
						var newNoReadNum = Number(noReadNum) - 1;
						$('#readNum').val(newReadNum);
						$('#noReadNum').val(newNoReadNum);

						$('#newReadNum').html(newReadNum);
						$('#newNoReadNum').html(newNoReadNum);
					}
				});
			}
		});

		$(".msg").find(".cancel").click(function() {
			var deleteId = $(this).attr('deleteId');
			$.getJSON("/message/deleteMessage", {
				ids : deleteId
			}, function(data) {
				if (data.success) {
					location.reload(true);
				}
			});
		});

		
		//选项卡
	   $("#msgNavUl li").on("click",function(){
            $("#msgNavUl li").removeClass("active").addClass("unactive");
            $(this).addClass("active");
            var text = $(this).text();
            getContent(tetx);
        });
		
		
		
		//点击邮件标题
		$("a.msg-title").click(function(ev) {

			$(this).find("i").removeClass("xf1").addClass("xf2");
			var index = $("a.msg-title").index($(this));
			$(".msg-content").hide();
			$(".msg-content").eq(index).show();

		});

		//全选	   
		$("#selectAll").click(function() {

			if ($(this).attr("checked")) {
				$(this).attr("checked", true);
				$("input[name='check-box']").attr("checked", true);

			} else {

				$(this).removeAttr("checked");
				$("input[name='check-box']").removeAttr("checked");

			}
		});

		//都选中则全选，一个未选则不全选

		$("input[name='check-box']")
				.each(
						function() {
							$(this)
									.click(
											function() {
												if ($("input[name='check-box']:checked").length == $("input[name='check-box']").length) {
													$("#selectAll").attr(
															"checked", true);
												} else {
													$("#selectAll").removeAttr(
															"checked");
												}
											});
						});
	});
	
	
	
	
	// 获取选项卡内容
	    function getContent(postUrl,data){
	        if(postUrl){
	            $.ajax({
	                url:"url",
	                type:"post",
	                dataType:"json",
	                data:data,
	                success:function(result){
	                    $(".msg-cnt").html(result);
	                }
	            })
        }
    }
	
	
	
	
	
	

	function deteleMessages() {
		var ids = '';
		$("input[name='check-box']").each(function() {
			if ($(this).attr("checked")) {
				var id = $(this).attr('deleteId');
				if (id != '' && id != 'undefined') {
					ids += ',' + id;
				}
			}
		});
		if (ids != '') {
			ids = ids.substr(1, ids.length);
			$.getJSON("/message/deleteMessage", {
				ids : ids
			}, function(data) {
				if (data.success) {
					location.reload(true);
				}
			});

		}

	}

	function nextPage() {
		var currentPage = Number($('#currentPage').val());
		var totalPage = Number($('#totalPage').val());
		if (currentPage < totalPage) {
			var type = $('#type').val();
			currentPage += 1;
			location.href = '/user/message?type=' + type + '&currentPage='
					+ currentPage;
		}
	}

	function perPage() {
		var currentPage = Number($('#currentPage').val());
		var totalPage = Number($('#totalPage').val());
		if (currentPage > 1) {
			var type = $('#type').val();
			currentPage -= 1;
			location.href = '/user/message?type=' + type + '&currentPage='
					+ currentPage;
		}
	}
	
	function repayWeekRepayment(repaymentId){
		$.ajax({
			url : '/week/repayWeekRepayment',
			data : {"repaymentId":repaymentId },
			type : 'post',
			cache : false,
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert('异常原因:' + errorThrown);
			},
			success : function(result) {
				result = $.parseJSON(result);
				if (result.success) {
					art.dialog({
						title : '微积金提示',
						content : "还款成功！",
						icon : 'warning',
						ok : function() {
							
						},
						opacity : .3,
						fixed : true,
						lock : true,
					});
				} else {
					art.dialog({
						title : '微积金提示',
						content : result.msg,
						icon : 'warning',
						ok : function() {
							
						},
						opacity : .3,
						fixed : true,
						lock : true,
					});
				}
			}
		});
	}
</script>













</body>
</html>


