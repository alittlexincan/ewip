package com.zhxu.message.service.wechat;

import com.alibaba.fastjson.JSONObject;
import com.xincan.utils.disaster.DisasterUtil;
import com.xincan.utils.disaster.MsgTypeUtil;
import com.zhxu.message.model.wechat.WechatParam;
import com.zhxu.message.model.wechat.WechatTemplate;
import com.zhxu.message.model.wechat.WechatTemplateParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("wechatSender")
public class WechatSender {

    @Autowired
    private RestTemplate restTemplate;

    public void send(WechatParam param) {
        JSONObject result = new JSONObject();

        if(param.getTemplate()==null){
            result = sendMessageTemplate(param);//一键式发布
        }else {
            result = sendWarnTemplate(param);//预警发布
        }

        // 如果灾种存在则说明发的是预警,否则是一键发布信息,分别存储在指定的回执状态表种
//        if(json.containsKey("disasterCode")){
//            // 插入回执状态主表信息
//            this.callBackMapper.insertMainWarn(result);
//        }else {
//            // 插入回执状态主表信息
//            this.callBackMapper.insertMainMsg(result);
//        }

    }


    public JSONObject sendMessageTemplate(WechatParam param){

        // 存储发送结果主表信息
        JSONObject main = new JSONObject();

        // 获取所有关注用户信息，提取用户openId: 如果okUser有值，则说明是手动发送
        List<String> userList = param.getUserList();

        // 设置模板字体颜色
        String rgb = "#000000";
        // 获取用户总个数
        int count = userList.size();
        if(count <= 0){
            return this.callBackMsg("", "微信暂无关注用户");
        }
        int number = param.getNumber();
        for (int i = 0; i < count; i += number) {
            //作用为number最后没有200条数据则剩余几条newList中就装几条
            if (i + number > count) {
                number = count - i;
            }
            userList.subList(i, i + number).forEach(u -> {
                WechatTemplate tem = new WechatTemplate();
                tem.setTemplateId(param.getServiceTemplate());
                tem.setTopColor(rgb);
                tem.setToUser(u);
                tem.setUrl("");
                List<WechatTemplateParam> paras = new ArrayList<>();
                paras.add(new WechatTemplateParam("first",param.getTitle(), rgb));
                paras.add(new WechatTemplateParam("keyword1","", rgb));
                paras.add(new WechatTemplateParam("keyword2","这是预警地区。" , rgb));
                paras.add(new WechatTemplateParam("keyword3","这是预警内容。" , rgb));
                paras.add(new WechatTemplateParam("remark",param.getContent(), rgb));
                tem.setTemplateParamList(paras);
                String url = param.getTemplateUrl()+"?access_token="+param.getAccessToken();
                ResponseEntity<JSONObject> rest = this.restTemplate.postForEntity(url, tem.toJSON(), JSONObject.class);

            });
        }
        main.put("messageId", "");
        main.put("channelCode", "WECHAT");
        main.put("total", 1);
        main.put("success", 1);
        main.put("fail", 0);
        main.put("work","微信推送成功");
        return main;
    }

