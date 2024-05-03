package com.stephen.thought.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThoughtDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate localDate;

}