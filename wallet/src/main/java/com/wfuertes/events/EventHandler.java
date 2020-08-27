package com.wfuertes.events;

public interface EventHandler {

    void handle(String eventBody, long createdAt);
}
