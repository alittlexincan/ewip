
layui.config({
    base: '/client/layuiadmin/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    index: "lib/index"
});

layui.use(["index","table","form","laytpl","layer"], function(){
    let table = layui.table			// 引用layui表格
        ,form = layui.form			// 引用layui表单
        ,laytpl = layui.laytpl		// 引用layui模板引擎
        ,layer = layui.layer		// 引用layui弹出层
        ,employee = layui.sessionData("ewip").employee // 当前登录用户信息
        ,$ = layui.$;   			// 引用layui的jquery


    /**
     * 统一函数处理
     * @type {{weekWeatherInfo: weekWeatherInfo}}
     */
    let active = {

        /**
         * 统一访问后台数据
         */
        getData:(param, callback)=>{
            $.ajax({
                async:true,
                type: param.type,
                url: param.url,
                data: param.data,
                dataType: "json",
                success: function(res){
                    callback(res.code == 200 && res.data.length > 0 ? res.data : null);
                }
            });
        }

        /**
         * 初始化预警表格
         * @param param
         *
         */
        ,initTableWarnInfo: param => {

            let titleFmt = d => {
                if(d.disasterColor == 0) return "<span style='color:red;'>" + d.title + "</span>";
                if(d.disasterColor == 1) return "<span style='color:orange;'>" + d.title + "</span>";
                if(d.disasterColor == 2) return "<span style='color:#d4d41e;'>" + d.title + "</span>";
                if(d.disasterColor == 3) return "<span style='color:blue;'>" + d.title + "</span>";
            }
                ,statusFmt = d => {
                if(d.status == 0) return "未发布";
                if(d.status == 1) return "发布";
                if(d.status == 2) return "解除";
            }
                ,msgType = d => {

                if(d.msgType == 'Alert') return "首次";
                if(d.msgType == 'Update') return "更新";
                if(d.msgType == 'Cancel') return "解除";
                if(d.msgType == 'Ack') return "确认";
                if(d.msgType == 'Error') return "错误";

            };

            // 查询发布,解除数据(1:发布，2:解除)
            active.getData({type:"GET", url: "/client/warn/edit/info", data:{limit:5}}, data => {
                $(".home .warn .warn-panel").empty();
                if(data == null){
                    $(".home .warn .warn-panel").append("<span>暂无数据</span>");
                    return;
                }else{
                    $(".home .warn .warn-panel").append("<table class='layui-table' layui-size='sm' lay-filter='table' id='table'></table>");
                }
                table.render({
                    elem: '#table'
                    ,skin: "line"
                    , data: data
                    ,cols: [[ //表头
                        {field: 'disasterName', title: '预警名称',width:120}
                        , {field: 'title', title: '预警标题', templet: titleFmt}
                        , {field: 'msgType', title: '信息类型', templet: msgType}
                        , {field: 'status', title: '预警流程', templet: statusFmt}
                        , {field: 'sendTime', title: '操作时间',width:170}
                    ]]
                });
            });
        }

        ,shikuang: data => {
            $("#maxTem").attr("title", data.TEM_Max + "℃").val(data.TEM_Max + "℃"); // 最高气温
            $("#minTem").attr("title", data.TEM_Min + "℃").val(data.TEM_Min + "℃"); // 最低气温
            $("#pre").attr("title", data.PRE_1h).val(data.PRE_1h + "mm"); // 降水
            $("#rhu").attr("title", data.RHU + "%").val(data.RHU + "%"); // 湿度
            $("#winS").attr("title", data.WIN_S_Avg_2mi + "米/秒").val(data.WIN_S_Avg_2mi + "米/秒"); // 风速
            $("#prs").attr("title", data.PRS + "mm").val(data.PRS + "hpa"); // 气压
        }
        // /**
        //  * 初始化7天天气折线图
        //  * @param param
        //  */
        // ,initWeekWeatherInfo:(param)=>{
        //     let option = {
        //         title: {
        //             text: ''
        //         },
        //         tooltip: {
        //             trigger: 'axis'
        //         },
        //         legend: {
        //             data:['最高气温','最低气温']
        //         },
        //         grid:{
        //             bottom: 25
        //         },
        //         toolbox: {
        //             show: false,
        //             feature: {
        //                 dataZoom: {
        //                     yAxisIndex: 'none'
        //                 },
        //                 dataView: {readOnly: false},
        //                 magicType: {type: ['line', 'bar']},
        //                 restore: {},
        //                 saveAsImage: {}
        //             }
        //         },
        //         xAxis:  {
        //             type: 'category',
        //             boundaryGap: false,
        //             data: ['周一','周二','周三','周四','周五','周六','周日']
        //         },
        //         yAxis: {
        //             type: 'value',
        //             axisLabel: {
        //                 formatter: '{value} °C'
        //             }
        //         },
        //         series: [{
        //             name:'最高气温',
        //             type:'line',
        //             data:[11, 11, 15, 13, 12, 13, 10],
        //             markPoint: {
        //                 data: [
        //                     {type: 'max', name: '最大值'}
        //                 ]
        //             },
        //             markLine: {
        //                 data: [
        //                     {type: 'average', name: '平均值'}
        //                 ]
        //             }
        //         }, {
        //             name:'最低气温',
        //             type:'line',
        //             data:[1, -2, 2, 5, 3, 2, 0],
        //             markPoint: {
        //                 data: [
        //                     // {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
        //                     {type: 'min', name:"最小值"}
        //                 ]
        //             },
        //             markLine: {
        //                 data: [
        //                     {type: 'average', name: '平均值'}
        //                 ]
        //             }
        //         }]
        //     };
        //
        //     let chart = echarts.init(param.id,'walden');
        //     chart.setOption(option,true);
        // }
        /**
         * 预警信息绑定地图上
         */
        ,initMapStationInfo : param => {

            //创建图片对象
            let createIcon = iconUrl =>{
                return new T.Icon({
                    iconUrl: iconUrl
                    ,iconSize: new T.Point(40, 40)
                    ,iconAnchor: new T.Point(20, 40)
                });
            };

            // 点击图标显示弹出层信息
            let content = data => {
                let html ="<div>";
                    html +="    <ul style='padding-bottom: 3px;display: inline-block;'>站点名称："+data.Station_Name+"</ul>";
                    html +="    <ul style='padding-bottom: 3px;" + (data.Type=="red" ? "color:red;":"") + "'>温度："+data.TEM+"</ul>";
                    html +="    <ul style='padding-bottom: 3px;'>湿度："+data.RHU+"</ul>";
                    html +="    <ul style='padding-bottom: 3px;'>风向："+data.WIN_D_Avg_2mi+"</ul>";
                    html +="    <ul style='padding-bottom: 3px;'>风速："+data.WIN_S_Avg_2mi+"</ul>";
                    html +="    <ul style='padding-bottom: 3px;'>气压："+data.PRS+"</ul>";
                    html +="    <ul style='padding-bottom: 3px;" + (data.Type=="red" ? "color:red;":"") + "'>降水量："+data.PRE_1h+"</ul>";
                    if(data.hasOwnProperty('PreMsg')){
                        html +="    <ul style='padding-bottom: 3px;'>温度告警信息：" + data.PreMsg + "</ul>";
                    }
                    if(data.hasOwnProperty('RainMsg')){
                        html +="    <ul style='padding-bottom: 3px;'>降水告警信息：" + data.RainMsg + "</ul>";
                    }
                    html +="</div>";

                return html;
            };

            // 信息窗口展开事件
            let openInfo = (content,e) =>{
                let point = e.lnglat;	            // 创建标注
                let markerInfoWin = new T.InfoWindow(content,{offset:new T.Point(0,-30)}); 	// 创建信息窗口对象
                bdMap.openInfoWindow(markerInfoWin,point); //开启信息窗口
            };

            // 图标点击事件
            let addClickHandler = (content,marker)=>{
                marker.addEventListener("click",function(e){
                    openInfo(content,e)
                });
            };

            // 查询发布,解除数据(1:发布，2:解除)
            active.getData({type:"GET", url: "/client/cimiss/shikuang", data:{}}, data => {
                if(data.code == 500) return;

                let shikuang = null, currentStation = $("stationId").val();

                data.forEach( station => {

                    if(station.Station_Id_C == currentStation){
                        shikuang = station;
                    }
                    let icon = createIcon("/client/images/map/station_"+station.Type+".png");			// 创建图标对象
                    let marker = new T.Marker(new T.LngLat(station.Lon,station.Lat), {icon: icon});  	// 创建标注
                    // 将标注添加到地图中
                    bdMap.addOverLay(marker);
                    // 追加图标点击事件
                    addClickHandler(content(station),marker);
                });

                // 实况信息渲染
                active.shikuang(shikuang);

            });

        }

        /**
         * 预警信息绑定地图上
         */
        ,initMapWarnInfo : param => {

            //创建图片对象
            let createIcon = iconUrl =>{
                return new T.Icon({
                    iconUrl: iconUrl,
                    iconSize: new T.Point(40, 40),
                    iconAnchor: new T.Point(10, 25)
                });
            };

            // 点击图标显示弹出层信息
            let content = data => {
                return "<div>"
                    + "<ul style='padding-bottom: 3px;display: inline-block;'>预警名称："+data.title+"</ul>"
                    + "<ul style='padding-bottom: 3px;'>发布地区："+data.areaName+"</ul>"
                    + "<ul style='padding-bottom: 3px;'>发布机构："+data.organizationName+"</ul>"
                    + "<ul style='padding-bottom: 3px;'>发布时间："+data.sendTime+"</ul>"
                    + "<ul style='padding-bottom: 3px;'>预警内容："+data.content+"</ul>"
                    + "</div>";
            };

            // 信息窗口展开事件
            let openInfo = (content,e) =>{
                let point = e.lnglat;	            // 创建标注
                let markerInfoWin = new T.InfoWindow(content,{offset:new T.Point(0,-30)}); 	// 创建信息窗口对象
                bdMap.openInfoWindow(markerInfoWin,point); //开启信息窗口
            };

            // 图标点击事件
            let addClickHandler = (content,marker)=>{
                marker.addEventListener("click",function(e){
                    openInfo(content,e)
                });
            };
            // 查询发布,解除数据(1:发布，2:解除)
            active.getData({type:"GET", url: "/client/warn/edit/info", data:{status:"1,2"}}, data => {
                if(data == null) return;
                data.forEach( res => {
                    let icon = createIcon(res.icon);						// 创建图标对象
                    let a = Math.floor(Math.random()*10+1);
                    let b = Math.floor(Math.random()*10+1);
                    let c = Math.floor(Math.random()*10+1);
                    let random = (a/100000+b/10000+c/1000).toFixed(5);          //随机数
                    let longitude = (res.longitude * 1 + random * 1).toFixed(5);  //经度添加随机数
                    let latitude = (res.latitude * 1 + random * 1).toFixed(5);    //维度添加随机数
                    let marker = new T.Marker(new T.LngLat(longitude,latitude), {icon: icon});  	// 创建标注
                    // 将标注添加到地图中
                    bdMap.addOverLay(marker);
                    // 追加图标点击事件
                    addClickHandler(content(res),marker);
                });
            });
        }

        // ,panIndex:0
        // ,initPanel: () => {
        //     //当前时间
        //     let time = new Date();
        //     //小时
        //     let min = time.getMinutes(), sec = time.getSeconds();
        //
        //     //下一次报时间隔
        //     let next = ((60 - min) * 60 - sec) * 1000; // 每小时执行一次
        //
        //     //设置下次启动时间
        //     setTimeout(active.initPanel, next);
        //     //整点查询cimiss，因为第一次进来min可能不为0所以要判断
        //     if (min == 0) {
        //         $.ajax({
        //             async:true,
        //             type: "GET",
        //             url: "/client/cimiss/shikuang",
        //             data: {},
        //             dataType: "json",
        //             success: function(res){
        //                 if(res.status==200){
        //                     layer.close(active.panIndex);
        //                     layer.open({
        //                         type: 1
        //                         ,title: "<i class='layui-icon layui-icon-edit'></i> 告警提醒"
        //                         ,area: '300px'
        //                         ,shade: 0
        //                         ,anim: 2
        //                         ,maxmin:false
        //                         ,offset: 'rb'
        //                         ,content:"<div id='addDiv' style='padding:20px 20px 0 20px'>"+res.msg+"</div>"
        //                         ,success: function(layero,index){
        //                             active.panIndex = index;
        //                         }
        //                     });
        //                 }
        //             }
        //         });
        //     }
        // }
        /**
         *初始化消息提醒
         */
        ,initMesRemind: () =>{
            let level = employee.level, data={
                level:level,
                areaId: employee.areaId,
                organizationId: employee.organizationId
            };
            $.ajax({
                async:true,
                type: "GET",
                url: 'warn/option/selectWarn',
                data: data,
                dataType: "json",
                success: function(data){
                    if(data.length>0){
                        $(".mesRemind li span").remove();
                        for(let i=0;i<data.length;i++){
                            let flow=data[i].flow, count=data[i].count;
                            if(level==2){
                                if(flow==1){
                                    if(count!=0){
                                        $(".mesRemind li:nth-child(1) a").after("<span>"+count+"</span>");
                                    }
                                }else if(flow==3){
                                    $(".mesRemind li:nth-child(2)").empty().append(" <a href='page/warn/emergency'><i class='layui-icon layui-icon-edit'></i><cite>待签发</cite></a>")
                                    $(".mesRemind li:nth-child(2) a").after("<span>"+count+"</span>");
                                    if(count!=0){
                                        $(".mesRemind li:nth-child(2) a").after("<span>"+count+"</span>");
                                    }
                                }else if(flow==4){
                                    if(count!=0){
                                        $(".mesRemind li:nth-child(3) a").after("<span>"+count+"</span>");
                                    }
                                }else if(flow==6){
                                    if(count!=0){
                                        $(".mesRemind li:nth-child(4) a").after("<span>"+count+"</span>");
                                    }
                                }
                            }else{
                                if(flow==1){
                                    if(count!=0){
                                        $(".mesRemind li:nth-child(1) a").after("<span>"+count+"</span>");
                                    }
                                }else if(flow==2){
                                    $(".mesRemind li:nth-child(2)").empty().append("<a href='page/warn/issue'><i class='layui-icon layui-icon-edit'></i><cite>待签发</cite></a>")
                                    if(count!=0){
                                        $(".mesRemind li:nth-child(2) a").after("<span>"+count+"</span>");
                                    }
                                }else if(flow==4){
                                    if(count!=0){
                                        $(".mesRemind li:nth-child(3) a").after("<span>"+count+"</span>");
                                    }
                                }else if(flow==6){
                                    if(count!=0){
                                        $(".mesRemind li:nth-child(4) a").after("<span>"+count+"</span>");
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    };

    /**
     * 添加时，如果选择的是省级，则隐藏上级地区
     */
    form.on('select(level)', function(data){
        if(data.value == 1){
            $(".pId").addClass("layui-hide");
        }else{
            $(".pId").removeClass("layui-hide");
        }
    });

    /**
     * 监听列表中按钮事件
     */
    table.on('tool(table)', function(obj){
        active[obj.event] ? active[obj.event].call(this, obj) : '';
    });

    /**
     * 监控表头工具条按钮事件
     */
    $('.tableBar .layui-btn').on('click', function(){
        let type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    // 初始化加载地图预警信息
    active.initTableWarnInfo(null);

    // 初始化加载地图预警信息
    active.initMapWarnInfo(null);

    // 初始化加载地图站点信息
    active.initMapStationInfo(null);

    //初始化查询预警提醒个数
    active.initMesRemind();//定时器
    setInterval(active.initMesRemind(), 10000);
});