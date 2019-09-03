package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.BranchEditVo;
import com.capgemini.cn.deemo.vo.request.BranchSearchVo;
import com.capgemini.cn.deemo.vo.response.BranchVo;

import java.util.List;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
public interface BranchService {
    /**
     * 根据branchId获取Branch实体
     * @param branchId Long
     * @return Branch
     */
    RespVos<BranchVo> getBranch(Long branchId);

    /**
     * 根据branchSearchVo获取Branch列表
     * @param branchSearchVo BranchSearchVo
     * @return RespVos<BranchVo>
     */
    RespVos<BranchVo> listBranches(BranchSearchVo branchSearchVo);

    /**
     * 添加Branch
     * @param branchEditVo BranchEditVo
     * @return 1
     */
    Integer addBranch(BranchEditVo branchEditVo);

    /**
     * 编辑Branch
     * @param branchEditVo BranchEditVo
     * @return 1
     */
    Integer updateBranch(BranchEditVo branchEditVo);

    /**
     * 删除Branch
     * @param branchIds branchId链表
     * @return 被删除的branch行数
     */
    Integer deleteBranches(List<Long> branchIds);
}
