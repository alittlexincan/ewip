$(function(){
	//严格模式
	"use strict";
	/**
	 * 所有统计图Option函数
	 * @author lxv
	 * @return option
	 */
	var initParam = {
	        "checked":"<i class='fa fa-check checked'></i>", // 勾选样式
	 }
	//渠道链路 总div内容
	,channelLinkContent = $("<div class='map-channel-link map-channel-link-bg' ></div>")
    /**
	 * 渠道链路 
	 * @param NULL
	 * @property channelLink
	 * @author lxv
	 * @return 对象
	 */	
	,channelLink = function(){
		return $("<div style='position:absolute;top:40px;left:5px;right:5px;bottom:5px;font-size:0;'>" +
					"<div id='main' style='width:100%;height:100%;font-size:1rem;display: inline-block;'></div>" +
				"</div>");
		}()
	//初始化 拼接页面
	,init =function(...params){
		for(var i = 0; i<params.length; i++){
			//拼接 HTML 
			$(channelLinkContent).append(params[i]);
		}
		//放到第一个js之前
		$("body").children("script").eq(0).before($(channelLinkContent));
	};  
	
	//初始化加载所有模块
	init(
		channelLink
	);	
	
	var monitor = function(){
	/*	var data={bottom:[
		  {sending: 0, text: "显示屏(显示屏)", imgSrc: "/map/images/channel-link/xianshiping_green.png", value: 0}
			,{sending: 0, text: "北斗卫星(北斗卫星)", imgSrc: "/map/images/channel-link/beidouweixing_green.png", value: 0}
			,{sending: 0, text: "电视(气象频道)", imgSrc: "/map/images/channel-link/dianshi_green.png", value: 0}
			,{sending: 0, text: "电视(影视直播)", imgSrc: "/map/images/channel-link/dianshi_green.png", value: 0}
			,{sending: 0, text: "电视(省级电视台渠道)", imgSrc: "/map/images/channel-link/dianshi_green.png", value: 0}]
		,left:[{sending: 0, text: "短信(10628121手段)", imgSrc: "/map/images/channel-link/duanxin_green.png", value: 0}
			,{sending: 0, text: "短信(12379手段)", imgSrc: "/map/images/channel-link/duanxin_green.png", value: 0}
			,{sending: 0, text: "广播(广播)", imgSrc: "/map/images/channel-link/guangbo_green.png", value: 0}
			,{sending: 0, text: "网站(网站)", imgSrc: "/map/images/channel-link/wangzhan_green.png", value: 0}
			,{sending: 0, text: "微博(新浪微博)", imgSrc: "/map/images/channel-link/weibo_green.png", value: 0}
			,{sending: 0, text: "微信(微信)", imgSrc: "/map/images/channel-link/weixin_green.png", value: 0}]
		,right:[{sending: 0, text: "手机APP(手机APP)", imgSrc: "/map/images/channel-link/shoujiapp_green.png", value: 0}
			,{sending: 0, text: "声讯(85600083手段)", imgSrc: "/map/images/channel-link/shengxun_green.png", value: 0}
			,{sending: 0, text: "声讯(400手段)", imgSrc: "/map/images/channel-link/shengxun_green.png", value: 0}
			,{sending: 0, text: "声讯(12379手段)", imgSrc: "/map/images/channel-link/shengxun_green.png", value: 0}
			,{sending: 0, text: "声讯(12121手段)", imgSrc: "/map/images/channel-link/shengxun_green.png", value: 0}
			,{sending: 0, text: "大喇叭(大喇叭)", imgSrc: "/map/images/channel-link/dalaba_green.png", value: 0}]};
		var cavData = $.channelMonitor("#main", {
	          bgColor:"none",
	          speed:2500,
	          centerIcon:"/map/images/channel-link/center_cpt0.png",
	          boxCallBack: function (a) {
	              if(a){
	                  alert("id="+a.id);
	              }
	          },
	          statusMapping:{                             //状态对应信息
	              0:{color:'lime',text:'运行正常',imgSrc:'/map/images/channel-link/status0.png',lineSrc:'/map/images/channel-link/status_line0.png',lineText:'渠道畅通'},
	              1:{color:'red',text:'运行异常',imgSrc:'/map/images/channel-link/status1.png',lineSrc:'/map/images/channel-link/status_line1.png',lineText:'渠道不通'},
	              2:{color:'lightgray',text:'未部署',imgSrc:'/map/images/channel-link/status2.png',lineSrc:'/map/images/channel-link/status_line2.png',lineText:'渠道未部署'}
	          },
	          data: data
	      });
	      if(cavData.messageBox){
	    	  cavData.messageBox.append("<p style='text-align: center;font-size: 1.6rem;margin-bottom: 0.5rem;'>突发平台实时推送预警信息</p>");
	    	  cavData.messageBox.append("<hr style='border-top: 1px solid #37c4cc;margin-top: 0rem!important;margin-bottom: 0.5rem;'/>");
	    	  cavData.messageBox.append("<div>" +
	    			  "<dl>");
	    	  if(data.img!=""){
	    		  cavData.messageBox.append("<dt style='float: left;padding: .5em;background: #163e8d;display: inline-block;width: 20%;height: 8.5rem;'><img style='width: 100%;height: 100%;' src='/images/warnlogo.png'></dt>");
	    	  }else{
	    		  cavData.messageBox.append("<dt style='float: left;padding: .5em;background: #4733da;display: inline-block;width: 20%;height: 8.5rem;'><img style='width: 100%;height: 100%;' src='/pc/earlywarningProcess/images/logo.gif'></dt>");
	    	  }
	    	  cavData.messageBox.append("<dd style='margin: 0.5em;display: inline-block;width: 75%;text-indent: 2em;color: #e8b82a;float: right;'>"+data.message+"</dd>" +
	        		  "</dl>"+
	          "</div>");
	      }
	      var content=function(){
	    	  cavData.setData();
	    	  window.requestAnimationFrame(content);
	      }
	      window.requestAnimationFrame(content);*/
		$.ajax({
			async: true,
			type: "POST",
			url: "/channel/monitor",
			data: {empAreaId:Globel.empAreaId},
			dataType: "json",
			success: function(data){
				var cavData = $.channelMonitor("#main", {
		          bgColor:"none",
		          speed:2500,
		          centerIcon:"/map/images/channel-link/center_cpt0.png",
		          boxCallBack: function (a) {
		              if(a){
		                  alert("id="+a.id);
		              }
		          },
		          statusMapping:{                             //状态对应信息
		              0:{color:'lime',text:'运行正常',imgSrc:'/map/images/channel-link/status0.png',lineSrc:'/map/images/channel-link/status_line0.png',lineText:'渠道畅通'},
		              1:{color:'red',text:'运行异常',imgSrc:'/map/images/channel-link/status1.png',lineSrc:'/map/images/channel-link/status_line1.png',lineText:'渠道不通'},
		              2:{color:'gray',text:'未部署',imgSrc:'/map/images/channel-link/status2.png',lineSrc:'/map/images/channel-link/status_line2.png',lineText:'渠道未部署'}
		          },
		          data: data
		      });
				console.log(data);
		      if(cavData.messageBox){
		    	  cavData.messageBox.append("<p style='text-align: center;font-size: 1.6rem;margin-bottom: 0.5rem;'>突发平台实时推送预警信息</p>");
		    	  cavData.messageBox.append("<hr style='border-top: 1px solid #37c4cc;margin-top: 0rem!important;margin-bottom: 0.5rem;'/>");
		    	  cavData.messageBox.append("<div>" +
		    			  "<dl>");
		    	  if(data.img!="" && data.img!=undefined){
		    		  cavData.messageBox.append("<dt style='float: left;padding: .5em;background: #163e8d;display: inline-block;width: 20%;height: 8.5rem;'><img style='width: 100%;height: 100%;' src='"+data.img+"'></dt>");
                      cavData.messageBox.append("<dd style='margin: 0.5em;display: inline-block;width: 75%;text-indent: 2em;color: #e8b82a;float: right;'>"+data.message+"</dd>");
		    	  }else{
		    		  cavData.messageBox.append("<dt style='float: left;padding: .5em;background: #4733da;display: inline-block;width: 20%;height: 8.5rem;'><img style='width: 100%;height: 100%;' src='/images/warnlogo.png'></dt>");
                      cavData.messageBox.append("<dd style='margin-top: 1.5em;display: inline-block;width: 69%;color: #e8b82a; text-align: center;font-size: 18px;margin-bottom: 1.5em;'>暂无预警！</dd>");
		    	  }
                  cavData.messageBox.append("</dl></div>");
		      }
		      var content=function(){
		    	  cavData.setData();
		    	  if(data.img!="" && data.img!=undefined){
		    	  	window.requestAnimationFrame(content);
				  }
		      }
		      window.requestAnimationFrame(content);
			}
		});
	}();

});