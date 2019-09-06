package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.Message;
import com.capgemini.cn.deemo.vo.request.MessageEditVo;
import com.capgemini.cn.deemo.vo.request.MessageSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bowen Du
 * @since 2019-08-26 11:04
 */
@Service
public interface MessageMapper {

     List<Message> listMessages(@Param("messageSearchVo") MessageSearchVo messageSearchVo);

     Integer countMessages(@Param("messageSearchVo") MessageSearchVo messageSearchVo);

     Message getMessage(@Param("messageId") Long messageId);

     Integer insertMessage(@Param("messageEditVo") MessageEditVo messageEditVo);

     Integer updateMessage(@Param("messageEditVo") MessageEditVo messageEditVo);

     Integer publishMessage(@Param("messageId") Long messageId);

     Integer deleteMessages(@Param("messageIds") List<Long> messageIds);
}
