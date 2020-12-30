package com.antondoddo.production.repository;

import static com.mongodb.client.model.Filters.eq;

import com.antondoddo.production.Production;
import com.antondoddo.production.repository.exception.CouldNotAddProductionException;
import com.antondoddo.production.repository.exception.CouldNotFindProductionException;
import com.antondoddo.production.repository.exception.CouldNotRemoveProductionException;
import com.mongodb.client.MongoCollection;
import java.util.UUID;

public class MongoRepository implements Repository {

  private final MongoCollection<MongoProductionPojo> mongoCollection;
  private final MongoMapper mapper;

  public MongoRepository(MongoCollection<MongoProductionPojo> mongoCollection, MongoMapper mapper) {
    this.mongoCollection = mongoCollection;
    this.mapper = mapper;
  }

  @Override
  public Production findProductionById(UUID id) throws CouldNotFindProductionException {
    MongoProductionPojo foundProduction;
    try {
      foundProduction = this.mongoCollection.find(eq("_id", id)).first();
    } catch (Exception e) {
      throw new CouldNotFindProductionException(e);
    }
    if (foundProduction == null) {
      return null;
    }
    return this.mapper.fromMongoProductionPojo(foundProduction);
  }

  @Override
  public void addProduction(Production production) throws CouldNotAddProductionException {
    MongoProductionPojo mongoProductionPojo = mapper.fromProduction(production);
    try {
      this.mongoCollection.insertOne(mongoProductionPojo);
    } catch (Exception e) {
      throw new CouldNotAddProductionException(e);
    }
  }

  @Override
  public void removeProductionById(UUID id) throws CouldNotRemoveProductionException {
    try {
      this.mongoCollection.deleteOne(eq("_id", id));
    } catch (Exception e) {
      throw new CouldNotRemoveProductionException(e);
    }
  }
}
