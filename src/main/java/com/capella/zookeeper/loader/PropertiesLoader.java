package com.capella.zookeeper.loader;

import com.capella.zookeeper.client.ZookeeperClientImpl;
import com.capella.zookeeper.guice.PropertiesWatcher;
import org.apache.commons.lang3.SerializationUtils;

import javax.inject.Inject;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Properties loader
 */
public class PropertiesLoader {

    @Inject
    private ZookeeperClientImpl zkConnection;

    /**
     * Load properties to zookeeper
     *
     * @param inputStream
     * @param namespace
     * @throws Exception
     */
    public void load(InputStream inputStream, String namespace) throws Exception {
        Properties properties = new Properties();
        properties.load(inputStream);
        Map<String, String> map = (Map) properties;

        String rootNode = zkConnection.create("/" + namespace, null);
        map.entrySet().stream().forEach(entry -> {
            try {
                byte[] data = SerializationUtils.serialize(entry.getValue());
                zkConnection.create(rootNode + "/" + entry.getKey(), data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Read properties from zookeeper
     *
     * @param namespace
     * @return
     * @throws Exception
     */
    public Map<String, String> readProperties(String namespace) throws Exception {
        List<String> children = zkConnection.getChildren(namespace, new PropertiesWatcher());
        Map<String, String> props = new HashMap<String, String>();
        for (String child : children) {
            String path = namespace + "/" + child;
            props.put(child, zkConnection.get(path, String.class));
        }
        return props;

    }
}
