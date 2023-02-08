package com.colorify.game.mechanics.typeAdapters;

import com.colorify.game.mechanics.cell.IntegerCell;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.platform.core.game.Cell;

import java.io.IOException;

public class BoardCellTypeAdapter extends TypeAdapter<Cell> {
    @Override
    public void write(JsonWriter out, Cell value) throws IOException {

    }

    @Override
    public Cell read(JsonReader in) throws IOException {
        Cell boardCell = null;
        in.beginObject();
        if (in.hasNext()) {
            String name = in.nextName();
            boardCell = new IntegerCell(in.nextInt());
        }
        in.endObject();
        return boardCell;
    }
}
