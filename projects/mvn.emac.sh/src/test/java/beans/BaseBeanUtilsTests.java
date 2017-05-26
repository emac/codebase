package beans;

import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

/**
 * @author Emac
 * @since 2017-05-24
 */
public class BaseBeanUtilsTests {

    protected PlainBean srcPlainBean;

    protected OptBean srcOptBean;

    protected OptBean srcOptBeanEmpty;

    protected PlainPojo srcPlainPojo;

    protected OptPojo srcOptPojo;

    protected OptPojo srcOptPojoEmpty;

    @BeforeEach
    public void before() {
        srcPlainBean = new PlainBean();
        srcPlainBean.setContent("hello");
        srcOptBean = new OptBean();
        srcOptBean.setContent(Optional.of("hello"));
        srcOptBeanEmpty = new OptBean();

        srcPlainPojo = new PlainPojo();
        srcPlainPojo.setContent("hello");
        srcOptPojo = new OptPojo();
        srcOptPojo.setContent(Optional.of("hello"));
        srcOptPojoEmpty = new OptPojo();
    }
}
