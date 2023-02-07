package com.platform.core.database;

import com.colorify.game.mechanics.BaseGame;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.platform.core.game.AbstractBaseGame;
import com.platform.core.player.Player;
import com.platform.core.utility.Constants;
import com.platform.core.utility.Logger;
import com.platform.core.utility.ObjectJsonConverter;
import lombok.NonNull;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

/**
 * package private MongoDB class.
 */
class MongoDB extends AbstractDatabase {
    private Gson gson;

    private MongoDatabase database;
    private MongoCollection<Document> playerCollection;
    private MongoCollection<Document> gameCollection;

    protected MongoDB() {
        super();
    }

    protected MongoDB(final Map<Class, Object> typeAdapters) {
        init(typeAdapters);
    }

    @Override
    public void init(final Map<Class, Object> typeAdapters) {
        Logger.info("DB: mongo init");
        MongoClient mongoClient = MongoClients.create(Constants.DBConstants.DB_URI);
        CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        database = mongoClient.getDatabase(Constants.DBConstants.DB_NAME).withCodecRegistry(pojoCodecRegistry);
        playerCollection = database.getCollection(Constants.DBConstants.PLAYER_TABLE_NAME);
        gameCollection = database.getCollection(Constants.DBConstants.GAME_COLLECTION_NAME);

        addTypeAdapters(typeAdapters);
    }

    @Override
    public boolean putPlayer(@NonNull final String playerId, final Player data) {
        Logger.info("DB: Putting Player :" + playerId);
        return put(playerCollection, playerId, data);
    }

    @Override
    public boolean putGame(@NonNull String gameId, AbstractBaseGame data) {
        Logger.info("DB: Putting Game :" + gameId);
        return put(gameCollection, gameId, data);
    }

    @Override
    public Player queryPlayer(String id) {
        return null;
    }

    @Override
    public AbstractBaseGame queryGame(String gameId, Class<BaseGame> gameClass) {
        Logger.info("DB: Querying Game :" + gameId);

        Document document = query(gameCollection, gameId);
        Logger.info("json1\n" + document.get(Constants.DBConstants._data));
        BaseGame temp = gson.fromJson(((Document) document.get(Constants.DBConstants._data)).toJson(), gameClass);
        Logger.info("json2\n" + ObjectJsonConverter.toJSON(temp));
        Logger.info("json3\n" + ((Document) document.get(Constants.DBConstants._data)).toJson());

        return temp;
    }

    @Override
    public boolean updatePlayer(String gameId, BaseGame game) {
        return false;
    }

    @Override
    public boolean updateGame(String gameId, BaseGame game) {
        Logger.info("DB: Updating Game :" + gameId);
        return update(gameCollection, gameId, game);
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
        Document document = new Document()
                .append(Constants.DBConstants._id, id)
                .append(Constants.DBConstants._data, data);
        InsertOneResult result = collection.insertOne(document);
        return result.wasAcknowledged();
    }

    private Document query(final MongoCollection<Document> collection, final String id) {
        return collection.find(eq(Constants.DBConstants._id, id)).first();
    }

    private boolean update(MongoCollection<Document> collection, String id, Object data) {
        Document document = new Document().append(Constants.DBConstants._id, id);
        Bson updates = Updates.set(Constants.DBConstants._data, data);
        UpdateResult result = collection.updateOne(document, updates);
        return result.wasAcknowledged();
    }

    private void addTypeAdapters(final Map<Class, Object> typeAdapters) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        for (Map.Entry<Class, Object> pair : typeAdapters.entrySet()) {
            gsonBuilder.registerTypeHierarchyAdapter(pair.getKey(), pair.getValue());
        }
        gson = gsonBuilder.create();
    }

}
