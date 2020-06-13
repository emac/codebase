/*
 * Created on 2012-7-12 Copyright (c) eBay, Inc. 2012 All rights reserved.
 */

package net;


import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author bishen
 */
public class URLTests {

    @Test
    public void testURL2URINormal()
            throws MalformedURLException, URISyntaxException {
        URL url = new URL("file:/temp");
        url.toURI();
    }

    @Test
    public void testURL2URIBlankFail()
            throws MalformedURLException, URISyntaxException {
        URL url = new URL("file:/t e m p");
        assertThrows(URISyntaxException.class, () -> url.toURI());
    }

    @Test
    public void testURL2URIBlank()
            throws MalformedURLException, URISyntaxException {
        URL url = new URL("file:/t e m p");
        new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(),
                url.getRef());
    }
}
