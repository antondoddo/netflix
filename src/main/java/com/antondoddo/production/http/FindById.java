package com.antondoddo.production.http;

import com.antondoddo.production.Production;
import com.antondoddo.production.repository.exception.CouldNotFindProductionException;
import com.antondoddo.production.usecase.FindUseCase;
import com.google.gson.Gson;
import java.util.UUID;
import spark.Request;
import spark.Response;
import spark.Route;

public class FindById implements Route {

  private final FindUseCase useCase;
  private final Gson gson;

  public FindById(FindUseCase useCase, Gson gson) {
    this.useCase = useCase;
    this.gson = gson;
  }

  @Override
  public Object handle(Request request, Response response) {
    UUID id;
    try {
      id = UUID.fromString(request.params(":id"));
    } catch (IllegalArgumentException e) {
      response.status(404);
      return "";
    }

    Production production;
    try {
      production = this.useCase.execute(id);
    } catch (CouldNotFindProductionException e) {
      response.status(404);
      return "";
    }

    return this.gson.toJson(production);
  }
}
