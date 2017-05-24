package beans;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Emac
 * @since 2017-05-24
 */
public class SpringBeanUtilsTests extends BaseBeanUtilsTests {

    @Test
    public void testJava8() throws InvocationTargetException, IllegalAccessException {
        // plain -> plain
        Plain targetPlain = new Plain();
        BeanUtils.copyProperties(srcPlain, targetPlain);
        Assert.assertEquals(SAMPLE, targetPlain.getContent());

        // plain -> opt
        Opt targetOpt = new Opt();
        BeanUtils.copyProperties(srcPlain, targetOpt);
        Assert.assertFalse(targetOpt.getContent().isPresent());

        // opt -> opt
        targetOpt = new Opt();
        BeanUtils.copyProperties(srcOpt, targetOpt);
        Assert.assertTrue(targetOpt.getContent().isPresent());
        BeanUtils.copyProperties(srcOptEmpty, targetOpt);
        Assert.assertFalse(targetOpt.getContent().isPresent());

        // opt -> plain
        targetPlain = new Plain();
        BeanUtils.copyProperties(srcOpt, targetPlain);
        Assert.assertNull(targetPlain.getContent());
        BeanUtils.copyProperties(srcOptEmpty, targetPlain);
        Assert.assertNull(targetPlain.getContent());
    }
}

