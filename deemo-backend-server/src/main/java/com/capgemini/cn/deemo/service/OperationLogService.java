package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.OperationLog;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.request.OperationLogSearchVo;
import com.capgemini.cn.deemo.vo.response.OperationLogVo;

/**
 * @author hasaker
 * @since 2019/9/4 22:12
 */
public interface OperationLogService {

    RespVos<OperationLogVo> listOperationLogs(OperationLogSearchVo operationLogSearchVo);

    Integer insertOperationLog(OperationLog operationLog);

    Integer deleteOperationLogs(DeleteVo deleteVo);
}
