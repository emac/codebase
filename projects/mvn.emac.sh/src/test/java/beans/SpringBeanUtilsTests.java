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
    public void testJava8Optional() throws InvocationTargetException, IllegalAccessException {
        // plain -> plain
        PlainBean targetPlainBean = new PlainBean();
        BeanUtils.copyProperties(srcPlainBean, targetPlainBean);
        Assertions.assertEquals(srcPlainBean, targetPlainBean);

        // plain -> opt 类型不同则跳过
        OptBean targetOptBean = new OptBean();
        BeanUtils.copyProperties(srcPlainBean, targetOptBean);
        Assertions.assertFalse(targetOptBean.getContent().isPresent());

        // opt -> opt
        targetOptBean = new OptBean();
        BeanUtils.copyProperties(srcOptBean, targetOptBean);
        Assertions.assertEquals(srcOptBean, targetOptBean);
        BeanUtils.copyProperties(srcOptBeanEmpty, targetOptBean);
        Assertions.assertEquals(srcOptBeanEmpty, targetOptBean);

        // opt -> plain 类型不同则跳过
        targetPlainBean = new PlainBean();
        BeanUtils.copyProperties(srcOptBean, targetPlainBean);
        Assertions.assertNull(targetPlainBean.getContent());
        BeanUtils.copyProperties(srcOptBeanEmpty, targetPlainBean);
        Assertions.assertNull(targetPlainBean.getContent());
    }
}

