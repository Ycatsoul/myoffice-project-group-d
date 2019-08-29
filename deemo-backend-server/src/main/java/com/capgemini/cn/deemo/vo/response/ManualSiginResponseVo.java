package com.capgemini.cn.deemo.vo.response;

import com.capgemini.cn.deemo.data.domain.ManualSigin;
import com.capgemini.cn.deemo.vo.request.BranchVo;
import com.capgemini.cn.deemo.vo.request.ManualSiginVo;
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
