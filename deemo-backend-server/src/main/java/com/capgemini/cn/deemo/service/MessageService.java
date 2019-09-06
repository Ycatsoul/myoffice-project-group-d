package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.MessageEditVo;
import com.capgemini.cn.deemo.vo.request.MessageSearchVo;
import com.capgemini.cn.deemo.vo.response.MessageVo;

import java.util.List;

/**
 * @author Bowen Du
 * @since 2019-08-22 17:53
 */

public interface MessageService {

    RespVos<MessageVo> listMessages(MessageSearchVo messageSearchVo);

    RespVos<MessageVo> getMessage(Long messageId);

    Integer addMessage(MessageEditVo messageEditVo);

    Integer updateMessage(MessageEditVo messageEditVo);

    Integer deleteMessages(List<Long> messageIds);

    Integer sendMessage(Long messageId);

    Integer readMessages(List<Long> messageIds, Long currentUserId);

    Integer deleteMessageTranses(List<Long> messageTransIds);

    Integer deleteMessageTransesByMessageId(List<Long> messageIds);
}
