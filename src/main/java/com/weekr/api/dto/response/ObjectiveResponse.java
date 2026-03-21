package com.weekr.api.dto.response;

import com.weekr.api.model.Objective;

import java.time.LocalDate;
import java.util.UUID;

public record ObjectiveResponse(
    UUID id, String title, String description, LocalDate weekOf, Objective.Status status) {}
