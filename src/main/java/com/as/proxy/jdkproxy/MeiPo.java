package com.as.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MeiPo implements InvocationHandler {

    private IPersion persion;


    public IPersion getInstance(IPersion persion) {
        this.persion = persion;
        Class<?> clazz = persion.getClass();
        return (IPersion) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object = method.invoke(this.persion, args);
        return object;
    }
}
