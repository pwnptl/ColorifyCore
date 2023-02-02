package com.platform.core.database;

public interface AbstractDatabase {
    public abstract boolean put();
    public abstract String query();
    public abstract String delete();
}
