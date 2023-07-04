package com.company.models;

public class Todo {
    private String title;
    private boolean completed;

    public Todo() {
    }

    public Todo(String title) {
        this.title = title;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
