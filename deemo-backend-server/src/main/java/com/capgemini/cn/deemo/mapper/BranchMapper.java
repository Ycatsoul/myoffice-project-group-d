package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.Branch;
import com.capgemini.cn.deemo.data.dto.BranchDto;
import com.capgemini.cn.deemo.vo.request.BranchSearchVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:机构信息mapper
 * @Classname :BranchMapper
 * @author: Cola Guo
 * @date:2019-8-26 15:51
 */
public interface BranchMapper extends BaseMapper<Branch> {
    List<Branch> listBranch(@Param("branchSearch") BranchSearchVo branchSearchVo);
    Long countBranch(@Param("branchSearch") BranchSearchVo branchSearchVo);
    List<BranchDto> queryAllBranch();
}
