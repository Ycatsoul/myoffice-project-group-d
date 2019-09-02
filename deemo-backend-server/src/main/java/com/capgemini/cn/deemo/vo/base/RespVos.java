package com.capgemini.cn.deemo.vo.base;

import lombok.Data;

import java.util.List;

/**
 * @author hasaker
 * @since 2019-08-25 23:44
 */
@Data
public class RespVos<T> {
    /**
     * 返回数据长度
     */
    private Integer size;

    /**
     * 返回数据数组
     */
    private List<T> vos;
}
