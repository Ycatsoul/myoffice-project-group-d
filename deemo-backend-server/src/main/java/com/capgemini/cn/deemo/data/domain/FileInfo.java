package com.capgemini.cn.deemo.data.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

/**
 * @author hasaker
 * @since 2019-08-21 15:06
 */
@Data
@ApiModel
public class FileInfo {
    private Integer id;

    @ApiModelProperty(value = "文件ID")
    private Long fileId;

    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "文件类型ID")
    private Long fileTypeId;

    @ApiModelProperty(value = "文件所有者ID")
    private Long fileOwnerId;

    @ApiModelProperty(value = "文件父目录ID")
    private Long parentId;

    @ApiModelProperty(value = "文件上传时间")
    private Date uploadTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "文件是否被删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "在回收站中的父文件夹ID")
    private Long parentIdInTrash;

    @ApiModelProperty(value = "删除时间")
    private Date deleteTime;
}
