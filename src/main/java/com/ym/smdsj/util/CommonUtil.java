package com.ym.smdsj.util;

import java.util.Random;

/**
 * @author ym
 * @date 2019/3/12
 **/
public class CommonUtil {
    /**
     * 获取指定位数的随机数
     * @param length 长度
     * @return java.lang.String
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
