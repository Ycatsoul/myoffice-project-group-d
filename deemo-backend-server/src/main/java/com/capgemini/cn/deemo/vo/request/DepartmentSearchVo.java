package com.capgemini.cn.deemo.vo.request;

import com.capgemini.cn.deemo.vo.request.DepartmentVo;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Data
public class DepartmentSearchVo {

//    //主键Id
//    private Integer id;
    //部门Id
    private Long departmentId;
    //部门名称
    private String departmentName;
    //联系电话
    private String connectPhone;
    //联系电话（座机）
    private String connectTelephone;
    //法人id
    private Long principalUserId;
    //分部门id
    private Long branchId;
    //当前页
    private int page;

    //一页几条数据
    private int limit;
}
