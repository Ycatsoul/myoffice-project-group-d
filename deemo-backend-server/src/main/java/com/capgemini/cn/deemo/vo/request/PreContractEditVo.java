package com.capgemini.cn.deemo.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/24 15:01
 */
@Data
@ApiModel(value = "PreContract更新请求Vo")
public class PreContractEditVo {

    @ApiModelProperty(value = "预约序号")
    private Long preContractId;

    @ApiModelProperty(value = "日程ID")
    private Long scheduleId;

    @ApiModelProperty(value = "预约人")
    private Long userId;
}
