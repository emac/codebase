package beans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
        Assertions.assertEquals(srcPlain, targetPlain);

        // plain -> opt 类型不同则跳过
        Opt targetOpt = new Opt();
        BeanUtils.copyProperties(srcPlain, targetOpt);
        Assertions.assertFalse(targetOpt.getContent().isPresent());

        // opt -> opt
        targetOpt = new Opt();
        BeanUtils.copyProperties(srcOpt, targetOpt);
        Assertions.assertEquals(srcOpt, targetOpt);
        BeanUtils.copyProperties(srcOptEmpty, targetOpt);
        Assertions.assertEquals(srcOptEmpty, targetOpt);

        // opt -> plain 类型不同则跳过
        targetPlain = new Plain();
        BeanUtils.copyProperties(srcOpt, targetPlain);
        Assertions.assertNull(targetPlain.getContent());
        BeanUtils.copyProperties(srcOptEmpty, targetPlain);
        Assertions.assertNull(targetPlain.getContent());
    }
}

