package cn.devzyh.smallspring.core.convert.support;


import cn.devzyh.smallspring.core.convert.converter.Converter;
import cn.devzyh.smallspring.core.convert.converter.ConverterFactory;
import cn.devzyh.smallspring.util.NumberUtils;

/**
 * Converts from a String any JDK-standard Number implementation.
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }

}
