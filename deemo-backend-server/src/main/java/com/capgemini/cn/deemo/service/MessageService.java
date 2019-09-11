package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.Message;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.request.MessageEditVo;
import com.capgemini.cn.deemo.vo.request.MessageReadVo;
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

    RespVos<MessageVo> getMessagesByRecipientId(Long recipientId);

    Integer addMessage(MessageEditVo messageEditVo);

    Integer updateMessage(MessageEditVo messageEditVo);

    Integer deleteMessages(List<Long> messageIds);

    Integer publishMessage(Long messageId);

    Integer readMessages(MessageReadVo messageReadVo);

    Integer deleteMessageTranses(DeleteVo deleteVo);

    Integer deleteMessageTransesByMessageId(List<Long> messageIds);

    MessageVo convertToVo(Message message);
}
