/*
 * Created on 2012-7-12 Copyright (c) eBay, Inc. 2012 All rights reserved.
 */

package net;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;

/**
 * @author bishen
 */
public class URLTest
{

    @Test
    public void testURL2URINormal()
            throws MalformedURLException, URISyntaxException
    {
        URL url = new URL("file:/temp");
        url.toURI();
    }

    @Test(expected = URISyntaxException.class)
    public void testURL2URIBlankFail()
            throws MalformedURLException, URISyntaxException
    {
        URL url = new URL("file:/t e m p");
        url.toURI();
    }

    @Test
    public void testURL2URIBlank()
            throws MalformedURLException, URISyntaxException
    {
        URL url = new URL("file:/t e m p");
        new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(),
                url.getRef());
    }

}
