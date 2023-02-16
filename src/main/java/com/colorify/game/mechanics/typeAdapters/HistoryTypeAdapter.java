package com.colorify.game.mechanics.typeAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.platform.core.game.gameHistory.History;

import java.io.IOException;

public class HistoryTypeAdapter extends TypeAdapter<History> {

    @Override
    public void write(JsonWriter out, History value) throws IOException {

    }

    @Override
    public History read(JsonReader in) throws IOException {
        History history = null;
//        // todo :
//        in.beginObject();
//        if (in.hasNext()) {
//            String name = in.nextName();
//            Logger.info("name" + name);
//            history = new BaseGameHistory();
//            JsonToken next = in.peek();
//            Logger.info("token" + next.toString());
//            Gson gson = new Gson();
//            Map<String, Score> scores = gson.fromJson(in, Map.class);
//            scoreTracker.setScores(scores);
//        }
//        in.endObject();
        return history;
    }
}
