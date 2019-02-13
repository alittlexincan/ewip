package com.hyt.server;

import com.hyt.server.entity.config.CimissConfig;
import com.hyt.server.entity.sys.User;
import com.hyt.server.mapper.config.ICimissConfigMapper;
import com.hyt.server.mapper.sys.IUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EwipServerApplication.class)
public class EwipServerApplicationTests {

    @Autowired
    private ICimissConfigMapper cimissConfigMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void contextLoads() {
        CimissConfig cimissConfig = new CimissConfig();
        cimissConfig.setAreaId("071e74c2e59011e88da08cec4b81c244");
        CimissConfig config = cimissConfigMapper.selectOne(cimissConfig);
        System.out.println(config);
    }

    @Test
    public void clean() {
        List<User> person =  jdbcTemplate.query("select o.* from usernew u inner join userold o on u.code=o.code ", new BeanPropertyRowMapper(User.class));
        List<User> usernew =  jdbcTemplate.query("select  * from usernew u where FIND_IN_SET(u.area_id, getAreaCList('cf6edfbae3f311e88da08cec4b81c244'))", new BeanPropertyRowMapper(User.class));
        person.forEach(user -> {
            Double old=user.getLongitude();
            Double lat=user.getLatitude();
            String code=user.getCode();
            System.out.println("update usernew set longitude='"+old+"',latitude='"+lat+"' where code='"+code+"';");
//            jdbcTemplate.execute("update usernew set longitude="+old+",latitude="+lat+" where code="+code+"");
        });
    }

}
