package com.capgemini.cn.deemo.vo.request;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */

@Data
public class ManualSiginSearchVo {


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

    private int page;
    //一页几条数据
    private int limit;
}
