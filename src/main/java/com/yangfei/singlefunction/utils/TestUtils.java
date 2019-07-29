package com.yangfei.singlefunction.utils;

import java.text.MessageFormat;

/**
 * <p>
 *  测试类，非工具类
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
public class TestUtils {
    //空格
    protected static final String DEFAULT_SEP = "\t";
    private static final String logMessageFormat = "{0}#{1}#{2}#{3}".replace("#", DEFAULT_SEP);
    private static final String logMessageFormat1 = "{0} {1} {2} {3}";

    public static void main(String[] args) {
        System.out.println(logMessageFormat);
        System.out.println(logMessageFormat1);
        String format = MessageFormat.format(logMessageFormat, "today", "is", "friday","ok");
        String format1 = MessageFormat.format(logMessageFormat1, "today", "is", "friday","ok");
        System.out.println(format);
        System.out.println(format1);

    }
}
