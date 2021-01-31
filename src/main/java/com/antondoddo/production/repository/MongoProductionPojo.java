package com.antondoddo.production.repository;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bson.codecs.pojo.annotations.BsonId;

public final class MongoProductionPojo {

  @BsonId
  public UUID id;
  public String title;
  public String description;
  public long duration;
  public String releaseDate;
  public List<String> genres;
  public List<List<String>> cast;
  public List<String> filmDirector;
  public String ageClassification;
  public int episode;
  public int season;

}
