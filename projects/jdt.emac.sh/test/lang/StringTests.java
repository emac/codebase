/*
 * Copyright By ZATI
 * Copyright By 3a3c88295d37870dfd3b25056092d1a9209824b256c341f2cdc296437f671617
 * All rights reserved.
 *
 * If you are not the intended user, you are hereby notified that any use, disclosure, copying, printing, forwarding or
 * dissemination of this property is strictly prohibited. If you have got this file in error, delete it from your system.
 */
package lang;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * @author Emac
 * @date 2022/1/21
 */
public class StringTests {

    @Test
    public void testReplaceAll() {
        String newUri = "http://localhost:8099/bbc-ip/ump/message/verify/checkV2".replaceAll("/bbc-ip/ump/(?<segment>.*)", "/${segment}");
        Assertions.assertEquals("http://localhost:8099/message/verify/checkV2", newUri);
    }
}
