package com.capella;

import com.capella.zookeeper.ZooKeeperConnection;
import org.apache.commons.lang3.SerializationUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by ramesh on 28/05/2016.
 */
public class PropertiesLoader {


    public static void main(String[] args) throws Exception {
        InputStream resourceAsStream = PropertiesLoader.class.getClassLoader().getResourceAsStream("ms-fulfillment-service.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        Map<String, String> map = (Map) properties;

        ZooKeeperConnection zkConnection = ZooKeeperConnection.getInstance();
        String rootNode = zkConnection.create("/ipt-ss-fulfillment-services", null);
        map.entrySet().stream().forEach(entry -> {
            try {
                byte[] data = SerializationUtils.serialize(entry.getValue());
                zkConnection.create(rootNode + "/" + entry.getKey(), data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        List<String> children = zkConnection.getChildren(rootNode);
        Map<String, String> props = new HashMap<String, String>();
        for (String child : children) {
            String path = rootNode + "/" + child;
            props.put(child, zkConnection.get(path, String.class));
        }


        // zkConnection.delete("/ipt-ss-fulfillment-services");


    }
}
