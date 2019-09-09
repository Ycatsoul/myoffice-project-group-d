package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.LoginLog;
import com.capgemini.cn.deemo.vo.request.LoginLogSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/4 15:09
 */
@Service
public interface LoginLogMapper {

    List<LoginLog> listLoginLogs(@Param("loginLogSearchVo") LoginLogSearchVo loginLogSearchVo);

    Integer countLoginLogs(@Param("loginLogSearchVo") LoginLogSearchVo loginLogSearchVo);

    Integer insertLoginLog(@Param("loginLog") LoginLog loginLog);

    Integer deleteLoginLogs(@Param("loginIds") List<Long> loginIds);
}
