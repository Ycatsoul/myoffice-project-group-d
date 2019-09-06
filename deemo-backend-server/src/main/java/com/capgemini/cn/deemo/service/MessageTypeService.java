package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.MessageTypeEditVo;
import com.capgemini.cn.deemo.vo.response.MessageTypeVo;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/5 15:59
 */
public interface MessageTypeService {

    RespVos<MessageTypeVo> listMessageTypes();

    Integer insertMessageType(MessageTypeEditVo messageTypeEditVo);

    Integer updateMessageType(MessageTypeEditVo messageTypeEditVo);

    Integer deleteMessageTypes(List<Long> messageTypeIds);
}
