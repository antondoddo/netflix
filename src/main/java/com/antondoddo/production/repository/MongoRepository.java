package com.antondoddo.production.repository;

import static com.mongodb.client.model.Filters.eq;

import com.antondoddo.production.Production;
import com.antondoddo.production.repository.exception.CouldNotAddProductionException;
import com.antondoddo.production.repository.exception.CouldNotFindProductionException;
import com.antondoddo.production.repository.exception.CouldNotRemoveProductionException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import java.util.UUID;

public final class MongoRepository implements Repository {

  private final MongoCollection<MongoProductionPojo> mongoCollection;
  private final MongoMapper mapper;

  public MongoRepository(MongoCollection<MongoProductionPojo> mongoCollection, MongoMapper mapper) {
    this.mongoCollection = mongoCollection;
    this.mapper = mapper;
  }

  @Override
  public Production findProductionById(UUID id) throws CouldNotFindProductionException {

    MongoProductionPojo foundProduction = this.mongoCollection.find(eq("_id", id)).first();

    if (foundProduction == null) {
      throw new CouldNotFindProductionException();
    }
    return this.mapper.fromMongoProductionPojo(foundProduction);
  }

  @Override
  public void addProduction(Production production) throws CouldNotAddProductionException {
    MongoProductionPojo mongoProductionPojo = mapper.fromProduction(production);
    InsertOneResult result = this.mongoCollection.insertOne(mongoProductionPojo);
    if (result.wasAcknowledged()) {
      return;
    }
    throw new CouldNotAddProductionException();
  }

  @Override
  public void removeProductionById(UUID id) throws CouldNotRemoveProductionException {
    DeleteResult result = this.mongoCollection.deleteOne(eq("_id", id));
    if (result.wasAcknowledged()) {
      return;
    }
    throw new CouldNotRemoveProductionException();
  }
}
