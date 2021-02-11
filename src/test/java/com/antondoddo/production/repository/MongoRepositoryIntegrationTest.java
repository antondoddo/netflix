package com.antondoddo.production.repository;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assume.assumeTrue;

import com.antondoddo.production.ObjectMother;
import com.antondoddo.production.Production;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import java.util.UUID;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.BasicConfigurator;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.MongoDBContainer;

@RunWith(JUnitParamsRunner.class)
public final class MongoRepositoryIntegrationTest {

  static boolean hasDockerInstalled = DockerClientFactory.instance().isDockerAvailable();

  private static MongoDBContainer mongoContainer;
  private static MongoClient mongoClient;

  @BeforeClass
  public static void init() {
    if (!hasDockerInstalled) {
      return;
    }

    BasicConfigurator.configure();
    mongoContainer = new MongoDBContainer("mongo:3.6.21");
    mongoContainer.start();
    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    MongoClientSettings settings = MongoClientSettings.builder()
        .codecRegistry(pojoCodecRegistry)
        .applyConnectionString(new ConnectionString("mongodb://" + mongoContainer.getHost()
            + ":" + mongoContainer.getMappedPort(27017) + "/?uuidRepresentation=STANDARD"))
        .build();
    mongoClient = MongoClients.create(settings);
    assertNotNull(mongoClient);
  }

  @AfterClass
  public static void tearDown() {
    if (!hasDockerInstalled) {
      return;
    }

    mongoClient.close();
    mongoContainer.close();
  }

  @Test
  public void test() {
    assumeTrue(hasDockerInstalled);

    assertEquals("netflix", mongoClient.getDatabase("netflix").getName());
  }

  public Object[] productionData() {
    return new Object[]{
        new Object[]{ObjectMother.createMovie()},
        new Object[]{ObjectMother.createEpisode()}
    };
  }

  @Test
  @Parameters(method = "productionData")
  public void shouldAddProduction(Production production) {
    assumeTrue(hasDockerInstalled);

    MongoCollection<MongoProductionPojo> mongoCollection =
        mongoClient.getDatabase("netflix")
            .getCollection("productions", MongoProductionPojo.class);
    assertNull(mongoCollection.find(eq("_id", production.getId())).first());
    MongoRepository mongoRepository = new MongoRepository(mongoCollection, new MongoMapper());
    mongoRepository.addProduction(production);
    MongoProductionPojo mongoProductionPojo =
        mongoCollection.find(eq("_id", production.getId().toString())).first();
    assertNotNull(mongoProductionPojo);
    assertEquals(production.getId().toString(), mongoProductionPojo.id);
  }

  @Test
  public void shouldAddProductionReplaceWhenAlreadyExists() {
    assumeTrue(hasDockerInstalled);

    UUID id = UUID.randomUUID();
    Production production = ObjectMother.createEpisode(id);

    MongoCollection<MongoProductionPojo> mongoCollection =
        mongoClient.getDatabase("netflix")
            .getCollection("productions", MongoProductionPojo.class);
    assertNull(mongoCollection.find(eq("_id", id.toString())).first());

    MongoRepository mongoRepository = new MongoRepository(mongoCollection, new MongoMapper());
    mongoRepository.addProduction(production);

    MongoProductionPojo mongoProductionPojo =
        mongoCollection.find(eq("_id", id.toString())).first();

    assertNotNull(mongoProductionPojo);
    assertEquals(id.toString(), mongoProductionPojo.id);

    Production updatedProduction = ObjectMother.createMovie(id);

    mongoRepository.addProduction(updatedProduction);

    MongoProductionPojo updatedMongoProductionPojo = mongoCollection.find(
        eq("_id", id.toString())
    ).first();

    assertNotNull(updatedMongoProductionPojo);
    assertEquals(id.toString(), updatedMongoProductionPojo.id);

    assertNotEquals(mongoProductionPojo.season, updatedMongoProductionPojo.season);
  }

  @Test
  @Parameters(method = "productionData")
  public void shouldFindProductionById(Production production) {
    assumeTrue(hasDockerInstalled);

    MongoCollection<MongoProductionPojo> mongoCollection =
        mongoClient.getDatabase("netflix")
            .getCollection("productions", MongoProductionPojo.class);
    MongoMapper mongoMapper = new MongoMapper();
    MongoRepository mongoRepository = new MongoRepository(mongoCollection, mongoMapper);
    MongoProductionPojo mongoProductionPojo = mongoMapper.fromProduction(production);
    mongoCollection.insertOne(mongoProductionPojo);
    Production prod = mongoRepository.findProductionById(production.getId());
    assertEquals(prod.getId(), production.getId());
  }

  @Test
  @Parameters(method = "productionData")
  public void shouldRemoveProductionById(Production production) {
    assumeTrue(hasDockerInstalled);

    MongoCollection<MongoProductionPojo> mongoCollection =
        mongoClient.getDatabase("netflix")
            .getCollection("productions", MongoProductionPojo.class);
    MongoMapper mongoMapper = new MongoMapper();
    MongoRepository mongoRepository = new MongoRepository(mongoCollection, mongoMapper);
    mongoCollection.insertOne(mongoMapper.fromProduction(production));
    mongoRepository.removeProductionById(production.getId());
    assertNull(mongoCollection.find(eq("_id", production.getId().toString())).first());
  }
}
