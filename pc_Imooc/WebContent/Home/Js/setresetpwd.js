define(function(require, exports, module){
	require('common');
	require("/static/component/base/util/validate.js");
	require("/static/component/base/placeholder/placeholder.js");
	var mbox=require("/static/page/user/messagebox.js");
	var $form=$("#resetpwdform");

	$form.validateSetup({
		fields:{
			oldpwd:{
				rules:[{
					rule:function(c,v){
						if(!v.length){
							return "当前密码不能为空！";
						}
					}
				}]
			},
			newpass:{
				blur:function(e){
					$(this).validate();
					$(this.form).find("[name='confirm']").validate();
				}
			},
			confirm:{
				rules:[{
					rule:function(cb,v){
						if($.trim($(this.form).find("[name='newpass']").val())!==v){
							return "两次密码输入不一致！";
						}
					},
					force:1
				}],
				keyup:function(e){
					if(e.keyCode=="13")
						$("#reset-submit").trigger("click");
					else{
						$(this).validate(e);
					}
				}						
			}
		},
		onerror:function(e){
			var $t,$d;
			if(e._relateField&&e.tip){
				$t=$(e._relateField);
				$t.addClass("rlf-field-error").next(".rlf-tip-wrap").html(e.tip).addClass("rlf-tip-error");
				($d=$t.data("placeholder-textinput"))&&$d.addClass("rlf-field-error");
			}
		},
		onvalid:function(e){
			var $t,$d;
			if(e._relateField){
				$t=$(e._relateField);
				$t.removeClass("rlf-field-error").next(".rlf-tip-wrap").removeClass("rlf-tip-error").empty();
				($d=$t.data("placeholder-textinput"))&&$d.removeClass("rlf-field-error");
			}
		}
	});


	$form.find("input[placeholder]").placeholder();
	//gogo
	$("#resetpwd-btn-save").on("click",function(){
		var $this=$(this);
		if($this.text()=="正在保存..."){
			return ;
		}
		$this.text("正在保存...");
		$this.closest("form").validate({
			success:function(vals){
				$.ajax({ 
					 type: 'post',
					 url: '/user/ajaxsetinfo',
					 data: {
					 	type:4,
					 	oldpw:vals.oldpwd,
					 	newpw:vals.newpass
					 },
					 dataType: 'json',
					 success: function(data){
						if(data.result == 1 ){
							mbox.info("修改密码成功！")
							$form[0].reset();
							$form.find(".rlf-tip-wrap").empty().end().find(".rlf-field-error").removeClass("rlf-field-error");
							$form.find("input[placeholder]").placeholder();
						}
						else{
							mbox.error(data.msg)
						}
					 },
					 error: function(e){
						alert('后端请求失败,请检查后端接口！');	 
					 },
					 complete:function(){
					 	$this.text("保存");
					 }
				})	
			},
			error:function(){
				$this.text("保存");
			}
		})
	});

});