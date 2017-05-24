package beans.v1;

import java.util.Optional;

/**
 * @author Emac
 * @since 2017-05-24
 */
public class OptionalStringConverter extends AbstractOptionalConverter {

    /**
     * Construct a <b>java.lang.String</b> <i>Converter</i> that throws
     * a <code>ConversionException</code> if an error occurs.
     */
    public OptionalStringConverter() {
        super();
    }

    /**
     * Construct a <b>java.lang.String</b> <i>Converter</i> that returns
     * a default value if an error occurs.
     *
     * @param defaultValue The default value to be returned
     * if the value to be converted is missing or an error
     * occurs converting the value.
     */
    public OptionalStringConverter(final Object defaultValue) {
        super(defaultValue);
    }

    /**
     * Return the default type this <code>Converter</code> handles.
     *
     * @return The default type this <code>Converter</code> handles.
     * @since 1.8.0
     */
    @Override
    protected Class<?> getDefaultType() {
        return String.class;
    }

    /**
     * Convert the specified input object into an output object of the
     * specified type.
     *
     * @param <T> Target type of the conversion.
     * @param type Data type to which this value should be converted.
     * @param value The input value to be converted.
     * @return The converted value.
     * @throws Throwable if an error occurs converting to the specified type
     * @since 1.8.0
     */
    @Override
    protected <T> T convertToType(final Class<T> type, final Object value) throws Throwable {
        // We have to support Object, too, because this class is sometimes
        // used for a standard to Object conversion
        if (String.class.equals(type) || Object.class.equals(type)) {
            if (value instanceof Optional) {
                return convertOptionalToType(type, (Optional) value);
            }
            return type.cast(value.toString());
        }
        throw conversionException(type, value);
    }
}
