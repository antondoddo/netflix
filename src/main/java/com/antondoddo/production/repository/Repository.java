package com.antondoddo.production.repository;

import com.antondoddo.production.Production;
import com.antondoddo.production.repository.exception.CouldNotFindProductionException;
import com.antondoddo.production.repository.exception.CouldNotRemoveProductionException;
import com.antondoddo.production.repository.exception.CouldNotAddProductionException;
import java.util.UUID;

public interface Repository {
  public Production findProductionById(UUID id) throws CouldNotFindProductionException;
  public void addProduction(Production production) throws CouldNotAddProductionException;
  public void removeProductionById(UUID id) throws CouldNotRemoveProductionException;
}
