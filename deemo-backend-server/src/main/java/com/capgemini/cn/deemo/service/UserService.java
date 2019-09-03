package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.UserEditVo;
import com.capgemini.cn.deemo.vo.request.UserSearchVo;
import com.capgemini.cn.deemo.vo.response.UserVo;

import java.util.List;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
public interface UserService {

    RespVos<UserVo> getUser(Long userId);

    RespVos<UserVo> listUsers(UserSearchVo userSearchVo);

    Integer addUser(UserEditVo userEditVo);

    Integer updateUser(UserEditVo userEditVo);

    Integer blockUsers(List<Long> userIds);

    Integer deleteUsers(List<Long> userIds);
}
