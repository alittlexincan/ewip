package com.hyt.server.service.impl.config;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.config.CimissConfig;
import com.hyt.server.mapper.config.ICimissConfigMapper;
import com.hyt.server.service.config.ICimissConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CimissConfigServiceImpl
 * @Description 获取cimiss配置信息实现类
 * @Author Xincan
 * @Version 1.0
 **/
@Service("cimissConfigService")
public class CimissConfigServiceImpl extends AbstractService<CimissConfig> implements ICimissConfigService {

    @Autowired
    ICimissConfigMapper cimissConfigMapper;

    private final String URL = "%s/cimiss-web/api?userId=%s&pwd=%s&interfaceId=%s&dataCode=SURF_CHN_MUL_HOR&adminCodes=%s&elements=%s&times=%s&dataFormat=json";

    public JSONObject getRequestUrl(String areaId) {
        JSONObject json=new JSONObject();
        CimissConfig cimissConfig = new CimissConfig();
        cimissConfig.setAreaId(areaId);
        cimissConfig = cimissConfigMapper.selectOne(cimissConfig);
        if(cimissConfig!=null){
            String url=String.format(URL, cimissConfig.getUrl(), cimissConfig.getUserId(), cimissConfig.getUserPwd(), cimissConfig.getInterfaceId(), cimissConfig.getStationCode(), cimissConfig.getElements(), getTime());
            String stationId=cimissConfig.getStationId();
            json.put("url",url);
            json.put("stationId",stationId);
        }
        return json;
    }

    @Override
    public PageInfo<CimissConfig> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<CimissConfig> userInfoList = this.cimissConfigMapper.findAll(map);
        return new PageInfo<>(userInfoList);
    }


    /**
     * 时间计算（世界时计算）
     * 如果当前时间分钟大于10则减去8小时，否则减去9小时
     * @param
     * @return
     */
    private String getTime(){
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
