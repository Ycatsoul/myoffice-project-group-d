package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.Role;
import com.capgemini.cn.deemo.mapper.UserRoleMapper;
import com.capgemini.cn.deemo.service.UserRoleService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.vo.request.UserRoleEditVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 00:54
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleMapper userRoleMapper;

    public UserRoleServiceImpl(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        return userRoleMapper.getRolesByUserId(userId);
    }

    @Override
    public Integer addUserRole(UserRoleEditVo userRoleEditVo) {
        return userRoleMapper.addUserRole(
                    IdWorker.get().nextId(),
                    userRoleEditVo.getUserId(),
                    userRoleEditVo.getRoleId());
    }

    @Override
    public Integer deleteUserRoleByUserRoleId(Long userRoleId) {
        return userRoleMapper.deleteUserRoleByUserRoleId(userRoleId);
    }

    @Override
    public Integer deleteUserRoleByUserIdAndRoleId(Long userId, Long roleId) {
        return userRoleMapper.deleteUserRoleByUserIdAndRoleId(userId, roleId);
    }

    @Override
    public Integer deleteUserRolesByUserId(Long userId) {
        return userRoleMapper.deleteUserRolesByUserId(userId);
    }
}
