/*
 * Created on 2011-5-13 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package lang;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author bishen
 */
public class RuntimeTests
{

    @Test
    public void testExecDirWithRedirect()
            throws IOException, InterruptedException
    {
        Process p = Runtime.getRuntime().exec(new String[]
        { "cmd", "/c", "dir", ">/temp/dir.txt"});
        int exitValue = p.waitFor();

        Assert.assertEquals(exitValue, 0);
    }

    @Test
    public void testExecJavaWithRedirect()
            throws IOException, InterruptedException
    {
        PrintWriter writer = null;
        BufferedReader reader = null;
        try
        {
            String javaHome = System.getProperty("java.home");
            Process p = Runtime.getRuntime().exec(new String[]
            { javaHome + "/bin/java", "-versions"});
            writer = new PrintWriter(new FileWriter("/temp/version.txt"));
            reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line;
            while ((line = reader.readLine()) != null)
            {
                writer.println(line);
            }
            int exitValue = p.waitFor();

            Assert.assertTrue(exitValue != 0);
        }
        finally
        {
            if ( reader != null )
            {
                reader.close();
            }
            if ( writer != null )
            {
                writer.flush();
                writer.close();
            }
        }
    }

    @Test
    public void testSafeExec()
            throws IOException, InterruptedException
    {
        String javaHome = System.getProperty("java.home");
        int exitValue = RuntimeUtils.exec(new String[]
        { javaHome + "/../bin/javac"});

        Assert.assertTrue(exitValue != 0);
    }

    @Test
    public void testExecBindex()
            throws IOException, InterruptedException
    {
        String javaHome = System.getProperty("java.home");
        String rideHome = "c:\\gep\\ride";

        String[] cmds = new String[]
        { javaHome + "/bin/java", "-jar", rideHome + "\\bindex-2.2-patch.jar", "-i", "-quiet", rideHome + "\\raptorobr",
                "-r", rideHome + "\\repository.xml"};
        int exitValue = RuntimeUtils.exec(cmds);

        Assert.assertTrue(exitValue == 0);
    }

}
