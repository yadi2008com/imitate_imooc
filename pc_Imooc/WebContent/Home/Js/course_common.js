define(function(require, exports, module) {
    require('common');
    require('../../../lib/jquery/plugin/jquery.progress');
    require('/static/lib/layer/1.6.0/layer.min.js');
    require('/static/lib/layer/1.6.0/skin/layer.css');

    if ($('progress').length && $.fn.progessBar) {
        $('progress').progessBar({
            width: 1200
        });
    }
    //进度提示

    $('#course-progress').hover(function() {
            $('#learn-tips').stop().fadeIn(100);
        }, function() {
            $('#learn-tips').stop().fadeOut(100);
        })
        //公告
    var noticeid;
    /*$('#JnoticeClose').on('click',function(){
    	$('#notice').remove();
    	if (typeof noticeid!=='undefined'){
    		$.post('/course/ajaxreadnotice',{noticeid:noticeid});
    	}
    });*/
	var popLayerTpl = '<div id="noticLayer" class="pop-notice">'
						+ '<div class="pop-header clearfix"> <h2 class="l">课程公告</h2><a id="noticLayerClose" href="javascript:void(0)" class="r icon-close pop-close"></a></div>'
						+ '<dl id="noticeContent" class="pop-content"></dl>'
						+ '</div>',
		noticeTpl = '<dt data-id="{id}" class="">'
					+ '<span class="r">{time}</span><a class="fold icon-plus" href="javascript:void(0)"></a>{title}'
					+ '<dd class="autowrap">{text}</dd>'
					+ '</dt>'

    $('#notice').on('click', function() {
        //创建公告
        var $overlay = $('<div id="overlay" class="pop-overlay"></div>').height($(document).height()).appendTo($('body'));
        var $notice = $(popLayerTpl),
        	content = $notice.find('.pop-content');

        $notice.hide().appendTo($('body'))
            .on('click', '.pop-close', function() {
                $overlay.remove();
                $notice.remove();
            })
            .on('click', '.pop-content dt', function() {
                var $this = $(this),
                	active = $this.siblings('dt.noticeopen');

                if($this.hasClass('noticeopen')){
                	$this.removeClass('noticeopen')
                	    .next('dd').hide();
                	$this.find('.fold').toggleClass('icon-sub');
                }else{
                	active.removeClass('noticeopen')
                		.find('.fold').toggleClass('icon-sub').end()
                		.next('dd').hide();

                	$this.addClass('noticeopen')
                	    .next('dd').slideDown();
                	$this.find('.fold').toggleClass('icon-sub');
                }

            });

        //写入公告内容
        $.getJSON('/course/ajaxgetnotice', {
            'cid': GC.course.id
        }, function(json) {
            if (json.result == 0 && json.data.length > 0) {
                noticeid = json.data.id;
                var noticeHtml = '';

                $.each(json.data, function(k, v) {
                	noticeHtml += simpleTpl(noticeTpl, {
                		id: v.id,
                		title: v.title,
                		text: v.content,
                		time: v.create_time
                	});
                });
                content.append(noticeHtml);
            } else {
                content.html(' 貌似还没有人发点什么')
            }
            $notice.show().animate({
            	opacity: 1
            }, 300, function(e){
            	content.find('dt:first').click();
            });
        });

    });

	$('.js-verify-box').on('click', '.js-verify-refresh', function(e){
		$(this).parents('.js-verify-box').trigger('reset');
	}).on('reset', function(e, isFocus){
		var box = $(this),
			ipt = box.find('.ipt'),
			ctrl = box.find('.js-verify-refresh:first'),
			img = box.find('.verify-media'),
			imgUrl = img.data('src').split('?')[0],
			prefix = new Date().getTime(),
			isFocus = isFocus == 0 ? isFocus : 1;

		img.attr('src', imgUrl + '?' + prefix);
		ipt.val('');

		isFocus && ipt.focus().select();
	}).on('error', function(e){
		$(this).addClass('vf-error');
	}).on('validate', function(e){
		var ipt = $(this).find('.ipt'),
			num = ipt.val().trim();

		if(!num || num.length < 4){
			$(this).trigger('error');
		}
	}).find('.ipt').on('keyup', function(e){
		$(this).parents('.js-verify-box').removeClass('vf-error');
	}).on('blur', function(e){
		var box = $(this).parents('.js-verify-box');
	});


	// 进入学习按钮
	if(!isLogin){
		$('.J-learn-course').on('click', function(e){
			seajs.use('login_sns', function(obj){
				obj.init();
			});
			return false;
		});
	}

	// 你的同学 换一批
	var tpl = '<li>' +
				'<a href="/space/u/uid/{uid}" target="_blank" title="{nickname}">' +
					'<img src={portrait}/>' +
				'</a>' +
			'</li>';

	$('.js-refresh-classmates').on('click', function(e){
		var _this = $(this);

		if(_this.hasClass('waiting')) return;
		_this.addClass('waiting');

		$.post('/course/classmates', {
		    cid: GC.course.id,
		    total: GC.classmates
		}, function(data) {
			if(!data || typeof data === 'string'){
				layer.msg('数据加载失败，请重试！', 1, 1);
				return;
			}
			var box = $('#users-mini');

			_this.addClass('waiting');

			box.find('li').removeClass('show in').addClass('out');

		    setTimeout(function(){
		    	box.empty();

		    	$.each(data, function(i, user){
		    		var u = simpleTpl(tpl, user);

		    		u = $(u);

		    		if((i + 1) % 4 == 0){
		    			u.addClass('row-last');
		    		}

		    		u.hide().addClass('in').appendTo(box);
		    	});

		    	box.find('li').show().addClass('show');

		    	_this.removeClass('waiting');
		    }, 600);
		}).error(function(){
			_this.removeClass('waiting');
		});

	});

	// 相关课程tab切换
	$('.js-comp-tabs').each(function(){
		var box = $(this),
			tabs = box.find('.js-comp-tab-item'),
			pannels = box.find('.js-comp-tab-pannel');

		tabs.on('click', function(e){
			if($(this).hasClass('on')) return;

			var pannel = $(this).data('pannel');

			tabs.removeClass('on');

			$(this).addClass('on');

			pannels.hide().filter(function(){
				return $(this).data('pannel') == pannel;
			}).show();

			return false;
		})
	})


	// canvas 学习页课程背景图，支持渐变效果(IE8以上以及非IE)
	function drawCourseCoverCanvas(){
		var img = new Image(),
			cs = document.createElement('canvas'),
			context = cs.getContext('2d'),
			bgView = $('#js-info-bg'),
			bw = bgView.width(),
			bh = bgView.height(),
			ratio = bw / bh;

		img.onload = function(e){
			var iw = img.width,
				ih = img.height,
				iratio = iw / ih,
				sw, sh, o,
				sx, sy;

			cs.width = bw;
			cs.height = bh;
			cs.className = 'cover-canvas';
			cs.id = 'js-cover-canvas';

			if(ratio > iratio){
				o = iw / ratio;
				sy = (ih - o) / 2;

				context.drawImage(img, 0, sy, iw, o, 0, 0, bw, bh);
			}else if(ratio < iratio){
				o = ih * ratio;
				sx = (iw - o) / 2;

				context.drawImage(img, sx, 0, o , ih, 0, 0, bw, bh);
			}

			var lg = context.createLinearGradient(0, 0, bw, 0);

			lg.addColorStop(0, 'rgba(0, 0, 0, .8)');
			lg.addColorStop(1, 'rgba(0, 0, 0, .2)');

			context.fillStyle = lg;

			context.beginPath();
			context.fillRect(0, 0, bw, bh);
			context.fill();
			//ctx.fill();

			bgView.append(cs);
		}

		img.src= $('#js-cover-img').data('src');
	}

	// img 学习页顶部课程背景(IE9以下)
	function showCourseCoverImg(){
		$('#js-info-bg').addClass('no-canvas');

		var img = new Image(),
			view = $('#js-info-bg'),
			vw = view.width(),
			vh = view.height(),
			coverImg = $('#js-cover-img'),
			imgSrc = coverImg.data('src');

		img.onload = function(){
			var iw = img.width,
				ih = img.height,
				left = 0,
				top = 0,
				wratio, hratio,
				temp, nw, nh;

			wratio = vw / iw;
			hratio = vh / ih;

			nw = wratio * ih;
			nh = hratio * iw;

			if(nh > vh){
				nw = vw;
			}else{
				nh = vh;
			}

			coverImg.attr('src', imgSrc).css({
				width: '100%'
			}).show();


			img.onload = null;
		}
		img.src = imgSrc;

		coverImg[0].ondragstart = function(e){
			return false;
		}
	}

	function displayCourseCover(){
		var testCanvas = document.createElement('canvas');

		if(testCanvas.getContext){
			drawCourseCoverCanvas();
		}else{
			showCourseCoverImg();
		}
	}
	displayCourseCover();

	/**
	 * 简易字符串模板替换
	 * @param  {string} str  模板字符串
	 * @param  {json} data   渲染数据
	 * @return {string}      渲染后字符串
	 */
	function simpleTpl(str, data) {
		return str.replace(/\{([^\}]*)\}/ig, function(p, o) {
		    return data[o] == null ? "" : data[o]
		})
	}

	return {
		tpl: simpleTpl
	}
});
