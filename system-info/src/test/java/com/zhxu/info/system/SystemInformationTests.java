package com.zhxu.info.system;

import com.zhxu.info.system.entity.Organization;
import com.zhxu.info.system.entity.User;
import com.zhxu.info.system.repository.JobRepository;
import com.zhxu.info.system.repository.OrganizationRepository;
import com.zhxu.info.system.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SystemInformation.class)
public class SystemInformationTests {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Test
    public void contextLoads() {
        List<Organization> all = organizationRepository.findAll();
        all.forEach(organization -> {
            System.out.println(organization.getName() + ": " + organization.getType().getName());
        });
    }

    @Test
    public void usetTest() {
        List<User> all = userRepository.findAll();
        all.forEach(user -> {
            jobRepository.findAllByUserId(user.getId()).forEach(
                    job -> {
                        System.out.println(job.getJob());
                    }
            );
        });
    }
}
