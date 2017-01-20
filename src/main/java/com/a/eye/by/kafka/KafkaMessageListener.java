package com.a.eye.by.kafka;

public interface KafkaMessageListener {
    public void onMessage(String msg);
}
