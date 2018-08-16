package com.hyt.server.service.impl.warn;

import com.alibaba.fastjson.JSONObject;
import com.hyt.server.service.warn.IRecordService;
import org.springframework.stereotype.Service;

/**
 * @Author: XincanJiang
 * @Description:
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Service("recordService")
public class RecordServiceImpl implements IRecordService {

    @Override
    public int record(JSONObject json) {

        System.out.println(json);

        return 0;
    }
}
