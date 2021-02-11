package com.antondoddo.production.http.serializzer;

import com.antondoddo.production.valueobject.Duration;
import com.antondoddo.production.valueobject.exception.IllegalDurationException;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class DurationAdapter implements JsonSerializer<Duration>, JsonDeserializer<Duration> {

  @Override
  public Duration deserialize(
      JsonElement json,
      Type type,
      JsonDeserializationContext jsonDeserializationContext
  ) throws JsonParseException {
    try {
      return new Duration(java.time.Duration.ofSeconds(json.getAsJsonPrimitive().getAsInt()));
    } catch (IllegalDurationException e) {
      throw new JsonParseException(e);
    } catch (Exception e) {
      throw new JsonParseException(new IllegalDurationException());
    }
  }

  @Override
  public JsonElement serialize(
      Duration duration,
      Type type,
      JsonSerializationContext jsonSerializationContext
  ) {
    return new JsonPrimitive(duration.getTimeDuration().getSeconds());
  }
}
