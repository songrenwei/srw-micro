package com.micro.srw.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.micro.srw.entity.Permission;
import com.micro.srw.entity.Role;
import com.micro.srw.entity.User;
import com.micro.srw.exception.BusinessException;
import com.micro.srw.mapper.PermissionMapper;
import com.micro.srw.mapper.RoleMapper;
import com.micro.srw.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/9/24 16:22
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper;

    public Map<String, String> login(String username, String password) {
        User user = getUserByUsername(username);
        if (user == null) {
            throw new BusinessException("用户名或密码错误!");
        }
        if (!StringUtils.equals(user.getPassword(), password)) {
            throw new BusinessException("用户名或密码错误");
        }
        // 密码校验成功后登录，一行代码实现登录
        StpUtil.login(user.getId());

        // 将用户信息存储到Session中
        String roleCode = getRoleByUserId(user.getId());
        StpUtil.getSession().set("user", user.getUsername());
        StpUtil.getSession().set("role", roleCode);
        StpUtil.getSession().set("permission", getPermissionByRoleCode(roleCode));

        // 获取当前登录用户Token信息
        SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
        if (saTokenInfo == null) {
            throw new BusinessException("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", saTokenInfo.getTokenValue());
        tokenMap.put("tokenHead", saTokenInfo.getTokenName());
        return tokenMap;
    }

    /**
     * 根据用户名获取用户信息
     */
    public User getUserByUsername(String username) {
        // 用户
        LambdaQueryWrapper<User> eq = new QueryWrapper<User>().lambda().eq(User::getUsername, username);
        return userMapper.selectOne(eq);
    }

    /**
     * 根据用户ID获取用户角色
     */
    public String getRoleByUserId(Long userId) {
        // 角色
        LambdaQueryWrapper<Role> eq = new QueryWrapper<Role>().lambda().eq(Role::getUserId, userId);
        return Optional.ofNullable(roleMapper.selectOne(eq)).orElseGet(Role::new).getRoleCode();
    }

    /**
     * 根据用户ID获取用户角色权限
     */
    public List<String> getPermissionByRoleCode(String roleCode) {
        // 权限
        LambdaQueryWrapper<Permission> eq = new QueryWrapper<Permission>().lambda().eq(Permission::getRoleCode, roleCode);
        List<Permission> permissionList = permissionMapper.selectList(eq);
        return permissionList.stream().map(Permission::getOperate).collect(Collectors.toList());
    }

}
