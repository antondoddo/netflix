package com.antondoddo.production.http.serializzer;

import com.antondoddo.production.valueobject.NullSeason;
import com.antondoddo.production.valueobject.Season;
import com.antondoddo.production.valueobject.SeasonImpl;
import com.antondoddo.production.valueobject.exception.IllegalSeasonException;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class SeasonAdapter implements JsonSerializer<Season>, JsonDeserializer<Season> {

  @Override
  public Season deserialize(
      JsonElement json,
      Type type,
      JsonDeserializationContext jsonDeserializationContext
  ) throws JsonParseException {
    int season = json.getAsJsonPrimitive().getAsInt();

    if (season == 0) {
      return new NullSeason();
    }

    try {
      return new SeasonImpl(season);
    } catch (IllegalSeasonException e) {
      throw new JsonParseException(e);
    }
  }

  @Override
  public JsonElement serialize(
      Season season,
      Type type,
      JsonSerializationContext jsonSerializationContext
  ) {
    if (season.getValue() == 0) {
      return null;
    }

    return new JsonPrimitive(season.getValue());
  }
}
