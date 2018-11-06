
layui.config({
    base: '/client/layuiadmin/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名

});

layui.use(["table","form","laytpl","layer","selectTree"], function(){
    let table = layui.table			// 引用layui表格
        ,form = layui.form			// 引用layui表单
        ,laytpl = layui.laytpl		// 引用layui模板引擎
        ,layer = layui.layer		// 引用layui弹出层
        ,selectTree = layui.selectTree
        ,$ = layui.$;   			// 引用layui的jquery


    let titleFmt = d => {
        if(d.disasterColor == 0) return "<span style='color:red;'>" + d.title + "</span>";
        if(d.disasterColor == 1) return "<span style='color:orange;'>" + d.title + "</span>";
        if(d.disasterColor == 2) return "<span style='color:#d4d41e;'>" + d.title + "</span>";
        if(d.disasterColor == 3) return "<span style='color:blue;'>" + d.title + "</span>";
    };

    table.render({
        elem: '#table'
        ,skin: "line"
        , data: [{
            "disasterName":"暴雨"
            ,"disasterColor":0
            ,"title": "华池县气象局2018年10月30日17时38分发布暴雨红色预警信号:测试：发布暴雨红色预警"
            ,"type": "驳回"
            ,"time": "2017-11-10 11:34"
        },{
            "disasterName":"暴雨"
            ,"disasterColor":1
            ,"title": "华池县气象局2018年10月30日17时38分发布暴雨红色预警信号:测试：发布暴雨红色预警"
            ,"type": "驳回"
            ,"time": "2017-11-10 11:34"
        },{
            "disasterName":"暴雨"
            ,"disasterColor":2
            ,"title": "华池县气象局2018年10月30日17时38分发布暴雨红色预警信号:测试：发布暴雨红色预警"
            ,"type": "驳回"
            ,"time": "2017-11-10 11:34"
        },{
            "disasterName":"暴雨"
            ,"disasterColor":3
            ,"title": "华池县气象局2018年10月30日17时38分发布暴雨红色预警信号:测试：发布暴雨红色预警"
            ,"type": "驳回"
            ,"time": "2017-11-10 11:34"
        },{
            "disasterName":"暴雨"
            ,"disasterColor":0
            ,"title": "华池县气象局2018年10月30日17时38分发布暴雨红色预警信号:测试：发布暴雨红色预警"
            ,"type": "驳回"
            ,"time": "2017-11-10 11:34"
        }]
        ,cols: [[ //表头
            {field: 'disasterName', title: '预警名称',width:120}
            , {field: 'title', title: '预警标题', templet: titleFmt}
            , {field: 'type', title: '预警流程', width: 120}
            , {field: 'time', title: '操作时间',width:150}
        ]]
    });


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
         * 初始化7天天气
         * @param param
         */
        ,weekWeatherInfo:(param)=>{
            let option = {
                title: {
                    text: ''
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data:['最高气温','最低气温']
                },
                grid:{
                    bottom: 25
                },
                toolbox: {
                    show: false,
                    feature: {
                        dataZoom: {
                            yAxisIndex: 'none'
                        },
                        dataView: {readOnly: false},
                        magicType: {type: ['line', 'bar']},
                        restore: {},
                        saveAsImage: {}
                    }
                },
                xAxis:  {
                    type: 'category',
                    boundaryGap: false,
                    data: ['周一','周二','周三','周四','周五','周六','周日']
                },
                yAxis: {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value} °C'
                    }
                },
                series: [{
                    name:'最高气温',
                    type:'line',
                    data:[11, 11, 15, 13, 12, 13, 10],
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                }, {
                    name:'最低气温',
                    type:'line',
                    data:[1, -2, 2, 5, 3, 2, 0],
                    markPoint: {
                        data: [
                            // {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
                            {type: 'min', name:"最小值"}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                }]
            };

            let chart = echarts.init(param.id,'walden');
            chart.setOption(option,true);
        }

        /**
         * 预警信息绑定地图上
         */
        ,initWarnInfo : param => {

            //创建图片对象
            let createIcon = (iconUrl)=>{
                return new T.Icon({
                    iconUrl: iconUrl,
                    iconSize: new T.Point(40, 40),
                    iconAnchor: new T.Point(10, 25)
                });
            };

            // // 点击图标显示弹出层信息
            // let content = data => {
            //     return "<div>"
            //         + "<ul style='padding-bottom: 3px;'>预警名称："+data.title+"</ul>"
            //         + "<ul style='padding-bottom: 3px;'>发布地区："+data.areaName+"</ul>"
            //         + "<ul style='padding-bottom: 3px;'>发布机构："+data.organizationName+"</ul>"
            //         + "<ul style='padding-bottom: 3px;'>发布时间："+data.sendTime+"</ul>"
            //         + "<ul style='padding-bottom: 3px;'>预警内容："+data.content+"</ul>"
            //         + "</div>";
            // };
            //
            // // 信息窗口展开事件
            // let openInfo = (content,e) =>{
            //     let point = e.lnglat;	            // 创建标注
            //     let markerInfoWin = new T.InfoWindow(content,{offset:new T.Point(0,-30)}); 	// 创建信息窗口对象
            //     bdMap.openInfoWindow(markerInfoWin,point); //开启信息窗口
            // };
            //
            // // 图标点击事件
            // let addClickHandler = (content,marker)=>{
            //     marker.addEventListener("click",function(e){
            //         openInfo(content,e)
            //     });
            // };

            active.getData({type:"GET", url: "/client/warn/option/home", data:{}}, data => {

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
                    // addClickHandler(content(data[i]),marker);

                });

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

    active.weekWeatherInfo({id:document.getElementById("week-line")});
    active.initWarnInfo(null);
});