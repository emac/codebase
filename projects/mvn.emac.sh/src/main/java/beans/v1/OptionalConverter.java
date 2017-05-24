package beans.v1;

import org.apache.commons.beanutils.converters.AbstractConverter;

import java.util.Optional;

/**
 * 添加{@code Optional}类型的属性值转换。转换规则：
 * <ul>
 * <li>如果不为空，则先取出{@code Optional}对象中的值，然后调用{@code convertToType}进行转换</li>
 * <li>如果为空，则返回{@code null}</li>
 * </ul>
 *
 * @author Emac
 * @since 2017-05-24
 */
public class OptionalConverter extends AbstractConverter {

    public OptionalConverter() {
        super();
    }

    public OptionalConverter(Object defaultValue) {
        super(defaultValue);
    }

    @Override
    protected Class<?> getDefaultType() {
        return Optional.class;
    }

    @Override
    protected <T> T convertToType(Class<T> type, Object value) throws Throwable {
        if (Optional.class.equals(type)) {
            return type.cast(Optional.ofNullable(value));
        }

        throw conversionException(type, value);
    }
}
