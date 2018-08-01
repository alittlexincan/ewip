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
    });
    laydate.render({
        elem: '#forecastTime'
        ,type: 'datetime'
    });
    laydate.render({
        elem: '#invalidTime'
        ,type: 'datetime'
    });

    /**
     * 初始化加载渠道
     */
    var initChannel = function(){
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
                        var st = "";
                        if(currentValue.name == '短信' || currentValue.name == '大喇叭' || currentValue.name == '北斗'){
                            st = "active";
                        }else{
                            st = "";
                        }
                        html += "<div class='imgbox "+ st +"' data-id='"+currentValue.id+"' data-title='"+currentValue.name+"' data-channel='"+currentValue.name+"' data-type='tabAdd'>";
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
     * 初始化加载群组树
     */
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
                onClick:null
            }
        }
    });

    /**
     * 初始化加载渠道
     */
    var initChannel = function(){
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
                        var st = "";
                        if(currentValue.name == '短信' || currentValue.name == '大喇叭' || currentValue.name == '北斗'){
                            st = "active";
                        }else{
                            st = "";
                        }
                        html += "<div class='imgbox "+ st +"' data-id='"+currentValue.id+"' data-title='"+currentValue.name+"' data-channel='"+currentValue.name+"' data-type='tabAdd'>";
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
     * 初始化加载项
     */
    initChannel();  // 初始化加载渠道
});