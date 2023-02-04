package com.platform.core.database;

import com.colorify.game.mechanics.BaseGame;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.platform.core.player.Player;
import com.platform.core.utility.Constants;
import lombok.NonNull;
import org.bson.Document;


/**
 * package private MongoDB class.
 */
class MongoDB extends AbstractDatabase {

    private MongoDatabase database;
    private MongoCollection<Document> playerCollection;
    private MongoCollection<Document> gameCollection;

    @Override
    public void init() {

        MongoClient mongoClient = MongoClients.create(Constants.DBConstants.DB_URI);
        database = mongoClient.getDatabase(Constants.DBConstants.DB_NAME);
        playerCollection = database.getCollection(Constants.DBConstants.PLAYER_TABLE_NAME);
        gameCollection = database.getCollection(Constants.DBConstants.GAME_COLLECTION_NAME);

    }

    @Override
    public boolean putPlayer(@NonNull final String id, final Player data) {
        return put(playerCollection, id, data);
    }

    @Override
    public boolean putGame(String id, BaseGame data) {
        return put(gameCollection, id, data);
    }

    @Override
    public String queryPlayer(String id) {
        return null;
    }

    @Override
    public BaseGame queryGame(String id) {
        return null;
    }

    @Override
    public boolean deletePlayer(String id) {
        return false;
    }

    @Override
    public boolean deleteGame(String id) {
        return false;
    }

    private boolean put(final MongoCollection<Document> collection, final String id, final Object data) {
        Document document = new Document();
        document.put(id, data);
        InsertOneResult result = collection.insertOne(document);
        return result.wasAcknowledged();
    }
}
