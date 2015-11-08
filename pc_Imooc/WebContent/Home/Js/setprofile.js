define(function(require, exports, module){
	require('common');
	require("placeholder.js");
	require("validate.js");
	var mbox=require("messagebox.js");

	//prov city area plugin init;
//	$('#province-select').change(function(){
//		$('#city-select').text('').append("<option value='0'>选择城市 </option>");
//		$('#area-select').text('').append("<option value='0'>选择区县 </option>");
//		$.get('/user/ajaxchangeprov', 'id='+$(this).val(),function(data){
//			if(data&&data.result==1){
//				var $c=$("#city-select"),
//					d=data.data,
//					len=d.length,
//					i=0;
//					//$c.append("<option value='0'>选择城市 </option>");
//					for(;i<len;i++){
//						$c.append("<option value="+d[i].id+" >"+d[i].name+ " </option>");
//					}
//			}
//		},'json')
//	});
//	
//	$('#city-select').change(function(){
//		$('#area-select').text('').append("<option value='0'>选择区县 </option>");
//		$.get('/user/ajaxchangecity', 'id='+$(this).val(),function(data){
//			if(data&&data.result==1){
//				var $c=$("#area-select"),
//					d=data.data,
//					len=d.length,
//					i=0;
//					//$c.append("<option value='0'>选择区县 </option>");
//					for(;i<len;i++){
//						$c.append("<option value="+d[i].id+" >"+d[i].name+ " </option>");
//					}
//			}
//
//		},'json')
//	});


	function formOnerror(e){
		var $t,$d;
		if(e._relateField&&e.tip){
			$t=$(e._relateField);
			$t.addClass("rlf-field-error").next(".rlf-tip-wrap").html(e.tip).addClass("rlf-tip-error");
			($d=$t.data("placeholder-textinput"))&&$d.addClass("rlf-field-error");
		}
	}

	function formOnvalid(e){
		var $t,$d;
		if(e._relateField){
			$t=$(e._relateField);
			$t.removeClass("rlf-field-error").next(".rlf-tip-wrap").removeClass("rlf-tip-error").empty();
			($d=$t.data("placeholder-textinput"))&&$d.removeClass("rlf-field-error");
		}
	}

	$("#job").change(function(){
		if(!!$(this).val()){
			formOnvalid.call(this,{_relateField:this});
		}
		else{
			formOnerror.call(this,{_relateField:this,tip:"请选择职位！"});
		}
	});
	$("#profile").validateSetup({
		fields:{
			"aboutme":{
				rules:[{
					rule:function(cb,v){
						if(v.length>128){
							return "个性签名不能超过128个字符！";
						}
					}
				}]
			}
		},
		onerror:formOnerror,
		onvalid:formOnvalid
	});


	$("#profile-submit").click(function(){

		var $this=$(this),$form,
			$s;
		if($this.text()=='正在保存...'){ return;}
		$this.text("正在保存...");


		$form=$this.closest("form");	
		$s=$form.find("#job");

		if(!!$s.val()){
			formOnvalid.call($s,{_relateField:$s});
		}
		else{
			formOnerror.call($s,{_relateField:$s,tip:"请选择职位！"});
		}

		$form.validate({
			success:function(vals){
				var s=$.trim($s.val());
				if(!s){
					$this.text("保存");
					return ;
				}
				//console.log(vals);
				//console.log($.extend(postData,vals));
				var postData={
					type:1  
				};
				postData.job=s;
				postData.sex=$("#profile input[name='sex']:checked").val();

				postData.nickname=vals.nickname;
				postData.about=vals.aboutme;
				postData.province=$("#province-select").val();
				postData.city=$("#city-select").val();
				postData.area=$("#area-select").val();
				$.ajax({
					url:"/user/ajaxsetinfo",
					data:postData,
					method:"post",
					dataType:"json",
					success:function(data){
						if(data.result==1){
							mbox.info("资料修改成功！");
						}
						else{
							mbox.error(data.msg||data.data);
						}
						$this.text("保存");
					},
					error:function(){
						mbox.error("服务程序错误，请稍后重试！");
						$this.text("保存");
					}
				});

			},
			error:function(){
				$this.text("保存");
			}
		});


	});


});
