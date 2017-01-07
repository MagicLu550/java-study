package com.a.eye.by.zk;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class MyZkClient implements Watcher {

    private static final int SESSION_TIMEOUT = 1000;

    private ZooKeeper zk = null;

    private CountDownLatch countDown = new CountDownLatch(1);

    public void process(WatchedEvent event) {
        if (event.getState() == KeeperState.SyncConnected) {
            countDown.countDown();
        }
    }

    public void connect(String host) throws IOException, InterruptedException {
        zk = new ZooKeeper(host, SESSION_TIMEOUT, this);
        countDown.await();
    }

    public void createData(String groupName, String value) throws KeeperException, InterruptedException {
        String rootPath = "/";
        String path = rootPath + groupName;
        zk.create(path, value.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public void readData(String groupName) throws KeeperException, InterruptedException {
        String rootPath = "/";
        String path = rootPath + groupName;
        zk.getData(path, this, null);
    }

    public void deleteData(String groupName) throws InterruptedException, KeeperException {
        String rootPath = "/";
        String path = rootPath + groupName;
        zk.delete(path, -1);
    }
    
    public void close() throws InterruptedException {
        if (null != zk) {
            zk.close();
        }
    }

}
