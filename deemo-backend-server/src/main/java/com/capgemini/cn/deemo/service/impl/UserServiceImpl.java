package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.Branch;
import com.capgemini.cn.deemo.data.domain.Department;
import com.capgemini.cn.deemo.data.domain.Role;
import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.mapper.*;
import com.capgemini.cn.deemo.service.UserService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.*;
import com.capgemini.cn.deemo.vo.response.BraDepUserVo;
import com.capgemini.cn.deemo.vo.response.UserVo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;
    private final DepartmentMapper departmentMapper;
    private final BranchMapper branchMapper;

    public UserServiceImpl(UserMapper userMapper,
                           RoleMapper roleMapper,
                           UserRoleMapper userRoleMapper,
                           DepartmentMapper departmentMapper,
                           BranchMapper branchMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.userRoleMapper = userRoleMapper;
        this.departmentMapper = departmentMapper;
        this.branchMapper = branchMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }

        List<Role> roles = userRoleMapper.getRolesByUserId(user.getUserId());
        user.setRoles(roles);

        return user;
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
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
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
        List<Branch> branches = branchMapper.listBranches(new BranchSearchVo(){{setSize(100);}});
        List<Department> departments = departmentMapper.listDepartments(new DepartmentSearchVo(){{setSize(200);}});
        List<User> users = userMapper.listUsers(new UserSearchVo(){{setSize(1000);}});
        List<BraDepUserVo> braDepUserVos = new ArrayList<>();
        RespVos<BraDepUserVo> respVos = new RespVos<>();

        for (Branch branch : branches) {
            List<BraDepUserVo.Department> convertedDepartments = new ArrayList<>();
            for (Department department : departments) {
                if (department.getBranchId().equals(branch.getBranchId())) {
                    List<BraDepUserVo.Department.User> convertedUsers = new ArrayList<>();
                    for (User user : users) {
                        if (user.getDepartmentId().equals(department.getDepartmentId())) {
                            convertedUsers.add(new BraDepUserVo.Department.User() {{
                                setUserId(user.getUserId());
                                setName(user.getName());
                            }});
                        }
                    }
                    convertedDepartments.add(new BraDepUserVo.Department(){{
                        setDepartmentId(department.getDepartmentId());
                        setDepartmentName(department.getDepartmentName());
                        setUsers(convertedUsers);
                    }});
                }
            }
            braDepUserVos.add(new BraDepUserVo(){{
                setBranchId(branch.getBranchId());
                setBranchShortName(branch.getBranchShortName());
                setDepartments(convertedDepartments);
            }});
        }

        respVos.setSize(braDepUserVos.size());
        respVos.setVos(braDepUserVos);

        return respVos;
    }

    @Override
    public Integer addUser(UserEditVo userEditVo) {
        // 用户名已存在, 返回-1
        if (userMapper.getUserByUsername(userEditVo.getUsername()) != null) {
            return -1;
        }

        userEditVo.setUserId(IdWorker.get().nextId());
        userEditVo.setPassword(new BCryptPasswordEncoder().encode(userEditVo.getPassword()));

        // 新注册用户默认为ROLE_USER
        Role role = roleMapper.getRoleByRoleName("ROLE_USER");

        Integer res = userMapper.insertUser(userEditVo);

        if (res == 1 && role != null) {
            userRoleMapper.addUserRole(IdWorker.get().nextId(), userEditVo.getUserId(), role.getRoleId());
        }

        return res;
    }

    @Override
    public Integer updateUser(UserEditVo userEditVo) {
        return userMapper.updateUser(userEditVo);
    }

    @Override
    public Integer blockUsers(DeleteVo deleteVo) {
        return userMapper.blockUsers(deleteVo.getIds());
    }

    @Override
    public Integer deleteUsers(DeleteVo deleteVo) {
        return userMapper.deleteUsers(deleteVo.getIds());
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
        userVo.setDepartmentName(department == null ? null : department.getDepartmentName());

        return userVo;
    }
}
