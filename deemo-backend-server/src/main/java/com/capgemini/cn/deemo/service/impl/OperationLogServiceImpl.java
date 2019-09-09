package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.OperationLog;
import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.mapper.OperationLogMapper;
import com.capgemini.cn.deemo.mapper.UserMapper;
import com.capgemini.cn.deemo.service.OperationLogService;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.request.OperationLogSearchVo;
import com.capgemini.cn.deemo.vo.response.OperationLogVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hasaker
 * @since 2019/9/4 22:27
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {
    private final OperationLogMapper operationLogMapper;
    private final UserMapper userMapper;

    public OperationLogServiceImpl(OperationLogMapper operationLogMapper, UserMapper userMapper) {
        this.operationLogMapper = operationLogMapper;
        this.userMapper = userMapper;
    }

    @Override
    public RespVos<OperationLogVo> listOperationLogs(OperationLogSearchVo operationLogSearchVo) {
        List<OperationLog> operationLogs = operationLogMapper.listOperationLogs(operationLogSearchVo);

        if (operationLogs != null && operationLogs.size() > 0) {
            RespVos<OperationLogVo> respVos = new RespVos<>();
            respVos.setSize(operationLogMapper.countOperationLogs(operationLogSearchVo));
            respVos.setVos(operationLogs.stream().map(this::convertToVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    @Override
    public Integer insertOperationLog(OperationLog operationLog) {
        return operationLogMapper.insertOperationLog(operationLog);
    }

    @Override
    public Integer deleteOperationLogs(DeleteVo deleteVo) {
        return operationLogMapper.deleteOperationLogs(deleteVo.getIds());
    }

    private OperationLogVo convertToVo(OperationLog operationLog) {
        OperationLogVo operationLogVo = new OperationLogVo();

        User user = userMapper.getUser(operationLog.getOperationUserId());

        operationLogVo.setOperationLogId(operationLog.getOperationId());
        operationLogVo.setOperationUserId(operationLog.getOperationUserId());
        operationLogVo.setOperationUserName(user == null ? null : user.getName());
        operationLogVo.setOperationName(operationLog.getOperationName());
        operationLogVo.setOperationDesc(operationLog.getOperationDesc());
        operationLogVo.setOperationTime(operationLog.getOperationTime());

        return operationLogVo;
    }
}
