package com.colorify.game.mechanics.typeAdapters;

import com.colorify.game.mechanics.cell.IntegerBoardCell;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.platform.core.game.BoardCell;

import java.io.IOException;

public class BoardCellTypeAdapter extends TypeAdapter<BoardCell> {
    @Override
    public void write(JsonWriter out, BoardCell value) throws IOException {

    }

    @Override
    public BoardCell read(JsonReader in) throws IOException {
        BoardCell boardCell = null;
        in.beginObject();
        if (in.hasNext()) {
            String name = in.nextName();
            boardCell = new IntegerBoardCell(in.nextInt());
        }
        in.endObject();
        return boardCell;
    }
}
