package com.capella.guice;

import com.google.inject.name.Named;

import javax.inject.Inject;

/**
 * Created by ramesh on 28/05/2016.
 */
public class ServiceName {

    @Named(value = "ipt.ipf.process.bpm.service.url")
    @Inject
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
