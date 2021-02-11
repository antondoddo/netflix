package com.antondoddo.production.http;

import com.antondoddo.production.Production;
import com.antondoddo.production.repository.exception.CouldNotAddProductionException;
import com.antondoddo.production.usecase.AddUseCase;
import com.antondoddo.production.usecase.AddUseCaseDto;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import spark.Request;
import spark.Response;
import spark.Route;

public class Add implements Route {

  private final AddUseCase useCase;
  private final Gson gson;

  public Add(AddUseCase useCase, Gson gson) {
    this.useCase = useCase;
    this.gson = gson;
  }

  @Override
  public Object handle(Request request, Response response) {
    AddUseCaseDto dto;
    try {
      dto = this.gson.fromJson(request.body(), AddUseCaseDto.class);
    } catch (JsonParseException e) {
      e.printStackTrace(System.out);
      response.status(400);
      return "";
    }

    Production production;
    try {
      production = this.useCase.execute(dto);
    } catch (CouldNotAddProductionException e) {
      e.printStackTrace(System.out);
      response.status(500);
      return "";
    }

    if (dto.getId() == null) {
      response.status(201);
    }

    return this.gson.toJson(production);
  }
}
