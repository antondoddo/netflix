package com.antondoddo.production.repository;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.antondoddo.production.Production;
import com.antondoddo.production.repository.exception.CouldNotAddProductionException;
import com.antondoddo.production.repository.exception.CouldNotFindProductionException;
import com.antondoddo.production.repository.exception.CouldNotRemoveProductionException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import java.util.UUID;
import org.junit.Test;

public class MongoRepositoryTest {

  @Test
  public void shouldCreateMongoRepository() {
    MongoCollection<MongoProductionPojo> mockedCollection = mock(MongoCollection.class);
    MongoMapper mapper = mock(MongoMapper.class);
    new MongoRepository(mockedCollection, mapper);
  }

  @Test
  public void shouldRemoveProductionById() {
    UUID id = mock(UUID.class);

    DeleteResult deleteResult = mock(DeleteResult.class);
    when(deleteResult.wasAcknowledged()).thenReturn(true);

    MongoCollection<MongoProductionPojo> mockedCollection = mock(MongoCollection.class);
    when(mockedCollection.deleteOne(eq("_id", id))).thenReturn(deleteResult);

    MongoMapper mapper = mock(MongoMapper.class);

    MongoRepository mongo = new MongoRepository(mockedCollection, mapper);
    mongo.removeProductionById(id);
  }

  @Test
  public void shouldThrowCouldNotRemoveProductionException() {
    CouldNotRemoveProductionException exception = null;
    UUID id = mock(UUID.class);

    DeleteResult deleteResult = mock(DeleteResult.class);
    when(deleteResult.wasAcknowledged()).thenReturn(false);

    MongoCollection<MongoProductionPojo> mockedCollection = mock(MongoCollection.class);
    when(mockedCollection.deleteOne(eq("_id", id))).thenReturn(deleteResult);

    MongoMapper mapper = mock(MongoMapper.class);

    MongoRepository mongo = new MongoRepository(mockedCollection, mapper);

    try {
      mongo.removeProductionById(id);

    } catch (CouldNotRemoveProductionException ex) {
      exception = ex;
    }
    assertNotNull(exception);
    assertEquals("Non ho potuto cancellare la produzione", exception.getMessage());
  }

  @Test
  public void shouldAddProduction() {

    Production production = mock(Production.class);

    MongoProductionPojo mongoProductionPojo = mock(MongoProductionPojo.class);

    MongoMapper mapper = mock(MongoMapper.class);
    when(mapper.fromProduction(production)).thenReturn(mongoProductionPojo);

    InsertOneResult result = mock(InsertOneResult.class);
    when(result.wasAcknowledged()).thenReturn(true);

    MongoCollection<MongoProductionPojo> mockedCollection = mock(MongoCollection.class);
    when(mockedCollection.insertOne(mongoProductionPojo)).thenReturn(result);

    MongoRepository mongo = new MongoRepository(mockedCollection, mapper);

    mongo.addProduction(production);
  }

  @Test
  public void shouldThrowCouldNotAddProductionException() {

    CouldNotAddProductionException exception = null;

    Production production = mock(Production.class);

    MongoProductionPojo mongoProductionPojo = mock(MongoProductionPojo.class);

    MongoMapper mapper = mock(MongoMapper.class);
    when(mapper.fromProduction(production)).thenReturn(mongoProductionPojo);

    InsertOneResult result = mock(InsertOneResult.class);
    when(result.wasAcknowledged()).thenReturn(false);

    MongoCollection<MongoProductionPojo> mockedCollection = mock(MongoCollection.class);
    when(mockedCollection.insertOne(mongoProductionPojo)).thenReturn(result);

    MongoRepository mongo = new MongoRepository(mockedCollection, mapper);

    try {
      mongo.addProduction(production);

    } catch (CouldNotAddProductionException ex) {
      exception = ex;
    }
    assertNotNull(exception);
    assertEquals("Non ho potuto aggiungere la produzione", exception.getMessage());
  }

  @Test
  public void shouldFindProductionById() {

    UUID id = mock(UUID.class);

    Production production = mock(Production.class);

    MongoProductionPojo mongoProductionPojo = mock(MongoProductionPojo.class);

    MongoMapper mapper = mock(MongoMapper.class);
    when(mapper.fromMongoProductionPojo(mongoProductionPojo)).thenReturn(production);

    FindIterable findIterable = mock(FindIterable.class);
    when(findIterable.first()).thenReturn(mongoProductionPojo);

    MongoCollection<MongoProductionPojo> mockedCollection = mock(MongoCollection.class);
    when(mockedCollection.find(eq("_id", id))).thenReturn(findIterable);

    MongoRepository mongoRepository = new MongoRepository(mockedCollection, mapper);
    Production foundProduction = mongoRepository.findProductionById(id);
    assertSame(production, foundProduction);
  }

  @Test
  public void shouldThrowCouldNotFindProductionException() {
    UUID id = mock(UUID.class);

    CouldNotFindProductionException exception = null;
    
    MongoMapper mapper = mock(MongoMapper.class);

    FindIterable findIterable = mock(FindIterable.class);
    when(findIterable.first()).thenReturn(null);

    MongoCollection<MongoProductionPojo> mockedCollection = mock(MongoCollection.class);
    when(mockedCollection.find(eq("_id", id))).thenReturn(findIterable);

    MongoRepository mongo = new MongoRepository(mockedCollection, mapper);

    try {
      mongo.findProductionById(id);
    } catch (CouldNotFindProductionException ex) {
      exception = ex;
    }
    assertNotNull(exception);
    assertEquals("Non ho potuto trovare nulla", exception.getMessage());
  }
}
