package com.as.proxy.staticproxy;

public class Test {
    public static void main(String[] args) {
        RealStudent realStudent = new RealStudent();
        ProxyStudent proxyStudent = new ProxyStudent(realStudent);
        proxyStudent.req();
    }
}
