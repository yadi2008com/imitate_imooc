!function($){
    var fn;
    function validate(options,form){
        this.fieldState={};
        this.rules={};
        this.$form=$(form);
        this.init(options);
    }
    
   
    //fn=validate.prototype;


    $.extend(validate.prototype,{
        options:{
            defaultBlur:function(e){
                $(this).validate(e);
            },
            defaultKeyup:function(e){
                $(this).validate(e);
            },
            defaultFocus:function(e){

            }
        }, 

        defaultRules:{
            email:function(cb,v){
                var r=/^[a-zA-Z0-9]+([._\-][a-zA-Z0-9]+)*@([a-zA-Z0-9]+([._\-][a-zA-Z0-9]+))+$/;
                if(!v.length){
                    return "邮箱不能为空！";
                }
                if(!r.test(v)){
                    return "邮箱格式不正确！"
                }
            },
            password:function(cb,v){
                var r=/^[\w~`!@#$%&*()_+<>?:"'+*\/\-\^\\\][{}]{6,16}$/;
                if(!v.length){
                    return "密码不能为空！";
                }
                if(!r.test(v)){
                    return "请输入6-16位密码，区分大小写，不能使用空格！";
                }
            },
            nick:function(cb,v){
                var r=/^[\w\u4E00-\u9FA5]{2,18}$/;
                if(!v.length){
                    return "昵称不能为空！";
                }
                if(!r.test(v)){
                    return "昵称2-18位中英文、数字及下划线！";
                }
            },
            equalTo:{
                rule:function(v,target){
                    var tv=$(this.form).find("[name='"+target+"']").val();
                    tv=$.trim(tv);
                    if(v!==tv){
                        return "两次密码输入不一致！";

                    }
                },
                force:1
            }

        },
        init:function(options){
            var fields=options.fields,
                _this=this,
                events={error:1,valid:1,before:1,after:1},
                key,rules,i,len,rule,fieldEvents,force,field;

            for(key in events){
                if(options["on"+key]){
                    this.on("vd."+key,options["on"+key]);
                    delete options["on"+key];
                }
            }

            var vl = this.$form.find("[data-validate]").length;
            this.$form.find("[data-validate]").each(function(i,el){
                var $this=$(this),
                    n=$(this).attr("name");

                if(!n) return;
                _this.rules[n]=(_this.rules[n]||(_this.rules[n]=[])).concat($this.attr("data-validate").split("|"));
            });



            for(key in fields){
                if(rules=fields[key].rules){
                    this.rules[key]=(this.rules[key]||(this.rules[key]=[])).concat(rules);
                }
            }

            
            for(key in this.rules){
                rules=this.rules[key];
                force=0;
                for(i=0,len=rules.length;i<len;i++){
                    rule=rules[i];
                    if(typeof rule==="string"){
                        rules.splice(i,1,this.defaultRules[rule]);
                    }
                    force=rules[i].force;
                }
                
                force&&(rules.force=1);

                field=fields[key]||{};
                fieldEvents={
                    blur:field.blur||this.options.defaultBlur,
                    keyup:field.keyup||this.options.defaultKeyup,
                    focus:field.focus||this.options.defaultFocus
                }
                for(i in events){
                    if(field["on"+i]){
                        fieldEvents["vd."+i]=field["on"+i];
                    }
                    
                }
                
                
                this.$form.find("[name='"+key+"']").on(fieldEvents);

                this.fieldState[key]=0;

            }
            delete options.fields;
          
        },

    
        valid:function(el,force){
            var name=$(el).attr("name"),
                val=$(el).val();
            if(this.fieldState[name]===0&&!val){
                return ;
            }
            if(this.fieldState[name]===0&&force&&force.type=="keyup"){
                return ;
            }

            //this.trigger("vd.error.form");
            this._valid(el,force);
        },

        _valid:function(el,force){
            //this.trigger($.Event("vd.before",{_form:this.$form[0]}));
            if(el){
                this.check(el,force);
            }
            else{
                for(var key in this.rules){
                    this.check(key,0);
                }
            }
        },

        check:function(el,force){
            var name,$el,rules,rule,val,index,len,_this;
            if(typeof el==="string"){
                name=el;
                $el=this.$form.find("[name='"+el+"']");
            }
            else{
                $el=$(el);
                name=$el.attr("name");
            }

            if(!name) return;

            //val=$.trim($el.val());
            val=$el.val();
            rules=this.rules[name];
            //force validate may configed or trigger by field validate 
            //console.dir(rules)
            //console.log(rules._value,name,force,rules.force);
            if(rules._value===val&&!force&&!rules.force) return this.fieldState[name];
            //begin check rules;
            //this.trigger($.Event("vd.before",{_relateField:$el[0]}))

            $el.trigger($.Event("vd.before",{_relateField:$el[0]}))
            this.fieldState[name]="pending";
            rules._value=val;
            if(rules.xhr){
                rules.xhr.abort();
                delete rules.xhr;
            }
            
            index=0;
            len=rules.length;
            _this=this;

            next();
            function next(){
                var r,fn;
                rule=rules[index++];
                fn=rule.rule||rule;
                if(typeof fn=="function"){
                    r=fn.call($el[0],handleResult,val);
                    if(typeof r==="object"){
                        rules.xhr=r;
                    }
                    else if(r!=="pending"){
                        handleResult(r);
                    }
                }
            }

            function handleResult(result){
                //console.log("handleResult",result)
                if(result!==undefined&&result!==true){
                    _this.fieldState[name]=false;
                    $el.trigger($.Event("vd.error",{_relateField:$el[0],tip:result||rule.tip||""}));
                    $el.trigger($.Event("vd.after",{_relateField:$el[0]}));
                }
                else if(index===len){
                    //_this.trigger($.Event("vd.valid",{_relateField:$el[0],tip:result||rules.tip||""}));
                    _this.fieldState[name]=true;
                    $el.trigger($.Event("vd.valid",{_relateField:$el[0]}));
                    $el.trigger($.Event("vd.after",{_relateField:$el[0]}));
                }
                else{
                    next();
                }
            }
        },

        getFullState:function(){
            var finish=true,key;
            for(key in this.fieldState){
                if(this.fieldState[key]!==true){
                    finish=this.fieldState[key];
                    if(finish===false)
                        break;
                }
            }
            return finish;
        },
        validate:function(cb){
            var st;
            
            //console.log("getFullState:",st);
            this._valid();
            st=this.getFullState();

            if(st==="pending"){
                if(!this.binded){
                    this.binded=1;
                    this.onerror=[];
                    this.onsuccess=[];

                    var fn=$.proxy(function(e){
                        this.fire("error");
                    },this);
                    this.one("vd.error.form",fn);
                    this.one("vd.before.errror",fn);
                    
                    this.on("vd.valid.success",$.proxy(function(){
                        if(this.getFullState()===true){
                            this.fire("success");
                        }
                    },this));
                }

                //this.one("vd.form.error",cb.error);
                //this.one("vd.form.success",cb.success);
                this.onerror.push(cb.error);
                this.onsuccess.push(cb.success);
               // console.dir(this.onsuccess);
                delete cb.error;
                delete cb.success;
            }
            else if(st===true){
                cb.success.call(this.$form[0],this.getValues());
            }
            else{
                cb.error.call(this.$form[0],this.getValues());
            }
        },
        fire:function(f){
            var a=this["on"+f],
                i=0,
                len,
                fun,val;
     
           
            if(!a) return;
            len=a.length
            val=this.getValues();
            for(;i<len;i++){
                a[i].call(this.$form[0],val);
            }
            this.clean();
        },
        clean:function(){
            this.binded=0;
            this.onerror=this.onsuccess=null;
            //this.off("vd.form.error");
            this.off("vd.before.errror");
            this.off("vd.form.success");
            this.off("vd.error.form");
            //this.off("vd.valid.success");
        },
        getValues:function(){
            var o={},k;
            for(k in this.rules){
                o[k]=this.rules[k]._value;
            }
            return o;
        }
    })
    
    $.each("on,off,trigger,one".split(","),function(i,key){
        validate.prototype[key]=function(){
            this.$form[key].apply(this.$form,arguments);
        }
    });

    $.fn.validate=function(cb){
        var vd;
        var _this=this[0];
        var tag=_this.tagName.toLowerCase();
        if(tag!="form"){
            vd=$(_this.form).data("_validater")
            if(vd){
                vd.valid(_this,cb);
            }
        }
        else if(vd=$(this).eq(0).data("_validater")){
            vd.validate(cb);
        }
        
    }
    $.fn.validateSetup=function(options){
        $(this).each(function(){
            $(this).data("_validater")||$(this).data("_validater",new validate(options,this));
        });
        return this;
    }
}($)