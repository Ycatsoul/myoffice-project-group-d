package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.MeetingType;
import com.capgemini.cn.deemo.vo.request.MeetingTypeEditVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/23 14:14
 */
@Service
public interface MeetingTypeMapper {

    MeetingType listMeetingType(@Param("meetingTypeId") Long meetingTypeId);

    List<MeetingType> listMeetingTypes();

    Integer insertMeetingType(@Param("meetingTypeEditVo") MeetingTypeEditVo meetingTypeEditVo);

    Integer updateMeetingType(@Param("meetingTypeEditVo") MeetingTypeEditVo meetingTypeEditVo);

    Integer deleteMeetingType(@Param("meetingTypeId") Long meetingTypeId);
}
