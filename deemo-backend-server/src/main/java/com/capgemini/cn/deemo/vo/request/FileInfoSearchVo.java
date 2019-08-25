package com.capgemini.cn.deemo.vo.request;

import com.capgemini.cn.deemo.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * @author hasaker
 * @since 2019-08-22 11:02
 */
@Setter
@Getter
@ApiModel
public class FileInfoSearchVo {

    @ApiModelProperty(value = "文件名搜索字段")
    private String fileName;

    @ApiModelProperty(value = "文件创建人搜索字段")
    private String createUserName;

    @ApiModelProperty(value = "文件创建起始时间")
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone = DateUtils.DEFAULT_ZONE)
    private Date startTime;

    @ApiModelProperty(value = "文件创建截止时间")
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone = DateUtils.DEFAULT_ZONE)
    private Date endTime;
}
