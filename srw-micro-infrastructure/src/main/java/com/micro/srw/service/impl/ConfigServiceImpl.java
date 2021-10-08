package com.micro.srw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.micro.srw.entity.Permission;
import com.micro.srw.entity.Role;
import com.micro.srw.entity.User;
import com.micro.srw.mapper.PermissionMapper;
import com.micro.srw.mapper.RoleMapper;
import com.micro.srw.mapper.UserMapper;
import com.micro.srw.service.ConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/10/8 14:36
 */
@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper;

    @Override
    @Cacheable(key = "'user_'+ #username", value = "30d")
    public User queryUserByUsername(String username) {
        // 用户
        LambdaQueryWrapper<User> eq = new QueryWrapper<User>().lambda().eq(User::getUsername, username);
        return userMapper.selectOne(eq);
    }

    @Override
    @Cacheable(key = "'role_'+#userId", value = "30d")
    public String queryRoleByUserId(Long userId) {
        // 角色
        LambdaQueryWrapper<Role> eq = new QueryWrapper<Role>().lambda().eq(Role::getUserId, userId);
        return Optional.ofNullable(roleMapper.selectOne(eq)).orElseGet(Role::new).getRoleCode();
    }

    @Override
    @Cacheable(key = "'permission_'+#roleCode", value = "30d")
    public List<String> queryPermissionByRoleCode(String roleCode) {
        // 权限
        LambdaQueryWrapper<Permission> eq = new QueryWrapper<Permission>().lambda().eq(Permission::getRoleCode, roleCode);
        List<Permission> permissionList = permissionMapper.selectList(eq);
        return permissionList.stream().map(Permission::getOperate).collect(Collectors.toList());
    }

    @Override
    @CacheEvict(key = "#key", value = "30d")
    public void clear(String key) {

    }


}
