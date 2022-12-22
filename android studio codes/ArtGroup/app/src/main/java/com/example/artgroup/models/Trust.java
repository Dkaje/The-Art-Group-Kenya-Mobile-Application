package com.example.artgroup.models;

public class Trust {
    private int image;
    private String tester;

    public Trust(int image, String tester) {
        this.image = image;
        this.tester = tester;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }
}
