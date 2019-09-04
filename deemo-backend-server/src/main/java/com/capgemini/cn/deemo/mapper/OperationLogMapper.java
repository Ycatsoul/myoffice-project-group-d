package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.OperationLog;
import com.capgemini.cn.deemo.vo.request.OperationLogSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/4 16:35
 */
@Service
public interface OperationLogMapper {

    List<OperationLog> listOperationLogs(@Param("operationLogSearchVo") OperationLogSearchVo operationLogSearchVo);

    Integer countOperationLogs(@Param("operationLogSearchVo") OperationLogSearchVo operationLogSearchVo);

    Integer insertOperationLog(@Param("operationLog") OperationLog operationLog);

    Integer deleteOperationLogs(@Param("operationIds") List<Long> operationIds);
}
