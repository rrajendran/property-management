package com.capella.property.management.modules;

import com.capella.property.management.resources.HelloResource;
import com.capella.property.management.services.GreeterService;
import com.capella.property.management.services.GreeterServiceImpl;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;

import java.util.Properties;

import static com.google.inject.name.Names.bindProperties;

public class ServiceModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(Properties.class).toProvider(PropertyManagementServicePropertiesProvider.class).in(Singleton.class);
        bindProperties(binder, PropertyManagementServicePropertiesProvider.getProperties());

        binder.bind(HelloResource.class);
        binder.bind(GreeterService.class).to(GreeterServiceImpl.class);
    }
}