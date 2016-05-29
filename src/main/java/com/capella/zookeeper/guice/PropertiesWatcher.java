package com.capella.zookeeper.guice;

import com.google.inject.Singleton;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Created by ramesh on 29/05/2016.
 */
@Singleton
public class PropertiesWatcher implements Watcher {

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("" + watchedEvent.getType());
    }
}
