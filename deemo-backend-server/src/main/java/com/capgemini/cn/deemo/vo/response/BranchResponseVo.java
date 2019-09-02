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
public class BranchResponseVo {
    private Long listCount;

    private List<BranchVo> branchVos;
}
