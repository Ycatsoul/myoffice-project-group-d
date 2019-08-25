package com.capgemini.cn.deemo.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author hasaker
 * @since 2019-08-22 17:48
 */
@Setter
@Getter
@ApiModel
public class FileInfoDestroyVo {

    @ApiModelProperty(value = "所要删除对象的ID集合")
    private List<Long> fileIds;
}
