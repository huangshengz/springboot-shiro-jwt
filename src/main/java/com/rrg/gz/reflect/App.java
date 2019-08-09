package com.rrg.gz.reflect;

import com.rrg.gz.utils.ConstantUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射
 *
 * @author ====> huangsz
 * @date ====> 2019/7/19
 */
public class App {

    public static void main(String[] args) {
        System.out.println(ConstantUtil.OrderStatus.STATUS_FORE.getKey());
        // 3、根据类全名
        try {
            Class aClass = Class.forName("com.rrg.gz.reflect.Person");
            // 实例化一个无参构造函数
            aClass.getConstructor().newInstance();
            // 有参的
            aClass.getConstructor(String.class).newInstance("王二小");
            // 获取一个实例
            Person p = (Person) aClass.newInstance();
            // 第一个参数方法命，第二个参数是方法的参数类型的class
            Method method = aClass.getMethod("test", null);
            method.invoke(p, null);
            Method method1 = aClass.getMethod("test1", String.class);
            method1.invoke(p, "张三");
            // 反射获取字段
            Field field = aClass.getDeclaredField("name");
            // 暴力获取私有字段，方法类似
            field.setAccessible(true);
            String name = (String) field.get(p);
            System.out.println("name = " + name);
            field.set(p, "王二小");
            System.out.println(p.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
