define(function(require, exports, module){
	require('common');
	require('/static/lib/layer/1.6.0/layer.min.js');
	 require('./space_common.js');
	 require('../../lib/jquery/plugin/jquery.progress');

	 var isAjax=0;
	var bindEvent=function(){
		//20150120后改变
		//$('progress').progessBar({width:830});
        //
		//$(".js-join").mouseover(function(){
		//	$(this).html($(this).attr("data-title"))
		//}).mouseout(function(){
		//	$(this).html("开始学习")
		//});

		//$(".js-program-edit").click(function(){
		//	var _t=$(this).attr("data-edit");
		//	if(_t=="0"){
		//		$(this).attr("data-edit","1").attr('class','space-tabs-edit-hover').html("完成")
		//		$(".js-btn-del").show()
		//	}else{
		//		$(this).attr("data-edit","0").attr('class','space-tabs-edit').html("")
		//		$(".js-btn-del").hide()
		//	}
		//});
		$(".js-program-edit").click(function(){
			var _t=$(this).data("edit");
			if(_t=="0"){
				$(this).data("edit","1").addClass('status-edit').html("<b>完成</b>");
				//$(".js-btn-del").show()
				$('.program-list').addClass("operate");
				$('.course-list').addClass('editable');
			}else{
				$(this).data("edit","0").removeClass('status-edit').html('<i class="icon icon-del"></i><b>删除</b>')
				//$(".js-btn-del").hide()
				$('.program-list').removeClass("operate");
				$('.course-list').removeClass('editable');
			}
		});


		/*$(".js-btn-del").on("click",function(){
			if(isAjax) return
			isAjax=1
			var obj=$(this).parent("li");
			var id=obj.attr("data-id")

			$.ajax({
				type: "POST",
				url: "/space/ajaxdelprogram",
				dataType:"json",
				data: {
					pid:id
				},
				success: function(res){
					isAjax=0;
					if(res.result==1){
						obj.remove()
						layer.msg('删除成功', 1, 1);
						if($(".program-list li").length==0){
							$(".program-list").html('<div class="unprogram"><span></span><p>您还没有添加任何计划</p></div>')
						}
					}else{
						layer.msg('删除失败，请稍后再试', 1, 1);
					}
				},
				error:function(){
					layer.msg('网络错误，请稍后再试', 1, 1);
                	isAjax=0
            	}
			});
		});*/
		$('.program-list').on('click','.program-item-del',function(){
			if(isAjax) return
			isAjax=1
			var obj=$(this).parent("li");
			var id=obj.attr("data-id")

			$.ajax({
				type: "POST",
				url: "/space/ajaxdelprogram",
				dataType:"json",
				data: {
					pid:id
				},
				success: function(res){
					isAjax=0;
					if(res.result==1){
						obj.remove()
						layer.msg('删除成功', 1, 1);
						if($(".program-list li").length==0){
							$(".program-list").html('<div class="uncourse"><span></span><p>您还没有添加任何计划</p></div>')
						}
					}else{
						layer.msg('删除失败，请稍后再试', 1, 1);
					}
				},
				error:function(){
					layer.msg('网络错误，请稍后再试', 1, 1);
					isAjax=0
				}
			});
		});

		$('.course-list').on('click','.del',function(){
			if(isAjax) return
			isAjax=1
			var obj=$(this).parent("li");
			var id=obj.attr("data-id")

			$.ajax({
				type: "POST",
				url: "/space/ajaxdelprogram",
				dataType:"json",
				data: {
					pid:id
				},
				success: function(res){
					isAjax=0;
					if(res.result==1){
						var $el, num;
						obj.remove();
						layer.msg('删除成功', 1, 1);

						$el = $('.js-count-plan .got-num');
						if ($el.length) {
							num = parseInt($el.text(), 10);
							num--;
							if (num <= 0 ) {
								num = 0;
							}
							$el.text(num);
							if (!$('.course-list li').length) {
								location.replace(location.href.replace(/\/page\/\d+|page=\d+/i, ''));
							}
						}
					}else{
						layer.msg('删除失败，请稍后再试', 1, 1);
					}
				},
				error:function(){
					layer.msg('网络错误，请稍后再试', 1, 1);
					isAjax=0
				}
			});
		});
	}

	bindEvent();

});
