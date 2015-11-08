//define('/static/page/course/view', ['jwplayer','player','common'],function(require, exports, module){
define(function(require, exports, module) {
    require('common');
    require('/static/page/course/common/course_common.js');
    require('/static/page/course/common/course_collection.js');

    function signup() {
        var _this = $(this);
        $.ajax({
            type: "POST",
            url: "/course/applycourse",
            data: "id=" + GC.course.id,
            success: function(data) {
                if (data.result == 1) {
                    $('#J_ApplyNum').text(data.data.total + '人');
                    if (_this.hasClass('not-start')) {
                        _this.replaceWith('<a href="javascript:;" class="disabled btn btn-large btn-block">未开始</a>');
                        //$('#J_Apply').text('未开始');
                    } else {
                        $('#J_Apply').text('开始学习').attr('href', data.data.listurl);
                    }
                    //alert('报名成功');
                } else {
                    alert(data.data.msg);
                }
            }
        });

    }

    function initPlayer() {
        if (GC.course.video_url) {
        	var mask = $('<div class="pop-overlay"/>').appendTo('body');

            $('#js-course-img').hide();
            $('#js-video-wrap').show()
            	.find('.pop-close')
            	.on('click', function(e){
            		$('#js-video-wrap').hide();
            		mask.remove();
            	})
            require.async('player', function(player) {
                player.init(600, 340, GC.course.video_url, "js-video");
            });
        }
    }

    $('#J_Apply').on('click', signup);
    $('#js-video-trigger').on('click', initPlayer);

    $(".back-btn").on("click", function(event) {
        if (document.referrer && document.referrer.indexOf("course/list") != -1) {

            window.location = document.referrer
        } else {
            window.location = "/course/list"
        }
        return false;
    });

    $(document).on('click', '.js-comp-tab-item', function(e){
    	var pannel = $(this).data('pannel'),
    		pannels = $('.js-comp-tab-pannel');

    	$('.js-comp-tab-item').removeClass('on');

    	$(this).addClass('on');

    	pannels.hide().filter(function(){
    		return $(this).data('pannel') == pannel;
    	}).show();

    	return false;
    });
});
