package com.hyt.server.service.ueditor;


import java.util.List;
import java.util.Map;


public interface IUeditorService {

    int insert(Map<String, Object> map);

    void sendMail(Map<String,Object> map);
}
