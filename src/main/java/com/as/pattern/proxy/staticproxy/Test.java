package com.as.pattern.proxy.staticproxy;

public class Test {
    public static void main(String[] args) {
        ProxyStudent proxyStudent = new ProxyStudent(new RealStudent());
        proxyStudent.request();
    }
}
