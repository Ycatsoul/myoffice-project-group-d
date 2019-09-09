package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.LoginLog;
import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.mapper.LoginLogMapper;
import com.capgemini.cn.deemo.mapper.UserMapper;
import com.capgemini.cn.deemo.service.LoginLogService;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.request.LoginLogSearchVo;
import com.capgemini.cn.deemo.vo.response.LoginLogVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hasaker
 * @since 2019/9/4 22:16
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {
    private final LoginLogMapper loginLogMapper;
    private final UserMapper userMapper;

    public LoginLogServiceImpl(LoginLogMapper loginLogMapper, UserMapper userMapper) {
        this.loginLogMapper = loginLogMapper;
        this.userMapper = userMapper;
    }

    @Override
    public RespVos<LoginLogVo> listLoginLogs(LoginLogSearchVo loginLogSearchVo) {
        List<LoginLog> loginLogs = loginLogMapper.listLoginLogs(loginLogSearchVo);

        if (loginLogs != null && loginLogs.size() > 0) {
            RespVos<LoginLogVo> respVos = new RespVos<>();
            respVos.setSize(loginLogMapper.countLoginLogs(loginLogSearchVo));
            respVos.setVos(loginLogs.stream().map(this::convertToVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    @Override
    public Integer insertLoginLog(LoginLog loginLog) {
        return loginLogMapper.insertLoginLog(loginLog);
    }

    @Override
    public Integer deleteLoginLogs(DeleteVo deleteVo) {
        return loginLogMapper.deleteLoginLogs(deleteVo.getIds());
    }

    private LoginLogVo convertToVo(LoginLog loginLog) {
        LoginLogVo loginLogVo = new LoginLogVo();

        User user = userMapper.getUser(loginLog.getLoginUserId());

        loginLogVo.setLoginLogId(loginLog.getLoginId());
        loginLogVo.setIsSuccess(loginLog.getIsSuccess());
        loginLogVo.setLoginUserId(loginLog.getLoginUserId());
        loginLogVo.setLoginUserName(user == null ? null : user.getName());
        loginLogVo.setLoginTime(loginLog.getLoginTime());
        loginLogVo.setLoginIp(loginLog.getLoginIp());
        loginLogVo.setLoginDesc(loginLog.getLoginDesc());

        return loginLogVo;
    }
}
