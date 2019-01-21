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
        ,url:'/client/unitSchool/select'
        ,even: true
        ,page:true
        ,height: 'full-165'
        ,limits:[5,10,20,50,100]
        ,cols: [[
            {type: 'checkbox'}
            ,{type: 'numbers', title: '编号'}
            ,{field: 'areaName', title: '区县', sort: true}
            ,{field: 'name', title: '名称', sort: true}
            ,{field: 'unit', title: '所属部门',sort: true}
            ,{field: 'type', title: '学校类型',sort: true}
            ,{field: 'area', title: '占地面积（㎡）', sort: true}
            // ,{field: 'people', title: '学校人数', sort: true}
            // ,{field: 'principal', title: '负责人', sort: true}
            // ,{field: 'phone', title: '联系电话', sort: true}
            // ,{field: 'description', title: '学校描述', sort: true}
            // ,{field: 'createUserName', title: '创建人', sort: true}
            // ,{field: 'updateUserName', title: '修改人', sort: true}
            ,{title: '操&nbsp;&nbsp;作',width: '25%', align:'center', toolbar: '#btnGroupOption'}
        ]]
    });


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


    let active = {
        /**
         * 工具条：添加信息
         */
        'addBarBtn': function(){
            layer.open({
                type: 1
                ,title: "<i class='layui-icon'>&#xe642;</i> 添加信息"
                ,area: ['700px','500px']
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
                        // 初始化下拉树(地区)
                        selectTree.render({
                            'id': 'addAreaId'
                            ,'url': '/client/tree/area'
                            ,'isMultiple': false
                            ,clickNode:function (event, treeId, treeNode) {
                                //绑定树操作
                                selectTree.setValue(treeId,treeNode);
                                selectTree.hideTree();
                            }
                        });
                    });
                    // 渲染表单
                    form.render();
                }
                ,yes: function(index, layero){
                    //触发表单按钮点击事件后，立刻监听form表单提交，向后台传参
                    form.on("submit(submitAddBtn)", function(data){
                        console.log(data.field);
                        submitServer({
                            index: index
                            ,type: 'POST'
                            ,param: data.field
                            ,url: '/client/unitSchool/insert'
                        });
                    });
                    // 触发表单按钮点击事件
                    $("#submitAddBtn").click();
                }
            });
        }
        /**
         * 工具条：批量删除信息
         * @returns {boolean}
         */
        ,'deleteBarBtn': function(){
            var checkStatus = table.checkStatus('table')
                ,data = checkStatus.data;
            if(data.length == 0){
                layer.msg('请选中数据进行删除', {time: 2000});
                return false;
            }

            var id = '',count = 0;
            for(var i = 0, len = data.length; i<len; i++){
                id += ",'" + data[i].id + "'";
                count += data[i].child;
            }

            if(count > 0){
                layer.msg('选中数据存在子节点，请先删除子节点', {time: 2000});
                return false;
            }

            layer.confirm('确定删除这批数据？', function(index){
                var id = '';
                for(var i = 0, len = data.length; i<len; i++){
                    id += ",'" + data[i].id + "'";
                }
                // 数据提交到后台，通用方法
                submitServer({
                    index: index
                    ,type: 'POST'
                    ,param: {id: id.substring(1)}
                    ,url: '/client/unitSchool/delete'
                });
            });
        }
        /**
         * 列表中：删除选中的信息
         * @param obj
         */
        ,'deleteOption': function (obj) {
            layer.confirm('确定删除该数据？', function(index){
                obj.del();
                // 数据提交到后台，通用方法
                submitServer({
                    index: index
                    ,param: null
                    ,type: 'DELETE'
                    ,url: '/client/unitSchool/delete/' + obj.data.id,
                });
            });
        }
        /**
         * 列表中：修改信息
         * @param obj
         */
        ,'updateOption': function (obj) {
            let param = obj.data;
            console.log(param);
            //示范一个公告层
            layer.open({
                type: 1
                ,title: "<i class='layui-icon'>&#xe642;</i> 修改信息"
                ,area: '500px'
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
                        // 初始化下拉树(地区)
                        selectTree.render({
                            'id': 'updateAreaId'
                            ,'url': '/client/tree/area'
                            ,'isMultiple': false
                            ,'checkNodeId': param.district
                            ,clickNode:function (event, treeId, treeNode) {
                                //绑定树操作
                                selectTree.setValue(treeId,treeNode);
                                selectTree.hideTree();
                            }
                        });
                        $("select[name='type']").val(param.type);
                    });
                    form.render();
                }
                ,yes: function(index, layero){
                    //触发表单按钮点击事件后，立刻监听form表单提交，向后台传参
                    form.on("submit(submitUpdateBtn)", function(data){
                        data.field.id = param.id;
                        console.log(data.field);

                        if($(".pId").hasClass("layui-hide")){
                            data.field.pId = "";
                        }
                        // 数据提交到后台，通用方法
                        submitServer({
                            index: index
                            ,type: 'POST'
                            ,param: data.field
                            ,url: '/client/unitSchool/update'
                        });
                    });
                    // 触发表单按钮点击事件
                    $("#submitUpdateBtn").click();
                }
            });
        }
        /**
         * 列表中：详细信息
         * @param obj
         */
        ,'detailsOption': function (obj) {
            let param = obj.data;
            console.log(param);
            //示范一个公告层
            layer.open({
                type: 1
                ,title: "<i class='layui-icon'>&#xe642;</i>详细信息"
                ,area: '500px'
                ,shade: 0.3
                ,maxmin:true
                ,offset: '50px'
                ,content:"<div id='detailsDiv' style='padding:20px 20px 0 20px'></div>"
                ,success: function(layero,index){
                    // 获取模板，并将数据绑定到模板，然后再弹出层中渲染
                    laytpl(detailsPop.innerHTML).render(param, function(html){
                        // 动态获取弹出层对象
                        $("#detailsDiv").empty().append(html);
                        // 初始化下拉树(地区)
                        selectTree.render({
                            'id': 'detailsAreaId'
                            ,'url': '/client/tree/area'
                            ,'isMultiple': false
                            ,'checkNodeId': param.district
                            ,clickNode:function (event, treeId, treeNode) {
                                //绑定树操作
                                selectTree.setValue(treeId,treeNode);
                                selectTree.hideTree();
                            }
                        });
                        $("select[name='type']").val(param.type);
                    });
                    form.render();
                }
            });
        }
    };

    /**
     * 自定义验证规则
     */
    form.verify({
        type: function (value) {
            console.log(value);
            if(value.length == 0)  return '请选择类型';
        }
        ,lon: function (value) {
            if(value.length == 0) return '请填写经度';
        }
        ,lat: function (value) {
            if(value.length == 0)  return '请填写纬度';
        }
    });
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
            }
        });
    };
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