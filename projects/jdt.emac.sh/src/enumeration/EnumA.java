/*
 * Created on Jan 11, 2011 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package enumeration;

/**
 * @author bishen
 */
public enum EnumA
{

    A("A")
    {
        @Override
        public int getLength()
        {
            System.out.println(nonfinalLock);
            System.out.println(finalLock);
            System.out.println(nonfinalId);

            return A.name.length();
        }
    },
    B("B")
    {
        @Override
        public int getLength()
        {
            return B.name.length();
        }
    };

    static
    {
        System.out.println("Static Block");
    }

    static Object       nonfinalLock = new Object();
    static final Object finalLock    = new Object();
    static int          nonfinalId   = 1;
    static final int    finalId      = 1;

    private String      name;

    private EnumA(String name)
    {
        this.name = name;

        // Error
        // System.out.println(nonfinalLock);
        // Error
        // System.out.println(finalLock);
        // Error
        // System.out.println(nonfinalId);

        /** The constructor method of an enum can only access static final constants */
        System.out.println(finalId);

        System.out.println("Constructing " + name);
    }

    public abstract int getLength();

    /** The constructor method of an enum is always private */
    /*
     * Error public EnumA(){ }
     */

    public static void main(String[] args)
    {
        for (EnumA e : EnumA.values())
        {
            System.out.println(e);
        }
    }

    public interface Lengthable
    {
        int getLength();
    }

}
