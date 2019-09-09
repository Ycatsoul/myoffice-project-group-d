package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.PreContract;
import com.capgemini.cn.deemo.mapper.PreContractMapper;
import com.capgemini.cn.deemo.service.PreContractService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.vo.request.PreContractEditVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/23 14:25
 */
@Service
public class PreContractServiceImpl implements PreContractService{

    private final PreContractMapper preContractMapper;

    public PreContractServiceImpl(PreContractMapper preContractMapper) {
        this.preContractMapper = preContractMapper;
    }

    /**
     * 获取预约信息
     */
    @Override
    public List<PreContract> listPreContracts() {
        return preContractMapper.listPreContracts();
    }

    /**
     * 根据preContractID获取信息
     */
    @Override
    public PreContract listPreContract(Long preContractId){
        return preContractMapper.listPreContract(preContractId);
    }

    /**
     * 更新
     */
    @Override
    public Integer updatePreContract(PreContractEditVo preContractEditVo){
        return preContractMapper.updatePreContract(preContractEditVo);
    }

    /**
     * 新增
     */
    @Override
    public Integer insertPreContract(PreContractEditVo preContractEditVo) {
        preContractEditVo.setPreContractId(IdWorker.get().nextId());

        return preContractMapper.insertPreContract(preContractEditVo);
    }

    /**
     * 删除
     */
    @Override
    public Integer deletePreContract(Long userId) {
        return preContractMapper.deletePreContract(userId);
    }
}
