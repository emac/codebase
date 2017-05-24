package beans;

import beans.v1.Beans;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Emac
 * @since 2017-05-24
 */
public class BeansV1Tests extends BaseBeanUtilsTests {

    @Test
    public void testJava8() throws InvocationTargetException, IllegalAccessException {
        // plain -> plain
        Plain targetPlain = new Plain();
        Beans.copyProperties(srcPlain, targetPlain);
        Assertions.assertEquals(srcPlain.getContent(), targetPlain.getContent());

        // plain -> opt 类型不同则抛错
        Opt targetOpt = new Opt();
        Beans.copyProperties(srcPlain, targetOpt);
        Assertions.assertTrue(targetOpt.getContent().isPresent());

        // opt -> opt
        targetOpt = new Opt();
        Beans.copyProperties(srcOpt, targetOpt);
        Assertions.assertEquals(srcOpt.getContent(), targetOpt.getContent());
        Beans.copyProperties(srcOptEmpty, targetOpt);
        Assertions.assertEquals(srcOptEmpty.getContent(), targetOpt.getContent());

        // opt -> plain 类型不同则抛错，但如果目标属性是String，则调用源属性的toString方法
        targetPlain = new Plain();
        Beans.copyProperties(srcOpt, targetPlain);
        Assertions.assertEquals(srcOpt.getContent().get(), targetPlain.getContent());
        Beans.copyProperties(srcOptEmpty, targetPlain);
        Assertions.assertNull(targetPlain.getContent());
    }
}

