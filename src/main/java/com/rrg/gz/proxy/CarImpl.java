package com.rrg.gz.proxy;

/**
 * @author ====> huangsz
 * @date ====> 2019/7/18
 */
public class CarImpl implements CarServer {

    @Override
    public void start() {
        System.out.println("轰轰轰轰，汽车启动了");
    }

    @Override
    public void run() {
        System.out.println("挂一档，跑啦");
    }
}
