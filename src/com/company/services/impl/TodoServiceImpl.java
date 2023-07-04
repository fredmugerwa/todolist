package com.company.services.impl;

import com.company.models.Todo;
import com.company.services.TodoService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoServiceImpl implements TodoService {
    private List<Todo> todos;

    public TodoServiceImpl() {
        this.todos = new ArrayList<>();
    }

    @Override
    public void createTodo(Todo todo) {
        todos.add(todo);
        System.out.println("Todo created successfully!");
    }

    @Override
    public List<Todo> readTodos() {
        return todos;
    }

    @Override
    public List<Todo> readTodos(boolean state) {
        List<Todo> stateTodos = new ArrayList<>();
        for (Todo todo : todos) {
            if (state && todo.isCompleted()) {
                stateTodos.add(todo);
            }else if(!state && !todo.isCompleted()){
                stateTodos.add(todo);
            }
        }
        return stateTodos;

//        return todos.stream()
//                .filter(todo -> todo.isCompleted() == state)
//                .collect(Collectors.toList());
    }

    @Override
    public void updateTodo(Todo updatedTodo) {
        int index = todos.indexOf(updatedTodo);
        if (index >= 0) {
            todos.set(index, updatedTodo);
            System.out.println("Todo updated successfully!");
        } else {
            System.out.println("Todo not found.");
        }
    }

    @Override
    public void deleteTodo(int index) {
        if (index >= 0 && index < todos.size()) {
            todos.remove(index);
            System.out.println("Todo deleted successfully!");
        } else {
            System.out.println("Invalid index.");
        }
    }
}