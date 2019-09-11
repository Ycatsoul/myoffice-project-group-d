package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.annotation.ControllerLog;
import com.capgemini.cn.deemo.service.MessageTypeService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.request.MessageTypeEditVo;
import com.capgemini.cn.deemo.vo.response.MessageTypeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author hasaker
 * @since 2019/9/5 21:41
 */
@Api
@RestController
@RequestMapping("/messageType")
public class MessageTypeController {

    private final MessageTypeService messageTypeService;

    public MessageTypeController(MessageTypeService messageTypeService) {
        this.messageTypeService = messageTypeService;
    }


    @ApiOperation("获取所有消息类型")
    @GetMapping("/")
    RespBean listMessageTypes() {
        RespVos<MessageTypeVo> respVos = messageTypeService.listMessageTypes();

        return RespBean.ok(respVos);
    }

    @ControllerLog(name = "添加消息类型")
    @ApiOperation("添加消息类型")
    @PostMapping("/")
    RespBean insertMessageType(@RequestBody MessageTypeEditVo messageTypeEditVo) {
        Integer res = messageTypeService.insertMessageType(messageTypeEditVo);

        return res > 0 ? RespBean.ok("添加成功!") : RespBean.error("添加失败!");
    }

    @ControllerLog(name = "修改消息类型")
    @ApiOperation("修改消息类型")
    @PutMapping("/")
    RespBean updateMessageType(@RequestBody MessageTypeEditVo messageTypeEditVo) {
        Integer res = messageTypeService.updateMessageType(messageTypeEditVo);

        return res > 0 ? RespBean.ok("更新成功!") : RespBean.error("更新失败!");
    }

    @ControllerLog(name = "删除消息类型")
    @ApiOperation("删除消息类型")
    @PostMapping("/delete")
    RespBean deleteMessageTypes(@RequestBody DeleteVo deleteVo) {
        Integer res = messageTypeService.deleteMessageTypes(deleteVo);

        return res > 0 ? RespBean.ok("成功删除" + res + "条信息!") : RespBean.error("删除失败!");
    }
}
