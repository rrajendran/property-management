package com.capella.property.management.services;


import com.capella.property.management.entity.Person;
import com.capella.zookeeper.client.ZookeeperClient;

import javax.inject.Inject;
import java.time.LocalDate;

public class GreeterServiceImpl implements GreeterService {

    @Inject
    private ZookeeperClient zookeeperClient;

    public Person greet(final String name) throws Exception {
       /* byte[] data = SerializationUtils.serialize(name);
        String path = zookeeperClient.create("/test/name", data);
        System.out.println("path =- "+path);*/
        String n = new String(zookeeperClient.get("/ipt-ss-fulfillment-services/service.name"));
        System.out.println("********data***********" + n);
        Person person = new Person();
        person.setName(n);
        person.setMessage("Hello world !! " + LocalDate.now());
        return person;
    }
}