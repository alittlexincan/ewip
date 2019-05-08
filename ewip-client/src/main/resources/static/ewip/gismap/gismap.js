
layui.use(["table","form","laytpl","layer"], function(){
    let table = layui.table			// 引用layui表格
        ,form = layui.form			// 引用layui表单
        ,laytpl = layui.laytpl		// 引用layui模板引擎
        ,layer = layui.layer		// 引用layui弹出层
        ,$ = layui.$   			    // 引用layui的jquery
        ,employee = layui.sessionData("ewip").employee; // 当前登录用户信息

        // 望奎县：http://192.168.1.132:8011/index.html?code=1
        // 通河县：http://192.168.1.132:8011/index.html?code=2
        // 延寿县：http://192.168.1.132:8011/index.html?code=3
        // 富锦市：http://192.168.1.132:8011/index.html?code=4
        // 绥棱县：http://192.168.1.132:8011/index.html?code=5
    console.log(employee);
    var url=$('#home', parent.document).parent().parent().children("#gisMapUrl").val();
    if(employee.areaName.indexOf("望奎")!=-1){
        var newUrl=url+"?code=1";
        window.location.href=newUrl;
    }else if(employee.areaName.indexOf("通河")!=-1){
        var newUrl=url+"?code=2";
        window.location.href=newUrl;
    }else if(employee.areaName.indexOf("延寿")!=-1){
        var newUrl=url+"?code=3";
        window.location.href=newUrl;
    }else if(employee.areaName.indexOf("富锦")!=-1){
        var newUrl=url+"?code=4";
        console.log(newUrl);
        window.location.href=newUrl;
    }else if(employee.areaName.indexOf("绥棱")!=-1){
        var newUrl=url+"?code=5";
        window.location.href=newUrl;
    }
    // var url=$('#home', parent.document).parent().parent().children("#gisMapUrl").val();


});

