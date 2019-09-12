package com.capgemini.cn.deemo.vo.request;

import com.capgemini.cn.deemo.vo.base.BaseSearchVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class ManualSignSearchVo extends BaseSearchVo {

    @ApiModelProperty(value = "机构ID")
    private Long branchId;

    @ApiModelProperty(value = "部门ID")
    private Long departmentId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "签卡起始时间")
    private Date startTime;

    @ApiModelProperty(value = "签卡结束时间")
    private Date endTime;
}
