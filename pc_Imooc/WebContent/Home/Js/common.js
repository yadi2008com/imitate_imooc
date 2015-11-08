define(function(require, exports, module) {
	require('/static/component/base/util/string');
	var	suggest = require('/static/component/base/suggest/suggest'),
		store = require('store');

	//load socket
	require.async('chat', function() {
		$.chat.init();
	});

	//非学习页加载头部和回到顶部脚本
	function popLoginSns() {
		require.async('../../logic/login/login-regist', function(login) {
			login.init();
		});
	}

	// 设置“回到顶部”按钮的显隐状态
	function setGo2TopBtnState() {
		h = $(window).height();
		t = $(document).scrollTop();
		if (t >= 768) {
			$('#backTop').show();
		} else {
			$('#backTop').hide();
		}
	}

	$(function(e) {
		// suggest功能实例化
		var $searchContainer = $('[data-search="top-banner"]');
		new suggest($searchContainer);

		if(OP_CONFIG.page == 'code'){
			$('#J_GotoTop').hide();
		}

		// 页面初始设置“回到顶部”按钮的显隐状态
		setGo2TopBtnState();

		$('[action-type="my_menu"],#nav_list').on('mouseenter', function() {
			$('[action-type="my_menu"]').addClass("hover")
			$('#nav_list').show()
		})
		$('[action-type="my_menu"],#nav_list').on('mouseleave', function() {
			$('[action-type="my_menu"]').removeClass("hover")
			$('#nav_list').hide()
		});
		$('#set_btn').click(function() {
			location.href = '/space/course'
		});

		$('#js-signin-btn').on('click', function(e) {
			e.preventDefault();
			require.async('../../logic/login/login-regist', function(login) {
				login.init();
			});
		});
		$('#js-signup-btn').on('click', function(e) {
			e.preventDefault();
			require.async('../../logic/login/login-regist', function(login) {
				login.signup();
			});
		});

		//点击课程链接 清空原来存储选项
		$("#nav-item a:eq(0)").click(function(event) {
			//store.clear();
			store.remove('lange_id');
			store.remove('pos_id');
			store.remove('tab_id');
			store.remove('is_easy');
			store.remove('sort');
		});

		// 回到顶部
		$('#backTop').click(function() {
			$("html,body").animate({
				scrollTop: 0
			}, 200);
		})

		$(window).scroll(function(e) {
			setGo2TopBtnState();
		});
	});

	// 浏览器版本检测
	!function() {
		var cookie,
			ua,
			match;
		ua = window.navigator.userAgent;
		match = /;\s*MSIE (\d+).*?;/.exec(ua);
		if (match && +match[1] < 9) {
			cookie = document.cookie.match(/(?:^|;)\s*ic=(\d)/);
			if (cookie && cookie[1]) {
				return;
			}
			$("body").prepend([
				"<div id='js-compatible' class='compatible-contianer'>",
				"<p class='cpt-ct'><i></i>您的浏览器版本过低。为保证最佳学习体验，<a href='/static/html/browser.html'>请点此更新高版本浏览器</a></p>",
				"<div class='cpt-handle'><a href='javascript:;' class='cpt-agin'>以后再说</a><a href='javascript:;' class='cpt-close'><i></i></a>",
				"</div>"
			].join(""));
			$("#js-compatible .cpt-agin").click(function() {
				var d = new Date();
				d.setTime(d.getTime() + 30 * 24 * 3600 * 1000);
				//d.setTime(d.getTime()+60*1000);
				document.cookie = "ic=1; expires=" + d.toGMTString() + "; path=/";
				$("#js-compatible").remove();
			});
			$("#js-compatible .cpt-close").click(function() {
				$("#js-compatible").remove();
			});
		}
	}();

	//判断是否有ast参数，提交统计
	!function() {
		var search = window.location.search,
			ref = document.referrer;
		search = /ast=([^&]+)/.exec(search);
		if (ref && ~ref.indexOf('/course/discovery') && search && search[1]) { //从discovery页来 ，避免民刷新当前页统计
			$.get('/index/adclick', {
				ast: search[1],
				r: (new Date).getTime()
			});
		}
		$(document).on('click', '[data-ast]', function() {
			$.get('/index/adclick', {
				ast: $(this).attr('data-ast'),
				r: (new Date).getTime()
			});
		});
	}();
});