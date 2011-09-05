/*
 * Created on Jan 11, 2011 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package enumeration;

import enumeration.EnumA.Lengthable;

/**
 * @author bishen
 */
public enum EnumB implements Lengthable
{

    A("Apple")
    {
        @Override
        public int getLength()
        {
            return A.name.length();
        }
    },
    B("Boy")
    {
        @Override
        public int getLength()
        {
            return B.name.length();
        }
    };

    private String name;

    private EnumB(String name)
    {
        this.name = name;
    }

    public static void main(String[] args)
    {
        printLength(EnumB.class);
    }

    // composite generic usage
    private static <T extends Enum<T> & Lengthable> void printLength(Class<T> lens)
    {
        for (Lengthable l : lens.getEnumConstants())
        {
            System.out.println(l.getLength());
        }
    }

}
