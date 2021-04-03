package com.antondoddo.production.http.serializzer;

import com.antondoddo.production.valueobject.ReleaseDate;
import com.antondoddo.production.valueobject.exception.IllegalReleaseDateException;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.LocalDate;

public class ReleaseDateAdapter implements
    JsonSerializer<ReleaseDate>,
    JsonDeserializer<ReleaseDate> {

  @Override
  public ReleaseDate deserialize(
      JsonElement json,
      Type type,
      JsonDeserializationContext jsonDeserializationContext
  ) throws JsonParseException {
    try {
      return new ReleaseDate(LocalDate.parse(json.getAsJsonPrimitive().getAsString()));
    } catch (IllegalReleaseDateException e) {
      throw new JsonParseException(e);
    } catch (Exception e) {
      throw new JsonParseException(new IllegalReleaseDateException());
    }
  }

  @Override
  public JsonElement serialize(
      ReleaseDate releaseDate,
      Type type,
      JsonSerializationContext jsonSerializationContext
  ) {
    return new JsonPrimitive(releaseDate.getValue().toString());
  }
}
