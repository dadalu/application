package com.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static jdk.nashorn.internal.objects.Global.print;

public class DynamiticProxyTest {
}


interface Interface {
    void doSomething();
    void somethingElse(String arg);
}

class RealObject implements Interface {
    @Override
    public void doSomething() {
        System.out.println("doSomething"); }
    @Override
    public void somethingElse(String arg) {
        System.out.println("somethingElse " + arg);
    }
}

class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;
    private static int count=0;
    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if(count<4)
        {
            count++;
            System.out.println("DynamicProxyHandler.invoke()————proxy类名获取: " +proxy.getClass());
            System.out.println("proxy.toString():"+proxy.toString());
        }
        return method.invoke(proxied, args);
    }
}

class SimpleDynamicProxy {
    public static void consumer(Interface iface) {
        iface.doSomething();
    }
    public static void main(String[] args) {
        RealObject real = new RealObject();
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[]{ Interface.class },
                new DynamicProxyHandler(real));
        System.out.println("Main()————proxy类名获取"+proxy.getClass());
        consumer(proxy);
    }
}