package cn.devzyh.demo.event;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 事件发布者
 */
public class EventPublisher {

    // 存放订阅者信息
    private Map<String, List<IEventSubscriber>> eventSubscribers = new HashMap<>();

    /**
     * 添加事件类型
     *
     * @param events
     */
    void addEvents(String... events) {
        for (String event : events) {
            this.eventSubscribers.put(event, new LinkedList<>());
        }
    }

    /**
     * 订阅事件
     *
     * @param event
     * @param subscriber
     */
    void subscribe(String event, IEventSubscriber subscriber) {
        List<IEventSubscriber> subscriberList = this.eventSubscribers.get(event);
        subscriberList.add(subscriber);
    }

    /**
     * 取消订阅
     *
     * @param event
     * @param subscriber
     */
    void unsubscribe(String event, IEventSubscriber subscriber) {
        List<IEventSubscriber> subscriberList = this.eventSubscribers.get(event);
        subscriberList.remove(subscriber);
    }

    /**
     * 事件通知
     *
     * @param event
     * @param params
     */
    void notify(String event, Object params) {
        List<IEventSubscriber> subscriberList = this.eventSubscribers.get(event);
        for (IEventSubscriber subscriber : subscriberList) {
            subscriber.update(event, params);
        }
    }

}
