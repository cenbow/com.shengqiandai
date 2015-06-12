<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<script type="text/javascript">
$(function(){
	$('.au-nav li a[href="' + window.location.pathname + '"]').parent().addClass("au-selected");
})
</script>
<!--左菜单 -->
<div class="about-us-left fl box-shadow">
	<h1>常见问题</h1>
	<div class="au-nav">
		<ul>
			<li><a href="${pageContext.request.contextPath}/utilpage/helpCenter">注册登录</a></li>
			<li><a href="${pageContext.request.contextPath}/utilpage/loanQuestion">充值提现</a></li>
			<li><a href="${pageContext.request.contextPath}/utilpage/accountCenter">投资还款</a></li>
			<li><a href="${pageContext.request.contextPath}/utilpage/websiteFee">账户安全</a></li>
			<li><a href="${pageContext.request.contextPath}/utilpage/offenQuestion">网站资费</a></li>
			<li><a href="${pageContext.request.contextPath}/utilpage/wordExplain">常见名词解释</a></li>
		</ul>
	</div>
</div>