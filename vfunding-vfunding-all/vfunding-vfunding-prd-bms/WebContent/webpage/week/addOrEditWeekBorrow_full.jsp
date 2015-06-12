<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>标的详情</title>
<jsp:include page="${pageContext.request.contextPath}/inc.jsp"></jsp:include>
</head>
<body>
<style>
.previewImg{
width:180px;
height:180px;
}
</style>
<!-- easyui modalDialog装载必须放在body里 不然加载不到相关资源 -->
<!-- 上传需要的CSS和JS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/upload/upload.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/upload/ajaxupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/upload/WeekBorrowUpload.js"></script>
<!-- 当前jsp需要的其他js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/week/addOrEditWeekBorrow.js"></script>
<!-- 标的新增或编辑页面 -->
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow-x: hidden;">
		<form id="addoreditform" method="post" style="text-align: center;">
			<h3	style="background-color: #0081c2; height: 30px; color: #ffffff; line-height: 30px;">一、标的信息</h3>
			<input type="hidden" value="${weekId}" id="weekId" name="weekId"/>
			<input type="hidden" value="${weekBorrow.id}" id="weekBorrowId" name="weekBorrowId"/>
			<table class="table table-hover table-condensed" style="text-align: center; ">
				<!-- 第一行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">标的名称:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="name" name="name"
						class="easyui-validatebox" 
						value="${weekBorrow.name}" />
					</td>
					
					<th style="vertical-align: middle; text-align: right;">借款总金额:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="account" name="account"
						class="easyui-numberbox"  precision="2"
						value="${weekBorrow.account}" />元
					</td>
				</tr>
				<!-- 第二行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">天数:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="timeLimit" name="timeLimit"
						class="easyui-numberbox"
						value="${weekBorrow.timeLimit}" />天
					</td>
					
					<th style="vertical-align: middle; text-align: right;">年化收益率:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="apr" name="apr"
						class="easyui-numberbox"  precision="2"
						value="${weekBorrow.apr}" />%
					</td>
				</tr>
				<!-- 第三行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">合同编号:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="contractNumber" name="contractNumber"
						class="easyui-validatebox" 
						value="${weekBorrow.contractNumber}" />
					</td>
					
					<th style="vertical-align: middle; text-align: right;">标的品种:</th>
					<td style="vertical-align: middle;">
						<select id="type" name="type" style="width: 120px; height: 28px;">
							<option value=""></option>
							<c:choose>
								<c:when test="${weekBorrow.type==1}">
									<option selected="selected" value="1">抵押标的</option>
								</c:when>
								<c:otherwise>
									<option value="1">抵押标的</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${weekBorrow.type==2||weekBorrow.type==null}">
									<option selected="selected" value="2">债权转让</option>
								</c:when>
								<c:otherwise>
									<option value="2">债权转让</option>
								</c:otherwise>
							</c:choose>
						</select> 
					</td>
				</tr>
				<!-- 第四行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;"> 合同开始时间:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="contractStart" name="contractStart"
						class="easyui-validatebox" 
						value="${formatContractStart}"
						onClick="WdatePicker()"
						readonly="readonly"
						style="cursor:pointer;"
						/>
					</td>
					
					<th style="vertical-align: middle; text-align: right;">合同结束时间 :</th>
					<td style="vertical-align: middle;">
						<input type="text" id="contractEnd" name="contractEnd"
						class="easyui-validatebox" 
						value="${formatContractEnd}"
						onClick="WdatePicker()"
						readonly="readonly"
						style="cursor:pointer;"
						 />
					</td>
				</tr>
			</table>
	
		
		
			<h3	style="background-color: #0081c2; height: 30px; color: #ffffff; line-height: 30px;">二、抵押物信息</h3>
			<input type="hidden" value="${weekBorrowPawn.id}" id="weekBorrowPawnId" name="weekBorrowPawnId"/>
			<table class="table table-hover table-condensed" style="text-align: center; ">
				<!-- 第一行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">物主姓名:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="ownerName" name="ownerName"
						class="easyui-validatebox" 
						value="${weekBorrowPawn.ownerName}" />
					</td>
					
					<th style="vertical-align: middle; text-align: right;">物主身份证:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="ownerCardid" name="ownerCardid"
						class="easyui-validatebox" 
						value="${weekBorrowPawn.ownerCardid}" />
					</td>
				</tr>
				<!-- 第二行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">物主手机号:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="ownerMobile" name="ownerMobile"
						class="easyui-validatebox" 
						value="${weekBorrowPawn.ownerMobile}" />
					</td>
					
					<th style="vertical-align: middle; text-align: right;">物主籍贯:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="ownerAddress" name="ownerAddress"
						class="easyui-validatebox" 
						value="${weekBorrowPawn.ownerAddress}" />
					</td>
				</tr>
				<!-- 第三行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">发动机号:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="carNumber" name="carNumber"
						class="easyui-validatebox" 
						value="${weekBorrowPawn.carNumber}" />
					</td>
					
					<th style="vertical-align: middle; text-align: right;">发证日期:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="certificationDate" name="certificationDate"
						class="easyui-validatebox" 
						value="${formatCertificationDate}"
						onClick="WdatePicker()"
						readonly="readonly"
						style="cursor:pointer;"
						/>
					</td>
				</tr>
				<!-- 第四行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;"> 注册日期:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="registerDate" name="registerDate"
						class="easyui-validatebox" 
						value="${formatregisterDate}"
						onClick="WdatePicker()"
						readonly="readonly"
						style="cursor:pointer;"
						/>
					</td>
					
					<th style="vertical-align: middle; text-align: right;">检验有效期 :</th>
					<td style="vertical-align: middle;">
						<input type="text" id="checkValidDate" name="checkValidDate"
						class="easyui-validatebox" 
						value="${formatcheckValidDate}"
						onClick="WdatePicker()"
						readonly="readonly"
						style="cursor:pointer;"
						 />
					</td>
				</tr>
				<!-- 第五行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;"> 车牌号:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="carLicense" name="carLicense"
						class="easyui-validatebox" 
						value="${weekBorrowPawn.carLicense}"
						/>
					</td>
					
					<th style="vertical-align: middle; text-align: right;">车架号 :</th>
					<td style="vertical-align: middle;">
						<input type="text" id="carStrutNum" name="carStrutNum"
						class="easyui-validatebox" 
						value="${weekBorrowPawn.carStrutNum}"
						 />
					</td>
				</tr>
				<!-- 第六行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;"> 品牌:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="brand" name="brand"
						class="easyui-validatebox" 
						value="${weekBorrowPawn.brand}"
						/>
					</td>
					
					<th></th>
					<td></td>
				</tr>
				<!-- 第七行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;"> 购买金额:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="buyMoney" name="buyMoney"
						class="easyui-numberbox"  precision="2"
						value="${weekBorrowPawn.buyMoney}"
						/>元
					</td>
					
					<th style="vertical-align: middle; text-align: right;">评估金额 :</th>
					<td style="vertical-align: middle;">
						<input type="text" id="assessMoney" name="assessMoney"
						class="easyui-numberbox"  precision="2"
						value="${weekBorrowPawn.assessMoney}"
						 />元
					</td>
				</tr>
			</table>

			<h3	style="background-color: #0081c2; height: 30px; color: #ffffff; line-height: 30px;">三、照片</h3>
			<input type="hidden" value="${weekBorrowPawnPic.id}" id="weekBorrowPawnPicId" name="weekBorrowPawnPicId"/>
			<table class="table table-hover table-condensed" style="text-align: center;">
				<!-- 第一行图片 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">身份证正面:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="card_pic1" readonly="readonly" class="input_" value=""/>
						<div id="btnUp1" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit">浏览</div>
						<span id="card_pic1_msg" style="color:#ff5555;"></span>
					</td>
					
					<th style="vertical-align: middle; text-align: right;">身份证反面:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="card_pic2" readonly="readonly" class="input_" value=""/>
						<div id="btnUp2" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit">浏览</div>
						<span id="card_pic2_msg" style="color:#ff5555;"></span>
					</td>
				</tr>
				<!-- 第一行图片预览 -->
				<tr style="text-align: center;">
					<c:choose>
						<c:when test="${cardpic1[0].pic==null}">
							<th></th>
							<td></td>
						</c:when>
						<c:otherwise>
							<th style="vertical-align: middle; text-align: right;">图片预览:</th>
							<td><img class="previewImg" src="${cardpic1[0].pic}"/></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${cardpic2[0].pic==null}">
							<th></th>
							<td></td>
						</c:when>
						<c:otherwise>
							<th style="vertical-align: middle; text-align: right;">图片预览:</th>
							<td><img class="previewImg" src="${cardpic2[0].pic}"/></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<!-- 第二行图片 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">车辆照片1:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="car_pic1" readonly="readonly" class="input_" value=""/>
						<div id="btnUp3" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit">浏览</div>
						<span id="car_pic1_msg" style="color:#ff5555;"></span>
					</td>
					
					<th style="vertical-align: middle; text-align: right;">车辆照片2:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="car_pic2" readonly="readonly" class="input_" value=""/>
						<div id="btnUp4" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit">浏览</div>
						<span id="car_pic2_msg" style="color:#ff5555;"></span>
					</td>
				</tr>
				<!-- 第二行图片预览 -->
				<tr style="text-align: center;">
					<c:choose>
						<c:when test="${carpic[0].pic==null}">
							<th></th>
							<td></td>
						</c:when>
						<c:otherwise>
							<th style="vertical-align: middle; text-align: right;">图片预览:</th>
							<td><img class="previewImg" src="${carpic[0].pic}"/></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${carpic[1].pic==null}">
							<th></th>
							<td></td>
						</c:when>
						<c:otherwise>
							<th style="vertical-align: middle; text-align: right;">图片预览:</th>
							<td><img class="previewImg" src="${carpic[1].pic}"/></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<!-- 第三行图片 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">车辆照片3:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="car_pic3" readonly="readonly" class="input_" value=""/>
						<div id="btnUp5" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit">浏览</div>
						<span id="car_pic3_msg" style="color:#ff5555;"></span>
					</td>
					
					<th style="vertical-align: middle; text-align: right;">车辆照片4:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="car_pic4" readonly="readonly" class="input_" value=""/>
						<div id="btnUp6" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit">浏览</div>
						<span id="car_pic4_msg" style="color:#ff5555;"></span>
					</td>
				</tr>
				<!-- 第三行图片预览 -->
				<tr style="text-align: center;">
					<c:choose>
						<c:when test="${carpic[2].pic==null}">
							<th></th>
							<td></td>
						</c:when>
						<c:otherwise>
							<th style="vertical-align: middle; text-align: right;">图片预览:</th>
							<td><img class="previewImg" src="${carpic[2].pic}"/></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${carpic[3].pic==null}">
							<th></th>
							<td></td>
						</c:when>
						<c:otherwise>
							<th style="vertical-align: middle; text-align: right;">图片预览:</th>
							<td><img class="previewImg" src="${carpic[3].pic}"/></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<!-- 第四行图片 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">车辆行驶证1:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="carcard_pic1" readonly="readonly" class="input_" value=""/>
						<div id="btnUp7" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit">浏览</div>
						<span id="carcard_pic1_msg" style="color:#ff5555;"></span>
					</td>
					
					<th style="vertical-align: middle; text-align: right;">车辆行驶证2:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="carcard_pic2" readonly="readonly" class="input_" value=""/>
						<div id="btnUp8" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit">浏览</div>
						<span id="carcard_pic2_msg" style="color:#ff5555;"></span>
					</td>
				</tr>
				<!-- 第四行图片预览 -->
				<tr style="text-align: center;">
					<c:choose>
						<c:when test="${carcardpic[0].pic==null}">
							<th></th>
							<td></td>
						</c:when>
						<c:otherwise>
							<th style="vertical-align: middle; text-align: right;">图片预览:</th>
							<td><img class="previewImg" src="${carcardpic[0].pic}"/></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${carcardpic[1].pic==null}">
							<th></th>
							<td></td>
						</c:when>
						<c:otherwise>
							<th style="vertical-align: middle; text-align: right;">图片预览:</th>
							<td><img class="previewImg" src="${carcardpic[1].pic}"/></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<!-- 第五行图片 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">车辆行驶证3:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="carcard_pic3" readonly="readonly" class="input_" value=""/>
						<div id="btnUp9" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit">浏览</div>
						<span id="carcard_pic3_msg" style="color:#ff5555;"></span>
					</td>
					
					<th style="vertical-align: middle; text-align: right;">其他1:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="other_pic1" readonly="readonly" class="input_" value=""/>
						<div id="btnUp10" style="width: 50px; display: inline-block;border-radius:4px;" class="btnsubmit">浏览</div>
						<span id="other_pic1_msg" style="color:#ff5555;"></span>
					</td>
				</tr>
				<!-- 第五行图片预览 -->
				<tr style="text-align: center;">
					<c:choose>
						<c:when test="${carcardpic[3].pic==null}">
							<th></th>
							<td></td>
						</c:when>
						<c:otherwise>
							<th style="vertical-align: middle; text-align: right;">图片预览:</th>
							<td><img class="previewImg" src="${carcardpic[3].pic}"/></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${otherpic[0].pic==null}">
							<th></th>
							<td></td>
						</c:when>
						<c:otherwise>
							<th style="vertical-align: middle; text-align: right;">图片预览:</th>
							<td><img class="previewImg" src="${otherpic[0].pic}"/></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</table>
		</form>		
		
		<div align="center" style="padding: 20px;">
			<input type="button" value="保存" id="subBtn"/>
			<input type="button" value="关闭"   id="clsBtn" style="margin-left:50px;"/>
		</div>
	</div>
</div>
</body>
</html>