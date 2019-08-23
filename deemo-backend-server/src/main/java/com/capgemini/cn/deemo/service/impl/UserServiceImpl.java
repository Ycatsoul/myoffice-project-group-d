package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.mapper.UserMapper;
import com.capgemini.cn.deemo.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryObject(Integer id) {
        return userMapper.queryObject(id);
    }

    @Override
    public List<User> queryList(Map<String,Object> map) {
        return userMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userMapper.queryTotal(map);
    }

    @Override
    public boolean save(User user) {
        return userMapper.save(user) > 0;
    }

    @Override
    public boolean update(User user) {
        return userMapper.save(user) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return userMapper.delete(id) > 0;
    }

    @Override
    public boolean deleteBatch(Integer[] ids) {
        return userMapper.deleteBatch(ids) > 0;
    }


//    @Override
//    public int deleteBatch(List<Integer> ids) {
//        return userMapper.deleteBatch(ids);
//    }
}
