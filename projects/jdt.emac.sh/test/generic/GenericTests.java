/*
 * Created on Sep 27, 2010 Copyright (c) eBay, Inc. 2010 All rights reserved.
 */

package generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * @author bishen
 */
public class GenericTests {

    @Test
    public void testGetProxy() {
        GenericA genericA = new GenericA();
        assertThrows(ClassCastException.class, () -> {
            String proxy = genericA.getProxy();
            System.out.println(proxy);
        });
    }
}
