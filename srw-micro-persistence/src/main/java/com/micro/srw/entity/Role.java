package com.micro.srw.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(value = "t_role", autoResultMap = true)
public class Role extends BaseEntity {

    private Long userId;

    private String roleCode;

    private String roleName;

}
