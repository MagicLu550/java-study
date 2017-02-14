package com.a.eye.by.design.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举类
 *
 */
public enum Singleton6 {

    INSTANCE;

    private Map<String, String> map = new HashMap<String, String>();

    private Singleton6() {
        map.put("aa", "aa");
    }

    public Map<String, String> getMap() {
        return map;
    }

    public static void main(String[] args) {
        Singleton6.INSTANCE.getMap();
    }

}
