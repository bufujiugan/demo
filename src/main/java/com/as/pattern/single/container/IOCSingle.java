package com.as.pattern.single.container;

import java.util.HashMap;
import java.util.Map;

/**
 * 无论在任何条件下有且只有一个实例
 * 构造器私有化
 * 全局有且只有一个对外提供的方法
 *
 * 优点: 使用时才创建 不会造成内存浪费
 * 缺点: 线程不安全
 */
public class IOCSingle {

    private static Map<String, Object> map = new HashMap<>();

    private IOCSingle() {

    }

    public Object getInstance(String className) {
        if(!map.containsKey(className)) {
            Object o = null;
            try {
                o = Class.forName(className).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put(className, o);
            return o;
        }
        return map.get(className);
    }

}
