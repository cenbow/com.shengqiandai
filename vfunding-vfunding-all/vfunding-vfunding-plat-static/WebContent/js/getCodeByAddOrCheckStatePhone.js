var mobile;
function checkPhone(mobileInputId) {
	mobile = $.trim($('#' + mobileInputId).val());
	if (mobile == '' || mobile.length == 0) {
		$('#' + mobileInputId).focus();
		return false;
	} else {
		var reg = /^(1[3|5|8])\d{9}$/;
		if (!reg.test(mobile)) {
			$('#' + mobileInputId).focus();
			return false;
		} else {
			return true;
		}
	}

}

/**
 * 获取手机验证码 
 * @param mobileInputId
 */
function getCodeByPhone(mobileInputId) {
	if (checkPhone(mobileInputId)) {
		$.get('/verification/getVCodeForPhoneState/'+mobile, function(data) {
			var wait = 60;
			var buttonObject = $('#phoneCode');
			doWait(buttonObject, wait);
		});
	}

}
/***
 * 获取语音验证码
 * @param mobileInputId
 */
function getCodeByVoice(mobileInputId) {
	if (checkPhone(mobileInputId)) {
		$.get('/verification/getVCodeByVoiceForPhoneState/'+mobile, function(data) {
			var wait = 60;
			var buttonObject = $('#phoneCode');
			doWait(buttonObject, wait);
		});
	}

}

function doWait(buttonObject, wait) {
	if (wait == 0) {
		buttonObject.attr({ href: "javascript:getCodeByPhone('mobile');"});
		buttonObject.removeAttr("style");
		
		$('#voiceCode').attr({ href: "javascript:getCodeByVoice('mobile');"});
		$('#voiceCode').removeAttr("style");
		wait = 60;
		$('#waitMsg').html("");
	} else {
		buttonObject.attr({ href: "javascript:;" ,style:"background-color: #EEEEEE"});
		$('#voiceCode').attr({ href: "javascript:;",style:"background-color: #EEEEEE"});
		$('#waitMsg').html(wait+" 秒后，重新获取验证码");
		wait--;
		setTimeout(function() {
			doWait(buttonObject, wait);
		}, 1000)
	}
}