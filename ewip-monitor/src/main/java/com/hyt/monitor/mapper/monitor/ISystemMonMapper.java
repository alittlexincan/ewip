package com.hyt.monitor.mapper.monitor;


import java.util.List;
import java.util.Map;

/**
 * 渠道链路监控服务接口层
 * @author JiangXincan
 *
 */
public interface ISystemMonMapper {

    /**
     * 网络监控系统运行情况
     * @author lxv
     * @Title: systemMonitoring
     * @param @param object
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @throws
     */
    List<Map<String, Object>> systemMonitoring(Object object);

    /**
     * 终端监控对比基础数据
     * @author lxv
     * @Title: terminalMonitorBasic
     * @param @param object
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @throws
     */
    List<Map<String,Object>> terminalMonitorBasic(Object o);

    /**
     * 终端监控
     * @author lxv
     * @Title: warnTerminalMonitor
     * @param @param map
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @throws
     */
    List<Map<String,Object>> warnTerminalMonitor(Map<String,Object> map);
}
