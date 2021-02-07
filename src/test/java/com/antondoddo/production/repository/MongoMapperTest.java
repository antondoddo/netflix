package com.antondoddo.production.repository;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.antondoddo.production.ObjectMother;
import com.antondoddo.production.Production;
import com.antondoddo.production.valueobject.Actor;
import com.antondoddo.production.valueobject.AgeClassification;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public final class MongoMapperTest {
  protected static Object[] shouldBeMapperFromProductionData() {
    return new Object[]{
        new Object[]{ObjectMother.createMovie()},
        new Object[]{ObjectMother.createEpisode()}
    };
  }

  @Test
  @Parameters(method = "shouldBeMapperFromProductionData")
  public void shouldBeMapperFromProduction(Production p) {

    MongoMapper mongoMapper = new MongoMapper();
    MongoProductionPojo mongoProductionPojo = mongoMapper.fromProduction(p);
    List<String> expectedDirector = new ArrayList<>();
    expectedDirector.add(p.getDirection().getName());
    expectedDirector.add(p.getDirection().getSurname());
    assertEquals(mongoProductionPojo.id, p.getId());
    assertEquals(mongoProductionPojo.title, p.getTitle().getValue());
    assertEquals(mongoProductionPojo.description, p.getDescription().getValue());
    assertEquals(mongoProductionPojo.ageClassification, p.getAgeClassification().name());
    assertEquals(mongoProductionPojo.episode, p.getEpisode().getValue());
    assertEquals(mongoProductionPojo.season, p.getSeason().getValue());
    assertEquals(mongoProductionPojo.filmDirector, expectedDirector);
    assertEquals(mongoProductionPojo.genres, p.getGenres().stream()
        .map(Enum::name).collect(Collectors.toCollection(ArrayList::new)));
    assertArrayEquals(mongoProductionPojo.cast.toArray(), p.getCast().stream().map((Actor a) ->
        Arrays.asList(a.getName(), a.getSurname()))
        .collect(Collectors.toCollection(ArrayList::new)).toArray());
    assertEquals(mongoProductionPojo.duration, p.getDuration().getFilmDuration().toMillis());
    assertEquals(mongoProductionPojo.releaseDate, p.getReleaseDate().getValue().toString());
  }

  protected static Object[] shouldBeMapperFromMongoProductionPojoData() {
    //Film
    MongoProductionPojo mongo1 = new MongoProductionPojo();
    List<List<String>> cast = new ArrayList<>();
    cast.add(0, Arrays.asList("Tom", "Cruise"));
    cast.add(1, Arrays.asList("Brad", "Pitt"));
    mongo1.id = UUID.randomUUID();
    mongo1.title = "Ma che bella giornata";
    mongo1.description = "Il film è molto avvincente";
    mongo1.ageClassification = AgeClassification.SETTE.name();
    mongo1.filmDirector = Arrays.asList("Mario", "Bianchi");
    mongo1.cast = cast;
    mongo1.genres = new ArrayList<String>();
    mongo1.genres.add("COMEDY");
    mongo1.releaseDate = "2018-02-03";
    mongo1.episode = 0;
    mongo1.season = 0;
    mongo1.duration = 8600;

    //Episode
    List<List<String>> cast2 = new ArrayList<>();
    cast2.add(0, Arrays.asList("Tom", "Hardy"));
    cast2.add(1, Arrays.asList("George", "Clooney"));
    MongoProductionPojo mongo2 = new MongoProductionPojo();
    mongo2.id = UUID.randomUUID();
    mongo2.title = "La guerra dei mondi";
    mongo2.description = "Il film è molto bello";
    mongo2.ageClassification = AgeClassification.QUATTORDICI.name();
    mongo2.filmDirector = Arrays.asList("Antonio", "Frasca");
    mongo2.cast = cast2;
    mongo2.genres = new ArrayList<String>();
    mongo2.genres.add("ADVENTURE");
    mongo2.releaseDate = "2017-03-05";
    mongo2.episode = 4;
    mongo2.season = 2;
    mongo2.duration = 8000;

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
    assertEquals(Arrays.asList(p.getDirection().getName(),
        p.getDirection().getSurname()), mongo.filmDirector);
    assertEquals(p.getGenres().stream().map(Enum::name)
        .collect(Collectors.toCollection(ArrayList::new)), mongo.genres);
    assertArrayEquals(p.getCast().stream().map((Actor a) ->
        Arrays.asList(a.getName(), a.getSurname()))
        .collect(Collectors.toCollection(ArrayList::new))
        .toArray(), mongo.cast.toArray());
    assertEquals(p.getDuration().getFilmDuration().toMillis(), mongo.duration);
    assertEquals(p.getReleaseDate().getValue().toString(), mongo.releaseDate);
  }
}
