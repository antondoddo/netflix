package com.antondoddo.production.http.serializzer;

import com.antondoddo.production.valueobject.Actor;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class ActorAdapter implements JsonSerializer<Actor>, JsonDeserializer<Actor> {

  @Override
  public Actor deserialize(
      JsonElement json,
      Type type,
      JsonDeserializationContext jsonDeserializationContext
  ) throws JsonParseException {
    try {
      JsonObject object = json.getAsJsonObject();
      return new Actor(
          object.getAsJsonPrimitive("name").getAsString(),
          object.getAsJsonPrimitive("surname").getAsString()
      );
    } catch (Exception e) {
      throw new JsonParseException(e);
    }
  }

  @Override
  public JsonElement serialize(
      Actor actor,
      Type type,
      JsonSerializationContext jsonSerializationContext
  ) {
    JsonObject object = new JsonObject();
    object.add("name", new JsonPrimitive(actor.getName()));
    object.add("surname", new JsonPrimitive(actor.getSurname()));
    return object;
  }
}
