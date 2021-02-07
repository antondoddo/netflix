package com.antondoddo.production.usecase;

import com.antondoddo.production.Production;
import com.antondoddo.production.repository.Repository;
import com.antondoddo.production.repository.exception.CouldNotAddProductionException;

public class AddUseCase {

  private Repository repository;

  public AddUseCase(Repository repository) {
    this.repository = repository;
  }

  public void execute(Production production) throws CouldNotAddProductionException {
    repository.addProduction(production);
  }
}
