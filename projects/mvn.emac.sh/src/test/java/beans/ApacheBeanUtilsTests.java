package beans;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Emac
 * @since 2017-05-24
 */
public class ApacheBeanUtilsTests extends BaseBeanUtilsTests {

    @Test
    public void testJava8() throws InvocationTargetException, IllegalAccessException {
        // plain -> plain
        Plain targetPlain = new Plain();
        BeanUtils.copyProperties(targetPlain, srcPlain);
        Assert.assertEquals(SAMPLE, targetPlain.getContent());

        // plain -> opt 类型不同则抛错
        Opt targetOpt = new Opt();
        try {
            BeanUtils.copyProperties(targetOpt, srcPlain);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        // opt -> opt
        targetOpt = new Opt();
        BeanUtils.copyProperties(targetOpt, srcOpt);
        Assert.assertTrue(targetOpt.getContent().isPresent());
        BeanUtils.copyProperties(targetOpt, srcOptEmpty);
        Assert.assertFalse(targetOpt.getContent().isPresent());

        // opt -> plain 类型不同则抛错，但如果目标属性是String，则调用源属性的toString方法
        targetPlain = new Plain();
        BeanUtils.copyProperties(targetPlain, srcOpt);
        Assert.assertEquals("Optional[hello]", targetPlain.getContent());
        BeanUtils.copyProperties(targetPlain, srcOptEmpty);
        Assert.assertEquals("Optional.empty", targetPlain.getContent());
    }
}

