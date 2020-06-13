package encryption;


import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Emac
 * @since 2016-03-26
 */
public class SnUtilsTests {

    @Test
    public void testEncrpt() {
        HashSet<Integer> nums = new HashSet<>();
        IntStream.range(1, 8192).forEach(seq -> {
            int cipher = SnUtils.encrypt(seq, (byte) 12);
            System.out.println(MessageFormat.format("{0} -> {1}", seq, cipher));
            assertTrue(nums.add(cipher));
            assertEquals(seq, SnUtils.decrypt(cipher, (byte) 12));
        });
    }
}
