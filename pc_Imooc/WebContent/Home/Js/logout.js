define(function(require, exports, module){
	require('common');
	require("/static/component/base/placeholder/placeholder.js");
	var el=document.createElement("input"),
		$input,
		$textarea;
	function toggleMsg(m){
		var $p=$(this).siblings("p");
		if(m){
			$p.addClass("rlf-tip-error").text(m);
			$(this).addClass("error-field");
		}
		else{
			$p.removeClass("rlf-tip-error").empty();
			$(this).removeClass("error-field")
		}
		$("#feedback-error").empty().hide();
	}


	$textarea=$("textarea[name='info']");
	$textarea.on({
		keyup:function(){
			if(!!$(this).val()){
				toggleMsg.call(this);
			}

		},
		blur:function(){
			if(!$(this).val()){
				toggleMsg.call(this,"内容不能为空！");
			}
		}
	});

	$input=$("input[name='contact']");
	$input.on({
		keyup:function(){
			if(!!$(this).val()){
				toggleMsg.call(this);
			}
		},
		blur:function(){
			if(!$(this).val()){
				toggleMsg.call(this,"联系方式不能为空！");
			}
		}
	});

	$(".result-wrap a").click(function(e){
		e.preventDefault();
		$(this).closest(".result-wrap").hide().next(".field-wrap").show();
		$("#feedback-error").empty().hide();
	});

	$input.placeholder();
	$textarea.placeholder();
	$("#submit").click(function(){
		var content,contact,f=1,d;

		content=$textarea.val();
		if(!content){
			$textarea.trigger("blur");
			f=0;
		}

		contact=$input.val();
		if(!contact){
			$input.trigger("blur");
			f=0;
		}
		if(!f){
			return ;
		}

		d={
			uid:OP_CONFIG.userInfo&&OP_CONFIG.userInfo.uid||'0',
			usersug:content,
			userconnection:contact
		}

		$.ajax({ 
			type: 'post',
			url: '/user/logoutsuggest',
			data:d,
			dataType: 'json',
			success: function(data){
				if(data.result == 0){
					//window.location.reload();
					$(".result-wrap").show().next(".field-wrap").hide();
					$input.val("").trigger("blur.placeholder");
					$textarea.val("").trigger("blur.placeholder");
				}
				else{
					$("#feedback-error").text(data.msg||data.data).show();
				}
				
			},
			error: function(e){
				$("#feedback-error").text("后端请求失败,请检查后端接口！").show();
			}
		});

	});
});
