<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	var addBorrowform;
	$(function() {
		parent.$.messager.progress('close');
		addBorrowform = $('#addBorrowform').form({
			url : '/system/borrow/addBorrow',
			onSubmit : function(param) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.messager.show({
						title : '系统提示',
						msg : '操作成功',
						timeout : 5000,
						showType : 'slide'
					});
				} else {
					parent.$.messager.show({
						title : '系统提示',
						msg : result.msg,
						timeout : 5000,
						showType : 'slide'
					});
				}
				parent.$.modalDialog.openner_dataGrid.datagrid('reload');
				parent.$.modalDialog.handler.dialog('close');
			}
		});
		parent.$.messager.progress('close');
	});
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<form id="addBorrowform" method="post" style="text-align: center;" enctype="multipart/form-data">
		<h3 style="background-color: #0081c2; height: 30px; color: #ffffff; line-height: 30px;">8戒发标</h3>
			<table width="750" cellpadding="0" cellspacing="0" style="margin:20px 0  0 200px;">
			<tr>
			    <td width="120" height="48" class="txtRight">发标人ID：</td>
			    <td width="150">
					<input type="text" class="easyui-validatebox" data-options="required:true,validType:['String','length[0,32]']"
					 name="userId" id="userId" onkeyup="this.value=this.value.split('.')[0].replace(/\D/g,'');" value="${borrowvo.userId}"/>
					<span id="account_msg"></span>
			    </td>
			    <td width="220" class="txtRight"></td>
			    <td width="250">
			    </td>
			</tr>
			<tr>
			    <td width="120" height="48" class="txtRight">借款总金额：</td>
			    <td width="150">
					<input type="text" class="easyui-validatebox" data-options="required:true,validType:['String','length[0,32]']"
					 name="account" id="account" onkeyup="this.value=this.value.split('.')[0].replace(/\D/g,'');" value="${borrowvo.account}"/>
					<span id="account_msg"></span>
			    </td>
			    <td width="220" class="txtRight">年利率：</td>
			    <td width="250">
					<input type="text" class="easyui-validatebox" data-options="required:true,validType:['String','length[0,32]']"
					 name="apr" id="apr" value="${borrowvo.apr }"/> %
			    </td>
			</tr>
			<tr class="hideTr">
				<td width="120" height="48" class="txtRight">是否天标：</td>
				<td width="150">
					<select id="isday" name="isday">
						<option value="0">否</option>
						<option value="1">是</option>
					</select>
				</td>
				<td width="120" height="48" class="txtRight">借款期限：</td>
				<td width="150">
					<div class="jkwrap">
						<div class="jkqx" id="day">
							<input type="text" class="easyui-validatebox" name="timeLimit" onkeyup="this.value=this.value.split('.')[0].replace(/\D/g,'');"/>天
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td width="220" class="txtRight">有效时间：</td>
				<td width="240">
					<select name="validTime" id="validTime">
						<option value="1">1天</option>
						<option value="2">2天</option>
						<option value="3">3天</option>
						<option value="4">4天</option>
						<option value="5">5天</option>
						<option value="6">6天</option>
						<option value="7">7天</option>
						<option value="8">永不</option>
					</select>
					<p id="validTime_msg"></p>
				</td>
				<td width="120" height="48" class="txtRight">借款名称:</td>
			    <td width="150">
			    	<input type="text" data-options="required:true,validType:['String','length[0,32]']" class="easyui-validatebox" name="name" id="name" />
			    </td>
			</tr>
			<tr>
			    <td width="120" height="48" class="txtRight">还款方式：</td>
				<td width="150">
					<div class="selectDiv">
						<select name="style" id="pay-type">
							<option value="0">等额本息</option>
							<option value="3">先息后本</option>
							<option value="2">到期还款</option>
							<option value="1">等本等息</option>
						</select>
						<p id="pay-type_msg"></p>
					</div>
				</td>
				<td width="220" class="txtRight">是否担保：</td>
				<td width="240">
					<div class="selectDiv">
						<select name="danbao" id="yes-no">
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
						<p id="yes-no_msg"></p>
					</div>
				</td>
			</tr>
		<tr>
		    <td width="120" height="48" class="txtRight">是否抵押标：</td>
		    <td width="150">
		     <select name="is_fast" id="yes-no-diya" data-placeholder="请选择">
				<option value="1" selected="selected">是</option>
				<option value="2">否</option>
		     </select>
		     <p id="yes-no-diya_msg"></p>
		    </td>
		    <td width="220" class="txtRight">
		     最低投标金额：
		    </td>
		    <td width="240">
			<select name="lowestAccount" id="tenderMoney">
				<option value="50">50元</option>
				<option value="100">100元</option>
		    </select>
		    <p id="tenderMoney_msg"></p>
		    </td>
		  </tr>
		  <tr>
		    <td width="120" height="48" class="txtRight">设定向标密码：</td>
		    <td width="150">
		    <input type="password" class="pwd-set" name="pwd" id="pwd" value="${borrowvo.pwd }"/>
		    </td>
		    <td width="120" height="48" class="txtRight">借款用途：</td>
				<td width="150">
					<select name="use" id="borrow-use">
						<option value="1">短期周转</option>
						<option value="2">生意周转</option>
						<option value="3">生活周转</option>
						<option value="4">购物消费</option>
						<option value="5">不提现借款</option>
						<option value="6">其它借款</option>
						<option value="7">创业借款</option>
					</select>
					<p id="borrow-use_msg"></p>
				</td>
		  </tr>
		  <tr>
		<td width="120" height="48" class="txtRight">金额大写：</td>
		<td width="150">
		<input type="text" class="amountCase" id="amountCase" readonly="readonly"/>
		</td>
		<td width="220" class="txtRight">抵押品种：</td>
		<td width="240">
		<select name="typeName" id="dy-kind" >
			<option value="汽车抵押">汽车抵押</option>
			<option value="债权抵押">债权抵押</option>
		</select>
		<p id="dy-kind_msg"></p>
		</td>
	</tr>
		  <tr id="isContract">
		    <td width="120" height="48" class="txtRight">合同开始时间：</td>
		    <td width="150">
		    	<input type="text" class="pwd-set" name="contract_Start" id="contractStart" value=""
		    	 readonly="readonly" onClick="WdatePicker(({realDateFmt:'yyyy-MM-dd'}))"/>
				<p id="contractStart_msg"></p>
		    </td>
		    <td width="120" height="48" class="txtRight">合同结束时间：</td>
				<td width="150">
			    	<input type="text" class="pwd-set" name="contract_End" id="contractEnd" value=""
			    	 readonly="readonly" onClick="WdatePicker(({realDateFmt:'yyyy-MM-dd'}))"/>
					<p id="contractEnd_msg"></p>
				</td>
		  </tr>
		   <tr id="isContract">
		    <td width="120" height="48" class="txtRight">借款内容：</td>
				<td width="150" colspan="4">
			    	<textarea class="easyui-kindeditor" id="content" style="width:500px;height:120px;" name="content" id="content"></textarea>
				</td>
		  </tr>
		  </table>
		  <h3 style="background-color: #0081c2; height: 30px; color: #ffffff; line-height: 30px;">抵押物信息</h3>
		  <table style="margin:20px 0  0 200px;">
		  <tr id="isContract">
		    <td width="120" height="48" class="txtRight">图片1：</td>
		    <td width="150">
				<input type="file" name="pic1"/>
		    </td>
		    <td width="120" height="48" class="txtRight">图片2：</td>
				<td width="150">
					<input type="file" name="pic2"/>
				</td>
		  </tr>
		  <tr>
    <td width="130" height="48" class="txtRight">车主姓名：</td>
    <td width="150">
    <input type="text" class="jk-amount" id="carHost" name="ownerName"/>
    <span id="carHost_msg"></span>
    </td>
    <td width="220" class="txtRight">车主身份证号码：</td>
    <td width="240">
    <input type="text" class="jk-amount" id="idcarHost" name="ownerCardid"/>
    <p id="idcarHost_msg"></p>
    </td>
  </tr>
  <tr>
    <td width="120" height="48" class="txtRight">车主手机号码：</td>
    <td width="150">
    
    <input type="text" class="jk-amount" id="phoneHost" name="ownerMobile" maxlength="11"/>
    <span id="phoneHost_msg"></span>
    </td>
    <td width="220" class="txtRight">车主籍贯：</td>
    <td width="240">
		<select name="ownerAddress" id="jiguan">
				<option value="${mortgageCar.ownerAddress}">${mortgageCar.ownerAddress}</option>
				<c:forEach items="${areas}" var="areas">
					<option value="${areas.name}">${areas.name}</option>
				</c:forEach>
		</select>
		<p id="jiguan_msg"></p>
    </td>
  </tr>
  <tr>
    <td width="120" height="48" class="txtRight">车架号：</td>
    <td width="150">
    <input type="text" class="jk-amount" id="chejia" name="carStrutNum"/>
    <p id="chejia_msg"></p>
    </td>
    <td width="220" class="txtRight">车牌号：</td>
    <td width="240">
    <input type="text" class="jk-amount" id="carNo" name="carLicense"/>
    <p id="carNo_msg"></p>
    </td>
  </tr>
  <tr>
    <td width="120" height="48" class="txtRight">发动机号：</td>
    <td width="150">
    <input type="text" class="jk-amount" id="enginNo" name="carNumber"/>
    <p id="enginNo_msg"></p>
    </td>
    <td width="220" class="txtRight">注册日期：</td>
    <td width="240">
     <input type="text" class="jk-amount" id="zhuceDate" name="register_Date" readonly="readonly" onClick="WdatePicker(({realDateFmt:'yyyy-MM-dd'}))"
     readonly="readonly"/>
     <p id="zhuceDate_msg"></p>
    </td>
  </tr>
  <tr>
   <td width="120" height="48" class="txtRight">发证日期：</td>
    <td width="150">
       <input type="text" class="jk-amount" id="fazhengDate" name="certification_Date"
        onClick="WdatePicker()" readonly="readonly" />
       <p id="fazhengDate_msg"></p>
    </td>
    <td width="220" class="txtRight">检验有效期：</td>
    <td width="240">
     <input type="text" class="jk-amount" id="jianyanDate" name="checkValid_Date" onClick="WdatePicker(({dateFmt:'yyyy-MM'}))" readonly="readonly" />
      <p id="jianyanDate_msg"></p>
    </td>
  </tr>
  
  <tr>
    <td width="120" height="48" class="txtRight">身份证正面：</td>
    <td width="150">
       	<input type="file" name="card_pic1"/>
    </td>
    <td width="120" height="48" class="txtRight">身份证反面：</td>
		<td width="150">
    	<input type="file" name="car_pic1"/>
		</td>
  </tr>
  <tr>
    <td width="120" height="48" class="txtRight">身份证反面：</td>
    <td width="150">
      <input type="file" name="card_pic2"/>
    </td>
    <td width="120" height="48" class="txtRight">车辆照片2:</td>
		<td width="150">
    <input type="file" name="car_pic2"/>
		</td>
  </tr>
  <tr>
    <td width="120" height="48" class="txtRight">车辆行驶证1：</td>
    <td width="150">
		<input type="file" name="carcard_pic1"/>
    </td>
    <td width="120" height="48" class="txtRight">车辆照片3:</td>
		<td width="150">
    	<input type="file" name="car_pic3"/>
		</td>
  </tr>
  
  <tr>
    <td width="120" height="48" class="txtRight">车辆行驶证2：</td>
    <td width="150">
      <input type="file" name="carcard_pic2"/>
    </td>
    <td width="120" height="48" class="txtRight">车辆照片4:</td>
		<td width="150">
      <input type="file" name="car_pic4"/>
		</td>
  </tr>
  
  <tr>
    <td width="120" height="48" class="txtRight">车辆行驶证3：</td>
    <td width="150">
      <input type="file" name="carcard_pic3"/>
    </td>
    <td width="120" height="48" class="txtRight">其他:</td>
		<td width="150">
      <input type="file" name="other1"/>
		</td>
  </tr>
  
  <tr>
    <td width="120" height="48" class="txtRight">其他：</td>
    <td width="150">
      <input type="file" name="other2"/>
    </td>
    <td width="120" height="48" class="txtRight">其他:</td>
		<td width="150">
      <input type="file" name="other3"/>
		</td>
  </tr>
		</table>
		<h3 style="background-color: #0081c2; height: 30px; color: #ffffff; line-height: 30px;">账户信息公开设置</h3>
		<input type="checkbox" id="myaccount" name="openAccount" value="1" checked="checked"><label>公开我的账户资金情况</label>
		<input type="checkbox" id="borrowmoney" name="openBorrow" value="1" checked="checked"><label>公开我的借款资金情况</label>
		<input type="checkbox" id="tendermoney" name="openTender" value="1" checked="checked"><label>公开我的投标资金情况</label>
		<input type="checkbox" id="creditmoney" name="openCredit" value="1" checked="checked"><label>公开我的信用额度情况</label>
		</form>
	</div>
</div>
