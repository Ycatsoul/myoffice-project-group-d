package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.data.dto.UserDto;
import com.capgemini.cn.deemo.vo.request.UserSearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



import java.util.List;

/**
 * @Description:DAO层UserMapper类
 * @Classname :UserMapper
 * @author: GuoBingjun
 * @date:2019-8-21 16:13
 */

public interface UserMapper extends BaseMapper<User>{

    List<User> listUser(@Param("userSearch") UserSearchVo userSearchVo);
    Long countUser(@Param("userSearch") UserSearchVo userSearchVo);
    List<UserDto> queryAll();

//    //根据id查询员工
//    User queryById(@Param("id") int id);
//    //查询所有员工
//    List<User> queryAll();
//    //增加员工信息
//    Integer insertUser(User user);
//    //更改员工信息
//    Integer updateUserById(int id);
//    //删除员工信息
//    Integer deleteUserById(int id);
//    //批量删除员工信息
//    //int deleteBatch(@Param("ids") List<Integer> ids);

}
