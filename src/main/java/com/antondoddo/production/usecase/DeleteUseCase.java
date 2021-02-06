package com.antondoddo.production.usecase;

import com.antondoddo.production.Production;
import com.antondoddo.production.repository.Repository;
import com.antondoddo.production.repository.exception.CouldNotFindProductionException;
import com.antondoddo.production.repository.exception.CouldNotRemoveProductionException;
import java.util.UUID;

public class DeleteUseCase {
  private Repository repository;

  public DeleteUseCase(Repository repository) {
    this.repository = repository;
  }

  public void execute(UUID id) throws CouldNotFindProductionException,
      CouldNotRemoveProductionException {
    Production production = repository.findProductionById(id);
    if (production != null) {
      repository.removeProductionById(id);
    }
  }
}
