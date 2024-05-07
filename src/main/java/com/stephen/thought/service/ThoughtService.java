package com.stephen.thought.service;

import com.stephen.thought.dto.ThoughtDto;

import java.util.List;

public interface ThoughtService {

    ThoughtDto createThought(ThoughtDto thoughtDto);
    ThoughtDto getThoughtById(long thoughtId);
    List<ThoughtDto> getAllThoughts();
    ThoughtDto updateThought(ThoughtDto thoughtDto, long thoughtId);
    void deleteThought(long thoughtId);

    void deleteSelectedThoughts(List<Long> thoughtIds);
}
