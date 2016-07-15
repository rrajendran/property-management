package com.capella.zookeeper.guice;

import com.google.inject.Provider;
import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by dev on 15/7/16.
 */
public class CuratorClientProvider implements Provider<CuratorFramework> {
    @Inject
    private RetryPolicy retryPolicy;

    @Inject
    @Named(value = "zookeeper.url")
    private String zookeeperUrl;



    @Override
    public CuratorFramework get() {
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperUrl, retryPolicy);
        client.start();
        return client;
    }
}
