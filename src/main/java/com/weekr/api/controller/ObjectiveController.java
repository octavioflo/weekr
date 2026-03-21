package com.weekr.api.controller;

import com.weekr.api.dto.request.ObjectiveRequest;
import com.weekr.api.dto.response.ObjectiveResponse;
import com.weekr.api.service.ObjectiveService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/objectives")
public class ObjectiveController {

  private final ObjectiveService service;

  public ObjectiveController(ObjectiveService objectiveService) {
    this.service = objectiveService;
  }

  @GetMapping
  public List<ObjectiveResponse> getObjectives() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public ObjectiveResponse getObjective(@PathVariable UUID id) {
    return service.getById(id);
  }

  @PostMapping
  public ResponseEntity<ObjectiveResponse> createObjective(
      @Valid @RequestBody ObjectiveRequest objectiveRequest) {
    ObjectiveResponse newObjective = service.create(objectiveRequest);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newObjective.id())
            .toUri();
    return ResponseEntity.created(location).body(newObjective);
  }
}
