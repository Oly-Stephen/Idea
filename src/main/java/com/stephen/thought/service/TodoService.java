package com.stephen.thought.service;

import com.stephen.thought.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto createTodo(TodoDto todoDto);
    TodoDto getTodoById(long todoId);
    List<TodoDto> getAllTodos();
    TodoDto updateTodo(TodoDto todoDto, long todoId);
    void deleteTodo(long todoId);

}
