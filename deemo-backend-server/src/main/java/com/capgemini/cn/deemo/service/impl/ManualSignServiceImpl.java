package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.Branch;
import com.capgemini.cn.deemo.data.domain.Department;
import com.capgemini.cn.deemo.data.domain.ManualSign;
import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.mapper.BranchMapper;
import com.capgemini.cn.deemo.mapper.DepartmentMapper;
import com.capgemini.cn.deemo.mapper.ManualSignMapper;
import com.capgemini.cn.deemo.mapper.UserMapper;
import com.capgemini.cn.deemo.service.ManualSignService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.utils.UserUtils;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.request.ManualSignEditVo;
import com.capgemini.cn.deemo.vo.request.ManualSignSearchVo;
import com.capgemini.cn.deemo.vo.response.ManualSignVo;
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
public class ManualSignServiceImpl implements ManualSignService {

    private final ManualSignMapper manualSignMapper;
    private final BranchMapper branchMapper;
    private final DepartmentMapper departmentMapper;
    private final UserMapper userMapper;

    public ManualSignServiceImpl(ManualSignMapper manualSignMapper,
                                 BranchMapper branchMapper,
                                 DepartmentMapper departmentMapper,
                                 UserMapper userMapper) {
        this.manualSignMapper = manualSignMapper;
        this.branchMapper = branchMapper;
        this.departmentMapper = departmentMapper;
        this.userMapper = userMapper;
    }

    @Override
    public RespVos<ManualSignVo> getManualSign(Long manualSignId) {
        ManualSign manualSign = manualSignMapper.getManualSign(manualSignId);

        if (manualSign != null) {
            RespVos<ManualSignVo> respVos = new RespVos<>();
            respVos.setSize(1);
            respVos.setVos(new ArrayList<ManualSignVo>(){{
                add(convertToVo(manualSign));
            }});

            return respVos;
        }

        return null;
    }

    @Override
    public RespVos<ManualSignVo> listManualSigns(ManualSignSearchVo manualSignSearchVo) {
        List<ManualSign> manualSigns = manualSignMapper.listManualSigns(manualSignSearchVo);

        if (manualSigns != null && manualSigns.size() > 0) {
            RespVos<ManualSignVo> respVos = new RespVos<>();
            respVos.setSize(manualSignMapper.countManualSigns(manualSignSearchVo));
            respVos.setVos(manualSigns.stream().map(this::convertToVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    @Override
    public ManualSignVo addManualSign(ManualSignEditVo manualSignEditVo) {
        manualSignEditVo.setManualSignId(IdWorker.get().nextId());
        manualSignEditVo.setUserId(UserUtils.getCurrentUser().getUserId());

        Integer res = manualSignMapper.insertManualSign(manualSignEditVo);

        ManualSign manualSign = manualSignMapper.getManualSign(manualSignEditVo.getManualSignId());
        User user = userMapper.getUser(manualSignEditVo.getUserId());
        Department department = departmentMapper.getDepartment(user.getDepartmentId());
        Branch branch = branchMapper.getBranch(department.getBranchId());

        ManualSignVo manualSignVo = new ManualSignVo();
        manualSignVo.setManualSignId(manualSign.getManualSignId());
        manualSignVo.setUserId(user.getUserId());
        manualSignVo.setUsername(user.getUsername());
        manualSignVo.setName(user.getName());
        manualSignVo.setDepartmentName(department.getDepartmentName());
        manualSignVo.setBranchName(branch.getBranchName());
        manualSignVo.setSignDesc(manualSign.getSignDesc());
        manualSignVo.setSignTag(manualSign.getSignTag());
        manualSignVo.setSignTime(manualSign.getSignTime());

        return res > 0 ? manualSignVo : null;
    }

    @Override
    public Integer updateManualSign(ManualSignEditVo manualSignEditVo) {

        return manualSignMapper.updateManualSign(manualSignEditVo);
    }

    @Override
    public Integer deleteManualSigns(DeleteVo deleteVo) {

        return manualSignMapper.deleteManualSigns(deleteVo.getIds());
    }

    private ManualSignVo convertToVo(ManualSign manualSign) {
        ManualSignVo manualSignVo = new ManualSignVo();

        User user = userMapper.getUser(manualSign.getUserId());
        Department department = departmentMapper.getDepartment(user.getDepartmentId());
        Branch branch = branchMapper.getBranch(department.getBranchId());

        manualSignVo.setManualSignId(manualSign.getManualSignId());
        manualSignVo.setUserId(manualSign.getUserId());
        manualSignVo.setUsername(user.getUsername());
        manualSignVo.setName(user.getName());
        manualSignVo.setDepartmentName(department.getDepartmentName());
        manualSignVo.setBranchName(branch.getBranchName());
        manualSignVo.setSignTime(manualSign.getSignTime());
        manualSignVo.setSignDesc(manualSign.getSignDesc());
        manualSignVo.setSignTag(manualSign.getSignTag());

        return manualSignVo;
    }
}
