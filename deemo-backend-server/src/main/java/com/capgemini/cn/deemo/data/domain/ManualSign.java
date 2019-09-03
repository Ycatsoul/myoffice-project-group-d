package com.capgemini.cn.deemo.data.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Description:考勤信息实体类表
 * @Classname :ManualSigin
 * @author: GuoBingjun
 * @date:2019-8-26 22:54
 */

@Data
public class ManualSign {
    /**
     * 签卡id
     */
    private Long manualSignId ;

    /**
     * 用户id
     */
    private Long userId;
    
    /**
     * 签卡时间
     */
    private Date signTime;
    
    /**
     * 签卡备注
     */
    private String signDesc;
    
    /**
     * 签卡标记
     */
    private Integer signTag;
}
