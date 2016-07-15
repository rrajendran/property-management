package com.capella.zookeeper.client;

import com.capella.zookeeper.guice.ApplicationModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.zookeeper.server.ServerCnxnFactory;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.fail;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by ramesh on 28/05/2016.
 */
public class BaseTest {
    public static final Logger LOGGER = getLogger(BaseTest.class);
    private static ServerCnxnFactory standaloneServerFactory;
    protected ZookeeperClient zookeeperClient;
    protected Injector injector;

    public BaseTest() {
        injector = Guice.createInjector(new ApplicationModule());
        zookeeperClient = injector.getInstance(ZooKeeperClientImpl.class);
    }

    @BeforeClass
    public static void init() throws IOException, InterruptedException {
        try {
            int tickTime = 2000;
            int numConnections = 5000;
            String dataDirectory = System.getProperty("java.io.tmpdir");

            File dir = new File(dataDirectory, "zookeeper").getAbsoluteFile();

            ZooKeeperServer server = new ZooKeeperServer(dir, dir, tickTime);
            standaloneServerFactory = ServerCnxnFactory.createFactory(2121, numConnections);

            standaloneServerFactory.startup(server);
            LOGGER.info("Zookeeper started successfully");
        } catch (Exception ex) {
            LOGGER.error("Error starting zookeeper", ex);
            fail("Error starting zookeeper");
        }
    }


    @AfterClass
    public static void shutdown() {
        try {
            standaloneServerFactory.shutdown();
            LOGGER.info("Zookeeper stopped successfully");
        } catch (Exception ex) {
            LOGGER.error("Error stopping zookeeper", ex);
            fail("");
        }
    }
}
