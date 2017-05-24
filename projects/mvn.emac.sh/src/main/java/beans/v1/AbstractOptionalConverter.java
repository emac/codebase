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
public abstract class AbstractOptionalConverter extends AbstractConverter {

    public AbstractOptionalConverter() {
        super();
    }

    public AbstractOptionalConverter(Object defaultValue) {
        super(defaultValue);
    }

    /**
     * Convert the specified input object of {@code Optional} type into an output object of the
     * specified type.
     */
    protected <T> T convertOptionalToType(final Class<T> type, final Optional value) throws Throwable {
        return value.isPresent() ? convertToType(type, value.get()) : null;
    }

    @Override
    protected String convertToString(Object value) throws Throwable {
        if (value instanceof Optional) {
            Optional optValue = (Optional) value;
            return optValue.isPresent() ? convertToString(optValue.get()) : null;
        }
        return super.convertToString(value);
    }
}
