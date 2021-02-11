package com.antondoddo.production.http.serializzer;

import com.antondoddo.production.valueobject.Episode;
import com.antondoddo.production.valueobject.EpisodeImpl;
import com.antondoddo.production.valueobject.NullEpisode;
import com.antondoddo.production.valueobject.exception.IllegalEpisodeException;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class EpisodeAdapter implements JsonSerializer<Episode>, JsonDeserializer<Episode> {

  @Override
  public Episode deserialize(
      JsonElement json,
      Type type,
      JsonDeserializationContext jsonDeserializationContext
  ) throws JsonParseException {
    int episode = json.getAsJsonPrimitive().getAsInt();

    if (episode == 0) {
      return new NullEpisode();
    }

    try {
      return new EpisodeImpl(episode);
    } catch (IllegalEpisodeException e) {
      throw new JsonParseException(e);
    }
  }

  @Override
  public JsonElement serialize(
      Episode episode,
      Type type,
      JsonSerializationContext jsonSerializationContext
  ) {
    if (episode.getValue() == 0) {
      return null;
    }

    return new JsonPrimitive(episode.getValue());
  }
}
