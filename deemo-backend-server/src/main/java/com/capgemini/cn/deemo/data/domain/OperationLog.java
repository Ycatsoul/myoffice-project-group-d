package com.capgemini.cn.deemo.data.domain;

import lombok.Data;

import java.sql.Date;

/**
 * @author hasaker
 * @since 2019/9/4 14:58
 */
@Data
public class OperationLog {

    private Long operationId;

    private Long operationUserId;

    private String operationName;

    private String operationDesc;

    private Date operationTime;
}
