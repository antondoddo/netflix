package com.antondoddo.production.repository;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.antondoddo.production.Production;
import com.antondoddo.production.valueobject.Actor;
import com.antondoddo.production.valueobject.AgeClassification;
import com.antondoddo.production.valueobject.Description;
import com.antondoddo.production.valueobject.Director;
import com.antondoddo.production.valueobject.Duration;
import com.antondoddo.production.valueobject.EpisodeImpl;
import com.antondoddo.production.valueobject.Genre;
import com.antondoddo.production.valueobject.SeasonImpl;
import com.antondoddo.production.valueobject.Title;
import com.antondoddo.production.valueobject.YearOfPublication;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
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
import org.testcontainers.containers.MongoDBContainer;

@RunWith(JUnitParamsRunner.class)
public final class MongoRepositoryIntegrationTest {

  private static MongoDBContainer mongoContainer;
  private static MongoClient mongoClient;

  @BeforeClass
  public static void init() {
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
    mongoClient.close();
    mongoContainer.close();
  }

  @Test
  public void test() {
    assertEquals("netflix", mongoClient.getDatabase("netflix").getName());
  }

  public Object[] shouldAddProductionData() {
    ArrayList<Genre> genres = new ArrayList<Genre>();
    genres.add(Genre.COMEDY);
    ArrayList<Actor> cast = new ArrayList<Actor>();
    cast.add(new Actor("Antonio", "Farina"));
    return new Object[]{
        new Object[]{
            Production.ofMovie(UUID.randomUUID(),
                new Title("L'alba dell'IntegrationTest"),
                new Description("Questo è il primo IntegrationTest di Antonio"),
                new Duration(java.time.Duration.ofSeconds(86000)),
                new YearOfPublication("1994-02-22"),
                genres,
                cast,
                new Director("Damiano", "Petrungaro"), AgeClassification.SETTE)
        },
        new Object[]{
            Production.ofEpisode(UUID.randomUUID(),
                new Title("Il risveglio dell'IntegrationTest"),
                new Description("IntegrationTest veramente bellissimo"),
                new Duration(java.time.Duration.ofSeconds(70000)),
                new YearOfPublication("1999-05-12"),
                genres,
                cast,
                new Director("Damiano", "Petrungaro"),
                AgeClassification.QUATTORDICI,
                new EpisodeImpl(2), new SeasonImpl(2))
        },
    };
  }

  @Test
  @Parameters(method = "shouldAddProductionData")
  public void shouldAddProduction(Production production) {
    MongoCollection<MongoProductionPojo> mongoCollection =
        mongoClient.getDatabase("netflix")
            .getCollection("productions", MongoProductionPojo.class);
    assertNull(mongoCollection.find(eq("_id", production.getId())).first());
    MongoRepository mongoRepository = new MongoRepository(mongoCollection, new MongoMapper());
    mongoRepository.addProduction(production);
    MongoProductionPojo mongoProductionPojo =
        mongoCollection.find(eq("_id", production.getId())).first();
    assertNotNull(mongoProductionPojo);
    assertEquals(production.getId(), mongoProductionPojo.id);
  }

  @Test
  @Parameters(method = "shouldAddProductionData")
  public void shouldFindProductionById(Production production) {
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
  @Parameters(method = "shouldAddProductionData")
  public void shouldRemoveProductionById(Production production) {
    MongoCollection<MongoProductionPojo> mongoCollection =
        mongoClient.getDatabase("netflix")
            .getCollection("productions", MongoProductionPojo.class);
    MongoMapper mongoMapper = new MongoMapper();
    MongoRepository mongoRepository = new MongoRepository(mongoCollection, mongoMapper);
    mongoCollection.insertOne(mongoMapper.fromProduction(production));
    mongoRepository.removeProductionById(production.getId());
    assertNull(mongoCollection.find(eq("_id", production.getId())).first());
  }
}
