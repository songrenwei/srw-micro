package com.micro.srw.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.micro.srw.bean.TokenDto;
import com.micro.srw.client.InfrastructureClient;
import com.micro.srw.entity.User;
import com.micro.srw.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/9/24 16:22
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final InfrastructureClient infrastructureClient;

    public TokenDto login(String username, String password) {
        User user = infrastructureClient.queryUserByUsername(username).getData();
        if (user == null) {
            throw new BusinessException("用户名或密码错误!");
        }
        if (!StringUtils.equals(user.getPassword(), password)) {
            throw new BusinessException("用户名或密码错误");
        }
        // 密码校验成功后登录，一行代码实现登录
        StpUtil.login(user.getId());

        // 将用户信息存储到Session中
        String roleCode = infrastructureClient.queryRoleByUserId(user.getId()).getData();
        List<String> permissionList = infrastructureClient.queryPermissionByRoleCode(roleCode).getData();
        StpUtil.getSession().set("user", user.getUsername());
        StpUtil.getSession().set("role", roleCode);
        StpUtil.getSession().set("permission", permissionList);

        // 获取当前登录用户Token信息
        SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
        if (saTokenInfo == null) {
            throw new BusinessException("用户名或密码错误");
        }
        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(saTokenInfo.getTokenValue());
        tokenDto.setTokenHead(saTokenInfo.getTokenName());
        return tokenDto;
    }

    public Boolean isLogin() {
        return StpUtil.isLogin();
    }

    public Boolean logout() {
        StpUtil.logout();
        return Boolean.TRUE;
    }

}
