
layui.use(['table','form','laydate','element','laytpl','layer'], function(){
    let table = layui.table			// 引用layui表格
        ,form = layui.form			// 引用layui表单
        ,laytpl = layui.laytpl		// 引用layui模板引擎
        ,layer = layui.layer		// 引用layui弹出层
        ,$ = layui.$   			// 引用layui的jquery
        ,element = layui.element
        ,laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#test1' //指定元素
            ,type: 'datetime'
        });

    /**
     *
     */
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
                // $(".channel-list").empty().append(html);
            }
        }
    });


    //触发事件
    var active = {
        tabAdd: function(obj){
            console.log(obj)
            //新增一个Tab项
            element.tabAdd('ew-release-channel', {
                title: '<li><div class="ccc"><img src="../../asseat/images/'+obj.channel+'.png" alt="' + obj.title + '" title="' +obj.title+'"></div></li>'
                ,content: obj.channel
                ,id: obj.id //实际使用一般是规定好的id
            })
        }
        ,tabDelete: function(othis){
            //删除指定Tab项
            element.tabDelete('ew-release-channel', '44'); //删除：“商品管理”
            othis.addClass('layui-btn-disabled');
        }
        ,tabChange: function(){
            //切换到指定Tab项
            element.tabChange('ew-release-channel', '22'); //切换到：用户管理
        }
    };

    $('.imgbox').on('click', function(){
        var othis = $(this),
            type = othis.data('type'),
            id = othis.data('id'),
            channel = othis.data('channel')
            title = othis.data('title')

            obj = {
                othis: othis,
                id: id,
                channel:channel,
                title: title
            },

            active[type] ? active[type].call(this, obj) : '';

    });


});