"use strict"

layui.use(['form'], function(){
    var form = layui.form
        ,$ = layui.$
        ,layer = layui.layer;

    //自定义验证规则
    form.verify({
        loginName: function(value){
            if(value.length == 0){
                return '登录名称不能为空';
            }
        }
        ,loginPassword: [/(.+){6,12}$/, '密码必须是6到12位']
    });

    //监听提交
    form.on('submit(submit)', function(data){
        console.log(data)
        let param = JSON.stringify(data.field);
        console.log(param)
        $.ajax({
            async:true,
            type: "POST",
            data: data.field,
            url: "employee/login",
            dataType: "json",
            success: function(json){
                console.log(json);
                if(json.code == 200 && json.data != null){
                    window.location.href = 'index';
                }else{
                    layer.alert('用户名或密码错误');
                }
                return false;
            }
        });
    });
});