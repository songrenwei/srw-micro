package com.micro.srw.utils;

import java.util.UUID;

public class CodeUtils {

    public static String createCodeByUuid(String code) {
        return code+UUID.randomUUID().toString().replace("-", "");
    }

    public static String createCodeByTime(String code) {
        return code + System.currentTimeMillis();
    }

}
