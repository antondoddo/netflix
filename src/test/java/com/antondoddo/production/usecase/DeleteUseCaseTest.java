package com.antondoddo.production.usecase;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.antondoddo.production.Production;
import com.antondoddo.production.repository.Repository;
import com.antondoddo.production.repository.exception.CouldNotFindProductionException;
import com.antondoddo.production.repository.exception.CouldNotRemoveProductionException;
import java.util.UUID;
import org.junit.Test;

public class DeleteUseCaseTest {

  @Test
  public void shouldRemoveProductionById() {

    UUID id = mock(UUID.class);
    Production production = mock(Production.class);
    Repository repository = mock(Repository.class);
    when(repository.findProductionById(id)).thenReturn(production);
    doNothing().when(repository).removeProductionById(id);
    DeleteUseCase deleteUseCase = new DeleteUseCase(repository);
    deleteUseCase.execute(id);
    verify(repository).findProductionById(id);
    verify(repository).removeProductionById(id);
  }

  @Test
  public void shouldThrowCouldNotRemoveProductionException() {

    UUID id = mock(UUID.class);
    CouldNotRemoveProductionException exception =
        mock(CouldNotRemoveProductionException.class);
    Production production = mock(Production.class);
    Repository repository = mock(Repository.class);
    when(repository.findProductionById(id)).thenReturn(production);
    doThrow(exception).when(repository).removeProductionById(id);
    DeleteUseCase deleteUseCase = new DeleteUseCase(repository);
    Exception genericException = null;
    try {
      deleteUseCase.execute(id);
    } catch (CouldNotRemoveProductionException ex) {
      genericException = ex;
    }
    assertNotNull(genericException);
    assertSame(genericException, exception);
    verify(repository).findProductionById(id);
    verify(repository).removeProductionById(id);
  }

  @Test
  public void shouldThrowCouldNotFindProductionException() {

    UUID id = mock(UUID.class);
    CouldNotFindProductionException exception = mock(CouldNotFindProductionException.class);
    Repository repository = mock(Repository.class);
    when(repository.findProductionById(id)).thenThrow(exception);
    DeleteUseCase deleteUseCase = new DeleteUseCase(repository);
    Exception genericException = null;
    try {
      deleteUseCase.execute(id);
    } catch (CouldNotFindProductionException ex) {
      genericException = ex;
    }
    assertNotNull(genericException);
    assertSame(exception, genericException);
    verify(repository).findProductionById(id);
  }
}
