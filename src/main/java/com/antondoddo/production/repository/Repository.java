package com.antondoddo.production.repository;

import com.antondoddo.production.Production;
import com.antondoddo.production.repository.exception.CouldNotAddProductionException;
import com.antondoddo.production.repository.exception.CouldNotFindProductionException;
import com.antondoddo.production.repository.exception.CouldNotRemoveProductionException;
import java.util.UUID;

public interface Repository {
  Production findProductionById(UUID id) throws CouldNotFindProductionException;

  void addProduction(Production production) throws CouldNotAddProductionException;

  void removeProductionById(UUID id) throws CouldNotRemoveProductionException;
}
