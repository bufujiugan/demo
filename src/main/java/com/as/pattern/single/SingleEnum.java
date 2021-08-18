package com.as.pattern.single;

/**
 * 无论在任何条件下有且只有一个实例
 * 构造器私有化
 * 全局有且只有一个对外提供的方法
 *
 * 枚举在jdk底层判断了 如果是枚举类是不允许反序列化的
 * 因枚举没有无参构造方法  所以反射也破坏不了
 */
public enum SingleEnum {
    INSTANCE;

    public static final SingleEnum singleEnum = INSTANCE;

    public static SingleEnum getInstance() {return INSTANCE;}
}
