
(function($){
	$.fn.slider = function(options){
	    var settings = {
	        btnPrevCls: '.btn.prev',  //向前按钮样式
	        btnNextCls: '.btn.next',  //向后按钮样式
	        pageCls: '.ctl-page',  //页数按钮样式
	        containerCls: '.slide-content',//具体滑动内容元素样式
	        itemCls: 'li',//滑动元素
	        activeCls: 'cur',//选中样式
	        perItem: 4,//显示的个数
	        startIndex: 0,//开始的位置
	        autoPlay: false,//是否自动播放
	        duration: 5000,//自动播放时速度
	        fxDuration: 1000,//滑动速度
	        circle: true,//是否循环
	        direction: 'horizon',//方向 horizon|vertical
	        onShow: function(){ }, //回调
            onStart:function(){},
            onEnd:function(){}
	    };

	    var opts = $.extend({}, settings, options);

	    var $this = $(this),
	        that = this,
	        container = $this.find(opts.containerCls),
	        items = container.find(opts.itemCls),
	        pages = $this.find(opts.pageCls),
	        btnPrev = $this.find(opts.btnPrevCls),
	        btnNext = $this.find(opts.btnNextCls);

	    if(!container || !items) return false;
	    var itemSize = (opts.direction != 'vertical') ? items.outerWidth() : items.outerHeight(),
            total = items.length,
            perItem = opts.perItem,
            current = -1,
            playTimer;
            this.busy =false;
        if(total <= perItem){
            btnPrev && btnPrev.addClass('disabled');
            btnNext && btnNext.addClass('disabled');
            return;
        }
        
        container.css((opts.direction != 'vertical') ? 'width' : 'height', itemSize * total);

        this.show = function(index){

            if(index == current || this.busy) return;
            if(current!==-1){opts.onStart(index);}
            index = Math.max(0, Math.min(index, items.length - 1));
            if(pages.length) pages.eq(current).removeClass(opts.activeCls);

            var fxOpts = {};
            fxOpts[(opts.direction != 'vertical') ? 'left' : 'top'] = - index * itemSize;

            if(current!==-1){
                this.busy = true;
                container.animate(fxOpts, opts.fxDuration, function(){
                    that.busy = false;
                    opts.onEnd(index);
                });
            }
            current = index;
            if(pages.length) pages.eq(current).addClass(opts.activeCls);
            if(!opts.circle){
                if(current == 0){
                    btnPrev.addClass('disabled');
                }else if (current >= total - perItem || total < perItem){
                    btnNext.addClass('disabled');
                }else{
                    btnPrev.add(btnNext).removeClass('disabled');
                }
            }

            opts.onShow.apply(this, [current, items]);
        };

        if(btnPrev.length) btnPrev.click(showPrev);
        if(btnNext.length) btnNext.click(showNext);
        if(pages.length) pages.each(function(index){
            $(this).click(function(){
                that.show(index);
            });
        });

        this.show(opts.startIndex);
        if(opts.autoPlay) {
            autoPlay();
            $this.hover(function(){
                if(playTimer) clearTimeout(playTimer);
            }, function(){
                autoPlay();
            });
        }

        function showNext(){
            if(current + perItem < total) that.show(current+1);
            else if(opts.circle) that.show(0);
            else return;
        }

        function showPrev(){
            if(current > 0) that.show(current-1);
            else if(opts.circle && total >= perItem) that.show(total - perItem);
            else return;
        }

        function autoPlay(){
            playTimer = setInterval(function(){
                showNext();
            }, opts.duration);
        }

        return this;
	};
})(jQuery);

