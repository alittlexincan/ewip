layui.config({
    base: '/client/layuiadmin/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    selectTree: 'selectTree'
    ,zTree: 'zTree'
});

layui.use(['table','form','laytpl','layer', 'selectTree', 'zTree'], function(){
    let table = layui.table			// 引用layui表格
        ,form = layui.form			// 引用layui表单
        ,laytpl = layui.laytpl		// 引用layui模板引擎
        ,layer = layui.layer		// 引用layui弹出层
        ,$ = layui.$       			// 引用layui的jquery
        ,selectTree = layui.selectTree
        ,zTree = layui.zTree;

    /**
     * 加载表格
     */
    table.render({
        id: 'table'
        ,elem: '#table'
        ,url:'/client/user/select'
        ,page:true
        ,height: 'full-180'
        ,limits:[5,10,20,50,100]
        ,cols: [[
            {type: 'checkbox'}
            ,{type: 'numbers', title: '编号'}
            ,{field: 'name', title: '受众名称', sort: true}
            ,{field: 'code', title: '终端号码',sort: true}
            ,{field: 'userGroupName', title: '所属群组', sort: true}
            ,{field: 'areaName', title: '所属地区'}
            ,{field: 'organizationName', title: '所属机构'}
            ,{field: 'channelName', title: '终端类别'}
            ,{title: '操&nbsp;&nbsp;作', width: 150, align:'center', toolbar: '#btnGroupOption'}
        ]]
        ,done:function (res, curr, count) {
            var panelHeight = $(".ewip-panel-right").height() - 7;
            var cardHeight = $(".ewip-panel-left .layui-card .layui-card-header").height();
            $(".ewip-left-tree").height(panelHeight - cardHeight - 20 - 3);
            $(".ewip-left-tree").parent().css("overflow","auto");
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
                name: param == undefined ? '' : param.name
                ,code: param == undefined ? '' : param.code
            }
        });
    };


    /**
     * 自定义验证规则
     */
    form.verify({
        pId: function(value){
            if(value.length == 0) {
                $("#addPId .addPIdShow, #updatePId .updatePIdShow").css("border-color","red");
                return '请选择上级灾种';
            }
        }
        ,type: function (value) {
            if(value.length == 0) return '请选择灾种类型';
        }
        ,name: function (value) {
            if(value.length == 0)  return '请选输入灾种名称';
            if(value.length > 10)  return '渠道手段长度不能大于10';
        }
        ,code: function (value) {
            if(value.length == 0) return '请输入灾种编码';
            if(value.length != 5) return "灾种编码长度是5位";
            var reg = /^[0-9a-zA-Z]+$/;
            if(!reg.test(value)) return '灾种编码必须包含数字和字母';
        }
        ,icon: function(value){
            if(value.length == 0) return '请上传灾种logo';
        }
    });

    /**
     * 群组树点击事件
     * @param event
     * @param treeId
     * @param treeNode
     */
    var userGroupClick = function(event, treeId, treeNode){
        var where = {};
        if(treeNode.type == 1){
            where.userGroupId = null;
            where.organizationId = treeNode.organizationId;
        }else {
            where.organizationId = null;
            where.userGroupId = treeNode.id;
        }
        table.reload('table', {
            page: {
                curr: 1
            },
            where: where
        });
    };
    /**
     * 初始化加载群组树
     */
    var userGroupZtree = zTree.async({
        id: "#organizationGroupTree",
        setting: {
            async:{
                enable:true,
                url: "/client/tree/organization/group",
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
                onClick:userGroupClick
            }
        }
    });


    /**
     * 提交到后台
     * @param option
     */
    let submit = function (option) {
        $.ajax({
            async:option.async
            ,type: option.type
            ,data: option.param
            ,url: option.url
            ,dataType: option.dataType
            ,success: function(json){
                if(option.index != null) layer.close(option.index);
                if(json.code == 200){
                    // 异步刷新地区树
                    userGroupZtree.reAsyncChildNodes(null, "refresh");
                }
                // 弹出提示信息，2s后自动关闭
                layer.msg(json.msg, {time: 2000});
            }
        });

    };

    /**
     * 统一按钮操作对象
     * @type {{addBarBtn: 添加信息, deleteBarBtn: 批量删除信息, deleteOption: 删除单个信息, updateOption: 修改信息}}
     */
    let active = {
        /**
         * 工具条：添加群组信息
         */
        'addBarBtn': function(){
            layer.open({
                type: 1
                ,title: "<i class='layui-icon'>&#xe642;</i> 添加群组信息"
                ,area: ['600px','400px']
                ,shade: 0.3
                ,maxmin:true
                ,offset:'50px'
                ,btn: ['添加', '取消']
                ,content:"<div id='addDiv' style='padding:20px 20px 0 20px'></div>"
                ,success: function(layero,index){
                    // 获取模板，并将数据绑定到模板，然后再弹出层中渲染
                    laytpl(addPop.innerHTML).render([], function(html){
                        // 动态获取弹出层对象并追加html
                        $("#addDiv").empty().append(html);
                        // 初始化下拉树
                        selectTree.render({
                            'id': 'addPId'
                            ,'url': '/client/tree/user/group'
                            ,'isMultiple': false
                        });
                    });
                    // 渲染表单
                    form.render();
                }
                ,yes: function(index, layero){
                    //触发表单按钮点击事件后，立刻监听form表单提交，向后台传参
                    form.on("submit(submitAddBtn)", function(data){
                        // 数据提交到后台，通用方法
                        submit({
                            index: index
                            ,async: 'true'
                            ,url: '/client/user/group/insert'
                            ,type: 'POST'
                            ,param: data.field
                            ,dataType: 'json'
                        });
                    });
                    // 触发表单按钮点击事件
                    $("#submitAddBtn").click();
                }
            });
        }
        /**
         * 工具条：修改群组信息
         * @param obj
         */
        ,'updateBarBtn': function (obj) {
            // 获取灾种树选中节点
            var nodes = userGroupZtree.getCheckedNodes(true);
            if(nodes.length == 0){
                layer.msg('请选中群组进行修改', {time: 2000});
                return false;
            }
            if(nodes.length > 1){
                layer.msg('请选中一个群组进行修改', {time: 2000});
                return false;
            }
            var param = nodes[0];

            //示范一个公告层
            layer.open({
                type: 1
                ,title: "<i class='layui-icon'>&#xe642;</i> 修改群组信息"
                ,area: ['600px','500px']
                ,shade: 0.3
                ,maxmin:true
                ,offset: '50px'
                ,btn: ['修改', '取消']
                ,content:"<div id='updateDiv' style='padding:20px 20px 0 20px'></div>"
                ,success: function(layero,index){
                    // 获取模板，并将数据绑定到模板，然后再弹出层中渲染
                    laytpl(updatePop.innerHTML).render(param, function(html){
                        // 动态获取弹出层对象
                        $("#updateDiv").empty().append(html);
                        // 地区级别下拉框赋值
                        $("#updateDiv select[name='type']").val(param.type);

                        // 初始化下拉树
                        selectTree.render({
                            'id': 'updatePId'
                            ,'url': '/client/tree/user/group'
                            ,'isMultiple': false
                            ,'checkNodeId': param.pId
                        });

                    });
                    form.render();
                }
                ,yes: function(index, layero){
                    //触发表单按钮点击事件后，立刻监听form表单提交，向后台传参
                    form.on("submit(submitUpdateBtn)", function(data){
                        data.field.id = param.id;
                        // 数据提交到后台，通用方法
                        submit({
                            index: index
                            ,async: 'true'
                            ,url: '/client/user/group/update'
                            ,type: 'POST'
                            ,param: data.field
                            ,dataType: 'json'
                        });
                    });
                    // 触发表单按钮点击事件
                    $("#submitUpdateBtn").click();
                }
            });
        }
        /**
         * 工具条：批量删除群组信息
         * @returns {boolean}
         */
        ,'deleteBarBtn': function(){
            // 获取灾种树选中节点
            var nodes = userGroupZtree.getCheckedNodes(true);
            
            if(nodes.length == 0){
                layer.msg('请选中群组进行删除', {time: 2000});
                return false;
            }
            var id = '', count =0, level = -1;
            for(var i = 0; i<nodes.length; i++){
                id += ",'" + nodes[i].id + "'";
                level = nodes[i].level == 0 ? 0 : -1;
                count += nodes[i].children == undefined ? 0 : nodes[i].children.length;
            }
            if(count > 0){
                layer.msg('选中数据存在子节点，请先删除子节点', {time: 2000});
                return false;
            }
            // 判断是否是根节点
            if(level == 0){
                layer.msg('根节点不允许删除', {time: 2000});
                return false;
            }

            layer.confirm('确定删除选中群组？', function(index){
                // 数据提交到后台，通用方法
                submit({
                    index: index
                    ,async: 'true'
                    ,url: '/client/user/group/delete'
                    ,type: 'POST'
                    ,param: {id: id.substring(1)}
                    ,dataType: 'json'
                });
            });
        }
        /**
         * 工具条：批量删除受众信息
         * @returns {boolean}
         */
        ,'deleteUserBarBtn': function(){
            var checkStatus = table.checkStatus('table')
                ,data = checkStatus.data;
            if(data.length == 0){
                layer.msg('请选中列表中数据进行删除', {time: 2000});
                return false;
            }

            var id = '';
            for(var i = 0, len = data.length; i<len; i++){
                id += ",'" + data[i].id + "'";
            }

            layer.confirm('确定删除选中灾种？', function(index){
                // 数据提交到后台，通用方法
                submit({
                    index: index
                    ,async: 'true'
                    ,url: '/client/user/delete'
                    ,type: 'POST'
                    ,param: {id: id.substring(1)}
                    ,dataType: 'json'
                    ,files: []
                });
            });
        }
        /**
         * 工具条：添加受众信息
         */
        ,'addUserBarBtn': function(){
            layer.open({
                type: 1
                ,title: "<i class='layui-icon'>&#xe642;</i> 添加受众信息"
                ,area: ['600px','500px']
                ,shade: 0.3
                ,maxmin:true
                ,offset:'50px'
                ,btn: ['添加', '取消']
                ,content:"<div id='configDiv' style='padding:20px 20px 0 20px'></div>"
                ,success: function(layero,index){
                    // 获取模板，并将数据绑定到模板，然后再弹出层中渲染
                    laytpl(configAddPop.innerHTML).render([], function(html){
                        // 动态获取弹出层对象并追加html
                        $("#configDiv").empty().append(html);
                        // 初始化下拉树
                        selectTree.render({
                            'id': 'addUserPId'
                            ,'url': '/client/tree/user/group'
                            ,'isMultiple': false
                            ,'range':'#configDiv'
                            ,'setData':['type','name','code']
                        });

                        // 监听color颜色单选
                        form.on('radio(color)', function (data) {
                            $('#configDiv input[name="levelName"]').val(disaster.chooseColorToLevel(data.value).name);
                            $('#configDiv input[name="disasterLevel"]').val(data.value);
                        });
                        // 默认选择级别为蓝色四级
                        $('#configDiv input[name="levelName"]').val(disaster.chooseColorToLevel(3).name);
                        $('#configDiv input[name="disasterLevel"]').val(3);

                    });
                    // 渲染表单
                    form.render();
                }
                ,yes: function(index, layero){
                    //触发表单按钮点击事件后，立刻监听form表单提交，向后台传参
                    form.on("submit(submitConfigAddBtn)", function(data){
                        // 数据提交到后台，通用方法
                        submit({
                            index: index
                            ,async: 'true'
                            ,url: '/client/user/insert'
                            ,type: 'POST'
                            ,param: data.field
                            ,dataType: 'json'
                        });
                    });
                    // 触发表单按钮点击事件
                    $("#submitConfigAddBtn").click();
                }
            });
        }

        /**
         * 列表中：删除选中的受众信息
         * @param obj
         */
        ,'deleteOption': function (obj) {
            layer.confirm('确定删除该受众？', function(index){
                obj.del();
                // 数据提交到后台，通用方法
                submit({
                    index: index
                    ,async: 'true'
                    ,url: '/client/user/delete/' + obj.data.id
                    ,type: 'POST'
                    ,param: {fileName: obj.data.icon}
                    ,dataType: 'json'
                });
            });
        }
        /**
         * 列表中：修改受众信息
         * @param obj
         */
        ,'updateOption': function (obj) {
            var param = obj.data;
            //示范一个公告层
            layer.open({
                type: 1
                ,title: "<i class='layui-icon'>&#xe642;</i> 修改受众信息"
                ,area: ['600px','500px']
                ,shade: 0.3
                ,maxmin:true
                ,offset: '50px'
                ,btn: ['修改', '取消']
                ,content:"<div id='updateDiv' style='padding:20px 20px 0 20px'></div>"
                ,success: function(layero,index){
                    // 获取模板，并将数据绑定到模板，然后再弹出层中渲染
                    laytpl(configUpdatePop.innerHTML).render(param, function(html){
                        // 动态获取弹出层对象
                        $("#updateDiv").empty().append(html);
                        // 初始化下拉树
                        selectTree.render({
                            'id': 'updatePId'
                            ,'url': '/client/tree/user/group'
                            ,'isMultiple': false
                            ,'range':'#updateDiv'
                            ,'setData':['type','name','code']
                            ,'checkNodeId': param.pId
                        });

                        $('#updateDiv select[name="type"]').val(param.type);
                        // 灾种颜色赋值
                        $("#updateDiv input[name='disasterColor'][value='"+param.disasterColor+"']").attr("checked",true);
                        // 灾种级别名称赋值
                        $('#updateDiv input[name="levelName"]').val(disaster.chooseColorToLevel(param.disasterLevel).name);
                        // 灾种级别赋值
                        $('#updateDiv input[name="disasterLevel"]').val(param.disasterLevel);

                        // 监听color颜色单选
                        form.on('radio(color)', function (data) {
                            $('#updateDiv input[name="levelName"]').val(disaster.chooseColorToLevel(data.value).name);
                            $('#updateDiv input[name="disasterLevel"]').val(data.value);
                        });
                    });
                    form.render();
                }
                ,yes: function(index, layero){
                    //触发表单按钮点击事件后，立刻监听form表单提交，向后台传参
                    form.on("submit(submitConfigUpdateBtn)", function(data){
                        data.field.id = param.id;
                        // 数据提交到后台，通用方法
                        submit({
                            index: index
                            ,async: 'true'
                            ,url: '/client/user/user'
                            ,type: 'POST'
                            ,param: data.field
                            ,dataType: 'json'
                        });
                    });
                    // 触发表单按钮点击事件
                    $("#submitConfigUpdateBtn").click();
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