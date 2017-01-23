package com.a.eye.by.kafka.partitioner;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class MyPartitioner implements Partitioner {

    public MyPartitioner(VerifiableProperties props) {

    }

    public int partition(Object key, int numPartitions) {
        String id = (String) key;
        return ((int) (Long.valueOf(id) / 100)) % numPartitions;
    }

}
