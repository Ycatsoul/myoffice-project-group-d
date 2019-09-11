package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.annotation.ControllerLog;
import com.capgemini.cn.deemo.data.domain.MeetingType;
import com.capgemini.cn.deemo.service.MeetingTypeService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.request.MeetingTypeEditVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/23 10:22
 */
@Slf4j
@Api(value = "会议类型")
@RestController
@RequestMapping("/meetingType")
public class MeetingTypeController {
    private final MeetingTypeService meetingTypeService;

    public MeetingTypeController(MeetingTypeService meetingTypeService) {
        this.meetingTypeService = meetingTypeService;
    }

    @ApiOperation(value = "根据meetingTypeId获取MeetingType详尽信息")
    @GetMapping("/{meetingTypeId}")
    public RespBean getMeetingType(@PathVariable Long meetingTypeId) {

        MeetingType meetingType = meetingTypeService.listMeetingType(meetingTypeId);

        // 判断是否获取成功
        if(meetingType != null){
            return RespBean.ok("获取成功",meetingType);
        }else{
            return RespBean.error("获取失败");
        }
    }

    @ApiOperation(value = "获取MeetingType列表")
    @GetMapping("/get")
    public RespBean getMeetingTypeList() {
        List<MeetingType> res = meetingTypeService.listMeetingTypes();

        if(res != null){
            return RespBean.ok("获取成功",res);
        }else{
            return RespBean.error("获取失败");
        }
    }

    @ControllerLog(name = "添加会议类型")
    @ApiOperation(value = "新建一条MeetingType")
    @PostMapping("/set")
    public RespBean insertMeetingType(@RequestBody MeetingTypeEditVo meetingTypeEditVo) {

        Integer res = meetingTypeService.insertMeetingType(meetingTypeEditVo);

        // 判断是否新增成功
        if(res!=null){
            return RespBean.ok("新增成功");
        }else{
            return RespBean.error("新增失败");
        }
    }

    @ApiOperation(value = "根据meetingTypeId更新一条meetingType")
    @PutMapping("/update")
    public RespBean updateMeetingType(@RequestBody MeetingTypeEditVo meetingType) {

        Integer res = meetingTypeService.updateMeetingType(meetingType);

        if(res !=null){
            return RespBean.ok("更新成功",res);
        }else {
            return RespBean.error("更新失败");
        }
    }

    @ApiOperation(value = "更具MeetingTypeId删除一条meetingType")
    @DeleteMapping("/{meetingTypeId}")
    public RespBean deleteMeetingType(@PathVariable Long meetingTypeId) {

        Integer res = meetingTypeService.deleteMeetingType(meetingTypeId);

        // 判断是否删除成功
        if(res > 0){
            return RespBean.ok("删除成功");
        }else{
            return RespBean.error("删除失败");
        }
    }
}
