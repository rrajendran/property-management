package com.capella.zookeeper.loader;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by dev on 15/7/16.
 */
public interface PropertiesLoader {
    void load(InputStream inputStream, String namespace) throws Exception;

    Map<String, String> readProperties(String namespace) throws Exception;
}
