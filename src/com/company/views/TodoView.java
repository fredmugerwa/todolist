package com.company.views;

import com.company.models.Todo;
import com.company.services.TodoService;
import com.company.services.impl.TodoServiceImpl;

import java.util.List;
import java.util.Scanner;

public class TodoView {
    private final TodoService todoService;
    private final Scanner scanner;

    public TodoView() {
        this.todoService = new TodoServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("********* Call Center Todo System *********");
        boolean running = true;

        while (running) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Create Todo");
            System.out.println("2. Read Todos");
            System.out.println("3. Read Todos of state");
            System.out.println("4. Update Todo title");
            System.out.println("5. Update Todo completed state");
            System.out.println("6. Delete Todo");
            System.out.println("7. Exit");
            try{
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                switch (choice) {
                    case 1:
                        createTodo();
                        break;
                    case 2:
                        readTodos();
                        break;
                    case 3:
                        readStateTodos();
                        break;
                    case 4:
                        updateTodoTitle();
                        break;
                    case 5:
                        updateTodoCompletedState();
                        break;
                    case 6:
                        deleteTodo();
                        break;
                    case 7:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }catch (Exception e){
                System.out.println("Invalid choice. Please try again.");
                scanner.nextLine(); // Consume the newline character
            }
        }
    }

    private void createTodo() {
        System.out.print("Enter the todo title: ");
        String title = scanner.nextLine();
        Todo todo = new Todo();
        todo.setTitle(title);
        todoService.createTodo(todo);
    }

    private void readTodos() {
        List<Todo> todos = todoService.readTodos();
        if (todos.isEmpty())
            System.out.println("No todos available.");
        System.out.println(todos);
    }

    private void readStateTodos() {
        System.out.print("Enter the state of the todos to display(true/false): ");
        boolean state = scanner.nextBoolean();
        List<Todo> completedTodos = todoService.readTodos(state);
        if (completedTodos.isEmpty())
            System.out.println("No todos available.");
        System.out.println(completedTodos);
    }

    private void updateTodoTitle() {
        System.out.print("Enter the index of the todo to update: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (index >= 0 && index < todoService.readTodos().size()) {
            Todo todo = todoService.readTodos().get(index);

            System.out.print("Enter the new todo title: ");
            String newTitle = scanner.nextLine();
            todo.setTitle(newTitle);

            todoService.updateTodo(todo);
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }

    private void updateTodoCompletedState() {
        System.out.print("Enter the index of the todo to update: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (index >= 0 && index < todoService.readTodos().size()) {
            Todo todo = todoService.readTodos().get(index);

            System.out.print("Enter the new completed state (true/false): ");
            boolean newCompletedState = scanner.nextBoolean();
            todo.setCompleted(newCompletedState);

            todoService.updateTodo(todo);
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }

    private void deleteTodo() {
        System.out.print("Enter the index of the todo to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        todoService.deleteTodo(index);
    }
}
