package com.wfuertes.infra;

import com.google.gson.GsonBuilder;

import javax.inject.Inject;

public class JsonMapper {

    private final GsonBuilder builder;

    @Inject
    public JsonMapper(GsonBuilder builder) {
        this.builder = builder;
    }

    public String toJson(Object object) {
        return builder.create().toJson(object);
    }
}
