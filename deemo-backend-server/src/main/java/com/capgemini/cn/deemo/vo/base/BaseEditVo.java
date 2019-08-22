package com.capgemini.cn.deemo.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hasaker
 * @since 2019-08-22 15:13
 */
@Setter
@Getter
@ApiModel(description = "编辑基础类")
public class BaseEditVo {

    @ApiModelProperty(value = "所要编辑对象的ID")
    private Long objectId;
}
