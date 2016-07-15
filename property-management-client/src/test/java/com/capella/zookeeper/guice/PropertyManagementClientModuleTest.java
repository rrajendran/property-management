package com.capella.zookeeper.guice;

import com.capella.zookeeper.client.BaseTest;
import com.capella.zookeeper.client.ZooKeeperClientImpl;
import com.capella.zookeeper.loader.PropertiesLoaderImpl;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ramesh on 28/05/2016.
 */
public class PropertyManagementClientModuleTest extends BaseTest {

    public PropertyManagementClientModuleTest() {

    }

    @Test
    public void testConfigureProperties() throws Exception {
        Properties properties = injector.getInstance(Properties.class);
        assertThat(properties.getProperty("zookeeper.url"), is("localhost:2121"));
        assertThat(properties.getProperty("zookeeper.sleeptime"), is("1000"));
        assertThat(properties.getProperty("zookeeper.max.retries"), is("3"));

    }

    @Test
    public void testZookeeperClient(){
        ZooKeeperClientImpl zooKeeperClient = injector.getInstance(ZooKeeperClientImpl.class);
        assertThat(zooKeeperClient,is(notNullValue()));
    }

    @Test
    public void testPropertyLoader(){
        PropertiesLoaderImpl propertiesLoader = injector.getInstance(PropertiesLoaderImpl.class);
        assertThat(propertiesLoader,is(notNullValue()));
    }


    @Test
    public void testRetryPolicy(){
        RetryPolicy retryPolicy = injector.getInstance(RetryPolicy.class);
        assertThat(retryPolicy,is(notNullValue()));
    }


    @Test
    public void testCuratorFramework(){
        CuratorFramework retryPolicy = injector.getInstance(CuratorFramework.class);
        assertThat(retryPolicy,is(notNullValue()));
    }

}