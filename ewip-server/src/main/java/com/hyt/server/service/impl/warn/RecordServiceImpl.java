package com.hyt.server.service.impl.warn;

import com.alibaba.fastjson.JSONObject;
import com.hyt.server.service.warn.IRecordService;
import com.xincan.utils.ftp.FTPConfig;
import com.xincan.utils.ftp.FTPUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Service("recordService")
public class RecordServiceImpl implements IRecordService {

    // ftp 路径
    @Value("${ftp.url}")
    private String url;

    // ftp 端口号
    @Value("${ftp.port}")
    private int port;

    // 上传文件存放位置
    @Value("${ftp.path}")
    private String path;

    // 登录名称
    @Value("${ftp.login.name}")
    private String loginName;

    // 登录密码
    @Value("${ftp.login.password}")
    private String loginPassword;

    // 下载文件存放位置
    @Value("${ftp.local.file}")
    private String localFile;

    @Override
    public int record(JSONObject json) {
        System.out.println(json);
        // ftp 基础配置信息
        FTPConfig config = new FTPConfig(this.url, this.port, this.path, this.loginName, this.loginPassword,null);
        // ftp 登录
        FTPUtil.ftpLogin(config);
        File file = new File("D:\\images\\channel\\01-短信.png");
        // ftp 文件上传
        FTPUtil.uploadFile("D:\\images\\channel\\01-短信.png", "短信.png");
        // ftp 关闭
        FTPUtil.close();
        return 0;
    }
}
