/*
 * Created on Sep 8, 2010 Copyright (c) eBay, Inc. 2010 All rights reserved.
 */

package singleton;

/**
 * @author bishen
 */
public class ClassB
{

    static
    {
        System.out.println("[ClassB] Static block - 1");
    }
    
    private static final ClassB instance = new ClassB();

    static
    {
        System.out.println(instance);
        System.out.println("[ClassB] Static block - 2");
    }

    {
        System.out.println(instance);
        System.out.println("[ClassB] Non-static block");
    }

    protected ClassB()
    {
        System.out.println("[ClassB] Constructor");
    }

    public static ClassB getInstance()
    {
        System.out.println("[ClassB] getInstance()");
        return instance;
    }

    public static void main(String[] args)
    {
        System.out.println(ClassB.class);
        ClassB.getInstance();
    }

}
