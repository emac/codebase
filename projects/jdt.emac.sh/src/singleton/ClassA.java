/*
 * Created on Sep 8, 2010 Copyright (c) eBay, Inc. 2010 All rights reserved.
 */

package singleton;

/**
 * @author bishen
 */
public class ClassA extends ClassB {

    static {
        System.out.println("[ClassA] Static block - 1");
    }

    private static final ClassA instance = new ClassA();

    static {
        System.out.println(instance);
        System.out.println("[ClassA] Static block - 2");
    }

    {
        System.out.println(instance);
        System.out.println("[ClassA] Non-static block");
    }

    private ClassA() {
        System.out.println("[ClassA] Constructor");
    }

    public static ClassA getInstance() {
        System.out.println("[ClassA] getInstance()");
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(ClassA.class);
        ClassA.getInstance();
    }

}
