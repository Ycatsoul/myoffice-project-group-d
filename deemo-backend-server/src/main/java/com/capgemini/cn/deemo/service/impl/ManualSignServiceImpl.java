package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.ManualSign;
import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.mapper.ManualSignMapper;
import com.capgemini.cn.deemo.mapper.OperationLogMapper;
import com.capgemini.cn.deemo.mapper.UserMapper;
import com.capgemini.cn.deemo.service.ManualSignService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.utils.OperationLogUtils;
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
    private final OperationLogMapper operationLogMapper;
    private final UserMapper userMapper;

    public ManualSignServiceImpl(ManualSignMapper manualSignMapper,
                                 OperationLogMapper operationLogMapper,
                                 UserMapper userMapper) {
        this.manualSignMapper = manualSignMapper;
        this.operationLogMapper = operationLogMapper;
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
    public Integer addManualSign(ManualSignEditVo manualSignEditVo) {
        manualSignEditVo.setManualSignId(IdWorker.get().nextId());

        operationLogMapper.insertOperationLog(
                OperationLogUtils.createOperationLog(
                        "打卡 - " + manualSignEditVo.getManualSignId())
        );

        return manualSignMapper.insertManualSign(manualSignEditVo);
    }

    @Override
    public Integer updateManualSign(ManualSignEditVo manualSignEditVo) {
        operationLogMapper.insertOperationLog(
                OperationLogUtils.createOperationLog(
                        "更新打卡 - " + manualSignEditVo.getManualSignId())
        );

        return manualSignMapper.updateManualSign(manualSignEditVo);
    }

    @Override
    public Integer deleteManualSigns(DeleteVo deleteVo) {
        for (Long id : deleteVo.getIds()) {
            operationLogMapper.insertOperationLog(
                    OperationLogUtils.createOperationLog(
                            "删除打卡 - " + id)
            );
        }

        return manualSignMapper.deleteManualSigns(deleteVo.getIds());
    }

    private ManualSignVo convertToVo(ManualSign manualSign) {
        ManualSignVo manualSignVo = new ManualSignVo();

        User user = userMapper.getUser(manualSign.getUserId());

        manualSignVo.setManualSignId(manualSign.getManualSignId());
        manualSignVo.setUserId(manualSign.getUserId());
        manualSignVo.setName(user == null ? null : user.getName());
        manualSignVo.setSignTime(manualSign.getSignTime());
        manualSignVo.setSignDesc(manualSign.getSignDesc());
        manualSignVo.setSignTag(manualSign.getSignTag());

        return manualSignVo;
    }
}
