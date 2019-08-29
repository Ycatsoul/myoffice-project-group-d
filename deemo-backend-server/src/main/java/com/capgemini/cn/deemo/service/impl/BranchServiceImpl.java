package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.Branch;
import com.capgemini.cn.deemo.data.dto.BranchDto;
import com.capgemini.cn.deemo.mapper.BranchMapper;
import com.capgemini.cn.deemo.service.BranchService;
import com.capgemini.cn.deemo.utils.ConvertUtils;
import com.capgemini.cn.deemo.vo.request.BranchSearchVo;
import com.capgemini.cn.deemo.vo.request.BranchVo;
import com.capgemini.cn.deemo.vo.response.BranchResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchMapper branchMapper;

    @Override
    public Branch queryObject(Integer id) {
        return branchMapper.queryObject(id);
    }

    @Override
    public List<Branch> queryList(Map<String, Object> map) {
        return branchMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return branchMapper.queryTotal(map);
    }

    @Override
    public boolean save(Branch branch) {
        return branchMapper.save(branch) > 0;
    }

    @Override
    public boolean update(Branch branch) {
        return branchMapper.update(branch) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return branchMapper.delete(id) > 0;
    }

    @Override
    public boolean deleteBatch(Integer[] ids) {
        return false;
    }

    @Override
    public BranchResponseVo listBranch(BranchSearchVo branchSearchVo) {
        BranchResponseVo branchResponseVo = new BranchResponseVo();
        List<Branch> branchList = branchMapper.listBranch(branchSearchVo);
        List<BranchVo> branchVos = new ArrayList<BranchVo>();
        for (Branch branch : branchList) {
            BranchVo branchVo = new BranchVo();
            branchVos.add(ConvertUtils.convertBranchToVo(branch));
        }
        branchResponseVo.setBranchVos(branchVos);
        branchResponseVo.setListCount(branchMapper.countBranch(branchSearchVo));
        return branchResponseVo;
    }

    @Override
    public List<BranchDto> queryAllBranch() {
        return branchMapper.queryAllBranch();
    }

//    @Override
//    public BranchResponseVo listBranch(BranchSearchVo branchSearchVo) {
//        BranchResponseVo branchResponseVo = new BranchResponseVo();
//        List<Branch> branchList = branchMapper.listBranch(branchSearchVo);
//        List<BranchVo> branchVos = new ArrayList<BranchVo>();
//        for (Branch branch : branchList) {
//            BranchVo branchVo = new BranchVo();
//            branchVos.add(ConvertUtils.convertDepartmentToVo(branch));
//        }
//        branchResponseVo.setBranchVos(branchVos);
//        branchResponseVo.setListCount(branchMapper.countBranch(branchSearchVo));
//        return branchResponseVo;
//    }
}
