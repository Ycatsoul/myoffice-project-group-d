package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.data.domain.Message;
import com.capgemini.cn.deemo.vo.base.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Bowen Du
 * @since 2019-08-23 12:25
 */

@Slf4j
@RestController
@Api(value = "消息传递接口")
public class MessageController {
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @ApiOperation(value = "查询消息", notes = "查询")
    @PostMapping("/ws/msg")
    //   public void handleChat(Principal principal, String msg) {
//        String destUser = msg.substring(msg.lastIndexOf(";") + 1, msg.length());
//        String message = msg.substring(0, msg.lastIndexOf(";"));
//        messagingTemplate.convertAndSendToUser(destUser, "/queue/chat", new Message(message, principal.getName()));
        public RespBean getMessages() {
            return RespBean.ok(new Message());
        }

    @ApiOperation(value = "系统消息")
    @PostMapping("/ws/nf")
    @SendTo("/topic/nf")
    public String handleNF() {
        return "系统消息";
    }
    }
