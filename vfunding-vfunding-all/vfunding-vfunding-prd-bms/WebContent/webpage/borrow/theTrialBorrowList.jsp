<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<title>产品列表</title>


<!-- 引入bootstrap样式
<link href="${ctx }/js/bootstrap-2.3.1/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
-->

<!-- 引入easyUI 1.4.2-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/themes/icon.css"  >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/demo/demo.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
	
	
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
<%-- <jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include> --%>
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
<div id="add-dlg" class="easyui-dialog"  style="width:800px;height:500px;"   
        data-options="maximizable:true,resizable:true,modal:true,closed: true,buttons:'#add-buts'">
<form id="add-form"  method="post" action="" novalidate="true">
  <table width="100%" border="0" cellpadding="5" cellspacing="5">
    <tr>
      <td>产品类型：</td>
      <td>
     
      
      <select id="productType" class="easyui-combobox" name="productType" >   
		    <option value="aa">基金</option>   
		    <option>保险</option>   
		    <option>保理</option>   
		    <option>小贷</option>   
		    <option>不良资产</option>   
		     <option>融资租赁</option>   
		     <option>票据</option>   
		    
		    
		</select> 
      
      </td>
      <td>产品系列：</td>
      <td>
      
      <input class="easyui-textbox" type="text" name="productGroup" data-options="required:false">
      </td>
    </tr>
    <tr>
      <td>产品名称：</td>
      <td>
      
      <input class="easyui-textbox" type="text" name="name" data-options="required:false">
      </td>
      <td>预期收益率：(%)</td>
      <td>
      
        <input class="easyui-textbox" type="text" name="apr" data-options="required:false">
      </td>
    </tr>
    <tr>
      <td>理财期限：(天)</td>
      <td>
        <input class="easyui-textbox" type="text" name="timeLimit" data-options="required:false">
      
      </td>
      <td>起息日：</td>
      <td>
      
       <input name="qxDate" class="easyui-datetimebox" required  data-options="editable:false" />
      </td>
    </tr>
    <tr>
      <td>还款日：</td>
      <td>
      
      <input name="hkDate" class="easyui-datetimebox" required  data-options="editable:false" />
      </td>
      <td>还款类型：</td>
      <td>
      
       <select id="hkType" class="easyui-combobox" name="hkType" >   
		    <option value="aa">到期自动还款至银行卡</option>   
		    <option>每月本息还款</option>   
		    
		    
		</select> 
      </td>
    </tr>
    <tr>
      <td>募集金额：(元)</td>
      <td>
      
        <input class="easyui-textbox" type="text" name="account" data-options="required:false">
      </td>
      <td>起购金额：(元)</td>
      <td>
      
        <input class="easyui-textbox" type="text" name="lowestAccount" data-options="required:false">
      </td>
    </tr>
    <tr>
      <td>累加金额：(元)</td>
      <td>
      
        <input class="easyui-textbox" type="text" name="ljMoney" data-options="required:false">
      </td>
      <td>运营标签：</td>
      <td>
      
        <input class="easyui-textbox" type="text" name="yyTags" data-options="required:false">
      </td>
    </tr>
    <tr>
      <td>标准标签：</td>
      <td>
      
        <input class="easyui-textbox" type="text" name="bzTags" data-options="required:false">
      </td>
      <td>购买按钮：</td>
      <td>
      
        <input class="easyui-textbox" type="text" name="buyButtonName" data-options="required:false">
      </td>
    </tr>
  </table>
  
  <br>
  <!-- 项目描述 -->
	  <table width="100%" border="0" cellpadding="5" cellspacing="5">
		  <tr>
		    <td colspan="2"><h3>项目描述</h3></td>
		  </tr>
		  <tr>
		    <td>募集金额：</td>
		    <td>
		    <input class="easyui-textbox" type="text" name="mjMoney" data-options="required:false">
		    
		    </td>
		  </tr>
		  
		  <tr>
		    <td>项目描述：</td>
		    <td>
		    <input name="projectDesc" class="easyui-textbox" data-options="multiline:true" 
		     style="width:400px;height:100px" />
		     
		     </td>
		  </tr>
		  <tr>
		    <td>资金用途：</td>
		    <td>
		    
		     <input name="projectDesc" class="easyui-textbox" data-options="multiline:true" 
		     style="width:400px;height:100px">
		    </td>
		  </tr>
		  
		  <tr>
		    <td>项目描述图片：</td>
		    <td>
		    <input type="file" id="sqdProjectDescImg" name="sqdProjectDescImg" />
		    </td>
		  </tr>
	</table>
   <br>
  <!-- 资金保障 -->
	  <table width="100%" border="0" cellpadding="5" cellspacing="5">
		  <tr>
		    <td colspan="2"><h3>资金保障</h3></td>
		  </tr>
		  <tr>
		    <td>募集金额：</td>
		    <td>
		    <input class="easyui-textbox" type="text" name="mjMoney" data-options="required:false">
		    
		    </td>
		  </tr>
		  
		  <tr>
		    <td>资金描述：</td>
		    <td>
		    <input name="projectDesc" class="easyui-textbox" data-options="multiline:true" 
		     style="width:400px;height:100px" />
		     
		     </td>
		  </tr>
		  <tr>
		    <td>资金用途：</td>
		    <td>
		    
		     <input name="projectDesc" class="easyui-textbox" data-options="multiline:true" 
		     style="width:400px;height:100px">
		    </td>
		  </tr>
		  
		  <tr>
		    <td>资金描述图片：</td>
		    <td>
		    <input type="file" id="sqdProjectDescImg" name="sqdProjectDescImg" />
		    </td>
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