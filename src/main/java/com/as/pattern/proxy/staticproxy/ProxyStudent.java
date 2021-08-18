package com.as.pattern.proxy.staticproxy;

public class ProxyStudent implements IStudent{

    private IStudent student;

    public ProxyStudent(IStudent student) {
        this.student = student;
    }

    @Override
    public String request() {
        System.out.println("before");
        System.out.println(student.request());
        System.out.println("after");
        return "";
    }
}
