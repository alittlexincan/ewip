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
     * 图片格式化
     * @param d
     * @returns {string}
     */
    let iconFormat = function(d){
        if(d.icon!="" && d.icon!=null) {
            return "<img src='/client/"+d.icon+"'  style='width:50px;height:50px;' >";
        }else{
            return "暂无图片";
        }
    };

    /**
     * 颜色转换
     * @param d
     * @returns {string}
     */
    let colorFormat = function(d){
        return disaster.color(d.disasterColor,"bg");
    };

    /**
     * 级别转换
     * @param d
     * @returns {string}
     */
    let levelFormat = function(d){
        return disaster.level(d.disasterLevel);
    };

    /**
     * 预警信息状态：
     * [Alert（首次）,Update（更新）,Cancel（解除）,Ack（确认）,Error（错误）]，目前只采用“Alert”“Update”“Cancel”三个枚举值，其余枚举值保留，暂不使用。
     * @param d
     * @returns {string}
     */
    let warnTypeFormat = (d) => {
        if(d.warnType=="Actual") return "实际";
        if(d.warnType=="Exercise") return "演练";
        if(d.warnType=="Test") return "测试";
        if(d.warnType=="Draft") return "草稿";
    };

    /**
     * 预警信息类型：
     * [Actual（实际）,Exercise（演练）,Test（测试）,Draft（草稿）],目前取值仅使用“Actual”和“Test”，其中 “Test”可用于发布测试预警， Exercise和Draft暂不使用
     * @param d
     * @returns {string}
     */
    let msgTypeFormat = (d)=>{
        if(d.msgType=="Alert") return "首次";
        if(d.msgType=="Update") return "更新";
        if(d.msgType=="Cancel") return "解除";
        if(d.msgType=="Ack") return "确认";
        if(d.msgType=="Error") return "错误";
    };

    /**
     * 发布状态：
     * 0：未发布；1：已发布；2：解除
     * @param d
     * @returns {string}
     */
    let statusFormat = (d) => {
        if(d.status==0) return "未发布";
        if(d.status==1) return "已发布";
        if(d.status==2) return "以解除";
    };

    /**
     * 流程状态：
     * 0：未发布；1：已发布；2：驳回；3：解除；4：终止
     * @param d
     * @returns {string}
     */
    let flowFormat = d => {
        if(d.flow == 0) return "录入";
        if(d.flow == 1) return "审核";
        if(d.flow == 2) return "签发";
        if(d.flow == 3) return "应急办签发";
        if(d.flow == 4) return "发布"
        if(d.flow == 5) return "保存代发";
        if(d.flow == 6) return "驳回";
        if(d.flow == 7) return "终止";
    };
    /**
     * 加载表格
     */
    table.render({
        id: 'table'
        ,elem: '#table'
        ,url:'/client/warn/edit/select'
        ,page:true
        ,even: true
        ,height: 'full-125'
        ,limits:[5,10,20,50,100]
        ,cols: [[
            {type: 'checkbox'}
            ,{type: 'numbers', title: '编号'}
            ,{field: 'icon', title: '预警图标',width:100, align:'center', sort: true, templet:iconFormat}
            ,{field: 'title', title: '预警标题',width:300, sort: true}
            ,{field: 'disasterName', title: '预警名称', sort: true}
            ,{field: 'disasterColor', title: '预警颜色', sort: true, templet:colorFormat}
            ,{field: 'disasterLevel', title: '预警级别', templet: levelFormat }
            ,{field: 'warnType', width:100, title: '信息类型', sort: true, templet: warnTypeFormat}
            ,{field: 'msgType', width:100, title: '信息状态', sort: true, templet: msgTypeFormat}
            ,{field: 'status',  width:100, title: '发布状态', templet: statusFormat}
            ,{field: 'status',  width:100, title: '流程状态', templet: flowFormat}
            ,{field: 'createTime', width:160, title: '操作时间'}
            ,{title: '操&nbsp;&nbsp;作', width: 150, align:'center', toolbar: '#btnGroupOption'}
        ]]
    });

    /**
     * 修改后重新刷新列表，curr: 1重新从第 1 页开始
     */
    let reloadTable = function (param) {
        console.log(param);
        table.reload('table', {
            page: { curr: 1 },
            where: { //设定异步数据接口的额外参数，任意设
                disasterName: param == undefined ? '' : param.disasterName
                ,disasterColor: param == undefined ? '' : param.disasterColor
                ,disasterLevel: param == undefined ? '' : param.disasterLevel
            }
        });
    };

    /**
     * 统一按钮操作对象
     * @type {{historyOption: historyOption}}
     */
    let active = {
        /**
         * 列表中：删除选中的地区信息
         * @param obj
         */
        historyOption: (obj) => {
            let index = layer.open({
                title: "<i class='layui-icon layui-icon-form'></i>预警追溯"
                ,type: 2
                ,content: "/client/page/warn/history/" + obj.data.id
                ,success: (layero, index) => {
                    setTimeout(() => {
                        layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500);
                }
            });
            layer.full(index);
        }
    };

    /**
     * 监听头部搜索
     */
    form.on('submit(search)', function(data){
        reloadTable(data.field);
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

});