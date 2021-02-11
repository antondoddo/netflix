package com.antondoddo.production.http;

import com.antondoddo.production.repository.exception.CouldNotFindProductionException;
import com.antondoddo.production.repository.exception.CouldNotRemoveProductionException;
import com.antondoddo.production.usecase.DeleteUseCase;
import java.util.UUID;
import spark.Request;
import spark.Response;
import spark.Route;

public class Delete implements Route {

  private final DeleteUseCase useCase;

  public Delete(DeleteUseCase useCase) {
    this.useCase = useCase;
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

    try {
      useCase.execute(id);
    } catch (CouldNotFindProductionException e) {
      response.status(404);
      return "";
    } catch (CouldNotRemoveProductionException e) {
      response.status(500);
      return "";
    }


    response.status(200);
    return "";
  }
}
