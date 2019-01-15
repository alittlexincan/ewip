$(function(){
	
	//严格模式
	"use strict";
	/**
	 * 所有统计图Option函数
	 * @author lxv
	 * @return option
	 */
	var searchParam = ""
	,lefthidden1 = "" 	//左侧上方模块是否关闭标识
	,lefthidden2 = ""	//左侧下方模块是否关闭标识
	,righthidden1= ""	//右侧上方模块是否关闭标识
	,righthidden2= ""	//右侧下方模块是否关闭标识
	,bottomhidden1= ""	//下左模块是否关闭标识
	,bottomhidden2= ""	//下中模块是否关闭标识
	,bottomhidden3= ""	//下右模块是否关闭标识
	/**
	 * 预警统计信息加载icon存放处
	 */
	,warnContent = $("<div class='index-content warn-message'></div>")
	/**
	 * @param param ECHART报表通用配置修改、添加参数
	 * ECHART报表通用配置
	 */
	,Settings = function(param){
		var setting = {
			title: {
		        text: param.count,
		        x: 'center',
		        y: 'center',
		        textStyle : {
		            color : 'white',
		            fontSize : 10
		        }
		    },
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)",
	        	position: function (pos, params, dom, rect, size) {
        	      // 鼠标在左侧时 tooltip 显示到右侧，鼠标在右侧时 tooltip 显示到左侧。
        	      var obj = {top: 60};
        	      obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 5;
        	      return obj;
	        	 }
		    },
		    calculable:true,
		    grid: {
	             borderWidth: 0,
	             top: 20,
	             bottom: 55,
	             textStyle: {
	                 color: "#fff"
	             }
		    },
		    series: [{
	            name:'预警信息',
	            type:'pie',
	            radius: ['100%', '50%'],
	            avoidLabelOverlap: false,
	            label: {
	                normal: {
	                    show: false,
	                    position: 'center'
	                },
	            },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data: param.data
	        }]
		};
		//2：柱状图折线图
		if(param.type == 2){
			setting.xAxis= [{
	             type: "category",
	             axisLine: {
	                 lineStyle: {
	                     color: '#e1eaef'
	                 }
	             },
	             axisLabel: {
	                 interval: 0,
	                 rotate:-50,
	                 textStyle: {
	                	 fontSize : 9,
	                	 color: '#fff'
		             }
	             },
	             data: null,
	         }],
	         setting.yAxis = [{
	             type: "value",
	             splitLine: {
	                 show: false
	             },
	             axisLine: {
	                 lineStyle: {
	                     color: '#dae2e7'
	                 }
	             },
	             axisLabel: {
	                 interval: 0,
	             }
	        }];
			setting.dataZoom = [{
                show: true,
                height: 15,
                handleColor: '#ddd',
                xAxisIndex: [0],
                bottom: 0,
                start: 0,
                end: 100,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '100%',
                handleStyle: {color: "#d3dee5"},
                textStyle: {color: "#fff"},
                borderColor: "#12315b"
        	},{
            type: "inside",
            show: true,
            height: 15,
            start: 1,
            end: 35
        	}];
		}
		return setting;
	}
	/**
	 * 预警拼接数量接收值为数组
	 * @property alreadyPublishWarnTitle
	 * @property alreadyPublishWarnCycle
	 * @author lxv
	 * @return 数组类型 [alreadyPublishWarnTitle,alreadyPublishWarnCycle]
	 */
	,[alreadyPublishWarnTitle,alreadyPublishWarnCycle] = function(){
		// 预警标题
		return [$("<div class='top-left-icon'>发布机构统计</div>")
		// 预警内容
		,$("<div class='lf-1 char-item  column'>"+
				"<div class='contentxd'>"+
					"<div class='con-top'><span class='titlelft' id='yifabu'>发布机构统计</span></div>"+
					"<div class='con-middle'>"+
                		"<div id='organizationSts' style='width:100%;height:100%;'></div>" +
						/*"<div class='contentpd'>"+
							"<div class='release-title'>"+
								"<div class='title-left'>共发布预警数 :</div>"+
								"<div class='title-right'></div>"+//共发布预警数<span></span>
							"</div>"+
							"<div class='chart-content mdht yfb'>"+
								"<ul></ul>" +//圈状统计图以及下方灾种类型名称显示<li><li>
							"</div>"+
						"</div>"+*/
					"</div>"+
					"<div class='con-foot'></div>"+ 
				"</div>"+ 
			"</div>")];
	}()
	/**
	 * 24小时预警信息列表 
	 * @property warnMessageTitle
	 * @property warnMessage
	 * @author lxv
	 * @return 数组类型 [warnMessageTitle,warnMessage]
	 */
	,[warnMessageTitle,warnMessage] = function(){
		//有效预警标题	
		return  [$("<div class='left-icon'>预报列表</div>")
		//有效预警内容
		,$("<div class='lf-2 char-item column'>" +
				"<div class='contentxd '>" +
					"<div class='con-top'><span class='titlelft'>预报列表</span></div>" +
					"<div class='con-middle'>" +
						"<div class='contentpd '  >" +
							"<div class='warnList '>" +
							/*	"<dl>" +
									"<dt ><img  src='/earlywarningProcess/images/logo.gif'></dt>" +
									"<dd >预警名称：</dd>" +
									"<dd >发布时间：</dd>" +
								"</dl>" +
								"<dl></dl>" + */
							"</div>" +
						"</div>" +
					"</div>" +
					"<div class='con-foot'></div>" +
				"</div>" +
			"</div>")];
	}()
	
	/**
	 * 预警级别统计
	 * @property warnLevelTotalTitle
	 * @property warnLevelTotal
	 * @author lxv
	 * @return 数组类型 [warnLevelTotalTitle,warnLevelTotal]
	 */
	,[warnLevelTotalTitle,warnLevelTotal] = function(){
		//预警级别统计标题	
		return [$( "<div class='bottom-left-icon '>预报类型统计</div>")
		   //预警级别统计内容
	       ,$("<div class='bottomlft bottomhd c-1 char-item column'>" +
	    		   "<div class='btxd'>" +
	    		   		"<div class='bottomtop down tab'><span>预报类型统计</span></div>" +
	    		   		"<div class='bottommidde'>" +
	    		   			"<div class='pb' id='drawWarnLevelTotal' style='width:100%;height:100%;'></div>" +
	    		   		"</div>" +
	    		   "</div>" +
	         "</div>")];
	}()  
	
	/**
	 * 发布地区统计 
	 * @property publishAreaTotalTitle
	 * @property publishAreaTotal
	 * @author lxv
	 * @return 数组类型 [publishAreaTotalTitle,publishAreaTotal]
	 */
	,[publishAreaTotalTitle,publishAreaTotal] = function(){
		//发布地区统计标题	
        return [$("<div class='top-right-icon'>影响地区统计</div>")
 		   //发布地区统计内容
           ,$("<div class='rt-1 char-item column'>" +
        		   	"<div class='contentxd'>" +
        		   		"<div class='con-top-right'><span class='titleright'>影响地区统计</span></div>" +
        		   		"<div class='con-middle'>" +
        		   			"<div id='drawPublishAreaTotal' style='width:100%;height:100%;'></div>" +
        		   		"</div>" +
        		   		"<div class='con-foot'></div>" + 
        		   	"</div>" +
           	"</div>")];
	}()
	
	/**
	 * 发布渠道统计
	 * @property publishChannelTotalTitle
	 * @property publishChannelTotal
	 * @author lxv
	 * @return 数组类型 [publishChannelTotalTitle,publishChannelTotal]
	 */
	,[publishChannelTotalTitle,publishChannelTotal] = function(){
		//事件列表标题	
		return [$( "<div class='right-icon'>发布渠道统计</div>")
	        //事件列表内容
	        ,$("<div class='rt-2 char-item column'>" +
		        "<div class='contentxd'>" +
		            "<div class='con-top-right'><span class='titleright'>发布渠道统计</span></div>" +
		            "<div class='con-middle'>" +
		            	"<div id='drawPublishChannelTotal' style='width:100%;height:100%;'></div>" +
		            "</div>" +
		            "<div class='con-foot'></div>" + 
	            "</div>" +
	        "</div>")];
	}()
	
	/**
	 * 接收受众统计
	 * @property personHotMapTitle
	 * @property personHotMap
	 * @author lxv
	 * @return 数组类型 [receivePersonHotMapTitle,receivePersonHotMap]
	 */
	,[receivePersonHotMapTitle,receivePersonHotMap] = function(){
		//接收受众统计标题	
		return [$("<div class='bottom-right-icon'>接收用户展示</div>")
		    //接收受众统计内容
		    ,$("<div class='bottomrt bottomhd char-item column'>" +
			        "<div class='btxd'>" +
			            "<div class='bottomtop down tab'><span>接收用户展示</span></div>" +
			            "<div class='bottommidde'>" +
				  /*          "<div class='personMap'>" +
				            	"<ul class='respons'>责任人</ul>" +
				            	"<ul class='audience'>受众</ul>" +
			            	"</div>" +*/
			                "<div class='contentpd-2'  id='drawPersonHotMap' style='width:100%;float:left;height:100%;'></div>" +
			            "</div>" +
			        "</div>" +
			   "</div>")];
	}()
	
	/**
	 * 各市预警统计TOP10
	 * @property warnCountTitle
	 * @property warnCount
	 * @author lxv
	 * @return 数组类型 [warnCountTitle,warnCount]
	 */
	,[warnCountTitle,warnCount] = function(){
		//预警统计标题	
		return [$("<div class='bottom-center-icon '七天预报发布趋势</div>" )
		//预警统计内容
		,$("<div class='bottommd bottomhd c-2 char-item column'>" +
		        "<div class='btxd'>" +
		            "<div class='bottomtop down tab'>" +
		                "<div class='title'><span>七天预报发布趋势</span></div>" +
		            "</div>" +
		            "<div id='drawWarnCountLine' style='width: 100%; height: 100%;'></div>" +
		        "</div>" +
		   "</div>")];
	}()
	/**
	 * 中间详细信息 
	 * @author lxv
	 * @return warnCenter
	 */
	,warnCenter = function(){
		return $("<div class='top-center-left-icon ' >" +
					"<div class='top-center-left' >" +
						"<div class='top'>" +
							"<div class='tbtop'>" +
			                    "<ul>" +
			                        "<li class='on'>预警详情</li>" +
			                    "</ul>" +
			                "</div>" +
			                "<div class='content' style=''>" +
			                	"<div class='relatedWarn-content' ></div>" +
		                    "</div>" +
	                    "</div>" +
	                    "<div class='bottom' >" +
							"<div class='tbtop'>" +
			                    "<ul>" +
			                        "<li class='on'>影响分析</li>" +
			                    "</ul>" +
		                    "</div>" +
		                    "<div class='content' style=''>" +
			                    "<div class='influence-content' ></div>" +
		                    "</div>" +
	                    "</div>" +
					"</div>" +
					"<div class='cls lft'>" +
	                	"<span></span>" +
	                "</div>" +
                "</div>" + 
                "<div class='top-center-right-icon ' >" +
					"<div class='top-center-right'>" +
						"<div class='top' style=''>" +
							"<div class='tbtop'>" +
			                    "<ul>" +
			                        "<li class='on'>发布流程</li>" +
			                    "</ul>" +
		                    "</div>" +
		                    "<div class='content' style=''>" +
			                    "<div class='process-content' style=''></div>" +
		                    "</div>" +
	                    "</div>" +
	                    "<div class='bottom' style=''>" +
							"<div class='tbtop'>" +
			                    "<ul>" +
			                        "<li class='on'>渠道反馈</li>" +
			                    "</ul>" +
		                    "</div>" +
		                    "<div class='content' style=''>" +
			                    "<div class='channelBack-content' style=''></div>" +
		                    "</div>" +
	                    "</div>" +
					"</div>" +
					"<div class='cls rt'>" +
	            		"<span></span>" +
	            	"</div>" +
				"</div>" );
	}()
	/**
	 * 顶部预警详情 
	 * @param NULL
	 * @property topwarnDetail
	 * @author lxv
	 * @return 对象
	 */	
	,topwarnDetail = function(){
		return $("<div class='details-md-warn'></div>" );
	}()
	,warnPersonlist = function(){
		return $("<div class='warnPersonlist-detail' style='display: none;'></div>" );
	}()
	/**
	 * 渠道反馈以及影响分析详情
	 */
	,channelBackDetail = function(){
		return $("<div class='channelBackDetail '>" +
					"<div class='setting-list-title'>" +
					"<span><i class='fa fa-list-alt'></i>详细列表</span>" +
					"<span><i class='fa fa-close' style='float: right;margin: 4px;'></i></span>" +
					"</div>" +
					"<table class='channelBackTable'>" +
					"<thead>" +
						"<tr>" +
							"<td>名称</td>" +
							"<td>号码 </td>" +
						"</tr>" +
					"</thead>" +
					"<tbody></tbody>" +
					"</table>" +
				"</div> ");
	}()
	/**
	 * @param params 界面需要拼接的浮层
	 * 初始化预警信息拼接页面
	 */
	,init =function(...params){
		for(var i = 0; i<params.length; i++){
			//拼接预警信息统计HTML 
			$(warnContent).append(params[i]);
		}
		//拼接Html
		$("body").children("script").eq(0).before($(warnContent));
	};  
	
	/**
	 * 初始化加载所有模块
	 */
	init(
		alreadyPublishWarnTitle,	// 已发布预警标题
		alreadyPublishWarnCycle,	// 已发布预警内容
		warnMessageTitle,			// 预警信息标题
		warnMessage,				// 预警信息内容
		warnLevelTotalTitle,		// 预警级别统计标题
		warnLevelTotal,				// 预警级别统计
		publishChannelTotalTitle,	// 发布渠道统计标题
		publishChannelTotal,		// 发布渠道统计
		publishAreaTotalTitle,		// 发布地区统计标题
		publishAreaTotal, 			// 发布地区统计
		warnCountTitle,				// 预警统计标题（24小时各市预警总数统计）标题	
		warnCount,					// 预警统计内容（24小时各市预警总数统计）	
		receivePersonHotMapTitle,	// 接收受众热力图统计标题
		receivePersonHotMap,		// 接收受众热力图统计
		topwarnDetail,				// 顶部预警详情
		warnPersonlist,				// 受众详细信息列表
		warnCenter,					// 预警中心内容
		channelBackDetail,			// 渠道反馈详情
	);	
	
	//渠道反馈以及影响分析详情关闭
	$(".channelBackDetail > .setting-list-title span:last-child").on("click", function(){
		$(".channelBackDetail").hide();
	});
	
    // 拖动设置中心
    $(".channelBackDetail > .setting-list-title").bind("mousedown",function(event){
        Move.mouseDown($(".channelBackDetail"), event);
        return false;
    });

	/**
	 * 显示弹出层绑定事件
	 */
	$(".top-left-icon ,.left-icon ,.top-right-icon ,.right-icon, " +
			".bottom-left-icon ,.bottom-center-icon ,.bottom-right-icon").on("click",function(){
		//左上 已发布信息（显示）
		if($(this).hasClass("top-left-icon")){
			$(".top-left-icon").animate({"left":"-4rem"}, "slow");
			$(".lf-1").fadeIn("slow");
			$(".lf-1").animate({"left":"-3px"}, "slow");
		//左上 待发布信息（显示）	
		}else if($(this).hasClass("left-icon")){
			$(".left-icon").animate({"left":"-4rem"}, "slow"); 
			$(".lf-2").fadeIn("slow");
			$(".lf-2").animate({"left":"-3px"}, "slow");
		//右上 灾情上报（显示）	
		}else if($(this).hasClass("top-right-icon")){
			$(".top-right-icon").animate({"right":"-4rem"}, "slow"); 
			$(".rt-1").fadeIn("slow");
			$(".rt-1").animate({"right":"-3px"}, "slow");
		//右上 预警统计（显示）		
		}else if($(this).hasClass("right-icon")){
			$(".right-icon").animate({"right":"-4rem"}, "slow"); 
			$(".rt-2").fadeIn("slow");
			$(".rt-2").animate({"right":"-3px"}, "slow");
		//下左 预警签发（显示）
		}else if($(this).hasClass("bottom-left-icon")){
			$(".bottom-left-icon").animate({"bottom":"-3rem"}, "slow");
			$(".bottomlft").fadeIn("slow");
			$(".bottomlft").animate({"bottom":"0"}, "slow");
		//下中 预警处理（显示）		
		}else if($(this).hasClass("bottom-center-icon")){
			$(".bottom-center-icon").animate({"bottom":"-3rem"}, "slow");
			$(".bottommd").fadeIn("slow");
			$(".bottommd").animate({"bottom":"0"}, "slow");
		//下右 预警审核（显示）		
		}else if($(this).hasClass("bottom-right-icon")){
			$(".bottom-right-icon").animate({"bottom":"-3rem"}, "slow");
			$(".bottomrt").fadeIn("slow");
			$(".bottomrt").animate({"bottom":"0"}, "slow");
		}
	});
	
	
	/**
	 * 24小时预警发布总数显示
	 */	
	var alreayWarnCount = function(data){
		var allCount=data.count,len=allCount.toString().length,html = "";
		if(len >=2){
			var arry = allCount.toString().split('');
			for(var i=0;i<arry.length;i++){
				html+="<span>"+arry[i]+"</span>";
			};
		}else{
			html+="<span>"+allCount+"</span>";
		}
		$(".release-title > div:nth-child(2)").empty().append(html);
	}
	
	/**
	 * 获取已发布的渠道
	 */
	,alreadyWarnNum=function(searchParam){
		$.ajax({
			async:true,
			type: "POST",
			url: "publishStatistics/alreadyPub",
			data: searchParam,
			dataType: "json",
			success: function(data){
				alreayWarnCount(data);//已发布预警拼接数量
				var option =data.option ;
				$(".contentxd .con-middle .contentpd .chart-content ul ").empty()
				for(var i =0; i<option.length; i++){
					option[i].type=1;
					$(".contentxd .con-middle .contentpd .chart-content ul ").append("<li>" +
							"<div class='chart-v' id='"+option[i].id+"'></div>" +
							"<div class='chart-text'>"+option[i].typeName+"</div>" +
						"</li>");
					//追加统计个数
					echarts.init(document.getElementById(option[i].id)).setOption(Settings(option[i]));
				}
			}
		});
	}
	
	/**
	 * 绘制：预报类型统计图
	 */
	,drawWarnLevelTotal = function(param){
		$.ajax({
			   async:true,
		       type: "POST",
		       url: 'publishStatistics/drawWarnLevelTotal',
		       data: param,
		       dataType: "json",
		       success: function(data){
	    	   var arry=data.arry;
	    	   var num=0;
	    	   for(var i=0;i<arry.length;i++){
	    		   num=num+Number(arry[i].value);
	    	   }
				var option = {
					title: {
				        text: num,
				        x: '61%',
				        y: '44%',
				        textStyle : {
				            color : 'white',
				            fontSize : 20
				        }
				    },
				    tooltip: {
				        trigger: 'item',
				        formatter: "{a} <br/>{b}: {c} ({d}%)"
				    },
				    legend: {
				        orient: 'vertical',
				        x: 'left',
				        left:'5%',
				        textStyle:{
				            color:'white'
				        },
				        data: data.name
				    },
				    series: [{
			            name:'预警级别统计',
			            type:'pie',
			            center:['65%','50%'],//位置
			            radius: ['60%', '80%'],//设置为空心
			            avoidLabelOverlap: false,
			            label: {
			                normal: {
			                    show: false,
			                    position: 'center'
			                }
			            },
			            labelLine: {
			                normal: {
			                    show: false
			                }
			            },
			            data:data.arry
			        	}]
					};
				echarts.init(document.getElementById("drawWarnLevelTotal")).setOption(option);
	        }
		});
	}

	/**
	 * 绘制：机构统计
	 */
	,organizationSts = function(param){
		$.ajax({
			async:true,
			type: "POST",
			url: 'publishStatistics/alreadyPub',
			data: param,
			dataType: "json",
			success: function(data){
				// if(data.area.length>0 && data.blue.length>0 && data.orange.length>0 && data.red.length>0 && data.yellow.length>0){
                var option = {
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    dataZoom : [{
                        show: true,
                        height: 15,
                        xAxisIndex: [0],
                        bottom: 0,
                        start: 0,
                        end: 100,
                        handleSize: '100%',
                        handleStyle: {
                            color: "#d3dee5",
                        },
                        textStyle: {
                            color: "#fff"
                        },
                        borderColor: "#12315b"
                    },
                        {
                            type: "inside",
                            show: true,
                            height: 15,
                            start: 1,
                            end: 35
                        }],
                    grid: {
                        left: '5%',
                        right: '8%',
                        bottom: '15%',
                        top:'5%',
                        containLabel: true
                    },
//					    calculable : true,
                    xAxis :  [{
                        type: "category",
                        axisLine: {
                            lineStyle: {
                                color: '#e1eaef'
                            }
                        },
                        axisLabel: {
                            interval: 0,
                        },
                        data: data.area,
                    }],
                    yAxis : [{
                        type : 'value',
                        // y轴的字体样式
                        axisLabel: {
                            interval: 0,
                            show: true,
                            textStyle: {
                                color: '#fff'
                            }
                        },
                        // y轴的颜色和宽度
                        axisLine:{
                            lineStyle:{
                                color:'#fff',
                                width:1,                  //这里是坐标轴的宽度
                            }
                        },
                        splitLine:{
                            show:false
                        }
                    }],
                    series : [{
                        name:'短期预报',
                        type:'bar',
                        stack: '总量',
                        barWidth : 20,//柱图宽度
                        data: data.red
                    },{
                        name:'中期预报',
                        type:'bar',
                        stack: '总量',
                        barWidth : 20,//柱图宽度
                        data: data.orange
                    },{
                        name:'长期预报',
                        type:'bar',
                        stack: '总量',
                        barWidth : 20,//柱图宽度
                        data:data.yellow
                    },{
                        name:'气象专题专报',
                        type:'bar',
                        stack: '总量',
                        barWidth : 20,//柱图宽度
                        data:data.blue
                    },{
                        name:'重大气象专题专报',
                        type:'bar',
                        stack: '总量',
                        barWidth : 20,//柱图宽度
                        data:data.green
                    }]
                };
				echarts.init(document.getElementById('organizationSts')).setOption(option,true);
			}
		});
	}
	
	/**
	 * 绘制：发布地区统计图
	 */
	,drawPublishAreaTotal = function(param){

		$.ajax({
			   async:true,
		       type: "POST",
		       url: 'publishStatistics/drawPublishAreaTotal',
		       data: param,
		       dataType: "json",
		       success: function(data){
		    	   	if(data.area.length>0 && data.blue.length>0 && data.orange.length>0 && data.red.length>0 && data.yellow.length>0){
	   				var option = {
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        }
					    },
					    dataZoom : [{
			                 show: true,
			                 height: 15,
			                 xAxisIndex: [0],
			                 bottom: 0,
			                 start: 0,
			                 end: 100,
			                 handleSize: '100%',
			                 handleStyle: {
			                     color: "#d3dee5",
			                 },
			                 textStyle: {
			                     color: "#fff"
			                 },
			                 borderColor: "#12315b"
			             }, 
			             {
			                 type: "inside",
			                 show: true,
			                 height: 15,
			                 start: 1,
			                 end: 35
			             }],
					    grid: {
			                left: '5%',
			                right: '8%',
			                bottom: '15%',
			                top:'5%',
			                containLabel: true
			            },
//					    calculable : true,
					    xAxis :  [{
				             type: "category",
				             axisLine: {
				                 lineStyle: {
				                     color: '#e1eaef'
				                 }
				             },
				             axisLabel: {
				                 interval: 0,
				                 rotate:-35,
				             },
				             data: data.area,
				         }],
					    yAxis : [{
				           type : 'value',
				           // y轴的字体样式
			               axisLabel: {
			            	   interval: 0,
			                   show: true,
			                   textStyle: {
			                       color: '#fff'
			                   }
			               },
			               // y轴的颜色和宽度
			               axisLine:{
			            	   lineStyle:{
			            		   color:'#fff',
			            		   width:1,                  //这里是坐标轴的宽度
			            	   }
			               },
			               splitLine:{ 
		                      show:false 
			               }
					    }],
					    series : [{
				            name:'短期预报',
				            type:'bar',
				            stack: '总量',
				            barWidth : 20,//柱图宽度
				            data: data.red
				        },{
				            name:'中期预报',
				            type:'bar',
				            stack: '总量',
				            barWidth : 20,//柱图宽度
				            data: data.orange
				        },{
				            name:'长期预报',
				            type:'bar',
				            stack: '总量',
				            barWidth : 20,//柱图宽度
				            data:data.yellow
				        },{
				            name:'气象专题专报',
				            type:'bar',
				            stack: '总量',
				            barWidth : 20,//柱图宽度
				            data:data.blue
				        },{
                            name:'重大气象专题专报',
                            type:'bar',
                            stack: '总量',
                            barWidth : 20,//柱图宽度
                            data:data.green
                        }]
	   				};
	   				echarts.init(document.getElementById('drawPublishAreaTotal')).setOption(option,true);
	    	   	}else{
	    	   		$("#drawPublishAreaTotal").html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
	    	   	}
       		}
		});
	}
	
	/**
	 * 绘制：发布渠道统计图
	 */
	,drawPublishChannelTotal = function(param){
		$.ajax({
			   async:true,
		       type: "POST",
		       url: 'publishStatistics/drawPublishChannelTotal',
		       data: param,
		       dataType: "json",
		       success: function(data){
			    var genData = function () {
				    var legendData = data.name;
				    var seriesData = data.arry;
				    var selected = data.select;
				    return {
				        legendData: legendData,
				        seriesData: seriesData,
				        selected: selected
				    };
				};
				var arrys =data.arry;
				var num=0;
				for(var i=0;i<arrys.length;i++){
					num=num+Number(arrys[i].value);
				}
				if(num!=0){
					var data = genData();
					var option = {
					    tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					    },
					    legend: {
					        type: 'scroll',
					        orient: 'vertical',
					        right: 0,
					        top: 0,
					        bottom: 0,
					        padding: 0,
					        data: data.legendData,
					        selected: data.selected,
					        textStyle:{
					        	color:"white"
					        },
					        pageIconColor:"white",
					        pageIconInactiveColor:"gray",
					        pageIconSize:10,
					        pageButtonGap:3,
					        pageTextStyle:{
					            color:"white"
					        }
					    },
					    series : [{
				            name: '发布渠道统计',
				            type: 'pie',
				            radius : '75%',
				            center: ['40%', '55%'],
				            data: data.seriesData,
				            label:{
				            	show:false
				            }
				        }]
					};
				echarts.init(document.getElementById('drawPublishChannelTotal')).setOption(option,true);
	       		}else {
					$("#drawPublishChannelTotal").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
				};
			} 
		});
	}
	
	/**
	 * 绘制：发送用户
	 */
	,drawPersonHotMap = function(param,state){
		var XMData="";
		var state=state;
		$.ajax({
			   async:true,
		       type: "POST",
		       url: 'publishStatistics/personStas',
		       data: param,
		       dataType: "json",
		       success: function(data){
			   	console.log(data);
			   	if(JSON.stringify(data) != "{}"){
                    var option = {
                        color: ['#3398DB'],
                        tooltip : {
                            trigger: 'axis',
                            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis : [
                            {
                                type : 'category',
                                data : data.channel,
                                axisTick: {
                                    alignWithLabel: true
                                },
                                axisLine: {
                                    lineStyle: {
                                        color: '#e1eaef'
                                    }
                                }
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value',
                                axisLabel: {
                                    interval: 0,
                                    show: true,
                                    textStyle: {
                                        color: '#fff'
                                    }
                                },
                                // y轴的颜色和宽度
                                axisLine:{
                                    lineStyle:{
                                        color:'#fff',
                                        width:1                  //这里是坐标轴的宽度
                                    }
                                }
                            }
                        ],
                        series : [
                            {
                                name:'用户',
                                type:'bar',
                                barWidth: 30,
                                data:data.num
                            }
                        ]
                    };
                    echarts.init(document.getElementById("drawPersonHotMap")).setOption(option);
                }else{
	    			$(".personMap").empty();
	    			$("#drawPersonHotMap").html("<span class='noData' style='font-size: 16px;left: 41%;position: absolute;top: 38%;color: gray;'>暂无数据！</span>");
	    		}
       		}
		});
	}
	,drawHourWarn= function(param){
		console.log(param);
		$.ajax({
			async:true,
			type: "POST",
			url: 'publishStatistics/hourWarn',
			data: param,
			dataType: "json",
	       	success: function(data){
			data.type=2;
			var setting=Settings(data);
			setting.color=['#A0522D', '#00B2EE','#8FBC8F'];
			setting.tooltip={
					trigger: 'axis',
					axisPointer : {            // 坐标轴指示器，坐标轴触发有效
						type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					}
				};
			setting.toolbox= {
	        	 top :20,
	             show : true,
	             feature : {
	                 restore : {show: true},
	             },
	             iconStyle :{
	            	 normal: {
	            		 borderColor: "white"
	            	 },
	            	 emphasis: {
	            		 borderColor: "white"
	            	 }
	             }
	         };
		setting.xAxis= [{
             type: "category",
             axisLine: {
                 lineStyle: {
                     color: '#e1eaef'
                 }
             },
             axisLabel: {
                 interval: 0,
       /*          rotate:-20,*/
             },
             data: data.time,
         	}];
         	setting.grid= {
             borderWidth: 0,
             top: 20,
             bottom: 65,
             textStyle: {
                 color: "#fff"
             }
         	};
         	setting.legend= {
             x:'right',
             textStyle: {
                 color: '#d7e7e6',
             },
             data: ["短期预报", "中期预报", "长期预报", "气象专题专报","重大气象专题专报"]
         	};
         	setting.series= [{
	             name: "短期预报",
	             type: "bar",
	             stack: "总量",
	             barWidth : 25,
	             itemStyle: {
	                 normal: {
	                     label: {
	                         position: "top",
	                         formatter: function (p) {
	                             return p.value > 0 ? (p.value) : '';
	                         }
	                     }
	                 }
	             },
	             data: data.red 
             	},
             	{
				 name: "中期预报",
                 type: "bar",
                 stack: "总量",
                 barWidth : 25,
                 itemStyle: {
                     normal: {
                         barBorderRadius: 0,
                         label: {
                             position: "top",
                             formatter: function (p) {
                                 return p.value > 0 ? (p.value) : '';
                             }
                         }
                     }
                 },
                 data: data.orange 
             },
             {
                 name: "长期预报",
                 type: "bar",
                 stack: "总量",
                 barWidth : 25,
                 itemStyle: {
                     normal: {
                         barBorderRadius: 0,
                         label: {
                             position: "top",
                             formatter: function (p) {
                                 return p.value > 0 ? (p.value) : '';
                             }
                         }
                     }
                 },
                 data: data.yellow 
             },
             {
                 name: "气象专题专报",
                 type: "bar",
                 stack: "总量",
                 barWidth : 25,
                 itemStyle: {
                     normal: {
                         barBorderRadius: 0,
                         label: {
                             position: "top",
                             formatter: function (p) {
                                 return p.value > 0 ? (p.value) : '';
                             }
                         }
                     }
                 },
                 data: data.blue 
			 },     {
				 name: "重大气象专题专报",
				 type: "bar",
				 stack: "总量",
				 barWidth : 25,
				 itemStyle: {
					 normal: {
						 barBorderRadius: 0,
						 label: {
							 position: "top",
							 formatter: function (p) {
								 return p.value > 0 ? (p.value) : '';
							 }
						 }
					 }
				 },
				 data: data.green
			 },
             {
                 name: "总数",
                 type: "line",
                 stack: "总量",
                 symbolSize: 9,
                 symbol: 'circle',
                 itemStyle: {
                     normal: {
                         color: "rgba(0,173,163,1)",
                         barBorderRadius: 0,
                         label: {
                             show: true,
                             position: "top",
                             formatter: function (p) {
                                 return p.value > 0 ? (p.value) : '';
                             }
                         }
                     }
                 },
                 data:data.total 
             }];
			 setting.xAxis[0].data = data.area;
			 echarts.init(document.getElementById("drawWarnCountLine")).setOption(setting);
	 		}
       	})
	}
	/**
	 * 绘制：预警统计:24小时各市预警发布top10图
	 */
	,drawWarnCountLine = function(param){
		$.ajax({
			async:true,
			type: "POST",
			url: 'publishStatistics/hourWarn',
			data: param,
			dataType: "json",
	       	success: function(data){
	       		var time=data.time;
	       		var count=data.count;
	    	   	var option = {
	    	   			animationDuration: 3000,
	    	   			tooltip : {
					        trigger: 'axis',
					    },
    				    calculable : true,
    				    xAxis : [
    				        {
    				            type : 'category',
    				            boundaryGap : false,
    				            data : time,
			                    // x轴的字体样式
				                axisLabel: {        
				                    show: true,
				                    textStyle: {
				                        color: '#fff'
				                    }
				                },
				                // y轴的颜色和宽度
				                axisLine:{
					                lineStyle:{
					                    color:'#fff',
					                    width:1,                  //这里是坐标轴的宽度
					                }
				                }
    				        },
    				
    				    ],
    				    yAxis : [
    				        {
    				            type : 'value',
    				         // y轴的字体样式
    				            axisLabel: {
    				            	show: true,
				                   	textStyle: {
				                   		color: '#fff'
				                   	}
    				            },
				               // y轴的颜色和宽度
				               	axisLine:{
				               		lineStyle:{
				               			color:'#fff',
				               			width:1,                  //这里是坐标轴的宽度
				               		}
				               	},
    				        },
    				    ],
    				    series : [
    				        {
    				            name:'预警数',
    				            type:'line',
    				            stack: '发布数量',
    				            itemStyle : {
    								normal : {
    									lineStyle:{
    										color:'#00FF00'
    									}
    								}
    							},
    				            data:count
    				        },
    				    ]
    				};
	    	   	echarts.init(document.getElementById("drawWarnCountLine")).setOption(option);
	       	}
		});
	}
	/**
	 * 发布的预报列表
	 * @param data
	 */
	,warnList = function(param,page){
		var da = param;
    	da.page=page;
    	da.pageSize=3;
    	da.min=da.page*da.pageSize;
		da.max= da.pageSize;
		$.ajax({
			async:false ,
			type: "POST",
			url: 'publishStatistics/warnList',
			data: da,
			dataType: "json",
	        success: function(data){
	        	console.log(data);
           	 var page = data.page;
           	 var count = data.count;
           	 var list = data.list;
           	 var html= "";
           	 if(list.length>0){
           		 $.each(list,function(i,item){
           			var dataobj=JSON.stringify(item);
           			html += "<dl class='"+(i==0?"active":"")+"'  data-warn='"+dataobj+"'>";
					html += "	<dd style='width: 100%;'>预报类型: "+item.type+"</dd>";
		        	html += "	<dd style='width: 100%;'>发布时间: "+item.sendTime+"</dd>";
		        	html += "</dl>";
		        	html += "<dl></dl>";
           		 });
           		 html += "<ul>";
           		 html += "	<span id='nextPage' data-next-page='"+page+"' data-count='"+count+"'>下页</span>";
           		 html += "	<span id='topPage' data-top-page='"+page+"' data-count='"+count+"'>上页</span>";
           		 html += "	<span>第"+(Number(page)+1)+"页</span>";
           		 html += "	<span>总数"+count+"条</span>";
           		 html += "</ul>";
           		 $(".warnList").empty().append(html);
           		 $(".relatedWarn-content").empty().append(html);
           	 }else{
           		 html += "<div><p class='noData'>暂无数据！</p><div>";
           		 $(".warnList").empty().append(html);
           		 $(".relatedWarn-content").empty().append(html);
       	 		}
	        }
		});
		 // 点击（下页）按钮事件
	    $("#nextPage").bind("click",function(){
	    	var nextPage = $(this).data("nextPage");
	    	var count = $(this).data("count");
	    	if((nextPage+1)*3>=count){
	    		return false;
	    	}
	    	warnList(param,nextPage+1);
	    	$(".warnList dl:nth-child(1)").click();
	    });
	    // 点击（上页）按钮事件
	    $("#topPage").bind("click",function(){
	    	var topPage = $(this).data("topPage");
	    	var count = $(this).data("count");
	    	if(topPage==0){
	    		return false;
	    	}
	    	warnList(param,topPage-1);
	       	// 左侧预警列表(点击)事件
	    	$(".warnList dl:nth-child(1)").click();
	    });
	    
		// 左侧预警信息点击事件
/*	    $(".warnList dl:nth-child(2n-1)").bind("click",function(){
				$(".esriPopupWrapper").hide();//冒泡详情隐藏
				$(".outerPointer.left").hide();//冒泡箭头隐藏
				$(this).parent().children("dl.active").removeClass("active");//删除非选中标识
				$(this).addClass("active");//添加选中标识
				$(".warnPersonlist-detail").hide();//关闭预警受众列表
				// 获取当前预警详细信息
				var json = $(this).data("warn");
				//顶部详细预警信息显示
				$(".details-md-warn").show();
				// 点击预警列表数据时，展示顶部当前点击的预警信息
				topWarnInfo(json);
				//加载预警、渠道、受众、地区信息
				warnAllDetail(json);
			});*/
	};

    /**
     * 点击预警列表数据时，展示顶部当前点击的预警信息
     */
    var topWarnInfo = function(json){
    	var content="";
    	$.ajax({
			async:false ,
			type: "POST",
			url: 'publishStatistics/warnNameByTaskId',
			data: json,
			dataType: "json",
	        success: function(data){
	        		if(data.length>0){
	        			content=data[0].MODELCONTENT
	        		}
	        	}
	        })
    	var html="";
			html += "<div class='porltv'>";
	    	html +=     "<span class='cls fa-close' id='topWarnClose'></span>";
	    	html +=     "<div class='details-content'>";
	    	html +=         "<div class='d-c-list'>";
	    	html +=             "<div class='details-content-left'>预警名称：</div>";
	    	html +=             "<div class='details-content-right'>";
	    	if(json.typeName =="预警信号"){
	    		html +=         	"<div class='yjtitle'>发布"+json.disasterName+json.colorLevelName+"预警信号</div>";
	    	}else{
	    		html +=         	"<div class='yjtitle'>发布"+json.disasterName+json.colorLevelName+"预警</div>";	
	    	}
	    	html +=             "</div>";
	    	html +=             "<div class='details-content-left'>发布时间：</div>";
	    	html +=             "<div class='details-content-right'>";
	    	html +=                 "<div class='yjtime'>"+json.sendTime+"</div>";
	    	html +=             "</div>";
	    	html +=         "</div>";
	    	html +=         "<div class='d-c-list'>";
	    	html +=             "<div class='details-content-left'>预警内容：</div>";
	    	html +=             "<div class='details-content-right'>";
	    	html +=                 "<div class='yjcontent'>"+content+"</div>";
	    	html +=             "</div>";
	    	html +=         "</div>";
	    	html +=     "</div>";
	    	html += "</div>"; 
	    	html +=     "<div class='details-img'><img src='"+json.img+"' />";
	    	html +=     "</div>";
    	// 顶部预警内容展示：如果有显示的弹出层则先删除在追加当前点击预警信息
    	if($(".details-md-warn").length>0){
    		$(".details-md-warn").empty();
    		}
		$(".details-md-warn").prepend(html);
		// 点击顶部预警关闭按钮事件
        $(".details-md-warn > .porltv > span ").bind("click",function(){
        	$(".details-md-warn").empty();
        	$(".details-md-warn").fadeOut(400);
        });
    };
	/**
     * 查询预警所有信息
     */
    var warnAllDetail = function(json){
    	debugger;
    	var areaWarnList = [];
    	//首先删除之前存在的图层
    	var player=map.graphicsLayerIds;
    	if(player!=undefined){
			var delLayer = [];
			for(var i=0;i<player.length;i++){
				if(player[i].indexOf("warn_")!=-1 || player[i].indexOf("warnlogoBindMap_")!=-1){
					delLayer.push(player[i]);
				}
			}
			for(var j = 0;j<delLayer.length;j++){
				var mark=new Mark();
				mark.remove_CodePolygon(map, delLayer[j]);
			}
		}
    	$.ajax({
			async:false ,
			type: "POST",
			url: 'publishStatistics/warnAllDetail',
			data: json,
			dataType: "json",
	        success: function(data){
	        	var area=data.area.list;
	        	var channel=data.channel.list;
	        	var detail=json;
	        	for(var i=0;i<area.length;i++){
	    	   		areaWarnList.push({
	    	   			"taskId":json.taskId,
	    	   			"areaId":area[i].areaId,
	    	   			"areaName":area[i].areaName,
	    	   			"sendTaskId":"",
	    	   			"channelId" :json.releaseChannel,
	    	   			"sendTime" :json.sendTime,
	    	   			"numberLevelName" :json.numberLevelName, 
	        			"colorLevelName":json.colorLevelName,
	        			"warnName":json.disasterName,
	        			"img":json.img,
	        			"x":area[i].longitude,
	        			"y":area[i].latitude,
	        		});
	        		checkShowByCode(area[i].areaCode,area[i].areaId,area[i].colorName);
	        	}
//	        	addWarnPoint(areaWarnList);		//原来的
	        	addWarnLogoOnMap(areaWarnList,0);	//现有的
        	}
        })
    };
    
    //中间左侧隐藏点击事件
	$(".top-center-left-icon .cls.lft").on("click",function(){
		if(lefthidden1=="close" && lefthidden2=="close" ){
			if($(".top-center-left-icon .top-center-left").is(':hidden')){
				$(".top-center-left-icon .cls.lft").hide();
				$(".top-center-left-icon .top-center-left").animate({"left":"0"}, "slow");
				$(this).animate({"left":"15%"});
				$(".top-center-left-icon .top-center-left").fadeIn();
				$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(/map/images/event/sjx_lt.png)"});
			}else{
				$(".top-center-left-icon .cls.lft").hide();
				$(".top-center-left-icon .top-center-left").fadeOut();
				$(this).animate({"left":"0"});
				$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(/map/images/event/sjx_rt.png)"});
			}
		}else{
			if($(".top-center-left-icon .top-center-left").is(':hidden')){
				$(this).animate({"left":"38.7%"});
				$(".top-center-left-icon .top-center-left").animate({"left":"23.7%"}, "slow");
				$(".top-center-left-icon .top-center-left").fadeIn();
				$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(/map/images/event/sjx_lt.png)"});
			}else{
				if(lefthidden1=="close" && lefthidden2=="close" ){
					$(".top-center-left-icon .cls.lft").hide();
					$(this).animate({"left":"0px"});
					$(".top-center-left-icon .top-center-left").fadeOut();
					$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(/map/images/event/sjx_rt.png)"});
				}else{
					$(".top-center-left-icon .cls.lft").hide();
					$(this).animate({"left":"23.7%"});
					$(".top-center-left-icon .top-center-left").fadeOut();
					$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(/map/images/event/sjx_rt.png)"});
				}
			}
		}
	});
	
	//中间右侧隐藏点击事件
	$(".top-center-right-icon .cls.rt").on("click",function(){
		if( righthidden1=="close" && righthidden2=="close"){
			if($(".top-center-right-icon .top-center-right").is(':hidden')){
				$(".top-center-right-icon .cls.rt").hide();
				$(".top-center-right-icon .top-center-right").animate({"right":"0"}, "slow");
				$(this).animate({"right":"15%"});
				$(".top-center-right-icon .top-center-right").fadeIn();
				$(".top-center-right-icon .cls.rt > span").css({"background-image": "url(/map/images/event/sjx_rt.png)"});
			}else{
				$(".top-center-right-icon .cls.rt").hide();
				$(".top-center-right-icon .top-center-right").fadeOut();
				$(this).animate({"right":"0"});
				$(".top-center-right-icon .cls.rt > span").css({"background-image": "url(/map/images/event/sjx_lt.png)"});
			}
		}else{
			if($(".top-center-right-icon .top-center-right").is(':hidden')){
				$(this).animate({"right":"38.7%"});
				$(".top-center-right-icon .top-center-right").animate({"right":"23.7%"}, "slow");
				$(".top-center-right-icon .top-center-right").fadeIn();
				$(".top-center-right-icon .cls.rt > span").css({"background-image": "url(/map/images/event/sjx_rt.png)"});
			}else{
				if( righthidden1=="close" && righthidden2=="close"){
					$(".top-center-right-icon .cls.rt").hide();
					$(this).animate({"right":"0px"});
					$(".top-center-right-icon .top-center-right").fadeOut();
					$(".top-center-right-icon .top-center-right").animate({"right":"0%"}, "slow");
					$(".top-center-right-icon .cls.rt > span").css({"background-image": "url(/map/images/event/sjx_lt.png)"});
				}else{
					$(".top-center-right-icon .cls.rt").hide();
					$(this).animate({"right":"23.7%"});
					$(".top-center-right-icon .top-center-right").fadeOut();
					$(".top-center-right-icon .top-center-right").animate({"right":"0%"}, "slow");
					$(".top-center-right-icon .cls.rt > span").css({"background-image": "url(/map/images/event/sjx_lt.png)"});
				}
			}
		}
	});
    
	 //时间计算
    var dateTime = function(day){
    	var now = new Date();
    	var date = new Date(now.getTime() - day * 24 * 3600 * 1000);
    	var year = date.getFullYear();
    	var month = date.getMonth() + 1;
    	var day = date.getDate();
    	var hour = date.getHours();
    	var minute = date.getMinutes();
    	var second = date.getSeconds();
    	month = month < 10 ? "0"+month : month;
    	day = day < 10 ? "0" + day : day;
    	hour = hour < 10 ? "0" + hour : hour;
    	minute = minute < 10 ? "0" + minute : minute;
    	second = second < 10 ? "0" + second : second;
    	return year + '-' + month + '-' + day  + ' ' + hour + ':' + minute + ':' + second;
    };
    
    //导航栏模块图标显示
    var modularShow=function(){
    	var html="";
	    	html += "<li title='地区对应渠道统计' class='nav-item mr-1 nav-menu-7 model-1'><span>24</span></li>";
	    	html += "<li title='预报列表' class='nav-item mr-1 nav-menu-7 model-2'><i id=''class='fa fa-list' aria-hidden='true'></i></li>";
	    	html += "<li title='预警级别统计' class='nav-item mr-1 nav-menu-7 model-3'><i id=''class='fa fa-pie-chart' aria-hidden='true'></i></li>";
	    	html += "<li title='七天预报' class='nav-item mr-1 nav-menu-7 model-4'><i id=''class='fa fa-line-chart' aria-hidden='true'></i></li>";
	    	html += "<li title='接收用户展示' class='nav-item mr-1 nav-menu-7 model-5'><i id=''class='fa fa-user' aria-hidden='true'></i></li>";
	    	html += "<li title='发布渠道统计' class='nav-item mr-1 nav-menu-7 model-6'><i id=''class='fa fa-stack-overflow' aria-hidden='true'></i></li>";
	    	html += "<li title='影响地区统计' class='nav-item mr-1 nav-menu-7 model-7'><i id=''class='fa fa-bar-chart' aria-hidden='true'></i></li>";
	    	html += "<li title='全部展示' class='nav-item mr-1 nav-menu-6 model-8' style='display:none'><i id=''class='fa fa-arrows-alt' aria-hidden='true'></i></li>";
	    	html += "<li title='全部关闭' class='nav-item mr-1 nav-menu-6 model-9' ><i id=''class='fa fa-compress' aria-hidden='true'></i></li>";
    	$(".modular-show").empty().append(html);
    	
    	//显示。隐藏7大模快
        $(".modular-show li").on("click",function(){
        	//左上 （显示）
        	if($(this).hasClass("model-1")){
       			//打开关闭
    			$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
        		if(lefthidden1=="close"){
        			$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
        			$(".lf-1").fadeIn("slow");
        			$(".lf-1").animate({"left":"0px"});
        			lefthidden1="";
        			if(lefthidden2=="" && righthidden1=="" && righthidden2=="" && bottomhidden1=="" && bottomhidden2=="" && bottomhidden3==""){
        				$(".modular-show .model-8").hide();
        				$(".modular-show .model-9").show();
        			}
        		}else{
        			$(this).removeClass("nav-menu-active");
        			$(this).addClass("nav-menu-7");
        			$(".lf-1").animate({"left":"-38.5%"});
        			$(".lf-1").fadeOut("slow");
        			lefthidden1="close";
        			if(lefthidden2=="close" && righthidden1=="close" && righthidden2=="close" && bottomhidden1=="close" && bottomhidden2=="close" && bottomhidden3=="close"){
        				$(".modular-show .model-9").hide();
        				$(".modular-show .model-8").show();
        			}
        		}
    			$(".top-center-left-icon .top-center-left").hide();
    			$(".top-center-left-icon .cls.lft").hide();
    			$(".top-center-right-icon .top-center-right").hide();
    			$(".top-center-right-icon .cls.rt").hide();

    		//左中（显示）	
    		}else if($(this).hasClass("model-2")){
       			//打开关闭
    			$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
    			if(lefthidden2=="close"){
    				$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
    				$(".lf-2").fadeIn("slow");
        			$(".lf-2").animate({"left":"0px"});
        			lefthidden2="";
        			if(lefthidden1=="" && righthidden1=="" && righthidden2=="" && bottomhidden1=="" && bottomhidden2=="" && bottomhidden3==""){
        				$(".modular-show .model-8").hide();
        				$(".modular-show .model-9").show();
        			}
        		}else{
        			$(this).removeClass("nav-menu-active");
        			$(this).addClass("nav-menu-7");
        			$(".lf-2").animate({"left":"-38.5%"});
        			$(".lf-2").fadeOut("slow");
        			lefthidden2="close";
        			if(lefthidden1=="close" && righthidden1=="close" && righthidden2=="close" && bottomhidden1=="close" && bottomhidden2=="close" && bottomhidden3=="close"){
        				$(".modular-show .model-9").hide();
        				$(".modular-show .model-8").show();
        			}
        		}
    			$(".top-center-left-icon .top-center-left").hide();
    			$(".top-center-left-icon .cls.lft").hide();
    			$(".top-center-right-icon .top-center-right").hide();
    			$(".top-center-right-icon .cls.rt").hide();

    		//左下（显示）
    		}else if($(this).hasClass("model-3")){
       			//打开关闭
    			$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
    			if(bottomhidden1=="close"){
    				$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
    				$(".bottomlft").fadeIn("slow");
        			$(".bottomlft").animate({"bottom":"0"});
        			bottomhidden1="";
        			if(lefthidden1=="" && lefthidden2=="" && righthidden1=="" && righthidden2==""  && bottomhidden2=="" && bottomhidden3==""){
        				$(".modular-show .model-8").hide();
        				$(".modular-show .model-9").show();
        			}
        		}else{
        			$(this).removeClass("nav-menu-active");
        			$(this).addClass("nav-menu-7");
        			$(".bottomlft").animate({"bottom":"-32%"});
        			$(".bottomlft").fadeOut("slow");
        			bottomhidden1="close";
        			if(lefthidden1=="close" && lefthidden2=="close" && righthidden1=="close" && righthidden2=="close"  && bottomhidden2=="close" && bottomhidden3=="close"){
        				$(".modular-show .model-9").hide();
        				$(".modular-show .model-8").show();
        			}
        		}
    		//中（显示）		
    		}else if($(this).hasClass("model-4")){
       			//打开关闭
    			$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
    			if(bottomhidden2=="close"){
    				$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
    				$(".bottommd").fadeIn("slow");
        			$(".bottommd").animate({"bottom":"0"});
        			bottomhidden2="";
        			if(lefthidden1=="" && lefthidden2=="" && righthidden1=="" && righthidden2==""  && bottomhidden1=="" && bottomhidden3==""){
        				$(".modular-show .model-8").hide();
        				$(".modular-show .model-9").show();
        			}
        		}else{
        			$(this).removeClass("nav-menu-active");
        			$(this).addClass("nav-menu-7");
        			$(".bottommd").animate({"bottom":"-32%"});
        			$(".bottommd").fadeOut("slow");
        			bottomhidden2="close";
        			if(lefthidden1=="close" && lefthidden2=="close" && righthidden1=="close" && righthidden2=="close"  && bottomhidden1=="close" && bottomhidden3=="close"){
        				$(".modular-show .model-9").hide();
        				$(".modular-show .model-8").show();
        			}
        		}
    		//右下 （显示）		
    		}else if($(this).hasClass("model-5")){
       			//打开关闭
    			$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
    			if(bottomhidden3=="close"){
    				$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
    				$(".bottomrt").fadeIn("slow");
        			$(".bottomrt").animate({"bottom":"0"});
        			bottomhidden3="";
        			if(lefthidden1=="" && lefthidden2=="" && righthidden1=="" && righthidden2==""  && bottomhidden1=="" && bottomhidden2==""){
        				$(".modular-show .model-8").hide();
        				$(".modular-show .model-9").show();
        			}
        		}else{
        			$(this).removeClass("nav-menu-active");
        			$(this).addClass("nav-menu-7");
        			$(".bottomrt").animate({"bottom":"-32%"});
        			$(".bottomrt").fadeOut("slow");
        			bottomhidden3="close";
        			if(lefthidden1=="close" && lefthidden2=="close" && righthidden1=="close" && righthidden2=="close"  && bottomhidden1=="close" && bottomhidden2=="close"){
        				$(".modular-show .model-9").hide();
        				$(".modular-show .model-8").show();
        			}
        		}
			//右中 （显示）		
    		}else if($(this).hasClass("model-6")){
       			//打开关闭
    			$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
    			if(righthidden2=="close"){
    				$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
        			$(".rt-2").fadeIn("slow");
        			$(".rt-2").animate({"right":"0px"});
        			righthidden2="";
        			if(lefthidden1=="" && lefthidden2=="" && righthidden1=="" && bottomhidden1=="" && bottomhidden2=="" && bottomhidden3==""){
        				$(".modular-show .model-8").hide();
        				$(".modular-show .model-9").show();
        			}
        		}else{
        			$(this).removeClass("nav-menu-active");
        			$(this).addClass("nav-menu-7");
        			$(".rt-2").animate({"right":"-38.5%"});
        			$(".rt-2").fadeOut("slow");
        			righthidden2="close";
        			if(lefthidden1=="close" && lefthidden2=="close" && righthidden1=="close" && bottomhidden1=="close" && bottomhidden2=="close" && bottomhidden3=="close"){
        				$(".modular-show .model-9").hide();
        				$(".modular-show .model-8").show();
        			}
        		}
    			$(".top-center-left-icon .top-center-left").hide();
    			$(".top-center-left-icon .cls.lft").hide();
    			$(".top-center-right-icon .top-center-right").hide();
    			$(".top-center-right-icon .cls.rt").hide();

			//右上 （显示）	
    		}else if($(this).hasClass("model-7")){
       			//打开关闭
    			$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
    			if(righthidden1=="close"){
    				$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
        			$(".rt-1").fadeIn("slow");
        			$(".rt-1").animate({"right":"0px"});
        			righthidden1="";
        			if(lefthidden1=="" && lefthidden2=="" && righthidden2=="" && bottomhidden1=="" && bottomhidden2=="" && bottomhidden3==""){
        				$(".modular-show .model-8").hide();
        				$(".modular-show .model-9").show();
        			}
        		}else{
        			$(this).removeClass("nav-menu-active");
        			$(this).addClass("nav-menu-7");
        			$(".rt-1").animate({"right":"-38.5%"});
        			$(".rt-1").fadeOut("slow");
        			righthidden1="close";
        			if(lefthidden1=="close" && lefthidden2=="close" && righthidden2=="close" && bottomhidden1=="close" && bottomhidden2=="close" && bottomhidden3=="close"){
        				$(".modular-show .model-9").hide();
        				$(".modular-show .model-8").show();
        			}
        		}
    			$(".top-center-left-icon .top-center-left").hide();
    			$(".top-center-left-icon .cls.lft").hide();
    			$(".top-center-right-icon .top-center-right").hide();
    			$(".top-center-right-icon .cls.rt").hide();

    		}else if($(this).hasClass("model-8")){
    			$(".modular-show li").removeClass("nav-menu-active");
    			$(".modular-show li").addClass("nav-menu-6");
    			$(".lf-1,.lf-2,.rt-1,.rt-2,.bottomlft,.bottommd,.bottomrt").fadeIn("slow");
    			$(".lf-1").animate({"left":"-3px"});
    			$(".lf-2").animate({"left":"-3px"});
    			$(".bottomlft").animate({"bottom":"0"});
    			$(".bottommd").animate({"bottom":"0"});
    			$(".bottomrt").animate({"bottom":"0"});
    			$(".rt-2").animate({"right":"-3px"});
    			$(".rt-1").animate({"right":"-3px"});
    			lefthidden1="",lefthidden2="",righthidden2="",righthidden1="",bottomhidden1="",bottomhidden2="",bottomhidden3="";
    			$(".top-center-left-icon .cls.lft").hide();
    			$(".top-center-right-icon .cls.rt").hide();
    			$(".channelBackDetail").hide();
    			
    			$(".top-center-left-icon .cls.lft").animate({"left":"23.7%"});
				$(".top-center-left-icon .top-center-left").fadeOut();
				$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(/map/images/event/sjx_rt.png)"});
    			
    			$(".top-center-right-icon .cls.rt").animate({"right":"23.7%"});
				$(".top-center-right-icon .top-center-right").fadeOut();
				$(".top-center-right-icon .cls.rt > span").css({"background-image": "url(/map/images/event/sjx_lt.png)"});
				$(this).hide();
    			$(".modular-show .model-9").show();
    		}else if($(this).hasClass("model-9")){
    			$(".modular-show li").removeClass("nav-menu-active");
    			$(".modular-show li").addClass("nav-menu-6");
    			$(".channelBackDetail").hide();
    			$(".lf-1").animate({"left":"-38.5%"});
				$(".lf-1").fadeOut("slow");
				$(".lf-2").animate({"left":"-38.5%"});
				$(".lf-2").fadeOut("slow");
				$(".rt-1").animate({"right":"-38.5%"});
				$(".rt-1").fadeOut("slow");
				$(".rt-2").animate({"right":"-38.5%"});
				$(".rt-2").fadeOut("slow");
				$(".bottomlft").animate({"bottom":"-32%"});
				$(".bottomlft").fadeOut("slow");
				$(".bottommd").animate({"bottom":"-32%"});
				$(".bottommd").fadeOut("slow");
				$(".bottomrt").animate({"bottom":"-32%"});
				$(".bottomrt").fadeOut("slow");
    			lefthidden1="close",lefthidden2="close",righthidden1="close",righthidden2="close",bottomhidden1="close",bottomhidden2="close",bottomhidden3="close";
    			$(".top-center-left-icon .top-center-left").fadeOut();
    			$(".top-center-right-icon .top-center-right").fadeOut();
    			$(".top-center-left-icon .cls.lft").hide();
    			$(".top-center-right-icon .cls.rt").hide();
    			$(".top-center-left-icon .cls.lft").animate({"left":"0"});
    			$(".top-center-right-icon .cls.rt").animate({"right":"0"});
    			
    			$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(/map/images/event/sjx_rt.png)"});
    			$(".top-center-right-icon .cls.rt > span").css({"background-image": "url(/map/images/event/sjx_lt.png)"});
    			$(this).hide();
				$(".modular-show .model-8").show();
    		}
        })
    }
	
    /**
     * 预警查询参数封装
     */
    var param = function(){
    	var p = {
    		orgId: "",
            empAreaId: Globel.empAreaId,
			startTime:"2019-01-07 08:28:21",
			endTime:"2019-01-11 11:28:21",
            // startTime:dateTime(7),
			// endTime:dateTime(0),
			disasterId:"",
			disasterName:"",
			warnColorNum: "",
			warnLevelNum:"",
			channelId: "",
			areaId : "",
			areaName : ""
    	};
    	return p;
    };
    
	/**
	 * 数据加载
	 */
	setTimeout(function(){
		
		modularShow();							// 右上角菜单（切换隐藏显示）
        organizationSts(param());			    // 绘制：机构统计图
        warnList(param(),0);					// 发布预报列表
        drawWarnLevelTotal(param());			// 绘制：预报类型统计
        drawHourWarn(param());					// 绘制：七天预报统计
        drawPublishAreaTotal(param());			// 绘制：影响地区统计图
        drawPublishChannelTotal(param());		// 绘制：发布渠道统计图
        drawPersonHotMap(param(),1);			// 绘制：接收用户展示图

        // alreadyWarnNum(param());				// 24小时有效预警
//		drawWarnCountLine(param());				// 绘制：24小时发布渠道趋势
	},500);
	
});	