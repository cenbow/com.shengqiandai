var wholeTotal = 1;

$(function() {
	borrowListAjax(1);
	pagingAjax();
	
	
})

function pagingAjax() {
	var pagingcount = $("#pagCount").val();
	if (wholeTotal > 1) {
		pagingcount = wholeTotal;
	}
	$("#paging").paginate({
		count : pagingcount,
		start : 1,
		images : false,
		mouse : 'press',
		onChange : function(page) {
			borrowListAjax(page);
			location.href = "#condition1"
		}
	});
}

function borrowListAjax(page) {
	$.ajax({
		url : '/weekBorrow/borrowListAjax',
		data : {
			page : page
		},
		cache : false,
		async : false,
		type : 'post',
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('很遗憾，出异常了' + errorThrown);
		},
		success : function(result) {
			var now = getCurDate();
			result = $.parseJSON(result);
//			console.log(result);
			var rows = result.rows;
			var html = '<tr><td width="264" height="56">标题&nbsp;</td><td width="143">份额&nbsp;</td><td width="100">期限&nbsp;</td><td width="109">年化收益率&nbsp;</td><td width="110">进度&nbsp;</td><td width="117">状态&nbsp;</td></tr>';
			if (rows != null && rows.length > 0) {
				$("#paging").show();
				for (var i = 0; i < rows.length; i++) {
					var rowContent = rows[i];
					var timeLimit = rowContent.timeLimit + "天";
					var url = "/week/weekDetail/" + rowContent.id;
					var tenderAction;
					var isSale = compareTime(now,rowContent.saleTime) > 0 ? true : false ;
					var isExpire = compareTime(now,rowContent.expireTime) > 0 ? true : false ;				
//					console.log(rowContent);
					//计算操作按钮样式
					//即将登场
					if(rowContent.status==3
							&& !isExpire
							&& !isSale
							){
						tenderAction = '<a href="'+url
								+ '"	class="btn116-gray" target="blank">即将登场';
					}					
					//已过期
					else if(rowContent.status==3
						&& isExpire){
						tenderAction = '<a href="'+url
								+ '"	class="btn116-gray" target="blank">认购结束';
					} 
					//可认购
					else if (rowContent.status == 3
							&& isSale
							&& parseFloat(rowContent.completePercent) < 100) {
						tenderAction = '<a href="'+url
								+ '"	class="btn116 yellow" target="blank">我要认购';
					}
					//已售罄
					else if (rowContent.status == 3
							&& isSale
							&& parseFloat(rowContent.completePercent) == 100) {
						tenderAction = '<a href="'+url+''
								+ '"	class="btn116-gray" target="blank">已售罄';
					}
					//还款中
					else if (rowContent.status == 4
							&& !rowContent.weekIsRepay) {
						tenderAction = '<a href="'+url
								+ '"	class="btn116 ice" target="blank">还款中';
					} 
					//已还款
					else if (rowContent.status == 4
							&& rowContent.weekIsRepay) {
						tenderAction = '<a href="'+url
								+ '"	class="btn116 ice" target="blank">已还款';
					} 
					var name = rowContent.name;
					if (name.length > 20) {
						name = name.substr(0, 15);
					}
					var name2 = rowContent.name;
					html = html
					   + '<tr><td width="260" height="112" class="boder_bottom"><a class="avantar zyb_icon avantar-position"></a>'
					   + '<a class="biaoti" target="blank" href="'+url+'">'+name2+'</a></td>'
					   + '<td width="143"  class="boder_bottom">'+rowContent.realityCount+'份</td>'
					   + '<td width="140"  class="boder_bottom">'+timeLimit+'</td>'
					   + '<td width="111" class="rate boder_bottom" align="center">'+rowContent.apr+'%</td>'
					   + '<td width="123" class="boder_bottom">'
					   + '<div class="progress_circle fl  progressBar progressBar'+rowContent.completeInteger+'"><p>'+rowContent.completePercent+'</p>'
					   + '</div></td>'
					   + '<td width="126" class="boder_bottom" align="center">'
					   + tenderAction+'</a>'
					   + '</td></tr>';		
				}
				$("#borrowItem").html(html);
				if (result.total >= 1) {
//					console.log(result.total / 10);
					$("#pagCount").val(result.total / 10);
					wholeTotal = result.total / 10;
				}
			} else {
				$("#paging").hide();
				html = html
						+ "<tr><td height='56' colspan='7'>没有记录</td></tr>";
				$("#borrowItem").html(html);
				location.href = "#condition1"
			}
		},
		contentType : "application/x-www-form-urlencoded;charset=UTF-8"
	});
}

//比较时间
function compareTime(beginTime,endTime) {
    var beginTimes = beginTime.substring(0, 10).split('-');
    var endTimes = endTime.substring(0, 10).split('-');
    beginTime = beginTimes[1] + '-' + beginTimes[2] + '-' + beginTimes[0] + ' ' + beginTime.substring(10, 19);
    endTime = endTimes[1] + '-' + endTimes[2] + '-' + endTimes[0] + ' ' + endTime.substring(10, 19);
    var a = (Date.parse(beginTime) - Date.parse(endTime)) / 3600 / 1000;
    
    if (a < 0) {
        return -1
    } else if (a > 0) {
        return 1
    } else if (a == 0) {
        return 0;
    } else {
        return 'exception'
    }
}

function getCurDate(){
	var d = new Date();
	var years = d.getFullYear();
	var month = add_zero(d.getMonth()+1);
	var days = add_zero(d.getDate());
	var hours = add_zero(d.getHours());
	var minutes = add_zero(d.getMinutes());
	var seconds=add_zero(d.getSeconds());
	var date = years+"-"+month+"-"+days+" "+hours+":"+minutes+":"+seconds;
	return date;
}
 
function add_zero(temp){
	if(temp<10) return "0"+temp;
	else return temp;
}
