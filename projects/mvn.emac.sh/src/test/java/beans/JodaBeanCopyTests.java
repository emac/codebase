package beans;

import jodd.bean.BeanCopy;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Emac
 * @since 2017-05-24
 */
public class JodaBeanCopyTests extends BaseBeanUtilsTests {

    @Test
    public void testJava8() throws InvocationTargetException, IllegalAccessException {
        // plain -> plain
        Plain targetPlain = new Plain();
        BeanCopy.beans(srcPlain, targetPlain).copy();
        Assert.assertEquals(srcPlain.getContent(), targetPlain.getContent());

        // plain -> opt 类型不同则跳过
        Opt targetOpt = new Opt();
        BeanCopy.beans(srcPlain, targetOpt).copy();
        Assert.assertFalse(targetOpt.getContent().isPresent());

        // opt -> opt
        targetOpt = new Opt();
        BeanCopy.beans(srcOpt, targetOpt).copy();
        Assert.assertEquals(srcOpt.getContent(), targetOpt.getContent());
        BeanCopy.beans(srcOptEmpty, targetOpt).copy();
        Assert.assertEquals(srcOptEmpty.getContent(), targetOpt.getContent());

        // opt -> plain 类型不同则跳过，但如果目标属性是String，则调用源属性的toString方法
        targetPlain = new Plain();
        BeanCopy.beans(srcOpt, targetPlain).copy();
        Assert.assertEquals("Optional[hello]", targetPlain.getContent());
        BeanCopy.beans(srcOptEmpty, targetPlain).copy();
        Assert.assertEquals("Optional.empty", targetPlain.getContent());
    }
}
