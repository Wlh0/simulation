package org.cgn.simulation.base.designpattern.singleton;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class Singleton {
    private static final ConcurrentHashMap<String, Object> SINGLE_OBJECT_POOL = new ConcurrentHashMap<>();

    public static <T> T get(String key) {
        Object result = SINGLE_OBJECT_POOL.get(key);
        return result == null ? null : (T) result;
    }

    public static <T> T get(String key, Supplier<T> supplier) {
        Object result = SINGLE_OBJECT_POOL.get(key);
        if (result == null && (result = supplier.get()) != null){
            SINGLE_OBJECT_POOL.put(key, result);
        }
        return result != null ? (T) result : null;
    }

    public static void put(Object value){
        put(value.getClass().getName(), value);
    }

    public static void put(String key, Object value) {
        SINGLE_OBJECT_POOL.put(key, value);
    }
}
