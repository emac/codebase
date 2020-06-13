/*
 * Created on 2011-10-28 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package io;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author bishen
 */
public class FileTests {

    @Test
    public void testReadHttpFile()
            throws IOException {
        File httpFile = new File("http://maven.apache.org/maven-v4_0_0.xsd");
        assertThrows(FileNotFoundException.class, () -> new FileInputStream(httpFile).close());
    }
}
