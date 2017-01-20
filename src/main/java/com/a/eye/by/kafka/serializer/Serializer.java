package com.a.eye.by.kafka.serializer;

public interface Serializer<T> {
    
    T deserialize(byte[] message);

    byte[] serialize(T object);
    
}
