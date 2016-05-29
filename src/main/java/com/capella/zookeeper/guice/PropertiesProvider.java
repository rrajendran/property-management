package com.capella.zookeeper.guice;

import com.capella.zookeeper.client.ZookeeperClient;
import com.capella.zookeeper.client.ZookeeperClientImpl;
import com.google.inject.Provider;

import java.util.List;
import java.util.Properties;

public class PropertiesProvider implements Provider<Properties> {

    private static Properties properties = new Properties();

    private static ZookeeperClient zookeeperClient = new ZookeeperClientImpl();

    static {
        try {
            String rootNode = "/ipt-ss-fulfillment-services";
            List<String> children = zookeeperClient.getChildren(rootNode, new PropertiesWatcher());
            for (String child : children) {
                String path = rootNode + "/" + child;
                properties.put(child, zookeeperClient.get(path, String.class));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Properties get() {
        return properties;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static Properties getProperties() {
        return properties;
    }
}
