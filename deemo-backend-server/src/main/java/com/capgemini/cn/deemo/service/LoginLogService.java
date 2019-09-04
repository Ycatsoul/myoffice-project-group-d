package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.LoginLog;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.LoginLogSearchVo;
import com.capgemini.cn.deemo.vo.response.LoginLogVo;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/4 22:12
 */
public interface LoginLogService {

    RespVos<LoginLogVo> listLoginLogs(LoginLogSearchVo loginLogSearchVo);

    Integer insertLoginLog(LoginLog loginLog);

    Integer deleteLoginLogs(List<Long> loginIds);
}
