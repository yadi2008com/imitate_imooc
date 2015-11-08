define(function(require, exports, module){
    require('common');
    require('/static/lib/layer/1.6.0/layer.min.js');
    require('./space_common.js');


	//编辑框内光标
	$.fn.setCursorPosition = function(position) {
		if (this.lengh == 0) return this;
		return $(this).setSelection(position, position);
	}

	//把光标移到到最后
	$.fn.setSelection = function(selectionStart, selectionEnd) {
		if (this.lengh == 0) return this;
		input = this[0];
		if (input.createTextRange) {
			var range = input.createTextRange();
			range.collapse(true);
			range.moveEnd('character', selectionEnd);
			range.moveStart('character', selectionStart);
			range.select();
		} else if (input.setSelectionRange) {
			input.focus();
			input.setSelectionRange(selectionStart, selectionEnd);
		}
		return this;
	};

	$.fn.focusEnd = function() {
		this.setCursorPosition(this.val().length);
	};

	var countLength=function(){
		var len = $.trim($('#js-note-textarea').val()).length;
			if(len > 1000) {
				$(this).addClass('space-fake-error');
				$('#js-note-limit').addClass('limit-overflow');
			}
			else{
				$(this).removeClass('space-fake-error');
				$('#js-note-limit').removeClass('limit-overflow');
			}
			$('#js-note-limit').text(len);
	}
	//绑定事件 编辑 保存 删除
	$(document).on('click','.editnote',
	   function(e) {
		   e.preventDefault();
			$('#js-note-input-fake').remove();
			$('.sava_btn').hide();
			$('.mynote').show();

			$('.editnote').show();
			$('.delnote').show();
			$('.editordel').hide();
		   //var oldhtml = $(this).closest('.discusslist').find('.mynote').html().replace(/<br>/g,"\n");
		  //var oldhtml = $(this).parents('.editordel').find("textarea").val;
		  	var oldhtml = $(this).parents('.editordel').find('textarea').html();
		  	//console.log(oldhtml);
		   $(this).hide().closest('.discusslist').find('.mynote').hide().after('<div class="space-note-input" id="js-note-input-fake">\
      <textarea placeholder="请输入笔记内容..." name="" class="js-placeholder" id="js-note-textarea">' + oldhtml + '</textarea>\
                     <p class="space-note-text-counter"><span id="js-note-limit">0</span>/1000</p>\
                    </div>');
		   countLength();

		   //	<textarea class="edit-area"></textarea>
		   $(this).closest('.discusslist').find('textarea').focus().focusEnd();
		   $(this).closest('.discusslist').find('.sava_btn').show();


	   });


	//笔记框
	$(document).on({
		focusin: function() {
			$(this).addClass('space-fake-focus')
		},
		focusout: function() {
			$(this).removeClass('space-fake-focus');
		},
		keyup: function() {
			countLength();
		}
	},'#js-note-input-fake');

	$(document).on('click','.sava_btn',
	  function(e) {
		  e.preventDefault();

		  var $this=$(this)
		  if($this.hasClass("submit-loading")) return ;

		  var noteId = $this.closest('.discuss').attr('noteId');//获取笔记id
		  var courseId = $this.closest('.discuss').attr('courseId'); //获取课程id
		  var newhtml = $.trim($this.closest('.discusslist').find('#js-note-textarea').val());

			var coverlay='<div class="contain-coverLayer"></div>';

	    	if(!newhtml.length){
		 		layer.msg('请输入内容', 2, -1);
		  		return;
		  	}
		  	if(newhtml.length>0 && newhtml.length < 3){
				layer.msg('输入不能小于3个字符', 2, -1);
				return;
			}
			if(newhtml.length > 1000){
				layer.msg('输入不能超过1000个字', 2, -1);
				return;
			}

var popsucc='<div id="js-pop-del" class="pop-deleting"><div class="deleting-bd"></div><div class="deleting-btm">笔记保存成功</div></div>';


		  $this.addClass("submit-loading");
		  $this.html("保存中...")
		  $.ajax({
			  url: '/space/editnote',

			  type: "post",
			  data: {
				  id: noteId,
				  course_id: courseId, //传递课程id
				  content: newhtml
			  },
			  dataType: "json",
			  success: function(json) {
			  	$this.removeClass("submit-loading");
				  if (json == 1) {
		   				$("body").append(popsucc).append(coverlay);
						//layer.msg('保存成功',1,1);
					  	$this.hide().closest('.discusslist').find('#js-note-input-fake').remove();
					  	var str=$("<div>").text(newhtml).html().replace(/([^>\r\n]?)(\r\n|\n\r|\r|\n)/g,"$1<br>$2");
						$this.closest('.discusslist').find('.mynote p').html(str).parent(".mynote").show();
						$this.closest('.discusslist').find('.editnote').show();
						$this.closest('.discusslist').find('textarea').val(newhtml);
						setTimeout(function(){
							$("#js-pop-del").remove();
							$(".contain-coverLayer").remove();
						},1000);
		  				$this.html("保存");
				  }
				  else if(json== -5){
					  //加入过滤关键词提示
					  layer.msg('包含非法关键字，请重新填写');
				  }
				  else {
				  	var poperror=$(popsucc);
						poperror.find(".deleting-bd").addClass("pop-error").end().find(".deleting-btm").html("笔记保存失败");
						$("body").append(poperror);
						setTimeout(function(){
							$("#js-pop-del").remove();
							$(".contain-coverLayer").remove();
						},1000);

					  layer.msg('保存失败');
				  }

			  },
			  error:function() {
			  	$this.removeClass("submit-loading");
		  		$this.html("保存");
			  }
		  });

});


	$(document).on('click', '.delnote',
	 function(e) {
		e.preventDefault();
		var coverlay='<div class="contain-coverLayer"></div>';
	 	var popHtml='<div id="js-popl-wrap" class="popl-wrap">\
		 	<div class="popl-hd"></div>\
			<div class="popl-main"><p>笔记删除将无法恢复，</p><p>您确定要删除这条笔记吗？</p></div>\
			<div class="popl-btm">\
				<a href="javascript:;" class="popl-yes popl-btn">确定</a>\
				<a href="javascript:;" class="popl-no popl-btn">取消</a>\
			</div>\
	    </div>';



	    var popsucc='<div id="js-pop-del" class="pop-deleting"><div class="deleting-bd"></div><div class="deleting-btm">笔记删除成功</div></div>';
		$("body").append(popHtml).append(coverlay);
		var toRemove = $(this).closest('.discuss');
		var noteId = $(this).closest('.discuss').attr('noteId');
		var count = $(this).parents('.discuss').find('.report').attr('data');
		var ajaxing=0;
		$('.popl-yes').on('click',function(){
			$("#js-popl-wrap").remove();
			if(ajaxing){
				return;
			}
			ajaxing=1;
			var dodeling=$(popsucc);
			dodeling.find(".deleting-bd").addClass("pop-dodeling").end().find(".deleting-btm").html("正在删除中...");
			$("body").append(dodeling);
		   	$.ajax({
			    url: '/space/delnote',
			    type: "GET",
			    data: {
				  	id: noteId
			    },
			    dataType: "json",
			    success: function(json) {
					$(".pop-deleting").remove();
				   	if (json == 1) {
						toRemove.remove();
						$("body").append(popsucc);
						setTimeout(function(){
							$(".pop-deleting").remove();
							$(".contain-coverLayer").remove();
						},1000);
						//删除一条笔记左边数字减1
						var $el, num;
						$el = $('.js-count-note .got-num');
						if ($el.length) {
							num = parseInt($el.text(), 10);
							num--;
							if (num <= 0 ) {
								num = 0;
							}
							$el.text(num);
						}
						if (!$('#J_NoteList .discuss').length) {
							location.replace(location.href.replace(/\/page\/\d+|page=\d+/i, ''));
						}
					} else {
						var poperror=$(popsucc);
						poperror.find(".deleting-bd").addClass("pop-error").end().find(".deleting-btm").html("笔记删除失败");
						$("body").append(poperror);
						setTimeout(function(){
							$("#js-pop-del").remove();
							$(coverlay).remove();
						},1000);
						ajaxing=0;
					}
				}
			})
		})
		$('.popl-no').on('click',function(){
			$("#js-popl-wrap").remove();
			$(".contain-coverLayer").remove();
		})
});


	$('#J_NoteList').on('click', '.js-toggle-content', function(e) {
		var $this = $(this),
			state = $this.attr('data-state');
		if(state === 'expanded') {
			$this
			.text('查看全文')
			.attr('data-state','collapsed')
			.parent().css({
				position: 'absolute'
			})
			.closest('.js-notelist-content').css({
				maxHeight: '132px' //22*6
			});
		}
		else {
			$this
			.text('收起全文')
			.attr('data-state','expanded')
			.parent().css({
				position: 'static'
			})
			.closest('.js-notelist-content').css({
				maxHeight: 'none'
			});
		}
		e.preventDefault();
	});

	$("#J_NoteList").delegate(".js-toggle-notelist","mouseenter",function(){
        if(!$(this).hasClass("hover")){
            $(this).addClass("hover")
        }
    }).delegate(".js-toggle-notelist","mouseleave",function(){
        if($(this).hasClass("hover")){
            $(this).removeClass("hover");
        }
    });
    $('#J_NoteList').on('click', '.js-catch-pic,.js-catch-picBig', function(e) {
    	var bigPic=$(this).closest('.notelist');

    	if(!$(bigPic).hasClass("biged")){
    		$(bigPic).addClass("biged");
    	}else{
    		$(bigPic).removeClass("biged");
    	}
    })

	$(".mycode-view").on("click",function(){
		var id=$(this).attr("data-id");
		require.async('../course/common/view_code',function(ViewCode){
			ViewCode.init('/course/viewnotecode',id,{})
		});
	});

$(document).on('mouseover','.discuss',
   function(e){
	$(this).find('.sava_btn').is(":visible")||$(this).find('.editordel').show();
})

$(document).on('mouseout','.discuss',
   function(e){
	   $(this).find('.sava_btn').is(":visible")||$(this).find('.editordel').hide();
})
/*$(document).on('mouseover','.discuss',
		function(e){
			$(this).find('.editnote').show();
			$(this).find('.editordel').show();

		})

$(document).on('mouseout','.discuss',
		function(e){
			$(this).find('.editnote').hide();
			$(this).find('.editordel').hide();
	   })*/
});
