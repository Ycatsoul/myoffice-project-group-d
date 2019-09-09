package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.vo.request.UserEditVo;
import com.capgemini.cn.deemo.vo.request.UserSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:DAO层UserMapper类
 * @Classname :UserMapper
 * @author: GuoBingjun
 * @date:2019-8-21 16:13
 */
@Service
public interface UserMapper {

    User getUser(@Param("userId") Long userId);

    User getUserByUsername(@Param("username") String username);

    List<User> listUsers(@Param("userSearchVo") UserSearchVo userSearchVo);

    Integer countUsers(@Param("userSearchVo") UserSearchVo userSearchVo);

    Integer insertUser(@Param("userEditVo") UserEditVo userEditVo);

    Integer updateUser(@Param("userEditVo") UserEditVo userEditVo);

    Integer blockUsers(@Param("userIds") List<Long> userIds);

    Integer deleteUsers(@Param("userIds") List<Long> userIds);
}
