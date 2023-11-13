package com.platform.core.database;

public abstract class AbstractDatabase {
    protected static AbstractDatabase instance;

    protected AbstractDatabase() {
        // Initialization code, if needed
    }

    public static synchronized AbstractDatabase getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }

    protected abstract void addCollection(String name);
    protected abstract void create(String collectionName, String key, Object value);
    protected abstract Object read(String collectionName, String key);
    protected abstract void update(String collectionName, String key, Object value);
    protected abstract void delete(String collectionName, String key);
    protected abstract void reset(String collectionName);
    protected abstract void close();

    private static AbstractDatabase createInstance() {
        // You can choose the concrete implementation to instantiate here
        // For simplicity, let's assume MapDB as the implementation
        return new MapDB();
    }
}
