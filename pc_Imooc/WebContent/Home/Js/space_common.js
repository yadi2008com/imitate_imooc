define(function(require, exports, module) {
	require('/static/lib/layer/1.6.0/layer.min.js');
	mpLoad();
	var faceLib = {}

	var layerbox = "";

	function formatChatMsg(val) {
		if (typeof(val) != "undefined") {
			var sArr = val.match(/\[.*?\]/g);

			if (sArr == null) {
				return val
			}

			for (var i = 0; i < sArr.length; i++) {
				if (faceLib[sArr[i]]) {
					var reStr = "<img src=\"" + faceLib[sArr[i]] + "\" height=\"24\" width=\"24\" />";
					val = val.replace(sArr[i], reStr);
				}
			}
			return val
		}
	}

	//表情
	function openFace(event, textareaId, iconURL) {

		var faceLib = [{
			title: '[微笑]',
			pic: iconURL + '1.png'
		}, {
			title: '[不]',
			pic: iconURL + '2.png'
		}, {
			title: '[亲亲]',
			pic: iconURL + '3.png'
		}, {
			title: '[无聊]',
			pic: iconURL + '4.png'
		}, {
			title: '[鼓掌]',
			pic: iconURL + '5.png'
		}, {
			title: '[伤心]',
			pic: iconURL + '6.png'
		}, {
			title: '[害羞]',
			pic: iconURL + '7.png'
		}, {
			title: '[闭嘴]',
			pic: iconURL + '8.png'
		}, {
			title: '[耍酷]',
			pic: iconURL + '9.png'
		}, {
			title: '[无语]',
			pic: iconURL + '10.png'
		}, {
			title: '[发怒]',
			pic: iconURL + '11.png'
		}, {
			title: '[惊讶]',
			pic: iconURL + '12.png'
		}, {
			title: '[委屈]',
			pic: iconURL + '13.png'
		}, {
			title: '[酷]',
			pic: iconURL + '14.png'
		}, {
			title: '[汗]',
			pic: iconURL + '15.png'
		}, {
			title: '[闪]',
			pic: iconURL + '16.png'
		}, {
			title: '[放屁]',
			pic: iconURL + '17.png'
		}, {
			title: '[洗澡]',
			pic: iconURL + '18.png'
		}, {
			title: '[偶耶]',
			pic: iconURL + '19.png'
		}, {
			title: '[困]',
			pic: iconURL + '20.png'
		}, {
			title: '[咒骂]',
			pic: iconURL + '21.png'
		}, {
			title: '[疑问]',
			pic: iconURL + '22.png'
		}, {
			title: '[晕]',
			pic: iconURL + '23.png'
		}, {
			title: '[衰]',
			pic: iconURL + '24.png'
		}, {
			title: '[装鬼]',
			pic: iconURL + '25.png'
		}, {
			title: '[受伤]',
			pic: iconURL + '26.png'
		}, {
			title: '[再见]',
			pic: iconURL + '27.png'
		}, {
			title: '[抠鼻]',
			pic: iconURL + '28.png'
		}, {
			title: '[心寒]',
			pic: iconURL + '29.png'
		}, {
			title: '[怒]',
			pic: iconURL + '30.png'
		}, {
			title: '[凄凉]',
			pic: iconURL + '31.png'
		}, {
			title: '[悄悄]',
			pic: iconURL + '32.png'
		}, {
			title: '[奋斗]',
			pic: iconURL + '33.png'
		}, {
			title: '[哭]',
			pic: iconURL + '34.png'
		}, {
			title: '[赞]',
			pic: iconURL + '35.png'
		}, {
			title: '[开心]',
			pic: iconURL + '36.png'
		}];

		event.stopPropagation();
		var position = {
			left: event.pageX - 10,
			top: event.pageY - 300
		}
		if ($('#face_panel').size() > 0) {
			$('#face_panel').fadeIn(function() {
				hidePanel();
			});

		} else {

			var $textInput = $(textareaId)[0];
			$('#layer_sendmsg').css("position", "relative")
			$('<div id="face_panel" style="display:block;left:-70px;bottom:50px; z-index:99999999; margin:0"><div id="choose_face"></div></div>').appendTo($('#layer_sendmsg'));

			for (var i = 0; i < faceLib.length; i++) {
				var _faceTitle = faceLib[i].title.substr(1).replace(']', '');
				$('<a title="' + faceLib[i].title + '"  href="javascript:;"><img class="ph_face_s" src=' + faceLib[i].pic + '><p>' + _faceTitle + '</p></a>').appendTo($('#choose_face')).on('click', function(event) {
					event.stopPropagation();
					insertFaceIcion($(this).attr('title'));
					$('#face_panel').hide()
				});

			}
			hidePanel();

			//插入表情转换函数
			function insertFaceIcion(text) {

				if (document.selection) {
					$textInput.focus();
					var cr = document.selection.createRange();
					cr.text = text;
					cr.collapse();
					cr.select();
				} else if ($textInput.selectionStart || $textInput.selectionStart == '0') {
					var start = $textInput.selectionStart,
						end = $textInput.selectionEnd;
					$textInput.value = $textInput.value.substring(0, start) + text + $textInput.value.substring(end, $textInput.value.length);
					$textInput.selectionStart = $textInput.selectionEnd = start + text.length;
				} else {
					$textInput.value += text;
				}
			}

		}

		function hidePanel() {
			$(document).on("click", function(event) { //对document绑定一个影藏Div方法
				if (event.target.id !== 'face_panel') {
					$('#face_panel').hide();
				} else {
					var $textInput = $(textareaId)[0];
					$textInput.focus()
				}

			});

		}

	}

	var $textInput = $('#textInput')[0];

	function insertText(text) {
		if (document.selection) {
			$textInput.focus();
			var cr = document.selection.createRange();
			cr.text = text;
			cr.collapse();
			cr.select();
		} else if ($textInput.selectionStart || $textInput.selectionStart == '0') {
			var start = $textInput.selectionStart,
				end = $textInput.selectionEnd;
			$textInput.value = $textInput.value.substring(0, start) + text + $textInput.value.substring(end, $textInput.value.length);
			$textInput.selectionStart = $textInput.selectionEnd = start + text.length;
		} else {
			$textInput.value += text;
		}
	};

	function sendMsg(url, uid) {
		if ($('#textInput').val() == '') {
			alert('请输入内容');
			return
		} else {
			var inputText = $('#textInput').val();
			$.ajax({
				type: "post",
				url: url,
				dataType: "json",
				//data:{uid:uid,message:inputText.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;')},
				data: {
					uid: uid,
					message: inputText
				},
				success: function(data) {
					layer.close(layerbox);
					layerbox = '';
					layer.msg(data.data.msg, 1, 1);
				}
			})

		}
	}

	$('#face_panel').find('a').each(function(index, element) {
		$(this).click(function() {
			$('#face_panel').hide();
			insertText('[' + $(this).attr('title') + ']');
		});
	});



	//发私信事件
	$('.chatSend').click(function(e) {
		e.preventDefault();
		sendMsg('/space/ajaxsendmessage', $('#js-send-msg').data('uid'));
		return false;
	});

	//发申请事件
	$('.sendInvite').click(function(e) {
		//console.log(1);
		e.preventDefault();
		sendMsg('/space/ajaxaddfriend', $('#js-add-frd').data('uid'));
		//time:3;
		return false;
	});


	// 加好友弹出层
	$('.container').delegate('#js-add-frd, #js-send-msg', 'click', function(e) {
		//console.log(e.target.id)
		if (OP_CONFIG.userInfo) {
			$('#textInput').val('');
			layerbox = $.layer({
				type: 1,
				area: [e.target.id == "js-send-msg" ? '580px' : '422px', 'auto'],
				title: false,
				move: ['.layer_notice', true],
				border: false,
				page: {
					dom: '#layer_sendmsg'
				}
			});

			$(".xubox_main").addClass("pravtie_close"); //点击发送私信时添加类名控制关闭按钮样式
		} else {
			//openLib.login();
			function popLogin() {
				require.async('login_sns', function(login) {
					login.init();
				});
			};
			popLogin();
		}
	});

	$('.tipclose').click(function() {
		$('.addfriendtip').css("display", "none");
	});

	//申请好友成功弹出框

	$('.suclose').click(function() {
		$('.successtip').css("display", "none");
	});

	$(document).delegate('#sendEmojiIcon', 'click', function(e) {
		var textarea = $('#textInput');
		textarea.focus()
		if ($("#face_panel").length > 0) {
			$("#face_panel").toggle()
		} else {
			openFace(e, textarea, '/static/img/smiley/');
		}
		return false;
	})

	//加载MP值
	function mpLoad() {
			var numBox = $("#mp-box .mp-num"),
				param = {};
			param.uid = numBox.attr("data-uid");
			if (!param.uid) return;
			$.ajax({
				url: "/user/ajaxgetmp",
				type: "post",
				dataType: "json",
				data: param,
				success: function(data) {
					if (data.result == 1) {
						data.data[param.uid] ? numBox.html(data.data[param.uid].mp) : numBox.html(0);
					} else {
						numBox.html(0);
						return false;
					}
				}
			})
		}
		//zss
	var _getTimeBox = $('.playtime');
	//_getTImeBoxVal = _getTimeBox.html();
	$('.notelist .videoBig').click(function() {
		$(this).hide();
		$(this).parents('.notelist').find('.videoSmall').show();
		$(this).parents('.notelist').find('.playtime').removeClass('playtime_Ac').addClass('playtime_AS');
	})
	$('.notelist .videoSmall').click(function() {
		$(this).hide();
		$(this).parents('.notelist').find('.videoBig').show();
		$(this).parents('.notelist').find('.playtime').removeClass('playtime_AS').addClass('playtime_Ac');
	})

	$("#publishsign,#signed").on('click', function() {
		var newH = $("#signed").innerHeight(),
			editor = $('#js-sign-editor'),
			signed = $("#signed"),
			placeholder = '这位童鞋很懒，什么也没有留下～～！';

		signed.addClass("signed_visible");
		editor.val($.trim(signed.text())).innerHeight(newH).addClass("sign_block");

		//光标定到textarea框内，选中其中内容
		editor.focus().select();
		//textarea失去焦点，就取新值，ajax请求

		editor.unbind('blur').blur(function() {
			var newtxt = editor.val(),
				len = newtxt.length;

			if (len > 128) {
				$("#rlf-tip-wrap").html("个性签名不能超过128个字符~");
				return false;
			} else {

				$("#rlf-tip-wrap").empty();
				//ajax传递签名到后台,如果参数是空的，那么就为原来的参数
				if (len <= 0) {
					newtxt = placeholder;
				} else {
					newtxt = newtxt;
				}

				$.ajax({
					url: "/user/singleset",
					type: "post",
					dataType: "json",
					data: {
						about: newtxt
					},
					success: function(data) {
						if (data.status != 0) {
							alert(data.msg);
							signed.removeClass("signed_visible");
							editor.focus();
							return false;
						}else{
							signed.find('strong').html(newtxt.replace(/</g, "&lt;").replace(/>/g, "&gt;")).removeClass("signed_visible");
							editor.removeClass("sign_block");
						}
					}
				})
			}
		})
	});
	//左边导航固定
	var setFix = function() {
			var distance = $(document).scrollTop();
			$(".myspace-list").show();
			if (distance > 70) {
				$(".myspace-list").addClass("fixed");
			} else {
				$(".myspace-list").removeClass("fixed");
			}
		}
	$('.myspace-tab-list').on('mouseover', 'li', function(e){
		var i = $(this).index(),
			ow = $(this).width(),
			dot = $(this).parent().siblings('.dot-curr');

		dot.stop().animate({left: i * ow}, 200);
	}).on('mouseleave', 'li', function(e){
		var cur = $('.myspace-tab-list').find('.curr'),
			oli = cur.parent(),
			i = oli.index(),
			ow = oli.width(),
			dot = oli.parent().siblings('.dot-curr')

		dot.stop().animate({left: i * ow}, 200);
	});
	//点击全部
    $("#js-columall").on('click',function(e){
    	e.stopPropagation();
        var jsicon=$(this).find(".icon"),
            jscolumbd=$(this).siblings("#js-columbd");
        jscolumbd.css("marginLeft",-(jscolumbd.width())/2);

        if(jsicon.hasClass("icon-down")){
            jscolumbd.show();
            jsicon.removeClass("icon-down").addClass("icon-top");
        }else{
            jscolumbd.hide();
            jsicon.removeClass("icon-top").addClass("icon-down");
        }
    })
    //点击全部弹层以外消失弹层
    $(document).mouseup(function(e) {
    	var _con=$("#js-columbd");
    	if(!_con.is(e.target)&&_con.has(e.target).length==0){
    		$("#js-columbd").hide();
    		$("#js-columall").find(".icon").removeClass("icon-top").addClass("icon-down")
    	}
    });
    var gotoUrl=function(){
        var url="/space/course/sid/";
        var sid=$(".js-filter-progress").attr("data-skillid");
        url+=sid+"/t/"
        if($(".js-filter-progress").length>0){
            url+=$(".js-filter-progress").attr("data-checked")
        }else{
            url+="2"
        }

        if($("#courseSelect").val()){
            url+="/sid/"+$("#courseSelect").val()
        }
        window.location.href=url
    };

	$(".js-filter-progress").on('click', function(e){
        if($(this).attr("data-checked")=="0"){
            $(this).attr("data-checked","1").addClass('checked')
        }else{
            $(this).attr("data-checked","0").removeClass('checked')
        }
        gotoUrl();
    });
    $(".space-tabs-menu select").change(gotoUrl);
});