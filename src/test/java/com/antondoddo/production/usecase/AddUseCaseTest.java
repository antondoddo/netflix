package com.antondoddo.production.usecase;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.antondoddo.production.Production;
import com.antondoddo.production.ProductionObjectMother;
import com.antondoddo.production.repository.Repository;
import com.antondoddo.production.repository.exception.CouldNotAddProductionException;
import java.util.UUID;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class AddUseCaseTest {

  protected Object[] shouldAddProductionData() {
    UUID id1 = UUID.randomUUID();
    UUID id2 = UUID.randomUUID();
    return new Object[]{
        new Object[]{
            ProductionObjectMother.createEpisode(id1)
        },
        new Object[]{
            ProductionObjectMother.createMovie(id2)
        }
    };
  }

  @Test
  @Parameters(method = "shouldAddProductionData")
  public void shouldAddProduction(Production production) {

    Repository repository = mock(Repository.class);
    doNothing().when(repository).addProduction(production);
    AddUseCase addUseCase = new AddUseCase(repository);
    addUseCase.execute(production);
    verify(repository).addProduction(production);
  }

  @Test
  public void shouldThrowCouldNotAddProductionException() {

    Production production = mock(Production.class);
    CouldNotAddProductionException exception = new CouldNotAddProductionException();
    Repository repository = mock(Repository.class);
    doThrow(exception).when(repository).addProduction(production);
    AddUseCase addUseCase = new AddUseCase(repository);
    Exception genericException = null;
    try {
      addUseCase.execute(production);
    } catch (CouldNotAddProductionException e) {
      genericException = e;
    }
    assertNotNull(genericException);
    assertSame(exception, genericException);
    verify(repository).addProduction(production);
  }
}
