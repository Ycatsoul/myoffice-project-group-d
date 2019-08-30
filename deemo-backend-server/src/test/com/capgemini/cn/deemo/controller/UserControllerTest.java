package src.test.com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private UserService userService;


    @Test
    public void selectById() {
        int id = 2;
        userService.queryObject(id);
        log.info("查询成功");
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void update() {
//        User user = new User();
//        user.setId(5);
//        user.setUsername("gbj");
//        userService.update(user);
    }

    @Test
    public void save() {
        User user = new User();

        user.setUserId((long) 15);
        user.setUsername("guobaorou");
        user.setPassword("111111");
        user.setGender("0");
        user.setDepartmentId((long) 15001);
        userService.save(user);

    }

    @Test
    public void deleteUser() {
        int id = 8;
        userService.delete(id);
    }
}