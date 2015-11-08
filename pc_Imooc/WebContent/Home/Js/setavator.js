define(function(require, exports, module){
	require('common');
	var mbox=require("/static/page/user/messagebox.js");

	
	var mutex=(function(){ //互斥上传类型
		var m={};
		return {
			set:function(t,xhr){
				m.xhr&&m.xhr.abort();
				m.type=t;
				xhr&&(m.xhr=xhr);
			},
			is:function(t){
				return m.type===t;
			},
			get:function(){
				return m.type;
			}
		}
	})();

	var setAvators=(function(){
		var $ha,$a;
		$ha=$("#header-avator img");
		$a=$(".avator-img img");
		return function(src){
			$a.attr("src",src);
			$ha.attr("src",src.replace(/(?:-\d*-\d*)?(\.\w+)$/,"-40-40$1"));
		}
	})();	

	$(document).on("logined.sns",function(e){
		$("#avator-btns").find("[data-sns=\""+mutex.get()+"\"]").trigger("click").siblings(".avator-sns-relogintip").remove();
		e.preventDefault();
	});

	var t=0;
	$(document).on("click","[data-sns]",function(e){
		var $this=$(this),
			type=$this.attr("data-sns");
		mutex.set(type);

		$.ajax({
			url:"/user/getimage?type="+type+"_sid",
			dataType:"json",
			success:function(data){
				if(!mutex.is(type)) return ;
				if(data.status==1){
					setAvators(data.img_url);
				}
				else if(data.status==3&&!$this.siblings(".avator-sns-relogintip").length){
					$("<span class='avator-sns-relogintip' data-opensns='"+type+"'>帐号过期，点我重登</span>").appendTo($this.parent()).animate({marginLeft:"-20px"});
				}
				else{
					mbox.error(data.msg);
				}
			},
			error:function(){
				mbox.error("后端请求失败,请稍后重试！")
			}
		})
	});

	function winOpen(url){
		var left = (screen.width - 600) / 2,
			top = (screen.height - 400) / 2,
			url="/user/login"+url;
		window.open(url, '_blank', 'toolbar=no, directories=no, status=no, menubar=no, width=600, height=500, top='+top+', left='+left).focus();
	}
    
    $(document).on("click","[data-opensns]",function(){
    	winOpen($(this).attr("data-opensns"));
    });



	!function(){
		var key,
			$img;
		$("#upload").on("change",function(e){
			if(!this.value) return ;
			mutex.set("local");
			this.form.submit();
		});
		$("#uploadtarget").on("load",function(){
			if(!mutex.is("local")) return ;
			var txt=$('#uploadtarget').contents().find('body').text();
			txt=$.parseJSON(txt);
			if(!txt.key){
				mbox.error("上传的图片无效，请重新上传");
				return ;
			}
			setAvators(txt.imgpath);
		});
	}();


	
	//avator change
	$(".js-avator-try").click(function(){
		mutex.set("try");
		$.getJSON("/user/randimage/","type=1",function(data){
			if(!mutex.is("try")) return ;
			if(data){
				//$img=$('.avator-inner img').attr("src",data.imgpath);
				setAvators(data.imgpath);
			}
		});
	});

	/*$("#avator-btn-save").click(function(){
		var $this=$(this);
		if($this.text()=="正在保存..."||!uploadType||!imgURL) return ;
		$this.text("正在保存...");
		$.ajax({
			type: 'post',
		 	url: '/user/ajaxsetinfo',
		 	data: {
		 		type:2,
		 		img_url:imgURL,
		 		diff:uploadType
		 	},
		 	dataType:"json",
		 	success:function(data){
		 		if(data.result==1){
		 			mbox.info("头像保存成功！");
		 			window.location.reload();
		 		}
		 		else{
		 			mbox.error(data.msg||data.data);
		 		}
		 		$this.text("保存");
		 	},
		 	error:function(){
		 		mbox.error("后端请求失败,请稍后重试！")
		 		$this.text("保存");
		 	}
		});

	});*/
});
