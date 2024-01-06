package com.platform.core.database;

import com.platform.core.utility.Constants;
import lombok.extern.slf4j.Slf4j;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class MapDB extends AbstractDatabase {
    private final DB db;
    private final Map<String, Map<String, Object>> dataMaps;

    public MapDB() {
        super();
        db = DBMaker.fileDB(Constants.DBConstants.DB_NAME)
                .closeOnJvmShutdown()
                .make();
        dataMaps = new HashMap<>();
    }

    // Singleton instance creation method
    public static synchronized AbstractDatabase getInstance() {
        if (instance == null) {
            instance = new MapDB();
        }
        return instance;
    }

    @Override
    protected void addCollection(String name) {
        HTreeMap collection = db.hashMap(name, Serializer.STRING, Serializer.JAVA).createOrOpen();
        dataMaps.put(name, collection);
    }

    @Override
    protected void create(String collectionName, String key, Object value) {
        try {
            Map<String, Object> collection = dataMaps.get(collectionName);
            collection.computeIfAbsent(key, k -> {return value;});
            db.commit();
        } catch (NullPointerException npe) {
            throw new NullPointerException();
        }
    }

    @Override
    protected Object read(String collectionName, String key) {
        Map<String, Object> collection = dataMaps.get(collectionName);
        log.debug("collection {} have {}", collectionName, collection);
        return collection.get(key);
    }

    @Override
    protected void update(String collectionName, String key, Object value) {
        Map<String, Object> collection = dataMaps.get(collectionName);
        collection.put(key, value);
        db.commit();
    }

    @Override
    protected void delete(String collectionName, String key) {
    }

    @Override
    protected void reset(String collectionName) {
        Map<String, Object> collection = dataMaps.get(collectionName);
        log.debug("clearing DB {} of size {}", collectionName, collection.size());
        collection.clear();
        db.commit();
    }


    @Override
    protected void close() {
        if (db != null && !db.isClosed()) {
            db.close();
        }
    }

    @Override
    public Set<String> getAll(String collectionName) {
        return dataMaps.get(collectionName).keySet();
    }
}
