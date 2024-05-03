package com.stephen.thought.serviceImpl;

import com.stephen.thought.Exception.ResourceNotFoundException;
import com.stephen.thought.dto.ThoughtDto;
import com.stephen.thought.models.Thought;
import com.stephen.thought.repository.ThoughtRepository;
import com.stephen.thought.service.ThoughtService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ThoughtServiceImpl implements ThoughtService {


    private final ThoughtRepository thoughtRepository;
    private ModelMapper modelMapper;

    public ThoughtServiceImpl(ThoughtRepository thoughtRepository, ModelMapper modelMapper) {
        this.thoughtRepository = thoughtRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ThoughtDto createThought(ThoughtDto thoughtDto) {
        Thought thought =mapToEntity(thoughtDto);
        Thought savedThought = thoughtRepository.save(thought);
        return modelMapper.map(savedThought, ThoughtDto.class);
    }

    @Override
    public ThoughtDto getThoughtById(long id) {
        Thought thought = thoughtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Thought", "id", id));
        return mapToDto(thought);
    }



    // Convert entity to DTO
    private ThoughtDto mapToDto(Thought thought){
        ThoughtDto thoughtDto = modelMapper.map(thought, ThoughtDto.class);
        return thoughtDto;
    }

    // convert DTO to entity
    private Thought mapToEntity(ThoughtDto thoughtDto){
        Thought thought = modelMapper.map(thoughtDto, Thought.class);
        return thought;
    }
}
