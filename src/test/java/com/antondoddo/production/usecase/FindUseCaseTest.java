package com.antondoddo.production.usecase;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.antondoddo.production.Production;
import com.antondoddo.production.repository.Repository;
import com.antondoddo.production.repository.exception.CouldNotFindProductionException;
import java.util.UUID;
import org.junit.Test;

public final class FindUseCaseTest {

  @Test
  public void shouldFindProductionById() {

    UUID id = mock(UUID.class);
    Production production = mock(Production.class);
    Repository repository = mock(Repository.class);
    when(repository.findProductionById(id)).thenReturn(production);
    FindUseCase findUseCase = new FindUseCase(repository);
    Production found = findUseCase.execute(id);
    assertSame(production, found);
    verify(repository).findProductionById(id);
  }

  @Test
  public void shouldThrowCouldNotFindProductionException() {

    UUID id = mock(UUID.class);
    CouldNotFindProductionException exception = mock(CouldNotFindProductionException.class);
    Repository repository = mock(Repository.class);
    when(repository.findProductionById(id)).thenThrow(exception);
    FindUseCase findUseCase = new FindUseCase(repository);
    Exception genericException = null;
    try {
      findUseCase.execute(id);
    } catch (CouldNotFindProductionException ex) {
      genericException = ex;
    }
    assertNotNull(genericException);
    assertSame(exception, genericException);
    verify(repository).findProductionById(id);
  }
}
