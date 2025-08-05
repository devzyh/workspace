package cn.devzyh.smallspring.beans.factory;

/**
 * 实现此接口可以感知到所属的BeanName
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String beanName);

}
