package com.as.pattern.factory.easy;

/**
 * 新农民工
 */
public class BottomWork implements IWork {
    @Override
    public void create() {
        System.out.println("新农民工工作");
    }
}
