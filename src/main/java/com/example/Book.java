package com.example;

import com.example.staticgenerator.templates.entities.IterableRouteEntity;

public class Book implements IterableRouteEntity {
    // Book properties
    @Override
    public String getIdentifier() {
        return String.valueOf(this.id); // Assuming 'id' is a property of Book
    }

    private int id;
    private String title;
    private String author;
    private String summary;

    // Constructor, getters, and setters
    public Book(int id, String title, String author, String summary) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.summary = summary;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getSummary() {
        return summary;
    }
}