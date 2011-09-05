/*
 * Created on Sep 27, 2010 Copyright (c) eBay, Inc. 2010 All rights reserved.
 */

package generic;

import generic.GenericA;

import org.junit.Test;


/**
 * @author bishen
 */
public class GenericTest
{

    @Test(expected = ClassCastException.class)
    public void testGetProxy()
    {
        GenericA t = new GenericA();
        String proxy = t.getProxy();
        System.out.println(proxy);
    }

}
