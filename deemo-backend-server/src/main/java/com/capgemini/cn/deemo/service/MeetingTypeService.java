package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.MeetingType;
import com.capgemini.cn.deemo.vo.request.MeetingTypeEditVo;

import java.util.List;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/23 20:08
 */
public interface MeetingTypeService {

    public List<MeetingType> listMeetingTypes();

    public MeetingType listMeetingType(Long meetingTypeId);

    public Integer updateMeetingType(MeetingTypeEditVo meetingTypeEditVo);

    public Integer insertMeetingType(MeetingTypeEditVo meetingTypeEditVo);

    public Integer deleteMeetingType(Long meetingTypeId);
}
