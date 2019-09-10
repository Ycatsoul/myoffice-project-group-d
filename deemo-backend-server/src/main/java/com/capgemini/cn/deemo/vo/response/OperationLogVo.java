package com.capgemini.cn.deemo.vo.response;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author hasaker
 * @since 2019/9/4 15:24
 */
@Data
public class OperationLogVo {

    private Long operationLogId;

    private Long operationUserId;

    private String operationUserName;

    private String operationName;

    private String operationDesc;

    private Timestamp operationTime;
}
