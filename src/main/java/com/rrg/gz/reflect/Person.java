package com.rrg.gz.reflect;

/**
 * @author ====> huangsz
 * @date ====> 2019/7/19
 */
public class Person {

    private String name = "王二";

    public Person() {
        System.out.println("无参构造");
    }

    public Person(String name) {
        System.out.println("name=" + name);
    }

    public void test() {
        System.out.println("无参方法1");
    }

    public void test1(String name) {
        System.out.println("有参方法1：" + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
