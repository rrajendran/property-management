package com.capella.guice;

import com.capella.zookeeper.ZooKeeperConnection;
import com.google.inject.Provider;

import java.util.List;
import java.util.Properties;

public class PropertiesProvider implements Provider<Properties> {

    private static Properties properties = new Properties();
    private static ZooKeeperConnection zkConnection = ZooKeeperConnection.getInstance();

    static {
        try {
            String rootNode = "/ipt-ss-fulfillment-services";
            List<String> children = zkConnection.getChildren(rootNode);
            for (String child : children) {
                String path = rootNode + "/" + child;
                properties.put(child, zkConnection.get(path, String.class));
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
