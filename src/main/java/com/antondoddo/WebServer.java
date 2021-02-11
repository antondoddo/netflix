package com.antondoddo;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static spark.Spark.after;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;

import com.antondoddo.production.http.Add;
import com.antondoddo.production.http.Delete;
import com.antondoddo.production.http.FindById;
import com.antondoddo.production.http.serializzer.ActorAdapter;
import com.antondoddo.production.http.serializzer.AgeClassificationAdapter;
import com.antondoddo.production.http.serializzer.DescriptionAdapter;
import com.antondoddo.production.http.serializzer.DirectorAdapter;
import com.antondoddo.production.http.serializzer.DurationAdapter;
import com.antondoddo.production.http.serializzer.EpisodeAdapter;
import com.antondoddo.production.http.serializzer.GenreAdapter;
import com.antondoddo.production.http.serializzer.IdAdapter;
import com.antondoddo.production.http.serializzer.ReleaseDateAdapter;
import com.antondoddo.production.http.serializzer.SeasonAdapter;
import com.antondoddo.production.http.serializzer.TitleAdapter;
import com.antondoddo.production.repository.MongoMapper;
import com.antondoddo.production.repository.MongoProductionPojo;
import com.antondoddo.production.repository.MongoRepository;
import com.antondoddo.production.repository.Repository;
import com.antondoddo.production.usecase.AddUseCase;
import com.antondoddo.production.usecase.DeleteUseCase;
import com.antondoddo.production.usecase.FindUseCase;
import com.antondoddo.production.valueobject.Actor;
import com.antondoddo.production.valueobject.AgeClassification;
import com.antondoddo.production.valueobject.Description;
import com.antondoddo.production.valueobject.Director;
import com.antondoddo.production.valueobject.Duration;
import com.antondoddo.production.valueobject.Episode;
import com.antondoddo.production.valueobject.Genre;
import com.antondoddo.production.valueobject.ReleaseDate;
import com.antondoddo.production.valueobject.Season;
import com.antondoddo.production.valueobject.Title;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import java.util.UUID;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class WebServer {
  public static void main(String[] args) {
    port(8081);

    Gson productionMapper = getProductionJsonMapper();
    MongoCollection<MongoProductionPojo> collection = getMongoCollection();
    Repository repo = new MongoRepository(collection, new MongoMapper());

    FindById findById = new FindById(new FindUseCase(repo), productionMapper);
    Add add = new Add(new AddUseCase(repo), productionMapper);
    Delete delete = new Delete(new DeleteUseCase(repo));

    path("/productions/", () -> {
      get("/:id", findById);
      post("/", add);
      delete("/:id", delete);
      after("/*", (request, response) -> {
        response.header("Content-Type", "application/json");
      });
    });
  }

  private static MongoCollection<MongoProductionPojo> getMongoCollection() {
    CodecRegistry pojoCodecRegistry = fromRegistries(
        MongoClientSettings.getDefaultCodecRegistry(),
        fromProviders(PojoCodecProvider.builder().automatic(true).build())
    );

    String connectionString = String.format(
        "mongodb://%s:%d/?uuidRepresentation=STANDARD",
        "localhost",
        27017
    );
    MongoClientSettings settings = MongoClientSettings
        .builder()
        .codecRegistry(pojoCodecRegistry)
        .applyConnectionString(new ConnectionString(connectionString))
        .build();

    MongoClient mongoClient = MongoClients.create(settings);

    return mongoClient
        .getDatabase("netflix")
        .getCollection("productions", MongoProductionPojo.class);
  }

  private static Gson getProductionJsonMapper() {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(UUID.class, new IdAdapter());
    builder.registerTypeAdapter(Actor.class, new ActorAdapter());
    builder.registerTypeAdapter(AgeClassification.class, new AgeClassificationAdapter());
    builder.registerTypeAdapter(Description.class, new DescriptionAdapter());
    builder.registerTypeAdapter(Director.class, new DirectorAdapter());
    builder.registerTypeAdapter(Duration.class, new DurationAdapter());
    builder.registerTypeAdapter(Episode.class, new EpisodeAdapter());
    builder.registerTypeAdapter(Genre.class, new GenreAdapter());
    builder.registerTypeAdapter(ReleaseDate.class, new ReleaseDateAdapter());
    builder.registerTypeAdapter(Season.class, new SeasonAdapter());
    builder.registerTypeAdapter(Title.class, new TitleAdapter());
    builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

    return builder.create();
  }
}
