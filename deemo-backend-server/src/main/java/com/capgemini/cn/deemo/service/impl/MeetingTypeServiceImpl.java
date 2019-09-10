package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.MeetingType;
import com.capgemini.cn.deemo.mapper.MeetingTypeMapper;
import com.capgemini.cn.deemo.mapper.OperationLogMapper;
import com.capgemini.cn.deemo.service.MeetingTypeService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.utils.OperationLogUtils;
import com.capgemini.cn.deemo.vo.request.MeetingTypeEditVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/23 14:24
 */
@Service
public class MeetingTypeServiceImpl implements MeetingTypeService {

    private final MeetingTypeMapper meetingTypeMapper;
    private final OperationLogMapper operationLogMapper;

    public MeetingTypeServiceImpl(MeetingTypeMapper meetingTypeMapper,
                                  OperationLogMapper operationLogMapper) {
        this.meetingTypeMapper = meetingTypeMapper;
        this.operationLogMapper = operationLogMapper;
    }

    /**
     * 获取会议类型信息
     * @return
     */
    @Override
    public List<MeetingType> listMeetingTypes() {
        return meetingTypeMapper.listMeetingTypes();
    }

    /**
     * 根据meetingType获取会议类型信息
     * @param meetingTypeId
     * @return
     */
    @Override
    public MeetingType listMeetingType(Long meetingTypeId){

        return meetingTypeMapper.listMeetingType(meetingTypeId);
    }

    /**
     * 增加
     */
    @Override
    public Integer insertMeetingType(MeetingTypeEditVo meetingTypeEditVo) {
        meetingTypeEditVo.setMeetingTypeId(IdWorker.get().nextId());

        operationLogMapper.insertOperationLog(
                OperationLogUtils.createOperationLog(
                        "添加会议类型 - " + meetingTypeEditVo.getMeetingTypeName())
        );

        return meetingTypeMapper.insertMeetingType(meetingTypeEditVo);
    }

    /**
     * 更新
     */
    @Override
    public Integer updateMeetingType(MeetingTypeEditVo meetingTypeEditVo) {
        operationLogMapper.insertOperationLog(
                OperationLogUtils.createOperationLog(
                        "修改会议类型 - " + meetingTypeEditVo.getMeetingTypeName())
        );

        return meetingTypeMapper.updateMeetingType(meetingTypeEditVo);
    }


    /**
     * 删除
     */
    @Override
    public Integer deleteMeetingType(Long meetingTypeId) {
        operationLogMapper.insertOperationLog(
                OperationLogUtils.createOperationLog(
                        "删除会议类型 - " + meetingTypeMapper.listMeetingType(meetingTypeId))
        );

        return meetingTypeMapper.deleteMeetingType(meetingTypeId);
    }
}
