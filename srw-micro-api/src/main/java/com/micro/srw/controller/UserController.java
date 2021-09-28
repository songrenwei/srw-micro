package com.micro.srw.controller;

import com.micro.srw.api.JsonResult;
import com.micro.srw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/login")
    public JsonResult<?> login(@RequestParam String username, @RequestParam String password) {
        return JsonResult.success(loginService.login(username, password));
    }

}
