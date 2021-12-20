package com.example.java;

public abstract class TodoTest {
    protected static Todo getTodo() {
        return new Todo("1", "2", "title", "completed");
    }
}
