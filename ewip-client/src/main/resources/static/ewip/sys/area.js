//
// layui.config({
//     base: '/client/layuiadmin/modules/' //假设这是你存放拓展模块的根目录
// }).extend({ //设定模块别名
//     treeselect: 'treeselect' //如果 mymod.js 是在根目录，也可以不用设定别名
//     ,mod1: 'modules' //相对于上述 base 目录的子目录
// });

layui.use(["table","form","laytpl","layer"], function(){
    let table = layui.table			// 引用layui表格
        ,form = layui.form			// 引用layui表单
        ,laytpl = layui.laytpl		// 引用layui模板引擎
        ,layer = layui.layer		// 引用layui弹出层
        ,$ = layui.$;   			// 引用layui的jquery


    /**
     * 格式化性别
     * @param d
     * @returns {string}
     */
    let levelFormat = function(d){
        if(d.level == 0) return '国家级';
        if(d.level == 1) return '省级';
        if(d.level == 2) return '市级';
        if(d.level == 3) return '县级';
        if(d.level == 4) return '乡镇级';
    };

    /**
     * 加载表格
     */
    table.render({
        id: 'table'
        ,elem: '#table'
        ,url:'/client/area/select'
        ,page:true
        ,height: 'full-200'
        ,limits:[5,10,20,50,100]
        ,cols: [[
            {type: 'checkbox',fixed: 'left'}
            ,{type: 'numbers', title: '编号'}
            ,{field: 'code', title: '地区编码', sort: true}
            ,{field: 'areaName', title: '地区名称', sort: true}
            ,{field: 'parentName', title: '上级地区', sort: true}
            ,{field: 'level', title: '地区级别',sort: true, templet: levelFormat}
            ,{title: '操&nbsp;&nbsp;作', width: 170, align:'center', fixed: 'right', toolbar: '#btnGroupOption'}
        ]]
    });



    /**
     * 自定义验证规则
     */
    form.verify({
        level: function (value) {
            if(value.length == 0) return '请选择地区级别';
        }
        ,pId: function (value) {

            if(value.length == 0) {
                $("#pIdShow").css("border-color","red");
                return '请选择上级地区';
            }
        }
        ,areaName: function(value){
            if(value.length == 0) return '请输入地区名称';
            if(value.length > 20) return '地区名称长度不能超过20位';

        }
        ,code: function (value) {
            if(value.length == 0) return '请输入地区编码';
            if(value.length >= 100000000000 && value.length <= 999999999999) return '地区编码范围值为[100000000000, 999999999999]';
        }
    });

    /**
     * 修改后重新刷新列表，curr: 1重新从第 1 页开始
     */
    let reloadTable = function () {
        table.reload('table', {
            page: {
                curr: 1
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
                ,title: "<i class='layui-icon'>&#xe642;</i> 添加员工信息"
                ,area: ['600px','420px']
                ,shade: 0.3
                ,maxmin:true
                ,offset:'50px'
                ,btn: ['添加', '取消']
                ,content:"<div id='addEmployee' style='padding:20px 20px 0 20px'></div>"
                ,success: function(layero,index){
                    // 获取模板，并将数据绑定到模板，然后再弹出层中渲染
                    laytpl(addEmployeeDiv.innerHTML).render([], function(html){
                        // 动态获取弹出层对象并追加html
                        $("#addEmployee").empty().append(html);

                        $.ajax({
                            async:false
                            ,type: 'POST'
                            ,data: {}
                            ,url: '/client/tree/area'
                            ,dataType: 'json'
                            ,success: function(json){
                                initSelectTree("pId", json.data, false);
                            }
                        });
                    });
                    form.render();
                }
                ,yes: function(index, layero){
                    //触发表单按钮点击事件后，立刻监听form表单提交，向后台传参
                    form.on("submit(subbmitAddBtn)", function(data){
                       /* let pId = data.field.pId;
                        if(pId.length == 0){
                            $("#pId").css({border: '1px solid red;'});
                            layer.msg('<div><i class="layui-icon layui-icon-face-cry" style="font-size: 30px; color: red;float:left;"></i><span>&nbsp;请选择上级地区</span></div>',{
                                anim:6,
                                time: 4000,
                                skin: 'demo-class'
                            });
                            return false;
                        }*/
                        submitServer({
                            index: index
                            ,type: 'POST'
                            ,param: data.field
                            ,url: '/client/area/insert'
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
                    id += ",'" + data[i].id + "'";
                }
                // 数据提交到后台，通用方法
                submitServer({
                    index: index
                    ,type: 'POST'
                    ,param: {id: id.substring(1)}
                    ,url: '/client/area/delete'
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
                    ,url: '/client/area/delete/' + obj.data.id,
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
                ,title: "<i class='layui-icon'>&#xe642;</i> 修改员工信息"
                ,area: '500px'
                ,shade: 0.3
                ,maxmin:true
                ,offset: '50px'
                ,btn: ['修改', '取消']
                ,content:"<div id='updateEmployee' style='padding:20px 20px 0 20px'>adsfds</div>"
                ,success: function(layero,index){
                    // 获取模板，并将数据绑定到模板，然后再弹出层中渲染
                    laytpl(updateEmployeeDiv.innerHTML).render(param, function(html){
                        // 动态获取弹出层对象
                        $("#updateEmployee").empty().append(html);
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
                            ,url: '/client/area/update'
                        });
                    });
                    // 触发表单按钮点击事件
                    $("#subbmitUpdateBtn").click();
                }
            });
        }
    };

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