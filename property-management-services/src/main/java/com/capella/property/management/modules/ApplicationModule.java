package com.capella.property.management.modules;

import com.capella.zookeeper.guice.PropertyManagementClientModule;
import com.google.inject.AbstractModule;

public class ApplicationModule extends AbstractModule {

    @Override
    public void configure() {
        install(new PropertyManagementClientModule());
        install(new ServiceModule());
    }
}