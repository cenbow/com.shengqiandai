<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
</head>
<body>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div style="text-align: center;">
	<form id="subTrial">
		<h3>红包明细列表</h3>
		<h4>总金额：${moneyCount}</h4>
		<h4>红包数量：${userCount}</h4>
		<table style="border: 1px black solid; margin: 0 auto;">
			<thead>
				<tr>
					<th>接收用户ID</th>
					<th>接收用户</th>
					<th>生效时间</th>
					<th>失效时间</th>
					<th>红包内容</th>
					<c:forEach var="col" items="${colsHead}">
						<th>${col}</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="gbi" items="${gbiList}" varStatus="vs">
					<tr>
						<td>${gbi.receiveUser}</td>
						<td>${gbi.receiveUserName}</td>
						<td><fmt:formatDate value="${gbi.takeTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${gbi.loseTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${gbi.content}</td>
						<c:forEach var="cols" begin="${vs.index}" end="${vs.index}" items="${colsBody}">
							<c:forEach var="str" items="${cols}" end="${fn:length(colsHead)-1}">
								<td>
								${str}
								</td>
							</c:forEach>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div style="text-align: center; float: left; width: 100%;"
			class="usermsg">
			<span><input type="radio" name="status"
				value="1"
				onclick="showHiddenContext(this)" />审核通过</span> <span><input
				type="radio" name="status" value="2"
				onclick="showHiddenContext(this)" />审核不通过</span>
			<div
				style="text-align:center;display: ${gb.status == 2?'block' : 'none'};width:100%;">
				审核备注：
				<textarea style="resize: none; width: 80%; height: 70px;"
					name="context">${gb.verifyRemark}</textarea>
			</div>
			<input type="hidden" name="verifyRemark" /> <input
				type="hidden" value="${gb.id}" name="id" />
			<input type="hidden" name="subType" id="subType">
		</div>
		
		<div style="text-align: center; float: left; width: 100%;"
					class="usermsg">
					<input type="button" name="subBtn" id="back" value="审核拒绝"  />
					<input type="button" name="subBtn" id="ok" value="审核通过" style="display: none;"/>
		</div>
		</form>
	</div>
	<style type="text/css">
th {
	border: 1px black solid;
}

td {
	border: 1px black solid;
}
</style>
<script type="text/javascript">
function showHiddenContext(opt) {
	if ($(opt).val() == '2') {
		$(opt).parent().parent().find("div").show();
		$("#ok").hide();
		$("#back").show();
	} else {
		$(opt).parent().parent().find("div").hide();
		$("#ok").show();
		$("#back").hide();
	}
}
//textarea 在表单中无法直接提交，将textarea中的值set到一个input 标签中进行提交
function setRemark(opt){
	$.each(opt,function(index){
		$(this).parent().parent().find("input[name*='verifyRemark']").val($(this).val());
	});
}
$(function(){
	$("input[name='subBtn']").on("click", function() {
		//提交数据前，进行 提交类型与备注值的设置
		setRemark($("textarea[name='context']"));
		$("#subType").val($(this).attr("id"));
		//提交数据
		$.ajax({
			url : '/giftboxTiral/tiralGiftboxInfo',
			data : $('#subTrial').serialize(),
			type : 'post',
			cache : false,
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert('很遗憾，出异常了' + errorThrown);
			},
			beforeSend : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog("close");
					parent.$.messager.show({
						title : '系统提示',
						msg : '操作成功',
						timeout : 5000,
						showType : 'slide'
					});
				} else {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog("close");
					parent.$.messager.show({
						title : '系统提示',
						msg : '操作失败:'+result.msg,
						timeout : 5000,
						showType : 'slide'
					});
				}
			}
		});
	});
});
</script>
</body>
</html>