/*
 * Created on 2011-10-28 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

/**
 * @author bishen
 */
public class FileTests
{

    @Test(expected = FileNotFoundException.class)
    public void testReadHttpFile()
            throws IOException
    {
        File httpFile = new File("http://maven.apache.org/maven-v4_0_0.xsd");
        new FileInputStream(httpFile).close();;
    }

}
