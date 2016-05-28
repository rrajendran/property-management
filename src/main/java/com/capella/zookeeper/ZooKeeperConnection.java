package com.capella.zookeeper;// import java classes

import org.apache.commons.lang3.SerializationUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

// import zk classes

public class ZooKeeperConnection {
    private static ZooKeeperConnection instance;
    private ZooKeeper zk;
    final CountDownLatch connectedSignal = new CountDownLatch(1);

    /**
     * @throws IOException
     * @throws InterruptedException
     */
    private ZooKeeperConnection() throws IOException, InterruptedException {
        zk = new ZooKeeper("localhost", 5000, new Watcher() {

            public void process(WatchedEvent we) {
                if (we.getState() == KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                } else if (we.getType() == Event.EventType.NodeDataChanged) {
                    System.out.println("NodeDataChanged :" + we.getPath());
                } else if (we.getType() == Event.EventType.NodeDeleted) {
                    System.out.println("NodeDeleted :" + we.getPath());
                } else if (we.getType() == Event.EventType.NodeCreated) {
                    System.out.println("NodeCreated :" + we.getPath());
                } else if (we.getType() == Event.EventType.NodeChildrenChanged) {
                    System.out.println("NodeChildrenChanged :" + we.getPath());
                } else if (we.getType() == Event.EventType.NodeDeleted) {
                    System.out.println("NodeDeleted :" + we.getPath());
                }
            }
        });

        connectedSignal.await();

    }

    /**
     * Singleton instance
     *
     * @return
     */
    public static ZooKeeperConnection getInstance() {
        try {
            return instance == null ? instance = new ZooKeeperConnection() : instance;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ZooKeeper getZookeeper() {
        return this.zk;
    }

    /**
     * Create or update data for a given path
     *
     * @param path
     * @param data
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     * @throws IOException
     */
    public String create(String path, byte[] data) throws
            KeeperException, InterruptedException, IOException {
        Stat stats = exists(path);
        if (stats == null) {

            return zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
        } else {
            zk.setData(path, data, stats.getVersion());
        }
        return path;
    }

    /**
     * check if a node exists
     *
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public Stat exists(String path) throws
            KeeperException, InterruptedException {
        return zk.exists(path, true);
    }

    /**
     * Get data for a give node path
     *
     * @param path
     * @param t
     * @param <T>
     * @return
     * @throws InterruptedException
     * @throws KeeperException
     */
    public <T> T get(final String path, Class<T> t) throws InterruptedException, KeeperException {
        try {
            Stat stat = exists(path);

            if (stat != null) {
                byte[] b = zk.getData(path, null, stat);
                return (T) SerializationUtils.deserialize(b);
            } else {
                System.out.println("Node does not exists");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Get all children
     *
     * @param path
     * @return
     * @throws Exception
     */
    public List<String> getChildren(String path) throws Exception {
        List<String> children;
        try {
            Stat stat = exists(path); // Stat checks the path

            if (stat != null) {
                //“getChildren” method- get all the children of znode.It has two
                children = zk.getChildren(path, false);

            } else {
                throw new RuntimeException("Node does not exists");
            }

        } catch (Exception e) {
            throw e;
        }

        return children;

    }

    /**
     * Delete node
     *
     * @param path
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void delete(String path) throws KeeperException, InterruptedException {
        zk.delete(path, exists(path).getVersion());
    }

    /**
     * Close the zk connection
     *
     * @throws InterruptedException
     */
    // Method to disconnect from zk server
    public void close() throws InterruptedException {
        if (zk != null) {
            zk.close();

        }
    }

}