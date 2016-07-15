package com.capella.zookeeper.guice;

import com.google.inject.Provider;
import org.slf4j.Logger;

import java.io.InputStream;
import java.util.Properties;

import static org.slf4j.LoggerFactory.getLogger;

public class PropertiesProvider implements Provider<Properties> {
    public static final Logger LOGGER = getLogger(PropertiesProvider.class);

    private static Properties properties = new Properties();

    /*private static ZookeeperClient zookeeperClient = new ZooKeeperClientImpl();*/

    static {
        try {
            InputStream inputStream = PropertiesProvider.class.getClassLoader().getResourceAsStream("property-management-service.properties");
            properties.load(inputStream);
            /*String rootNode = "/ipt-ss-fulfillment-services";
            List<String> children = zookeeperClient.getChildren(rootNode, new PropertiesWatcher());
            for (String child : children) {
                String path = rootNode + "/" + child;
                properties.put(child, zookeeperClient.get(path, String.class));
            }*/
        } catch (Exception ex) {
            LOGGER.error("Get properties: " + ex);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static Properties getProperties() {
        return properties;
    }

    @Override
    public Properties get() {
        return properties;
    }
}
