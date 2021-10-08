package com.micro.srw.service;

import com.micro.srw.entity.User;

import java.util.List;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/10/8 14:33
 */
public interface ConfigService {

    User queryUserByUsername(String username);

    String queryRoleByUserId(Long userId);

    List<String> queryPermissionByRoleCode(String roleCode);

    void clear(String key);

}
