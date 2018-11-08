package com.hyt.client.controller.cimiss;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.sys.IAlarmThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("cimiss")
public class CimissController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IAlarmThresholdService alarmThresholdService;

    /**
     * 获取cimiss路径（根据地区查询实况信息）
     */
    @Value("${cimiss.area.shikuang}")
    private String cimissAreaShikuang;

    @GetMapping("shikuang")
    public JSONObject getCimissAreaShikuang(){
        JSONObject result = new JSONObject();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String time = sdf.format(new Date());
            String url = this.cimissAreaShikuang.replace("{{time}}", time);
            System.out.println(url);

            ResponseEntity<JSONObject> rest = restTemplate.getForEntity(url, JSONObject.class);
            int status = rest.getStatusCode().value();
            if(status == 200){
                JSONObject json = new JSONObject();
                JSONObject body = rest.getBody(); // 获取cimiss主体信息
                if(result.getInteger("returnCode") == 200){
                    json = body.getJSONArray("DS").getJSONObject(0);
                }
                JSONObject alarn = this.alarmThresholdService.select(null); // 获取阈值信息
                JSONArray array = alarn.getJSONArray("data");

                for(int i = 0; i<array.size(); i++){
                    JSONObject obj = array.getJSONObject(i);
                    int maxtmp = obj.getInteger("maxtmp");
                    int mintmp = obj.getInteger("mintmp");
                    if(json.getInteger("TEM") > mintmp && json.getInteger("TEM") < maxtmp){
                        result.put("status", 200);
                        result.put("msg", obj.getString("remarks"));
                    }
                }
            }
        }catch (Exception e){
            result.put("status", 500);
            result.put("msg", "暂无消息");
        }
        return result;
    }

}
