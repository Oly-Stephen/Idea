package com.stephen.thought.serviceImpl;

import com.stephen.thought.Exception.ResourceNotFoundException;
import com.stephen.thought.dto.ThoughtDto;
import com.stephen.thought.dto.TodoDto;
import com.stephen.thought.models.Thought;
import com.stephen.thought.repository.ThoughtRepository;
import com.stephen.thought.service.ThoughtService;
import com.stephen.thought.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThoughtServiceImpl implements ThoughtService {



    private final TodoService todoService;
    private final ThoughtRepository thoughtRepository;
    private final ModelMapper modelMapper;

    public ThoughtServiceImpl(TodoService todoService, ThoughtRepository thoughtRepository, ModelMapper modelMapper) {
        this.todoService = todoService;
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
    public ThoughtDto getThoughtById(long thoughtId) {
        Thought thought = thoughtRepository.findById(thoughtId)
                .orElseThrow(() -> new ResourceNotFoundException("Thought", "id", thoughtId));
        return mapToDto(thought);
    }

    @Override
    public List<ThoughtDto> getAllThoughts() {
        List<Thought> thoughts = thoughtRepository.findAll();
        return thoughts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ThoughtDto updateThought(ThoughtDto thoughtDto, long thoughtId) {
        Thought existingThought = thoughtRepository.findById(thoughtId)
                .orElseThrow(() -> new ResourceNotFoundException("Thought", "id", thoughtId));
//        existingThought.setId(thoughtId);

        existingThought.setTitle(thoughtDto.getTitle());
        existingThought.setDescription(thoughtDto.getDescription());

        boolean wasRelevant = existingThought.isRelevant();
        existingThought.setRelevant(thoughtDto.isRelevant());

        if (!wasRelevant && thoughtDto.isRelevant()) {
            TodoDto todoDto = new TodoDto();
            todoDto.setTitle(existingThought.getTitle());
            todoDto.setDescription(existingThought.getDescription());
            todoService.createTodo(todoDto);
        }

        Thought updatedThought = thoughtRepository.save(existingThought); // Save the updated thought back to the database
        return mapToDto(updatedThought); // Return the updated ThoughtDto
    }


    @Override
    public void deleteThought(long thoughtId) {
        Thought existingThought = thoughtRepository.findById(thoughtId)
                .orElseThrow(() -> new ResourceNotFoundException("Thought", "id", thoughtId));
        thoughtRepository.delete(existingThought);
    }

    @Override
    public void deleteSelectedThoughts(List<Long> thoughtIds) {
        for(Long id : thoughtIds) {
            Thought existingThought = thoughtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Thought", "id", id));
            thoughtRepository.delete(existingThought);
        }
    }


    // Convert entity to DTO
    private ThoughtDto mapToDto(Thought thought){
        return modelMapper.map(thought, ThoughtDto.class);
    }

    // convert DTO to entity
    private Thought mapToEntity(ThoughtDto thoughtDto){
        return modelMapper.map(thoughtDto, Thought.class);
    }
}
