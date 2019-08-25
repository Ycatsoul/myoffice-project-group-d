package com.capgemini.cn.deemo.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hasaker
 * @since 2019-08-22 11:02
 */
@Getter
@Setter
@ApiModel
public class FileInfoEditVo {
    @ApiModelProperty(value = "修改文件ID")
    private Long fileId;

    @ApiModelProperty(value = "修改后的文件名")
    private String fileName;

    @ApiModelProperty(value = "修改后的文件备注")
    private String remark;
}
