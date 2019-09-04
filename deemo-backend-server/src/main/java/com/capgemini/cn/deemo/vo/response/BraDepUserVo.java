package com.capgemini.cn.deemo.vo.response;

import lombok.Data;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/4 23:17
 * 获取
 */
@Data
public class BraDepUserVo {

    @Data
    public static class Department {

        @Data
        public static class User {

            Long userId;

            String name;
        }

        Long departmentId;

        String departmentName;

        List<User> users;
    }

    Long branchId;

    String branchShortName;

    List<Department> departments;
}
