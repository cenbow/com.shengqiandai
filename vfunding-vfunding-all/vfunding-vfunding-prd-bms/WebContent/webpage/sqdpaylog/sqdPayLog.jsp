<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sqd支付记录</title>

<!-- 引入easyUI 1.4.2-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/themes/icon.css" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/demo/demo.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
	
	
	<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/sqdpaylog/sqdPayLog.js"></script>
	
	
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
<body>
<!-- 搜索框 -->
<div id="serchForm">
<form id="form1" name="form1" method="post" action="">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td>产品名称：</td>
      <td>
     <input type="text" name="productName" id="productName" />
      
      </td>
      <td>用户名称：</td>
      <td>
      <input type="text" name="username" id="username" />
      </td>
    </tr>
    <tr>
      <td>支付状态：</td>
      <td>
      <input type="text" name="resultPay" id="resultPay" />
      </td>
      <td>支付日期：</td>
      <td>
      <input type="text" name="addDate" id="addDate" />
      </td>
    </tr>
   
     <tr>
      <td colspan="4">
     <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>  
      
      </td>
    </tr>
   
  </table>
  



  
</form>
</div>
<!-- 表格 -->
<table id="dg"></table>
</body>
</html>