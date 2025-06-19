package cn.devzyh.smallspring.aop;

/**
 * 切入点访问器
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * 获取切入点
     *
     * @return
     */
    Pointcut getPointcut();
}
