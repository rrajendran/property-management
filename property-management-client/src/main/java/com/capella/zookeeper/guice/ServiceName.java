package com.capella.zookeeper.guice;

import javax.inject.Inject;
import java.util.Properties;

/**
 * Created by ramesh on 28/05/2016.
 */
public class ServiceName {

    @Inject
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
