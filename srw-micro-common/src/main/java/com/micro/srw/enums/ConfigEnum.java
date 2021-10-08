package com.micro.srw.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConfigEnum {

    USER(100000L, "user", "用户"),
    ROLE(200000L, "role", "角色"),
    PERMISSION(300000L, "permission", "权限"),
    ;

    /**
     * 配置编码
     */
    private Long code;
    /**
     * 配置名称
     */
    private String name;
    /**
     * 配置描述
     */
    private String desc;


    public String cacheKey() {
        return "cfg:" + this.code;
    }
}
