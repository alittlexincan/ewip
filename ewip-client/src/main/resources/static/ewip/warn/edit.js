layui.config({
    base: '/client/layuiadmin/modules/'
}).extend({
    zTree: 'zTree'
    ,selectTree: 'selectTree'
    ,disaster: 'disaster'
});

layui.use(['table','form','laydate','element','laytpl','layer','zTree','selectTree','disaster'], function(){
    let table = layui.table			// 引用layui表格
        ,form = layui.form			// 引用layui表单
        ,laytpl = layui.laytpl		// 引用layui模板引擎
        ,layer = layui.layer		// 引用layui弹出层
        ,$ = layui.$   			// 引用layui的jquery
        ,element = layui.element
        ,laydate = layui.laydate
        ,zTree = layui.zTree
        ,selectTree = layui.selectTree
        ,employee = layui.sessionData("ewip").employee // 当前登录用户信息
        ,disaster = layui.disaster;


    /**
     * 自定义验证规则
     */
    form.verify({
        disasterId: function(value){
            if(value.length == 0) {
                $("#addDisasterId .addDisasterIdShow, #updateDisasterId .updateDisasterIdShow").css("border-color","red");
                return '请选择灾种';
            }
        }
    });


    var active = {

        /**
         * 基础信息配置
         * @param param
         */
        "setBasis": function (param) {
            if(param.step==1){
                layer.msg("该预警没有匹配到策略", {time: 2000});
                return false;
            }
            if(param.step==1){
                layer.msg("该预警没有匹配到预警内容", {time: 2000});
                return false;
            }
            // 预警标题
            $(".basis input[name='title']").val(param.organizationName + "发布" + param.disasterName + disaster.color(param.disasterColor) + "["+disaster.level(param.disasterLevel)+"]预警");
            // 预警名称
            $(".basis input[name='disasterName']").val(param.disasterName);
            // 预警颜色
            $(".basis select[name='disasterColor']").val(param.disasterColor);
            // 预警级别
            $(".basis select[name='disasterLevel']").val(param.disasterLevel);
            // 政府应对措施
            $(".basis textarea[name='measure']").val(param.measure);
            // 防御指南
            $(".basis textarea[name='instruction']").val(param.instruction);
            // 预警图标赋值
            $(".warn-icon > img").attr("src",param.icon);
            form.render("select");
            console.log("--------------");
            console.log(param);
        }

        /**
         * 获取策略配置
         * @param param
         */
        ,"getStrategyMsg": function (param, callback) {
            $.ajax({
                async:false
                ,type: "GET"
                ,data: param
                ,url: "/client/strategy/config"
                ,dataType: 'json'
                ,success: function(json){
                    if(json.code == 200 && json.data != null){
                        param.flow = json.data.flow;            // 获取配置的流程
                        param.channelId = json.data.channelId;  // 获取配置的渠道
                        callback(param);
                    }else {
                        param.step = 1;
                    }
                }
            });
        }
        /**
         * 获取预警配置
         * @param param
         * @param callback
         */
        ,"getWarnMsg": function (param, callback) {
            $.ajax({
                async:false
                ,type: "GET"
                ,data: param
                ,url: "/client/warn/config"
                ,dataType: 'json'
                ,success: function(json){
                    if(json.code == 200 && json.data != null){
                        param.measure = json.data.measure;          // 政府应对措施
                        param.instruction = json.data.instruction;  // 防御指南
                        callback(param);
                    }else{
                        param.step = 1;
                    }
                }
            });
        }
        /**
         * 获取策略和配置信息后，对其灾种、流程渠道等信息进行匹配
         */
        ,"setStrategyAndChannel":function (result) {
            var channelIds = result.channelId
                ,flow = result.flow;

            // 流程赋值
            flow.split(",").forEach(function (item) {
                $(".process-list .process input[type='checkbox'][value='"+item+"']").attr("checked","checked");
                form.render("checkbox");
            });

            // 渠道赋值
            channelIds.split(",").forEach(function (item) {
                $(".channel-list .imgbox[data-id='"+item+"']").addClass("active");
            });
        }
        /**
         * 获取预警信息后对政府应急措施、防御指南、预警内容进行处理
         * @param result
         */
        ,"setWarn": function (result) {
            //console.log(result);
        }
        /**
         * tab选项卡左移按钮
         */
        ,"warn-tab-prev": function () {
            alert("prev");
        }
        /**
         * tab选项卡右移按钮
         */
        ,"warn-tab-next": function () {
            alert("next");
        }
    };



    /**
     * 初始化页面基础信息
     */
    var initPageMsg = function(){
        $("#organizationName").val(employee.organizationName);
    };

    /**
     * 初始化发布时间
     */
    laydate.render({
        elem: '#editTime'
        ,type: 'datetime'
        ,theme: 'molv'
        ,value: new Date()
        ,format: 'yyyy-MM-dd HH:mm:ss'
    });
    /**
     * 初始化预计发生时间
     */
    laydate.render({
        elem: '#forecastTime'
        ,type: 'datetime'
        ,theme: 'molv'
        ,format: 'yyyy-MM-dd HH:mm:ss'
    });
    /**
     * 初始化失效时间
     */
    laydate.render({
        elem: '#invalidTime'
        ,type: 'datetime'
        ,theme: 'molv'
        ,format: 'yyyy-MM-dd HH:mm:ss'
    });

    /**
     * 初始化加载灾种级别树
     */
    var disasterLevelZtree = function () {
        selectTree.render({
            'id': 'addDisasterId'
            ,'url': '/client/tree/disaster/level'
            ,'isMultiple': false
            ,clickNode:function (event, treeId, treeNode) {
                if(treeNode.isConfig==1){
                    var name = treeNode.name;
                    name = name.substring(0, name.indexOf("["));
                    treeNode.name = name;
                    //绑定树操作
                    selectTree.setValue(treeId,treeNode);
                    selectTree.hideTree();

                    // 设置参数值
                    var param = {
                        areaId: employee.areaId                     // 地区ID
                        ,organizationId: employee.organizationId    // 机构ID
                        ,organizationName: employee.organizationName// 机构名称
                        ,disasterId: treeNode.id                    // 灾种ID
                        ,disasterName: name                         // 灾种及名称
                        ,disasterColor: treeNode.disasterColor      // 灾种颜色
                        ,disasterLevel: treeNode.disasterLevel      // 灾种级别
                        ,icon: "/client/"+treeNode.img              // 灾种图标
                    };
                    // 获取策略信息, 并设置流程、渠道
                    active.getStrategyMsg(param, active.setStrategyAndChannel);
                    // 获取预警信息，并匹配预警内容
                    active.getWarnMsg(param, active.setWarn);

                    // 基础信息配置
                    active.setBasis(param);


                }
                return false;
            }
        });
    };

    /**
     * 初始化加载群组树
     */
    var initAreaTree = function () {
        zTree.async({
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
                    onClick:null
                }
            }
        });
    };

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
                        html += "<div class='imgbox' data-id='"+currentValue.id+"' data-title='"+currentValue.name+"' data-channel='"+currentValue.name+"' data-type='tabAdd'>";
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
            $("." + event + " .imgbox").addClass("active");
        }else{
            $("." + event + " .imgbox").removeClass("active");
        }
    });
    /**
     * 渠道点击单选、取消选择
     */
    $(".channel-list").on("click", ".imgbox", function(element) {
        if($(this).hasClass("active")){
            $(this).removeClass("active");
        }else{
            $(this).addClass("active");
        }
    });

    /**
     * 文件上传
     */
    $(".fileUpload, .warn-upload-msg").on("click", function () {
        // 文件唯一标志
        var index = new Date().getTime();
        // 1:拼接文件文本框
        $(".warn-file-list").append("<input type='file' name='warn-"+index+"' data-index='"+index+"'>");
        // 2: 触发文件文本框
        $(".warn-file-list > input[name='warn-"+index+"']").click().change(function () {
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
                html += "   <td><a class='layui-btn layui-btn-danger layui-btn-xs' data-file-name='warn-" + index + "'><i class='layui-icon layui-icon-delete'></i>删除</a></td>";
                html += "</tr>";
            //追加到文件列表
            $(".warn-file-table > tbody").append(html);
            // 点击列表删除事件，删除当前行，并且删除，文件文本框
            $(".warn-file-table > tbody > tr > td > a").on("click",function () {
                var fileName = $(this).data("fileName");
                // 删除文件文本框
                $(".warn-file-list input[type='file'][name='"+fileName+"']").remove();
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
     * tab选项卡点击切换
     */
    element.on('tab(warn-tab)', function(data){
        console.log(this); //当前Tab标题所在的原始DOM元素
        console.log(data.index); //得到当前Tab的所在下标
        console.log(data.elem); //得到当前的Tab大容器
    });


    /**
     * tab选项卡删除监听事件
     */
    element.on('tabDelete(warn-tab)', function(data){
        console.log(this); //当前Tab标题所在的原始DOM元素
        console.log(data.index); //得到当前Tab的所在下标
        console.log(data.elem); //得到当前的Tab大容器
    });


    /**
     * tab选项卡前按钮移动操作
     */
    var tabIndex = 0;
    $(".warn-tab").on('click','.warn-tab-prev',function () {
        // title可视化宽度
        var width = $(".warn-tab-title").width();
        // 获取可视化区域li的个数，四舍五入
        var move = Math.round(width/95);
        if(tabIndex > 0){
            tabIndex--;
            var moveWidth = move * 95 * tabIndex;
            $(".warn-tab-title > li:nth-child(1)").css({"margin-left": -moveWidth});
        }
    });
    /**
     * tab选项卡后按钮移动操作
     */
    $(".warn-tab").on('click','.warn-tab-next',function () {
        // title可视化宽度
        var width = $(".warn-tab-title").width();
        // 获取可视化区域li的个数，四舍五入
        var move = Math.round(width/95);
        // li的总个数
        var count = $(".warn-tab-title > li").length;
        if(move * (tabIndex + 1) < count){
            tabIndex++;
            var moveWidth = move * 95 * tabIndex;
            $(".warn-tab-title > li:nth-child(1)").css({"margin-left": -moveWidth});

        }

    });



    /**
     * 监听预警提交事件
     */
    form.on("submit(submit)", function(data){
        // 数据提交到后台，通用方法
        console.log(data);
    });






    /**
     * 初始化加载项
     */
    initPageMsg();          // 初始化页面加载信息
    disasterLevelZtree();   // 初始化加载灾种级别树
    initChannelList();      // 初始化加载渠道
    initAreaTree();         // 初始化加载地区
});