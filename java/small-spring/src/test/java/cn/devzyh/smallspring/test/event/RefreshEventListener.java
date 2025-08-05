package cn.devzyh.smallspring.test.event;

import cn.devzyh.smallspring.context.ApplicationListener;
import cn.devzyh.smallspring.context.event.ContextRefreshedEvent;

/**
 * 容器刷新完成事件监听
 */
public class RefreshEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.printf("收到容器刷新完成事件" + event.getApplicationContext());
    }
}
