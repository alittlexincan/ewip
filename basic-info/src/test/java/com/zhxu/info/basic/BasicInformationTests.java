package com.zhxu.info.basic;

import com.zhxu.info.BasicInformation;
import com.zhxu.info.basic.entity.DisasterType;
import com.zhxu.info.basic.repository.DisasterTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BasicInformation.class)
public class BasicInformationTests {

    @Autowired
    private DisasterTypeRepository disasterTypeRepository;

    @Test
    public void contextLoads() {
        List<DisasterType> all = disasterTypeRepository.findAll();
        all.forEach(System.out::println);
    }
}
