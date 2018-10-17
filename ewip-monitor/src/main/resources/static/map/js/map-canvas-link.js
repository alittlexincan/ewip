if (typeof jQuery === 'undefined') {
    throw new Error('需要jQuery依赖');
}
(function($){
    var imageCache = {};
    var Monitor;
    var  centerX,centerY,centerW ,centerH;
    var canvas; 
    var ctx;    //canvas
    $.channelMonitor = function(obj,options){
        $(obj).html("");
        canvas = document.createElement('canvas'); 
        canvas.width  = $(obj).width(); 
        canvas.height = $(obj).height();
        $(obj).append(canvas); 
        ctx = canvas.getContext("2d");
        var thisMonitor = new Monitor(options);
        if(thisMonitor.showMessageBox){
            $(obj).css("position","relative");
            var _div = $("<div></div>");
            _div.css("position","absolute");
            _div.css("top",thisMonitor.panelPadding+"px");
            _div.css("left",(thisMonitor.panelPadding+thisMonitor.boxWidth*canvas.width+thisMonitor.curveWidth*canvas.width+15)+"px");
            _div.css("right",(thisMonitor.panelPadding+thisMonitor.boxWidth*canvas.width+thisMonitor.curveWidth*canvas.width+15)+"px");
            _div.css("bottom",((canvas.height+canvas.width*thisMonitor.centerIconWidth)/2+15)+"px");
//            _div.css("background-color","#353b56");

            var _pannel = $("<div style='position:relative;width:100%;height:80%;'></div>");
            _pannel.append("<div style='position:absolute;top:0;left:0;width:20px;height:20px;border-top:1px solid #acebfd;border-left:1px solid #acebfd;'></div>");
            _pannel.append("<div style='position:absolute;top:0;right:0;width:20px;height:20px;border-top:1px solid #acebfd;border-right:1px solid #acebfd;'></div>");
            _pannel.append("<div style='position:absolute;bottom:0;left:0;width:20px;height:20px;border-bottom:1px solid #acebfd;border-left:1px solid #acebfd;'></div>");
            _pannel.append("<div style='position:absolute;bottom:0;right:0;width:20px;height:20px;border-bottom:1px solid #acebfd;border-right:1px solid #acebfd;'></div>");
            var _contentDiv = $("<div style='width:100%;height:100%;padding:5px;overflow: hidden;background-color: #142f81;'></div>");
            _pannel.append(_contentDiv);

            _div.append(_pannel);

            $(obj).append(_div); 
            thisMonitor.messageBox = _contentDiv;
        }
        $(window).resize(function(){
            //窗口大小改变，自动适应
            canvas.width  = $(obj).width(); 
            canvas.height = $(obj).height();
            var reDiv = $(obj).children("div");
            reDiv.css("top",thisMonitor.panelPadding+"px");
            reDiv.css("left",(thisMonitor.panelPadding+thisMonitor.boxWidth*canvas.width+thisMonitor.curveWidth*canvas.width+15)+"px");
            reDiv.css("right",(thisMonitor.panelPadding+thisMonitor.boxWidth*canvas.width+thisMonitor.curveWidth*canvas.width+15)+"px");
            reDiv.css("bottom",((canvas.height+canvas.width*thisMonitor.centerIconWidth)/2+15)+"px");
        });
        return thisMonitor;
    };


    Monitor = function(options){
         var options = $.extend({}, Monitor.pluginDefaults, options);
         $.extend(this, options);
    };

    Monitor.prototype = {
        setData : function(data){
            if(data){
                this.data = data;
            }
            //缓存图片，当缓存完成后初始化
            this._cacheImage();
        },
        //缓存图片
        _cacheImage : function(){
            var imgSrcArray =[];
            imgSrcArray.push(this.centerIcon);
            imgSrcArray.push(this.statusMapping[0].imgSrc);
            // imgSrcArray.push(this.statusMapping[0].lineSrc);
            imgSrcArray.push(this.statusMapping[1].imgSrc);
            // imgSrcArray.push(this.statusMapping[1].lineSrc);
            imgSrcArray.push(this.statusMapping[2].imgSrc);
            // imgSrcArray.push(this.statusMapping[2].lineSrc);
            $.each(this.data.left,function(){
                imgSrcArray.push(this.imgSrc);
            });
            $.each(this.data.right,function(){
                imgSrcArray.push(this.imgSrc);
            });
            $.each(this.data.bottom,function(){
                imgSrcArray.push(this.imgSrc);
            });
            this._loadImage(imgSrcArray,0);
        },
        _loadImage : function(imgSrcArray,index){
            var that = this;
            if(imgSrcArray.length > index){
                // console.log("加载中："+(index +1) +"/"+imgSrcArray.length+"    "+imgSrcArray[index]);
                var imgSrc = imgSrcArray[index];
                if(!imageCache[imgSrc]){    
                    //没有缓存图片，缓存
                    var img = new Image();
                    img.onload = function(){
                        imageCache[imgSrc] = img;
                        index++;
                        that._loadImage(imgSrcArray,index);
                    };
                    img.src = imgSrc;
                }else{
                    //存在就直接缓存下一个
                    index++;
                    that._loadImage(imgSrcArray,index);
                }
            }else{
                //缓存完成
                // console.log("加载完毕。");
                that._init();
            }
        },
        //初始化
        _init : function(){
            //清空canvas
			ctx.save();
            ctx.clearRect(0,0,canvas.width,canvas.height);
			if(this.bgColor != "none"){
				ctx.fillStyle = this.bgColor;   //背景颜色
				ctx.fillRect(0,0,canvas.width,canvas.height);
			}
			ctx.restore();
            this.boxs = []; //添加box数组

            //生成标题
           // this._drawTitle();
            //生成中心
            this._drawCenter(this._autoBox);
            //计算框的大小位置
            var that = this;
            //绑定事件
            if(this.boxCallBack && typeof this.boxCallBack == 'function'){
                $(canvas).unbind("click");
                $(canvas).bind("click",function(e){
                    //canvas点击事件
                    var offsetX = e.offsetX;
                    var offsetY = e.offsetY;
                    for(var box of that.boxs){
                        if(offsetX > box.x && offsetX < box.x + box.width){
                            if(offsetY > box.y && offsetY < box.y + box.height){
                                that.boxCallBack(box.params);       //返回用户参数给回调函数
                                break;
                            }
                        }
                    }
                });
            }
        },
        
        //生成标题
        _drawTitle : function(){
            var titleWidth = getTextWidth(this.title.text,this.title.font);
			ctx.save();
            ctx.font = this.title.font;
            ctx.fillStyle = this.title.color;
            ctx.textBaseline="middle";
            ctx.fillText(this.title.text, (canvas.width-titleWidth)/2, this.panelPadding);
			ctx.restore();
        },
        //生成中心
        _drawCenter : function(callBack){
            var img = imageCache[this.centerIcon];
            var centerIconWidth = this.centerIconWidth;
            var that = this;
            var w = img.width;
            var h = img.height;
            var maxWidth = canvas.width * centerIconWidth;
            if(w > maxWidth){
                h = h * maxWidth / w;
                w = maxWidth;
            }
            var x = (canvas.width - w)/2;
            var y = (canvas.height - h)/2;
            centerX = x;
            centerY = y;
            centerW = w;
            centerH = h;
            callBack(that);
            ctx.save();
            
            // 绘制中心 分发平台 矩形，并添加背景颜色
			ctx.fillStyle = "rgb(13, 8, 154)";   //背景颜色
			ctx.fillRect(x,y,w,h);
			
			
			ctx.restore();
            ctx.drawImage(img,x,y,w,h);
            
            ctx.save();
            ctx.beginPath(); // 开始路径绘制
            ctx.moveTo(x+w/2, y); 
            ctx.lineTo(x+w/2, y/2);
			ctx.lineWidth = 2;
			ctx.strokeStyle = "lime";
            ctx.stroke();
            ctx.restore();
            
            
            
        },
        //计算框的大小位置
        _autoBox : function(that){
            //左边框
            var leftLength = that.data.left.length;
            if(leftLength > 0){
                var boxMargin = that.boxMargin;
                var a = canvas.height - 2 * that.panelPadding - (leftLength + 1) * boxMargin - leftLength * that.boxMaxHeight;
                if(a > 0){
                    boxMargin = (canvas.height - 2 * that.panelPadding - leftLength * that.boxMaxHeight)/(leftLength + 1);
                }
                var leftBoxHeight = (canvas.height - 2 * that.panelPadding - (leftLength + 1) * boxMargin)/leftLength;
                for(var i=0;i<that.data.left.length;i++){
                    var d = that.data.left[i];
                    var box = new textBox();
                    box.text = d.text;
                    box.imgSrc = d.imgSrc;
                    box.status = d.value;
                    box.x = that.panelPadding;
                    box.y = that.panelPadding  + i * (boxMargin + leftBoxHeight) + boxMargin;
                    box.width = that.boxWidth*canvas.width;
                    box.height = leftBoxHeight;
                    box.position = 'left';
                    box.statusMapping = that.statusMapping;
                    box.boxPadding = that.boxPadding;
                    box.boxTextFont = that.boxTextFont;
                    box.params = d.params;              //用户自定义参数
                    box.draw();

                    that.boxs.push(box);

                    var ex = centerX;
                    var ey = centerY + centerH/(leftLength+1)*(i+1);
                    drawLine(i,leftLength,box.x+box.width,box.y+box.height/2,ex,ey,that.statusMapping[d.value].color,that.curveWidth*canvas.width,"left",d.sending,that.speed);
                }
            }

            //右边框
            var rightLength = that.data.right.length;
            if(rightLength > 0){
                var boxMargin = that.boxMargin;
                var a = canvas.height - 2 * that.panelPadding - (rightLength + 1) * boxMargin - rightLength * that.boxMaxHeight;
                if(a > 0){
                    boxMargin = (canvas.height - 2 * that.panelPadding - rightLength * that.boxMaxHeight)/(rightLength + 1);
                }
                var rightBoxHeight = (canvas.height - 2 * that.panelPadding - (rightLength + 1) * boxMargin)/rightLength;
                for(var i=0;i<that.data.right.length;i++){
                    var d = that.data.right[i];
                    var box = new textBox();
                    box.text = d.text;
                    box.imgSrc = d.imgSrc;
                    box.status = d.value;
                    box.x = canvas.width - that.panelPadding - that.boxWidth*canvas.width;
                    box.y = that.panelPadding + i * (boxMargin + rightBoxHeight)  + boxMargin;
                    box.width = that.boxWidth*canvas.width;
                    box.height = rightBoxHeight;
                    box.position = 'right';
                    box.statusMapping = that.statusMapping;
                    box.boxPadding = that.boxPadding;
                    box.boxTextFont = that.boxTextFont;
                    box.params = d.params;
                    box.draw();

                    that.boxs.push(box);

                    var ex1 = centerX + centerW;
                    var ey1 = centerY + centerH/(rightLength+1)*(i+1);
                    drawLine(i,rightLength,box.x,box.y+box.height/2,ex1,ey1,that.statusMapping[d.value].color,that.curveWidth*canvas.width,"right",d.sending,that.speed);
                }
            }


            //底部边框
            var bottomLength = that.data.bottom.length;
            if(bottomLength > 0){
                var boxMargin = that.boxMargin;
                var a = canvas.width - 2 * that.panelPadding - (bottomLength + 1) * boxMargin - bottomLength * that.bottomBoxMaxWidth - 2 * that.boxWidth*canvas.width -2 * that.curveWidth*canvas.width;
                if(a > 0){
                    boxMargin = (canvas.width - 2 * that.panelPadding - bottomLength * that.bottomBoxMaxWidth- 2 * that.boxWidth*canvas.width -2 * that.curveWidth*canvas.width)/(bottomLength + 1);
                }
                var bottomBoxWidth = (canvas.width - 2 * that.panelPadding - 2 * that.boxWidth*canvas.width -2 * that.curveWidth*canvas.width - (bottomLength + 1) * boxMargin)/bottomLength;
                for(var i=0;i<bottomLength;i++){
                    var d = that.data.bottom[i];
                    var box = new textBox();
                    box.text = d.text;
                    box.imgSrc = d.imgSrc;
                    box.status = d.value;
                    box.x = that.panelPadding + that.boxWidth*canvas.width + that.curveWidth*canvas.width + (i+1) * boxMargin + i * bottomBoxWidth;
                    box.y = canvas.height - that.panelPadding - that.bottomBoxHeight*canvas.height;
                    box.width = bottomBoxWidth;
                    box.height = that.bottomBoxHeight*canvas.height;
                    box.position = 'bottom';
                    box.statusMapping = that.statusMapping;
                    box.boxPadding = that.boxPadding;
                    box.boxTextFont = that.boxTextFont;
                    box.params = d.params;
                    box.draw();

                    that.boxs.push(box);

                    var ex2 = centerX + centerW/(bottomLength+1)*(i+1);
                    var ey2 = centerY + centerH;
                    drawLine(i,bottomLength,box.x+box.width/2,box.y,ex2,ey2,that.statusMapping[d.value].color,that.curveHeight*canvas.height,"bottom",d.sending,that.speed);
                }
            }
        }
    };

    /*
    *   默认配置
    */
    Monitor.pluginDefaults = {
        showMessageBox:true,                        //是否显示信息框
        bgColor: 'black',                           //背景颜色
        title:{
            font:"2em 微软雅黑",                   //标题字体及大小
            text:"突发事件预警信息监控系统",          //标题文本
            color:"#fff"                            //标题颜色
        },
        centerIcon:'image/center_cpt0.png',
        centerIconWidth:0.1,                        //中间图标宽，表示占总宽的比例
        statusTitle:{                               //状态说明头
            show:true,                                  //是否显示，默认显示
            bgColor:"red",                              //背景颜色
            title:'图例'                                //标题
        },
        statusMapping:{                             //状态对应信息
            0:{color:'green',text:'运行正常',imgSrc:'image/status0.png',lineSrc:'image/status_line0.png',lineText:'渠道畅通'},
            1:{color:'red',text:'运行异常',imgSrc:'image/status1.png',lineSrc:'image/status_line1.png',lineText:'渠道不通'},
            2:{color:'gray',text:'未部署',imgSrc:'image/status2.png',lineSrc:'image/status_line2.png',lineText:'渠道未部署'}
        }, 
        panelPadding:30,                            //面板边距
        boxMargin:10,                               //box之间间距
        boxPadding:0.2,                             //box内间距,相当于box宽或高的比例
        boxMaxHeight:60,                            //box 最大高
        boxWidth:0.2,                               //box 宽,总宽的比例
        boxTextFont:"1.4em 微软雅黑",                  //box中文本
        bottomBoxMaxWidth:80,                       //底部box最大宽
        bottomBoxHeight:0.2,                        //底部box高,总高的比例
        boxCallBack:null,                           //点击框事件  
        lineWidth:2,                                //线条宽度
        curveWidth:0.05,                            //链接线拐角到框最大长度,总宽的比例
        curveHeight:0.06,                           //链接线拐角到框最大长度,总高的比例
        speed:8000,                                 //流光速度，毫秒
        data:{
            left:[],                                //左边显示
            right:[],                               //右边显示
            bottom:[]                               //下面显示
        }
    };

    //渠道展示框实体
    var textBox = function(){
        var box = {
            text:'',                        //显示文本
            imgSrc:'',                      //显示图片地址
            status:0,                       //状态值
            x:0,                            //框横坐标
            y:0,                            //框纵坐标
            width:0,                        //框宽
            height:0,                       //框高
            position:'left',                //位置，左、右、下
            statusMapping:{},               //状态对应信息
            boxPadding:0,                   //box内间距
            boxTextFont:"",                 //box中文本
            draw:function(){                //生成渠道框
                //生成虚线框
                drawDashRect(this.x,this.y,this.x+this.width,this.y+this.height,this.statusMapping[this.status].color);
                var x = this.x,y=this.y,width=this.width,height=this.height,text=this.text;
                var that = this;
                var font = this.boxTextFont;
                if(this.position == 'bottom'){
                    var imgX = this.x + this.boxPadding * this.width;
                    var imgY = this.y + this.boxPadding * this.width;
                    //画图片
                    var imgWidth = this.width - 2 * this.boxPadding * this.width;
                    drawImg(this.imgSrc,imgX,imgY,imgWidth,null,function(imgW,imgH){
                        var textWidth = getTextWidth(text,font);  //获取文本的宽
                        var txtY = y + imgH + 2 * that.boxPadding * that.width;
                        var txtX = x + (width-textWidth)/2;
                        var scaleNum = 1;   //缩放等级，1原始大小，大于1放大，小于1缩小
                        if(textWidth>width){
                            txtX = x;
                            scaleNum = width/textWidth;
                        }
                        //写文本
                        ctx.save();
                        ctx.scale(scaleNum,scaleNum);
                        writeText(text,txtX/scaleNum,txtY/scaleNum,font,"top");
                        ctx.restore();
                    });
                }else{
                    var imgX = this.x + this.boxPadding * this.height;
                    var imgY = this.y + this.boxPadding * this.height;
                    //画图片
                    var imgHeight = this.height - 2 * this.boxPadding * this.height;
                    drawImg(this.imgSrc,imgX,imgY,null,imgHeight,function(imgW,imgH){
                        var textWidth = getTextWidth(text,font);  //获取文本的宽
                        //写文本
                        var txtX = x + imgW + 2 * that.boxPadding * that.height;
                        var txtY = y + height/2;
                        var scaleNum = 1;   //缩放等级，1原始大小，大于1放大，小于1缩小
                        if(textWidth>width){
                            scaleNum = width/textWidth;
                        }
                        ctx.save();
                        ctx.scale(scaleNum,scaleNum);
                        writeText(text,txtX/scaleNum,txtY/scaleNum,font,"middle");
                        ctx.restore();
                    });
                }
                //画状态图标
                if(this.position == 'right'){       //显示在右上角
                    drawStatus(this.x + this.width,y,this.statusMapping[this.status].imgSrc,this.height);
                }else if(this.position == 'bottom'){       //显示在右上角
                    drawStatus(this.x + this.width,y,this.statusMapping[this.status].imgSrc,this.width);
                }else{                              //显示在左上角
                    drawStatus(this.x,y,this.statusMapping[this.status].imgSrc,this.height);
                }
            }
        };
        return box;
    };

    /**
     * 写文本
     * @param {string} txt 文本内容
     * @param {number} x 横坐标
     * @param {number} y 纵坐标
     * @param {string} font 字体样式
     * @param {boolean} setBaseline 是否设置文本基线为中央
     */
	var writeText = function(txt,x,y,font,setBaseline){
		ctx.save();
        var baseLine = ctx.textBaseline;
		ctx.font= font;
        ctx.fillStyle = '#fff';
        if(setBaseline){
            ctx.textBaseline = setBaseline;    //基准线为文本中间
        }
		ctx.fillText(txt, x, y);
        ctx.textBaseline = baseLine;    //基准线为文本中间
		ctx.restore();
    };

    /**
     * 获取文本的宽
     * @param {string} txt 
     * @param {string} fontSize 
     */
    var getTextWidth = function(txt,fontSize){
        ctx.font = fontSize;
        return ctx.measureText(txt).width;
    };

    /**
     * 画图片
     * @param {string} imgSrc   图片路径
     * @param {number} x        图片横坐标  
     * @param {number} y        图片纵坐标
     * @param {number} width    图片宽
     * @param {number} height   图片高
     * @param {function} callBack 回掉函数
     */
    var drawImg = function(imgSrc,x,y,width,height,callBack){
        var img = imageCache[imgSrc];
        var w = img.width;
        var h = img.height;
        if(width == null && height != null){
            width = w * height / h;     //只输入高，宽按比例缩放
        }else if(width != null && height == null){
            height = width * h /w;  //只输入宽，高按比例缩放
        }else if(width == null && height == null){
            width = w;
            height = h;
        }
        ctx.drawImage(img,x,y,width,height);
        if(callBack){
            callBack(width,height);
        }
    };

    /**
     * 画虚线矩形(x1,y1)左上角坐标，(x2,y2)右下角坐标
     * @param {number} x1 
     * @param {number} y1 
     * @param {number} x2 
     * @param {number} y2 
     * @param {string} color 
     */
    var drawDashRect = function(x1, y1, x2, y2,color){
		for(var j=0;j<4;j++){
            var xx1,xx2,yy1,yy2;
			switch(j){
				case 0:xx1=x1;yy1=y1;xx2=x2;yy2=y1;break;
				case 1:xx1=x2;yy1=y1;xx2=x2;yy2=y2;break;
				case 2:xx1=x1;yy1=y2;xx2=x2;yy2=y2;break;
				case 3:xx1=x1;yy1=y1;xx2=x1;yy2=y2;break;
            }
            ctx.save();
            ctx.setLineDash([10,5]);
            ctx.beginPath(); // 开始路径绘制
            ctx.moveTo(xx1, yy1); 
            ctx.lineTo(xx2, yy2);
			ctx.lineWidth = 2;
			ctx.strokeStyle = color;
            ctx.stroke();
            ctx.restore();
		}
    };

    /**
     * 画状态框状态图标
     * @param {number} x 框的状态图标所在角横坐标
     * @param {number} y 框的状态图标所在角纵坐标
     * @param {string} imgSrc 状态图标地址
     * @param {number} reference 参考数
     */
    var drawStatus = function(x,y,imgSrc,reference){
        var img = imageCache[imgSrc];
        // var size = 0.35;   //相对于参考值的比例
        // var w = reference*size;
        // var h = reference*size;
        var w = 21,h=21;
        ctx.drawImage(img,x-w/2,y-h/2,w,h);
    };

    /**
     * 画手段到平台渠道线
     * @param {number} index 第几条线
     * @param {number} total 一共几条线
     * @param {number} sx 开始横坐标
     * @param {number} sy 开始纵坐标
     * @param {number} ex 截至横坐标
     * @param {number} ey 截至纵坐标
     * @param {string} color 颜色
     * @param {number} curveWidth 拐角最大宽度
     * @param {string} position box位置
     * @param {number} sending 发送消息状态，0发送中，1没发送
     * @param {number} time 流光时间，单位毫秒
     */
    var drawLine = function(index,total,sx,sy,ex,ey,color,curveWidth,position,sending,time){
        var middelNum = parseInt(total/2);

        var pointArray = [];
        
        //偶数处理
        if(total % 2 == 0 && middelNum <= index){
            index ++;
        }

        var myCurveWidth = curveWidth/middelNum * Math.abs(middelNum-index);
        ctx.beginPath(); // 开始路径绘制
        ctx.moveTo(sx, sy); // 设置路径起点
        var obj ={'x':sx,'y':sy};
        pointArray.push(obj);
        var x = 0;
        var y = 0;
        switch(position){
            case 'left':x=sx + myCurveWidth;y=sy;break;
            case 'right':x=sx - myCurveWidth;y=sy;break;
            case 'bottom':x=sx;y=sy-myCurveWidth;break;
        }
        ctx.lineTo(x, y); // 绘制直线
        var obj1 ={'x':x,'y':y};
        pointArray.push(obj1);

        switch(position){
            case 'left':x=sx + myCurveWidth;y=ey;break;
            case 'right':x=sx - myCurveWidth;y=ey;break;
            case 'bottom':x=ex;y=sy-myCurveWidth;break;
        }
        ctx.lineTo(x, y); // 绘制直线
        var obj2 ={'x':x,'y':y};
        pointArray.push(obj2);

        ctx.lineTo(ex, ey); // 绘制直线
        var obj3 ={'x':ex,'y':ey};
        pointArray.push(obj3);

        ctx.lineWidth = 2; // 设置线宽
        ctx.strokeStyle =color; // 设置线的颜色
        ctx.stroke(); // 进行线的着色，这时整条线才变得可见

        if(sending == 0){
            //画流光
            flowingLight(pointArray.reverse(),time);
        }
        // var img = imageCache[lineSrc];
        // var w = img.width;
        // var h = img.height;
        // var imgX = 0;
        // var imgY = 0;
        // if(position == 'left'){
        //     var n = 0;
        //     if(total % 2 != 0){n=20;}
        //     imgX = ex - w - (Math.abs(middelNum-index)*20)-n;
        //     imgY = ey-h/2;
        //     ctx.drawImage(img,imgX,imgY);
        // }else if(position == 'right'){
        //     var n = 0;
        //     if(total % 2 != 0){n=20;}
        //     imgX = ex + (Math.abs(middelNum-index)*20)+n;
        //     imgY = ey-h/2;
        //     ctx.drawImage(img,imgX,imgY);
        // }else if(position == 'bottom'){
        //     ctx.save();                         //保存画布状态
        //     ctx.translate(ex,ey);               //设置画布原点为线段终点
        //     ctx.rotate(-Math.PI/2);             //画布逆时针转动90°
        //     ctx.drawImage(img,-40,-(h/2));      //生成图片
        //     ctx.restore();                      //还原画布状态
        // }
    };

    var flowingTime = 0;        //开始流光时间
    /**
     * 流光
     * @param {Array} pointArray 点的路径
     * @param {number} time 流光时间，单位毫秒
     */
    var flowingLight = function(pointArray,time){
        var size = pointArray.length;
        if(size > 1){       //一点不成线，至少有两个点
            var lineLength = 0;  //路径长度
            var lengthArray = [];
            for(var i = 0;i<size-1;i++){
                var sObj = pointArray[i];
                var eObj = pointArray[i+1];
                var x = sObj.x - eObj.x;
                var y = sObj.y - eObj.y;
                var z = Math.sqrt(x*x + y*y);   //获取两点之间距离
                lengthArray.push(z);
                lineLength += z;
            }

            var lengthInMs = lineLength / time;     //每毫秒前进长度
            var now = new Date().getTime();
            var flowTime = 0;
            if(flowingTime == 0){
                flowingTime = now;
            }else{
                flowTime = now - flowingTime;
                if(flowTime >= time){
                    flowingTime = 0;
                }
            }
            //移动距离
            var moveLength = lengthInMs * flowTime;
            //递归画点
			ctx.save();
            mathFlowingLight(0,pointArray,lengthArray,moveLength,'rgb(76, 255,  1)',3);
            mathFlowingLight(0,pointArray,lengthArray,moveLength-2,'rgb(76, 255,  0.8)',2.5);
            mathFlowingLight(0,pointArray,lengthArray,moveLength-4,'rgb(76, 255,  0.7)',2);
            mathFlowingLight(0,pointArray,lengthArray,moveLength-6,'rgb(76, 255,  0.6)',1.5);
            mathFlowingLight(0,pointArray,lengthArray,moveLength-8,'rgb(76, 255,  0.4)',1);
            
			ctx.restore();
        }
    };

    /**
     * 递归画点
     * @param {number} index 第几个线段
     * @param {Array} pointArray 点的集合
     * @param {Array} lengthArray 线段长度集合
     * @param {number} moveLength 在当前线段上移动的长度
     * @param {string} color 点的颜色
     * @param {number} radius 点的半径大小
     */
    var mathFlowingLight = function(index,pointArray,lengthArray,moveLength,color,radius){
        if(index >= lengthArray.length){
            return;
        }
        if(moveLength > lengthArray[index]){
            moveLength = moveLength - lengthArray[index];
            index ++;
            mathFlowingLight(index,pointArray,lengthArray,moveLength,color,radius);
        }else{
            //画
            var sPoint = pointArray[index];
            var ePoint = pointArray[index+1];
            var x = (ePoint.x - sPoint.x)*moveLength/lengthArray[index] + sPoint.x;
            var y = (ePoint.y - sPoint.y)*moveLength/lengthArray[index] + sPoint.y;
			
            ctx.beginPath(); // 开始路径绘制
            ctx.fillStyle = color;
            ctx.arc(x,y,radius,0,2*Math.PI);
            ctx.fill();
        }
    };
})(jQuery);