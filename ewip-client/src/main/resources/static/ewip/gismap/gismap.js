
layui.use(["table","form","laytpl","layer"], function(){
    let table = layui.table			// 引用layui表格
        ,form = layui.form			// 引用layui表单
        ,laytpl = layui.laytpl		// 引用layui模板引擎
        ,layer = layui.layer		// 引用layui弹出层
        ,$ = layui.$   			    // 引用layui的jquery
        ,employee = layui.sessionData("ewip").employee; // 当前登录用户信息


        let code = () => {
            let name = employee.areaName;

            if(name == "望奎县") return 1;
            if(name == "通河县") return 2;
            if(name == "延寿县") return 3;
            if(name == "富锦市") return 4;
            if(name == "绥棱县") return 5;

            return 1;

        };
        // 望奎县：192.168.1.132:8011/index.html?code=1
        // 通河县：192.168.1.132:8011/index.html?code=2
        // 延寿县：192.168.1.132:8011/index.html?code=3
        // 富锦市：192.168.1.132:8011/index.html?code=4
        // 绥棱县：192.168.1.132:8011/index.html?code=5
    window.location.href="http://192.168.1.132:8011/index.html?code=" + code();
});

