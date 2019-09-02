package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.Message;
import com.capgemini.cn.deemo.data.domain.MessageTrans;
import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.mapper.MessageMapper;
import com.capgemini.cn.deemo.service.MessageService;
import com.capgemini.cn.deemo.service.UserService;
import com.capgemini.cn.deemo.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bowen Du
 * @since 2019-08-22 17:53
 */

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    UserService userService;

    //添加消息
    @Override
    public boolean sendMsg(Message message) {
//        int result = messageMapper.sendMsg(message);
//        List<User> users = userService.queryList();
//        int result2 = messageMapper.addMsgToAllUser(users, message.getMessageId());
        return true;
    }

    /**
     * 发送消息到所有人
     * @param users
     * @param messageId
     * @return
     */
    @Override
    public int addMsgToAllUser(List<User> users, Long messageId){
        return 0;
    }

    //获取消息列表
    @Override
    public List<MessageTrans> getMsgByPage(Integer page, Integer size, Long userId) {
        int start = (page - 1) * size;
        return messageMapper.getMsg(start,size,UserUtils.getCurrentUser().getUserId());
    }

    //消息发布状态
    @Override
    public boolean markPublished(Long isPublished){
        if (isPublished != -1) {
            return messageMapper.markPublished(isPublished)==1;
        }
        messageMapper.markPublished(isPublished);
        return true;
    }

    //消息阅读状态
    @Override
    public boolean markRead(Long isRead) {
        if (isRead != -1) {
            return messageMapper.markRead(isRead,UserUtils.getCurrentUser().getUserId())==1;
        }
        messageMapper.markRead(isRead,UserUtils.getCurrentUser().getUserId());
        return true;
    }

    //更新消息
    @Override
    public int updateMsg(Long messageId) {
        return 1;
    }


    //删除消息
    @Override
    public int deleteMsg(Long messageId){
        return 1;
    }
}
