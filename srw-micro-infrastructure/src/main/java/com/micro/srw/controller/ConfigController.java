package com.micro.srw.controller;

import com.micro.srw.annotation.Log;
import com.micro.srw.bean.JsonResult;
import com.micro.srw.entity.User;
import com.micro.srw.service.ConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/10/8 14:28
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/config")
public class ConfigController {

    private final ConfigService configService;

    /**
     * 用户查询
     */
    @Log
    @GetMapping("/user/query")
    public JsonResult<User> queryUserByUsername(@RequestParam String username) {
        return JsonResult.success(configService.queryUserByUsername(username));
    }

    /**
     * 角色查询
     */
    @Log
    @GetMapping("/role/query")
    public JsonResult<String> queryRoleByUserId(@RequestParam Long userId) {
        return JsonResult.success(configService.queryRoleByUserId(userId));
    }

    /**
     * 权限查询
     */
    @Log
    @GetMapping("/permission/query")
    public JsonResult<List<String>> queryPermissionByRoleCode(@RequestParam String roleCode) {
        return JsonResult.success(configService.queryPermissionByRoleCode(roleCode));
    }

    @DeleteMapping("/clear")
    public void clear(@RequestParam String key) {
        configService.clear(key);
    }

}
