package com.antondoddo.production.repository;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.antondoddo.production.Production;
import com.antondoddo.production.valueobject.Actor;
import com.antondoddo.production.valueobject.AgeClassification;
import com.antondoddo.production.valueobject.Description;
import com.antondoddo.production.valueobject.Director;
import com.antondoddo.production.valueobject.Duration;
import com.antondoddo.production.valueobject.EpisodeImpl;
import com.antondoddo.production.valueobject.Genre;
import com.antondoddo.production.valueobject.SeasonImpl;
import com.antondoddo.production.valueobject.Title;
import com.antondoddo.production.valueobject.YearOfPublication;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class MongoMapperTest {

  protected static Object[] shouldBeMapperFromProductionData() {

    ArrayList<Genre> genres = new ArrayList<Genre>();
    genres.add(Genre.COMEDY);
    ArrayList<Actor> cast = new ArrayList<Actor>();
    cast.add(new Actor("Antonio", "Farina"));
    return new Object[]{
        new Object[]{
            Production.ofMovie(UUID.randomUUID(),
                new Title("L'alba"),
                new Description("Film veramente bellissimo"),
                new Duration(java.time.Duration.ofSeconds(86000)),
                new YearOfPublication("1993-01-19"),
                genres,
                cast,
                new Director("Damiano", "Petrungaro"), AgeClassification.SETTE)
        },
        new Object[]{
            Production.ofEpisode(UUID.randomUUID(),
                new Title("Il risveglio"),
                new Description("Episodio veramente bellissimo"),
                new Duration(java.time.Duration.ofSeconds(70000)),
                new YearOfPublication("1998-02-20"),
                genres,
                cast,
                new Director("Damiano", "Petrungaro"),
                AgeClassification.QUATTORDICI,
                new EpisodeImpl(2), new SeasonImpl(2))
        },
    };
  }

  @Test
  @Parameters(method = "shouldBeMapperFromProductionData")
  public void shouldBeMapperFromProduction(Production p) {

    MongoMapper mongoMapper = new MongoMapper();
    MongoProductionPojo mongoProductionPojo = mongoMapper.fromProduction(p);
    assertEquals(mongoProductionPojo.id, p.getId());
    assertEquals(mongoProductionPojo.title, p.getTitle().getValue());
    assertEquals(mongoProductionPojo.description, p.getDescription().getValue());
    assertEquals(mongoProductionPojo.ageClassification, p.getAgeClassification().name());
    assertEquals(mongoProductionPojo.episode, p.getEpisode().getValue());
    assertEquals(mongoProductionPojo.season, p.getSeason().getValue());
    assertArrayEquals(mongoProductionPojo.filmDirector,
        new String[]{p.getDirection().getName(), p.getDirection().getSurname()});
    assertEquals(mongoProductionPojo.genres, p.getGenres().stream()
        .map(Enum::name).collect(Collectors.toCollection(ArrayList::new)));
    assertArrayEquals(mongoProductionPojo.cast.toArray(), p.getCast().stream().map((Actor a) ->
        new String[]{a.getName(), a.getSurname()})
        .collect(Collectors.toCollection(ArrayList::new)).toArray());
    assertEquals(mongoProductionPojo.duration, p.getDuration().getFilmDuration());
    assertEquals(mongoProductionPojo.yearOfPublication,
        p.getYearOfPublication().getYearOfPublication());
  }

  protected static Object[] shouldBeMapperFromMongoProductionPojoData() {
    //Film
    MongoProductionPojo mongo1 = new MongoProductionPojo();
    mongo1.id = UUID.randomUUID();
    mongo1.title = "Ma che bella giornata";
    mongo1.description = "Il film è molto avvincente";
    mongo1.ageClassification = AgeClassification.SETTE.name();
    mongo1.filmDirector = new String[]{"Mario Bianchi", "Giacomo Verdi"};
    mongo1.cast = new ArrayList<String[]>();
    mongo1.cast.add(new String[]{"Tom Cruise", "Brad Pitt"});
    mongo1.genres = new ArrayList<String>();
    mongo1.genres.add("COMEDY");
    mongo1.yearOfPublication = "2018-02-03";
    mongo1.episode = 0;
    mongo1.season = 0;
    mongo1.duration = java.time.Duration.ofSeconds(86000);
    //Episode
    MongoProductionPojo mongo2 = new MongoProductionPojo();
    mongo2.id = UUID.randomUUID();
    mongo2.title = "La guerra dei mondi";
    mongo2.description = "Il film è molto bello";
    mongo2.ageClassification = AgeClassification.QUATTORDICI.name();
    mongo2.filmDirector = new String[]{"Antonio Frasca", "Mario Belli"};
    mongo2.cast = new ArrayList<String[]>();
    mongo2.cast.add(new String[]{"Tom Hardy", "George Clooney"});
    mongo2.genres = new ArrayList<String>();
    mongo2.genres.add("ADVENTURE");
    mongo2.yearOfPublication = "2017-03-05";
    mongo2.episode = 4;
    mongo2.season = 2;
    mongo2.duration = java.time.Duration.ofSeconds(800);

    return new Object[]{
        new Object[]{
            mongo1
        },
        new Object[]{
            mongo2
        },
    };
  }

  @Test
  @Parameters(method = "shouldBeMapperFromMongoProductionPojoData")
  public void shouldBeMapperFromMongoProductionPojo(MongoProductionPojo mongo) {

    MongoMapper mongoMapper = new MongoMapper();
    Production p = mongoMapper.fromMongoProductionPojo(mongo);

    assertEquals(p.getId(), mongo.id);
    assertEquals(p.getTitle().getValue(), mongo.title);
    assertEquals(p.getDescription().getValue(), mongo.description);
    assertEquals(p.getAgeClassification().name(), mongo.ageClassification);
    assertEquals(p.getEpisode().getValue(), mongo.episode);
    assertEquals(p.getSeason().getValue(), mongo.season);
    assertArrayEquals(new String[]{p.getDirection().getName(),
        p.getDirection().getSurname()}, mongo.filmDirector);
    assertEquals(p.getGenres().stream().map(Enum::name)
        .collect(Collectors.toCollection(ArrayList::new)), mongo.genres);
    assertArrayEquals(p.getCast().stream().map((Actor a) ->
        new String[]{a.getName(), a.getSurname()})
        .collect(Collectors.toCollection(ArrayList::new))
        .toArray(), mongo.cast.toArray());
    assertEquals(p.getDuration().getFilmDuration(), mongo.duration);
    assertEquals(p.getYearOfPublication().getYearOfPublication(), mongo.yearOfPublication);
  }
}
