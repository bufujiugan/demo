package com.as.pattern.proxy.staticproxy;

public class ProxyStudent {
    private IStudent student;

    public ProxyStudent(IStudent student) {
        this.student = student;
    }

    public void req() {
        System.out.println("before");
        System.out.println(student.request());
        System.out.println("after");
    }
}
