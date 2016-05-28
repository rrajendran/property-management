package com.capella.guice;

import com.capella.zookeeper.BaseTest;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ramesh on 28/05/2016.
 */
public class ApplicationModuleTest extends BaseTest {

    @Test
    public void testConfigure() throws Exception {
        Injector injector = Guice.createInjector(new ApplicationModule());
        Properties properties = injector.getInstance(Properties.class);
        System.out.printf("properties : " + properties);
        assertThat(properties.size() > 1, is(true));

    }
}