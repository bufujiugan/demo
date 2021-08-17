package com.as.proxy.staticproxy;

public class RealStudent implements IStudent{

    public String request() {
        return "真实的方法1";
    }
}
