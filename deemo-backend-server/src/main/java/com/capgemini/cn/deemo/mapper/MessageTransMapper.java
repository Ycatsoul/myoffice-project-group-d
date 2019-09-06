package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.MessageTrans;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/5 10:08
 */
@Service
public interface MessageTransMapper {

    Integer sendMessages(@Param("messageTranses") List<MessageTrans> messageTranses);

    Integer readMessages(@Param("messageIds") List<Long> messageIds, @Param("currentUserId") Long currentUserId);

    Integer deleteMessageTranses(@Param("messageTransIds") List<Long> messageTransIds);

    Integer deleteMessageTransesByMessageId(@Param("messageIds") List<Long> messageIds);
}
