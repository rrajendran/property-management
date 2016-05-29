package com.capella.zookeeper.exceptions;

import java.util.concurrent.Callable;

/**
 * Created by ramesh on 28/05/2016.
 */
public class Exceptions {
    public static <T> T uncheck(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
