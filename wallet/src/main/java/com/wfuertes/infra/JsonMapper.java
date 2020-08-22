package com.wfuertes.infra;

import com.google.gson.GsonBuilder;

import javax.inject.Inject;
import java.lang.reflect.Type;

public class JsonMapper {

    private final GsonBuilder builder;

    @Inject
    public JsonMapper(GsonBuilder builder) {
        this.builder = builder;
    }

    public <T> T fromJson(String json, Type type) {
        return builder.create().fromJson(json, type);
    }

    public <T> T fromJson(byte[] json, Type type) {
        return builder.create().fromJson(new String(json), type);
    }
}
