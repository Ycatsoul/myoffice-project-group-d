package com.capgemini.cn.deemo.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hasaker
 * @since 2019-08-22 15:07
 */
@Getter
@Setter
@ApiModel(description = "Response类")
public class RespBean {

    @ApiModelProperty(value = "状态码")
    private Integer status;

    @ApiModelProperty(value = "消息")
    private String msg;

    @ApiModelProperty(value = "数据")
    private Object obj;

    private RespBean(Integer status, Object obj) {
        this.status = status;
        this.obj = obj;
    }

    private RespBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public static RespBean ok(Object obj) {
        return new RespBean(200, obj);
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean ok(String msg, Object obj) {
        return new RespBean(200, msg, obj);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    public static RespBean error(String msg, Object obj) {
        return new RespBean(500, msg, obj);
    }
}
