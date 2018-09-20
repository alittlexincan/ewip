package com.hyt.publish.mapper.publish;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;

/**
 * 短信接口数据回执接口
 */
@Repository("callBackMapper")
public interface ICallBackMapper {

    int insertMainMsg(JSONObject json);

    int insertChildMsg(JSONArray array);

    int insertMainWarn(JSONObject json);

    int insertChildWarn(JSONArray array);

}
