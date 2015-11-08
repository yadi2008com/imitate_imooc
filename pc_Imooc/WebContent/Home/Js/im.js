/*=============================================================================
#     FileName: chat.js
#         Desc: 消息与后端通讯的chat对象，已修改成jquery对象
#       Author: jiangsf
#   LastUpdate: 2013-10-18 11:48:39
=============================================================================*/
define(function(require, exports, module){
	var $ = require('jquery');
	require('socket.io');
	$.chat = {};
	$.chat = {
		iosocket:null,
		unreadEvent:null,
		uid:0,
		port : 80,
		ready:[],
		analyzeData:null,
		events : {},	//绑定的事件
		ie: navigator.userAgent.match(/(?:\b(MS)?IE\s+|\bTrident\/7\.0;.*\s+rv:)(\d+)/), // IE Version

		//初始化socket
		init:function() {
			if (this.ie && this.ie[2] < 10) {
				this.iosocket = io('http://im.mukewang.com', {
					enablesXDR: true,
					upgrade: false,
					transports: ['polling'],
					path: '/message',
					reconnectionAttempts: 5
				});
			} else {
				this.iosocket = io('http://im.mukewang.com', {
					transports: ['websocket'],
					path: '/message',
					reconnectionAttempts: 5
				});
			}
			//analyzeData
			var cookie = getCookie(),
				cninfo = (cookie.cninfo || '').split('-'),
				uuid = cookie['imooc_uuid']
			this.analyzeData = {
				marking: cninfo[1] || '',
				channel: cninfo[0] || '',
				uuid: uuid || '',
				//c: document.cookie,
				url : window.location.href,
				uid: OP_CONFIG.userInfo && OP_CONFIG.userInfo.uid || 0,
				isweb: 1 // 1 pc web, 0 others
			}
			for (var event in this.events) {
				this.iosocket.on(event, this.events[event]);
			}
			this.iosocket.on("connect",function(){
				var chat = $.chat,
					ready = chat.ready||[],
					socket=chat.iosocket,
					i,len;
				for(i = 0,len = ready.length;i < len;i++){
					socket.emit.apply(socket,ready[i]);
				}
			});

			this.checkUnreads();

		},

		//用户登录
		login:function(uinfo) {
			uinfo.expand = this.analyzeData;
			this.iosocket.emit('login', uinfo);
		},
		emit:function(type,data){
			if(this.ready){

				this.ready.push(arguments);
			}
			else{
				this.iosocket.emit(type,data);
			}
		},
		//绑定未读消息事件
		bandUnreads:function(uid, cb) {
			if (uid && cb) {
				this.uid = uid;
			}
			this.events['unreads'] = cb;
			if (this.iosocket) {
				this.iosocket.on('unreads', cb);
			}
			//this.checkUnreads();
		},
		//检查未读消息总数
		checkUnreads:function() {
			//发送获取未读消息总数指令
			this.analyzeData.uid = this.uid;
			this.emit("unreads",this.analyzeData);
			this.emit("getremind");
			//this.iosocket.emit('unreads', this.uid);
			//this.iosocket.emit('getremind',this.uid);
		},

		//绑定服务端响应事件
		bindEvent:function(type, cb) {
			if (this.iosocket) {

				this.iosocket.on(type, cb);
			} else {
				this.events[type] = cb;
			}
		},

		//发送指令到服务端
		send:function(type, msg) {
			this.iosocket.emit(type, msg);
		}
	}

	function getCookie(name) {
		var obj = {};
		var pairs = document.cookie.split(/ *; */);
		var pair;
		//if ('' == pairs[0]) return obj;
		for (var i = 0; i < pairs.length; ++i) {
		  pair = pairs[i].split('=');
		  obj[decodeURIComponent(pair[0])] = decodeURIComponent(pair[1]);
		}
		return name ? obj[name] : obj;
	}
	//绑定未读消息事件
	if (OP_CONFIG.userInfo && OP_CONFIG.userInfo.uid) {
		$.chat.bandUnreads(OP_CONFIG.userInfo.uid, function(total){
			//console.log(JSON.stringify(total));
			if (total > 0) {
				$('.msg_icon').show().html(total);
				$('#msg_new a').html('<span class="unread_num">'+total+'</span>');
			} else {
				$('.msg_icon').hide();
				$('#msg_new a').empty();

			}
		});
		$.chat.bindEvent("remind",function(msg){
			switch(msg.type){
                case "global":
                    //console.log(msg.data);
					if (msg.data.course_unreads || msg.data.bbs_unreads) {
						$('.myspace_remind').show();
					} else {
						$('.myspace_remind').hide();
					}
					tips('.my-mooc', msg.data.course_unreads);
					tips('.my-sns', msg.data.bbs_unreads);

				break;

				case "space":
					//bindIcon(msg.data.type,msg.data.subtype)
				break;
			}

			function tips(selector, data) {
				var $span = $(selector + ' span');
				if (!$span.length) {
					//todo: html完善结构，把样式移到css里？
					$span = $('<span style="display:inline-block;font-size:12px;color:#fff;background:#f01400;border-radius:50%;width:16px;height:16px;vertical-align:middle;line-height:16px;margin:-5px 0 0 5px;text-align:center;font-weight:normal;">').appendTo($(selector));
				}
				if (data) {
					$span.text(data || 0).show();
				}
				else {
					$span.text(0).hide();
				}
			}
		});
	}

});
