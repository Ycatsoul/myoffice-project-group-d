package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.Branch;
import com.capgemini.cn.deemo.data.domain.Department;
import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.mapper.BranchMapper;
import com.capgemini.cn.deemo.mapper.DepartmentMapper;
import com.capgemini.cn.deemo.mapper.UserMapper;
import com.capgemini.cn.deemo.service.UserService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.BranchSearchVo;
import com.capgemini.cn.deemo.vo.request.DepartmentSearchVo;
import com.capgemini.cn.deemo.vo.request.UserEditVo;
import com.capgemini.cn.deemo.vo.request.UserSearchVo;
import com.capgemini.cn.deemo.vo.response.BraDepUserVo;
import com.capgemini.cn.deemo.vo.response.UserVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final DepartmentMapper departmentMapper;
    private final BranchMapper branchMapper;

    public UserServiceImpl(UserMapper userMapper, DepartmentMapper departmentMapper, BranchMapper branchMapper) {
        this.userMapper = userMapper;
        this.departmentMapper = departmentMapper;
        this.branchMapper = branchMapper;
    }

    @Override
    public RespVos<UserVo> getUser(Long userId) {
        User user = userMapper.getUser(userId);

        if (user != null) {
            RespVos<UserVo> respVos = new RespVos<>();
            respVos.setSize(1);
            respVos.setVos(new ArrayList<UserVo>(){{
                add(convertToVo(user));
            }});

            return respVos;
        }

        return null;
    }

    @Override
    public RespVos<UserVo> listUsers(UserSearchVo userSearchVo) {
        List<User> users = userMapper.listUsers(userSearchVo);

        if (users != null && users.size() > 0) {
            RespVos<UserVo> respVos = new RespVos<>();
            respVos.setSize(userMapper.countUsers(userSearchVo));
            respVos.setVos(users.stream().map(this::convertToVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    @Override
    public RespVos<BraDepUserVo> getBraDepUserTree() {
        List<Branch> branches = branchMapper.listBranches(new BranchSearchVo(){{
            setStart(0);
            setSize(100);
        }});
        List<BraDepUserVo> braDepUserVos = new ArrayList<>(branches.size());
        RespVos<BraDepUserVo> respVos = new RespVos<BraDepUserVo>(){{
            setSize(branches.size());
        }};

        for (Branch branch : branches) {
            List<Department> departments = departmentMapper.listDepartments(
                    new DepartmentSearchVo(){{
                        setStart(0);
                        setSize(100);
                        setBranchId(branch.getBranchId());
                    }}
            );
            List<BraDepUserVo.Department> braDeps = new ArrayList<>(departments.size());

            for (Department department : departments) {
                List<User> users = userMapper.listUsers(
                        new UserSearchVo(){{
                            setStart(0);
                            setSize(1000);
                            setDepartmentId(department.getDepartmentId());
                        }}
                );
                List<BraDepUserVo.Department.User> depUsers = new ArrayList<>(users.size());

                for (User user : users) {
                    depUsers.add(new BraDepUserVo.Department.User(){{
                        setUserId(user.getUserId());
                        setName(user.getName());
                    }});
                }

                BraDepUserVo.Department braDep = new BraDepUserVo.Department(){{
                   setDepartmentId(department.getDepartmentId());
                   setDepartmentName(department.getDepartmentName());
                   setUsers(depUsers);
                }};

                braDeps.add(braDep);
            }

            BraDepUserVo braDepUserVo = new BraDepUserVo();
            braDepUserVo.setBranchId(branch.getBranchId());
            braDepUserVo.setBranchShortName(branch.getBranchShortName());
            braDepUserVo.setDepartments(braDeps);

            braDepUserVos.add(braDepUserVo);
        }

        respVos.setVos(braDepUserVos);

        return respVos;
    }

    @Override
    public Integer addUser(UserEditVo userEditVo) {
        userEditVo.setUserId(IdWorker.get().nextId());

        return userMapper.insertUser(userEditVo);
    }

    @Override
    public Integer updateUser(UserEditVo userEditVo) {
        return userMapper.updateUser(userEditVo);
    }

    @Override
    public Integer blockUsers(List<Long> userIds) {
        return userMapper.blockUsers(userIds);
    }

    @Override
    public Integer deleteUsers(List<Long> userIds) {
        return userMapper.deleteUsers(userIds);
    }

    /**
     * 将User装换为UserVo
     */
    private UserVo convertToVo(User user) {
        UserVo userVo = new UserVo();
        Department department = departmentMapper.getDepartment(user.getDepartmentId());

        userVo.setUserId(user.getUserId());
        userVo.setUsername(user.getUsername());
        userVo.setName(user.getName());
        userVo.setGender(user.getGender());
        userVo.setAvatar(user.getAvatar());
        userVo.setDepartmentId(user.getDepartmentId());
        userVo.setDepartmentName(department == null ? "不属于任何部门" : department.getDepartmentName());

        return userVo;
    }
}
