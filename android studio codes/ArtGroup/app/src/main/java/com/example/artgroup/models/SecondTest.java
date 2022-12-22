package com.example.artgroup.models;

public class SecondTest {
    String category = null;
    String type = null;

    public SecondTest(String category, String type) {
        this.category = category;
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return category + " " + type;
    }
}