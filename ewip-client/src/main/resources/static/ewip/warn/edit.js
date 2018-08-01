layui.config({
    base: '/client/layuiadmin/modules/'
}).extend({
    zTree: 'zTree',
    selectTree: 'selectTree'
});

layui.use(['table','form','laydate','element','laytpl','layer','zTree','selectTree'], function(){
    let table = layui.table			// 引用layui表格
        ,form = layui.form			// 引用layui表单
        ,laytpl = layui.laytpl		// 引用layui模板引擎
        ,layer = layui.layer		// 引用layui弹出层
        ,$ = layui.$   			// 引用layui的jquery
        ,element = layui.element
        ,laydate = layui.laydate
        ,zTree = layui.zTree
        ,selectTree = layui.selectTree;

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
     * 初始化加载项
     */
    initChannelList();  // 初始化加载渠道
    initAreaTree();     // 初始化加载地区
});