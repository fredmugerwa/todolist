package com.company.services.impl;

import com.company.dao.BaseDao;
import com.company.models.Todo;
import com.company.dao.TodoDAO;
import com.company.models.User;
import com.company.services.TodoService;

import java.util.List;
import java.util.stream.Collectors;

public class TodoServiceImpl implements TodoService {
//    private final BaseDao<Todo> todoDAO;
    private final TodoDAO todoDAO;

    public TodoServiceImpl() {
//        todoDAO = new BaseDao<>(Todo.class);
        todoDAO = new TodoDAO();
    }

    @Override
    public void createTodo(Todo todo) {
        todoDAO.save(todo);
        System.out.println("Todo created.");
    }

    @Override
    public List<Todo> getTodos() {
        return todoDAO.getAllTodos();
    }

    @Override
    public List<Todo> getTodos(User user) {
        return todoDAO.getTodos(user);
    }

    @Override
    public List<Todo> getTodos(String searchTerm) {
        return todoDAO.getAllTodos();
    }

    @Override
    public List<Todo> getTodos(boolean state) {
        return todoDAO.getAllTodos().stream()
                .filter(todo -> todo.isCompleted() == state)
                .collect(Collectors.toList());
    }

    @Override
    public Todo getTodo(long todoId) {
        return todoDAO.getAllTodos().get(0);
    }

    @Override
    public void updateTodo(Todo updatedTodo) {
        todoDAO.update(updatedTodo);
        System.out.println("Todo updated.");
    }

    @Override
    public void deleteTodo(Todo todo) {
        todoDAO.delete(todo);
        System.out.println("Todo deleted.");
    }
}