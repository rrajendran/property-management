package com.capella.zookeeper.client;

import org.apache.zookeeper.server.ServerCnxnFactory;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.File;
import java.io.IOException;

/**
 * Created by ramesh on 28/05/2016.
 */
public class BaseTest {

    private static ServerCnxnFactory standaloneServerFactory;

    @BeforeClass
    public static void init() throws IOException, InterruptedException {
        int tickTime = 2000;
        int numConnections = 5000;
        String dataDirectory = System.getProperty("java.io.tmpdir");

        File dir = new File(dataDirectory, "zookeeper").getAbsoluteFile();

        ZooKeeperServer server = new ZooKeeperServer(dir, dir, tickTime);
        standaloneServerFactory = ServerCnxnFactory.createFactory(2121, numConnections);

        standaloneServerFactory.startup(server);
    }


    @AfterClass
    public static void shutdown() {
        standaloneServerFactory.shutdown();
    }
}
