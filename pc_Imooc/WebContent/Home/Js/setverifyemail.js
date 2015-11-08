define(function(require, exports, module){
	require('common');
	var mbox=require("/static/page/user/messagebox.js");
    require("/static/component/base/util/modal.button.js");


	$(".js-resetemail").click(function(){

        var m=$(".msg-layer");
        if(!m.length){
            $("body").append('<div class="msg-layer">\
                <h3>身份验证</h3>\
                <p>为保障用户身份安全，我们需要验证您的身份。<br>用户名：'+$(this).data("mail")+'</p>\
                <input type="password" placeholder="请输入慕课网登录密码" /><a href="javascript:void(0)" class="btn-submit">确定</a>\
                <button aria-hidden="true" hidefocus="true" data-dismiss="modal" class="btn-close" type="button"></button>\
            </div>');
            m=$(".msg-layer");
            function sendEmail(){
                var c=$(".msg-layer input").val();
                if(c.length==0){
                	$(".msg-layer input").addClass('error').focus();
                	mbox.error("请输入慕课网登录密码");
                    return ;
                }

				$.ajax({
					url:"/user/ajaxcheckpass",
					data:{
                        passwd:c
                	},
					dataType:"json",
					success:function(data){
						if(data.result==0){
							window.location="/user/resetemail"
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
            m.on("shown",function(){
                $(".msg-layer .btn-submit").on("click",function(event) {
                	sendEmail()
                })

                $(".msg-layer input").on("keydown",function(event){
		            switch (event.keyCode) {
		                case 13:
	                        sendEmail()
		                break;
		            }
                })
            }).on("hidden",function(){
                $(this).remove();
            });
        }
        m.modal("show");


	});
	$("#verify-btn-sent").click(function(){
		var $this=$(this);
		if($this.text()=="正在发送...") return ;
		$this.text("正在发送....");
		$.ajax({
			url:"/user/verificationmail",
			dataType:"json",
			success:function(data){
				
				if(data.status==0){
					window.location.href="/user/setverifysent";
				}
				else{
					mbox.error(data.msg);
					$this.text("发送验证邮件");
				}
				
			},
			error:function(){
				mbox("后台出错，请稍后重试！");
				$this.text("发送验证邮件");
			}
		})

	});

});