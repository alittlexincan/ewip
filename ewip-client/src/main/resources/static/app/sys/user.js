layui.use(["table","form","laytpl","layer"], function(){
    var table = layui.table			// 引用layui表格
        ,form = layui.form				// 引用layui表单
        ,laytpl = layui.laytpl			// 引用layui模板引擎
        ,layer = layui.layer			// 引用layui弹出层
        ,$ = layui.$;					// 引用layui的jquery

    table.render({
        elem: "#table"
        ,url:"/client/user/select"
        ,initSort: {field:"id", type:"DESC"}
        ,even: true
        ,page:true
        ,cols: [[
            {type:"checkbox"}
            ,{field:"index",	    title: "序号", type: "numbers"}
            ,{field:"loginName", 	title: "登录名", 	sort: true}
            ,{field:"name", 	    title: "员工名称", 	sort: true}
            ,{field:"sex", 			title: "性别",      sort: true, templet: "#sexFormat"}
            ,{field:"phone", 		title: "电话号码",	sort: true}
            ,{field:"createTime", 	title: "创建时间", 	sort: true}
            ,{field: "right",       title: "操作",      align:"center", toolbar: "#option"}
        ]]
    });


    /**
     * 弹出层：编辑员工信息
     */
    var editEmployee = function(data){
        //示范一个公告层
        layer.open({
            type: 1
            ,title: "<i class='layui-icon'>&#xe642;</i> 员工编辑"
            ,area: "600px"
            ,shade: 0.4
            ,maxmin:true
            ,offset:"c"
            ,id: "update-"+data.index
            ,btn: ['保存', '取消']
            ,content:"<div id='updateEmployee-"+data.index+"' style='padding:20px 20px 0 20px'></div>"
            ,success: function(layero,index){
                // 动态获取弹出层对象
                var update = document.getElementById("updateEmployee-"+data.index);
                // 获取模板，并将数据绑定到模板，然后再弹出层中渲染
                laytpl(updateEmployeeDiv.innerHTML).render(data, function(html){
                    update.innerHTML = html;
                });
                form.render();
            }
            ,yes: function(index, layero){
                // 触发表单按钮点击事件
                $("#subbmitBtn-"+data.index).click();
                //触发表单按钮点击事件后，立刻监听form表单提交，向后台传参
                form.on("submit(subbmitBtn-"+data.index+")", function(data){
                    console.log(data.elem) //被执行事件的元素dom对象，一般为button对象
                    console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                    console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                    return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
                });
            }
        });
    };

    //监听工具条，自定义按钮（增删该查，导入导出等）
    //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    table.on("tool(table)", function(obj){
        var data = obj.data 		//获得当前行数据
            ,layEvent = obj.event; 	//获得 lay-event 对应的值
        if(layEvent === "detail"){
            console.log(data);
        } else if(layEvent === "del"){
            layer.confirm("确定要删除？", function(index){
                obj.del(); 		//删除对应行tr的DOM结构
                layer.close(index);
                //向服务端发送删除指令
            });
        } else if(layEvent === "edit"){
            editEmployee(data);
        }
    });
});