package beans;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

/**
 * @author Emac
 * @since 2017-05-24
 */
public class ApacheBeanUtilsTests {

    @Test
    public void testJava8() throws InvocationTargetException, IllegalAccessException {
        Plain srcPlain = new Plain();
        srcPlain.setContent("hello");
        Opt srcOpt = new Opt();
        srcOpt.setContent(Optional.of("hello"));
        Opt srcOptEmpty = new Opt();
        // plain -> plain
        Plain targetPlain = new Plain();
        BeanUtils.copyProperties(targetPlain, srcPlain);

        // plain -> opt
        Opt targetOpt = new Opt();
        try {
            BeanUtils.copyProperties(targetOpt, srcPlain);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        // opt -> opt
        targetOpt = new Opt();
        BeanUtils.copyProperties(targetOpt, srcOpt);
        BeanUtils.copyProperties(targetOpt, srcOptEmpty);

        // opt -> plain
        targetPlain = new Plain();
        BeanUtils.copyProperties(targetPlain, srcOpt);
        Assert.assertEquals("Optional[hello]", targetPlain.getContent());
        BeanUtils.copyProperties(targetPlain, srcOptEmpty);
        Assert.assertEquals("Optional.empty", targetPlain.getContent());
    }
}

