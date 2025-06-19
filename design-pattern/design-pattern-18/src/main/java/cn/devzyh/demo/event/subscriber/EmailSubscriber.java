package cn.devzyh.demo.event.subscriber;

import cn.devzyh.demo.event.IEventSubscriber;

/**
 * 邮件订阅者
 */
public class EmailSubscriber implements IEventSubscriber {

    @Override
    public void update(String event, Object params) {
        System.out.printf("电子邮件订阅者收到事件[%s]，事件内容[%s]。\n", event, params.toString());
    }
}
