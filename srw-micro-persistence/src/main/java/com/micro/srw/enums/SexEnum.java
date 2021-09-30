package com.micro.srw.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @Description: 使用注解@EnumValue
 * @Author: renwei.song
 * @Date: 2021/5/10 16:43
 */
@Getter
@AllArgsConstructor
public enum SexEnum {

    FEMALE("female", "女性"),
    MALE("male", "男性");

    @EnumValue // 标记数据库存的值是code
    private final String code;

    private final String desc;

    public SexEnum getEnum(String code) {
        return Arrays.stream(SexEnum.values())
                .filter(e -> StringUtils.equals(e.getCode(), code))
                .findAny()
                .orElse(null);
    }

}
