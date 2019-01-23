
layui.config({
    base: '/static/layuiadmin/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    productTemplate: 'productTemplate' //如果 mymod.js 是在根目录，也可以不用设定别名
    ,mod1: 'modules' //相对于上述 base 目录的子目录
    ,zTree: 'zTree'
});

layui.use(["table","form","laytpl","layer","zTree","productTemplate","element"], function() {

    let table = layui.table			// 引用layui表格
        , form = layui.form			// 引用layui表单
        , laytpl = layui.laytpl		// 引用layui模板引擎
        , layer = layui.layer		// 引用layui弹出层
        , zTree = layui.zTree
        , productTemplate = layui.productTemplate
        , $ = layui.$   			// 引用layui的jquery
        , element = layui.element
        , employee = layui.sessionData("ocpp").employee; // 当前登录用户信息
    /**
     * 全局参数
     * @type {{editor}}
     */
    let active = {
        /**
         * 初始化ueditor编辑器
         */
        editor: UE.getEditor('editor')
        /**
         * ueditor工具类
         */
        ,editorUtils: UE.dom.domUtils

    };

    /**
     * 获取当前时间
     */
    let dateTime = function(){
        var now = new Date();
        var date = new Date(now.getTime());
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var hour = date.getHours();
        var minute = date.getMinutes();
        var second = date.getSeconds();
        month = month < 10 ? "0"+month : month;
        day = day < 10 ? "0" + day : day;
        hour = hour < 10 ? "0" + hour : hour;
        minute = minute < 10 ? "0" + minute : minute;
        second = second < 10 ? "0" + second : second;
        return year + '年' + month + '月' + day  + '日' + hour + '时' + minute + '分';
    };

    /**
     * 初始化Ueditor模板
     * @param isAppendTo
     */
    function setContent(isAppendTo) {
        active.editor.setContent(productTemplate.init(), isAppendTo);
    }

    /**
     * 设置内容
     */
    $("#btns").on("click","#setContent", isAppendTo => {
        active.editor.setContent(productTemplate.getDecisionServiceProductTemplate(null), isAppendTo);
        return false;
    });

    /**
     * 选择模板类型
     */
    form.on('select(type)',  function(data,isAppendTo) {
        var option={dateTime:dateTime(),group:employee.organizationName};
        var flag=data.value;
        if(flag==0) {
            active.editor.setContent(productTemplate.getFestivalProductTemplate(option), isAppendTo);
        }else if(flag==1){
            active.editor.setContent(productTemplate.getQixiangProductTemplate(option), isAppendTo);
        }else if(flag==2){
            active.editor.setContent(productTemplate.getWarnProductTemplate(option), isAppendTo);
        }else if(flag==3){
            active.editor.setContent(productTemplate.getWorkProductTemplate(option), isAppendTo);
        }else if(flag==4){
            active.editor.setContent(productTemplate.getDisasterProductTemplate(option), isAppendTo);
        }else if(flag==5){
            active.editor.setContent("",isAppendTo);
        }
        // active.editor.setEnabled();//使其可以编辑
    });

    /**
     * 创建产品
     */
    $(".submit").on("click","#createProduct", () => {
        var type = $("#type option:selected").val();
        var title=$("#title").val();
        var text = active.editor.getContent();

        var group = {};
        if(type=="" || type==null){
            layer.msg('请选择模板', {time: 2000});
            return false;
        }
        if(title==""|| title==null ){
            layer.msg('请填写产品名称', {time: 2000});
            return false;
        }
        layer.confirm('确定生成word文档？', function(){
            $(".channel-list .imgbox.active").each(function () {
                var channelId = $(this).data("id");
                var channelName = $(this).data("channel");
                if(channelName=="邮件") {
                    zTree.getZTree("group_" + channelId).getCheckedNodes(true).forEach(function (item) {
                        if (channelName == "邮件") {
                            group = {
                                userGroupId: item.id,
                                userGroupName: item.name
                            };
                        }
                    });
                }
            });
            let param = {
                type:type,
                title:title,
                html: "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" +
                    "xmlns:w=\"urn:schemas-microsoft-com:office:word\" xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\"\n" +
                    "xmlns=\"http://www.w3.org/TR/REC-html40\">" +
                    "<head><!--[if gte mso 9]><xml><w:WordDocument><w:View>Print</w:View><w:TrackMoves>false</w:TrackMoves><w:TrackFormatting/><w:ValidateAgainstSchemas/><w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid><w:IgnoreMixedContent>false</w:IgnoreMixedContent><w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText><w:DoNotPromoteQF/><w:LidThemeOther>EN-US</w:LidThemeOther><w:LidThemeAsian>ZH-CN</w:LidThemeAsian><w:LidThemeComplexScript>X-NONE</w:LidThemeComplexScript><w:Compatibility><w:BreakWrappedTables/><w:SnapToGridInCell/><w:WrapTextWithPunct/><w:UseAsianBreakRules/><w:DontGrowAutofit/><w:SplitPgBreakAndParaMark/><w:DontVertAlignCellWithSp/><w:DontBreakConstrainedForcedTables/><w:DontVertAlignInTxbx/><w:Word11KerningPairs/><w:CachedColBalance/><w:UseFELayout/></w:Compatibility><w:BrowserLevel>MicrosoftInternetExplorer4</w:BrowserLevel><m:mathPr><m:mathFont m:val=\"Cambria Math\"/><m:brkBin m:val=\"before\"/><m:brkBinSub m:val=\"--\"/><m:smallFrac m:val=\"off\"/><m:dispDef/><m:lMargin m:val=\"0\"/> <m:rMargin m:val=\"0\"/><m:defJc m:val=\"centerGroup\"/><m:wrapIndent m:val=\"1440\"/><m:intLim m:val=\"subSup\"/><m:naryLim m:val=\"undOvr\"/></m:mathPr></w:WordDocument></xml><![endif]--> \n</head><body>" + text + "</body></html>"
            };
            $.ajax({
                async: true,
                url: "/ueditor/getWord",
                data: param,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    if(data.code=="success"){
                        layer.msg('生成word成功', {time: 2000});
                    }else{
                        layer.msg('生成word失败', {time: 2000});
                    }
                }
            })
        });
    });

    /**
     * 初始化监听
     */
    UE.getEditor('editor').addListener( 'ready', function() {
        this.setHeight(400);
        this.initialFrameHeight='400px';//设置编辑器高度
    });


});