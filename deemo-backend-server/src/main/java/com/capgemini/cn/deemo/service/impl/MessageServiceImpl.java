package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.*;
import com.capgemini.cn.deemo.mapper.*;
import com.capgemini.cn.deemo.service.MessageService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.*;
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
    private final DepartmentMapper departmentMapper;


    public MessageServiceImpl(MessageMapper messageMapper,
                              MessageTransMapper messageTransMapper,
                              MessageTypeMapper messageTypeMapper,
                              UserMapper userMapper,
                              DepartmentMapper departmentMapper) {
        this.messageMapper = messageMapper;
        this.messageTransMapper = messageTransMapper;
        this.messageTypeMapper = messageTypeMapper;
        this.userMapper = userMapper;
        this.departmentMapper = departmentMapper;
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
        messageEditVo.setMessageId(IdWorker.get().nextId());

        return messageMapper.insertMessage(messageEditVo);
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
    public Integer sendMessage(Long messageId) {
        Message message = messageMapper.getMessage(messageId);
        Long branchId = message.getBranchId();
        Long departmentId = message.getDepartmentId();
        Long recipientId = message.getRecipientId();
        List<MessageTrans> messageTranses = new ArrayList<>();
        MessageTrans messageTrans;

        messageMapper.publishMessage(messageId);

        if (recipientId != null) {
            // 如果选择了用户, 发送给选中的用户
            messageTrans = new MessageTrans();
            messageTrans.setMessageTransId(IdWorker.get().nextId());
            messageTrans.setMessageId(messageId);
            messageTrans.setRecipientId(recipientId);

            messageTranses.add(messageTrans);
        } else if (departmentId != null) {
            // 如果没有选中用户, 选中了部门, 发送给该部门下所有用户
            List<User> users = userMapper.listUsers(new UserSearchVo(){{
                setStart(0);
                setSize(1000);
                setDepartmentId(departmentId);
            }});

            for (User user : users) {
                messageTrans = new MessageTrans();
                messageTrans.setMessageTransId(IdWorker.get().nextId());
                messageTrans.setMessageId(messageId);
                messageTrans.setRecipientId(user.getUserId());

                messageTranses.add(messageTrans);
            }
        } else if (branchId != null) {
            // 如果没有选中部门和用户, 选中了机构, 发送给该机构下的所有用户
            List<Department> departments = departmentMapper.listDepartments(new DepartmentSearchVo(){{
                setStart(0);
                setSize(100);
                setBranchId(branchId);
            }});
            for (Department department : departments) {
                List<User> users = userMapper.listUsers(new UserSearchVo(){{
                    setStart(0);
                    setSize(1000);
                    setDepartmentId(department.getDepartmentId());
                }});

                for (User user : users) {
                    messageTrans = new MessageTrans();
                    messageTrans.setMessageTransId(IdWorker.get().nextId());
                    messageTrans.setMessageId(messageId);
                    messageTrans.setRecipientId(user.getUserId());

                    messageTranses.add(messageTrans);
                }
            }
        } else {
            // 如果都没选中, 发送给所有用户
            List<User> users = userMapper.listUsers(new UserSearchVo(){{
                setStart(0);
                setSize(1000);
            }});

            for (User user : users) {
                messageTrans = new MessageTrans();
                messageTrans.setMessageTransId(IdWorker.get().nextId());
                messageTrans.setMessageId(messageId);
                messageTrans.setRecipientId(user.getUserId());

                messageTranses.add(messageTrans);
            }
        }


        return messageTranses.size() > 0 ? messageTransMapper.sendMessages(messageTranses) : 0;
    }

    @Override
    public Integer readMessages(MessageReadVo messageReadVo) {
        return messageTransMapper.readMessages(messageReadVo);
    }

    @Override
    public Integer deleteMessageTranses(List<Long> messageTransIds) {
        return messageTransMapper.deleteMessageTranses(messageTransIds);
    }

    @Override
    public Integer deleteMessageTransesByMessageId(List<Long> messageIds) {
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
        messageVo.setBranchId(message.getBranchId());
        messageVo.setDepartmentId(message.getDepartmentId());
        messageVo.setRecipientId(message.getRecipientId());
        messageVo.setIsPublished(message.getIsPublished());
        messageVo.setPublishTime(message.getPublishTime());

        return messageVo;
    }
}
