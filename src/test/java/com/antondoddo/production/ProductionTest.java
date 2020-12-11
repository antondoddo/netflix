package com.antondoddo.production;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import com.antondoddo.production.valueobject.Actor;
import com.antondoddo.production.valueobject.AgeClassification;
import com.antondoddo.production.valueobject.Description;
import com.antondoddo.production.valueobject.Director;
import com.antondoddo.production.valueobject.Duration;
import com.antondoddo.production.valueobject.Episode;
import com.antondoddo.production.valueobject.Genre;
import com.antondoddo.production.valueobject.NullEpisode;
import com.antondoddo.production.valueobject.NullSeason;
import com.antondoddo.production.valueobject.RealEpisode;
import com.antondoddo.production.valueobject.RealSeason;
import com.antondoddo.production.valueobject.Season;
import com.antondoddo.production.valueobject.Title;
import com.antondoddo.production.valueobject.YearOfPublication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ProductionTest {

  protected static Object[] shouldCreateMovieData() {
    return new Object[]{
        new Object[]{
            UUID.fromString("2c59f410-c3a6-4e18-82d1-93802e249c2a"),
            new Title("Natale in India"),
            new Description("Un film davvero poco divertente"),
            new Duration(java.time.Duration.ofMinutes(120)),
            new YearOfPublication(2020),
            new ArrayList<Genre>(Arrays.asList(Genre.COMEDY)),
            new ArrayList<Actor>(Arrays.asList(new Actor("Christian", "De Sica"))),
            new Director("De Laurentis", "Bros"),
            AgeClassification.TRE
        },
    };
  }

  @Test
  @Parameters(method = "shouldCreateMovieData")
  public void shouldCreateMovie(
      UUID id,
      Title title,
      Description description,
      Duration duration,
      YearOfPublication yearOfPublication,
      ArrayList<Genre> genres,
      ArrayList<Actor> cast,
      Director direction,
      AgeClassification ageClassification
  ) {
    Production production = Production.ofMovie(
        id,
        title,
        description,
        duration,
        yearOfPublication,
        genres,
        cast,
        direction,
        ageClassification
    );

    assertSame(id, production.getId());
    assertSame(title, production.getTitle());
    assertSame(description, production.getDescription());
    assertSame(duration, production.getDuration());
    assertSame(yearOfPublication, production.getYearOfPublication());
    assertSame(genres, production.getGenres());
    assertSame(cast, production.getCast());
    assertSame(direction, production.getDirection());
    assertSame(ageClassification, production.getAgeClassification());
    assertEquals(new NullEpisode(), production.getEpisode());
    assertEquals(new NullSeason(), production.getSeason());
  }


  protected static Object[] shouldCreateEpisodeData() {
    return new Object[]{
        new Object[]{
            UUID.fromString("2c59f410-c3a6-4e18-82d1-93802e249c2a"),
            new Title("Natale in India"),
            new Description("Un film davvero poco divertente"),
            new Duration(java.time.Duration.ofMinutes(120)),
            new YearOfPublication(2020),
            new ArrayList<Genre>(Arrays.asList(Genre.COMEDY)),
            new ArrayList<Actor>(Arrays.asList(new Actor("Christian", "De Sica"))),
            new Director("De Laurentis", "Bros"),
            AgeClassification.TRE,
            new RealEpisode(10),
            new RealSeason(1),
        },
    };
  }

  @Test
  @Parameters(method = "shouldCreateEpisodeData")
  public void shouldCreateEpisode(
      UUID id,
      Title title,
      Description description,
      Duration duration,
      YearOfPublication yearOfPublication,
      ArrayList<Genre> genres,
      ArrayList<Actor> cast,
      Director direction,
      AgeClassification ageClassification,
      Episode episode,
      Season season
  ) {
    Production production = Production.ofEpisode(
        id,
        title,
        description,
        duration,
        yearOfPublication,
        genres,
        cast,
        direction,
        ageClassification,
        episode,
        season
    );

    assertSame(id, production.getId());
    assertSame(title, production.getTitle());
    assertSame(description, production.getDescription());
    assertSame(duration, production.getDuration());
    assertSame(yearOfPublication, production.getYearOfPublication());
    assertSame(genres, production.getGenres());
    assertSame(cast, production.getCast());
    assertSame(direction, production.getDirection());
    assertSame(ageClassification, production.getAgeClassification());
    assertSame(episode, production.getEpisode());
    assertSame(season, production.getSeason());
  }
}
