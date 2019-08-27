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

    @ApiModelProperty(value = "父目录ID")
    private Long parentId;

    @ApiModelProperty(value = "备注")
    private String remark;
}