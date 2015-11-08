define(function(require, exports, module){
	var tpl={
		info:'<div class="mmsg-box mmsg-box-info"><div class="mmsg-content"><i class="mmsg-icon"></i><p></p></div><div class="mmsg-background"></div></div>',
		error:'<div class="mmsg-box mmsg-box-error"><div class="mmsg-content"><i class="mmsg-icon"></i><p></p></div><div class="mmsg-background"></div></div>'
	}


	function show(s,m,t){
		var tl=tpl[s],$tpl;
		if(!tl) return ;
		$tpl=$(tl);
		$tpl.find(".mmsg-content p").text(m)
		$tpl.appendTo("body");
		$tpl.css({marginTop:$tpl.height()/-2,marginLeft:Math.min(600,$tpl.width())/-2}).fadeIn().delay(t||2000).fadeOut(function(){
			//$tpl.remove();
		});
	}
	return {
		info:function(m,t){
			show("info",m,t);
		},
		error:function(m,t){
			show("error",m,t);
		}
	}
});
