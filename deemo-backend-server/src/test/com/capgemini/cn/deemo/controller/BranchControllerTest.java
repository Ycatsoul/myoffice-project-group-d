package src.test.com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.DeemoApplication;
import com.capgemini.cn.deemo.data.domain.Branch;
import com.capgemini.cn.deemo.service.BranchService;
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
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeemoApplication.class)
public class BranchControllerTest {

    @Autowired
    private BranchService branchService;

    @Test
    public void list() {
    }

    @Test
    public void info() {
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
//        Branch branch = new Branch();
//
//        branch.setId(2);
//        branch.setBranchName("gbj");
//        branch.setBranchId((long)005);
//        branchService.update(branch);

    }

    @Test
    public void delete() {
        int id = 7;
        branchService.delete(id);
    }
}