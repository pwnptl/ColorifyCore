package com.platform.core.database;

import com.colorify.game.mechanics.BaseGame;
import com.platform.core.game.AbstractBaseGame;
import com.platform.core.player.Player;
import com.platform.core.utility.Constants;
import com.platform.core.utility.Logger;

import java.io.*;
import java.util.HashMap;

public class InMemoryStorage extends AbstractDatabase {
    private HashMap<String, Object> store;
    private final String storageFilePath;

    public InMemoryStorage() {
        super();
        String projectDirectory = System.getProperty("user.dir");
        this.storageFilePath = projectDirectory + File.separator +Constants.DBConstants.FILE_NAME;
        Logger.info(this.getClass().getName(), "FilePath " + this.storageFilePath);
        loadStoreFromFile();
    }

    @Override
    protected void finalize() {
        saveStoreToFile();
    }

    private void loadStoreFromFile() {
        try {
            Logger.info(this.getClass().getName(), "Reading from file : " + storageFilePath);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storageFilePath));
            store = (HashMap<String, Object>) ois.readObject();
        } catch (FileNotFoundException e) {
            store = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            store = new HashMap<>();
        }
    }

    private void saveStoreToFile() {
        try {
            Logger.info(this.getClass().getName(), "Writing to file : " + storageFilePath);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storageFilePath));
            oos.writeObject(store);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init() {
        loadStoreFromFile();
    }

    @Override
    public boolean putPlayer(String id, Player data) {
        store.put(id, data);
        saveStoreToFile();
        return false;
    }

    @Override
    public boolean putGame(String id, AbstractBaseGame data) {
        store.put(id, data);
        return false;
    }

    @Override
    public Player queryPlayer(String id) {
        return (Player) store.get(id);
    }

    @Override
    public AbstractBaseGame queryGame(String id, Class<BaseGame> baseGameClass) {
        return (AbstractBaseGame) store.get(id);
    }

    @Override
    public boolean updatePlayer(String gameId, BaseGame game) {
        return false;
    }

    @Override
    public boolean updateGame(String gameId, BaseGame game) {
        return false;
    }

    @Override
    public boolean deletePlayer(String id) {
        return false;
    }

    @Override
    public boolean deleteGame(String id) {
        return false;
    }
}
