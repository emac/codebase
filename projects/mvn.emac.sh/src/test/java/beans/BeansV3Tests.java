package beans;

import beans.v3.Beans;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Emac
 * @since 2017-05-26
 */
public class BeansV3Tests extends BaseBeanUtilsTests {

    @Test
    public void testJava8OptionalBean() throws InvocationTargetException, IllegalAccessException {
        // plain -> plain
        PlainBean targetPlainBean = new PlainBean();
        Beans.copyProperties(srcPlainBean, targetPlainBean);
        Assertions.assertEquals(srcPlainBean, targetPlainBean);

        // plain -> opt
        OptBean targetOptBean = new OptBean();
        Beans.copyProperties(srcPlainBean, targetOptBean);
        Assertions.assertTrue(targetOptBean.getContent().isPresent());

        // opt -> opt
        targetOptBean = new OptBean();
        Beans.copyProperties(srcOptBean, targetOptBean);
        Assertions.assertEquals(srcOptBean, targetOptBean);
        Beans.copyProperties(srcOptBeanEmpty, targetOptBean);
        Assertions.assertEquals(srcOptBeanEmpty, targetOptBean);

        // opt -> plain
        targetPlainBean = new PlainBean();
        Beans.copyProperties(srcOptBean, targetPlainBean);
        Assertions.assertEquals(srcOptBean.getContent().get(), targetPlainBean.getContent());
        Beans.copyProperties(srcOptBeanEmpty, targetPlainBean);
        Assertions.assertNull(targetPlainBean.getContent());
    }

    @Test
    public void testJava8OptionalPojo() throws InvocationTargetException, IllegalAccessException {
        // plain -> opt
        OptPojo targetOptPojo = new OptPojo();
        Beans.copyProperties(srcPlainPojo, targetOptPojo);
        Assertions.assertTrue(targetOptPojo.getContent().isPresent());

        // opt -> plain
        PlainPojo targetPlainPojo = new PlainPojo();
        Beans.copyProperties(srcOptPojo, targetPlainPojo);
        Assertions.assertEquals(srcOptPojo.getContent().get(), targetPlainPojo.getContent());
        Beans.copyProperties(srcOptPojoEmpty, targetPlainPojo);
        Assertions.assertNull(targetPlainPojo.getContent());
    }
}

