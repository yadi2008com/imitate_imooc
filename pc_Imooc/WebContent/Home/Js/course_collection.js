define(function(require, exports, module){
	require('/static/lib/layer/1.6.0/layer.min.js');
	require('/static/lib/layer/1.6.0/skin/layer.css');
	//关注课程

    var isAjax = 0;
	$('.js-follow-action').on('click',function() {
        if (!OP_CONFIG.userInfo) {
            function popLogin() {
                require.async('login_sns',
                function(login) {
                    login.init();
                });
            };
            popLogin();
            return;
        }
		if(isAjax) return;
		isAjax=1;

		var obj=$(this),
			id=obj.data("cid"),
			cmd = obj.data('cmd'),
			urls={
				follow: "/space/ajaxfollow",
				cancle: "/space/ajaxfollowcancel"
			};

		$.ajax({
			type: "POST",
			url: urls[cmd],
			dataType:"json",
			data: {
				course_id:id
			},
			success: function(res){
				if(res.result==0){
					if(obj.data('cmd') == 'cancle'){
						obj.removeClass('followed').html("关注").data('cmd', 'follow');
					}else{
						obj.addClass('followed').html("已关注").data('cmd', 'cancle');
					}			
				}else{
					layer.msg('操作失败，请稍后再试', 1, 1);
				}
			},
			error:function(){
				layer.msg('网络错误，请稍后再试', 1, 1);
        	},
        	complete: function(){
        		isAjax=0;
        	}
		});
	});
})