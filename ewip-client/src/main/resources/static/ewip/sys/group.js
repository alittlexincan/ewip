layui.config({
    base: '/client/layuiadmin/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    selectTree: 'selectTree'
    ,zTree: 'zTree'
});

layui.use(['table','form','laytpl','layer', 'selectTree', 'zTree','ajaxFileUpload'], function(){
    let table = layui.table			// 引用layui表格
        ,form = layui.form			// 引用layui表单
        ,laytpl = layui.laytpl		// 引用layui模板引擎
        ,layer = layui.layer		// 引用layui弹出层
        ,$ = layui.$       			// 引用layui的jquery
        ,selectTree = layui.selectTree
        ,zTree = layui.zTree
        ,ajaxFileUpload = layui.ajaxFileUpload
        ,employee = layui.sessionData("ewip").employee; // 当前登录用户信息

    let typeFormat = function(d){
        return d.type == 1? '责任人群组' : '基层防御群组';
    };

    /**
     * 加载表格
     */
    table.render({
        id: 'table'
        ,elem: '#table'
        ,url:'/client/group/select'
        ,page:true
        ,even: true
        ,height: 'full-165'
        ,limits:[10,20,50,100]
        ,cols: [[
            {type: 'checkbox'}
            ,{type: 'numbers', title: '编号'}
            ,{field: 'name', title: '群组名称', sort: true}
            ,{field: 'channelName', title: '所属渠道'}
            ,{field: 'areaName', title: '所属地区'}
            ,{field: 'organizationName', title: '所属机构'}
            ,{field: 'type', title: '类型' , templet: typeFormat}
            ,{title: '操&nbsp;&nbsp;作', width: 200, align:'center', toolbar: '#btnGroupOption'}
        ]]
        ,done:function (res, curr, count) {
            var panelHeight = $(".ewip-panel-right").height();
            var cardHeight = $(".ewip-panel-left .layui-card .layui-card-header").height();
            $(".ewip-left-tree").height(panelHeight - cardHeight - 20 - 2);
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
                ,areaId: param == undefined ? '' : param.areaId
                ,organizationId: param == undefined ? '' : param.organizationId
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
            if(json.code == 200){
                // 刷新列表
                reloadTable();
            }
            // 弹出提示信息，2s后自动关闭
            layer.msg(json.msg, {time: 2000});
        });
    };


    /**
     * 查询渠道手段下拉列表
     * @param callback
     */
    let selectChannel = function(callback){
        $.ajax({
            async:true
            ,type: "POST"
            ,data: {type:0} // 0：表示渠道
            ,url: "/client/channel/list"
            ,dataType: 'json'
            ,success: function(json){
                callback(json.data.length > 0 ? json.data : null);
            }
        });
    };

    /**
     * 初始化下拉树(机构)
     */
    // selectTree.render({
    //     'id': 'searchOrganizationId'
    //     ,'url': '/client/tree/organization'
    //     ,'isMultiple': false
    //     ,'isVerify': false
    // });

    /**
     * 初始化地区树
     */
    selectTree.render({
        'id': 'searchAreaId'
        ,'url': '/client/tree/area'
        ,'isMultiple': false
        ,'isVerify': false
        ,clickNode:function (event, treeId, treeNode) {
            let areaId = treeNode.id;
            initOrg(0,areaId);
            //绑定树操作
            selectTree.setValue(treeId,treeNode);
            selectTree.hideTree();
        }
    });


    /**
     * 初始化下拉机构列表
     * @param param
     */
    let initOrg=function(flag,param){
        $.ajax({
            async:false
            ,type: "POST"
            ,data: {areaId:param}
            ,url: "/client/organization/selectOrg"
            ,dataType: 'json'
            ,success: function(json){
                let list=json.list;
                var html="";
                    html +="<option value=''>直接选择或搜索选择</option>";
                if(list.length>0){
                    for(var i=0;i<list.length;i++){
                        html +="<option value='"+list[i].id+"'>"+list[i].organizationName+"</option>";
                    }
                    if(flag==0){
                        $("#searchOrganizationId").empty().append(html);
                    }else if(flag==1){
                        $("#addOrganizationId").empty().append(html);
                    }else{
                        $("#updateOrganizationId").empty().append(html);
                        $("#detailsOrganizationId").empty().append(html);
                    }
                }
                form.render();
            }
        });
    }


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
        ,channelId: function (value) {
            if(value.length == 0) return '请选择所属渠道';
        }
        ,name: function (value) {
            if(value.length == 0)  return '请选输入群组名称';
            if(value.length > 50)  return '群组名称长度不能大于50';
        }
        // ,type: function (value) {
        //     if(value.length == 0) return '请选择灾种类型';
        // }
        // ,code: function (value) {
        //     if(value.length == 0) return '请输入灾种编码';
        //     if(value.length != 5) return "灾种编码长度是5位";
        //     var reg = /^[0-9a-zA-Z]+$/;
        //     if(!reg.test(value)) return '灾种编码必须包含数字和字母';
        // }
        // ,icon: function(value){
        //     if(value.length == 0) return '请上传灾种logo';
        // }
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
            where.id = null;
            where.organizationId = treeNode.id;
        }else {
            where.organizationId = null;
            where.id = treeNode.id;
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
    let userGroupZtree =function() {
        zTree.async({
            id: "#organizationGroupTree",
            setting: {
                async: {
                    enable: true,
                    url: "/client/tree/organization/group",
                    autoParam: ["id"],
                    dataType: "json",
                },
                view: {
                    selectedMulti: false, //是否允许多选
                    fontCss: getFontCss
                },
                check: {
                    enable: false,
                    chkboxType: {"Y": "", "N": ""},
                    chkStyle: "checkbox"
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onClick: userGroupClick
                }
            }
        })
    };




    /**
     * 给树节点标红
     * @param treeId
     * @param treeNode
     * @returns {*}
     */
    let getFontCss= function(treeId, treeNode) {
        return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
    }

    /**
     * 高亮显示并展示【指定节点s】
     * @param treeId
     * @param highlightNodes 需要高亮显示的节点数组
     * @param flag             需要高亮显示的节点标识
     */
    let highlightAndExpand_ztree= function (treeId, highlightNodes, flag){
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
        //<1>. 先把全部节点更新为普通样式
        var treeNodes = treeObj.transformToArray(treeObj.getNodes());
        for (var i = 0; i < treeNodes.length; i++) {
            treeNodes[i].highlight = false;
            treeObj.updateNode(treeNodes[i]);
        }
        //<2>.收起树, 只展开根节点下的一级节点
        close_ztree(treeId);
        //<3>.把指定节点的样式更新为高亮显示，并展开
        if (highlightNodes != null) {
            for (var i = 0; i < highlightNodes.length; i++) {
                if (flag != null && flag != "") {
                    if (highlightNodes[i].flag == flag) {
                        //高亮显示节点，并展开
                        highlightNodes[i].highlight = true;
                        treeObj.updateNode(highlightNodes[i]);
                        //高亮显示节点的父节点的父节点....直到根节点，并展示
                        var parentNode = highlightNodes[i].getParentNode();
                        var parentNodes = getParentNodes_ztree(treeId, parentNode);
                        treeObj.expandNode(parentNodes, true, false, true);
                        treeObj.expandNode(parentNode, true, false, true);
                    }
                } else {
                    //高亮显示节点，并展开
                    highlightNodes[i].highlight = true;
                    treeObj.updateNode(highlightNodes[i]);
                    //高亮显示节点的父节点的父节点....直到根节点，并展示
                    var parentNode = highlightNodes[i].getParentNode();
                    var parentNodes = getParentNodes_ztree(treeId, parentNode);
                    treeObj.expandNode(parentNodes, true, false, true);
                    treeObj.expandNode(parentNode, true, false, true);
                }
            }
        }
    }

    /**
     * 递归得到指定节点的父节点的父节点....直到根节点
     */
    let getParentNodes_ztree=function(treeId, node){
        if (node != null) {
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            var parentNode = node.getParentNode();
            return getParentNodes_ztree(treeId, parentNode);
        } else {
            return node;
        }
    }

    /**
     * 设置树节点字体样式
     */
    let setFontCss_ztree=function (treeId, treeNode) {
        if (treeNode.id == 0) {
            //根节点
            return {color:"#333", "font-weight":"bold"};
        } else if (treeNode.isParent == false){
            //叶子节点
            return (!!treeNode.highlight) ? {color:"#ff0000", "font-weight":"bold"} : {color:"#660099", "font-weight":"normal"};
        } else {
            //父节点
            return (!!treeNode.highlight) ? {color:"#ff0000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
        }
    }

    /**
     * 收起树：只展开根节点下的一级节点
     * @param treeId
     */
    let close_ztree=function (treeId){
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
        var nodes = treeObj.transformToArray(treeObj.getNodes());
        var nodeLength = nodes.length;
        for (var i = 0; i < nodeLength; i++) {
            if (nodes[i].id == '0') {
                //根节点：展开
                treeObj.expandNode(nodes[i], true, true, false);
            } else {
                //非根节点：收起
                treeObj.expandNode(nodes[i], false, true, false);
            }
        }
    }
    /**
     * 移除节点
     * @param nodeList
     * @returns {*}
     */
    let removeNodes=function (nodeList) {
        for (var i=0;i<nodeList.length;i++){
            var node=nodeList[i];
            if(!node.isParent&& node.code==""){
                nodeList.splice(i,1);
                i--;
            }
        }
        return nodeList;
    }

    /**
     * 左侧树模糊查询
     */
    $("#search-bt").on("click",function () {
        autoMatch($("#keyword").val());
    })
    /**
     * 根据关键字匹配
     * @param txtObj
     */
    let autoMatch=function(txtObj){
        if (txtObj.length > 0) {
            var zTree = $.fn.zTree.getZTreeObj("organizationGroupTree");
            var nodeList = zTree.getNodesByParamFuzzy("name", txtObj, null);
            //模糊查询并标红
            highlightAndExpand_ztree("organizationGroupTree", nodeList, "");
        } else {
            userGroupZtree();
        }
    }


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
                    // userGroupZtree.reAsyncChildNodes(null, "refresh");
                    reloadTable();
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
                ,area: ['600px','600px']
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
                        let areaId = "";
                        // 初始化地区下拉树
                        selectTree.render({
                            'id': 'addAreaId'
                            ,'url': '/client/tree/area'
                            ,'isMultiple': false
                            ,clickNode:function (event, treeId, treeNode) {
                                areaId = treeNode.id;
                                initOrg(1,areaId);
                                //绑定树操作
                                selectTree.setValue(treeId,treeNode);
                                selectTree.hideTree();
                            }
                        });
                        // 初始化机构下拉树
                        // selectTree.render({
                        //     'id': 'addOrganizationId'
                        //     ,'url': '/client/tree/organization'
                        //     ,'isMultiple': false
                        // });
                        // 渠道下拉绑定
                        selectChannel(function (result) {
                            if(result!=null){
                                for(var i = 0; i<result.length; i++){
                                    $("#addDiv select[name='channelId']").append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
                                }
                            }
                            form.render('select');
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
                            ,url: '/client/group/insert'
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
         * 工具条：批量删除群组信息
         * @returns {boolean}
         */
        ,'deleteBarBtn': function(){
            var checkStatus = table.checkStatus('table')
                ,data = checkStatus.data;
            if(data.length == 0){
                layer.msg('请选中群组进行删除', {time: 2000});
                return false;
            }

            var id = '';
            for(var i = 0, len = data.length; i<len; i++){
                id += ",'" + data[i].id + "'";
            }

            layer.confirm('确定删除选中群组？', function(index){
                // 数据提交到后台，通用方法
                submit({
                    index: index
                    ,async: 'true'
                    ,url: '/client/group/delete'
                    ,type: 'POST'
                    ,param: {id: id.substring(1)}
                    ,dataType: 'json'
                });
            });
        }


        /**
         * 列表中：删除选中的群组信息
         * @param obj
         */
        ,'deleteOption': function (obj) {
            layer.confirm('确定删除该群组？', function(index){
                obj.del();
                // 数据提交到后台，通用方法
                submit({
                    index: index
                    ,async: 'true'
                    ,url: '/client/group/delete/' + obj.data.id
                    ,type: 'DELETE'
                    ,param: null
                    ,dataType: 'json'
                });
            });
        }
        /**
         * 列表中：修改群组信息
         * @param obj
         */
        ,'updateOption': function (obj) {
            var param = obj.data;
            //示范一个公告层
            layer.open({
                type: 1
                ,title: "<i class='layui-icon'>&#xe642;</i> 修改群组信息"
                ,area: ['600px','600px']
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
                        $("select[name='type']").val(param.type);
                        initOrg(2,null);//初始化机构列表
                        $("select[name='organizationId']").val(param.organizationId);
                        // 初始化机构下拉树
                        let areaId = "";
                        selectTree.render({
                            'id': 'updateAreaId'
                            ,'url': '/client/tree/area'
                            ,'isMultiple': false
                            // ,'range':'#updateDiv'
                            // ,'setData':['type','name','code']
                            ,'checkNodeId': param.areaId
                            ,clickNode:function (event, treeId, treeNode) {
                                areaId = treeNode.id;
                                initOrg(2,areaId);
                                //绑定树操作
                                selectTree.setValue(treeId,treeNode);
                                selectTree.hideTree();
                            }
                        });

                        // 初始化机构下拉树
                        // selectTree.render({
                        //     'id': 'updateOrganizationId'
                        //     ,'url': '/client/tree/organization'
                        //     ,'isMultiple': false
                        //     // ,'range':'#updateDiv'
                        //     // ,'setData':['type','name','code']
                        //     ,'checkNodeId': param.organizationId
                        // });
                        // 渠道下拉绑定
                        selectChannel(function (result) {
                            if(result!=null){
                                for(var i = 0; i<result.length; i++){
                                    $("#updateDiv select[name='channelId']").append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
                                }
                            }
                            // 地区级别下拉框赋值
                            $("#updateDiv select[name='channelId']").val(param.channelId);
                            form.render('select');
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
                            ,url: '/client/group/update'
                            ,type: 'POST'
                            ,param: data.field
                            ,dataType: 'json'
                        });
                    });
                    // 触发表单按钮点击事件
                    $("#submitUpdateBtn").click();
                }
            });
        }  /**
         * 列表中：修改群组信息
         * @param obj
         */
        ,'detailsOption': function (obj) {
            var param = obj.data;
            //示范一个公告层
            layer.open({
                type: 1
                ,title: "<i class='layui-icon'>&#xe642;</i> 群组信息"
                ,area: ['600px','600px']
                ,shade: 0.3
                ,maxmin:true
                ,offset: '50px'
                ,content:"<div id='detailsDiv' style='padding:20px 20px 0 20px'></div>"
                ,success: function(layero,index){
                    // 获取模板，并将数据绑定到模板，然后再弹出层中渲染
                    laytpl(detailsPop.innerHTML).render(param, function(html){
                        // 动态获取弹出层对象
                        $("#detailsDiv").empty().append(html);
                        $("select[name='type']").val(param.type);
                        initOrg(2,null);//初始化机构列表
                        $("select[name='organizationId']").val(param.organizationId);
                        // 初始化机构下拉树
                        let areaId = "";
                        selectTree.render({
                            'id': 'detailsAreaId'
                            ,'url': '/client/tree/area'
                            ,'isMultiple': false
                            ,'checkNodeId': param.areaId
                            ,clickNode:function (event, treeId, treeNode) {
                                areaId = treeNode.id;
                                initOrg(2,areaId);
                                //绑定树操作
                                selectTree.setValue(treeId,treeNode);
                                selectTree.hideTree();
                            }
                        });

                        // 渠道下拉绑定
                        selectChannel(function (result) {
                            if(result!=null){
                                for(var i = 0; i<result.length; i++){
                                    $("#detailsDiv select[name='channelId']").append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
                                }
                            }
                            // 地区级别下拉框赋值
                            $("#detailsDiv select[name='channelId']").val(param.channelId);
                            form.render('select');
                        });
                    });
                    form.render();
                }
            });
        }
        /**
         * 下载模板
         */
        ,'downModel': function () {
            window.location.href="/client/group/downModel";
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
                ,url: '/client/group/importExcel'
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

    initOrg(0,null);//初始化机构列表

    userGroupZtree();//初始化左侧群组树
});