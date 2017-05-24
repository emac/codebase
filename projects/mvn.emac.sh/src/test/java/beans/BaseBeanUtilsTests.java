package beans;

import org.junit.Before;

import java.util.Optional;

/**
 * @author Emac
 * @since 2017-05-24
 */
public class BaseBeanUtilsTests {

    protected Plain srcPlain;

    protected Opt srcOpt;

    protected Opt srcOptEmpty;

    @Before
    public void before() {
        srcPlain = new Plain();
        srcPlain.setContent("hello");
        srcOpt = new Opt();
        srcOpt.setContent(Optional.of("hello"));
        srcOptEmpty = new Opt();
    }
}
