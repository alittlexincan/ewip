package com.hyt.monitor.service.monitor;


import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 网络监控服务接口层
 * @author lixioawei
 *
 */
public interface SystemMonitorService {
    /**
     * 网络监控系统运行监控
     * @return
     * @throws Exception
     */
    JSONObject systemMonitoring(Object object);


    /**
     * 网络监控--终端监控
     * @param map
     * @return
     * @throws Exception
     */
    JSONObject warnTerminalMonitor(Map<String, Object> map);

}
