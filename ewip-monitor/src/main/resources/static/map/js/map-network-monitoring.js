
$(function(){
	//严格模式
	"use strict";
	/**
	 * 所有统计图Option函数
	 * @author lxv
	 * @return option
	 */
	var dlNum=0	 // dl的长度
	,playNum = 1 //预警终端定时器定时器标记
	,netNum =0	 //虚拟化资源定时器标记
	//预警统计信息总div内容
	,networkMonitoring = $("<div class='map-network-monitoring net-monitoring'></div>")
    /**
	 * 左上侧系统运行监控内容
	 * @param NULL
	 * @property systemMonitoring
	 * @author lxv
	 * @return 对象
	 */	
	,[systemRunMonitorTitle,systemRunMonitor] = function(){
		return [
		   $("<div class='top-left-icon mo-title-1' data-direction='title-left-top'>系统运行监控</div>")
		  ,$("<div class='modle mo-1'>" +
				  "<div class='mdxd'>" +
		           	"<div class='leftfirst mdxd'>" +
			           	"<div class='mdxd' id='yxqk'>" +
			           		"<div class='modletop'><span class='title' data-direction='left-top'>系统运行监控</span></div>" +
			           		"<div class='modlemiddle'>" +
			           			"<div class='modlect'>" +
			           				"<div class='pdd-2'>" +
			           					"<div class='runsever'>" +
			           						"<div class='runsever-top'>正常运行系统<span class='lvse'>0</span>个异常运行系统<span class='fense'>0</span>个" +
			                            "</div>" +
			                            "<div class='runsever-list'>" +
			                                "<ul>" +
			                                    "<li class='on'>" +
			                                        "<span class='leftbox'>" +
			                                        "<img src='/map/images/netWork/images/dx.png' >" +
			                                        "</span >" +
			                                        "<span class='rightbox'>短信</span>" +
			                                    "</li>" +
			                                    "<li  class='on'>" +
			                                          "<span>" +
			                                            "<i class='leftbox'>" +
			                                               "<span>" +
			                                                    "<img src='/map/images/netWork/images/bd.png' >" +
			                                               "</span>" +
			                                            "</i>" +
			                                            "<i class='rightbox'>北斗卫星</i>" +
			                                        "</span>" +
			                                    "</li>" +
			                                "</ul>" +
			                            "</div>" +
			                        "</div>" +
			                    "</div>" +
			                "</div>" +
			            "</div>" +
			            "<div class='modlefoot'></div>" +
			        "</div>" +
			    "</div>" +
		    "</div>" +
		"</div>")];
	}()

	/**
	 * 各市运行情况监控内容
	 * @param NULL
	 * @property provinceRunMonitor
	 * @author lxv
	 * @return 对象
	 */	
	,[provinceRunMonitorTitle, provinceRunMonitor] = function(){
		return [
		   $("<div class='top-right-icon mo-title-2' data-direction='title-right-top'>服务运行情况监控</div>")
		  ,$("<div class='modle mo-2'>" +
			 	"<div class='rightfirst mdxd'>" +
			 		"<div class='mdxd'>" +
				 		"<div class='modletop'><span class='title' data-direction='right-top'>服务运行情况监控</span></div>" +
				 		"<div class='modlemiddle'>" +
			 				"<div class='modlect'>" +
			 					"<div class='pdd-2'>" +
			 						"<div class='sever-control serverisuse'>" +
			 							"<div class='sever-r'>" +
			 								"<div class='topbox'>正在运行服务监控系统<span class='huang'>0</span>个</div>" +
			 									"<div class='bottombox'>" +
			 										"<div class='severlist'>" +
			 											"<ul>" +
			 												/*"<li>" +
			 													"<div class='leftbox'></div>" +
			 													"<div class='righttext'>" +
			 														"<p class='title'><span>中间件</span></p>" +
			 														"<p><span title='正常：5个 异常：8个'>正常：<span class='huang'>5</span>个 异常：<span class='hong'>8</span>个</span></p>" +
			 													"</div>" +
			 												"</li>" +*/
					                                    "</ul>" +
					                                "</div>" +
					                            "</div>" +
					                        "</div>" +
					                    "</div>" +
					                "</div>" +
					            "</div>" +
					        "</div>" +
					        "<div class='modlefoot'></div>" +
					    "</div>" +
					"</div>" +
				"</div>" +
		  	"</div>")];
	}()
    /**
	 * 左下侧预警终端内容
	 * @param NULL
	 * @property warnTerminal
	 * @author lxv
	 * @return 对象
	 */	
	,[warnTerminalTitle, warnTerminal] = function(){
		return [
		 $("<div class='left-icon mo-title-3' data-direction='title-left-buttom'>预警渠道监控</div>")
		,$("<div class='modle mo-3'>" +
			    "<div class='mdxd'>" +
		         "<div class='leftfirst2 mdxd'>" +
		         	"<div class='modletop'>" +
		                "<span class='title'  data-direction='left-buttom'>预警渠道监控</span>" +
		            "</div>" +
		            "<div class='modlemiddle'>" +
		                "<div class='modlect'>" +
		                    "<div class='yjzdctt zdlist'>" +
		                        "<div class='hd movespot'>" +
		                            "<ul><li class='on'>1</li><li>2</li><li>3</li><li>4</li></ul>" +
		                        "</div>" +
		                        "<div class='bd zditem'>" +
		                            "<div class='tempWrap ul_wrap'>" +
		                            "<ul>" +
		                                "<li>" +
		                                    "<div class='dlList'>" +
		                                        "<dl>" +
		                                            "<div class='netWork-tempWrap'>" +
		                                                "<div class='zdbox'>" +
		                                                    "<div class='topbox'>" +
		                                                        "<span class='ico zdico-note'>" +"</span>" +
		                                                        "<span class='text'>短信</span>" +
		                                                    "</div>" +
		                                                    "<div class='middlebox'>" +
		                                                        "<div id='Note' class='zdabsolute' _echarts_instance_='ec_1516258119870' style='-webkit-tap-highlight-color: transparent; user-select: none; background: transparent;'>" +
		                                                        	"<div style='position: relative; overflow: hidden; width: 120px; height: 120px; padding: 0px; margin: 0px; border-width: 0px;'></div>" +
		                                                        "</div>" +
		                                                    "</div>" +
		                                                    "<div class='bottombox'>" +
		                                                        "<div class='zt'>" +
		                                                            "<div>" +
		                                                                "<span class='colorbox bluebg'></span>" +
		                                                                "<span class='zttext'>正常</span>" +
		                                                                "<span id='zd-dxzc' class='ztbl dls'>30</span>" +
		                                                            "</div>" +
		                                                            "<div>" +
		                                                                "<span class='colorbox yellobg'></span>" +
		                                                                "<span class='zttext'>异常</span>" +
		                                                                "<span id='zd-dxyc' class='ztbl coloryellow'>50</span>" +
		                                                            "</div>" +
		                                                        "</div>" +
		                                                    "</div>" +
		                                                "</div>" +
		                                            "</div>" +
		                                        "</dl>" +
		                                        "<dl>" +
		                                            "<div class='netWork-tempWrap'>" +
		                                                "<div class='zdbox'>" +
		                                                    "<div class='topbox'>" +
		                                                        "<span class='ico zdico-tv'></span>" +
		                                                        "<span class='text'>电视</span>" +
		                                                    "</div>" +
		                                                    "<div class='middlebox'>" +
		                                                        "<div id='Tv' class='zdabsolute' _echarts_instance_='ec_1516258119871' style='-webkit-tap-highlight-color: transparent; user-select: none; background: transparent;'>" +
		                                                        "<div style='position: relative; overflow: hidden; width: 120px; height: 120px; padding: 0px; margin: 0px; border-width: 0px;'>" +
		                                                        "</div></div>" +
		                                                    "</div>" +
		                                                    "<div class='bottombox'>" +
		                                                        "<div class='zt'>" +
		                                                            "<div>" +
		                                                                "<span class='colorbox bluebg'></span>" +
		                                                                "<span class='zttext'>正常</span>" +
		                                                                "<span id='zd-tvzc' class='ztbl dls'>70</span>" +
		                                                            "</div>" +
		                                                            "<div>" +
		                                                                "<span class='colorbox yellobg'></span>" +
		                                                                "<span class='zttext'>异常</span>" +
		                                                                "<span id='zd-tvyc' class='ztbl coloryellow'>100</span>" +
		                                                            "</div>" +
		                                                        "</div>" +
		                                                    "</div>" +
		                                                "</div>" +
		                                            "</div>" +
		                                        "</dl>" +
		                                    "</div>" +
		                                "</li>" +
		                            "</ul></div>" +
		                        "</div>" +
		                    "</div>" +
		                "</div>" +
		            "</div>" +
		            "<div class='modlefoot'></div>" +
		        "</div>" +
		    "</div>" +
		"</div>")];
	}()
    /**
	 * 右下侧各市异常情况统计
	 * @param NULL
	 * @property provinceAbnormal
	 * @author lxv
	 * @return 对象
	 */	
	,[provinceAbNormalTitle, provinceAbNormal] = function(){
		return [
		    $("<div class='right-icon mo-title-4' data-direction='title-right-buttom'>服务运行监控</div>")
		    ,$("<div class='modle mo-4'>" +
				    "<div class='mdxd'>" +
				        "<div class='rightfirst2 mdxd'>" +
					        "<div class='mdxd'>" +
					            "<div class='modletop'><span class='title'  data-direction='right-buttom'>服务异常情况统计</span></div>" +
					            "<div class='modlemiddle'>" +
					                "<div class='modlect'>" +
					                    "<div class='pdd-2'>" +
					                        "<div class='sever-control serverUse'>" +
					                            "<div class='sever-r'>" +
					                                "<div id='jkcharts' class='bottombox'>" +
					                                    "<div id='weekchart' class='jkchart' _echarts_instance_='ec_1516258119882' style='-webkit-tap-highlight-color: transparent; user-select: none; position: relative; background: transparent;'><div style='position: relative; overflow: hidden; width: 328px; height: 232px; padding: 0px; margin: 0px; border-width: 0px;'><canvas width='410' height='290' data-zr-dom-id='zr_0' style='position: absolute; left: 0px; top: 0px; width: 328px; height: 232px; user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); padding: 0px; margin: 0px; border-width: 0px;'></canvas></div><div></div></div>" +
//					                                    "<div id='monthcharts' class='jkchart none' _echarts_instance_='ec_1516258119883' style='-webkit-tap-highlight-color: transparent; user-select: none; position: relative; background: transparent;'><div style='position: relative; overflow: hidden; width: 100px; height: 94px; padding: 0px; margin: 0px; border-width: 0px;'><canvas width='125' height='117' data-zr-dom-id='zr_0' style='position: absolute; left: 0px; top: 0px; width: 100px; height: 94px; user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); padding: 0px; margin: 0px; border-width: 0px;'></canvas></div><div></div></div>" +
					                                "</div>" +
					                            "</div>" +
					                        "</div>" +
					                    "</div>" +
					                "</div>" +
					            "</div>" +
					            "<div class='modlefoot'></div>" +
					        "</div>" +
					    "</div>" +
				    "</div>" +
				"</div>" +
		    "</div>")];
	}()
	
    /**
	 * 中间内容
	 * @param NULL
	 * @property centerMap
	 * @author lxv
	 * @return 对象
	 */	
	,centerMap = function(){
		return $("<div class='netBox' id='ifrmmid'>" +
					"<div class='netleft' style='width:100%;margin-right:0;background:transparent;'>" +
//        				"<div class='mptitle'>各市运行情况监控</div>" +
						// 动态地球背景
		    			"<div class='add_demo'>" +
		    				"<div style='width: 250px;height: 250px;overflow: hidden;position: relative;border-radius: 50%;position: absolute;left: 248px;z-index: 12;top: 75px;'>" +
		    					"<div class='are_wrap'>" +
		    						"<img class='are_' src='/map/images/netWork/images/images/are01.jpg' >" +
		    						"<img class='are_1' src='/map/images/netWork/images/images/are01.jpg' >" +
		    					"</div>" +
		    				"</div>" +
		    				"<div class='earth_sm' style=''>" +
		    					"<div class='are_wrap2'>" +
		    						"<img class='are_2' src='/map/images/netWork/images/images/are03.jpg' >" +
		    						"<img class='are_22' src='/map/images/netWork/images/images/are03.jpg' >" +
		    					"</div>" +
		    				"</div>" +
		    				"<div class='wrap_zt'>" +
		    					"<div class='are_group are_group01 bottom_02'>" +
		    						"<div class='ad_wrap1'>" +
		    							"<div class='once_eight once_eight1'></div><div class='once_eight once_eight2'></div>" +
		    							"<div class='once_eight once_eight3'></div><div class='once_eight once_eight4'></div>" +
		    						"</div>" +
		    						"<div class='ad_wrap2'>" +
		    							"<div class='once_eight once_eight1'></div><div class='once_eight once_eight2'></div>" +
		    							"<div class='once_eight once_eight3'></div><div class='once_eight once_eight4'></div>" +
		    						"</div>" +
		    					"</div> " +
		    					"<div class='are_group are_group02 bottom_03'>" +
		    						"<div class='ad_wrap1'>" +
		    							"<div class='once_eight once_eight1'></div><div class='once_eight once_eight2'></div>" +
		    							"<div class='once_eight once_eight3'></div><div class='once_eight once_eight4'></div>" +
		    						"</div>" +
		    						"<div class='ad_wrap2'>" +
		    							"<div class='once_eight once_eight1'></div><div class='once_eight once_eight2'></div>" +
		    							"<div class='once_eight once_eight3'></div><div class='once_eight once_eight4'></div>" +
		    						"</div> " +
		    					"</div>" +
		    					"<div class='text_wrap'>" +
		    						"<div class='txt_ txt_01'>系统运行监控</div><div class='txt_ txt_02'>发布终端监控</div>" +
		    						"<div class='txt_ txt_03'>服务运行监控</div><div class='txt_ txt_04'>服务异常监控</div>" +
		    						"<div class='txt_ txt_05'>政务外网监控</div><div class='txt_ txt_06'>政务外网异常监控</div>" +
		    						"<div class='txt_ txt_07'>服务资源使用情况监控</div><div class='txt_ txt_08'>服务使用情况监控</div>" +
		    					"</div>" +
		    					"<div class='text_wrap text_hover'>" +
		    						"<div class='txt_ txt_01'>系统运行监控</div><div class='txt_ txt_02'>发布终端监控</div>" +
		    						"<div class='txt_ txt_03'>服务运行监控</div><div class='txt_ txt_04'>服务异常监控</div>" +
		    						"<div class='txt_ txt_05'>政务外网监控</div><div class='txt_ txt_06'>政务外网异常监控</div>" +
		    						"<div class='txt_ txt_07'>服务资源使用情况监控</div><div class='txt_ txt_08'>服务使用情况监控</div>" +
		    					"</div>" +
		    					"<img class='bottom_01' src='/map/images/netWork/images/images/bottom_01.png' >" +
		    					"<img class='weixin_' src='/map/images/netWork/images/images/weixin_.png' >" +
		    					"<img class='dipan' src='/map/images/netWork/images/images/dipan.png' >" +
		    					"<div class='earth_warp'>" +
		    						"<img class='bottom_04' src='/map/images/netWork/images/images/bottom_04.png' >" +
		    						"<img class='btm_01' src='/map/images/netWork/images/images/btm_01.png' >" +
		    						"<img class='btm_02' src='/map/images/netWork/images/images/btm_02.png' >" +
		    						"<img class='btm_03' src='/map/images/netWork/images/images/btm_03.png' >" +
		    						"<img class='btm_01' src='/map/images/netWork/images/images/btm_01.png' >" +
		    						"<img class='btm_02' src='/map/images/netWork/images/images/btm_02.png' >" +
		    						"<img class='btm_03' src='/map/images/netWork/images/images/btm_03.png' >" +
		    						"<img class='deng_01' src='/map/images/netWork/images/images/deng_.png' >" +
		    						"<img class='deng_02' src='/map/images/netWork/images/images/deng_.png' >" +
		    						"<img class='deng_03' src='/map/images/netWork/images/images/deng_.png' >" +
		    						"<img class='deng_05' src='/map/images/netWork/images/images/deng_.png' >" +
		    						"<img class='deng_06' src='/map/images/netWork/images/images/deng_.png' >" +
		    						"<img class='deng_07' src='/map/images/netWork/images/images/deng_.png' >" +
		    						"<img class='deng_08' src='/map/images/netWork/images/images/deng_.png' >" +
		    						"<img class='da_mianqiu01' src='/map/images/netWork/images/images/da_mianqiu.png' >" +
		    						"<img class='da_mianqiu02' src='/map/images/netWork/images/images/da_mianqiu.png' >" +
		    						"<img class='da_mianqiu03' src='/map/images/netWork/images/images/da_mianqiu.png' >" +
		    						"<img class='da_mianqiu05' src='/map/images/netWork/images/images/da_mianqiu.png' >" +
		    						"<img class='da_mianqiu06' src='/map/images/netWork/images/images/da_mianqiu.png' >" +
		    						"<img class='da_mianqiu07' src='/map/images/netWork/images/images/da_mianqiu.png' >" +
		    						"<img class='da_mianqiu08' src='/map/images/netWork/images/images/da_mianqiu.png' >" +
		    						"<img class='neihuan_' src='/map/images/netWork/images/images/neihuan_.png' >" +
		    						"<img class='btm_are01' src='/map/images/netWork/images/images/btm_are01.png' >" +
		    						"<img class='are01_' src='/map/images/netWork/images/images/are01_.png' >" +
		    						"<img class='are02_' src='/map/images/netWork/images/images/are02_.png' >" +
		    						"<img class='are03_' src='/map/images/netWork/images/images/are03_.png' >" +
		    						"<img class='are04_' src='/map/images/netWork/images/images/are04_.png' >" +
		    						"<img class='are05_' src='/map/images/netWork/images/images/are05_.png' >" +
		    						"<img class='are06_' src='/map/images/netWork/images/images/are06_.png' >" +
		    						"<img class='are07_' src='/map/images/netWork/images/images/are07_.png' >" +
		    						//"<img class='dipan' src='/map/images/netWork/images/images/dipan.png' >" +
		    						"<img class='mianqiu_01' src='/map/images/netWork/images/images/mianqiu_.png' >" +
		    						"<img class='mianqiu_02' src='/map/images/netWork/images/images/mianqiu_.png' >" +
		    						"<img class='mianqiu_03' src='/map/images/netWork/images/images/mianqiu_.png' >" +
		    						"<img class='mianqiu_04' src='/map/images/netWork/images/images/mianqiu_.png' >" +
		    						"<img class='mianqiu_05' src='/map/images/netWork/images/images/mianqiu_.png' >" +
		    						"<img class='mianqiu_06' src='/map/images/netWork/images/images/mianqiu_.png' >" +
		    						"<img class='mianqiu_07' src='/map/images/netWork/images/images/mianqiu_.png' >" +
		    						"<img class='mianqiu_08' src='/map/images/netWork/images/images/mianqiu_.png' >" +
		    						"<img class='mianqiu_09' src='/map/images/netWork/images/images/mianqiu_.png' >" +
		    						"<img class='mianqiu_10' src='/map/images/netWork/images/images/mianqiu_.png' >" +
		    					"</div>" +
		    					"<div class='earth_warp earth_warp1'>" +
		    						"<img class='line_01' src='/map/images/netWork/images/images/line_01.png' >" +
		    						"<img class='line_02' src='/map/images/netWork/images/images/line_02.png' >" +
		    						"<img class='xinqiu_' src='/map/images/netWork/images/images/xinqiu_.png' >" +
		    					"</div>" +
		    				"</div>" +
		    			"</div>" +
		    		"</div>" +
				"</div>");
	}()
	  /**
	 * 中政务外网监控
	 * @param NULL
	 * @property governmentExtranet
	 * @author lxv
	 * @return 对象
	 */	
	,[governmentExtranetTitle,governmentExtranet] = function(){
		return [ 
         $("<div class='center-top-left-icon mo-title-5' >政务外网监控</div>")
		,$("<div class='centermodle mo-5'>" +
			"<div class='centermdxd'>" +
	         	"<div class='centerleftfirst mdxd'>" +
		         	"<div class='centermodletop'>" +
		                "<span class='title'  data-direction='left-buttom'>政务外网监控</span>" +
		            "</div>" +
		            "<div class='centermodlemiddle governmentExtranet'>" +
		            	"<div id='governmentExtranet' style='width:100%;height:100%;'></div>" +
		            	"<div class='recovery'><ul >还原</ul></div>" +
		            "</div>" +
		            "<div class='centermodlefoot'></div>" +
		        "</div>" +
	        "</div>" +
		"</div>")];
	}()
	 /**
	 * 北斗网络监控
	 * @param NULL
	 * @property beidouNetwork
	 * @author lxv
	 * @return 对象
	 */	
	,[beidouNetworkTitle,beidouNetwork] = function(){
		return [
        $("<div class='center-top-right-icon mo-title-6' >政务外网异常监控</div>")
		,$("<div class='centermodle mo-6'>" +
			"<div class='centermdxd'>" +
	         	"<div class='centerrightfirst mdxd'>" +
		         	"<div class='centermodletop'>" +
		                "<span class='title'  data-direction='left-buttom'>政务外网异常监控</span>" +
		            "</div>" +
		            "<div class='centermodlemiddle beidouNetwork' >" +
		            	"<ul></ul>" +
		            "</div>" +
		            "<div class='centermodlefoot'></div>" +
		        "</div>" +
	        "</div>" +
		"</div>")];
	}()
	/**
	 * 虚拟化网络监控
	 * @param NULL
	 * @property virtualization
	 * @author lxv
	 * @return 对象
	 */	
	,[virtualizationTitle,virtualization] = function(){
		return [
        $("<div class='center-bottom-left-icon mo-title-7' >服务器资源使用情况监控</div>")
		,$("<div class='centermodle mo-7'>" +
			"<div class='centermdxd'>" +
	         	"<div class='centerleftfirst2 mdxd'>" +
		         	"<div class='centermodletop'>" +
		                "<span class='title'  data-direction='left-buttom'>服务器资源使用情况监控</span>" +
		            "</div>" +
		            "<div class='centermodlemiddle virtualization'>" +
	        			"<div class='listTitle'>" +
	        				"<dl class='active' >" +
								"<dd >名称</dd>" +
								"<dd >CPU</dd>" +
								"<dd >RAM</dd>" +
								"<dd >硬盘</dd>" +
							"</dl>" +
						"</div>" +
						"<div class='listContent'>" +
						"</div>" +
		            "</div>" +
		            "<div class='centermodlefoot'></div>" +
		        "</div>" +
	        "</div>" +
		"</div>")];
	}()
	/**
	 * 通讯网络监控
	 * @param NULL
	 * @property communicationNetwork
	 * @author lxv
	 * @return 对象
	 */	
	,[communicationNetworkTitle,communicationNetwork] = function(){
		return [
        $("<div class='center-bottom-right-icon mo-title-8' >服务器使用情况监控</div>")
		,$("<div class='centermodle mo-8'>" +
			"<div class='centermdxd'>" +
	         	"<div class='centerrightfirst2 mdxd'>" +
		         	"<div class='centermodletop'>" +
		                "<span class='title'  data-direction='left-buttom'>服务器使用情况监控</span>" +
		            "</div>" +
		            "<div class='centermodlemiddle virtualizationRight'>" +
		            	"<div  id='CPU'  style='width:33%;height:100%;     float: left;'>" +
		            	"</div>" +
		            	"<div  id='RAM'  style='width:33%;height:100%;     float: left;'>" +
		            	"</div>" +
		            	"<div  id='DISK'  style='width:33%;height:100%;     float: left;'>" +
		            	"</div>" +
		            "</div>" +
		            "<div class='centermodlefoot'></div>" +
		        "</div>" +
	        "</div>" +
		"</div>")];
	}()
	/**
	 * 各市运行情况监控详细列表
	 */
	,[conditionMonitorDetail] = function(){
		return $("<div class='conditionMonitorDetail '>" +
					"<div class='setting-list-title'>" +
					"<span><i class='fa fa-list-alt'></i>详细列表</span>" +
					"<span><i class='fa fa-close' style='float: right;margin: 4px;'></i></span>" +
					"</div>" +
					"<div class='monitorDetailTree'>" +
						"<div id='monitorDetailTree' class='ztree '></div>" +
					"</div>" +
				"</div> ");
	}()
	/**
	 * 各市运行情况监控详细列表
	 */
	,[terminalMonitorDetail] = function(){
		return $("<div class='terminalMonitorDetail '>" +
					"<div class='setting-list-title'>" +
						"<span><i class='fa fa-list-alt'></i>详细列表</span>" +
						"<span><i class='fa fa-close' style='float: right;margin: 4px;'></i></span>" +
					"</div>" +
					"<div class='content'></div>" +
				"</div> ");
	}()
	
	//初始化预警信息拼接页面
	,init =function(...params){
		for(var i = 0; i<params.length; i++){
			//拼接预警信息HTML 
			$(networkMonitoring).append(params[i]);
		}
		//拼接Html
		$("body").children("script").eq(0).before($(networkMonitoring));
	};  
	
	//初始化加载所有模块
	init(
		conditionMonitorDetail		// 各市运行情况监控详细列表
		,terminalMonitorDetail		// 预警终端详细信息列表
		,systemRunMonitorTitle  	// 系统运行监控标题
		,systemRunMonitor			// 系统运行监控内容
		,warnTerminalTitle			// 预警终端监控标题
		,warnTerminal				// 预警终端监控内容
		,provinceRunMonitorTitle 	// 各市区运行情况监控标题
		,provinceRunMonitor			// 各市区运行情况监控内容
		,provinceAbNormalTitle		// 各市异常情况统计标题
		,provinceAbNormal			// 各市异常情况统计内容
		,centerMap 					// 中心地球
		,governmentExtranetTitle	// 政务外网监控标题
		,beidouNetworkTitle			// 北斗网络监控标题
		,virtualizationTitle		// 虚拟化网络监控标题
		,communicationNetworkTitle	// 通讯网络监控标题
		,governmentExtranet			// 政务外网监控内容
		,beidouNetwork				// 北斗网络监控内容
		,virtualization				// 虚拟化网络监控内容
		,communicationNetwork		// 通讯网络监控内容
	);
	//渠道反馈以及影响分析详情关闭
	$(".conditionMonitorDetail > .setting-list-title span:last-child").on("click", function(){
		$(".conditionMonitorDetail").hide();
	});
	
	//终端异常情况详细列表关闭
	$(".terminalMonitorDetail  > .setting-list-title span:last-child").on("click", function(){
		$(".terminalMonitorDetail ").hide();
	});
	//四大模块隐藏显示，点击内容弹出层上的title触发效果
	$(".leftfirst .modletop span, " +
	  ".leftfirst2 .modletop span, " +
	  ".rightfirst .modletop span, " +
	  ".rightfirst2 .modletop span, " +
	  ".mo-title-1, .mo-title-2, .mo-title-3, .mo-title-4").on("click",function(){
		// 获取弹出层方位
		var direction = $(this).data("direction");
		if(direction == "left-top"){
			$(".mo-1").animate({"left":"-22%"}, "slow");
			$(".mo-title-1").animate({"left":"0"}, "slow");
		}
		if(direction == "right-top"){
			$(".mo-2").animate({"right":"-22%"}, "slow");
			$(".mo-title-2").animate({"right":"0"}, "slow");
		}
		if(direction == "left-buttom"){
			$(".mo-3").animate({"left":"-22%"}, "slow");
			$(".mo-title-3").animate({"left":"0"}, "slow");
		}
		if(direction == "right-buttom"){
			$(".mo-4").animate({"right":"-22%"}, "slow");
			$(".mo-title-4").animate({"right":"0"}, "slow");
		}
		
		if(direction == "title-left-top"){
			$(this).animate({"left":"-2rem"}, "slow");
			$(".mo-1").animate({"left":"0"}, "slow");
		}
		if(direction == "title-right-top"){
			$(this).animate({"right":"-2rem"}, "slow");
			$(".mo-2").animate({"right":"0"}, "slow");
		}
		if(direction == "title-left-buttom"){
			$(this).animate({"left":"-2rem"}, "slow");
			$(".mo-3").animate({"left":"0"}, "slow");
		}
		if(direction == "title-right-buttom"){
			$(this).animate({"right":"-2rem"}, "slow");
			$(".mo-4").animate({"right":"0"}, "slow");
		}
	});
	
	//中间四大模块隐藏
	//政务外网监控隐藏
	$(".centerleftfirst .centermodletop").on("click",function(){
		$(".mo-5").animate({"bottom":-23.5+"%"}, "slow");
		$(".mo-5").addClass("close");
		if($(".mo-7").css('bottom')=="0px"){
			$(".center-top-left-icon").animate({"bottom":23.5+"%"}, "slow");
		}else{
			$(".center-top-left-icon").animate({"bottom":"0px"}, "slow");
		}
	});
	//虚拟化网络监控隐藏
	$(".centerleftfirst2 .centermodletop").on("click",function(){
		$(".mo-7").animate({"bottom":-23.5+"%"}, "slow");
		$(".center-bottom-left-icon").animate({"bottom":"0px"}, "slow");
		if($(".mo-5").hasClass("close")){
			$(".center-top-left-icon").animate({"bottom":"0px"}, "slow");
		}else{
			$(".center-top-left-icon").animate({"bottom":-23.5+"%"}, "slow");
		}
	});
	//北斗网络监控隐藏
	$(".centerrightfirst .centermodletop").on("click",function(){
		$(".mo-6").animate({"bottom":-23.5+"%"}, "slow");
		$(".mo-6").addClass("close");
		if($(".mo-8").css('bottom')=="0px"){
			$(".center-top-right-icon").animate({"bottom":23.5+"%"}, "slow");
		}else{
			$(".center-top-right-icon").animate({"bottom":"0px"}, "slow");
		}
	});
	//通讯网络监控隐藏
	$(".centerrightfirst2 .centermodletop").on("click",function(){
		$(".mo-8").animate({"bottom":-23.5+"%"}, "slow");
		$(".center-bottom-right-icon").animate({"bottom":"0px"}, "slow");
		if($(".mo-6").hasClass("close")){
			$(".center-top-right-icon").animate({"bottom":"0px"}, "slow");
		}else{
			$(".center-top-right-icon").animate({"bottom":-23.5+"%"}, "slow");
		}
	});
	
	//中间四大模块显示
	//政务外网监控显示
	$(".center-top-left-icon").on("click",function(){
		$(".mo-5").animate({"bottom":23.5+"%"}, "slow");
		$(".mo-5").removeClass("close");
		$(this).animate({"bottom":"-20px"}, "slow");
		if($(".mo-7").css('bottom')!="0px"){
			$(".mo-7").animate({"bottom":"0px"}, "slow");
			$(".center-bottom-left-icon").animate({"bottom":"-20px"}, "slow");
		}
	});
	//虚拟化网络监控显示
	$(".center-bottom-left-icon").on("click",function(){
		$(".mo-7").animate({"bottom":"0px"}, "slow");
		$(this).animate({"bottom":"-20px"}, "slow");
		if($(".mo-5").hasClass("close")){
			$(".center-top-left-icon").animate({"bottom":23.5+"%"}, "slow");
		}else{
			$(".center-top-left-icon").animate({"bottom":"-20px"}, "slow");
		}
	});
	

	//北斗网络监控显示
	$(".center-top-right-icon").on("click",function(){
		$(".mo-6").animate({"bottom":23.5+"%"}, "slow");
		$(".mo-6").removeClass("close");
		$(this).animate({"bottom":"-20px"}, "slow");
		if($(".mo-8").css('bottom')!="0px"){
			$(".mo-8").animate({"bottom":"0px"}, "slow");
			$(".center-bottom-right-icon").animate({"bottom":"-20px"}, "slow");
		}
	});
	//政务外网还原事件
	$(".governmentExtranet .recovery").on("click",function(){
		beidouNetwork("");
	});
	//通讯网络监控显示
	$(".center-bottom-right-icon").on("click",function(){
		$(".mo-8").animate({"bottom":"0px"}, "slow");
		$(this).animate({"bottom":"-20px"}, "slow");
		if($(".mo-6").hasClass("close")){
			$(".center-top-right-icon").animate({"bottom":23.5+"%"}, "slow");
		}else{
			$(".center-top-right-icon").animate({"bottom":"-20px"}, "slow");
		}
	});
	//中间各市运行监控左右移动事件
	$(".hdbutton.lft.outright,.hdbutton.rt").on("click",function(){
		// 获取将要移动ul里的li个数
    	var dlLen = $(".netBox .runninglist ul").children("li").length;
    	// 不够2个时左右点击切换失效（显示区域永远为2个）
    	if(dlLen< 2){
    		return false;
    	}
		var width = $(window).width(), left = width == 1366 ? 18.7 : 19;
		if($(this).hasClass("lft")){
			if(dlNum>0){
    			dlNum = dlNum-1;
    			$(".netBox .runninglist ul").animate({"left": -(left*dlNum) + "rem"}, "slow");
        	}
		}else if($(this).hasClass("rt")){
			if(dlNum<dlLen && (dlLen-dlNum-2)>0){
    			dlNum = dlNum+1;
    			$(".netBox .runninglist ul").animate({"left": -(left*dlNum) + "rem"}, "slow");
        	}
		}
	});

	/**
	 * 系统运行监控
	 */
	var systemMonitoring=function(){
	/*	var	data={list:[{SENDMETHOD: "短信(10628121手段)", RESULTTYPE: 1, DEPLOYTYPE: 1, CHANNELNAME: "短信", CHANNELID: "1"}
		,{SENDMETHOD: "短信(12379手段)", RESULTTYPE: 1, DEPLOYTYPE: 1, CHANNELNAME: "短信", CHANNELID: "1"}
		,{SENDMETHOD: "广播(广播)", RESULTTYPE: 1, DEPLOYTYPE: 1, CHANNELNAME: "广播", CHANNELID: "10"}
		,{SENDMETHOD: "网站(网站)", RESULTTYPE: 1, DEPLOYTYPE: 1, CHANNELNAME: "网站", CHANNELID: "11"}
		,{SENDMETHOD: "微博(新浪微博)", RESULTTYPE: 1, DEPLOYTYPE: 1, CHANNELNAME: "微博", CHANNELID: "12"}
		,{SENDMETHOD: "微信(微信)", RESULTTYPE: 1, DEPLOYTYPE: 1, CHANNELNAME: "微信", CHANNELID: "13"}
		,{SENDMETHOD: "手机APP(手机APP)", RESULTTYPE: 1, DEPLOYTYPE: 1, CHANNELNAME: "手机APP", CHANNELID: "14"}
		,{SENDMETHOD: "声讯(85600083手段)", RESULTTYPE: 1, DEPLOYTYPE: 1, CHANNELNAME: "声讯", CHANNELID: "2"}
		,{SENDMETHOD: "声讯(400手段)", RESULTTYPE: 1, DEPLOYTYPE: 1, CHANNELNAME: "声讯", CHANNELID: "2"}
		,{SENDMETHOD: "声讯(12379手段)", RESULTTYPE: 1, DEPLOYTYPE: 1, CHANNELNAME: "声讯", CHANNELID: "2"}
		,{SENDMETHOD: "声讯(12121手段)", RESULTTYPE: 1, DEPLOYTYPE: 1, CHANNELNAME: "声讯", CHANNELID: "2"}
		,{SENDMETHOD: "大喇叭(大喇叭)", RESULTTYPE: 1, DEPLOYTYPE: 1, CHANNELNAME: "大喇叭", CHANNELID: "3"}
		,{SENDMETHOD: "显示屏(显示屏)", RESULTTYPE: 1, DEPLOYTYPE: 1, CHANNELNAME: "显示屏", CHANNELID: "4"}
		,{SENDMETHOD: "北斗卫星(北斗卫星)", RESULTTYPE: 1, DEPLOYTYPE: 1, CHANNELNAME: "北斗卫星", CHANNELID: "5"}
		,{SENDMETHOD: "电视(气象频道)", RESULTTYPE: 1, DEPLOYTYPE: 1, CHANNELNAME: "电视", CHANNELID: "8"}
		,{SENDMETHOD: "电视(影视直播)", RESULTTYPE: 1, DEPLOYTYPE: 1, CHANNELNAME: "电视", CHANNELID: "8"}
		,{SENDMETHOD: "电视(省级电视台渠道)", RESULTTYPE: 1, DEPLOYTYPE: 1, CHANNELNAME: "电视", CHANNELID: "8"}]
		,abnormalNum:0,normalNum:17};
		var list=data.list;
		if(list.length>0){
			var html="";
			$(".runsever-top span:first-child").text(data.normalNum);
			$(".runsever-top span:last-child").text(data.abnormalNum);
			$.each(list,function(i,item){
				var dataobj=JSON.stringify(item);
				if(item.RESULTTYPE=="1"){
					html +="<li >";
				}else{
					html +="<li class='on'>";
				}
				 	html +="	<span>";
				 	html +="		<i class='leftbox'>";
				 	html +="			<span><img src='/map/images/channel/"+item.CHANNELID+".png'></span>";
				 	html +="		</i>";
				 	html +="		<span class='rightbox' title='"+item.SENDMETHOD+"'>"+item.SENDMETHOD.substring(0,15)+"</span>";
				 	html +="	</span>";warnTerminalMonitor
				 	html +="</li>";
			 	});
			$(".runsever-list ul").empty().append(html);
		}else{
			$(".runsever").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
		}*/
		$.ajax({
			async:false,
        	type:"POST",
         	url:"/systemMonitor/systemMonitoring",
			data:{"empAreaId": Globel.empAreaId},
         	success:function(data){
         		var list=data.list;
         		if(list.length>0){
	         		var html="";
	         		$(".runsever-top span:first-child").text(data.normalNum);
	         		$(".runsever-top span:last-child").text(data.abnormalNum);
	         		$.each(list,function(i,item){
	         			var dataobj=JSON.stringify(item);
	         			if(item.resultType=="1"){
	         				html +="<li >";
	         			}else{
	         				html +="<li class='on'>";
	         			}
		     			 	html +="	<span>";
		     			 	html +="		<i class='leftbox'>";
                        	html +="			<span><img src='"+item.img+"'></span>";
		     			 	html +="		</i>";
		     			 	html +="		<span class='rightbox' title='"+item.sendMethod+"'>"+item.sendMethod.substring(0,15)+"</span>";
		     			 	html +="	</span>";
		     			 	html +="</li>";
	         		 	});
	         		$(".runsever-list ul").empty().append(html);
         		}else{
         			$(".runsever").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
         		}
         	}
		})
	}
	/**
	 * 预警终端异常统计饼图
	 */
	,warnTerminalStatisc=function(){
	var orgId=$("#orgId").val();
	var data={list:[{totalNum: 0, channelId: "3", name: "大喇叭", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
		,{totalNum: 0, channelId: "10", name: "广播", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
 		,{totalNum: 0, channelId: "11", name: "网站", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
 		,{totalNum: 0, channelId: "26", name: "报警器", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
 		,{totalNum: 0, channelId: "7", name: "传真", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
 		,{totalNum: 0, channelId: "2", name: "声讯", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
 		,{totalNum: 0, channelId: "13", name: "微信", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
 		,{totalNum: 0, channelId: "12", name: "微博", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
 		,{totalNum: 0, channelId: "14", name: "手机APP", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
 		,{totalNum: 0, channelId: "4", name: "显示屏", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
 		,{totalNum: 0, channelId: "5", name: "北斗卫星", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
 		,{totalNum: 0, channelId: "8", name: "电视", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
 		,{totalNum: 1, channelId: "1", name: "短信", list: [{name: "异常", value: "1"},{name: "正常", value: "0"}]}
 		]};
 		var list=data.list;
		$.each(list,function(i,item){
			$.each(item.list,function(i,items){
				var value=items.value;
				if(items.name=="正常"){
					$("#zd-zc_"+item.channelId+"").text(value);
				}else if(items.name=="异常"){
					$("#zd-yc_"+item.channelId+"").text(value);
				}
			});
		  var option = {
	                series: [
	                    {
	                    	label: {
	   			                normal: {
	   			                    show: false,
	   			                    position: 'center'
	   			                },
	   			                emphasis: {
	   			                    show: true,
	   			                    position: 'center',
	   			                    textStyle: {
	   			                        fontSize: '10',
	   			                        fontWeight: 'bold'
	   			                    }
	   			                }
	   			            },
	                        type: 'pie',
	                        radius: ['30%', '50%'],
	                        hoverAnimation: false,
	                        color: ['#f9e421', '#2addb9'],
	                        data: item.list
	                    }
	                ]
	            };
			 echarts.init(document.getElementById("channelId_"+item.channelId)).setOption(option);
		 });
/*	$.ajax({
		async:false,
		data: {orgId :orgId},
		dataType: "json",
    	type:"POST",
     	url:"event/warnTerminalMonitor",
     	success:function(data){
     		data={list:[{totalNum: 0, channelId: "3", name: "大喇叭", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
     		,{totalNum: 0, channelId: "10", name: "广播", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
     		,{totalNum: 0, channelId: "11", name: "网站", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
     		,{totalNum: 0, channelId: "26", name: "报警器", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
     		,{totalNum: 0, channelId: "7", name: "传真", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
     		,{totalNum: 0, channelId: "2", name: "声讯", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
     		,{totalNum: 0, channelId: "13", name: "微信", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
     		,{totalNum: 0, channelId: "12", name: "微博", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
     		,{totalNum: 0, channelId: "14", name: "手机APP", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
     		,{totalNum: 0, channelId: "4", name: "显示屏", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
     		,{totalNum: 0, channelId: "5", name: "北斗卫星", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
     		,{totalNum: 0, channelId: "8", name: "电视", list: [{name: "异常", value: "0"},{name: "正常", value: "0"}]}
     		,{totalNum: 1, channelId: "1", name: "短信", list: [{name: "异常", value: "1"},{name: "正常", value: "0"}]}
     		]};
     		console.log(data);
     		var list=data.list;
    		$.each(list,function(i,item){
    			$.each(item.list,function(i,items){
    				var value=items.value;
    				if(items.name=="正常"){
    					$("#zd-zc_"+item.channelId+"").text(value);
    				}else if(items.name=="异常"){
    					$("#zd-yc_"+item.channelId+"").text(value);
    				}
    			});
			  var option = {
		                series: [
		                    {
		                    	label: {
		   			                normal: {
		   			                    show: false,
		   			                    position: 'center'
		   			                },
		   			                emphasis: {
		   			                    show: true,
		   			                    position: 'center',
		   			                    textStyle: {
		   			                        fontSize: '10',
		   			                        fontWeight: 'bold'
		   			                    }
		   			                }
		   			            },
		                        type: 'pie',
		                        radius: ['30%', '50%'],
		                        hoverAnimation: false,
		                        color: ['#f9e421', '#2addb9'],
		                        data: item.list
		                    }
		                ]
		            };
				 echarts.init(document.getElementById("channelId_"+item.channelId)).setOption(option);
			 });
     		}
 		})*/
	}
	/**
	 * 获取预警终端异常统计详细列表
	 */
	,terminalMonitorDetail=function(page,param,count){
		param.page=page;
		$.ajax({
			async:false,
        	type:"POST",
        	data:param,
			dataType:"json",
         	url:"map/terminalMonitorDetail",
         	success:function(data){
        		var html="";
				html +="<table class='terminalMonitortable' >";
				html +="<thead >";
				html +="	<tr>";
				html +="	<td  >名称</td>";
				html +="	<td  >号码 </td>";
				html +="	</tr>";
				html +="</thead>";
				html +="<tbody>";
				if(data.length>0){
					$.each(data,function(i,item){
						html +="<tr>";
						html +="	<td >"+item.TYPENAME+"</td>";
						html +="	<td title='"+item.METHODCONTENT+"'>"+item.METHODCONTENT.substring(0,11)+"</td>";
						html +="</tr>";
					});
				}else{
					html +="<tr><td style='width: 350px;text-align:center; '>无数据</td></tr>";
				}
				html +="</tbody>";
				html +="</table>";
				html +="<ul class='ul_btn' >";
				html +="	<span id='terminalDetailNextPage' data-next-page='"+page+"' data-count='"+count+"'>下页</span>";
				html += "	<span id='terminalDetailTopPage'  data-top-page='"+page+"' data-count='"+count+"'>上页</span>";
				html += "	<span>第"+(Number(page)+1)+"页</span>";
				html +="	<span>总数"+count+"条</span>";
				html +="</ul>";
				$(".terminalMonitorDetail .content").empty().prepend(html);
     		}
 		})
        // 点击（下页）按钮事件
        $("#terminalDetailNextPage").bind("click",function(){
        	var nextPage = $(this).data("nextPage");
        	var count = $(this).data("count");
        	if((nextPage+1)* param.limit>=count){	
        		return false;
        	}
        	terminalMonitorDetail(nextPage+1,param,count);
        });
        // 点击（上页）按钮事件
        $("#terminalDetailTopPage").bind("click",function(){
        	var topPage = $(this).data("topPage");
        	var count = $(this).data("count");
        	if(topPage==0){
        		return false;
        	}
        	terminalMonitorDetail(topPage-1,param,count);
        });
	}	
	/**
	 * 获取预警终端异常统计
	 */
	,warnTerminalMonitor=function(){
		var channel=[
 		     {channelType:"1",contentSplit:"1",createTime:"2015-7-21 15:16:45",description:"发送短信"
     			,keyId:"1",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"短信"
     			,sendType:"2",sortNo:"1",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
  		    ,{channelType:"14",contentSplit:"1",createTime:"2015-7-21 15:43",description:"APP手机"
     			,keyId:"14",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"手机APP"
     			,sendType:"2",sortNo:"2",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
  		    ,{channelType:"2",contentSplit:"1",createTime:"2015-7-21 15:43",description:"声讯"
  		    	,keyId:"2",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"声讯"
	    		,sendType:"2",sortNo:"3",sourceAreaCode:"43000000000000",syncTime:"",userType:"1"}
      		,{channelType:"3",contentSplit:"1",createTime:"2015-7-21 15:43",description:"DLB"
      			,keyId:"3",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"大喇叭"
  				,sendType:"1",sortNo:"4",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
          	,{channelType:"4",contentSplit:"1",createTime:"2015-7-21 15:39:30",description:""
             	,keyId:"4",maintainer:"wc",maintainerArea:"wc",maintainerOrg:"wc",maintainerTel:"13012345678",name:"显示屏"
             	,sendType:"1",sortNo:"5",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
             ,{channelType:"11",contentSplit:"2",createTime:"2015-7-21 15:42:27. 0",description:""
             	,keyId:"11",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"网站"
             	,sendType:"2",sortNo:"6",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
             ,{channelType:"12",contentSplit:"2",createTime:"2015-7-21 15:42:27. 0",description:""
              	,keyId:"12",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"微博"
              	,sendType:"2",sortNo:"7",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
             ,{channelType:"13",contentSplit:"2",createTime:"2015-7-21 15:42:27. 0",description:""
               	,keyId:"13",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"微信"
               	,sendType:"2",sortNo:"8",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
             ,{channelType:"5",contentSplit:"2",createTime:"2015-7-21 15:42:27. 0",description:""
                	,keyId:"5",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"北斗卫星"
                	,sendType:"2",sortNo:"9",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
             ,{channelType:"8",contentSplit:"2",createTime:"2015-7-21 15:42:27. 0",description:""
             	,keyId:"8",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"电视"
             	,sendType:"2",sortNo:"10",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
             ,{channelType:"10",contentSplit:"2",createTime:"2015-7-21 15:42:27. 0",description:""
             	,keyId:"10",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"广播"
             	,sendType:"2",sortNo:"11",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
             ,{channelType:"7",contentSplit:"2",createTime:"2015-7-21 15:42:27. 0",description:""
             	,keyId:"7",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"传真"
             	,sendType:"2",sortNo:"12",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
             ,{channelType:"26",contentSplit:"2",createTime:"2015-7-21 15:42:27. 0",description:""
             	,keyId:"26",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"报警器"
             	,sendType:"2",sortNo:"13",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
  			];
		var html="";
		var movespot="";
/*		$.ajax({
			async:false,
        	type:"POST",
         	url:"channel/findChannelAll",
         	success:function(data){
         		console.log(data);
         		channel=[
         		     {channelType:"1",contentSplit:"1",createTime:"2015-7-21 15:16:45",description:"发送短信"
	         			,keyId:"1",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"短信"
	         			,sendType:"2",sortNo:"1",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
         		    ,{channelType:"14",contentSplit:"1",createTime:"2015-7-21 15:43",description:"APP手机"
	         			,keyId:"14",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"手机APP"
	         			,sendType:"2",sortNo:"2",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
         		    ,{channelType:"2",contentSplit:"1",createTime:"2015-7-21 15:43",description:"声讯"
         		    	,keyId:"2",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"声讯"
     		    		,sendType:"2",sortNo:"3",sourceAreaCode:"43000000000000",syncTime:"",userType:"1"}
             		,{channelType:"3",contentSplit:"1",createTime:"2015-7-21 15:43",description:"DLB"
             			,keyId:"3",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"大喇叭"
         				,sendType:"1",sortNo:"4",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
                 	,{channelType:"4",contentSplit:"1",createTime:"2015-7-21 15:39:30",description:""
	                 	,keyId:"4",maintainer:"wc",maintainerArea:"wc",maintainerOrg:"wc",maintainerTel:"13012345678",name:"显示屏"
	                 	,sendType:"1",sortNo:"5",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
                    ,{channelType:"11",contentSplit:"2",createTime:"2015-7-21 15:42:27. 0",description:""
                    	,keyId:"11",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"网站"
                    	,sendType:"2",sortNo:"6",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
                    ,{channelType:"12",contentSplit:"2",createTime:"2015-7-21 15:42:27. 0",description:""
                     	,keyId:"12",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"微博"
                     	,sendType:"2",sortNo:"7",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
                    ,{channelType:"13",contentSplit:"2",createTime:"2015-7-21 15:42:27. 0",description:""
                      	,keyId:"13",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"微信"
                      	,sendType:"2",sortNo:"8",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
                    ,{channelType:"5",contentSplit:"2",createTime:"2015-7-21 15:42:27. 0",description:""
                       	,keyId:"5",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"北斗卫星"
                       	,sendType:"2",sortNo:"9",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
                    ,{channelType:"8",contentSplit:"2",createTime:"2015-7-21 15:42:27. 0",description:""
                    	,keyId:"8",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"电视"
                    	,sendType:"2",sortNo:"10",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
                    ,{channelType:"10",contentSplit:"2",createTime:"2015-7-21 15:42:27. 0",description:""
                    	,keyId:"10",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"广播"
                    	,sendType:"2",sortNo:"11",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
                    ,{channelType:"7",contentSplit:"2",createTime:"2015-7-21 15:42:27. 0",description:""
                    	,keyId:"7",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"传真"
                    	,sendType:"2",sortNo:"12",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
                    ,{channelType:"26",contentSplit:"2",createTime:"2015-7-21 15:42:27. 0",description:""
                    	,keyId:"26",maintainer:"",maintainerArea:"",maintainerOrg:"",maintainerTel:"",name:"报警器"
                    	,sendType:"2",sortNo:"13",sourceAreaCode:"43000000000000",syncTime:"",userType:"0"}
         			];
         		}
     		})*/
		var len=Math.ceil(channel.length/2);
		//左右移动点拼接
		for(var i=1;i<len+1;i++){
			if(i==1){
				movespot +="<li class='on sport_"+i+"'>"+i+"</li>";
			}else{
				movespot +="<li class='sport_"+i+"'>"+i+"</li>";
			}
			$(".movespot ul").empty().append(movespot);
		}
		for(var i=0;i<len;i++){
			 	html +="<li>" ;
			 	html +="<div class='dlList'>" ;
             for(var j=2*i;j<2*i+2;j++){
            	 if(j<channel.length){
            	 var name=channel[j].name;
//            	 var pinyin= codefans_net_CC2PY(name).toLowerCase();//转化成拼音并转化小写;
                 html +="	<dl>" ;
                 html +="		<div class='netWork-tempWrap'>" ;
                 html +="			<div class='zdbox'>" ;
                 html +="				<div class='topbox'>" ;
                 html +="					<span ><img src='/map/images/channel/"+channel[j].keyId+".png' style='width: 2.3rem;'></span>" ;
                 html +="					<span class='text'>"+name+"</span>" ;
                 html +="				</div>" ;
                 html +="				<div class='middlebox'>" ;
                 html +="					<div id='channelId_"+channel[j].keyId+"' class='zdabsolute' _echarts_instance_='ec_1516258119870' style='-webkit-tap-highlight-color: transparent; user-select: none; background: transparent;'>" ;
             	 html +="						<div style='position: relative; overflow: hidden; width: 120px; height: 120px; padding: 0px; margin: 0px; border-width: 0px;'></div>" ;
                 html +="					</div>" ;
                 html +="				</div>" ;
                 html +="				<div class='bottombox' data-channel-id='"+channel[j].keyId+"'>" ;
                 html +="					<div class='zt'>" ;
                 html +="						<div>" ;
                 html +="							<span class='colorbox bluebg'></span>" ;
                 html +="							<span class='zttext'>正常</span>" ;
                 html +="							<span id='zd-zc_"+channel[j].keyId+"' class='ztbl dls'>0</span>" ;
                 html +="						</div>" ;
                 html +="						<div>" ;
                 html +="							<span class='colorbox yellobg'></span>" ;
                 html +="							<span class='zttext'>异常</span>" ;
                 html +="							<span id='zd-yc_"+channel[j].keyId+"' class='ztbl coloryellow'>0</span>" ;
                 html +="						</div>" ;
                 html +="					</div>" ;
                 html +="				</div>" ;
                 html +="			</div>" ;
                 html +="		</div>" ;
                 html +="	</dl>" ; 
            	 }
             }
             html +="</div>" ;
             html +="</li>" ;
		}
		$(".ul_wrap ul").empty().append(html);
		//获取预警终端异常统计
		warnTerminalStatisc();
		$(".hd li").on("click",function(){
			var num=$(this).index();
			var dlLen = $(this).parent().children("li").length;
	    	// 不够2个时左右点击切换失效（显示区域永远为2个）
	    	if(dlLen< 2){
	    		return false;
	    	}
	    	$(this).parent().children("li").removeClass("on");
	    	$(this).addClass("on");
	    	$(".leftfirst2 .modlemiddle .modlect .yjzdctt .bd .tempWrap ul").animate({"left": -($(".ul_wrap ul").outerWidth(true)*0.125*num) + "px"}, "slow");
		});
		$(".tempWrap ul li .dlList dl .netWork-tempWrap .zdbox .bottombox .zt div span:last-child").on("click",function(){
			var channelId=$(this).parent().parent().parent().data("channelId");
			var state=$(this).parent().children("span:nth-child(2)").text();
			var count=Number($(this).parent().children("span:last-child").text());
			var param={
				"state":state,"channelId":channelId,"limit":20	
			}
			//终端异常情况详细列表
			$(".terminalMonitorDetail").show();
			terminalMonitorDetail(0,param,count);
		});
	}
	
    /**
     * 政务外网监控
     */
    ,governmentExtranet=function(state){
    	var datas=null;
    	var zc=0;
    	var yc=0;
    	var param={"state":state};
    	var data=[
			{STATE: "正常", CHANNELCODE: "省地震局", KEYID: "104"}
			,{STATE: "异常", CHANNELCODE: "省国土资源厅", KEYID: "105"}
			,{STATE: "正常", CHANNELCODE: "省应急办", KEYID: "106"}
			,{STATE: "异常", CHANNELCODE: "省林业厅", KEYID: "107"}
			,{STATE: "正常", CHANNELCODE: "省旅游局", KEYID: "108"}
			,{STATE: "异常", CHANNELCODE: "省环保厅", KEYID: "109"}
			,{STATE: "正常", CHANNELCODE: "省水利厅", KEYID: "7095BB5F12AC404D9B8CF3B976AC0F84"}
			,{STATE: "异常", CHANNELCODE: "省安监局", KEYID: "111"}
			,{STATE: "异常", CHANNELCODE: "省住建厅", KEYID: "112"}
			,{STATE: "异常", CHANNELCODE: "省交管局", KEYID: "113"}
			,{STATE: "异常", CHANNELCODE: "省水运管理局", KEYID: "114"}];
	 		for(var i=0;i<data.length;i++){
	 			if(data[i].STATE=="正常"){
	 				zc=zc+1;
	 			}else{
	 				yc=yc+1;
	 			}
	 		}
/*    	$.ajax({
			async:false,
        	type:"POST",
        	data:param,
        	dataType:'json',
         	url:"map/governmentExtranet",
         	success:function(data){
         		console.log(data);
         		data=[
					{STATE: "正常", CHANNELCODE: "省地震局", KEYID: "104"}
					,{STATE: "异常", CHANNELCODE: "省国土资源厅", KEYID: "105"}
					,{STATE: "正常", CHANNELCODE: "省应急办", KEYID: "106"}
					,{STATE: "异常", CHANNELCODE: "省林业厅", KEYID: "107"}
					,{STATE: "正常", CHANNELCODE: "省旅游局", KEYID: "108"}
					,{STATE: "异常", CHANNELCODE: "省环保厅", KEYID: "109"}
					,{STATE: "正常", CHANNELCODE: "省水利厅", KEYID: "7095BB5F12AC404D9B8CF3B976AC0F84"}
					,{STATE: "异常", CHANNELCODE: "省安监局", KEYID: "111"}
					,{STATE: "异常", CHANNELCODE: "省住建厅", KEYID: "112"}
					,{STATE: "异常", CHANNELCODE: "省交管局", KEYID: "113"}
					,{STATE: "异常", CHANNELCODE: "省水运管理局", KEYID: "114"}];
	         		for(var i=0;i<data.length;i++){
	         			if(data[i].STATE=="正常"){
	         				zc=zc+1;
	         			}else{
	         				yc=yc+1;
	         			}
	         		}
         		}
         	});*/
    	var option = {
    		    tooltip : {
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b} : {c} ({d}%)"
    		    },
    		    color:['#206b57','#dc3545'],
    		    legend: {
    		        orient : 'vertical',
    		        x : 'left',
    		        left: 10,
    		        textStyle:{
    		            color: 'white'
    		        },
    		        data:['正常','异常']
    		    },
    		    calculable : true,
    		    series : [{
    		    	name: '异常统计',
	                type: 'pie',
	                radius : '80%',
		            center: ['50%', '50%'],
	                data:[
	                      {value:zc, name:'正常'},
	                      {value:yc, name:'异常'}],
			              itemStyle: {
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
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }]
    		};
			var myChart = echarts.init(document.getElementById("governmentExtranet"));
			myChart.setOption(option);
			myChart.on("click", function(pm){
				beidouNetwork(pm.name);
		});
    };
    
    /**
     * 政务外网异常监控
     */
    var beidouNetwork=function(state){
    	var param={"state":state}
    	var data=[];
    	
    	if(state=="正常"){
 			data=[
					{STATE: "正常", CHANNELCODE: "省地震局", KEYID: "104"}
					,{STATE: "正常", CHANNELCODE: "省应急办", KEYID: "106"}
					,{STATE: "正常", CHANNELCODE: "省旅游局", KEYID: "108"}
					,{STATE: "正常", CHANNELCODE: "省水利厅", KEYID: "7095BB5F12AC404D9B8CF3B976AC0F84"}];
 		}else if(state=="异常"){
 			data=[{STATE: "异常", CHANNELCODE: "省国土资源厅", KEYID: "105"}
					,{STATE: "异常", CHANNELCODE: "省林业厅", KEYID: "107"}
					,{STATE: "异常", CHANNELCODE: "省环保厅", KEYID: "109"}
					,{STATE: "异常", CHANNELCODE: "省安监局", KEYID: "111"}
					,{STATE: "异常", CHANNELCODE: "省住建厅", KEYID: "112"}
					,{STATE: "异常", CHANNELCODE: "省交管局", KEYID: "113"}
					,{STATE: "异常", CHANNELCODE: "省水运管理局", KEYID: "114"}];
 		}else{
       		data=[
			{STATE: "正常", CHANNELCODE: "省地震局", KEYID: "104"}
			,{STATE: "异常", CHANNELCODE: "省国土资源厅", KEYID: "105"}
			,{STATE: "正常", CHANNELCODE: "省应急办", KEYID: "106"}
			,{STATE: "异常", CHANNELCODE: "省林业厅", KEYID: "107"}
			,{STATE: "正常", CHANNELCODE: "省旅游局", KEYID: "108"}
			,{STATE: "异常", CHANNELCODE: "省环保厅", KEYID: "109"}
			,{STATE: "正常", CHANNELCODE: "省水利厅", KEYID: "7095BB5F12AC404D9B8CF3B976AC0F84"}
			,{STATE: "异常", CHANNELCODE: "省安监局", KEYID: "111"}
			,{STATE: "异常", CHANNELCODE: "省住建厅", KEYID: "112"}
			,{STATE: "异常", CHANNELCODE: "省交管局", KEYID: "113"}
			,{STATE: "异常", CHANNELCODE: "省水运管理局", KEYID: "114"}];
 		}
 		var html="";
 		$.each(data,function(i,item){
 			var dataobj=JSON.stringify(item);
 			if(item.STATE=="正常"){
 				html +="<li >";
 			}else{
 				html +="<li class='on'>";
 			}
 			 	html +="	<span title='"+item.CHANNELCODE+"'>"+item.CHANNELCODE.substring(0,4)+"</span>";
 			 	html +="</li>";
 		 	});
 		$(".beidouNetwork ul").empty().append(html);
    /*	$.ajax({
			async:false,
        	type:"POST",
        	data:param,
        	dataType:'json',
         	url:"map/governmentExtranet",
         	success:function(data){
         		
         		if(state=="正常"){
         			data=[
      					{STATE: "正常", CHANNELCODE: "省地震局", KEYID: "104"}
      					,{STATE: "正常", CHANNELCODE: "省应急办", KEYID: "106"}
      					,{STATE: "正常", CHANNELCODE: "省旅游局", KEYID: "108"}
      					,{STATE: "正常", CHANNELCODE: "省水利厅", KEYID: "7095BB5F12AC404D9B8CF3B976AC0F84"}];
         		}else if(state=="异常"){
         			data=[{STATE: "异常", CHANNELCODE: "省国土资源厅", KEYID: "105"}
      					,{STATE: "异常", CHANNELCODE: "省林业厅", KEYID: "107"}
      					,{STATE: "异常", CHANNELCODE: "省环保厅", KEYID: "109"}
      					,{STATE: "异常", CHANNELCODE: "省安监局", KEYID: "111"}
      					,{STATE: "异常", CHANNELCODE: "省住建厅", KEYID: "112"}
      					,{STATE: "异常", CHANNELCODE: "省交管局", KEYID: "113"}
      					,{STATE: "异常", CHANNELCODE: "省水运管理局", KEYID: "114"}];
         		}else{
               		data=[
					{STATE: "正常", CHANNELCODE: "省地震局", KEYID: "104"}
					,{STATE: "异常", CHANNELCODE: "省国土资源厅", KEYID: "105"}
					,{STATE: "正常", CHANNELCODE: "省应急办", KEYID: "106"}
					,{STATE: "异常", CHANNELCODE: "省林业厅", KEYID: "107"}
					,{STATE: "正常", CHANNELCODE: "省旅游局", KEYID: "108"}
					,{STATE: "异常", CHANNELCODE: "省环保厅", KEYID: "109"}
					,{STATE: "正常", CHANNELCODE: "省水利厅", KEYID: "7095BB5F12AC404D9B8CF3B976AC0F84"}
					,{STATE: "异常", CHANNELCODE: "省安监局", KEYID: "111"}
					,{STATE: "异常", CHANNELCODE: "省住建厅", KEYID: "112"}
					,{STATE: "异常", CHANNELCODE: "省交管局", KEYID: "113"}
					,{STATE: "异常", CHANNELCODE: "省水运管理局", KEYID: "114"}];
         		}
         		var html="";
         		$.each(data,function(i,item){
         			var dataobj=JSON.stringify(item);
         			if(item.STATE=="正常"){
         				html +="<li >";
         			}else{
         				html +="<li class='on'>";
         			}
	     			 	html +="	<span title='"+item.CHANNELCODE+"'>"+item.CHANNELCODE.substring(0,4)+"</span>";
	     			 	html +="</li>";
         		 	});
         		$(".beidouNetwork ul").empty().append(html);
         	}
		})*/
    }
    /**
     * 虚拟网络监控
     */
    ,virtualization=function(){
    	var data=[
       		{NAME: "短信", RAM: "21%", DISK: "8%", CPU: "9%"}
     		,{NAME: "广播", RAM: "80%", DISK: "7%", CPU: "0%"}
     		,{NAME: "网站", RAM: "53%", DISK: "19%", CPU: "12%"}
     		,{NAME: "微博", RAM: "27%", DISK: "4%", CPU: "0%"}
     		,{NAME: "微信", RAM: "27%", DISK: "4%", CPU: "0%"}
     		,{NAME: "手机APP", RAM: "59%", DISK: "7%", CPU: "17%"}
     		,{NAME: "声讯", RAM: "21%", DISK: "8%", CPU: "9%"}
     		,{NAME: "大喇叭", RAM: "30%", DISK: "3%", CPU: "0%"}
     		,{NAME: "显示屏", RAM: "30%", DISK: "3%", CPU: "0%"}
     		,{NAME: "北斗卫星", RAM: "22%", DISK: "3%", CPU: "0%"}
     		,{NAME: "传真", RAM: "30%", DISK: "9%", CPU: "8%"}
     		,{NAME: "电视", RAM: "80%", DISK: "7%", CPU: "0%"}];
     		if(data.length>0){
         		var html="";
         		$.each(data,function(i,item){
         			var dataobj=JSON.stringify(item);
         				html +="<dl class='' data-server='"+dataobj+"'>";
	     			 	html +="	<dd title='"+item.NAME+"'>"+item.NAME+"</dd>";
	     			 	html +="	<dd title='CPU:"+item.CPU+"'>"+item.CPU+"</dd>";
	     			 	html +="	<dd title='RAM:"+item.RAM+"'>"+item.RAM+"</dd>";
	     			 	html +="	<dd title='硬盘:"+item.DISK+"'>"+item.DISK+"</dd>";
	     			 	html +="</dl>";
         		 	});
         		$(".virtualization .listContent").empty().append(html);
         		$(".virtualization .listContent dl").on("click",function(){
         			var obj=$(this).data("server");
         			communicationNetwork(obj);
         		});
     		}else{
     			$(".virtualization").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
     		}
 /*   	$.ajax({
			async:false,
        	type:"POST",
        	data:null,
        	dataType:'json',
         	url:"map/virtualization",
         	success:function(data){
         		console.log(data);
         		data:[
         		{NAME: "短信", RAM: "21%", DISK: "8%", CPU: "9%"}
         		,{NAME: "广播", RAM: "80%", DISK: "7%", CPU: "0%"}
         		,{NAME: "网站", RAM: "53%", DISK: "19%", CPU: "12%"}
         		,{NAME: "微博", RAM: "27%", DISK: "4%", CPU: "0%"}
         		,{NAME: "微信", RAM: "27%", DISK: "4%", CPU: "0%"}
         		,{NAME: "手机APP", RAM: "59%", DISK: "7%", CPU: "17%"}
         		,{NAME: "声讯", RAM: "21%", DISK: "8%", CPU: "9%"}
         		,{NAME: "大喇叭", RAM: "30%", DISK: "3%", CPU: "0%"}
         		,{NAME: "显示屏", RAM: "30%", DISK: "3%", CPU: "0%"}
         		,{NAME: "北斗卫星", RAM: "22%", DISK: "3%", CPU: "0%"}
         		,{NAME: "传真", RAM: "30%", DISK: "9%", CPU: "8%"}
         		,{NAME: "电视", RAM: "80%", DISK: "7%", CPU: "0%"}];
         		if(data.length>0){
             		var html="";
             		$.each(data,function(i,item){
             			var dataobj=JSON.stringify(item);
             				html +="<dl class='' data-server='"+dataobj+"'>";
    	     			 	html +="	<dd title='"+item.NAME+"'>"+item.NAME+"</dd>";
    	     			 	html +="	<dd title='CPU:"+item.CPU+"'>"+item.CPU+"</dd>";
    	     			 	html +="	<dd title='RAM:"+item.RAM+"'>"+item.RAM+"</dd>";
    	     			 	html +="	<dd title='硬盘:"+item.DISK+"'>"+item.DISK+"</dd>";
    	     			 	html +="</dl>";
             		 	});
             		$(".virtualization .listContent").empty().append(html);
             		$(".virtualization .listContent dl").on("click",function(){
             			var obj=$(this).data("server");
             			communicationNetwork(obj);
             		});
         		}else{
         			$(".virtualization").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
         		}
         	}
		})*/
    }
    /**
     * 通讯网络监控
     */
    ,communicationNetwork=function(obj){
    	if(obj==null){
    		var obj=$(".virtualization .listContent dl:first-child").data("server");
    	}
		var cpu=obj.CPU;
		var ram=obj.RAM;
		var disk=obj.DISK;
		var cpuUse=Number(cpu.substring(0, cpu.lastIndexOf('%')));
		var cpuUnuse=100-cpuUse;
		var ramUse=Number(ram.substring(0, ram.lastIndexOf('%')));
		var ramUnuse=100-ramUse;
		var diskUse=Number(disk.substring(0, disk.lastIndexOf('%')));
		var diskUnuse=100-diskUse;
		var arry=[{"name":"CPU","use":cpuUse,"unuse":cpuUnuse},{"name":"RAM","use":ramUse,"unuse":ramUnuse},{"name":"DISK","use":diskUse,"unuse":diskUnuse}];
		for(var i=0;i<arry.length;i++){
			var name="";
			if(arry[i].name=="DISK"){
				name="硬盘";
			}else{
				name=arry[i].name;
			}
	    	var option = {
	    			title : {
    			        text: name,
    			        x:'center',
    			        textStyle:{
    			            color: 'white',
    			            fontSize:12
    			        }
    			    },
	    		    tooltip : {
	    		        trigger: 'item',
	    		        formatter: "{a} <br/>{b} : ({d}%)"
	    		    },
	    		    color:['#9c921a','#3cbdb7'],
	    		    calculable : true,
	    		    series : [{
	    		    	name: name,
		                type: 'pie',
		                radius : '75%',
			            center: ['50%', '60%'],
		                data:[
		                      {value:arry[i].use, name:'使用'},
		                      {value:arry[i].unuse, name:'剩余'}],
				              itemStyle: {
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
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }]
	    		};
			echarts.init(document.getElementById(arry[i].name)).setOption(option);
		}
    }
    /**
     * 预警终端监控自动滚动
     */
    var channelSlow = function(){
    	var len = $(".movespot > ul > li ").length ;
    	clearInterval(Globel.interval);
    	var fun = function(){
    		playNum=playNum+1;
    		$(".sport_"+playNum).parent().children("li").removeClass("on");
    		$(".sport_"+playNum).addClass("on");
        	if(playNum<=len){
    			$(".leftfirst2 .modlemiddle .modlect .yjzdctt .bd .tempWrap ul").animate({"left": -($(".ul_wrap ul").outerWidth(true)*0.125*(playNum-1)) + "px"}, "slow");
    		}
    		if(playNum==len){
    			playNum=0;
    		}
    	};
    	Globel.interval = setInterval(fun, 3000);//全局变量定时器
    };
    
    /**
     * 虚拟化网络监控自动滚动
     */
    var virtualizationSlow = function(){
    	var len = $(".virtualization .listContent dl").length ;
    	clearInterval(Globel.netInterval);
    	var fun = function(){
    		netNum=netNum+1;
    		$(".virtualization .listContent dl").removeClass("on");
    		$(".virtualization .listContent dl:nth-child("+netNum+")").addClass("on");
        	if(netNum<=len){
        		var obj=$(".virtualization .listContent dl:nth-child("+netNum+")").data("server");
     			communicationNetwork(obj);
    		}
    		if(netNum==len){
    			netNum=0;
    		}
    	};
    	Globel.netInterval = setInterval(fun, 3000);//全局变量定时器
    };
    
    //鼠标悬浮操作
    $(".virtualization").mouseover(function(){
    	clearInterval(Globel.netInterval);
    	$(".virtualization .listContent dl").removeClass("on");
      });
    //鼠标离开操作
    $(".virtualization").mouseout(function(){
    	netNum=0;
    	virtualizationSlow();	
    });
    
    //鼠标悬浮操作
    $(".leftfirst2").mouseover(function(){
    	clearInterval(Globel.interval);
      });
    //鼠标离开操作
    $(".leftfirst2").mouseout(function(){
    	playNum=0;
    	channelSlow();	
    });
    /**
     * 各市运行情况监控详细列表
     */
    var monitorDetail=function(name,state){
		var setting = {
				async:{
					enable:true,
					url: "map/monitorTreeDetail",
					autoParam: ["id=parentId"],
					otherParam:["servicetype",name,"state",state]
				},
				data:{ // 必须使用data    
		            simpleData : {    
		                enable : true,    
		                idKey : "id", // id编号命名     
		                pIdKey : "pId", // 父id编号命名      
		            }    
	            },
			};
		$.fn.zTree.init($("#monitorDetailTree"), setting);  
		$(".conditionMonitorDetail").show();
    }
    /**
	 * 各市运行情况统计
	 */
    var conditionMonitorHtml=function(data){
    	if(data.length >0){
    		var html="";
    		$.each(data,function(i,item){
    			var dataobj=JSON.stringify(item);
    			html +="<li data-monitor='"+dataobj+"'>";
    			html +="	<div class='leftbox '></div>";
    			html +="	<div class='righttext'>";
    			html +="		<p class='title'><span>"+item.name+"</span></p>";
    			html +="		<p><span title=''>";
    			for(var i=0;i<2;i++){
    				if(item.list[i].name=="正常"){
    					html +="正常：<span class='huang'>"+item.list[i].value+"</span>个 ";
    				}else{
    					html +="异常：<span class='hong'>"+item.list[i].value+"</span>个";
    				}
    			}
    			html +="		</span></p>";
    			html +="    </div>";
    			html +="</li>";
    		});
    		$(".severlist ul").empty().append(html);
    		$(".severlist>ul>li>div.righttext>p>span .huang").on("click",function(){
    			var name=$(this).parent().parent().parent().children(".title").text();
    			if($(this).text()!=0){
    				monitorDetail(name,"正常");//正常情况统计详细信息
    			}else{
    				$(".conditionMonitorDetail").hide();
    			}
    		})
    		$(".severlist>ul>li>div.righttext>p>span .hong").on("click",function(){
    			var name=$(this).parent().parent().parent().children(".title").text();
    			if($(this).text()!=0){
    				monitorDetail(name,"异常");//异常情况统计详细信息
    			}else{
    				$(".conditionMonitorDetail").hide();
    			}
    		})
    	}else{
	   		$(".serverisuse > .sever-r").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
    	}
	}
    /**
	 * 各市异常情况统计
	 */
	var abnormalMonitor=function (data) {
		if(JSON.stringify(data) != "{}"){
        var option = {
                tooltip: { //提示框组件
                    trigger: 'axis',
                    formatter: '{b}<br />{a0}: {c0}<br />{a1}: {c1}',
                    axisPointer: {
                        type: 'shadow',
                        label: {
                            backgroundColor: '#6a7985'
                        }
                    },
                    textStyle: {
                        color: '#fff',
                        fontStyle: 'normal',
                        fontFamily: '微软雅黑',
                        fontSize: 12,
                    }
                },
                grid: {
                    left: '1%',
                    right: '4%',
                    bottom: '1%',
                    top: 30,
                    padding: '0 0 10 0',
                    containLabel: true,
                },
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: true,//坐标轴两边留白
                        data: data.name,
                        axisLabel: { //坐标轴刻度标签的相关设置。
                            interval: 0,//设置为 1，表示『隔一个标签显示一个标签』
                            margin: 15,
                            textStyle: {
                                color: '#50f1ff',
                                fontStyle: 'normal',
                                fontFamily: '微软雅黑',
                                fontSize: 12,
                            }
                        },
                        axisTick: {//坐标轴刻度相关设置。
                            show: false,
                        },
                        axisLine: {//坐标轴轴线相关设置
                            lineStyle: {
                                color: '#fff',
                                opacity: 0.2
                            }
                        },
                        splitLine: { //坐标轴在 grid 区域中的分隔线。
                            show: false,
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        splitNumber: 5,
                        axisLabel: {
                            textStyle: {
                                color: '#a8aab0',
                                fontStyle: 'normal',
                                fontFamily: '微软雅黑',
                                fontSize: 12,
                            }
                        },
                        axisLine: {
                            show: false
                        },
                        axisTick: {
                            show: false
                        },
                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: ['#fff'],
                                opacity: 0.06
                            }
                        }
                    }
                ],
                series: [
                    {
                        name: '正常',
                        type: 'bar',
                        data: data.zc,
                        barWidth: 14,
                        barGap: 0,//柱间距离
                        label: {//图形上的文本标签
                            normal: {
                                show: true,
                                position: 'top',
                                textStyle: {
                                    color: '#a8aab0',
                                    fontStyle: 'normal',
                                    fontFamily: '微软雅黑',
                                    fontSize: 12,
                                },
                            },
                        },
                        itemStyle: {//图形样式
                            normal: {
                                barBorderRadius: [5, 5, 0, 0],
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 1, color: 'rgba(127, 128, 225, 0.7)'
                                }, {
                                    offset: 0.9, color: 'rgba(72, 73, 181, 0.7)'
                                }, {
                                    offset: 0.31, color: 'rgba(0, 208, 208, 0.7)'
                                }, {
                                    offset: 0.15, color: 'rgba(0, 208, 208, 0.7)'
                                }, {
                                    offset: 0, color: 'rgba(104, 253, 255, 0.7)'
                                }], false),
                            },
                        }
                    },
                    {
                        name: '异常',
                        type: 'bar',
                        data: data.yc,
                        barWidth: 14,
                        barGap: 0.2,//柱间距离
                        label: {//图形上的文本标签
                            normal: {
                                show: true,
                                position: 'top',
                                textStyle: {
                                    color: '#a8aab0',
                                    fontStyle: 'normal',
                                    fontFamily: '微软雅黑',
                                    fontSize: 12,
                                },
                            },
                        },
                        itemStyle: {//图形样式
                            normal: {
                                barBorderRadius: [5, 5, 0, 0],
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 1, color: 'rgba(127, 128, 225, 0.7)'
                                }, {
                                    offset: 0.9, color: 'rgba(72, 73, 181, 0.7)'
                                }, {
                                    offset: 0.25, color: 'rgba(226, 99, 74, 0.7)'
                                }, {
                                    offset: 0, color: 'rgba(253, 200, 106, 0.7)'
                                }], false),
                            },
                        },
                    }
                ]
            };
        	var myChart = echarts.init(document.getElementById("weekchart"));
        	myChart.setOption(option);
		}else {
			$(".serverUse > .sever-r").empty().html("<span class='noData' style='font-size: 16px;top: 38%;right: 38%;position: absolute; color: gray;'>暂无数据！</span>");
		}
    };
    /**
     * 各市预警情况监控
     */
    var runnConditionMonitor=function(){
    	var data={totalNum: 60, yc: ["4", "1", "0"], zc: ["54", "0", "1"], name: ["应用", "数据库", "服务器"]
		,list:[{name: "应用", list: [{name: "正常", value: "54"},{name: "异常", value: "4"}]}
        ,{name: "数据库", list: [{name: "正常", value: 0},{name: "异常", value: "1"}]}
        ,{name: "服务器", list: [{name: "正常", value: "1"},{name: "异常", value: 0}]}
         ]};
    	$(".sever-r .topbox span").text(data.totalNum);
		conditionMonitorHtml(data.list);//各市运行情况统计
		abnormalMonitor(data);//各市异常统计柱状图
    	
  /*  	$.ajax({
    		async:false,
    		data:null,
    		dataType:"json",
			type:"POST",
			url:"event/runnConditionMonitor",
			success:function(data){
				console.log(data);
				data={totalNum: 60, yc: ["4", "1", "0"], zc: ["54", "0", "1"], name: ["应用", "数据库", "服务器"]
				,list:[{name: "应用", list: [{name: "正常", value: "54"},{name: "异常", value: "4"}]}
                ,{name: "数据库", list: [{name: "正常", value: 0},{name: "异常", value: "1"}]}
                ,{name: "服务器", list: [{name: "正常", value: "1"},{name: "异常", value: 0}]}
                 ]};
				$(".sever-r .topbox span").text(data.totalNum);
				conditionMonitorHtml(data.list);//各市运行情况统计
				abnormalMonitor(data);//各市异常统计柱状图
			}
    	})*/
    };
    systemMonitoring();		//系统运行监控
    warnTerminalMonitor();	//预警渠道监控
    runnConditionMonitor(); //服务运行情况监控

    governmentExtranet(null);	//政务外网异常监控
    beidouNetwork("");			//政务外网监控
    virtualization();			//虚拟网络监控
    communicationNetwork(null);	//通讯网络监控

    channelSlow();			//预警终端监控自动滚动 定时器
    virtualizationSlow();	//虚拟化网络监控自动滚动 定时器

});