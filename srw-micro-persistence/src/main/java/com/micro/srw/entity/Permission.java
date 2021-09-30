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
@TableName(value = "t_permission", autoResultMap = true)
public class Permission extends BaseEntity {

    private String roleCode;

    private String target;

    private String operate;

}
