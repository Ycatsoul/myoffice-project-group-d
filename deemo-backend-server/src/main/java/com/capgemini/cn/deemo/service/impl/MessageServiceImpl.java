package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.Message;
import com.capgemini.cn.deemo.data.domain.MessageTrans;
import com.capgemini.cn.deemo.data.domain.MessageType;
import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.mapper.MessageMapper;
import com.capgemini.cn.deemo.mapper.MessageTransMapper;
import com.capgemini.cn.deemo.mapper.MessageTypeMapper;
import com.capgemini.cn.deemo.mapper.UserMapper;
import com.capgemini.cn.deemo.service.MessageService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.request.MessageEditVo;
import com.capgemini.cn.deemo.vo.request.MessageReadVo;
import com.capgemini.cn.deemo.vo.request.MessageSearchVo;
import com.capgemini.cn.deemo.vo.response.MessageVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author hasaker
 * @since 2019/9/5 16:11
 */
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageMapper messageMapper;
    private final MessageTransMapper messageTransMapper;
    private final MessageTypeMapper messageTypeMapper;
    private final UserMapper userMapper;


    public MessageServiceImpl(MessageMapper messageMapper,
                              MessageTransMapper messageTransMapper,
                              MessageTypeMapper messageTypeMapper,
                              UserMapper userMapper) {
        this.messageMapper = messageMapper;
        this.messageTransMapper = messageTransMapper;
        this.messageTypeMapper = messageTypeMapper;
        this.userMapper = userMapper;
    }

    @Override
    public RespVos<MessageVo> listMessages(MessageSearchVo messageSearchVo) {
        List<Message> messages = messageMapper.listMessages(messageSearchVo);

        if (Objects.nonNull(messages) && messages.size() > 0) {
            RespVos<MessageVo> respVos = new RespVos<>();
            respVos.setSize(messageMapper.countMessages(messageSearchVo));
            respVos.setVos(messages.stream().map(this::convertToVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    @Override
    public RespVos<MessageVo> getMessage(Long messageId) {
        Message message = messageMapper.getMessage(messageId);

        if (Objects.nonNull(message)) {
            return new RespVos<MessageVo>(){{
                setSize(1);
                setVos(new ArrayList<MessageVo>(){{
                    add(convertToVo(message));
                }});
            }};
        }

        return null;
    }

    @Override
    public Integer addMessage(MessageEditVo messageEditVo) {
        Long messageId = IdWorker.get().nextId();
        List<MessageTrans> messageTranses = new ArrayList<>();

        messageEditVo.setMessageId(messageId);
        for (Long recipientId : messageEditVo.getRecipientIds()) {
            messageTranses.add(new MessageTrans(){{
                setMessageTransId(IdWorker.get().nextId());
                setMessageId(messageId);
                setRecipientId(recipientId);
            }});
        }

        Integer res1 = messageMapper.insertMessage(messageEditVo);
        Integer res2 = messageTransMapper.insertMessageTranses(messageTranses);

        return res1 + res2;
    }

    @Override
    public Integer updateMessage(MessageEditVo messageEditVo) {
        return messageMapper.updateMessage(messageEditVo);
    }

    @Override
    public Integer deleteMessages(List<Long> messageIds) {
        messageTransMapper.deleteMessageTransesByMessageId(messageIds);

        return messageMapper.deleteMessages(messageIds);
    }

    @Override
    public Integer publishMessage(Long messageId) {
        return messageMapper.publishMessage(messageId);
    }

    @Override
    public Integer readMessages(MessageReadVo messageReadVo) {
        return messageTransMapper.readMessages(messageReadVo);
    }

    @Override
    public Integer deleteMessageTranses(DeleteVo deleteVo) {
        return messageTransMapper.deleteMessageTranses(deleteVo.getIds());
    }

    private Integer deleteMessageTransesByMessageId(List<Long> messageIds) {
        return messageTransMapper.deleteMessageTransesByMessageId(messageIds);
    }

    private MessageVo convertToVo(Message message) {
        MessageVo messageVo = new MessageVo();

        User createUser = userMapper.getUser(message.getCreateUserId());
        MessageType messageType = messageTypeMapper.getMessageType(message.getMessageTypeId());

        messageVo.setMessageId(message.getMessageId());
        messageVo.setMessageTypeId(message.getMessageTypeId());
        messageVo.setMessageTypeName(messageType == null ? null : messageType.getMessageTypeName());
        messageVo.setMessageTitle(message.getMessageTitle());
        messageVo.setMessageContent(message.getMessageContent());
        messageVo.setBeginTime(message.getBeginTime());
        messageVo.setEndTime(message.getEndTime());
        messageVo.setCreateUserId(message.getCreateUserId());
        messageVo.setCreateUserName(createUser == null ? null : createUser.getName());
        messageVo.setIsPublished(message.getIsPublished());
        messageVo.setPublishTime(message.getPublishTime());

        return messageVo;
    }
}
