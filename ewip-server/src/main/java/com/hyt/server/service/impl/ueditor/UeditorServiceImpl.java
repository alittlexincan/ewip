package com.hyt.server.service.impl.ueditor;

import com.hyt.server.mapper.ueditor.IUeditorMapper;
import com.hyt.server.service.ueditor.IUeditorService;
import com.hyt.server.utils.SendMailUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.hyt.server.utils.SendMailUtils.initJavaMailSender;


/**
 * @Auther: lxv
 * @Date: 2018/10/24 17:24
 * @Description:
 */
@Service("ueditorService")
public class UeditorServiceImpl implements IUeditorService {

    @Resource
    private IUeditorMapper mapper;

    @Override
    public int insert(Map<String, Object> map) {
        return mapper.insert(map);
    }

    /**
     * 发送邮件
     * @param map
     */
    @Override
    public void sendMail(Map<String, Object> map) {
        List<String> newlist = new ArrayList<String>();
        try {
            List<Map<String, Object>> list =mapper.selectUser(map);
            if(list.size()>0){
                for(int i=0;i<list.size();i++){
                    newlist.add(list.get(i).get("code").toString());
                }
            }
//            String[] user = { "2686909258@qq.com", "471046266@qq.com" };
            String[] user = newlist.toArray(new String[newlist.size()]);
            JavaMailSenderImpl sender = initJavaMailSender();
            String title=map.get("title").toString();
            String filePath=map.get("filePath").toString();
            SendMailUtils.sendWithAttament(sender,user,title,"",title+".doc",filePath);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
