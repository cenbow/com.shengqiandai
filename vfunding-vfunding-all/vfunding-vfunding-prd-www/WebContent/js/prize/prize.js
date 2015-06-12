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
    	'三等奖-投资收益10%' : 0,
        '特等奖-加油卡' : 1,
        '四等奖-5元现金红包' : 2,
        'empty' : 3,
        '一等奖-投资收益50%' : 4,
        '二等奖-荣昌E袋洗价值30元体验券' : 5,
        'empty' : 6,
        '阳光奖-5元提现券' : 7
    },
    lotteryList : [
		'三等奖-投资收益10%',           
        '特等奖-加油卡',
        '四等奖-5元现金红包',
        '感谢参与',
        '一等奖-投资收益50%',
        '二等奖-荣昌E袋洗价值30元体验券',
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
						window.location.href = "/goLogin?returnUrl=/prize/season1/toChoosePage";
					},
					focus : true
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
                url:"/activity/prize/season1/choose",
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
                		}else if(result.obj.prizeId=="1000"){
                			turnplate.lotteryIndex = 1;
                		}else if(result.obj.prizeId=="1001"){
                			turnplate.lotteryIndex = 4;
                		}else if(result.obj.prizeId=="1002"){
                			turnplate.lotteryIndex = 5;
                		}else if(result.obj.prizeId=="1003"){
                			turnplate.lotteryIndex = 0;
                		}else if(result.obj.prizeId=="1004"){
                			turnplate.lotteryIndex = 2;
                		}else if(result.obj.prizeId=="1005"){
                			turnplate.lotteryIndex = 7;
                		}else{
                			turnplate.lotteryIndex = 6;
                		}
            	        if(result.msg=="1"){
            	        	$(".onGet").html(result.obj.prizeTitle);
            	        	$(".prizeName").html(result.obj.prizeName);
            	        	$(".getPrizeSteps").html(result.obj.prizeDescription);
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
	$.get("/prize/season1/myPrizeChooseList",function(data,status){
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
	var d = $("#prizeList");
	var html ="";
	$.get("/prize/season1/prizeChooseList",function(data,status){
		data = $.parseJSON(data);
		for(i=0;i<data.length;i++){
			html+="<li>";
			html+=data[i].userName;
			html+="&nbsp;&nbsp;&nbsp;获得"+data[i].prizeName;
			html+="</li>";     
		}
		if(html!=""){
			d.html(html);
	        $("div.prizeList").myScroll({
	            speed:40,  //数值越大，速度越慢
	            rowHeight:32 //li的高度

	        });
		}
	});
}

function prizeChooseCount(){
	var d = $("#chooseCount");
	var count;
	$.get("/prize/season1/getUserPrizeChooseRule",function(data,status){
		data = $.parseJSON(data);
		if(data!=undefined && data!=null && data.count>=0){
			count=data.count;
			jiathis_config.url = window.location.host+"/prize/season1/toChoosePage?f="+data.shareId; 
			jiathis_config.summary = "#微积金周年庆，百万现金感恩回馈#新手收益提升50%！全场活动奖励超百万！转发分享好友迎取更多抽奖机会，现金红包、收益提升券任你拿！"; 
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
	var data = {};
	if(!isNaN(f)){
		data["f"] = f;
	}else{
		return false;
	}
	if(data.f!=""){
		$.post("/prize/season1/shareLink",data, 
				function (data, textStatus){             
				}, "json");
	}

}