package com.antondoddo.production;

import java.util.ArrayList;
import java.util.UUID;

import com.antondoddo.valueobjects.Actor;
import com.antondoddo.valueobjects.Description;
import com.antondoddo.valueobjects.Director;
import com.antondoddo.valueobjects.Duration;
import com.antondoddo.valueobjects.Genre;
import com.antondoddo.valueobjects.Title;
import com.antondoddo.valueobjects.YearOfPublication;
import com.antondoddo.valueobjects.AgeClassification;

public final class Production {
    private final UUID id;
    private final Title title;
    private final Description description;
    private final Duration duration;
    private final YearOfPublication yearOfPublication;
    private final Genre[] genres;
    private final ArrayList<Actor> cast;
    private final Director filmDirector;
    private final AgeClassification ageClassification;
    private final Episode episode;
    private final Season season;

    private Production(
            UUID id,
            Title title,
            Description description,
            Duration duration,
            YearOfPublication yearOfPublication,
            Genre[] genres,
            ArrayList<Actor> cast,
            Director filmDirector,
            AgeClassification ageClassification,
            Episode episode,
            Season season
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.yearOfPublication = yearOfPublication;
        this.genres = genres;
        this.cast = cast;
        this.filmDirector = filmDirector;
        this.ageClassification = ageClassification;
        this.episode = episode;
        this.season = season;
    }

    public static Production ofMovie(
            UUID id,
            Title title,
            Description description,
            Duration duration,
            YearOfPublication yearOfPublication,
            Genre[] genres,
            ArrayList<Actor>  cast,
            Director direction,
            AgeClassification ageClassification
    ) {
        return new Production(
                id,
                title,
                description,
                duration,
                yearOfPublication,
                genres,
                cast,
                direction,
                ageClassification,
                new NullEpisode(),
                new NullSeason()
        );
    }

    public static Production ofEpisode(
            UUID id,
            Title title,
            Description description,
            Duration duration,
            YearOfPublication yearOfPublication,
            Genre[] genres,
            ArrayList<Actor>  cast,
            Director direction,
            AgeClassification ageClassification,
            Episode episode,
            Season season
    ) {
        return new Production(
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

    public YearOfPublication getYearOfPublication() {
        return yearOfPublication;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public  ArrayList<Actor>  getCast() {
        return cast;
    }

    public Director getDirection() {
        return filmDirector;
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
