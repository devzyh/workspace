package cn.devzyh.demo;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.header.Header;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsumerApplication {

    private static Properties properties;

    public static void main(String[] args) throws InterruptedException {

        final Properties props = loadProperties();

        int consumerNum = 1;
        Thread[] consumerThreads = new Thread[consumerNum];
        for (int i = 0; i < consumerNum; i++) {
            props.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, "runnerName" + i);
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
            List<String> subscribedTopics = new ArrayList<>();
            subscribedTopics.add("apac.product.sku.953ab190bc.v1");
            subscribedTopics.add("apac.product.bom.ir021011.v1");
            subscribedTopics.add("apac.customer.customers.953ab190bc20.v1");
            subscribedTopics.add("apac.supply.pricingcondition.953ab190bc30.v1");
            subscribedTopics.add("apac.supply.inbounddelivery.ir021011.v1");
            subscribedTopics.add("apac.supply.outbounddelivery.ir021011.v1");
            subscribedTopics.add("apac.supply.stockmovement.ir021011.v1");
            subscribedTopics.add("apac.supply.supersession.1r02.v1");
            subscribedTopics.add("apac.supply.purchaseorder.1r021011ab.v1");
            consumer.subscribe(subscribedTopics);

            KafkaConsumerRunner kafkaConsumerRunner = new KafkaConsumerRunner(consumer);
            consumerThreads[i] = new Thread(kafkaConsumerRunner);
        }

        for (int i = 0; i < consumerNum; i++) {
            consumerThreads[i].start();
        }

        for (int i = 0; i < consumerNum; i++) {
            consumerThreads[i].join();
        }
    }

    /**
     * 消费者线程实现
     */
    static class KafkaConsumerRunner implements Runnable {
        private final AtomicBoolean closed = new AtomicBoolean(false);
        private final KafkaConsumer consumer;

        KafkaConsumerRunner(KafkaConsumer consumer) {
            this.consumer = consumer;
        }

        public void run() {
            try {
                while (!closed.get()) {
                    try {
                        ConsumerRecords<String, String> records = consumer.poll(100);
                        //必须在下次poll之前消费完这些数据, 且总耗时不得超过SESSION_TIMEOUT_MS_CONFIG
                        for (ConsumerRecord<String, String> record : records) {

                            for (Header header : record.headers()) {
                                String hk = header.key();
                                String hv = "";
                                if (header.value() != null) {
                                    hv = new String(header.value());
                                }
                                System.out.println(String.format("key:%s value:%s", hk, hv));
                            }

                            String topic = record.topic();
                            String key = record.key();
                            String value = record.value();
                            System.out.println(String.format("Consumed event from topic %s: key = %-10s value = %s", topic, key, value));
                        }
                    } catch (Exception e) {
                        try {
                            Thread.sleep(1000);
                        } catch (Throwable ignore) {

                        }
                        //参考常见报错: https://help.aliyun.com/document_detail/68168.html?spm=a2c4g.11186623.6.567.2OMgCB
                        e.printStackTrace();
                    }
                }
            } catch (WakeupException e) {
                // Ignore exception if closing
                if (!closed.get()) {
                    throw e;
                }
            } finally {
                consumer.close();
            }
        }

        // Shutdown hook which can be called from a separate thread
        public void shutdown() {
            closed.set(true);
            consumer.wakeup();
        }
    }


    /**
     * 加载配置文件
     *
     * @return
     */
    public synchronized static Properties loadProperties() {
        if (null != properties) {
            return properties;
        }
        //获取配置文件kafka.properties的内容
        Properties kafkaProperties = new Properties();
        try {
            kafkaProperties.load(ConsumerApplication.class.getClassLoader().getResourceAsStream("kafka-2cprdnew.properties"));
        } catch (Exception e) {
            //没加载到文件，程序要考虑退出
            e.printStackTrace();
        }
        properties = kafkaProperties;
        return kafkaProperties;
    }

}
