package com.micro.srw.domain;

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
@TableName(value = "t_order", autoResultMap = true)
public class Order extends BaseEntity {

    private String code;

    private String name;

    private String createBy;

}
