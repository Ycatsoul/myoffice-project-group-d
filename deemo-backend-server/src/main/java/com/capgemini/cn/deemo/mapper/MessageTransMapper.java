package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.Message;
import com.capgemini.cn.deemo.data.domain.MessageTrans;
import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.vo.request.MessageReadVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/5 10:08
 */
@Service
public interface MessageTransMapper {

    Integer insertMessageTranses(@Param("messageTranses") List<MessageTrans> messageTranses);

    List<User> getRecipientsByMessageId(@Param("messageId") Long messageId);

    List<Message> getMessagesByRecipientId(@Param("recipientId") Long recipientId);

    Integer readMessages(@Param("messageReadVo") MessageReadVo messageReadVo);

    Integer deleteMessageTranses(@Param("messageTransIds") List<Long> messageTransIds);

    Integer deleteMessageTransesByMessageId(@Param("messageIds") List<Long> messageIds);
}
