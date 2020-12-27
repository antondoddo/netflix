package com.antondoddo.production.repository;


import java.time.Duration;
import java.util.ArrayList;
import java.util.UUID;
import org.bson.codecs.pojo.annotations.BsonId;

public class MongoProductionPojo {

  @BsonId
  public UUID id;
  public String title;
  public String description;
  public Duration duration;
  public String yearOfPublication;
  public ArrayList<String> genres;
  public ArrayList<String[]> cast;
  public String[] filmDirector;
  public String ageClassification;
  public int episode;
  public int season;


}
