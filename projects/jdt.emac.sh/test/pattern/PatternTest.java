/*
 * Created on Aug 19, 2010 Copyright (c) eBay, Inc. 2010 All rights reserved.
 */

package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author bishen
 */
public class PatternTest
{

    private static final Pattern PATTERN_EBOX_RELEASE_LABEL = Pattern
                                                                    .compile("(EBOX_(S14_)?)(\\d{1,})_(Q[1-4])(.\\d{1,})?");

    private static final Pattern PATTERN_BEOL               = Pattern.compile("^\\.(jpg|jpeg|png|gif)$");

    @Test
    public void testGroup()
    {
        Matcher matcher = PATTERN_EBOX_RELEASE_LABEL.matcher("EBOX_2010_Q2.1");
        Assert.assertTrue(matcher.matches());
        Assert.assertTrue(".1".equals(matcher.group(5)));

        matcher = PATTERN_EBOX_RELEASE_LABEL.matcher("EBOX_2010_Q2");
        Assert.assertTrue(matcher.matches());
        Assert.assertNull(matcher.group(5));
    }

    @Test
    public void testBEOL()
    {
        Matcher matcher = PATTERN_BEOL.matcher(".jpg");
        Assert.assertTrue(matcher.matches());

        matcher = PATTERN_BEOL.matcher("1.jpg");
        Assert.assertFalse(matcher.matches());

        matcher = PATTERN_BEOL.matcher(".jpg ");
        Assert.assertFalse(matcher.matches());
    }

}
