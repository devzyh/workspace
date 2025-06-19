package cn.devzyh.smallspring.context.annotation;

import cn.devzyh.smallspring.beans.factory.config.BeanDefinition;
import cn.devzyh.smallspring.beans.factory.support.BeanDefinitionRegistry;
import cn.hutool.core.util.StrUtil;

import java.util.Set;

/**
 * Bean定义扫描器
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * 扫描包里的Bean注解并转为Bean定义
     *
     * @param basePackages
     */
    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidates) {
                // 解析 Bean 的作用域 singleton、prototype
                String beanScope = resolveBeanScope(beanDefinition);
                if (StrUtil.isNotEmpty(beanScope)) {
                    beanDefinition.setScope(beanScope);
                }
                registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }

            // 注册处理注解的 BeanPostProcessor（@Autowired、@Value）
            registry.registerBeanDefinition("cn.devzyh.smallspring.context.annotation.internalAutowiredAnnotationBeanPostProcessor",
                    new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
        }
    }

    /**
     * 解析Bean作用域设置
     *
     * @param beanDefinition
     * @return
     */
    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (scope != null) {
            return scope.value();
        }
        return StrUtil.EMPTY;
    }

    /**
     * Bean对象自动小驼峰命名
     *
     * @param beanDefinition
     * @return
     */
    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }

}
