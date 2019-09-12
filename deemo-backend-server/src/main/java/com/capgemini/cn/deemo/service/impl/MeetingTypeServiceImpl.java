package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.MeetingType;
import com.capgemini.cn.deemo.mapper.MeetingTypeMapper;
import com.capgemini.cn.deemo.service.MeetingTypeService;
import com.capgemini.cn.deemo.utils.IdWorker;
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

    public MeetingTypeServiceImpl(MeetingTypeMapper meetingTypeMapper) {
        this.meetingTypeMapper = meetingTypeMapper;
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
     * 更新
     */
    @Override
    public Integer updateMeetingType(MeetingTypeEditVo meetingTypeEditVo){
        return meetingTypeMapper.updateMeetingType(meetingTypeEditVo);
    }

    /**
     * 增加
     */
    @Override
    public Integer insertMeetingType(MeetingTypeEditVo meetingTypeEditVo) {
        meetingTypeEditVo.setMeetingTypeId(IdWorker.get().nextId());

        return meetingTypeMapper.insertMeetingType(meetingTypeEditVo);
    }

    /**
     * 删除
     */
    @Override
    public Integer deleteMeetingType(Long meetingTypeId) {
        return meetingTypeMapper.deleteMeetingType(meetingTypeId);
    }
}
