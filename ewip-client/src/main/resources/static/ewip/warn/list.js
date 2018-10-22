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

    // 预警信息状态：[Alert（首次）,Update（更新）,Cancel（解除）,Ack（确认）,Error（错误）]，目前只采用“Alert”“Update”“Cancel”三个枚举值，其余枚举值保留，暂不使用。
    let warnTypeFormat = (d) => {
        if(d.warnType=="Actual") return "实际";
        if(d.warnType=="Exercise") return "演练";
        if(d.warnType=="Test") return "测试";
        if(d.warnType=="Draft") return "草稿";
    };

    // 预警信息类型：[Actual（实际）,Exercise（演练）,Test（测试）,Draft（草稿）],目前取值仅使用“Actual”和“Test”，其中 “Test”可用于发布测试预警， Exercise和Draft暂不使用
    let msgTypeFormat = (d)=>{
        if(d.msgType=="Alert") return "首次";
        if(d.msgType=="Update") return "更新";
        if(d.msgType=="Cancel") return "解除";
        if(d.msgType=="Ack") return "确认";
        if(d.msgType=="Error") return "错误";
    };

    // 0：未发布；1：已发布；2：解除
    let statusFormat = (d) => {
        if(d.status==0) return "未发布";
        if(d.status==1) return "已发布";
        if(d.status==2) return "以解除";
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
            ,{field: 'sendTime', width:160, title: '发布时间'}
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
     * 自定义验证规则
     */
    form.verify({
        areaId: function(value){
            if(value.length == 0) {
                $("#addAreaId .addAreaIdShow, #updateAreaId .updateAreaIdShow").css("border-color","red");
                return '请选择所属地区';
            }
        }
        ,organizationId: function(value){
            if(value.length == 0) {
                $("#addOrganizationId .addOrganizationIdShow, #updateOrganizationId .updateOrganizationIdShow").css("border-color","red");
                return '请选择所属机构';
            }
        }
        ,channelId: function(value){
            if(value.length == 0) return '请选择发布渠道';
        }
        ,disasterId: function(value){
            if(value.length == 0) {
                $("#addDisasterId .addDisasterIdShow, #updateDisasterId .updateDisasterIdShow").css("border-color","red");
                return '请选择灾种';
            }
        }
        ,content: function (value) {
            if(value.length == 0)  return '请输入预警内容';
            if(value.length > 10)  return '预警内容长度不能大于1000';
        }
        ,measure: function (value) {
            if(value.length == 0)  return '请输入政府应对措施';
            if(value.length > 10)  return '政府应对措施内容长度不能大于1000';
        }
        ,instruction: function (value) {
            if(value.length == 0) return '请输入防御指南';
            if(value.length > 100) return '防御指南长度不能大于1000';
        }
    });

    /**
     * 群组树点击事件
     * @param event
     * @param treeId
     * @param treeNode
     */
    let organizationClick = function(event, treeId, treeNode){
        table.reload('table', {
            page: { curr: 1 },
            where: { organizationId: treeNode.id}
        });
    };
    /**
     * 初始化加载机构树
     */
    zTree.async({
        id: "#organizationTree",
        setting: {
            async:{
                enable:true,
                url: "/client/tree/organization",
                autoParam:["id"],
                dataType:"json",
            },
            check: {
                enable: false,
                chkboxType: {"Y":"", "N": ""},
                chkStyle:"checkbox"
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback:{
                onClick:organizationClick
            }
        }
    });

    /**
     * 统一按钮操作对象
     * @type {{addBarBtn: 添加信息, deleteBarBtn: 批量删除信息, deleteOption: 删除单个信息, updateOption: 修改信息}}
     */
    let active = {
        /**
         * 列表中：删除选中的地区信息
         * @param obj
         */
        'detailOption': (obj) => {
            var index = layer.open({
                title: "<i class='layui-icon layui-icon-form'></i>预警追溯"
                ,type: 2
                ,content: "/client/page/warn/history/" + obj.data.id
                ,success: (layero, index) => {
                    setTimeout( () => {
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
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});