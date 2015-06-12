<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/week/addOrEditWeek.js" />
<!-- 短期理财计划新增或编辑页面 -->
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow-x: hidden;">
		<form id="addoreditform" method="post" style="text-align: center;">
			<input type="hidden" value="${week.id}" id="id" name="id"/>
			<table class="table table-hover table-condensed" style="text-align: center; ">
				<!-- 第一行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">标题:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="name" name="name"
						data-options="required:true,validType:['String','length[0,16]']"
						class="easyui-validatebox" 
						placeholder="请输入计划标题"
						value="${week.name}" />
					</td>
					
					<th style="vertical-align: middle; text-align: right;">天数:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="timeLimit" name="timeLimit"
						data-options="required:true,validType:['Integer','length[0,1]']"
						class="easyui-numberbox"
						placeholder="请输入计划天数"
						value="${week.timeLimit}" />天
					</td>
				</tr>
				<!-- 第二行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">每份价值:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="sharePrice" name="sharePrice"
						data-options="required:true,validType:['Integer','length[0,4]']"
						class="easyui-numberbox"  precision="2"
						placeholder="请输入每份价值"
						value="${week.sharePrice}" />元
					</td>
					<th></th>
					<td></td>					
				</tr>
				<!-- 第三行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">认购基数:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="buyBase" name="buyBase"
						data-options="required:true,validType:['Integer','length[0,4]']"
						class="easyui-numberbox"
						placeholder="请输入认购基数"
						value="${week.buyBase}" />份
					</td>
					
					<th style="vertical-align: middle; text-align: right;">计划总份数:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="shareCount" name="shareCount"
						data-options="required:true,validType:['Integer','length[0,8]']"
						class="easyui-numberbox"
						placeholder="请输入计划总份数"
						value="${week.shareCount}" />份
					</td>
				</tr>
				<!-- 第四行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;"> 个人最小投资份数:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="singleMin" name="singleMin"
						data-options="required:true,validType:['Integer','length[0,8]']"
						class="easyui-numberbox"
						placeholder="请输入个人最小投资份数"
						value="${week.singleMin}" />份
					</td>

					<th style="vertical-align: middle; text-align: right;">个人最大投资份数:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="singleMax" name="singleMax"
						data-options="required:true,validType:['Integer','length[0,8]']"
						class="easyui-numberbox"
						placeholder="请输入个人最大投资份数"
						value="${week.singleMax}" />份
					</td>
				</tr>
				<!-- 第五行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;"> 发售开始时间:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="saleTime" name="saleTime"
						data-options="required:true,validType:['String','length[0,40]']"
						class="easyui-validatebox" 
						placeholder="请输入发售开始时间"
						value="${formatSaleTime}"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						readonly="readonly"
						style="cursor:pointer;"
						/>
					</td>
					
					<th style="vertical-align: middle; text-align: right;">发售截止时间 :</th>
					<td style="vertical-align: middle;">
						<input type="text" id="expireTime" name="expireTime"
						data-options="required:true,validType:['String','length[0,40]']"
						class="easyui-validatebox" 
						placeholder="请输入发售截止时间 "
						value="${formatExpireTime}"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						readonly="readonly"
						style="cursor:pointer;"
						 />
					</td>
				</tr>
				<!-- 第六行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;"> 资金入账账户:</th>
					<td style="vertical-align: middle;">
						<select id="holderUser" name="holderUser" style="width: 120px; height: 28px;">
							<option value=""></option>
							<c:choose>
								<c:when test="${week.holderUser==20}">
									<option selected="selected" value="20">vf1010</option>
								</c:when>
								<c:otherwise>
									<option value="20">vf1010</option>
								</c:otherwise>
							</c:choose>							
						</select> 
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr style="height:50px;">
					<th></th>
					<td></td>
					<th></th>
					<td></td>
				</tr>
				<!-- 第七行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">产品年化收益率:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="weekRate" name="weekRate"
						data-options="required:true,validType:['Integer','length[0,8]']"
						class="easyui-numberbox"  precision="2"
						placeholder="请输入年化收益率"
						value="${weekRate.weekRate}" />%
					</td>
					<th></th>
					<td></td>
				</tr>
				<!-- 第八行 -->
				<tr style="text-align: center;">
					<th style="vertical-align: middle; text-align: right;">服务费率:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="platformRate" name="platformRate"
						data-options="required:true,validType:['Integer','length[0,8]']"
						class="easyui-numberbox"  precision="2"
						placeholder="请输入服务费率"
						value="${weekRate.platformRate}" />%
					</td>
					
					<th style="vertical-align: middle; text-align: right;">担保费率:</th>
					<td style="vertical-align: middle;">
						<input type="text" id="guaranteeRate" name="guaranteeRate"
						data-options="required:true,validType:['Integer','length[0,8]']"
						class="easyui-numberbox"  precision="2"
						placeholder="请输担保费率"
						value="${weekRate.guaranteeRate}" />%
					</td>
				</tr>
			</table>

		</form>
	</div>
</div>