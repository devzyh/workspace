package cn.devzyh.smallspring.core.convert;

/**
 * A service interface for type conversion. This is the entry point into the convert system.
 * Call {@link #convert(Object, Class)} to perform a thread-safe type conversion using this system.
 * <p>
 * 类型转换抽象接口
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public interface ConversionService {

    /**
     * Return {@code true} if objects of {@code sourceType} can be converted to the {@code targetType}.
     */
    boolean canConvert(Class<?> sourceType, Class<?> targetType);

    /**
     * Convert the given {@code source} to the specified {@code targetType}.
     */
    <T> T convert(Object source, Class<T> targetType);

}
