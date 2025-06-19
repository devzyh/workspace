package cn.devzyh.smallspring.aop;

import org.aopalliance.aop.Advice;

public interface Advisor {

    /**
     * 返回一个切面
     * @return
     */
    Advice getAdvice();

}
