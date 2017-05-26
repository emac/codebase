package beans.v1;

import beans.converters.OptionalConverter;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ContextClassLoaderLocal;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

/**
 * 扩展org.apache.commons.beanutils.BeanUtils支持{@code Optional}类型和非{@code Optional}类型的属性复制。
 *
 * @author Emac
 * @since 2017-05-24
 */
public class Beans {

    private static final ContextClassLoaderLocal<BeanUtilsBean> BEANS_BY_CLASSLOADER = new ContextClassLoaderLocal<BeanUtilsBean>() {
        // Creates the default instance used when the context classloader is unavailable
        @Override
        protected BeanUtilsBean initialValue() {
            BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
            // 注册Optional Converter
            beanUtilsBean.getConvertUtils().register(new OptionalConverter(), Optional.class);
            // 替换默认的String Converter
            beanUtilsBean.getConvertUtils().register(new OptionalStringConverter(), String.class);
            return beanUtilsBean;
        }
    };

    /**
     * @see org.apache.commons.beanutils.BeanUtils#copyProperties
     */
    public static void copyProperties(final Object orig, final Object dest)
            throws IllegalAccessException, InvocationTargetException {
        BEANS_BY_CLASSLOADER.get().copyProperties(dest, orig);
    }

    /**
     * @see org.apache.commons.beanutils.BeanUtils#copyProperty
     */
    public static void copyProperty(final Object bean, final String name, final Object value)
            throws IllegalAccessException, InvocationTargetException {
        BEANS_BY_CLASSLOADER.get().copyProperty(bean, name, value);
    }
}
