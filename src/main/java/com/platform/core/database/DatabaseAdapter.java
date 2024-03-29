package com.platform.core.database;

import com.platform.core.utility.ObjectJsonConverter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Set;

@Slf4j
public class DatabaseAdapter {
    private final AbstractDatabase abstractDatabase2;
    private final String playerCollectionName = "PLAYER";
    private final String gameCollectionName = "GAME";

    public DatabaseAdapter() {
        abstractDatabase2 = MapDB.getInstance();
        abstractDatabase2.addCollection(playerCollectionName);
        abstractDatabase2.addCollection(gameCollectionName);
        log.info("Init Database");

    }

    // Create methods
    public void putPlayer(String key, Object value) {
        abstractDatabase2.create(playerCollectionName, key, value);
    }

    public void putGame(String key, Object value) {
        abstractDatabase2.create(gameCollectionName, key, value);
    }

    // Read methods
    public Object queryPlayer(String key) {
        return abstractDatabase2.read(playerCollectionName, key);
    }

    public Object queryGame(String key) {
        return abstractDatabase2.read(gameCollectionName, key);
    }

    // Update methods
    public void updatePlayer(String key, Object value) {
        abstractDatabase2.update(playerCollectionName, key, value);
    }

    public void updateGame(String key, Object value) {
        abstractDatabase2.update(gameCollectionName, key, value);
    }

    // Delete methods
    public void deletePlayer(String key) {
        abstractDatabase2.delete(playerCollectionName, key);
    }

    public void deleteGame(String key) {
        abstractDatabase2.delete(gameCollectionName, key);
    }

    public void clearAll() {
        abstractDatabase2.reset(gameCollectionName);
    }

    public String getAll() {
        Set<String> gameCollectionEntries = abstractDatabase2.getAll(gameCollectionName);
        Set<String> playerCollectionEntries = abstractDatabase2.getAll(playerCollectionName);
        HashMap<String, Set<String>> entries = new HashMap<>();
        entries.put(gameCollectionName, gameCollectionEntries);
        entries.put(playerCollectionName, playerCollectionEntries);
        return ObjectJsonConverter.toJSON(entries);
    }
}
