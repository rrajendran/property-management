package com.capella.zookeeper.client;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Created by ramesh on 29/05/2016.
 */
public interface ZookeeperClient {
    String create(String path, byte[] data) throws Exception;

    Stat exists(String path) throws Exception;

    <T> T get(String path, Class<T> t) throws InterruptedException, KeeperException;

    List<String> getChildren(String path, Watcher watcher) throws Exception;

    void delete(String path) throws Exception;

    void close() throws InterruptedException;
}
