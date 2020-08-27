package com.wfuertes.infra;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;
import java.lang.reflect.Type;

public class JsonMapper {

    private final Gson gson;

    private JsonMapper(Gson gson) {
        this.gson = gson;
    }

    @Inject
    public JsonMapper(final GsonBuilder builder) {
        this(builder.create());
    }

    public <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }
}
