var jiathis_config = {};  
var turnplate = {
    turnplateBox : $('#turnplate'),
    turnplateBtn : $('#platebtn'),
    lightDom : $('#turnplatewrapper'),
    freshLotteryUrl : '',
    msgBox : $('#msg'),
    lotteryUrl : '',
    height : '547', //帧高度
    lightSwitch : 0, //闪灯切换开关. 0 和 1间切换
    minResistance : 5, //基本阻力
    Cx : 0.01, //阻力系数 阻力公式：  totalResistance = minResistance + curSpeed * Cx;
    accSpeed : 15, //加速度
    accFrameLen : 40, //加速度持续帧数
    maxSpeed : 250, //最大速度
    minSpeed : 20, //最小速度
    frameLen : 8, //帧总数
    totalFrame : 0, //累计帧数,每切换一帧次数加1
    curFrame : 0, //当前帧
    curSpeed : 20, //上帧的速度
    lotteryTime : 0, //抽奖次数
    lotteryIndex : 6, //奖品index
    errorIndex : 6, //异常时的奖品指针
    initBoxEle : $('#turnplate #init'),
    progressEle : $('#turnplate #init span'),
    initProgressContent : '~~~^_^~~~', //初始化进度条的内容
    initFreshInterval : 500, //进度条刷新间隔
    virtualDistance : 10000, //虚拟路程,固定值，速度越快，切换到下帧的时间越快: 切换到下帧的时间 = virtualDistance/curSpeed
    isStop : false,
    timer2 : undefined, //计时器
    initTimer : undefined,
    showMsgTime : 2000, //消息显示时间
    lotteryChannel: '',
    channelList: {
        'login': '每日登录',
        'consume': ''
    },
    lotteryType : {
    	'三等奖-微积金精美笔记本' : 0,
        '特等奖-500元春日踏青旅行基金' : 1,
        '四等奖-5元现金红包' : 2,
        'empty' : 3,
        '一等奖-2%投资加息' : 4,
        '二等奖-1%投资加息' : 5,
        'empty' : 6,
        '阳光奖-5元提现券' : 7
    },
    lotteryList : [
		'三等奖-微积金精美笔记本',           
        '特等奖-500元春日踏青旅行基金',
        '四等奖-5元现金红包',
        '感谢参与',
        '一等奖-2%投资加息',
        '二等奖-1%投资加息',
        '感谢参与',
        '阳光奖-5元提现券'
    ],
    lotteryDes : [
        '',
        '',
        '',
        '',
        '',
        '',
        '',
        ''
    ],
    lotteryTime : 0, //剩余抽奖次数
    //初始化
    init : function() {
        //this.initAnimate();
        this.freshLottery();
        this.turnplateBtn.click($.proxy(function(){
            this.click();
        },this));
    },
    //初始化动画
    initAnimate : function() {
        this.initBoxEle.show();
        clearTimeout(this.initTimer);
        var curLength = this.progressEle.text().length,
                totalLength = this.initProgressContent.length;

        if (curLength < totalLength) {
            this.progressEle.text(this.initProgressContent.slice(0, curLength + 1));
        }else{
            this.progressEle.text('');
        }
        this.initTimer = setTimeout($.proxy(this.initAnimate, this), this.initFreshInterval);
    },
    //停止初始化动画
    stopInitAnimate : function() {
        clearTimeout(this.initTimer);
        this.initBoxEle.hide();
    },
    freshLotteryTime : function() {
        if(this.lotteryTime==0){
            $('#chooseCount').html("0");
            return false;
        }
        $('#chooseCount').html(this.lotteryTime);
    },
    //更新抽奖次数
    freshLottery : function() {
        //this.stopInitAnimate();
        this.setBtnHover();
        this.isStop = true;
        this.freshLotteryTime();

    },
    //设置按钮三态
    setBtnHover : function() {
        //按钮三态
        $('#platebtn').hover(function(){
            $(this).addClass('hover');
        },function() {
            $(this).removeClass('hover click');
        }).mousedown(function(){
            $(this).addClass('click');
        }).mouseup(function(){
            $(this).removeClass('click');
        });
    },
    //获取奖品
    lottery : function() {
    	if(!this.logined){
    		art.dialog({
				content : "您还未登录，登录后才能抽奖",
				title : '微积金提示',
				icon : 'warning',
				cancelVal : '关闭',
				cancel : true,
				button : [ {
					name : '去登录',
					callback : function() {
						window.location.href = "/goLogin?returnUrl=/prize/season2/toChoosePage";
					},
					focus : true
				},{
					name : '去注册',
					callback : function() {
						if($("#inviteCode").val()==""){
							window.location.href = "/goRegister";
						}else{
							window.location.href = "/fp/inviteRegister/"+$("#inviteCode").val();
						}
						
					}
				} ],
				fixed : true,
				lock : true,
				opacity : .3
			});

    		
    		
//    		turnplate.showMsg('请先登录')
    		return false;
    	}
    	if(this.lotteryTime==0){
    		turnplate.showMsg('您已用完所有抽奖机会')
    	}else{
            //加锁
            this.isStop = false;
            $.ajax({
                url:"/activity/prize/season2/choose",
                type:"post",
                dataType:"json",
                success:function(result){
                	if(result.success){
                		turnplate.lotteryTime--;
            			turnplate.freshLotteryTime();
            			turnplate.totalFrame = 0;
            			turnplate.curSpeed = turnplate.minSpeed;
            			turnplate.turnAround();
                		if(result.msg=="0"){
                			turnplate.lotteryIndex = 6;
                		}else if(result.obj.prizeId=="1006"){
                			turnplate.lotteryIndex = 1;
                		}else if(result.obj.prizeId=="1007"){
                			turnplate.lotteryIndex = 4;
                		}else if(result.obj.prizeId=="1008"){
                			turnplate.lotteryIndex = 5;
                		}else if(result.obj.prizeId=="1009"){
                			turnplate.lotteryIndex = 0;
                		}else if(result.obj.prizeId=="1010"){
                			turnplate.lotteryIndex = 2;
                		}else if(result.obj.prizeId=="1011"){
                			turnplate.lotteryIndex = 7;
                		}else{
                			turnplate.lotteryIndex = 6;
                		}
            	        if(result.msg=="1"){
            	        	$(".onGet").html(result.obj.prizeTitle);
            	        	$(".prizeName").html(result.obj.prizeName);
            	        	//$(".getPrizeSteps").html(result.obj.prizeDescription);
            	        	$(".getPrizeSteps").html("奖品详情请及时查看礼品盒！");
            	        }else{
            	        	$(".onGet").html("很遗憾您未抽中奖品");
            	        	$(".prizeName").html("谢谢参与！");
            	        	$(".getPrizeSteps").html("感谢您的参与！将活动分享给朋友还能获得更多抽奖机会哦！");
            	        }      		
                	}
                	else{
                		turnplate.showMsg(result.msg);
                		turnplate.isStop = true;
                	}
                },
                error:function(result){
                }
            });        
    	}    	
    },
    //点击抽奖
    click : function() {
        //加锁时
        if(this.isStop == false) {
            this.showMsg('正在进行抽奖.');
            return;
        }
        this.lottery();
    },
    //更新当前速度
    freshSpeed : function() {
        var totalResistance = this.minResistance + this.curSpeed * this.Cx;
        var accSpeed = this.accSpeed;
        var curSpeed = this.curSpeed;
        if(this.totalFrame >= this.accFrameLen) {
            accSpeed = 0;
        }
        curSpeed = curSpeed - totalResistance + accSpeed;

        if(curSpeed < this.minSpeed){
            curSpeed = this.minSpeed;
        }else if(curSpeed > this.maxSpeed){
            curSpeed = this.maxSpeed;
        }

        this.curSpeed  = curSpeed;
    },
    //闪灯,切换到下一张时调用
    switchLight : function() {
        this.lightSwitch = this.lightSwitch === 0 ? 1 : 0;
        var lightHeight = -this.lightSwitch * this.height;
        this.lightDom.css('backgroundPosition','0 ' + lightHeight.toString() + 'px');
    },
    //旋转,trunAround,changeNext循环调用
    turnAround : function() {
        //加锁
        this.isStop = false;
        var intervalTime = parseInt(this.virtualDistance/this.curSpeed);
        this.timer = window.setTimeout('turnplate.changeNext()', intervalTime);
    },
    //弹出奖品
    showAwards : function(){
    	myPrizeChooseList();
    	prizeChooseList();
        $('#lotteryMsg').dialog('open');
    },
    //显示提示信息
    showMsg : function(msg, isFresh) {
        isFresh = typeof isFresh == 'undefined' ? false : true;
        if(typeof msg != 'string'){
            msg = '今天已经抽过奖了，明天再来吧';
        }
        var msgBox = this.msgBox;
        var display = msgBox.css('display');

        msgBox.html(msg);

        window.clearTimeout(this.timer2);
        msgBox.stop(true,true).show();
        var fadeOut = $.proxy(function() {
            this.msgBox.fadeOut('slow');
        }, this);
        this.timer2 = window.setTimeout(fadeOut, this.showMsgTime);
    },
    //切换到下帧
    changeNext : function() {
        //判断是否应该停止
        if(this.lotteryIndex !== undefined && this.curFrame == this.lotteryIndex && this.curSpeed <= this.minSpeed+10 && this.totalFrame > this.accFrameLen) {
            this.isStop = true;
            this.showAwards();
            return;
        }

        var nextFrame =  this.curFrame+1 < this.frameLen ? this.curFrame+1 : 0;
        var bgTop = - nextFrame * this.height;
        this.turnplateBox.css('backgroundPosition','0 ' + bgTop.toString() + 'px');
        this.switchLight();
        this.curFrame = nextFrame;
        this.totalFrame ++;
        this.freshSpeed();
        this.turnAround();
    }
}
function myPrizeChooseList(){
	var d = $("#myPrizeList");
	var html ="";
	$.get("/prize/season2/myPrizeChooseList",function(data,status){
		data = $.parseJSON(data);
		for(i=0;i<data.length;i++){
			html+="<tr>";
			html+=" <td width=\"250\" height=\"32\">"+data[i].chooseTime+"</td>";
			html+="<td width=\"120\" height=\"32\">获得"+data[i].prizeName.substring(0,3)+"</td>";
			html+="</tr>";     
		}
		if(html!=""){
			d.html(html);
		}
	});
}

