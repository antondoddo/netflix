package com.antondoddo.production.repository;

import com.antondoddo.production.Production;
import com.antondoddo.production.valueobject.Actor;
import com.antondoddo.production.valueobject.AgeClassification;
import com.antondoddo.production.valueobject.Description;
import com.antondoddo.production.valueobject.Director;
import com.antondoddo.production.valueobject.Duration;
import com.antondoddo.production.valueobject.Episode;
import com.antondoddo.production.valueobject.Genre;
import com.antondoddo.production.valueobject.Title;
import com.antondoddo.production.valueobject.YearOfPublication;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MongoMapper {

  public MongoProductionPojo fromProduction(Production production) {

    MongoProductionPojo pojo = new MongoProductionPojo();

    pojo.id = production.getId();
    pojo.title = production.getTitle().toString();
    pojo.description = production.getDescription().toString();
    pojo.duration = production.getDuration().getFilmDuration();
    pojo.ageClassification = production.getAgeClassification().toString();
    pojo.filmDirector = new String[]{production.getDirection().getName(), production.getDirection().getSurname()};
    pojo.episode = production.getEpisode().getValue();
    pojo.season = production.getSeason().getValue();
    pojo.yearOfPublication = production.getYearOfPublication().toString();
    pojo.cast = production
        .getCast()
        .stream()
        .map((Actor actor) -> new String[]{actor.getName(), actor.getSurname()})
        .collect(Collectors.toCollection(ArrayList::new));
    pojo.genres = production
        .getGenres()
        .stream()
        .map(Enum::toString)
        .collect(Collectors.toCollection((ArrayList::new)));

    return pojo;

  }

  public Production fromMongoProductionPojo(MongoProductionPojo mongoPojo) {

    if (mongoPojo.season == 0) {

      return Production.ofMovie(
          mongoPojo.id,
          new Title(mongoPojo.title),
          new Description((mongoPojo.description)),
          new Duration(mongoPojo.duration),
          new YearOfPublication(mongoPojo.yearOfPublication),
          mongoPojo.genres.stream().map(Genre::valueOf).collect(Collectors.toCollection(ArrayList::new)),
          mongoPojo.cast.stream().map((String[] actor)-> new Actor(actor[0],actor[1])).collect(Collectors.toCollection(ArrayList::new)),
          new Director(mongoPojo.filmDirector[0],mongoPojo.filmDirector[1]),
          AgeClassification.valueOf(mongoPojo.ageClassification)
          );
    }

    return Production.ofEpisode(
        mongoPojo.id,
        new Title(mongoPojo.title),
        new Description((mongoPojo.description)),
        new Duration(mongoPojo.duration),
        new YearOfPublication(mongoPojo.yearOfPublication),
        mongoPojo.genres.stream().map(Genre::valueOf).collect(Collectors.toCollection(ArrayList::new)),
        mongoPojo.cast.stream().map((String[] actor) -> new Actor(actor[0], actor[1])).collect(Collectors.toCollection(ArrayList::new)),
        new Director(mongoPojo.filmDirector[0], mongoPojo.filmDirector[1]),
        AgeClassification.valueOf(mongoPojo.ageClassification),
        mongoPojo.episode
        }


    )
  }
}
