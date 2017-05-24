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
        Assert.assertEquals(srcPlain.getContent(), targetPlain.getContent());

        // plain -> opt 类型不同则跳过
        Opt targetOpt = new Opt();
        BeanUtils.copyProperties(srcPlain, targetOpt);
        Assert.assertFalse(targetOpt.getContent().isPresent());

        // opt -> opt
        targetOpt = new Opt();
        BeanUtils.copyProperties(srcOpt, targetOpt);
        Assert.assertEquals(srcOpt.getContent(), targetOpt.getContent());
        BeanUtils.copyProperties(srcOptEmpty, targetOpt);
        Assert.assertEquals(srcOptEmpty.getContent(), targetOpt.getContent());

        // opt -> plain 类型不同则跳过
        targetPlain = new Plain();
        BeanUtils.copyProperties(srcOpt, targetPlain);
        Assert.assertNull(targetPlain.getContent());
        BeanUtils.copyProperties(srcOptEmpty, targetPlain);
        Assert.assertNull(targetPlain.getContent());
    }
}

