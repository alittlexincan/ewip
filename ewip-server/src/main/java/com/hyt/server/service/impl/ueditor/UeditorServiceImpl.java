package com.hyt.server.service.impl.ueditor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.server.entity.sys.User;
import com.hyt.server.mapper.ueditor.IUeditorMapper;
import com.hyt.server.service.ueditor.IUeditorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @Auther: lxv
 * @Date: 2018/10/24 17:24
 * @Description:
 */
@Service("ueditorService")
public class UeditorServiceImpl implements IUeditorService {

    @Resource
    private IUeditorMapper mapper;

    /**
     * 保存产品
     * @param map
     */
    @Override
    public int insert(Map<String, Object> map) {
        return mapper.insert(map);
    }

    /**
     * 发送邮件
     * @param map
     */
    @Override
    public JSONObject sendData(Map<String, Object> map) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject emailJson = new JSONObject();
        JSONArray array=new JSONArray();
        List<User> list =mapper.selectUser(map);
        String title=map.get("title").toString();
        String filePath=map.get("filePath").toString();
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                JSONObject json=new JSONObject();
                json.put("email",list.get(i).getCode());
                json.put("userName",list.get(i).getName());
                array.add(json);
            }
        }
        emailJson.put("title",title);
        emailJson.put("filePath",filePath);
        emailJson.put("user",array);
        data.put("email",emailJson);
        result.put("code", 200);
        result.put("msg", "获取成功");
        result.put("data", data);
        System.out.println(result);

//        List<String> newlist = new ArrayList<String>();
//        try {
//            List<User> list =mapper.selectUser(map);
//            if(list.size()>0){
//                for(int i=0;i<list.size();i++){
//                    json.put("email",list.get(i).getCode());
//                    newlist.add(list.get(i).getCode());
//                }
//            }
//          String[] user = { "2686909258@qq.com", "471046266@qq.com" };
//            String[] user = newlist.toArray(new String[newlist.size()]);


//            JavaMailSenderImpl sender = initJavaMailSender();
//            SendMailUtils.sendWithAttament(sender,user,title,"",title+".doc",filePath);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }

//        String str = "{" +
//                "email:{" +
//                "titile:\"asdf\"," +
//                "url:\"asdfasdf\"," +
//                "user:[{\"email\":\"2686909258@qq.com\",\"userName\":\"李卫东\"}]" +
//                "}" +
//                "}";
//        JSONObject data1 = JSONObject.parseObject(str);
//
//
//        result.put("code", 200);
//        result.put("msg", "获取成功");
//        result.put("data", data1);
        return result;
    }



}
