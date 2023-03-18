package com.colorify.game.mechanics.typeAdapters;

import com.colorify.game.mechanics.scoreTracker.ColorifyScoreTracker;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.platform.core.game.Score;
import com.platform.core.game.ScoreTracker;

import java.io.IOException;
import java.util.Map;

public class ScoreTrackerTypeAdapter extends TypeAdapter<ScoreTracker> {
    @Override
    public void write(JsonWriter out, ScoreTracker value) throws IOException {

    }

    @Override
    public ScoreTracker read(JsonReader in) throws IOException {
        ScoreTracker scoreTracker = null;
        in.beginObject();
        if (in.hasNext()) {
            String name = in.nextName();
            scoreTracker = new ColorifyScoreTracker();
            JsonToken next = in.peek();
            Gson gson = new Gson();
            Map<String, Score> scores = gson.fromJson(in, Map.class );
            scoreTracker.setScores(scores);
        }
        in.endObject();
        return scoreTracker;
    }
}
