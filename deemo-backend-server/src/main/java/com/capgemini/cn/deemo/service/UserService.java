package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.data.dto.UserDto;
import com.capgemini.cn.deemo.mapper.UserMapper;
import com.capgemini.cn.deemo.vo.request.UserSearchVo;
import com.capgemini.cn.deemo.vo.response.UserResponseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Service
public interface UserService {

    User queryObject(Integer id);

    List<User> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    boolean save(User user);

    boolean update(User user);

    boolean delete(Integer id);

    boolean deleteBatch(Integer[] ids);

    UserResponseVo listUser(UserSearchVo userSearchVo);

    List<UserDto> queryAll();
    //批量删除员工信息接口
    //public int deleteBatch(@Param("id") List<Integer> ids);
}
