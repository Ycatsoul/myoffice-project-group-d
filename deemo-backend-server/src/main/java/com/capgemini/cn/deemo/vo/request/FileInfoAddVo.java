package com.capgemini.cn.deemo.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hasaker
 * @since 2019-08-22 11:02
 */
@Data
@ApiModel
public class FileInfoAddVo {
    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "文件类型ID")
    private Long fileTypeId;

    @ApiModelProperty(value = "文件拥有者ID")
    private Long fileOwnerId;

    @ApiModelProperty(value = "父目录ID")
    private Long parentId;

    @ApiModelProperty(value = "备注")
    private String remark;
}
