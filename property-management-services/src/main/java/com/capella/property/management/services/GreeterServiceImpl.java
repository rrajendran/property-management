package com.capella.property.management.services;


import com.capella.property.management.entity.Person;
import com.capella.zookeeper.client.ZookeeperClient;

import javax.inject.Inject;
import java.time.LocalDate;

public class GreeterServiceImpl implements GreeterService {

    @Inject
    private ZookeeperClient zookeeperClient;

    public Person greet(final String name) throws Exception {
        zookeeperClient.create("/test/name", name.getBytes());
        String n = zookeeperClient.get("/test/name", String.class);
        Person person = new Person();
        person.setName(n);
        person.setMessage("Hello world !! " + LocalDate.now());
        return person;
    }
}