/**
 * 上传JS
 */
$(function() {
	var button = $('#btnUp'), interval;
	new AjaxUpload(button, {
		action : '/user/uploadAttestation',
		name : 'files',
		onSubmit : function(file, ext) {
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF|bmp|BMP)$/
					.test(ext))) {
				alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
				return false;
			}
			// change button text, when user selects file
			button.text('上传');

			// If you want to allow uploading only 1 file at time,
			// you can disable upload button
			this.disable();

			// Uploding -> Uploading. -> Uploading...
			interval = window.setInterval(function() {
				var text = button.text();
				if (text.length < 10) {
					button.text(text + '...');
				} else {
					button.text('上传');
				}
			}, 200);
		},
		onComplete : function(file, response) {
			var res= $.parseJSON(response);
			if (res.success) {
				// file 本地文件名称，response 服务器端传回的信息
				button.text('浏览');
				window.clearInterval(interval);
				// enable upload button
				this.enable();
				$("#txtFileName").val(file);
				// $("<img />").appendTo($('#imglist')).attr("src", k);
			}

		}
	});
});
