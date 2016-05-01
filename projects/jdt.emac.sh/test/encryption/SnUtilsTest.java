package encryption;

import org.junit.Assert;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.stream.IntStream;

/**
 * @author Emac
 * @since 2016-03-26
 */
public class SnUtilsTest {

    @Test
    public void testEncrpt() {
        HashSet<Integer> nums = new HashSet<>();
        IntStream.range(1, 8192).forEach(seq -> {
            int cipher = SnUtils.encrypt(seq, (byte) 12);
            System.out.println(MessageFormat.format("{0} -> {1}", seq, cipher));
            Assert.assertTrue(nums.add(cipher));
            Assert.assertEquals(seq, SnUtils.decrypt(cipher, (byte) 12));
        });
    }
}
