package com.platform.core.game;

public interface Cell {
    public void setCell(int cell);
    public void setCoordinate(int r, int c);
    public abstract int getCellValue();

}
