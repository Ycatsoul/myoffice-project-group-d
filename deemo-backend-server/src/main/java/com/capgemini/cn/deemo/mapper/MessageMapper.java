package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.Message;
import com.capgemini.cn.deemo.data.domain.MessageTrans;
import com.capgemini.cn.deemo.data.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Bowen Du
 * @since 2019-08-26 11:04
 */

public interface MessageMapper {

    int sendMsg(Message message);

    int addMsgToAllUser(@Param("users") List<User> users, @Param("messageId") Long messageId);

    List<MessageTrans> getMsg(@Param("recipientId") Long recipientId);

    int markPublished(@Param("messageId") Long messageId);

    int markRead(@Param("isRead") Long isRead, @Param("recipientId") Long recipientId);

    int updateMsg(@Param("messageId") Long messageId);

    int deleteMsg(@Param("messageId") Long messageId);
}
