layui.use(["table","form","laytpl","layer"], function(){
    let table = layui.table			// 引用layui表格
        ,form = layui.form			// 引用layui表单
        ,laytpl = layui.laytpl		// 引用layui模板引擎
        ,layer = layui.layer		// 引用layui弹出层
        ,$ = layui.$   			// 引用layui的jquery
        ,employee = layui.sessionData("ewip").employee; // 当前登录用户信息

    let active = {
        map: null
        /**
         * 统一处理后台读取数据
         *
         * {
         *  type:"POST"
         *  ,url: ""
         *  ,data: {}查询条件
         * }
         *
         * callback 回调函数取出数据进行渲染
         *
         */
        ,render: (param,callback) => {
            $.ajax({
                async:true
                ,type: param.type
                ,data: param.data
                ,url: param.url
                ,dataType: 'json'
                ,success: function(json){
                    if(json.code == 200 && json.data != null){
                        return callback(json.data);
                    }else{
                        layer.msg("暂无数据",{time:2000});
                    }
                }
            });
        }

        /**
         * 初始化地图高度
         * @param container
         */
        ,initMapHeight: container => {
            let height  = $("body").parent().parent().height() - (50 - 15 * 2);
            $("#" + container).height(height);
        }

        ,marker:(imgUrl, jw) => {
            let myIcon = new BMap.Icon(imgUrl, new BMap.Size(38.4,31.2),{
                imageSize: new BMap.Size(38.4,31.2) // 引用图片实际大小
            });
            let marker = new BMap.Marker(new BMap.Point(jw[0],jw[1]),{icon: myIcon});  // 创建标注
            return marker;
        }

        //定义弹出窗口参数
        ,opts : {
            width : 200,     // 信息窗口宽度
            enableMessage:true//设置允许信息窗发送短息
        }
        // 望奎
        // 设置灾害影响区域
        ,points:[
            {"lng":126.3711807458,"lat":46.6132795477,"count":50},
            {"lng":126.3731807458,"lat":46.6142795477,"count":51},
            {"lng":126.3321807458,"lat":46.6134395477,"count":15},
            {"lng":126.3743507458,"lat":46.6234795477,"count":40},
            {"lng":126.3234807458,"lat":46.6132543477,"count":100},
            {"lng":126.3715457458,"lat":46.6133445477,"count":6},
            {"lng":126.3711807234,"lat":46.6164695477,"count":18},
            {"lng":126.3734337458,"lat":46.6132795477,"count":80},
            {"lng":126.3988807458,"lat":46.6133495477,"count":11},
            {"lng":126.3712337458,"lat":46.6132545477,"count":7},
            {"lng":126.3711234458,"lat":46.6132345477,"count":42},
            {"lng":126.3711236558,"lat":46.6132795477,"count":4},
            {"lng":126.3231807458,"lat":46.6132712477,"count":27},
            {"lng":126.3723407458,"lat":46.6432743232,"count":23},
            {"lng":126.3432407458,"lat":46.3432795499,"count":60},
            {"lng":127.2052346465,"lat":47.3773634473,"count":87}
        ]
        ,points1:[
            {"lng":126.8753633682,"lat":47.1084975189,"count":50},
            {"lng":126.8731807458,"lat":47.1042795477,"count":51},
            {"lng":126.8721807458,"lat":47.1034395477,"count":15},
            {"lng":126.8743507458,"lat":47.1034795477,"count":40},
            {"lng":126.8234807458,"lat":47.1032543477,"count":100},
            {"lng":126.8715457458,"lat":47.1033445477,"count":6},
            {"lng":126.8711807234,"lat":47.0964695477,"count":18},
            {"lng":126.8734337458,"lat":47.0832795477,"count":80},
            {"lng":126.8388807458,"lat":47.1033495477,"count":11},
            {"lng":126.8712337458,"lat":47.1032545477,"count":7},
            {"lng":126.8711234458,"lat":47.1032345477,"count":42},
            {"lng":126.8711236558,"lat":47.0632795477,"count":4},
            {"lng":126.8531807458,"lat":47.0532712477,"count":27},
            {"lng":126.8723407458,"lat":47.1032743232,"count":23},
            {"lng":126.8432407458,"lat":47.1032795499,"count":60},
            {"lng":126.8595701070,"lat":47.1055097453,"count":87}
        ]
        ,points2:[
            {"lng":126.8595701070,"lat":46.7955097453,"count":50},
            {"lng":126.8531807458,"lat":46.7942795477,"count":51},
            {"lng":126.8521807458,"lat":46.7934395477,"count":15},
            {"lng":126.8543507458,"lat":46.7934795477,"count":40},
            {"lng":126.8234807458,"lat":46.7932543477,"count":100},
            {"lng":126.8415457458,"lat":46.7933445477,"count":6},
            {"lng":126.8511807234,"lat":46.7964695477,"count":18},
            {"lng":126.8534337458,"lat":46.7832795477,"count":80},
            {"lng":126.8388807458,"lat":46.7933495477,"count":11},
            {"lng":126.8512337458,"lat":46.7932545477,"count":7},
            {"lng":126.8511234458,"lat":46.7932345477,"count":42},
            {"lng":126.8511236558,"lat":46.7632795477,"count":4},
            {"lng":126.8531807458,"lat":46.7532712477,"count":27},
            {"lng":126.8423407458,"lat":46.7932743232,"count":23},
            {"lng":126.8432407458,"lat":46.7932795499,"count":60},
            {"lng":126.3711807458,"lat":46.6132795477,"count":87}
        ]
        ,points3:[
            {"lng":126.6565648576,"lat":46.8287256332,"count":50},
            {"lng":126.6531807458,"lat":46.8242795477,"count":51},
            {"lng":126.6521807458,"lat":46.8234395477,"count":15},
            {"lng":126.6543507458,"lat":46.8234795477,"count":40},
            {"lng":126.6234807458,"lat":46.8232543477,"count":100},
            {"lng":126.6415457458,"lat":46.8233445477,"count":6},
            {"lng":126.6511807234,"lat":46.8264695477,"count":18},
            {"lng":126.6534337458,"lat":46.8232795477,"count":80},
            {"lng":126.6388807458,"lat":46.8233495477,"count":11},
            {"lng":126.6512337458,"lat":46.8232545477,"count":7},
            {"lng":126.6511234458,"lat":46.8232345477,"count":42},
            {"lng":126.6511236558,"lat":46.8232795477,"count":4},
            {"lng":126.6531807458,"lat":46.8232712477,"count":27},
            {"lng":126.6423407458,"lat":46.8232743232,"count":23},
            {"lng":126.6432407458,"lat":46.8232795499,"count":60},
            {"lng":126.4321890687,"lat":46.8196796167,"count":87}
        ]

        //绥棱
        //设置灾害影响区域
        // ,points:[
        //     {"lng":127.3711807458,"lat":47.6132795477,"count":50},
        //     {"lng":127.3731807458,"lat":47.6142795477,"count":51},
        //     {"lng":127.3321807458,"lat":47.6134395477,"count":15},
        //     {"lng":127.3743507458,"lat":47.6234795477,"count":40},
        //     {"lng":127.3234807458,"lat":47.6132543477,"count":100},
        //     {"lng":127.3715457458,"lat":47.6133445477,"count":6},
        //     {"lng":127.3711807234,"lat":47.6164695477,"count":18},
        //     {"lng":127.3734337458,"lat":47.6132795477,"count":80},
        //     {"lng":127.3988807458,"lat":47.6133495477,"count":11},
        //     {"lng":127.3712337458,"lat":47.6132545477,"count":7},
        //     {"lng":127.3711234458,"lat":47.6132345477,"count":42},
        //     {"lng":127.3711236558,"lat":47.6132795477,"count":4},
        //     {"lng":127.3231807458,"lat":47.6132712477,"count":27},
        //     {"lng":127.3723407458,"lat":47.6432743232,"count":23},
        //     {"lng":127.3432407458,"lat":47.3432795499,"count":60},
        //     {"lng":127.2052346465,"lat":47.3773634473,"count":87}
        // ]
        // ,points1:[
        //     {"lng":127.8753633682,"lat":47.1084975189,"count":50},
        //     {"lng":127.8731807458,"lat":47.1042795477,"count":51},
        //     {"lng":127.8721807458,"lat":47.1034395477,"count":15},
        //     {"lng":127.8743507458,"lat":47.1034795477,"count":40},
        //     {"lng":127.8234807458,"lat":47.1032543477,"count":100},
        //     {"lng":127.8715457458,"lat":47.1033445477,"count":6},
        //     {"lng":127.8711807234,"lat":47.0964695477,"count":18},
        //     {"lng":127.8734337458,"lat":47.0832795477,"count":80},
        //     {"lng":127.8388807458,"lat":47.1033495477,"count":11},
        //     {"lng":127.8712337458,"lat":47.1032545477,"count":7},
        //     {"lng":127.8711234458,"lat":47.1032345477,"count":42},
        //     {"lng":127.8711236558,"lat":47.0632795477,"count":4},
        //     {"lng":127.8531807458,"lat":47.0532712477,"count":27},
        //     {"lng":127.8723407458,"lat":47.1032743232,"count":23},
        //     {"lng":127.8432407458,"lat":47.1032795499,"count":60},
        //     {"lng":127.8595701070,"lat":47.1055097453,"count":87}
        // ]
        // ,points2:[
        //     {"lng":127.8595701070,"lat":47.7955097453,"count":50},
        //     {"lng":127.8531807458,"lat":47.7942795477,"count":51},
        //     {"lng":127.8521807458,"lat":47.7934395477,"count":15},
        //     {"lng":127.8543507458,"lat":47.7934795477,"count":40},
        //     {"lng":127.8234807458,"lat":47.7932543477,"count":100},
        //     {"lng":127.8415457458,"lat":47.7933445477,"count":6},
        //     {"lng":127.8511807234,"lat":47.7964695477,"count":18},
        //     {"lng":127.8534337458,"lat":47.7832795477,"count":80},
        //     {"lng":127.8388807458,"lat":47.7933495477,"count":11},
        //     {"lng":127.8512337458,"lat":47.7932545477,"count":7},
        //     {"lng":127.8511234458,"lat":47.7932345477,"count":42},
        //     {"lng":127.8511236558,"lat":47.7632795477,"count":4},
        //     {"lng":127.8531807458,"lat":47.7532712477,"count":27},
        //     {"lng":127.8423407458,"lat":47.7932743232,"count":23},
        //     {"lng":127.8432407458,"lat":47.7932795499,"count":60},
        //     {"lng":127.3711807458,"lat":47.6132795477,"count":87}
        // ]
        // ,points3:[
        //     {"lng":127.6565648576,"lat":47.8287256332,"count":50},
        //     {"lng":127.6531807458,"lat":47.8242795477,"count":51},
        //     {"lng":127.6521807458,"lat":47.8234395477,"count":15},
        //     {"lng":127.6543507458,"lat":47.8234795477,"count":40},
        //     {"lng":127.6234807458,"lat":47.8232543477,"count":100},
        //     {"lng":127.6415457458,"lat":47.8233445477,"count":6},
        //     {"lng":127.6511807234,"lat":47.8264695477,"count":18},
        //     {"lng":127.6534337458,"lat":47.8232795477,"count":80},
        //     {"lng":127.6388807458,"lat":47.8233495477,"count":11},
        //     {"lng":127.6512337458,"lat":47.8232545477,"count":7},
        //     {"lng":127.6511234458,"lat":47.8232345477,"count":42},
        //     {"lng":127.6511236558,"lat":47.8232795477,"count":4},
        //     {"lng":127.6531807458,"lat":47.8232712477,"count":27},
        //     {"lng":127.6423407458,"lat":47.8232743232,"count":23},
        //     {"lng":127.6432407458,"lat":47.8232795499,"count":60},
        //     {"lng":127.4321890687,"lat":47.8196796167,"count":87}
        // ]

        //通河
        //设置灾害影响区域
        // ,points:[
        //     {"lng":128.3711807458,"lat":46.6132795477,"count":50},
        //     {"lng":128.3731807458,"lat":46.6142795477,"count":51},
        //     {"lng":128.3321807458,"lat":46.6134395477,"count":15},
        //     {"lng":128.3743507458,"lat":46.6234795477,"count":40},
        //     {"lng":128.3234807458,"lat":46.6132543477,"count":100},
        //     {"lng":128.3715457458,"lat":46.6133445477,"count":6},
        //     {"lng":128.3711807234,"lat":46.6164695477,"count":18},
        //     {"lng":128.3734337458,"lat":46.6132795477,"count":80},
        //     {"lng":128.3988807458,"lat":46.6133495477,"count":11},
        //     {"lng":128.3712337458,"lat":46.6132545477,"count":7},
        //     {"lng":128.3711234458,"lat":46.6132345477,"count":42},
        //     {"lng":128.3711236558,"lat":46.6132795477,"count":4},
        //     {"lng":128.3231807458,"lat":46.6132712477,"count":27},
        //     {"lng":128.3723407458,"lat":46.6432743232,"count":23},
        //     {"lng":128.3432407458,"lat":46.3432795499,"count":60},
        //     {"lng":128.2052346465,"lat":46.3773634473,"count":87}
        // ]
        // ,points1:[
        //     {"lng":128.8753633682,"lat":46.1084975189,"count":50},
        //     {"lng":128.8731807458,"lat":46.1042795477,"count":51},
        //     {"lng":128.8721807458,"lat":46.1034395477,"count":15},
        //     {"lng":128.8743507458,"lat":46.1034795477,"count":40},
        //     {"lng":128.8234807458,"lat":46.1032543477,"count":100},
        //     {"lng":128.8715457458,"lat":46.1033445477,"count":6},
        //     {"lng":128.8711807234,"lat":46.0964695477,"count":18},
        //     {"lng":128.8734337458,"lat":46.0832795477,"count":80},
        //     {"lng":128.8388807458,"lat":46.1033495477,"count":11},
        //     {"lng":128.8712337458,"lat":46.1032545477,"count":7},
        //     {"lng":128.8711234458,"lat":46.1032345477,"count":42},
        //     {"lng":128.8711236558,"lat":46.0632795477,"count":4},
        //     {"lng":128.8531807458,"lat":46.0532712477,"count":27},
        //     {"lng":128.8723407458,"lat":46.1032743232,"count":23},
        //     {"lng":128.8432407458,"lat":46.1032795499,"count":60},
        //     {"lng":128.8595701070,"lat":46.1055097453,"count":87}
        // ]
        // ,points2:[
        //     {"lng":128.8595701070,"lat":46.7955097453,"count":50},
        //     {"lng":128.8531807458,"lat":46.7942795477,"count":51},
        //     {"lng":128.8521807458,"lat":46.7934395477,"count":15},
        //     {"lng":128.8543507458,"lat":46.7934795477,"count":40},
        //     {"lng":128.8234807458,"lat":46.7932543477,"count":100},
        //     {"lng":128.8415457458,"lat":46.7933445477,"count":6},
        //     {"lng":128.8511807234,"lat":46.7964695477,"count":18},
        //     {"lng":128.8534337458,"lat":46.7832795477,"count":80},
        //     {"lng":128.8388807458,"lat":46.7933495477,"count":11},
        //     {"lng":128.8512337458,"lat":46.7932545477,"count":7},
        //     {"lng":128.8511234458,"lat":46.7932345477,"count":42},
        //     {"lng":128.8511236558,"lat":46.7632795477,"count":4},
        //     {"lng":128.8531807458,"lat":46.7532712477,"count":27},
        //     {"lng":128.8423407458,"lat":46.7932743232,"count":23},
        //     {"lng":128.8432407458,"lat":46.7932795499,"count":60},
        //     {"lng":128.3711807458,"lat":46.6132795477,"count":87}
        // ]
        // ,points3:[
        //     {"lng":128.6565648576,"lat":46.8287256332,"count":50},
        //     {"lng":128.6531807458,"lat":46.8242795477,"count":51},
        //     {"lng":128.6521807458,"lat":46.8234395477,"count":15},
        //     {"lng":128.6543507458,"lat":46.8234795477,"count":40},
        //     {"lng":128.6234807458,"lat":46.8232543477,"count":100},
        //     {"lng":128.6415457458,"lat":46.8233445477,"count":6},
        //     {"lng":128.6511807234,"lat":46.8264695477,"count":18},
        //     {"lng":128.6534337458,"lat":46.8232795477,"count":80},
        //     {"lng":128.6388807458,"lat":46.8233495477,"count":11},
        //     {"lng":128.6512337458,"lat":46.8232545477,"count":7},
        //     {"lng":128.6511234458,"lat":46.8232345477,"count":42},
        //     {"lng":128.6511236558,"lat":46.8232795477,"count":4},
        //     {"lng":128.6531807458,"lat":46.8232712477,"count":27},
        //     {"lng":128.6423407458,"lat":46.8232743232,"count":23},
        //     {"lng":128.6432407458,"lat":46.8232795499,"count":60},
        //     {"lng":128.4321890687,"lat":46.8196796167,"count":87}
        // ]


        //富锦
        //设置灾害影响区域
        // ,points:[
        //     {"lng":131.3711807458,"lat":47.6132795477,"count":50},
        //     {"lng":131.3731807458,"lat":47.6142795477,"count":51},
        //     {"lng":131.3321807458,"lat":47.6134395477,"count":15},
        //     {"lng":131.3743507458,"lat":47.6234795477,"count":40},
        //     {"lng":131.3234807458,"lat":47.6132543477,"count":100},
        //     {"lng":131.3715457458,"lat":47.6133445477,"count":6},
        //     {"lng":131.3711807234,"lat":47.6164695477,"count":18},
        //     {"lng":131.3734337458,"lat":47.6132795477,"count":80},
        //     {"lng":131.3988807458,"lat":47.6133495477,"count":11},
        //     {"lng":131.3712337458,"lat":47.6132545477,"count":7},
        //     {"lng":131.3711234458,"lat":47.6132345477,"count":42},
        //     {"lng":131.3711236558,"lat":47.6132795477,"count":4},
        //     {"lng":131.3231807458,"lat":47.6132712477,"count":27},
        //     {"lng":131.3723407458,"lat":47.6432743232,"count":23},
        //     {"lng":131.3432407458,"lat":47.3432795499,"count":60},
        //     {"lng":127.2052346465,"lat":47.3773634473,"count":87}
        // ]
        // ,points1:[
        //     {"lng":131.8753633682,"lat":47.1084975189,"count":50},
        //     {"lng":131.8731807458,"lat":47.1042795477,"count":51},
        //     {"lng":131.8721807458,"lat":47.1034395477,"count":15},
        //     {"lng":131.8743507458,"lat":47.1034795477,"count":40},
        //     {"lng":131.8234807458,"lat":47.1032543477,"count":100},
        //     {"lng":131.8715457458,"lat":47.1033445477,"count":6},
        //     {"lng":131.8711807234,"lat":47.0964695477,"count":18},
        //     {"lng":131.8734337458,"lat":47.0832795477,"count":80},
        //     {"lng":131.8388807458,"lat":47.1033495477,"count":11},
        //     {"lng":131.8712337458,"lat":47.1032545477,"count":7},
        //     {"lng":131.8711234458,"lat":47.1032345477,"count":42},
        //     {"lng":131.8711236558,"lat":47.0632795477,"count":4},
        //     {"lng":131.8531807458,"lat":47.0532712477,"count":27},
        //     {"lng":131.8723407458,"lat":47.1032743232,"count":23},
        //     {"lng":131.8432407458,"lat":47.1032795499,"count":60},
        //     {"lng":131.8595701070,"lat":47.1055097453,"count":87}
        // ]
        // ,points2:[
        //     {"lng":131.8595701070,"lat":47.7955097453,"count":50},
        //     {"lng":131.8531807458,"lat":47.7942795477,"count":51},
        //     {"lng":131.8521807458,"lat":47.7934395477,"count":15},
        //     {"lng":131.8543507458,"lat":47.7934795477,"count":40},
        //     {"lng":131.8234807458,"lat":47.7932543477,"count":100},
        //     {"lng":131.8415457458,"lat":47.7933445477,"count":6},
        //     {"lng":131.8511807234,"lat":47.7964695477,"count":18},
        //     {"lng":131.8534337458,"lat":47.7832795477,"count":80},
        //     {"lng":131.8388807458,"lat":47.7933495477,"count":11},
        //     {"lng":131.8512337458,"lat":47.7932545477,"count":7},
        //     {"lng":131.8511234458,"lat":47.7932345477,"count":42},
        //     {"lng":131.8511236558,"lat":47.7632795477,"count":4},
        //     {"lng":131.8531807458,"lat":47.7532712477,"count":27},
        //     {"lng":131.8423407458,"lat":47.7932743232,"count":23},
        //     {"lng":131.8432407458,"lat":47.7932795499,"count":60},
        //     {"lng":131.3711807458,"lat":47.6132795477,"count":87}
        // ]
        // ,points3:[
        //     {"lng":131.6565648576,"lat":47.8287256332,"count":50},
        //     {"lng":131.6531807458,"lat":47.8242795477,"count":51},
        //     {"lng":131.6521807458,"lat":47.8234395477,"count":15},
        //     {"lng":131.6543507458,"lat":47.8234795477,"count":40},
        //     {"lng":131.6234807458,"lat":47.8232543477,"count":100},
        //     {"lng":131.6415457458,"lat":47.8233445477,"count":6},
        //     {"lng":131.6511807234,"lat":47.8264695477,"count":18},
        //     {"lng":131.6534337458,"lat":47.8232795477,"count":80},
        //     {"lng":131.6388807458,"lat":47.8233495477,"count":11},
        //     {"lng":131.6512337458,"lat":47.8232545477,"count":7},
        //     {"lng":131.6511234458,"lat":47.8232345477,"count":42},
        //     {"lng":131.6511236558,"lat":47.8232795477,"count":4},
        //     {"lng":131.6531807458,"lat":47.8232712477,"count":27},
        //     {"lng":131.6423407458,"lat":47.8232743232,"count":23},
        //     {"lng":131.6432407458,"lat":47.8232795499,"count":60},
        //     {"lng":131.4321890687,"lat":47.8196796167,"count":87}
        // ]

        //延寿
        //设置灾害影响区域
        // ,points:[
        //     {"lng":128.3711807458,"lat":45.6132795477,"count":50},
        //     {"lng":128.3731807458,"lat":45.6142795477,"count":51},
        //     {"lng":128.3321807458,"lat":45.6134395477,"count":15},
        //     {"lng":128.3743507458,"lat":45.6234795477,"count":40},
        //     {"lng":128.3234807458,"lat":45.6132543477,"count":100},
        //     {"lng":128.3715457458,"lat":45.6133445477,"count":6},
        //     {"lng":128.3711807234,"lat":45.6164695477,"count":18},
        //     {"lng":128.3734337458,"lat":45.6132795477,"count":80},
        //     {"lng":128.3988807458,"lat":45.6133495477,"count":11},
        //     {"lng":128.3712337458,"lat":45.6132545477,"count":7},
        //     {"lng":128.3711234458,"lat":45.6132345477,"count":42},
        //     {"lng":128.3711236558,"lat":45.6132795477,"count":4},
        //     {"lng":128.3231807458,"lat":45.6132712477,"count":27},
        //     {"lng":128.3723407458,"lat":45.6432743232,"count":23},
        //     {"lng":128.3432407458,"lat":45.3432795499,"count":60},
        //     {"lng":128.2052346465,"lat":45.3773634473,"count":87}
        // ]
        // ,points1:[
        //     {"lng":128.8753633682,"lat":45.1084975189,"count":50},
        //     {"lng":128.8731807458,"lat":45.1042795477,"count":51},
        //     {"lng":128.8721807458,"lat":45.1034395477,"count":15},
        //     {"lng":128.8743507458,"lat":45.1034795477,"count":40},
        //     {"lng":128.8234807458,"lat":45.1032543477,"count":100},
        //     {"lng":128.8715457458,"lat":45.1033445477,"count":6},
        //     {"lng":128.8711807234,"lat":45.0964695477,"count":18},
        //     {"lng":128.8734337458,"lat":45.0832795477,"count":80},
        //     {"lng":128.8388807458,"lat":45.1033495477,"count":11},
        //     {"lng":128.8712337458,"lat":45.1032545477,"count":7},
        //     {"lng":128.8711234458,"lat":45.1032345477,"count":42},
        //     {"lng":128.8711236558,"lat":45.0632795477,"count":4},
        //     {"lng":128.8531807458,"lat":45.0532712477,"count":27},
        //     {"lng":128.8723407458,"lat":45.1032743232,"count":23},
        //     {"lng":128.8432407458,"lat":45.1032795499,"count":60},
        //     {"lng":128.8595701070,"lat":45.1055097453,"count":87}
        // ]
        // ,points2:[
        //     {"lng":128.8595701070,"lat":45.7955097453,"count":50},
        //     {"lng":128.8531807458,"lat":45.7942795477,"count":51},
        //     {"lng":128.8521807458,"lat":45.7934395477,"count":15},
        //     {"lng":128.8543507458,"lat":45.7934795477,"count":40},
        //     {"lng":128.8234807458,"lat":45.7932543477,"count":100},
        //     {"lng":128.8415457458,"lat":45.7933445477,"count":6},
        //     {"lng":128.8511807234,"lat":45.7964695477,"count":18},
        //     {"lng":128.8534337458,"lat":45.7832795477,"count":80},
        //     {"lng":128.8388807458,"lat":45.7933495477,"count":11},
        //     {"lng":128.8512337458,"lat":45.7932545477,"count":7},
        //     {"lng":128.8511234458,"lat":45.7932345477,"count":42},
        //     {"lng":128.8511236558,"lat":45.7632795477,"count":4},
        //     {"lng":128.8531807458,"lat":45.7532712477,"count":27},
        //     {"lng":128.8423407458,"lat":45.7932743232,"count":23},
        //     {"lng":128.8432407458,"lat":45.7932795499,"count":60},
        //     {"lng":128.3711807458,"lat":45.6132795477,"count":87}
        // ]
        // ,points3:[
        //     {"lng":128.6565648576,"lat":45.8287256332,"count":50},
        //     {"lng":128.6531807458,"lat":45.8242795477,"count":51},
        //     {"lng":128.6521807458,"lat":45.8234395477,"count":15},
        //     {"lng":128.6543507458,"lat":45.8234795477,"count":40},
        //     {"lng":128.6234807458,"lat":45.8232543477,"count":100},
        //     {"lng":128.6415457458,"lat":45.8233445477,"count":6},
        //     {"lng":128.6511807234,"lat":45.8264695477,"count":18},
        //     {"lng":128.6534337458,"lat":45.8232795477,"count":80},
        //     {"lng":128.6388807458,"lat":45.8233495477,"count":11},
        //     {"lng":128.6512337458,"lat":45.8232545477,"count":7},
        //     {"lng":128.6511234458,"lat":45.8232345477,"count":42},
        //     {"lng":128.6511236558,"lat":45.8232795477,"count":4},
        //     {"lng":128.6531807458,"lat":45.8232712477,"count":27},
        //     {"lng":128.6423407458,"lat":45.8232743232,"count":23},
        //     {"lng":128.6432407458,"lat":45.8232795499,"count":60},
        //     {"lng":128.4321890687,"lat":45.8196796167,"count":87}
        // ]


        //定义气象预警
        ,markerArrWarn:[
            {title: "寒潮黄色预警", point: "126.492496,46.839156", mes: "<div><div>预计我县24小时内最低气温将要下降10℃以上，最低气温小于等于4℃，陆地平均风力可达6级以上；或者已经下降10℃以上，最低气温小于等于4℃，平均风力达6级以上，并可能持续。</div><hr /><div>望奎县气象局2018年11月5日10时发布</div>",addss: "/client/base/hanchaohuangse.gif"}

        ]

        //单击图层
        ,addClickHandler: (content, marker) => {
            marker.addEventListener("click",function(e){
                active.openInfo(content,e);
            });
        }
        //图层弹框消息
        ,openInfo: (content,e) => {
            var p = e.target;
            var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
            var infoWindow = new BMap.InfoWindow(content, active.opts);  // 创建信息窗口对象
            active.map.openInfoWindow(infoWindow, point);
        }

        /**
         * 百度地图初始化
         */
        ,initBMap: (container, initMapHeight) => {
            initMapHeight(container);
            // 百度地图API功能
            active.map = new BMap.Map(container);    // 创建Map实例
            active.map.centerAndZoom(new BMap.Point(107.98,36.47), 11);  // 初始化地图,设置中心点坐标和地图级别
            //添加地图类型控件
            active.map.addControl(new BMap.MapTypeControl({
                mapTypes:[
                    BMAP_NORMAL_MAP,
                    BMAP_HYBRID_MAP
                ]}));
            active.map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
            //设置望奎县边界
            var name=employee.areaName;
            var bdary = new BMap.Boundary();
            bdary.get(name, function(rs){//获取行政区域
                //BDmap.clearOverlays();        //清除地图覆盖物
                var count = rs.boundaries.length; //行政区域的点有多少个
                if (count === 0) {
                    alert('未能获取当前输入行政区域');
                    return ;
                }
                var pointArray = [];
                for (var i = 0; i < count; i++) {
                    var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 3, fillColor:"",strokeColor: "#ff0000"}); //建立多边形覆盖物
                    active.map.addOverlay(ply);  //添加覆盖物
                    pointArray = pointArray.concat(ply.getPath());
                }
                active.map.setViewport(pointArray);    //调整视野
            });
        }
    }



    /**
     * 点击向右图标
     */
    $("#rightId").bind("click", () => {
        $("#ass").hide();
        //$("#ass").animate({right:'-130px'});
        $("#rightId").hide();
        $("#leftId").show();
    });

    /**
     * 点击向左图标
     */
    $("#leftId").bind("click", () => {
        $("#ass").show();
        //$("#ass").animate({left:'-50px'});
        $("#rightId").show();
        $("#leftId").hide();
    });

    /**
     * 点击历史气象灾情菜单
     */
    let lishiArrayMarker=new Array();
    $("#hisWarnId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/disasterHistory/list", data:{}}, data => {
                var colorName="";
                var level="";
                var damage="";
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/' + chineseToPinYin(res.disasterType+res.color)+'.gif';
                        let mes = '<div>灾害名称:'+res.disasterName+'</div><hr />' +
                            '<div>灾种类别:'+res.disasterType+'</div><hr />' +
                            '<div>灾种颜色:'+res.color+'</div><hr />' +
                            '<div>灾种级别:'+res.level+'</div><hr />' +
                            '<div>危害程度:'+res.damage+'</div><hr />' +
                            '<div>影响范围:'+res.influence+'</div><hr />' +
                            '<div>监测单位:'+res.monitorOrgan+'</div><hr />';
                        let marker = active.marker(iconUrl ,[res.lon , res.lat]
                            ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.disasterName + '</span>'  +'<br/>'+ mes);
                        // 将标注添加到地图中
                        active.map.addOverlay(marker);
                        lishiArrayMarker.push(marker);
                        //信息弹出框
                        active.addClickHandler(content,marker);
                    });
                });
            // });
        }else{
            $(this).children("input").removeAttr("checked");
            for(let i=0; i<lishiArrayMarker.length; i++){
                active.map.removeOverlay(lishiArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击地质灾害隐患点菜单
     */
    let dizhiArrayMarker=new Array();
    $("#dizhizaihaiId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/riskGeologic/list", data:{}}, data => {
                data.forEach( res => {
                    let iconUrl = '/client/base/dizhizaihai.png';
                    let mes = '<div>气象致灾因子:'+res.weatherCauses+'</div><br />';
                    mes += '<div>区县名称:'+res.areaName+'</div><br />';
                    mes += '<div>街道办:'+res.street+'</div><br />';
                    mes += '<div>地区名称:'+res.name+'</div><br />';
                    mes += '<div>灾害点规模:'+res.scale+'</div><br />';
                    mes += '<div>等级:'+res.level+'</div><br />';
                    mes += '<div>灾害点类型:'+res.type+'</div><br />';
                    mes += '<div>威胁人口:'+res.threadPeople+'</div><br />';
                    mes += '<div>威胁资产:'+res.threadProperty+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    dizhiArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<dizhiArrayMarker.length;i++){
                active.map.removeOverlay(dizhiArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击中小河流洪水菜单
     */
    let heliuHongShuiArrayMarker=new Array();
    $("#zhongxiaoheliuId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/riskFlood/list", data:{}}, data => {
                console.log(data);
                //{title: "海丰镇恭头一村闫家大窝棚屯", point: "126.8897242556,46.8162732895", mes: "<div>灾害点类型:崩塌</div><hr /><div>区县:绥化市望奎县</div><hr /><div>灾害点规模:小型</div><hr /><div>稳定性:不稳定</div><hr /><div>威胁人口:370</div><hr /><div>威胁资产:20000000</div><hr /><div>气象致灾因子:暴雨</div>",addss: "/client/base/dizhizaihai.png"},
                data.forEach( res => {
                    let iconUrl = '/client/base/zhongxiaoheliu.png';
                    let mes = '<div>河流名称:'+res.name+'</div><br />';
                    mes += '<div>省名称:'+res.province+'</div><br />';
                    mes += '<div>省代码:'+res.provinceCode+'</div><br />';
                    mes += '<div>市名称:'+res.city+'</div><br />';
                    mes += '<div>市代码:'+res.cityCode+'</div><br />';
                    mes += '<div>区县名称:'+res.areaName+'</div><br />';
                    mes += '<div>区县代码:'+res.districtCode+'</div><br />';
                    mes += '<div>管理单位:'+res.monitorOrgan+'</div><br />';
                    mes += '<div>联系人:'+res.monitorPeople+'</div><br />';
                    mes += '<div>防御措施:'+res.measures+'</div><br />';
                    mes += '<div>气象致灾因子:'+res.weatherCauses+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    heliuHongShuiArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<heliuHongShuiArrayMarker.length;i++){
                active.map.removeOverlay(heliuHongShuiArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击山洪菜单
     */
    let shanhongHongShuiArrayMarker=new Array();
    $("#shanhongId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/riskMountain/list", data:{}}, data => {
                console.log(data);
                //{title: "海丰镇恭头一村闫家大窝棚屯", point: "126.8897242556,46.8162732895", mes: "<div>灾害点类型:崩塌</div><hr /><div>区县:绥化市望奎县</div><hr /><div>灾害点规模:小型</div><hr /><div>稳定性:不稳定</div><hr /><div>威胁人口:370</div><hr /><div>威胁资产:20000000</div><hr /><div>气象致灾因子:暴雨</div>",addss: "/client/base/dizhizaihai.png"},
                data.forEach( res => {
                    let iconUrl = '/client/base/shanhong.png';
                    let mes = '<div>河流名称:'+res.name+'</div><br />';
                    mes += '<div>省名称:'+res.province+'</div><br />';
                    mes += '<div>省代码:'+res.provinceCode+'</div><br />';
                    mes += '<div>市名称:'+res.city+'</div><br />';
                    mes += '<div>市代码:'+res.cityCode+'</div><br />';
                    mes += '<div>区县名称:'+res.areaName+'</div><br />';
                    mes += '<div>区县代码:'+res.districtCode+'</div><br />';
                    mes += '<div>管理单位:'+res.monitorOrgan+'</div><br />';
                    mes += '<div>联系人:'+res.monitorPeople+'</div><br />';
                    mes += '<div>防御措施:'+res.measures+'</div><br />';
                    mes += '<div>危害等级:'+res.level+'</div><br />';
                    mes += '<div>气象致灾因子:'+res.weatherCauses+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    shanhongHongShuiArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<shanhongHongShuiArrayMarker.length;i++){
                active.map.removeOverlay(shanhongHongShuiArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击内涝菜单
     */
    let neilaoArrayMarker=new Array();
    $("#neilaoId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/riskWaterlogging/list", data:{}}, data => {
                console.log(data);
                //{title: "海丰镇恭头一村闫家大窝棚屯", point: "126.8897242556,46.8162732895", mes: "<div>灾害点类型:崩塌</div><hr /><div>区县:绥化市望奎县</div><hr /><div>灾害点规模:小型</div><hr /><div>稳定性:不稳定</div><hr /><div>威胁人口:370</div><hr /><div>威胁资产:20000000</div><hr /><div>气象致灾因子:暴雨</div>",addss: "/client/base/dizhizaihai.png"},
                data.forEach( res => {
                    let iconUrl = '/client/base/neilao.png';
                    let mes = '<div>内涝名称:'+res.name+'</div><br />';
                    mes += '<div>省名称:'+res.province+'</div><br />';
                    mes += '<div>省代码:'+res.provinceCode+'</div><br />';
                    mes += '<div>市名称:'+res.city+'</div><br />';
                    mes += '<div>市代码:'+res.cityCode+'</div><br />';
                    mes += '<div>区县名称:'+res.areaName+'</div><br />';
                    mes += '<div>区县代码:'+res.districtCode+'</div><br />';
                    mes += '<div>管理单位:'+res.monitorOrgan+'</div><br />';
                    mes += '<div>联系人:'+res.monitorPeople+'</div><br />';
                    mes += '<div>开始时间:'+res.startTime+'</div><br />';
                    mes += '<div>结束时间:'+res.endTime+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    neilaoArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<neilaoArrayMarker.length;i++){
                active.map.removeOverlay(neilaoArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击易涝区菜单
     */
    let yilaoArrayMarker=new Array();
    $("#yilaoquId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/riskWaterloggingArea/list", data:{}}, data => {
                console.log(data);
                //{title: "海丰镇恭头一村闫家大窝棚屯", point: "126.8897242556,46.8162732895", mes: "<div>灾害点类型:崩塌</div><hr /><div>区县:绥化市望奎县</div><hr /><div>灾害点规模:小型</div><hr /><div>稳定性:不稳定</div><hr /><div>威胁人口:370</div><hr /><div>威胁资产:20000000</div><hr /><div>气象致灾因子:暴雨</div>",addss: "/client/base/dizhizaihai.png"},
                data.forEach( res => {
                    let iconUrl = '/client/base/yilaoqu.png';
                    let mes = '<div>内涝名称:'+res.name+'</div><br />';
                    mes += '<div>省名称:'+res.province+'</div><br />';
                    mes += '<div>省代码:'+res.provinceCode+'</div><br />';
                    mes += '<div>市名称:'+res.city+'</div><br />';
                    mes += '<div>市代码:'+res.cityCode+'</div><br />';
                    mes += '<div>区县名称:'+res.areaName+'</div><br />';
                    mes += '<div>区县代码:'+res.districtCode+'</div><br />';
                    mes += '<div>管理单位:'+res.monitorOrgan+'</div><br />';
                    mes += '<div>联系人:'+res.monitorPeople+'</div><br />';
                    mes += '<div>防御措施:'+res.measures+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    yilaoArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<yilaoArrayMarker.length;i++){
                active.map.removeOverlay(yilaoArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击陡坡菜单
     */
    let doupoArrayMarker=new Array();
    $("#doupoId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/riskSlope/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/huapo.png';
                    let mes = '<div>内涝名称:'+res.name+'</div><br />';
                    mes += '<div>省名称:'+res.province+'</div><br />';
                    mes += '<div>省代码:'+res.provinceCode+'</div><br />';
                    mes += '<div>市名称:'+res.city+'</div><br />';
                    mes += '<div>市代码:'+res.cityCode+'</div><br />';
                    mes += '<div>区县名称:'+res.areaName+'</div><br />';
                    mes += '<div>区县代码:'+res.districtCode+'</div><br />';
                    mes += '<div>管理单位:'+res.monitorOrgan+'</div><br />';
                    mes += '<div>联系人:'+res.monitorPeople+'</div><br />';
                    mes += '<div>灾害点规模:'+res.scale+'</div><br />';
                    mes += '<div>稳定性:'+res.stability+'</div><br />';
                    mes += '<div>灾害点类型:'+res.type+'</div><br />';
                    mes += '<div>气象致灾因子:'+res.weatherCauses+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    doupoArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<doupoArrayMarker.length;i++){
                active.map.removeOverlay(doupoArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击洼地菜单
     */
    let wadiArrayMarker=new Array();
    $("#wadiId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/riskDepression/list", data:{}}, data => {
                console.log(data);
                //{title: "海丰镇恭头一村闫家大窝棚屯", point: "126.8897242556,46.8162732895", mes: "<div>灾害点类型:崩塌</div><hr /><div>区县:绥化市望奎县</div><hr /><div>灾害点规模:小型</div><hr /><div>稳定性:不稳定</div><hr /><div>威胁人口:370</div><hr /><div>威胁资产:20000000</div><hr /><div>气象致灾因子:暴雨</div>",addss: "/client/base/dizhizaihai.png"},
                data.forEach( res => {
                    let iconUrl = '/client/base/wadi.png';
                    let mes = '<div>内涝名称:'+res.name+'</div><br />';
                    mes += '<div>省名称:'+res.province+'</div><br />';
                    mes += '<div>省代码:'+res.provinceCode+'</div><br />';
                    mes += '<div>市名称:'+res.city+'</div><br />';
                    mes += '<div>市代码:'+res.cityCode+'</div><br />';
                    mes += '<div>区县名称:'+res.areaName+'</div><br />';
                    mes += '<div>区县代码:'+res.districtCode+'</div><br />';
                    mes += '<div>管理单位:'+res.monitorOrgan+'</div><br />';
                    mes += '<div>联系人:'+res.monitorPeople+'</div><br />';
                    mes += '<div>灾害点规模:'+res.scale+'</div><br />';
                    mes += '<div>稳定性:'+res.stability+'</div><br />';
                    mes += '<div>灾害点类型:'+res.type+'</div><br />';
                    mes += '<div>气象致灾因子:'+res.weatherCauses+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    wadiArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<wadiArrayMarker.length;i++){
                active.map.removeOverlay(wadiArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击实时灾情菜单
     */
    $("#zaiqingId").bind("click", function(){
        let flag = $(this).data("flag");
        let b  = document.getElementById('myCarousel');
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            b.style.display = "block";
        }else{
            $(this).data("flag",0);
            $(this).children("input").removeAttr("checked");
            b.style.display = "none";
        }
    });

    /**
     * 点击气象灾害影响范围菜单
     */
    let heatmapOverlayBak = new BMapLib.HeatmapOverlay({"radius":20});
    let heatmapOverlayBak1 = new BMapLib.HeatmapOverlay({"radius":20});
    let heatmapOverlayBak2 = new BMapLib.HeatmapOverlay({"radius":20});
    let heatmapOverlayBak3 = new BMapLib.HeatmapOverlay({"radius":20});
    let heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":20});
    let heatmapOverlay1 = new BMapLib.HeatmapOverlay({"radius":20});
    let heatmapOverlay2 = new BMapLib.HeatmapOverlay({"radius":20});
    let heatmapOverlay3 = new BMapLib.HeatmapOverlay({"radius":20});
    $("#disasterScopeId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.map.addOverlay(heatmapOverlay);
            active.map.addOverlay(heatmapOverlay1);
            active.map.addOverlay(heatmapOverlay2);
            active.map.addOverlay(heatmapOverlay3);
            heatmapOverlay.setDataSet({data:active.points,max:100});
            heatmapOverlay1.setDataSet({data:active.points1,max:100});
            heatmapOverlay2.setDataSet({data:active.points2,max:100});
            heatmapOverlay3.setDataSet({data:active.points3,max:100});
            heatmapOverlayBak = heatmapOverlay;
            heatmapOverlayBak1 = heatmapOverlay1;
            heatmapOverlayBak2 = heatmapOverlay2;
            heatmapOverlayBak3 = heatmapOverlay3;
        }else{
            $(this).children("input").removeAttr("checked");
            active.map.removeOverlay(heatmapOverlayBak);
            active.map.removeOverlay(heatmapOverlayBak1);
            active.map.removeOverlay(heatmapOverlayBak2);
            active.map.removeOverlay(heatmapOverlayBak3);
            $(this).data("flag","0");
        }
    });

    /**
     * 点击危化品菜单
     */
    let weihuapinArrayMarker=new Array();
    $("#weihuapinId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/unitDanger/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/weihuapin.png';
                    let mes = '<div>名称:'+res.name+'</div><br />';
                    mes += '<div>区县名称:'+res.areaName+'</div><br />';
                    mes += '<div>地址:'+res.address+'</div><br />';
                    mes += '<div>建筑物:'+res.building+'</div><br />';
                    mes += '<div>储罐个数及容量:'+res.tanks+'</div><br />';
                    mes += '<div>加油机台数:'+res.machine+'</div><br />';
                    mes += '<div>单体数:'+res.number+'</div><br />';
                    mes += '<div>项目:'+res.project+'</div><br />';
                    mes += '<div>产品:'+res.product+'</div><br />';
                    mes += '<div>最新报告编号:'+res.report+'</div><br />';
                    mes += '<div>防雷安全隐患情况:'+res.status+'</div><br />';
                    mes += '<div>防雷所分管领导:'+res.lightningLeader+'</div><br />';
                    mes += '<div>企业防雷安全责任人:'+res.lightningPeople+'</div><br />';
                    mes += '<div>责任人联系电话:'+res.lightningPhone+'</div><br />';
                    mes += '<div>检测片区组长:'+res.testLeader+'</div><br />';
                    mes += '<div>检测片区组员:'+res.testMember+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    weihuapinArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<weihuapinArrayMarker.length;i++){
                active.map.removeOverlay(weihuapinArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击农业园区菜单
     */
    let nongyeyuanquArrayMarker=new Array();
    $("#nyyqId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/unitAgriculturPark/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/nongyeyuanqu.png';
                    let mes = '<div>园区名称:'+res.name+'</div><br />';
                    mes += '<div>区县名称:'+res.areaName+'</div><br />';
                    mes += '<div>所属管辖单位:'+res.unit+'</div><br />';
                    mes += '<div>园区类型:'+res.type+'</div><br />';
                    mes += '<div>占地面积（㎡）:'+res.area+'</div><br />';
                    mes += '<div>园区描述:'+res.description+'</div><br />';
                    mes += '<div>工作人员人数:'+res.worker+'</div><br />';
                    mes += '<div>地址:'+res.address+'</div><br />';
                    mes += '<div>负责人:'+res.principal+'</div><br />';
                    mes += '<div>联系电话:'+res.phone+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    nongyeyuanquArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<nongyeyuanquArrayMarker.length;i++){
                active.map.removeOverlay(nongyeyuanquArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击农作物种植区菜单
     */
    let nongzuowuArrayMarker=new Array();
    $("#nzwzzId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/unitPlantArea/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/zhongzhiqu.png';
                    let mes = '<div>种植区名称:'+res.name+'</div><br />';
                    mes += '<div>区县名称:'+res.areaName+'</div><br />';
                    mes += '<div>所属管辖单位:'+res.unit+'</div><br />';
                    mes += '<div>主要作物:'+res.crops+'</div><br />';
                    mes += '<div>占地面积（㎡）:'+res.area+'</div><br />';
                    mes += '<div>园区描述:'+res.description+'</div><br />';
                    mes += '<div>工作人员人数:'+res.worker+'</div><br />';
                    mes += '<div>地址:'+res.address+'</div><br />';
                    mes += '<div>负责人:'+res.principal+'</div><br />';
                    mes += '<div>联系电话:'+res.phone+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    nongzuowuArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<nongzuowuArrayMarker.length;i++){
                active.map.removeOverlay(nongzuowuArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击旅游景区菜单
     */
    let lvyouArrayMarker=new Array();
    $("#lvyouId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/unitAttractions/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/lvyoujingqu.jpg';
                    let mes = '<div>景区名称:'+res.name+'</div><br />';
                    mes += '<div>区县名称:'+res.areaName+'</div><br />';
                    mes += '<div>所属管辖单位:'+res.unit+'</div><br />';
                    mes += '<div>园区类型:'+res.type+'</div><br />';
                    mes += '<div>占地面积（㎡）:'+res.area+'</div><br />';
                    mes += '<div>园区描述:'+res.description+'</div><br />';
                    mes += '<div>可容纳人数:'+res.capacity+'</div><br />';
                    mes += '<div>工作人员人数:'+res.worker+'</div><br />';
                    mes += '<div>地址:'+res.address+'</div><br />';
                    mes += '<div>负责人:'+res.principal+'</div><br />';
                    mes += '<div>联系电话:'+res.phone+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    lvyouArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<lvyouArrayMarker.length;i++){
                active.map.removeOverlay(lvyouArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击高速公路菜单
     */
    let gaosuArrayMarker=new Array();
    $("#gaosuId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/unitHighway/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/gaosu.png';
                    let mes = '<div>高速公路名称:'+res.name+'</div><br />';
                    mes += '<div>高速编号:'+res.code+'</div><br />';
                    mes += '<div>起点:'+res.start+'</div><br />';
                    mes += '<div>终点:'+res.end+'</div><br />';
                    mes += '<div>高速描述:'+res.description+'</div><br />';
                    mes += '<div>省份:'+res.province+'</div><br />';
                    mes += '<div>全长:'+res.length+'</div><br />';
                    mes += '<div>所属管辖单位:'+res.unit+'</div><br />';
                    mes += '<div>负责人:'+res.principal+'</div><br />';
                    mes += '<div>联系电话:'+res.phone+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    gaosuArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<gaosuArrayMarker.length;i++){
                active.map.removeOverlay(gaosuArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });
    /**
     * 点击桥梁菜单
     */
    let qiaoliangArrayMarker=new Array();
    $("#qiaoliangId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/unitBridge/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/qiaoliang.jpg';
                    let mes = '<div>名称:'+res.name+'</div><br />';
                    mes += '<div>桥型:'+res.type+'</div><br />';
                    mes += '<div>地址:'+res.address+'</div><br />';
                    mes += '<div>全长:'+res.length+'</div><br />';
                    mes += '<div>成桥时间:'+res.buildTime+'</div><br />';
                    mes += '<div>地区:'+res.areaName+'</div><br />';
                    mes += '<div>描述:'+res.description+'</div><br />';
                    mes += '<div>所属管辖单位:'+res.unit+'</div><br />';
                    mes += '<div>负责人:'+res.principal+'</div><br />';
                    mes += '<div>联系电话:'+res.phone+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    qiaoliangArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<qiaoliangArrayMarker.length;i++){
                active.map.removeOverlay(qiaoliangArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击堤防菜单
     */
    let difangArrayMarker=new Array();
    $("#difangId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/unitDike/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/difang.jpg';
                    let mes = '<div>名称:'+res.name+'</div><br />';
                    mes += '<div>河流:'+res.river+'</div><br />';
                    mes += '<div>防洪标准:'+res.floodPrevention+'</div><br />';
                    mes += '<div>全长:'+res.length+'</div><br />';
                    mes += '<div>高程:'+res.altitude+'</div><br />';
                    mes += '<div>平均堤距:'+res.distance+'</div><br />';
                    mes += '<div>高度:'+res.height+'</div><br />';
                    mes += '<div>宽度:'+res.width+'</div><br />';
                    mes += '<div>堤身土质:'+res.soil+'</div><br />';
                    mes += '<div>堤岸堤坡长度:'+res.slopeLength+'</div><br />';
                    mes += '<div>省份:'+res.province+'</div><br />';
                    mes += '<div>所属管辖单位:'+res.unit+'</div><br />';
                    mes += '<div>负责人:'+res.principal+'</div><br />';
                    mes += '<div>联系电话:'+res.phone+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    difangArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<difangArrayMarker.length;i++){
                active.map.removeOverlay(difangArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });
    /**
     * 点击车站菜单
     */
    let chezhanArrayMarker=new Array();
    $("#chezhanId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/unitStation/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/chezhan.png';
                    let mes = '<div>名称:'+res.name+'</div><br />';
                    mes += '<div>地区:'+res.areaName+'</div><br />';
                    mes += '<div>面积:'+res.area+'</div><br />';
                    mes += '<div>车辆数:'+res.vehicle+'</div><br />';
                    mes += '<div>可容纳人数:'+res.capacity+'</div><br />';
                    mes += '<div>描述:'+res.description+'</div><br />';
                    mes += '<div>所属管辖单位:'+res.unit+'</div><br />';
                    mes += '<div>负责人:'+res.principal+'</div><br />';
                    mes += '<div>联系电话:'+res.phone+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    chezhanArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<chezhanArrayMarker.length;i++){
                active.map.removeOverlay(chezhanArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击学校菜单
     */
    let xuexiaoArrayMarker=new Array();
    $("#xuexiaoId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/unitSchool/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/xuexiao.png';
                    let mes = '<div>名称:'+res.name+'</div><br />';
                    mes += '<div>地区:'+res.areaName+'</div><br />';
                    mes += '<div>类型:'+res.type+'</div><br />';
                    mes += '<div>面积:'+res.area+'</div><br />';
                    mes += '<div>人数:'+res.people+'</div><br />';
                    mes += '<div>描述:'+res.description+'</div><br />';
                    mes += '<div>所属管辖单位:'+res.unit+'</div><br />';
                    mes += '<div>负责人:'+res.principal+'</div><br />';
                    mes += '<div>联系电话:'+res.phone+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    xuexiaoArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<xuexiaoArrayMarker.length;i++){
                active.map.removeOverlay(xuexiaoArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击医院菜单
     */
    let yiyuanArrayMarker=new Array();
    $("#yiyuanId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/unitHospital/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/yiyuan.png';
                    let mes = '<div>名称:'+res.name+'</div><br />';
                    mes += '<div>地区:'+res.areaName+'</div><br />';
                    mes += '<div>面积:'+res.area+'</div><br />';
                    mes += '<div>人数:'+res.doctor+'</div><br />';
                    mes += '<div>护士人数:'+res.nurse+'</div><br />';
                    mes += '<div>救护车数量:'+res.ambulance+'</div><br />';
                    mes += '<div>床位数量:'+res.bed+'</div><br />';
                    mes += '<div>描述:'+res.description+'</div><br />';
                    mes += '<div>所属管辖单位:'+res.unit+'</div><br />';
                    mes += '<div>负责人:'+res.principal+'</div><br />';
                    mes += '<div>联系电话:'+res.phone+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    yiyuanArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<yiyuanArrayMarker.length;i++){
                active.map.removeOverlay(yiyuanArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });


    /**
     * 点击广场菜单
     */
    let guangchangArrayMarker=new Array();
    $("#guagnchangId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/unitSquare/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/guangchang.png';
                    let mes = '<div>名称:'+res.name+'</div><br />';
                    mes += '<div>地区:'+res.areaName+'</div><br />';
                    mes += '<div>面积:'+res.area+'</div><br />';
                    mes += '<div>容纳人数:'+res.capacity+'</div><br />';
                    mes += '<div>所属管辖单位:'+res.unit+'</div><br />';
                    mes += '<div>负责人:'+res.principal+'</div><br />';
                    mes += '<div>联系电话:'+res.phone+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    guangchangArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<guangchangArrayMarker.length;i++){
                active.map.removeOverlay(guangchangArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击商场菜单
     */
    let shangchangArrayMarker=new Array();
    $("#shangchangId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/unitMarket/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/shangchang.png';
                    let mes = '<div>名称:'+res.name+'</div><br />';
                    mes += '<div>地区:'+res.areaName+'</div><br />';
                    mes += '<div>面积:'+res.area+'</div><br />';
                    mes += '<div>楼层:'+res.floor+'</div><br />';
                    mes += '<div>商户数量:'+res.merchant+'</div><br />';
                    mes += '<div>容纳人数:'+res.capacity+'</div><br />';
                    mes += '<div>商场描述:'+res.description+'</div><br />';
                    mes += '<div>所属管辖单位:'+res.unit+'</div><br />';
                    mes += '<div>负责人:'+res.principal+'</div><br />';
                    mes += '<div>联系电话:'+res.phone+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    shangchangArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<shangchangArrayMarker.length;i++){
                active.map.removeOverlay(shangchangArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击水库菜单
     */
    let shuikuArrayMarker=new Array();
    $("#shuikuId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/unitReservoir/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/shuiku.png';
                    let mes = '<div>名称:'+res.name+'</div><br />';
                    mes += '<div>地区:'+res.areaName+'</div><br />';
                    mes += '<div>地区编码:'+res.districtCode+'</div><br />';
                    mes += '<div>地址:'+res.address+'</div><br />';
                    mes += '<div>总库容(万m3):'+res.storage+'</div><br />';
                    mes += '<div>防限库容(万m3):'+res.limitStorage+'</div><br />';
                    mes += '<div>防限水位(m):'+res.waterLimit+'</div><br />';
                    mes += '<div>正常蓄水位(m):'+res.waterNormal+'</div><br />';
                    mes += '<div>有无水位:'+res.waterLine+'</div><br />';
                    mes += '<div>负责人:'+res.principal+'</div><br />';
                    mes += '<div>联系电话:'+res.phone+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    shuikuArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<shuikuArrayMarker.length;i++){
                active.map.removeOverlay(shuikuArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击基层防御人员菜单
     */
    let jicengArrayMarker=new Array();
    $("#jicengPeopleId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/user/list", data:{type:2,channelName:"短信"}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/jiceng.png';
                    let sex="";
                    if(res.sex==1){
                        sex="男"
                    }else{
                        sex="女"
                    }
                    let mes = '<div>名称:'+res.name+'</div><br />';
                    mes += '<div>性别:'+sex+'</div><br />';
                    mes += '<div>地址:'+res.address+'</div><br />';
                    mes += '<div>年龄:'+res.age+'</div><br />';
                    mes += '<div>职务:'+res.job+'</div><br />';
                    mes += '<div>职责:'+res.duties+'</div><br />';
                    mes += '<div>领导:'+res.leader+'</div><br />';
                    mes += '<div>机构名称:'+res.organizationName+'</div><br />';
                    mes += '<div>联系电话:'+res.code+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.longitude , res.latitude]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    jicengArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<jicengArrayMarker.length;i++){
                active.map.removeOverlay(jicengArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击单位人员菜单
     */
    let danweiArrayMarker=new Array();
    $("#danweiPeopleId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/user/list", data:{type:1,channelName:"短信"}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/danweiren.png';
                    if(res.sex==1){
                        sex="男"
                    }else{
                        sex="女"
                    }
                    let mes = '<div>名称:'+res.name+'</div><br />';
                    mes += '<div>性别:'+sex+'</div><br />';
                    mes += '<div>地址:'+res.address+'</div><br />';
                    mes += '<div>年龄:'+res.age+'</div><br />';
                    mes += '<div>职务:'+res.job+'</div><br />';
                    mes += '<div>职责:'+res.duties+'</div><br />';
                    mes += '<div>机构名称:'+res.organizationName+'</div><br />';
                    mes += '<div>联系电话:'+res.code+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.longitude , res.latitude]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    danweiArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<danweiArrayMarker.length;i++){
                active.map.removeOverlay(danweiArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击预警信息发布菜单
     */
    let sheshiArrayMarker=new Array();
    $("#xianshipinId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/facilityPublish/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/xianshipin.png';
                    let status="";
                    if(res.status==1){
                        status="部署"
                    }else{
                        status="未部署"
                    }
                    let mes = '<div>设备名称:'+res.name+'</div><br />';
                    mes += '<div>设备编号:'+res.code+'</div><br />';
                    mes += '<div>设备厂家:'+res.factory+'</div><br />';
                    mes += '<div>设备类型:'+res.type+'</div><br />';
                    mes += '<div>地址:'+res.address+'</div><br />';
                    mes += '<div>设备用途:'+res.use+'</div><br />';
                    mes += '<div>设备状态:'+status+'</div><br />';
                    mes += '<div>负责人:'+res.principal+'</div><br />';
                    mes += '<div>联系电话:'+res.phone+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    sheshiArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<sheshiArrayMarker.length;i++){
                active.map.removeOverlay(sheshiArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击避难设施菜单
     */
    let binanArrayMarker=new Array();
    $("#yingjibinanId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/facilityShelter/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/yingjibinan.png';
                    let mes = '<div>设备名称:'+res.name+'</div><br />';
                    mes += '<div>区县:'+res.areaName+'</div><br />';
                    mes += '<div>地址:'+res.address+'</div><br />';
                    mes += '<div>地区编码:'+res.code+'</div><br />';
                    mes += '<div>容纳人口（人）:'+res.capacity+'</div><br />';
                    mes += '<div>面积（㎡）:'+res.area+'</div><br />';
                    mes += '<div>主管单位:'+res.unit+'</div><br />';
                    mes += '<div>负责人:'+res.principal+'</div><br />';
                    mes += '<div>联系电话:'+res.tel+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    binanArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<binanArrayMarker.length;i++){
                active.map.removeOverlay(binanArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击物资储备菜单
     */
    let chubeiArrayMarker=new Array();
    $("#wuzichubeiId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/facilitySupply/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/chubeichangsuo.png';
                    let mes = '<div>名称:'+res.name+'</div><br />';
                    mes += '<div>区县:'+res.areaName+'</div><br />';
                    mes += '<div>地址:'+res.address+'</div><br />';
                    mes += '<div>地区编码:'+res.code+'</div><br />';
                    mes += '<div>救援物资类型:'+res.type+'</div><br />';
                    mes += '<div>已有物资:'+res.existing+'</div><br />';
                    mes += '<div>已有物资规格型号:'+res.model+'</div><br />';
                    mes += '<div>数量:'+res.amount+'</div><br />';
                    mes += '<div>用途:'+res.use+'</div><br />';
                    mes += '<div>主管单位:'+res.unit+'</div><br />';
                    mes += '<div>单位联系电话:'+res.tel+'</div><br />';
                    mes += '<div>负责人:'+res.principal+'</div><br />';
                    mes += '<div>联系电话:'+res.phone+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    chubeiArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<chubeiArrayMarker.length;i++){
                active.map.removeOverlay(chubeiArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击办公场所菜单
     */
    let bangongArrayMarker=new Array();
    $("#bangongId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");

            active.render({type:"GET", url: "/client/facilityOffice/list", data:{}}, data => {
                console.log(data);
                data.forEach( res => {
                    let iconUrl = '/client/base/bangongchangsuo.png';
                    let mes = '<div>办公场所名称:'+res.name+'</div><br />';
                    mes += '<div>场所类型:'+res.type+'</div><br />';
                    mes += '<div>地址:'+res.address+'</div><br />';
                    mes += '<div>描述:'+res.description+'</div><br />';
                    mes += '<div>占地面积（㎡）:'+res.area+'</div><br />';
                    mes += '<div>工作人员人数:'+res.worker+'</div><br />';
                    mes += '<div>负责人:'+res.principal+'</div><br />';
                    mes += '<div>联系电话:'+res.phone+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    bangongArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<bangongArrayMarker.length;i++){
                active.map.removeOverlay(bangongArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });


    /**
     * 点击自动站菜单
     */
    let stationArrayMarker=new Array();
    $("#stationId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.render({type:"GET", url: "/client/automaticStation/list", data:{}}, data => {
                data.forEach( res => {
                    let iconUrl = '/client/base/station.png';
                    let mes = '<div>站名:'+res.name+'</div><br />';
                    mes += '<div>地区:'+res.areaName+'</div><br />';
                    mes += '<div>地址:'+res.address+'</div><br />';
                    mes += '<div>区站号:'+res.code+'</div><br />';
                    mes += '<div>海拔高度（m）:'+res.height+'</div><br />';
                    mes += '<div>气压传感器高度（m）:'+res.pressureHeight+'</div><br />';
                    mes += '<div>自动站型号:'+res.type+'</div><br />';
                    mes += '<div>自动站生产厂:'+res.factory+'</div><br />';
                    let marker = active.marker(iconUrl ,[res.lon , res.lat]
                        ,content = '<span style="font-size: 20px;color: #FF4500;">' +  res.name + '</span>'  +'<br/>'+ mes);
                    // 将标注添加到地图中
                    active.map.addOverlay(marker);
                    stationArrayMarker.push(marker);
                    //信息弹出框
                    active.addClickHandler(content,marker);
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<stationArrayMarker.length;i++){
                active.map.removeOverlay(stationArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });



    /**
     * 点击雷达菜单
     */
    $("#radarPTId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            var index = layer.open({
                title: "<i class='layui-icon layui-icon-form'></i>信息详情"
                ,type: 2
                ,content: "http://www.nmc.cn/publish/radar/hei-long-jiang/jia-mu-si.htm"
                ,fix: false //不固定
                ,maxmin: true
                ,shadeClose: false
                ,area: ['900px', '100%']
                ,offset:['0' , '0']
                ,cancel : function() {
                    $("#radarPTId").data("flag",0);
                    $("#radarPTId").children("input").removeAttr("checked");
                }
            });
            // layer.full(index);
        }else{
            $(this).data("flag",0);
            $(this).children("input").removeAttr("checked");
        }
    });


    /**
     * 点击气象预警菜单
     */
    let warnArrayMarker=new Array();
    $("#warnId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0"){
            $(this).data("flag",1);
            $(this).children("input").prop("checked","checked");
            active.markerArrWarn.forEach( json => {
                active.render({type:"GET", url: "/client/warn/edit/info", data:{}}, data => {
                    console.log(data);
                    data.forEach( json => {
                        let color="";
                        let colorname="";
                        if(json.disasterColor==0){
                            colorname="红色"
                        }else if(json.disasterColor==1){
                            colorname="橙色"
                        }else if(json.disasterColor==2){
                            colorname="黄色"
                        }else if(json.disasterColor==3){
                            colorname="蓝色"
                        }
                        let mes = '<div><div>'+json.content+'</div><hr /><div>'+json.organizationName+json.sendTime+'发布</div>';
                        let marker = active.marker('/client/'+json.icon,[json.longitude , json.latitude]
                            ,content = '<span style="font-size: 20px;color: #FF4500;">' +  json.disasterName + colorname+'预警'+'</span>'  +'<br/>'+ mes);
                        // 将标注添加到地图中
                        active.map.addOverlay(marker);
                        warnArrayMarker.push(marker);
                        //信息弹出框
                        active.addClickHandler(content,marker);
                    });
                });
            });
        }else{
            $(this).children("input").removeAttr("checked");
            for(var i=0;i<warnArrayMarker.length;i++){
                active.map.removeOverlay(warnArrayMarker[i]);
            }
            $(this).data("flag","0");
        }
    });

    /**
     * 点击气象服务产品菜单
     */
    $("#serviceProductId").bind("click", function(){
        let flag = $(this).data("flag");
        if(flag=="0") {
            $(this).data("flag", 1);
            $(this).children("input").prop("checked","checked");
            active.render({type: "GET", url: "/client/serverProduct/selectList", data: {}}, data => {
                let html = "";
                html += "<ul >";
                data.forEach(json => {
                    let path = "/client" + json.path;
                    html += "<li style='border:1px solid #1b6147;text-align: left;color:white;font-size:12px;'><a style='color: white' href='" + path + "'>" + json.title + "</a></li>";
                });
                html += "</ul >";
                $("#dizhiId").empty().append(html);
                $("#dizhiId").show();
            });
        }else{
            $(this).children("input").removeAttr("checked");
            $(this).data("flag","0");
            $("#dizhiId").hide();
        }
    });

    /**
     *
     * 初始化地图
     * @param bdMap:地图容器
     *  @param initMapHeight：地图容器高度自适应界面
     */
    active.initBMap("bdMap", active.initMapHeight);
});

