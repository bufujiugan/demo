package com.as.factory.easy;

/**
 * 中层工作
 */
public class MiddleWork implements IWork {
    @Override
    public void create() {
        System.out.println("中层工作");
    }
}
