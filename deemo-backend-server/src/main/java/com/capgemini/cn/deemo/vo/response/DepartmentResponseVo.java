package com.capgemini.cn.deemo.vo.response;

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
public class DepartmentResponseVo {
    private Long listCount;

    private List<DepartmentVo> departmentVos;
}