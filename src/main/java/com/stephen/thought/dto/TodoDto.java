package com.stephen.thought.dto;

import com.stephen.thought.utils.Priorities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {

    private Long id;
    private String title;
    private String description;
    private LocalDate localDate;
    private boolean isActive = false;
    private boolean isCompleted = false;
    private Priorities priority = Priorities.NONE;
}
