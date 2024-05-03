package com.stephen.thought.service;

import com.stephen.thought.dto.ThoughtDto;
import com.stephen.thought.models.Thought;

public interface ThoughtService {

    ThoughtDto createThought(ThoughtDto thoughtDto);
    ThoughtDto getThoughtById(long id);
}
