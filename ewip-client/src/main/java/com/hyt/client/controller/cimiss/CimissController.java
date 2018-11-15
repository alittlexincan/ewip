package com.hyt.client.controller.cimiss;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.sys.IAlarmThresholdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Slf4j
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
    public JSONObject getCimissAreaShikuang(@RequestParam Map<String, Object> map){
        JSONObject result = new JSONObject();
        try {

            String url = this.cimissAreaShikuang.replace("{{time}}", getTime());
            log.info("cimiss请求URL:" + url);
            System.out.println(url);
            ResponseEntity<JSONObject> rest = restTemplate.getForEntity(url, JSONObject.class);
            // rest接口抓取数据之后判断其状态码
            if(rest.getStatusCode().value() != 200){
                result.put("status", 500);
                result.put("msg", "CIMISS接口调用错误");
                result.put("ulr", url);
                log.info(result.toJSONString());
                return result;
            }

            // 获取cimiss主体信息
            JSONObject body = rest.getBody();

            if(body.getInteger("returnCode") != 0){
                result.put("status", 500);
                result.put("msg", "CIMISS没有数据或读取错误");
                log.info(result.toJSONString());
                return result;
            }

            // 存储cimiss数据
            JSONArray cimissArray = body.getJSONArray("DS");

            // 获取阈值信息
            JSONObject alarm = this.alarmThresholdService.select(map);

            if(alarm.getInteger("code") != 200){
                result.put("status", 500);
                result.put("msg", "SERVER端后台报错");
                log.info(result.toJSONString());
                return result;
            }

            if(alarm.getJSONArray("data").size()==0){
                result.put("status", 500);
                result.put("msg", "SERVER端没有数据，请添加阈值");
                log.info(result.toJSONString());
                return result;
            }
            // 存储阈值数据
            JSONArray alarmArray = alarm.getJSONArray("data");
            // 存储返回客户端数据
            JSONArray data = new JSONArray();
            cimissArray.forEach(stationItem ->  {
                JSONObject station = JSONObject.parseObject(stationItem.toString());
                double temMax = station.getDoubleValue("TEM_Max");
                double preH = station.getDoubleValue("PRE_1h");
                station.put("Type", "blue");
                alarmArray.forEach(temItem -> {

                    JSONObject tem = JSONObject.parseObject(temItem.toString());
                    double max = tem.getDoubleValue("maxtmp"); // 高温
                    double rain = tem.getDoubleValue("hourRainNum"); // 降水
                    String name = tem.getString("name");        // 隐患名称

                    if(name.equals("森林火灾重点防范区")){
                        // 温度比对
                        if (temMax >= max && temMax < 999999) {
                            station.put("Type", "red");
                            station.put("PreMsg", tem.getString("remarks"));
                        }
                    }else{
                        // 降水比对
                        if (preH >= rain && preH < 999999) {
                            station.put("Type", "red");
                            station.put("RainMsg", tem.getString("remarks"));
                        }
                    }
                });
                data.add(station);
            });
            result.put("data", data);
            result.put("status", 200);
            result.put("msg", "读取成功");
            log.info(result.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
            result.put("status", 500);
            result.put("msg", "接口报错"+e.getMessage());
            log.info(result.toJSONString());
            return result;
        }
        return result;
    }

    /**
     * 时间计算（世界时计算）
     * 如果当前时间分钟大于10则减去8小时，否则减去9小时
     * @param
     * @return
     */
    private static String getTime(){
        Date date = new Date();
        SimpleDateFormat minSdf = new SimpleDateFormat("mm");
        int min = Integer.parseInt(minSdf.format(new Date()));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//date 换成已经已知的Date对象
        cal.add(Calendar.HOUR_OF_DAY, - (min > 10 ? 8 : 9));// before 8 hour
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH0000");
        return sdf.format(cal.getTime());
    }
}
