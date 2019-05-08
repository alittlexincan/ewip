layui.config({
    base: '/client/layuiadmin/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    selectTree: 'selectTree'
    ,zTree: 'zTree'
    ,disaster: 'disaster'
});

layui.use(['table','form','laytpl','layer', 'ajaxFileUpload'], function(){
    let table = layui.table			// 引用layui表格
        ,form = layui.form			// 引用layui表单
        ,laytpl = layui.laytpl		// 引用layui模板引擎
        ,layer = layui.layer		// 引用layui弹出层
        ,$ = layui.$       			// 引用layui的jquery
        ,ajaxFileUpload = layui.ajaxFileUpload;


    let factoryFormat = function(d){
        if(d.factoryID == "0001" ) return "双顺达";
        if(d.factoryID == "0002" ) return "伍豪科技";
        if(d.factoryID == "0003" ) return "沈阳恒远";
        if(d.factoryID == "0004" ) return "强讯公司";
        if(d.factoryID == "0005" ) return "华泰德丰";
        if(d.factoryID == "0006" ) return "联合远航";
        if(d.factoryID == "0007" ) return "赛乐科技";
        if(d.factoryID == "0008" ) return "瑞彩";
        if(d.factoryID == "0015" ) return "天齐信息";
        if(d.factoryID == "0016" ) return "安徽中科金诚";
        if(d.factoryID == "0017" ) return "深圳昆特";
        if(d.factoryID == "0018" ) return "成都奥天";
        if(d.factoryID == "0019" ) return "河南物理所";
        if(d.factoryID == "0020" ) return "平治东方";
        if(d.factoryID == "0021" ) return "花冠";
        if(d.factoryID == "0022" ) return "畅运";
        if(d.factoryID == "0023" ) return "锦州创安";
        if(d.factoryID == "0024" ) return "电视台";
        if(d.factoryID == "0025" ) return "广播电台";
        if(d.factoryID == "0099" ) return "其他厂家";
    };

    let typeFormat = function(d){
        if(d.clientStyle == 0) return "大喇叭";
        if(d.clientStyle == 1) return "电子屏";
        if(d.clientStyle == 2) return "北斗";
        if(d.clientStyle == 3) return "呼叫中心";
        if(d.clientStyle == 4) return "短信";
        if(d.clientStyle == 5) return "传真";
        if(d.clientStyle == 6) return "邮件";
        if(d.clientStyle == 7) return "电视";
        if(d.clientStyle == 8) return "广播";
        if(d.clientStyle == 9) return "微博";
        if(d.clientStyle == 10) return "微信";
        if(d.clientStyle == 11) return "网站";
        if(d.clientStyle == 12) return "手机客户端";
        if(d.clientStyle == 13) return "海洋广播";
        if(d.clientStyle == 14) return "气象频道";
        if(d.clientStyle == 15) return "预警智能盒子";
        if(d.clientStyle == 99) return "其他设备";
    };

    /**
     * 数据提交到后台（通用发方法）
     * @param option
     */
    let submitFile = function(option){
        ajaxFileUpload.render({
            async: option.async
            ,url : option.url
            ,type: option.type
            ,param : option.param//需要传递的数据 json格式
            ,files : option.files
            ,dataType: 'json'
        },function (json) {
            if(json.code == 200){
                // 刷新列表
                reloadTable();
            }
            // 弹出提示信息，2s后自动关闭
            layer.msg(json.msg, {time: 2000});
        });
    };

    /**
     * 加载表格
     */
    table.render({
        id: 'table'
        ,elem: '#table'
        ,url:'/client/dsInfo/select'
        ,even: true
        ,page:true
        ,height: 'full-165'
        ,limits:[5,10,20,50,100]
        ,cols: [[
            {type: 'checkbox'}
            ,{type: 'numbers', title: '编号'}
            ,{field: 'clientID', title: '终端编号', sort: true}
            ,{field: 'factoryID', title: '厂商编号', sort: true,templet:factoryFormat}
            ,{field: 'factoryServerID', title: '厂商服务器编号', sort: true}
            ,{field: 'clientStyle', title: '终端型号',sort: true,templet:typeFormat}
            ,{field: 'lon', title: '经度', sort: true}
            ,{field: 'lat', title: '纬度',sort: true}
            ,{field: 'clientPerson', title: '终端负责人', sort: true}
            ,{field: 'clientTel', title: '负责人联系方式', sort: true}
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
                            ,url: '/client/dsInfo/insert'
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
                    ,url: '/client/dsInfo/delete'
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
                    ,url: '/client/dsInfo/delete/' + obj.data.id,
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
                ,area: ['700px','500px']
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
                        $("select[name='factoryID']").val(param.factoryID);
                        $("select[name='clientStyle']").val(param.clientStyle);
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
                            ,url: '/client/dsInfo/update'
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
            //示范一个公告层
            layer.open({
                type: 1
                ,title: "<i class='layui-icon'>&#xe642;</i>详细信息"
                ,area: ['700px','500px']
                ,shade: 0.3
                ,maxmin:true
                ,offset: '50px'
                ,content:"<div id='detailsDiv' style='padding:20px 20px 0 20px'></div>"
                ,success: function(layero,index){
                    // 获取模板，并将数据绑定到模板，然后再弹出层中渲染
                    laytpl(detailsPop.innerHTML).render(param, function(html){
                        // 动态获取弹出层对象
                        $("#detailsDiv").empty().append(html);
                        $("select[name='factoryID']").val(param.factoryID);
                        $("select[name='clientStyle']").val(param.clientStyle);
                    });
                    form.render();
                }
            });
        }
        /**
         * 下载模板
         */
        ,'downModel': function () {
            window.location.href="/client/dsInfo/downModel";
        }
        /**
         * 开始导入数据
         */
        ,'importData': function () {
            $("#addFile").click();
        }
    };

    // 选择上传文件
    $("#addFile").change(function (e) {
        //上传文件路径
        var fileName=$(this).val();
        //返回String对象中子字符串最后出现的位置.
        var seat=fileName.lastIndexOf(".");
        //返回位于String对象中指定位置的子字符串并转换为小写.
        var extension=fileName.substring(seat).toLowerCase();
        //判断允许上传的文件格式
        if(extension==".xls" || extension==".xlsx"){
            $("#excelPath").val($(this).val());
            submitFile({
                async: 'true'
                ,url: '/client/dsInfo/importExcel'
                ,type: 'POST'
                ,param: null
                ,files: ['addFile']
                ,dataType: 'json'
            });
        }else{
            layer.msg("上传格式不正确", {time: 1000});
            return false;
        }
    });

    /**
     * 自定义验证规则
     */
    form.verify({
        schoolName: function (value) {
            if(value.length == 0)  return '请填写名称';
        }
        ,schoolType: function (value) {
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
                schoolName: param == undefined ? '' : param.schoolName
                ,schoolType: param == undefined ? '' : param.schoolType
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