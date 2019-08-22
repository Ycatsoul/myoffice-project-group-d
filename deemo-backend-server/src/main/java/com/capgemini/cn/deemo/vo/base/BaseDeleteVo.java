package com.capgemini.cn.deemo.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author hasaker
 * @since 2019-08-22 15:11
 */
@Setter
@Getter
@ApiModel(description = "删除基础类")
public class BaseDeleteVo {

    @ApiModelProperty(value = "所要删除对象的ID集合")
    private List<Long> objectIds;
}
