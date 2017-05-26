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
    public void testJava8Optional() throws InvocationTargetException, IllegalAccessException {
        // plain -> plain
        PlainBean targetPlainBean = new PlainBean();
        Beans.copyProperties(srcPlainBean, targetPlainBean);
        Assertions.assertEquals(srcPlainBean, targetPlainBean);

        // plain -> opt 自动装包
        OptBean targetOptBean = new OptBean();
        Beans.copyProperties(srcPlainBean, targetOptBean);
        Assertions.assertTrue(targetOptBean.getContent().isPresent());

        // opt -> opt
        targetOptBean = new OptBean();
        Beans.copyProperties(srcOptBean, targetOptBean);
        Assertions.assertEquals(srcOptBean, targetOptBean);
        Beans.copyProperties(srcOptBeanEmpty, targetOptBean);
        Assertions.assertEquals(srcOptBeanEmpty, targetOptBean);

        // opt -> plain 自动拆包
        targetPlainBean = new PlainBean();
        Beans.copyProperties(srcOptBean, targetPlainBean);
        Assertions.assertEquals(srcOptBean.getContent().get(), targetPlainBean.getContent());
        Beans.copyProperties(srcOptBeanEmpty, targetPlainBean);
        Assertions.assertNull(targetPlainBean.getContent());
    }
}

