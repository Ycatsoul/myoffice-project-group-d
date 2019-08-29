package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.Message;
import com.capgemini.cn.deemo.data.domain.MessageTrans;
import com.capgemini.cn.deemo.data.domain.User;

import java.util.List;

/**
 * @author Bowen Du
 * @since 2019-08-22 17:53
 */

public interface MessageService {
    public boolean sendMsg(Message message);

    public int addMsgToAllUser(List<User> users, Long messageId);

    public List<MessageTrans> getMsgByPage(Integer page, Integer size, Long userId);

    public boolean markPublished(Long isPublished);

    public boolean markRead(Long isRead);

    public int updateMsg(Long messageId);

    public int deleteMsg(Long messageId);
}
