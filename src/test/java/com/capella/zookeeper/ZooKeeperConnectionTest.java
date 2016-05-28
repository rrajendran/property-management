package com.capella.zookeeper;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.zookeeper.KeeperException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.capella.zookeeper.ZooKeeperConnection.getInstance;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ZooKeeperConnectionTest {
    private ZooKeeperConnection zoo = getInstance();
    private String path;

    @Before
    public void setUp() throws Exception {
        byte[] data = SerializationUtils.serialize("helloworld");
        path = zoo.create("/ipt-ms-template-management", data);
        assertThat(path, is("/ipt-ms-template-management"));

    }

    @Test
    public void testGetNode() throws KeeperException, InterruptedException {
        assertThat(zoo.get(path, String.class), is("helloworld"));
        ;
    }

    @Test
    public void testGetChildren() throws Exception {
        List<String> children = zoo.getChildren(path);
        for (String child : children) {
            System.out.println("child : " + child);
        }
    }

    @After
    public void tearDown() throws KeeperException, InterruptedException {
        zoo.delete(path);
    }
}