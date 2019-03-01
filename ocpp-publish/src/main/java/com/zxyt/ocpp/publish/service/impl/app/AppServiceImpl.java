package com.zxyt.ocpp.publish.service.impl.app;

import com.alibaba.fastjson.JSONObject;
import com.zxyt.ocpp.publish.service.app.IAppService;
import com.zxyt.ocpp.publish.util.XinLangWeiBoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service("appService")
public class AppServiceImpl implements IAppService {

    @Override
    @Async
    public void app(JSONObject json) {

        String url = "http://192.168.1.111:8080/tour/share";
        String param = "title="+"预警标题"+"&content="+"预警内容";
        try {
            XinLangWeiBoUtil.sendPost(url, param);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    @Async
    public void sendApp(JSONObject json) {

        String url = "http://192.168.1.111:8080/tour/share";
        String param = "title="+"预警标题"+"&content="+"预警内容";
        try {
            XinLangWeiBoUtil.sendPost(url, param);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
