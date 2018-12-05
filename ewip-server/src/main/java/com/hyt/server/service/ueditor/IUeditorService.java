package com.hyt.server.service.ueditor;


import com.alibaba.fastjson.JSONObject;

import java.util.Map;


public interface IUeditorService {

    int insert(Map<String, Object> map);

    JSONObject sendData(Map<String,Object> map);
}
