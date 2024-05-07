package com.stephen.thought.serviceImpl;

import com.stephen.thought.Exception.ResourceNotFoundException;
import com.stephen.thought.dto.TodoDto;
import com.stephen.thought.models.Todo;
import com.stephen.thought.repository.TodoRepository;
import com.stephen.thought.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    public TodoServiceImpl(TodoRepository todoRepository, ModelMapper modelMapper) {
        this.todoRepository = todoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        Todo todo = mapToEntity(todoDto);
        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDto.class);
    }

    @Override
    public TodoDto getTodoById(long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", todoId));
        return mapToDto(todo);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, long todoId) {
        Todo existingTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", todoId));

        existingTodo.setTitle(todoDto.getTitle());
        existingTodo.setDescription(todoDto.getDescription());
        existingTodo.setActive(todoDto.isActive());
        existingTodo.setCompleted(todoDto.isCompleted());
        existingTodo.setPriority(todoDto.getPriority());

        Todo updatedTodo = todoRepository.save(existingTodo);
        return mapToDto(updatedTodo);
    }

    @Override
    public void deleteTodo(long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", todoId));
        todoRepository.delete(todo);
    }


    // Convert entity to DTO
    private TodoDto mapToDto(Todo todo){
        return modelMapper.map(todo, TodoDto.class);
    }

    // convert DTO to entity
    private Todo mapToEntity(TodoDto todoDto){
        return modelMapper.map(todoDto, Todo.class);
    }
}
