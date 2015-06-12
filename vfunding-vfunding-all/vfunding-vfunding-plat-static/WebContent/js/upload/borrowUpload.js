/**
 * 上传JS--1
 */
$(function() {
	var button = $('#btnUp1'), interval;
	new AjaxUpload(button, {
		action : '/user/borrowUploadAttestation',
		name : 'files',
		data:{
			'name':'card_pic1','width':'800','height':600
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
});
/**
 * 上传JS--2
 */
$(function() {
	var button = $('#btnUp2'), interval;
	new AjaxUpload(button, {
		action : '/user/borrowUploadAttestation',
		name : 'files',
		data:{
			'name':'car_pic1','width':800,'height':600
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
				$("#car_pic1").val(file);
			}
		}
	});
});
/**
 * 上传JS--3
 */
$(function() {
	var button = $('#btnUp3'), interval;
	new AjaxUpload(button, {
		action : '/user/borrowUploadAttestation',
		name : 'files',
		data:{
			'name':'card_pic2','width':800,'height':600
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
				$("#card_pic2").val(file);
			}
		}
	});
});
/**
 * 上传JS--4
 */
$(function() {
	var button = $('#btnUp4'), interval;
	new AjaxUpload(button, {
		action : '/user/borrowUploadAttestation',
		name : 'files',
		data:{
			'name':'car_pic2','width':800,'height':600
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
				$("#car_pic2").val(file);
			}
		}
	});
});
/**
 * 上传JS--5
 */
$(function() {
	var button = $('#btnUp5'), interval;
	new AjaxUpload(button, {
		action : '/user/borrowUploadAttestation',
		name : 'files',
		data:{
			'name':'carcard_pic1','width':800,'height':600
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
				$("#carcard_pic1").val(file);
			}
		}
	});
});
/**
 * 上传JS--6
 */
$(function() {
	var button = $('#btnUp6'), interval;
	new AjaxUpload(button, {
		action : '/user/borrowUploadAttestation',
		name : 'files',
		data:{
			'name':'car_pic3','width':800,'height':600
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
				$("#car_pic3").val(file);
			}
		}
	});
});
/**
 * 上传JS--7
 */
$(function() {
	var button = $('#btnUp7'), interval;
	new AjaxUpload(button, {
		action : '/user/borrowUploadAttestation',
		name : 'files',
		data:{
			'name':'carcard_pic2','width':800,'height':600
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
				$("#carcard_pic2").val(file);
			}
		}
	});
});
/**
 * 上传JS--8
 */
$(function() {
	var button = $('#btnUp8'), interval;
	new AjaxUpload(button, {
		action : '/user/borrowUploadAttestation',
		name : 'files',
		data:{
			'name':'car_pic4','width':800,'height':600
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
				$("#car_pic4").val(file);
			}
		}
	});
});
/**
 * 上传JS--9
 */
$(function() {
	var button = $('#btnUp9'), interval;
	new AjaxUpload(button, {
		action : '/user/borrowUploadAttestation',
		name : 'files',
		data:{
			'name':'carcard_pic3','width':800,'height':600
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
				$("#carcard_pic3").val(file);
			}
		}
	});
});
/**
 * 上传JS--10
 */
$(function() {
	var button = $('#btnUp10'), interval;
	new AjaxUpload(button, {
		action : '/user/borrowUploadAttestation',
		name : 'files',
		data:{
			'name':'other1','width':800,'height':600
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
				$("#other1").val(file);
			}
		}
	});
});
/**
 * 上传JS--11
 */
$(function() {
	var button = $('#btnUp11'), interval;
	new AjaxUpload(button, {
		action : '/user/borrowUploadAttestation',
		name : 'files',
		data:{
			'name':'other2','width':800,'height':600
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
				$("#other2").val(file);
			}
		}
	});
});
/**
 * 上传JS--12
 */
$(function() {
	var button = $('#btnUp12'), interval;
	new AjaxUpload(button, {
		action : '/user/borrowUploadAttestation',
		name : 'files',
		data:{
			'name':'other3','width':800,'height':600
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
				$("#other3").val(file);
			}
		}
	});
});
/**
 * 上传JS--标图片(暂时不上传标图片)
 */
/*$(function() {
	var button = $('#bpic'), interval;
	new AjaxUpload(button, {
		action : '/user/uploadAttestation',
		name : 'files',
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
				$("#litpic").val(file);
			}
		}
	});
});*/
