package beans;

import beans.v2.Beans;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Emac
 * @since 2017-05-24
 */
public class BeansV2Tests extends BaseBeanUtilsTests {

    @Test
    public void testJava8() throws InvocationTargetException, IllegalAccessException {
        // plain -> plain
        Plain targetPlain = new Plain();
        Beans.copyProperties(srcPlain, targetPlain);
        Assertions.assertEquals(srcPlain, targetPlain);

        // plain -> opt 自动装包
        Opt targetOpt = new Opt();
        Beans.copyProperties(srcPlain, targetOpt);
        Assertions.assertTrue(targetOpt.getContent().isPresent());

        // opt -> opt
        targetOpt = new Opt();
        Beans.copyProperties(srcOpt, targetOpt);
        Assertions.assertEquals(srcOpt, targetOpt);
        Beans.copyProperties(srcOptEmpty, targetOpt);
        Assertions.assertEquals(srcOptEmpty, targetOpt);

        // opt -> plain 自动拆包
        targetPlain = new Plain();
        Beans.copyProperties(srcOpt, targetPlain);
        Assertions.assertEquals(srcOpt.getContent().get(), targetPlain.getContent());
        Beans.copyProperties(srcOptEmpty, targetPlain);
        Assertions.assertNull(targetPlain.getContent());
    }
}

