package com.as.proxy.cglib;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CGMeiPo implements InvocationHandler {

    IPersion persion;

    public IPersion getInstance(IPersion persion) {
        this.persion = persion;
        Class<?> clazz = persion.getClass();
        return (IPersion) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始物色");
        Object result = method.invoke(this.persion, args);
        System.out.println("开始交往");
        return result;
    }
}
