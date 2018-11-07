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
        ,url:'/client/unitDanger/select'
        ,even: true
        ,page:true
        ,height: 'full-165'
        ,limits:[5,10,20,50,100]
        ,cols: [[
            {type: 'checkbox'}
            ,{type: 'numbers', title: '编号'}
            ,{field: 'name', title: '危险品场所名称', sort: true}
            ,{field: 'address', title: '地址', sort: true}
            ,{field: 'product', title: '品名', sort: true}
            ,{field: 'tanks', title: '储罐个数及容量', sort: true}
            ,{field: 'machine', title: '加油机台数', sort: true}
            ,{field: 'lightningPeople', title: '企业防雷安全责任人', sort: true}
            ,{field: 'lightningPhone', title: '联系电话', sort: true}
            ,{field: 'testDate', title: '最新检测日期', sort: true}
            ,{field: 'report', title: '最新报告编号', sort: true}
            ,{field: 'status', title: '防雷安全隐患情况', sort: true}
            ,{title: '操&nbsp;&nbsp;作',width: '15%', align:'center', toolbar: '#btnGroupOption'}
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
    form.on('submit(search)', data =>{

    });

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