layui.config({
    base: '/client/layuiadmin/modules/'
}).extend({
    zTree: 'zTree'
    ,selectTree: 'selectTree'
    ,ajaxFileUpload: 'ajaxFileUpload'
});

layui.use(['table','form','laydate','element','laytpl','layer','zTree','ajaxFileUpload'], function(){
    let form = layui.form			// 引用layui表单
        ,laytpl = layui.laytpl		// 引用layui模板引擎
        ,layer = layui.layer		// 引用layui弹出层
        ,$ = layui.$   			    // 引用layui的jquery
        ,element = layui.element
        ,laydate = layui.laydate
        ,zTree = layui.zTree
        ,ajaxFileUpload = layui.ajaxFileUpload
        ,employee = layui.sessionData("ewip").employee; // 当前登录用户信息


    var active = {
        /**
         * 渠道对应受众树加载
         * @param option
         */
        "channelToUserGroup": function (option) {
            zTree.async({
                id: '#group_'+option.channelId,
                setting: {
                    async:{
                        enable:true,
                        url: '/client/tree/user/group/count',
                        autoParam:["id"],
                        otherParam: { "areaId":option.areaId, "organizationId": option.organizationId, "channelId":option.channelId},
                        dataType:"json",
                        dataFilter:function (treeId, parentNode, responseData) {
                            if(responseData!=null){
                                for(var i = 0; i<responseData.length; i++){
                                    responseData[i].checked = true;
                                }
                            }
                            return responseData;
                        }
                    },
                    check: {
                        enable: true,
                        chkboxType: {"Y":"", "N": ""},
                        chkStyle:"checkbox"
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback:{
                        onClick:null,
                        onCheck:null
                    }
                }
            });
        }
        /**
         * 发送内容拼接
         * @param param
         */
        ,"setWarnContent": function (result) {
            // 拼接预警内容前缀
            var areas = new Set();
            // 循环渠道
            result.channelId.forEach(function (channelId) {
                var channel = $(".channel-list .imgbox[data-id='"+channelId+"']")
                    ,channelName = $(channel).data("title")
                    ,html = "";
                html += "<div class='layui-row layui-col-space5'>";
                html += "	<div class='layui-col-xs9 layui-col-md9'>";
                html += "		<div class='layui-card warn-card-content'>";
                html += "			<div class='layui-card-header'><span>&nbsp;&nbsp;<i class='layui-icon warn-card-hader-icon'>&#xe618;</i>预警编辑</span></div>";
                html += "			<div  class='layui-card-body warn-card-content-list content_"+channelId+"'>";
                // 循环地区
                result.area.forEach(function (area) {
                    areas.add(area.areaId);
                    html += "				<div class='layui-row layui-col-space5 warn-item_"+channelId+"_"+area.areaId+"'>";
                    html += "					<div class='layui-col-xs1 layui-col-md1 warn-content-title'>";
                    html += "						<div>"+area.areaName+"</div>";
                    html += "					</div>";
                    html += "					<div class='layui-col-xs11 layui-col-md11 warn-content-body'>";
                    html += "                       <textarea type='text' name='content_"+channelId+"_"+area.areaId+"' placeholder='请输入预警内容' autocomplete='off' class='layui-textarea'>" + title + active.warnContent+"</textarea>";
                    html += "					</div>";
                    html += "				</div>";
                });
                html += "			</div>";
                html += "		</div>";
                html += "	</div>";
                html += "	<div class='layui-col-xs3 layui-col-md3'>";
                html += "		<div class='layui-card warn-card-content'>";
                html += "			<div class='layui-card-header'><span>&nbsp;&nbsp;<i class='layui-icon layui-icon-tree warn-card-hader-icon'></i>受众群组</span></div>";
                html += "			<div  class='layui-card-body warn-card-content-list'>";
                html += "				<div class='ztree' id='group_"+channelId+"'></div>";
                html += "			</div>";
                html += "		</div>";
                html += "	</div>";
                html += "</div>"


                $(".warn-card-content-list").empty().append(html);


                // 动态添加渠道地区对应的受众（根据地区和渠道查询）
                // active.channelToUserGroup({
                //     areaId: function () {
                //         var id = "";
                //         for (let item of areas) {
                //             id += "," + item;
                //         }
                //         return id.substring(1);
                //     },
                //     organizationId: result.organizationId,
                //     channelId: channelId
                // });

            });

        }

        /**
         * 渠道单击事件
         * 判断active选中样式是否存在
         * 如果存在：已经选中，否则没有选中
         * @param obj
         */
        ,"channelOneClick": function (obj) {
            var channelId = $(obj).data("id");
            if ($(obj).hasClass("active")) {
                // 获取选中渠道
                var param = {
                    /**
                     * 获取选中渠道
                     */
                    "channelId": [channelId]
                    /**
                     * 获取选中地区
                     */
                    , "area": function () {
                        var area = [];
                        initAreaTree.getCheckedNodes().forEach(function (item) {
                            area.push({
                                areaId: item.id,
                                areaName: item.name
                            });
                        });
                        return area;
                    }()
                };
                // 追加选中渠道的tab页
                active.setWarnContent(param);
            } else {

            }
        }

        /**
         * 地区选择
         */
        ,"areaCheck": function (event, treeId, treeNode) {
            // 判断渠道、预警是否选中，如果没有选中渠道则先选择渠道、如果预警没有选中则选择预警，做拦截
            var channel = $(".channel-list .imgbox.active");

            // 判断渠道是否选中
            if(channel.length == 0){
                initAreaTree.checkNode(treeNode, true, true);
                layer.msg("请先选择渠道", {time: 2000});
                return false;
            }

            // 判断至少选中一个地区
            var areaTree = zTree.getZTree(treeId);
            var nodes = areaTree.getCheckedNodes(true);
            if(nodes.length == 0){
                initAreaTree.checkNode(treeNode, true, true);
                layer.msg("请至少选中一个地区", {time: 2000});
                return false;
            }

            // 判断当前节点是否选中
            var checked = treeNode.getCheckStatus().checked;
            // 拼接参数
            var param = {
                "treeId":treeId
                ,"checked":checked
                ,"channelId": function () {
                    var cId = [];
                    $(channel).each(function () {
                        cId.push($(this).data("id"));
                    }) ;
                    return cId;
                }()
                ,"area": [{
                    areaId: treeNode.id,
                    areaName: treeNode.name
                }]
            };
        }
    };

    /**
     * 初始化页面基础信息
     */
    var initPageMsg = function(){
        $(".basis input[name='organizationName']").val(employee.organizationName);
        $(".basis input[name='organizationCode']").val(employee.organizationCode);
    };

    /**
     * 初始化发布时间
     */
    laydate.render({
        elem: '#sendTime'
        ,type: 'datetime'
        ,theme: 'molv'
        ,value: new Date()
        ,format: 'yyyy-MM-dd HH:mm:ss'
    });


    /**
     * 初始化加载群组树
     */
    var initAreaTree =  zTree.async({
        id: "#areaTree",
        setting: {
            async:{
                enable:true,
                url: "/client/tree/area",
                autoParam:["id"],
                dataType:"json",
            },
            check: {
                enable: true,
                chkboxType: {"Y":"", "N": ""},
                chkStyle:"checkbox"
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback:{
                onClick:null,
                onCheck:active.areaCheck
            }
        }
    });

    /**
     * 初始化加载渠道
     */
    var initChannelList = function(){
        $.ajax({
            async:true
            ,type: "POST"
            ,data: {type: 0}
            ,url: "/client/channel/list"
            ,dataType: 'json'
            ,success: function(json){
                if(json.code == 200 && json.data != null){
                    var html ="";
                    json.data.forEach(function (currentValue, index, arr) {
                        html += "<div class='imgbox' data-id='"+currentValue.id+"' data-title='"+currentValue.name+"' data-channel='"+currentValue.name+"' data-code='"+currentValue.code+"' >";
                        html += "   <img src='/client/"+currentValue.icon+"' alt='"+currentValue.name+"' />";
                        html += "<span>"+currentValue.name+"</span>";
                        html += "</div>";
                    });
                    $(".channel-list").empty().append(html);
                }
            }
        });
    };

    /**
     * 渠道全选、反选
     */
    $(".channel-option").on("click", "div > span", function(element) {
        var text = $(this).text(),
            event = $(this).data("event");

        if(text == '全选'){
            // 给所有渠道添加选中样式
            $("." + event + " .imgbox").addClass("active");
            // 获取选中渠道
            var param = {
                /**
                 * 获取选中渠道
                 */
                "channelId": function () {
                    var cId = [];
                    $("." + event + " .imgbox").each(function () {
                        cId.push($(this).data("id"));
                    });
                    return cId;
                }()
                /**
                 * 获取选中地区
                 */
                ,"area": function () {
                    var area = [];
                    initAreaTree.getCheckedNodes().forEach(function (item) {
                        area.push({
                            areaId: item.id,
                            areaName: item.name
                        });
                    });
                    return area;
                }()
            };
            // 清除tab页所有内容
            $(".warn-tab .warn-tab-title, .warn-tab .warn-tab-content").empty();
        }else{
            $("." + event + " .imgbox").removeClass("active");
            // 清除tab页所有内容
            $(".warn-tab .warn-tab-title, .warn-tab .warn-tab-content").empty();
        }
    });
    /**
     * 渠道点击单选、取消选择
     */
    $(".channel-list").on("click", ".imgbox", function(element) {
        // 追加或删除样式
        if($(this).hasClass("active")){
            $(this).removeClass("active");
        }else{
            $(this).addClass("active");
        }
        // 调用渠道点击事件业务
        active.channelOneClick($(this));
    });

    /**
     * 文件上传
     */
    $(".fileUpload, .warn-upload-msg").on("click", function () {
        // 文件唯一标志
        var index = new Date().getTime();
        // 1:拼接文件文本框
        $(".warn-file-list").append("<input type='file' id='warn-"+index+"' name='warnFile' class='warn-"+index+"' data-file-index='"+index+"'>");
        // 2: 触发文件文本框
        $(".warn-file-list > .warn-"+index).click().change(function () {
            // 如果是最先添加则显示文件表格
            if($(".warn-file-table").hasClass("layui-hide")){
                $(".warn-file-table").removeClass("layui-hide");
                // 3:隐藏初始化提示信息
                $(".warn-upload-msg").addClass("layui-hide");
            }
            // 获取选中文件信息
            var file = $(this)[0].files[0], size = file.size;
            // 计算文件大小
            var s = (size/1024) > 1024 ? (file.size/1024/1024).toFixed(2) + "MB": (file.size/1024).toFixed(2) + "KB";
            // 拼接文件内容
            var html = "<tr>";
            html += "   <td>" + index + "</td>";
            html += "   <td>" + file.name + "</td>";
            html += "   <td>" + s + "</td>";
            html += "   <td>" + file.name.substring(file.name.lastIndexOf(".")+1,file.name.length) + "</td>";
            html += "   <td><a class='layui-btn layui-btn-danger layui-btn-xs' data-file-class='warn-" + index + "'><i class='layui-icon layui-icon-delete'></i>删除</a></td>";
            html += "</tr>";
            //追加到文件列表
            $(".warn-file-table > tbody").append(html);
            // 点击列表删除事件，删除当前行，并且删除，文件文本框
            $(".warn-file-table > tbody > tr > td > a").on("click",function () {
                var fileClass = $(this).data("fileClass");
                // 删除文件文本框
                $(".warn-file-list > ." + fileClass).remove();
                // 删除当前行
                $(this).parent().parent().remove();

                if($(".warn-file-table > tbody > tr").length == 0){
                    $(".warn-file-table").addClass("layui-hide");
                    // 3:隐藏初始化提示信息
                    $(".warn-upload-msg").removeClass("layui-hide");
                }
            });
        });
    });



    /**
     * 监听预警提交事件
     */
    form.on("submit(submit)", function(data){
        // 数据提交到后台，通用方法
        var param = data.field;



        // 渠道处理
        param.channel = function(){
            var channel = [];
            $(".channel-list .imgbox.active").each(function () {
                channel.push({
                    channelId: $(this).data("id"),
                    channelName: $(this).data("title"),
                    channelCode: $(this).data("code")
                });
            });
            return JSON.stringify(channel).replace(/\"/g,"'");
        }();

        // 地区处理
        param.area = function(){
            var area = [];
            initAreaTree.getCheckedNodes(true).forEach(function (item) {
                area.push({
                    areaId: item.id,
                    areaName: item.name,
                    areaCode: item.code
                });
            });
            return JSON.stringify(area).replace(/\"/g,"'");
        }();

        // 群组处理
        param.group = function(){
            var group = {};
            $(".channel-list .imgbox.active").each(function () {
                var channelId = $(this).data("id")
                    ,channelGroup = [];
                zTree.getZTree("group_"+channelId).getCheckedNodes(true).forEach(function (item) {
                    channelGroup.push({
                        userGroupId: item.id,
                        userGroupName: item.name
                    });
                });
                group[channelId] = channelGroup;
            });
            return JSON.stringify(group).replace(/\"/g,"'");
        }();

        // 上传文件名称处理
        var files = function () {
            delete param.warnFile;
            var file = [];
            $(".warn-file-list > input[type='file']").each(function () {
                file.push("warn-" + $(this).data("fileIndex"));
            });
            return file;
        };

        ajaxFileUpload.render({
            async: true
            ,url : "/client/warn/edit/insert"
            ,type: "POST"
            ,param : param//需要传递的数据 json格式
            ,files : files()
            ,dataType: 'json'
        },function (json) {
            if(json.code == 200){
                // 弹出提示信息，2s后自动关闭
                layer.msg(json.msg, {time: 2000},function(){
                    location.reload();
                });
            }
        });
    });


    /**
     * 初始化加载项
     */
    initPageMsg();          // 初始化页面加载信息
    initChannelList();      // 初始化加载渠道
});