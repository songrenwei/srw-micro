package com.micro.srw.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 实现接口IEnum
 * @Author: renwei.song
 * @Date: 2021/5/10 16:43
 */
@Getter
@AllArgsConstructor
public enum GenderEnum implements IEnum<Integer> {

    FEMALE(0, "女性"),
    MALE(1, "男性");

    private final Integer code;
    private final String desc;

    @Override
    public Integer getValue() {
        return this.code;
    }
}
