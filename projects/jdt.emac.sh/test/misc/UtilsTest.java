/*
 * Created on 2011-12-9 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package misc;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author bishen
 */
public class UtilsTest
{

    @SuppressWarnings("static-access")
	@Test
    public void testGreet()
    {
        Assert.assertTrue(UtilsB.greet().endsWith(UtilsB.class.getName()));

        UtilsA utils = new UtilsB();
        Assert.assertTrue(utils.greet().endsWith(UtilsA.class.getName()));
    }

}
