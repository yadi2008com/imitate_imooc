define(function(require, exports, module){
	require('common');
	require('/static/lib/layer/1.6.0/layer.min.js');
	//require('../../lib/jquery/plugin/jquery.progress');
	require('./space_common.js');
	 //$('progress').progessBar({width:220});
	var isAjax=0;

 	$.fn.progessNum = function(timer){
 		$(this).each(function(index, obj) {
	 		obj=$(obj)
	 		var val=obj.attr("data-finishVal");
	 		if(!val) return;
            val=Math.floor(val)
            if(val==0) return;
	 		var _timer=Math.ceil(timer/val)
	 		var i=0;
	 		var _fun=function(){
	 			i++
	 			obj.html(i+"%");
	 			if(i!=val){
	 				setTimeout(_fun,_timer)
	 			}
	 		}
	 		_fun();
 		});
    }

    var bindDom=function(){
    	//$('progress').progessBar({width:220});
    	//$('.dot-progress').progessNum()
    }

	var bindEvent=function(){
		$(".js-join").mouseover(function(){
			var str = $(this).attr("data-title");
			if(str.length > 15){
				str = $(this).attr("data-title").substr(0,30)+'...';
				$(this).html(str);
				//$(this).width(240);
			}
			else{
				$(this).html(str);
			}
			if(str  == "近期开放"){
				$(this).width(100);
			}
			else{
				$(this).width(300);
			}

		}).mouseout(function(){
			var str = $(this).attr("data-title");
			if(str  == "近期开放"){
				$(this).html("近期开放");
				$(this).width(100);
			}
			else{
				$(this).html("开始学习");
				$(this).width(100);
			}
		})

		$(".js-program-edit").click(function(){
			var _t=$(this).data("edit") || 0;

			if(!_t){
				$(this).data("edit",1).addClass('status-edit').html('<b>完成</b>')
				$(".follow-list").addClass('editable');
			}else{
				$(this).data("edit",0).removeClass('status-edit').html('<i class="icon icon-del"/><b>删除</b>')
				$(".follow-list").removeClass('editable');
			}
		})

		$(".follow-list").on('click', '.js-btn-del', function(e){
			var obj=$(this).parent("li");
			var id=obj.attr("data-id");
			var coverlay='<div class="contain-coverLayer"></div>';
			var popsucc='<div id="js-pop-del" class="pop-deleting"><div class="deleting-bd"></div><div class="deleting-btm">删除成功</div></div>';


			$.ajax({
				type: 'POST',
				url: '/space/ajaxfollowcancel/?course_id=' + id,
				dataType:"json",
				data: {
					course:id
				},
				success: function(res){
					if(res.result==0){
						obj.remove();


		   			$("body").append(popsucc).append(coverlay);
						setTimeout(function(){
							$(".pop-deleting").remove();
							$(".contain-coverLayer").remove();
						},1000);

						//layer.msg('删除成功', 1, 1);
						if($(".follow-list li").length==0){
							$(".follow-list").html('<div class="uncourse"><p>暂无任何关注的课程</p></div>');
							location.replace(location.href.replace(/\/page\/\d+|page=\d+/i, ''));
						}
					}else{
						layer.msg('删除失败，请稍后再试', 1, 1);
					}
				},
				error:function(){
					layer.msg('网络错误，请稍后再试', 1, 1);
            	}
			});
		})
	}

	bindDom();
	bindEvent();



});
