package com.micro.srw.controller;

import com.micro.srw.bean.JsonResult;
import com.micro.srw.bean.TokenDto;
import com.micro.srw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/9/24 16:22
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService loginService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public JsonResult<TokenDto> login(@RequestParam String username, @RequestParam String password) {
        return JsonResult.success(loginService.login(username, password));
    }

    /**
     * 是否登录
     */
    @GetMapping("/isLogin")
    public JsonResult<Boolean> isLogin() {
        return JsonResult.success(loginService.isLogin());
    }

    /**
     * 登出
     */
    @GetMapping("/logout")
    public JsonResult<Boolean> logout() {
        return JsonResult.success(loginService.logout());
    }

}
