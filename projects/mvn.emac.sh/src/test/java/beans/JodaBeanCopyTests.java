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
    public void testJava8Optional() throws InvocationTargetException, IllegalAccessException {
        // plain -> plain
        PlainBean targetPlainBean = new PlainBean();
        BeanCopy.beans(srcPlainBean, targetPlainBean).copy();
        Assertions.assertEquals(srcPlainBean, targetPlainBean);

        // plain -> opt 类型不同则跳过
        OptBean targetOptBean = new OptBean();
        BeanCopy.beans(srcPlainBean, targetOptBean).copy();
        Assertions.assertFalse(targetOptBean.getContent().isPresent());

        // opt -> opt
        targetOptBean = new OptBean();
        BeanCopy.beans(srcOptBean, targetOptBean).copy();
        Assertions.assertEquals(srcOptBean, targetOptBean);
        BeanCopy.beans(srcOptBeanEmpty, targetOptBean).copy();
        Assertions.assertEquals(srcOptBeanEmpty, targetOptBean);

        // opt -> plain 类型不同则跳过，但如果目标属性是String，则调用源属性的toString方法
        targetPlainBean = new PlainBean();
        BeanCopy.beans(srcOptBean, targetPlainBean).copy();
        Assertions.assertEquals("Optional[hello]", targetPlainBean.getContent());
        BeanCopy.beans(srcOptBeanEmpty, targetPlainBean).copy();
        Assertions.assertEquals("Optional.empty", targetPlainBean.getContent());
    }
}
