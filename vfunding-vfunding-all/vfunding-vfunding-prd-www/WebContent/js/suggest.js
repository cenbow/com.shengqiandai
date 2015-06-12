
(function($) {
	$.fn.fnSuggestLib = function(options) {
        var defaults = {
			url: '', //源数据地址
			data: {}, //搜索参数源数据
			hotUrl: '', //热门、推荐数据源
			hotData: {}, //默认、推荐、热门源数据
            tabs: null, //选项卡推荐、热门面板源数据
            preset: false, //是否显示热门、推荐源数据面板 true为显示 false为不显示
			suggest: true, //点击是否显示推荐下拉层
            onSelect: null, //扩展响应
            suggestLength: 10, //最大信息显示条数
			pageLength: null, //设置最大显示分页数
			page: false, //设置分页显示：true显示，false不显示
			width: '',//提示层宽度
			coder: true, //对应代码是否显示
            inputText: '中文/拼音',
            popTitle: '请从以下选择或输入中文/拼音',
            suggestTitle: '请输入中文/拼音'
        };
        var options = $.extend(defaults, options);
        this.each(function() {
            new $.fnSuggestLib(this, options);
        });
        return this;
    };
    $.fnSuggestLib = function(input, options) {
        var input = $(input); //当前输入框
        var idput = input.attr('id'); //得到当前input输入框id
        input.attr('autocomplete', 'off'); //防止input输入框缓存历史数据
        if ($.trim(input.val()) == '' || $.trim(input.val()) == options.inputText) {
            input.val(options.inputText).css('color', '#999');
        };
        var popFocus = false;
        var suggestFocus = false;
        var suggestPageClick = false;
        
        var suggestLayout = '<div id="suggestLib_'+idput+'" class="suggest_lib"><div class="suggest_head"></div><div class="suggest_cont"></div>';
		if(options.page){
			suggestLayout += '<div class="pager-city"></div>';
		}
		suggestLayout += '</div>';
		$('body').append(suggestLayout);
        //是否显示热门、推荐源数据面板
		if(options.preset){
			var popLayout = '<div id="popLib_'+idput+'" class="pop_lib"><div class="pop_head"></div><ul class="pop_label"></ul><div class="pop_cont"></div></div>';
			$('body').append(popLayout);
			var popMain = $("#popLib_" + idput);
			var popCont = popMain.find('.pop_cont');
        	var labelMain = popMain.find('.pop_label');
		};
        var suggestMain = $('#suggestLib_' + idput);
		var suggestHead = suggestMain.find('.suggest_head');
		hotSource();
		resetPosition();
        $(window).resize(function() {
            resetPosition();
        });
		/* 内容装置器显示目标位置 */
		function resetPosition() {
			var offset = input.offset();
			var height = input.outerHeight();
			var suggestWidth = 100;
			if(options.width == ''){
				suggestWidth = input.outerWidth() - suggestMain.outerWidth() + suggestMain.width();
			} else {
				suggestWidth = options.width;
			};
			if(options.preset){
				popMain.css({
					top: (offset.top + height),
					left: offset.left + 'px'
				});
			};
			suggestMain.css({
				top: (offset.top + height),
				left: offset.left + 'px',
				width: suggestWidth
			});
		};
		/* 推荐面板是否显示或装载 */
        function setPreset() {
            if (options.preset) {
				resetPosition();
                suggestMain.hide();
				if(options.preset){
                	popMain.show();
				}
            } else if(options.suggest){
				resetPosition();
				loadParameter();
				suggestMain.find('.suggest_head').html(options.suggestTitle);
				suggestMain.show();
			}
        };
		/* 设置对象触发各种事件 */
        input.change(function() {
			if ($.trim($(this).val()) == null || $.trim($(this).val()) == ''){
                $('#' + idput).val(options.inputText);
            };
        }).focus(function() {			
			resetPosition();
            if (suggestPageClick) {
                suggestPageClick = false;
                return true;
            };
            if ($.trim($(this).val()) == options.inputText) {
                $(this).val('').css('color', '#444');
            };
            setPreset();
        }).click(function(e) {
			e?e.stopPropagation():event.cancelBubble = true;
			resetPosition();
            if (suggestPageClick) {
                suggestPageClick = false;
                return;
            };
			$(this).select();

            setPreset();
        }).blur(function() {
            if (popFocus == false) {
				if(options.preset){
                	popMain.hide();
				};
                if ($.trim(input.val()) == '' || $.trim(input.val()) == options.inputText) {
                    input.val(options.inputText).css('color', '#999');
                };
            };
			if (suggestFocus == false) {
				suggestMain.hide();
                if ($(this).val() == '') {
					var sugRel = suggestMain.find('.suggest_cont a.selected');
					var b = sugRel.children('b').text();
					$(this).val(b).css('color', '#444');
                };
            };
        });
		if(options.preset){
			labelMain.find('a').live('click',function(e) {
				e?e.stopPropagation():event.cancelBubble = true;
				input.focus();
				popFocus = true;
				var labelId = $(this).attr('id');
				labelMain.find('li a').removeClass('current');
				$(this).addClass('current');
				popCont.find('ul').hide();
				$('#' + labelId + '_wrap').show();
				return false;
			});
			popCont.find('a').live('click',function(e) {
				e?e.stopPropagation():event.cancelBubble = true;
				input.val($(this).html()).css('color', '#444');
				if(options.preset){
					popMain.hide();
				};
				onSelectResult();
				return false;
			}).live('mouseover', function() {
				popFocus = true;
			}).live('mouseout', function() {
				popFocus = false;
			});
		};
		/* 捕获键盘各种事件 */
        input.keydown(function(event) {
			if(options.preset){
            	popMain.hide();
			};
            event = window.event || event;
            var keyCode = event.keyCode || event.which || event.charCode;
			if (keyCode == 37) { //左
                prevPage();
            } else if (keyCode == 39) { //右
                nextPage();
            } else if (keyCode == 38) { //上
                prevResult();
            } else if (keyCode == 40) { //下
                nextResult();
            };
        }).keypress(function(event) {
            event = window.event || event;
            var keyCode = event.keyCode || event.which || event.charCode;
            if (keyCode == 13) {
				var sugRel = suggestMain.find('.suggest_cont a.selected');
                if (sugRel.length > 0) {
					var b = sugRel.children('b').text();
					input.val(b).css('color', '#444');
                    suggestMain.hide();
					onSelectResult();
                };
            };
        }).keyup(function(event) {
            event = window.event || event;
            var keyCode = event.keyCode || event.which || event.charCode;
            if (keyCode != 13 && keyCode != 37 && keyCode != 39 && keyCode != 9 && keyCode != 38 && keyCode != 40) {
                querySource();
            };
        });
		/* 点击显示内容列表项并取得各自参数值 */
        suggestMain.find('.suggest_cont a').live('click', function(e) {
			e?e.stopPropagation():event.cancelBubble = true;            
            input.val($(this).children('b').text()).css('color', '#444');
            suggestMain.hide();
			onSelectResult();
			return false;
        }).live('mouseover', function() {
            suggestFocus = true;
			$(this).addClass('selected').siblings().removeClass('selected');
        }).live('mouseout', function() {
            suggestFocus = false;
        });
		$(document).click(function() { //点击除指定input外，隐藏UL
			suggestMain.hide();
			if(options.preset){
				popMain.hide();
			};
		});
		/* 按下键盘向下键时向下选择（循环） */
        function nextResult() {
			var a_tag = suggestMain.find('.suggest_cont a');
			var a_tag_v = suggestMain.find('.suggest_cont a:visible');
            var eqs = a_tag.index(suggestMain.find('.suggest_cont a.selected')[0]);
            suggestMain.find('.suggest_cont').children().removeClass('selected');
            eqs += 1;
            var t_end = a_tag.index(a_tag_v.filter(':last')[0]);
            if (eqs > t_end) {
                eqs = a_tag.index(a_tag_v.eq(0));
            };
            a_tag.eq(eqs).addClass('selected');
        };
		/* 按下键盘向上键时向上选择（循环） */
        function prevResult() {
			var a_tag = suggestMain.find('.suggest_cont a');
			var a_tag_v = suggestMain.find('.suggest_cont a:visible');
            var eqs = a_tag.index(suggestMain.find('.suggest_cont a.selected')[0]);
            suggestMain.find('.suggest_cont').children().removeClass('selected');
            eqs -= 1;
            var t_start = a_tag.index(a_tag_v.filter(':first')[0]);
            if (eqs < t_start) {
                eqs = a_tag.index(a_tag_v.filter(':last')[0]);
            }
            a_tag.eq(eqs).addClass('selected');
        };
        /* 设置默认、推荐、热门  */
        function loadParameter() {
            var suggestCont = suggestMain.find('.suggest_cont');
            suggestCont.empty();
            if (!options.hotData) {
                suggestCont.html('<span class="msg">暂无数据</span>');
            };
            for (var items in options.hotData) {
                if (items > options.suggestLength - 1) {
                    return;
                };
                suggestCont.append('<a href="#"><b>'+entry[0]+'</b><span '+(options.coder?'style="display:block"':'style="display:none"')+'>'+entry[1]+'</span></a>');
            };
            suggestMain.find('.suggest_head').html(options.suggestTitle);
			if(options.page){
            	setNumberPager(1);
			};
            suggestMain.show();
            setTopSelect();
        };
		/* 搜索结果载入装置 */
        function querySource() {
			var value = input.val().toLowerCase();
			if(options.preset){
            	popMain.hide();
			};
            if (value.length == 0) {
                loadParameter();
                return;
            };
            if (value != null || value != '') {
				ajaxSourceLib(value);
            };
            setTimeout(function() { searchSuggestShow()},200);
        };
		/* 数据源 */
		function ajaxSourceLib(val){
            if(options.url=="") return;
			$.ajax({
				url: options.url,
				type: "GET",
				dataType: "json",
				success: function(data) {
					webUtil.config.CITYSPOTLIGHT.splice(0,webUtil.config.CITYSPOTLIGHT.length);
					$.each(data,function(key, items){
						webUtil.config.CITYSPOTLIGHT.push(items);
					});
				}
			});
		};
		/* 热门、推荐等数据源 */
		function hotSource(){
            if(options.hotUrl=="") return;
			$.getJSON(options.hotUrl,function(data){
				$.each(data,function(key, items){
					webUtil.config.HOTCITYSPOTLIGHT.push(items);
				});
			});
		};
		function searchSuggestShow(){
			var value = input.val().toLowerCase();
			var lib_cont = suggestMain.find('.suggest_cont');
			var isHave = false;
			var _tmp = [];
			for (var items in options.data) {
				var entry = options.data[items];
				if (typeof(entry) != 'undefined') {
					if (entry[0].indexOf(value) > -1 || entry[1].indexOf(value) > -1) {
						isHave = true;
						_tmp.push(entry);
					}
				};
			};
			if (isHave) {
				lib_cont.empty();
				for (var items in _tmp) {
					var entry = _tmp[items];
					if(items < options.suggestLength){
						lib_cont.append('<a href="#" '+(items==0?'class="selected"':'')+'><b>'+entry[0]+'</b><span '+(options.coder?'style="display:block"':'style="display:none"')+'>'+entry[1]+'</span></a>');
					}
				};
				//suggestMain.find('.suggest_head').html(value + ',按拼音排序');
				suggestMain.find('.suggest_head').hide();
				if(options.page){
					setNumberPager(1);
				};
				setTopSelect();
			} else {
				lib_cont.html('');
				suggestMain.find('.suggest_head').html('对不起('+value+')没有找到').show();//.css({'color':'#d35353'})
			};
			suggestMain.show();
		};
		//分页
		if(options.page){
			/* 点击分页事件 */
			suggestMain.find('.pager-city a').live('mouseover',function() {
				suggestFocus = true;
			}).live('mouseout',function() {
				suggestFocus = false;
			});
			suggestMain.find('.pager-city a').live('click',function(event) {
				suggestPageClick = true;
				input.click();
				if ($(this).attr('data-rel') != null) {
					setNumberPager($(this).attr('data-rel'));
				}
			});
			/* 按下键盘右键时向下翻页 */
			function nextPage() {
				var add_cur = suggestMain.find('.pager-city a.current').next();
				if (add_cur != null) {
					if ($(add_cur).attr('data-rel') != null) {
						setNumberPager($(add_cur).attr('data-rel'));
					}
				}
			};
			/* 按下键盘左键时向上翻页 */
			function prevPage() {
				var add_cur = suggestMain.find('.pager-city a.current').prev();
				if (add_cur != null) {
					if ($(add_cur).attr('data-rel') != null) {
						setNumberPager($(add_cur).attr('data-rel'));
					}
				}
			};
			/* 设置分页结果显示页 */
			function setNumberPager(pagerEqs) {
				var a_tag = suggestMain.find('.suggest_cont a');
				a_tag.removeClass('selected');
				suggestMain.find('.suggest_cont').children().each(function(i) {
					var k = i + 1;
					if (k > options.suggestLength * (pagerEqs - 1) && k <= options.suggestLength * pagerEqs) {
						$(this).css('display', 'block');
					} else {
						$(this).hide();
					}
				});
				setTopSelect();
				if(options.page){
					setAddPageHtml(pagerEqs);
				}
			};
			/* 添加分页HTML */
			function setAddPageHtml(pagerEqs) {
				var pageBreak = suggestMain.find('.pager-city');
				pageBreak.empty();
				if (suggestMain.find('.suggest_cont').children().length > options.suggestLength) {
					var pageSize = Math.ceil(suggestMain.find('.suggest_cont').children().length / options.suggestLength);
					if (pageSize <= 1) {
						return;
					};
					var start = end = pagerEqs;
					var number, index;
					for (var j = 0, number = 1; j < options.pageLength && number < options.pageLength; j++) {
						if (start > 1) {
							start--;
							number++;
						};
						if (end < pageSize) {
							end++;
							number++;
						};
					};
					for (var i = start; i <= end; i++) {
						if (i == pagerEqs) {
							pageBreak.append('<a href="#" class="current" data-rel="' + (i) + '">' + (i) + '</a');
						} else {
							pageBreak.append('<a href="#" data-rel="' + (i) + '">' + (i) + '</a');
						};
					};
					pageBreak.show();
				} else {
					pageBreak.hide();
				};
				return;
			};
		};
        
        /* 设置显示第一条数据高亮 */
        function setTopSelect() {
            if (suggestMain.find('.suggest_cont').children().length > 0) {
                suggestMain.find('.suggest_cont a:first').addClass('selected');
            };
        };
        /* 选择时添加其它选项 */
        function onSelectResult() {
            if (options.onSelect) {
                options.onSelect.apply(input[0]);
            };
        };
        /* 默认推荐面板 */
        function popPanel() {
            var index = 0;
            popMain.find('.pop_head').html(options.popTitle);
            /* 装置没有TAB时，显示内容区 */
            if (!options.tabs) {
                popCont.append('<ul id="label_' + idput + '_wrap" class="current"></ul>');
                labelMain.remove();
				for (var items in options.hotData) {
					if (items < 40) {
						var entry = options.hotData[items];
						$('#label_' + idput + '_wrap').append('<li><a href="#">'+ entry[0]+'</a></li>');
					};
				};
                return;
            };
            /* 装置有TAB时，显示内容区 */
            for (var itemLabel in options.tabs) {
                index++;
                if (index == 1) {
                    popCont.append("<ul id='label_" + idput + index + "_wrap' class='current' data-type='" + itemLabel + "'></ul>");
                    labelMain.append("<li><a id='label_" + idput + index + "' class='current' href='#'>" + itemLabel + "</a></li>");
                } else {
                    popCont.append("<ul style='display:none' id='label_" + idput + index + "_wrap' data-type='" + itemLabel + "'></ul>");
                    labelMain.append("<li><a id='label_" + idput + index + "' href='#'>" + itemLabel + "</a></li>");
                };
                for (var items in options.tabs[itemLabel]) {
                    var nameCode = options.tabs[itemLabel][items];
                    if (!options.hotData[nameCode]) {
                        break;
                    };
                    $("#label_"+idput + index+"_wrap").append('<li><a href="#">'+entry[0]+'</a></li>');
                };
            };
        };
		if(options.preset){
			setTimeout(function(){popPanel()},200);
		};
    }
})(jQuery);


