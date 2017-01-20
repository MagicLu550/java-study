package com.a.eye.by.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaConsumer<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private ExecutorService executor = null;

    private KafkaMessageListener listener = null;

    private ConsumerConnector consumerConnector = null;

    private final static String CONFIG_FILE = "/kafka-consumer.properties";

    public KafkaConsumer(KafkaMessageListener listener) {
        Properties props = new Properties();
        try {
            props.load(getClass().getResourceAsStream(CONFIG_FILE));
            consumerConnector = Consumer.createJavaConsumerConnector(new ConsumerConfig(props));
            this.listener = listener;
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    public void consume(final String topicName, final int partitionNums) {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topicName, new Integer(partitionNums));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap =
                consumerConnector.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topicName);
        executor = Executors.newFixedThreadPool(partitionNums);
        for (final KafkaStream<byte[], byte[]> stream : streams) {
            executor.submit(new Runnable() {
                public void run() {
                    ConsumerIterator<byte[], byte[]> it = stream.iterator();
                    while (it.hasNext()) {
                        try {
                            String msg = new String(it.next().message(), "UTF-8");
                            listener.onMessage(msg);
                        } catch (Exception e) {
                            LOGGER.error(e.getMessage(), e);
                        }
                    }
                }
            });
        }
    }

    public void shutdown() {
        if (null != consumerConnector) {
            consumerConnector.shutdown();
        }
        if (null != executor) {
            executor.shutdown();
        }
    }

}
