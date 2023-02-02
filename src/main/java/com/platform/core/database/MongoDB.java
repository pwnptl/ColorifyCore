package com.platform.core.database;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDB {
    private final String DB_URI = "mongodb://localhost:27017";
    private final String DB_NAME ="Colorify";
    private final String PLAYER_COLLECTION_NAME ="User";
    private final String GAME_COLLECTION_NAME ="User";
    public void init() {

        MongoClient mongoClient = MongoClients.create(DB_URI);
        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
        MongoCollection<Document> playerCollection = database.getCollection(PLAYER_COLLECTION_NAME);

        // Todo.
    }
}
