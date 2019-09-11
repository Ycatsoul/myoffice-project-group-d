package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.Menu;
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
 * @since 2019/9/9 12:19
 */
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MenuMapperTest.class)
@MapperScan("com.capgemini.cn.deemo.mapper")
public class MenuMapperTest {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Test
    public void addMenu() {
        Long ROLE_ADMIN_ID = 2867588375773184L;
        Long ROLE_USER_ID = 2867590938492928L;

        String[][] menuUrls = {
                {"/", "/", "/", "/", "/", "/"},
                {"/branch/**", "/department/**", "/user/**"},
                {"/schedule/**", "/schedule/**", "/note/**"},
                {"/file/**", "/file/**", "/trash/**"},
                {"/message/**", "/message/**"},
                {"/manualSign/**", "/manualSign/**", "/manualSign/**"},
                {"/loginLog/**", "/operationLog/**", "/role/**", "/menu/**"}
        };

        String[][] menuPaths = {
                {"/home", "/home", "/home", "/home", "/home", "/home"},
                {"/personal/organization", "/personal/department", "/personal/employee"},
                {"/schedule/mySchedule", "/schedule/departmentSchedule", "/schedule/myNotes"},
                {"/document/docManage", "/document/fileSearch", "/document/recycle"},
                {"/message/msgManage", "/message/mailBox"},
                {"/check/checkCount", "/check/Inquiry", "/check/employeeCheck"},
                {"/system/loginRecord", "/system/operateRecord", "/system/roleManage", "/system/menuOrder"}
        };

        String[][] components = {
                {"Home", "Home", "Home", "Home", "Home", "Home"},
                {"organization", "department", "employee"},
                {"mySchedule", "departmentSchedule", "myNotes"},
                {"docManage", "fileSearch", "recycle"},
                {"msgManage", "mailBox"},
                {"checkCount", "checkInquiry", "employeeCheck"},
                {"loginRecord", "operateRecord", "roleManage", "menuOrder"}
        };

        String[][] names = {
                {"人事管理", "日程管理", "文档管理", "消息管理", "考勤管理", "系统管理"},
                {"机构管理", "部门管理", "员工管理"},
                {"我的日程", "部门日程", "我的便签"},
                {"文件管理", "文件搜索", "回收站"},
                {"信息管理", "信箱"},
                {"考勤统计", "考勤历史记录查询", "员工签到/签退"},
                {"登录日志", "操作日志", "角色管理", "菜单排序"}
        };

        for (int i = 0; i < names[0].length; i++) {
            int finalI = i;
            Menu menu = new Menu(){{
                setMenuId(IdWorker.get().nextId());
                setMenuUrl(menuUrls[0][finalI]);
                setMenuPath(menuPaths[0][finalI]);
                setMenuComponent(components[0][finalI]);
                setMenuName(names[0][finalI]);
                setParentMenuId(0L);
            }};
            menuMapper.addMenu(menu);

            switch (finalI) {
                case 1:
                case 6:
                    menuRoleMapper.addMenuRole(IdWorker.get().nextId(), menu.getMenuId(), ROLE_ADMIN_ID);
                    break;
                default:
                    menuRoleMapper.addMenuRole(IdWorker.get().nextId(), menu.getMenuId(), ROLE_ADMIN_ID);
                    menuRoleMapper.addMenuRole(IdWorker.get().nextId(), menu.getMenuId(), ROLE_USER_ID);
            }


            for (int j = 0; j < names[finalI + 1].length; j++) {
                int finalJ = j;
                Menu childMenu = new Menu(){{
                    setMenuId(IdWorker.get().nextId());
                    setMenuUrl(menuUrls[finalI + 1][finalJ]);
                    setMenuPath(menuPaths[finalI + 1][finalJ]);
                    setMenuComponent(components[finalI + 1][finalJ]);
                    setMenuName(names[finalI + 1][finalJ]);
                    setParentMenuId(menu.getMenuId());
                }};
                menuMapper.addMenu(childMenu);

                switch (finalI) {
                    case 1:
                    case 6:
                        menuRoleMapper.addMenuRole(IdWorker.get().nextId(), childMenu.getMenuId(), ROLE_ADMIN_ID);
                        break;
                    case 5:
                        if (finalJ == 2) {
                            menuRoleMapper.addMenuRole(IdWorker.get().nextId(), childMenu.getMenuId(), ROLE_USER_ID);
                        } else {
                            menuRoleMapper.addMenuRole(IdWorker.get().nextId(), childMenu.getMenuId(), ROLE_ADMIN_ID);
                        }
                        break;
                    default:
                        menuRoleMapper.addMenuRole(IdWorker.get().nextId(), childMenu.getMenuId(), ROLE_USER_ID);
                        menuRoleMapper.addMenuRole(IdWorker.get().nextId(), childMenu.getMenuId(), ROLE_ADMIN_ID);
                }
            }
        }
    }
}
