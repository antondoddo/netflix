package com.antondoddo.production.usecase;

import com.antondoddo.production.Production;
import com.antondoddo.production.repository.Repository;
import com.antondoddo.production.repository.exception.CouldNotFindProductionException;
import java.util.UUID;

public class FindUseCase {

  private Repository repository;

  public FindUseCase(Repository repository) {
    this.repository = repository;
  }

  public Production execute(UUID id) throws CouldNotFindProductionException {
    return repository.findProductionById(id);
  }
}
