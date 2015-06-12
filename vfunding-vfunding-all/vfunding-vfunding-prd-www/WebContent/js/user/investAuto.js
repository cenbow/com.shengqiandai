$(function() {
		$("#select1,#select2,#select3,#select4,#select5,#select6,#select7,#select8,#open-status").chosen({
			no_results_text : "没有找到",
			allow_single_de : true
		});
		
		$('#investNum').keyup(function(){
			if(this.value == ''){
				$('#tenderAccount').html('投标金额不能为空');
			} else {
				$('#tenderAccount').html('');
			}
		});
		$('#sub').click(function(){
			var flag = true;
			if($('#investNum').val()==''||$('#investNum').val() == null){
				$('#tenderAccount').html('投标金额不能为空');
				flag = false;
			} else if($('#investNum').val() < 50){
				$('#tenderAccount').html('投标金额不能小于50元');
				flag = false;
			}
			if($('#select3').val() == ''||$('#select4').val() == ''){
				$('#time_limit').html('借款期限不能为空');
				flag = false;
			}
			if(parseInt($('#select3').val())>parseInt($('#select4').val())){
				$('#time_limit').html('借款期限范围不正确');
				flag = false;
			}
			if($('#select5').val() == ''||$('#select6').val() == ''){
				$('#apr').html('年利率不能为空');
				flag = false;
			}
			if(parseInt($('#select5').val())>parseInt($('#select6').val())){
				$('#apr').html('年利率范围不正确');
				flag = false;
			}
			if($('#select7').val() == ''){
				$('#style').html('还款方式不能为空');
				flag = false;
			}
			if($("input[name='status']:checked").val()==undefined){
				flag=false;
				art.dialog({
					icon: 'face-smile',
					title:'微积金提示',
					left: '48%',
					content : "投标状态不能为空",
					lock: true,
					button: [{
						name:'确定'
					}]
				});
			}
			if(flag){
				$.ajax({
					url : "/borrowAuto/changeBorrowAuto",
					type : "post",
					data : $('#autoForm').serialize(),
					success : function(result) {
						result = $.parseJSON(result);
						art.dialog({
							icon: 'face-smile',
							title:'微积金提示',
							left: '48%',
							content : result.msg,
							lock: true,
							button: [{
								name:'确定',
								callback : function() {
									window.location.href = '/borrowAuto/borrowAuto';
								},
								focus: true
							}]
						});
					}
				});
			}
		});
	})