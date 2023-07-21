package com.company.views;

import com.company.constant.Hyperlink;
import com.company.models.Todo;
import com.company.models.User;
import com.company.services.TodoService;
import com.company.services.impl.TodoServiceImpl;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class TodoBean implements Serializable {
    private List<Todo> todos;
    private String newTodo;
    private Todo selectedTodo;
    private User currentUser;

    private final TodoService todoService;

    public TodoBean() {
        todoService = new TodoServiceImpl();
    }

    @PostConstruct
    public void init() {
        System.out.println("Current user " + getCurrentUser());
        currentUser = getCurrentUser();
        todos = todoService.getTodos(currentUser);

        // Prevent non authenticated users from accessing this page
        try {
            if (currentUser == null) {
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                externalContext.getSessionMap().put("currentUser", null);
                externalContext.redirect(externalContext.getRequestContextPath() + Hyperlink.LOGIN_VIEW);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public String getNewTodo() {
        return newTodo;
    }

    public void setNewTodo(String newTodo) {
        this.newTodo = newTodo;
    }

    private User getCurrentUser() {
        // Used to fetch currently logged in user
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        return (User) externalContext.getSessionMap().get("currentUser");
    }

    public void addTodo() {
        Todo todo = new Todo();
        todo.setTitle(newTodo);
        System.out.println("Current user add " + currentUser);
        if(currentUser != null)
            todo.setUser(getCurrentUser());
        todoService.createTodo(todo);
        todos = todoService.getTodos(currentUser);
        newTodo = null;
    }

    public void setSelectedTodo(Todo selectedTodo) {
        this.selectedTodo = selectedTodo;
        newTodo = "";
        if (selectedTodo != null)
            newTodo = selectedTodo.getTitle();
    }

    public void updateTodo() {
        if (selectedTodo != null && !newTodo.isEmpty()) {
            selectedTodo.setTitle(newTodo);
            System.out.println("Current user add " + currentUser);
            if(currentUser != null)
                selectedTodo.setUser(getCurrentUser());
            todoService.updateTodo(selectedTodo);
            todos = todoService.getTodos(currentUser);
            newTodo = "";
            selectedTodo = null;
        }
    }

    public void markTodoComplete() {
        if (selectedTodo != null && !selectedTodo.isCompleted()) {
            selectedTodo.setCompleted(true);
            System.out.println("Current user update " + currentUser);
            if(currentUser != null)
                selectedTodo.setUser(getCurrentUser());
            todoService.updateTodo(selectedTodo);
            todos = todoService.getTodos(currentUser);
            newTodo = "";
            selectedTodo = null;
        }
    }

    public void unmarkTodoComplete() {
        if (selectedTodo != null && selectedTodo.isCompleted()) {
            selectedTodo.setCompleted(false);
            System.out.println("Current user update " + currentUser);
            if(currentUser != null)
                selectedTodo.setUser(getCurrentUser());
            todoService.updateTodo(selectedTodo);
            todos = todoService.getTodos(currentUser);
            newTodo = "";
            selectedTodo = null;
        }

    }

    public void deleteTodo(Todo todo) {
        if (todo != null) {
            todoService.deleteTodo(todo);
            todos = todoService.getTodos(currentUser);
        }
    }

    public Todo getSelectedTodo() {
        return selectedTodo;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
