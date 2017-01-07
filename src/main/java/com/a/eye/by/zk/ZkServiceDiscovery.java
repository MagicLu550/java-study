package com.a.eye.by.zk;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZkServiceDiscovery implements ServiceDiscovery {

    private static final Logger logger = LoggerFactory.getLogger(ZkServiceDiscovery.class);

    private final ZkClient zk;

    public ZkServiceDiscovery(String host) {
        zk = new ZkClient(host, ZkConstant.ZK_SESSION_TIMEOUT, ZkConstant.ZK_CONNECTION_TIMEOUT);
        logger.debug("connect zookeeper success !");
    }

    public String discover(String service) {

        String path = ZkConstant.ZK_REGISTRY_PATH + "/" + service;

        if (!zk.exists(path)) {
            throw new RuntimeException(String.format("not find any service node on path: %s", path));
        }

        List<String> addressList = zk.getChildren(path);

        if (null == addressList || addressList.size() == 0) {
            throw new RuntimeException(String.format("not find any address node on path: %s", path));
        }

        int addressSize = addressList.size();

        String address;

        if (addressSize == 1) {
            address = addressList.get(0);
        } else {
            address = addressList.get(ThreadLocalRandom.current().nextInt(addressSize));
        }

        String addressPath = path + "/" + address;

        return zk.readData(addressPath);
    }

}
