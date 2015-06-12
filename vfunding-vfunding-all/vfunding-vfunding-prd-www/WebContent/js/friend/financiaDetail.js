function upfinancia() {
	$.ajax({
		url : '/friend/upfinancia',
		cache : false,
		async : false,
		type : 'get',
		error : function(errorLog) {
			art.dialog({
				content : errorLog.responseText,
				icon : 'warning',
				left : '48%',
				ok : true,
				fixed : true,
				lock : true
			// 为true等价于function(){}
			});
		},
		success : function(result) {
			result = $.parseJSON(result);
			art.dialog({
				icon : 'face-smile',
				left : '48%',
				content : result,
				lock : true,
				ok : function() {
					window.location.href = "/friend/financiaDetail";
				}
			});

		},
		contentType : "application/x-www-form-urlencoded;charset=UTF-8"
	});
}
