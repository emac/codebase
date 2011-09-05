/*
 * Created on Feb 28, 2011 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package properties;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

/**
 * @author bishen
 */
public class PropertiesTest
{

    @Test(expected = IllegalArgumentException.class)
    public void testParse()
            throws IOException
    {
        InputStream is = getClass().getResourceAsStream("sample.properties");
        Properties props = new Properties();
        props.load(is);

        assertTrue(props.size() == 26);
    }

}
