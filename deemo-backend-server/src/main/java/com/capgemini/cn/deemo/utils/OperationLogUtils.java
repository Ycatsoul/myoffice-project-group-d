package com.capgemini.cn.deemo.utils;

import com.capgemini.cn.deemo.data.domain.OperationLog;

/**
 * @author hasaker
 * @since 2019/9/9 23:49
 */
public class OperationLogUtils {

    public static OperationLog createOperationLog(String operationName) {
        OperationLog operationLog = new OperationLog();
        operationLog.setOperationId(IdWorker.get().nextId());
        operationLog.setOperationUserId(UserUtils.getCurrentUser().getUserId());
        operationLog.setOperationName(operationName);
        operationLog.setOperationTime(new java.sql.Timestamp(System.currentTimeMillis()));

        return operationLog;
    }
}
