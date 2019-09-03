package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.Branch;
import com.capgemini.cn.deemo.vo.request.BranchEditVo;
import com.capgemini.cn.deemo.vo.request.BranchSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:机构信息mapper
 * @Classname :BranchMapper
 * @author: Cola Guo
 * @date:2019-8-26 15:51
 */
@Service
public interface BranchMapper {
    Branch getBranch(@Param("branchId") Long branchId);

    List<Branch> listBranches(@Param("branchSearchVo") BranchSearchVo branchSearchVo);

    Integer countBranches(@Param("branchSearchVo") BranchSearchVo branchSearchVo);

    Integer insertBranch(@Param("branchEditVo") BranchEditVo branchEditVo);

    Integer updateBranch(@Param("branchEditVo") BranchEditVo branchEditVo);

    Integer deleteBranches(@Param("branchIds") List<Long> branchIds);
}
