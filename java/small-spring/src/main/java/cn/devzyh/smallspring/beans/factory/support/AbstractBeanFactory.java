package cn.devzyh.smallspring.beans.factory.support;

import cn.devzyh.smallspring.beans.BeansException;
import cn.devzyh.smallspring.beans.factory.FactoryBean;
import cn.devzyh.smallspring.beans.factory.config.BeanDefinition;
import cn.devzyh.smallspring.beans.factory.config.BeanPostProcessor;
import cn.devzyh.smallspring.beans.factory.config.ConfigurableBeanFactory;
import cn.devzyh.smallspring.core.convert.ConversionService;
import cn.devzyh.smallspring.util.ClassUtils;
import cn.devzyh.smallspring.util.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 * <p>
 * BeanDefinition注册表接口
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegisterSupport implements ConfigurableBeanFactory {

    /**
     * BeanPostProcessors to apply in createBean
     */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /**
     * String resolvers to apply e.g. to annotation attribute values
     */
    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

    private ConversionService conversionService;

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) getObjectForBeanInstance(bean, name);
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, name);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    /**
     * 返回所属的ClassLoader
     *
     * @return
     */
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

    /**
     * 如果是FactoryBean对象，则调用对应的getObject方法
     *
     * @param beanInstance
     * @param beanName
     * @return
     */
    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        Object obj = getCacheObjectForFactoryBean(beanName);
        if (obj == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            obj = getObjectFromFactoryBean(factoryBean, beanName);
        }

        return obj;
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }

    public ConversionService getConversionService() {
        return conversionService;
    }

    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
}
