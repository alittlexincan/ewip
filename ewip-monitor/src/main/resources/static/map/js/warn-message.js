$(function(){
	
	//严格模式
	"use strict";
	/**
	 * 所有统计图Option函数
	 * @author lxv
	 * @return option
	 */
    var initParam = {
        "checked":"<i class='fa fa-check checked'></i>" 	// 勾选样式
    };
	var lefthidden1 = "" 	//左侧上方模块是否关闭标识
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
		    color:['red','orange','yellow','blue'],
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
		// 24小时预警标题
		return [$("<div class='top-left-icon'>有效预警</div>")
		// 24小时预警内容
		,$("<div class='lf-1 char-item  column'>"+
				"<div class='contentxd'>"+
					"<div class='con-top'><span class='titlelft' id='yifabu'>有效预警</span></div>"+
					"<div class='con-middle'>"+
						"<div class='contentpd leftTop'>"+
							"<div class='release-title'>"+
								"<div class='title-left'>共发布预警数 :</div>"+
								"<div class='title-right'></div>"+//共发布预警数<span></span>
							"</div>"+
							"<div class='chart-content mdht yfb'>"+
								"<ul></ul>" +//圈状统计图以及下方灾种类型名称显示<li><li>
							"</div>"+
						"</div>"+
					"</div>"+
					"<div class='con-foot'></div>"+ 
				"</div>"+ 
			"</div>")];
	}()
	/**
	 * 预警信息列表
	 * @property warnMessageTitle
	 * @property warnMessage
	 * @author lxv
	 * @return 数组类型 [warnMessageTitle,warnMessage]
	 */
	,[warnMessageTitle,warnMessage] = function(){
		//有效预警标题	
		return  [$("<div class='left-icon'>预警列表</div>")
		//有效预警内容
		,$("<div class='lf-2 char-item column'>" +
				"<div class='contentxd '>" +
					"<div class='con-top'><span class='titlelft'>预警列表</span></div>" +
					"<div class='con-middle'>" +
						"<div class='contentpd'  >" +
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
		return [$( "<div class='bottom-left-icon '>预警级别统计</div>")
		   //预警级别统计内容
	       ,$("<div class='bottomlft bottomhd c-1 char-item column'>" +
	    		   "<div class='btxd'>" +
	    		   		"<div class='bottomtop down tab'><span>预警级别统计</span></div>" +
	    		   		"<div class='bottommidde leftBottommidde '>" +
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
        		   		"<div class='con-middle rightTop'>" +
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
		            "<div class='con-middle rightCenter'>" +
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
		return [$("<div class='bottom-right-icon'>灾种统计</div>")
		    //接收受众统计内容
		    ,$("<div class='bottomrt bottomhd char-item column'>" +
			        "<div class='btxd'>" +
			            "<div class='bottomtop down tab'><span>灾种统计</span></div>" +
			            "<div class='bottommidde rightBottom'>" +
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
		return [$("<div class='bottom-center-icon '>预警发布趋势</div>" )
		//预警统计内容
		,$("<div class='bottommd bottomhd c-2 char-item column'>" +
		        "<div class='btxd'>" +
		            "<div class='bottomtop down tab'>" +
		                "<div class='title'><span>预警发布趋势</span></div>" +
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
		return $("<div class='warnPersonlist-detail' style='display: none;'>" +
				"</div>" );
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
	,warnSearch = function(){
        return $("<div class='pop-search' style='display: none' >" +
            "<div class='pop-search-content'>" +
            "<ul><span><i class='fa fa-search'></i>查询条件</span><span><i class='fa fa-close'></i></span></ul>" +
            "<div>" +
            "<ul>" +
            "<li><input id='startTime' class='cursor-pointer' placeholder='开始时间'/><i class='fa fa-calendar'></i></li>" +
            "<li><input id='endTime' class='cursor-pointer' placeholder='结束时间'/><i class='fa fa-calendar'></i></li>" +
            "<li class='earn-name'>" +
            "<input id='earnId' type='hidden'/>" +
            "<input id='earnName' readonly='readonly' placeholder='灾种名称' title=''/><i class='fa fa-warning'></i>" +
            "<div id='earnNameTree' class='earn-name-tree ztree' data-display='hidden'></div>" +
            "</li>" +
            "<li class='earn-color'>" +
            "<span class='active'>灾种级别</span>" +
            "<div>" +
				"<ul data-color='0' data-level='0'></ul>" +
				"<ul data-color='1' data-level='1'></ul>" +
				"<ul data-color='2' data-level='2'></ul>" +
				"<ul data-color='3' data-level='3'></ul>" +
            "</div>" +
            "</li>" +
            "</ul>" +
            "</div>" +
            "<ul class='pop-channel-title'>" +
            "<span><i class='fa fa-sheqel'></i>渠道选择</span>" +
            "<span><span class='all-channel'>全选</span><span class='un-all-channel'>反选</span></span>" +
            "</ul>" +
            "<div class='pop-content-channel'></div>" +
            "<ul><span><i class='fa fa-list'></i>地区选择</span><span></span></ul>" +
            "<div class='pop-content-tree'><ul id='areaTree' class='ztree'></ul></div>" +
            "<div class='pop-content-bottom'>" +
            	"<ul id='search'>查询</ul>" +
            "</div>" +
            "</div>" +
            "</div>");
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
        warnSearch,					// 预警查询条件
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

    //关闭查询预警弹出层
    $(".pop-search .pop-search-content ul span:last-child i").bind("click",function(){
        $(".pop-search").hide();
    });
    // 拖动设置中心
/*    $(".pop-search .pop-search-content ul:first-child").bind("mousedown",function(event){
        Move.mouseDown($(".pop-search"), event);
        return false;
    });*/

    // 点击灾种名称弹出灾种名称数据
    $(".earn-name > input").bind("click",function () {
        var display=$(".earn-name-tree ").data("display");
        if(display=="hidden"){
            $(".earn-name-tree").show();
            $(".earn-name-tree").data("display","show");
        }else{
            $(".earn-name-tree").hide();
            $(".earn-name-tree").data("display","hidden");
        }
    });


    // 预警颜色选择
    $(".earn-color > div > ul").bind("click", function () {
        var level=$(this).data("level");
        var num = $(this).children("i").length;
        if(num==0){
            $(this).append(initParam.checked);
        }else{
            $(this).children("i").remove();
        }
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
	 * 获取已发布预警
	 */
	,alreadyWarnNum=function(searchParam){
		$.ajax({
			async: true,
			type: "POST",
			url: "event/alreadyPub",
			data: searchParam,
			dataType: "json",
			success: function(data){
				if(data.message==1){
                    $(".leftTop").empty().append("<div class='release-title'>" +
                        "<div class='title-left'>共发布预警数 :</div>"+
                        "<div class='title-right'></div>"+//共发布预警数<span></span>
                        "</div>"+
                        "<div class='chart-content mdht yfb'>"+
                        "<ul></ul>" +//圈状统计图以及下方灾种类型名称显示<li><li>
                        "</div>");
                    alreayWarnCount(data);//已发布预警拼接数量
                    var option =data.option ;
                    for(var i =0; i<option.length; i++){
                        option[i].type=1;
                        $(".contentxd .con-middle .contentpd .chart-content ul ").append("<li>" +
                            "<div class='chart-v' id='"+option[i].id+"'></div>" +
                            "<div class='chart-text'>"+option[i].typeName+"</div>" +
                            "</li>");
                        //追加统计个数
                        echarts.init(document.getElementById(option[i].id)).setOption(Settings(option[i]),true);
					}
				}else{
					$(".leftTop").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
                }
			}
		});
	}
	
	/**
	 * 绘制：预警级别统计图
	 */
	,drawWarnLevelTotal = function(param){
		$.ajax({
			async:true,
		   	type: "POST",
			url: 'event/drawWarnLevelTotal',
			data: param,
			dataType: "json",
			success: function(data){
                if(data.message==1){
					var arry=data.arry
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
						color:['red','orange','yellow','blue'],
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
                    $(".leftBottommidde").empty().append("<div class='pb' id='drawWarnLevelTotal' style='width:100%;height:100%;'></div>");
					echarts.init(document.getElementById("drawWarnLevelTotal")).setOption(option,true);
	        	}else{
                    $("#drawWarnLevelTotal").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
				}
            }
		});
	}
	
	/**
	 * 绘制：发布地区统计图
	 */
	,drawPublishAreaTotal = function(param){
		var totalData="";
		$.ajax({
			   async:true,
		       type: "POST",
		       url: 'event/drawPublishAreaTotal',
		       data: param,
		       dataType: "json",
		       success: function(data){
                   if(data.message==1){
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
				            name:'红色【I级】',
				            type:'bar',
				            stack: '总量',
				            barWidth : 20,//柱图宽度
				            itemStyle : { 
				              normal: {
				                color:"red",
				              }
				            },
				            data: data.red
				        },{
				            name:'橙色【II级】',
				            type:'bar',
				            stack: '总量',
				            barWidth : 20,//柱图宽度
				            itemStyle : {
				            	normal: {
				            		color:"orange",
				            	}
				            },
				            data: data.orange
				        },{
				            name:'黄色【III级】',
				            type:'bar',
				            stack: '总量',
				            barWidth : 20,//柱图宽度
				            itemStyle : {
				            	normal: {
				            		color:"yellow",
				            	}
				            },
				            data:data.yellow
				        },{
				            name:'蓝色【IV级】',
				            type:'bar',
				            stack: '总量',
				            barWidth : 20,//柱图宽度
				            itemStyle : {
				            	normal: {
				            		color:"blue",
				            	}
				            },
				            data:data.blue
				        }]
	   				};
	   				$(".rightTop").empty().append("<div id='drawPublishAreaTotal' style='width:100%;height:100%;'></div>");
	   				echarts.init(document.getElementById('drawPublishAreaTotal')).setOption(option,true);
	    	   	}else{
	    	   		$("#drawPublishAreaTotal").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
	    	   	}
       		}
		});
	}
    /**
	 * 灾种统计
     */
	,disasterSts = function(param){
        $.ajax({
            async:true,
            type: "POST",
            url: 'event/personStas',
            data: param,
            dataType: "json",
            success: function(data){
                if(data.message==1){
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
                            name:'红色【I级】',
                            type:'bar',
                            stack: '总量',
                            barWidth : 20,//柱图宽度
                            itemStyle : {
                                normal: {
                                    color:"red",
                                }
                            },
                            data: data.red
                        },{
                            name:'橙色【II级】',
                            type:'bar',
                            stack: '总量',
                            barWidth : 20,//柱图宽度
                            itemStyle : {
                                normal: {
                                    color:"orange",
                                }
                            },
                            data: data.orange
                        },{
                            name:'黄色【III级】',
                            type:'bar',
                            stack: '总量',
                            barWidth : 20,//柱图宽度
                            itemStyle : {
                                normal: {
                                    color:"yellow",
                                }
                            },
                            data:data.yellow
                        },{
                            name:'蓝色【IV级】',
                            type:'bar',
                            stack: '总量',
                            barWidth : 20,//柱图宽度
                            itemStyle : {
                                normal: {
                                    color:"blue",
                                }
                            },
                            data:data.blue
                        }]
                    };
                    $(".rightBottom").empty().append("<div class='contentpd-2'  id='drawPersonHotMap' style='width:100%;float:left;height:100%;'></div>");
                    echarts.init(document.getElementById('drawPersonHotMap')).setOption(option,true);
                }else{
                    $("#drawPersonHotMap").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
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
		       url: 'event/drawPublishChannelTotal',
		       data: param,
		       dataType: "json",
		       success: function(data){
                   if(data.message==1){
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
				   	$(".rightCenter").empty().append("<div id='drawPublishChannelTotal' style='width:100%;height:100%;'></div>");
					echarts.init(document.getElementById('drawPublishChannelTotal')).setOption(option,true);
	       		}else {
					$("#drawPublishChannelTotal").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
				};
			} 
		});
	}
	/**
	 * 发布趋势
	 * @param data
	 */
	,drawHourWarn= function(param){
		$.ajax({
			async:true,
			type: "POST",
			url: 'event/hourWarn',
			data: param,
			dataType: "json",
	       	success: function(data){
                if(data.message==1){
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
						 rotate:-20,
					 },
					 data: data.time,
				 }];
				setting.grid= {
					 borderWidth: 0,
					 top: 40,
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
					 data: ['红色', '橙色', '黄色', '蓝色']
				 };
				setting.series= [{
						 name: "红色",
						 type: "bar",
						 stack: "总量",
						 barWidth : 25,
						 itemStyle: {
							 normal: {
								 color: "red",
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
						 name: "橙色",
						 type: "bar",
						 stack: "总量",
						 barWidth : 25,
						 itemStyle: {
							 normal: {
								 color: "orange",
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
						 name: "黄色",
						 type: "bar",
						 stack: "总量",
						 barWidth : 25,
						 itemStyle: {
							 normal: {
								 color: "yellow",
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
						 name: "蓝色",
						 type: "bar",
						 stack: "总量",
						 barWidth : 25,
						 itemStyle: {
							 "normal": {
								 "color": "blue",
								 "barBorderRadius": 0,
								 "label": {
									 "position": "top",
									 formatter: function (p) {
										 return p.value > 0 ? (p.value) : '';
									 }
								 }
							 }
						 },
						 data: data.blue
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
                }else{
                    $("#drawWarnCountLine").html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
				}
	 		}
       	})
	}
	,warnList = function(param,page){
		var da = param;
    	da.page=page;
    	da.pageSize=3;
    	da.min=da.page*da.pageSize;
		da.max= da.pageSize;
		$.ajax({
			async:false ,
			type: "POST",
			url: 'event/warnList',
			data: da,
			dataType: "json",
	        success: function(data){
                 if(data.message==1){
				 var page = data.page;
				 var count = data.count;
				 var list = data.list;
				 var html= "";
				 if(list.length>0){
					 $.each(list,function(i,item){
						var dataobj=JSON.stringify(item);
						html += "<dl class='"+(i==0?"active":"")+"'  data-warn='"+dataobj+"'>";
						if(item.img=="" ||item.img==null){
							html += "	<dt ><img src='images/warnlogo.png' id='warnLogo' /></dt>";
						}else{
							html += "	<dt ><img src='"+item.img+"' id='warnLogo' /></dt>";
						}
						html += "<dd>预警名称: 发布"+item.disasterName+item.colorLevelName+"预警</dd>";
						html += "	<dd>发布时间: "+item.sendTime+"</dd>";
						html += "</dl>";
						html += "<dl></dl>";
					 });
					 html += "<ul>";
					 html += "	<span id='nextPage' data-next-page='"+page+"' data-count='"+count+"'>页下</span>";
					 html += "	<span id='topPage' data-top-page='"+page+"' data-count='"+count+"'>上页</span>";
					 html += "	<span>第"+(Number(page)+1)+"页</span>";
					 html += "	<span>总数"+count+"条</span>";
					 html += "</ul>";
					 $(".warnList").empty().append(html);
					 $(".relatedWarn-content").empty().append(html);
				 	}
			 }else{
				$(".warnList").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
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
	    	// $(".warnList dl:nth-child(1)").click();
	    });
		// 左侧预警信息点击事件
		$(".warnList dl:nth-child(2n-1)").bind("click",function(){
			$(this).parent().children("dl.active").removeClass("active");//删除非选中标识
			$(this).addClass("active");//添加选中标识
			//顶部详细预警信息显示
            $(".pop-search").hide();
			$(".details-md-warn").show();
            // 获取当前预警详细信息
            var json = $(this).data("warn");
			// 点击预警列表数据时，展示顶部当前点击的预警信息
			// topWarnInfo(json);
		});
	};

	/**
     * 加载灾种名称树
     */
    var initWarnName = function(){
        var treeCheck = function(e, treeId, treeNode,clickFlag){
            	$("#earnId").val();
            	$("#earnName").val();
                var disasterId = "";
            	var disasterName = "";
                var treeObj = $.fn.zTree.getZTreeObj("earnNameTree");
                var nodes= treeObj.getCheckedNodes(true);
                if(nodes.length>0){
                    for(var i = 0 , len=nodes.length; i<len; i++){
                        disasterId += "," + nodes[i].id;
                        disasterName += "," + nodes[i].name;
                    }
                }
                disasterId != "" ? disasterId = disasterId.substring(1) : disasterId;
                $("#earnId").val(disasterId);
				disasterName != "" ? disasterName = disasterName.substring(1) : disasterName;
				$("#earnName").val(disasterName);

            $("#earnNameTree").hide();
            if(clickFlag!=undefined){
                earnTree.checkNode(treeNode, !treeNode.checked, true);
            }
        }
        var setting = {
            check: {
                enable: true,
                chkStyle: "checkbox",
                radioType: "level",
                chkboxType: { "Y": "s", "N": "s" }
            },
            view: {dblClickExpand: false},
            data: {simpleData: {enable: true}},
            callback: {onCheck: treeCheck,onClick: treeCheck}
        },earnTree;
        // 读取灾种数据， 获取灾种树
        var tree = function(){
            var treeData = null;
            $.ajax({
                async:false,
                type: "POST",
                url: "event/disasterTree",
                dataType: "json",
                success: function(data){
                    treeData = data;
                }
            });
            return treeData;
        };
        var data = tree();
        earnTree = $.fn.zTree.init($("#earnNameTree"), setting, data);
    };

    /**
     * 地区数据加载
     */
    var initAreaTree = function(){
        var treeCheck = function(e, treeId, treeNode, clickFlag){
            var areaid = treeNode.id;
            var areaname = treeNode.name;
            $("#parentId").val(areaid);
            $("#parentName").val(areaname);
            if(clickFlag!=undefined){
                areaTree.checkNode(treeNode, !treeNode.checked, true);
            }
        }
        var setting = {
            async:{
                enable:true,
                url: "event/areaTree",
                otherParam: {"empAreaId": Globel.empAreaId},
                autoParam: ["id=pid"]
            },
            check: {
                enable: true,
                chkStyle: "checkbox",
                radioType: "level",
                chkboxType: { "Y": "s", "N": "s" }
            },
            view: {
                dblClickExpand: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onCheck: treeCheck,
                onClick: treeCheck
            }
        },areaTree;
        areaTree=$.fn.zTree.init($("#areaTree"), setting);
    };

    // 初始化渠道信息
    var initChannel = function(){
        $.ajax({
            async:false,
            type:"POST",
            url:"event/channelList",
			data:{"empAreaId": Globel.empAreaId},
            dataType: "json",
            success:function(data){
                var html = "";
                for(var i=0,len=data.length;i<len;i++){
                    var dataobj=JSON.stringify(data[i]);
                    html += "<dl data-channel='"+dataobj+"'><dt><img src='"+data[i].icon+"' /></dt><dd>"+data[i].name+"</dd></dl>";
                }
                $(".pop-content-channel").empty().prepend(html);
                //精准发布
                $(".pop-warnPublish-channel").empty().prepend(html);

                // 渠道选择(单选)
                $(".pop-content-channel > dl").bind("click", function () {
                    var num = $(this).children("i").length;
                    if(num==0){
                        $(this).append(initParam.checked);
                    }else{
                        $(this).children("i").remove();
                    }
                    return false;
                });

                // 渠道选择(全选)
                $(".pop-channel-title > span:last-child .all-channel").bind("click", function () {
                    $(".pop-content-channel > dl").children("i").remove();
                    $(".pop-content-channel > dl").append(initParam.checked);
                    return false;
                });
                // 渠道选择(反选)
                $(".pop-channel-title > span:last-child .un-all-channel").bind("click", function () {
                    $(".pop-content-channel > dl").children("i").remove();
                    return false;
                });
            }
        });
    };

    /**
     * 点击预警列表数据时，展示顶部当前点击的预警信息
     */
    var topWarnInfo = function(json){
    	var html="";
			html += "<div class='porltv'>";
	    	html +=     "<span class='cls fa-close' id='topWarnClose'></span>";
	    	html +=     "<div class='details-content'>";
	    	html +=         "<div class='d-c-list'>";
	    	html +=             "<div class='details-content-left'>预警名称：</div>";
	    	html +=             "<div class='details-content-right'>";
			html +=         		"<div class='yjtitle'>发布"+json.disasterName+json.colorLevelName+"预警</div>";
	    	html +=             "</div>";
	    	html +=             "<div class='details-content-left'>发布时间：</div>";
	    	html +=             "<div class='details-content-right'>";
	    	html +=                 "<div class='yjtime'>"+json.sendTime+"</div>";
	    	html +=             "</div>";
	    	html +=         "</div>";
	    	html +=         "<div class='d-c-list'>";
	    	html +=             "<div class='details-content-left'>预警内容：</div>";
	    	html +=             "<div class='details-content-right'>";
	    	html +=                 "<div class='yjcontent'>"+json.content+"</div>";
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
    // 开始时间
    laydate.render({
        theme: "molv",
        elem: "#startTime",
        type: "datetime",
        value: dateTime(7)
    });
    // 结束时间
    laydate.render({
        theme: "molv",
        elem: "#endTime",
        type: "datetime",
        value: new Date()
    });

    //导航栏模块图标显示
    var modularShow=function(){
    	var html="";
        	html += "<li title='查询预警' class='nav-item mr-1 nav-menu-7 model-10'><i id=''class='fa fa-search' aria-hidden='true'></i></li>";
	    	html += "<li title='有效预警' class='nav-item mr-1 nav-menu-7 model-1'><i id=''class='fa fa-pie-chart' aria-hidden='true'></i></li>";
	    	html += "<li title='预警列表' class='nav-item mr-1 nav-menu-7 model-2'><i id=''class='fa fa-list' aria-hidden='true'></i></li>";
	    	html += "<li title='级别统计' class='nav-item mr-1 nav-menu-7 model-3'><i id=''class='fa fa-pie-chart' aria-hidden='true'></i></li>";
	    	html += "<li title='发布趋势' class='nav-item mr-1 nav-menu-7 model-4'><i id=''class='fa fa-line-chart' aria-hidden='true'></i></li>";
	    	html += "<li title='灾种统计' class='nav-item mr-1 nav-menu-7 model-5'><i id=''class='fa fa-user' aria-hidden='true'></i></li>";
	    	html += "<li title='渠道统计' class='nav-item mr-1 nav-menu-7 model-6'><i id=''class='fa fa-stack-overflow' aria-hidden='true'></i></li>";
	    	html += "<li title='地区统计' class='nav-item mr-1 nav-menu-7 model-7'><i id=''class='fa fa-bar-chart' aria-hidden='true'></i></li>";
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
    		}else if($(this).hasClass("model-10")){
                $(".details-md-warn").hide();
                $(".pop-search").show();
            }
        })
    }

    /**
     * 预警查询参数封装
     */
    var param = function(){
        var p = {
            empAreaId: Globel.empAreaId,
            // startTime:"2018-09-10 11:28:21",
            // endTime:"2018-10-10 11:28:21",
            startTime:$("#startTime").val(),
            endTime:$("#endTime").val(),
            disasterId : function(){
                var disasterId = "";
                var treeObj = $.fn.zTree.getZTreeObj("earnNameTree");
                var nodes= treeObj.getCheckedNodes(true);
                if(nodes.length>0){
                    for(var i = 0 , len=nodes.length; i<len; i++){
                        disasterId += "," + nodes[i].id;
                    }
                }
                disasterId != "" ? disasterId = disasterId.substring(1) : disasterId
                $("#earnId").val(disasterId);
                return disasterId ;
            }(),
            disasterName : function(){
                var disasterName = "";
                var treeObj = $.fn.zTree.getZTreeObj("earnNameTree");
                var nodes=treeObj.getCheckedNodes(true);
                if(nodes.length>0){
                    for(var i = 0 , len=nodes.length; i<len; i++){
                        disasterName += "," + nodes[i].name;
                    }
                }
                disasterName != "" ? disasterName = disasterName.substring(1) : disasterName
                $("#earnName").val(disasterName);
                return disasterName;
            }(),
            warnColorNum: function(){
                var warnColorNum = "";
                $(".earn-color > div >ul").children("i").each(function(){
                    warnColorNum +="," + $(this).parent().data("color");
                });
                return warnColorNum != "" ? warnColorNum = warnColorNum.substring(1) : warnColorNum ;
            }(),
            channelId: function(){
                var channelId = "";
                $(".pop-content-channel > dl").children("i").each(function(){
                    channelId += "," + $(this).parent().data("channel").id;
                });
                return channelId != "" ? channelId = channelId.substring(1) : channelId ;
            }(),
            areaId : function(){
                var areaId = "";
                var treeObj = $.fn.zTree.getZTreeObj("areaTree");
                var nodes= treeObj.getCheckedNodes(true);
                if(nodes.length>0){
                    for(var i = 0 , len=nodes.length; i<len; i++){
                        areaId += "," + nodes[i].id;
                    }
                }
                return areaId != "" ? areaId = areaId.substring(1) : areaId ;
            }(),
            areaName : function(){
                var areaName = "";
                var treeObj = $.fn.zTree.getZTreeObj("areaTree");
                var nodes=treeObj.getCheckedNodes(true);
                if(nodes.length>0){
                    for(var i = 0 , len=nodes.length; i<len; i++){
                        areaName += "," + nodes[i].name;
                    }
                }
                return areaName != "" ? areaName = areaName.substring(1) : areaName ;
            }()
        };
        return p;
    };

    /**
	 * 清除地图覆盖物
     */
    var remove_overlay=function (){
        Globel.map.clearOverLays();
    }

    /**
     * 预警信息绑定地图上
     */
    var warnData = function(param){
        remove_overlay();
        //创建图片对象
        var createIcon = (iconUrl)=>{
            return new T.Icon({
                iconUrl: iconUrl,
                iconSize: new T.Point(40, 40),
                iconAnchor: new T.Point(10, 25)
            });
        };

        // 点击图标显示弹出层信息
        var content = data => {
        	var  json=null;
            $.ajax({
                async:false,
                type: "POST",
                url: 'event/channelsByIdArea',
                data: data,
                dataType: "json",
                success: function(data){
                	json=data;
                }
            });
            return "<div>"
                + "<ul style='padding-bottom: 3px;'>预警名称："+data.title+"</ul>"
                + "<ul style='padding-bottom: 3px;'>发布地区："+data.areaName+"</ul>"
                + "<ul style='padding-bottom: 3px;'>发布渠道："+json.channel+"</ul>"
                + "<ul style='padding-bottom: 3px;'>发布机构："+data.orgName+"</ul>"
                + "<ul style='padding-bottom: 3px;'>发布时间："+data.sendTime+"</ul>"
                + "<ul style='padding-bottom: 3px;'>预警内容："+data.content+"</ul>"
                + "</div>";
        };

        // 信息窗口展开事件
        var openInfo = (content,e) =>{
            var point = e.lnglat;	            // 创建标注
            var markerInfoWin = new T.InfoWindow(content,{offset:new T.Point(0,-30)}); 	// 创建信息窗口对象
            Globel.map.openInfoWindow(markerInfoWin,point); //开启信息窗口
        };

        // 图标点击事件
        var addClickHandler = (content,marker)=>{
            marker.addEventListener("click",function(e){
                openInfo(content,e)
            });
        };
        $.ajax({
            async:true,
            type: "POST",
            url: 'event/getWarnData',
            data: param,
            dataType: "json",
            success: function(data){
                if(data.length>0){
                    for(var i=0;i<data.length;i++){
                        var icon = createIcon(data[i].icon);						// 创建图标对象
                        var a=Math.floor(Math.random()*10+1);
                        var b=Math.floor(Math.random()*10+1);
                        var c=Math.floor(Math.random()*10+1);
                        var random=(a/100000+b/10000+c/1000).toFixed(5);          //随机数
                        var longitude=(data[i].longitude*1+random*1).toFixed(5);  //经度添加随机数
                        var latitude=(data[i].latitude*1+random*1).toFixed(5);    //维度添加随机数
                        var marker = new T.Marker(new T.LngLat(longitude,latitude), {icon: icon});  	// 创建标注
                        // 将标注添加到地图中
						Globel.map.addOverLay(marker);
                        // 追加图标点击事件
                        addClickHandler(content(data[i]),marker);
                    }
                }
            }
        });
    }

    /**
     * 预警查询参数封装
     */
/*    var param = function(){
        var p = {
            startTime:$("#startTime").val(),
            endTime:$("#endTime").val(),
            disasterId:"",
            disasterName:"",
            warnColorNum: "",
            warnLevelNum:"",
            channelId: function(){
                var channelId = "";
                $(".pop-content-channel > dl").children("i").each(function(){
                    channelId += "," + $(this).parent().data("channel").id;
                });
                return channelId != "" ? channelId = channelId.substring(1) : channelId ;
            }(),
            areaId : "",
            areaName : ""
        };
        return p;
    };*/
    // 查询预警信息
    $("#search").bind("click", function(){

        alreadyWarnNum(param());				// 有效预警
        warnList(param(),0);					// 预警列表
        drawWarnLevelTotal(param());			// 级别统计
        drawHourWarn(param());					// 预警发布趋势
        drawPublishAreaTotal(param());			// 地区统计
        drawPublishChannelTotal(param());		// 渠道统计
        disasterSts(param());					// 灾种统计
        warnData(param());						// 预警图标绑定到地图
    });

	/**
	 * 数据加载
	 */
	setTimeout(function(){
        modularShow();							// 导航栏

        initWarnName();							// 初始化灾种数据(查询条件)
        initAreaTree();							// 初始化地区信息树
        initChannel();							// 初始化渠道信息

		alreadyWarnNum(param());				// 有效预警
        warnList(param(),0);					// 预警列表
		drawWarnLevelTotal(param());			// 级别统计
        drawHourWarn(param());					// 预警发布趋势
		drawPublishAreaTotal(param());			// 地区统计
        drawPublishChannelTotal(param());		// 渠道统计
        disasterSts(param());					// 灾种统计
		warnData(param());						// 预警图标绑定到地图

	},500);
	
});	