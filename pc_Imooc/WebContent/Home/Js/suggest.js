/**
 * 全局搜索
 * @version 1.0
 * @author zhangsl
 * @date 2015-8-14
 */
define(function(require, exports, module) {
	var Class = {
		create: function() {
			return function() {
				this.initialize.apply(this, arguments);
			};
		}
	};

	var Suggest = Class.create();
	Suggest.prototype = {
		initialize: function($container) {
			var dom = this.getDom($container);
			this.container = $container;
			this.searchBtn = dom.searchBtn;
			this.suggestTrigger = dom.suggestTrigger;
			this.suggestPanel = dom.suggestPanel;
			this.clearTextBtn = dom.clearTextBtn;
			this.timer = null;
			this.currIndex = -1;
			this.addListener();
		},
		createSuggest: function(key) { // suggest初始化
			var that = this;
			this.timer && clearTimeout(this.timer);
			this.timer = setTimeout(function() {
				if (key) {
					// 如果搜索框不为空，就智能提示与key相关的词
					// 该版本不加此功能
					// that.getTargetSuggest(key);
				} else {
					// 如果用户已登录且搜索框为空，就返回最近的0-5条搜索记录
					if(!isLogin){
						that.clearSuggest();
						return;
					}
					that.getLatestSuggest();
				}
			}, 200);
		},
		getTargetSuggest: function(key) { // 目标关键字智能提示
			var that = this;
			$.ajax({
				type: "POST",
				url: "/index/ajaxgetsearch",
				dataType: "json",
				data: {
					search_title: key
				},
				success: function(res) {
					var data = res && res.data;
					data && that.autoComplete(data);
				}
			});
		},
		getLatestSuggest: function() { // 最近的0-5条搜索记录
			var that = this;
			$.ajax({
				type: "GET",
				url: "/index/searchhistory",
				dataType: "json",
				success: function(res) {
					res.length && that.autoComplete(res);
				}
			});
		},
		addListener: function() { // 事件监听
			// 监听搜索框的相关事件
			var that = this;
			this.suggestTrigger.on({
				/**
				 * 监听键盘事件时，主要有：
				 * 1、上下方向键
				 * 2、回车键
				 * 3、其他输入键
				 */
				keyup: function(evt) {
					that.timer && clearTimeout(that.timer);

					switch (evt.keyCode) {
						case 38: // 上方向键
							that.setCurrIndex('up');
							that.itemSwitch();
							break;
						case 40: // 下方向键
							that.setCurrIndex('down');
							that.itemSwitch();
							break;
						case 13: // 回车键
							that.triggerSearch($(this));
							return false; // or evt.preventDefault(); 取消回车键的默认提交行为
							break;
						default: // 其他输入键
							var key = that.getTriggerValue($(this));
							that.createSuggest(key);
							that.setClearTextBtnState(key);
					}
				},
				focus: function() {
					var key = that.getTriggerValue($(this));
					that.container.addClass('suggest-active');
					that.createSuggest(key);
				},
				blur: function() {
					that.container.removeClass('suggest-active');

					// 延迟执行清空操作，防止suggest项点击操作失效
					setTimeout(function() {
						that.clearSuggest();
					}, 200);
				}
			});

			// 搜索按钮点击处理
			this.searchBtn.on("click", function() {
				var $suggestTrigger = that.getTriggerEl($(this));
				that.triggerSearch($suggestTrigger);
			});

			// suggest项点击操作处理
			this.suggestPanel.on({
				click: function() {
					that.searchBtn.trigger('click');
				},
				mouseover: function(evt) {
					that.currIndex = $(this).index();
				},
				mouseout: function(evt) {
					that.currIndex = -1;
				}
			}, 'li');

			// 清空搜索关键字
			this.clearTextBtn.on('click', function(){
				that.clearSearchText();
			});
		},
		itemSwitch: function(callback) { // 搜索结果项切换
			var $suggestItems = this.suggestPanel.find('li'),
				$currentItem = null,
				currentKey = '';

			$suggestItems.removeClass('light');

			if (this.currIndex > -1) {
				$currentItem = $suggestItems.eq(this.currIndex);
				$currentItem.addClass('light');

				currentKey = $currentItem.data('key');
				this.setTriggerValue(currentKey);
				this.setClearTextBtnState(currentKey);
				callback && callback();
			}
		},
		search: function(keyword) { // 搜索主体方法
			location.href = "/index/search?words=" + encodeURIComponent(keyword);
		},
		autoComplete: function(suggestData) { // 智能提示
			var docFragment = [],
				suggestResult = '';

			this.currIndex = -1;

			if (suggestData.length) {
				for (var i = 0, len = suggestData.length; i < len; i++) {
					var itemStr = this.tpl(suggestData[i]);
					docFragment.push(itemStr);
				}
				suggestResult = docFragment.join('');
				this.suggestPanel.html(suggestResult).slideDown(100);
			} else {
				// 如果没有数据就清空suggest
				that.clearSuggest();
			}
		},
		triggerSearch: function($trigger) {
			if (this.currIndex > -1) {
				var items = this.suggestPanel.find('li');
				this.search(items.eq(this.currIndex).data('key'));
			} else {
				// 如果没有选择suggest result
				var keyword = this.getTriggerValue($trigger);
				if (!keyword) { // 如果搜索框为空
					location.reload();
					//this.setTriggerValue('');
					return false;
				}
				this.search(keyword);
			}
		},
		setTriggerValue: function(key) { // 设置搜索框的值
			this.suggestTrigger.val(key);
		},
		getTriggerValue: function($trigger){
			return $trigger.val().replace(/\s/g, '');
		},
		getTriggerEl: function($el){
			return $el.closest('[data-search]').find('[data-suggest-trigger]');
		},
		setCurrIndex: function(direction) { // 设置当前suggest项的序号
			var items = this.suggestPanel.find('li'),
				itemsCount = items.length;

			if (direction === 'up') {
				this.currIndex = this.currIndex > -1 ? this.currIndex - 1 : itemsCount - 1;
			} else {
				this.currIndex = this.currIndex < itemsCount - 1 ? this.currIndex + 1 : -1;
			}
		},
		setClearTextBtnState: function(key){
			if(!key){
				this.clearTextBtn.addClass('hide');
			}else{
				this.clearTextBtn.removeClass('hide');
			}
		},
		clearSearchText: function(){ // 清空搜索框
			this.setTriggerValue('');
			this.clearTextBtn.addClass('hide');
		},
		clearSuggest: function() { // 清空suggestPanel
			this.suggestPanel.slideUp(100).delay(100).html('');
		},
		tpl: function(dataItem) { // 搜索结果项html模板
			return '<li data-key="' + dataItem.word + '">' + dataItem.word + '</li>';
		},
		getDom: function($el) {
			return {
				searchBtn: $el.find('[data-search-btn]'),
				suggestTrigger: $el.find('[data-suggest-trigger]'),
				suggestPanel: $el.find('[data-suggest-result]'),
				clearTextBtn: $el.find('[data-clear-btn]')
			};
		}
	};

	return Suggest;
});