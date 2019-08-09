package com.rrg.gz.proxy;

/**
 * 静态代理
 *
 * @author ====> huangsz
 * @date ====> 2019/7/18
 */
public class StaticProxy implements CarServer {

    private CarServer carServer;

    public void setCarServer(CarServer carServer) {
        this.carServer = carServer;
    }

    @Override
    public void start() {
        System.out.println("上车，调坐位，系安全带");
        carServer.start();
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        StaticProxy staticProxy = new StaticProxy();
        CarServer c = new CarImpl();
        staticProxy.setCarServer(c);
        staticProxy.start();
    }
}
