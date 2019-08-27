package com.capgemini.cn.deemo.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.print.attribute.standard.Media;

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
    private String message;

    @ApiModelProperty(value = "数据")
    private Object data;

    private RespBean(Integer status, Object data) {
        this.status = status;
        this.data = data;
    }

    private RespBean(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static RespBean ok(Object data) {
        return new RespBean(200, data);
    }

    public static RespBean ok(String message) {
        return new RespBean(200, message, null);
    }

    public static RespBean ok(String message, Object data) {
        return new RespBean(200, message, data);
    }

    public static RespBean error(String message) {
        return new RespBean(500, message, null);
    }

    public static RespBean error(String message, Object data) {
        return new RespBean(500, message , data);
    }
}
