layui.config({
    base: '/client/layuiadmin/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    selectTree: 'selectTree'
    ,zTree: 'zTree'
});

layui.use(['table','form','laytpl','layer', 'selectTree', 'zTree', 'disaster'], function(){
    let table = layui.table			// 引用layui表格
        ,form = layui.form			// 引用layui表单
        ,laytpl = layui.laytpl		// 引用layui模板引擎
        ,layer = layui.layer		// 引用layui弹出层
        ,$ = layui.$       			// 引用layui的jquery
        ,selectTree = layui.selectTree
        ,zTree = layui.zTree
        ,disaster = layui.disaster;


    /**
     * 加载表格
     */
    table.render({
        id: 'table'
        ,elem: '#table'
        ,url:'/client/riskGeologic/select'
        ,even: true
        ,page:true
        ,height: 'full-165'
        ,limits:[5,10,20,50,100]
        ,cols: [[
            {type: 'checkbox'}
            ,{type: 'numbers', title: '编号'}
            ,{field: 'name', title: '隐患点名称'}
            ,{field: 'district', title: '区县'}
            ,{field: 'street', title: '镇街道办'}
            ,{field: 'type', title: '灾害类型'}
            ,{field: 'rock', title: '岩土成因'}
            ,{field: 'scale', title: '灾害规模'}
            ,{field: 'stability', title: '稳定性'}
            ,{field: 'economicLoss', title: '直接经济损失'}
            ,{field: 'threadPeople', title: '威胁人口'}
            ,{field: 'threadProperty', title: '威胁资产'}
            ,{field: 'weatherCauses', title: '气象致灾因子'}
            ,{field: 'prec24', title: '过去24小时雨量'}
            ,{field: 'prec1', title: '过去1小时雨量'}
           // ,{title: '操&nbsp;&nbsp;作',width: '15%', align:'center', toolbar: '#btnGroupOption'}
        ]]
    });


    let active = {

        updateOption: param => {
            console.log(param);
        }

    };
    /**
     * 监听头部搜索
     */
    form.on('submit(search)', function(data){
        reloadTable(data.field);
    });
    /**
     * 修改后重新刷新列表，curr: 1重新从第 1 页开始
     */
    let reloadTable = function (param) {
        table.reload('table', {
            page: {
                curr: 1
            },
            where: { //设定异步数据接口的额外参数，任意设
                name: param == undefined ? '' : param.name
                ,type: param == undefined ? '' : param.type
                ,scale: param == undefined ? '' : param.scale
                ,stability: param == undefined ? '' : param.stability
            }
        });
    };

    /**
     * 监听列表中按钮事件
     */
    table.on('tool(table)', obj => {
        active[obj.event] ? active[obj.event].call(this, obj) : '';
    });

    /**
     * 监控表头工具条按钮事件
     */
    $('.tableBar .layui-btn').on('click', () =>{
        let type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});