package com.hyt.server;

import com.hyt.server.entity.config.CimissConfig;
import com.hyt.server.mapper.config.ICimissConfigMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EwipServerApplication.class)
public class EwipServerApplicationTests {

    @Autowired
    private ICimissConfigMapper cimissConfigMapper;

    @Test
    public void contextLoads() {
        CimissConfig cimissConfig = new CimissConfig();
        cimissConfig.setAreaId("071e74c2e59011e88da08cec4b81c244");
        CimissConfig config = cimissConfigMapper.selectOne(cimissConfig);
        System.out.println(config);
    }

}
