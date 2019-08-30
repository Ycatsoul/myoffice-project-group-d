package com.capgemini.cn.deemo.data.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @Description:考勤信息实体类表
 * @Classname :ManualSigin
 * @author: GuoBingjun
 * @date:2019-8-26 22:54
 */

@Data
@Slf4j
public class ManualSigin {
    //id
    private Integer id;
    //签卡id
    private Long manualSiginId ;
    //用户id
    private Long userId;
    //签卡时间
    private Date singInTime;
    //签卡备注
    private String singInDesc;
    //签卡标记
    private Integer singInTag;
}
