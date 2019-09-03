package com.capgemini.cn.deemo.vo.request;

import com.capgemini.cn.deemo.vo.base.BaseSearchVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class BranchSearchVo extends BaseSearchVo {

    @ApiModelProperty(value = "机构名称")
    private String branchName;
}
