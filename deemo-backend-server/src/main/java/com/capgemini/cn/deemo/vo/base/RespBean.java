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
    private Object object;

    private RespBean(Integer status, Object object) {
        this.status = status;
        this.object = object;
    }

    private RespBean(Integer status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public static RespBean success(Object object) {
        return new RespBean(200, object);
    }

    public static RespBean success(String message) {
        return new RespBean(200, message, null);
    }

    public static RespBean success(String message, Object object) {
        return new RespBean(200, message, object);
    }

    public static RespBean error(String message) {
        return new RespBean(500, message, null);
    }

    public static RespBean error(String message, Object object) {
        return new RespBean(500, message , object);
    }
}
