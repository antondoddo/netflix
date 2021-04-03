package com.antondoddo.production.http.serializzer;

import com.antondoddo.production.valueobject.Title;
import com.antondoddo.production.valueobject.exception.IllegalTitleException;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class TitleAdapter implements JsonSerializer<Title>, JsonDeserializer<Title> {

  @Override
  public Title deserialize(
      JsonElement json,
      Type type,
      JsonDeserializationContext jsonDeserializationContext
  ) throws JsonParseException {
    try {
      return new Title(json.getAsJsonPrimitive().getAsString());
    } catch (IllegalTitleException e) {
      throw new JsonParseException(e);
    }
  }

  @Override
  public JsonElement serialize(
      Title title,
      Type type,
      JsonSerializationContext jsonSerializationContext
  ) {
    return new JsonPrimitive(title.getValue());
  }
}
