package com.as.single.lazy;

import cn.hutool.core.util.ObjectUtil;

/**
 * 无论在任何条件下有且只有一个实例
 * 构造器私有化
 * 全局有且只有一个对外提供的方法
 *
 * 优点: 使用时才创建 不会造成内存浪费
 * 缺点: 线程不安全
 */
public class LazySingleV1 {

    private static volatile LazySingleV1 lazySingleV1;

    private LazySingleV1() {

    }

    // V2
//    public synchronized LazySingleV1 getInstance() {
    public LazySingleV1 getInstance() {

        // V1
//        if(ObjectUtil.isNull(lazySingleV1)) {
//            return new LazySingleV1();
//        }


        // V3
//        synchronized (this) {
//            if(ObjectUtil.isNull(lazySingleV1)) {
//                return new LazySingleV1();
//            }
//        }

        // V4  可读性低
        if(ObjectUtil.isNull(lazySingleV1)) {
            synchronized (this) {
                if (ObjectUtil.isNull(lazySingleV1)) {
                    // 指令重排
                    return new LazySingleV1();
                }
            }
        }

        return lazySingleV1;
    }

}
