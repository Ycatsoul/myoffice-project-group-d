package com.capgemini.cn.deemo.vo.response;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Data
public class DepartmentResponseVo {
    private Long listCount;

    private List<DepartmentVo> departmentVos;
}
