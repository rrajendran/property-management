package com.capella;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.test.TestingServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest {
    TestingServer zkTestServer;
    private CuratorFramework cli;

    @Before
    public void startZookeeper() throws Exception {
        zkTestServer = new TestingServer(2181);
        cli = CuratorFrameworkFactory.newClient(zkTestServer.getConnectString(), new RetryOneTime(2000));
    }

    @Test
    public void testZookeeper() {

    }

    @After
    public void stopZookeeper() throws IOException {
        cli.close();
        zkTestServer.stop();
    }
}