(function($) {
	$.fn.fnBanksLib = function(options) {
        var defaults = {
			url: '', //源数据地址
			data: {}, //搜索参数源数据
			suggest: true, //点击是否显示推荐下拉层
            onSelect: null, //扩展响应
            suggestLength: 10, //最大信息显示条数
			pageLength: null, //设置最大显示分页数
			page: false, //设置分页显示：true显示，false不显示
			width: '',//提示层宽度
			coder: true, //对应代码是否显示
            inputText: '中文',
            suggestTitle: '请输入中文'
        };
        var options = $.extend(defaults, options);
        this.each(function() {
            new $.fnBanksLib(this, options);
        });
        return this;
    };
    $.fnBanksLib = function(input, options) {
        var input = $(input); //当前输入框
        var idput = input.attr('id'); //得到当前input输入框id
        input.attr('autocomplete', 'off'); //防止input输入框缓存历史数据
        if ($.trim(input.val()) == '' || $.trim(input.val()) == options.inputText) {
            input.val(options.inputText).css('color', '#999');
        };
        var popFocus = false;
        var suggestFocus = false;
        var suggestPageClick = false;
        
        var suggestLayout = '<div id="suggestLib_'+idput+'" class="suggest_lib"><div class="suggest_head"></div><div class="suggest_cont"></div>';
		if(options.page){
			suggestLayout += '<div class="pager-city"></div>';
		}
		suggestLayout += '</div>';
		$('body').append(suggestLayout);
        
        var suggestMain = $('#suggestLib_' + idput);
		var suggestHead = suggestMain.find('.suggest_head');
		var bankval = $('#BankCheck');
		var HouseBankCode = $('#HouseBankCode');
		resetPosition();
        $(window).resize(function() {
            resetPosition();
        });
		/* 内容装置器显示目标位置 */
		function resetPosition() {
			var offset = input.offset();
			var height = input.outerHeight();
			if(options.width == ''){
				suggestWidth = input.outerWidth() - suggestMain.outerWidth() + suggestMain.width();
			} else {
				suggestWidth = options.width;
			};
			
			suggestMain.css({
				top: (offset.top + height),
				left: offset.left + 'px',
				width: suggestWidth
			});
		};
		/* 推荐面板是否显示或装载 */
        function setPreset() {
            if(options.suggest){
				resetPosition();
				suggestMain.find('.suggest_head').html(options.suggestTitle);
				suggestMain.show();
			}
        };
		/* 设置对象触发各种事件 */
        input.change(function() {
			if ($.trim($(this).val()) == null || $.trim($(this).val()) == ''){
                $('#' + idput).val(options.inputText);
            };
        }).focus(function() {			
			resetPosition();
            if (suggestPageClick) {
                suggestPageClick = false;
                return true;
            };
            if ($.trim($(this).val()) == options.inputText) {
                $(this).val('').css('color', '#444');
            };
            setPreset();
        }).click(function(e) {
			e?e.stopPropagation():event.cancelBubble = true;
			resetPosition();
            if (suggestPageClick) {
                suggestPageClick = false;
                return;
            };
			$(this).select();
            setPreset();
        }).blur(function() {
            if (popFocus == false) {
                if ($.trim(input.val()) == '' || $.trim(input.val()) == options.inputText) {
                    input.val(options.inputText).css('color', '#999');
                };
            };
			if (suggestFocus == false) {
				suggestMain.hide();
                if ($(this).val() == '') {
					var sugRel = suggestMain.find('.suggest_cont a.selected');
					var b = sugRel.children('b').text();
					$(this).val(b).css('color', '#444');
					HouseBankCode.val(sugRel.children('span').text());
                };
            };
        });
		/* 捕获键盘各种事件 */
        input.keydown(function(event) {
            event = window.event || event;
            var keyCode = event.keyCode || event.which || event.charCode;
			if (keyCode == 37) { //左
                prevPage();
            } else if (keyCode == 39) { //右
                nextPage();
            } else if (keyCode == 38) { //上
                prevResult();
            } else if (keyCode == 40) { //下
                nextResult();
            };
        }).keypress(function(event) {
            event = window.event || event;
            var keyCode = event.keyCode || event.which || event.charCode;
            if (keyCode == 13) {
				var sugRel = suggestMain.find('.suggest_cont a.selected');
                if (sugRel.length > 0) {
					var b = sugRel.children('b').text();
					input.val(b).css('color', '#444');
					HouseBankCode.val(sugRel.children('span').text());
                    suggestMain.hide();
					onSelectResult();
                };
            };
        }).keyup(function(event) {
            event = window.event || event;
            var keyCode = event.keyCode || event.which || event.charCode;
            if (keyCode != 13 && keyCode != 37 && keyCode != 39 && keyCode != 9 && keyCode != 38 && keyCode != 40) {
                querySource();
            };
        });
		/* 点击显示内容列表项并取得各自参数值 */
        suggestMain.find('.suggest_cont a').live('click', function(e) {
			e?e.stopPropagation():event.cancelBubble = true;            
            input.val($(this).children('b').text()).css('color', '#444');
			HouseBankCode.val($(this).children('span').text());
            suggestMain.hide();
			onSelectResult();
			return false;
        }).live('mouseover', function() {
            suggestFocus = true;
			$(this).addClass('selected').siblings().removeClass('selected');
        }).live('mouseout', function() {
            suggestFocus = false;
        });
		$(document).click(function() { //点击除指定input外，隐藏UL
			suggestMain.hide();
		});
		/* 按下键盘向下键时向下选择（循环） */
        function nextResult() {
			var a_tag = suggestMain.find('.suggest_cont a');
			var a_tag_v = suggestMain.find('.suggest_cont a:visible');
            var eqs = a_tag.index(suggestMain.find('.suggest_cont a.selected')[0]);
            suggestMain.find('.suggest_cont').children().removeClass('selected');
            eqs += 1;
            var t_end = a_tag.index(a_tag_v.filter(':last')[0]);
            if (eqs > t_end) {
                eqs = a_tag.index(a_tag_v.eq(0));
            };
            a_tag.eq(eqs).addClass('selected');
        };
		/* 按下键盘向上键时向上选择（循环） */
        function prevResult() {
			var a_tag = suggestMain.find('.suggest_cont a');
			var a_tag_v = suggestMain.find('.suggest_cont a:visible');
            var eqs = a_tag.index(suggestMain.find('.suggest_cont a.selected')[0]);
            suggestMain.find('.suggest_cont').children().removeClass('selected');
            eqs -= 1;
            var t_start = a_tag.index(a_tag_v.filter(':first')[0]);
            if (eqs < t_start) {
                eqs = a_tag.index(a_tag_v.filter(':last')[0]);
            }
            a_tag.eq(eqs).addClass('selected');
        };
		/* 搜索结果载入装置 */
        function querySource() {
			var value = input.val().toLowerCase();
            if (value.length == 0) {
                return;
            };
            if (!(value == null || value == '')) {
				ajaxSourceLib();
            };
            setTimeout(function() { searchSuggestShow()},200);
        };
		/* 数据源 */
		function ajaxSourceLib(){
			var bank = bankval.val();
			var bankcode = bankval.attr('data-bankcode');
			if(!(bank == '' || bank == null || bank == '未选择银行')){
				$.ajax({
					url: "",
					type: "GET",
					dataType: "json",
					success: function(data) {
						webUtil.config.BANKSSPOTLIGHT.splice(0,webUtil.config.BANKSSPOTLIGHT.length);
						$.each(data,function(key, items){
							webUtil.config.BANKSSPOTLIGHT.push(items);
						});
					}
				});
			}
		};
		function searchSuggestShow(){
			var value = input.val().toLowerCase();
			var lib_cont = suggestMain.find('.suggest_cont');
			var isHave = false;
			var _tmp = [];
			for (var items in options.data) {
				var entry = options.data[items];
				if (typeof(entry) != 'undefined') {
					if (entry[1].indexOf(value) > -1) {
						isHave = true;
						_tmp.push(entry);
					}
				};
			};
			if (isHave) {
				lib_cont.empty();
				for (var items in _tmp) {
					var entry = _tmp[items];
					if(items < options.suggestLength){
						lib_cont.append('<a href="#" '+(items==0?'class="selected"':'')+'><b>'+entry[1]+'</b><span '+(options.coder?'style="display:block"':'style="display:none"')+'>'+entry[0]+'</span></a>');
					}
				};
				//suggestMain.find('.suggest_head').html(value + ',按拼音排序');
				suggestMain.find('.suggest_head').hide();
				if(options.page){
					setNumberPager(1);
				};
				setTopSelect();
			} else {
				lib_cont.html('');
				suggestMain.find('.suggest_head').html('对不起('+value+')没有找到').show();//.css({'color':'#d35353'})
			};
			suggestMain.show();
		};
		//分页
		if(options.page){
			/* 点击分页事件 */
			suggestMain.find('.pager-city a').live('mouseover',function() {
				suggestFocus = true;
			}).live('mouseout',function() {
				suggestFocus = false;
			});
			suggestMain.find('.pager-city a').live('click',function(event) {
				suggestPageClick = true;
				input.click();
				if ($(this).attr('data-rel') != null) {
					setNumberPager($(this).attr('data-rel'));
				}
			});
			/* 按下键盘右键时向下翻页 */
			function nextPage() {
				var add_cur = suggestMain.find('.pager-city a.current').next();
				if (add_cur != null) {
					if ($(add_cur).attr('data-rel') != null) {
						setNumberPager($(add_cur).attr('data-rel'));
					}
				}
			};
			/* 按下键盘左键时向上翻页 */
			function prevPage() {
				var add_cur = suggestMain.find('.pager-city a.current').prev();
				if (add_cur != null) {
					if ($(add_cur).attr('data-rel') != null) {
						setNumberPager($(add_cur).attr('data-rel'));
					}
				}
			};
			/* 设置分页结果显示页 */
			function setNumberPager(pagerEqs) {
				var a_tag = suggestMain.find('.suggest_cont a');
				a_tag.removeClass('selected');
				suggestMain.find('.suggest_cont').children().each(function(i) {
					var k = i + 1;
					if (k > options.suggestLength * (pagerEqs - 1) && k <= options.suggestLength * pagerEqs) {
						$(this).css('display', 'block');
					} else {
						$(this).hide();
					}
				});
				setTopSelect();
				if(options.page){
					setAddPageHtml(pagerEqs);
				}
			};
			/* 添加分页HTML */
			function setAddPageHtml(pagerEqs) {
				var pageBreak = suggestMain.find('.pager-city');
				pageBreak.empty();
				if (suggestMain.find('.suggest_cont').children().length > options.suggestLength) {
					var pageSize = Math.ceil(suggestMain.find('.suggest_cont').children().length / options.suggestLength);
					if (pageSize <= 1) {
						return;
					};
					var start = end = pagerEqs;
					var number, index;
					for (var j = 0, number = 1; j < options.pageLength && number < options.pageLength; j++) {
						if (start > 1) {
							start--;
							number++;
						};
						if (end < pageSize) {
							end++;
							number++;
						};
					};
					for (var i = start; i <= end; i++) {
						if (i == pagerEqs) {
							pageBreak.append('<a href="#" class="current" data-rel="' + (i) + '">' + (i) + '</a');
						} else {
							pageBreak.append('<a href="#" data-rel="' + (i) + '">' + (i) + '</a');
						};
					};
					pageBreak.show();
				} else {
					pageBreak.hide();
				};
				return;
			};
		};
        /* 设置显示第一条数据高亮 */
        function setTopSelect() {
            if (suggestMain.find('.suggest_cont').children().length > 0) {
                suggestMain.find('.suggest_cont a:first').addClass('selected');
            };
        };
        /* 选择时添加其它选项 */
        function onSelectResult() {
            if (options.onSelect) {
                options.onSelect.apply(input[0]);
            };
        };
    }
})(jQuery);
$(function(){
	
	$('#BankCity').fnSuggestLib({
		url: '',
		data: webUtil.config.CITYSPOTLIGHT,
		onSelect: function(){
			
		}
	});
	$('#HouseBankName').fnBanksLib({
		data: webUtil.config.BANKSSPOTLIGHT,
		width: 300,
		coder: false,
		onSelect: function(){	
		}
	});
});