package com.stephen.thought.service;

import com.stephen.thought.dto.ThoughtDto;

public interface ThoughtService {

    ThoughtDto createThought(ThoughtDto thoughtDto);
    ThoughtDto getThoughtById(long thoughtId);
    ThoughtDto updateThought(ThoughtDto thoughtDto, long thoughtId);
    void deleteThought(long thoughtId);
}
