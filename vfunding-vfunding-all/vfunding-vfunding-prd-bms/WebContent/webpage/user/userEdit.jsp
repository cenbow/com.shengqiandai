<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>编辑用户信息</title>
<jsp:include page="${pageContext.request.contextPath }/inc.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user/userListAdd.js"></script>

</head>

<body>
	<table width="450" border="1">
		<tr width="100px">
			<td width="70px">用户名：</td>
			<td width="212">${user.username}</td>
		</tr>
		<tr width="100px">
			<td width="70px">登录密码：</td>
			<td><label for="textfield"></label> <input type="text"
				name="user.password"
				style="width: 120px; font-size: 12px; height: 12px; margin: 0;"
				id="password" /></td>
		</tr>
		<tr width="100px">
			<td width="70px">确认密码：</td>
			<td><input type="text" name="password"
				style="width: 120px; font-size: 12px; height: 12px; margin: 0;"
				id="password1" /></td>
		</tr>
		<tr width="100px">
			<td width="70px">真实姓名：</td>
			<td><input type="text" name="realname"
				style="width: 120px; height: 12px; font-size: 12px; margin: 0;"
				id="realname" value="${user.realname }" /></td>
		</tr>
		<tr width="100px">
			<td width="70px">性别：</td>
			<td>
	
		<c:if test="${user.sex==1 }">
		  <input name="sex" type="radio" id="sex" value="1" checked="checked" /> 男    
          <input type="radio" name="sex" id="sex" value="2" />女
        </c:if> 
        <c:if test="${user.sex==2 || user.sex==0 }">
		  <input name="sex" type="radio" id="sex" value="1" /> 男    
          <input type="radio" name="sex" id="sex" value="2" checked="checked" />女
        </c:if>
          </td>
		
	  </tr>
		<tr width="100px">
			<td width="70px">生日：</td>
			<td><input type="text" name="user.birthday"
				style="width: 120px; height: 12px; margin: 0;" id="birthday"
				value="${birthday}" /></td>
		</tr>
		<!--     <tr width="100px"> -->
		<!--       <td width="70px">所属客服：</td> -->
		<!--       <td><label for="select"></label> -->
		<!--         <select name="select" style="height: 20px; line-height:20px; width:95px; font-size: 12px; padding: 0; margin: 0 auto;" id="select"> -->
		<!--           <option value="0">无</option> -->
		<!--       </select></td> -->
		<!--     </tr> -->
		<tr width="100px">
			<td width="70px">是否可以发担保标：</td>
			<td>
			    <input type="radio" name="radio" id="radio3" value="radio" checked="checked" /> 否 
			    <input type="radio" name="radio" id="radio4" value="radio" /> 可以
			</td>
		</tr>
		<tr width="100px">
			<td width="70px">类型：</td>
			<td><select name="typeId"
				style="height: 20px; line-height: 20px; font-size: 12px; width: 95px; padding: 0; margin: 0 auto;"
				id="typeId">
					<option selected="selected" value="2">普通用户</option>
					<option value="27">集团用户</option>
					<option value="28">特约理财师</option>
					<option value="29">高级理财师</option>
					<option value="30">首席理财师</option>
					<option value="31">集团内部理财师</option>
			</select></td>
		</tr>

		<tr width="100px">
			<td width="70px">是否锁定：</td>
			<td>
<%-- 	         <c:if test="${user.userStatus == 1 }"> --%>
		       <input type="radio" name="userStatus" id="userStatus" value="1" checked="checked" />开通 
               <input type="radio" name="userStatus" id="status" value="0" /> 锁定
