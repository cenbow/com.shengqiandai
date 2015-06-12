$(document).ready(function(){
	var flag = true;
	$('#account').keyup(function(){
		this.value=this.value.split('.')[0];
		//this.value=this.value.replace(/\D/g,'');
		$.ajax({
			url : "/borrow/getMoneyUppercase",
			type : "post",
			data : {
				"account" : this.value
			},
			success : function(result) {
				result = $.parseJSON(result);
				$('#amountCase').val(result.msg);
			}
		});
	});
	
	$('#account').keyup(function(){
		this.value=this.value.split('.')[0];
		//this.value=this.value.replace(/\D/g,'');
		if(this.value != ''){
			$('#account_msg').html('');
		} else {
			$('#account_msg').html('<span style="color:#ff5555;">借款金额不能为空</span>');
		}
	});
	$('#apr').keyup(function(){
		if(this.value != ''){
			$('#apr_msg').html('');
		} else {
			$('#apr_msg').html('<span style="color:#ff5555;">借款利率不能为空</span>');
		}
	});
	$('#dy-kind').change(function(){
		if(this.value != ''){
			$('#dy-kind_msg').html('');
		}
	});
	$('#b-limit').change(function(){
		if(this.value != ''){
			$('#b-limit_msg').html('');
		}
	});
	$('#validTime').change(function(){
		if(this.value != ''){
			$('#validTime_msg').html('');
		}
	});
	$('#pay-type').change(function(){
		if(this.value != ''){
			$('#pay-type_msg').html('');
		}
	});
	$('#yes-no').change(function(){
		if(this.value != ''){
			$('#yes-no_msg').html('');
		}
	});
	$('#tenderMoney').change(function(){
		if(this.value != ''){
			$('#tenderMoney_msg').html('');
		}
	});
	$('#borrow-use').change(function(){
		if(this.value != ''){
			$('#borrow-use_msg').html('');
		}
	});
	$('#carHost').keyup(function(){
		if(this.value != ''){
			$('#carHost_msg').html('');
		} else {
			$('#carHost_msg').html('<span style="color:#ff5555;">车主姓名不能为空</span>');
		}
	});
	$('#idcarHost').keyup(function(){
		if(this.value != ''){
			$('#idcarHost_msg').html('');
		} else {
			$('#idcarHost_msg').html('<span style="color:#ff5555;">身份证号码不能为空</span>');
		}
	});
	$('#idcarHost').blur(function(){
		if(!isCardValidate(this.value)){
			$('#idcarHost_msg').html('<span style="color:#ff5555;">身份证号码不正确</span>');
			flag = false;
		} else {
			$('#idcarHost_msg').html('');
		}
	});
	$('#phoneHost').blur(function(){
		var reg= /^[1][3458]\d{9}$/;
		if(this.value != '' && reg.test(this.value)){
			$('#phoneHost_msg').html('');
		} else {
			$('#phoneHost_msg').html('<span style="color:#ff5555;">手机号码不正确</span>');
		}
	});
	
	$('#jiguan').keyup(function(){
		if(this.value != ''){
			$('#jiguan_msg').html('');
		} else {
			$('#jiguan_msg').html('<span style="color:#ff5555;">籍贯不能为空</span>');
		}
	});
	$('#chejia').keyup(function(){
		if(this.value != ''){
			$('#chejia_msg').html('');
		} else {
			$('#chejia_msg').html('<span style="color:#ff5555;">车架号不能为空</span>');
		}
	});
	$('#carNo').keyup(function(){
		if(this.value != ''){
			$('#carNo_msg').html('');
		} else {
			$('#carNo_msg').html('<span style="color:#ff5555;">车牌号不能为空</span>');
		}
	});
	$('#enginNo').keyup(function(){
		if(this.value != ''){
			$('#enginNo_msg').html('');
		} else {
			$('#enginNo_msg').html('<span style="color:#ff5555;">发动机号不能为空</span>');
		}
	});
	$('#borrowTitle').keyup(function(){
		if(this.value != ''){
			$('#borrowTitle_msg').html('');
		} else {
			$('#borrowTitle_msg').html('<span style="color:#ff5555;">标题不能为空</span>');
		}
	});
	$('#borroweEx').keyup(function(){
		if(this.value != ''){
			$('#borroweEx_msg').html('');
		} else {
			$('#borroweEx_msg').html('<span style="color:#ff5555;">借款描述不能为空</span>');
		}
	});
	$('#zhuceDate').focus(function(){
		if(this.value != ''){
			$('#zhuceDate_msg').html('');
		}
	});
	$('#fazhengDate').focus(function(){
		if(this.value != ''){
			$('#fazhengDate_msg').html('');
		}
	});
	$('#jianyanDate').focus(function(){
		if(this.value != ''){
			$('#jianyanDate_msg').html('');
		}
	});
	
	$('#release').submit(function(){
		var reg= /^[1][3458]\d{9}$/;
		if($('#account').val() == ''){
			$('#account').focus();
			$('#account_msg').html('<span style="color:#ff5555;">借款金额不能为空</span>');
			return false;
		}
		if($('#apr').val() == ''){
			$('#apr').focus();
			$('#apr_msg').html('<span style="color:#ff5555;">年利率不能为空</span>');
			return false;
		}
		if($('#dy-kind').val() == -1){
			window.scroll(0,200);
			$('#dy-kind_msg').html('<span style="color:#ff5555;">请选择抵押品种</span>');
			return false;
		}
		if($('#b-limit').val() == -1){
			window.scroll(0,200);
			$('#b-limit_msg').html('<span style="color:#ff5555;">请选择借款期限</span>');
			return false;
		}
		if($('#validTime').val() == -1){
			window.scroll(0,200);
			$('#validTime_msg').html('<span style="color:#ff5555;">请选择有效时间</span>');
			return false;
		}
		if($('#pay-type').val() == -1){
			window.scroll(0,200);
			$('#pay-type_msg').html('<span style="color:#ff5555;">请选择还款方式</span>');
			return false;
		}
		if($('#yes-no').val() == -1){
			window.scroll(0,200);
			$('#yes-no_msg').html('<span style="color:#ff5555;">请选择是否担保</span>');
			return false;
		}
		if($('#tenderMoney').val() == -1){
			window.scroll(0,200);
			$('#tenderMoney_msg').html('<span style="color:#ff5555;">请选择最低投标金额</span>');
			return false;
		}
		if($('#dy-kind').val() == '债权抵押'){
			if($('#contractStart').val() ==''){
				window.scroll(0,200);
				$('#contractStart_msg').html('<span style="color:#ff5555;">请选择合同结束日期</span>');
				return false;
			} else if($('#contractEnd').val() == ''){
				window.scroll(0,200);
				$('#contractEnd_msg').html('<span style="color:#ff5555;">请选择合同结束日期</span>');
				return false;
			}
		}
		if($('#borrow-use').val() == -1){
			window.scroll(0,200);
			$('#borrow-use_msg').html('<span style="color:#ff5555;">请选择借款用途</span>');
			return false;
		}
		if($('#carHost').val() == ''){
			$('#carHost').focus();
			$('#carHost_msg').html('<span style="color:#ff5555;">车主姓名不能为空</span>');
			return false;
		}
		if($('#idcarHost').val() == ''){
			$('#idcarHost').focus();
			$('#idcarHost_msg').html('<span style="color:#ff5555;">身份证号码不能为空</span>');
			return false;
		}
		if(!isCardValidate($('#idcarHost').val())){
			$('#idcarHost').focus();
			$('#idcarHost_msg').html('<span style="color:#ff5555;">身份证号码不正确</span>');
			return false;
		}
		if($('#jiguan').val() == ''){
			$('#jiguan').focus();
			$('#jiguan_msg').html('<span style="color:#ff5555;">车主籍贯不能为空</span>');
			return false;
		}
		if($('#chejia').val() == ''){
			$('#chejia').focus();
			$('#chejia_msg').html('<span style="color:#ff5555;">车主籍贯不能为空</span>');
			return false;
		}
		if($('#phoneHost').val() == ''){
			$('#phoneHost').focus();
			$('#phoneHost_msg').html('<span style="color:#ff5555;">手机号码不能为空</span>');
			return false;
		}
		if(!reg.test($('#phoneHost').val())){
			$('#phoneHost').focus();
			$('#phoneHost_msg').html('<span style="color:#ff5555;">手机号码不正确</span>');
			return false;
		}
		if($('#carNo').val() == ''){
			$('#carNo').focus();
			$('#carNo_msg').html('<span style="color:#ff5555;">车牌号不能为空</span>');
			return false;
		}
		if($('#enginNo').val() == ''){
			$('#enginNo').focus();
			$('#enginNo_msg').html('<span style="color:#ff5555;">发动机号不能为空</span>');
			return false;
		}
		if($('#zhuceDate').val() == ''){
			$('#zhuceDate').focus();
			$('#zhuceDate_msg').html('<span style="color:#ff5555;">注册日期不能为空</span>');
			return false;
		}
		if($('#fazhengDate').val() == ''){
			$('#fazhengDate').focus();
			$('#fazhengDate_msg').html('<span style="color:#ff5555;">发证日期不能为空</span>');
			return false;
		}
		if($('#jianyanDate').val() == ''){
			$('#jianyanDate').focus();
			$('#jianyanDate_msg').html('<span style="color:#ff5555;">检验有效日期不能为空</span>');
			return false;
		}
		if($('#borrowTitle').val() == ''){
			$('#borrowTitle').focus();
			$('#borrowTitle_msg').html('<span style="color:#ff5555;">借款标题不能为空</span>');
			return false;
		}
		if($('#borroweEx').val() == ''){
			$('#borroweEx').focus();
			$('#borroweEx_msg').html('<span style="color:#ff5555;">借款描述不能为空</span>');
			return false;
		}
		/*
		 * 图片不做控制
		 * if($('#card_pic1').val() == ''){
			$('#card_pic1').focus();
			$('#card_pic1_msg').html('身份证图片不能为空');
			return false;
		}
		if($('#card_pic2').val() == ''){
			$('#card_pic2').focus();
			$('#card_pic2_msg').html('身份证图片不能为空');
			return false;
		}
		if($('#carcard_pic1').val() == ''){
			$('#carcard_pic1').focus();
			$('#carcard_pic1_msg').html('车辆行驶证不能为空');
			return false;
		}
		if($('#car_pic1').val() == ''){
			$('#car_pic1').focus();
			$('#car_pic1_msg').html('车辆照片不能为空');
			return false;
		}
		if($('#car_pic2').val() == ''){
			$('#car_pic2').focus();
			$('#car_pic2_msg').html('车辆照片不能为空');
			return false;
		}
		if($('#car_pic3').val() == ''){
			$('#car_pic3').focus();
			$('#car_pic3_msg').html('车辆照片不能为空');
			return false;
		}
		if($('#car_pic4').val() == ''){
			$('#car_pic4').focus();
			$('#car_pic4_msg').html('车辆照片不能为空');
			return false;
		}*/
		if(parseFloat($('#credit_').val()) < parseInt($('#account').val())){
			window.scroll(0,0);
			$('#amount_msg').html('借款金额不能超过可借总额');
			return false;
		}
		if(flag){
			return true;
		}
	});
	
	
	
	
});