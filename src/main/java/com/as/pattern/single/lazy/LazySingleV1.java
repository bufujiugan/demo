package com.as.pattern.single.lazy;

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
        throw new RuntimeException("非法访问");
    }

    // V2 线程安全  但是性能差 因为都挡住了
//    public synchronized LazySingleV1 getInstance() {
    public static LazySingleV1 getInstance() {

        // V1 线程不安全【多线程会创建多个对象】
//        if(ObjectUtil.isNull(lazySingleV1)) {
//            return new LazySingleV1();
//        }

        // V3
//        synchronized (this) {
//            if(ObjectUtil.isNull(lazySingleV1)) {
//                return new LazySingleV1();
//            }
//        }

        // V4  可读性难度加大
        if(ObjectUtil.isNull(lazySingleV1)) {
            synchronized (LazySingleV1.class) {
                if (ObjectUtil.isNull(lazySingleV1)) {
                    /**
                     * 对象new的过程
                     *  1. 遇到new指令，首先将去检查这个指令的参数是否能在常量池中定位到一个类的符号引用，并且检查这个符号引用代表的类是否已被加载、解析和初始化过。 如果没有，那必须先执行相应的类加载过程。
                     *  2. 在类加载检查通过后，接下来虚拟机将为新生对象分配内存。 对象所需内存的大小在类加载完成后便可完全确定。
                     *      为对象分配空间的任务实际上便等同于把一块确定大小的内存块从Java堆中划分出来。【即开辟堆空间】
                     *  3. 内存分配完成之后，虚拟机必须将分配到的内存空间（但不包括对象头）都初始化为 零值
                     *  4. 接下来，Java虚拟机还要对对象进行必要的设置，例如这个对象是哪个类的实例、如何才能找到类的元数据信息、
                     *      对象的哈希码（实际上对象的哈希码会延后到真正调用Object::hashCode()方法时才计算）、对象的GC分代年龄等信息。这些信息存放在对象的对象头（Object Header）之中。根据虚拟机当前运行状态的不同，如是否启用偏向锁等，对象头会有不同的设置方式。
                     */
                    // 指令重排 3 4 如果不加volatile 可能会颠倒。 导致直接赋值了一个空
                    lazySingleV1 = new LazySingleV1();
                }
            }
        }
        return lazySingleV1;
    }

}