function prizeChooseList(){
	var d = $(".rewardList");
	var html ="";
	$.get("/prize/season2/prizeChooseList",function(data,status){
		data = $.parseJSON(data);
		for(i=0;i<data.length;i++){
			html+="<li>";
			html+=data[i].userName;
			html+="&nbsp;&nbsp;&nbsp;获得"+data[i].prizeName;
			html+="</li>";     
		}
		if(html!=""){
			d.html("<ul>"+html+"</ul>");
			//alert(d.html());
			d.myScroll({
				speed : 70, //数值越大，速度越慢
				rowHeight : 34
			});
		}
	});
}

function ajaxActivityInfo(){
	var html ="";
	$.ajax({
		url:'/prize/season2/ajaxActivityInfo',
		type:'post',
		success:function(json){
			json = $.parseJSON(json);
			if(json.success){
				$("#c1").html(json.obj.c1);
				$("#c2").html(json.obj.c2);
				$("#c3").html(json.obj.c3);
			}
		}		
	});
}


function prizeChooseCount(){
	var d = $("#chooseCount");
	var count;
	$.get("/prize/season2/getUserPrizeChooseRule",function(data,status){
		data = $.parseJSON(data);
		if(data!=undefined && data!=null && data.count>=0){
			count=data.count;
			jiathis_config.url = window.location.host+"/prize/season2/toChoosePage?f="+data.shareId; 
			jiathis_config.summary = "#微积金带你赚钱带你飞！"; 
		}else{
			count=0;
		}	
		d.html(count);
		turnplate.lotteryTime = count;
	});
}

