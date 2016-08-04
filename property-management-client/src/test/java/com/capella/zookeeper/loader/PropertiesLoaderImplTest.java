package com.capella.zookeeper.loader;

import com.capella.zookeeper.client.BaseTest;
import com.capella.zookeeper.client.ZookeeperClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by ramesh on 29/05/2016.
 */
public class PropertiesLoaderImplTest extends BaseTest {
    private PropertiesLoaderImpl propertiesLoader;

    @Before
    public void setUp() throws Exception {
        zookeeperClient = injector.getInstance(ZookeeperClient.class);
        assertThat(zookeeperClient, is(notNullValue()));

        propertiesLoader = injector.getInstance(PropertiesLoaderImpl.class);
        assertThat(propertiesLoader, is(notNullValue()));


        InputStream inputStream = new ByteArrayInputStream("name=test".getBytes());
        assertNotNull(inputStream);
        propertiesLoader.load(inputStream, "testProperties/sample");
    }

    @After
    public void tearDown() throws Exception {
        zookeeperClient.delete("/testProperties");
    }


    @Test
    public void testReadProperties() throws Exception {
        Map<String, String> stringStringMap = propertiesLoader.readProperties("/testProperties/sample");
        System.out.println(stringStringMap);
    }
}