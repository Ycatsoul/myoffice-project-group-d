package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.Branch;
import com.capgemini.cn.deemo.mapper.BranchMapper;
import com.capgemini.cn.deemo.mapper.OperationLogMapper;
import com.capgemini.cn.deemo.service.BranchService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.utils.OperationLogUtils;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.BranchEditVo;
import com.capgemini.cn.deemo.vo.request.BranchSearchVo;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.response.BranchVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Service
public class BranchServiceImpl implements BranchService {

    private final BranchMapper branchMapper;
    private final OperationLogMapper operationLogMapper;

    public BranchServiceImpl(BranchMapper branchMapper, OperationLogMapper operationLogMapper) {
        this.branchMapper = branchMapper;
        this.operationLogMapper = operationLogMapper;
    }

    @Override
    public RespVos<BranchVo> getBranch(Long branchId) {
        Branch branch = branchMapper.getBranch(branchId);

        if (branch != null) {
            RespVos<BranchVo> respVos = new RespVos<>();
            respVos.setSize(1);
            respVos.setVos(new ArrayList<BranchVo>(){{
                add(convertToVo(branch));
            }});

            return respVos;
        }

        return null;
    }

    @Override
    public RespVos<BranchVo> listBranches(BranchSearchVo branchSearchVo) {
        List<Branch> branches = branchMapper.listBranches(branchSearchVo);

        if (branches != null && branches.size() > 0) {
            RespVos<BranchVo> respVos = new RespVos<>();
            respVos.setSize(branchMapper.countBranches(branchSearchVo));
            respVos.setVos(branches.stream().map(this::convertToVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    @Override
    public Integer addBranch(BranchEditVo branchEditVo) {
        branchEditVo.setBranchId(IdWorker.get().nextId());
        operationLogMapper.insertOperationLog(
                OperationLogUtils.createOperationLog("添加机构 - " + branchEditVo.getBranchName())
        );

        return branchMapper.insertBranch(branchEditVo);
    }

    @Override
    public Integer updateBranch(BranchEditVo branchEditVo) {
        operationLogMapper.insertOperationLog(
                OperationLogUtils.createOperationLog("修改机构 - " + branchEditVo.getBranchName())
        );

        return branchMapper.updateBranch(branchEditVo);
    }

    @Override
    public Integer deleteBranches(DeleteVo deleteVo) {
        Integer res = branchMapper.deleteBranches(deleteVo.getIds());

        if (res > 0) {
            for (Long id : deleteVo.getIds()) {
                operationLogMapper.insertOperationLog(
                        OperationLogUtils.createOperationLog(
                                "删除机构 - " + branchMapper.getBranch(id).getBranchName())
                );
            }
        }

        return res;
    }

    private BranchVo convertToVo(Branch branch) {
        BranchVo branchVo = new BranchVo();

        branchVo.setBranchId(branch.getBranchId());
        branchVo.setBranchName(branch.getBranchName());
        branchVo.setBranchShortName(branch.getBranchShortName());

        return branchVo;
    }

}
