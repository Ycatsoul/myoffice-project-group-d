package com.capgemini.cn.deemo.vo.request;

import com.capgemini.cn.deemo.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

/**
 * @author hasaker
 * @since 2019-08-22 11:02
 *
 */
@Data
@ApiModel
public class FileInfoSearchVo {

    @ApiModelProperty(value = "文件名搜索字段")
    private String fileName = null;

    @ApiModelProperty(value = "文件创建人搜索字段")
    private String createUserName = null;

    @ApiModelProperty(value = "起始时间")
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone = DateUtils.DEFAULT_ZONE)
    private Date startTime = null;

    @ApiModelProperty(value = "截止时间")
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone = DateUtils.DEFAULT_ZONE)
    private Date endTime = null;

    @ApiModelProperty(value = "父文件夹ID")
    private Long parentId;

    @ApiModelProperty(value = "偏移量")
    private Integer start = 0;

    @ApiModelProperty(value = "分页大小")
    private Integer size = 10;
}
