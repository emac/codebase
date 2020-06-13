/*
 * Created on Feb 28, 2011 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package properties;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author bishen
 */
public class PropertiesTest {

    @Test
    public void testParse()
            throws IOException {
        InputStream is = getClass().getResourceAsStream("sample.properties");
        Properties props = new Properties();
        assertThrows(IllegalArgumentException.class, () -> props.load(is));
    }
}
