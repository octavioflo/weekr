package com.weekr.api.service;

import com.weekr.api.dto.request.ObjectiveRequest;
import com.weekr.api.dto.response.ObjectiveResponse;
import com.weekr.api.model.Objective;
import com.weekr.api.repository.ObjectiveRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ObjectiveService {
  private final ObjectiveRepository objectiveRepository;
  private final ModelMapper modelMapper;

  public ObjectiveService(ObjectiveRepository objectiveRepository, ModelMapper modelMapper) {
    this.objectiveRepository = objectiveRepository;
    this.modelMapper = modelMapper;
  }

  public ObjectiveResponse getById(UUID id) {
    Objective objective = objectiveRepository.findById(id).orElse(null);
    return modelMapper.map(objective, ObjectiveResponse.class);
  }

  public List<ObjectiveResponse> getAll() {
    List<Objective> objectives = objectiveRepository.findAll();
    List<ObjectiveResponse> responses = new ArrayList<>();
    for (Objective objective : objectives) {
      ObjectiveResponse objectiveResponse = modelMapper.map(objective, ObjectiveResponse.class);
      responses.add(objectiveResponse);
    }
    return responses;
  }

  public ObjectiveResponse create(ObjectiveRequest objective) {
    LocalDate today = LocalDate.now();
    LocalDate mostRecentSunday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
    Objective objectiveEntity =
        new Objective(
            UUID.randomUUID(),
            objective.name(),
            objective.description(),
            mostRecentSunday,
            Objective.Status.ACTIVE);
    objectiveRepository.save(objectiveEntity);
    return modelMapper.map(objectiveEntity, ObjectiveResponse.class);
  }
}
