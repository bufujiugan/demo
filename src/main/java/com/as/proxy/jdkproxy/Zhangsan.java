package com.as.proxy.jdkproxy;

public class Zhangsan implements IPersion {
    @Override
    public void findLove() {
        System.out.println("张三");
    }

    @Override
    public void butInsure() {
        System.out.println("张三买保险");
    }
}
