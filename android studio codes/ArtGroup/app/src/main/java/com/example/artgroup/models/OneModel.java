package com.example.artgroup.models;

public class OneModel {
    String classification = null;

    public OneModel(String classification) {
        this.classification = classification;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public String toString() {
        return classification;
    }
}