/**
 * 上传JS--1
 */
$(function() {
	//身份证正面
	var button = $('#btnUp1'), interval;
	new AjaxUpload(button, {
		action : '/system/week/weekBorrowUploadAttestation',
		name : 'files',
		data:{
			'name':'card_pic1'
		},
		onSubmit : function(file, ext) {
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF|bmp|BMP)$/
					.test(ext))) {
				alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
				return false;
			}
			button.text('上传');
			this.disable();
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
				button.text('浏览');
				window.clearInterval(interval);
				this.enable();
				$("#card_pic1").val(file);
			}

		}
	});
	//身份证反面
	var button2 = $('#btnUp2'), interval;
	new AjaxUpload(button2, {
		action : '/system/week/weekBorrowUploadAttestation',
		name : 'files',
		data:{
			'name':'card_pic2'
		},
		onSubmit : function(file, ext) {
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF|bmp|BMP)$/
					.test(ext))) {
				alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
				return false;
			}
			button2.text('上传');
			this.disable();
			interval = window.setInterval(function() {
				var text = button2.text();
				if (text.length < 10) {
					button2.text(text + '...');
				} else {
					button2.text('上传');
				}
			}, 200);
		},
		onComplete : function(file, response) {
			var res= $.parseJSON(response);
			if (res.success) {
				button2.text('浏览');
				window.clearInterval(interval);
				this.enable();
				$("#card_pic2").val(file);
			}

		}
	});
	//车辆照片1
	var button3 = $('#btnUp3'), interval;
	new AjaxUpload(button3, {
		action : '/system/week/weekBorrowUploadAttestation',
		name : 'files',
		data:{
			'name':'car_pic1'
		},
		onSubmit : function(file, ext) {
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF|bmp|BMP)$/
					.test(ext))) {
				alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
				return false;
			}
			button3.text('上传');
			this.disable();
			interval = window.setInterval(function() {
				var text = button3.text();
				if (text.length < 10) {
					button3.text(text + '...');
				} else {
					button3.text('上传');
				}
			}, 200);
		},
		onComplete : function(file, response) {
			var res= $.parseJSON(response);
			if (res.success) {
				button3.text('浏览');
				window.clearInterval(interval);
				this.enable();
				$("#car_pic1").val(file);
			}

		}
	});
	//车辆照片2
	var button4 = $('#btnUp4'), interval;
	new AjaxUpload(button4, {
		action : '/system/week/weekBorrowUploadAttestation',
		name : 'files',
		data:{
			'name':'car_pic2'
		},
		onSubmit : function(file, ext) {
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF|bmp|BMP)$/
					.test(ext))) {
				alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
				return false;
			}
			button4.text('上传');
			this.disable();
			interval = window.setInterval(function() {
				var text = button4.text();
				if (text.length < 10) {
					button4.text(text + '...');
				} else {
					button4.text('上传');
				}
			}, 200);
		},
		onComplete : function(file, response) {
			var res= $.parseJSON(response);
			if (res.success) {
				button4.text('浏览');
				window.clearInterval(interval);
				this.enable();
				$("#car_pic2").val(file);
			}

		}
	});
	//车辆照片3
	var button5 = $('#btnUp5'), interval;
	new AjaxUpload(button5, {
		action : '/system/week/weekBorrowUploadAttestation',
		name : 'files',
		data:{
			'name':'car_pic3'
		},
		onSubmit : function(file, ext) {
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF|bmp|BMP)$/
					.test(ext))) {
				alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
				return false;
			}
			button5.text('上传');
			this.disable();
			interval = window.setInterval(function() {
				var text = button5.text();
				if (text.length < 10) {
					button5.text(text + '...');
				} else {
					button5.text('上传');
				}
			}, 200);
		},
		onComplete : function(file, response) {
			var res= $.parseJSON(response);
			if (res.success) {
				button5.text('浏览');
				window.clearInterval(interval);
				this.enable();
				$("#car_pic3").val(file);
			}

		}
	});
	//车辆照片4
	var button6= $('#btnUp6'), interval;
	new AjaxUpload(button6, {
		action : '/system/week/weekBorrowUploadAttestation',
		name : 'files',
		data:{
			'name':'car_pic4'
		},
		onSubmit : function(file, ext) {
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF|bmp|BMP)$/
					.test(ext))) {
				alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
				return false;
			}
			button6.text('上传');
			this.disable();
			interval = window.setInterval(function() {
				var text = button6.text();
				if (text.length < 10) {
					button6.text(text + '...');
				} else {
					button6.text('上传');
				}
			}, 200);
		},
		onComplete : function(file, response) {
			var res= $.parseJSON(response);
			if (res.success) {
				button6.text('浏览');
				window.clearInterval(interval);
				this.enable();
				$("#car_pic4").val(file);
			}

		}
	});
	//车辆行驶证1
	var button7= $('#btnUp7'), interval;
	new AjaxUpload(button7, {
		action : '/system/week/weekBorrowUploadAttestation',
		name : 'files',
		data:{
			'name':'carcard_pic1'
		},
		onSubmit : function(file, ext) {
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF|bmp|BMP)$/
					.test(ext))) {
				alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
				return false;
			}
			button7.text('上传');
			this.disable();
			interval = window.setInterval(function() {
				var text = button7.text();
				if (text.length < 10) {
					button7.text(text + '...');
				} else {
					button7.text('上传');
				}
			}, 200);
		},
		onComplete : function(file, response) {
			var res= $.parseJSON(response);
			if (res.success) {
				button7.text('浏览');
				window.clearInterval(interval);
				this.enable();
				$("#carcard_pic1").val(file);
			}

		}
	});
	//车辆行驶证2
	var button8= $('#btnUp8'), interval;
	new AjaxUpload(button8, {
		action : '/system/week/weekBorrowUploadAttestation',
		name : 'files',
		data:{
			'name':'carcard_pic2'
		},
		onSubmit : function(file, ext) {
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF|bmp|BMP)$/
					.test(ext))) {
				alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
				return false;
			}
			button8.text('上传');
			this.disable();
			interval = window.setInterval(function() {
				var text = button8.text();
				if (text.length < 10) {
					button8.text(text + '...');
				} else {
					button8.text('上传');
				}
			}, 200);
		},
		onComplete : function(file, response) {
			var res= $.parseJSON(response);
			if (res.success) {
				button8.text('浏览');
				window.clearInterval(interval);
				this.enable();
				$("#carcard_pic2").val(file);
			}

		}
	});
	//车辆行驶证3
	var button9= $('#btnUp9'), interval;
	new AjaxUpload(button9, {
		action : '/system/week/weekBorrowUploadAttestation',
		name : 'files',
		data:{
			'name':'carcard_pic3'
		},
		onSubmit : function(file, ext) {
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF|bmp|BMP)$/
					.test(ext))) {
				alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
				return false;
			}
			button9.text('上传');
			this.disable();
			interval = window.setInterval(function() {
				var text = button9.text();
				if (text.length < 10) {
					button9.text(text + '...');
				} else {
					button9.text('上传');
				}
			}, 200);
		},
		onComplete : function(file, response) {
			var res= $.parseJSON(response);
			if (res.success) {
				button9.text('浏览');
				window.clearInterval(interval);
				this.enable();
				$("#carcard_pic3").val(file);
			}

		}
	});
	//其他1
	var button10= $('#btnUp10'), interval;
	new AjaxUpload(button10, {
		action : '/system/week/weekBorrowUploadAttestation',
		name : 'files',
		data:{
			'name':'other_pic1'
		},
		onSubmit : function(file, ext) {
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF|bmp|BMP)$/
					.test(ext))) {
				alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
				return false;
			}
			button10.text('上传');
			this.disable();
			interval = window.setInterval(function() {
				var text = button10.text();
				if (text.length < 10) {
					button10.text(text + '...');
				} else {
					button10.text('上传');
				}
			}, 200);
		},
		onComplete : function(file, response) {
			var res= $.parseJSON(response);
			if (res.success) {
				button10.text('浏览');
				window.clearInterval(interval);
				this.enable();
				$("#other_pic1").val(file);
			}

		}
	});
	//
});

