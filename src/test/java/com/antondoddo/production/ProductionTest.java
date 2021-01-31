package com.antondoddo.production;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;

import com.antondoddo.production.valueobject.Actor;
import com.antondoddo.production.valueobject.AgeClassification;
import com.antondoddo.production.valueobject.Description;
import com.antondoddo.production.valueobject.Director;
import com.antondoddo.production.valueobject.Duration;
import com.antondoddo.production.valueobject.Episode;
import com.antondoddo.production.valueobject.EpisodeImpl;
import com.antondoddo.production.valueobject.Genre;
import com.antondoddo.production.valueobject.NullEpisode;
import com.antondoddo.production.valueobject.NullSeason;
import com.antondoddo.production.valueobject.ReleaseDate;
import com.antondoddo.production.valueobject.Season;
import com.antondoddo.production.valueobject.SeasonImpl;
import com.antondoddo.production.valueobject.Title;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import org.junit.Test;

public final class ProductionTest {

  @Test
  public void ofMovieShouldReturnSameArgumentsAsInjected() {
    UUID id = UUID.randomUUID();
    Title title = new Title("A title");
    Description description = new Description("A description");
    Duration duration = new Duration(java.time.Duration.ofSeconds(100));
    ReleaseDate releaseDate = new ReleaseDate("2000-01-02");
    ArrayList<Genre> genres = new ArrayList<>(Arrays.asList(Genre.DRAMA, Genre.COMEDY));
    ArrayList<Actor> cast = new ArrayList<>(Arrays.asList(new Actor("Micheal", "Jackson")));
    Director direction = new Director("John", "Snow");
    AgeClassification ageClassification = AgeClassification.TRE;

    Production production = Production.ofMovie(
        id,
        title,
        description,
        duration,
        releaseDate,
        genres,
        cast,
        direction,
        ageClassification
    );

    assertSame(id, production.getId());
    assertSame(title, production.getTitle());
    assertSame(description, production.getDescription());
    assertSame(duration, production.getDuration());
    assertSame(releaseDate, production.getReleaseDate());
    assertSame(genres, production.getGenres());
    assertSame(cast, production.getCast());
    assertSame(direction, production.getDirection());
    assertSame(ageClassification, production.getAgeClassification());
    assertEquals(new NullEpisode(), production.getEpisode());
    assertEquals(new NullSeason(), production.getSeason());
  }

  @Test
  public void ofEpisodeShouldReturnSameArgumentsAsInjected() {
    UUID id = UUID.randomUUID();
    Title title = new Title("A title");
    Description description = new Description("A description");
    Duration duration = new Duration(java.time.Duration.ofSeconds(100));
    ReleaseDate releaseDate = new ReleaseDate("2000-01-02");
    ArrayList<Genre> genres = new ArrayList<>(Arrays.asList(Genre.DRAMA, Genre.COMEDY));
    ArrayList<Actor> cast = new ArrayList<>(Arrays.asList(new Actor("Micheal", "Jackson")));
    Director direction = new Director("John", "Snow");
    AgeClassification ageClassification = AgeClassification.TRE;
    Episode episode = new EpisodeImpl(1);
    Season season = new SeasonImpl(1);

    Production production = Production.ofEpisode(
        id,
        title,
        description,
        duration,
        releaseDate,
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
    assertSame(releaseDate, production.getReleaseDate());
    assertSame(genres, production.getGenres());
    assertSame(cast, production.getCast());
    assertSame(direction, production.getDirection());
    assertSame(ageClassification, production.getAgeClassification());
    assertSame(episode, production.getEpisode());
    assertSame(season, production.getSeason());
  }
}
