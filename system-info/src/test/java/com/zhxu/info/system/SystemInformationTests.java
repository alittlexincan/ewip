package com.zhxu.info.system;

import com.zhxu.info.system.entity.Group;
import com.zhxu.info.system.entity.Organization;
import com.zhxu.info.system.entity.User;
import com.zhxu.info.system.repository.GroupRepository;
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

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void contextLoads() {
        List<Organization> all = organizationRepository.findAll();
        all.forEach(organization -> {
            System.out.println(organization.getName() + ": " + organization.getType().getName());
        });
    }

    @Test
    public void userTest() {
        List<User> all = userRepository.findAll();
        all.forEach(user -> {
            jobRepository.findAllByUserId(user.getId()).forEach(
                    job -> {
                        System.out.println(job.getJob());
                    }
            );
        });
    }

    @Test
    public void groupInsertTest() {
        Group group = new Group();
        group.setName("test");
        group.setDescription("test");
        Group g = groupRepository.save(group);
        System.out.println(g.getId());
    }
}
