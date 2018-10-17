;(function($, undefined) {
    /**
     * 弹出框构造函数
     * @param {Object} opt
     */
    var Move = function(opt){};
    /**
     * 定义原型对象
     */
    Move.prototype = {
    		
    	/**
    	 * 弹出层
    	 * {
    			titleIcon:"fa-warning",
    			title:"温馨提示",
    			contentIcon:"fa-warning",
    			content:"请输入查询半径",
    			btnGroup:[{
        			"cls":"pop-btn-close",
        			"value":"取消"
        		}]
    		}
    	 */
    	pop:function(opt){
    		var than = this;
    		var html = "";
    		html += "<div class='pop-trans'>";
    		html += "    <div class='pop'>";
    		html += "        <div class='pop-head'>";
    		html += "            <span>&nbsp;<i class='fa "+opt.titleIcon+"'></i>&nbsp;"+opt.title+"</span>";
    		html += "            <span><i class='fa fa-close'></i></span>";
    		html += "        </div>";
    		html += "        <div class='pop-body'>";
    		html += "            <span><i class='fa "+opt.contentIcon+"'></i>"+opt.content+"</span>";
    		html += "        </div>";
    		html += "        <div class='pop-foot'>";
    		if(opt.btnGroup.length>0){
    			for(var i = 0, len=opt.btnGroup.length; i<len; i++){
    				var btn = opt.btnGroup[i];
    				html += "            <span class='"+btn.cls+"'>"+btn.value+"</span>";
//    				$("body").on("mousedown","."+btn.cls,function(){
    					$("body").on("click","."+btn.cls,function(){
    					$(".pop").animate({"top":"130%"},200,"linear",function(){
    	    				$(".pop-trans").remove();
    	    			});
    	    		});
    			}
    		}
    		html += "        </div>";
    		html += "    </div>";
    		html += "</div>";
    		$("body").append(html);
    		$(".pop-trans").css({"display":"inline-block"});
    		$(".pop").animate({"top":"50%"});
    		$("body").on("mousedown",".pop-trans > .pop > .pop-head",function(){
    			than.mouseDown($(".pop"),event);
    			return false;
    		});
    		$("body").on("mousedown",".pop-head > span:last-child > i",function(){
    			$(".pop").animate({"top":"130%"},200,"linear",function(){
    				$(".pop-trans").remove();
    			});
    		});
    	},
        /**
         * 鼠标移动参数
         */
        paramMove : function(){
            var _move=false;//移动标记
            var _x,_y;//鼠标离控件左上角的相对位置
            return {x:0,y:0,move:false};
        },
        mouseDown : function(obj, event){
            this.paramMove.move=true;
            this.paramMove.x=event.pageX-parseInt($(obj).css("left"));
            this.paramMove.y=event.pageY-parseInt($(obj).css("top"));
            $(obj).fadeTo(20, 0.5);//点击后开始拖动并透明显示
            this.mouseMove(obj);
        },
        mouseMove : function(obj){
            var then = this;
            //弹窗拖动事件
            $(obj).mousemove(function(event){
                if(then.paramMove && then.paramMove.move){
                    var x=event.pageX-then.paramMove.x;//移动时根据鼠标位置计算控件左上角的绝对位置
                    var y=event.pageY-then.paramMove.y;
                    $(obj).css({top:y,left:x});//控件新位置
                }
            }).mouseup(function(){
                then.paramMove.move=false;
                $(obj).fadeTo("fast", 1);//松开鼠标后停止移动并恢复成不透明
            });
        }
    };
    //实例暴露全局化
    window.Move = new Move();
})(jQuery);