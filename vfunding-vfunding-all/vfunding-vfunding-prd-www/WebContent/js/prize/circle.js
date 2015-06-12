var turnplate = {
	turnplateBox : $('#turnplate'),
	turnplateBtn : $('#platebtn'),
	lightDom : $('#turnplatewrapper'),
	freshLotteryUrl : 'http://huodong.kuaipan.cn/ajaxTurnplate/freshLottery/',
	msgBox : $('#msg'),
	lotteryUrl : 'http://huodong.kuaipan.cn/ajaxTurnplate/lottery/',
	height : '592', //帧高度
	lightSwitch : 0, //闪灯切换开关. 0 和 1间切换
	minResistance : 5, //基本阻力
	Cx : 0.01, //阻力系数 阻力公式：  totalResistance = minResistance + curSpeed * Cx;	
	accSpeed : 15, //加速度
	accFrameLen : 40, //加速度持续帧数
	maxSpeed : 250, //最大速度 
	minSpeed : 20, //最小速度 
	frameLen : 10, //帧总数
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
	isStop : false, //抽奖锁,为true时，才允许下一轮抽奖
	timer2 : undefined, //计时器
	initTimer : undefined,
	showMsgTime : 2000, //消息显示时间
	lotteryChannel: '',

	channelList: {
		'login': '每日登录',
		'consume': '购买空间'
	},

	lotteryType : {
		'5M' : 0,	
		'80M' : 1,
		'1G' : 2,
		'200M' : 3,
		'100M' : 4,
		'20M' : 5,
		'empty' : 6,
		'10G' : 7,
		'50M' : 8,
		'1T' : 9
	},

	lotteryList : [
		'5M 永久空间',
		'80M 永久空间',		
		'1G 永久空间',		
		'200M 永久空间',		
		'100M 永久空间',		
		'20M 永久空间',		
		'继续攒人品',		
		'10G 永久空间',		
		'50M 永久空间',		
		'1T 永久空间',
	],

	lotteryDes : [
		'手气一般般，幸运指数半颗星！',
		'手气不错呢，幸运指数3颗星！',
		'手气无敌了，幸运指数4颗星！',
		'手气很好呢，幸运指数3颗星！',
		'手气很好呢，幸运指数3颗星！',
		'手气还凑合，幸运指数1颗星！',
		'手气太差了，幸运指数0颗星！',
		'手气太好了，幸运指数5颗星！',
		'手气还可以，幸运指数2颗星！',
		'手气爆棚了，幸运指数5颗星！'
	],

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
            $('#top-menu').find('.lottery-times').html("0");
             return false;
        }
		$('#top-menu').find('.lottery-times').html(this.lotteryTime);
	},

	//更新抽奖次数
	freshLottery : function() {
		//this.stopInitAnimate();
		this.setBtnHover();
		this.isStop = false;
		this.lotteryTime = 1;
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
		this.lotteryIndex = undefined;
		this.lotteryTime--;
		this.freshLotteryTime();
		this.totalFrame = 0;
		this.curSpeed = this.minSpeed;
		this.turnAround();
		this.lotteryIndex = typeof this.lotteryType[2] !== 'undefined' ? this.lotteryType[2] : this.errorIndex;
		this.lotteryChannel = typeof this.channelList[1] !== 'undefined' ? this.channelList[1] : '';	
	},

	//点击抽奖
	click : function() {
		//加锁时
		if(this.isStop == true) {
			this.showMsg('现在还不能抽奖哦');
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
