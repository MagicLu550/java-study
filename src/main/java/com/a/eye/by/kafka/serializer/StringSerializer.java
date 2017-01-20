package com.a.eye.by.kafka.serializer;

public class StringSerializer implements Serializer<String> {

    public String deserialize(byte[] message) {
        try {
            String value = new String(message, "UTF-8");
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] serialize(String object) {
        try {
            return object.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
