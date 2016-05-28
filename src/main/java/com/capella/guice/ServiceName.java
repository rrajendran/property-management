package com.capella.guice;

import com.google.inject.name.Named;

import javax.inject.Inject;

/**
 * Created by ramesh on 28/05/2016.
 */
public class ServiceName {

    @Named(value = "ipt.fulfillment.persistence.service.properties")
    @Inject
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
