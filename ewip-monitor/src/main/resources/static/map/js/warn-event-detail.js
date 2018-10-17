$(function(){
	var initParam = {
	        "checked":"<i class='fa fa-check checked'></i>", 	// 勾选样式
	        "flag": null,											// 预警唯一标识
	        "terminal": null,										// 终端信息
	        "warnPic": "images/warnlogo.png",	// 预警图片
	        "orgId":$("#orgId").val(),								// 机构ID
	        "sensitiveWord" :null,									// 敏感字
	        "overlookedWord" :"",									// 已忽略敏感字
        	"userTypeId":""
	 	};
	
	//严格模式
	"use strict";
	/**
	 * 所有统计图Option函数
	 * @author lxv
	 * @return option
	 */
	var dlNum=0			//左右移动按钮数量标识
	,searchParam = "" 	//查询预警参数
	,lefthidden1 = "close" 	//左侧上方模块是否关闭标识
	,lefthidden2 = "close"	//左侧下方模块是否关闭标识
	,righthidden1= "close"	//右侧上方模块是否关闭标识
	,righthidden2= "close"	//右侧下方模块是否关闭标识
	,bottomhidden1= "close"	//下左模块是否关闭标识
	,bottomhidden2= "close"	//下中模块是否关闭标识
	,bottomhidden3= "close"	//下右模块是否关闭标识
	,stsData=""			//统计数据
	//预警统计信息总div内容
	,warnContent = $("<div class='warn-event-detail index-content'></div>")
	/**
	 * echars图公用属性
	 * @param param
	 * @author lxv
	 * @return setting
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
		    color:['green','blue','yellow','red','orange'], 
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
	            name:'灾害信息',
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
	 * 事件切换 
	 */
	,[eventTitle,eventCycle] = function(){
		return [$("<div class='top-left-icon'>事件切换</div>"),
		        $("<div class='lf-1 char-item  column' style='display:none'>"+
				"<div class='contentxd'>"+
					"<div class='con-top'><span class='titlelft' >事件切换</span></div>"+ 
					"<div class='con-middle' id='eventList'>"+
					"</div>"+
					"<div class='con-foot'><ul id='addEvent' class='addEvent'>添加事件</ul></div>"+ 
				"</div>"+ 
			"</div>")];
	}()
	,addEvent = function(){
		return $("<div class='pop-search' >" +
	             "<div class='pop-search-content'>" +
	                "<ul><span><i class='fa fa-search'></i>事件条件</span><span><i class='fa fa-close'></i></span></ul>" +
	                "<div>" +
	                    "<ul>" +
	                        "<li><input id='startTime' class='cursor-pointer' placeholder='开始时间'/><i class='fa fa-calendar'></i></li>" +
	                        "<li><input id='endTime' class='cursor-pointer' placeholder='结束时间'/><i class='fa fa-calendar'></i></li>" +
	                        "<li class='earn-name'>" +
	                            "<input id='earnId' type='hidden'/>" +
	                            "<input id='earnName' readonly='readonly' placeholder='灾种名称'/><i class='fa fa-warning'></i>" +
	                            "<div id='earnNameTree' class='earn-name-tree ztree' data-display='hidden'></div>" +
	                        "</li>" +
	                        "<li class='earn-color'>" +
	                            "<span class='active'>灾种颜色</span>" +
	                            "<div>" +
	                                "<ul data-color='1' data-level='I'></ul>" +
	                                "<ul data-color='2' data-level='II'></ul>" +
	                                "<ul data-color='3' data-level='III'></ul>" +
	                                "<ul data-color='4' data-level='IV'></ul>" +
	                                "<ul data-color='9' data-level='V'></ul>" +
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
	               		"<input type='text' placeholder='请输入事件名称' id='eventName' value='' >" +
	                    "<ul id='clear'>清空</ul><ul id='saveEvent'>保存事件</ul>" +	
	                "</div>" +
	            "</div>" +
	        "</div>");
	}()
	/**
	 * 涉事预警
	 */
	,[warnMesTitle,warnMesCycle] = function(){
		return  [$("<div class='left-icon'>涉事预警</div>")
		,$("<div class='lf-2 char-item column' >" +
				"<div class='contentxd'>" +
					"<div class='con-top'><span class='titlelft'>涉事预警</span></div>" +
					"<div class='con-middle'>"+
						"<div class='contentpd'>"+
							"<div class='release-title'>"+
								"<div class='title-left'>共发布预警数 :</div>"+
								"<div class='title-right'> </div>"+//共发布预警数<span></span>
							"</div>"+
							"<div class='chart-content mdht warnMessage'>"+
								"<ul></ul>" +//圈状统计图以及下方灾种类型名称显示<li><li>
							"</div>"+
						"</div>"+
					"</div>"+
					"<div class='con-foot'></div>" +
				"</div>" +
			"</div>")];
	}()
	
	/**
	 * 事件评估 
	 */
	,[assessmentTitle,assessmentCycle] = function(){
		return [$( "<div class='top-right-icon'>事件评估</div>")
		,$("<div class='rt-1 char-item column' style='display:none'>" +
				"<div class='contentxd'>" +
	            	"<div class='con-top-right'><span class='titleright'>事件评估</span></div>" +
		            "<div class='con-middle'>" +
		       /*     	"<div class='personMap'>" +
		            		"<ul class='respons'>责任人</ul>" +
		            		"<ul class='audience'>终端</ul>" +
		            	"</div>" +*/
		            	"<div class='contentpd-2'  id='responsibility' style='width:100%;float:left;height:185px; top:0px;'>" +
			            	"<div class='fb-text'>" +
		                    "涉事预警<span class='ftcl-10 earlyWarningNum'>0</span>个，" +
		                    "传播渠道<span class='ftcl-10 influenceChannelNum'>0</span>个，" +
		                    "影响地区<span class='ftcl-10 influenceAreaNum'>0</span>个，" +
		                    "参与单位<span class='ftcl-10 partOrgNum'>0</span>个，" +
		   /*                 "影响人数<span class='ftcl-10 influencePersonaNum'>0</span>人，&nbsp;&nbsp;&nbsp;&nbsp;" +*/
		                    "人口：<input id='people' type='text' value=''/> 人，" +
		                    "面积：<input id='areaSize' type='text' value=''/> km²，" +
		                    "GDP：<input id='gdpValue' type='text' value=''/> 亿" +
		                    "</div>" +
		            	"</div>" +
		            "</div>" +
			        "<div class='con-foot'></div>" + 
			    "</div>" +
		    "</div>")];
	}()
	/**
	 * 影响区域
	 */
	,[influenceAreaTitle,influenceAreaCycle] = function(){
		return [$( "<div class='right-icon'>影响区域</div>")
		,$("<div class='rt-2 char-item column' >" +
		        "<div class='contentxd'>" +
		            "<div class='con-top-right'><span class='titleright'>影响区域</span></div>" +
		            "<div class='con-middle' >" +
		         		"<div id='influenceArea' style='width:100%; height:100%;'></div>" +
		            "</div>" +
		            "<div class='con-foot'></div>" + 
	            "</div>" +
	        "</div>")];
	}()
       
	/**
	 * 影响渠道
	 */
	,[influenceChannelTitle,influenceChannelCycle] = function(){
		return [$( "<div class='bottom-left-icon '>传播渠道</div>")
		,$("<div class='bottomlft bottomhd c-1 char-item column'>" +
		        "<div class='btxd'>" +
		            "<div class='bottomtop down tab'><span>传播渠道</span></div>" +
		            "<div class='bottommidde'>" +
			            "<div class='toleft'  data-fx='left'><i class='fa fa-chevron-left text-white'></i></div>" +//向左图标
			        	"<div class='toright' data-fx='right'><i class='fa fa-chevron-right text-white'></i></div>" +//向右图标
			        	"<div class='con-middle influenceChannel' id='influenceChannel'>" +
		            		"<div class='tempWrapParent' ><div class='tempWrap'></div>" +
	            		"</div>"+
	                "</div>" +
		            "</div>" +
		            
		        "</div>" +
		   "</div>" )];
	}()  
	/**
	 * 事件汇总
	 */
	,[eventTotalTitle,eventTotalCycle] = function(){
		return [$("<div class='bottom-center-icon '>事件汇总</div>" )
		,$("<div class='bottommd bottomhd c-2 char-item column'>" +
		        "<div class='btxd'>" +
		            "<div class='bottomtop down tab'>" +
		                "<div class='title'><span>事件汇总</span></div>" +
		            "</div>" +
		            "<div class='contentpdbottommidde'>" +
		                "<div class='contentpd-2' id='chyj' style='width:31%;float:left;height:100%;'>" +
		                "</div>" +
		                 "<div class='contentpd-2' id='chyjzx' style='width:67%;float:left;height:100%;'>" +
		                 "</div>" +
		            "</div>" +
		        "</div>" +
		    "</div>" )];
	}()
	
	/**
	 * 参与单位
	 */
	,[participateCompanyTitle,participateCompanyCycle] = function(){
		return [$("<div class='bottom-right-icon '>参与单位</div>" )
		, $("<div class='bottomrt bottomhd char-item column'>" +
		        "<div class='btxd'>" +
		            "<div class='bottomtop down tab'><span>参与单位</span></div>" +
		            "<div class='bottommidde'>" +
		                "<div class='contentpd-2'  id='participateOrg'  style='width:100%;height:100%;float:left;'>" +
		                "</div>" +
		            "</div>" +
		        "</div>" +
	    	"</div>" )];
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
			                        "<li class='on'>相关预警</li>" +
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
	//初始化预警信息拼接页面
	,init =function(...params){
		for(var i = 0; i<params.length; i++){
			$(warnContent).append(params[i]);//拼接预警信息统计HTML 
		}
		$("body").children("script").eq(0).before($(warnContent));		//拼接Html
	};  
	
	//初始化加载所有模块
	init(	
		addEvent,				//添加事件
		channelBackDetail,		//渠道反馈详情
		warnCenter,				//预警中心内容
		eventTitle,				//事件标题
		eventCycle,				//事件内容
		warnMesTitle,			//涉事预警标题
		warnMesCycle,			//涉事预警内容
		assessmentTitle,		//事件评估标题
		assessmentCycle,		//事件评估内容
		influenceAreaTitle,		//影响地区标题
		influenceAreaCycle,		//影响地区内容
		influenceChannelTitle,	//影响渠道标题
		influenceChannelCycle,	//影响渠道内容
		eventTotalTitle,		//事件汇总标题
		eventTotalCycle,		//事件汇总内容
		participateCompanyTitle,//参与单位标题
		participateCompanyCycle //参与单位内容
	);	
	
	
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
    
    // 定时发布时间
    laydate.render({
        theme: "molv",
        elem: "#timingPublishTime",
        type: "datetime",
        value: ""
    });
    
    // 发布时间
    laydate.render({
        theme: "molv",
        elem: "#publishTime",
        type: "datetime",
        value: ""
    });
    
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
	//渠道反馈以及影响分析详情关闭
	$(".channelBackDetail > .setting-list-title span:last-child").on("click", function(){
		$(".channelBackDetail").hide();
	});
	//左右移动绑定事件
	$(".toright,.toleft").on("click", function(){
    	// 获取点击的方向
    	var fx = $(this).data("fx");
    	// 获取将要移动div里的dl个数
    	var dlLen = $(".tempWrapParent .tempWrap").children("dl").length;
    	// 不够2个时左右点击切换失效（显示区域永远为2个）
    	if(dlLen< 2){
    		return false;
    	}
		if(fx=="right"){
    		if(dlNum<dlLen && (dlLen-dlNum-2)>0){
    			dlNum = dlNum+1;
    			$(".tempWrapParent .tempWrap").animate({"left": (-131*dlNum) + "px"}, "slow");
        	}
    	}
    	if(fx=="left"){
    		if(dlNum>0){
    			dlNum = dlNum-1;
    			$(".tempWrapParent .tempWrap").animate({"left": (-132*dlNum) + "px"}, "slow");
        	}
    	}
    });
	/**
	 * 将中心左右小图标移动到边缘
	 * @param :lefthidden1,lefthidden2,righthidden1,righthidden2
	 * @author lxv
	 */
	var moveEdge=function(lefthidden1,lefthidden2,righthidden1,righthidden2){
		if(lefthidden1=="close" && lefthidden2=="close"){
			if($(".top-center-left-icon .top-center-left").is(':hidden')){
				$(".top-center-left-icon .cls.lft").animate({"left":"0px"}, "slow");
				$(".top-center-left-icon .top-center-left").fadeOut("slow");
				$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(resources/map/images/event/sjx_rt.png)"});
			}
		}
		if(righthidden1=="close" && righthidden2=="close"){
			if($(".top-center-right-icon .top-center-right").is(':hidden')){
				$(".top-center-right-icon .cls.rt").animate({"right":"0px"}, "slow");
				$(".top-center-right-icon .top-center-right").fadeOut("slow");
				$(".top-center-right-icon .cls.rt > span").css({"background-image": "url(resources/map/images/event/sjx_lt.png)"});
			}
		}
	}
	/**
	 * 将中心左右小图标移动到中心
	 * @param :lefthidden1,lefthidden2,righthidden1,righthidden2
	 * @author lxv
	 */
	var moveCenter=function(lefthidden1,lefthidden2,righthidden1,righthidden2){
		if(lefthidden1=="" || lefthidden2==""){
			if($(".top-center-left-icon .top-center-left").is(':hidden')){
				$(".top-center-left-icon .cls.lft").animate({"left":"23.7%"}, "slow");
				$(".top-center-left-icon .top-center-left").fadeOut("slow");
				$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(resources/map/images/event/sjx_rt.png)"});
			}
		}
		if(righthidden1=="" || righthidden2==""){
			if($(".top-center-right-icon .top-center-right").is(':hidden')){
				$(".top-center-right-icon .cls.rt").animate({"right":"23.7%"}, "slow");
				$(".top-center-right-icon .top-center-right").fadeOut("slow");
				$(".top-center-right-icon .cls.rt > span").css({"background-image": "url(resources/map/images/event/sjx_lt.png)"});
			}
		}
	}
/*	//右侧 中 图例责任人点击事件
	$(".personMap .respons").on("click",function(){
		person(1);
		$(".personMap .audience").css("background","#54c382");
		$(".personMap .audience").css("border","1px solid #54c382");
		$(this).css("background","deepskyblue");
		$(this).css("border","1px solid deepskyblue");
	});
	//右侧 中 图例受众点击事件
	$(".personMap .audience").on("click",function(){
		person(2);
		$(".personMap .respons").css("background","#54c382");
		$(".personMap .respons").css("border","1px solid #54c382");
		$(this).css("background","deepskyblue");
		$(this).css("border","1px solid deepskyblue");
	});*/

	//隐藏7大模块
/*	$(".contentxd,.btxd").on("click","div:first",function(){
		//左上 已发布信息（隐藏）
		if($(this).parent().parent().hasClass("lf-1")){
			$(".lf-1").animate({"left":"-38.5%"}, "slow");
			$(".lf-1").fadeOut("slow");
			lefthidden1="close";
			if(lefthidden2=="close"){
				if($(".top-center-left-icon .top-center-left").is(":hidden")){
					$(".top-center-left-icon .cls.lft").animate({"left":"0%"}, "slow");
				}else{
					$(".top-center-left-icon .top-center-left").animate({"left":"0%"}, "slow");
					$(".top-center-left-icon .cls.lft").animate({"left":"15%"}, "slow");
				}
			}
		//左上 待发布信息（隐藏）	
		}else if($(this).parent().parent().hasClass("lf-2")){
			$(".lf-2").animate({"left":"-38.5%"}, "slow");
			$(".lf-2").fadeOut("slow");
			lefthidden2="close";
			if(lefthidden1=="close"){
				if($(".top-center-left-icon .top-center-left").is(":hidden")){
					$(".top-center-left-icon .cls.lft").animate({"left":"0%"}, "slow");
				}else{
					$(".top-center-left-icon .top-center-left").animate({"left":"0%"}, "slow");
					$(".top-center-left-icon .cls.lft").animate({"left":"15%"}, "slow");
				}
			}
		//右上 灾情上报（隐藏）	
		}else if($(this).parent().parent().hasClass("rt-1")){
			$(".rt-1").animate({"right":"-38.5%"}, "slow");
			$(".rt-1").fadeOut("slow");
			righthidden1="close";
			if(righthidden2=="close"){
				if($(".top-center-right-icon .top-center-right").is(":hidden")){
					$(".top-center-right-icon .cls.rt").animate({"right":"0%"}, "slow");
				}else{
					$(".top-center-right-icon .top-center-right").animate({"right":"0%"}, "slow");
					$(".top-center-right-icon .cls.rt").animate({"right":"15%"}, "slow");
				}
			}
		//右上 预警责任人（隐藏）		
		}else if($(this).parent().parent().hasClass("rt-2")){
			$(".rt-2").animate({"right":"-38.5%"}, "slow");
			$(".rt-2").fadeOut("slow");
			righthidden2="close";
			if(righthidden1=="close"){	
				if($(".top-center-right-icon .top-center-right").is(":hidden")){
					$(".top-center-right-icon .cls.rt").animate({"right":"0%"}, "slow");
				}else{
					$(".top-center-right-icon .top-center-right").animate({"right":"0%"}, "slow");
					$(".top-center-right-icon .cls.rt").animate({"right":"15%"}, "slow");
				}
			}
		//下左 预警签发（隐藏）	
		}else if($(this).parent().parent().hasClass("bottomlft")){
			$(".bottomlft").animate({"bottom":"-32%"}, "slow");
			$(".bottomlft").fadeOut("slow");
			bottomhidden1="close";
		//下中 预警处理（隐藏）		
		}else if($(this).parent().parent().hasClass("bottommd")){
			$(".bottommd").animate({"bottom":"-32%"}, "slow");
			$(".bottommd").fadeOut("slow");
			bottomhidden2="close";
		//下右 预警审核（隐藏）		
		}else if($(this).parent().parent().hasClass("bottomrt")){
			$(".bottomrt").animate({"bottom":"-32%"}, "slow");
			$(".bottomrt").fadeOut("slow");
			bottomhidden3="close";
		}
//		moveEdge(lefthidden1,lefthidden2,righthidden1,righthidden2);
	});*/

	//中间左侧隐藏点击事件
	$(".top-center-left-icon .cls.lft").on("click",function(){
		if(lefthidden1=="close" && lefthidden2=="close" ){
			if($(".top-center-left-icon .top-center-left").is(':hidden')){
				$(".top-center-left-icon .cls.lft").hide();
				$(".top-center-left-icon .top-center-left").animate({"left":"0"}, "slow");
				$(this).animate({"left":"15%"});
				$(".top-center-left-icon .top-center-left").fadeIn();
				$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(resources/map/images/event/sjx_lt.png)"});
			}else{
				$(".top-center-left-icon .cls.lft").hide();
				$(".top-center-left-icon .top-center-left").fadeOut();
				$(this).animate({"left":"0"});
				$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(resources/map/images/event/sjx_rt.png)"});
			}
		}else{
			if($(".top-center-left-icon .top-center-left").is(':hidden')){
				$(this).animate({"left":"38.7%"});
				$(".top-center-left-icon .top-center-left").animate({"left":"23.7%"}, "slow");
				$(".top-center-left-icon .top-center-left").fadeIn();
				$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(resources/map/images/event/sjx_lt.png)"});
			}else{
				if(lefthidden1=="close" && lefthidden2=="close" ){
					$(".top-center-left-icon .cls.lft").hide();
					$(this).animate({"left":"0px"});
					$(".top-center-left-icon .top-center-left").fadeOut();
					$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(resources/map/images/event/sjx_rt.png)"});
				}else{
					$(".top-center-left-icon .cls.lft").hide();
					$(this).animate({"left":"23.7%"});
					$(".top-center-left-icon .top-center-left").fadeOut();
					$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(resources/map/images/event/sjx_rt.png)"});
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
				$(".top-center-right-icon .cls.rt > span").css({"background-image": "url(resources/map/images/event/sjx_rt.png)"});
			}else{
				$(".top-center-right-icon .cls.rt").hide();
				$(".top-center-right-icon .top-center-right").fadeOut();
				$(this).animate({"right":"0"});
				$(".top-center-right-icon .cls.rt > span").css({"background-image": "url(resources/map/images/event/sjx_lt.png)"});
			}
		}else{
			if($(".top-center-right-icon .top-center-right").is(':hidden')){
				$(this).animate({"right":"38.7%"});
				$(".top-center-right-icon .top-center-right").animate({"right":"23.7%"}, "slow");
				$(".top-center-right-icon .top-center-right").fadeIn();
				$(".top-center-right-icon .cls.rt > span").css({"background-image": "url(resources/map/images/event/sjx_rt.png)"});
			}else{
				if( righthidden1=="close" && righthidden2=="close"){
					$(".top-center-right-icon .cls.rt").hide();
					$(this).animate({"right":"0px"});
					$(".top-center-right-icon .top-center-right").fadeOut();
					$(".top-center-right-icon .top-center-right").animate({"right":"0%"}, "slow");
					$(".top-center-right-icon .cls.rt > span").css({"background-image": "url(resources/map/images/event/sjx_lt.png)"});
				}else{
					$(".top-center-right-icon .cls.rt").hide();
					$(this).animate({"right":"23.7%"});
					$(".top-center-right-icon .top-center-right").fadeOut();
					$(".top-center-right-icon .top-center-right").animate({"right":"0%"}, "slow");
					$(".top-center-right-icon .cls.rt > span").css({"background-image": "url(resources/map/images/event/sjx_lt.png)"});
				}
			}
		}
	});
	//中左侧正常异常点击事件
	$(".channelIsNormal label").on("click",function(){
		$(".terminalSearch .span02").click();
	 	});
	
	//中左侧点击事件
	$(".top-center-left .tbtop >ul >li").on("click",function(){
		$(this).parent().children().removeClass("on");
		$(this).addClass("on");
		$(".terminalSearch .span02").click();
	 	});
	//中左侧查询点击事件
	$(".terminalSearch .span02").on("click",function(){
		var param=$(".terminalSearch").data("terminalSearch");
		var name=$(".top-center-left .tbtop >ul").children(".on").text();
		if(name=='显示屏'){
			param.flag='显示屏';
		}else if(name=='北斗卫星'){
			param.flag='北斗卫星';
		}else if(name=='广播'){
			param.flag='广播';
		}
	    var radio=document.getElementsByName("state");//获取所有的选框
        var selectvalue=null;   //  selectvalue为radio中选中的值
        for(var i=0;i<radio.length;i++){
            if(radio[i].checked==true) {
              selectvalue=radio[i].value;
           }
        }
		var terminalState='';
		if (selectvalue=="正常"){
			param.terminalState='0';
		}else if(selectvalue=="故障"){
			param.terminalState='1';
		}else{
			param.terminalState=null;
		}
		param.terminalNum=$(".terminalSearch input").val();
		$.ajax({
			async: false,
			type: "POST",
			url: baseUrl+"event/terminal",
			data: param,
			dataType: "json",
			success: function(data){
				var html= "";
				var list=data.list;
				var map=data.map;
				$(".terminalSearch").data("terminalSearch", map);
				if(list.length>0){
					$.each(list,function(i,item){
						html += "<dl class='terminal'  >";
				  		html += "	<dd title='"+item.TYPENAME+"'>终端名称："+item.TYPENAME+"</dd>";
			        	html += "</dl>";
					});
				}else{
					html += "<p class='noData'>暂无数据！</p>";
				}
			 $(".top-center-left .content ").empty().append(html);
			}
		});
	});
	//中右上侧发布流程和相关预警点击事件
	$(".top-center-right .top .tbtop >ul >li").on("click",function(){
		$(this).parent().children().removeClass("on");
		$(this).addClass("on");
		var name=$(".top-center-right .top .tbtop >ul").children(".on").text();
		if(name=='相关预警'){
			$(".top-center-right .top .content .relatedWarn-content ").show();
			$(".top-center-right .top .content .process-content ").hide();
		}else{
			$(".top-center-right .top .content .process-content ").show();
			$(".top-center-right .top .content .relatedWarn-content ").hide();
		}
 	});
	
	//中右下侧发布流程和相关预警点击事件
	$(".top-center-right .bottom .tbtop >ul >li").on("click",function(){
		$(this).parent().children().removeClass("on");
		$(this).addClass("on");
		var name=$(".top-center-right .bottom .tbtop >ul").children(".on").text();
		if(name=='影响分析'){
			$(".top-center-right .bottom .content .influence-content ").show();
			$(".top-center-right .bottom .content .channelBack-content ").hide();
			$(".channelBackDetail").hide();
		}else{
			$(".top-center-right .bottom .content .channelBack-content ").show();
			$(".top-center-right .bottom .content .influence-content ").hide();
		}
 	});
    /**
     * 根据事件ID查询对应统计信息
     */
    var eventData=function(searchParam){
    	if(searchParam!=undefined){
	    	var mapEventId={"mapEventId":searchParam.keyId};
	    	$.ajax({
	    		 async:false,
	             type: "POST",
	             url: baseUrl+"eventResult/findInfoById",
	             data: mapEventId,
	             dataType: "json",
	             success: function(data){
	            	 console.log(data);
	            	 if(data.length>0){
						var list=data[0];
						stsData=list;
						alreadyWarnNum(JSON.parse(list.ALREADYWARNNUM));		//获取涉事预警统计
						influenceChannel(JSON.parse(list.CHANNELSTATE));		//影响渠道状态统计
						eventAssess(list);										//事件评估
						/*person(1);	*/										//受众信息
						influenceArea();										//影响区域
						warnStsPie(JSON.parse(list.WARNSTSPIE));				//预警统计饼状图
						warnStsColumn(null);							    	//预警统计柱状图
						participateOrg();										//参与单位	 
	            	 }else{
	            		 $("#eventList").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
	            		 $(".warnMessage").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
	            		 $("#influenceArea").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
     	 				 $("#influenceChannel").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
     	 				 $("#responsibility").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
	 	     	 		 $(".contentpdbottommidde").empty().html("<span class='noData' style='font-size: 16px; top: 38%;right: 43%;position: absolute; color: gray;'>暂无数据！</span>");
	 	     	 		 $("#participateOrg").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
	            	 }
	             }
	    	})
    	}
    }
    
    /**
     * 加载事件列表信息
     */
    var eventHtml =function(page,name){
    	var da = param();
    	da.offset=page;
    	da.limit=6;
    	da.name=name;
    	$.ajax({
    		 async:false,
             type: "POST",
             url: baseUrl+"event/find",
             data: da,
             dataType: "json",
             success: function(data){
        	 	var name = data.name;
        	 	var page = data.page;
        	 	var count = data.totalLength;
        	 	var list = data.dataList;
        	 	var html= "";
        	 		html +="<div class='pop-event'>";
	     		 	html +="<div class='pop-event-content'>";
     		 	if(list.length>0){
	 		 		$.each(list,function(i,item){
	 		 			var dataobj=JSON.stringify(item);
		     			 	html +="<dl class='' data-event='"+dataobj+"'>";
		     			 	html +="<dd title='"+item.name+"'>"+item.name.substring(0,15)+"</dd>";
		     			 	html +="<dd>"+item.createTime+"</dd>";
		     			 	html +="<dd>删除</dd>";
		     			 	html +="</dl>";
	         		 	});
	 		 		html +="</div>";
	         		html +="<ul class='ul_btn' >";
	         		html +="	<span id='topEventClose'></span>";
	         	 	html +="	<span id='eventNextPage' data-count='"+count+"'>下页</span>";
	            	html += "	<span id='eventTopPage' data-count='"+count+"'>上页</span>";
	          		html +="	<span>总数"+count+"条</span>";
	         		html +="</ul>";
	         		html +="</div>";
	         		$("#eventList").empty().append(html);//给第一个事件选中事件
	         		$(".pop-event-content dl").first().addClass("active");//初始化加载第一个选中的事件
	         		searchParam=$(".pop-event-content .active").data("event");
     		 	}else{
	     	 		$("#eventList").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
	     	 		$(".warnMessage").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
	     	 		$("#influenceArea").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
	     	 		$("#influenceChannel").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
	     	 		$("#responsibility").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
	     	 		$(".contentpdbottommidde").empty().html("<span class='noData' style='font-size: 16px; top: 38%;right: 43%;position: absolute; color: gray;'>暂无数据！</span>");
	     	 		$("#participateOrg").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
	     		 }
             }
    	});
    	//事件列表点击事件
    	$(".pop-event-content >dl >dd:first-child,.pop-event-content >dl >dd:nth-child(2)").bind("click",function(){
    		$(".channelBackDetail").hide();
    		$(this).parent().parent().children(".active").removeClass("active");
    		$(this).parent().addClass("active");
    		searchParam=$(this).parent().data("event");
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
    		
    		loadWarningData(searchParam,3);//预警图标加载地图上  3:表示点击事件按钮
        	var mapEventId={"mapEventId":searchParam.keyId};
        	$.ajax({
        		async:false,
        		type: "POST",
        		url: baseUrl+"eventResult/findInfoById",
        		data: mapEventId,
        		dataType: "json",
        		success: function(data){
        			if(data.length>0){
        				var list=data[0];
                    	stsData=list;
                    	alreadyWarnNum(JSON.parse(list.ALREADYWARNNUM));		//获取涉事预警统计
    	            	influenceChannel(JSON.parse(list.CHANNELSTATE));		//影响渠道状态统计
    	            	eventAssess(list);										//事件评估
    	            	/*person(1);*/											//受众信息
    	            	influenceArea();										//影响区域
    	         		warnStsPie(JSON.parse(list.WARNSTSPIE));				//预警统计饼状图
    	         		warnStsColumn(null);							    	//预警统计柱状图
    	         		participateOrg();										//参与单位	
        			}else{
        				$("#eventList").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
	            		$(".warnMessage").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
	            		$("#influenceArea").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
     	 				$("#influenceChannel").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
     	 				$("#responsibility").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
	 	     	 		$(".contentpdbottommidde").empty().html("<span class='noData' style='font-size: 16px; top: 38%;right: 43%;position: absolute; color: gray;'>暂无数据！</span>");
	 	     	 		$("#participateOrg").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
        			}
                 }
        	})
		
			$(".top-center-left-icon .cls.lft").animate({"left":"23.7%"}, "slow");
			$(".top-center-left-icon .top-center-left").fadeOut("slow");
			$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(resources/map/images/event/sjx_rt.png)"});

			$(".top-center-right-icon .cls.rt").animate({"right":"23.7%"}, "slow");
			$(".top-center-right-icon .top-center-right").fadeOut("slow");
			$(".top-center-right-icon .cls.rt > span").css({"background-image": "url(resources/map/images/event/sjx_lt.png)"});

			$(".contentPane").empty();//清空内容
			$(".esriPopupWrapper").hide();
			$(".esriPopup .outerPointer.left ").hide();
    	});
    	
	//事件列表删除事件
	$(".pop-event-content >dl >dd:last-child").bind("click",function(){
		var searchParam=$(this).parent().data("event");
		var keyId={"keyId":searchParam.keyId};
   		if(confirm("确认删除吗")){
   			$.ajax({
                async: false,
                type: "POST",
                url: "event/delete",
                data: keyId,
                dataType: "json",
                success: function (data) {
                        var promptBox=box();
        			    promptBox.content="删除成功";
        	         	promptBox.contentIcon="fa-check-circle";
        			    Move.pop(promptBox);
        			    eventHtml(0,null); 					// 显示事件列表
        				var channelAndWarn = map.graphicsLayerIds;
        				if(channelAndWarn!=undefined){
        					var delLayer = [];
        					for(var i=0;i<channelAndWarn.length;i++){
        						delLayer.push(channelAndWarn[i]);
        					}
        					for(var i = 0;i<delLayer.length;i++){
        						if(delLayer[i]!="player" && delLayer[i]!="regionShiGraphicslayer" ){
        							var mark=new Mark();
        							mark.remove_CodePolygon(map, delLayer[i]);
        						}
        					}
        					var mark = new Mark();
        					mark.clearImage("cloudLayer");
        					mark.clearImage("radarLayer");
        					
        					clearInterval(intt_);
        					var mark = new Mark();
        					mark.getGraphicsLayer("warningGraphicsLayer").clear();
        				}
                    },
                    error: function () {
                    	var promptBox=box();
        			    promptBox.content="删除失败";
        	         	promptBox.contentIcon="fa-check-circle";
                    }
   				});
    		}
    	})
    	
        // 顶部事件点击（下页）按钮事件
        $("#eventNextPage").bind("click",function(){
        	var nextPage = page;
        	var count = $(this).data("count");
        	if((nextPage+da.limit)>=count){
        		return false;
        	}
        	eventHtml(nextPage+da.limit,name);
        });
        // 顶部事件点击（上页）按钮事件
        $("#eventTopPage").bind("click",function(){
        	var topPage = page;
        	if(topPage-da.limit<0){
        		return false;
        	}
        	eventHtml(topPage-da.limit,name);
        });
    };
	
	
	/**
	 * 已发布总数
	 */	
	var alreayWarnCount = function(data){
		var allCount=data.count;
		var len=allCount.toString.length;
		var html = "";
		if(len >=2){
			var arry = allCount.toString().split('');
			for(var i=0;i<arry.length;i++){
				html+="<span>"+arry[i]+"</span>";
			};
		}else{
			html+="<span>"+allCount+"</span>";
		}
		$(".release-title > div:nth-child(2)").empty().append(html);
	};
	/**
	 * 获取已发布后台数据
	 * @param:searchParam
	 * @author lxv
	 */	
	var alreadyWarnNum=function(data){
		alreayWarnCount(data);//已发布预警拼接数量
		var option = data.option;
		$(".chart-content ul ").empty();
		for(var i =0; i<option.length; i++){
			option[i].type=1;
			$(".chart-content ul ").append("<li>" +
					"<div class='chart-v' id='"+option[i].id+"'></div>" +
					"<div class='chart-text'>"+option[i].typeName+"</div>" +
				"</li>");
			//追加统计个数
			var optionnew=Settings(option[i]);
			echarts.init(document.getElementById(option[i].id)).setOption(optionnew);
		}
		$(".alreadyLoading").empty();
	};
	
	
	/**
	 * 预警统计饼状图
	 */
	var warnStsPie=function(data){
		var setting=Settings(data);
		setting.title={
		        text: '预警统计',
		        x:'center',
		        textStyle:{
		            color: 'white',
		            fontSize:15
		        }
		    };
		setting.color=['red', 'orange','yellow','blue'];  
/*		setting.legend= {
			left: 'center',
			data: ['红色','橙色','黄色','蓝色'],
			textStyle:{    //图例文字的样式
				color:'#fff',
			}
		};*/
		setting.series= [{
			name:'预警统计',
			type:'pie',
			center : ['51%', '50%'],
			radius : 60,
			itemStyle : {
				normal : {
					label : {
						position : 'inner',
						formatter : function (params) {                         
						  return (params.percent - 0).toFixed(0) + '%'
						}
					},
					labelLine : {
						show : false
					}
				},
				emphasis : {
					label : {
						show : true,
						formatter : "{b}\n{d}%"
					}
				}
			},
			data: data.list
		}];
		var myChart = echarts.init(document.getElementById("chyj"));
			myChart.setOption(setting);
			myChart.on("click", function(pm){
				warnStsColumn(pm.name);
		});
	};
	
	
	/**
	 * 预警统计柱状图
	 */
	var warnStsColumn = function(state){
		var data="";
		if(state==null){
			data=JSON.parse(stsData.WARNSTSCOLUMN);
		}else if(state=="红色"){
			data=JSON.parse(stsData.WARNSTSRED);
		}else if(state=="橙色"){
			data=JSON.parse(stsData.WARNSTSORANGE);
		}else if(state=="黄色"){
			data=JSON.parse(stsData.WARNSTSYELLOW);
		}else if(state=="蓝色"){
			data=JSON.parse(stsData.WARNSTSBLUE);
		}else if(state=="综合"){
			data=JSON.parse(stsData.WARNSTSGREEN);
		}
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
             bottom: 55,
             textStyle: {
                 color: "#fff"
             }
         };
         setting.dataZoom= [{
             show: false,
             height: 30,
             xAxisIndex: [0],
   /*          start: 30,*/
/*             end: 70,*/
//	                 handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
             handleSize: '110%',
             handleStyle: {
                 color: "#d3dee5",
             },
             textStyle: {
                 color: "#fff"
             },
             borderColor: "#12315b"
         }, {
             type: "inside",
             show: true,
             height: 15,
//	                 start: 1,
//	                 end: 35
         }];
         setting.legend= {
             x:'right',
             textStyle: {
                 color: '#d7e7e6',
             },
             data: ['红色', '橙色', '黄色', '蓝色', '综合']
         };	      
         setting.series= [{
	             name: "红色",
	             type: "bar",
	             stack: "总量",
	             barWidth : 40,
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
                 barWidth : 40,
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
                 barWidth : 40,
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
                 barWidth : 40,
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
                 name: "综合",
                 type: "bar",
                 stack: "总量",
                 barWidth : 40,
                 itemStyle: {
                     "normal": {
                         "color": "green",
                         "barBorderRadius": 0,
                         "label": {
                             "position": "top",
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
		 echarts.init(document.getElementById("chyjzx")).setOption(setting);
		};
		/**
		 * 渠道状态Option值获取
		 */
		var channelOption=function(list){
			$.each(list,function(i,item){
				 var setting=Settings(item)
				 setting.color= ['red','orange','yellow','blue','green']; 
				 setting.tooltip= {
				        trigger: 'item',
				        formatter: "{a} <br/>{b}: {c} ({d}%)",
			        	position: function (pos, params, dom, rect, size) {
		        	      // 鼠标在左侧时 tooltip 显示到右侧，鼠标在右侧时 tooltip 显示到左侧。
		        	      var obj = {top: 60};
		        	      obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 5;
		        	      return obj;
			        	 }
				    };
				 setting.series =[{
   			            name:'影响渠道',
   			            type:'pie',
   			            radius: ['40%', '65%'],
   			            avoidLabelOverlap: false,
   			            label: {
   			                normal: {
   			                    show: false,
   			                    position: 'center'
   			                },
   			                emphasis: {
   			                    show: true,
   			                    textStyle: {
   			                        fontSize: '10',
   			                        fontWeight: 'bold'
   			                    }
   			                }
   			            },
   			            labelLine: {
   			                normal: {
   			                    show: false
   			                }
   			            },
   			            data:item.list,
		   			}];
					 echarts.init(document.getElementById("channel_"+item.channelId)).setOption(setting);
				 });
			};  
		/**
		 * 影响渠道后台获取数据
		 */
		var influenceChannel=function(data){
			var count=data.count;
			var list=data.list;
			var html="";
			if(list.length>0){
			for(var i=0;i<list.length;i++){
				html+="<dl >";
				html+="	<div class='zdbox'>";
				html+="		<div class='topbox'>";
				html+="			<span class='text'  >"+list[i].name+"</span>";
				html+="		</div>";
				html+="		<div class='middlebox'  id='channel_"+list[i].channelId+"'  >";
				html+="		</div>";
		    	html+="	</div>";
				html+="</dl>";
			}
		 }
		 $(".tempWrapParent .tempWrap").empty().prepend(html);
		 //渠道状态Option
		 channelOption(list);
		};
		/**
		 * 事件评估
		 */
		var eventAssess = function (list){
			var dister=JSON.parse(list.ALREADYWARNNUM).option;
			var keyid=list.MAPEVENTID;
			var earlyWarningNum=JSON.parse(list.ALREADYWARNNUM).count;
			var influenceChannelNum=JSON.parse(list.CHANNELSTATE).count;
			var influenceAreaNum=JSON.parse(list.INFLUENCEAREA).area.length;
			var influenceAreaNum=JSON.parse(list.INFLUENCEAREA).area.length;
			var partOrgNum=JSON.parse(list.PARTICIPATEORG).area.length;
			var warnDayNum=JSON.parse(list.WARNSTSCOLUMN).area.length;
			var personNum=list.PERSONNUM ;
			var areaSize=list.AREASIZE ;
			var gdpValue=list.GDPVALUE ;
			if(personNum == undefined || personNum == "undefined"){
				personNum="";
			}
			if(areaSize == undefined || areaSize == "undefined"){
				areaSize="";
			}
			if(gdpValue == undefined || gdpValue == "undefined"){
				gdpValue="";
			}
			var html="";
				html +="<div class='fb-text'>";
				html +=		"涉事预警总数<span class='ftcl-10 earlyWarningNum'>"+earlyWarningNum+"</span>个，";
				for(var i=0;i<dister.length;i++){
					if(dister[i].count !=0){
						html +=	""+dister[i].typeName+"<span class='ftcl-10 earlyWarningNum'>"+dister[i].count+"</span>个，";
					}
				}
				html +=		"影响渠道<span class='ftcl-10 influenceChannelNum'>"+influenceChannelNum+"</span>个，";
				html +=     "影响区域<span class='ftcl-10 influenceAreaNum'>"+influenceAreaNum+"</span>个，" ;
				html +=     "参与单位<span class='ftcl-10 partOrgNum'>"+partOrgNum+"</span>个，";
				html +=     "预警天数<span class='ftcl-10 warnDayNum'>"+warnDayNum+"</span>天";
				/*html +=     "影响人口：<input id='personNum' type='text' title='"+personNum+"' value='"+personNum+"' /> 万人，";
				html +=     "影响面积：<input id='areaSize' type='text' title='"+areaSize+"' value='"+areaSize+"'  /> km²，";
				html +=     "损失GDP： <input id='gdpValue' type='text' title='"+gdpValue+"' value='"+gdpValue+"'  /> 亿元 ";*/
				html +=     "<input id='eventKeyId' type='hidden' value=''  />";
				html +="</div>";
			$("#responsibility").empty().append(html);
			$("#eventKeyId").val(keyid);
			
/*			$("#personNum").keyup(function () {
		        this.value = this.value.replace(/[^\d]/g, '');
			})
		    $("#areaSize").keyup(function () {
		    	  this.value = this.value.replace(/[^\d]/g, '');
		    })
		    $("#gdpValue").keyup(function () {
		    	  this.value = this.value.replace(/[^\d]/g, '');
		    })*/
			//添加影响内容
			$(".contentxd .con-middle .contentpd-2 .fb-text input").blur(function(){
				var personNum=$("#personNum").val().replace(/[^\d]/g, '');
				var areaSize=$("#areaSize").val().replace(/[^\d]/g, '');
				var gdpValue=$("#gdpValue").val().replace(/[^\d]/g, '');
				var keyId=$("#eventKeyId").val();
				var param={"personNum":personNum,"areaSize":areaSize,"gdpValue":gdpValue,"keyId":keyId};
				$.ajax({
					async:false,
			        type: "POST",
			        url: baseUrl+"event/updateInfluence",
			        data: param,
			        dataType: "json",
			        success: function(data){
			        }
				})
			});
			
		};	
		
		/**
		 * 接收人地图展示
		 */
		var person = function(state){
			//默认选中图例
			var state=state,XMData = [],geoCoordMap={};
			var geoCoordMapAll=function(hunanJson){
				geoCoordMap = {"湖南省":hunanJson.cp};
				var arry=hunanJson.features;
				for(var i=0;i<arry.length;i++){
				    geoCoordMap[arry[i].properties.name]=arry[i].properties.cp;
				}
				return geoCoordMap;
			};
	        var convertData = function (data) {
	            var res = [];
	            for (var i = 0; i < data.length; i++) {
	                var dataItem = data[i];
	                var fromCoord = geoCoordMap[dataItem[0].name];
	                var toCoord = geoCoordMap[dataItem[1].name];
	                if (fromCoord && toCoord) {
	                    res.push({
	                        fromName: dataItem[0].name,
	                        toName: dataItem[1].name,
	                        coords: [fromCoord, toCoord]
	                    });
	                }
	            }
	            return res;
	        };
	        var color = ['red', 'orange', 'yellow','blue','green'];
	        var seriesAll=function(XMData){
	        	var series = [];
	        	[['湖南', XMData]].forEach(function (item, i) {
	                var markLine = {
	                    type: 'lines',
	                    zlevel: 1,
	                    effect: {
	                        show: true,
	                        period: 6,
	                        trailLength: 0.7,
	                        color: '#fff',
	                        symbolSize: 3
	                    },
	                    lineStyle: {
	                        normal: {
	                            color: color[2],
	                            width: 0,
	                            curveness: 0.2
	                        }
	                    },
	                    data: convertData(item[1])
	                };

	                var markBoldLine = {
	                    type: 'lines',
	                    zlevel: 2,
	                    effect: {
	                        show: true,
	                        period: 6,
	                        trailLength: 0,
	                        symbolSize: 5
	                    },
	                    lineStyle: {
	                        normal: {
	                            color: color[2],
	                            width: 1,
	                            opacity: 0.4,
	                            curveness: 0.2
	                        },
	                        emphasis : {
	                            color: color[2],
	                            width: 3
	                        }
	                    },
	                    data: convertData(item[1])
	                };

	                var markPonit = {
	                    type: 'effectScatter',
	                    mapType: '湖南',
	                    coordinateSystem: 'geo',
	                    zlevel: 2,
	                    symbol:'circle',
	                    rippleEffect: {
	                        period: 2,
	                        scale: 50,
	                        brushType: 'fill' // fill
	                    },
	                    label: {
	                        normal: {
	                            show: true,
	                            position: 'right',
	                            formatter: '{b}'
	                        }
	                    },
	                    symbolSize: function (val) {
	                        return 1;
	                    },
	                    itemStyle: {
	                        normal: {
	                            color: color[2]
	                        }
	                    },
	                    data:  item[1].map(function (dataItem) {
	                        return {
	                            name: dataItem[1].name,
	                            value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value])
	                        };
	                    })
	                };
	                series.push(markLine,markBoldLine,markPonit);
	            });
	        	return series;
	        };
	        var setting=function(XMData){
	            var option = {
			            tooltip : {
			                trigger: 'item',
		                	formatter: function(params) {
			                	var myseries = option.series;
		                		var pointData=myseries[2].data;
			                	if(state=="1"){
			                		for(var i=0;i<pointData.length;i++){
			                			if(pointData[i].name==params.data.toName){
			                				return params.data.toName+ "<br />"+"责任人:"+pointData[i].value[2]+"人";
			                			}
			                		}
			                	}else{
			                		for(var i=0;i<pointData.length;i++){
			                			if(pointData[i].name==params.data.toName){
			                				return params.data.toName+ "<br />"+"终端:"+pointData[i].value[2]+"个";
			                			}
			                		}
			                	}
			                }
			            },
			            geo: {
			                map: 'hunan',
			                aspectScale: 0.75, // 地图窗宽比
			                zoom: 1.2,  // 地图缩放比
			                regions: [{  
			                    name: '湖南',
			                    itemStyle: {
			                        normal: {
			                            areaColor: 'white',
			                            color: 'white'
			                        }
			                    }
			                }],
			                label: {
			                    emphasis: {
			                    	color:"#FFF",
			                        show: true
			                    }
			                },
			                roam: true,
			                itemStyle: {
			                    normal: {
			                        areaColor: 'rgba(56, 139, 158, 0.80)',
			                        borderColor: 'white'
			                    },
			                    emphasis: {
			                        areaColor: 'rgba(56, 139, 158, 0.80)'
			                        //borderColor: 'orange'
			                    }
			                }
			            },
			            series: seriesAll(XMData)
			        };
	    		return option;
	    	}
	        var json=JSON.parse(stsData.PERSON);
			var respons=json.respons;
			var audience=json.audience;
			if(state=='1'){
				XMData=[];
					for(var i=0;i<respons.length;i++){
						XMData.push(
							[{name:'长沙市'}, respons[i]]
						);
					}
				}else if(state=='2'){
					XMData=[];
					for(var i=0;i<audience.length;i++){
						XMData.push([{name:'长沙市'}, audience[i]]);
					}
				};
				var resNum = 0,audNum = 0; 
				for(var j=0;j<respons.length;j++){
					resNum +=parseInt(respons[j].value);
				}
				for(var k=0;k<audience.length;k++){
					audNum +=parseInt(audience[k].value);
				}
				$(".personMap .respons").text("责任人("+resNum+")人");
				$(".personMap .audience").text("终端("+audNum+")个");
				//地图显示
				$.get('resources/map/gis/data/geojson/hunan.json', function (hunanJson) {
					geoCoordMapAll(hunanJson);
					var myChart = echarts.init(document.getElementById('responsibility'));
		        	echarts.registerMap('hunan', hunanJson) //注册
		        	myChart.setOption(setting(XMData),true);
				});
			};
			
			
	/**
	 * 影响区域
	 */
	var influenceArea = function(){
		var data="";
			data=JSON.parse(stsData.INFLUENCEAREA);
			data.type=2;
		var setting=Settings(data);
		setting.color=['red', 'orange','yellow','blue','green']; 
		setting.tooltip={
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    }; 
		setting.toolbox= {
	        	 top :10,
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
             data: data.area,
         }];
         setting.dataZoom= [{
             show: false,
             height: 30,
             xAxisIndex: [0],
             start: 30,
             end: 70,
             handleSize: '110%',
             handleStyle: {
                 color: "#d3dee5",
             },
             textStyle: {
                 color: "#fff"
             },
             borderColor: "#12315b"
         }, {
             type: "inside",
             show: true,
             height: 15,
      /*       start: 1,
             end: 35*/
         }];
         setting.series= [{
	             name: "红色",
	             type: "bar",
	             stack: "总量",
	             barWidth : 20,
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
                 barWidth : 20,
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
                 barWidth : 20,
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
                 barWidth : 20,
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
	             name: "综合",
	             type: "bar",
	             stack: "总量",
	             barWidth : 40,
	             itemStyle: {
	                 normal: {
	                     color: "green",
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
		 echarts.init(document.getElementById("influenceArea")).setOption(setting);
	};
	
	/**
	 * 参与单位
	 */
	var participateOrg = function(){
		var data="";
			data=JSON.parse(stsData.PARTICIPATEORG);
			data.type=2;
		var setting=Settings(data);
		setting.color=['red', 'orange','yellow','blue']; 
		setting.tooltip={
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    }; 
		setting.toolbox= {
	        	 top :10,
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
             data: data.area,
         }];
         setting.dataZoom= [{
             show: false,
             height: 30,
             xAxisIndex: [0],
             handleSize: '110%',
             handleStyle: {
                 color: "#d3dee5",
             },
             textStyle: {
                 color: "#fff"
             },
             borderColor: "#12315b"
         }, {
             type: "inside",
             show: true,
             height: 15,
             start: 1,
             end: 35
         }];
         setting.series= [{
	             name: "红色",
	             type: "bar",
	             stack: "总量",
	             barWidth : 40,
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
                 barWidth : 40,
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
                 barWidth : 40,
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
                 barWidth : 40,
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
		 echarts.init(document.getElementById("participateOrg")).setOption(setting);
	};
    /**
     * 加载预警名称树
     */
    var initEarnName = function(){
    	var treeCheck = function(e, treeId, treeNode,clickFlag){
			var earnId = treeNode.id;
			var earnName = treeNode.name;
			$("#earnId").val(earnId);
			$("#earnName").val(earnName);
			$("#earnNameTree").hide();
			earnTree.checkNode(treeNode, !treeNode.checked, true);   
		}
    	var setting = {
			check: {enable: true,chkStyle: "radio",radioType: "level",
            chkboxType: { "Y": "s", "N": "s" }},
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
		         url: baseUrl+"entry/findDisasterTree",
		         data: {keyId:$("#orgId").val()},
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
    	var userareaId=$("#areaId").val();
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
					url: "area/find?loginUserAreaId="+userareaId,
					autoParam: ["id=parentId"]
				},
				check: {
					enable: true,
	                chkStyle: "checkbox",
	                radioType: "level",
	                chkboxType: { "Y": "ps", "N": "ps" }
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
    var initchannel = function(){
      	$.ajax({
      		async:false,
      		type:"POST",
      		url:baseUrl+"map/channelList",
      		dataType: "json",
      		success:function(data){
      			var html = "";
      			for(var i=0,len=data.length;i<len;i++){
      				var dataobj=JSON.stringify(data[i]);
      				html += "<dl data-channel='"+dataobj+"'><dt><img src='resources/map/images/channel/"+data[i].channelType+".png' /></dt><dd>"+data[i].name+"</dd></dl>";
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
    //取消事件保存弹框
    $(".warn-event-detail .pop-search .pop-search-content ul:first-child  span:last-child").on("click",function () {
    	$(".warn-event-detail .pop-search").hide();
    });
    
    // 添加事件弹框
    $("#addEvent").bind("click",function () {
    	$(".top-center-left-icon .cls.lft").animate({"left":"23.7%"});
		$(".top-center-left-icon .top-center-left").fadeOut();
		$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(resources/map/images/event/sjx_rt.png)"});
    	$(".warn-event-detail .pop-search").show();
    });
    
    // 点击灾种区域以外的地方关闭灾种div
    $(document).bind("click",function(e){ 
    	var target = $(e.target); 
    	if(target.closest(".earn-name").length == 0){ 
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
     * 预警查询参数封装
     */
    var param = function(){
    	var p = {
    		startDate:null,
    		endDate:null,
        	name:null,
    		page:0,
    		offset:0,	
			pageSize:5,
    		orgId: $("#orgId").val(),
			startTime:$("#startTime").val(),
			endTime:$("#endTime").val(),
			disasterId:$("#earnId").val(),
			disasterName:$("#earnName").val(),
			warnColorNum: function(){ 
				var warnColorNum = "";
				$(".earn-color > div >ul").children("i").each(function(){
					warnColorNum +="," + $(this).parent().data("color");
				});
				return warnColorNum != "" ? warnColorNum = warnColorNum.substring(1) : warnColorNum ;
			}(),
			warnLevelNum: function(){
				var warnLevelNum= "";
				var lg=""; 
				$(".earn-color > div >ul").children("i").each(function(){
					var num = $(this).parent().data("color");
					if(num=="1") {lg="5"}
		    		if(num=="2") {lg="6"}
		    		if(num=="3") {lg="7"}
		    		if(num=="4") {lg="8"}
		    		if(num=="9") {lg="10"}
		    		warnLevelNum +="," + lg;
				});
				return warnLevelNum != "" ? warnLevelNum = warnLevelNum.substring(1) : warnLevelNum ;
			}(),
			channelId: function(){
				var channelId = "";
				$(".pop-content-channel > dl").children("i").each(function(){
					channelId += "," + $(this).parent().data("channel").keyId;
				});
				return channelId != "" ? channelId = channelId.substring(1) : channelId ;
			}(),
			areaId : function(){
				var areaId = "";
				var treeObj = $.fn.zTree.getZTreeObj("areaTree");
				var nodes=treeObj.getCheckedNodes(true);
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

    
    // 保存事件取消点击
    $("#saveEvent").bind("click", function(){
    	if($("#eventName").val()==""){
		  	var promptBox=box();
		    promptBox.content="请填写事件名称";
		    Move.pop(promptBox);
    		return false;
    	}
     	var pm = param();
    	pm.name=$("#eventName").val()
    	//后台保存方法
        $.ajax({
            async: false,
            type: "POST",
            url: "event/add",
            data: pm,
            dataType: "json",
	        beforeSend:function(XMLHttpRequest){ 
	        	$("#eventSave").attr('disabled',"true");
	        },
            success: function (data) {
            	var promptBox=box();
			    promptBox.content="保存成功";
	         	promptBox.contentIcon="fa-check-circle";
			    Move.pop(promptBox);
            	$("#eventSave").removeAttr("disabled"); 
            	$(".add_setting-list").hide();
            	$("#eventName").val("");
            },
            error: function (e) {
            	var promptBox=box();
        	    promptBox.content="保存失败";
			    Move.pop(promptBox);
            	return false;
            }
        });
    	$(".warn-event-detail .pop-search").hide();
    	eventHtml(0,null);
    });
    
    // 清空事件条件
    $("#clear").bind("click", function(){
    	$("#eventName").val("");  	// 清空事件名称
    	$("#startTime").val("");  	// 清空开始时间
    	$("#endTime").val("");		// 清空结束时间
    	$("#earnName").val("");		// 清空灾种名称
    	$("#earnId").val("");		// 清空灾种id
    	// 预警颜色默认还原
        $(".earn-color > div > ul").each(function(){
        	$(this).parent().find(".checked").remove();
        	$(this).append(initParam.checked);
        	$("#earnLevel").val("");// 清空灾种级别
        });
        // 渠道清空还原
        $(".pop-content-channel > dl").each(function(){
        	$(this).children("i").remove();
        });
        // 地区清空还原
        var treeObj = $.fn.zTree.getZTreeObj("areaTree");
        treeObj.checkAllNodes(false);
    });
    
    /**
     * 温馨提示框
     */
    var box=function(){
		  return {
			   	"titleIcon":"fa-commenting",
				"title":"温馨提示",
				"contentIcon":"fa-warning",
				"btnGroup":[{
					"cls":"pop-btn-close",
	    			"value":"确定"
	    		}]
			};
	   };
   //导航栏模块图标显示
    var modularShow=function(){
    	var html="";
	    	html += "<li title='事件切换' class='nav-item mr-1 nav-menu-7 model-1'><i id=''class='fa fa-list' aria-hidden='true'></i></li>";
	    	html += "<li title='涉事预警' class='nav-item mr-1 nav-menu-7 model-2'><i id=''class='fa fa-pie-chart' aria-hidden='true'></i></li>";
	    	html += "<li title='传播渠道' class='nav-item mr-1 nav-menu-7 model-3'><i id=''class='fa fa-stack-overflow' aria-hidden='true'></i></li>";
	    	html += "<li title='事件汇总' class='nav-item mr-1 nav-menu-7 model-4'><i id=''class='fa fa-area-chart' aria-hidden='true'></i></li>";
	    	html += "<li title='参与单位' class='nav-item mr-1 nav-menu-7 model-5'><i id=''class='fa fa-briefcase' aria-hidden='true'></i></li>";
	    	html += "<li title='影响区域' class='nav-item mr-1 nav-menu-7 model-6'><i id=''class='fa fa-bar-chart' aria-hidden='true'></i></li>";
	    	html += "<li title='事件评估' class='nav-item mr-1 nav-menu-7 model-7'><i id=''class='fa fa-list-alt' aria-hidden='true'></i></li>";
	    	html += "<li title='全部展示' class='nav-item mr-1 nav-menu-6 model-8'><i id=''class='fa fa-arrows-alt' aria-hidden='true'></i></li>";
	    	html += "<li title='全部关闭' class='nav-item mr-1 nav-menu-6 model-9' style='display:none'><i id=''class='fa fa-compress' aria-hidden='true'></i></li>";
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
        			$(".lf-1").animate({"left":"-3px"});
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
    	/*		if($(".top-center-left-icon .top-center-left").is(":hidden")){
    				$(".top-center-left-icon .cls.lft").animate({"left":"23.7%"});
    			}else{
    				$(".top-center-left-icon .top-center-left").animate({"left":"23.7%"});
    				$(".top-center-left-icon .cls.lft").animate({"left":"38.7%"});
    			}*/
    			
    		//左中（显示）	
    		}else if($(this).hasClass("model-2")){
    			//打开关闭
    			$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
    			if(lefthidden2=="close"){
    				$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
    				$(".lf-2").fadeIn("slow");
        			$(".lf-2").animate({"left":"-3px"});
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
    			/*if($(".top-center-left-icon .top-center-left").is(":hidden")){
    				$(".top-center-left-icon .cls.lft").animate({"left":"23.7%"});
    			}else{
    				$(".top-center-left-icon .top-center-left").animate({"left":"23.7%"});
    				$(".top-center-left-icon .cls.lft").animate({"left":"38.7%"});
    			}*/
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
        			$(".rt-2").animate({"right":"-3px"});
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
    			/*if($(".top-center-right-icon .top-center-right").is(":hidden")){
    				$(".top-center-right-icon .cls.rt").animate({"right":"23.7%"});
    			}else{
    				$(".top-center-right-icon .top-center-right").animate({"right":"23.7%"});
    				$(".top-center-right-icon .cls.rt").animate({"right":"38.7%"});
    			}*/
			//右上 （显示）	
    		}else if($(this).hasClass("model-7")){
    			//打开关闭
    			$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
    			if(righthidden1=="close"){
    				$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
        			$(".rt-1").fadeIn("slow");
        			$(".rt-1").animate({"right":"-3px"});
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
    /*			if($(".top-center-right-icon .top-center-right").is(":hidden")){
    				$(".top-center-right-icon .cls.rt").animate({"right":"23.7%"});
    			}else{
    				$(".top-center-right-icon .top-center-right").animate({"right":"23.7%"});
    				$(".top-center-right-icon .cls.rt").animate({"right":"38.7%"});
    			}*/
    		}else if($(this).hasClass("model-8")){
    			$(".modular-show li").removeClass("nav-menu-active");
    			$(".modular-show li").addClass("nav-menu-7");
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
				$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(resources/map/images/event/sjx_rt.png)"});
    			
    			$(".top-center-right-icon .cls.rt").animate({"right":"23.7%"});
				$(".top-center-right-icon .top-center-right").fadeOut();
				$(".top-center-right-icon .cls.rt > span").css({"background-image": "url(resources/map/images/event/sjx_lt.png)"});
				$(this).hide();
    			$(".modular-show .model-9").show();
    		}else if($(this).hasClass("model-9")){
    			$(".modular-show li").removeClass("nav-menu-active");
    			$(".modular-show li").addClass("nav-menu-7");
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
    			
    			$(".top-center-left-icon .cls.lft > span").css({"background-image": "url(resources/map/images/event/sjx_rt.png)"});
    			$(".top-center-right-icon .cls.rt > span").css({"background-image": "url(resources/map/images/event/sjx_lt.png)"});
    			$(this).hide();
				$(".modular-show .model-8").show();
    		}
        })
    }
	//查询事件列表信息
	var warnBindMap=function(searchParam){
		if(searchParam!=""){
			eventData(searchParam);//查询对应统计信息
//			loadWarningData(searchParam,3);//预警图标加载地图上  3:表示点击事件按钮
		}
	}
	
/*	modularShow();
    initEarnName();						// 初始化灾种数据(查询条件)
    initAreaTree();						// 初始化地区信息树
    initchannel();						// 初始化渠道信息
	eventHtml(0,null); 					// 显示事件列表
	warnBindMap(searchParam);			// 查询事件列表预警信息
*/	});	