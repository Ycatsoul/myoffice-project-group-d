package com.capgemini.cn.deemo.vo.response;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Data
public class ManualSignVo {

    /**
     * 签卡ID
     */
    private Long manualSignId ;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名字
     */
    private String name;

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
