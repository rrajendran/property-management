package com.capella.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ramesh on 28/05/2016.
 */
public class ServiceNameTest {

    @Test
    public void testGetUrl() throws Exception {
        Injector injector = Guice.createInjector(new ApplicationModule());
        ServiceName serviceName = injector.getInstance(ServiceName.class);
        assertThat(serviceName.getUrl(), is("http://localhost:9923/fulfilment-bpm-service"));
    }
}