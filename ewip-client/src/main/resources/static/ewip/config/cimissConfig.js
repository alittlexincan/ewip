layui.config({
    base: '/client/layuiadmin/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    selectTree: 'selectTree' //如果 mymod.js 是在根目录，也可以不用设定别名
    ,mod1: 'modules' //相对于上述 base 目录的子目录
});

layui.use(["table","form","laytpl","layer","selectTree"], function(){
    let table = layui.table			// 引用layui表格
        ,form = layui.form			// 引用layui表单
        ,laytpl = layui.laytpl		// 引用layui模板引擎
        ,selectTree = layui.selectTree
        ,layer = layui.layer		// 引用layui弹出层
        ,$ = layui.$;				// 引用layui的jquery

    /**
     * 加载表格
     */
    table.render({
        id: 'table'
        ,elem: '#table'
        ,url:'/client/cimissConfig/select'
        ,page:true
        ,even: true
        ,height: 'full-165'
        ,limits:[10,20,50,100]
        ,cols: [[
            {type: 'checkbox'}
            ,{type: 'numbers', title: '编号'}
            ,{field: 'areaName', title: '所属地区', sort: false}
            ,{field: 'stationId', title: '国家站号', sort: true}
            ,{field: 'stationCode', title: '地区编码', sort: true}
            ,{field: 'url', title: 'ip地址', sort: false}
            ,{field: 'userId', title: '用户名', sort: true}
            ,{field: 'userPwd', title: '密码', sort: false}
            ,{field: 'interfaceId', title: '接口名称', sort: false}
            ,{field: 'elements', title: '要素信息', sort: false}
            ,{title: '操&nbsp;&nbsp;作', width: 200, align:'center', toolbar: '#btnGroupOption'}
        ]]
    });

    /**
     * 初始化下拉树(地区)
     */
    selectTree.render({
        'id': 'searchAreaId'
        ,'url': '/client/tree/area'
        ,'isMultiple': false
        ,'isVerify': false
    });

    /**
     * 自定义验证规则
     */
    form.verify({
        stationId: function(value){
            if(value.length == 0){
                return '国家站号不能为空';
            }
            // 校验员工是否存在
        }
        ,stationCode: function (value) {
            if(value.length == 0){
                return '地区编码不能为空';
            }
        }
        ,interfaceId: function (value) {
            if(value.length == 0){
                return '接口名称不能为空';
            }
        }
        ,elements: function (value) {
            if(value.length == 0){
                return '要素信息不能为空';
            }
        }
        ,userId: function (value) {
            if(value.length == 0){
                return '用户名不能为空';
            }
        }
        ,userPwd: function (value) {
            if(value.length == 0){
                return '密码不能为空';
            }
        }
        ,areaId: function(value){
            if(value.length == 0) {
                $("#addAreaId .addAreaIdShow, #updateAreaId .updateAreaIdShow").css("border-color","red");
                return '请选择所属地区';
            }
        }
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
                areaId: param == undefined ? '' : param.areaId
            }
        });
    };
    /**
     * 数据提交到后台（通用发方法）
     * @param option
     */
    let submitServer = function(option){
        $.ajax({
            async:true
            ,type: option.type
            ,data: option.param
            ,url: option.url
            ,dataType: 'json'
            ,success: function(json){
                if(option.index != null) layer.close(option.index);
                if(json.code == 200){
                    // 刷新列表
                    reloadTable();
                }
                // 弹出提示信息，2s后自动关闭
                layer.msg(json.msg, {time: 2000});
            }
        });
    };

    /**
     * 统一按钮操作对象
     * @type {{addBtn: 添加员工信息, deleteBtn: 批量删除信息, deleteOption: 删除单个员工信息, updateOption: 修改员工信息}}
     */
    let active = {
        /**
         * 工具条：添加员工信息
         */
        'addBarBtn': function(){
            layer.open({
                type: 1
                ,title: "<i class='layui-icon'>&#xe642;</i> 添加Cimiss配置信息"
                ,area: '600px'
                ,shade: 0.3
                ,maxmin:true
                ,offset:'50px'
                ,btn: ['添加', '取消']
                ,content:"<div id='addEmployee' style='padding:20px 20px 0 20px'></div>"
                ,success: function(layero,index){
                    // 获取模板，并将数据绑定到模板，然后再弹出层中渲染
                    laytpl(addPop.innerHTML).render([], function(html){
                        // 动态获取弹出层对象并追加html
                        $("#addEmployee").empty().append(html);
                        // 初始化下拉树(地区)
                        selectTree.render({
                            'id': 'addAreaId'
                            ,'url': '/client/tree/area'
                            ,'isMultiple': false
                        });
                    });
                    form.render();
                }
                ,yes: function(index, layero){
                    //触发表单按钮点击事件后，立刻监听form表单提交，向后台传参
                    form.on("submit(subbmitAddBtn)", function(data){
                        submitServer({
                            index: index
                            ,type: 'POST'
                            ,param: data.field
                            ,url: '/client/cimissConfig/insert'
                        });
                    });
                    // 触发表单按钮点击事件
                    $("#subbmitAddBtn").click();
                }
            });
        }
        /**
         * 工具条：批量删除员工信息
         * @returns {boolean}
         */
        ,'deleteBarBtn': function(){
            var checkStatus = table.checkStatus('table')
                ,data = checkStatus.data;
            if(data.length == 0){
                layer.msg('请选中员工进行删除', {time: 2000});
                return false;
            }
            layer.confirm('确定删除这批用户？', function(index){
                var id = '';
                for(var i = 0, len = data.length; i<len; i++){
                    id += "," + data[i].id;
                }
                // 数据提交到后台，通用方法
                submitServer({
                    index: index
                    ,type: 'POST'
                    ,param: {id: id.substring(1)}
                    ,url: '/client/cimissConfig/delete'
                });
            });
        }
        /**
         * 列表中：删除选中的员工信息
         * @param obj
         */
        ,'deleteOption': function (obj) {
            layer.confirm('确定删除该用户？', function(index){
                obj.del();
                // 数据提交到后台，通用方法
                submitServer({
                    index: index
                    ,param: null
                    ,type: 'DELETE'
                    ,url: '/client/cimissConfig/delete/' + obj.data.id,
                });
            });
        }
        /**
         * 列表中：修改员工信息
         * @param obj
         */
        ,'updateOption': function (obj) {
            let param = obj.data;
            //示范一个公告层
            layer.open({
                type: 1
                ,title: "<i class='layui-icon'>&#xe642;</i> 修改Cimiss配置信息"
                ,area: '500px'
                ,shade: 0.3
                ,maxmin:true
                ,offset: '50px'
                ,btn: ['修改', '取消']
                ,content:"<div id='updateEmployee' style='padding:20px 20px 0 20px'>adsfds</div>"
                ,success: function(layero,index){
                    // 获取模板，并将数据绑定到模板，然后再弹出层中渲染
                    laytpl(updatePop.innerHTML).render(param, function(html){
                        // 动态获取弹出层对象
                        $("#updateEmployee").empty().append(html);
                        // 初始化下拉树(地区)
                        selectTree.render({
                            'id': 'updateAreaId'
                            ,'url': '/client/tree/area'
                            ,'isMultiple': false
                            ,'checkNodeId': param.areaId
                        });
                    });
                    form.render();
                }
                ,yes: function(index, layero){
                    //触发表单按钮点击事件后，立刻监听form表单提交，向后台传参
                    form.on("submit(subbmitUpdateBtn)", function(data){
                        data.field.id = param.id;
                        // 数据提交到后台，通用方法
                        submitServer({
                            index: index
                            ,type: 'POST'
                            ,param: data.field
                            ,url: '/client/cimissConfig/update'
                        });
                    });
                    // 触发表单按钮点击事件
                    $("#subbmitUpdateBtn").click();
                }
            });
        }
    };

    /**
     * 监听头部搜索
     */
    form.on('submit(search)', function(data){
        reloadTable(data.field);
    });

    //监听列表中按钮事件
    table.on('tool(table)', function(obj){
        active[obj.event] ? active[obj.event].call(this, obj) : '';
    });

    // 监控表头工具条按钮事件
    $('.tableBar .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});