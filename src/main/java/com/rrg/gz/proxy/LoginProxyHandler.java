package com.rrg.gz.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 登录的动态代理
 *
 * @author ====> huangsz
 * @date ====> 2019/7/18
 */
public class LoginProxyHandler implements InvocationHandler {

    /**
     * 目标类，也就是被代理对象
     */
    private Object target;

    public Object setTarget(Object target) {
        this.target = target;
        System.out.println(this.target.getClass().getClassLoader());
        System.out.println(this.target.getClass().getInterfaces());
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(),
                this.target.getClass().getInterfaces(), this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 这里可以做增强
        System.out.println("上车，调坐位，系安全带");
        Object result = method.invoke(target, args);
        return result;

    }

    public static void main(String[] args) {
        CarImpl carServer = new CarImpl();
        LoginProxyHandler proxyHandler = new LoginProxyHandler();
        CarServer o = (CarServer) proxyHandler.setTarget(carServer);
        o.start();
    }
}
