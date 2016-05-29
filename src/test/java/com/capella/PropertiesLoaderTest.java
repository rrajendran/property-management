package com.capella;

import com.capella.zookeeper.client.BaseTest;
import com.capella.zookeeper.client.ZooKeeperConnection;
import com.capella.zookeeper.guice.ApplicationModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
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
public class PropertiesLoaderTest extends BaseTest {
    Injector injector = Guice.createInjector(new ApplicationModule());

    private ZooKeeperConnection zooKeeperConnection;
    private PropertiesLoader propertiesLoader;

    @Before
    public void setUp() throws Exception {
        zooKeeperConnection = injector.getInstance(ZooKeeperConnection.class);
        assertThat(zooKeeperConnection, is(notNullValue()));

        propertiesLoader = injector.getInstance(PropertiesLoader.class);
        assertThat(propertiesLoader, is(notNullValue()));


        InputStream inputStream = new ByteArrayInputStream("name=test".getBytes());
        assertNotNull(inputStream);
        propertiesLoader.load(inputStream, "testProperties/sample");
    }

    @After
    public void tearDown() throws Exception {
        zooKeeperConnection.delete("/testProperties");
    }


    @Test
    public void testReadProperties() throws Exception {
        Map<String, String> stringStringMap = propertiesLoader.readProperties("/testProperties/sample");
        System.out.println(stringStringMap);
    }
}