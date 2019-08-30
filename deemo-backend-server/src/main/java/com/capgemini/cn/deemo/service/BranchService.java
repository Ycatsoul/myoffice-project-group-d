package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.Branch;
import com.capgemini.cn.deemo.data.dto.BranchDto;
import com.capgemini.cn.deemo.vo.request.BranchSearchVo;
import com.capgemini.cn.deemo.vo.request.BranchVo;
import com.capgemini.cn.deemo.vo.response.BranchResponseVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Service
public interface BranchService {
    Branch queryObject(Integer id);

    List<Branch> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    boolean save(Branch branch);

    boolean update(Branch branch);

    boolean delete(Integer id);

    boolean deleteBatch(Integer[] ids);

    BranchResponseVo listBranch(BranchSearchVo branchSearchVo);

    List<BranchDto> queryAllBranch();
}
