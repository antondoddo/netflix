package com.antondoddo.production.http.serializzer;

import com.antondoddo.production.valueobject.AgeClassification;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class AgeClassificationAdapter implements
    JsonSerializer<AgeClassification>,
    JsonDeserializer<AgeClassification> {

  @Override
  public AgeClassification deserialize(
      JsonElement json,
      Type type,
      JsonDeserializationContext jsonDeserializationContext
  ) throws JsonParseException {
    try {
      return AgeClassification.valueOf(json.getAsJsonPrimitive().getAsString());
    } catch (Exception e) {
      throw new JsonParseException(e);
    }
  }

  @Override
  public JsonElement serialize(
      AgeClassification ageClassification,
      Type type,
      JsonSerializationContext jsonSerializationContext
  ) {
    return new JsonPrimitive(ageClassification.toString());
  }
}
