package com.capella.zookeeper.guice;

import com.capella.zookeeper.client.ZookeeperClient;
import com.google.inject.Singleton;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import javax.inject.Inject;
import java.util.Properties;

/**
 * Created by ramesh on 29/05/2016.
 */
@Singleton
public class PropertiesWatcher implements Watcher {

    @Inject
    private Properties properties;

    @Inject
    private ZookeeperClient zookeeperClient;
    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
