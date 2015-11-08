define(function(require, exports, module){
	//require('./login-regist.css');
	require("../../base/placeholder/placeholder.js");
	require("../../base/util/modal.button.js");
	require("../../base/util/validate.js");
	require('../../base/autocomplete/autocomplete.js');
	require('/static/css/poplogin-less.css');


	
	

	function signupTpl(){
		/*
		<div id="signup" class="rl-modal ">
		  <div class="rl-modal-header">
		    <button type="button" class="rl-close" data-dismiss="modal" aria-hidden="true"></button>
		    <h1>
				<span data-fromto="signup:signin">登录</span>
				<span class="active-title">注册</span>
		    </h1>
		  </div>
		  <div class="rl-modal-body">
		    <form id="signup-form">
				<p class="rlf-tip-globle rlf-g-tip" id="signup-globle-error"></p>
				<div class="rlf-group proclaim-loc">
					<input  type="text" name="email" data-validate="email" class="ipt ipt-email" autocomplete="off" placeholder="请输入电子邮箱地址"/>
					<input style="display:none;" >
					<p class="rlf-tip-wrap"></p>
				</div>
				<div class="rlf-group proclaim-loc js-proclaim-on">
					<a href="javascript:void(0)" hidefocus="true" class="proclaim-btn js-proclaim icon-show-pw is-pwd"></a>
					<input type="password" name="password" data-validate="password" class="ipt ipt-pwd js-pass-pwd" placeholder="6-16位密码，区分大小写，不能用空格"/>
			        <!--ie8 hack-->
			        <input type="text" name="password" data-validate="password" class="ipt ipt-pwd js-txt-pwd" placeholder="6-16位密码，区分大小写，不能用空格"/>
			        <p class="rlf-tip-wrap"></p>
				</div>
				<div class="rlf-group">
					<input  type="text" name="nick" data-validate="nick" class="ipt ipt-nick" placeholder="昵称为2-18位，中英文、数字及下划线"/>
					<p class="rlf-tip-wrap"></p>
				</div>
				<div class="rlf-group clearfix">
				    <input type="text" name="verify" class="ipt ipt-verify l" placeholder="请输入验证码">
				    <a href="javascript:void(0)" hidefocus="true" class="verify-img-wrap js-verify-refresh"></a>
				    <a href="javascript:void(0)" hidefocus="true" class="icon-refresh js-verify-refresh"></a>
					<p class="rlf-tip-wrap"></p>
				</div>
				<div class="rlf-group clearfix">
					<input  type="button" id="signup-btn" value="注册" hidefocus="true" class="btn-red btn-full r"/>
				</div>
		    </form>
		  </div>
		  <div class="rl-model-footer">
		  	<div class="pop-login-sns clearfix">
		  		<span class="l">其他方式登录</span>
		  		<a href="javascript:void(0)" hidefocus="true" data-login-sns="/user/loginweibo" class="pop-sns-weibo r"><i class="icon-weibo"></i></a>
		  		<a href="javascript:void(0)" hidefocus="true" data-login-sns="/user/loginqq" class="pop-sns-qq r"><i class="icon-qq"></i></a>
		  		<a href="javascript:void(0)" hidefocus="true" data-login-sns="/user/loginweixin" class="pop-sns-weixin r"><i class="icon-weixin"></i></a>
		  	</div>
		  </div>
		</div>
		 */
	}
	function signinTpl(){
		/*
		<div id="signin" class="rl-modal">
		  <div class="rl-modal-header">
		    <h1>
				<span class="active-title">登录</span>
				<span data-fromto="signin:signup">注册</span>
		    </h1>
		    <button type="button" class="rl-close" data-dismiss="modal" hidefocus="true" aria-hidden="true"></button>
		  </div>
		  <div class="rl-modal-body">
		  	<div class="clearfix">
				<div class="l-left-wrap l">
					<form id="signup-form" autocomplete="off">
						<p class="rlf-tip-globle " id="signin-globle-error"></p>
						<div class="rlf-group">
							<input type="text" value="" name="email" data-validate="email" autocomplete="off" class="ipt ipt-email js-own-name" placeholder="请输入登录邮箱"/>
							<p class="rlf-tip-wrap"></p>
						</div>
						<!-- fake fields are a workaround for chrome autofill getting the wrong fields -->
						<input style="display:none" type="text" name="fakeusernameremembered"/>
						<input style="display:none" type="password" name="fakepasswordremembered"/>
						<div class="rlf-group">
							<input  type="password" name="password" autocomplete="off" class="ipt ipt-pwd" placeholder="请输入密码"/>
							<p class="rlf-tip-wrap"></p>
						</div>
						<div class="rlf-group js-verify-row clearfix" style="display: none">
						    <input type="text" name="verify" class="ipt ipt-verify l" placeholder="请输入验证码">
						    <a href="javascript:void(0)" class="verify-img-wrap js-verify-refresh"></a>
				    		<a href="javascript:void(0)" class="icon-refresh js-verify-refresh"></a>
							<p class="rlf-tip-wrap"></p>
						</div>
						<div class="rlf-group rlf-appendix clearfix">
							<label for="auto-signin" class="rlf-autoin l" hidefocus="true"><input type="checkbox" checked="checked" class="auto-cbx" id="auto-signin">下次自动登录</label>
							<a href="/user/newforgot" class="rlf-forget r" target="_blank" hidefocus="true">忘记密码 </a>
						</div>
						<div class="rlf-group clearfix">
							<input  type="button" id="signin-btn" value="登录" hidefocus="true" class="btn-red btn-full"/>
						</div>
				    </form>
				</div>
		  	</div>
		  </div>
		  <div class="rl-model-footer">
			<div class="pop-login-sns clearfix">
				<span class="l">其他方式登录</span>
				<a href="javascript:void(0)" hidefocus="true" data-login-sns="/user/loginweibo" class="pop-sns-weibo r"><i class="icon-weibo"></i></a>
				<a href="javascript:void(0)" hidefocus="true" data-login-sns="/user/loginqq" class="pop-sns-qq r"><i class="icon-qq"></i></a>
				<a href="javascript:void(0)" hidefocus="true" data-login-sns="/user/loginweixin" class="pop-sns-weixin r"><i class="icon-weixin"></i></a>
			</div>
		  </div>
		</div>
		*/
	}

    var tpl = {
  		signup:signupTpl,
		signin:signinTpl
	}


	function getTpl(m){
		var r=/\/\*([\S\s]*?)\*\//m,
			m=r.exec(tpl[m].toString());
		return m&&m[1]||m;
	}

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
			},
			clear:clearPrev
		}
	})();
	$(function(){
		$(document).on("click","[data-fromto]",function(e){
			e.preventDefault();
			var d=$(this).attr("data-fromto").split(":");
			$("#"+d[0]).modal("hide");
			d[1]&&m[d[1]]();
		})
		.on("click","[data-login-sns]",function(){
			winsns.open($(this).attr("data-login-sns"));
		})
		.on("click",".j-chgvtfimg",function(){
			var $p=$(this).prev("img");
			$p.attr("src",$p.attr("src").replace(/\?\d+|$/,"?"+(new Date()).getTime()));
		});
	})

	function showModal(p){
		var m;
		p=p||"signin";
		m=$("#"+p);
		if(!m.length){
			$("body").append(getTpl(p));
			m=$("#"+p);
			m.on("shown",function(){
				$(this).find("form")[0].reset();
				$(this).find("input").placeholder();
			});
		}
		m.modal("show");
	}

	// 是否需要验证码
	var loginWithCode = false;

	var showLoginVerify = function(){
		loginWithCode = true;
		$('#signup-form').find('.js-verify-row').show();
	};

	var m={
		signin:function(){
			var tpl=getTpl("signin");
			return function(){
				var m=$("#signin");
				if(m.length) m.remove();
				$("body").append(getTpl("signin"));
				if(typeof(ownName) !== "undefined"){$(".js-own-name").val(ownName)};
				m=$("#signin");
				m.on("shown",function(){
					$(this).find("form").validateSetup({
						fields:{
							email: {
								rules: [{
									rule: function(cb, v){
										// 账号输入后检测是否需要显示验证码
										if(!loginWithCode){
											$.getJSON('/user/checkip?_t=' + new Date().getTime, function(data){
												if(data.status == 12){
													showLoginVerify();
												}
											});
										}else{
											showLoginVerify();
										}
									}
								}]
							},
							password:{
								rules:[function(cb,v){
									if(!v){
										return "密码不能为空！";
									}else if(v.length < 6){
										return "密码长度不能少于6位！"
									}
								}],
								keyup:function(e){
									if(e.keyCode=="13"){
										$("#signin-btn").trigger("click");
									}
									else{
										$(this).validate();
									}

								}
							},
							verify: {
								rules:[
								{
									rule: function(cb, v){
										if(!loginWithCode) return true;
										if(!v) {
											return "验证码不能为空！";
										}else if(v.length != 4){
											return "验证码错误";
										}
										return $.ajax({
											url:"/user/ajaxchecklogincode",
											method:"post",
											data:{verify: v},
											dataType:"json",
											success:function(data){
												if(data.result == 0){
													cb();
												}
												else{
													cb(data.msg)
												}
											}
										})
									}
								}
								],
								keyup: (function(){
									var interval=300,timer;
									return function(e){
										var $this=$(this),
											val = $.trim($this.val());

										if(timer) clearTimeout(timer);
										timer=setTimeout(function(){
											$this.validate(e);
										},interval);
									}
								})(),
								blur: function(e){
									/*$(this).siblings(".rlf-tip-wrap").find("span").hide();*/
									$(this).validate(e);
								}/*,
								focus: function(){
									$(this).siblings(".rlf-tip-wrap").find("span").show();
								}*/
							}
						},
						onerror:function(e){
							var $t,$d;
							if(e._relateField&&e.tip){
								$t=$(e._relateField);
								$t.addClass("ipt-error").siblings(".rlf-tip-wrap").html(e.tip).addClass("rlf-tip-error");
								($d=$t.data("placeholder-textinput"))&&$d.addClass("ipt-error");
							}
							$("#signin-globle-error").removeClass("rlf-tip-error").empty();
						},
						onvalid:function(e){
							var $t,$d;
							if(e._relateField){
								$t=$(e._relateField);
								$t.removeClass("ipt-error").siblings(".rlf-tip-wrap").removeClass("rlf-tip-error").empty();
								($d=$t.data("placeholder-textinput"))&&$d.removeClass("ipt-error");
							}
							$("#signin-globle-error").removeClass("rlf-tip-error").empty();
						}
					});


					$("#signin-btn").button({loadingText:"正在登录..."})
					.on("click",function(){
						var $this=$(this);
						var signInForm=$('#signup-form');
						if($this.hasClass("disabled")){ return;}
						$this.button("loading");
						$this.closest("form").validate({
							success:function(vals){
								var remember=$("#auto-signin")[0].checked?"1":"0",
									params = {
										username:vals.email,
										password:vals.password,
										remember:remember
									};

								if(loginWithCode){
									params.verify = vals.verify;
								}

								$.ajax({
									url:"/user/login",
									data: params,
									method:"post",
									dataType:"json",
									success:function(data){
										if(data.status===1){
											fireLogined(data.data.userInfo);
											return ;
										}
										else if(data.status==5){
											window.location.href="/user/userfrozen";
											return ;
										}
										else if(data.status == 9){ // 连续三次登陆失败
											showLoginVerify();
										}
										$("#signin-globle-error").addClass("rlf-tip-error").html(data.msg);

										if(loginWithCode){
											refreshVerifyCode();
											signInForm.find('.ipt-verify').val('');
										}
										//$("#signin-btn").button("reset");
									},
									error:function(){
										$("#signin-globle-error").addClass("rlf-tip-error").html("服务错误，稍后重试");
									},
									complete:function(){
										$("#signin-btn").button("reset");
									}

								});

							},
							error:function(){
								if(signInForm.find('.ipt-verify').val()){
									refreshVerifyCode();

									var interval=500,valTimer;

									if(valTimer) clearTimeout(valTimer);
									valTimer=setTimeout(function(){
										signInForm.find('.ipt-verify').val('');
									},interval)
								}

								setTimeout('$("#signin-btn").button("reset");',1);
							}
						})
					});
					//placeholder  init;
					$(this).find("input").placeholder();

					var signInForm = $(this),
						verifyCodeUrl = '/user/getlogincode?_=',
						refreshVerifyCode = function(){
							signInForm.find('.verify-img').attr('src', verifyCodeUrl + new Date().getTime());
						};


					signInForm.find('.verify-img-wrap').append(
						$('<img class="verify-img"/>')
					);

					refreshVerifyCode();

					signInForm.on('click', '.js-verify-refresh', function(e){
						refreshVerifyCode();
					});

				}).on("hidden",function(){
					$(this).remove();
					winsns.clear();
				});
				m.modal("show");
			}
		}(),
		signup:function(){
			var tpl=getTpl("signup");
			return function(){
				var m=$("#signup");
				if(m.length) m.remove();
				$("body").append(getTpl("signup"));
				m=$("#signup");
				m.on("shown",function(){
					$(this).find("form").validateSetup({
						fields:{
							email:{
								rules:[{
									rule:function(cb,v){
										return $.ajax({
											url:"/user/ajaxcheckemail",
											method:"post",
											data:{email:v},
											dataType:"json",
											success:function(data){
												if(data.status==1){
													cb();
												}
												else{
													cb(data.msg)
												}
											}
										})
									}
								}],
								/*keyup:(function(){
									var interval=300,timer;
									return function(e){
										var $this=$(this);
										if(timer) clearTimeout(timer);
										timer=setTimeout(function(){
											$this.validate(e);
										},interval);
									}
								})(),*/
								blur:function(e){
									$(this).validate(e);
								},
								focus:function(){
									$(".ipt-email").autocomplete();
								}
							},
							password:{
								blur:function(e){
									//rgrTip.empty();
									$(this).validate(e);
								}
								/*focus:function(){
									rgrTip.html("6-16位密码，区分大小写，不能使用空格");
								},
								onvalid: function(e) {
									$('input[name="cfmpwd"]', this.form).validate(e);
								}*/
							},
							nick:{
								rules:[{
									rule:function(cb,v){
										return $.ajax({
											url:"/user/ajaxcheckusername",
											method:"post",
											data:{nick:v},
											dataType:"json",
											success:function(data){
												if(data.status==1){
													cb();
												}
												else{
													cb(data.msg)
												}
											}
										})
									}
								}],
								blur:function(e){
									//rgrTip.empty();
									$(this).validate(e);
								}
								/*,
								focus:function(){
									rgrTip.html("2-18位中英文、数字或下划线");
								}*/
							},
							/*cfmpwd:{
								rules:[function(cb,v) {
									if(!v) {
										return "验证密码不能为空！";
									}
									if($('input[name="password"]',this.form).val() !== v) {
										return '两次密码不一致！';
									}
								}],
								blur:function(e){
									$(this).next(".rlf-tip-wrap").find("span").hide();
									$(this).validate(e);
								},
								focus:function(){
									$(this).next(".rlf-tip-wrap").find("span").show();
								}
							},*/
							verify: {
								rules:[
								{
									rule: function(cb, v){
										if(!v) {
											return "验证码不能为空！";
										}
										if(v.length != 4){
											return "验证码错误";
										}
										return $.ajax({
											url:"/user/ajaxcheckcode",
											method:"post",
											data:{verify: v},
											dataType:"json",
											success:function(data){
												if(data.result == 0){
													cb();
												}
												else{
													cb(data.msg)
												}
											}
										})
									}
								}
								],
								keyup: (function(){
									var interval=300,timer;
									return function(e){
										var $this=$(this),
											val = $.trim($this.val());

										if(timer) clearTimeout(timer);
										timer=setTimeout(function(){
											$this.validate(e);
										},interval);
									}
								})(),
								blur: function(e){
									$(this).validate(e);
									
								}/*,
								focus: function(){
									rgrTip.html("请输入右侧图片内容");
								}*/
							}
						},
						onerror:function(e){
							var $t,$d;
							if(e._relateField&&e.tip){
								$t=$(e._relateField);
								$t.addClass("ipt-error").siblings(".rlf-tip-wrap").html(e.tip).addClass("rlf-tip-error");
								($d=$t.data("placeholder-textinput"))&&$d.addClass("ipt-error");
							}
						},
						onvalid:function(e){
							var $t,$d;
							if(e._relateField){
								$t=$(e._relateField);
								$t.removeClass("ipt-error").siblings(".rlf-tip-wrap").removeClass("rlf-tip-error").empty();
								($d=$t.data("placeholder-textinput"))&&$d.removeClass("ipt-error");
							}
							$("#signup-globle-error").removeClass("rlf-tip-error").empty();
						}
					});

					$(this).find("input").placeholder();

					$("#signup-btn").button({loadingText:"正在注册..."})
					.on("click",function(){
						var $this=$(this);
						if($this.hasClass("disabled")){ return;}
						$this.button("loading");
						$this.closest("form").validate({
							success:function(vals){
								$.ajax({
									url:"/user/register/",
									data:{
										username:vals.email,
										password:vals.password,
										nickname:vals.nick,
										verify: vals.verify
									},
									method:"post",
									dataType:"json",
									success:function(data){
										if(data.status===0){
											fireLogined(data.data.userInfo,true);
											return ;
										}
										$("#signup-globle-error").addClass("rlf-tip-error").html(data.msg);

										// 重置验证码
										refreshVerifyCode();
										signUpForm.find('.ipt-verify').val('');
									},
									error:function(){
										$("#signup-globle-error").addClass("rlf-tip-error").html("服务错误，稍后重试");
									},
									complete:function(){
										$("#signup-btn").button("reset");
									}
								});

							},
							error:function(){
								if(signUpForm.find('.ipt-verify').val()){
									refreshVerifyCode();

									var interval=500,valTimer;

									if(valTimer) clearTimeout(valTimer);
									valTimer=setTimeout(function(){
										signUpForm.find('.ipt-verify').val('');
									},interval)
								}
								

								setTimeout('$("#signup-btn").button("reset")',1);
							}
						})
					});

					var signUpForm = $(this),
						pwdPass = $(".js-pass-pwd"),
						protxt = $(".js-txt-pwd"),
						proclaimBtn = $(".js-proclaim"),
						verifyCodeUrl = '/user/getregcode?_=',
						refreshVerifyCode = function(){
							signUpForm.find('.verify-img').attr('src', verifyCodeUrl + new Date().getTime());
						},
						proclaimCode = function(){
							if(proclaimBtn.hasClass("is-pwd")){
								//明文
								var inpValue=pwdPass.val();
								protxt.val(inpValue);

								proclaimBtn.parent().removeClass("js-proclaim-on").addClass("js-proclaim-off");
								proclaimBtn.removeClass("is-pwd");
							
							}else{
								//密码
								var inpValue=protxt.val();
								pwdPass.val(inpValue);

								proclaimBtn.parent().removeClass("js-proclaim-off").addClass("js-proclaim-on");
								proclaimBtn.addClass("is-pwd");
								
							}
						};

					signUpForm.find('.verify-img-wrap').append(
						$('<img class="verify-img"/>')
					);

					refreshVerifyCode();

					signUpForm.on('click', '.js-verify-refresh', function(e){
						refreshVerifyCode();
					});
					signUpForm.on('click', '.js-proclaim', function(e){
						e.stopPropagation();
						proclaimCode();
					});

				}).on("hidden",function(){
					$(this).remove();
				});
				m.modal("show");
			}
		}()
	}


	var fireLogined=window.__fireLogined=window.__fireLogined||function(data,signup){
		var e=$.extend($.Event("logined.imooc"),{_data:data});
		$("#signin [data-dismiss],#signup [data-dismiss]").trigger("click");
		$(document).trigger(e);
		winsns.clear();

		if(e.isDefaultPrevented()){
			return ;
		}
		//default actions; can be prevent by call e.preventDefault method
		if(signup) {
			window.location.replace("/user/setuserinfo");
			return ;
		}
		var pathname=window.location.pathname,forward;

		forward="error,forget,logout,newforgot,userfrozen,sendresult,resetpasspage,resetpassword,checkaopenguser".split(",").join("|");
		forward=new RegExp("\\/(?:"+forward+")(?:\\/|$|\\?|#)");
		if(forward.test(pathname)){ //remove pathname=="/" index login
			window.location.replace("/course/list"); ///index
			return ;
		}
		window.location.reload();

	}

	//fireLogined();

	module.exports = {
        init : function(){
        	//$("body").append(getTpl("signin"));
        	m.signin();
        },
		signup:function(){
			m.signup();
		}
    };

});


