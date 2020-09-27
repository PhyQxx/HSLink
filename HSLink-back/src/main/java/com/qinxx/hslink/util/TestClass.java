package com.qinxx.hslink.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 帅气的布里茨
 */
public class TestClass {

    /**
     * 主线程
     */
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("name","张氏");
        System.out.println(map.get("sex"));
    }
}
