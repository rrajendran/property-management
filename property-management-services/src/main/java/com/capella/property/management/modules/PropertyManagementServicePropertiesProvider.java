package com.capella.property.management.modules;

import com.capella.zookeeper.loader.PropertiesLoader;
import com.google.inject.Provider;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.io.InputStream;
import java.util.Properties;

import static org.slf4j.LoggerFactory.getLogger;

public class PropertyManagementServicePropertiesProvider implements Provider<Properties> {
    public static final Logger LOGGER = getLogger(PropertyManagementServicePropertiesProvider.class);

    private static Properties properties = new Properties();

    @Inject
    private static PropertiesLoader propertiesLoader;

    static {
        try {
            InputStream inputStream = PropertyManagementServicePropertiesProvider.class.getClassLoader().getResourceAsStream("property-management-client.properties");
            properties.load(inputStream);
            String rootNode = "/ipt-ss-fulfillment-services";
            propertiesLoader.load(inputStream, rootNode);
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
