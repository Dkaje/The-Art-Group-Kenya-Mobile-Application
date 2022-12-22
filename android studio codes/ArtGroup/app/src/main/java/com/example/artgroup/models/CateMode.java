package com.example.artgroup.models;

public class CateMode {

    String category = null;
    String image = null;

    public CateMode(String type, String image) {
        this.category = type;
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String toString() {
        return category;
    }
}
