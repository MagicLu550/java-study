package com.a.eye.by.kafka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private Producer<String, String> inner;
    private final static String CONFIG_FILE = "/kafka-producer.properties";

    public KafkaProducer() {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getResourceAsStream(CONFIG_FILE));
            final ProducerConfig config = new ProducerConfig(properties);
            inner = new Producer<String, String>(config);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 发送消息
     * 
     * @param topicName
     * @param message
     */
    public void send(String topicName, String message) {
        if (topicName == null || message == null) {
            return;
        }
        KeyedMessage<String, String> km = new KeyedMessage<String, String>(topicName, message);
        inner.send(km);
    }

    /**
     * 分区下发送消息
     * 
     * @param topicName
     * @param message
     * @param key 分区id
     */
    public void send(String topicName, String message, String key) {
        if (topicName == null || message == null) {
            return;
        }
        KeyedMessage<String, String> km = new KeyedMessage<String, String>(topicName, key, message);
        inner.send(km);
    }

    /**
     * 批量发送消息
     * 
     * @param topicName
     * @param messages
     */
    public void send(String topicName, Collection<String> messages) {
        if (topicName == null || messages == null) {
            return;
        }
        if (messages.isEmpty()) {
            return;
        }
        List<KeyedMessage<String, String>> kms = new ArrayList<KeyedMessage<String, String>>();
        for (String entry : messages) {
            KeyedMessage<String, String> km = new KeyedMessage<String, String>(topicName, entry);
            kms.add(km);
        }
        inner.send(kms);
    }

    /**
     * 批量发送消息，分区情况下
     * 
     * @param topicName
     * @param messages
     */
    public void send(List<KeyedMessage<String, String>> messages) {
        if (messages == null) {
            return;
        }
        if (messages.isEmpty()) {
            return;
        }
        inner.send(messages);
    }

    public void close() {
        inner.close();
    }

}
