package com.antondoddo.production.repository;

import static com.mongodb.client.model.Filters.eq;

import com.antondoddo.production.Production;
import com.antondoddo.production.repository.exception.CouldNotAddProductionException;
import com.antondoddo.production.repository.exception.CouldNotFindProductionException;
import com.antondoddo.production.repository.exception.CouldNotRemoveProductionException;
import com.mongodb.client.MongoCollection;
import java.util.UUID;

public class MongoRepository implements Repository {

  MongoCollection<MongoProductionPojo> mongoCollection;

  // MongoCollection<MongoProductionPojo> col = db.getCollection("productions", MongoProductionPojo.class);
  public MongoRepository(MongoCollection<MongoProductionPojo> mongoCollection) {
    this.mongoCollection = mongoCollection;
  }

  @Override
  public Production findProductionById(UUID id) throws CouldNotFindProductionException {

    MongoProductionPojo foundProduction = this.mongoCollection.find(eq("_id", id)).first();


    return null;
  }

  @Override
  public void addProduction(Production production) throws CouldNotAddProductionException {

  }

  @Override
  public void removeProductionById(UUID id) throws CouldNotRemoveProductionException {

  }
}
