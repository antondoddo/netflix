package com.antondoddo.netflix.movie;

import java.time.Duration;
import java.util.UUID;

public final class Movie {
    private UUID id;
    private String title;
    private Duration duration;


    public Movie(
            UUID id,
            String title,
            Duration duration
    ) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public UUID getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public Duration getDuration() {
        return this.duration;
    }
}
