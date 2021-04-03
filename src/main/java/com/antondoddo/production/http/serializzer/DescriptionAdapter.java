package com.antondoddo.production.http.serializzer;

import com.antondoddo.production.valueobject.Description;
import com.antondoddo.production.valueobject.exception.IllegalDescriptionException;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class DescriptionAdapter implements
    JsonSerializer<Description>,
    JsonDeserializer<Description> {

  @Override
  public Description deserialize(
      JsonElement json,
      Type type,
      JsonDeserializationContext jsonDeserializationContext
  ) throws JsonParseException {
    try {
      return new Description(json.getAsJsonPrimitive().getAsString());
    } catch (IllegalDescriptionException e) {
      throw new JsonParseException(e);
    }
  }

  @Override
  public JsonElement serialize(
      Description description,
      Type type,
      JsonSerializationContext jsonSerializationContext
  ) {
    return new JsonPrimitive(description.getValue());
  }
}
