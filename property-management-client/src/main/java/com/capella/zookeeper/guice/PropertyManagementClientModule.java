package com.capella.zookeeper.guice;

import com.capella.zookeeper.client.ZooKeeperClientImpl;
import com.capella.zookeeper.client.ZookeeperClient;
import com.capella.zookeeper.loader.PropertiesLoader;
import com.capella.zookeeper.loader.PropertiesLoaderImpl;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;

import java.util.Properties;

import static com.google.inject.name.Names.bindProperties;

/**
 * Created by ramesh on 28/05/2016.
 */
public class PropertyManagementClientModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(Properties.class).toProvider(PropertiesProvider.class).in(Singleton.class);
        binder.bind(RetryPolicy.class).toProvider(RetryPolicyProvider.class);
        binder.bind(CuratorFramework.class).toProvider(CuratorClientProvider.class);

        bindProperties(binder, PropertiesProvider.getProperties());

        binder.bind(PropertiesLoader.class).to(PropertiesLoaderImpl.class);
        binder.bind(ZookeeperClient.class).to(ZooKeeperClientImpl.class).asEagerSingleton();
    }
}
