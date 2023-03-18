package com.platform.core.network;

import java.util.List;

public interface BaseNetwork {

    public abstract void boradcast(List<String> connectedSessionsIds, String jsonData);
}