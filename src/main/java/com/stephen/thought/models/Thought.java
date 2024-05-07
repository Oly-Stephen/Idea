package com.stephen.thought.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "thought")
public class Thought {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private LocalDate localDate;
    private boolean isRelevant = false;

    @PrePersist
    protected void onCreate() {
        localDate = LocalDate.now();
    }
}
