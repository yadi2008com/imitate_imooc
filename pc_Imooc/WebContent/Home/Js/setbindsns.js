define(function(require, exports, module){
	require('common');
	var mbox=require("/static/page/user/messagebox.js");
    require("/static/component/base/util/modal.button.js");
	var winsns=(function(){
	    var o={};
	    function clearPrev(){//dereference
	        for(var key in o){
	            if(key.indexOf("/user")>-1){
	                o[key].close&&o[key].close();
	                o[key]=null;
	                delete o[key];
	            }
	        }
	    }

	    return {
	        open:function(url){
	            var l,t;
	            if(o[url]&&o[url].closed===false){
	                o[url].focus&&o[url].focus();
	                return ;
	            }
	            clearPrev();
	            l=(screen.width-600)/2,
	            t=(screen.height-400)/2;
	            (o[url]=window.open(url, '_blank', 'toolbar=no, directories=no, status=no, menubar=no, width=600, height=500, top='+t+', left='+l)).focus();
	            //o[url].onclose=winClose;

	        },
	        clear:clearPrev
	    }
	})();

	$(document).on("click",".js-bind",function(e){
		e.preventDefault();
		winsns.open($(this).attr("href"));
	});

	$(document).on("click","[data-unbind]",function(){
		var type=$(this).attr("data-unbind");
		var str={'qq':'QQ', 'weibo':'新浪微博', 'weixin':'微信'}[type];

        var m=$(".msg-layer");
        if(!m.length){
            $("body").append('<div class="msg-layer">\
                <h3>解除绑定</h3>\
                <p>解绑后将不能再使用'+str+'帐号登录慕课网。<br>用户名：'+UserEmail+'</p>\
                <input type="password" placeholder="请输入慕课网登录密码" /><a href="javascript:void(0)" class="btn-submit">确定</a>\
                <button aria-hidden="true" hidefocus="true" data-dismiss="modal" class="btn-close" type="button"></button>\
            </div>');
            m=$(".msg-layer");
            m.on("shown",function(){
            	function unbind(){
                    var c=$(".msg-layer input").val();
                    if(c.length==0){
                    	$(".msg-layer input").addClass('error').focus();
                    	mbox.error("请输入慕课网登录密码");
                        return ;
                    }

					$.ajax({
						url:"/user/ajaxunbindsns",
						data:{
	                        passwd:c,
	                        t:type
                    	},
						dataType:"json",
						success:function(data){
							if(data.result==0){
								window.location.reload();
							}
							else{
								mbox.error(data.msg);
							}
						},
						error:function(){
							mbox.error("服务器错误，请稍后重试！");
						}

					})
            	}
                $(".msg-layer .btn-submit").on("click",function(event) {
                	unbind();
                })

                $(".msg-layer input").on("keydown",function(event){
		            switch (event.keyCode) {
		                case 13:
	                        unbind()
		                break;
		            }
                })


            }).on("hidden",function(){
                $(this).remove();
            });
        }
        m.modal("show");


	});
});
