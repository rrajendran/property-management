package com.capella.zookeeper.guice;

import com.google.inject.Provider;
import org.apache.curator.RetryPolicy;
import org.apache.curator.retry.ExponentialBackoffRetry;

import javax.inject.Inject;
import javax.inject.Named;


/**
 * Created by dev on 15/7/16.
 */
public class RetryPolicyProvider  implements Provider<RetryPolicy> {
    @Inject
    @Named(value = "zookeeper.sleeptime")
    private int sleepTime;
    @Inject
    @Named(value = "zookeeper.max.retries")
    private int maxRetries;

    @Override
    public RetryPolicy get() {
        return new ExponentialBackoffRetry(this.sleepTime, this.maxRetries);
    }
}
