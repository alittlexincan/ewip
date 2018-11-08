layui.config({
    base: '/client/layuiadmin/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    selectTree: 'selectTree'
    ,zTree: 'zTree'
    ,disaster: 'disaster'
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
        ,url:'/client/disasterHistory/select'
        ,even: true
        ,page:true
        ,height: 'full-165'
        ,limits:[5,10,20,50,100]
        ,cols: [[
            {type: 'checkbox'}
            ,{type: 'numbers', title: '编号'}
            ,{field: 'type', title: '灾害类型', sort: true}
            ,{field: 'color', title: '灾害等级',sort: true}
            ,{field: 'level', title: '规模等级', sort: true}
            ,{field: 'damage', title: '危害程度',sort: true}
            ,{field: 'startTime', title: '开始时间', sort: true}
            ,{field: 'endTime', title: '结束时间',sort: true}
            ,{field: 'scale', title: '影响范围', sort: true}
            ,{field: 'monitorOrgan', title: '监管单位',sort: true}
            ,{field: 'contact', title: '联系方式',sort: true}
            //,{title: '操&nbsp;&nbsp;作',width: '15%', align:'center', toolbar: '#btnGroupOption'}
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
                type: param == undefined ? '' : param.type
                ,color: param == undefined ? '' : param.color
                ,level: param == undefined ? '' : param.level
                ,damage: param == undefined ? '' : param.damage
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