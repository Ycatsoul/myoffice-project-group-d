package src.test.com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.DeemoApplication;
import com.capgemini.cn.deemo.data.domain.Department;
import com.capgemini.cn.deemo.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */

@Slf4j
@SpringBootTest(classes = DeemoApplication.class)
@RunWith(SpringRunner.class)
public class DepartmentControllerTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void list() {
        Department department = departmentService.queryObject(1);
        System.out.println(department);
    }

    @Test
    public void info() {
    }

    @Test
    public void save() {
        Department department = new Department();

        department.setDepartmentId((long) 15);
        department.setDepartmentName("guobaorou");
        department.setConnectPhone("111111");
        department.setConnectTelephone("12346");
        department.setPrincipalUserId((long) 002);
        department.setBranchId((long)1);
        departmentService.save(department);
    }

    @Test
    public void update() {
//        Department department = new Department();
//
//        department.setId(5);
//        department.setDepartmentName("gbj");
//        departmentService.update(department);

    }

    @Test
    public void delete() {
        int id = 7;
        departmentService.delete(id);
    }
}