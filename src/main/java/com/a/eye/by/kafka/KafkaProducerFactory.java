package com.a.eye.by.kafka;

public class KafkaProducerFactory {

    private KafkaProducerFactory() {

    }

    public static KafkaProducer getInstance() {
        return NestedProduct.producer;
    }

    static class NestedProduct {
        private static final KafkaProducer producer = new KafkaProducer();
    }

}
