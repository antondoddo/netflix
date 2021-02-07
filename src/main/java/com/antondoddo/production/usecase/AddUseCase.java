package com.antondoddo.production.usecase;

import com.antondoddo.production.Production;
import com.antondoddo.production.repository.Repository;
import com.antondoddo.production.repository.exception.CouldNotAddProductionException;
import com.antondoddo.production.valueobject.NullEpisode;
import java.util.UUID;

public final class AddUseCase {

  private Repository repository;

  public AddUseCase(Repository repository) {
    this.repository = repository;
  }

  public Production execute(AddUseCaseDto addUseCaseDto) throws CouldNotAddProductionException {

    UUID id = addUseCaseDto.getId();

    if (id == null) {
      id = UUID.randomUUID();
    }

    Production production;
    if (!addUseCaseDto.getEpisode().equals(new NullEpisode())) {
      production = Production.ofEpisode(id,
          addUseCaseDto.getTitle(),
          addUseCaseDto.getDescription(),
          addUseCaseDto.getDuration(),
          addUseCaseDto.getReleaseDate(),
          addUseCaseDto.getGenres(),
          addUseCaseDto.getCast(),
          addUseCaseDto.getFilmDirector(),
          addUseCaseDto.getAgeClassification(),
          addUseCaseDto.getEpisode(),
          addUseCaseDto.getSeason());
    } else {
      production = Production.ofMovie(id,
          addUseCaseDto.getTitle(),
          addUseCaseDto.getDescription(),
          addUseCaseDto.getDuration(),
          addUseCaseDto.getReleaseDate(),
          addUseCaseDto.getGenres(),
          addUseCaseDto.getCast(),
          addUseCaseDto.getFilmDirector(),
          addUseCaseDto.getAgeClassification()
      );
    }
    repository.addProduction(production);
    return production;
  }
}
