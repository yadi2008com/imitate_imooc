//进度条
/*  $(obj).progessBar(); */
(function($){
	$.fn.progessBar=function(opt){
		var progessBar = function(obj, opt) {
			this.obj = $(obj);
			this.value = this.obj.val() || this.obj.attr("data-finishVal");
			this.config = {
				width: 300,
				speed: 500
			}
			$.extend(this.config, opt);
			this.init()
		}
		progessBar.prototype = {
			init: function() {
				if (this.obj.css('zoom') == 'normal') { //ie
					var progress = document.createElement('div');
					progress.className = 'progress';
					this.progress = $(progress);
					this.progress.css({
						width: this.config.width
					});
					this.bar = $('<div class="progressBar" />');
					this.bar.css({
						width: 0
					});
					this.progress.append(this.bar);
					this.obj.replaceWith(this.progress)
				}
				else {
					this.obj.css({
						width: this.config.width
					});
					this.obj.val(0);
				}
				this.move(this.value)		
				return this

			},
			move: function(value) {
				if (this.bar) { //ie
					this.bar.stop().animate({
						width: value + '%'
					},
					this.speed)
				} else {
					this.obj.stop().animate({
						'value': value
					},
					this.speed);
				}


			}
		}
		return $(this).each(function(k,v){
			return new progessBar(v,opt);
		})

	}
})(jQuery)
