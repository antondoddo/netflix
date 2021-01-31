package com.antondoddo.production;

import com.antondoddo.production.valueobject.Actor;
import com.antondoddo.production.valueobject.AgeClassification;
import com.antondoddo.production.valueobject.Description;
import com.antondoddo.production.valueobject.Director;
import com.antondoddo.production.valueobject.Duration;
import com.antondoddo.production.valueobject.Episode;
import com.antondoddo.production.valueobject.EpisodeImpl;
import com.antondoddo.production.valueobject.Genre;
import com.antondoddo.production.valueobject.ReleaseDate;
import com.antondoddo.production.valueobject.Season;
import com.antondoddo.production.valueobject.SeasonImpl;
import com.antondoddo.production.valueobject.Title;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public final class ProductionObjectMother {

  private static Faker faker = new Faker();

  public static Production createEpisode() {
    return Production.ofEpisode(
        UUID.randomUUID(),
        getRandomTitle(),
        getRandomDescription(),
        getRandomDuration(),
        getRandomReleaseDate(),
        getRandomGenre(),
        getRandomCast(),
        getRandomDirector(),
        getRandomAgeClassification(),
        getRandomEpisode(),
        getRandomSeason()
    );
  }

  public static Production createMovie() {
    return Production.ofMovie(
        UUID.randomUUID(),
        getRandomTitle(),
        getRandomDescription(),
        getRandomDuration(),
        getRandomReleaseDate(),
        getRandomGenre(),
        getRandomCast(),
        getRandomDirector(),
        getRandomAgeClassification()
    );
  }

  private static Title getRandomTitle() {
    return new Title(
        faker.options().option(
            "The lord of the Rings",
            "Titanic",
            "The Simpsons",
            "World World Z"
        ));
  }

  private static Description getRandomDescription() {
    return new Description(
        faker.options().option(
            "Lorem ipsum dolor sit amet.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        ));
  }

  private static Duration getRandomDuration() {
    return new Duration(
        java.time.Duration.ofSeconds(faker.options().option(10, 100, 1000))
    );
  }

  private static ReleaseDate getRandomReleaseDate() {
    return new ReleaseDate(
        faker.options().option(
            LocalDate.parse("2010-10-10"),
            LocalDate.parse("1990-11-03"),
            LocalDate.parse("1992-09-04")
        )
    );
  }

  private static ArrayList<Genre> getRandomGenre() {
    ArrayList<Genre> genres = new ArrayList<>();
    int random = faker.random().nextInt(1, 5);
    for (int i = 0; i < random; i++) {
      genres.add(faker.options().option(
          Genre.COMEDY,
          Genre.THRILLER,
          Genre.CRIME,
          Genre.FANTASY,
          Genre.WAR,
          Genre.DRAMA,
          Genre.HORROR,
          Genre.ADVENTURE,
          Genre.ACTION,
          Genre.ROMANTIC,
          Genre.DOCUMENTARY
      ));
    }
    return genres;
  }

  private static ArrayList<Actor> getRandomCast() {
    ArrayList<Actor> cast = new ArrayList<>();
    int random = faker.random().nextInt(3, 10);
    for (int i = 0; i < random; i++) {
      cast.add(new Actor(
          faker.options().option("Al", "John", "Micheal", "Frank"),
          faker.options().option("Red", "Blue", "Bay", "Sea", "Di Caprio")
      ));
    }

    return cast;
  }

  private static Director getRandomDirector() {
    return new Director(
        faker.options().option("Al", "John", "Micheal", "Frank"),
        faker.options().option("Red", "Blue", "Bay", "Sea", "Di Caprio")
    );
  }

  private static AgeClassification getRandomAgeClassification() {
    return faker.options().option(
        AgeClassification.TRE,
        AgeClassification.SETTE,
        AgeClassification.QUATTORDICI,
        AgeClassification.DICIOTTO
    );
  }

  private static Episode getRandomEpisode() {
    return new EpisodeImpl(
        faker.options().option(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40
        )
    );
  }

  private static Season getRandomSeason() {
    return new SeasonImpl(
        faker.options().option(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40
        )
    );
  }
}