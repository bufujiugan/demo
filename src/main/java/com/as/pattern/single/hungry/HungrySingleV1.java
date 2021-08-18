package com.as.pattern.single.hungry;

/**
 * 无论在任何条件下有且只有一个实例
 * 构造器私有化
 * 全局有且只有一个对外提供的方法
 *
 * 优点: 线程安全
 * 缺点: 大量单例时会造成内存浪费 占用资源
 */
public class HungrySingleV1 {

    // ============= V1 ===============
//    private static final HungrySingleV1 hungrySingleV1 = new HungrySingleV1();

    // ============= V2 ===============
    private static final HungrySingleV1 hungrySingleV1;

    static {
        hungrySingleV1 = new HungrySingleV1();
    }

    private HungrySingleV1() {

    }

    public HungrySingleV1 getInstance() {
        return hungrySingleV1;
    }
}
