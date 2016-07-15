package com.capella.zookeeper.guice;

import com.capella.zookeeper.client.ZooKeeperClientImpl;
import com.capella.zookeeper.client.ZookeeperClient;
import com.capella.zookeeper.loader.PropertiesLoader;
import com.capella.zookeeper.loader.PropertiesLoaderImpl;
import com.google.inject.Binder;
import com.google.inject.Module;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;

/**
 * Created by ramesh on 28/05/2016.
 */
public class PropertyManagementClientModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(RetryPolicy.class).toProvider(RetryPolicyProvider.class);
        binder.bind(CuratorFramework.class).toProvider(CuratorClientProvider.class);
        binder.bind(PropertiesLoader.class).to(PropertiesLoaderImpl.class);
        binder.bind(ZookeeperClient.class).to(ZooKeeperClientImpl.class).asEagerSingleton();
    }
}
