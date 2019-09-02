package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.Role;
import com.capgemini.cn.deemo.utils.IdWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hasaker
 * @since 2019/8/29 14:39
 */
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RoleMapperTest.class)
@MapperScan("com.capgemini.cn.deemo.mapper")
public class RoleMapperTest {

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void addRoleTest() {
        String[] roleNames = new String[]{"ROLE_ADMIN", "ROLE_HR", "ROLE_USER"};
        String[] roleDescs = new String[]{"超级管理员", "管理员(运维/HR)", "用户/员工"};

        for (int i = 0; i < roleDescs.length; i++) {
            Role role = new Role();
            role.setRoleId(IdWorker.get().nextId());
            role.setRoleName(roleNames[i]);
            role.setRoleDesc(roleDescs[i]);

            assert roleMapper.addRole(role) == 1;
        }
    }
}
