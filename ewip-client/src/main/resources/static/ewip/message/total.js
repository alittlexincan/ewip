
layui.config({
    base: '/client/layuiadmin/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    zTree: '/modules/zTree'
    ,disaster: '/modules/disaster'
});


layui.use(['table','form','element','zTree','laydate' , 'disaster'], function(){
    let table = layui.table			// 引用layui表格
        ,form = layui.form			// 引用layui表单
        ,$ = layui.$   			    // 引用layui的jquery
        ,element = layui.element
        ,zTree = layui.zTree
        ,laydate = layui.laydate
        ,disaster = layui.disaster;


    var active = {

        /**
         * 统计发布渠道占比饼状图
         */
        "channelPieTotal":(id)=>{
            var option = {
                title : {
                    text: '业务类型发布比率统计',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} %"
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: false, readOnly: false},// 数据按钮
                        restore : {show: false},// 刷新按钮
                        saveAsImage : {show: false},// 保存按钮
                        magicType : {
                            show: true,
                            type: ['pie', 'funnel'],
                            option: {
                                funnel: {
                                    x: '25%',
                                    width: '50%',
                                    funnelAlign: 'left',
                                    max: 1548
                                }
                            }
                        }
                    }
                },
                calculable : true,
                noDataLoadingOption:{
                    effect:"bubble",
                    text:"暂无数据",
                    effectOption:{
                        effect:{
                            n:0
                        }
                    },
                    textStyle:{
                        fontSize:32,
                        fontWeight:'bold'
                    }
                },
                series : [{
                    name:'业务类型',
                    type:'pie',
                    radius : '55%',//饼图的半径大小
                    center: ['50%', '50%'],//饼图的位置
                    data:[
                        {value:30,name:'高圆圆'},
                        {value:26,name:'赵丽颖'},
                        {value:24,name:'江莱'}
                    ]
                }]
            };
            var pie = echarts.init(id,'walden');
            pie.setOption(option);
        }

        /**
         * 统计渠道发布成功树柱状图
         */
        ,"channelColumnTotal": (id) => {
            var option = {
                title : {
                    text: '渠道发布统计',
                },
                legend: {
                    data:['总数','成功数']
                },
                toolbox: {
                    show : true,
                    feature : {
                        dataView : {show: false, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: false},
                        saveAsImage : {show: false}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : ['短信','APP','大喇叭','显示屏','微博','微信','网站','邮件','传真','电视','广播','北斗']
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'总数',
                        type:'bar',
                        data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
                    },
                    {
                        name:'成功数',
                        type:'bar',
                        data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
                    }
                ]
            };
            var pie = echarts.init(id,'walden');
            pie.setOption(option);
        }
    };


    /**
     * 初始化发布时间
     */
    laydate.render({
        elem: '#startTime'
        ,type: 'date'
        ,theme: 'molv'
        ,value: new Date()
        ,format: 'yyyy-MM-dd'
    });
    /**
     * 初始化预计发生时间
     */
    laydate.render({
        elem: '#endTime'
        ,type: 'date'
        ,value: new Date()
        ,theme: 'molv'
        ,format: 'yyyy-MM-dd'
    });


    active.channelPieTotal(document.getElementById("pie"));
    active.channelColumnTotal(document.getElementById("column"));



});