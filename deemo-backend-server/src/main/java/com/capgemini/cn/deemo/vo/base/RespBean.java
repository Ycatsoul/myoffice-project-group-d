package com.capgemini.cn.deemo.vo.base;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description:工具类用于提示json数据格式的信息，200代表成功，404资源不存在，500服务器错误
 * @Classname :RespBean
 * @author: GuoBingjun
 * @date:2019-8-22 15:41
 */
@Getter
@Setter
public class RespBean {

    private Integer status;
    private String msg;
    private Object obj;

    private RespBean() {
    }

    public static RespBean build() {
        return new RespBean();
    }

    public static RespBean ok(Object obj) {
        return new RespBean(200, obj);
    }

    public static RespBean ok(String msg, Object obj) {
        return new RespBean(200, msg, obj);
    }

    public static RespBean okMessage(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean error(String msg, Object obj) {
        return new RespBean(500, msg, obj);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }


    private RespBean(Integer status, Object obj) {
        this.status = status;
        this.obj = obj;
    }

    private RespBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }
}
