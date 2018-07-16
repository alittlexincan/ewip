
layui.define(function(exports){

    var beforeClick = function(treeId, treeNode) {
        var check = (treeNode && !treeNode.isParent);
        if (!check) alert("只能选择城市...");
        return check;
    };

    var onClick = function(event, treeId, treeNode) {
        $("#pIdShow").css("border-color","#e6e6e6");
        var zTree = $.fn.zTree.getZTreeObj(treeId);
        if (zTree.setting.check.enable == true) {
            zTree.checkNode(treeNode, !treeNode.checked, false)
            assignment(treeId, zTree.getCheckedNodes());
        } else {
            assignment(treeId, zTree.getSelectedNodes());
            hideMenu();
        }
    };

    var onCheck = function(event, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj(treeId);
        assignment(treeId, zTree.getCheckedNodes());
    };

    var hideMenu = function() {
        $(".select-tree").removeClass("layui-form-selected");
        $(".tree-content").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    };

    var assignment = function(treeId, nodes) {
        var names = "";
        var ids = "";
        for (var i = 0, l = nodes.length; i < l; i++) {
            names += nodes[i].name + ",";
            ids += nodes[i].id + ",";
        }
        if (names.length > 0) {
            names = names.substring(0, names.length - 1);
            ids = ids.substring(0, ids.length - 1);
        }
        treeId = treeId.substring(0, treeId.length - 4);
        $("#" + treeId + "Show").attr("value", names);
        $("#" + treeId + "Show").attr("title", names);
        $("#" + treeId + "Hide").attr("value", ids);
    };

    var onBodyDown = function(event) {
        if ($(event.target).parents(".tree-content").html() == null) {
            hideMenu();
        }
    };

    /**
     * 获取树
     * @param param
     * @returns {*}
     */
    var getData = function (param) {
        var treeData = null;
        $.ajax({
            async:false
            ,type: 'POST'
            ,data: param.data|{}
            ,url: param.url
            ,dataType: 'json'
            ,success: function(json){
                treeData = json.data;
            }
        });
        return treeData;
    };

    /**
     * 下拉zTree封装
     * @param id         div id
     * @param zNodes     zTree 节点数据
     * @param isMultiple 是否多选
     * @param chkboxType 多选框类型{"Y": "ps", "N": "s"}
     * @param checkNodeId 数据回显时的树节点id
     */

    var selectTree = {
        "render": function(option) {
            var data = getData(option);
            var setting = {
                view: {
                    dblClickExpand: false,
                    showLine: false
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey : 'id',
                        pIdKey: 'pId'
                    }
                },
                check: {
                    enable: false,
                    chkboxType: {"Y": "ps", "N": "s"}
                },
                callback: {
                    onClick: onClick,
                    onCheck: onCheck
                }
            };
            if (option.isMultiple) {
                setting.check.enable = option.isMultiple;
            }
            if (option.chkboxType !== undefined && option.chkboxType != null) {
                setting.check.chkboxType = option.chkboxType;
            }
            var html = '<div class = "layui-select-title" >' +
                '<input id="' + option.id + 'Show"' + 'type = "text" placeholder = "请选择" value = "" class = "layui-input" readonly>' +
                '<i class= "layui-edge" ></i>' +
                '</div>';

                $("#" + option.id).prepend(html);
                $("#" + option.id).parent().append('<div class="tree-content scrollbar">' +
                '<input hidden id="' + option.id + 'Hide" ' + 'name="' + $(".select-tree").attr("id") + '" lay-verify="'+$(".select-tree").attr("id")+'">' +
                '<ul id="' + option.id + 'Tree" class="ztree scrollbar" style="margin-top:0;"></ul>' +
                '</div>');
                $("#" + option.id).bind("click", function () {
                    if ($(this).parent().find(".tree-content").css("display") !== "none") {
                        hideMenu();
                    } else {
                        $(this).addClass("layui-form-selected");
                        var Offset = $(this).offset();
                        var width = $(this).width() - 2;
                        $(this).parent().find(".tree-content").css({
                            left: Offset.left + "px",
                            top: Offset.top + $(this).height() + "px"
                        }).slideDown("fast");
                        $(this).parent().find(".tree-content").css({
                            width: width
                        });
                        $("body").bind("mousedown", onBodyDown);
                    }

                });
            var tree = $.fn.zTree.init($("#" + option.id + "Tree"), setting, data);
            // 如果有选中项则回填选中结果
            if(option.checkNodeId != undefined && option.checkNodeId != null){
                var node = tree.getNodeByParam("id",option.checkNodeId);
                tree.selectNode(node,true);//将指定ID的节点选中
                onClick(event, option.id + "Tree", node)
            }

        }
    };
    //输出test接口
    exports('selectTree', selectTree);
});