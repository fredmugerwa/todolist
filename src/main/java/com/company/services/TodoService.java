package com.company.services;

import com.company.models.Todo;

import java.util.List;

public interface TodoService {
    void createTodo(Todo todo);
    List<Todo> getTodos();
    List<Todo> getTodos(String searchTerm);
    List<Todo> getTodos(boolean state);
    Todo getTodo(long todoId);
    void updateTodo(Todo updatedTodo);
    void deleteTodo(Todo todo);
}
