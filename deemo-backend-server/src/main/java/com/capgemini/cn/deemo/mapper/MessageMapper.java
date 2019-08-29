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

    Integer sendMsg(Message message);

    Integer addMsgToAllUser(@Param("users") List<User> users, @Param("messageId") Long messageId);

    List<MessageTrans> getMsg(@Param("start") int start, @Param("size") Integer size,@Param("recipientId") Long recipientId);

    Integer markPublished(@Param("messageId") Long isPublished);

    Integer markRead(@Param("isRead") Long isRead, @Param("recipientId") Long recipientId);

    Integer updateMsg(@Param("messageId") Long messageId);

    Integer deleteMsg(@Param("messageId") Long messageId);
}
