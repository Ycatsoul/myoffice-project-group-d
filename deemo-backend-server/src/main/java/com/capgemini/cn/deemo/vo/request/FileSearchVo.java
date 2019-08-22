package com.capgemini.cn.deemo.vo.request;

import com.capgemini.cn.deemo.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * @author hasaker
 * @since 2019-08-22 11:02
 */
@Setter
@Getter
public class FileSearchVo {
    private String fileName;

    private String fileContent;

    private String createUserName;

    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone = DateUtils.DEFAULT_ZONE)
    private Date startTime;

    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone = DateUtils.DEFAULT_ZONE)
    private Date endTime;
}
