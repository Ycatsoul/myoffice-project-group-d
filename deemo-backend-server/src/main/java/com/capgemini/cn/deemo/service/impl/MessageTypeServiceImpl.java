package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.MessageType;
import com.capgemini.cn.deemo.mapper.MessageTypeMapper;
import com.capgemini.cn.deemo.service.MessageTypeService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.MessageTypeEditVo;
import com.capgemini.cn.deemo.vo.response.MessageTypeVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author hasaker
 * @since 2019/9/5 21:18
 */
@Service
public class MessageTypeServiceImpl implements MessageTypeService {

    private final MessageTypeMapper messageTypeMapper;

    public MessageTypeServiceImpl(MessageTypeMapper messageTypeMapper) {
        this.messageTypeMapper = messageTypeMapper;
    }

    @Override
    public RespVos<MessageTypeVo> listMessageTypes() {
        List<MessageType> messageTypes = messageTypeMapper.listMessageTypes();

        if (Objects.nonNull(messageTypes) && messageTypes.size() > 0) {
            RespVos<MessageTypeVo> respVos = new RespVos<>();
            respVos.setSize(messageTypes.size());
            respVos.setVos(messageTypes.stream().map(this::convertToVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    @Override
    public Integer insertMessageType(MessageTypeEditVo messageTypeEditVo) {
        messageTypeEditVo.setMessageTypeId(IdWorker.get().nextId());

        return messageTypeMapper.insertMessageType(messageTypeEditVo);
    }

    @Override
    public Integer updateMessageType(MessageTypeEditVo messageTypeEditVo) {
        return messageTypeMapper.updateMessageType(messageTypeEditVo);
    }

    @Override
    public Integer deleteMessageTypes(List<Long> messageTypeIds) {
        return messageTypeMapper.deleteMessageTypes(messageTypeIds);
    }

    private MessageTypeVo convertToVo(MessageType messageType) {
        MessageTypeVo messageTypeVo = new MessageTypeVo();
        messageTypeVo.setMessageTypeId(messageType.getMessageTypeId());
        messageTypeVo.setMessageTypeName(messageType.getMessageTypeName());
        messageTypeVo.setMessageTypeDesc(messageType.getMessageTypeDesc());

        return messageTypeVo;
    }
}
