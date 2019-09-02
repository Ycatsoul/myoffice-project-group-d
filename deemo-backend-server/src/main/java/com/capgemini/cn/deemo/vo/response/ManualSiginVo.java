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
public class ManualSiginVo {

    /**
     * 签卡id
     */
    private Long manualSiginId ;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 签卡时间
     */
    private Date singInTime;

    /**
     * 签卡备注
     */
    private String singInDesc;

    /**
     * 签卡标记
     */
    private Integer singInTag;
}
