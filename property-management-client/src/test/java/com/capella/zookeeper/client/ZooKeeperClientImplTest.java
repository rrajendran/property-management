package com.capella.zookeeper.client;

import com.capella.zookeeper.guice.PropertiesWatcher;
import org.apache.zookeeper.KeeperException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ZooKeeperClientImplTest extends BaseTest {

    private String path;

    @Before
    public void testCreate() throws Exception {
        byte[] data = "helloworld".getBytes();
        path = zookeeperClient.create("/test", data);
        assertThat(path, is("/test"));

    }

    @Test
    public void testGetNode() throws KeeperException, InterruptedException {
        assertThat(zookeeperClient.get(path).toString(), is("helloworld"));
    }

    @Test
    public void testGetChildren() throws Exception {
        List<String> children = zookeeperClient.getChildren(path, new PropertiesWatcher());
        for (String child : children) {
            System.out.println("child : " + child);
        }
    }

    @After
    public void tearDown() throws Exception {
        zookeeperClient.delete(path);
    }
}