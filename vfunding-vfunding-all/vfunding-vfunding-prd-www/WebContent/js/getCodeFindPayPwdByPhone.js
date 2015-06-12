/**
 * 获取注册验证码
 * 
 * @param mobileInputId
 */
function getPhoneCode() {
	$.getJSON('/checkCanGetCodeForFindPayPwd', function(data) {
		if (data) {
			$.get('/verification/getVerifyCodeForFindPayPwd', function(data) {
				var wait = 60;
				var buttonObject = $('#phoneCode');
				doWait(buttonObject, wait);
			});
		} else {
			$('#username').focus();
		}
	});

}
/**
 * 获取注册语音验证码
 * 
 * @param mobileInputId
 */
function getVoiceCode() {
	$.getJSON('/checkCanGetCodeForFindPayPwd', function(data) {
		if (data) {
			$.get('/verification/getVerifyCodeByVoiceForFindPayPwd', function(
					data) {
				var wait = 60;
				var buttonObject = $('#phoneCode');
				doWait(buttonObject, wait);
			});
		} else {
			$('#username').focus();
		}
	});

}

function doWait(buttonObject, wait) {
	if (wait == 0) {
		buttonObject.attr({
			href : "javascript:getPhoneCode();"
		});
		buttonObject.removeAttr("style");

		$('#voiceCode').attr({
			href : "javascript:getVoiceCode();"
		});
		$('#voiceCode').removeAttr("style");
		wait = 60;
		$('#waitMsg').html("");
	} else {
		buttonObject.attr({
			href : "javascript:;",
			style : "background-color: #EEEEEE"
		});
		$('#voiceCode').attr({
			href : "javascript:;",
			style : "background-color: #EEEEEE"
		});
		$('#waitMsg').html(wait + " 秒后，重新获取验证码");
		wait--;
		setTimeout(function() {
			doWait(buttonObject, wait);
		}, 1000)
	}
}