$(function(){
	//严格模式
	"use strict";
	/**
	 * 所有统计图Option函数
	 * @author lxv
	 * @return option
	 */
	var typhoonModel= "close"	//台风
	,arrayBh = []		//台风列表数组
	,typhoonSelect = ""	//台风是否选择
	,radarValue1=1				//雷达定时数
	,cloudValue1=1				//卫星云图定时数
	,initParam = {
	        "checked":"<i class='fa fa-check checked'></i>", 	// 勾选样式
	        "flag": null,											// 预警唯一标识
	        "terminal": null,										// 终端信息
	        "warnPic": "",	// 预警图片
	        "orgId":$("#orgId").val(),								// 机构ID
	        "sensitiveWord" :null,									// 敏感字
	        "overlookedWord" :"",									// 已忽略敏感字
        	"userTypeId":"",
            "selectTime": "", // 定义全局变量（选择时间值）
	 	}
	//预警统计信息总div内容
	,warnHomeContent = $("<div class='map-warn-home'>" +
//			"<div class='naviLoading'></div>" +							// 预警图标
			"</div>")
    /**
	 * 预警列表 
	 * @param NULL
	 * @property warnList
	 * @author lxv
	 * @return 对象
	 */	
	,warnList = function(){
		return $("<div class='pop-public-btn-icon pop-warn-icon div-three' style='display:none'><i class='fa fa-warning' ></i>预警列表</div>" +
				"<div class='add_box div-three' >" +
					"<div class='pop-warn-title'>" +
						"<span><i class='fa fa-warning'></i>预警列表</span>" +
						"<span class='ico right fa-chevron-left' id='leftWarnClose'  ></span>" +
					"</div>" +
					"<div class='pop-warn'></div>" +
				"</div>");
		}()
    /**
	 * 资源展示 
	 * @param NULL
	 * @property resources
	 * @author lxv
	 * @return 对象
	 */	
	,resources = function(){
		return $("<div class='pop-public-btn-icon pop-resources-icon ' style='display:none'><i class='fa fa-industry'></i>资源展示</div>" +
				 "<div class='zyzs-relt '>" +
	                "<div class='shade div-three ' style='display:none'></div>" +
	                "<div class='resource-content div-three' style='display:none'>" +
	                "<div class='resource-selecttop'><div class='resourceitme'></div></div>" +
	                "<div class='resource-body'></div>" +
                 "</div>" +
                 "<div class='pop-resourcesall div-two'>" +
	                 "<div class='pop-resources-title'>" +
						"<span><i class='fa fa-industry'></i>资源展示</span>" +
						"<span class='ico right ' id='leftResourcesClose'></span>" +
					 "</div>" +
					 "<div class='pop-resources'>" +
						"<div class='resources-content'></div>" +
						"<div class='resources-slet'>" +
			                "<ul>" +
				                "<span><i class='fa fa-square-o'></i>全选</span>&nbsp;&nbsp;" +
				                "<span><i class='fa fa-check-square-o checked'></i>反选</span>&nbsp;&nbsp;" +
			                "</ul> " +
		                "</div>" +
	            	 "</div>" +
        		"</div>");
		}()
    /**
	 * 预警发布 
	 * @param NULL
	 * @property warnPublish
	 * @author lxv
	 * @return 对象
	 */	
	,warnPublish = function(){
		return $("<div class='pop-public-btn-icon pop-warnpublish-icon ' style='display:none'><i class='fa fa-pencil-square-o'></i>精准发布</div>" +
			     "<div class='fz accurate-fb div-two'>" +
			     	"<div class='flbox'>" +
				        "<div class='top'>" +
				            "<div class='title-top bgcl-1'>" +
				                "<div class='ttf'>" +
				                    "<span class='ico left fa-pencil-square-o'></span>" +
				                    "<span class='pbtitle'> 精准发布</span>" +
				                "</div>" +
				                "<div class='ttr'>" +
				                    "<span class='ico right fa-chevron-left'></span>" +
				                "</div>" +
				            "</div>" +
				        "</div>" +
				        "<div class='midlle'>" +
				            "<div class='autogd'>" +
				                "<div class='fzcontent '>" +
				                    "<div class='fb-text'>" +
				                    "大喇叭<span class='ftcl-10 trumpetNum'>0</span>个，" +
				                    "显示屏<span class='ftcl-10 displayNum'>0</span>个，" +
				                    "责任人<span class='ftcl-10 responsNum'>0</span>人，" +
				                    "受众<span class='ftcl-10 audienceNum' >0</span>人，" +
				                    "地区市<span class='ftcl-10 cityNum' >0</span>个，" +
				                    "地区县<span class='ftcl-10 countyNum' >0</span>个，" +
				                    "面积<span class='ftcl-10 areaSize'>0</span>km²，" +
				                    "GDP<span class='ftcl-10 gdpValue'>0</span>亿" +
				                    "</div>" +
				                    
				                    "<input id='areaCodes' type='hidden' value=''/>" +
				                    "<input id='selectedAreaName' type='hidden' value=''/>" +
				                    "<input id='cityCodes' type='hidden' value=''/>" +
				                    "<input id='countyCodes' type='hidden' value=''/>" +
				                    "<input id='almSerialNo' type='hidden' value=''/>" +//期数
				                    "<input id='nextProcess' type='hidden' value=''/>" +//是否保存待发 
				                    "<input id='isBCDFFlag' type='hidden' value=''/>" +//保存待发标志 
				                "</div>" +
				                "<div class='fzitem'>" +
				                    "<div class='title-top bgcl-4'>" +
				                        "<div class='ttf'>" +
				                            "<span class='ico left fa-arrows'></span>" +
				                            "<span class='pbtitle'>基础信息</span>" +
				                        "</div>" +
				                    "</div>" +
				                "</div>" +
				                "<div class='fzcontent '>" +
				                    "<!--发布基础信息列表-->" +
				                    "<div class='fb-base publishmsg' >" +
				                        "<ul class='publishbase'>" +
				                            "<li>" +
				                                "<div class='company'>" +
				                                    "<span class='fb-form-text'>录入单位：</span>" +
				                                    "<span class='fb-form-input'><input class='form-control' type='text' value='' readonly='readonly' style='background-color: #190fab;' ></span>" +
				                                "</div>" +
				                                "<div class='disaster-select'>" +
				                                	"<input id='disasterId' type='hidden'/>" +
				                                    "<span class='fb-form-text'>灾种名称：</span>" +
				                                    "<span class='fb-form-input'>" +
				                                    	"<input class='form-control' id='disasterName' type='text' placeholder='灾种名称' value=''>" +
				                                    "</span>" +
				                                    "<div id='disasterNameTree' class='disaster-name-tree ztree' data-display='hidden'></div>" +
			                                    "</div>" +
				                            "</li>" +
				                            "<li>" +
				                                "<div>" +
				                                    "<span class='fb-form-text'>灾种颜色：</span>" +
				                                	"<input class='form-control'  id='colorLevel' type='hidden' value=''   >" +
				                                	"<input class='form-control'  id='discolor' type='hidden' value=''   >" +
				                                    "<span class='fb-form-input'>" +
				                                    	"<div class='selectcl'>" +
				                                        	"<ul class='clslct'>" +
				                                            	"<li>" +
				                                                	"<span class='fa-check' style='background: red'>&nbsp;</span>" +
				                                                "</li>" +
				                                                "<li>" +
				                                                	"<span class='fa-check' style='background: orange'>&nbsp;</span>" +
				                                                "</li>" +
				                                                "<li>" +
		                                                        	"<span class='fa-check' style='background: yellow'>&nbsp;</span>" +
				                                                "</li>" +
				                                                "<li>" +
				                                                    "<span class='fa-check' style='background: blue'>&nbsp;</span>" +
				                                                "</li>" +
				                                                "<li>" +
			                                                		"<span class='fa-check' style='background: GREEN'>&nbsp;</span>" +
			                                                	"</li>" +
				                                            "</ul>" +
				                                        "</div>" +
				                                    "</span>" +
				                                "</div>" +
				                                "<div>" +
				                                    "<span class='fb-form-text'>预警级别：</span>" +
				                                    "<span class='fb-form-input'>" +
				                                    	"<input class='form-control'  id='publishlevelId' type='hidden' value=''   >" +
				                                    	"<input class='form-control'  id='publishlevel' type='text' value='' readonly='readonly' style='background-color: #190fab;' >" +
				                                    "</span>" +
				                                "</div>" +
				                            "</li>" +
				                            "<li>" +
				                                "<div>" +
				                                    "<span class='fb-form-text'>预警类型：</span>" +
				                                    "<span class='fb-form-input'>" +
				                                    	"<select class='form-control' id='warnType'> " +
				                                    		"<option value='2'>测试预警</option>" +
				                                    		"<option value='1'>正式预警</option>" +
				                                    	"</select>" +
				                                    "</span>" +
				                                "</div>" +
				                                "<div>" +
				                                    "<span class='fb-form-text'>持续时间：</span>" +
				                                    "<span class='fb-form-input'>" +
				                                    	"<input class='form-control' style='width: 100%;' id='continueTime' type='text' value='24'>" +
				                                	"</span>" +
				                                "</div>" +
				                            "</li>" +
				                            "<li>" +
					                            "<div class='publishTime' >" +
				                                    "<span class='fb-form-text'>发布时间：</span>" +
				                                    "<span class='fb-form-input'>" +
							                        	"<input id='publishTime' class='form-control' type='text' value='' title=''>" +
							                        "</span>" +
						                        "</div>" +
						                        "<div class='publisharea-select'>" +
							                        "<input id='publishAreaId' type='hidden'/>" +
				                                    "<span class='fb-form-text'>地区名称：</span>" +
				                                    "<span class='fb-form-input'>" +
				                                    	"<input class='form-control' id='publishAreaName' type='text' placeholder='地区名称' value='' title=''>" +
				                                    "</span>" +
				                                    "<div id='publishAreaTree' class='publish-area-tree ztree' data-display='hidden' ></div>" +
						                        "</div>" +
				                            "</li>" +
				                            
				                        /*    "<li style='width: 100%;display: inline-block;'>" +
			                                    "<span class='fb-form-text yearStage' style=' display: inline-block; padding: 8px 5px; width: 18.5%;float: left;' >2018年&nbsp;&nbsp;&nbsp;&nbsp;总</span>" +
			                                    "<span class='fb-form-text' style=' display: inline-block; padding: 5px 0px; width: 26.5%;float: left;'>" +
						                        	"<input class='form-control' id='allStageNum' type='text' value=''  >" +
						                        "</span>" +
						                        "<span class='fb-form-text'  style=' display: inline-block; padding: 8px 0px; width: 5%;float: left;'>期</span>" +
			                                    
						                        "<span class='fb-form-text warnStage'  style=' display: inline-block; padding: 8px 4px; width: 19.5%;float: left;' >XX预警&nbsp;&nbsp;&nbsp;&nbsp;第</span>" +
			                                    "<span class='fb-form-text'  style=' display: inline-block; padding: 5px 0px; width: 25.5%; float: left;'>" +
			                                    	"<input class='form-control' id='stageNum' type='text'  value=''>" +
			                                    "</span>" +
			                                    "<span class='fb-form-text'  style=' display: inline-block; padding: 8px 0px; width: 5%;float: left;'>期</span>" +
		                                    "</li>" +*/
		                                    
				                            "<li>" +
				                                "<div class='publishMode'>" +
				                                    "<span class='fb-form-text'>发布类型：</span>" +
			                                		"<input  id='sendFlag' class='form-control'  type='hidden' value='2'   >" +
				                                    "<span class='fb-form-input'>"+
								                        "<div class='selectbox'>" +
								                            "<ul class='slctbox'>" +
								                                "<li>" +
								                                    "<span class='selectbox fa-check'></span>" +
								                                    "<span class='selecttitle'>定时发布</span>" +
								                                "</li>" +
								                            "</ul>" +
								                        "</div>" +
								                    "</span>" +
				                                "</div>" +
				                                "<div class='timingPublishTime' style='display:none'>" +
				                                    "<span class='fb-form-text'>定发时间：</span>" +
				                                    "<span class='fb-form-input'>" +
							                        	"<input id='timingPublishTime' class='form-control' type='text' value='' title=''>" +
							                        "</span>" +
						                        "</div>" +
				                            "</li>" +
				                            "<li>" +
	
				                                "<div class='record'>" +
			                                    	"<span class='fb-form-text'>国突备案：</span>" +
			                                    	"<span class='fb-form-input'>" +
						                            	"<label class='item-label'>" +
						                                	"<input class='item-radioInput' type='radio' name='demo-radio' value='1'>是" +
						                                "</label>" +
						                                "<label class='item-label'>" +
						                                	"<input class='item-radioInput' type='radio' name='demo-radio' value='0' checked='checked'>否" +
						                                "</label>" +
						                            "</span>" +
					                            "</div>" +
					                            
				                            "</li>" +
				                            
				                            /*"<li>" +
		                                    "<span class='selectbox fa-check'></span>" +
		                                    "<span class='selecttitle'>业务人员代办</span>" +
		                                "</li>" +
		                                "<li>" +
		                                    "<span class='selectbox fa-check'></span>" +
		                                    "<span class='selecttitle'>预警补发</span>" +
		                                "</li>" +
				                            
			                               "<div>" +
		                                    "<span class='fb-form-text'>发布方式：</span>" +
		                                    "<span class='fb-form-input'>" +
					                        	"<select class='form-control' id='publishType' >" +
					                                "<option value='0'>本级发布</option>" +
					                                "<option value='1'>委托发布</option>" +
					                            "</select>" +
					                        "</span>" +
		                                "</div>" +*/
				                          /*"<li>" +
				                                "<div class='remindChannel'>" +
				                                	"<span class='fb-form-text'>提醒渠道：</span>" +
				                                	"<input class='form-control'  id='remindChannels' type='hidden' value=''   >" +
				                                    "<span class='fb-form-input'>" +
							                         	"<div class='selectbox'>" +
								                            "<ul class='slctbox'>" +
								                                "<li>" +
								                                    "<span class='selectbox fa-check'></span>" +
								                                    "<span class='selecttitle'>短信</span>" +
								                                "</li>" +
								                                "<li>" +
								                                    "<span class='selectbox fa-check'></span>" +
								                                    "<span class='selecttitle'>报警器</span>" +
								                                "</li>" +
								                            "</ul>" +
								                        "</div>" +
								                    "</span>" +
				                                "</div>" +
				                            "</li>" +*/
				                        "</ul>" +
				                    "</div>" +
				                    "<!--发布基础信息列表end-->" +
				                "</div>" +
				                "<div class='fzitem'>" +
				                    "<div class='title-top bgcl-4'>" +
				                        "<div class='ttf'>" +
				                            "<span class='ico left fa-connectdevelop'></span>" +
				                            "<span class='pbtitle'>发布渠道</span>" +
				                            "<input class='form-control'  id='channelId' type='hidden' value='' >" +
				                        "</div>" +
				                        "<div class='ttr'>" +
			                                "<ul class='chanelSelect'>" +
				                                "<span class='all-channel'>全选</span>" +
				    		                    "<span class='un-all-channel'>反选</span>" +
			                                "</ul>" +
				                        "</div>" +
				                    "</div>" +
				                "</div>" +
				                "<div class='pop-warnPublish-content'>" +
					                "<div class='pop-warnPublish-channel'></div>" +
				                "</div>" +
				                "<div class='fzitem'>" +
				                    "<div class='title-top bgcl-4'>" +
				                        "<div class='ttf'>" +
				                            "<span class='ico left fa-arrows'></span>" +
				                            "<span class='pbtitle'>发布内容</span>" +
				                        "</div>" +
				                    "</div>" +
				                "</div>" +
				                "<div class='fbqdct'>" +
				                    "<div class='yjcontentqdtop cctqdlist'>" +
				                        "<ul>" +
				                        "</ul>" +
				                    "</div>" +
				                    // 预警显示内容
				                    "<div id='publishContent' class='yjct'  contenteditable='true'>" +
//				                    "测试测试测试测试测试测试测试测测试测试测试测试测试测试<span class='bubbling' contenteditable='false'>法轮功<span>【法轮功】是敏感字词，是否忽略<button>忽略</button></span></span>测试测试测试测试测试测试测试测试" +
				                    "</div>" +
				                "</div>" +
				            "</div>" +
				        "</div>" +
				        "<div class='bottom'>" +
				            "<div class='title-bottom bgcl-1'>" +
				                "<div class='float-right pd-right-3'>" +
				                    "<button class='btn btn-1 btn-green warnPublish'>发送</button>" +
				                    "<button class='btn btn-1 btn-green saveNoPub'>保存待发</button>" +
				                    "<button class='btn btn-1 btn-qingse  reset'>重置</button>" +
				                "</div>" +
				            "</div>" +
				        "</div>" +
				    "</div>" +
				    "<div class='sz personTree div-three'>" +
					    "<div class='sz-header'>" +
						    "<ul class='sz-icon'>&nbsp;<span class='ico left fa-connectdevelop'></span>&nbsp;&nbsp;<span class='pbtitle'>发布对象</span></ul>" +
						    "<ul class='sz-btn'><span>责任人</span><span>公众</span></ul>" +
					    "</div>" +
					    "<div id='responsTree' class='sz-content ztree'></div>" +//责任人
					    "<div id='audienceTree' class='sz-content ztree' style='display:none'></div>" +//受众
				    "</div>"+
				"</div>");
		}()
		
		/**
		 * 工具箱浮层 
		 * @param NULL
		 * @property toolfloats
		 * @author lxv
		 * @return 对象
		 */	
		,toolfloats = function(){
			// 已发布预警标题
			return $("<div class='cogs ' ><li><i class='fa fa-cogs'></i></li></div>" +
					"<div class='setting-list ' >" +
		         		"<ul class='setting-list-title'>" +
				        	"<span><i class='fa fa-cogs'></i>系统设置</span><span><i class='fa fa-close'></i></span>" +
				        "</ul>" +
				        "<div class='setting-list-content'>" +
				/*	        "<ul class='huatu-config-top' data-toggle='.setting-tool-top'>" +
					        	"<span>绘图配置</span><span>顶部</span>" +
					        "</ul>" +*/
					        "<ul class='huatu-config' data-toggle='.setting-tool'>" +
					        	"<span>绘图工具</span><span>关闭</span>" +
					        "</ul>" +
				        "</div>" +
				        "<ul class='setting-list-title-theme' >" +
			        		"<span><i class='fa fa-cogs'></i>地图样式</span><span></span>" +
				        "</ul>" +
				        "<div class='setting-map-choose' >" +
					        "<ul class='map-choose-ul'>" +
			        		"<span class='active' data-map-type='satellite'>卫星图</span>" +
			        		"<span class='' data-map-type='roadmap'>业务图</span>" +
			        		"<span class='' data-map-type='terrain'>地形图</span>" +
			        		"</ul>" +
			        	"</div>" +
				   /*   "<ul class='setting-list-title-theme' style='display:none'>" +
				        	"<span><i class='fa fa-cogs'></i>系统主题</span><span></span>" +
				        "</ul>" +
				        // 系统主题选择
				        "<div class='setting-list-title-theme-choose' style='display:none'>" +
			        		"<ul><i class='fa fa-check'></i></ul><ul></ul><ul></ul><ul></ul>" +
			        	"</div>" +*/
		        	"</div> " +
			        "<div class='setting-configs ' >" +
			            // 配置绘图工具开始 
			            "<div class='setting-tool'  >" +
				            "<div class='setting-title'>" +
					            "<span><i class='fa fa-archive'></i>绘图工具</span>" +
					            "<span data-toggle='.huatu-config'><i class='fa fa-close'></i></span>" +
				            "</div>" +
				            "<div class='setting-content'>" +
					            "<ul class='navbar-nav'>" +
						            "<li class='map-option cycle-check' title='半径圈选'>" +
							            "<i class='fa fa-circle-thin' aria-hidden='true'></i>" +
							            "<div class='div-three'>" +
								            "<ul>" +
									            "<li>" +
									            	"<label><input id='drawRadius' placeholder='请输入圈选半径' value=''/><span>KM</span></label><button id='draw' type='button'>圈选</button>" +
									            "</li>" +
									            "<li>" +
										            "<label>" +
											            "<i class='fa fa-circle-o' data-cycle='50'></i>50KM&nbsp;&nbsp;" +
											            "<i class='fa fa-circle-o' data-cycle='100'></i>100KM&nbsp;&nbsp;" +
											            "<i class='fa fa-circle-o' data-cycle='200'></i>200KM" +
										            "</label>" +
									            "</li>" +
								            "</ul>" +
							            "</div>" +
						            "</li>" +
						            "<li class='map-option' title='任意圈选'><i class='fa fa-moon-o' aria-hidden='true'></i></li>" +
						          /*  "<li class='map-option' title='矩形圈选'><i class='fa fa-square-o' aria-hidden='true'></i></li>" +
						            "<li class='map-option' title='两点距离'><i class='fa fa-arrows-h' aria-hidden='true'></i></li>" +
						            "<li class='map-option' title='文本输入'><i class='fa fa-font' aria-hidden='true'></i></li>" +
						            "<li class='map-option' title='擦除'><i class='fa fa-eraser' aria-hidden='true'></i></li>" +
						            "<li class='map-option' title='保存'><i class='fa fa-save' aria-hidden='true'></i></li>" +*/
						            "<li class='map-option' title='圈选删除'><i class='fa fa-trash-o' aria-hidden='true'></i></li>" +
					            "</ul>" +
				            "</div>" +
				        "</div>" +
	            	"</div>");
			}()
	/**
	 * 查询条件 
	 * @param NULL
	 * @property queryCriteria
	 * @author lxv
	 * @return 对象
	 */	
	,queryCriteria = function(){
		return $("<div class='pop-public-btn-icon pop-search-icon ' title='查询条件' style='display:none'><i class='fa fa-search'></i></div>" +
				 "<div class='pop-search div-two' >" +
	             "<div class='pop-search-content'>" +
	                "<ul><span><i class='fa fa-search'></i>查询条件</span><span><i class='fa '></i></span></ul>" +
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
	                        /*    "<li><input id='earnLevel' readonly='readonly' placeholder='预警级别'/><i class='fa fa-sort-numeric-asc'></i></li>" +
	                        "<li class='earn-type' ><span class='active'>预警类别</span>&nbsp;<i class='fa fa-circle checked'></i>实况资料&nbsp;<i class='fa fa-circle-o'></i>预报资料</li>" +*/
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
	                    "<ul id='search'>查询</ul><ul id='clear'>清空</ul>" +	
	                "</div>" +
	            "</div>" +
	        "</div>");
	}()
    /**
	 * 预警详情 
	 * @param NULL
	 * @property warnDetail
	 * @author lxv
	 * @return 对象
	 */	
	,warnDetail = function(){
		return $("<div class='warn-detail div-three'>" +
					"<div class='xqbigtitle fa-file-text-o'><span>预警详情</span></div>" +
				    "<div class='yjscro'>" +
						"<div class='xqct'></div>" +
				        "<div class='xqsmalltitle fa-pencil-square-o'><span>发布渠道<i class='font-yellow' id='channelCount'></i>个</span></div>" +
					    "<div class='xqct'></div>" +
				        "<div class='xqsmalltitle fa-font-awesome' id='areaStaticCount'><span>影响地区<i class='font-yellow'>0</i>个</span></div>" +
				        "<div class='xqct'></div>" +
				        "<div class='xqsmalltitle fa-user-circle-o' id='personLiable'><span>责任人<i class='font-yellow'>0</i>人</span></div>" +
				        "<div class='xqct'></div>" +
				        "<div class='xqsmalltitle fa-group' id='countAudience'><span>接收受众<i class='font-yellow'>0</i>人</span></div>" + 
				        "<div class='xqct' id='audiencediv'></div>" +
				    "</div>" +
				    
			        // 责任人详细列表
			        "<div class='personLiable-detail div-three' >" +
			        	"<div class='setting-list-title'><span><i class='fa fa-cogs'></i>详细列表</span></div>" +
			            "<div class='content'></div>" +
			        "</div>" +
			        // 公众详细列表 
			        "<div class='audience-detail div-three' >" +
			        	"<div class='setting-list-title'>" +
			                "<span><i class='fa fa-cogs'></i>详细列表</span><span><i class='fa fa-close'></i></span>" +
			            "</div>" +
			            "<div class='content'></div>" +
			        "</div>" +
				"</div>");
	}()
	/**
	 * 时间控件 
	 * @param NULL
	 * @property timeControl
	 * @author lxv
	 * @return 对象
	 */	
	,timeControl = function(){
		// 已发布预警标题
		return $("<div class='pop-right-bottom div-three'  style='display:none'>" +
					"<ul>" +
						"<input id='playerTime' onclick='laydate({ playerTime: true, format: 'YYYY/MM/DD HH' })'/>" +
						"<i class='fa fa-calendar' id='player-time'></i>" +
						"<div class='canlender-line'></div>" +
					"</ul>" +
					"<ul >" +
						"<li class='active' data-time='hour' >00<span></span></li><li>01<span></span></li>" +
						"<li >02<span></span></li><li >03<span></span></li>" +
						"<li >04<span></span></li><li >05<span></span></li>" +
						"<li >06<span></span></li><li >07<span></span></li>" +
						"<li >08<span></span></li><li >09<span></span></li>" +
						"<li >10<span></span></li><li >11<span></span></li> " +
						"<span>" +
							"<i class='fa fa-caret-left' title='向前'></i>" +
							"<i class='fa fa-caret-right' title='向后'></i>" +
						"</span>" +
					"</ul>" +
					"<ul><i id='play' class='fa fa-play-circle-o'></i></ul>" +
				"</div>");
	}()
	,typhoon = function(){
		return $("<div class='typhoonNewList div-three'  >" +
					"<div class='content' >" +
					"<div class='query-criteria' style=''>" +
						"<ul >" +
							"<li ><input id='typhoonStartTime' class='cursor-pointer' placeholder='开始时间'  value=''></li>" +
							"<li ><input id='typhoonEndTime' class='cursor-pointer' placeholder='结束时间'  value=''></li>" +
						"</ul>" +
						"<ul >" +
							"<li ><input id='typhoonName' class='cursor-pointer' placeholder='名称'  value=''></li>" +
							"<li ><input id='typhoonCode' class='cursor-pointer' placeholder='编号'  value=''></li>" +
							"<li ><span class='span02' ><i class='fa fa-search'  ></i></span></li>" +
						"</ul>" +
					"</div>" +
					"<div class='line'></div>" +
					"<div class='detail-list' >" +
						"<table class='typhoontable' >" +
							"<thead >" +
								"<tr >" +
									"<td >编号</td>" +
									"<td >名称 </td>" +
								"</tr>" +
							"</thead>" +
						"<tbody class='tbody-content'>" +
						"<tr>" +
							"<td style='display: inline-block;width: 100%;text-align: center;float: left;''>暂无数据</td>	" +
						"</tr>"+
						"</tbody>" +
						"</table>" +
						"<ul class='ul_btn' >	" +
						"<span id='typhoonNextPage' data-next-page='0' data-count='0' >下页</span>" +
						"<span id='typhoonTopPage' data-top-page='0' data-count='0' >上页</span>	" +
						"<span >总数0条</span></ul>" +
					"</div>" +
					"</div>"+
				"</div>" +
				"<div class='typhoonlist div-three' id='typhoonlist' ></div>" +
				"<div class='typhoondata div-three' id='typhoondata' style='display: none;'></div>" +
				"<div id='menu' style='display: none; position: absolute;right: 0px;bottom: 50px;'>" +
					"<label><input type='checkbox' autocomplete='off' data-choosename='taifeng'><span>台风</span></label>" +
				"</div>" +		
				"<div class='J_legend legend_box div-three'>" +
					"<div class='legends taifeng show active' >" +
			        	"<div class='unit'>图例</div>" +
			            "<ul>" +
			                "<li><span>热带低压(级) 10.8-17.1米/秒</span></li>" +
			                "<li><span>热带风暴(级) 17.2-24.4米/秒</span></li>" +
			                "<li><span>强热带风暴(级) 24.5-32.6米/秒</span></li>" +
			                "<li><span>台风(级) 32.7-41.4米/秒</span></li>" +
			               " <li><span>强台风(级) 41.5-50.9米/秒</span></li>" +
			                "<li><span>超强台风(级) ≥51.0米/秒</span></li>" +
			                "<li><span>中国：</span></li>" +
			                "<li><span>香港：</span></li>" +
			                "<li><span>韩国：</span></li>" +
			                "<li><span>日本：</span></li>" +
			                "<li><span>台湾：</span></li>" +
			            "</ul>" +
			        "</div>" +
				"</div>");
	}()
	,navi = function(){
		return $(
				"<div class='navigation' style='position: absolute;top:43px;left:0px'>" +
				"<div id='navi_' title='自动导航' class='auto'></div>" +
				"<select id='disasterNavigation' style='display: none;'>" +
					"<option value='emergencyFacilities'>导航至:避难点</option>" +
					"<option value='hospital'>导航至:医院</option>" +
					"<option value='school'>导航至:学校</option>" +
				"</select>" +
				"<div class='routesInfo div-three' style='display: none;position: absolute; top: 52px; left: 0px;background: #183683; width: 500px;'>" +
					"<table id='tablestatics_tbody'>" +
						"<tbody>" +
							"<tr>" +
								"<td>路线</td>" +
								"<td>距离</td>" +
								"<td>用时</td>" +
								"<td>路径</td>" +
							"</tr>" +
						"</tbody>" +
						"<tr><td>A</td><td>7.85km</td><td>8.63分钟</td><td></td></tr>" +
						"<tr><td>C</td><td>10.23km</td><td>11.32分钟</td><td>环岛东路,</td></tr>" +
						"<tr><td>B</td><td>13.73km</td><td>12.47分钟</td><td>环岛西路,国道4W/广澳高速公路,</td></tr>" +
					"</table>" +
				"</div>" +
			"</div>");
	}()
	,radar = function(){
		return $("<div class='radarList div-three'  >" +
				"<div class='content' >" +
					"<div class='query-criteria' style=''>" +
						"<ul >" +
							"<li ><input id='radarStartTime' class='cursor-pointer' placeholder='开始时间'  value=''></li>" +
							"<li ><input id='radarEndTime' class='cursor-pointer' placeholder='结束时间'  value=''></li>" +
							"<li ><span class='span02' ><i class='fa fa-search'  ></i></span></li>" +
						"</ul>" +
					"</div>" +
					"<div class='radardetail-list' >" +
						"<table class='radartable' >" +
							"<thead >" +
								"<tr >" +
									"<td >雷达名称 </td>" +
								"</tr>" +
							"</thead>" +
							"<tbody class='tbody-content'>" +
								"<tr>" +
									"<td style='display: inline-block;width: 100%;text-align: center;float: left;''>暂无数据</td>	" +
								"</tr>"+
							"</tbody>" +
						"</table>" +
	/*					"<ul class='ul_btn' >	" +
							"<span id='radarNextPage' data-next-page='0' data-count='0' >下页</span>" +
							"<span id='radarTopPage' data-top-page='0' data-count='0' >上页</span>	" +
							"<span >总数0条</span>" +
						"</ul>" +*/
					"</div>" +
					"<div class='radar_bo_div'>" +
						"<ul style='display: inline-block; width: 100%;'>" +
							"<li style='display: inline-block; width: 45%; margin-left: 30px;'>" +
								"<span style='padding: 11%;'>播放间隔</span>" +
								"<select id='selectradarintervalr' style='background: #193481;color: white;'>" +
								"<option value='1000' selected=''>1秒</option>" +
								"<option value='2000'>2秒</option>" +
								"<option value='3000'>3秒</option>" +
								"<option value='5000'>5秒</option>" +
								"</select>" +
							"</li>" +
							"<li style='display: inline-block; width: 35%; float: right;'>" +
								"<span  class='play' style='text-align: center;background: #54c382;border-radius: 4px;cursor: pointer; padding: .3rem;' title='播放'>" +
								"<i class='fa fa-play-circle-o' style='width: 2.5rem;'></i></span>" +
								"<span  class='stop' style='display: none; text-align: center;background: #54c382;border-radius: 4px;cursor: pointer; padding: .3rem;' title='停止'>" +
								"<i class='fa fa-pause-circle-o' style='width: 2.5rem;'></i></span>" +
							"</li>" +
						"</ul>" +
					"</div>" +
				"</div>"+
		"</div>");
	}()
	,cloud = function(){
		return $("<div class='cloudList div-three'>" +
				"<div class='content'>" +
					"<div class='query-criteria' style=''>" +
						"<ul >" +
							"<li ><input id='cloudStartTime' class='cursor-pointer' placeholder='开始时间'  value=''></li>" +
							"<li ><input id='cloudEndTime' class='cursor-pointer' placeholder='结束时间'  value=''></li>" +
							"<li ><span class='span02' ><i class='fa fa-search'  ></i></span></li>" +
						"</ul>" +
					"</div>" +
					"<div class='clouddetail-list' >" +
						"<table class='cloudtable' >" +
							"<thead >" +
								"<tr >" +
									"<td >云图名称 </td>" +
								"</tr>" +
							"</thead>" +
							"<tbody class='tbody-content'>" +
								"<tr>" +
									"<td style='display: inline-block;width: 100%;text-align: center;float: left;''>暂无数据</td>	" +
								"</tr>"+
							"</tbody>" +
						"</table>" +
	/*					"<ul class='ul_btn' >	" +
							"<span id='cloudNextPage' data-next-page='0' data-count='0' >下页</span>" +
							"<span id='cloudTopPage' data-top-page='0' data-count='0' >上页</span>	" +
							"<span >总数0条</span>" +
						"</ul>" +*/
					"</div>" +
					"<div class='cloud_bo_div'>" +
						"<ul style='display: inline-block; width: 100%;'>" +
							"<li style='display: inline-block; width: 45%; margin-left: 30px;'>" +
								"<span style='padding: 11%;'>播放间隔</span>" +
								"<select id='selectcloudintervalr' style='background: #193481;color: white;'>" +
								"<option value='1000' selected=''>1秒</option>" +
								"<option value='2000'>2秒</option>" +
								"<option value='3000'>3秒</option>" +
								"<option value='5000'>5秒</option>" +
								"</select>" +
							"</li>" +
							"<li style='display: inline-block; width: 35%; float: right;'>" +
								"<span  class='play' style='text-align: center;background: #54c382;border-radius: 4px;cursor: pointer; padding: .3rem;' title='播放'>" +
								"<i class='fa fa-play-circle-o' style='width: 2.5rem;'></i></span>" +
								"<span  class='stop' style='display: none; text-align: center;background: #54c382;border-radius: 4px;cursor: pointer; padding: .3rem;' title='停止'>" +
								"<i class='fa fa-pause-circle-o' style='width: 2.5rem;'></i></span>" +
							"</li>" +
						"</ul>" +
					"</div>" +
				"</div>"+
		"</div>");
	}()
	/**
	 * 中间详细信息 
	 * @author lxv
	 * @return warnCenter
	 */
	,warnCenter = function(){
		return $("<div class='top-center-left-icon div-three' >" +
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
                "<div class='top-center-right-icon div-three' >" +
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
		return $("<div class='details-md-warn div-three'></div>" );
	}()
	,warnPersonlist = function(){
		return $("<div class='warnPersonlist-detail div-three' style='display: none;'></div>" );
	}()
	/**
	 * 渠道反馈以及影响分析详情
	 */
	,channelBackDetail = function(){
		return $("<div class='channelBackDetail div-three'>" +
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
	,naviLoading = function(){
		return $("<div class='naviLoading' >" +    
					"<div class='' style='top: 50%;  position: absolute;width: 25rem;z-index: 10;left: 50%;margin-left: -15rem;margin-top: -14rem;border: 0.2rem solid #1f8abf; color: white;'>" +        
						"<div class='' style=' display: inline-block;width: 100%;background: #1f8abf;line-height: 2rem;cursor: move;float: left; margin-top: -0.1rem;'>" +            
							"<span style='float: left;'>&nbsp;<i class='fa fa-commenting'></i>&nbsp;温馨提示</span>" +            
						"</div>" +        
						"<div class='' style=' display: inline-block; width: 100%;color: #3e6e79;background: rgb(245, 247, 249)'>" +            
							"<span style='display: inline-block;width: 100%;padding: 2rem 0;font-size: 1.4rem; text-align: center;'>" +
						    	"<i class='fa fa-warning' style='font-size: 2rem; margin-right: 1rem; '></i>请稍等！正在匹配路线 </span>" +        
						"</div>" +        
					"</div>" +
			"</div>");
	}()
	//初始化预警信息拼接页面
	,init =function(...params){
		for(var i = 0; i<params.length; i++){
			//拼接预警信息HTML 
			$(warnHomeContent).append(params[i]);
		}
		//拼接Html
		$("body").children("script").eq(0).before($(warnHomeContent));
	};  
	//初始化加载所有模块
	init(
		toolfloats,		//工具箱
		resources,		//资源展示
		warnPublish,	//预警发布
		warnList,		//预警列表
		queryCriteria,	//查询条件
		warnDetail,		//预警详情
		topwarnDetail,	//顶部预警详情
		timeControl,	//时间控件
		typhoon,		//台风
//		navi,			//逃生路线
		radar,			//雷达回波
		cloud,			//卫星云图
		warnPersonlist,				// 受众详细信息列表
		warnCenter,					// 预警中心内容
		channelBackDetail,				//渠道反馈详情
		naviLoading,				//渠道反馈详情
	);	
	//渠道反馈以及影响分析详情关闭
	$(".channelBackDetail > .setting-list-title span:last-child").on("click", function(){
		$(".channelBackDetail").hide();
	});
	
	$(".top-center-left-icon .cls.lft").on("click",function(){
		$(".top-center-left-icon .cls.lft").hide();
		$(".top-center-left-icon .top-center-left").fadeOut();
	});
	
	$(".top-center-right-icon .cls.rt").on("click",function(){
		$(".top-center-right-icon .cls.rt").hide();
		$(".top-center-right-icon .top-center-right").fadeOut();
	});
	//切换菜单地图图层显示与隐藏
	//台风显示
	$('#menu input[type="checkbox"]').change(function() {
		$(".esriPopupWrapper").show();
		$(".legend_box").show();
		$(".typhoonlist").show();
		var $this = $(this);
	    var layer = new Layer();
	    var layerNames = [];
	    var menubool = $this.is(':checked');
	    var cName = $this.attr('data-chooseName');
	    layerNames.push(typhoonLyName);
    		//加载台风列表
        	if(menubool){
        		inittyphoon(true);
        	}else{
        		inittyphoon();
        		$(".legend_box").hide();
        		$(".typhoonlist").hide();
        	}
        	checkTyphoonMap(menubool)
	  });
	//查询台风列表
	$(".typhoonNewList .content .query-criteria span ").on("click",function(){
	    $.ajax({
	        type: "get",
	        cache: "false",
	        url: "resources/map/gis/typhoon/data/typhoonList.json",
	        dataType: "json",
	        success: function(datas) {
	        	var html = "";
	        	if(datas != "" && datas != null && datas.length > 0){
		            $.each(datas,function(i, data) {
		                    arrayBh.push(data.vintbh);
		                    name = "" == data.tyname ? data.cname : data.cname + "(" + data.tyname + ")";
		                    if(data.vintbh != 16){
		                    	html +="<tr class='' data-code='"+data.vintbh+"' data-name='"+name+"' >" ;
		                    	html +="<td >"+ data.vintbh +"</td>	";
		                    	html +="<td title='"+ name +"'>"+ name +"</td></tr>";
		                    }
		                });
		            $(".typhoontable tbody").empty().append(html);
		            $(".detail-list ul span:last-child").text("总数"+datas.length+"条");
	        	}else{
	        		html +="<tr ><td style='display: inline-block;width: 100%;text-align: center;float: left;'>暂无数据</td></tr>";
	        		$(".typhoontable tbody").empty().append(html);
		            $(".detail-list ul span:last-child").text("总数0条");
	        	}
	        	
	        	$(".typhoontable tbody tr ").on("click",function(){
	        		$(this).siblings().removeClass("active");
	        		var layer = new Layer();
        			var layerNames = [];
        			var menubool = false;
        			var cName = "taifeng";
        			layerNames.push(typhoonLyName);
        	    	typhoonModel="";
        	    	var code=$(this).data("code");
        	    	var name=$(this).data("name");
        	    	var selectcode=$(this).data("code")+$(this).data("name");
	        		if($(this).hasClass("active")){
	        			if(typhoonSelect==selectcode){
	        				$(this).removeClass("active");
		        			$(".legend_box").hide();
		        		    $(arrayBh).each(function(i) {
		        		    	clearLayerGraphics(typhoonLyName + arrayBh[i]);
		        		    	clearLayerGraphics(typhoonWaterstation + arrayBh[i]);
		        		    });
		        		    addtyphoonData(null, code, name, false);
	        				menubool = false;
	        				typhoonModel="close";
	        				typhoonSelect="";
	        			}
	        		}else{
	        			typhoonSelect=selectcode;
	        			$(this).addClass("active");
	        			//详情冒泡
        				$(".esriPopupWrapper").show();
        				$(".legend_box").show();
        				addtyphoonData(null, code, name, true);
        				menubool = true;
        				typhoonModel="";
	        		}
	        		checkTyphoonMap(menubool)
	 		    });
	        }
	    });
	});
	
	//查询雷达列表
	$(".radarList .content .query-criteria ul li span ").on("click",function(){
		var startTime=$("#radarStartTime").val();
		var endTime=$("#radarEndTime").val();
		var param={"startTime":startTime,"endTime":endTime};
		$.ajax({
			type: "get", //使用get方法访问后台
			dataType: "json", //返回json格式的数据
			url: "resources/map/gis/radar/data/radar/radar.json",
			data: param,
			success: function(result) {
				console.log(result);
				var imagelist = new Array();
				var imagename = new Array();
				var html = "";
				if(result != "" && result != null  && result.length >0){
		            $.each(result,function(i, data) {
		            	var dataPath = "resources/map/gis/radar/data/radar/"
							imagelist.push(dataPath+data.img);
		                    var name=data.name;
		                    	html +="<tr class='' data-img='"+dataPath+data.img+"' data-name='"+name+"' >" ;
		                    	html +="<td title='"+ name +"'>"+ name +"</td></tr>";
		                });
		            $(".radartable tbody").empty().append(html);
		            var extent={xmin:108.785489,ymin:24.639302,xmax:114.254852,ymax:30.129033};
//		            var extent={xmin:110.65963,ymin:26.107626,xmax:115.36237,ymax:30.810373};
		            var mark = new Mark();
		            $(".radartable tbody tr:first-child").addClass("active");
		            mark.LoadImage(imagelist[0],"radarimagelayer",extent);
	        	}else{
	        		html +="<tr ><td style='display: inline-block;width: 100%;text-align: center;float: left;'>暂无数据</td></tr>";
	        		$(".radartable tbody").empty().append(html);
	        	}
	        	$(".radartable tbody tr ").on("click",function(){
	        		$(".radartable tbody tr").removeClass("active");
        	    	var code=$(this).data("name");
        	    	var img=$(this).data("img");
	        		if($(this).hasClass("active")){
	        			$(this).removeClass("active");
    					typhoonSelect="";
        				mark.clearImage("radarimagelayer");
	        		}else{
	        			$(this).addClass("active");
	        			var layerid ="radarimagelayer";
	        			var extent={xmin:108.785489,ymin:24.639302,xmax:114.254852,ymax:30.129033};
//	        			var extent={xmin:110.65963,ymin:26.107626,xmax:115.36237,ymax:30.810373};
	        			addImageLayer(img, layerid, extent);
	        		}
	 		    });
			},
			error: function() {
				alert("雷达图")
			}
		});
	});
	
	/**循环播放雷达图**/
	function flashimage1(layerid, selectid, extent) {
		var len=$(".radartable tbody tr").length;
		radarValue1=radarValue1+1;
		$(".radartable tbody tr").removeClass("active");
    	if(radarValue1<=len){
    		$(".radartable tbody tr:nth-child("+radarValue1+")").click();
		}
		if(radarValue1>len){
			radarValue1=1;
			$(".radartable tbody tr:nth-child("+radarValue1+")").click();
		}
	}
	
	//雷达回波播放动画
	$(".radar_bo_div ul li:last-child span ").on("click",function(){
		var extent={xmin:108.785489,ymin:24.639302,xmax:114.254852,ymax:30.129033};
		var intervaltime=document.getElementById('selectradarintervalr').options[document.getElementById('selectradarintervalr').selectedIndex].value;
		if($(this).hasClass("play")){
			if($(".radartable tbody tr").length>0){
				$(".radar_bo_div ul li:last-child .stop ").show();
				$(this).hide();
				Globel.radarIntervalid = window.setInterval(function() {
					flashimage1("radarimagelayer", "selectRadar", extent)
				}, intervaltime);
			}
		}else{
			$(this).hide();
			$(".radar_bo_div ul li:last-child .play ").show();
			window.clearInterval(Globel.radarIntervalid);
		}
	})
	
	//查询卫星云图列表
	$(".cloudList .content .query-criteria ul li span ").on("click",function(){
		var startTime=$("#cloudStartTime").val();
		var endTime=$("#cloudEndTime").val();
		var param={"startTime":startTime,"endTime":endTime};
		$.ajax({
			type: "get", //使用get方法访问后台
			dataType: "json", //返回json格式的数据
			data: param,
			url: "resources/map/gis/cloud/data/cloud/cloud.json",
			success: function(result) {
				var imagelist = new Array();
				var imagename = new Array();
				var html = "";
				if(result != "" && result != null  && result.length >0){
		            $.each(result,function(i, data) {
		            	var dataPath = "resources/map/gis/cloud/data/cloud/"
							imagelist.push(dataPath+data.img);
		                    var name=data.name;
		                    	html +="<tr class='' data-img='"+dataPath+data.img+"' data-name='"+name+"' >" ;
		                    	html +="<td title='"+ name +"'>"+ name.substring(0,30)+"...</td></tr>";
		                });
		            $(".cloudtable tbody").empty().append(html);
		            var extent={xmin:86.59,ymin:-0.64,xmax:161.77,ymax:60.52};
		            var mark = new Mark();
		            $(".cloudtable tbody tr:first-child").addClass("active");
		            mark.LoadImage(imagelist[0],"cloudimagelayer",extent);
	        	}else{
	        		html +="<tr ><td style='display: inline-block;width: 100%;text-align: center;float: left;'>暂无数据</td></tr>";
	        		$(".cloudtable tbody").empty().append(html);
	        	}
	        	$(".cloudtable tbody tr ").on("click",function(){
	        		$(".cloudtable tbody tr").removeClass("active");
        	    	var code=$(this).data("name");
        	    	var img=$(this).data("img");
	        		if($(this).hasClass("active")){
	        				$(this).removeClass("active");
	        				typhoonSelect="";
	        				mark.clearImage("cloudimagelayer");
	        		}else{
	        			$(this).addClass("active");
	        			var layerid ="cloudimagelayer";
	        			var extent={xmin:86.59,ymin:-0.64,xmax:161.77,ymax:60.52};
	        			addImageLayer(img, layerid, extent);
	        		}
	 		    });
			},
			error: function() {
				alert("云图")
			}
		});
	});
	
	/**
	 * 循环播放卫星云图
	 **/
	function flashimage2(layerid, selectid, extent) {
		var len=$(".cloudtable tbody tr").length;
		cloudValue1=cloudValue1+1;
		$(".cloudtable tbody tr").removeClass("active");
    	if(cloudValue1<=len){
    		$(".cloudtable tbody tr:nth-child("+cloudValue1+")").click();
		}
		if(cloudValue1>len){
			cloudValue1=1;
			$(".cloudtable tbody tr:nth-child("+cloudValue1+")").click();
		}
	}
	
	//卫星云图播放动画
	$(".cloud_bo_div ul li:last-child span ").on("click",function(){
		var extent={xmin:108.785489,ymin:24.639302,xmax:114.254852,ymax:30.129033};
		var intervaltime=document.getElementById('selectcloudintervalr').options[document.getElementById('selectcloudintervalr').selectedIndex].value;
		if($(this).hasClass("play")){
			if($(".cloudtable tbody tr").length>0){
				$(".cloud_bo_div ul li:last-child .stop ").show();
				$(this).hide();
				Globel.cloudIntervalid = window.setInterval(function() {
					flashimage2("cloudimagelayer", "selectCloud", extent)
				}, intervaltime);
			}
		}else{
			$(this).hide();
			$(".cloud_bo_div ul li:last-child .play ").show();
			window.clearInterval(Globel.cloudIntervalid);
		}
	})
	
	
	/**
	 * 点击工具箱隐藏所有模快
	 */
	var hiddenDiv=function(){
		$(".div-two").hide();
		$(".div-three").hide();
		//获取地图上所有图层，找出预警图层和地区图层并删除
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
			mark.clearImage("radarimagelayer");
			mark.clearImage("cloudimagelayer");
			window.clearInterval(Globel.radarIntervalid);
			window.clearInterval(Globel.cloudIntervalid);
			removeNavi();
			crearNaviAuto();//清除逃生路线
		}
		$(".modular-show li").removeClass("nav-menu-active");
		$(".modular-show li").addClass("nav-menu-7");
	}
	
	
	/**
	 * 点击模块隐藏所有模快
	 */
	var hiddenAllDiv=function(){
		
		$(".cloud_bo_div ul li:last-child .play ").show();
		$(".radar_bo_div ul li:last-child .play ").show();
		$(".radar_bo_div ul li:last-child .stop ").hide();
		$(".radar_bo_div ul li:last-child .stop ").hide();
		$(".div-two").hide();
		$(".div-three").hide();
		//获取地图上所有图层，找出预警图层和地区图层并删除
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
			mark.clearImage("radarimagelayer");
			mark.clearImage("cloudimagelayer");
			window.clearInterval(Globel.radarIntervalid);
			window.clearInterval(Globel.cloudIntervalid);
//			mark.getGraphicsLayer("warningGraphicsLayer").clear();
			removeNavi();
			crearNaviAuto();//清除逃生路线
		}
		
		$(".navbar-nav > li.map-option > i").removeClass("active");
		if(map.graphics!=undefined){
			map.graphics.clear();
		}
		//使其圈选图层失效
		if(mapParam.drawToolbar!=null){
    		mapParam.drawToolbar.deactivate();
    		map.graphics.clear();//清除图层
		}
		if(mapParam.drawing != null){
			mapParam.drawing.remove();	
		}
	}
	
	//逃生路线
/*	$("#navi_").click(function(){
		var flag= $(this).hasClass("selectedDiv");
		if(flag){
			$(this).removeClass("selectedDiv");
		}else{
			$(this).addClass("selectedDiv");	
		}
		if($(this).hasClass("selectedDiv")){
	        crearNaviAuto();
			removeNavi();
			map.mapExtentChange=map.on('click', addDisasterEvent);
		}else{
			crearNaviAuto();
			if(window.shanshuo_delete){
				clearInterval(window.shanshuo_delete);	
			}
		}
})*/

/*$("#disasterNavigation").change(function(){
	removeNavi();
	map.mapExtentChange=map.on('click', addDisasterEvent);
})*/
	
	//提醒渠道点击样式
/*	$(".remindChannel .fb-form-input .selectbox ul li").on("click",function(){
		if($(this).hasClass("on")){
			$(this).removeClass("on");
		}else{
			$(this).addClass("on");
		}
	});*/
	
	//是否指定发布时间
	$(".publishMode .fb-form-input .selectbox ul li span:first-child").on("click",function(){
		if($(this).parent().hasClass("on")){
			$(this).parent().removeClass("on");
			$(".timingPublishTime").hide();
			$("#timingPublishTime").val("");
			$("#sendFlag").val("2");
		}else{
			$(this).parent().addClass("on");
			$("#sendFlag").val("1");
			$(".timingPublishTime").show();
		}
	});
	/**
	 * 判断省份信息
	 */
    var judgeProvince=function(){
    	var name=$("nav .collapse .text-center li").text().substring(0,3);
    	if(name!="湖南省"){
    		$(".provincemessage").hide();
    	}
    }
   /**
    * 预警发布参数封装
    */
	var warnPublishParam = function(){
	   	var p = {
   			almSerialNo:$("#almSerialNo").val(),									//期数
			sourceType:"0", 														//0：首发  1继续 2：变更 3解除 4修改
			taskId :"", 															//任务id 你那边没有为空
			isDelegateFrom :"2",													//是否代发1：代发  2自发   你那么没有设置为2
			deReferences:"",														//代发预警唯一标示 为空
			issueNo: "0",															//是否使用
			disasterId: $("#disasterId").val(),										//灾种ID	
			colorLevel: $("#colorLevel").val(),										//灾种颜色
			img:initParam.warnPic,													//图片
			numberLevel: $("#publishlevelId").val(),								//预警级别
			duration: $("#continueTime").val(),										//持续时间
			processMsg: "",															//请示意见
			earlyType: $("#warnType").val(),										//预警类型
			releaseMode: 0,															//发布方式
			isRecord:$('.record span label input[name="demo-radio"]:checked').val(),//国突是否备份
			submitFlag: true,
			insertType: 1,															// 区分预警录入和预案录入 1：预警录入；2：预案录入 
			sendFlag: $("#sendFlag").val(),											//是否定时发布 1-指定 2-不指定 
			relieaseTime: $("#timingPublishTime").val(),									//定时发布时间
			publishTime: $("#publishTime").val(),									//发布时间
			releaseProcess: "1,5",													//预警流程
			agentFlag: 0,															//是否待办：0否 1是		 
			channels:function(){
				var channelId = "";
				$(".pop-warnPublish-channel > dl").children("i").each(function(){
					channelId += "," + $(this).parent().data("channel").keyId;
				});
				$("#channelId").val(channelId);
				return channelId != "" ? channelId = channelId.substring(1) : channelId ;
			}(),
			areaIds: $("#areaCodes").val(),											//获取选择的地区
		    taskFileNameList: "",													//附件名称，逗号隔开
		    taskFilePathList: "",													//附件地址，逗号隔开
		    taskFileDescList: "",													//附件描述，逗号隔开
		    taskFileSizeList: "",													//附件大小，逗号隔开
		    titileContent: $("#disasterName").val()+$("#discolor").val()+"预警["+$("#publishlevel").val()+"]",
		    fristContent: "湖南省"+$("#publishContent").text(),
		    disasterName: $("#disasterName").val(),
		    areaCode: "430000000000",
		    nextProcess: $("#nextProcess").val()	//保存待发 首次发布：9 默认为空
	   	};
	   	return p;
   };
   /**
    * 获取所有敏感词
    */
   var sensitiveWord=function(){
	   var sensitiveWord=null;
		$.ajax({
			   async:false,
		       type: "POST",
		       url: 'sensitive/findSensitiveAll',
		       data: param,
		       dataType: "json",
		       success: function(data){
		    		if(data!=null){
		    			initParam.sensitiveWord=data;
		    		}
	       		}
			});
		}
  	//预警信息重置
  	$(".reset").on("click",function(){
  		removeResourcesLayer();
		var ztreeObj=$.fn.zTree.getZTreeObj("publishAreaTree");//取消勾选地区树
		ztreeObj.checkAllNodes(false);
		var mark = new Mark();
		var graphicLayer = mark.getGraphicsLayer("regionHNGraphicslayer");
		graphicLayer.clear();
	   $("#disasterId").val("");					//灾种ID
	   $("#disasterName").val("");					//灾种名称
	   $("#colorLevel").val("");					//灾种颜色ID
	   $("#discolor").val("");						//颜色
	   $("#publishlevelId").val("");				//级别ID
	   $("#publishlevel").val("");					//级别
	   $("#continueTime").val("24");				//持续时间
	   $("#channelId").val("");						//渠道ID
	   $("#areaCodes").val("");						//地区ID
	   $("#timingPublishTime").val("");				//定时发布时间
	   $("#publishTime").val("");					//发布时间
	   $("#publishContent").text("");				//发布内容
	   $(".selectcl ul li").removeClass("on");		//勾选图标
	   $(".chanelSelect .un-all-channel").click();  //渠道取消勾选
  })
  	var publish =function(){
  		var userType=[];
  		var userTypeId="";
  		var orgAreaId=$("#areaId").val();				//机构ID
  		var orgAreaName=$("#areaName").val();			//机构名称
  		var disasterId=$("#disasterId").val();			//灾种ID
  		var colorLevel=$("#colorLevel").val();			//灾种颜色
  		var continueTime=$("#continueTime").val();		//持续时间
  		var timingPublishTime=$("#timingPublishTime").val();			//指定时间
  		var publishTime=$("#publishTime").val();			//发布时间
  		var channelId=$("#channelId").val();				//渠道ID
  		var responsNum=$(".responsNum").text();
  		var audienceNum=$(".audienceNum").text();
  		var areaIds=$("#areaCodes").val();				//地区ID
  		var publishContent=$("#publishContent").text();	//内容
  		var promptBox=box();
  		if($(".publishMode .fb-form-input .selectbox ul li").hasClass("on")){
  			if(timingPublishTime==""){
  				promptBox.content="请选择指定时间";
  				Move.pop(promptBox);
  				return false;
  			}
  		};   
  		if(disasterId=="" ||disasterId==null){
  			promptBox.content="请选择灾种名称";
  			Move.pop(promptBox);
  			return false;
  		}else if(colorLevel=="" ||colorLevel==null){
  			promptBox.content="请选择灾种颜色";
  			Move.pop(promptBox);
  			return false;
  		}else if(continueTime=="" ||continueTime==null){
  			promptBox.content="请选择持续时间";
  			Move.pop(promptBox);
  			return false;
  		}else if(publishTime=="" ||publishTime==null){
  			promptBox.content="请选择发布时间";
  			Move.pop(promptBox);
  			return false;
  		}else if(areaIds=="" ||areaIds==null){
  			promptBox.content="请框选地区";
  			Move.pop(promptBox);
  			return false;
  		}else if(channelId=="" ||channelId==null){
  			promptBox.content="请选择渠道";
  			Move.pop(promptBox);
  			return false;
  		}else if(responsNum=="0" && audienceNum=="0"){
  			promptBox.content="请在选择的渠道对应用户组里后台添加人员";
  			Move.pop(promptBox);
  			return false;
  		}else if(publishContent=="" ||publishContent==null){
  			promptBox.content="请填写预警内容";
  			Move.pop(promptBox);
  			return false;
  		} else {
  			var publishCont=$("#publishContent").text();
  			var sensitiveWord=initParam.sensitiveWord;
  			var words="";
  			var strs= new Array(); //定义一数组 
  			for(var i=0;i<sensitiveWord.length;i++){
  				if(initParam.overlookedWord!=""){
  					if(initParam.overlookedWord.substr(0,1)==","){
  						initParam.overlookedWord != "" ? initParam.overlookedWord = initParam.overlookedWord.substring(1) : initParam.overlookedWord ;
  					}
  					if(publishContent.indexOf(sensitiveWord[i])>-1 ){
  						if(initParam.overlookedWord.indexOf(sensitiveWord[i])==-1){  
  							if(sensitiveWord[i]!="敏感字词"){
  								var word=$("#publishContent span").hasClass("bubbling");
  								if(word==false){
  									var cont=publishCont.replace(sensitiveWord[i],"<span class='bubbling' >"+sensitiveWord[i]+"<span  contenteditable='false'>【"+sensitiveWord[i]+"】是敏感字词，是否忽略<button>忽略</button></span></span>");
  									words +=","+sensitiveWord[i];
  									$("#publishContent").empty().append(cont);
  									//忽略敏感词
  									$("#publishContent span button").on("click",function(){
  										$(this).parent().remove();
  										var newcontent=$("#publishContent").text();
  										var word=$("#publishContent span").text();
  										initParam.overlookedWord +=","+word;
  										$("#publishContent").empty().append(newcontent);
  									});
  								} 
  							}
  						}
  					}
  				}else{
  					if(publishContent.indexOf(sensitiveWord[i])>-1){
  						if(sensitiveWord[i]!="敏感字词"){
  							var word=$("#publishContent span").hasClass("bubbling");
  							if(word==false){
  								var cont=publishCont.replace(sensitiveWord[i],"<span class='bubbling' >"+sensitiveWord[i]+"<span  contenteditable='false'>【"+sensitiveWord[i]+"】是敏感字词，是否忽略<button>忽略</button></span></span>");
  								words +=","+sensitiveWord[i];
  								$("#publishContent").empty().append(cont);
  								//忽略敏感词
  								$("#publishContent span button").on("click",function(){
  									$(this).parent().remove();
  									var newcontent=$("#publishContent").text();
  									var word=$("#publishContent span").text();
  									initParam.overlookedWord +=","+word;
  									$("#publishContent").empty().append(newcontent);
  								});
  							} 
  						}
  					}
  				}
  			}
  			if($("#publishContent span").hasClass("bubbling")){
  				return false;
  			}
  			var par=warnPublishParam();
  			var isBCDFFlag=$("#isBCDFFlag").val();
  			if(isBCDFFlag!="" && isBCDFFlag!=null){
  				par.isBCDFFlag="1";
  			}
  			var responsTreeObj = $.fn.zTree.getZTreeObj("responsTree");
  			var nodes=responsTreeObj.getCheckedNodes(true);
  			if(nodes.length>0){
  				for(var i = 0 , len=nodes.length; i<len; i++){
  					userType.push(nodes[i].id);
  				}
  			}
  			var treeObj = $.fn.zTree.getZTreeObj("audienceTree");
  			var nodes=treeObj.getCheckedNodes(true);
  			if(nodes.length>0){
  				for(var i = 0 , len=nodes.length; i<len; i++){
  					userType.push(nodes[i].id);
  				}
  			}
  			for(var i = 0 , len=userType.length; i<len; i++){
  				userTypeId += "," + userType[i];
  			}
  			initParam.userTypeId=userTypeId.substring(1);
  			$(".pop-warnPublish-channel > dl").children("i").each(function(){
  				var contentSplit=$(this).parent().data("channel").contentSplit;
  				var sendType=$(this).parent().data("channel").sendType;
  				var channelId=$(this).parent().data("channel").keyId;
  				var user="user_"+$(this).parent().data("channel").keyId;
  				var param={
  						orgId:initParam.orgId,
  						channelId:channelId,
  						areaCode:Globel.areaCode,
  						disasterId:disasterId,
  						colorLevel:colorLevel,
  						userTypeId:initParam.userTypeId
  				}
  				var users="";
  				//按照受众发布
  				if(sendType=='1'){
  					$.ajax({
  						async:false,
  						data:param,
  						dataType:"json",
  						type:"POST",
  						url:"map/channelByUser",
  						success:function(data){
  							for(var i=0;i<data.length;i++){
  								users +="," +data[i].KEYID;
  							}
  						}
  					});
  					//按照群组发布
  				}else if(sendType=='2'){
  					var userTypeId=initParam.userTypeId;
  					users +="," +userTypeId;
  				}
  				users=users.substring(1);
  				par[user]=users;
  				var areaIds= $("#areaCodes").val();	
  				var areaId = areaIds.split(","); 
  				var areaNames= $("#selectedAreaName").val();	
  				var areaName= areaNames.split(",");
  				var content="";
  				if(contentSplit==1){
  					for(var j=0;j<areaId.length;j++){
  						content ="content_"+channelId+"_"+areaId[j];
  						//par[content]=areaName[j]+$("#publishContent").text();
  						par[content]=$("#publishContent").text();
  					}
  				}else{
  					content ="content_"+channelId+"_"+orgAreaId;
  					//par[content]=orgAreaName+$("#publishContent").text();
  					par[content]=$("#publishContent").text();
  				}
  			});
  			console.log(par);
  			var str="精准发布";
  			var bt = encode64(strUnicode2Ansi(encodeURI(str+JSON.stringify(par)+str)));
  			var parambt = new Object();
  			parambt.bt=bt;
  			$.ajax({
  				async:false,
  				data:parambt,
  				dataType:"json",
  				type:"POST",
  				url:"ar/insertToDB",
  				beforeSend:function(XMLHttpRequest){ 
  					$(".warnPublish").attr('disabled',"true");
  				},
  				success:function(data){
  					if(data!=null){
  						if(data.status=="success"){
  							var promptBox=box();
  							if(par.nextProcess==""){
  								promptBox.content="提交成功";
  							}else{
  								promptBox.content="保存成功";
  							}
  							promptBox.contentIcon="fa-check-circle";
  							Move.pop(promptBox);
  							$(".warnPublish").removeAttr("disabled"); 
  							$(".reset").click();
  							$(".personTree").hide();
  						}else{
  							var promptBox=box();
  							if(par.nextProcess==""){
  								promptBox.content="提交失败";
  							}else{
  								promptBox.content="保存失败";
  							}
  							promptBox.contentIcon="fa-times-rectangle";
  							Move.pop(promptBox);
  							$(".warnPublish").removeAttr("disabled"); 
  							/*			$(".reset").click();
							$(".personTree").hide();*/
  						}
  					}
  				}
  			});
  		}
  	}
  	//保存待发
  	$(".saveNoPub ").on("click",function(){
  		$("#nextProcess").val("9");	//保存待发 首次发布：9 ,默认为空
  		$("#isBCDFFlag").val("1");	//保存待发标志
  		publish();
  	});
   //预警信息发布点击
  	$(".warnPublish ").on("click",function(){
  		$("#nextProcess").val("");	//保存待发 首次发布：9 ,默认为空
	   	publish();
   	})
	
	//发布预警颜色选择添加样式事件
	$(".selectcl ul li").on("click",function(){
		var disasterId=$("#disasterId").val();
	 	if(disasterId=="" || disasterId==null){
		  	var promptBox=box();
			    promptBox.content="请先选择灾种名称";
			    Move.pop(promptBox);
		}
	});
	
    /**
     * 获取所有终端信息
     */
    var terminalList = function(){
    	var terminalData = null;
    	$.ajax({
    		async: false,
            type: "POST",
            url: "map/terminalList",
            dataType: "json",
            success: function(data){
            	terminalData=data.list;
            }
    	});
    	return terminalData;
    };
	
	/**
	 * 初始化加载行业信息显示在地图
	 */
    var initIndustry = function(){
    	initParam.terminal = terminalList();
    	console.log(initParam.terminal);
      	$.ajax({
      		async:true,
      		type:"POST",
      		url:"map/industry",
      		dataType: "json",
      		success:function(data){
      			var html = "";
      			for(var i=0,len=data.length;i<len;i++){
      				var dataobj=JSON.stringify(data[i]);
      				html += "<dl data-industry='"+dataobj+"'><dt><img class='"+(data[i].status ==0?"gray":"")+"' src='"+data[i].picUrl+"' /></dt><dd>"+data[i].industryName+"</dd></dl>";
      			}
      			$(".pop-resources .resources-content").children("dl").remove();
      			$(".pop-resources .resources-content").prepend(html);
      			//机构信息拼接
      			html = "";
      			for(var i=0,len=data.length;i<len;i++){
      				var dataobj=JSON.stringify(data[i]);
      				html += "<div id='"+data[i].keyId+"' class='rsclist rscitem ' style='display:none'>";
      				html +=    "<div class='top'>";	
      				html +=    		"<ul>";
      				for (var j=0; j<initParam.terminal.length; j++){
      					if( data[i].keyId == initParam.terminal[j].INDUSTRYID ){
      						var terminalObj=JSON.stringify(initParam.terminal[j]);
      						html +=		"<span data-terminal='"+terminalObj+"'><i class='fa fa-square-o'></i>"+initParam.terminal[j].TYPENAME+"</span>&nbsp;&nbsp;";
      					}
      				}
      		    	html +=    		"</ul>";
      		    	html +=    "</div>";
      		    	html +=    "<div class='bottom'>";
      		    	html +=    		"<div class='resources-slet'>";
      		    	html +=    			"<ul>";
      		    	html +=        			"<span><i class='fa fa-square-o'></i>全选</span>&nbsp;&nbsp;";
      		    	html +=        			"<span><i class='fa fa-check-square-o checked'></i>反选</span>&nbsp;&nbsp;";
      		    	html +=        			"<span><i class='fa fa-times'></i>关闭</span>&nbsp;&nbsp;";
      		    	html +=    			"</ul>";
      		    	html +=    		"</div>";
      		    	html +=		"</div>";
      		    	html +="</div>";
  				}
      			$(".resource-body").prepend(html);
	  		}
		});
	}; 
    // 行业单选(左边漂浮层tab页中，数据单选)
    $(document).off("click",".pop-resources .resources-content > dl").on("click",".pop-resources .resources-content > dl",function(){
    	var industry= $(this).data("industry");
    	var bool = $(this).children("dt").children("img").hasClass("gray");
	         if(!bool){
	             var num = $(this).find(".checked").length;
	      		 var select=$(this).data("industry");
	          	 var selectId=$(this).data("industry").keyId;
	             if(num==0){
	            	 //行业添加勾选样式
	        	 	$(this).append(initParam.checked);
	        	 	//终端信息图层显示出来
	        	 	$(".zyzs-relt .shade").show();
	        	 	$(".zyzs-relt .resource-content").show();
	        	 	//终端图层勾选的图标
	         		var html = "";
	     				html += "<dl ><dt><img class='' src='"+select.picUrl+"' style='width: 29px;border-radius: 2.8rem;'></dt><dd style='text-align:center'>"+select.industryName+"</dd></dl>";
	     			$(".resourceitme").empty().prepend(html);
	     			//将勾选的行业下对应的终端信息显示
	             	$(".zyzs-relt .resource-body .rsclist").each(function(i){
	             		var id=$(this).attr('id');
	             		if(selectId==id){
	             			$(this).show();
	             		}
	             	});
	             }else {
	                 $(this).find(".checked").remove();
	                 //将勾选的行业下对应的终端信息显示
		           	$(".zyzs-relt .resource-content .resource-body .rsclist").each(function(i){
	             		var id=$(this).attr('id');
	             		if(selectId==id){
	             			$(this).children(".top").children("ul").children("span").each(function(j){
	             				var terminal=$(this).data("terminal");
	             		        var tablename="ssd_ems_bd_"+$(this).data("terminal").TEMPLATENAME;
	             		        var templateParam=$(this).data("terminal").TEMPLATEPARAM;
	             		        
	             		        var displayMode=terminal.DISPLAYMODE;
	             		        var restartAt=terminal.RESTARTAT;
	             		        var resourceMapping="";
	             		        var mappingField="";
	             		        if(terminal.RESOURCEMAPPING!=undefined){
	             		        	resourceMapping=terminal.RESOURCEMAPPING;
	             		        }
	             		        if(terminal.MAPPINGFIELD!=undefined){
	             		        	mappingField=terminal.MAPPINGFIELD;
	             		        }
	             		        var da = [];
	             		        var field =null;
	             	        	$.ajax({
	             	            	async:false,
	             	            	data:{tablename:tablename,templateParam:templateParam},
	             	            	type: "GET",
	             	            	url: "map/findAllTerminal",
	             	            	dataType: "json",
	             	    	        success: function(data){
	             	    	        	field=data.field;
	             	    	        	var data=data.arry;
	             	            		for(var i=0;i<data.length;i++){
	             	    	        		var a=data[i];
	             	    	        		a.x = a.LONGITUDE;
	             	                		a.y = a.LATITUDE;
	             	                		a.img = terminal.ICON;
	             	                		a.displayMode=displayMode;
	             		            		a.resourceMapping=resourceMapping;
	             		            		a.restartAt=restartAt;
	             		            		a.mappingField=mappingField;
	             	                		da.push(a);
	             	    	        	}
	             	    	         }
	             	            });
             	        	  var obj = {
             	     	    		id:terminal.KEYID,
             	     	    		data:da,
             	     	    		field:field,
             	     	    		terminal:terminal
             	     	        };
					    		addPoint(null)(obj);
				    	  	});
	             		}
	             	});
	             }
	         }
	         return false;
	    });    
					
	//终端单选(左边漂浮层行业点击漂浮层)
	$(document).off("click",".resource-body .rsclist .top ul span").on("click",".resource-body .rsclist .top ul span",function(){
		var num = $(this).find(".checked").length;
		if(num==0){
	        $(this).find(".fa-square-o").removeClass("fa-square-o").addClass("fa-check-square-o checked");
		}else{
			$(this).find(".checked").removeClass("fa-check-square-o checked").addClass("fa-square-o");
		}
	    var terminal=$(this).data("terminal");
	    if(terminal.TEMPLATENAME!=undefined){
	        var tablename="ssd_ems_bd_"+$(this).data("terminal").TEMPLATENAME;
	        var templateParam=$(this).data("terminal").TEMPLATEPARAM;
	        
	        var displayMode=terminal.DISPLAYMODE;
	        var restartAt=terminal.RESTARTAT;
	        var resourceMapping="";
	        var mappingField="";
	        if(terminal.RESOURCEMAPPING!=undefined){
	        	resourceMapping=terminal.RESOURCEMAPPING;
	        }
	        if(terminal.MAPPINGFIELD!=undefined){
	        	mappingField=terminal.MAPPINGFIELD;
	        }
	        var da = [];
	        var field =null;
	    	$.ajax({
	        	async:false,
	        	data:{tablename:tablename,
	        		templateParam:templateParam},
	        	type: "GET",
	        	url: "map/findAllTerminal",
	        	dataType: "json",
		        success: function(data){
		        	field=data.field;
		        	var data=data.arry;
	        		for(var i=0;i<data.length;i++){
		        		var a=data[i];
		        		a.x = a.LONGITUDE;
	            		a.y = a.LATITUDE;
	            		a.img = terminal.ICON;
	            		a.displayMode=displayMode;
	            		a.resourceMapping=resourceMapping;
	            		a.restartAt=restartAt;
	            		a.mappingField=mappingField;
	            		da.push(a);
		        	}
		         }
	        });
			if(num==0){
		        var obj = {
		    		id:terminal.KEYID,
		    		data:da,
		    		field:field,
		    		terminal:terminal
		        };
		        addPoint(obj);
			}else{
		      var obj = {
		    		data:da,
		    		field:field,
		    		terminal:terminal
		        };
				addPoint(null)(obj);
			}
			return false;
	    }
	});
		
	//行业-终端-关闭(左边漂浮层行业点击漂浮层)
	$(document).on("click",".resource-body .rsclist .bottom .resources-slet ul span:nth-child(3)",function(){
		$(this).parent().parent().parent().parent().hide();
		$(".zyzs-relt .shade").hide();
		$(".zyzs-relt .resource-content").hide();
	 	var span = $(this).parent().parent().parent().parent().children(".top").children("ul").children("span");
	 	for(var i = 0, len = span.length; i<len; i++){
	 		$(span[i]).find(".checked").removeClass("fa-check-square-o checked").addClass("fa-square-o");
	 	}
	 	$(this).parent().children().find(".checked").removeClass("fa-check-square-o checked").addClass("fa-square-o");
	 	$(this).find(".fa-square-o").removeClass("fa-square-o").addClass("fa-check-square-o checked");
	})
		
	//行业-终端-全选/反选(左边漂浮层行业点击漂浮层)
	$(document).on("click",".resource-body .rsclist .bottom .resources-slet ul span:nth-child(1),.resource-body .rsclist .bottom .resources-slet ul span:nth-child(2)",function(){
		  var text = $(this).text();
		  $(this).parent().children().find(".checked").removeClass("fa-check-square-o checked").addClass("fa-square-o");
	      $(this).find(".fa-square-o").removeClass("fa-square-o").addClass("fa-check-square-o checked");
	      var span = $(this).parent().parent().parent().parent().children(".top").children("ul").children("span");
	      if(text=="全选"){
	          for(var i = 0, len = span.length; i<len; i++){
                  $(span[i]).find(".fa-square-o").removeClass("fa-square-o").addClass("fa-check-square-o checked");
                  	var terminal=$(span[i]).data("terminal");
                    if(terminal.TEMPLATENAME!=undefined){
				        var tablename="ssd_ems_bd_"+$(span[i]).data("terminal").TEMPLATENAME;
				        var templateParam=$(span[i]).data("terminal").TEMPLATEPARAM;
				        var param={tablename:tablename,templateParam:templateParam};
				        
				        var displayMode=terminal.DISPLAYMODE;
				        var restartAt=terminal.RESTARTAT;
				        var resourceMapping="";
				        var mappingField="";
				        if(terminal.RESOURCEMAPPING!=undefined){
				        	resourceMapping=terminal.RESOURCEMAPPING;
				        }
				        if(terminal.MAPPINGFIELD!=undefined){
				        	mappingField=terminal.MAPPINGFIELD;
				        }
				        var da = [];
				        var field =null;
				        	$.ajax({
				            	async:false,
				            	data:param,
				            	type: "GET",
				            	url: "map/findAllTerminal",
				            	dataType: "json",
				    	        success: function(data){
				    	        	field=data.field;
				    	        	var data=data.arry;
				            		for(var i=0;i<data.length;i++){
				    	        		var a=data[i];
				    	        		a.x = a.LONGITUDE;
				                		delete a.LONGITUDE;
				                		a.y = a.LATITUDE;
				                		delete a.LATITUDE;
				                		a.img = terminal.ICON;
				                		a.displayMode=displayMode;
					            		a.resourceMapping=resourceMapping;
					            		a.restartAt=restartAt;
					            		a.mappingField=mappingField;
				                		da.push(a);
				    	        	}
				    	         }
				            });
				        var obj = {
	                		id:terminal.KEYID,
	                		data: da,
	                		field: field,
	                		terminal:terminal
				        };
				        addPoint(obj);
                    }
	          	}
	      }else{
	          for(var i = 0, len = span.length; i<len; i++){
              	$(span[i]).find(".checked").removeClass("fa-check-square-o checked").addClass("fa-square-o");
              	var terminal=$(span[i]).data("terminal");
                if(terminal.TEMPLATENAME!=undefined){
			        var tablename="ssd_ems_bd_"+$(span[i]).data("terminal").TEMPLATENAME;
			        var templateParam=$(span[i]).data("terminal").TEMPLATEPARAM;
			        var param={tablename:tablename,templateParam:templateParam};
			        
			        var displayMode=terminal.DISPLAYMODE;
			        var restartAt=terminal.RESTARTAT;
			        var resourceMapping="";
			        var mappingField="";
			        if(terminal.RESOURCEMAPPING!=undefined){
			        	resourceMapping=terminal.RESOURCEMAPPING;
			        }
			        if(terminal.MAPPINGFIELD!=undefined){
			        	mappingField=terminal.MAPPINGFIELD;
			        }
			        var da = [];
			        var field =null;
			        	$.ajax({
			            	async:false,
			            	data:param,
			            	type: "GET",
			            	url: "map/findAllTerminal",
			            	dataType: "json",
			    	        success: function(data){
			    	        	field=data.field;
			    	        	var data=data.arry;
			            		for(var i=0;i<data.length;i++){
			    	        		var a=data[i];
			    	        		a.x = a.LONGITUDE;
			                		delete a.LONGITUDE;
			                		a.y = a.LATITUDE;
			                		delete a.LATITUDE;
			                		a.img = terminal.ICON;
			                		a.displayMode=displayMode;
				            		a.resourceMapping=resourceMapping;
				            		a.restartAt=restartAt;
				            		a.mappingField=mappingField;
			                		da.push(a);
			    	        	}
			    	         }
			            });
			        var obj = {
	            		id:terminal.KEYID,
	            		data: da,
	            		field: field,
	            		terminal:terminal
			        };
	                  addPoint(null)(obj);
                	}
	          }
	      }	 
	})
	
	//行业-全选反选(左边漂浮层tab切换按钮)
	$(".pop-resources .resources-slet > ul > span:nth-child(1),.pop-resources .resources-slet > ul > span:nth-child(2)").bind("click",function() {
	    var text = $(this).text();
	    $(this).parent().children().find(".checked").removeClass("fa-check-square-o checked").addClass("fa-square-o");
	    $(this).find(".fa-square-o").removeClass("fa-square-o").addClass("fa-check-square-o checked");
	    $(".pop-resources .resources-content dl").children("i").remove();
	    if (text == "全选") {
	        var img = $(".pop-resources .resources-content dl dt").children("img");
	        for (var i = 0,len = img.length; i < len; i++) {
	            var bool = $(img[i]).hasClass("gray"); // 判断图片是否为灰色，如果为灰色则不选
	            if (!bool) {
	                $(img[i]).parent().parent().append(initParam.checked);
	            }
	        }
	        for (var i = 0; i < initParam.terminal.length; i++) {
			    var terminal=initParam.terminal[i];
			    if(terminal.TEMPLATENAME!=undefined){
			        var tablename="ssd_ems_bd_"+initParam.terminal[i].TEMPLATENAME;
			        var templateParam=initParam.terminal[i].TEMPLATEPARAM;
			        
			        var displayMode=terminal.DISPLAYMODE;
			        var restartAt=terminal.RESTARTAT;
			        var resourceMapping="";
			        var mappingField="";
			        if(terminal.RESOURCEMAPPING!=undefined){
			        	resourceMapping=terminal.RESOURCEMAPPING;
			        }
			        if(terminal.MAPPINGFIELD!=undefined){
			        	mappingField=terminal.MAPPINGFIELD;
			        }
			        var da = [];
			        var field =null;
			        	$.ajax({
			            	async:false,
			            	data:{tablename:tablename,templateParam:templateParam},
			            	type: "GET",
			            	url: "map/findAllTerminal",
			            	dataType: "json",
			    	        success: function(data){
			    	        	field=data.field;
			    	        	var data=data.arry;
			            		for(var i=0;i<data.length;i++){
			    	        		var a=data[i];
			    	        		a.x = a.LONGITUDE;
			                		delete a.LONGITUDE;
			                		a.y = a.LATITUDE;
			                		delete a.LATITUDE;
			                		a.img = terminal.ICON;
			                		a.displayMode=displayMode;
				            		a.resourceMapping=resourceMapping;
				            		a.restartAt=restartAt;
				            		a.mappingField=mappingField;
			                		da.push(a);
			    	        	}
			    	         }
			            });
			        var obj = {
	            		id:terminal.KEYID,
	            		data:da,
	            		field:field,
	            		terminal:terminal
			        };
			        addPoint(obj);
			    }
	        }
	    } else {
	        for (var i = 0; i < initParam.terminal.length; i++) {
	        	var terminal=initParam.terminal[i];
			    if(terminal.TEMPLATENAME!=undefined){
			        var tablename="ssd_ems_bd_"+initParam.terminal[i].TEMPLATENAME;
			        var templateParam=initParam.terminal[i].TEMPLATEPARAM;
			        
			        var displayMode=terminal.DISPLAYMODE;
			        var restartAt=terminal.RESTARTAT;
			        var resourceMapping="";
			        var mappingField="";
			        if(terminal.RESOURCEMAPPING!=undefined){
			        	resourceMapping=terminal.RESOURCEMAPPING;
			        }
			        if(terminal.MAPPINGFIELD!=undefined){
			        	mappingField=terminal.MAPPINGFIELD;
			        }
			        var da = [];
			        var field =null;
			        	$.ajax({
			            	async:false,
			            	data:{tablename:tablename,
			            		templateParam:templateParam},
			            	type: "GET",
			            	url: "map/findAllTerminal",
			            	dataType: "json",
			    	        success: function(data){
			    	        	field=data.field;
			    	        	var data=data.arry;
			            		for(var i=0;i<data.length;i++){
			    	        		var a=data[i];
			    	        		a.x = a.LONGITUDE;
			                		delete a.LONGITUDE;
			                		a.y = a.LATITUDE;
			                		delete a.LATITUDE;
			                		a.img = terminal.ICON;
			                		a.displayMode=displayMode;
				            		a.resourceMapping=resourceMapping;
				            		a.restartAt=restartAt;
				            		a.mappingField=mappingField;
			                		da.push(a);
			    	        	}
			    	         }
			            });
			        var obj = {
	            		id:terminal.KEYID,
	            		data:da,
	            		field:field,
	            		terminal:terminal
			        };
		        	addPoint(null)(obj);
			    }
	        }
	    }
	});
	/**
	 * 清除地图上资源信息以及修改精准发布顶部信息图层
	 */
	var removeResourcesLayer=function(){
		Globel.areaCode="";							//全局变量圈选地区为空
		$(".chanelSelect .un-all-channel").click();	//渠道反选功能
	    $(".pop-earn-table").hide();			//预警详细列表
		$(".cityNum").text("0");					//精准发布顶部城市数量为0
		$(".countyNum").text("0");					//精准发布顶部县数量为0
		$(".areaSize").text("0");					//精准发布顶部面积为0
		$(".trumpetNum").text("0");					//精准发布顶部大喇叭数量为0
		$(".displayNum").text("0");					//精准发布顶部显示屏数量为0
		$("#cityCodes").val("");					//所选市地区编码清空
		$("#countyCodes").val("");					//所选县地区编码清空
		$("#areaCodes").val("");					//所选地区编码清空
		$("#selectedAreaName").val("");				//所选地区名称清空
		$("#publishAreaName").val("");				//地区选择书名称清空
		var resources = map.graphicsLayerIds;
		if(resources!=undefined){
			var delLayer = [];
			for(var i=0;i<resources.length;i++){
				delLayer.push(resources[i]);
			}
			
			for(var i = 0;i<delLayer.length;i++){
				if(delLayer[i]!="player" && delLayer[i]!="regionShiGraphicslayer" ){
					var mark=new Mark();
					mark.remove_CodePolygon(map, delLayer[i]);
				}
			}
		}
	}
  
	// 资源展示显示(左边漂浮层tab切换按钮)
	$(".pop-resources-icon").bind("click",function(){
		// 左侧预警信息隐藏
    	$(".add_box").animate({left: "-500px"}, "slow");
    	$(".pop-warn-icon").animate({left: "-2.3rem"}, "slow");
		$(this).animate({"left":"-2.3rem"}, "slow");
		$(".pop-resourcesall").animate({"left":"0"}, "slow");
		//精准发布隐藏
		$(".personTree").hide();
		$(".accurate-fb").animate({"left":"-36.2em"}, "slow");
		$(".pop-warnpublish-icon").animate({"left":"0"}, "slow");

    	// 顶部预警信息（隐藏）
	    $(".details-md").fadeOut(400);
		// 右侧预警统计（隐藏）事件
	    $(".warn-detail").animate({right: "-24rem"}, "slow");
		// 清除上次点击预警对应的所有图层（渠道图层，地区图层）
		clearWarnMapLayer(initParam.flag);
		//隐藏责任人和受众弹框--开始
    	$(".personLiable-detail .content").empty();
    	$(".personLiable-detail").hide();
    	$(".audience-detail .content").empty();
    	$(".audience-detail").hide();
	});
	// 资源展示关闭(左边漂浮层tab切换按钮)
	$(".pop-resources-title > span:last-child").bind("click",function(){
	    $(".pop-resourcesall").animate({"left":"-251px"}, "slow");
	    $(".pop-resources-icon").animate({"left":"0"}, "slow");
	    $(".pop-warn-icon").animate({"left":"0"}, "slow");
	    $(".zyzs-relt .shade").hide();
		$(".zyzs-relt .resource-content").hide();
	});
	  
	// 精准发布显示(左边漂浮层tab切换按钮)
	$(".pop-warnpublish-icon").bind("click",function(){
		var name=$("#orgName").val();
		$(".company .fb-form-input input").attr("value",name);
		$(".pop-resources-title > span:last-child").click();
		$(this).animate({"left":"-2.3rem"}, "slow");
		$(".accurate-fb").show();
		$(".accurate-fb").animate({"left":"0"}, "slow");
		//左侧预警信息隐藏
    	$(".add_box").animate({left: "-500px"});
    	// 顶部预警信息（隐藏）
	    $(".details-md").fadeOut(400);
		//右侧预警统计（隐藏）事件
	    $(".warn-detail").animate({right: "-24rem"});
		// 清除上次点击预警对应的所有图层（渠道图层，地区图层）
		clearWarnMapLayer(initParam.flag);
		//隐藏责任人和受众弹框--开始
    	$(".personLiable-detail .content").empty();
    	$(".personLiable-detail").hide();
    	$(".audience-detail .content").empty();
    	$(".audience-detail").hide();
	});
	  
	//精准发布关闭事件
	$(".accurate-fb .flbox .top .title-top .ttr").on("click",function(){
		$(".personTree").hide();
		$(".accurate-fb").animate({"left":"-36.2em"}, "slow");
		$(".pop-warnpublish-icon").animate({"left":"0"}, "slow");
//	    $(".pop-warn-icon").animate({"left":"0"}, "slow");
	});
	
    // 查询条件处理（显示）
    $(".pop-search-icon").bind("click",function(){
		// 清除上次点击预警对应的所有图层（渠道图层，地区图层）
		clearWarnMapLayer(initParam.flag);
    	//显示查询条件信息
    	$(".pop-search").animate({right: 0}, "slow");
    	$(this).animate({right: -($(this).width()+5)}, "slow");
    	//左侧信息隐藏
    	$(".add_box").animate({left: "-500px"}, "slow");
    	 $(".pop-warn-icon").animate({"left":"0"}, "slow");
    	// 顶部预警信息（隐藏）
	    $(".details-md").fadeOut(400);
		//右侧预警统计（隐藏）事件
	    $(".warn-detail").animate({right: "-24rem"});

    	//隐藏责任人和受众弹框--开始
    	$(".personLiable-detail .content").empty();
    	$(".personLiable-detail").hide();
    	$(".audience-detail .content").empty();
    	$(".audience-detail").hide();
    });
    
    // 查询条件处理（隐藏）
    $(".pop-search > .pop-search-content > ul > span:last-child").bind("click",function(){
    	$(".pop-search").animate({left: -($(".pop-search").width()+15)}, "slow");
    	$(".pop-search-icon").animate({right: 0}, "slow");
    });

    // 图例处理 (隐藏)
    $(".pop-left-bottom > div").bind("click",function(){
    	var obj = $(this).parent();
    	$(obj).animate({left: -($(obj).outerWidth(true)+3)}, "slow");
    	$(".pop-left-bottom-tuli").animate({left: 0}, "slow");
    });
    // 图例处理（显示）
    $(".pop-left-bottom-tuli").bind("click",function(){
    	$(".pop-left-bottom").animate({left: 0}, "slow");
    	$(this).animate({left: -($(this).width()+15)}, "slow");
    });
    
    // 地图操作选择
    $(".navbar-nav > li.map-option > i").bind("click",function(){
    	if(Globel.mapMgLayerFlag==true){
			locateExtent_geojson();
			Globel.mapMgLayerFlag=false;
		}
    	hiddenDiv();
    	channel();							// 初始化渠道信息
	    initDisasterName();					// 获取灾种名称（精准发布）
	    initPublishAreaTree();				// 初始化发布地区信息树
//	    sensitiveWord();						// 获取敏感字信息
		removeResourcesLayer();//清除地图上资源信息图层
		var ztreeObj=$.fn.zTree.getZTreeObj("publishAreaTree");//取消勾选地区树
			ztreeObj.checkAllNodes(false);
		var mark = new Mark();
		var graphicLayer = mark.getGraphicsLayer("regionHNGraphicslayer");
		graphicLayer.clear();
    	var bool = $(this).hasClass("active");
    	$(this).parent().parent().children("li").find(".active").removeClass("active");
//    	consoleDraw();
    	if(bool){
    		$(this).removeClass("active");//删除选中样式问题
    		if(mapParam.drawing != null){
    			mapParam.drawing.remove();	
    		}
    	}else{
    		$(this).addClass("active");
    	}
    	// 半径圈选
    	var ca = $(this).hasClass("fa-circle-thin active");
    	var cycle = $(this).parent().parent().find(".cycle-check");
    	var div = $(cycle).find("div");
    	if(ca){
        	$(div).slideDown(500);
    	}else{
    		// 控制单选按钮组样式
    		$(div).children("ul").children("li:first-child").children("label").children("input").val("");
    		$(div).children("ul").children("li:last-child").children("label").children("i").removeClass("fa-circle active").addClass("fa-circle-o");
    		$(div).slideUp(500);
    	}
    	// 任意圈选
    	var moon = $(this).hasClass("fa-moon-o active");
    	if(moon){	
    		drawKuangxuan();
    	}else{
    		if(mapParam.drawToolbar!=null){
    			mapParam.drawToolbar.deactivate();
    			map.graphics.clear();//清除图层
    			
    			var mark = new Mark();
    			var graphicLayer = mark.getGraphicsLayer("drawGraphicslayer");
    			graphicLayer.clear();
    		}
    	}
    });
    // 圈选（半径选择）
    $(".cycle-check > div > ul > li:last-child > label > i").bind("click",function(){
    	// 清空半径文本框输入数据
    	$(".cycle-check > div > ul > li:first-child > label > input").val("");
    	var bool = $(this).hasClass("active");
    	$(this).parent().find(".fa-circle").removeClass("fa-circle active").addClass("fa-circle-o");
    	if(!bool){
    		$(this).addClass("fa-circle active");
    		var cycle = $(this).data("cycle");
    		draw(cycle);
    	}else{
    		consoleDraw(); // 清空圈选
    	}
    });
    // 圈选（半径选择）
    $(".cycle-check > div > ul > li:first-child > label > input").bind("click",function(){
    	// 还原单选半径按钮样式
    	$(".cycle-check > div > ul > li:last-child > label > i").removeClass("fa-circle active").addClass("fa-circle-o");
    });
    // 地图圈选
	$("#draw, #topDraw").bind("click",function(){
	var drawRadius = $(this).parent().children("label").children("input").val();
		if(drawRadius.length==0){
		  	var promptBox=box();
		    promptBox.content="请输入查询半径";
		    Move.pop(promptBox);
			return false;
		}
		draw(drawRadius);
    });   
	
	 // 设置中心
    $(".cogs").bind("click",function () {
        $(".setting-list").show();
    });
    // 关闭设置中心
    $(".setting-list-title > span:last-child").bind("click",function () {
        $(".setting-list").hide();
        //关闭工具栏信息
//        $(".setting-tool").hide();
    });
    // 拖动设置中心
    $(".setting-list > .setting-list-title").bind("mousedown",function(event){
        Move.mouseDown($(".setting-list"), event);
        return false;
    });
    // 设置中心皮肤切换
    $(".setting-list > div.setting-list-title-theme-choose > ul").bind("click",function(){
    	var check = "<i class='fa fa-check'></i>";
    	$(this).parent().find("i").remove();
    	$(this).append(check);
    });
    
    // 系统设置中心 关闭 工具箱
    $(".setting-list > .setting-list-content > .huatu-config").bind("click", function () {
        var cls = $(this).data("toggle");
        var display = $(cls).css("display");
        if(display!="block"){
            $(cls).show();
            $(this).children("span:last-child").text("关闭");
            $("#top-tool").children("ul:last-child").hide();
            $(".ssd > nav .navbar-nav .show_").hide();
        }else{
            $(cls).hide();
            
            $(this).children("span:last-child").text("开启");
        }
    });
    // 地图图层样式切换
    $(".setting-map-choose ul span").bind("click", function () {
     	$(".setting-map-choose ul span").removeClass("active");
    	$(this).addClass("active");
    	var type = $(this).data("mapType");
    	mapCheck(type);
		if(Globel.mapMgLayerFlag==true){
			locateExtent_geojson1();
		}
/*		else{
			locateExtent_geojson();
			var mark = new Mark();
			mark.mark_setStyle(map, "regionShiGraphicslayer",type);
		}*/
    });
    
    // 设置顶部工具箱
    $(".setting-list > .setting-list-content > .huatu-config-top").bind("click", function () {
    	$(".setting-tool").hide();
    	$("#top-tool").children("ul:last-child").show();
    	$(".ssd > nav .navbar-nav .show_").show();
    	$(".setting-list-content").children("ul:last-child").children("span:last-child").text("开启");
    });
    // 拖动工具箱
    $(".setting-configs > .setting-tool > .setting-title").bind("mousedown",function(event){
        Move.mouseDown($(".setting-tool"), event);
        return false;
    });
    // 关闭工具箱
    $(".setting-configs > .setting-tool > .setting-title > span:last-child").bind("click", function () {
        $(".setting-tool").hide();
        var cls = $(this).data("toggle");
        $(cls).children("span:last-child").text("开启");
        $(".huatu-config").data("display","none");
    });
    
    // 生成随机数
	var generateMixed = function (n) {
		var chars = ['0','1','2','3','4','5','6','7','8','9'];
	     var res = "";
	     for(var i = 0; i < n ; i ++) {
	         var id = Math.ceil(Math.random()*10);
	         res += chars[id];
	     }
	     return res;
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
    
    // 点击灾种名称弹出灾种名称数据（精准发布）
    $(".disaster-select > span > input").bind("click",function () {
        var display=$(".disaster-name-tree ").data("display");
        if(display=="hidden"){
            $(".disaster-name-tree").show();
            $(".disaster-name-tree").data("display","show");
        }else{
            $(".disaster-name-tree").hide();
            $(".disaster-name-tree").data("display","hidden");
        }
    });
    
    // 点击地区名称弹出地区名称数据（精准发布）
    $(".publisharea-select > span > input").bind("click",function () {
        var display=$(".publish-area-tree").data("display");
        if(display=="hidden"){
            $(".publish-area-tree").show();
            $(".publish-area-tree").data("display","show");
        }else{
            $(".publish-area-tree").hide();
            $(".publish-area-tree").data("display","hidden");
        }
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

    // 预警类型选择
    $(".earn-type > i").bind("click", function () {
        var bool = $(this).hasClass(".checked");
        if(!bool){
            $(this).parent().find(".checked").removeClass("fa-circle checked").addClass("fa-circle-o");
            $(this).addClass("fa-circle checked").removeClass("fa-circle-o");
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

    var nowdateTime = function(){
    	var date = new Date();
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
    
    /**
     * 雷达以及云图查询时间
     */
    var onlyDay= function(){
    	var now = new Date();
    	var date = new Date(now.getTime());
    	var year = date.getFullYear();
    	var month = date.getMonth() + 1;
    	var day = date.getDate();
    	month = month < 10 ? "0"+month : month;
    	day = day < 10 ? "0" + day : day;
    	return year + '-' + month + '-' + day;
    };
    
    // 定时发布时间
    laydate.render({
        theme: "molv",
        elem: "#timingPublishTime",
        min: nowdateTime(),
        type: "datetime",
        value: ""
    });
    
    // 发布时间
    laydate.render({
        theme: "molv",
        elem: "#publishTime",
        type: "datetime",
        max: nowdateTime(),
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
    
    // 台风查询开始时间
    laydate.render({
        elem: "#typhoonStartTime",
        value: onlyDay()
    });
    // 台风查询结束时间
    laydate.render({
        elem: "#typhoonEndTime",
        value: onlyDay()
    });
    
    // 雷达查询开始时间
    laydate.render({
        elem: "#radarStartTime",
        value: onlyDay()
    });
    // 雷达查询结束时间
    laydate.render({
        elem: "#radarEndTime",
        value: onlyDay()
    });
    
    // 卫星云图查询开始时间
    laydate.render({
        elem: "#cloudStartTime",
        value: onlyDay()
    });
    //  卫星云图查询结束时间
    laydate.render({
        elem: "#cloudEndTime",
        value: onlyDay()
    });
    
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
		         url: "entry/findDisasterTree",
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

    /**
     * 根据灾种匹配颜色
     */
    var matchColor=function(earnId){
		$.ajax({
			 async:false,
	         type: "POST",
	         url: "map/findDisasterByColor",
	         data: {keyId: earnId},
	         dataType: "json",
	         success: function(data){
	        	 if(data.length>0){
	        		 var html="";
	        		 $.each(data,function(i,item){
					 var dataobj=JSON.stringify(item);
						html += "<li>";
						html += 	"<span class='fa-check' style='background: "+item.LEVELNO+"'>&nbsp;</span>";
						html += "</li>";
					 });
	        		 $(".clslct").empty().prepend(html);
	        	 }else{
	        		 $(".clslct").empty();
	        	 }
	        	//发布预警颜色选择添加样式事件
        		$(".selectcl ul li").on("click",function(){
        			var disasterId=$("#disasterId").val();
					if($(this).hasClass("on")){
    					$(this).removeClass("on");
    					$("#colorLevel").val("");
    					$("#discolor").val("");
    					$("#publishlevelId").val("");
    					$("#publishlevel").val("");
    				}else{
    					var li = $(this).parent().children(".on");
    				 	for(var i = 0, len = li.length; i<len; i++){
    				 		$(li[i]).removeClass("on");
    					}
    					$(this).addClass("on");
    					var colorLevel=$(this).children("span").css("background-color");
    					if(colorLevel=="rgb(255, 0, 0)"){
    						$("#colorLevel").val(1);
    						$("#discolor").val("红色");
    						$("#publishlevelId").val(5);
    						$("#publishlevel").val("Ⅰ级/特别严重");
    					}else if(colorLevel=="rgb(255, 165, 0)"){
    						$("#colorLevel").val(2);
    						$("#discolor").val("橙色");
    						$("#publishlevelId").val(6);
    						$("#publishlevel").val("Ⅱ级/严重");
    					}else if(colorLevel=="rgb(255, 255, 0)"){
    						$("#colorLevel").val(3);
    						$("#discolor").val("黄色");
    						$("#publishlevelId").val(7);
    						$("#publishlevel").val("Ⅲ级/较重");
    					}else if(colorLevel=="rgb(0, 0, 255)"){
    						$("#colorLevel").val(4);
    						$("#discolor").val("蓝色");
    						$("#publishlevelId").val(8);
    						$("#publishlevel").val("Ⅳ级/一般");
    					}else if(colorLevel=="rgb(0, 255, 0)"){
    						$("#colorLevel").val(9);
    						$("#discolor").val("综合");
    						$("#publishlevelId").val(10);
    						$("#publishlevel").val("综合");
    					}
    					var param={
    						disasterId:disasterId,
    						colorLevel:$("#colorLevel").val(),
    						level:$("#publishlevelId").val(),
    						orgId:$("#orgId").val()
    					};
    					$.ajax({
    						async:false,
    						data:param,
    						dataType:"json",
    						type:"POST",
    						url:"map/matchingStrategy",
    						success:function(data){
    							if(data.length>0 ){
									if(data[0].IMG != undefined || data[0].IMG == "undefined" ){
										initParam.warnPic=data[0].IMG;
									}
									if(data[0].DURATION != undefined || data[0].DURATION == "undefined" ){
										$("#continueTime").val(data[0].DURATION);
									}else{
										$("#continueTime").val("24");
									}
									if(data[0].ISRECORD != undefined || data[0].ISRECORD == "undefined" ){
    								    if(data[0].ISRECORD==1){
    									   $(".record span label:first-child input").prop("checked", true);
    								    }else{
    									   $(".record span label:last-child input").prop("checked", true);
    								    }
    							   	 }else{
    							   	   $(".record span label:last-child input").prop("checked", true);
    							   	 }
								}else{
									$("#colorLevel").val("");
									$("#publishlevelId").val("");
									$("#publishlevel").val("");
									$("#continueTime").val("24");
									$(".selectcl ul li").removeClass("on");
									$(".record span label:last-child input").prop("checked", true);
    							 	var promptBox=box();
    							    promptBox.content="请配置策略";
    							    Move.pop(promptBox);
    					    		return false;
    							}
    						}
    					});
    				}
        		});
	         }
		});
    }
    
    var getIssueNo = function(disasterId,earnName){
    	$.ajax({
    	   async:false,
           type: "POST",
           url: 'entry/getIssueNo',
           data: {disasterId:disasterId},
           dataType: "json",
           success: function(data){
        	    var name=earnName+"预警";
        	   	var html="";
    		    html +="<li style='width: 100%;display: inline-block;' class='stageFlag'>";
    		    html +=		"<span class='fb-form-text yearStage' style=' display: inline-block; padding: 8px 5px; width: 18.5%;float: left;' >"+data.year+"年&nbsp;&nbsp;&nbsp;&nbsp;总</span>";
    		    html +=     "<span class='fb-form-text' style=' display: inline-block; padding: 5px 0px; width: 26.5%;float: left;'>";
    		    html +=     	"<input class='form-control' id='allStageNum' type='text' value='"+data.stage+"'  >";
    		    html +=     "</span>";
    		    html +=     "<span class='fb-form-text'  style=' display: inline-block; padding: 8px 0px; width: 5%;float: left;'>期</span>";
			    html +=     "<span class='fb-form-text warnStage'  style=' display: inline-block; padding: 8px 4px; width: 19.5%;float: left;' title='"+name+"'>"+name.substring(0,4)+"&nbsp;&nbsp;&nbsp;&nbsp;第</span>";
			    html +=     "<span class='fb-form-text'  style=' display: inline-block; padding: 5px 0px; width: 25.5%; float: left;'>";
			    html +=     	"<input class='form-control' id='stageNum' type='text'  value='"+data.issueNo+"'>";
			    html +=     "</span>";
			    html +=     "<span class='fb-form-text'  style=' display: inline-block; padding: 8px 0px; width: 5%;float: left;'>期</span>";
			   	html +="</li>";
			   	var flag=$(".publishmsg  > .publishbase > li").find(".stageFlag");
			   	if(flag){
			   		$(".publishmsg  > .publishbase > li").remove(".stageFlag");
			   	}
			    $(".publishmsg  > .publishbase > li:nth-child(4)").after(html);
			    
				var almSerialNo = data.year+"年 总"+data.stage+"期 "+name+"";
	    		almSerialNo=almSerialNo+" 第"+data.issueNo+"期";
	    		$("#almSerialNo").val(almSerialNo);
			    
			    $("#allStageNum").keyup(function () {
			    	this.value = this.value.replace(/[^\d]/g, '');
			    });
			    $("#stageNum").keyup(function () {
			    	this.value = this.value.replace(/[^\d]/g, '');
			    });
			    
           	}
		});
    }
    
    /**
    *加载预警名称树
    */
    var initDisasterName = function(){
    	var treeCheck = function(e, treeId, treeNode,clickFlag){
			var earnId = treeNode.id;
			var earnName = treeNode.name;
			$("#disasterId").val(earnId);
			$("#disasterName").val(earnName);
			$("#disasterNameTree").hide();
			$(".selectcl ul li").removeClass("on");
			$("#colorLevel").val("");
			$("#publishlevelId").val("");
			$("#publishlevel").val("");
			$("#continueTime").val("24");
			earnTree.checkNode(treeNode, !treeNode.checked, true);   
			getIssueNo(earnId,earnName);
			matchColor(earnId);
		}
    	var setting = {
			check: {enable: true,chkStyle: "radio",radioType: "level",
            chkboxType: { "Y": "s", "N": "s" }},
			view: {dblClickExpand: false},
			data: {simpleData: {enable: true}},
			callback: {onCheck: treeCheck,onClick: treeCheck}
		},earnTree;
	/**
	 * 读取灾种数据， 获取灾种树
	 */ 
	var tree = function(){
		var treeData = null;
			$.ajax({
				 async:false,
		         type: "POST",
		         url: "entry/findDisasterTree",
		         data: {keyId:$("#orgId").val()},
		         dataType: "json",
		         success: function(data){
    		        treeData = data;
		         }
			});
			return treeData;
		};
		var data = tree();
		earnTree = $.fn.zTree.init($("#disasterNameTree"), setting, data);
    };
    //发布预警选择渠道树
    $(".personTree .sz-header .sz-btn span").on("click",function(){
    	var name=$(this).text();
    	if(name=="责任人"){
    		 $("#responsTree ").show();
    		 $("#audienceTree ").hide();
    	}else{
    		 $("#audienceTree ").show();
    		 $("#responsTree ").hide();
    	}
    });
  	
    /**
     * 地区数据加载
     */
    var initPublishAreaTree = function(){
    	var userareaId=$("#areaId").val();
    	var treeCheck = function(e, treeId, treeNode, clickFlag){
    		removeResourcesLayer();
    		//清除圈选的图层
    		if(map.graphics!=undefined){
    			map.graphics.clear();
    		}
    		//使其圈选图层失效
    		if(mapParam.drawToolbar!=null){
        		mapParam.drawToolbar.deactivate();
        		map.graphics.clear();//清除图层
    		}
    		if(mapParam.drawing != null){
    			mapParam.drawing.remove();	
    		}
    	    $(".navbar-nav > li.map-option").find(".active").removeClass("active");
			var areaIds = "";	//勾选的地区ID
			var areaCodes = "";	//勾选的地区编码
			var areaNames = "";	//勾选的地区编码
			var cityNum = 0;	//勾选市的数量
			var countyNum = 0;	//勾选县的数量
			var areaCodeArry=[];
			var treeObj = $.fn.zTree.getZTreeObj("publishAreaTree");
			var nodes=treeObj.getCheckedNodes(true);
			if(nodes.length>0){
				for(var i = 0 ,len=nodes.length; i<len;  i++){
					areaIds += "," + nodes[i].id;
					areaCodes += "," + nodes[i].code;
					areaNames += "," + nodes[i].name;
					areaCodeArry.push(nodes[i].code.substring(0,6));
					var level=nodes[i].level;
					if(level=="1"){
						cityNum=cityNum+1;
					}else if(level=="2"){
						countyNum=countyNum+1;
					}
				}
			}
			areaIds != "" ? areaIds = areaIds.substring(1) : areaIds ;
			$("#areaCodes").val(areaIds);
			$("#publishAreaName").val(areaNames.substring(1));
			$("#publishAreaName").attr("title",areaNames.substring(1));
			Globel.areaCode=areaCodes.substring(1);
			$(".cityNum").text(cityNum);
			$(".countyNum").text(countyNum);
			selectedDisFromTree(areaCodeArry);
			return areaIds;
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
/*	                chkboxType: { "Y": "ps", "N": "ps" }*/
					chkboxType: { "Y": "ps", "N": "s" }
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
		areaTree=$.fn.zTree.init($("#publishAreaTree"), setting);
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
//					url: "area/find",
					autoParam: ["id=parentId"]
				},
				check: {
					enable: true,
	                chkStyle: "checkbox",
	                radioType: "level",
	                chkboxType: { "Y": "ps", "N": "ps" }
				},
				view: {
					dblClickExpand: true   
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
		var nodeList = areaTree.getNodes();
　　　　　　//展开第一个根节点
		areaTree.expandNode(nodeList[0], true);

    };
    
    /**
     * 预警查询参数封装
     */
    var param = function(){
    	var p = {
    		startDate:null,
			endDate:null,
    		name:null,
			page:0,
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

    /**
     * 点击预警列表数据时，展示顶部当前点击的预警信息
     */
    var topWarnInfo = function(json){
    	var content="";
    	$.ajax({
			async:false ,
			type: "POST",
			url: 'event/warnNameByTaskId',
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
    	if($(".details-md").length>0){
    		$(".details-md").empty();
    		}
		$(".details-md").prepend(html);
		// 点击顶部预警关闭按钮事件
        $(".details-md > .porltv > span ").bind("click",function(){
        	$(".details-md").empty();
        	$(".details-md").fadeOut(400);
        });
    };
    
    /**
     * 后台加载预警渠道统计右侧预警信息
     */
    var rightWarnChannelAndAreaCountInfo = function(json){
		$.ajax({
			async:false,
			type:"POST",
			url:"map/warnStatistics",
			dataType: "json",
			data: json,
			success:function(data){
				initParam.flag=data.detail.areaId;								//标注当前预警唯一标志
				detailHtml(data.detail);										//拼接详细信息HTML
				channelHtml(data.channel);										//拼接渠道HTML	
				areaHtml(data.area);											//拼接影响地区HTML
				personLiableHtml(data.person.personLiable,data.detail);			//拼接责任人数HTML
				audiencesHtml(data.person.audiences,data.detail);				//拼接受众人数HTML
				// 查询发布渠道点击事件
			    $(".qd-list > ul > li").bind("click", function(){
			    	var num = $(this).children("i").length;
			    	var channel = $(this).data("channel");
			    		// 存储渠道对应的gis定点值域（经纬度，以及必要的协助信息）
				    	var areaChannelList = [];
		            	var areaList = channel.area;
		            	for(var i =0, len=areaList.length; i<len; i++){
		            		areaChannelList.push({
		            			"channelId":channel.id,
		            			"channelName":channel.name,
		            			"areaCode":areaList[i].areaCode,
		            			"areaChannelLayer":areaList[i].areaCode+"_"+channel.id,
		            			"img":channel.image,
		            			"x":areaList[i].longitude,
		            			"y":areaList[i].latitude
		            		});
		            	}
		            	// 判断当前渠道是否勾选上，根据initParam.checked全局属性值判断
			            if(num==0){
			            	// 勾选：当前选中渠道添加对号样式
			                $(this).append(initParam.checked);
			                // 在地图上，添加勾选后的渠道图层
			                addChannelPoint(areaChannelList,initParam.flag);
			            }else{
			            	// 取消勾选：当前选中渠道删除对号样式
			                $(this).children("i").remove();
			                // 存储当前预警对应的渠道图层id也就是图层名称，便于遍历清除图层
			                var channelAreaId = [];
			                // 找出当前预警对应的渠道图层（一个预警对应多个渠道，一个渠道对应多个地区）
			                for(var i =0, len=areaChannelList.length; i<len; i++){
			                	if(areaChannelList[i].channelId == channel.id){
			                		channelAreaId.push(areaChannelList[i].areaCode+"_"+channel.id);
			                	}
			                }
			                // 在地图上，删除当前预警对应的渠道图层
			                addChannelPoint(null,initParam.flag)(channelAreaId);
			            }
			            return false;
			    	});
			    // 地区点击事件
			    $(".area").click(function(){
			    	// 获取当前地区信息
			    	var area = $(this).data("area");
			    	// 判断当前地区是否勾选，如果勾选上，则清除对应地区图层，否则添加当前地区图层
			    	if($(this).children("span").hasClass("fa-check")){
			    		// 清除地区选中样式
			    		$(this).children("span").eq(0).removeClass("fa-check");
			    		consoleShowByCode(area.areaCode,initParam.flag);
			    	}else{
			    		// 添加地区选中样式
			    		$(this).children("span").eq(0).addClass("fa-check");
			    		checkShowByCode(area.areaCode,initParam.flag,area.colorName);
			    	}
			    });
			}
		});
    };
    /**
     * 初始化加载左侧预警信息
     */
    var initWarning =function(page){
    	debugger;
    	var da = param();
    	da.page=page;
    	$.ajax({
    		 async:false,
             type: "POST",
             url: "map/findSendWarnInfo",
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
            			if(item.img=="" ||item.img==null){
            				html += "	<dt ><img src="+initParam.warnPic+" id='warnLogo' /></dt>";
            			}else{
            				html += "	<dt ><img src='' id='warnLogo' /></dt>";
            			}
     	        		if(item.typeName =="预警信号"){
     	        			html += "<dd>预警名称: 发布"+item.disasterName+item.colorLevelName+"预警信号</dd>";
     	        		}else{
     	        			html += "<dd>预警名称: 发布"+item.disasterName+item.colorLevelName+"预警</dd>";
     	        		}
     		        	html += "	<dd>发布时间: "+item.sendTime+"</dd>";
     		/*        	html += "	<dd>预警地区: "+item.areaName+"</dd>";*/
     		        	html += "</dl>";
     		        	html += "<dl></dl>";
            		 });
            		 html += "<ul>";
            		 html += "	<span id='nextPage' data-next-page='"+page+"' data-count='"+count+"'>下页</span>";
            		 html += "	<span id='topPage' data-top-page='"+page+"' data-count='"+count+"'>上页</span>";
            		 html += "	<span>第"+(Number(page)+1)+"页</span>";
            		 html += "	<span>总数"+count+"条</span>";
            		 html += "</ul>";
        /*    		 $(".pop-warn").css("position","");
            		 $(".pop-warn").css("width","");
            		 $(".pop-warn").css("height","");*/
            		 $(".pop-warn").empty().append(html);
            		 $(".pop-search > .pop-search-content > ul > span:last-child").click();// 右侧查询信息（隐藏）事件
            		 $(".add_box").show();
            		 $(".add_box").animate({left: "0"}, "slow");
//            		 $(".pop-warn-icon").click();						// 左侧预警信息（显示）事件
//            	     $(".pop-warn dl:nth-child(1)").click();			// 左侧预警列表(点击)事件
            	 }else{
            		 var promptBox=box();
            		 promptBox.content="暂无数据！请选择其他查询条件";
 				     Move.pop(promptBox);
            /*		 html += "<div><p class='noData'>暂无数据！</p><div>";
            		 $(".pop-warn").empty().append(html);
            		 $(".pop-warn").css("position","relative");
            		 $(".pop-warn").css("width","100%");
            		 $(".pop-warn").css("height","420px");*/
            	 }
             }
    	});
  
    // 左侧预警信息（隐藏）事件
	$("#leftWarnClose").bind("click",function(){
   		 $(".add_box").animate({left: "-500px"}, "slow");
   		 $(".pop-warn-icon").animate({left: "0px"}, "slow");
	     // 顶部预警信息（隐藏）
	     $(".details-md").fadeOut(400);
		 // 右侧预警统计（隐藏）
	     $(".warn-detail").animate({right: "-24rem"}, "slow");
   	 });
	   	 
   	// 左侧预警信息（显示）事件
   	$(".pop-warn-icon").bind("click",function(){
/*   		//精准发布隐藏
		$(".personTree").hide();
		$(".accurate-fb").animate({"left":"-36.2em"}, "slow");
		$(".pop-warnpublish-icon").animate({"left":"0"}, "slow");   
		//资源展示隐藏
		$(".pop-resourcesall").animate({"left":"-251px"}, "slow");
	    $(".pop-resources-icon").animate({"left":"0"}, "slow");
   		$(".pop-warn-icon").animate({left: "-2.4rem"}, "slow");*/
   		$(".add_box").show();
   		$(".add_box").animate({left: "0"}, "slow");
    });
    // 点击（下页）按钮事件
    $("#nextPage").bind("click",function(){
    	var nextPage = $(this).data("nextPage");
    	var count = $(this).data("count");
    	if((nextPage+1)*5>=count){
    		return false;
    	}
    	initWarning(nextPage+1);
     	// 左侧预警列表(点击)事件
    	$(".pop-warn dl:nth-child(1)").click();
    });
    // 点击（上页）按钮事件
    $("#topPage").bind("click",function(){
    	var topPage = $(this).data("topPage");
    	var count = $(this).data("count");
    	if(topPage==0){
    		return false;
    	}
    	initWarning(topPage-1);
     	// 左侧预警列表(点击)事件
    	$(".pop-warn dl:nth-child(1)").click();
    });
        
	// 左侧预警信息点击事件
    $(".pop-warn dl:nth-child(2n-1)").bind("click",function(){	
			$(this).parent().children("dl.active").removeClass("active");
			$(this).addClass("active");
			// 清除上次点击预警对应的所有图层（渠道图层，地区图层）
			clearWarnMapLayer(initParam.flag);
			// 右侧预警信息（显示）
			/*$(".warn-detail").show();
			$(".warn-detail").animate({right: "0"});*/
			// 获取当前预警详细信息
			var json = $(this).data("warn");
			topWarnInfo(json);
			warnAllDetail(json);
			//顶部详细预警信息显示
			$(".details-md").show();
			/*// 后台加载预警渠道统计右侧预警信息
		    rightWarnChannelAndAreaCountInfo(json);
		    // 当前地区图层绑定到地图
		    $(".area").click();
		    // 当前渠道图层绑定到地图
		    $(".qd-list > ul > li").click();
	    	//隐藏责任人和受众详细信息弹框
	    	$(".personLiable-detail .content").empty();
	    	$(".personLiable-detail").hide();
	    	$(".audience-detail .content").empty();
	    	$(".audience-detail").hide();*/
		});
    };
    
	/**
     * 查询预警所有信息
     */
    var warnAllDetail = function(json){
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
			url: 'event/warnAllDetail',
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
	        	addWarnLogoOnMap(areaWarnList,1);	//现有的
        	}
        })
    };
    /**
     * 右侧详细信息
     */
    var detailHtml = function(detail){
			var html="";
			html += "<div class='xqlist'>";
			html += "	<div class='xqleft'>预警名称：</div>";
			html += "	<div class='xqright'>"+detail.disasterName+detail.colorLevelName+(detail.typeName =="预警信号"?"预警信号":"预警")+"</div>";
			html += "</div>";
			html += "<div class='xqlist'>";
			html += "	<div class='xqleft'>预警级别：</div>";
			html += "	<div class='xqright'>"+detail.numberLevelName+"</div>";
			html += "</div>";
			html += "<div class='xqlist'>";
			html += "	<div class='xqleft'>预警类型：</div>";
			html += "	<div class='xqright'>"+detail.typeName+"</div>";
			html += "</div>";
			html += "<div class='xqlist'>";
			html += "	<div class='xqleft'>发布单位：</div>";
			html += "	<div class='xqright'>"+detail.orgName+"</div>";
			html += "</div>";
			html += "<div class='xqlist'>"
			html += "	<div class='xqleft'>发布时间：</div>";
			html += "	<div class='xqright'>"+detail.sendTime+"</div>";
			html += "</div>";
		$(".warn-detail > .yjscro > .xqct:first").empty().append(html);
    };
    
    /**
     * 预警渠道信息
     */
    var channelHtml =function(channel){
    	var html="";
			html += "<div class='qd-list' id='channel'>";
			html += "	<ul>";
			$.each(channel.list,function(i,item){
				item.area=channel.map;
				var obj = JSON.stringify(item);
					html += "<li data-channel='"+obj+"'  title='"+item.name+"'><img src="+item.image+" alt='' >";
					html +=	"</li>";
				});
			html += " 	</ul>";
			html += "</div>";
		$("#channelCount").text(JSON.stringify(channel.count));
		$(".warn-detail > .yjscro > .xqct ").eq(1).empty().prepend(html);
		$(".qd-list > ul > li").click();
    };
	
    /**
     * 预警影响地区信息
     */
    var areaHtml =function(area){
		var html="";
			html += "<div class='effect-list'>";
			html += "	<ul>";
			 $.each(area.list,function(i,item){
				var obj = JSON.stringify(item);
     			html += "<li class='area'  title="+item.areaName+" data-area='"+obj+"'>";
     			html += "   <span class='ico'></span>";
     			html += "   <span class='title'>"+item.areaName+"</span>";
     			html += "</li>";
			 });
			html += "	</ul>";
            html += "</div>";
        $("#areaStaticCount span i").text(JSON.stringify(area.count));
        $(".warn-detail > .yjscro > .xqct ").eq(2).empty().prepend(html);
    };
    /**
     * 责任人与受众详细列表
     */
    var personLiableDetailList=function(page,choiceId,param,userType,name,telNum){
		if(name==null){
			name="";
		}
		if(telNum==null){
			telNum="";
		}
    	if(userType=="1"){
    		$(".personLiable-detail").show();
    	}else if(userType=="2"){
    		$(".audience-detail").show();
    	}
    	param.choiceId =choiceId;
    	param.searchName =name;
    	param.searchNum =telNum;
    	param.page =page;
    	param.limit =6;
    	param.userType =userType;
    	$.ajax({
    		async: false,
            url: "map/personLiableList",
            data: param,
            type: "POST",
            dataType: "json",
    		success:function(data){
    			var list=data.list;
    			var count=data.count;
    			var html="";
	    			html +="<div class='query-criteria' >";
	    			html +="	<input  class='name' type='text' placeholder='"+(name =="" ? "名称：": "")+"' id='name' value="+name+" >";
	    			html +="	<input  class='telNum' type='text' placeholder='"+(telNum =="" ? "号码：": "")+"' id='telNum' value="+telNum+" >";
	    			html +="	<span class='span02'><i class='fa fa-search' ></i></span>";
	    			html +="</div>";
	    			html +="<div class='line' >";
	    			html +="</div>";
					html +="<div class='detail-list' >";
					html +="<table class='persontable' >";
					html +="<thead >";
					html +="	<tr>";
					html +="	<td  >名称</td>";
					html +="	<td  >号码 </td>";
					html +="	</tr>";
					html +="</thead>";
					html +="<tbody>";
					if(list.length>0){
						$.each(list,function(i,item){
							html +="<tr>";
							html +="	<td style='width: 16.8rem;text-align:center; '>"+item.TYPENAME+"</td>";
							html +="	<td style='width: 16rem;text-align:center; ' title='"+item.METHODCONTENT+"'>"+item.METHODCONTENT.substring(0,11)+"</td>";
							html +="</tr>";
						});
					}else{
						html +="<tr><td style='width: 350px;text-align:center; '>无数据</td></tr>";
					}
					html +="</tbody>";
					html +="</table>";
					html +="<ul class='ul_btn' >";
					html +="	<span id='personDetailClose'>关闭</span>";
					html +="	<span id='personDetailNextPage' data-next-page='"+page+"' data-count='"+count+"'>下页</span>";
					html += "	<span id='personDetailTopPage'  data-top-page='"+page+"' data-count='"+count+"'>上页</span>";
					html +="	<span>总数"+count+"条</span>";
					html +="</ul>";
					html +="</div>";
			  	if(userType=="1"){
			  		$(".personLiable-detail .content").empty().prepend(html);
			  	}else if(userType=="2"){
			  		$(".audience-detail .content").empty().prepend(html);
			  	}
			}
		});
	    	
    	// (查询)事件
       	$(".personLiable-detail .content .query-criteria .span02").bind("click",function(){
       		var name=$(".personLiable-detail .content .query-criteria .name").val();
       		var telNum=$(".personLiable-detail .content .query-criteria .telNum").val();
       		personLiableDetailList(0,choiceId,param,userType,name,telNum);
        });
     	$(".audience-detail .content .query-criteria .span02").bind("click",function(){
       		var name=$(".audience-detail .content .query-criteria .name").val();
       		var telNum=$(".audience-detail .content .query-criteria .telNum").val();
       		personLiableDetailList(0,choiceId,param,userType,name,telNum);
        });
    	  // 关闭事件
	   	$("#personDetailClose").bind("click",function(){
	   		if(userType=="1"){
	   			$(".personLiable-detail .content").empty()
	   			$(".personLiable-detail").hide();
		  	}else if(userType=="2"){
		  		$(".audience-detail .content").empty()
	   			$(".audience-detail").hide();
		  	}
	   	 });
        // 点击（下页）按钮事件
        $("#personDetailNextPage").bind("click",function(){
        	var nextPage = $(this).data("nextPage");
        	var count = $(this).data("count");
        	if((nextPage+1)*6>=count){	
        		return false;
        	}
        	personLiableDetailList(nextPage+1,choiceId,param,userType,name,telNum);
        });
        // 点击（上页）按钮事件
        $("#personDetailTopPage").bind("click",function(){
        	var topPage = $(this).data("topPage");
        	var count = $(this).data("count");
        	if(topPage==0){
        		return false;
        	}
        	personLiableDetailList(topPage-1,choiceId,param,userType,name,telNum);
        });
	};
    /**
     * 预警责任人信息
     */
	var personLiableHtml =function(personLiable,detail){
        var html="";
 			html += "<div class='effect-list' id='personLiable'>";
 			html += "<ul >";
     			$.each(personLiable.list,function(i,item){
     					html +="<li class='pub' data-area="+item.areaId+">";
     					html +="    <span class='title'>"+item.areaName+"<i class='font-yellow'>"+item.num+"</i>人</span>";
     					html +="</li >" ;
 					});
            html += "</ul>";
            html += "</div>";
        $("#personLiable span i").text(JSON.stringify(personLiable.count));
        $(".warn-detail > .yjscro > .xqct ").eq(3).empty().prepend(html);
        
        //点击责任人事件
        $("#personLiable ul li").bind("click",function(){
        	//清空div
        	$(".audience-detail").hide();
        	$(".audience-detail .content").empty();
        	//选择的地区
        	var choiceId = $(this).data("area");
        	//责任人详细列表
        	personLiableDetailList(0,choiceId,detail,1,null,null);
        });
    };
    
    /**
     * 预警受众信息
     */
    var audiencesHtml =function(audiences,detail){
        if(JSON.stringify(audiences.count)!=0){
        	$("#countAudience").show();
        	$("#audiencediv").show();
        	var html="";
 			html += "<div class='effect-list' id='audience'>";
 			html += "<ul>";
 			$.each(audiences.list,function(i,item){
 					html +="<li class='pub' data-area="+item.areaId+">";
 					html +="    <span class='title'>"+item.areaName+"<i class='font-yellow'>"+item.num+"</i>人</span>";
 					html +="</li>" ;
				});
            html += "</ul>";
            html += "</div>";
	        $("#countAudience span i").text(JSON.stringify(audiences.count));
	        $(".warn-detail > .yjscro > .xqct ").eq(4).empty().prepend(html);
        }else{
        	 $("#countAudience").hide();
        	 $("#audiencediv").hide();
        }
        //点击受众事件
        $("#audience ul li").bind("click",function(){
        	//清空div
        	$(".personLiable-detail").hide();
        	$(".personLiable-detail .content").empty();
        	//选择的地区
        	var choiceId = $(this).data("area");
        	//受众详细列表
        	personLiableDetailList(0,choiceId,detail,2,null,null);
        });
    };
    // 清空查询菜单
    $("#clear").bind("click", function(){
    	$("#startTime").val("");  	// 清空开始时间
    	$("#endTime").val("");		// 清空结束时间
    	$("#earnName").val("");		// 清空栽种名称
    	$("#earnId").val("");		// 清空栽种id
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
    
    // 查询预警信息
    $("#search").bind("click", function(){
    	// initWarning(0);											// 左侧预警信息（显示）
//    	$(".pop-warn-icon").click();						// 左侧预警信息（显示）事件
//    	$(".pop-warn dl:nth-child(1)").click();				// 左侧预警列表(点击)事件
//    	$(".setting-list-title > span:last-child").click(); // 工具箱关闭
//    	loadWarningData(param(),1);								// 加载预警图标到地图上
//    	$(".accurate-fb .flbox .top .title-top .ttr").click();	// 精准发布隐藏
//    	$(".pop-resources-title > span:last-child ").click();//行业信息隐藏
    });

   
    // 初始化渠道信息
    var channel = function(){
      	$.ajax({
      		async:false,
      		type:"POST",
      		url:"map/channelList",
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
      		    
	    /**
	     * 点击渠道显示渠道数组并赋值给责任人、受众人数
	     */
		    var channelTree=function(channelIds){
	    	var dataTree=null;
	    	var orgId=$("#orgId").val();
	    	var disasterId=$("#disasterId").val();			//灾种ID
	    	var colorLevel=$("#colorLevel").val();			//灾种颜色
			var param={
					orgId:orgId,
					channelIds:channelIds,
					areaCode:Globel.areaCode,
					disasterId:disasterId,
					colorLevel:colorLevel
				};
			$.ajax({
	        	async:false,
	        	type:"POST",
	        	dataType:"json",
	        	data:param,
	        	url:"map/groupType",
	        	success:function(data){
	        		dataTree=data;
	        	}	
	        })
	    	var data= dataTree.respons;
			var data1= dataTree.audience;
			var responsNum= dataTree.responsNum;
			var audienceNum= dataTree.audienceNum;
			$(".responsNum").text(responsNum);
			$(".audienceNum").text(audienceNum);
	    	var treeCheck = function(e, treeId, treeNode,clickFlag){
				var userTypeId = "";
				var num = 0;
				var treeObj = $.fn.zTree.getZTreeObj("responsTree");
				var nodes=treeObj.getCheckedNodes(true);
				if(nodes.length>0){
					for(var i = 0 , len=nodes.length; i<len; i++){
						userTypeId += "," + nodes[i].id;
					var name=nodes[i].name;
					var userTypeName = name.substring(name.indexOf("(")+1,name.indexOf(")"));
						num =num+Number(userTypeName);
					}
				}
				$(".responsNum").text(num);
				return userTypeId != "" ? userTypeId = userTypeId.substring(1) : userTypeId ;
			};
		  	var treeCheckAudience = function(e, treeId, treeNode,clickFlag){
				var userTypeId = "";
				var num = 0;
				var treeObj = $.fn.zTree.getZTreeObj("audienceTree");
				var nodes=treeObj.getCheckedNodes(true);
				if(nodes.length>0){
					for(var i = 0 , len=nodes.length; i<len; i++){
						userTypeId += "," + nodes[i].id;
					var name=nodes[i].name;
					var userTypeName = name.substring(name.indexOf("(")+1,name.indexOf(")"));
						num =num+Number(userTypeName);
					}
				}
				$(".audienceNum").text(num);
				return userTypeId != "" ? userTypeId = userTypeId.substring(1) : userTypeId ;
			};
	        var setting = {
				check: {
					enable: true,
	                chkStyle: "checkbox",
	                radioType: "level",
	                chkboxType: { "Y": "", "N": "" }
				},
				view: {
					dblClickExpand: false
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {onCheck: treeCheck,onClick: treeCheck}
			},channelTree;
	        channelTree=$.fn.zTree.init($("#responsTree"), setting,data);
		    var treeObj = $.fn.zTree.getZTreeObj("responsTree");
	        treeObj.checkAllNodes(true);
	        
	        var setting1 = {
				check: {
					enable: true,
	                chkStyle: "checkbox",
	                radioType: "level",
	                chkboxType: { "Y": "", "N": "" }
				},
				view: {
					dblClickExpand: false
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {onCheck: treeCheckAudience,onClick: treeCheckAudience}
			},channelTree;
			channelTree=$.fn.zTree.init($("#audienceTree"), setting1,data1);
		    var treeObj = $.fn.zTree.getZTreeObj("audienceTree");
	        treeObj.checkAllNodes(true);
	    };
	      		    
	    // 精准发布渠道选择(全选)
	    $(".chanelSelect .all-channel").bind("click", function () {
	    	var promptBox=box();
	    	var disasterId=$("#disasterId").val();			//灾种ID
	    	var colorLevel=$("#colorLevel").val();			//灾种颜色
	    	var areaCode=Globel.areaCode;					//圈选地区
		    var continueTime=$("#continueTime").val();		//持续时间
		    var timingPublishTime=$("#timingPublishTime").val();		//指定时间
		    var publishTime=$("#publishTime").val();		//发布时间
		    if($(".publishMode .fb-form-input .selectbox ul li").hasClass("on")){
			    if(timingPublishTime==""){
				    promptBox.content="请选择指定时间!";
				    Move.pop(promptBox);
				    return false;
			    }
		    };
	    	if(disasterId=="" ||disasterId==null){
	    		promptBox.content="请选择灾种名称!";
	    		Move.pop(promptBox);
	    		return false;
	    	}else if(colorLevel=="" ||colorLevel==null){
	    		promptBox.content="请选择灾种颜色!";
	    		Move.pop(promptBox);
	    		return false;
		    }else if(continueTime=="" ||continueTime==null){
			    promptBox.content="请选择持续时间!";
			    Move.pop(promptBox);
			    return false;
		    }else if(publishTime=="" ||publishTime==null){
			    promptBox.content="请选择发布时间!";
			    Move.pop(promptBox);
			    return false;
		    }else if(areaCode=="" ||areaCode==null){
		    	promptBox.content="请框选地区!";
			    Move.pop(promptBox);
	    		return false;
	    	};
			$(".personTree").show();
	    	$(".pop-warnPublish-channel > dl").children("i").remove();
	    	var channelNames="";
			$(".pop-warnPublish-channel dl").each(function(){
				var channelId=$(this).data("channel").keyId;//当前选中的渠道ID
				var channelName=$(this).data("channel").name;//当前选中的渠道名称
				var orgId=$("#orgId").val();//当前登录人机构ID
				var param={"channelId":channelId,"orgId":orgId,"areaCode":areaCode,"disasterId":disasterId,"colorLevel":colorLevel}//参数
				var userData="";
				$.ajax({
					async:false,
					type:"post",
					data:param,
					dataType:"json",
					url:"map/channelByUser",
					success:function(data){
						userData=data;
						if(data.length<=0){
							channelNames +=","+channelName;
						}
					}	
				})
				if(userData.length>0){
				  	$(this).append(initParam.checked);
				}
			});
			//所有没有用户的渠道。
			channelNames=channelNames.substring(1);
	    	var channelIds="";
				$(".pop-warnPublish-channel dl i").each(function(){
					channelIds +=","+$(this).parent().data("channel").keyId;
				});
				channelIds=channelIds.substring(1);
				$("#channelId").val(channelIds);
			channelTree(channelIds);
			return false;
	    });
	    
	    // 精准发布渠道选择(反选)
	    $(".chanelSelect .un-all-channel").bind("click", function () {
	    	$(".pop-warnPublish-channel > dl").children("i").remove();
	    	 var channelIds="";
				$(".pop-warnPublish-channel dl i").each(function(){
					channelIds +=","+$(this).parent().data("channel").keyId;
				});
				channelIds=channelIds.substring(1);
				$("#channelId").val("");
			channelTree(channelIds);
	        return false;
	    });  
		//精准发布渠道点击事件
		$(".pop-warnPublish-channel dl").on("click",function(){
			var areaCode=Globel.areaCode;					//圈选地区
			var promptBox=box();							//温馨提示框
			var disasterId=$("#disasterId").val();			//灾种ID
			var colorLevel=$("#colorLevel").val();			//灾种颜色
		    var continueTime=$("#continueTime").val();		//持续时间
		    var timingPublishTime=$("#timingPublishTime").val();		//指定时间
		    var publishTime=$("#publishTime").val();		//发布时间
		    if($(".publishMode .fb-form-input .selectbox ul li").hasClass("on")){
			    if(timingPublishTime==""){
				    promptBox.content="请选择指定时间!";
				    Move.pop(promptBox);
				    return false;
			    }
		    };
			if(disasterId=="" ||disasterId==null){
				promptBox.content="请选择灾种名称!";
				Move.pop(promptBox);
				return false;
			}else if(colorLevel=="" ||colorLevel==null){
				promptBox.content="请选择灾种颜色!";
				Move.pop(promptBox);
				return false;
		    }else if(continueTime=="" ||continueTime==null){
			    promptBox.content="请选择持续时间!";
			    Move.pop(promptBox);
			    return false;
		    }else if(publishTime=="" ||publishTime==null){
			    promptBox.content="请选择发布时间!";
			    Move.pop(promptBox);
			    return false;
		    }else if(areaCode=="" ||areaCode==null){
		    	promptBox.content="请框选地区!";
			    Move.pop(promptBox);
				return false;
			};
			var channelId=$(this).data("channel").keyId;//当前选中的渠道ID
			var orgId=$("#orgId").val();//当前登录人机构ID
			var param={"channelId":channelId,"orgId":orgId,"areaCode":areaCode,"disasterId":disasterId,"colorLevel":colorLevel}//参数
			var userData="";
			$.ajax({
				async:false,
				type:"post",
				data:param,
				dataType:"json",
				url:"map/channelByUser",
				success:function(data){
					userData=data;
					if(data.length<=0){
					  	var promptBox=box();
					    promptBox.content="请添加用户";
					    Move.pop(promptBox);
						return false;
					}
				}	
			})	
			$(".personTree").show();
		    var channelIds="";
			var num = $(this).children("i").length;
				if(num==0){
					if(userData!=""){
						$(this).append(initParam.checked);
					}
		        }else{
		            $(this).children("i").remove();
		        }
			$(".pop-warnPublish-channel dl i").each(function(){
				channelIds +=","+$(this).parent().data("channel").keyId;
				});
				channelIds=channelIds.substring(1);
				$("#channelId").val(channelIds);
				channelTree(channelIds);
			    return false;
				});
      		}
      	});
    };	
    var locateExtent_geojson1 = function() {
		var channelAndWarn = map.graphicsLayerIds;
		if(channelAndWarn!=undefined){
			var delLayer = [];
			for(var i=0;i<channelAndWarn.length;i++){
				delLayer.push(channelAndWarn[i]);
			}
			for(var i = 0;i<delLayer.length;i++){
				var mark=new Mark();
				mark.remove_CodePolygon(map, delLayer[i]);
			}
		}
    	var data=$("#areaBorderData").val();
    	$.ajax({
    		type: "get", //使用get方法访问后台
    		dataType: "json", //返回json格式的数据
    		url: "/map/gis/data/geojson/"+data+".geojson",
    		success: function(data) {
    			loadDataByCitycode1(data);
    		}
    	});
    }
    
    /**
     * 根据账号行政区划编码加载边界数据
     */
    function loadDataByCitycode1(areadatas){
    	var areaCode=$("#areaCode").val().substring(0,6);
    	var obj = boundaryRegionDisplay(areaCode,areadatas.features);//430000，430301,430726
    	var features=obj.boundaryfeatures;
    	var level = obj.level;
    	var mark = new Mark();
    	$.each(features, function(i, feature) {
    		var properties=feature.properties;
    		if (feature.properties.CITYCODE.substring(2,6) === "0000" ) { //省边界样式
    			var coordinates = feature.geometry.coordinates[0];
    			var text = feature.properties.NAME;
    			mark.mark_polygonPlayer(this.map, "player", coordinates, "");
    			
    			if (level === "1000" && feature.properties.CITYCODE.substring(2,6) === "0000") {
    				
    				mark.mark_MaskpolygonByCitycode(this.map, "regionShiGraphicslayer", coordinates, "");//显示周边白色蒙层
//    				mark.mark_polygonByCitycode(this.map, "regionShiGraphicslayer", coordinates, "");
    			}
    		} 
    		if (feature.properties.CITYCODE.substring(2,6) !== "0000" && feature.properties.CITYCODE.substring(4,6) === "00") { //市边界样式
    			var coordinates = feature.geometry.coordinates[0];
    			var text = feature.properties.NAME; //县为NAME99；
    			mark.mark_polygonPlayer(this.map, "player", coordinates, "",properties);
//    			mark.mark_polygon(this.map, "regionShiGraphicslayer", coordinates, "");  //显示市、县边界线
    			if (level === "1001" && feature.properties.CITYCODE.substring(4,6) === "00") {
    				
    				mark.mark_MaskpolygonByCitycode(this.map, "regionShiGraphicslayer", coordinates, "");//显示周边白色蒙层 
//    				mark.mark_polygonByCitycode(this.map, "regionShiGraphicslayer", coordinates, "");
    			}
    		}
    		if (feature.properties.CITYCODE.substring(2,6) !== "0000" && feature.properties.CITYCODE.substring(4,6) !== "00") { //县边界样式
    			var coordinates = feature.geometry.coordinates[0];
    			var text = feature.properties.NAME; //县为NAME99；
    			mark.mark_polygonPlayer(this.map, "player", coordinates, text , properties);
//    			mark.mark_polygon2(this.map, "regionXianGraphicslayer", coordinates, text); //显示市、县边界线
    				if (level === "1002") {
    					
    				mark.mark_MaskpolygonByCitycode(this.map, "regionShiGraphicslayer", coordinates, "");//显示周边白色蒙层
//    				mark.mark_polygonByCitycode(this.map, "regionShiGraphicslayer", coordinates, "");
    			}
    		}
    	});
    }
	/**
	 * 导航栏模块图标显示
	 */
	var modularShow=function(){
		var html="";
	    	html += "<li title='资源展示' class='nav-item mr-1 nav-menu-7 model-1'><i id=''class='fa fa-industry' aria-hidden='true'></i></li>";
	    	html += "<li title='历史查询' class='nav-item mr-1 nav-menu-7 model-3'><i id=''class='fa fa-search' aria-hidden='true'></i></li>";
//	    	html += "<li title='雷达回波' class='nav-item mr-1 nav-menu-7 model-4'><i id=''class='fa fa-bullseye' aria-hidden='true'></i></li>";
//	    	html += "<li title='卫星云图' class='nav-item mr-1 nav-menu-7 model-5'><i id=''class='fa fa-cloud' aria-hidden='true'></i></li>";
//	    	html += "<li title='台风路径' class='nav-item mr-1 nav-menu-7 model-8'><i id=''class='fa fa-modx' aria-hidden='true'></i></li>";
	    	html += "<li title='雷达回波' class='nav-item mr-1 nav-menu-7 model-9'><i id=''class='fa fa-bullseye' aria-hidden='true'></i></li>";
	    	html += "<li title='卫星云图' class='nav-item mr-1 nav-menu-7 model-10'><i id=''class='fa fa-cloud' aria-hidden='true'></i></li>";
	    	html += "<li title='逃生路线' class='nav-item mr-1 nav-menu-7 model-7'><i id=''class='fa fa-paper-plane-o' aria-hidden='true'></i></li>";
//	    	html += "<li title='精准发布' class='nav-item mr-1 nav-menu-7 model-2'><i id=''class='fa fa-list' aria-hidden='true'></i></li>";
		$(".modular-show").empty().append(html);
		//模快显示
        $(".modular-show li").on("click",function(){
        	//资源信息
        	if($(this).hasClass("model-1")){
        		if(Globel.mapMgLayerFlag==true){
					locateExtent_geojson();
					Globel.mapMgLayerFlag=false;
				}
        		//打开关闭
        		$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
        		if($(this).hasClass("nav-menu-active")){
        			$(this).removeClass("nav-menu-active");
        			$(this).addClass("nav-menu-7");
        			$(".pop-resources-title > span:last-child").click();
        		}else{
        			//隐藏所有模快
        			hiddenAllDiv();
        			$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
        			initParam.terminal =terminalList();	// 加载左侧行业信息
        		    initIndustry();						// 初始化行业数据
        		    $(".pop-resourcesall").show();
        			$(".pop-resourcesall").animate({"left":"0"}, "slow");
        		}
        	//精准发布	
    		}else if($(this).hasClass("model-2")){
    			if(Globel.mapMgLayerFlag==true){
					locateExtent_geojson();
					Globel.mapMgLayerFlag=false;
				}
        		//打开关闭
    			$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
    			if($(this).hasClass("nav-menu-active")){
        			$(this).removeClass("nav-menu-active");
        			$(this).addClass("nav-menu-7");
        			$(".accurate-fb .flbox .top .title-top .ttr").click();
        		}else{
        			//隐藏所有模快
        			hiddenAllDiv();
        			$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
        			channel();							// 初始化渠道信息
        		    initDisasterName();					// 获取灾种名称（精准发布）
        		    initPublishAreaTree();				// 初始化发布地区信息树
//        		    sensitiveWord();					// 获取敏感字信息
        			//给精准发布登陆单位赋值
        			var name=$("#orgName").val();
        			$(".company .fb-form-input input").attr("value",name);
        			$(".accurate-fb").show();
        			$(".accurate-fb").animate({"left":"0"}, "slow");
        		}
    		//历史查询	
    		}else if($(this).hasClass("model-3")){
    			if(Globel.mapMgLayerFlag==true){
					locateExtent_geojson();
					Globel.mapMgLayerFlag=false;
				}
    			//打开关闭
    			$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
     			if($(this).hasClass("nav-menu-active")){
        			$(this).removeClass("nav-menu-active");
        			$(this).addClass("nav-menu-7");
        			$(".pop-search > .pop-search-content > ul > span:last-child").click();
        		}else{
        			//隐藏所有模快
        			hiddenAllDiv();
        			$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
        			initEarnName();						// 初始化灾种数据(查询条件)
        		    channel();							// 初始化渠道信息
        		    initAreaTree();						// 初始化地区信息树
        		    //显示查询条件信息
        		    $(".pop-search").show();
    	    		$(".pop-search").animate({left: 0}, "slow");
        		}
    		}else if($(this).hasClass("model-4")){
    			if(Globel.mapMgLayerFlag==true){
					locateExtent_geojson();
					Globel.mapMgLayerFlag=false;
				}
    			//隐藏所有模快
    			hiddenAllDiv();
    			$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
     			if($(this).hasClass("nav-menu-active")){
        			$(this).removeClass("nav-menu-active");
        			$(this).addClass("nav-menu-7");
        		}else{
        			$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
        			loadRadar();
        			$(".pop-right-bottom").show();
        		}
    		}else if($(this).hasClass("model-5")){
    			if(Globel.mapMgLayerFlag==true){
					locateExtent_geojson();
					Globel.mapMgLayerFlag=false;
				}
    			//隐藏所有模快
    			hiddenAllDiv();
        		//打开关闭
        		$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
      			if($(this).hasClass("nav-menu-active")){
        			$(this).removeClass("nav-menu-active");
        			$(this).addClass("nav-menu-7");
        		}else{
        			$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
        			loadCloud();
        			$(".pop-right-bottom").show();
        		}
    		}else if($(this).hasClass("model-7")){
    			if(Globel.mapMgLayerFlag==true){
					locateExtent_geojson();
					Globel.mapMgLayerFlag=false;
				}
    			//隐藏所有模快
    			hiddenAllDiv();
        		//打开关闭
      			$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
    			if($(this).hasClass("nav-menu-active")){
        			$(this).removeClass("nav-menu-active");
        			$(this).addClass("nav-menu-7");
        		}else{
        			$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
        			var promptBox=box();
        			promptBox.content="请选择地区灾害点";
          			Move.pop(promptBox);
        		}
    			if($(this).hasClass("nav-menu-active")){
    				crearNaviAuto();
  					removeNavi();
  					map.mapExtentChange=map.on('click', addDisasterEvent);
    			}else{
    				crearNaviAuto();
					if(window.shanshuo_delete){
						clearInterval(window.shanshuo_delete);	
					}
    			} 
    		}else if($(this).hasClass("model-8")){
    			if(Globel.mapMgLayerFlag==true){
					locateExtent_geojson();
					Globel.mapMgLayerFlag=false;
				}
    			//隐藏所有模快
    			hiddenAllDiv();
        		//打开关闭
    			$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
    			if($(this).hasClass("nav-menu-active")){
    				console.log(map);
        			$(this).removeClass("nav-menu-active");
        			$(this).addClass("nav-menu-7");
        			$(".typhoonNewList").slideUp(500);
        			$(".legend_box").hide();
/*            		reSetMapView(111.5332,27.3779, 7);*/
        	        typhoonModel="close";
        		}else{
        			$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
        			$(".typhoonNewList .content .query-criteria span ").click();
        			$(".typhoonNewList").slideDown(500);
        		}
    		}else if($(this).hasClass("model-9")){
    			//隐藏所有模快
    			hiddenAllDiv();
        		//打开关闭
    			$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
    			if($(this).hasClass("nav-menu-active")){
    				if(Globel.mapMgLayerFlag==true){
    					locateExtent_geojson();
    					Globel.mapMgLayerFlag=false;
    				}
        			$(this).removeClass("nav-menu-active");
        			$(this).addClass("nav-menu-7");
        			$(".cloudList").slideUp(500);
        		}else{
        			if(Globel.mapMgLayerFlag==false){
    					locateExtent_geojson1();
    					Globel.mapMgLayerFlag=true;
    				}
        			$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
        			$(".radarList .content .query-criteria ul li span ").click();
        			$(".radarList").slideDown(500);
        		}
    		}else if($(this).hasClass("model-10")){
    			//隐藏所有模快
    			hiddenAllDiv();
        		//打开关闭
    			$(this).siblings().removeClass("nav-menu-active");
    			$(this).siblings().addClass("nav-menu-7");
    			if($(this).hasClass("nav-menu-active")){
    				if(Globel.mapMgLayerFlag==true){
    					locateExtent_geojson();
    					
    				}
        			$(this).removeClass("nav-menu-active");
        			$(this).addClass("nav-menu-7");
        			$(".cloudList").slideUp(500);
        		}else{
        			if(Globel.mapMgLayerFlag==false){
    					locateExtent_geojson1();
    					Globel.mapMgLayerFlag=true;
    				}
        			$(this).removeClass("nav-menu-7");
        			$(this).addClass("nav-menu-active");
        			$(".cloudList .content .query-criteria ul li span ").click();
        			$(".cloudList").slideDown(500);
        		}
    		}
        })
	}
	
    // 地图轮播日期设置
    laydate.render({
        theme: "molv",
        elem:"#playerTime",
        eventElem: "#player-time",
        trigger: "click",
        type: "datetime",
        format:"yyyy-MM-dd HH",
        //获取时间改变日期值
        done: function(value, date, endDate){
        	initParam.selectTime=value;
        	timesRun = 0;
	        if(value.substring(11,13)=="00" || value.substring(11,13)==""){
	        	$(".pop-right-bottom").children("ul:nth-child(2)").children("li").removeClass("active");
	        	$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(0).addClass("active");	
	        	for(var i=0,len=12;i<len;i++){
	        		if(i<10){
	        			$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(i).html("0"+i+"<span></span>");
	        		}else{
	        			$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(i).html(i+"<span></span>");
	        		}
      			}
	        }else{
	        	timesRun = 0;
	        	$(".pop-right-bottom").children("ul:nth-child(2)").children("li").removeClass("active");
	        	$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(0).addClass("active");	
        		for(var i=0,len=12;i<len;i++){
	        		if(i<2){
	        			$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(i).html("0"+5*i+"<span></span>");
	        		}else{
	        			$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(i).html(5*i+"<span></span>");
	        		}
        		}
	        }
    	 }
    });
    // 地图轮播日期 向前按钮点击事件
	$(".fa-caret-left").click(function(){
		if(initParam.selectTime.substring(11,13)=="00" || initParam.selectTime.substring(11,13)==""){
	    	for(var i=0,len=12;i<len;i++){
	    		if(i<10){
	    			$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(i).html("0"+i+"<span></span>");
	    		}else{
	    			$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(i).html(i+"<span></span>");
	    		}
			}
	    	$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(0).addClass("active");	
		}
	});
	// 地图轮播日期 向后按钮点击事件
	$(".fa-caret-right").click(function(){
		if(initParam.selectTime.substring(11,13)=="00" || initParam.selectTime.substring(11,13)==""){
    	for(var i=0,len=12;i<len;i++){
    		$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(i).html(i+len+"<span></span>");
			}
    	$(".pop-right-bottom").children("ul:nth-child(2)").children("li").removeClass("active");
		}
	});
	// 地图轮播日期  播放按钮点击事件
	var interval="";
	var timesRun = 0;
	$("#play").click(function(){
		// 判断是否暂停
		var bool = $(this).hasClass("fa fa-pause-circle-o"); 
		if(!bool){
			// 修改暂停图标
			$(".pop-right-bottom").children("ul:nth-child(3)").children("i").removeClass("fa fa-play-circle-o");
			$(".pop-right-bottom").children("ul:nth-child(3)").children("i").addClass("fa fa-pause-circle-o");
			// 触发时间控件
			interval = setInterval(function(){
				timesRun += 1;
				// 判断选择的是天
				if(initParam.selectTime.substring(11,13)=="00" || initParam.selectTime.substring(11,13)==""){
					$(".pop-right-bottom").children("ul:nth-child(2)").children("li").removeClass("active");	
					if(timesRun <=11){
						$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(timesRun).addClass("active");
						}
					if(timesRun === 12){
				    	for(var i=0,len=12;i<len;i++){
					    		$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(i).html(i+len+"<span></span>");
					    		$(".pop-right-bottom").children("ul:nth-child(2)").children("li").removeClass("active");
							}
						}
					if(timesRun >11){
						$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(timesRun-12).addClass("active");
						}
					// 判断时间等于1天
					if(timesRun > 24){
						clearInterval(interval);
						timesRun = 0;
						$(".pop-right-bottom").children("ul:nth-child(3)").children("i").removeClass("fa fa-pause-circle-o");
						$(".pop-right-bottom").children("ul:nth-child(3)").children("i").addClass("fa fa-play-circle-o");
						for(var i=0,len=12;i<len;i++){
				    		if(i<10){
				    			$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(i).html("0"+i+"<span></span>");
				    		}else{
				    			$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(i).html(i+"<span></span>");
				    		}
						}
						$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(0).addClass("active");
						}
				}else{
					// 判断选的是时间信息
					if( timesRun %5 == 0){
						$(".pop-right-bottom").children("ul:nth-child(2)").children("li").removeClass("active");
						$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(timesRun/5).addClass("active");
						if(timesRun >=60){
						for(var i=1,len=24;i<len;i++){
							if( timesRun >=60*i && timesRun %5 == 0){
								$(".pop-right-bottom").children("ul:nth-child(2)").children("li").removeClass("active");
								$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq((timesRun-60*i)/5).addClass("active");
								}
							}
						}
					}
					// 判断时间等于1小时
				   	if(timesRun > 24*60){
				   		clearInterval(interval);
				   		timesRun = 0;
				   		$(".pop-right-bottom").children("ul:nth-child(3)").children("i").removeClass("fa fa-pause-circle-o");
				   		$(".pop-right-bottom").children("ul:nth-child(3)").children("i").addClass("fa fa-play-circle-o");
						for(var i=0,len=12;i<len;i++){
			        		if(i<2){
			        			$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(i).html("0"+5*i+"<span></span>");
			        		}else{
			        			$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(i).html(5*i+"<span></span>");
			        		}
		        		}
						$(".pop-right-bottom").children("ul:nth-child(2)").children("li").eq(0).addClass("active");
					}
				}
			}, 1000);
		}else{
			// 清除定时器
			clearInterval(interval);
			$(".pop-right-bottom").children("ul:nth-child(3)").children("i").removeClass("fa fa-pause-circle-o");
			$(".pop-right-bottom").children("ul:nth-child(3)").children("i").addClass("fa fa-play-circle-o");
		}
	});
	
	modularShow();						// 模块显示
//    judgeProvince();					// 判断省份名称
/*    initParam.terminal =terminalList();	// 加载左侧行业信息
    // initIndustry();						// 初始化行业数据
    // initDisasterName();					// 获取灾种名称（精准发布）
    // initPublishAreaTree();				// 初始化发布地区信息树
    // sensitiveWord();					// 获取敏感字信息
    // initEarnName();						// 初始化灾种数据(查询条件)
    // channel();							// 初始化渠道信息
    // initAreaTree();						// 初始化地区信息树
    // initWarning(0);						// 加载预警信息
*/    
    
/*    setTimeout(function(){
    	loadWarningData(param(),1); 	// 1:表示首页，2:预警信息，3:事件信息统计	
    },1000)*/
});