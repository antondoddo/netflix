package com.antondoddo.netflix.movie;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.Duration;
import java.util.UUID;

import static org.junit.Assert.assertSame;

@RunWith(JUnitParamsRunner.class)
public class MovieTest {

    @Test
    @Parameters(method = "movieData")
    public void shouldCreateMovieWithoutAlteringProperties(UUID id, String title, Duration duration) {
        Movie movie = new Movie(id, title, duration);
        assertSame(id, movie.getId());
        assertSame(title, movie.getTitle());
        assertSame(duration, movie.getDuration());
    }

    protected static Object[] movieData() {
        return new Object[]{
                new Object[]{UUID.fromString("f7d1f1d4-611d-4d56-ac9d-511d62d86691"), "A title", Duration.ofSeconds(3000)},
        };
    }
}
