package com.capgemini.cn.deemo.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hasaker
 * @since 2019-08-22 15:05
 */
@Setter
@Getter
@ApiModel(description = "搜索基础类")
public class BaseSearchVo {

    @ApiModelProperty(value = "分页起始位置")
    private Integer start;
    
    @ApiModelProperty(value = "分页大小")
    private Integer size;

}
