package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.MessageType;
import com.capgemini.cn.deemo.vo.request.MessageTypeEditVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/5 10:08
 */
@Service
public interface MessageTypeMapper {

    List<MessageType> listMessageTypes();

    MessageType getMessageType(@Param("messageTypeId") Long messageTypeId);

    Integer insertMessageType(@Param("messageTypeEditVo") MessageTypeEditVo messageTypeEditVo);

    Integer updateMessageType(@Param("messageTypeEditVo") MessageTypeEditVo messageTypeEditVo);

    Integer deleteMessageTypes(@Param("messageTypeIds") List<Long> messageTypeIds);
}
