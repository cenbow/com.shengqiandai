
$(function(){
	var name = ["微积金","银行活期","银行定期","货币基金","余额宝"],
	gray = '#e7ecef',
	blue = '#345d7d',
	colors = [blue,blue,blue,blue,blue,blue,blue],
	value = [0, 0, 0, 0, 0],
	maxs = 40;
	var rep = $('#RepaymentBox'), html = '';
	var reCast = $('#reCastBox');
	for(var i=0; i<name.length; i++){
		html += '<li><div class="yCheckbox"><span class="ico selected"></span></div>'+name[i]+'</li>';
	};
	rep.html(html);
	webUtil.CustomCheckbox($('.yCheckbox'));
	ContrastChart(name, value, maxs, colors);
	var putM = $('#putMoney');
	var mt = $('#MoneyTip');
	var putLen = $('#putLength');
	$('#submitButton').click(function(){
		if(isNaN(putM.val()*1) || putM.val()*1 <= 0) {
			mt.html('<i class="icons reg-error"></i>请输入大于0的数值').show();
			putM.addClass('inputErr');
			putM.select();
			putM.focus();
		} else {
			value = [0, 0, 0, 0, 0];
			putM.removeClass('inputErr');
			mt.hide();
			var investAmount = putM.val()*1;
			var futou = $("#futou");
			var yCheckbox = $("#RepaymentBox .yCheckbox span");
			//微积金
			if($(yCheckbox[0]).hasClass("selected")){
				var ann = 0.106,monthAnn = 0.00883;
				if(futou.hasClass("selected")){	
					var profit = investAmount*Math.pow(1+monthAnn,putLen.val()) - investAmount;
				}else{
					var monthRepay = (investAmount*monthAnn*Math.pow(1+monthAnn,putLen.val()))/(Math.pow(1+monthAnn,putLen.val())-1);
					var investAmountTemp = investAmount;
					for(var i=0;i<putLen.val()-1;i++){
						investAmountTemp = investAmountTemp - (monthRepay - investAmountTemp*monthAnn);
					}
					var profit = investAmountTemp*(1+monthAnn)+monthRepay*(putLen.val()-1) - investAmount;
				}
				value[0] = profit.toFixed(2);
			}
			
			//银行活期
			if($(yCheckbox[1]).hasClass("selected")){
				var profit = investAmount*0.0035/12*putLen.val();
				value[1] = profit.toFixed(2);
			}
			//银行定期
			if($(yCheckbox[2]).hasClass("selected")){
				var ann = 0;
				switch(putLen.val()*1){
					case 3:ann = 0.0285;var profit = investAmount*ann/12*putLen.val();break;
					case 6:ann = 0.0305;var profit = investAmount*ann/12*putLen.val();break;
					case 12:ann = 0.0325;var profit = investAmount*ann/12*putLen.val();break;
					case 18:ann = 0.0345;var profit = investAmount*ann/12*12;break;
					case 24:ann = 0.0375;var profit = investAmount*ann/12*putLen.val();break;
					case 36:ann = 0.0425;var profit = investAmount*ann/12*putLen.val();break;
					case 60:ann = 0.0475;var profit = investAmount*ann/12*putLen.val();break;
				}
				value[2] = profit.toFixed(2);
			}
			//货币基金
			if($(yCheckbox[3]).hasClass("selected")){
				var profit = investAmount/10000*1.2000*putLen.val()*30;
				value[3] = profit.toFixed(2);
			}
			
		    //余额宝
		
		 if($(yCheckbox[4]).hasClass("selected")){
			 
			 	var profit = investAmount/10000*1.2723*putLen.val()*30;
				value[4] = profit.toFixed(2);
			 
			 }

			ContrastChart(name, value, null, colors);
		}
		

	
		
	});
	$('#resetButton').click(function(){
		value = [0,0,0,0,0];
		putM.val('');
		putLen.find("option").eq(0).attr("selected", "selected");
		ContrastChart(name, value, maxs, colors);
	});
});

function ContrastChart(name, value, maxs, colors){
	var arrValue = [];
	arrValue.push('[');
	for(var i=0; i<value.length; i++){
		arrValue.push('{y:'+value[i]+',color:"'+colors[i]+'"},');
	};
	arrValue.push(']');
	var items = eval('('+arrValue.join('')+')');
	ColumnChart(name, items, maxs);
	
};

function ColumnChart(cate, data, maxs){
	var chart = $('#columnChart').highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: '单位(元)',
			align: 'left',
			x: 10,
			style:{color:'#475058'}
		},
		legend: {
			enabled: false
		},
		xAxis: {
			enabled: true,
			categories: cate
		},
		yAxis: {
			max: maxs,
			min: 0,
			gridLineWidth: 0,
			gridLineColor: '#eee',
			labels: {
				enabled: false
			},
			title: {
				enabled: false
			}
		},
		plotOptions: {
			column: {
				pointWidth: 40,
				dataLabels: {
					enabled: true,
					style: {
						//fontWeight: 'bold'
					},
					formatter: function() {
						return this.y;
					}
				},
				marker: {  
					enabled: false,  
					states: {  
						hover: {enabled: false}  
					}  
				}
			}
		},
		tooltip: {enabled: false},
		series: [{
			data: data,
			color: '#335c7d',
			states:{
				hover:{
					enabled:false
				}
			}
		}],
		exporting: {enabled: false},
		credits: {enabled: false}
	}).highcharts();
};