function shareLink(){
	var url = window.location.href;
	var f=url.substring(url.indexOf('?f=')+3,url.length);
	if(f.indexOf('#')>0){
		f = f.substring(0,f.indexOf('#'));
	}
	var data = {};
	if(!isNaN(f)){
		data["f"] = f;
	}else{
		return false;
	}
	if(data.f!=""){
		$.post("/prize/season2/shareLink",data, 
				function (data, textStatus){             
				}, "json");
	}

}

function register(){
	if($("#inviteCode").val()==""){
		window.location.href = "/goRegister";
	}else{
		window.location.href = "/fp/inviteRegister/"+$("#inviteCode").val();
	}	
}

function dialogTop(){
	// 弹窗
	$(".btn-link").click(function(){
		 if(!turnplate.logined){
	    		art.dialog({
					content : "您还未登录，登录后才能查看",
					title : '微积金提示',
					icon : 'warning',
					cancelVal : '关闭',
					cancel : true,
					button : [ {
						name : '去登录',
						callback : function() {
							window.location.href = "/goLogin?returnUrl=/prize/season2/toChoosePage";
						},
						focus : true
					} ,{
						name : '去注册',
						callback : function() {
							if($("#inviteCode").val()==""){
								window.location.href = "/goRegister";
							}else{
								window.location.href = "/fp/inviteRegister/"+$("#inviteCode").val();
							}
							
						}
					}],
					fixed : true,
					lock : true,
					opacity : .3
				});
	    		return false;
		 }
		 type = $(this).attr('type');
		 if(type==1){
			 classname="top-daren";
			 resultname="用户数";
		 }else if(type==2){
			 classname="top-niuren";
			 resultname="用户数";
		 }else if(type==3){
			 classname="top-renxing";
			 resultname="累计金额";
		 }
		var data = {};
		data["type"]=type;
		var tableHtml ="";
		$.ajax({
			url:'/prize/season2/dialogTop',
			type:'post',
			data:data,
			async:false,
			success:function(json){
				json = $.parseJSON(json);
				if(json.success){
					for(i=0;i<json.obj.viewList.length;i++){
						tableHtml+="<tr>";
						tableHtml+="<td width='90' height='40'>"+(i+1)+"</td>";
						tableHtml+="<td width='160' height='40'>"+json.obj.viewList[i].username+"</td>";
						if(type==3){
							tableHtml+="<td width='130' height='40'>"+json.obj.viewList[i].allaccount+"</td>";
						}else{
							tableHtml+="<td width='130' height='40'>"+json.obj.viewList[i].allcount+"</td>";
						}
						tableHtml+="</tr>";
					}	
					tableHtml+="<tr><td colspan='3'  height='40'>...........</td></tr>";
					tableHtml+="<tr>";
					tableHtml+="<td width='90' height='40' class='light-yellow' style='color:#fff;'>"+json.obj.userMap.index+"</td>";
					tableHtml+="<td width='160' height='40' class='light-yellow' style='color:#fff;'>"+json.obj.userMap.username+"</td>";
					tableHtml+="<td width='130' height='40' class='light-yellow' style='color:#fff;'>"+json.obj.userMap.result+"</td>";
					tableHtml+="</tr>";
				}
			}		
		});
		 var html='<div id="overlay"></div>'+
		 '<div class="modalBox"><div class="'+classname+'  mar-modal"></div><div class="close mar-modal"></div><table class="ruturn-rule">'+
		 '<tr><td width="90" height="40" class="light-gray">排行</td><td width="160" class="light-gray">用户名</td><td width="130" class="light-gray">'+resultname+'</td></tr>'+
		 tableHtml+
         '</table></div>';
		  var tempWidth = $(document.body).width();
            var tempHeight = $(document.body).height();
            $("body").append(html);
            $("#overlay").css({
              "width":tempWidth,
              "height":tempHeight,
              "position":"absolute",
              "left":"0",
              "top":"0",
              "background":"#000",
              "opacity":"0.5",
              "filter":"alpha(opacity=50)",
              "z-index":"999"
            });
            $(".modalBox").css({
              "width":"560px",
              "height":"490px",
              "position":"absolute",
              "left":"50%",
              "top":"50%",
              "margin-left":"-280px",
              "margin-top":$(window).scrollTop()-245+"px",
              "background":"#fff",
              "z-index":"999",
              "border":"6px solid #ff8636"
            });

            $(".close").click(function(){
            	$(".modalBox").fadeOut();
            	$("#overlay").fadeOut(function(){
            	  $("#overlay,.modalBox").remove();
            	})
            	
            });
		 
	});
	
	
	
}

