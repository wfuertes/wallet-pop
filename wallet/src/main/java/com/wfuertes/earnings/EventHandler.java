package com.wfuertes.earnings;

public interface EventHandler {

    void handle(String eventBody, long createdAt);
}
