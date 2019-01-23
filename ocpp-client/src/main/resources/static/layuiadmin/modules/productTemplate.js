
layui.define(function(exports){

    /**
     * 灾种相关数据转换
     */
    var productTemplate = {
        /**
         * 重大节日产品
         * @param option
         * @returns {*}
         */
        "getFestivalProductTemplate": function(option) {
            console.log(option);
            return '<p style="text-align:center">\n' +
                '    <strong><span style="font-size:29px;font-family:宋体;color:red"></span></strong>\n' +
                '</p>\n' +
                '<p style="text-align:center">\n' +
                '    <span style="font-size:70px;font-family:宋体;color:red;position:relative;top:1px;letter-spacing:-1px">春运气象服务专报</span>\n' +
                '</p>\n' +
                '<p style="text-align:center">\n' +
                '    <strong><span style="font-size:29px;font-family:宋体;color:red"></span></strong>' +
                '<span style="text-align: center; font-size: 24px; font-family: 宋体; color: red;">〔</span>' +
                '<span style="text-align: center; font-size: 24px;">2018</span>' +
                '<span style="text-align: center; font-size: 24px; font-family: 宋体; color: red;">〕第</span>' +
                '<span style="text-align: center; font-size: 24px; color: red;">01</span>' +
                '<span style="text-align: center; font-size: 24px; font-family: 宋体; color: red;">期</span>\n' +
                '</p>\n' +
                '<p style="display: inline-block;width: 100%;text-align:center;">\n' +
                '    <span style="display: inline-block;font-size:21px;font-family:仿宋_GB2312; text-align: justify; float: left; ">XXX气象局</span>\n' +
                '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                '<span style="display: inline-block;font-size:21px;font-family:仿宋_GB2312; text-align: justify; float: right;">签发人:XXX</span>\n' +
                '</p>\n' +
                '<p style="border-top: 2px solid red; border-bottom: none; border-right: none;border-left: none;align-content: center;">\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p style="text-align:center">\n' +
                '    <span style="font-size:23px">天气分析</span>\n' +
                '</p>\n' +
                '<p style="text-indent:36px">\n' +
                '    未来一周，前期冷空气较强，降温、降雨；中后期暖空气势利明显加强，将转为升温和少雨天气时段。气温明显升高，风力较大，对春播有利，需关注局地阵雨。请各农区未播种地块抓住晴好天气时段适时播种，已播种地块做好田间管理，水稻产区根据当地具体情况利用有利时机适时移栽。\n' +
                '</p>\n' +
                '<p>\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p>\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p>\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p style="text-align: right;">' +
                '<span style="font-family: 仿宋_GB2312; font-size: 21px; text-align: justify;">XXX气象局</span><span style="font-family: 仿宋_GB2312; font-size: 21px; text-align: justify;"></span>' +
                '</p>\n' +
                '<p style="text-align: right;">\n' +
                '    <span style="font-family: 仿宋_GB2312; font-size: 21px; text-align: justify;">'+option.dateTime+'</span>\n' +
                '</p>\n' +
                '<p style="text-indent:13px;line-height:37px">\n' +
                '    <span style="font-size:21px;font-family:仿宋_GB2312;"><br/></span>\n' +
                '</p>\n' +
                '<p style="text-indent:13px;line-height:37px">\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p style="text-indent:13px;line-height:37px">\n' +
                '    <span style="font-size:21px;font-family:仿宋_GB23129;"></span>\n' +
                '</p>\n' +
                '<p style="text-indent:0">\n' +
                '    <strong><span style="font-size:21px;font-family:仿宋_GB2312;">24小时值班电话：XXXX-XXXXXXX 审核人：XX</span></strong>\n' +
                '</p>\n' +
                '<hr/>\n' +
                '<p style="text-indent: 0px;">\n' +
                '    <span style="font-size: 21px; font-family: 仿宋_GB2312, serif;">报送：</span>\n' +
                '</p>';
        },
        /**
         * 预报产品
         * @param option
         * @returns {*}
         */
        "getQixiangProductTemplate": function(option) {
            return '<p style="text-align:center">\n' +
                '    <strong><span style="font-size:29px;font-family:宋体;color:red"></span></strong>\n' +
                '</p>\n' +
                '<p style="text-align:center">\n' +
                '    <span style="font-size:70px;font-family:宋体;color:red;position:relative;top:1px;letter-spacing:-1px">重大气象信息快报</span>\n' +
                '</p>\n' +
                '<p style="text-align:center">\n' +
                '    <strong><span style="font-size:29px;font-family:宋体;color:red"></span></strong>' +
                '<span style="text-align: center; font-size: 24px; font-family: 宋体; color: red;">〔</span>' +
                '<span style="text-align: center; font-size: 24px;">2018</span>' +
                '<span style="text-align: center; font-size: 24px; font-family: 宋体; color: red;">〕第</span>' +
                '<span style="text-align: center; font-size: 24px; color: red;">01</span>' +
                '<span style="text-align: center; font-size: 24px; font-family: 宋体; color: red;">期</span>\n' +
                '</p>\n' +
                '<p style="display: inline-block;width: 100%;text-align:center;">\n' +
                '    <span style="display: inline-block;font-size:21px;font-family:仿宋_GB2312; text-align: justify; float: left; ">XXX气象局</span>\n' +
                '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                '<span style="display: inline-block;font-size:21px;font-family:仿宋_GB2312; text-align: justify; float: right;">签发人:XXX</span>\n' +
                '</p>\n' +
                '<p style="border-top: 2px solid red; border-bottom: none; border-right: none;border-left: none;align-content: center;">\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p style="text-align:center">\n' +
                '    <span style="font-size:23px">天气分析</span>\n' +
                '</p>\n' +
                '<p style="text-indent:36px">\n' +
                '    未来一周，前期冷空气较强，降温、降雨；中后期暖空气势利明显加强，将转为升温和少雨天气时段。气温明显升高，风力较大，对春播有利，需关注局地阵雨。请各农区未播种地块抓住晴好天气时段适时播种，已播种地块做好田间管理，水稻产区根据当地具体情况利用有利时机适时移栽。\n' +
                '</p>\n' +
                '<p>\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p>\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p>\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p style="text-align: right;">' +
                '<span style="font-family: 仿宋_GB2312; font-size: 21px; text-align: justify;">XXX气象局</span><span style="font-family: 仿宋_GB2312; font-size: 21px; text-align: justify;"></span>' +
                '</p>\n' +
                '<p style="text-align: right;">\n' +
                '    <span style="font-family: 仿宋_GB2312; font-size: 21px; text-align: justify;">'+option.dateTime+'</span>\n' +
                '</p>\n' +
                '<p style="text-indent:13px;line-height:37px">\n' +
                '    <span style="font-size:21px;font-family:仿宋_GB2312;"><br/></span>\n' +
                '</p>\n' +
                '<p style="text-indent:13px;line-height:37px">\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p style="text-indent:13px;line-height:37px">\n' +
                '    <span style="font-size:21px;font-family:仿宋_GB23129;"></span>\n' +
                '</p>\n' +
                '<p style="text-indent:0">\n' +
                '    <strong><span style="font-size:21px;font-family:仿宋_GB2312;">24小时值班电话：XXXX-XXXXXXX 审核人：XX</span></strong>\n' +
                '</p>\n' +
                '<hr/>\n' +
                '<p style="text-indent: 0px;">\n' +
                '    <span style="font-size: 21px; font-family: 仿宋_GB2312, serif;">报送：</span>\n' +
                '</p>';
        },
        /**
         * 预警产品
         * @param option
         * @returns {*}
         */
        "getWarnProductTemplate": function(option) {
            return '<p style="text-align:center">\n' +
                '    <strong><span style="font-size:29px;font-family:宋体;color:red"></span></strong>\n' +
                '</p>\n' +
                '<p style="text-align:center">\n' +
                '    <span style="font-size:70px;font-family:宋体;color:red;position:relative;top:1px;letter-spacing:-1px">森林火险气象预报</span>\n' +
                '</p>\n' +
                '<p style="text-align:center">\n' +
                '    <strong><span style="font-size:29px;font-family:宋体;color:red"></span></strong>' +
                '<span style="text-align: center; font-size: 24px; font-family: 宋体; color: red;">〔</span>' +
                '<span style="text-align: center; font-size: 24px;">2018</span>' +
                '<span style="text-align: center; font-size: 24px; font-family: 宋体; color: red;">〕第</span>' +
                '<span style="text-align: center; font-size: 24px; color: red;">01</span>' +
                '<span style="text-align: center; font-size: 24px; font-family: 宋体; color: red;">期</span>\n' +
                '</p>\n' +
                '<p style="display: inline-block;width: 100%;text-align:center;">\n' +
                '    <span style="display: inline-block;font-size:21px;font-family:仿宋_GB2312; text-align: justify; float: left; ">XXX气象局</span>\n' +
                '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                '<span style="display: inline-block;font-size:21px;font-family:仿宋_GB2312; text-align: justify; float: right;">签发人:XXX</span>\n' +
                '</p>\n' +
                '<p style="border-top: 2px solid red; border-bottom: none; border-right: none;border-left: none;align-content: center;">\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p style="text-align:center">\n' +
                '    <span style="font-size:23px">天气分析</span>\n' +
                '</p>\n' +
                '<p style="text-indent:36px">\n' +
                '    未来一周，前期冷空气较强，降温、降雨；中后期暖空气势利明显加强，将转为升温和少雨天气时段。气温明显升高，风力较大，对春播有利，需关注局地阵雨。请各农区未播种地块抓住晴好天气时段适时播种，已播种地块做好田间管理，水稻产区根据当地具体情况利用有利时机适时移栽。\n' +
                '</p>\n' +
                '<p>\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p>\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p>\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p style="text-align: right;">' +
                '<span style="font-family: 仿宋_GB2312; font-size: 21px; text-align: justify;">XXX气象局</span><span style="font-family: 仿宋_GB2312; font-size: 21px; text-align: justify;"></span>' +
                '</p>\n' +
                '<p style="text-align: right;">\n' +
                '    <span style="font-family: 仿宋_GB2312; font-size: 21px; text-align: justify;">'+option.dateTime+'</span>\n' +
                '</p>\n' +
                '<p style="text-indent:13px;line-height:37px">\n' +
                '    <span style="font-size:21px;font-family:仿宋_GB2312;"><br/></span>\n' +
                '</p>\n' +
                '<p style="text-indent:13px;line-height:37px">\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p style="text-indent:13px;line-height:37px">\n' +
                '    <span style="font-size:21px;font-family:仿宋_GB23129;"></span>\n' +
                '</p>\n' +
                '<p style="text-indent:0">\n' +
                '    <strong><span style="font-size:21px;font-family:仿宋_GB2312;">24小时值班电话：XXXX-XXXXXXX 审核人：XX</span></strong>\n' +
                '</p>\n' +
                '<hr/>\n' +
                '<p style="text-indent: 0px;">\n' +
                '    <span style="font-size: 21px; font-family: 仿宋_GB2312, serif;">报送：</span>\n' +
                '</p>';
        },
        /**
         * 预警产品
         * @param option
         * @returns {*}
         */
        "getWorkProductTemplate": function(option) {
            return '<p style="text-align:center">\n' +
                '    <strong><span style="font-size:29px;font-family:宋体;color:red"></span></strong>\n' +
                '</p>\n' +
                '<p style="text-align:center">\n' +
                '    <span style="font-size:70px;font-family:宋体;color:red;position:relative;top:1px;letter-spacing:-1px">天气报告</span>\n' +
                '</p>\n' +
                '<p style="text-align:center">\n' +
                '    <strong><span style="font-size:29px;font-family:宋体;color:red"></span></strong>' +
                '<span style="text-align: center; font-size: 24px; font-family: 宋体; color: red;">〔</span>' +
                '<span style="text-align: center; font-size: 24px;">2018</span>' +
                '<span style="text-align: center; font-size: 24px; font-family: 宋体; color: red;">〕第</span>' +
                '<span style="text-align: center; font-size: 24px; color: red;">01</span>' +
                '<span style="text-align: center; font-size: 24px; font-family: 宋体; color: red;">期</span>\n' +
                '</p>\n' +
                '<p style="display: inline-block;width: 100%;text-align:center;">\n' +
                '    <span style="display: inline-block;font-size:21px;font-family:仿宋_GB2312; text-align: justify; float: left; ">XXX气象局</span>\n' +
                '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                '<span style="display: inline-block;font-size:21px;font-family:仿宋_GB2312; text-align: justify; float: right;">签发人:XXX</span>\n' +
                '</p>\n' +
                '<p style="border-top: 2px solid red; border-bottom: none; border-right: none;border-left: none;align-content: center;">\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p style="text-align:center">\n' +
                '    <span style="font-size:23px">天气分析</span>\n' +
                '</p>\n' +
                '<p style="text-indent:36px">\n' +
                '    未来一周，前期冷空气较强，降温、降雨；中后期暖空气势利明显加强，将转为升温和少雨天气时段。气温明显升高，风力较大，对春播有利，需关注局地阵雨。请各农区未播种地块抓住晴好天气时段适时播种，已播种地块做好田间管理，水稻产区根据当地具体情况利用有利时机适时移栽。\n' +
                '</p>\n' +
                '<p>\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p>\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p>\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p style="text-align: right;">' +
                '<span style="font-family: 仿宋_GB2312; font-size: 21px; text-align: justify;">XXX气象局</span><span style="font-family: 仿宋_GB2312; font-size: 21px; text-align: justify;"></span>' +
                '</p>\n' +
                '<p style="text-align: right;">\n' +
                '    <span style="font-family: 仿宋_GB2312; font-size: 21px; text-align: justify;">'+option.dateTime+'</span>\n' +
                '</p>\n' +
                '<p style="text-indent:13px;line-height:37px">\n' +
                '    <span style="font-size:21px;font-family:仿宋_GB2312;"><br/></span>\n' +
                '</p>\n' +
                '<p style="text-indent:13px;line-height:37px">\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p style="text-indent:13px;line-height:37px">\n' +
                '    <span style="font-size:21px;font-family:仿宋_GB23129;"></span>\n' +
                '</p>\n' +
                '<p style="text-indent:0">\n' +
                '    <strong><span style="font-size:21px;font-family:仿宋_GB2312;">24小时值班电话：XXXX-XXXXXXX 审核人：XX</span></strong>\n' +
                '</p>\n' +
                '<hr/>\n' +
                '<p style="text-indent: 0px;">\n' +
                '    <span style="font-size: 21px; font-family: 仿宋_GB2312, serif;">报送：</span>\n' +
                '</p>';
        },
        /**
         * 预警产品
         * @param option
         * @returns {*}
         */
        "getDisasterProductTemplate": function(option) {
            return '<p style="text-align:center">\n' +
                '    <strong><span style="font-size:29px;font-family:宋体;color:red"></span></strong>\n' +
                '</p>\n' +
                '<p style="text-align:center">\n' +
                '    <span style="font-size:50px;font-family:宋体;color:red;position:relative;top:1px;letter-spacing:-1px">地质灾害气象风险专题预报</span>\n' +
                '</p>\n' +
                '<p style="text-align:center">\n' +
                '    <strong><span style="font-size:29px;font-family:宋体;color:red"></span></strong>' +
                '<span style="text-align: center; font-size: 24px; font-family: 宋体; color: red;">〔</span>' +
                '<span style="text-align: center; font-size: 24px;">2018</span>' +
                '<span style="text-align: center; font-size: 24px; font-family: 宋体; color: red;">〕第</span>' +
                '<span style="text-align: center; font-size: 24px; color: red;">01</span>' +
                '<span style="text-align: center; font-size: 24px; font-family: 宋体; color: red;">期</span>\n' +
                '</p>\n' +
                '<p style="display: inline-block;width: 100%;text-align:center;">\n' +
                '    <span style="display: inline-block;font-size:21px;font-family:仿宋_GB2312; text-align: justify; float: left; ">XXX气象局</span>\n' +
                '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                '<span style="display: inline-block;font-size:21px;font-family:仿宋_GB2312; text-align: justify; float: right;">签发人:XXX</span>\n' +
                '</p>\n' +
                '<p style="border-top: 2px solid red; border-bottom: none; border-right: none;border-left: none;align-content: center;">\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p style="text-align:center">\n' +
                '    <span style="font-size:23px">天气分析</span>\n' +
                '</p>\n' +
                '<p style="text-indent:36px">\n' +
                '    未来一周，前期冷空气较强，降温、降雨；中后期暖空气势利明显加强，将转为升温和少雨天气时段。气温明显升高，风力较大，对春播有利，需关注局地阵雨。请各农区未播种地块抓住晴好天气时段适时播种，已播种地块做好田间管理，水稻产区根据当地具体情况利用有利时机适时移栽。\n' +
                '</p>\n' +
                '<p>\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p>\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p>\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p style="text-align: right;">' +
                '<span style="font-family: 仿宋_GB2312; font-size: 21px; text-align: justify;">XXX气象局</span><span style="font-family: 仿宋_GB2312; font-size: 21px; text-align: justify;"></span>' +
                '</p>\n' +
                '<p style="text-align: right;">\n' +
                '    <span style="font-family: 仿宋_GB2312; font-size: 21px; text-align: justify;">'+option.dateTime+'</span>\n' +
                '</p>\n' +
                '<p style="text-indent:13px;line-height:37px">\n' +
                '    <span style="font-size:21px;font-family:仿宋_GB2312;"><br/></span>\n' +
                '</p>\n' +
                '<p style="text-indent:13px;line-height:37px">\n' +
                '    <br/>\n' +
                '</p>\n' +
                '<p style="text-indent:13px;line-height:37px">\n' +
                '    <span style="font-size:21px;font-family:仿宋_GB23129;"></span>\n' +
                '</p>\n' +
                '<p style="text-indent:0">\n' +
                '    <strong><span style="font-size:21px;font-family:仿宋_GB2312;">24小时值班电话：XXXX-XXXXXXX 审核人：XX</span></strong>\n' +
                '</p>\n' +
                '<hr/>\n' +
                '<p style="text-indent: 0px;">\n' +
                '    <span style="font-size: 21px; font-family: 仿宋_GB2312, serif;">报送：</span>\n' +
                '</p>';
        }
    };
    //输出test接口
    exports('productTemplate', productTemplate);
});