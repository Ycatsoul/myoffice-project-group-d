package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.PreContract;
import com.capgemini.cn.deemo.vo.request.PreContractEditVo;

import java.util.List;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/23 20:08
 */
public interface PreContractService {

    public List<PreContract> listPreContracts();

    public PreContract listPreContract(Long preContractId);

    public Integer updatePreContract(PreContractEditVo preContractEditVo);

    public Integer insertPreContract(PreContractEditVo preContractEditVo);

    public Integer deletePreContract(Long userId);
}
