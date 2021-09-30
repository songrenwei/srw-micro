package com.micro.srw.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.micro.srw.enums.SexEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2020/10/13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_user", autoResultMap = true)
public class User extends BaseEntity {

    private String username;

    private String password;

    private Integer age;

    private SexEnum sex;

}
