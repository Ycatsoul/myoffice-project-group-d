package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.Schedule;
import com.capgemini.cn.deemo.mapper.PreContractMapper;
import com.capgemini.cn.deemo.mapper.ScheduleMapper;
import com.capgemini.cn.deemo.service.ScheduleService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.utils.UserUtils;
import com.capgemini.cn.deemo.vo.request.PreContractEditVo;
import com.capgemini.cn.deemo.vo.request.ScheduleEditVo;
import com.capgemini.cn.deemo.vo.request.ScheduleListVo;
import com.capgemini.cn.deemo.vo.request.ScheduleSearchVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/23 14:25
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleMapper scheduleMapper;

    private final PreContractMapper preContractMapper;

    public ScheduleServiceImpl(ScheduleMapper scheduleMapper, PreContractMapper preContractMapper) {
        this.scheduleMapper = scheduleMapper;
        this.preContractMapper = preContractMapper;
    }

    /**
     * 获取日程信息
     */
    @Override
    public List<Schedule> listSchedule(ScheduleListVo scheduleListVo) {
        return scheduleMapper.listSchedule(scheduleListVo);
    }

    /**
     * 根据beginTime,createUserId获取日程信息
     */
    @Override
    public List<Schedule> listSchedules(ScheduleSearchVo scheduleSearchVo){
        return scheduleMapper.listSchedules(scheduleSearchVo);
    }

    /**
     * 更新
     */
    @Override
    public Integer updateSchedule(ScheduleEditVo scheduleEditVo){

        return scheduleMapper.updateSchedule(scheduleEditVo);
    }

    /**
     * 新增
     */
    @Override
    public boolean insertSchedule(ScheduleEditVo scheduleEditVo) {
        PreContractEditVo preContractEditVo = null;

        scheduleEditVo.setScheduleId(IdWorker.get().nextId());
        scheduleEditVo.setCreateUserId(UserUtils.getCurrentUser().getUserId());
        int a = scheduleMapper.insertSchedule(scheduleEditVo);

        for (Long userId : scheduleEditVo.getUserIds()) {
            preContractEditVo = new PreContractEditVo();
            preContractEditVo.setPreContractId(IdWorker.get().nextId());
            preContractEditVo.setScheduleId(scheduleEditVo.getScheduleId());
            preContractEditVo.setUserId(userId);
            
            preContractMapper.insertPreContract(preContractEditVo);
        }

        return a > 0;

    }

    /**
     * 删除
     */
    @Override
    public boolean deleteSchedule(Long scheduleId) {
        return scheduleMapper.deleteSchedule(scheduleId) > 0;
    }
}
