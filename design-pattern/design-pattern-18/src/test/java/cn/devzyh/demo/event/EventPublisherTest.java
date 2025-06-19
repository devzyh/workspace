package cn.devzyh.demo.event;

import cn.devzyh.demo.event.subscriber.EmailSubscriber;
import cn.devzyh.demo.event.subscriber.SmsSubscriber;
import org.junit.Test;

public class EventPublisherTest {

    @Test
    public void run() {
        // 发布者
        EventPublisher publisher = new EventPublisher();
        publisher.addEvents("newPaper", "newPeople");

        // 订阅者
        IEventSubscriber s1 = new EmailSubscriber();
        IEventSubscriber s2 = new SmsSubscriber();

        // 订阅
        publisher.subscribe("newPaper", s1);
        publisher.subscribe("newPaper", s2);
        publisher.unsubscribe("newPaper", s2);
        publisher.subscribe("newPeople", s1);
        publisher.subscribe("newPeople", s2);

        // 事件通知
        publisher.notify("newPaper", "新的报纸发版");
        publisher.notify("newPeople", "新的会员加入");

    }

}