<%--             </c:if>  --%>
<%--             <c:if test="${user.userStatus == 0 }"> --%>
<!-- 		       <input type="radio" name="userStatus" id="status" value="1"  />开通  -->
<!--               <input type="radio" name="userStatus" id="userStatus" value="0" checked="checked"/>锁定 -->
<%--            </c:if> --%>
         </td>
		</tr>


		<tr width="100px">
			<td width="70px">介绍人ID：</td>
			<td><input type="text" name="user.inviteUserid"
				style="width: 120px; height: 12px; margin: 0;" id="inviteUserid"
				value="${user.inviteUserid }" /></td>
		</tr>
		<!--     <tr width="100px"> -->
		<!--       <td width="70px">推广提成费用：</td> -->
		<!--       <td><input type="text" name="textfield6" style="width: 120px; height: 12px; margin: 0;" id="textfield6" /></td> -->
		<!--     </tr> -->



		<tr width="100px">
			<td width="70px">状态：</td>
			<td><input type="radio" name="aaa" id="radio7" value="radio" />
				关闭 <input name="aaa" type="radio" id="radio8" value="radio"
				checked="checked" /> 开通</td>
		</tr>
		<tr width="100px">
			<td width="70px">所在地：</td>
			<td><select name="sh"
				style="height: 20px; line-height: 20px; font-size: 12px; width: 95px; padding: 0; margin: 0 auto;"
				id="sh">
					<option value="">请选择</option>
					<c:forEach items="${areas}" var="areas">
						<option value="${areas.id }">${areas.name}</option>
					</c:forEach>
			</select> <select name="hl"
				style="height: 20px; line-height: 20px; font-size: 12px; width: 95px; padding: 0; margin: 0 auto;"
				id="hl">
					<option value="">请选择</option>
			</select> <select name="xl"
				style="height: 20px; line-height: 20px; font-size: 12px; width: 95px; padding: 0; margin: 0 auto;"
				id="xl">
					<option value="">请选择</option>
			</select></td>
		</tr>
		<tr width="100px">
			<td width="70px">证件类型：</td>
			<td><select name="user.cardType"
				style="height: 20px; line-height: 20px; font-size: 12px; width: 95px; padding: 0; margin: 0 auto;"
				id="cardType">
					<option value="1">身份证</option>
					<option value="2">军官证</option>
					<option value="3">台胞证</option>
			</select> <input type="text" name="user.cardId"
				style="width: 200px; font-size: 12px; height: 12px; margin: 0;"
				id="cardId" readonly="readonly" value="${user.cardId }" /></td>
		</tr>
		<tr width="100px">
			<td width="70px">电子邮件地址：</td>
			<td><input type="text" name="user.email"
				style="width: 120px; font-size: 12px; height: 12px; margin: 0;"
				id="email" value="${user.email }" /></td>
		</tr>
		<tr width="100px">
			<td width="70px">QQ：</td>
			<td><input type="text" name="user.qq"
				style="width: 120px; font-size: 12px; height: 12px; margin: 0;"
				id="qq" value="${user.qq }" /></td>
		</tr>
		<tr width="100px">
			<td width="70px">旺旺：</td>
			<td><input type="text" name="wangwang"
				style="width: 120px; font-size: 12px; height: 12px; margin: 0;"
				id="wangwang" value="${user.wangwang }" /></td>
		</tr>
		<tr width="100px">
			<td width="70px">家庭电话：</td>
			<td><input type="text" name="textfield11"
				style="width: 120px; font-size: 12px; height: 12px; margin: 0;"
				id="textfield11" value="${info.mateTel }" /></td>
		</tr>
		<tr width="100px">
			<td width="70px">手机：</td>
			<td><input type="text" name="user.phone"
				style="width: 120px; font-size: 12px; height: 12px; margin: 0;"
				id="phone" value="${user.phone }" /></td>
		</tr>
		<tr width="100px">
			<td width="70px">详细地址：</td>
			<td><input type="text" name="textfield13" id="address"
				style="width: 250px; font-size: 12px; height: 12px; margin: 0;"
				id="textfield13" value="${user.address}" /></td>
		</tr>
		<!--     <tr width="100px"> -->
		<!--       <td width="70px">令牌序列号SN：</td> -->
		<!--       <td><input type="text" name="textfield14" style="width: 120px; height: 12px; margin: 0;" id="textfield14" /></td> -->
		<!--     </tr> -->
		<tr width="100px" align="right">
			<td colspan="2"><a href="javascript:;" class="easyui-linkbutton"
				onclick="updateUser(${user.userId})" id="searchButton">确认提交</a>
		</tr>
	</table>
	<script>
 $(function(){
	 //获取市 
	$('#sh').change(function(){
		var id=$(this).val();
		$.ajax({
			type : 'POST',
			url :"/system/userMan/city?id="+id,
			dataType: "json",
			success : function(date){
				$(".hl").val("");
				console.info($(".hl"));
	    		var options="";
				$("#hl").empty();
				  $.each(date,function(index,obj){
	    			   //alert(obj.id);alert(obj.promotionName);
	    			   options="<option value="+obj.id+">"+obj.name+"</option>";
	    			  $("#hl").append(options);
	    			  $("#hl").trigger("chosen:updated");
	    		     });
			},
		error :function(){
			alert("ERROR!")
		}
		});
	});
	
	
	//获取县
	$('#hl').change(function(){
		var id=$(this).val();
		$.ajax({
			type : 'POST',
			url :"/system/userMan/city?id="+id,
			dataType: "json",
			success : function(date){
				$("#xl").val("");
				console.info($("#xl"));
	    		var options="";
				$("#xl").empty();
				  $.each(date,function(index,obj){
	    			   options="<option value="+obj.id+">"+obj.name+"</option>";
	    			  $("#xl").append(options);
	    			  $("#xl").trigger("chosen:updated");
	    		     });
			},
		error :function(){
			alert("ERROR!")
		}
		});
	});
})





function updateUser(id){
	var sh=$("#sh").val();
	var hl=$("#hl").val();
	var xl=$("#xl").val();
	var password=$("#password").val();
	var realname=$("#realname").val();
	var sex=$("#sex").val();
	var birthday=$("#birthday").val();
	var typeId=$("#typeId").val();
	var inviteUserid=$("#inviteUserid").val();
	var cardType=$("#cardType").val();
	var cardId=$("#cardId").val();
	var email=$("#email").val();
	var qq=$("#qq").val();
	var wangwang=$("#wangwang").val();
	var phone=$("#phone").val();
	var address=$("#address").val();
	var userStatus=$("#userStatus").val();
	$.ajax({
		url :  '/system/userMan/updateUser?id=' + id + '&sh=' + sh
				+ '&hl=' + hl + '&xl='
				+ xl + '&password=' + password + '&realname=' + realname+'&sex=' + sex+'&birthday=' + birthday +'&typeId=' +typeId+'&inviteUserid='+inviteUserid
				+ '&cardType='+cardType+'&cardId='+cardId+'&email='+email+'&qq='+qq+'&wangwang='+wangwang+'&phone='+phone+'&address='+address+'&userStatus='+userStatus,
		cache : true,
		type : 'POST',
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			//alert("成功！");
			parent.$.messager.show({
				title : '系统提示',
				msg : '操作成功',
				timeout : 5000,
				showType : 'slide'
			});
			parent.$.modalDialog.handler.dialog("close");
			parent.$.messager.progress('close');
			parent.$.modalDialog.openner_dataGrid.datagrid('reload');
		}
	});

	
}


// function changeProvince(){
// 	alert(11)
// 	//var sh=$(this).val();
// 	$.ajax({
// 		type : 'POST',
// 		url :'/system/userMan/updateUser?id='+id,
// 		dataType: 'json',
// 		success : function(areas){
// 			alert(111)
// 		}
// 	});
	
// } 
</script>
</body>






</html>