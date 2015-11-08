define(function(require, exports, module){
    require('common');
    require('../../lib/jquery/plugin/jquery.progress');
	//require('/static/lib/layer/1.6.0/layer.min.js');
    require('./space_common.js');
    $('progress').progessBar({width:"100%"});


    //管理与完成
    $(".js-program-edit").click(function(){
        var _t=$(this).data("edit") | 0;
        if(!_t){
            $(".course-item").addClass("delete");
            $(this).data("edit","1").addClass('status-edit').html("<b>完成</b>")
            $(".js-course-list").addClass('editable');
        }else{
            $(".course-item").removeClass("delete");
            $(this).data("edit","0").removeClass('status-edit').html('<i class="icon icon-del"></i><b>删除</b>');
            $(".js-course-list").removeClass('editable');
        }
    })
                            console.log(window.location.href)
    //放弃课程
    $(".js-course-list").on("click", ".del", function(){
        var $li =$(this).parent("li");
        var id = $li.attr("data-id"),
            delBtn = $(this);

        if(delBtn.hasClass('deleting')) return;

        delBtn.addClass('deleting');

        $.ajax({
            type: 'POST',
            url: '/space/ajaxgiveup/?id=' + id,
            dataType:"json",
            data: {
                course:id
            },
            success: function(ret){
                if(ret.result==1){
                    $li.animate({
                        'margin-top': -6,
                        opacity: 0
                    }, 300, function(){
                        var $ul = $li.parent();
                        $li.remove();
                        $ul.children("li").length == 0 && $ul.parents('.tl-item').remove();
                        var $courselist = $(".js-course-list");
                        if($courselist.children(".tl-item").length < 1) {
                            location.replace(location.href.replace(/\/page\/\d+|page=\d+/i, ''));
                            /*var type = $("#J_MyClass").attr("data-type");
                            type = type == 2 ? "暂无任何已学完课程" : "暂无任何已学课程";

                            $courselist.replaceWith('<div class="uncourse"><span></span><p>' + type + '</p></div>');*/
                        }
                    });
                    var $el, num;
                    $el = $('.js-count-course .got-num');
                    if ($el.length) {
                        num = parseInt($el.text(), 10);
                        num--;
                        if (num <= 0 ) {
                            num = 0;
                        }
                        $el.text(num);

                    }

                    layer.msg('删除成功', 1, 1);

                }else{
                    layer.msg('删除失败，请稍后再试', 1, 1);
                }
            },
            error:function(){
                layer.msg('网络错误，请稍后再试', 1, 1);
            }
        });
    })
    //关注
    $(".js-course-list").on("click", ".btn-add-collection", function(e) {
        e.preventDefault();

        var obj=$(this).parents(".course-one");
        var id=obj.data("id");
        var _this = $(this);
        if(_this.hasClass("btn-remove-collection")){
            $.ajax({
                type: 'POST',
                url: '/space/ajaxfollowcancel/?course_id=' + id,
                dataType:"json",
                data: {
                    course:id
                },
                success:function(ret){
                    if(ret.result==0){
                        _this.removeClass("btn-remove-collection");
                        layer.msg('取消关注', 1, 1);
                    }else{
                        layer.msg('取消失败，请稍后再试', 1, 1);
                    }
                }
            })
        }
        else{
            //$(this).addClass("btn-remove-collection");
            //alert(22);//增加关注
            $.ajax({
                type:'POST',
                url:'/space/ajaxfollow/?course_id=' + id,
                dataType:'json',
                data:{
                    course:id
                },
                success:function(ret){
                    if(ret.result==0){
                        _this.addClass("btn-remove-collection");
                        layer.msg('增加关注', 1, 1);
                    }else{
                        layer.msg('关注失败，请稍后再试', 1, 1);
                    }
                }
            })
        }

    })

    $('#J_MyClass').find('.gaveup').on('click', function(){
        gaveupTip($(this).attr('data-cid'));
    });
    $('.myClassPageHead').delegate('.teacher_icon','mouseover', function(){
        $('.teacher_iden').show();
    });
    $('.myClassPageHead').delegate('.teacher_icon','mouseout', function(){
        $('.teacher_iden').hide();
    });

   $('mycourselist').find('.showstudytime').hover(
        function(){
			$(this).find('a').show();
			},
		function(){
			$(this).find('a').hide();
			}
   )

    $(document).delegate('#sendEmojiIcon','click', function(e){
        var textarea=$('#textInput');
        textarea.focus()
        openFace(e,textarea,'/static/img/smiley/');
    })



    var PAGE = 1;
    /*classmates: "3"
    course_id: "263"
    course_is_new: 0
    course_update_time: "2014-04-04"
    description: "888888888888"
    duration: "900"
    finished: 0
    learn_rate: "0"
    name: "8888888"
    pic: "http://img.mukewang.com/533e6c7c0001b24d02830204-300-170.jpg"
    update_time: "1426754974"*/
    function replaceCourse(arr){
        var li = '<li data-id="$course_id" class="course-item">' +
            '<a class="btn-add-collection $is_followed" href="javascript:void(0);"></a>' +
            '<a class="btn-del js-btn-del"></a>' +
            '<a href="/learn/$course_id" title="$course_name">' +
            '<div class="course-list-img">' +
            '<img src="$course_pic" width="280" height="158" />' +
            '<div class="pro-bg"></div>' +
            '<span class="dot-progress">$learn_rate%</span>' +
            '</div>' +
            '<progress  max="100" value="$learn_rate" data-finishVal="$learn_rate" class="studyrate"></progress>' +
            '<h5>$course_short_name</h5>' +
            '<div class="intro">' +
            '<p>$description</p>' +
            '<span class="l">总课时:$duration</span>' +
            '<span class="r">$classmates人学习</span>' +
            '</div>' +
            '<div class="tips">' +
            '<span class="l">' + arr.time + '学习</span>' +
            '<span class="r $is_new">$update</span>' +
            '</div>' +
            '</a>' +
            '</li>';
        var lis = "";
        for(var i = 0, l = arr.length; i < l; i++){
            var $course_id = arr[i].course_id;
            var $is_followed = arr[i].is_followed == 1 ? "btn-remove-collection" : "";
            var $course_name = arr[i].name;
            var $course_short_name = $course_name.length > 16 ? $course_name.substring(0, 16) + "..." : $course_name;
            var $course_pic = arr[i].pic;
            var $learn_rate = arr[i].learn_rate;
            var $description = arr[i].description;
            $description = $description.length > 30 ? $description.substring(0, 30) + "..." : $description;
            var $duration = arr[i].duration - 0;
            var h = Math.floor($duration / 3600);
            var mi = Math.floor($duration % 3600 / 60);
            $duration = h > 0 ? h + "小时" + mi + "分钟" : mi + "分钟";
            var $classmates = arr[i].classmates;
            var $is_new = arr[i].course_is_new == 0 ? "new" : "";
            var $update = arr[i].finished == 1 ? "更新完毕" : arr[i].course_update_time + "更新";

            lis += li.replace(/\$course_id/g, $course_id)
                .replace('$is_followed', $is_followed)
                .replace('$course_name', $course_name)
                .replace('$course_short_name', $course_short_name)
                .replace('$course_pic', $course_pic)
                .replace('$learn_rate', $learn_rate)
                .replace('$description', $description)
                .replace('$duration', $duration)
                .replace('$classmates', $classmates)
                .replace('$is_new', $is_new)
                .replace('$update', $update);
        }
        return lis;
    }
    function addCourse(arr){
        var uls = "";
        for(var i = 0, l = arr.length; i < l; i++){
            uls += '<ul class="course-list-new clearfix"><li class="time">' + arr[i].time + '</li>';
            uls += replaceCourse(arr[i]);
            uls += '</ul>';
        }
        return uls;
    }


    var isAjaxing = false;
    var $del = $('.js-program-edit');
    $(window).on("scroll", function(){
        if(isAjaxing || ($del.attr("data-edit") == 1))return;
        var st = $(document).scrollTop();
        var h = $(document).height();
        if(h - st <= $(window).height()){
            isAjaxing = true;
            var type = $("#J_MyClass").attr("data-type");
            $.ajax({
                type:"post",
                url:"/space/course",
                data:{
                    t:type,
                    page: ++PAGE
                },
                dataType:"json"
            }).done(function(data){
                isAjaxing = false;
                if(data.result == 0){
                    var course = data.data;
                    var cs = [];
                    for(var i in course){
                        cs.push(course[i]);
                        cs[cs.length-1].time = i;
                    }
                    if(cs.length < 1){
                        isAjaxing = true;
                        return;
                    }
                    //按日期逆序排列
                    cs.sort(function(a, b){
                        return a.time >= b.time ? -1 : 1;
                    });

                    //上次最后一天数据没取完的情况
                    var $lastul = $("ul.course-list-new").last();
                    if(cs[0].time == $lastul.children(".time").text()){
                        $lastul.append(replaceCourse(cs[0]));
                        $lastul.after(addCourse(cs.slice(1)));
                    }else{
                        $lastul.after(addCourse(cs));
                    }
                    $('progress').progessBar({width:"100%"});
                }
            });
        }
    });
});
