package com.antondoddo.production.repository;

import com.antondoddo.production.Production;
import com.antondoddo.production.valueobject.Actor;
import com.antondoddo.production.valueobject.AgeClassification;
import com.antondoddo.production.valueobject.Description;
import com.antondoddo.production.valueobject.Director;
import com.antondoddo.production.valueobject.Duration;
import com.antondoddo.production.valueobject.EpisodeImpl;
import com.antondoddo.production.valueobject.Genre;
import com.antondoddo.production.valueobject.ReleaseDate;
import com.antondoddo.production.valueobject.SeasonImpl;
import com.antondoddo.production.valueobject.Title;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class MongoMapper {

  public MongoProductionPojo fromProduction(Production production) {

    MongoProductionPojo pojo = new MongoProductionPojo();

    pojo.id = production.getId().toString();
    pojo.title = production.getTitle().getValue();
    pojo.description = production.getDescription().getValue();
    pojo.duration = production.getDuration().getTimeDuration().toMillis();
    pojo.ageClassification = production.getAgeClassification().name();
    pojo.filmDirector = Arrays.asList(production.getDirection()
        .getName(), production.getDirection().getSurname());
    pojo.episode = production.getEpisode().getValue();
    pojo.season = production.getSeason().getValue();
    pojo.releaseDate = production.getReleaseDate().getValue().toString();
    pojo.cast = production
        .getCast()
        .stream()
        .map((Actor actor) -> Arrays.asList(actor.getName(), actor.getSurname()))
        .collect(Collectors.toCollection(ArrayList::new));
    pojo.genres = production
        .getGenres()
        .stream()
        .map(Enum::name)
        .collect(Collectors.toCollection((ArrayList::new)));

    return pojo;
  }

  public Production fromMongoProductionPojo(MongoProductionPojo mongoPojo) {

    if (mongoPojo.season == 0) {
      return Production.ofMovie(
          UUID.fromString(mongoPojo.id),
          new Title(mongoPojo.title),
          new Description(mongoPojo.description),
          new Duration(java.time.Duration.ofMillis(mongoPojo.duration)),
          new ReleaseDate(LocalDate.parse(mongoPojo.releaseDate)),
          mongoPojo.genres.stream().map(Genre::valueOf)
              .collect(Collectors.toCollection(ArrayList::new)),
          mongoPojo.cast.stream().map((List<String> actor) ->
              new Actor(actor.get(0), actor.get(1)))
              .collect(Collectors.toCollection(ArrayList::new)),
          new Director(mongoPojo.filmDirector.get(0), mongoPojo.filmDirector.get(1)),
          AgeClassification.valueOf(mongoPojo.ageClassification)
      );
    }

    return Production.ofEpisode(
        UUID.fromString(mongoPojo.id),
        new Title(mongoPojo.title),
        new Description((mongoPojo.description)),
        new Duration(java.time.Duration.ofMillis(mongoPojo.duration)),
        new ReleaseDate(LocalDate.parse(mongoPojo.releaseDate)),
        mongoPojo.genres.stream().map(Genre::valueOf)
            .collect(Collectors.toCollection(ArrayList::new)),
        mongoPojo.cast.stream().map((List<String> actor) -> new Actor(actor.get(0), actor.get(1)))
            .collect(Collectors.toCollection(ArrayList::new)),
        new Director(mongoPojo.filmDirector.get(0), mongoPojo.filmDirector.get(1)),
        AgeClassification.valueOf(mongoPojo.ageClassification),
        new EpisodeImpl(mongoPojo.episode),
        new SeasonImpl(mongoPojo.season)
    );
  }
}



