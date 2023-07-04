package com.company.services;

import com.company.models.Todo;

import java.util.List;

public interface TodoService {
    void createTodo(Todo todo);
    List<Todo> readTodos();
    List<Todo> readTodos(boolean state);
    void updateTodo(Todo updatedTodo);
    void deleteTodo(int index);
}
