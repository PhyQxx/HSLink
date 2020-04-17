package com.qinxx.hslink.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 帅气的布里茨
 */
public class TestClass {
    public static void main(String[] args) {
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // new Date()为获取当前系统时间
        System.out.println(df.format(new Date()));
    }
}
