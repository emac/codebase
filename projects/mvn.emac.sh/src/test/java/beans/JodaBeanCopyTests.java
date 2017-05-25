package beans;

import jodd.bean.BeanCopy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals(srcPlain, targetPlain);

        // plain -> opt 类型不同则跳过
        Opt targetOpt = new Opt();
        BeanCopy.beans(srcPlain, targetOpt).copy();
        Assertions.assertFalse(targetOpt.getContent().isPresent());

        // opt -> opt
        targetOpt = new Opt();
        BeanCopy.beans(srcOpt, targetOpt).copy();
        Assertions.assertEquals(srcOpt, targetOpt);
        BeanCopy.beans(srcOptEmpty, targetOpt).copy();
        Assertions.assertEquals(srcOptEmpty, targetOpt);

        // opt -> plain 类型不同则跳过，但如果目标属性是String，则调用源属性的toString方法
        targetPlain = new Plain();
        BeanCopy.beans(srcOpt, targetPlain).copy();
        Assertions.assertEquals("Optional[hello]", targetPlain.getContent());
        BeanCopy.beans(srcOptEmpty, targetPlain).copy();
        Assertions.assertEquals("Optional.empty", targetPlain.getContent());
    }
}
