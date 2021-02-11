package com.antondoddo.production.http.serializzer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.UUID;

public class IdAdapter implements JsonSerializer<UUID>, JsonDeserializer<UUID> {

  @Override
  public UUID deserialize(
      JsonElement json,
      Type type,
      JsonDeserializationContext jsonDeserializationContext
  ) throws JsonParseException {
    try {
      return UUID.fromString(json.getAsJsonPrimitive().getAsString());
    } catch (Exception e) {
      throw new JsonParseException(e);
    }
  }

  @Override
  public JsonElement serialize(
      UUID uuid,
      Type type,
      JsonSerializationContext jsonSerializationContext
  ) {
    return new JsonPrimitive(uuid.toString());
  }
}
