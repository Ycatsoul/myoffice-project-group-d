package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.service.MessageService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.request.MessageEditVo;
import com.capgemini.cn.deemo.vo.request.MessageReadVo;
import com.capgemini.cn.deemo.vo.request.MessageSearchVo;
import com.capgemini.cn.deemo.vo.response.MessageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author hasaker
 * @since 2019/9/5 22:00
 */
@Api
@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @ApiOperation("获取一条消息")
    @GetMapping("/{messageId}")
    RespBean getMessage(@PathVariable Long messageId) {
        RespVos<MessageVo> respVos = messageService.getMessage(messageId);

        if (respVos != null && respVos.getSize() == 1) {
            return RespBean.ok(respVos);
        }

        return RespBean.error("未找到相关消息!");
    }

    @ApiOperation("获取信息列表")
    @PostMapping("/list")
    RespBean listMessages(@RequestBody MessageSearchVo messageSearchVo) {
        RespVos<MessageVo> respVos = messageService.listMessages(messageSearchVo);

        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok(respVos);
        }

        return RespBean.error("查询失败!");
    }

    @ApiOperation("添加消息为草稿")
    @PostMapping("/add")
    RespBean addMessage(@RequestBody MessageEditVo messageEditVo) {
        return messageService.addMessage(messageEditVo) > 0 ? RespBean.ok("添加成功!") : RespBean.error("添加失败!");
    }

    @ApiOperation("修改草稿")
    @PutMapping("/update")
    RespBean updateMessage(@RequestBody MessageEditVo messageEditVo) {
        return messageService.updateMessage(messageEditVo) > 0 ? RespBean.ok("更新成功!") : RespBean.error("更新失败!");
    }

    @ApiOperation("删除消息")
    @PostMapping("/deleteMessages")
    RespBean deleteMessages(@RequestBody DeleteVo deleteVo) {
        Integer res = messageService.deleteMessages(deleteVo.getIds());

        return res > 0 ? RespBean.ok("成功删除" + res + "条消息!") : RespBean.error("删除失败!");
    }

    @ApiOperation("发送消息")
    @PutMapping("/publish")
    RespBean sendMessage(@RequestBody Long messageId) {
        Integer res = messageService.publishMessage(messageId);

        return res > 0 ? RespBean.ok("成功将消息发送给" + res + "个用户!") : RespBean.error("发送失败!");
    }

    @ApiOperation("阅读消息/将消息标记为已读")
    @PostMapping("/read")
    RespBean readMessages(@RequestBody MessageReadVo messageReadVo) {
        messageReadVo.setCurrentUserId(4715897054822400L);
        Integer res = messageService.readMessages(messageReadVo);

        return res > 0 ? RespBean.ok("成功阅读" + res + "条信息!") : RespBean.error("阅读失败!");
    }

    @ApiOperation("删除收件箱里的消息")
    @PostMapping("/deleteMessageTranses")
    RespBean deleteMessageTranses(@RequestBody DeleteVo deleteVo) {
        Integer res = messageService.deleteMessageTranses(deleteVo);

        return res > 0 ? RespBean.ok("成功删除" + res + "条信息!") : RespBean.error("删除失败!");
    }
}
