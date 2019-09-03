package com.capgemini.cn.deemo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hasaker
 * @since 2019/9/2 18:55
 */
@Data
public class ManualSignEditVo {

    @ApiModelProperty(value = "签卡ID")
    private Long manualSignId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "签卡备注")
    private String signDesc;

    @ApiModelProperty(value = "签卡标记")
    private Integer signTag;
}
