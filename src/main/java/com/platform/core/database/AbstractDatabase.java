package com.platform.core.database;

abstract class AbstractDatabase {

    protected static AbstractDatabase instance;

    protected AbstractDatabase() {}

    public static AbstractDatabase getInstance()
    {
        if(instance == null)
            instance = new MongoDB(); // todo: initiate via Config.
        return instance;
    }


    public abstract void init();

    public abstract boolean put(final String id, final Object data);

    public abstract String query(final String id);

    public abstract String delete(final String id);
}
