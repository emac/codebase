package beans;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Emac
 * @since 2017-05-24
 */
public class ApacheBeanUtilsTests extends BaseBeanUtilsTests {

    @Test
    public void testJava8Optional() throws InvocationTargetException, IllegalAccessException {
        // plain -> plain
        PlainBean targetPlainBean = new PlainBean();
        BeanUtils.copyProperties(targetPlainBean, srcPlainBean);
        Assertions.assertEquals(srcPlainBean, targetPlainBean);

        // plain -> opt 类型不同则抛错
        OptBean targetOptBean = new OptBean();
        try {
            BeanUtils.copyProperties(targetOptBean, srcPlainBean);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof IllegalArgumentException);
        }

        // opt -> opt
        targetOptBean = new OptBean();
        BeanUtils.copyProperties(targetOptBean, srcOptBean);
        Assertions.assertEquals(srcOptBean, targetOptBean);
        BeanUtils.copyProperties(targetOptBean, srcOptBeanEmpty);
        Assertions.assertEquals(srcOptBeanEmpty, targetOptBean);

        // opt -> plain 类型不同则抛错，但如果目标属性是String，则调用源属性的toString方法
        targetPlainBean = new PlainBean();
        BeanUtils.copyProperties(targetPlainBean, srcOptBean);
        Assertions.assertEquals("Optional[hello]", targetPlainBean.getContent());
        BeanUtils.copyProperties(targetPlainBean, srcOptBeanEmpty);
        Assertions.assertEquals("Optional.empty", targetPlainBean.getContent());
    }
}

