package com.a.eye.by.zk;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZkServiceRegister implements ServiceRegister {

    private static final Logger logger = LoggerFactory.getLogger(ZkServiceRegister.class);

    private final ZkClient zkClient;

    public ZkServiceRegister(String host) {
        zkClient = new ZkClient(host, ZkConstant.ZK_SESSION_TIMEOUT, ZkConstant.ZK_CONNECTION_TIMEOUT);
        logger.debug("connect zookeeper success !");
    }

    public void register(String serverName, String serverAddress) {

        String rootPath = ZkConstant.ZK_REGISTRY_PATH;

        if (!zkClient.exists(rootPath)) {
            zkClient.createPersistent(rootPath);
            logger.debug("create root path success, path:{}", rootPath);
        }

        String servicePath = rootPath + "/" + serverName;
        if (!zkClient.exists(servicePath)) {
            zkClient.createPersistent(rootPath);
            logger.debug("create service path success, path:{}", rootPath);
        }

        String addressPath = servicePath + "/ip-" + serverAddress;
        if (!zkClient.exists(addressPath)) {
            zkClient.createEphemeral(addressPath);
            logger.debug("create service path success, path:{}", rootPath);
        }

    }

}
