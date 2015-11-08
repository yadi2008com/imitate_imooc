define(function(require, exports, module){
    require('common');
    require('/static/lib/layer/1.6.0/layer.min.js');
    require('../../lib/jquery/plugin/jquery.progress');
    require('../../lib/jquery/plugin/jquery.scrollbar');
    require('../../css/jquery.scrollbar.css');
    require('./common/raphael.js');
    
    var ajaxLoading = {},
        isAjax = 0;

    var planMethod = {
        join: function(pid, target){
            if(ajaxLoading['join']) return;

            ajaxLoading['join'] = true;

            $.post('/course/AjaxPostProgramLearn', {pid: pid}, function(data){
                if(data.result == 1){
                    target.addClass('success');
                    setTimeout(function(){
                        window.location.reload();
                    }, 300);
                }
            }, 'json').complete(function(e){
                ajaxLoading['join'] = false;

                target.removeClass('loading');
            });
        },
        cancel: function(pid, target){
            if(ajaxLoading['cancel-plan']) return;

            $.confirm('确认退出该计划吗？', function(){
                if(ajaxLoading['cancel-plan']) return;
                ajaxLoading['cancel-plan'] = true;

                $.post('/space/ajaxdelprogram', {pid: pid}, function(data){
                    if(data.result == 1){
                        $.confirm('close');

                        setTimeout(function(){
                            window.location.reload();
                        }, 300);
                    }
                }, 'json').complete(function(){
                    ajaxLoading['cancel-plan'] = false;
                });
            });
            
        }
    }
    // 知识点 提示层
    function anchorPop(){
        this.box = $('.anchor-pop');
        this.targets = $('.step-anchor');

        this.init();
    }

    anchorPop.prototype = {
        init: function(){
            var _this = this;

            var _timer = null;
            $('.plan-step').on('mouseover', '.step-anchor', function(){
                var anchor = $(this);

                if(_timer){
                    clearTimeout(_timer);
                }
                _timer = setTimeout(function(){
                    _this.position(anchor);
                }, 100)
                
            }).on('mouseleave', '.step-anchor', function(){
                if(_timer){
                    clearTimeout(_timer);
                }
                _this.hide();
            })
        },
        position: function(target){
            var anchor = target,
                offset = anchor.offset(),
                anchorW = anchor.width(),
                anchorH = anchor.height(),
                popW, popH,
                left = 0, top = 0;

            this.box.hide().find('.anchor-pop-main').html(anchor.find('.rules').html());

            popW = this.box.width();
            popH = this.box.height();

            left = offset.left - (popW / 2) + anchorW / 2;
            top = offset.top - popH - 4;

            top = top < 0 ? 0 : top;

            this.box.css({
                left: left,
                top: top
            });

            this.show();
        },
        show: function(){
            this.box.stop(true).css({
                marginTop: - 10,
                opacity: 0
            }).show().animate({marginTop: 0, opacity: 1}, 200, function(){

            });
        },
        hide: function(){
            var box = this.box;

            box.animate({
                marginTop: -10,
                opacity: 0
            }, 100, function(){
                box.hide();
            });
        }
    }

    new anchorPop();

    $.confirm=function(text,callback){

        var cmd = {
            'close': function(){
                $('.confirm-box').remove();
            }
        };

        if(arguments.length == 1 && cmd[text]){
            cmd[text].call(null);
            return;
        }

        if($(".confirm-box").length==0){
            $("body").append('<div class="confirm-box">\
                <a href="javascript:void(0)" class="btn-setbox-close js-confirm-close"></a>\
                <p>'+text+'</p>\
                <a href="javascript:void(0)" class="confirm-ok">确定</a>\
                <a href="javascript:void(0)" class="confirm-cancel">取消</a>\
            </div>')
        }

        $(".js-confirm-close,.confirm-cancel").click(function(event) {
            $(".confirm-box").remove();
        });
        $(".confirm-ok").click(callback);
    }

    function initReportChart(data){
        seajs.use('/static/lib/echarts/echarts.js', function(){
            var reportChart = echarts.init($('#report-chart')[0]),
                option = {
                    tooltip : {
                        trigger: 'item'
                    },
                    /*legend: {
                        data:['课节']
                    },*/
                    /*toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },*/
                    calculable : false,
                    xAxis :[ 
                        {
                            type : 'category',
                            boundaryGap : false,
                            lineStyle: {
                                color: '#00b33b'
                            },
                            data : ['周一','周二','周三','周四','周五','周六','周日']
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value',
                            lineStyle: {
                                color: '#00b33b'
                            }
                        }
                    ],
                    series : [
                        {
                            name:'课节',
                            type:'line',
                            color: 'red',
                            smooth:true,
                            itemStyle: {normal: {areaStyle: {type: 'default', color: 'rgba(0, 179, 59, .05)'}}},
                            data:[10, 12, 21, 54, 260, 830, 710]
                        }
                    ],
                },
                seriesArr = [],
                xaxisArr = [];
        
            if(data.length > 0){
                $.each(data, function(i, item){
                    xaxisArr.push(item.week);
                    seriesArr.push(item.medias || 0);
                });

                option.xAxis[0].data = xaxisArr;
                option.series[0].data = seriesArr;
            }     

            var theme = {
                // 默认色板
                color: [
                    '#408829','#68a54a','#a9cba2','#86b379',
                    '#397b29','#8abb6f','#759c6a','#bfd3b7'
                ],          
                // 值域
                dataRange: {
                    color:['#1f610a','#97b58d']
                },

                // 提示框
                tooltip: {
                    backgroundColor: 'rgba(0,0,0,0.5)',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'line',         // 默认为直线，可选为：'line' | 'shadow'
                        lineStyle : {          // 直线指示器样式设置
                            color: '#408829',
                            type: 'dashed'
                        },
                        crossStyle: {
                            color: '#408829'
                        },
                        shadowStyle : {                     // 阴影指示器样式设置
                            color: 'rgba(200,200,200,0.3)'
                        }
                    }
                },
                
                // 网格
                grid: {
                    borderWidth: 0
                },

                // 类目轴
                categoryAxis: {
                    axisLine: {            // 坐标轴线
                        lineStyle: {       // 属性lineStyle控制线条样式
                            color: '#d0d6d9',
                            width: '1'
                        }
                    },
                    axisTick: {
                        show: false
                    },
                    splitLine: {           // 分隔线
                        lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                            color: ['#eee']
                        }
                    }
                },

                // 数值型坐标轴默认参数
                valueAxis: {
                    axisLine: {            // 坐标轴线
                        lineStyle: {       // 属性lineStyle控制线条样式
                            color: '#ffffff'
                        }
                    },
                    splitArea : {
                        show : true,
                        areaStyle : {
                            color: ['rgba(250,250,250,0.1)','rgba(200,200,200,0.1)']
                        }
                    },
                    splitLine: {           // 分隔线
                        lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                            color: ['#eee']
                        }
                    }
                },
                timeline : {
                    lineStyle : {
                        color : '#d0d6d9'
                    },
                    controlStyle : {
                        normal : { color : '#408829'},
                        emphasis : { color : '#408829'}
                    }
                },     
                textStyle: {
                    fontFamily: '微软雅黑, Arial, Verdana, sans-serif'
                }
            };

            
            reportChart.setTheme(theme);
            reportChart.setOption(option);
        });
    }

    function fillJoinTime(){
        var otime = $('#js-join-time').data('time'),
            etime = new Date().getTime(),
            diff = etime - otime,
            diffHour = diff / (1000 * 60 * 60);

        console.log(diffHour);
    }

    function bindEvents(){
        $('.js-plan-action').on('click', function(e){
            var cmd = $(this).data('cmd'),
                pid = $(this).data('pid');

            if(!OP_CONFIG.isLogin){
                $('#js-signin-btn').trigger('click');
            }else{
                planMethod[cmd] && planMethod[cmd].call(this, pid, $(this))
            }
        })
        $('.js-join-plan').on('click', function(e){
            var pid = $(this).data('pid');

            planMethod['join'].call(this, pid);
        });
        $('.js-cancel-plan').on('click', function(e){
            var pid = $(this).data('pid');

            planMethod['cancel'].call(this, pid);
        });

        $('.js-route-panel').on('click', '.step-anchor', function(e){
            var _this = $(this),
                index = _this.index(),
                mediaWrap = _this.parents('.step-item').find('.step-medias-wrap:first');

            mediaWrap.stop(true, true);
            if(_this.hasClass('open')){
                _this.removeClass('open');
                mediaWrap.slideUp('fast');
                return;
            }

            $('.js-route-panel').find('.open').removeClass('open');
            _this.addClass('open');

            mediaWrap.find('ul').hide().eq(index).show();

            if(!mediaWrap.is(':visible')){
                $('.js-route-panel').find('.step-medias-wrap').hide();

                mediaWrap.slideDown();
            }     
        });

        $('.js-raise-panel').on('click', 'h4', function(e){
            var _this = $(this),
                panel = $('.js-raise-panel'),
                curMediasWrap = _this.parents('.step-item').find('.step-medias-wrap');

            curMediasWrap.stop(true, true);
            if(_this.hasClass('open')){

                _this.removeClass('open');
                curMediasWrap.slideUp('fast');
                return;
            }

            panel.find('h4.open').removeClass('open');

            _this.addClass('open');

            panel.find('.step-medias-wrap').hide();
            curMediasWrap.slideDown();
        });

        $('#js-plan-desc').on('click', '.txt-more', function(e){
            var descBox = $('#js-plan-desc'),
                shortDesc = descBox.find('.desc-short'),
                longDesc = descBox.find('.desc-long'),
                maxHeight = descBox.data('max-height') | 192;

            if(descBox.hasClass('expand')){
                descBox.removeClass('expand');
                $(this).text('展开');
            }else{
                descBox.addClass('expand');
                $(this).text('收起');
            }
        });
    }
    
    // 初始化学习报告
    if($('#report-chart').length > 0){
        initReportChart(chartData);        
    }

    bindEvents();

    // 加薪利器，默认打开第一步
    if($('#js-raise-panel').is(':visible')){
        setTimeout(function(){
            var panel = $('#js-raise-panel'),
                lastStepLearned = panel.find('.step-item.step-last-learned:first');

            if(lastStepLearned.length == 0){
                lastStepLearned = panel.find('.step-item').first();
            }

            if(lastStepLearned.length){
                lastStepLearned.find('h4:first').trigger('click');
            }
        }, 100);
    }else if($('#js-route-panel').is(':visible')){
        // 求职路线 打开最后一次学习的知识点，否则打开第一个
        
        var lastAnchorLearned = $('#js-route-panel').find('.step-anchor.step-last-learned:first');

        if(lastAnchorLearned.length == 0){
            lastAnchorLearned = $('#js-route-panel').find('.step-anchor').first();
        }

        if(lastAnchorLearned.length){
            lastAnchorLearned.trigger('click');
        }
    }

    $(".js-note").on("click",function(event) {
        var obj = $(this),
            flag = "close",
            pid = obj.data('pid'),
            stateTxt = obj.find('.clock-state');

        if(!obj.hasClass('open')){
            flag = "open";
        }
        if(isAjax) return;

        isAjax = 1;
        $.ajax({
            url:"/course/AjaxPostProgramSet",
            data:{pid: pid,t: "set_msg_open",s: flag},
            method:"get",
            dataType:"json",
            success:function(res){
                if(res.result==1){
                    if(flag == 'open'){
                        obj.addClass('open');
                        stateTxt.text('已开启');
                        $(".note-tip").text("慕女神会通知课程更新").fadeIn(150)
                     }else{
                        obj.removeClass('open');
                        stateTxt.text('已关闭');
                        $(".note-tip").text("慕女神不通知课程更新").fadeIn(150)
                     }
                } 
                setTimeout(function(){
                    $(".note-tip").text("").fadeOut(200)
                },1000)
            },
            error:function(){
                (".note-tip").text("操作失败,请稍后再试").fadeIn(200)
                setTimeout(function(){
                    $(".note-tip").text("").fadeOut(200)
                },1000)
            },
            complete: function(){
                isAjax = 0;
            }
        })
    });
})