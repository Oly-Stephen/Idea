package com.stephen.thought.serviceImpl;

import com.stephen.thought.Exception.ResourceNotFoundException;
import com.stephen.thought.dto.ThoughtDto;
import com.stephen.thought.models.Thought;
import com.stephen.thought.repository.ThoughtRepository;
import com.stephen.thought.service.ThoughtService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

    @Override
    public ThoughtDto updateThought(ThoughtDto thoughtDto, long id) {
        Thought existingThought = thoughtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Thought", "id", id));
        existingThought.setId(id);
        existingThought.setTitle(thoughtDto.getTitle());
        existingThought.setDescription(thoughtDto.getDescription());
        existingThought.setLocalDate(LocalDate.now());
        existingThought.setRelevant(thoughtDto.isRelevant());

        Thought updatedThought = thoughtRepository.save(existingThought); // Save the updated thought back to the database
        return mapToDto(updatedThought); // Return the updated ThoughtDto
    }


    @Override
    public void deleteThought(long id) {
        Thought existingThought = thoughtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Thought", "id", id));
        thoughtRepository.delete(existingThought);
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
