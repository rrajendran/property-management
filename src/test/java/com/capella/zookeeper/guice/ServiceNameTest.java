package com.capella.zookeeper.guice;

import com.capella.zookeeper.client.BaseTest;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ramesh on 28/05/2016.
 */
public class ServiceNameTest extends BaseTest {

    @Test
    public void testGetUrl() throws Exception {
        Injector injector = Guice.createInjector(new ApplicationModule());
        ServiceName serviceName = injector.getInstance(ServiceName.class);
        assertThat(serviceName.getProperties().size(), is(31));
    }
}