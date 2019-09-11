package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.request.UserEditVo;
import com.capgemini.cn.deemo.vo.request.UserSearchVo;
import com.capgemini.cn.deemo.vo.response.BraDepUserVo;
import com.capgemini.cn.deemo.vo.response.UserVo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
public interface UserService extends UserDetailsService {

    List<Long> getAllUserIds();

    RespVos<UserVo> getUser(Long userId);

    User getUserByUsername(String username);

    RespVos<UserVo> listUsers(UserSearchVo userSearchVo);

    RespVos<BraDepUserVo> getBraDepUserTree();

    Integer addUser(UserEditVo userEditVo);

    Integer updateUser(UserEditVo userEditVo);

    Integer blockUsers(DeleteVo deleteVo);

    Integer deleteUsers(DeleteVo deleteVo);

    UserVo convertToVo(User user);
}
