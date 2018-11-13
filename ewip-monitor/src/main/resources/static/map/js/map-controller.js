$(function(){
	
	/**
	 * 菜单切换时，删除所有层和JS
	 */
	var removeHtmlAndJs = function(option){
		if(option.isMapShow == true){
			$(".map").show(); 				// 显示地图
		}else{
			$(".map").hide(); 				// 隐藏地图
		}
		$(".warn-message, #warn-message").remove();								// 删除24小时预警
        $(".warn-publish-statistics, #warn-publish-statistics").remove();		// 删除一键式发布统计
        $(".map-network-monitoring, #map-network-monitoring").remove();			// 删除网络监控模块
        $(".map-channel-link, #map-canvas-link, #map-channel-link").remove();	// 删除渠道链路监控模块

		$(".map-warn-home, #map-warn-home").remove();							// 删除辅助决策查询模块
		$(".warn-event-detail, #warn-event-detail").remove();					// 删除事件查询模块

		$(".modular-show").empty();												// 删除导航栏显示模快菜单
		clearInterval(Globel.netInterval);
	};
	
	/**
	 * js加载
	 */
	var loadJS = function(option){
		var data = option.load;
		if(data.length>0){
			removeHtmlAndJs(option); 							// 清空所有HTML和JS
			for(var i = 0; i<data.length; i++){
				var script = document.createElement("script");	// 创建script dom元素
				script.id = data[i].id;							// 给script元素添加id
				script.src= data[i].src;						// 给script元素添加js路径引用
				document.body.appendChild(script);				// 追加到节目body最后
			}
		}
	};
	
	/**
	 * 头部左侧导航：地区选择
	 */
    $("#area-list > a").bind("click", function(){
    	var areaName = $(this).text();
    	var areaId = $(this).data("areaId");
    });
    
    /**
     * 头部左侧导航：顶部地图切换业务图、卫星图
     */
//    $(".nav-map").bind("click",function(){
//    	$(".navbar-nav > li.nav-item").removeClass("active");
//    	$(this).addClass("active");
//    	var type = $(this).data("mapType");
//    	mapCheck(type);
//    });
    

	
	/**
	 * 头部菜单控制跳转：预警信息监控
	 */
	$("#warnMessage").bind("click", function(){
		$(this).parent().parent().children().removeClass("nav-button-active");
		$(this).parent().parent().children().addClass("nav-menu-4");
		if($(this).parent().hasClass("nav-button-active")){
			$(this).parent().removeClass("nav-button-active");
			$(this).parent().addClass("nav-menu-3");
		}else{
			$(this).parent().removeClass("nav-menu-3");
			$(this).parent().addClass("nav-button-active");
		}
	  	//加载资源信息
		loadJS({
			isMapShow: true,
			load: [{id: "warn-message", src: "/map/js/warn-message.js"}]
		});
	});

	/**
	 * 头部菜单控制跳转：事件查询模块控制层
	 */
	$("#warnEventDetail").bind("click", function(){
		$(this).parent().parent().children().removeClass("nav-button-active");
		$(this).parent().parent().children().addClass("nav-menu-4");
		if($(this).parent().hasClass("nav-button-active")){
			$(this).parent().removeClass("nav-button-active");
			$(this).parent().addClass("nav-menu-2");
		}else{
			$(this).parent().removeClass("nav-menu-2");
			$(this).parent().addClass("nav-button-active");
		}
	 	//加载资源信息
		loadJS({
			isMapShow: true,
			load: [{id: "warn-event-detail", src: "/map/js/warn-event-detail.js"}]
		});
	});



    /**
     * 头部菜单控制跳转：一键发布统计模块控制层
     */
    $("#warnPublishSts").bind("click", function(){
        $(this).parent().parent().children().removeClass("nav-button-active");
        $(this).parent().parent().children().addClass("nav-menu-4");
        if($(this).parent().hasClass("nav-button-active")){
            $(this).parent().removeClass("nav-button-active");
            $(this).parent().addClass("nav-menu-2");
        }else{
            $(this).parent().removeClass("nav-menu-2");
            $(this).parent().addClass("nav-button-active");
        }
        //加载资源信息
        loadJS({
            isMapShow: true,
            load: [{id: "warn-publish-statistics", src: "/map/js/warn-publish-statistics.js"}]
        });
    });
	
	
	
	/**
	 * 头部菜单控制跳转：辅助决策模块控制层
	 */
	$("#mapWarnHome").bind("click",function(){
		$(this).parent().parent().children().removeClass("nav-button-active");
		$(this).parent().parent().children().addClass("nav-menu-4");
		if($(this).parent().hasClass("nav-button-active")){
			$(this).parent().removeClass("nav-button-active");
			$(this).parent().addClass("nav-menu-1");
		}else{
			$(this).parent().removeClass("nav-menu-1");
			$(this).parent().addClass("nav-button-active");
		}
		//加载资源信息
		loadJS({
			isMapShow: true,
			load: [{id: "map-warn-home", src: "/map/js/map-warn-home.js"}]
		});
	});
	
	/**
	 * 头部菜单控制跳转：网络监控模块控制层
	 */
	$("#mapNetworkMonitoring").bind("click", function(){
		$(this).parent().parent().children().removeClass("nav-button-active");
		$(this).parent().parent().children().addClass("nav-menu-4");
		if($(this).parent().hasClass("nav-button-active")){
			$(this).parent().removeClass("nav-button-active");
			$(this).parent().addClass("nav-menu-4");
		}else{
			$(this).parent().removeClass("nav-menu-4");
			$(this).parent().addClass("nav-button-active");
		}
	  	//加载资源信息
		loadJS({
			isMapShow: false,
			load: [{id: "map-network-monitoring", src: "/map/js/map-network-monitoring.js"}]
		});
	});

	/**
	 * 头部菜单控制跳转：渠道链路监控模块控制层
	 */
	$("#mapChannelLink").bind("click", function(){
		$(this).parent().parent().children().removeClass("nav-button-active");
		$(this).parent().parent().children().addClass("nav-menu-4");
		if($(this).parent().hasClass("nav-button-active")){
			$(this).parent().removeClass("nav-button-active");
			$(this).parent().addClass("nav-menu-5");
		}else{
			$(this).parent().removeClass("nav-menu-5");
			$(this).parent().addClass("nav-button-active");
		}
		//加载资源信息
		loadJS({
			isMapShow: false,
			load: [
			   {id: "map-canvas-link", src: "/map/js/map-canvas-link.js"},
			   {id: "map-channel-link", src: "/map/js/map-channel-link.js"}

			]
		});
	});
	
	/**
     * 头部右侧侧导航：后台管理入口
     */
    $("#serverManager").bind("click", function(){
        // window.location.href="http://111.40.45.119:9042/client";
        window.location.href="http://localhost:8080/client";
    	// window.location.href=baseUrl+"login/main";
		// window.location.href=baseUrl+"login/load";
    });
    
    
	/**
     * 头部右侧侧导航：退出
     */
    $("#quit").bind("click", function(){
    	window.location.href=baseUrl+"login/load";
    });
    /**
     * 系统登录成功后跳转到 主页，初始化加载辅助决策js文件
     */
    loadJS({
		isMapShow: true,
		load: [{id: "warn-message", src: "/map/js/warn-message.js"}]
	});
});
   