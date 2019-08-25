package com.capgemini.cn.deemo.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author hasaker
 * @since 2019-08-23 10:06
 */
@ApiModel
@Getter
@Setter
public class FileInfoDeleteVo {

    @ApiModelProperty(value = "所要删除文件的ID集合")
    private List<Long> fileIds;
}
