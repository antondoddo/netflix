package com.antondoddo.production.http.serializzer;

import com.antondoddo.production.valueobject.Actor;
import com.antondoddo.production.valueobject.Director;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class DirectorAdapter implements JsonSerializer<Director>, JsonDeserializer<Director> {

  @Override
  public Director deserialize(
      JsonElement json,
      Type type,
      JsonDeserializationContext jsonDeserializationContext
  ) throws JsonParseException {
    try {
      JsonObject object = json.getAsJsonObject();
      return new Director(
          object.getAsJsonPrimitive("name").getAsString(),
          object.getAsJsonPrimitive("surname").getAsString()
      );
    } catch (Exception e) {
      throw new JsonParseException(e);
    }
  }

  @Override
  public JsonElement serialize(
      Director director,
      Type type,
      JsonSerializationContext jsonSerializationContext
  ) {
    JsonObject object = new JsonObject();
    object.add("name", new JsonPrimitive(director.getName()));
    object.add("surname", new JsonPrimitive(director.getSurname()));
    return object;
  }
}
