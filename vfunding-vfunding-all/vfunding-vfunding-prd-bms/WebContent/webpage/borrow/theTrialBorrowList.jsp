<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>产品列表</title>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/borrow/theTrialBorrowList.js"></script>
<c:if test="${canEdit==true }">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>

<script type="text/javascript">
$(function(){
	parent.$.messager.progress('close');
})

</script>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
<div id="searchToolBar">
		<div style="float: left;">
			<a href="javascript:openAddDialog();" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加产品</a>
		</div>
		<div id="searchTool">
		用户名<input type="text" id="userName" name="userName" /> 类型<select
			id="biaoType" name="biaoType">
			<option value="">全部</option>
			<option value="fast">抵押标</option>
			<option value="xin">信用标</option>
			<option value="jin">净值标</option>
			<option value="lz">流转标</option>
			<option value="miao">秒还标</option>
			<option value="huodong">活动标</option>
		</select> 状态:<select id="status" name="status">
			<option value="">全部</option>
			<option value="0" selected="selected">待初审</option>
			<option value="1">初审成功</option>
			<option value="2">初审失败</option>
		</select> <a href="javascript:queryDetail();" class="easyui-linkbutton"
			iconCls="icon-search" id="searchButton">查询</a>
	</div>
</div>
<!-- 产品表格 -->
<table id="dg"></table>
<!--  添加-->
<div id="add-dlg" class="easyui-dialog"  style="width:800px;height:400px;"   
        data-options="maximizable:true,resizable:true,modal:true,closed: true,buttons:'#add-buts'">
<form id="add-form"  method="post" action="">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td>产品类型：</td>
      <td>
      <input type="text" name="productType" /></td>
      <td>产品系列：</td>
      <td><input type="text" name="productGroup" /></td>
    </tr>
    <tr>
      <td>产品名称：</td>
      <td><input type="text" name="name"  /></td>
      <td>预期收益率：</td>
      <td><input type="text" name="apr" /></td>
    </tr>
    <tr>
      <td>理财期限：</td>
      <td><input type="text" name="timeLimit"  /></td>
      <td>起息日：</td>
      <td><input type="text" name="qxDate"  /></td>
    </tr>
    <tr>
      <td>还款日：</td>
      <td><input type="text" name="endTime"  /></td>
      <td>还款类型：</td>
      <td><input type="text" name="hkType"  /></td>
    </tr>
    <tr>
      <td>募集金额：</td>
      <td><input type="text" name="account"  /></td>
      <td>起购金额：</td>
      <td><input type="text" name="lowestAccount"  /></td>
    </tr>
    <tr>
      <td>累加金额：</td>
      <td><input type="text" name="ljMoney"  /></td>
      <td>运营标签：</td>
      <td><input type="text" name="yyTags"  /></td>
    </tr>
    <tr>
      <td>标准标签：</td>
      <td><input type="text" name="bzTags"  /></td>
      <td>购买按钮：</td>
      <td><input type="text" name="buyButtonName"  /></td>
    </tr>
  </table>
</form>

</div> 
<!-- 添加buttons -->
<div id="add-buts">
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:add()">添加</a>
<a href="#" class="easyui-linkbutton" onclick="javascript:$('#add-dlg').dialog('close')">关闭</a>
</div>


</body>
</html>