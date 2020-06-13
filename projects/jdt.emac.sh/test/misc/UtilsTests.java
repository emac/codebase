/*
 * Created on 2011-12-9 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package misc;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author bishen
 */
public class UtilsTests {

    @SuppressWarnings("static-access")
    @Test
    public void testGreet() {
        assertTrue(UtilsB.greet().endsWith(UtilsB.class.getName()));

        UtilsA utils = new UtilsB();
        assertTrue(utils.greet().endsWith(UtilsA.class.getName()));
    }
}
