package com.capella.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;

import java.util.Properties;

/**
 * Created by ramesh on 28/05/2016.
 */
public class ApplicationModuleTest {

    @Test
    public void testConfigure() throws Exception {
        Injector injector = Guice.createInjector(new ApplicationModule());
        Properties properties = injector.getInstance(Properties.class);
        System.out.println(properties);

    }
}