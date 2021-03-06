package com.capella.zookeeper.client;// import java classes

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

// import zk classes

public class ZooKeeperClientImpl implements ZookeeperClient {
    @Inject
    private CuratorFramework client;

    @Inject
    private Properties properties;


    /**
     * @throws IOException
     * @throws InterruptedException
     */
    public ZooKeeperClientImpl() {

    }

    public CuratorFramework getZookeeper() {
        return this.client;
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
    public String create(String path, byte[] data) throws Exception {
        if(client.checkExists().forPath(path) != null){
            client.setData().forPath(path,data);
            return path;
        }else{
            return client.create().creatingParentsIfNeeded().forPath(path, data);
        }
    }

    /**
     * check if a node exists
     *
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public Stat exists(String path) throws Exception {
        return client.checkExists().forPath(path);
    }

    /**
     *
     * @param path
     * @return
     * @throws InterruptedException
     * @throws KeeperException
     */
    public byte[] get(final String path) throws InterruptedException, KeeperException {
        try {
            Stat stat = exists(path);

            if (stat != null) {
                return client.getData().forPath(path);
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
     * @param watcher
     * @return
     * @throws Exception
     */
    public List<String> getChildren(String path, Watcher watcher) throws Exception {
        List<String> children;
        try {
            Stat stat = exists(path); // Stat checks the path

            if (stat != null) {
                //“getChildren” method- get all the children of znode.It has two
                children = client.getChildren().usingWatcher(watcher).forPath(path);

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
    public void delete(String path) throws Exception {
        client.delete().deletingChildrenIfNeeded().forPath(path);
    }

    /**
     * Close the zk connection
     *
     * @throws InterruptedException
     */
    // Method to disconnect from zk server
    public void close() throws InterruptedException {
        client.close();
    }

}