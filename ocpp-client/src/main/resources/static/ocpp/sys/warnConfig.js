layui.config({
    base: '/static/layuiadmin/modules/' //假设这是你存放拓展模块的根目录
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
     * 加载表格
     */
    table.render({
        id: 'table'
        ,elem: '#table'
        ,url:'/warnConfig/select'
        ,page:true
        ,even: true
        ,height: 'full-165'
        ,limits:[5,10,20,50,100]
        ,cols: [[
            {type: 'checkbox'}
            ,{type: 'numbers', title: '编号'}
            ,{field: 'disasterName', title: '预警名称', sort: true}
            ,{field: 'disasterColor', title: '预警颜色', sort: true, templet:colorFormat}
            ,{field: 'disasterLevel', title: '预警级别', templet: levelFormat }
            ,{field: 'content', title: '预警内容', sort: true}
            ,{field: 'instruction', title: '防御指南'}
            ,{title: '操&nbsp;&nbsp;作', width: '25%', align:'center', toolbar: '#btnGroupOption'}
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
        console.log(param);
        table.reload('table', {
            page: {
                curr: 1
            },
            where: { //设定异步数据接口的额外参数，任意设
                disasterId: param == undefined ? '' : param.disasterId
                ,disasterName: param == undefined ? '' : param.disasterName
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
    var organizationClick = function(event, treeId, treeNode){
        table.reload('table', {
            page: {
                curr: 1
            },
            where: { organizationId: treeNode.id}
        });
    };

    /**
     * 初始化下拉机构列表
     * @param param
     */
    let initOrg=function(flag,param){
        $.ajax({
            async:false
            ,type: "POST"
            ,data: {areaId:param}
            ,url: "/organization/selectOrg"
            ,dataType: 'json'
            ,success: function(json){
                let list=json;
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
     * 灾种级别树点击事件
     * @param event
     * @param treeId
     * @param treeNode
     */
    var disasterLevelClick = function(event, treeId, treeNode){
        console.log(treeNode);
        if(treeNode.type < 2){
            layer.msg('请点击灾种或灾种级别查询', {time: 1000});
            return false;
        }
        var where = {disasterId: treeNode.id};
        if(treeNode.isConfig == 0){
            where.disasterColor = null;
            where.disasterLevel = null;
        }else{
            where.disasterColor = treeNode.disasterColor;
            where.disasterLevel = treeNode.disasterLevel;
        }
        reloadTable(where, 2);
    };

    /**
     * 初始化加载灾种树
     */
    var disasterLevelTree =function(){
        zTree.async({
            id: "#disasterLevelTree",
            setting: {
                async:{
                    enable:true,
                    url: "/tree/disaster/haveContent",
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
                    onClick:disasterLevelClick
                },
                view: {
                    addDiyDom: function(treeId, treeNode){
                        console.log(treeNode);
                        if(treeNode.isStrategy>=1){
                            console.log(treeNode.isStrategy);
                            $("#" + treeNode.tId + "_a").prepend('<i class="layui-icon layui-icon-ok" style="font-weight: bold"></i>');
                        }
                    },
                    fontCss: function(treeId, treeNode) {
                        if(treeNode.level == 4){
                            if(treeNode.disasterColor == 0){
                                return {color:"red"}
                            }else  if(treeNode.disasterColor == 1){
                                return {color:"orange"}
                            }else if(treeNode.disasterColor == 2){
                                return {color:"#d0d057"}
                            }else if(treeNode.disasterColor == 3){
                                return {color:"blue"}
                            }
                        }
                        return {};
                    }

                }
            }
        })
    };



    /**
     * 初始化加载机构树
     */
    let orgZtree =function() {
        zTree.async({
            id: "#organizationTree",
            setting: {
                async:{
                    enable:true,
                    url: "/tree/organization",
                    autoParam:["id"],
                    dataType:"json",
                },
                view: {
                    selectedMulti: false, //是否允许多选
                    fontCss: getFontCss
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
            var zTree = $.fn.zTree.getZTreeObj("organizationTree");
            var nodeList = zTree.getNodesByParamFuzzy("name", txtObj, null);
            //模糊查询并标红
            highlightAndExpand_ztree("organizationTree", nodeList, "");
        } else {
            orgZtree();
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
     * @type {{addBarBtn: 添加信息, deleteBarBtn: 批量删除信息, deleteOption: 删除单个信息, updateOption: 修改信息}}
     */
    let active = {
        /**
         * 工具条：添加预警配置信息
         */
        'addBarBtn': function(){
            layer.open({
                type: 1
                ,title: "<i class='layui-icon'>&#xe642;</i> 添加预警配置信息"
                ,area: '600px'
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
                        // 初始化下拉地区拉树
                        let areaId = "";
                        // 初始化下拉灾种级别拉树
                        selectTree.render({
                            'id': 'addDisasterId'
                            ,'url': '/tree/disaster/level'
                            ,'isMultiple': false
                            ,clickNode:function (event, treeId, treeNode) {
                                if(treeNode.isConfig==1){
                                    var name = treeNode.name;
                                    name = name.substring(0, name.indexOf("["));
                                    treeNode.name = name;
                                    $("#addDiv input[name='disasterName']").val(name);
                                    $("#addDiv select[name='disasterColor']").val(treeNode.disasterColor);
                                    $("#addDiv select[name='disasterLevel']").val(treeNode.disasterLevel);
                                    selectTree.setValue(treeId,treeNode);
                                    selectTree.hideTree();
                                    form.render("select");
                                }
                                return false;
                            }
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
                            ,url: '/warnConfig/insert'
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
         * 工具条：批量删除受众信息
         * @returns {boolean}
         */
        ,'deleteBarBtn': function(){
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

            layer.confirm('确定删除选中预警配置？', function(index){
                // 数据提交到后台，通用方法
                submit({
                    index: index
                    ,async: 'true'
                    ,url: '/warnConfig/delete'
                    ,type: 'POST'
                    ,param: {id: id.substring(1)}
                    ,dataType: 'json'
                });
            });
        }

        /**
         * 列表中：修改预警配置信息
         * @param obj
         */
        ,'updateOption': function (obj) {
            var param = obj.data;
            layer.open({
                type: 1
                ,title: "<i class='layui-icon'>&#xe642;</i> 修改预警配置信息"
                ,area: '600px'
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
                        // 初始化下拉灾种级别拉树
                        selectTree.render({
                            'id': 'updateDisasterId'
                            ,'url': '/tree/disaster/level'
                            ,'isMultiple': false
                            ,'checkNodeId': param.disasterId
                            ,clickNode:function (event, treeId, treeNode) {
                                if(treeNode.isConfig==1){
                                    var name = treeNode.name;
                                    name = name.substring(0, name.indexOf("["));
                                    treeNode.name = name;
                                    $("#updateDiv input[name='disasterName']").val(name);
                                    $("#updateDiv select[name='disasterColor']").val(treeNode.disasterColor);
                                    $("#updateDiv select[name='disasterLevel']").val(treeNode.disasterLevel);
                                    selectTree.setValue(treeId,treeNode);
                                    selectTree.hideTree();
                                    form.render("select");
                                }
                                return false;
                            }
                        });
                        $("#updateDiv input[name='disasterName']").val(param.disasterName);
                        $("#updateDiv select[name='disasterColor']").val(param.disasterColor);
                        $("#updateDiv select[name='disasterLevel']").val(param.disasterLevel);
                        $("#updateDiv textarea[name='content']").val(param.content);
                        $("#updateDiv textarea[name='measure']").val(param.measure);
                        $("#updateDiv textarea[name='instruction']").val(param.instruction);
                        form.render('select');
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
                            ,url: '/warn/update'
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
         * 列表中：预警配置信息
         * @param obj
         */
        ,'detailsOption': function (obj) {
            var param = obj.data;
            layer.open({
                type: 1
                ,title: "<i class='layui-icon'>&#xe642;</i>预警配置信息"
                ,area: '600px'
                ,shade: 0.3
                ,maxmin:true
                ,offset: '50px'
                ,content:"<div id='detailsDiv' style='padding:20px 20px 0 20px'></div>"
                ,success: function(layero,index){
                    // 获取模板，并将数据绑定到模板，然后再弹出层中渲染
                    laytpl(detailsPop.innerHTML).render(param, function(html){
                        // 动态获取弹出层对象
                        $("#detailsDiv").empty().append(html);

                        // 初始化下拉地区拉树
                        let areaId = "";
                        selectTree.render({
                            'id': 'detailsAreaId'
                            ,'url': '/tree/area'
                            ,'isMultiple': false
                            ,'checkNodeId': param.areaId
                            ,clickNode:function (event, treeId, treeNode) {
                                areaId = treeNode.id;
                                //绑定树操作
                                selectTree.setValue(treeId,treeNode);
                                selectTree.hideTree();
                            }
                        });
                        // 初始化下拉灾种级别拉树
                        selectTree.render({
                            'id': 'detailsDisasterId'
                            ,'url': '/tree/disaster/level'
                            ,'isMultiple': false
                            ,'checkNodeId': param.disasterId
                            ,clickNode:function (event, treeId, treeNode) {
                                if(treeNode.isConfig==1){
                                    var name = treeNode.name;
                                    name = name.substring(0, name.indexOf("["));
                                    treeNode.name = name;
                                    $("#detailsDiv input[name='disasterName']").val(name);
                                    $("#detailsDiv select[name='disasterColor']").val(treeNode.disasterColor);
                                    $("#detailsDiv select[name='disasterLevel']").val(treeNode.disasterLevel);
                                    selectTree.setValue(treeId,treeNode);
                                    selectTree.hideTree();
                                    form.render("select");
                                }
                                return false;
                            }
                        });
                        $("#detailsDiv input[name='disasterName']").val(param.disasterName);
                        $("#detailsDiv select[name='disasterColor']").val(param.disasterColor);
                        $("#detailsDiv select[name='disasterLevel']").val(param.disasterLevel);
                        $("#detailsDiv textarea[name='content']").val(param.content);
                        $("#detailsDiv textarea[name='measure']").val(param.measure);
                        $("#detailsDiv textarea[name='instruction']").val(param.instruction);
                        form.render('select');
                    });
                    form.render();
                }
            });
        }

        /**
         * 列表中：删除选中的预警配置信息
         * @param obj
         */
        ,'deleteOption': function (obj) {
            layer.confirm('确定删除该预警配置？', function(index){
                obj.del();
                // 数据提交到后台，通用方法
                submit({
                    index: index
                    ,async: 'true'
                    ,url: '/warnConfig/delete/' + obj.data.id
                    ,type: 'DELETE'
                    ,param: {fileName: obj.data.icon}
                    ,dataType: 'json'
                });
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

    initOrg(0,null);//初始化机构列表

    disasterLevelTree();
    // orgZtree();

});