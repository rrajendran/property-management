package com.capella.property.management.services;


import com.capella.property.management.entity.Person;

public interface GreeterService {
    public Person greet(final String name) throws Exception;
}