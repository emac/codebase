package jdk.j8;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author Emac
 * @since 2015-11-29
 */
public class MethodTests {

    @Test
    public void test() {
        Arrays.asList(1, 2, 3).forEach(System.out::println);
    }
}