    /**
     * 发送预警模板消息
     * @param
     * @param
     * @return
     */
    private JSONObject sendWarnTemplate(WechatParam param){

        // 存储发送结果主表信息
        JSONObject main = new JSONObject();

        // 获取所有关注用户信息，提取用户openId: 如果okUser有值，则说明是手动发送
        List<String> userList = param.getUserList();
        // 3：获取灾种信息
        JSONObject disaster = DisasterUtil.getDisasterInfo(param.getDisasterColor());
        // 获取灾种名称
        String disasterName = param.getDisasterName()
                // 获取灾种颜色(rgb)
                , rgb = disaster.getString("rgb")
                // 获取灾种颜色(名称)
                ,color = disaster.getString("color");
        // 获取用户总个数
        int count = userList.size();
        if(count <= 0){
            return this.callBackMsg("", "微信暂无关注用户");
        }
        int number = param.getNumber();
        for (int i = 0; i < count; i += number) {
            if (i + number > count) {        //作用为number最后没有200条数据则剩余几条newList中就装几条
                number = count - i;
            }
            StringBuilder sb = new StringBuilder();
            userList.subList(i, i + number).forEach(u -> {
                sb.append("," + u);
                WechatTemplate tem = new WechatTemplate();
                tem.setTemplateId(param.getServiceTemplate());
                tem.setTopColor(rgb);
                tem.setToUser(u);
                tem.setUrl("");
                List<WechatTemplateParam> paras = new ArrayList<>();
                paras.add(new WechatTemplateParam("first",getWarnTitle(param, disaster), rgb));
                paras.add(new WechatTemplateParam("keyword1",param.getOrganizationName(), rgb));
                paras.add(new WechatTemplateParam("keyword2",disasterName, rgb));
                paras.add(new WechatTemplateParam("keyword3",color, rgb));
                paras.add(new WechatTemplateParam("keyword4","", rgb));
                paras.add(new WechatTemplateParam("remark",param.getContent(), rgb));
                tem.setTemplateParamList(paras);
                String url = param.getTemplateUrl()+"?access_token="+param.getAccessToken();
                ResponseEntity<JSONObject> rest = this.restTemplate.postForEntity(url, tem.toJSON(), JSONObject.class);
            });
        }
        main.put("messageId", "");
        main.put("channelCode", "WECHAT");
        main.put("total", 1);
        main.put("success", 1);
        main.put("fail", 0);
        main.put("work","微信推送成功");
        return main;

    }

    /**
     * 三天预报模板推送：模板类型：服务模板
     */
    private JSONObject sendThreeWeather(WechatParam param){

        JSONObject result = new JSONObject();

        // 2：获取所有关注用户信息，提取用户openId: 如果okUser有值，则说明是手动发送
        List<String> userList = param.getUserList();

        // 获取用户总个数
        int count = userList.size();
        if(count <= 0){
            result.put("code",404);
            result.put("code","微信暂无关注用户");
            return result;
        }
        int number = param.getNumber();
        for (int i = 0; i < count; i += number) {
            if (i + number > count) {        //作用为number最后没有200条数据则剩余几条newList中就装几条
                number = count - i;
            }
            StringBuilder sb = new StringBuilder();
            String rgb = "#3F48CC";
            userList.subList(i, i + number).forEach(u -> {
                sb.append("," + u);
                WechatTemplate tem = new WechatTemplate();
                tem.setTemplateId(param.getServiceTemplate());
                tem.setTopColor(rgb);
                tem.setToUser(u);
                tem.setUrl("");
                List<WechatTemplateParam> paras = new ArrayList<>();
                paras.add(new WechatTemplateParam("first",param.getTitle(), rgb));
                paras.add(new WechatTemplateParam("keyword1","type", rgb));
                paras.add(new WechatTemplateParam("keyword2","time", rgb));
                paras.add(new WechatTemplateParam("remark",param.getContent(), rgb));
                tem.setTemplateParamList(paras);
                String url = param.getTemplateUrl()+"?access_token="+param.getAccessToken();
                ResponseEntity<JSONObject> rest = this.restTemplate.postForEntity(url, tem.toJSON(), JSONObject.class);
            });
        }
        result.put("code",200);
        result.put("msg","三天预报推送成功");
        return result;
    }

    /**
     * 获取预警模板标题
     *
     * @param
     * @return
     */
    private String getWarnTitle(WechatParam param, JSONObject disaster){
        String title = param.getOrganizationName();
        String msgType = param.getMsgType();
        title += MsgTypeUtil.parseMsgType(msgType);
        title += param.getDisasterName();
        title += disaster.getString("color");
        title += "预警信号";
        title += disaster.getString("level");
        return title;
    }

    /**
     * 状态报告错误回执信息处理
     * @param id
     * @param msg
     * @return
     */
    private JSONObject callBackMsg(String id, String msg){
        JSONObject json = new JSONObject();
        json.put("messageId", id);
        json.put("channelCode", "WECHAT");
        json.put("total", 1);
        json.put("success", 0);
        json.put("fail", 1);
        json.put("work",msg);
        return json;
    }

}
