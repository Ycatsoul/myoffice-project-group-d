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
public class ManualSiginResponseVo {
    private Long listCount;

    private List<ManualSiginVo> manualSiginVos;
}
