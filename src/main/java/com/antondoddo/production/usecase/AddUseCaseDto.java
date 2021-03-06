package com.antondoddo.production.usecase;

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

public final class AddUseCaseDto {

  private final UUID id;
  private final Title title;
  private final Description description;
  private final Duration duration;
  private final ReleaseDate releaseDate;
  private final ArrayList<Genre> genres;
  private final ArrayList<Actor> cast;
  private final Director director;
  private final AgeClassification ageClassification;
  private final Episode episode;
  private final Season season;

  public AddUseCaseDto(UUID id, Title title, Description description,
                       Duration duration, ReleaseDate releaseDate,
                       ArrayList<Genre> genres, ArrayList<Actor> cast,
                       Director filmDirector, AgeClassification ageClassification,
                       Episode episode, Season season) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.duration = duration;
    this.releaseDate = releaseDate;
    this.genres = genres;
    this.cast = cast;
    this.director = filmDirector;
    this.ageClassification = ageClassification;
    this.episode = episode;
    this.season = season;
  }

  public UUID getId() {
    return id;
  }

  public Title getTitle() {
    return title;
  }

  public Description getDescription() {
    return description;
  }

  public Duration getDuration() {
    return duration;
  }

  public ReleaseDate getReleaseDate() {
    return releaseDate;
  }

  public ArrayList<Genre> getGenres() {
    return genres;
  }

  public ArrayList<Actor> getCast() {
    return cast;
  }

  public Director getDirector() {
    return director;
  }

  public AgeClassification getAgeClassification() {
    return ageClassification;
  }

  public Episode getEpisode() {
    return episode;
  }

  public Season getSeason() {
    return season;
  }
}
