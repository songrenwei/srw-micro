package com.micro.srw.config;

import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

@Configuration
public class SaTokenConfig {
    /**
     * 注册Sa-Token全局过滤器
     */
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 拦截地址
                .addInclude("/**")
                // 开放地址
                .addExclude("/favicon.ico")
                // 鉴权方法：每次访问进入
                .setAuth(r -> {
                    // 登录认证：除登录接口都需要认证
                    SaRouter.match("/**", "/api/user/login", StpUtil::checkLogin);

                    // 角色认证：ROLE_ADMIN可以访问所有接口，ROLE_USER只能访问查询全部接口
//                    SaRouter.match("/api/order/listAll", () -> {
//                        StpUtil.checkRoleOr("ROLE_ADMIN","ROLE_USER");
//                        //强制退出匹配链
//                        SaRouter.stop();
//                    });
//                    SaRouter.match("/api/order/**", () -> StpUtil.checkRole("ROLE_ADMIN"));

                    // 权限认证：不同接口访问权限不同
                    SaRouter.match("/api/order/listAll", () -> StpUtil.checkPermission("order:read"));
                    SaRouter.match("/api/order/create", () -> StpUtil.checkPermission("order:create"));
                    SaRouter.match("/api/order/update", () -> StpUtil.checkPermission("order:update"));
                    SaRouter.match("/api/order/delete", () -> StpUtil.checkPermission("order:delete"));
                    SaRouter.match("/api/order/listByPage", () -> StpUtil.checkPermission("order:read"));
                    SaRouter.match("/api/order/get/{id}", () -> StpUtil.checkPermission("order:read"));
                })
                // setAuth方法异常处理
                .setError(e -> {
                    //设置错误返回格式为JSON
                    ServerWebExchange exchange = SaReactorSyncHolder.getContent();
                    exchange.getResponse().getHeaders().set("Content-Type", "application/json; charset=utf-8");
                    return SaResult.error(e.getMessage());
                });
    }
}
