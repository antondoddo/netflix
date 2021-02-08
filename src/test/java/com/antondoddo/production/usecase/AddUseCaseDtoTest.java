package com.antondoddo.production.usecase;

import static org.junit.Assert.assertSame;

import com.antondoddo.production.ObjectMother;
import com.antondoddo.production.valueobject.Actor;
import com.antondoddo.production.valueobject.AgeClassification;
import com.antondoddo.production.valueobject.Description;
import com.antondoddo.production.valueobject.Director;
import com.antondoddo.production.valueobject.Duration;
import com.antondoddo.production.valueobject.Episode;
import com.antondoddo.production.valueobject.Genre;
import com.antondoddo.production.valueobject.ReleaseDate;
import com.antondoddo.production.valueobject.Season;
import com.antondoddo.production.valueobject.Title;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.Test;


public final class AddUseCaseDtoTest {

  @Test
  public void shouldCreateAddUseCaseDto() {

    UUID id = UUID.randomUUID();
    Title title = ObjectMother.getRandomTitle();
    Duration duration = ObjectMother.getRandomDuration();
    ReleaseDate releaseDate = ObjectMother.getRandomReleaseDate();
    ArrayList<Genre> genres = ObjectMother.getRandomGenre();
    ArrayList<Actor> cast = ObjectMother.getRandomCast();
    Director director = ObjectMother.getRandomDirector();
    AgeClassification ageclassification = ObjectMother.getRandomAgeClassification();
    Description description = ObjectMother.getRandomDescription();
    Episode episode = ObjectMother.getRandomEpisode();
    Season season = ObjectMother.getRandomSeason();

    AddUseCaseDto addUseCaseDto = new AddUseCaseDto(
        id,
        title,
        description,
        duration,
        releaseDate,
        genres,
        cast,
        director,
        ageclassification,
        episode,
        season
    );

    assertSame(id, addUseCaseDto.getId());
    assertSame(title, addUseCaseDto.getTitle());
    assertSame(duration, addUseCaseDto.getDuration());
    assertSame(releaseDate, addUseCaseDto.getReleaseDate());
    assertSame(genres, addUseCaseDto.getGenres());
    assertSame(cast, addUseCaseDto.getCast());
    assertSame(director, addUseCaseDto.getDirector());
    assertSame(ageclassification, addUseCaseDto.getAgeClassification());
    assertSame(description, addUseCaseDto.getDescription());
    assertSame(episode, addUseCaseDto.getEpisode());
    assertSame(season, addUseCaseDto.getSeason());
  }
}
