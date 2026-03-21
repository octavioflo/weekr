package com.weekr.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Objective {
  @Id @GeneratedValue private UUID id;

  @Column(nullable = false)
  private String title;

  private String description;

  @Column(nullable = false)
  private LocalDate weekOf;

  @Enumerated(EnumType.STRING)
  private Status status = Status.ACTIVE; // ACTIVE, COMPLETED

  //  @ManyToOne
  //  @JoinColumn(name = "user_id", nullable = false)
  //  private User user;

  //  @OneToMany(mappedBy = "objective", cascade = CascadeType.ALL)
  //  private List<KeyResult> keyResults = new ArrayList<>();

  public enum Status {
    ACTIVE,
    COMPLETED
  }
}
