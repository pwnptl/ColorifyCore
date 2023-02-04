package com.platform.core.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
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
    public boolean put(@NonNull final String id, final Object data) {
        Document document = new Document();
        document.put(id, data);
        InsertOneResult result = playerCollection.insertOne(document);
        return result.wasAcknowledged();
    }

    @Override
    public String query(String id) {
        return null;
    }

    @Override
    public String delete(String id) {
        return null;
    }
}
