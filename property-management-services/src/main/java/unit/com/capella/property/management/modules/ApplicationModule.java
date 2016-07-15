package unit.com.capella.property.management.modules;

import com.capella.property.management.resources.HelloResource;
import com.capella.property.management.services.GreeterService;
import com.capella.property.management.services.GreeterServiceImpl;
import com.capella.zookeeper.guice.PropertyManagementClientModule;
import com.google.inject.AbstractModule;

public class ApplicationModule extends AbstractModule {

    @Override
    public void configure() {
        install(new PropertyManagementClientModule());
        bind(HelloResource.class);
        bind(GreeterService.class).to(GreeterServiceImpl.class);
    }
}