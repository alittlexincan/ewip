layui.config({
    base: '/client/layuiadmin/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    selectTree: 'selectTree'
    ,zTree: 'zTree'
    ,disaster: 'disaster'
});

layui.use(['table','form','laytpl','layer', 'selectTree', 'zTree', 'disaster', 'laydate', 'ajaxFileUpload'], function(){
    let table = layui.table			// 引用layui表格
        ,form = layui.form			// 引用layui表单
        ,laytpl = layui.laytpl		// 引用layui模板引擎
        ,layer = layui.layer		// 引用layui弹出层
        ,$ = layui.$       			// 引用layui的jquery
        ,selectTree = layui.selectTree
        ,zTree = layui.zTree
        ,disaster = layui.disaster
        ,laydate = layui.laydate
        ,ajaxFileUpload = layui.ajaxFileUpload;


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
            ,{field: 'areaName', title: '地区', sort: true}
            ,{field: 'disasterType', title: '灾害类别', sort: true}
            ,{field: 'disasterName', title: '灾害名称',sort: true}
            ,{field: 'color', title: '颜色',sort: true}
            ,{field: 'level', title: '级别', sort: true}
            ,{field: 'damage', title: '危害程度', sort: true}
            ,{field: 'startTime', title: '开始时间', sort: true}
            ,{field: 'endTime', title: '结束时间',sort: true}
            ,{title: '操&nbsp;&nbsp;作',width: '20%', align:'center', toolbar: '#btnGroupOption'}
        ]]
    });

    /**
     * 数据提交到后台（通用发方法）
     * @param option
     */
    let submitServer = function(option){
        console.log(option);
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
            if(option.index != null) layer.close(option.index);
            if(json.code == 200){
                // 刷新列表
                reloadTable();
            }
            // 弹出提示信息，2s后自动关闭
            layer.msg(json.msg, {time: 2000});
        });
    };

    /**
     * 判断上传文件格式是否正确
     * @param flag
     * @param fileName
     * @returns {boolean}
     */
    let checkFile =function (flag,fileName){
        //返回String对象中子字符串最后出现的位置.
        var seat=fileName.lastIndexOf(".");
        //返回位于String对象中指定位置的子字符串并转换为小写.
        var extension=fileName.substring(seat).toLowerCase();
        //判断允许上传的文件格式
        //flag含义，1：音频，2：图片，3：视频
        if(flag==1){
            if(extension==".mp3" || extension==".wma"|| extension==".wav"|| extension==".ogg"){
                $("input[name='audio']").val(fileName);
            }else{
                layer.msg("上传音频格式不正确", {time: 1000});
                return false;
            }
        }else if(flag==2){
            if(extension==".jpg"|| extension==".jpeg"|| extension==".gif"|| extension==".png"|| extension==".bmp"){
                $("input[name='picture']").val(fileName);
            }else{
                layer.msg("上传图片格式不正确", {time: 1000});
                return false;
            }
        }else{
            if(extension==".mp4"|| extension==".avi"|| extension==".rm"|| extension==".flv"|| extension==".mov"
                &&extension==".rmvb"|| extension==".mpg"|| extension==".mkv"|| extension==".wmv"|| extension==".3gp"){
                $("input[name='video']").val(fileName);
            }else{
                layer.msg("上传视频格式不正确", {time: 1000});
                return false;
            }
        }
    }


    let active = {
        /**
         * 工具条：添加信息
         */
        'addBarBtn': function(){
            layer.open({
                type: 1
                ,title: "<i class='layui-icon'>&#xe642;</i> 添加信息"
                ,area: ['1100px','500px']
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
                        /**
                         * 初始化发布时间
                         */
                        laydate.render({
                            elem: '#startTime'
                            ,value: new Date()
                            ,format: 'yyyy-MM-dd HH:mm:ss'
                        });

                        /**
                         * 初始化发布时间
                         */
                        laydate.render({
                            elem: '#endTime'
                            ,value: new Date()
                            ,format: 'yyyy-MM-dd HH:mm:ss'
                        });

                    });
                    // 点击上传按钮，触发上传音频按钮
                    $('#uploadAudioBtn').on('click', function(){
                        $("#addAudio").click();
                    });
                    $("#addAudio").change(function (e) {
                        checkFile(1,$(this).val());

                    });
                    // 点击上传按钮，触发上传图片按钮
                    $('#uploadPictureBtn').on('click', function(){
                        $("#addPicture").click();
                    });
                    $("#addPicture").change(function (e) {
                        checkFile(2,$(this).val());
                    });

                    // 点击上传按钮，触发上传视频按钮
                    $('#uploadVideoBtn').on('click', function(){
                        $("#addVideo").click();
                    });
                    $("#addVideo").change(function (e) {
                        checkFile(3,$(this).val());
                    });
                    // 渲染表单
                    form.render();
                }
                ,yes: function(index, layero){
                    //触发表单按钮点击事件后，立刻监听form表单提交，向后台传参
                    form.on("submit(submitAddBtn)", function(data){
                        submitFile({
                            index: index
                            ,async: 'true'
                            ,url: '/client/disasterHistory/insert'
                            ,type: 'POST'
                            ,param: data.field
                            ,files: ['addAudio','addPicture','addVideo']
                            ,dataType: 'json'
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
                layer.msg('请选中进行删除', {time: 2000});
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
                    ,url: '/client/disasterHistory/delete'
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
                    ,url: '/client/disasterHistory/delete/' + obj.data.id,
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
                ,area: ['1100px','500px']
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
                            ,'checkNodeId': param.reportArea
                            ,clickNode:function (event, treeId, treeNode) {
                                //绑定树操作
                                selectTree.setValue(treeId,treeNode);
                                selectTree.hideTree();
                            }
                        });

                        $("#updateDiv select[name='severity']").val(param.severity);
                        $("#updateDiv select[name='color']").val(param.color);
                        $("#updateDiv select[name='level']").val(param.level);
                        $("#updateDiv select[name='damage']").val(param.damage);
                    });
                    /**
                     * 初始化发布时间
                     */
                    laydate.render({
                        elem: '#updateStartTime'
                        ,value: param.startTime
                        ,format: 'yyyy-MM-dd HH:mm:ss'
                    });
                    /**
                     * 初始化发布时间
                     */
                    laydate.render({
                        elem: '#updateEndTime'
                        ,value: param.endTime
                        ,format: 'yyyy-MM-dd HH:mm:ss'
                    });

                    // 点击上传按钮，触发上传音频按钮
                    $('#updateAudioBtn').on('click', function(){
                        $("#updateAudio").click();
                    });
                    $("#updateAudio").change(function (e) {
                        checkFile(1,$(this).val());
                    });
                    // 点击上传按钮，触发上传图片按钮
                    $('#updatePictureBtn').on('click', function(){
                        $("#updatePicture").click();
                    });
                    $("#updatePicture").change(function (e) {
                        checkFile(2,$(this).val());
                    });

                    // 点击上传按钮，触发上传视频按钮
                    $('#updateVideoBtn').on('click', function(){
                        $("#updateVideo").click();
                    });
                    $("#updateVideo").change(function (e) {
                        checkFile(3,$(this).val());
                    });
                    form.render();
                }
                ,yes: function(index, layero){
                    //触发表单按钮点击事件后，立刻监听form表单提交，向后台传参
                    form.on("submit(submitUpdateBtn)", function(data){
                        data.field.id = param.id;
                        submitFile({
                            index: index
                            ,async: 'true'
                            ,url: '/client/disasterHistory/update'
                            ,type: 'POST'
                            ,param: data.field
                            ,files: ['updateAudio','updatePicture','updateVideo']
                            ,dataType: 'json'
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
        ,'detailOption': function (obj) {
        let param = obj.data;
        //示范一个公告层
        layer.open({
            type: 1
            ,title: "<i class='layui-icon'>&#xe642;</i>详细信息"
            ,area: ['1100px','600px']
            ,shade: 0.3
            ,maxmin:true
            ,content:"<div id='detailDiv' style='padding:20px 20px 0 20px'></div>"
            ,success: function(layero,index){
                // 获取模板，并将数据绑定到模板，然后再弹出层中渲染
                laytpl(detailPop.innerHTML).render(param, function(html){
                    // 动态获取弹出层对象

                    $("#detailDiv").empty().append(html);
                    // 初始化下拉树(地区)

                    selectTree.render({
                        'id': 'detailAreaId'
                        ,'url': '/client/tree/area'
                        ,'isMultiple': false
                        ,'checkNodeId': param.reportArea
                        ,clickNode:function (event, treeId, treeNode) {
                            //绑定树操作
                            selectTree.setValue(treeId,treeNode);
                            selectTree.hideTree();
                        }
                    });

                    $("#detailDiv select[name='severity']").val(param.severity);
                    $("#detailDiv select[name='color']").val(param.color);
                    $("#detailDiv select[name='level']").val(param.level);
                    $("#detailDiv select[name='damage']").val(param.damage);
                });
                /**
                 * 点击音频播放按钮
                 */
                $('#openPicture').on('click', function(){
                    layer.open({
                        type: 1,
                        title: false,
                        closeBtn: 1,
                        shadeClose: true,
                        area: ['500px', '500px'], //宽高
                        content: "<img alt='图片' title='图片' src='/client"+param.picture+"' style='width:500px;height: 500px;' />"
                    });
                });
                /**
                 * 点击视频播放按钮
                 */
                $('#videoPlay').on('click', function(){
                    layer.open({
                        type: 1,
                        title: false,
                        closeBtn: 1,
                        shadeClose: true,
                        area: ['500px', '500px'], //宽高
                        content: "<video width='500' height='500' controls='controls' autoplay='autoplay'>" +
                            "<source src='/client"+param.video+"' type='video/ogg' />" +
                            "<source src='/client"+param.video+"' type='video/mp4' />" +
                            "<source src='/client"+param.video+"' type='video/webm' />" +
                            "</video>"
                    });
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
        lon: function (value) {
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
                disasterType: param == undefined ? '' : param.disasterType
                ,disasterName: param == undefined ? '' : param.disasterName
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