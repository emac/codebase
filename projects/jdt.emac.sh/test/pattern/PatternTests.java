/*
 * Created on Aug 19, 2010 Copyright (c) eBay, Inc. 2010 All rights reserved.
 */

package pattern;


import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author bishen
 */
public class PatternTests {

    private static final Pattern PATTERN_EBOX_RELEASE_LABEL = Pattern
            .compile("(EBOX_(S14_)?)(\\d{1,})_(Q[1-4])(.\\d{1,})?");

    private static final Pattern PATTERN_BEOL = Pattern.compile("^\\.(jpg|jpeg|png|gif)$");

    @Test
    public void testGroup() {
        Matcher matcher = PATTERN_EBOX_RELEASE_LABEL.matcher("EBOX_2010_Q2.1");
        assertTrue(matcher.matches());
        assertTrue(".1".equals(matcher.group(5)));

        matcher = PATTERN_EBOX_RELEASE_LABEL.matcher("EBOX_2010_Q2");
        assertTrue(matcher.matches());
        assertNull(matcher.group(5));
    }

    @Test
    public void testBEOL() {
        Matcher matcher = PATTERN_BEOL.matcher(".jpg");
        assertTrue(matcher.matches());

        matcher = PATTERN_BEOL.matcher("1.jpg");
        assertFalse(matcher.matches());

        matcher = PATTERN_BEOL.matcher(".jpg ");
        assertFalse(matcher.matches());
    }
}
