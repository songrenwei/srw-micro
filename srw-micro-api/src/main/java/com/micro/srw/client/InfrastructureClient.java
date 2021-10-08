package com.micro.srw.client;

import com.micro.srw.api.JsonResult;
import com.micro.srw.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/10/8 14:51
 */
@FeignClient("srw-micro-infrastructure")
public interface InfrastructureClient {

    @GetMapping("/config/user/query")
    JsonResult<User> queryUserByUsername(@RequestParam("username") String username);

    @GetMapping("/config/role/query")
    JsonResult<String> queryRoleByUserId(@RequestParam("userId") Long userId);

    @GetMapping("/config/permission/query")
    JsonResult<List<String>> queryPermissionByRoleCode(@RequestParam("roleCode") String roleCode);

}
