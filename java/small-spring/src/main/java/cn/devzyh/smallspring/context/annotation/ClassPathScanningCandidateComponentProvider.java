package cn.devzyh.smallspring.context.annotation;

import cn.devzyh.smallspring.beans.factory.config.BeanDefinition;
import cn.hutool.core.util.ClassUtil;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Bean对象注解扫描器
 */
public class ClassPathScanningCandidateComponentProvider {

    /**
     * 在指定路径下扫描Bean注解
     *
     * @param basePackage
     * @return
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }

}
