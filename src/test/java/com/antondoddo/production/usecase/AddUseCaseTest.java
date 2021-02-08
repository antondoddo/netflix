package com.antondoddo.production.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.antondoddo.production.ObjectMother;
import com.antondoddo.production.Production;
import com.antondoddo.production.repository.Repository;
import com.antondoddo.production.repository.exception.CouldNotAddProductionException;
import com.antondoddo.production.valueobject.NullEpisode;
import com.antondoddo.production.valueobject.NullSeason;
import java.util.UUID;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public final class AddUseCaseTest {

  protected Object[] shouldAddProductionData() {

    return new Object[]{
        new Object[]{
            new AddUseCaseDto(null,
                ObjectMother.getRandomTitle(),
                ObjectMother.getRandomDescription(),
                ObjectMother.getRandomDuration(),
                ObjectMother.getRandomReleaseDate(),
                ObjectMother.getRandomGenre(),
                ObjectMother.getRandomCast(),
                ObjectMother.getRandomDirector(),
                ObjectMother.getRandomAgeClassification(),
                ObjectMother.getRandomEpisode(),
                ObjectMother.getRandomSeason())
        },
        new Object[]{
            new AddUseCaseDto(UUID.randomUUID(),
                ObjectMother.getRandomTitle(),
                ObjectMother.getRandomDescription(),
                ObjectMother.getRandomDuration(),
                ObjectMother.getRandomReleaseDate(),
                ObjectMother.getRandomGenre(),
                ObjectMother.getRandomCast(),
                ObjectMother.getRandomDirector(),
                ObjectMother.getRandomAgeClassification(),
                ObjectMother.getRandomEpisode(),
                ObjectMother.getRandomSeason())
        },
        new Object[]{
            new AddUseCaseDto(UUID.randomUUID(),
                ObjectMother.getRandomTitle(),
                ObjectMother.getRandomDescription(),
                ObjectMother.getRandomDuration(),
                ObjectMother.getRandomReleaseDate(),
                ObjectMother.getRandomGenre(),
                ObjectMother.getRandomCast(),
                ObjectMother.getRandomDirector(),
                ObjectMother.getRandomAgeClassification(),
                new NullEpisode(),
                new NullSeason())
        },
        new Object[]{
            new AddUseCaseDto(null,
                ObjectMother.getRandomTitle(),
                ObjectMother.getRandomDescription(),
                ObjectMother.getRandomDuration(),
                ObjectMother.getRandomReleaseDate(),
                ObjectMother.getRandomGenre(),
                ObjectMother.getRandomCast(),
                ObjectMother.getRandomDirector(),
                ObjectMother.getRandomAgeClassification(),
                new NullEpisode(),
                new NullSeason())
        },
    };
  }

  @Test
  @Parameters(method = "shouldAddProductionData")
  public void shouldAddProduction(AddUseCaseDto addUseCaseDto
  ) {

    Repository repository = mock(Repository.class);
    doNothing().when(repository).addProduction(argThat((Production production) ->
        production.getAgeClassification().equals(addUseCaseDto
            .getAgeClassification())
            && production.getCast().equals(addUseCaseDto
            .getCast())
            && production.getDescription().equals(addUseCaseDto
            .getDescription())
            && production.getDirection().equals(addUseCaseDto
            .getDirector())
            && production.getTitle().equals(addUseCaseDto
            .getTitle())
            && production.getGenres().equals(addUseCaseDto
            .getGenres())
            && production.getDuration().equals(addUseCaseDto
            .getDuration())
            && production.getReleaseDate().equals(addUseCaseDto
            .getReleaseDate())
            && production.getEpisode().equals(addUseCaseDto
            .getEpisode())
            && production.getSeason().equals(addUseCaseDto
            .getSeason())
    ));
    AddUseCase addUseCase = new AddUseCase(repository);
    Production prod = addUseCase.execute(addUseCaseDto
    );
    if (addUseCaseDto
        .getId() != null) {
      assertEquals(prod.getId(), addUseCaseDto
          .getId());
    }
    assertNotNull(prod.getId());
    assertEquals(prod.getAgeClassification(), addUseCaseDto
        .getAgeClassification());
    assertEquals(prod.getCast(), addUseCaseDto
        .getCast());
    assertEquals(prod.getDescription(), addUseCaseDto
        .getDescription());
    assertEquals(prod.getDirection(), addUseCaseDto
        .getDirector());
    assertEquals(prod.getTitle(), addUseCaseDto
        .getTitle());
    assertEquals(prod.getGenres(), addUseCaseDto
        .getGenres());
    assertEquals(prod.getDuration(), addUseCaseDto
        .getDuration());
    assertEquals(prod.getReleaseDate(), addUseCaseDto
        .getReleaseDate());
    assertEquals(prod.getEpisode(), addUseCaseDto
        .getEpisode());
    assertEquals(prod.getSeason(), addUseCaseDto
        .getSeason());
    verify(repository).addProduction(argThat((Production production) ->
        production.getTitle().equals(addUseCaseDto
            .getTitle())));
  }

  @Test
  public void shouldThrowCouldNotAddProductionException() {

    AddUseCaseDto addUseCaseDto
        = mock(AddUseCaseDto.class);
    when(addUseCaseDto
        .getEpisode()).thenReturn(new NullEpisode());
    CouldNotAddProductionException exception = new CouldNotAddProductionException();
    Repository repository = mock(Repository.class);
    doThrow(exception).when(repository).addProduction(argThat((Production production) ->
        production.getEpisode().equals(new NullEpisode())));
    AddUseCase addUseCase = new AddUseCase(repository);
    Exception genericException = null;
    try {
      addUseCase.execute(addUseCaseDto
      );
    } catch (CouldNotAddProductionException e) {
      genericException = e;
    }
    assertNotNull(genericException);
    assertSame(exception, genericException);
    verify(repository).addProduction(argThat((Production production) ->
        production.getEpisode().equals(new NullEpisode())));
  }
}
